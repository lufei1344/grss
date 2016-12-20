<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/include.inc.jsp"%>
<h2 class="contentTitle">查看帖子</h2>

<form method="post" class='required-validate pageForm' onsubmit="return iframeCallback(this, navTabAjaxDone);" enctype="multipart/form-data">
<input name="clubId" type="text" hidden="hidden" value="${club.clubId}">
<div class="pageFormContent" layoutH="97">
	<div class="unit">
		<label>标题</label>
		${posts.title }
	</div>
	<div class="unit">
		<label>发帖时间</label>
		<fmt:formatDate value="${posts.sendDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
	</div>
	<div class="unit">
		<label>是否原贴</label>
		${posts.isMaster == 1 ? "原贴" :"转发" }
	</div>
	<div class="unit">
		<label>内容</label>
		<textarea cols=150 rows='25'>${posts.idea }</textarea>
	</div>
	
</div>
<div class="formBar">
	<ul>
		<li><div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div></li>
	</ul>
</div>
</form>
	