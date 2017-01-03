<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>用户信息</title>
    <link href="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/js/bootstrap/style.min.css?v=4.1.0" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/bootstrap/jquery.min.js?v=2.1.4"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js?v=3.3.6"></script>
    <script type="text/javascript">
    	function show(){
    		var url = "${pageContext.request.contextPath}/api/listCoachByClub"
    		var data = new Object();
    		data.token = "";
    		data.type = "newest";
    		$.post(url,data,function(redata){
    			if(redata.code == "1"){
    				
    			}else{
    				alert(redata.message);
    			}
    		});
    	}
    	$(function(){
    		show();
    	});
    </script>
</head>

<body class="gray-bg" style="background-color: #77343E;">
    
  
</body>
</html>
