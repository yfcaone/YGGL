Ext.onReady(function(){
	
	var store = new Ext.data.ArrayStore({

		id : 'selected',
		fields : [ {
			name : 'TID'
		}, {
			name : 'TAFFAIR'
		}, {
			name : 'JOB_NUMBER'
		}, {
			name : 'TNAME'
		}, {
			name : 'DPOST'
		}, {
			name : 'TDATE'
		}],
		proxy : {
			type : 'ajax',
			url : 'getTravelInfo.action',
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
	
	 /**
	  * 城市等级
	  */
	 var city_grade=Ext.create('Ext.data.Store',{     
	 	 storeId:'specialtyStore',
	    	    fields:['T_CLEVEL_CODE','T_CLEVEL'],
	    	     proxy: {
	       			    type: 'ajax',
	         			url: 'getCityGrade.action'
	     		},
	     autoLoad: true
	 });
	 /**
	  * 城市
	  */
	 var city=Ext.create('Ext.data.Store',{
	 	 storeId:'dqtzStore',
	    	    fields:['CI_CODE','CITY'],
	    	     proxy: {
	       			    type: 'ajax',
	         			url: 'getCity.action'
	     		},
	     		listeners: {  
	        		'beforeload': function (store, op, options) {  
	            		var params = {  
	                	city:Ext.getCmp('city_grade').getValue()
	            			};  
	            		Ext.apply(store.proxy.extraParams, params);   
	       						 }  
	    		} ,
	     		autoLoad: false
	 });
	 
	 /**
	  *部门
	  */
	 var department=Ext.create('Ext.data.Store',{     
	 	 storeId:'specialtyStore',
	    	    fields:['DEPARTMENT_CODE','DEPARTMENT'],
	    	     proxy: {
	       			    type: 'ajax',
	         			url: 'getDepartment.action'
	     		},
	     autoLoad: true
	 });
	 /**
	  * 职位
	  */
	 var post_name=Ext.create('Ext.data.Store',{
	 	 storeId:'dqtzStore',
	    	    fields:['DPOST_CODE','DPOST'],
	    	     proxy: {
	       			    type: 'ajax',
	         			url: 'getPost.action'
	     		},
	     		listeners: {  
	        		'beforeload': function (store, op, options) {  
	            		var params = {  
	                	department:Ext.getCmp('department_name').getValue()
	            			};  
	            		Ext.apply(store.proxy.extraParams, params);   
	       						 }  
	    		} ,
	     		autoLoad: false
	 });
	Ext.create('Ext.form.Panel',{
		id:'ccluFrom',
		bodyPadding:10,
		renderTo:Ext.getBody(),
		border:0,
		items:[{
			xtype:'container',
			anchor:'50%',
			layout:'hbox',
			border:0,
			items:[{
				xtype:'container',
				flex:1,
				layout:'anchor',
				items:[{
					xtype:'combobox',
                    fieldLabel: '城市等级',
                    id:'city_grade',
                    labelWidth: 60,
                    store:city_grade,
                    valueField:'T_CLEVEL_CODE',
                    displayField:'T_CLEVEL',
                    emptyText : '--请选择城市等级--',
                    name: 'city_grade',
                    editable : false,
                    anchor:'96%',
                    listeners:{
         				scope: this,
         			 'select': function(){
         			 Ext.getCmp("city").clearValue();
         			city.load();}
   					}
				}]
			},{
                xtype: 'container',
                flex: 1,
                layout: 'anchor',
                items: [{
                    xtype:'combobox',
                    fieldLabel: '城&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;市',
                    id:'city',
                    labelWidth: 60,
                    store:city,
                    valueField:'CI_CODE',
                    displayField:'CITY',
                    emptyText : '--请选择城市--',
                    name: 'city',
                    editable : false,
                    anchor:'96%',
                   
                }]
			}]
		},{

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
                    fieldLabel: '项目名称',
                    id:'affair_name',
                    labelWidth: 60,
                    name: 'affair_name',
                    editable : false,
                    anchor:'96%',
				}]
			},{
                xtype: 'container',
                flex: 1,
                layout: 'anchor',
                items: [{
                    xtype:'datefield',
                    fieldLabel: '出发时间',
                    labelWidth: 60,
                    //afterLabelTextTpl: required,
                    allowBlank: false,
                    name: 'go_date',
                    id: 'go_date',
                    format:'Y-m-d',
                    msgTarget: 'side',
                    anchor:'96%'
                }]
			},{
				xtype:'container',
				flex:1,
				layout:'anchor',
				items:[{
					xtype:'combobox',
                    fieldLabel: '部门名称',
                    id:'department_name',
                    labelWidth: 60,
                    store:department,
                    valueField:'DEPARTMENT_CODE',
                    displayField:'DEPARTMENT',
                    emptyText : '--请选择部门--',
                    name: 'department_name',
                    editable : false,
                    anchor:'96%',
                    listeners:{
         				scope: this,
         			 'select': function(){
         			 Ext.getCmp("post_name").clearValue();
         			post_name.load();}
   					}
				}]
			},{
                xtype: 'container',
                flex: 1,
                layout: 'anchor',
                items: [{
                    xtype:'combobox',
                    fieldLabel: '职&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;务',
                    id:'post_name',
                    labelWidth: 60,
                    store:post_name,
                    valueField:'DPOST_CODE',
                    displayField:'DPOST',
                    emptyText : '--请选择职务--',
                    name: 'post_name',
                    editable : false,
                    anchor:'96%',
                   
                }]
			}]
		
		},{

			xtype:'container',
			anchor:'30%',
			layout:'hbox',
			margin:'10 0 0 0',
			border:0,
			items:[{
				xtype:'container',
				flex:.84,
				layout:'anchor',
				items:[{
					xtype:'textfield',
                    fieldLabel: '工&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号',
                    id:'job_number',
                    labelWidth: 60,
                    //store:specialtyStore,
                    name: 'job_number',
                    editable : false,
                    anchor:'96%',
				}]
			},{
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
                    	var city = Ext.getCmp('city').getValue();
                		var affair_name = Ext.getCmp('affair_name').getValue();
                		var go_date = Ext.getCmp('go_date').getValue();
                		var post_name = Ext.getCmp('post_name').getValue();
                		var job_number = Ext.getCmp('job_number').getValue();
                    	Ext.Ajax.request({
                    		url:'addTravelInfo.action',
                    		params:{
                        			city:city,
                        			affair_name:affair_name,
                        			go_date:go_date,
                        			post_name:post_name,
                        			job_number:job_number
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
		
		},{
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
						Ext.Ajax.request({
                    		url:'DaoChu.action',
                    		
                    		success:function(response){
                    			
                    			 Ext.Msg.alert("提示","导出成功。"); 
                    		},failure:function(){  
                                Ext.Msg.alert("错误","与后台联系的时候出了问题。");  
                            },  
                    	})
					}
				}],
				columns : [{
							header : '序号',
							width : '10%',
							menuDisabled : true,// 去掉表格下拉排序
							dataIndex : 'TID',
							align : "center"
						}, {
							header : '项目名称',
							dataIndex : 'TAFFAIR',
							menuDisabled : true,
							width : '30%',
							align : "center"
						},{
							header : '工号',
							dataIndex : 'JOB_NUMBER',
							menuDisabled : true,
							width : '15%',
							align : "center"
						},
						// 可设置是否为该列进行排序
						{
							header : '姓名',
							dataIndex : 'TNAME',
							width : '15%',
							menuDisabled : true,
							align : "center"
						},
						{
							header : '职务',
							dataIndex : 'DPOST',
							width : '15%',
							menuDisabled : true,
							align : "center",
						},{
							header : '出发时间',
							dataIndex : 'TDATE',
							width : '15%',
							menuDisabled : true,
							align : "center",
							format : 'Y-m-d',
							flex : 1
						}]
			}]

		
		}]
	})
})

