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
                pressed: false, text: username,
                menu:new Ext.menu.Menu({
                    ignoreParentCilcks:true,
                    items:[{
                        text:'注销',
                        handler:function(){
                        	Ext.MessageBox.confirm("提示", "确定注销该用户？");
                        	window.location.href="../login/login.action";
                        }
                    },{
                        text:'退出',
                        handler:function(){
                        	Ext.MessageBox.confirm("提示", "确定要退出吗？");
                        	CloseWebPage()
                        }
                    }]
                })
            }, {
                pressed: false, text: '帮助'
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
                                {text: '在外员工信息', leaf: true},
                                {text: '个人在外信息', leaf: true},
                               
                            ]
                        },
                        listeners:{
                            //如果项被点击的话
                            itemclick:function(view,record,item){
                                //如果是叶子，对应上面的leaf==true
                                if(record.data.leaf){
                                    //如果没有与当前点击项目id相同的的标签页的话
                                	if(record.data.text=="在外员工信息"){
                                		$('#cenIF').attr("src","yzwygxx.action");
                                	}else if(record.data.text=="个人在外信息"){
                                		$('#cenIF').attr("src","grzwxx.action");
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
    	    items: [{
	        	width:'100%',
	        	html:'<iframe frameborder=0 id=cenIF src=yzwygxx.action scrolling=yes height=600 width=100%></iframe>'
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
        function CloseWebPage() {
            if (navigator.userAgent.indexOf("MSIE") > 0) {      //IE浏览器
                if (navigator.userAgent.indexOf("MSIE 6.0") > 0) {
                    window.opener = null; 
                    window.close();
                }
                else {
                    window.open('', '_top'); window.top.close();
                }
            }
            else if (navigator.userAgent.indexOf("Firefox") > 0) {     //火狐浏览器
                window.location.href = 'https://www.baidu.com/ ';
                //window.history.go(-2);
            }
            else {                      //其他浏览器
                window.opener = null; 
                window.open('', '_self', '');
                window.close();
            }
        }  