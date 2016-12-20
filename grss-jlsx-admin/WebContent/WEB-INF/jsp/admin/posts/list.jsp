<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/include.inc.jsp"%>
<c:import url="../../_frag/pager/pagerForm.jsp"></c:import>
<style>
	.grid .gridTbody td div{
		height:100%;
	}
</style>
<form method="post" rel="pagerForm" action="<c:url value='/posts/list'/>" onsubmit="return navTabSearch(this)">
<div class="pageHeader">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>帖子内容：</label>
				<input type="text" name="idea" value="${param.idea}"/>
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
			<li><a class="delete" target="ajaxTodo" href="<c:url value='/posts/delete/{slt_id}'/>" title="确定要删除吗？"><span>删除</span></a></li>
			<li class="line">line</li>
			<li><a class="edit" target="navTab" href="<c:url value='/posts/edit/{slt_id}'/>" ><span>查看</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="50px">序号</th>
				<th>帖子标题</th>
				<th >帖子内容</th>
				<th width="100px">发帖人</th>
				<th width="150px">创建时间</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="item" items="${pager.datas}" varStatus="s">
			<tr target="slt_id" rel="${item.id}" style="height:80px">
				<td>${s.index + 1}</td>
				<td>${item.title }</td>
				<td>${item.idea }</td>
				<td>${item.grssUser.nikeName }</td>
				<td><fmt:formatDate value="${item.sendDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>

	<c:import url="../../_frag/pager/panelBar.jsp"></c:import>
</div>