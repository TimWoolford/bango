Ext.define('Spm.model.WorkItem', {
    extend: 'Ext.data.Model',
    alias: 'model.workItem',

    fields: [
        {
            name: 'workItemId'
        },
        {
            name: 'status'
        }
    ],

    isHeld: function() {
        return 'Held' == this.get('status');
    },

    isAssigned: function() {
        return 'Unassigned' != this.get('status');
    },

    isPullable: function() {
        return 'Unassigned' == this.get('status');
    },

    isAssignedTo: function(agent) {
        return this.agent().get('code') === agent.get('code');
    },

    hasOne: [
        'agent', {model: 'Spm.model.Agent', name: 'agent', associationKey: 'agent', getterName: 'agent'}
    ]
});