<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/include.inc.jsp"%>
<c:import url="../../_frag/pager/pagerForm.jsp"></c:import>

<form method="post" rel="pagerForm" action="<c:url value='/admin/user/listUser.do'/>" onsubmit="return navTabSearch(this)">
<div class="pageHeader">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>手机号：</label>
				<input type="text" name="userPhone" value="${param.userPhone}"/>
			</li>
			<li>
				<label>微博号：</label>
				<input type="text" name="userWeibo" value="${param.userWeibo}"/>
			</li>
			<li>
				<label>微信号：</label>
				<input type="text" name="userWeixin" value="${param.userWeixin}"/>
			</li>
			<li>
				<label>QQ号：</label>
				<input type="text" name="userQQ" value="${param.userQQ}"/>
			</li>
		</ul>
		<ul class="searchContent" style="display:initial">
			<li>
				<label>起始日期：</label>
				<input type="text" name="date3" class="date textInput readonly valid" datefmt="yyyy/MM/dd" mindate="2000-01" maxdate="2012-06" readonly="true">
			</li>
			<li>
				<label>结束日期：</label>
				<input type="text" name="date3" class="date textInput readonly valid" datefmt="yyyy/MM/dd" mindate="2000-01" maxdate="2012-06" readonly="true">
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
			<%-- <li><a class="add" target="navTab" rel="newsNav" href="<c:url value='/management/news/add'/>" title="Add News"><span>添加</span></a></li>--%>
			<li><a class="edit" target="navTab" href="<c:url value='/admin/user/restPassowrd/{slt_userId}'/>" title="重置密码"><span>重置密码</span></a></li>
			<li><a class="delete" target="ajaxTodo" href="<c:url value='/admin/user/forbiddenAccount/{slt_userId}'/>" title="确定禁用此用户吗？"><span>封号</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="50px"></th>
				<th>用户编号</th>
				<th width="140px">用户手机号</th>
				<th width="140px">用户微博</th>
				<th width="140px">用户微信</th>
				<th width="140px">用户QQ</th>
				<th width="140px">用户昵称</th>
				<th width="100px">性别</th>
				<th width="100px">用户状态</th>
				<th width="100px">注册时间</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="item" items="${pager.datas}" varStatus="s">
			<tr target="slt_userId" rel="${item.userId }">
				<td>${s.index + 1}</td>
				<td>${item.userId }</td>
				<td>${item.userPhone}</td>
				<td>${item.userWeibo}</td>
				<td>${item.userWeixin}</td>
				<td>${item.userQQ}</td>
				<td>${item.nikeName}</td>
				<td>
					<c:choose>
							<c:when test="${item.userSex == 1}">男</c:when>
							<c:otherwise>女</c:otherwise>
					</c:choose>
				</td>
				<td>
					<c:choose>
						<c:when test="${item.status == 1}">
							正常使用
						</c:when>
						<c:when test="${item.status == 2}">
							用户被封号
						</c:when>
						<c:otherwise>
							教练信息审核中
						</c:otherwise>
					</c:choose>
				</td>
				<td><fmt:formatDate value="${item.regDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>

	<c:import url="../../_frag/pager/panelBar.jsp"></c:import>
</div>
