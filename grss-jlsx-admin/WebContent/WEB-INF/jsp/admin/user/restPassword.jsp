<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/include.inc.jsp"%>
<h2 class="contentTitle">重置密码</h2>

<form action="<c:url value='/admin/user/updatePassword?navTabId=listUserNav&callbackType=closeCurrent'/>" method="post" class='required-validate pageForm' onsubmit="return iframeCallback(this, navTabAjaxDone);">

<div class="pageFormContent" layoutH="97">
	<div class="unit">
		<label>手机号</label>
		<input hidden="hidden" name="userId" value="${user.userId}"/>
		<input type="text" name="userPhone" value="${user.userPhone}" maxlength="200" size="100" disabled="disabled"/>
	</div>
	<div class="unit">
		<label>新密码</label>
		<input type="password" name="password" maxlength="200" size="100"/>
	</div>
</div>
<div class="formBar">
	<ul>
		<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
		<li><div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div></li>
	</ul>
</div>