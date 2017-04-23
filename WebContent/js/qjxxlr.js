Ext.onReady(function(){
	
	var store = new Ext.data.ArrayStore({

		id : 'selected',
		fields : [ {
			name : 'LID'
		}, {
			name : 'LOG_NUMBER'
		}, {
			name : 'LNAME'
		}, {
			name : 'START_TIME'
		}, {
			name : 'END_TIME'
		}, {
			name : 'DAYS'
		}],
		proxy : {
			type : 'ajax',
			url : 'getLeaveInfo.action',
			reader : {
				type : 'json',
				root : 'list'
			}
		},
		listeners:{

			  'beforeload': function (store, op, options) {
                var params = {
                  //name: Ext.getCmp('username').getValue()
                };
                Ext.apply(store.proxy.extraParams, params);
            }
		},
		pageSize : 10,
		autoLoad : true
	});
	
	
	Ext.create('Ext.form.Panel',{
		id:'ccluFrom',
		bodyPadding:10,
		renderTo:Ext.getBody(),
		border:0,
		items:[{

			xtype:'container',
			anchor:'100%',
			margin:'10 0 0 0',
			layout:'hbox',
			border:0,
			items:[{
				xtype:'container',
				flex:1,
				layout:'anchor',
				items:[{
					xtype:'textfield',
                    fieldLabel: '工&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号',
                    id:'log_number',
                    labelWidth: 60,
                    name: 'log_number',
                    editable : false,
                    anchor:'96%',
				}]
			},{
                xtype: 'container',
                flex: 1,
                layout: 'anchor',
                items: [{
                    xtype:'datefield',
                    fieldLabel: '开始时间',
                    labelWidth: 60,
                    //afterLabelTextTpl: required,
                    allowBlank: false,
                    name: 'start_date',
                    id: 'start_date',
                    format:'Y-m-d',
                    msgTarget: 'side',
                    anchor:'96%'
                }]
			},{
				xtype:'container',
				flex:1,
				layout:'anchor',
				items:[{
                    xtype:'datefield',
                    fieldLabel: '结束时间',
                    labelWidth: 60,
                    //afterLabelTextTpl: required,
                    allowBlank: false,
                    name: 'end_date',
                    id: 'end_date',
                    format:'Y-m-d',
                    msgTarget: 'side',
                    anchor:'96%'
                }]
			}]
		
		},{

			xtype:'container',
			anchor:'100%',
			layout:'hbox',
			margin:'10 0 0 0',
			border:0,
			items:[{
				xtype:'container',
				flex:.84,
				layout:'anchor',
				items:[{
					xtype:'textareafield',
                    fieldLabel: '请假事由',
                    id:'leave_cause',
                    labelWidth: 60,
                    //store:specialtyStore,
                    name: 'leave_cause',
                    editable : false,
                    anchor:'98.5%',
				}]
			}]
		
		},{
			xtype:'container',
			anchor:'7%',
			layout:'hbox',
			margin:'10 0 10 0',
			border:0,
			items:[{
                xtype: 'container',
                flex: .16,
                layout: 'anchor',
                items: [{
                    xtype:'button',
                    text:'确&nbsp;&nbsp;定',
                    id:'create',
                    
                    labelWidth: 60,
                    name: 'create',
                    anchor:'96%',
                    handler:function(){
                    	var log_number = Ext.getCmp('log_number').getValue();
                		var start_date = Ext.getCmp('start_date').getValue();
                		var end_date = Ext.getCmp('end_date').getValue();
                		var leave_cause = Ext.getCmp('leave_cause').getValue();
                    	Ext.Ajax.request({
                    		url:'addLeaveInfo.action',
                    		params:{
                        			log_number:log_number,
                        			start_date:start_date,
                        			end_date:end_date,
                        			leave_cause:leave_cause
                    		},
                    		success:function(response){
                    			
                    				  var store2 = Ext.getStore('selected');
                    				  store2.reload();
                    			
                    			 Ext.Msg.alert("提示","提交成功。"); 
                    		},failure:function(){  
                                Ext.Msg.alert("错误","与后台联系的时候出了问题。");  
                            },  
                    	})
                    
                    }
                   
                }]
			
			}]
		}/*,{
			xtype:'label',
			border:0,
			width: '100%',
			border:0,
			width:4,
			margin:'-10 0 0 0',
			html:'<hr style="color: white;">'
		}*/,{
			xtype : "form",
			
			border : false,
			id : "gridform",
			items : [{
				xtype : 'grid',
				columnLines : true,
				id : "gridd",
				width : "100%",
				store : store,
				tbar : [{
					xtype : "button",
					text : "删除",
					iconCls : "Delete",
					handler : function() {}
				}, '->', {
					xtype : 'button',
					text : '导出',
					iconCls : "Bulletmagnify",
					handler : function() {
						
					}
				}],
				columns : [{
							header : '序号',
							width : '10%',
							menuDisabled : true,// 去掉表格下拉排序
							dataIndex : 'LID',
							align : "center"
						}, {
							header : '工号',
							dataIndex : 'LOG_NUMBER',
							menuDisabled : true,
							width : '30%',
							align : "center"
						},{
							header : '姓名',
							dataIndex : 'LNAME',
							menuDisabled : true,
							width : '15%',
							align : "center"
						},
						// 可设置是否为该列进行排序
						{
							header : '开始时间',
							dataIndex : 'START_TIME',
							width : '15%',
							menuDisabled : true,
							align : "center",
							format : 'Y-m-d',
						},
						{
							header : '结束时间',
							dataIndex : 'END_TIME',
							width : '15%',
							menuDisabled : true,
							align : "center",
							format : 'Y-m-d'
						},{
							header : '天数',
							dataIndex : 'DAYS',
							width : '15%',
							menuDisabled : true,
							align : "center",
							flex : 1
						}]
			}]

		
		}]
	})
})