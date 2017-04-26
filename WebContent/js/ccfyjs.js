Ext.onReady(function(){
	
	var store = new Ext.data.ArrayStore({

		id : 'selected',
		fields : [ {
			name : 'RID'
		}, {
			name : 'R_JOB_NUMBER'
		}, {
			name : 'R_JOB_NAME'
		}, {
			name : 'R_POST'
		}, {
			name : 'R_DAYS'
		}, {
			name : 'MONEYS'
		}, {
			name : 'R_ENDTIME'
		}, {
			name : 'R_RELEASETIME'
		}],
		proxy : {
			type : 'ajax',
			url : 'getAllMoneyInfo.action',
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
	
	Ext.create("Ext.form.Panel",{
		id:'ccfyFrom',
		bodyPadding:10,
		renderTo:Ext.getBody(),
		border:0,
		items:[{
			xtype:'container',
			anchor:'30%',
			layout:'hbox',
			border:0,
			items:[{
				xtype:'container',
				flex:0.83,
				layout:'anchor',
				items:[{
					xtype:'textfield',
                    fieldLabel: '项目编号',
                    id:'project_number',
                    labelWidth: 60,
                    //store:specialtyStore,
                    name: 'project_number',
                    editable : false,
                    anchor:'96%',
				}]
			},{
                xtype: 'container',
               
                layout: 'anchor',
                items: [{
					xtype:'button',
					width:60,
					text:'确&nbsp;&nbsp;&nbsp;定',
					id:'select',
					handler:function(){
                    	var project_number = Ext.getCmp('project_number').getValue();
                    	Ext.Ajax.request({
                    		url:'getAllInfo.action',
                    		params:{
                    			project_number:project_number,
                    		},
                    		success:function(response){
                    			console.log(response.responseText);
                    			var result = Ext.decode(response.responseText);
                    			
                    			$.each(result,function(i,comm){
                    				console.log(comm);
                    				TCITY = comm['TCITY'];
                    				P_NAME= comm['P_NAME'];
                    				P_DATE= comm['P_DATE'];
                    				S_STAY= comm['S_STAY'];
                    				S_FOOD= comm['S_FOOD'];
                    				S_TRAFFIC= comm['S_TRAFFIC'];
                    				P_NUMBER = comm['P_NUMBER'];
                    				
                    			})
                    			console.log(S_STAY+TCITY+S_FOOD+S_TRAFFIC+P_NAME+P_DATE);
                    			Ext.getCmp('affair_name').setValue(P_NAME);
                    			Ext.getCmp('city_name').setValue(TCITY);
                    			Ext.getCmp('start_date').setValue(P_DATE);
                    			Ext.getCmp('stay_subsidy').setValue(S_STAY);
                    			Ext.getCmp('food_subsidy').setValue(S_FOOD);
                    			Ext.getCmp('traffic_subsidy').setValue(S_TRAFFIC);
                    			 Ext.Msg.alert("提示","提交成功。"); 
                    		},failure:function(){  
                                Ext.Msg.alert("错误","与后台联系的时候出了问题。");  
                            }, 
                            
                        	
                    	})
					}
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
                    readOnly:true,
                    fieldStyle:'color:#999999',
                    //value:P_NAME,
                    name: 'affair_name',
                    editable : false,
                    anchor:'96%',
				}]
			},,{
				xtype:'container',
				flex:1,
				layout:'anchor',
				items:[{
					xtype:'textfield',
                    fieldLabel: '城市名称',
                    readOnly:true,
                    fieldStyle:'color:#999999',
                    id:'city_name',
                    //value:TCITY,
                    labelWidth: 60,
                    name: 'city_name',
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
                    readOnly:true,
                    fieldStyle:'color:#999999',
                    labelWidth: 60,
                    //value:P_DATE,
                    //afterLabelTextTpl: required,
                    allowBlank: false,
                    name: 'start_date',
                    id: 'start_date',
                    format:'Y-m-d',
                    msgTarget: 'side',
                    anchor:'96%'
                }]
			},{
                xtype: 'container',
                flex: 1,
                layout: 'anchor',
                items: [{
                    xtype:'datefield',
                    fieldLabel: '截至时间',
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
			anchor:'80%',
			layout:'hbox',
			margin:'0 0 0 0',
			border:0,
			items:[{
				xtype:'container',
				flex:1,
				layout:'anchor',
				items:[{
					xtype:'panel',
					border:0,
					margin:'-5 0 -10 0',
					html:'<h3 align="left" >每日差补</h3>'
				}]
			}]
		
		},{
			xtype:'container',
			anchor:'75%',
			layout:'hbox',
			margin:'0 0 0 0',
			border:0,
			items:[{
				xtype:'container',
				flex:1,
				layout:'anchor',
				items:[{
					xtype:'textfield',
                    fieldLabel: '住宿补助',
                    readOnly:true,
                    fieldStyle:'color:#999999',
                    //value:S_STAY,
                    id:'stay_subsidy',
                    labelWidth: 60,
                    //store:specialtyStore,
                    name: 'stay_subsidy',
                    editable : false,
                    anchor:'96%',
				}]
			},{
                xtype: 'container',
                flex: 1,
                layout: 'anchor',
                items: [{
					xtype:'textfield',
                    fieldLabel: '伙食补助',
                    readOnly:true,
                    fieldStyle:'color:#999999',
                    id:'food_subsidy',
                    labelWidth: 60,
                    //value:S_FOOD,
                    //store:specialtyStore,
                    name: 'food_subsidy',
                    editable : false,
                    anchor:'96%',
				}]
			},{
                xtype: 'container',
                flex: 1,
                layout: 'anchor',
                items: [{
					xtype:'textfield',
                    fieldLabel: '交通补助',
                    readOnly:true,
                    fieldStyle:'color:#999999',
                    //value:S_TRAFFIC,
                    id:'traffic_subsidy',
                    labelWidth: 60,
                    //store:specialtyStore,
                    name: 'traffic_subsidy',
                    editable : false,
                    anchor:'96%',
				}]
			}]
		
		},{
			xtype:'container',
			anchor:'100%',
			layout:'hbox',
			margin:'10 0 10 0',
			border:0,
			items:[{
				xtype:'container',
				flex:1,
				layout:'anchor',
				items:[{
	                xtype: 'container',
	                flex: 1,
	                layout: 'anchor',
	                items: [{
						xtype:'button',
						text:'确&nbsp;&nbsp;&nbsp;定',
						width:60,
						id:'result',
						handler:function(){

	                    	var end_date = Ext.getCmp('end_date').getValue();
	                    	Ext.Ajax.request({
	                    		url:'getWageInfo.action',
	                    		params:{
	                    			affair_name:P_NAME,
	                    			end_date:end_date,
	                    			city_name:TCITY,
	                    			start_date:P_DATE,
	                    			stay_subsidy:S_STAY,
	                    			food_subsidy:S_FOOD,
	                    			traffic_subsidy:S_TRAFFIC,
	                    			p_number:P_NUMBER
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
                    		url:'ExportInfo.action',
                    		
                    		success:function(response){
                    			
                    			
                    			 Ext.Msg.alert("提示","提交成功。"); 
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
							dataIndex : 'RID',
							align : "center"
						}, {
							header : '工号',
							dataIndex : 'R_JOB_NUMBER',
							menuDisabled : true,
							width : '10%',
							align : "center"
						},{
							header : '姓名',
							dataIndex : 'R_JOB_NAME',
							menuDisabled : true,
							width : '10%',
							align : "center"
						},
						// 可设置是否为该列进行排序
						{
							header : '职务',
							dataIndex : 'R_POST',
							width : '15%',
							menuDisabled : true,
							align : "center"
						},
						{
							header : '天数',
							dataIndex : 'R_DAYS',
							width : '10%',
							menuDisabled : true,
							align : "center",
						},{
							header : '金额',
							dataIndex : 'MONEYS',
							width : '15%',
							menuDisabled : true,
							align : "center",
							format : 'Y-m-d'
						},{
							header : '结算时间',
							dataIndex : 'R_ENDTIME',
							width : '15%',
							menuDisabled : true,
							align : "center",
							format : 'Y-m-d'
						},{
							header : '发放时间',
							dataIndex : 'R_RELEASETIME',
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