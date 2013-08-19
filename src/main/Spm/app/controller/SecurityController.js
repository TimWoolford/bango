/*
 * File: app/controller/SecurityController.js
 *
 * This file was generated by Sencha Architect version 2.2.2.
 * http://www.sencha.com/products/architect/
 *
 * This file requires use of the Ext JS 4.2.x library, under independent license.
 * License of Sencha Architect does not include license for Ext JS 4.2.x. For more
 * details see http://www.sencha.com/license or contact license@sencha.com.
 *
 * This file will be auto-generated each and everytime you save your project.
 *
 * Do NOT hand edit this file.
 */

Ext.define('Spm.controller.SecurityController', {
    extend: 'Ext.app.Controller',
    alias: 'controller.securityController',

    stores: [
        'AuthenticatedAgent'
    ],
    views: [
        'MainContainer'
    ],

    refs: [
        {
            ref: 'mainContainer',
            selector: 'mainContainer',
            xtype: 'mainContainer'
        },
        {
            ref: 'loginWindow',
            selector: 'loginWindow',
            xtype: 'loginWindow'
        }
    ],

    onPerformAuthentication: function(credentials) {
        Ext.Ajax.request({
            url: 'j_spring_security_check',
            params: credentials,
            success: function(response) {
                Spm.application.fireEvent('authenticated');
            }
        });
    },

    onAuthenticated: function() {
        this.getAuthenticatedAgentStore().load();

        this.getMainContainer().setVisible(true);
        this.getLoginWindow().close();
    },

    onAuthenticationRequired: function(response) {
        this.getMainContainer().setVisible(false);
        Ext.create('Spm.view.LoginWindow').show();
    },

    onStartAuthentication: function() {
        this.getAuthenticatedAgentStore().load();
    },

    onLogout: function() {
        Ext.Ajax.request({
            url: 'j_spring_security_logout',
            success: function(response) {
                Spm.application.fireEvent('authenticationRequired');
            }
        });
    },

    init: function(application) {
        application.on({
            performAuthentication: {
                fn: this.onPerformAuthentication,
                scope: this
            },
            authenticated: {
                fn: this.onAuthenticated,
                scope: this
            },
            authenticationRequired: {
                fn: this.onAuthenticationRequired,
                scope: this
            },
            startAuthentication: {
                fn: this.onStartAuthentication,
                scope: this
            },
            logout: {
                fn: this.onLogout,
                scope: this
            }
        });
    }

});
