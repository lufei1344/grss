<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/include.inc.jsp"%>
<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>

<form method="post" rel="pagerForm" action="<c:url value='/admin/sys/user/listSysUser.do'/>" onsubmit="return navTabSearch(this)">
<div class="pageHeader">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>手机号：</label>
				<input type="text" name="phone" value="${param.phone}"/>
			</li>
			<li>
				<label>用户名：</label>
				<input type="text" name="name" value="${param.name}"/>
			</li>
		</ul>
		<div class="subBar">
			<%-- <span style="margin-left: 5px; line-height: 25px; float: left">Matching Records Found: <strong>${vo.totalCount}</strong></span> --%>
			<ul>						
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">搜索</button></div></div></li>
			</ul>
		</div>
	</div>
</div>
</form>

<div class="pageContent">

	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" target="navTab" rel="newsNav" href="<c:url value='/admin/sys/user/addSysUser'/>" title="添加管理员"><span>添加</span></a></li>
			<li><a class="edit" target="navTab" href="<c:url value='/admin/sys/user/editSysUser/{slt_userId}'/>" title="编辑管理员信息"><span>编辑</span></a></li>
			<li><a class="delete" target="ajaxTodo" href="<c:url value='/admin/sys/user/deleteSysUser/{slt_userId}'/>" title="删除管理员"><span>删除</span></a></li>
			<li class="line">line</li>	
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="50px"></th>
				<th>用户编号</th>
				<th width="140px">用户名称</th>
				<th width="140px">手机号</th>
				<th width="140px">性别</th>
				<th width="140px">邮箱</th>
				<th width="140px">角色名称</th>
				<th width="100px">角色类型</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="item" items="${pager.datas}" varStatus="s">
			<tr target="slt_userId" rel="${item.id}">
				<td>${s.index + 1}</td>
				<td>${item.id}</td>
				<td>${item.name}</td>
				<td>${item.phone}</td>
				<td>
					<c:choose>
							<c:when test="${item.sex == 1}">男</c:when>
							<c:otherwise>女</c:otherwise>
					</c:choose>
				</td>
				<td>${item.email}</td>
				<td>${item.grssRole.roleName}</td>
				<td>${item.grssRole.roleType}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>

	<c:import url="../../../_frag/pager/panelBar.jsp"></c:import>
</div>
