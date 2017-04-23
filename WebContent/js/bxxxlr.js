Ext.onReady(function(){
	//报销数据
	var bxstore = new Ext.data.ArrayStore({

		id : 'bxstore',
		fields : [ {
			name : 'RID'
		}, {
			name : 'LOG_NUMBER'
		}, {
			name : 'RNAME'
		}, {
			name : 'INVOICE_TYPE'
		}, {
			name : 'RMONEY'
		}, {
			name : 'RDATE'
		}],
		proxy : {
			type : 'ajax',
			url : 'getReimbursementInfo.action',
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
	
	//借款数据
	var jkstore = new Ext.data.ArrayStore({

		id : 'jkstore',
		fields : [ {
			name : 'LID'
		}, {
			name : 'LOG_NUMBER'
		}, {
			name : 'LNAME'
		}, {
			name : 'LOAN_REASON'
		}, {
			name : 'LOAN_MONEY'
		}, {
			name : 'LOAN_DATE'
		}, {
			name : 'ISREPAYMENT'
		}],
		proxy : {
			type : 'ajax',
			url : 'getloanInfo.action',
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
		renderTo:Ext.getBody(),
		border:0,
		items:[{
			xtype:'form',
			title:'报销信息录入 ',
			height:260,
			items:[{
				xtype:'panel',
				layout:'hbox',
				border:0,
				width:'100%',
				items:[{
					xtype:'panel',
					border:0,
					width:"25%",
					items:[{
						xtype:'textfield',
	                    fieldLabel: '工&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号',
	                    id:'bx_log_number',
	                    labelWidth: 60,
	                    margin:'40 0 0 20',
	                    name: 'bx_log_number',
	                    editable : false,
					},{
						xtype:'textfield',
	                    fieldLabel: '发票类型',
	                    id:'bx_invoice',
	                    labelWidth: 60,
	                    margin:'20 0 0 20',
	                    name: 'bx_invoice',
	                    editable : false,
					},{
						xtype:'textfield',
	                    fieldLabel: '金&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;额',
	                    id:'bx_maney',
	                    labelWidth: 60,
	                    margin:'20 0 0 20',
	                    name: 'bx_maney',
	                    editable : false,
					},{
						xtype:'button',
	                    text:'按&nbsp;&nbsp;&nbsp;钮',
	                    id:'add',
	                    width:50,
	                    margin:'20 0 0 20',
	                    name: 'add',
	                    handler:function(){
	                    	var bx_log_number = Ext.getCmp('bx_log_number').getValue();
	                		var bx_invoice = Ext.getCmp('bx_invoice').getValue();
	                		var bx_maney = Ext.getCmp('bx_maney').getValue();
	                    	Ext.Ajax.request({
	                    		url:'addReimbursementInfo.action',
	                    		params:{
	                    				bx_log_number:bx_log_number,
	                    				bx_invoice:bx_invoice,
	                    				bx_maney:bx_maney,
	                    		},
	                    		success:function(response){
	                    				  var store2 = Ext.getStore('bxstore');
	                    				  store2.reload();
	                    			 Ext.Msg.alert("提示","提交成功。"); 
	                    		},failure:function(){  
	                                Ext.Msg.alert("错误","与后台联系的时候出了问题。");  
	                            },  
	                    	})
	                    }
					}]
				},{
					xtype:'panel',
					border:0,
					width:'100%',
					items:[{
						xtype : 'grid',
						columnLines : true,
						id : "gridd",
						width : "75%",
						store : bxstore,
						columns : [{
									header : '序号',
									width : '10%',
									menuDisabled : true,// 去掉表格下拉排序
									dataIndex : 'RID',
									align : "center"
								}, {
									header : '工号',
									dataIndex : 'LOG_NUMBER',
									menuDisabled : true,
									width : '30%',
									align : "center"
								},{
									header : '姓名',
									dataIndex : 'RNAME',
									menuDisabled : true,
									width : '15%',
									align : "center"
								},
								// 可设置是否为该列进行排序
								{
									header : '发票类型',
									dataIndex : 'INVOICE_TYPE',
									width : '15%',
									menuDisabled : true,
									align : "center",
									format : 'Y-m-d',
								},
								{
									header : '报销金额',
									dataIndex : 'RMONEY',
									width : '15%',
									menuDisabled : true,
									align : "center",
									
								},{
									header : '报销时间',
									dataIndex : 'RDATE',
									width : '15%',
									menuDisabled : true,
									align : "center",
									format : 'Y-m-d',
									flex : 1
								}]
					}]
				}]
			}]
		},{
			xtype:'form',
			title:'借款信息录入 ',
			border:0,
			items:[{
				xtype:'panel',
				layout:'hbox',
				border:0,
				width:'100%',
				items:[{
					xtype:'panel',
					border:0,
					width:'25%',
					items:[{

						xtype:'textfield',
	                    fieldLabel: '工&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号',
	                    id:'jk_log_numbers',
	                    labelWidth: 60,
	                    margin:'20 0 0 20',
	                    name: 'jk_log_numbers',
	                    editable : false,
					
					},{

						xtype:'textfield',
	                    fieldLabel: '借款金额',
	                    id:'jk_money',
	                    labelWidth: 60,
	                    margin:'20 0 0 20',
	                    name: 'jk_money',
	                    editable : false,
					
					},{

						xtype:'textareafield',
	                    fieldLabel: '借款事由',
	                    id:'jk_loan',
	                    labelWidth: 60,
	                    margin:'20 0 0 20',
	                    //store:specialtyStore,
	                    name: 'jk_loan',
	                    height:100,
	                    editable : false,
	                    anchor:'98.5%',
					
					},{
						xtype:'button',
	                    text:'按&nbsp;&nbsp;&nbsp;钮',
	                    id:'add_maney',
	                    margin:'20 0 0 20',
	                    width:50,
	                    name: 'add',
	                    handler:function(){

	                    	var jk_log_numbers = Ext.getCmp('jk_log_numbers').getValue();
	                		var jk_loan = Ext.getCmp('jk_loan').getValue();
	                		var jk_money = Ext.getCmp('jk_money').getValue();
	                    	Ext.Ajax.request({
	                    		url:'addLoanInfo.action',
	                    		params:{
	                    				jk_log_numbers:jk_log_numbers,
	                    				jk_loan:jk_loan,
	                    				jk_money:jk_money
	                    		},
	                    		success:function(response){
	                    				  var store2 = Ext.getStore('jkstore');
	                    				  store2.reload();
	                    			 Ext.Msg.alert("提示","提交成功。"); 
	                    		},failure:function(){  
	                                Ext.Msg.alert("错误","与后台联系的时候出了问题。");  
	                            },  
	                    	})
	                    
	                    }
					}]
				},{
					xtype:'panel',
					border:0,
					width:'100%',
					items:[{

						xtype : 'grid',
						columnLines : true,
						id : "jkgrid",
						width : "75%",
						store : jkstore,
						columns : [{
									header : '序号',
									width : '8%',
									menuDisabled : true,// 去掉表格下拉排序
									dataIndex : 'LID',
									align : "center"
								}, {
									header : '工号',
									dataIndex : 'LOG_NUMBER',
									menuDisabled : true,
									width : '13%',
									align : "center"
								},{
									header : '姓名',
									dataIndex : 'LNAME',
									menuDisabled : true,
									width : '13%',
									align : "center"
								},{
									header : '借款事由',
									dataIndex : 'LOAN_REASON',
									menuDisabled : true,
									width : '26%',
									align : "center"
								},
								// 可设置是否为该列进行排序
								{
									header : '借款金额',
									dataIndex : 'LOAN_MONEY',
									width : '15%',
									menuDisabled : true,
									align : "center",
									format : 'Y-m-d',
								},
								{
									header : '借款时间',
									dataIndex : 'LOAN_DATE',
									width : '15%',
									menuDisabled : true,
									align : "center",
									
								},{
									header : '是否还款',
									dataIndex : 'ISREPAYMENT',
									width : '10%',
									menuDisabled : true,
									align : "center",
									format : 'Y-m-d',
									flex : 1
								}]
					}]
				}]
			}]
		}]
	})
})