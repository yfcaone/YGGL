//后台管理人员输入手机的基本信息界面
Ext.onReady(function(){
	
	var msgBasicMsg = Ext.create('Ext.form.Panel', {
   		id:'msgBasicMsg',
	    title: '在外员工信息录入：',
	    autoScroll:true,
	    renderTo:Ext.getBody(), 
	    bodyPadding: 5,
	    margins:'0 20 0 20',
	   autoWidth:true,
	    layout: 'anchor', 		//表单域 Fields 将被竖直排列, 占满整个宽度
	    items : [{
			xtype : "form",
			id : "form3",
			margin : "0 20 0 0",
			layout : "form",
			border : 0,
			items : [{
				border:0,
				html:'<html><b>拍照功能：</b></html>'
			},{
				xtype: "panel",
                layout: "hbox",
                border: 0,
                items:[{
                	id:"pz_qz",
    				xtype : "textfield",
    				name : "pz_qz",
    				flex:1,
    				margins:'0 50 0 130',
    				labelAlign : "right",
    				fieldLabel : "前置摄像头",
    				lableWidth:80
                }, {
    				id:"pz_hz",
    				xtype : "textfield",
    				margins:'0 100 0 0',
    				name : "pz_hz",
    				flex:1,
    				labelAlign : "right",
    				fieldLabel : "后置摄像头",
    				lableWidth:80
    			}]
				
			},{
				xtype: "panel",
                layout: "hbox",
                border: 0,
                items:[{
                	id:"pz_lx",
    				xtype : "textfield",
    				name : "pz_lx",
    				margins:'0 50 0 130',
    				
    				flex:1,
    				labelAlign : "right",
    				fieldLabel : "摄像头类型",
    				
                },{
    				id:"pz_xsgs",
    				xtype : "textfield",
    				name : "pz_xsgs",
    				margins:'0 100 0 0',
    				flex:1,
    				labelAlign : "right",
    				fieldLabel : "视频显示格式",
    				
    			},]
			},  {
				border:0,
				html:'<html><b>显示</b></html>'
			},{
				id:"xs_cmplx",
				xtype : "textfield",
				name : "xs_cmplx",
				labelAlign : "right",
				fieldLabel : "触摸屏类型",
				labelWidth : 78
			}, {
				id:"",
				xtype : "textfield",
				name : "",
				labelAlign : "right",
				fieldLabel : "分辨率",
				labelWidth : 78
			}, {
				border:0,
				html:'<html><b>网络</b></html>'
			},{
				id:"wl_wllx",
				xtype : "textfield",
				name : "wl_wllx",
				labelAlign : "right",
				fieldLabel : "网络类型",
				labelWidth : 78
			}, {
				id:"wl_ms",
				xtype : "textfield",
				name : "wl_ms",
				labelAlign : "right",
				fieldLabel : "网络模式",
				labelWidth : 78
			},{
				border:0,
				html:'<html><b>机身详情</b></html>'
			},{
				id:"xq_ks",
				xtype : "textfield",
				name : "xq_ks",
				labelAlign : "right",
				fieldLabel : "款式",
				labelWidth : 78
			}, {
				id:"xq_jplx",
				xtype : "textfield",
				name : "xq_jplx",
				labelAlign : "right",
				fieldLabel : "键盘类型",
				labelWidth : 78
			}, {
				id:"xq_hd",
				xtype : "textfield",
				name : "xq_hd",
				labelAlign : "right",
				fieldLabel : "厚度",
				labelWidth : 78
			},  {
				id:"xq_zl",
				xtype : "textfield",
				name : "xq_zl",
				labelAlign : "right",
				fieldLabel : "重量",
				labelWidth : 78
			},{
				border:0,
				html:'<html><b>存储</b></html>'
			},{
				id:"cc_nc",
				xtype : "textfield",
				name : "cc_nc",
				labelAlign : "right",
				fieldLabel : "运行内存",
				labelWidth : 78
			}, {
				id:"cc_rl",
				xtype : "textfield",
				name : "cc_rl",
				labelAlign : "right",
				fieldLabel : "存储容量",
				labelWidth : 78
			},{
				border:0,
				html:'<html><b>基本参数</b></html>'
			}, {
				id:"cs_pp",
				xtype : "textfield",
				name : "cs_pp",
				labelAlign : "right",
				fieldLabel : "品牌",
				labelWidth : 78
			}, {
				id:"cc_xh",
				xtype : "textfield",
				name : "cc_xh",
				labelAlign : "right",
				fieldLabel : "型号",
				labelWidth : 78
			}, {
				id:"cc_ys",
				xtype : "textfield",
				name : "cc_ys",
				labelAlign : "right",
				fieldLabel : "机身颜色",
				labelWidth : 78
			}, {
				id:"cc_xt",
				xtype : "textfield",
				name : "cc_xt",
				labelAlign : "right",
				fieldLabel : "操作系统",
				labelWidth : 78
			}, {
				id:"cc_sjlx",
				xtype : "textfield",
				name : "cc_sjlx",
				labelAlign : "right",
				fieldLabel : "手机类型",
				labelWidth : 78
			}, {
				id:"cc_dclx",
				xtype : "textfield",
				name : "cc_dclx",
				labelAlign : "right",
				fieldLabel : "电池类型",
				labelWidth : 78
			}, {
				id:"cc_dcrl",
				xtype : "textfield",
				name : "cc_dcrl",
				labelAlign : "right",
				fieldLabel : "电池容量",
				labelWidth : 78
			},{
				border:0,
				html:'<html><b>屏幕</b></html>'
			}, {
				id:"pm_cc",
				xtype : "textfield",
				name : "pm_cc",
				labelAlign : "right",
				fieldLabel : "屏幕尺寸",
				labelWidth : 78
			}, {
				id:"pm_fbl",
				xtype : "textfield",
				name : "pm_fbl",
				labelAlign : "right",
				fieldLabel : "分辨率",
				labelWidth : 78
			}, {
				id:"pm_pmcz",
				xtype : "textfield",
				name : "pm_pmcz",
				labelAlign : "right",
				fieldLabel : "屏幕材质",
				labelWidth : 78
			},{
				border:0,
				html:'<html><b>CPU信息</b></html>'
			}, {
				id:"cpu_xh",
				xtype : "textfield",
				name : "cpu_xh",
				labelAlign : "right",
				fieldLabel : "CPU型号",
				labelWidth : 78
			}, {
				id:"cpu_pp",
				xtype : "textfield",
				name : "cpu_pp",
				labelAlign : "right",
				fieldLabel : "CPU品牌",
				labelWidth : 78
			}, {
				id:"cpu_xxs",
				xtype : "textfield",
				name : "cpu_xxs",
				labelAlign : "right",
				fieldLabel : "核心数",
				labelWidth : 78
			},{
				border:0,
				html:'<html><b>其他</b></html>'
			}, {
				id:"qt_ccc",
				xtype : "textfield",
				name : "qt_ccc",
				labelAlign : "right",
				fieldLabel : "CCC证书编号",
				labelWidth : 78
			}, {
				id:"qt_sssj",
				xtype : "textfield",
				name : "qt_sssj",
				labelAlign : "right",
				fieldLabel : "上市时间",
				labelWidth : 78
			}, {
				id:"qt_shfw",
				xtype : "textfield",
				name : "qt_shfw",
				labelAlign : "right",
				fieldLabel : "售后服务",
				labelWidth : 78
			}, {
				id:"qt_tclx",
				xtype : "textfield",
				name : "qt_tclx",
				labelAlign : "right",
				fieldLabel : "套餐类型",
				labelWidth : 78
			}, {
				id:"qt_fjgn",
				xtype : "textfield",
				name : "qt_fjgn",
				labelAlign : "right",
				fieldLabel : "附加功能",
				labelWidth : 78
			}, {
				id:"qt_bblx",
				xtype : "textfield",
				name : "qt_bblx",
				labelAlign : "right",
				fieldLabel : "版本类型",
				labelWidth : 78
			}, {
				id:"qt_sim",
				xtype : "textfield",
				name : "qt_sim",
				labelAlign : "right",
				fieldLabel : "SIM类型",
				labelWidth : 78
			}, {
				id:"qt_sjx",
				xtype : "textfield",
				name : "qt_sjx",
				labelAlign : "right",
				fieldLabel : "数据线接口",
				labelWidth : 78
			}, {
				id:"qt_xgsj",
				xtype : "datefield",
				name : "qt_xgsj",
				labelAlign : "right",
				fieldLabel : "时间--fdafa ",
				format:'Y-m-d',
				labelWidth : 78
			}, {
				id:"qt_tc",
				xtype : "textfield",
				name : "qt_tc",
				labelAlign : "right",
				fieldLabel : "是否停产",
				labelWidth : 78
			}, {
				id:"",
				xtype : "datefield",
				name : "datefield",
				labelAlign : "right",
				fieldLabel : "签名时间",
				labelWidth : 78
			},{
				id:"",
				xtype : "textarea",
				name : "textarea",
				labelAlign : "right",
				fieldLabel : "审批意见",
				labelWidth : 78,
				height:70
			} ]
		} ],

	    // 重置 和 保存 按钮.
	    buttons: [{
	        text: '重置',
	        handler: function() {
	            this.up('form').getForm().reset();
	        }
	    }, {
	        text: '保存',
	        /*formBind: true, //only enabled once the form is valid
	        disabled: false,*/
	        handler: function() {
	        	console.log("点击保存");
	        	var formData = Ext
				.getCmp("msgBasicMsg")
				.getForm()
				.getValues();
	        	console.log("表单数据：" + Ext.encode(formData));
	        	
	        	var qt_xgsj = Ext.util.Format.date(Ext.getCmp("qt_xgsj").getValue(), 'Y-m-d')
	        	//	Ext.getCmp("qt_xgsj").getValue();
	        	console.log("时间类型：" + typeof(Ext.getCmp("qt_xgsj").getValue()));
	        	
	        	
	        	Ext.getCmp("msgBasicMsg").getForm().submit({
					url : 'insertMsg.action',
					//等待时显示 等待  
					waitTitle : '请稍等...',
					waitMsg : '正在提交信息...',
					params : {
						msgBean:Ext.encode(formData),
						qt_xgsj:qt_xgsj
					},
					success : function(fp, o) {
						if (o.result == true) {
							Ext.MessageBox.alert("信息提示", "保存成功!");
							syswin.close(); //关闭窗口  
						} else {
					//		msg('信息提示', '添加时出现异常！');
						}
					},
					failure : function() {
						Ext.MessageBox.alert("信息提示", "添加失败!");
					}
				});
	        	
	        	
	        }
	    }]
	});
	
});