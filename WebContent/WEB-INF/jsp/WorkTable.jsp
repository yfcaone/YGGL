<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="../ext/resources/css/ext-all.css"></link>
<script type="text/javascript" src="../ext/ext-all.js"></script>
<title>Insert title here</title>
<script type="text/javascript">
	
	Ext.onReady(function() {
		
				//var sm = new Ext.grid.CheckboxSelectionModel({singleSelect:true});
				//转换原始数据为EXT可以显示的数据
		var store = Ext.create('Ext.data.JsonStore', {
		     fields : [ 'ID', 'UNIT', 'REPORTER', 'CONTENT',
				'RN' ],  
			//model: "Province",
			proxy : {
				type : 'ajax',
				url : 'getList.action',
				reader:{
					type:'json',
					totalProperty:'totalCount',
					root:'list'
					},	
				},
			pageSize : 4,
			autoLoad : true
		});
		//加载数据
		/* store.load({
			params : {
				start : 1,
				limit : 5
			}
		}); */
				var columns = [
						  /* {
							header : "操作",
						
							height : 30,
							align : "center",
							renderer : function(value, cellmeta) {
								var returnStr = "<INPUT type='checkBox' singleSelect ='false'>";
								return returnStr;
							}
						},  */ 
						{
							header : '序号',
							/* width:60, */
							dataIndex : 'ID',
							align : "center"
						},
						{
							header : '汇报人',
							dataIndex : 'REPORTER',
							/* width:70, */
							align : "center"
						}, //sortable:true 可设置是否为该列进行排序
						{
							header : '简要内容',
							dataIndex : 'CONTENT',
							flex:1
							/* width : 300  */
						},
						{
							header : "编辑",
							height : 30,
							align : "center",
							renderer:function(value,cellmeta,record){
								var content = record.data["content"];
								var rownum=record.data["rownum"];
								var returnStr = "<a href='${pageContext.request.contextPath}/mydemo/workEdit.action?content="+content+"&rownum="+rownum+"'>编辑</a>";
								return returnStr;

							}
						} ];
				var sm = Ext.create('Ext.selection.CheckboxModel');
				
				var grid = new Ext.grid.Panel(
						{
							 /* width : 800, */ 
							/* height : 300, */
							id : 'grid',
							//autoExpandColumn:'descn',
							store :store,
							selModel: sm,
							columns : columns,
							loadMask : true,
							stripeRows : true,
							style : 'padding:5px;border:1px;line-height:30px;height:30px;',
							align : "center",
							viewConfig : {
								columnsText : '显示的列',
								scrollOffset : 30, //表格右侧为滚动条预留的宽度,默认为20
								sortAscText : '正序',
								sortDescText : '倒序',
								forceFit : true
							//表格会自动延展每列的长度，使内容填满整个表格
							},
							bbar:new Ext.PagingToolbar({
								//pageSize:10,
								store :store,
								displayInfo:true,
								displayMsg:'显示第{0}条到{1}条记录，一共 {2}条',
								emptyMsg : "没有记录"	
							}),
							renderTo : 'grid'
						});
				var downPanel = new Ext.FormPanel({
					renderTo : 'downPanel',
					style : 'padding-top:5px;',
					align : "center",
					layout : 'column',
					border : false,
					items : [ {
						xtype : 'datefield',
						fieldLabel : '日  期',
						name : 'dateField',
						labelWidth : 100,
						labelAlign : 'right'
					}, {
						xtype : 'textfield',
						fieldLabel : '汇报人',
						id:'reporter',
						name : 'person',
						labelWidth : 100,
						labelAlign : 'right',
					}, {
						xtype : 'button',
						text : '新建',
						width : 60,
						style : {
							marginLeft : '120px'
						},
						handler:function(){
							location.href="${pageContext.request.contextPath}/yggl/workEdit.action?content=&rownum=&reporter="+Ext.getCmp("reporter").getValue();
						}
						
					}, {
						xtype : 'button',
						text : '删除',
						width : 60,
						style : {
							marginLeft : '5px'
						},
						handler : function() {
							del();
						}
					} ]
				});

			});
	function edit() {
		Ext.Ajax.request({
			url : 'workEdit.action',
			method : 'post'
		});
	}
	
	function del() {

		var selectedId = Ext.getCmp("grid").getSelectionModel();
		var count=selectedId.selected.items.length;
		alert(count);
		if (selectedId.selected.items &&  count> 0) {
			
			var ids="";
			for(var i=0;i<count;i++){
				var rec = selectedId.selected.items[i]["data"];
				ids=ids+rec["rownum"]+",";
			}
			ids=ids.substring(0,ids.length-1);
			Ext.MessageBox.confirm("提示","您确定要删除"+count+"条记录吗？",function(btn){
				if(btn=="yes"){
					Ext.Ajax.request({
						url : 'workDel.action',
						method : 'post',
						params : {
							ids : ids
						},
						success : function(response, options) {
							Ext.Msg.alert("提示", "删除成功!", function() {
								var grid = Ext.getCmp('grid');
								grid.store.reload();
							});

						},
						failure : function() {
							Ext.Msg.alert("提示", "删除失败!");
						}

					});
				}
				else{
					Ext.Msg.alert("提示","请选择一条记录删除！");
				}
			});
		} else {
			Ext.Msg.alert("提示", "请选择一条记录进行删除!");
		}
	}
</script>
</head>
<body id="body">
	<div>
		<div id="grid" align="center" class="margin:50px;height:90%"></div>
	    <div id="downPanel" align="center"></div>
	</div>
</body>
</html>