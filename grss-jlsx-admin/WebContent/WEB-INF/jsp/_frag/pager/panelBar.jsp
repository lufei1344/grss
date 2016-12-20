<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/include.inc.jsp"%>
<%-- <c:set var="targetType" value="${empty param.targetType ? 'navTab' : param.targetType}"/> --%>
<div class="panelBar">
	<div class="pages">
		<span>每页</span>
		<select name="pageSize" onchange="dwzPageBreak({targetType:'${targetType}',data:{numPerPage:this.value}})">
			<c:forEach begin="10" end="40" step="10" varStatus="s">
				<option value="${s.index}" ${pager.pageSize eq s.index ? 'selected="selected"' : ''}>${s.index}</option>
			</c:forEach>
		</select>

		<span>总条数: ${pager.totalCount}</span>
	</div>
	
	<div class="pagination" targetType="${targetType}" totalCount="${pager.totalCount}" numPerPage="${pager.pageSize}" pageNumShown="10" currentPage="${pager.pageNum}"></div>
</div>