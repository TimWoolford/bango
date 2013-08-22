/*
 * File: app/view/AgentStatusPanel.js
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

Ext.define('Spm.view.AgentStatusPanel', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.agentStatusPanel',

    cls: 'status-panel',
    height: 126,
    margin: '',
    width: 266,
    layout: {
        align: 'stretch',
        type: 'vbox'
    },
    bodyPadding: 5,
    iconCls: 'icon-status',
    title: 'My Status',

    initComponent: function() {
        var me = this;

        Ext.applyIf(me, {
            items: [
                {
                    xtype: 'dataview',
                    flex: 1,
                    id: 'inbox-stats-view',
                    itemTpl: Ext.create('Ext.XTemplate', 
                        '<div>',
                        '    <div class="{[this.labelClass(values.isAvailable)]}">{[this.labelText(values.isAvailable)]}</div>',
                        '    <div id="toggle-button-wrapper"></div>',
                        '	<div id="inbox-stats">',
                        '		<div id="inbox-active-value">{activeCount}</div><div id="inbox-active-label">Active Items:</div>',
                        '		<div id="inbox-hold-value">{heldCount}</div><div id="inbox-hold-label">Held Items:</div>',
                        '	</div>',
                        '</div>',
                        {
                            labelClass: function(isAvailable) {
                                return isAvailable ? "availability-indicator-on": "availability-indicator-off";
                            },
                            labelText: function(isAvailable) {
                                return isAvailable ?  "Available" : "Unavailable";
                            }
                        }
                    ),
                    store: 'AuthenticatedAgent',
                    listeners: {
                        refresh: {
                            fn: me.renderToggleButton,
                            scope: me
                        },
                        click: {
                            delegate: 'a#toggle-button',
                            fn: me.onToggleAvailability,
                            element: 'el',
                            scope: me
                        }
                    }
                }
            ]
        });

        me.callParent(arguments);
    },

    renderToggleButton: function(dataview, eOpts) {
        if(!dataview.getStore().isLoading()) {
            Ext.create('Ext.button.Button', {
                renderTo: 'toggle-button-wrapper',
                text: 'Toggle Availability',
                width: '100%',
                id : 'toggle-button'
            });
        }
    },

    onToggleAvailability: function() {
        Spm.application.fireEvent('toggleAvailability');
    }

});