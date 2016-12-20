<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%><%@ include
	file="/include.inc.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%-- <title><fmt:message key="ui.title" /></title> --%>

<link href="<c:url value='/styles/dwz/themes/css/login.css'/>"
	rel="stylesheet" type="text/css" />
<link href="<c:url value='/styles/dwz/themes/default/style.css'/>"
	rel="stylesheet" type="text/css" />
<link href="<c:url value='/styles/dwz/themes/css/core.css'/>"
	rel="stylesheet" type="text/css" />
<link href="<c:url value='/styles/dwz/themes/css/login.css'/>"
	rel="stylesheet" type="text/css" />
<script src="<c:url value='/styles/dwz/js/dwz.alertMsg.js'/>"
	type="text/javascript"></script>
<script src="<c:url value='/styles/dwz/js/jquery-1.7.2.js'/>"
	type="text/javascript"></script>
<script type="text/javascript">
$(function(){
<c:choose>
	<c:when test="${statusCode eq 200}">
	<c:if test="${!empty message}">alertMsg.correct("${message}")</c:if>
	</c:when>
	<c:otherwise><c:if test="${!empty message}">alertMsg.error("${message}")</c:if></c:otherwise>
</c:choose>	
});
</script>
<style type="text/css">
.centerDiv {
	position: absolute;
	top: 50%;
	left: 50%;
	height: 30rem;
	width: 30rem;
	border: #000 solid 3px;
	background: #eee;
	-webkit-transform: translateX(-50%) translateY(-50%);
}

.centerDiv h1 {
	line-height: 4;
	margin-top: 40px;
}

.centerDiv .login form p {
	line-height: 3;
}

.centerDiv .login form .login_submit {
	margin-left: 2rem;
	padding-top: 1rem;
}
}
</style>
</head>
<body style="background: #0000">
	<div class="centerDiv">
		<h1 style="font-size: 30">教练随行后台</h1>
		<div class="login">
			<form action="<c:url value='/passport/signin.do'/>" method="post">
				<p>
					<label style="width: 20px;">用户名：</label> <input type="text"
						name="username" size="20" />
				</p>
				<p>
					<label style="width: 20px;">密 &nbsp;&nbsp;码：</label> <input
						type="password" name="password" size="20" />
				</p>
				<div class="login_submit">
					<input type="submit" value="登录" class="sub_button" />
				</div>
			</form>
		</div>
	</div>


</body>
</html>
