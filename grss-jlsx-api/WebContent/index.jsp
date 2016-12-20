
<!-- 头部标准代码 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/include.jsp"%> <!-- 公共头部信息 -->


<!DOCTYPE HTML>
<html lang="zh_CN">
<head>
<title>rest接口后台，rest接口服务</title>
</head>
<body>
<h2>Project-rest </h2>
rest接口后台，rest接口服务
<BR>
<label> 返回json: </label>
<br>
<textarea id="json" rows="20" style="height: 50%;width: 50%;">${result}</textarea>
<input type ="hidden" id="data" name="data" value="${result}">
<div class="row">
<form method="post" action="" class="form-horizontal" role="form" id="form" enctype= "multipart/form-data">
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right"> 地址: </label>
		<div class="col-sm-9">
			<textarea class="col-xs-10 col-sm-5" cols="100" id="address" name="address">http://localhost:8080/project-rest/rest/user/personalData</textarea>
		</div>
	</div>
		<a href="javascript:subTest();" title="提交测试"> 提交测试
		</a>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right">业务参数: </label>
		<div class="col-sm-9">
			<textarea rows="5" cols="100" id="para" name="para"  >nickname=测试&birthday=1990-01-01&user_desc=测试啊啊啊啊啊&baby_name=啊啊啊啊&baby_sex=1&baby_birthday=1990-01-01</textarea>
			<input type="hidden" id="nickname" name="nickname">
			<input type="hidden" id="birth" name="birth">
			<input type="hidden" id="user_desc" name="user_desc">
			<input type="hidden" id="baby_name" name="baby_name">
			<input type="hidden" id="baby_sex" name="baby_sex">
			<%-- <fmt:formatDate value='' pattern='yyyy-MM-dd' /> --%>
			<input type="hidden" id="baby_birthday" name="baby_birthday">
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right">图片上传: </label>
		<div class="col-sm-9">
			<label class="col-xs-6">
				<input type="file" class="fileImg" name="user_photo"  id="user_photo" />
			</label>
		</div>
		<a href="javascript:sub();" >提交表单</a>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right"> App Token: </label>
		<div class="col-sm-9">
			<input  type="text" class="col-xs-10 col-sm-6" name="token" id="token" value="0f489c40554441fbab1d4da952b9d135|b1aedb44c981419d84c240d00139bf4a">
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right"> App版本号: </label>
		<div class="col-sm-9">
			<input  type="text" class="col-xs-10 col-sm-6" name="client_version" id="client_version" value="3.0.1">
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right"> App Build: </label>
		<div class="col-sm-9">
			<input  type="text" class="col-xs-10 col-sm-6" name="build" id="build" value="31">
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right"> 客户端唯一识别: </label>
		<div class="col-sm-9">
			<input  type="text" class="col-xs-10 col-sm-6" name="uuid" id="uuid" value="080d19ebae22ba1a4d77e20a96115c97ee2506fc">
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right"> 客户端系统: </label>
		<div class="col-sm-9">
			<input  type="text" class="col-xs-10 col-sm-6" name="client" id="client" value="android">
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right"> 客户端系统版本号: </label>
		<div class="col-sm-9">
			<input  type="text" class="col-xs-10 col-sm-6" name="os_version" id="os_version" value="4.4">
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right"> 设备品牌: </label>
		<div class="col-sm-9">
			<input  type="text" class="col-xs-10 col-sm-6" name="d_brand" id="d_brand" value="HTC">
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right"> 设备型号: </label>
		<div class="col-sm-9">
			<input  type="text" class="col-xs-10 col-sm-6" name="d_model" id="d_model" value="m8t">
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right"> 网络类型: </label>
		<div class="col-sm-9">
			<input  type="text" class="col-xs-10 col-sm-6" name="network_type" id="network_type" value="wifi">
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right"> 屏幕分辨率: </label>
		<div class="col-sm-9">
			<input  type="text" class="col-xs-10 col-sm-6" name="screen" id="screen" value="500x250">
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right"> 客户端校验: </label>
		<div class="col-sm-9">
			<input  type="text" class="col-xs-10 col-sm-6" name="sign" id="sign" value="81dd3379faa8a11b8768c83249487e95">
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right"> 客户端时间戳: </label>
		<div class="col-sm-9">
			<input  type="text" class="col-xs-10 col-sm-6" name="st" id="st" value="1450746473">
		</div>
	</div>
</form>

</div><!-- /.row -->
		
<!--4.自定义 js区域  -->
<script src="<c:url value='/js/jquery.min.js'/>"></script>
<script type="text/javascript">

	
	function subTest(){
		var para = $("#para").val();
		var url = $("#address").val().trim();
		var data = { url:$("#address").val().trim(),
				  para:para.trim(),
				  token:$("#token").val().trim(),
				  client_version:$("#client_version").val().trim(),
				  build:$("#build").val().trim(),
				  uuid:$("#uuid").val().trim(),
				  client:$("#client").val(),
				  os_version:$("#os_version").val().trim(),
				  d_brand:$("#d_brand").val().trim(),
				  d_model:$("#d_model").val().trim(),
				  network_type:$("#network_type").val().trim(),
				  screen:$("#screen").val().trim(),
				  sign:$("#sign").val().trim(),
				  st:$("#st").val().trim()
				  };
		var dataArray = para.split("&");
		for(var i = 0; i < dataArray.length; i++){
			var kvs = (""+dataArray[i]).split("=");
			data[kvs[0]] = kvs[1];
		}
		$.ajax({
			  type: 'POST',
			  url: url,
			  data:data,
			  success:function(data){
				if(null != data){
					$("#json").val(JSON.stringify(data, null, "\t"));
				}
			}
	    });
	}
	
	function sub(){
		var url = "<c:url value='/rest/test/send'/>";
		var para = $("#para").val();
		var parArr = para.split("&");
		for(var i=0;i<parArr.length;i++){
			var keyValues = parArr[i].split("=");
			if(keyValues[0]=="nickname"){
				$("#nickname").attr("value",keyValues[1]);
			}
			if(keyValues[0]=="birthday"){
				$("#birth").attr("value",keyValues[1]);
			}
			if(keyValues[0]=="user_desc"){
				$("#user_desc").attr("value",keyValues[1]);
			}
			if(keyValues[0]=="baby_name"){
				$("#baby_name").attr("value",keyValues[1]);
			}
			if(keyValues[0]=="baby_sex"){
				$("#baby_sex").attr("value",keyValues[1]);
			}
			if(keyValues[0]=="baby_birthday"){
				$("#baby_birthday").attr("value",keyValues[1]);
			}
		}
		$("#form").attr("action",url);
		$("#form").submit();
	}
</script>
</body>
</html>
