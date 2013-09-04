Ext.define('Spm.view.navigation.SearchPanel', {
    extend: 'Ext.form.Panel',
    alias: 'widget.searchPanel',
    requires: ['Ext.form.RadioGroup'],

    cls: 'search-panel',
    iconCls: 'icon-search',
    title: 'Search',
    layout: 'vbox',

    initComponent: function () {
        var me = this;

        Ext.applyIf(me, {
            items: [
                {
                    xtype: 'radiogroup',
                    columns: 1,
                    items: [
                        {
                            boxLabel: 'Service Problem ID',
                            name: 'searchType',
                            inputValue: 'serviceProblemId',
                            checked: true
                        },
                        {
                            boxLabel: 'Service ID',
                            name: 'searchType',
                            inputValue: 'serviceId'
                        },
                        {
                            boxLabel: 'Directory Number',
                            name: 'searchType',
                            inputValue: 'directoryNumber'
                        },
                        {
                            boxLabel: 'MSP ID',
                            name: 'searchType',
                            inputValue: 'mspId'
                        }
                    ]
                },
                {
                    xtype: 'container',
                    margin: 3,
                    width: '100%',
                    defaults: {
                        width: '100%'
                    },
                    items: [
                        {
                            xtype: 'textfield'
                        },
                        {
                            xtype: 'button',
                            text: 'Search',
                            handler: me.onSearch,
                            scope: me
                        }
                    ]
                }


            ]});

        me.callParent(arguments);
    },

    onSearch: function() {
        var radioGroup = this.down('radiogroup');
        var textField = this.down('textfield');

        this.fireEvent('searchStarted', Ext.apply(radioGroup.getValue(), {searchParameter: textField.getValue()}));
    }
});