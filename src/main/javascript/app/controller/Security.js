Ext.define('Spm.controller.Security', {
    extend: 'Ext.app.Controller',
    alias: 'controller.security',
    requires: [
        'Spm.view.application.LoginWindow'
    ],

    models: [
        'Agent'
    ],
    stores: [
        'AuthenticatedAgent'
    ],

    refs: [
        {
            ref: 'appContainer',
            selector: 'appContainer',
            xtype: 'appContainer'
        }
    ],

    onPerformAuthentication: function (credentials) {
        Ext.Ajax.request({
            url: 'j_spring_security_check',
            params: credentials,
            success: function () {
                this.onAuthenticated(false);
            },
            scope: this
        });
    },

    onAuthenticated: function (alreadyAuthenticated) {
        if (!alreadyAuthenticated) {
            this.getAuthenticatedAgentStore().load({
                callback: this.authenticatedAgentCallback,
                scope: this
            });
        }
    },

    onAuthenticationRequired: function () {
        this.getAppContainer().setVisible(false);
        Ext.create('Spm.view.application.LoginWindow').show();
    },

    authenticatedAgentCallback: function (records, operation, success) {
        if (success) {
            this.fireEvent('authenticated', records[0]);
            this.getAppContainer().setVisible(true);
        }
    },

    startAuthentication: function () {
        this.getAuthenticatedAgentStore().load(
                {
                    callback: this.authenticatedAgentCallback,
                    scope: this
                });
    },

    onLogout: function () {
        Ext.Ajax.request({
            url: 'j_spring_security_logout',
            success: function () {
                this.onAuthenticationRequired();
            },
            scope: this
        });
    },

    init: function () {
        this.listen({
            controller: {
                '#Errors': {
                    authenticationRequired: this.onAuthenticationRequired
                },
                '#Login': {
                    performAuthentication: this.onPerformAuthentication
                }
            },
            component: {
                'headerView': {
                    logout: this.onLogout
                },
                'loginWindow': {
                    accepted: this.onPerformAuthentication
                }
            }
        });
    },

    onLaunch: function () {
        this.startAuthentication();
    }
});
