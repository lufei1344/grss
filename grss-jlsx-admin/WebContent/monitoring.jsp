<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>监控窗口</title>
<script src="<c:url value='/styles/dwz/js/jquery-1.7.2.js'/>" type="text/javascript"></script>
<script type="text/javascript">
function monitor() {
    //向子窗口发送监控任务消息
    window.frames[0].postMessage('monitor', '*');
}

window.addEventListener('message', function (e) {
    //接收子窗口反馈消息
    if(e.data=="ok"){
    	$("#content").css("backgroundImage","url('<c:url value='/img/GIF2.gif'/>')"); 
    }else{
    	$("#content").css("backgroundImage","url('<c:url value='/img/GIF1.gif'/>')");
    	$("#message").css("display","block");
    	$("#labelId").css("display","block");
    	if($("#message").text()){
    		$("#message").text("");
    	}
    	$("#message").text(e.data);
    }
}, false);
window.onload=function(){
	
	setInterval(monitor,30000);
}
</script>
</head>
<body>
<center>
<h1>监控</h1>
</center>
<div id="content" style="width: 100px;height: 100px;"></div>
<div style="display: none" >
    <iframe id="child" src="https://admin3501.careauto.cn/monitor_iframe"></iframe>
</div>
<div id="labelId" style="width: 100%;margin-top: 20px; color: red;display:none;"><label>最近一次错误：</label></div>
<div id="message" style="width: 100%;height: 300px; border: 1px solid red; display:none; margin-top: 10px;"></div>
</body>
</html>