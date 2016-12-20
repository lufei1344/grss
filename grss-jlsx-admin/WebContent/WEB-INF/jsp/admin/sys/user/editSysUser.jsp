<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/include.inc.jsp"%>
<h2 class="contentTitle">编辑管理员信息</h2>

<form action="<c:url value='/admin/sys/user/updateSysUser?navTabId=sysUserLiNav&callbackType=closeCurrent'/>" method="post" class='required-validate pageForm' onsubmit="return iframeCallback(this, navTabAjaxDone);" enctype="multipart/form-data">
<input type="text" name="id" value="${adminUser.id}" hidden="hidden"/>
<div class="pageFormContent" layoutH="97">
	<div class="unit">
		<label>管理员名称</label>
		<input type="text" name="name" maxlength="200" size="100" value="${adminUser.name }"/>
	</div>
	<div class="unit">
		<label>手机号码</label>
		<input type="text" name="phone" maxlength="200" size="100" value="${adminUser.phone }"/>
	</div>
	<div class="unit">
		<label>角色类型</label>
		<select name="roleId" style="width: 59rem">
			<c:forEach items="${roleList}" var="role">
				<option value="${role.roleId }" ${role.roleId eq adminUser.roleId ? 'selected="selected"' : ''}>${role.roleName}</option>
			</c:forEach>
		</select>
	</div>
	<div class="unit">
		<label>性别</label>
		男:<input type="radio" name="sex" value="1" ${1 eq adminUser.roleId ? 'checked="checked"' : ''}/>
		女:<input type="radio" name="sex" value="0" ${0 eq adminUser.roleId ? 'checked="checked"' : ''}/>
	</div>
	<div class="unit">
		<label>邮箱</label>
		<input type="text" name="email" maxlength="200" size="100" value="${adminUser.email}"/>
	</div>
	<div class="unit">
		<label>密码</label>
		<input type="password" name="password" maxlength="200" size="100"/>
	</div>
</div>
<div class="formBar">
	<ul>
		<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
		<li><div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div></li>
	</ul>
</div>
</form>
