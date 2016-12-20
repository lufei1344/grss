<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/include.inc.jsp"%>
<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>
<style>
	.grid .gridTbody td div{
		height:100%;
	}
</style>
<form method="post" rel="pagerForm" action="<c:url value='/videoCategory/listVideoCategory.do'/>" onsubmit="return navTabSearch(this)">
<div class="pageHeader">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>分类名称：</label>
				<input type="text" name="catName" value="${param.catName}"/>
			</li>
		</ul>
		<div class="subBar">
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
			<li><a class="add" target="navTab" rel="newsNav" href="<c:url value='/videoCategory/addVideoCategory'/>" title="添加分类"><span>添加</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="140px">分类名称</th>
				<th width="100px">分类等级</th>
				<th width="100px">分类编号</th>
				<th width="100px">栏目名称</th>
				<th width="100px">创建时间</th>
				<th width="100px">更新时间</th>
				<th width="100px">操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="item" items="${pager.datas}" varStatus="s">
			<tr target="slt_catId" rel="${item.id}" style="height:80px">
				<td>${item.name}</td>
				<td>
					<c:choose>
						<c:when test="${fn:length(item.cateCode)==4}">
							一级分类
						</c:when>
						<c:when test="${fn:length(item.cateCode)==7}">
							二级分类
						</c:when>
						<c:otherwise>
							三级分类
						</c:otherwise>
					</c:choose>
				</td>
				<td>${item.cateCode}</td>
				<td>${item.programaName}</td>
				<td><fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td><fmt:formatDate value="${item.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>
					<div>
						<a title="删除" target="ajaxTodo" href="<c:url value='/videoCategory/deleteVideoCategory?id=${item.id}'/>" class="btnDel">删除</a>
						<a title="编辑" target="navTab" href="<c:url value='/videoCategory/editVideoCategory?id=${item.id}'/>" class="btnEdit">编辑</a>
					</div>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>

	<c:import url="../../../_frag/pager/panelBar.jsp"></c:import>
</div>