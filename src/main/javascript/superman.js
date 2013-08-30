//@require @packageOverrides
Ext.Loader.setConfig({
    enabled: true
});

Ext.require('Spm.view.application.SpmViewport');

Ext.application({
    name: 'Spm',
    controllers: [
        'Errors',
        'MyQueues',
        'MyStatus',
        'Security',
        'Queues'
    ],
    launch: function() {
        Ext.create('Spm.view.application.SpmViewport');
    }
});