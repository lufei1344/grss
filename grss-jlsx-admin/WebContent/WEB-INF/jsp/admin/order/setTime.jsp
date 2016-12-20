<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/include.inc.jsp"%>
<h2 class="contentTitle">订单失效时间</h2>

<form action="<c:url value='/order/updateSysConfig?navTabId=listClubNav&callbackType=closeCurrent'/>" method="post" class='required-validate pageForm' onsubmit="return iframeCallback(this, navTabAjaxDone);" >
<div class="pageFormContent" layoutH="97">
	<div class="unit">
		<label>失效时间</label>
		<input type="text" name="value" value="${sysConfig.value }"/> 分钟
		<input type="hidden" name="id" value="${sysConfig.id }"/>
	</div>
	
</div>
<div class="formBar">
	<ul>
	<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
		<li><div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div></li>
	</ul>
</div>
</form>
	