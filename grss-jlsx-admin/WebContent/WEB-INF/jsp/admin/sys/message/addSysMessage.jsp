<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/include.inc.jsp"%>
<h2 class="contentTitle">添加系统消息</h2>

<form action="<c:url value='/admin/sys/message/sendSysMessage?navTabId=listSysMessageNav&callbackType=closeCurrent'/>" method="post" class='required-validate pageForm' onsubmit="return iframeCallback(this, navTabAjaxDone);" enctype="multipart/form-data">

<div class="pageFormContent" layoutH="97">
	<div class="unit">
		<label>标题</label>
		<input type="text" name="title" maxlength="200" size="50"/>
	</div>
	<div class="unit">
		<label>内容</label>
		<textarea rows="5" cols="48" name="content"></textarea>
	</div>
	<div class="unit">
		<label>主图</label>
		<input type="file" name="imgFile" maxlength="200" size="100"/>
	</div>
	<div class="unit">
		<label>HTML详情文件</label>
		<input type="file" name="htmlFile" maxlength="200" size="100"/>
	</div>
</div>
<div class="formBar">
	<ul>
		<li><div class="buttonActive"><div class="buttonContent"><button type="submit">发送</button></div></div></li>
		<li><div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div></li>
	</ul>
</div>
</form>