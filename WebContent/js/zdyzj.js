       Ext.onReady (function () {
    
        
       Ext.define ('MydesktopIcon', {
               /* Begin Definitions */
               alias: 'widget.desktopIcon',
               extend: 'Ext.Component',
                
               width: 76,
              //height: 84,
              overCls: 'x-view-over',
              renderTpl:
                  '<div class="ux-desktop-shortcut" id="{module}-shortcut">' +
                  '<div class="ux-desktop-shortcut-icon">' + 
                      '<img src="{iconName}" title="{name}">' +'sssssssssss'+'{name}'+
                  '</div>' +
                  '<span class="ux-desktop-shortcut-text">{name}</span>' +
                  '</div>' +
                  '<div class="x-clear"></div>',
               
              // private
              onRender: function(ct, position) {
                  // classNames for the button
                  var me = this;
                   // Render internal structure
                  me.callParent(arguments);
                  //添加单击事件
                  alert('dddddd');
                  alert(me.name);
                  me.mon(me.el, 'click', me.onClick, me);
              },
              getTemplateArgs: function () {
                  var me = this;
                  return {
                     name: me.name || ' ',
                     module: me.module || ' ',
                     iconName: me.iconName || Ext.BLANK_IMAGE_URL,
                  }
              },
              onClick: function(e) {
                      var me = this;
                      me.ownerCt.fireEvent ('itemClick', this);
                  },
   
              // inherit docs
                  initComponent: function() {
                      alert('dd');
                      var me = this;
                      Ext.applyIf(me.renderData, me.getTemplateArgs());
                     // me.callParent(arguments);
                   
              },
          });
   
   
      Ext.create ('Ext.panel.Panel', {
              title:'test',
              frame: true,
              height:800,
              renderTo: Ext.getBody(),
              items: [{
                          xtype: 'desktopIcon',
                          name:'首页',
                          module:'firstPage',
                          iconName: '../SDOJ/images/problems.png'
                      },{
                          xtype: 'desktopIcon',
                          name:'个人信息',
                          module:'firstPage',
                          iconName: '../SDOJ/images/myself.png'
                  },{
                          xtype: 'desktopIcon',
                          name:'首页',
                          module:'firstPage',
                          iconName: '../SDOJ/images/firstpage.png'
                      },{
                          xtype: 'desktopIcon',
                          name:'首页',
                          module:'firstPage',
                          iconName: '../SDOJ/images/contest.png'
                  },
                  {
                          xtype: 'desktopIcon',
                          name:'首页',
                          module:'firstPage',
                          iconName: '../SDOJ/images/college.png'
                      },{
                          xtype: 'desktopIcon',
                          name:'首页',
                          module:'firstPage',
                          iconName: '../SDOJ/images/letter.png'
                  }],
              listeners: {
                  itemClick: function (item) {
                      alert (item.name)
                  }
              }
                   
          });
  
  
 });