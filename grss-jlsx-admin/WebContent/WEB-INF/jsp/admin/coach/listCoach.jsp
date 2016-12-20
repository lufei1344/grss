<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/include.inc.jsp"%>
<c:import url="../../_frag/pager/pagerForm.jsp"></c:import>

<form method="post" rel="pagerForm" action="<c:url value='/admin/coach/listCoach.do'/>" onsubmit="return navTabSearch(this)">
<div class="pageHeader">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>手机号：</label>
				<input type="text" name="userPhone" value="${param.userPhone}"/>
			</li>
			<li>
				<label>资料完整：</label>
				<select name="infoComplete">
					<option value="">全部</option>
					<option value="1" ${'1' eq param.infoComplete ? 'selected="selected"' : ''}>已完整</option>
					<option value="0" ${'0' eq param.infoComplete ? 'selected="selected"' : ''}>未完整</option>
				</select>
			</li>
			<li>
				<label>用户状态：</label>
				<select name="status">
					<option value="">全部</option>
					<option value="1" ${'1' eq param.status ? 'selected="selected"' : ''}>正常使用</option>
					<option value="2" ${'2' eq param.status ? 'selected="selected"' : ''}>封号</option>
					<%-- <option value="3" ${'3' eq param.status ? 'selected="selected"' : ''}>教练信息审核中</option> --%>
				</select>
			</li>
			
		</ul>
		<ul class="searchContent" style="float:left">
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
			<%-- <li><a class="add" target="navTab" rel="newsNav" href="<c:url value='/management/news/add'/>" title="Add News"><span>添加</span></a></li> --%>
			<%-- <li><a class="edit" target="navTab" rel="newsNav" href="<c:url value='/management/news/edit/{slt_newsId}'/>" title="Edit News"><span>修改</span></a></li> --%>
			<%-- <li><a class="delete" target="ajaxTodo" href="<c:url value='/management/news/delete/{slt_newsId}'/>" title="Are you sure remove?"><span>删除</span></a></li> --%>
			<li><a class="edit" target="navTab" href="<c:url value='/admin/coach/restPassowrd/{slt_userId}'/>" title="重置密码"><span>重置密码</span></a></li>
			<li><a class="delete" target="ajaxTodo" href="<c:url value='/admin/coach/forbiddenAccount/{slt_userId}'/>" title="确定禁用此用户吗？"><span>封号</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="50px"></th>
				<th>用户编号</th>
				<th width="140px">用户手机号</th>
				<th width="140px">用户昵称</th>
				<th width="100px">性别</th>
				<th width="100px">教练信息是否完整</th>
				<th width="100px">用户状态</th>
				<th width="100px">注册时间</th>
				<th width="100px">教练资格通过时间</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="item" items="${pager.datas}" varStatus="s">
			<tr target="slt_userId" rel="${item.userId }">
				<td>${s.index + 1}</td>
				<td>${item.userId }</td>
				<td>${item.userPhone}</td>
				<td>${item.nikeName}</td>
				<td>
					<c:choose>
							<c:when test="${item.userSex == 1}">男</c:when>
							<c:otherwise>女</c:otherwise>
					</c:choose>
				</td>
				<td>
					<c:if test="${item.userType == 'coach'}">
					<c:choose>
						<c:when test="${item.infoComplete == 1}">
							资料已完整
						</c:when>
						<c:otherwise>
							资料未完整
						</c:otherwise>
					</c:choose>
					</c:if>
				</td>
				<td>
					<c:choose>
						<c:when test="${item.status == 1}">
							正常使用
						</c:when>
						<c:when test="${item.status == 2}">
							封号
						</c:when>
						<c:otherwise>
							教练信息审核中
						</c:otherwise>
					</c:choose>
				</td>
				<td><fmt:formatDate value="${item.regDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td><fmt:formatDate value="${item.regCoachDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>

	<c:import url="../../_frag/pager/panelBar.jsp"></c:import>
</div>