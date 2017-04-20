 var store1 = Ext.create('Ext.data.Store', // 公告内容数据
            		{
            			id : "gridd1",
            			fields : ['VID','VNAME', 'VACCOUNT', 'VPASSWORD','VDATE'],
            			proxy : {
            				type : 'ajax',
            				url : 'getUserInfo.action',
            				reader : {
            					type : 'json',
            					totalProperty : 'totalCount',
            					root : 'list'
            				}
            			},
            			listeners : {
            				'beforeload' : function(store, op, options) {
            					var params = {
            						date : Ext.getCmp('date').getValue(),
            						name : Ext.getCmp('name').getValue()
            					};
            					Ext.apply(store.proxy.extraParams, params);
            				}
            			},
            			pageSize : 10,
            			autoLoad : true
            		});	 