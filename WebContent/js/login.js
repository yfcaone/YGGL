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
                    
                }, {
                    title: 'VIP客户信息管理',
                }, {
                    title: '商品信息管理系统',
                }],
            }, {
                collapsible: true,
                style: 'margin-top: 3px;',
                height: 205,
                border:0,
                title: "未读信息",
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
                  /*  ignoreParentCilcks:true,
                    items:[{
                        text:'打开'
                    },{
                        text:'保存'
                    },{
                        text:'退出'
                    }]*/
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
            },'->',{
                text:'您还未登录！'
            },'-',{
            	xtype:'button',
                text:'登录',
                handler: function() {  
                   login()  
                }  
            },'-',{
                text:'帮助'
            }],
    	    items: [{
	        	width:'100%',
	        	html:'<h3 align="center" >----------请先登录再进行操作！----------</h3>'
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
      
