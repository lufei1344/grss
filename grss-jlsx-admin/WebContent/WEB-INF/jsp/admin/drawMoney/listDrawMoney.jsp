<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/include.inc.jsp"%>
<c:import url="../../_frag/pager/pagerForm.jsp"></c:import>
<style>
	.grid .gridTbody td div{
		height:100%;
	}
</style>
<form method="post" id="searchForm" rel="pagerForm" action="<c:url value='/admin/drawMoney/listDrawMoney.do'/>" onsubmit="return navTabSearch(this)">
<div class="pageHeader">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>申请提现日期：</label>
				<input type="text" name="startDate" id="startDate"  value="${param.startDate}" class="date textInput readonly valid" readonly="readonly" />
				<a class="inputDateButton" href="javascript:;">选择</a>-
			</li>
			<li>
				<input type="text" name="endDate"  id="endDate" value="${param.endDate}" class="date textInput readonly valid" readonly="readonly" />
				<a class="inputDateButton" href="javascript:;">选择</a>
			</li>
			<li>
				<label>提现处理状态：</label>
				<select name="state" id="selectState">
					<option value="" ${param.state eq '' ?'selected="selected"':''}>请选择状态</option>
					<option value="0" ${param.state eq '0' ?'selected="selected"':''}>申请提现</option>
					<option value="1" ${param.state eq '1' ?'selected="selected"':''}>正在提现</option>
					<option value="2" ${param.state eq '2' ?'selected="selected"':''}>完成提现</option>
				</select>
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
			<li class=""><a class="icon" onclick="exportDrawMoneyExcel('<c:url value='/admin/drawMoney/exportDrawMoneyExcel'/>');" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="140px">申请提现时间</th>
				<th width="100px">提现金额</th>
				<th width="100px">提现状态</th>
				<th width="100px">提现卡号</th>
				<th width="100px">开户人</th>
				<th width="100px">开户行</th>
				<th width="100px">开户地区</th>
				<th width="100px">完成提现时间</th>
				<!-- <th width="100px">操作</th> -->
			</tr>
		</thead>
		<tbody>
		<c:forEach var="item" items="${pager.datas}" varStatus="s">
			<tr target="slt_Id" rel="${item.id}" style="height:80px">
				<td><fmt:formatDate value="${item.drawTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>${item.amount}</td>
				<td>
					<c:choose>
						<c:when test="${item.state eq 0}">申请提现</c:when>
						<c:when test="${item.state eq 1}">正在提现</c:when>
						<c:otherwise>完成提现</c:otherwise>
					</c:choose>
				</td>
				<td>${item.cardNo}</td>
				<td>${item.openAccountName}</td>
				<td>${item.openAccountBank}</td>
				<td>${item.openAccountCity}</td>
				<td><fmt:formatDate value="${item.finishTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<%-- <td>
					<div>
						<a title="删除" target="ajaxTodo" href="<c:url value='/video/deleteVideo?id=${item.id}'/>" class="btnDel">删除</a>
						<a title="编辑" target="navTab" href="<c:url value='/video/editVideo?id=${item.id}'/>" class="btnEdit">编辑</a>
					</div>
				</td> --%>
			</tr>
		</c:forEach>
		</tbody>
	</table>

	<c:import url="../../_frag/pager/panelBar.jsp"></c:import>
</div>
<script type="text/javascript">
	function exportDrawMoneyExcel(url){
		var selectState=$("#selectState").val();
		var startDate=$("#startDate").val();
		var endDate=$("#endDate").val();
		if(selectState==''){
			alertMsg.info('请选择提现处理状态！')
			return;
		}
		if(startDate==null||startDate==''||endDate==null||endDate==''){
			alertMsg.info('请选择申请提现的开始结束时间区间！')
			return;			
		}
		$.ajax({
			type: "post", 
			url: url, 
			data: "state="+selectState+"&startDate="+startDate+"&endDate="+endDate, 
			success: function(result){
				if(result=="error"){
					alertMsg.error('数据导出异常！')
				}else{
					window.open(result);
				}
			} 
		}); 
	}
</script>