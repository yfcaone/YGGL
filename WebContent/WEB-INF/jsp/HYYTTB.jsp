<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../ext/resources/css/ext-all.css"></link>
<script type="text/javascript" src="../ext/ext-all.js"></script>
<title>Insert title here</title>
<script type="text/javascript">
function save(content,rownum,reporter){
	var url;
	var NewContent=Ext.getCmp("content").getValue();
	//alert(NewContent+"-"+rownum+"-"+reporter);
	if(content.length>0){
		url="workEditSave.action";
		
	}else{
		url="workAddSave.action";
		
	}
	Ext.Ajax.request({
		url:url,
		method:'post',
		params:{
			content:NewContent,
			rownum:rownum
			
		},
		success:function(response,options){
			Ext.Msg.alert("提示","提交成功！",function(){
				window.location="WorkTable.action";
			});
			
		},
		failure:function(){
			Ext.Msg.alert("提示", "获取记录值失败",function(){
				window.location="WorkTable.action";
			});
		}
	});
};
Ext.onReady(function(){
	var con="${content}";
	var rownum="${rownum}";
	/* var reporter="${reporter}"; */
	 var p = new Ext.FormPanel({
        title: '工作汇报管理',//标题
        collapsible:true,//右上角上的那个收缩按钮，设为false则不显示
        renderTo: 'container',//这个panel显示在html中id为container的层中
        width:800,
        height:500,
        region:'center',
        frame:true,
        
        items:[{
        	bodyPadding:5,
        	padding:2,
        	items:[{
        		xtype:'fieldset',
        		title : '发电部(汇报人:admin)',
        		collapsible:false,
        		align:'left',
        		width:760,
        		height:300,
        			items:[{
        				xtype:'label',
        				text:'汇报内容：',
        				align:'left'
        			},{
        				xtype:'textarea',
        				id:'content',
        				width:700,
        				height:200
        				
        			},{
        				layout:'column',
        				border:false,
        				items:[{
        					xtype:'button',
        					text:'提交',
        					width:60,
        					height:30,
        					style:{
        						marginLeft:'300px',
        						marginTop:'5px'
        					},
        					handler:function(){
        						save(con,rownum);
        					}
        						
        				},{
        					xtype:'button',
        					text:'返回',
        					width:60,
        					height:30,
        					style:{
        						marginLeft:'10px',
        						marginTop:'5px'
        					},
        					handler:function(){
        						window.location="WorkTable.action";
        					}
        				}]
        			}],
    	
        	},{
        		xtype:'textarea',
        		width:760,
        		height:50
        	}]
        }]
    });

	
	Ext.getCmp("content").setValue(con);
	/*var panelUp=new Ext.FormPanel({
		title:'汇报内容',
		region : 'north',
		width:1000,
		height:500,
		border:1
	}); */
});


</script>
<!-- <style type="text/css">
	.maxDiv{
		width:90%;
		height:350px;
		background:#CCCCFF;
		margin:20px 20px 20px 20px;
	}
	.whiteDiv{
		padding-left:10px;
		background:white;
		border:2px solid light-blue;
		height:280px;
		margin:2px 2px 2px 2px;
	}
	

</style> -->
</head>
<body>
	 <div id="container" align="center"></div>
	<!-- <div class="maxDiv">
		<span style="font-size:20px">工作汇报管理</span>
		<div class="whiteDiv"></div>
	</div> -->
</body>
</html>