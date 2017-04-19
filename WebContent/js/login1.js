function login() {  
    var userLoginPanel = Ext.create('Ext.panel.Panel', {  
        bodyCls: 'bgimage',  
        border : false,  
        defaults:{  
            margin:'58 0'  
        },  
        items:{  
            xtype : 'tabpanel',  
            id : 'loginTabs',  
            activeTab : 0,  
            height : 180,  
            border : false,  
            items:[{  
                title : "身份认证",  
                xtype : 'form',  
                id : 'loginForm',  
                defaults : {  
                    width : 260,  
                    margin: '10 0 0 70'  
                },  
                // The fields  
                defaultType : 'textfield',  
                labelWidth : 40,  
                items: [{  
                    fieldLabel: '用户名',  
                      id:'username',
                    name: 'username',  
                    labelAlign:'right',  
                    labelWidth:65,  
                    maxLength : 30,  
                    emptyText:'请在这里填写用户名',  
                    maxLengthText : '账号的最大长度为30个字符',  
                    blankText:"用户名不能为空，请填写！",//错误提示信息，默认为This field is required!  
                    allowBlank: false  
                },{  
                    fieldLabel: '密&nbsp;&nbsp;&nbsp;码',  
                    name: 'password',  
                    id:'password',
                    inputType:"password",  
                    labelWidth:65,  
                    labelAlign:'right',  
                    emptyText:'请在这里填写密码',  
                    maxLengthText :'密码长度不能超过20',  
                    maxLength : 20,  
                    blankText:"密码不能为空，请填写！",//错误提示信息，默认为This field is required!  
                    allowBlank: false  
                },{  
                    xtype:"combo",  
                    fieldLabel: '角&nbsp;&nbsp;&nbsp;色',  
                    name: 'role',
                    id:'role',
                    labelAlign:'right',  
                    labelWidth:65,  
                    store:["管理员","员工"],//数据源为一数组  
                    emptyText:'请选择角色.',  
                    blankText:"请选择角色！",//错误提示信息，默认为This field is required!  
                    allowBlank: false  
                }, {  
                    id : 'id_reg_panel',  
                    xtype : 'panel',  
                    border : false,  
                    hidden : true,  
                   
                }]  
            }]  
        }  
    });  
      
    var win = Ext.create('Ext.window.Window', {  
        title : '员工后台管理系统',  
        width : 440,  
        height : 300,  
        layout: 'fit',  
        plain : true,  
        modal : true,  
        maximizable : false,  
        draggable : false,  
        closable : false,  
        resizable : false,  
        items: userLoginPanel,  
        // 重置 和 登录 按钮.  
        buttons: [{  
            text: '重置',  
            iconCls : 'Wrench',  
            handler: function() {  
                Ext.Msg.alert('提示', '重置！');  
            }  
        }, {  
            text: '登录',  
            iconCls : 'User',  
            handler: function() {
            	var form = Ext.getCmp("loginForm").getForm();
            	var formData = form.getValues();
            	var username = Ext.getCmp("username").getValue();
            	var password = Ext.getCmp("password").getValue();
            	Ext.Ajax.request({     
            	       url:'getLogin.action',  
            	       data : JSON.stringify(formData),
            	       params:{  
            	    	   username:username,
            	    	   password:password,
            	    	   role:Ext.getCmp("role").getValue(),
            	        }, 
            	        success: function(resp,opts) {
            	        	console.log(resp.responseText);if(resp.responseText =="true"){
            	        		
								Ext.Msg.alert("提示", "登录成功",
										function() {
									window.location.href='../yggl/homepage.action?username='+username+'';
									win.close();
										});
							}else{
								
									Ext.Msg.alert("提示", "登录成功",
											function() {
										window.location.href='../employee/employee.action?username='+username+'';
										win.close();
											});
							}
            	                              
            	                     },   
            	                     failure: function(resp,opts) { 
            	                    	 console.log("ddddddddddddd");
            	                             Ext.Msg.alert('错误',"输入信息错误，请重新输入！");   
            	                      }     
            	         
            	      });
            	
				/*var form = Ext.getCmp("loginForm").getForm();
				if (form.isValid()) {
					var formData = form.getValues();
					$.ajax({
						async : false,
						type : "post",
						url : "getLogin.action",
						data : JSON.stringify(formData),
						dataType : "json",
						contentType : "application/json; charset=UTF-8",
						success : function(form,options) {
							console.log(form);
							if(form =="true"){
								Ext.Msg.alert("提示", "登录成功",
										function() {
									window.location.href='../yggl/homepage.action';
									win.close();
										});
							}else{
								
									Ext.Msg.alert("提示", "登录成功",
											function() {
										window.location.href='../employee/employee.action';
										win.close();
											});
							}
							
						},
						failure : function(form, options) {
							console.log("dsfsdfsffsf");
						}
					
					});
				}*/
			
			
           }  
        }]  
    }).show();  
};  