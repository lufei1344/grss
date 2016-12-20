<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/include.inc.jsp"%>
<c:import url="../../_frag/pager/pagerForm.jsp"></c:import>
<style>
	.grid .gridTbody td div{
		height:100%;
	}
</style>
<form method="post" rel="pagerForm" action="<c:url value='/admin/order/list'/>" onsubmit="return navTabSearch(this)">
<div class="pageHeader">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>订单编号：</label>
				<input type="text" name="id" value="${param.id}"/>
			</li>
			<li>
				<label>订单状态：</label>
				<select name="state">
					<option value="" >全部</option>
					<option value="0" ${param.state =='0'? 'selected' :'' }>下单未支付</option>
					<option value="1" ${param.state =='1'? 'selected' :'' }>下单未完成</option>
					<option value="2" ${param.state =='2'? 'selected' :'' }>订单完成</option>
				</select>
			</li>
			<li>
				<label>下单开始日期：</label>
				<input type="text" name="startDate" class="date textInput readonly valid" readonly="true" value="${param.startDate}">
				<a class="inputDateButton" href="javascript:;">选择</a>
			</li>
			<li>
				<label>下单结束日期：</label>
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
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="50px">序号</th>
				<th>订单编号</th>
				<th >订单金额</th>
				<th >订单状态</th>
				<th>买家用户名称</th>
				<th width="150px">下单日期</th>
				<th>用户备注说明</th>
				<th>教练备注说明</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="item" items="${pager.datas}" varStatus="s">
			<tr target="slt_id" rel="${item.id}" style="height:80px">
				<td>${s.index + 1}</td>
				<td>${item.id}</td>
				<td>${item.amount }</td>
				<td>
					<c:if test="${item.state =='0' }">未支付</c:if>
					<c:if test="${item.state =='1' }">已支付</c:if>
					<c:if test="${item.state =='2' }">订单完成</c:if>
				</td>
				<td>${item.grssUser.nikeName}</td>
				<td><fmt:formatDate value="${item.orderDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>${tiem.userComment }</td>
				<td>${tiem.coachComment }</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>

	<c:import url="../../_frag/pager/panelBar.jsp"></c:import>
</div>