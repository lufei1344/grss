<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/include.inc.jsp"%>
<c:import url="../../_frag/pager/pagerForm.jsp"></c:import>
<style>
	.grid .gridTbody td div{
		height:100%;
	}
</style>
<form method="post" rel="pagerForm" action="<c:url value='/admin/club/listClub.do'/>" onsubmit="return navTabSearch(this)">
<div class="pageHeader">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>场馆名称：</label>
				<input type="text" name="clubName" value="${param.clubName}"/>
			</li>
			<li>
				<label>开始日期：</label>
				<input type="text" name="startDate" class="date textInput readonly valid" readonly="true" value="${param.startDate}">
				<a class="inputDateButton" href="javascript:;">选择</a>
			</li>
			<li>
				<label>结束日期：</label>
				<input type="text" name="endDate" class="date textInput readonly valid" readonly="true" value="${param.endDate}">
				<a class="inputDateButton" href="javascript:;">选择</a>
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
			<li><a class="add" target="navTab" rel="newsNav" href="<c:url value='/admin/club/addClub'/>" title="添加场馆"><span>添加</span></a></li>
			<li><a class="edit" target="navTab" rel="newsNav" href="<c:url value='/admin/club/editClub/{slt_clubId}'/>" title="修改场馆信息"><span>修改</span></a></li>
			<li><a class="delete" target="ajaxTodo" href="<c:url value='/admin/club/deleteClub/{slt_clubId}'/>" title="确定删除吗？"><span>删除</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="50px"></th>
				<th width="140px">场馆名称</th>
				<th width="100px">场馆主图</th>
				<th width="100px">场馆小图片</th>
				<th width="100px">场馆电话</th>
				<th width="100px">场馆地址</th>
				<th width="100px">创建时间</th>
				<th width="100px">更新时间</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="item" items="${pager.datas}" varStatus="s">
			<tr target="slt_clubId" rel="${item.clubId}" style="height:80px">
				<td>${s.index + 1}</td>
				<td>${item.clubName}</td>
				<td><a href="${item.clubImg}" target="_blank"><img src="${item.clubImg}" width="150px" height="80px"/></a></td>
				<td><a href="${item.clubIco}" target="_blank"><img src="${item.clubIco}" width="150px" height="80px"/></a></td>
				<td>${item.clubTel}</td>
				<td>${item.clubAddress}</td>
				<td><fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td><fmt:formatDate value="${item.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>

	<c:import url="../../_frag/pager/panelBar.jsp"></c:import>
</div>