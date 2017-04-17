  Ext.onReady(function () {
            //使用Viewport布局
            var mypanel = new Ext.container.Viewport({
                layout: 'border',//表格布局
                items: [
                    north,
                    west,
                    centerWin,
                    south
                ]
            });
        });
        //编写 各个模块
        //编写north panel
        var north = new Ext.panel.Panel({
            region: 'north',
            title: '员工后台管理系统',
           
            bbar: [{
                text: '行政办公'
            }, '-', {
                text: '电话通信'
            }, '-', {
                text: '客户管理'
            }, '-', {
                text: '人力资源管理'
            }, '-', {
                text: '进销存管理'
            }, '-', {
                text: 'VIP客户管理'
            }, '-', {
                text: '业绩管理',
            }, {
                xtype: "tbfill"
            }, {
                pressed: false, text: '刷新'
            }, {
                pressed: false, text: '帮助'
            }, {
                pressed: false, text: '退出'
            }, {
                xtype: "combo"
            }]
        });//north panel 编写

        //编写 west panel
        var west = new Ext.panel.Panel({
            title: '信息管理栏',
            region: 'west',
            collapsible: true,
            split: true,
            width: 200,
            margins: 1,
            //添加两块面板
            layout: 'auto',

            items: [{
                title: "员工管理",
                height: 285,
                collapsible: true,
                layout: 'accordion',//使用手风琴进行布局
                items: [{
                    title: '在外员工信息管理',
                    items: [{
                        xtype: 'treepanel',
                        border: 0,
                        rootVisible: false,//根节点是否可见
                        root: {
                            children: [
                                {text: '未评在外员工信息', leaf: true},
                                {text: '添加在外员工信息', leaf: true},
                                {text: '已评在外员工信息', leaf: true},
                                {text: '客户信息', leaf: true},
                                {text: 'VIP客户信息', leaf: true},
                                {text: '销售信息', leaf: true}
                            ]
                        },
                        listeners:{
                            //如果项被点击的话
                            itemclick:function(view,record,item){
                                //如果是叶子，对应上面的leaf==true
                                if(record.data.leaf){
                                    //如果没有与当前点击项目id相同的的标签页的话
                                	if(record.data.text=="未评在外员工信息"){
                                		$('#cenIF').attr("src","zwygxx.action");
                                	}else if(record.data.text=="添加在外员工信息"){
                                		$('#cenIF').attr("src","addEmplInfo.action");
                                	}else if(record.data.text=="已评在外员工信息"){
                                		$('#cenIF').attr("src","ypzwygxx.action");
                                	}
                                }
                            }
                        },
                    }],
                }, {
                    title: 'VIP客户信息管理',
                    items: [{
                        xtype: 'treepanel',
                        border: 0,
                        rootVisible: false,//根节点是否可见
                        root: {
                            children: [
                                {text: '收购信息', leaf: true},
                                {text: '订单信息', leaf: true},
                                {text: '产品信息', leaf: true},
                                {text: '客户信息', leaf: true},
                                {text: 'VIP客户信息', leaf: true},
                                {text: '销售信息', leaf: true}
                            ]
                        }
                    }]
                }, {
                    title: '商品信息管理系统',
                    items: [{
                        xtype: 'treepanel',
                        border: 0,
                        rootVisible: false,//根节点是否可见
                        root: {
                            children: [
                                {text: '收购信息', leaf: true},
                                {text: '订单信息', leaf: true},
                                {text: '产品信息', leaf: true},
                                {text: '客户信息', leaf: true},
                                {text: 'VIP客户信息', leaf: true},
                                {text: '销售信息', leaf: true}
                            ]
                        }
                    }]
                }],
            }, {
                collapsible: true,
                style: 'margin-top: 3px;',
                height: 205,
                title: "未读信息",
                items: [{
                    xtype: 'treepanel',
                    border: 0,
                    rootVisible: false,//根节点是否可见
                    root: {
                        children: [
                            {text: 'xxx信息消费记录', leaf: true},
                            {text: '财政支出信息', leaf: true},
                            {text: '产品信息', leaf: true},
                            {text: '客户建议信息', leaf: true}
                        ]
                    }
                }]
            }]
        });
        //编写 west panel
        //定义表格数据
        var datas = [];
        //编写 center panel
var centerWin = Ext.create('Ext.panel.Panel', {
        	id:'centerWin',
    	 //   title: 'Hello',
    	    region:'center',
    	    height: '100%',
    	    x:275,
    	    y:0,
    	    width: '80%',
    	    layout: 'fit',
    	    tbar:[{
                text:'文件',
                menu:new Ext.menu.Menu({
                    ignoreParentCilcks:true,
                    items:[{
                        text:'打开',handler:win
                    },{
                        text:'保存'
                    },{
                        text:'退出'
                    }]
                })
            },'-',{
                text:'编辑'
            },'-',{
                text:'处理'
            },'-',{
                text:'工程'
            },'-',{
                text:'视图'
            },'-',{
                text:'窗口'
            },'-',{
                text:'帮助'
            }],
    	    items: [{
	        	width:'100%',
	        	html:'<iframe id=cenIF src=zwygxx.action scrolling=yes height=600 width=100%></iframe>'
	        }]	
    	});

   
        var south = new Ext.panel.Panel({
            id:'south',
            region: 'south',
            height: 30,
            bbar: [{
                text: '工具栏'
            }, '-', {
                xtype: "tbfill"
            }, '-', {
                text: 'XXX后台管理系统'
            }, '-', {
                text: "XXX信息技术有限公司"
            }, '-', {
                text: '与我们练习'
            }]
        });//south panel
        // 创建修改窗体
        function win() {
            Ext.QuickTips.init();
            Ext.form.Field.prototype.msgTarget = 'side';
            //提交按钮处理方法
            var btnsubmitclick = function () {
                if (form.getForm().isValid()) {
                    //通常发送到服务器端获取返回值再进行处理，我们在以后的教程中再讲解表单与服务器的交互问题。
                    Ext.Msg.alert("提示", "登陆成功!");
                }
            }
            //重置按钮"点击时"处理方法
            var btnresetclick = function () {
                form.getForm().reset();
            }
            
            
            //提交按钮
            var btnsubmit = new Ext.Button({
                text: '提 交',
                handler: btnsubmitclick
                
            });
            //重置按钮
            var btnreset = new Ext.Button({
                text: '重 置',
                handler: btnresetclick
            });
            //用户名input
            var txtusername = new Ext.form.TextField({
                width: 200,
                allowBlank: false,
                maxLength: 20,
                name: 'username',
                fieldLabel: '用户名',
                blankText: '请输入用户名',
                maxLengthText: '用户名不能超过20个字符'
            });
            //密码input
            var txtpassword = new Ext.form.TextField({
                width: 200,
                allowBlank: false,
                maxLength: 20,
                inputType: 'password',
                name: 'password',
                fieldLabel: '密　码',
                blankText: '请输入密码',
                maxLengthText: '密码不能超过20个字符'
            });
            //验证码input
            var txtcheckcode = new Ext.form.TextField({
                fieldLabel: '验证码',
                id: 'checkcode',
                allowBlank: false,
                width: 176,
                blankText: '请输入验证码！',
                maxLength: 4,
                maxLengthText: '验证码不能超过4个字符!'
            });
            //表单
            var form = new Ext.form.FormPanel({
               
                labelAlign: 'right',
                labelWidth: 45,
                frame: true,
                cls: 'loginform',
                buttonAlign: 'center',
                bodyStyle: 'padding:6px 0px 0px 15px',
                items: [txtusername, txtpassword, txtcheckcode],
                buttons: [btnsubmit, btnreset]
            });
            //窗体
            var win = new Ext.Window({
                title: '用户登陆',
                iconCls: 'loginicon',
                plain: true,
                width: 276,
                height: 174,
                resizable: false,
                shadow: true,
                modal: true,
                closable: false,
                animCollapse: true,
                items: form,
                url: 'form.action'
            });
            win.show();
        }