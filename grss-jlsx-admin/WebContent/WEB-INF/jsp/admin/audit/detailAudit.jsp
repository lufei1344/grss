<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/include.inc.jsp"%>
<h2 class="contentTitle">资料审核</h2>

<form action="<c:url value='/admin/audit/updateAudit?navTabId=listAuditNav&callbackType=closeCurrent'/>" method="post" class='required-validate pageForm' onsubmit="return iframeCallback(this, navTabAjaxDone);">

<div class="pageFormContent" layoutH="97">
	<div class="unit">
		<label>手机号</label>
		<input hidden="hidden" name="userId" value="${user.userId}"/>
		<input type="text" name="" value="${user.userPhone}" maxlength="200" size="100" disabled="disabled"/>
	</div>
	<div class="unit">
		<label>银行卡号</label>
		<input type="text" name="" value="${userAccount.cardNo}" maxlength="200" size="100" disabled="disabled"/>
	</div>
	<div class="unit">
		<label>持卡人姓名</label>
		<input type="text" name="" value="${userAccount.openAccountName}" maxlength="200" size="100" disabled="disabled"/>
	</div>
	<div class="unit">
		<label>开户银行</label>
		<input type="text" name="" value="${userAccount.openAccountBank}" maxlength="200" size="100" disabled="disabled"/>
	</div>
	<div class="unit">
		<label>开户省份</label>
		<input type="text" name="" value="${userAccount.openAccountCity}" maxlength="200" size="100" disabled="disabled"/>
	</div>
	<div class="unit">
		<label>身份证</label>
		<img src="${coachAttrstation.identityCardUrl}" height="500rm" width="800rm">
	</div>
	<div class="unit">
		<label>教练资格证</label>
		<img src="${coachAttrstation.certificationUrl}" height="500rm" width="800rm">
	</div>
	<div class="unit">
		<label>是否审核通过</label>
		通过<input type="radio" name="isPass" value="1"/>
		不通过<input type="radio" name="isPass" value="0" checked="checked"/>
	</div>
</div>
<div class="formBar">
	<ul>
		<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
		<li><div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div></li>
	</ul>
</div>