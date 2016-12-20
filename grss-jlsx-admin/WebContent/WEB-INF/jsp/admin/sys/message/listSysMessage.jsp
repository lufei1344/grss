<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/include.inc.jsp"%>
<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>
<style>
	.grid .gridTbody td div{
		height:100%;
	}
</style>
<form method="post" rel="pagerForm" action="<c:url value='/admin/sys/message/listSysMessage.do'/>" onsubmit="return navTabSearch(this)">
<div class="pageHeader">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>标题：</label>
				<input type="text" name="title" value="${param.title}"/>
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
			<li><a class="add" target="navTab" rel="newsNav" href="<c:url value='/admin/sys/message/addSysMessage'/>" title="添加系统消息"><span>添加</span></a></li>
			<%-- <li><a class="edit" target="navTab" href="<c:url value='/admin/sys/user/editSysUser/{slt_userId}'/>" title="编辑管理员信息"><span>编辑</span></a></li> --%>
			<li><a class="delete" target="ajaxTodo" href="<c:url value='/admin/sys/message/deleteSysMessage/{slt_userId}'/>" title="删除系统消息"><span>删除</span></a></li>
			<li class="line">line</li>	
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="50px"></th>
				<th width="140px">标题</th>
				<th width="140px">内容</th>
				<th width="140px">详细地址</th>
				<th width="140px">图片</th>
				<th width="100px">发送时间</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="item" items="${pager.datas}" varStatus="s">
			<tr target="slt_userId" rel="${item.id}" style="height:80px">
				<td>${s.index + 1}</td>
				<td>${item.title}</td>
				<td>${item.content}</td>
				<td><a href="${item.linkUrl}" target="_blank">${item.linkUrl}</a></td>
				<td><a href="${item.imgUrl}" target="_blank"><img src="${item.imgUrl}" width="150px" height="80px"/></a></td>
				<td><fmt:formatDate value="${item.sendTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>

	<c:import url="../../../_frag/pager/panelBar.jsp"></c:import>
</div>
