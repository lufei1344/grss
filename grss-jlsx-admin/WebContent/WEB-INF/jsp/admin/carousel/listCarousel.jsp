<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/include.inc.jsp"%>
<c:import url="../../_frag/pager/pagerForm.jsp"></c:import>
<style>
	.grid .gridTbody td div{
		height:100%;
	}
</style>
<form method="post" rel="pagerForm" action="<c:url value='/carousel/listCarousel.do'/>" onsubmit="return navTabSearch(this)">
<div class="pageHeader">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>资讯标题：</label>
				<input type="text" name="title" value="${param.title}"/>
			</li>
			<li>
				<label>资讯类型：</label>
				<select name="type" id="selectCarouselType">
					<option value="" >请选择类型</option>
					<option value="CAROUSEL" ${param.type eq 'CAROUSEL' ?'selected="selected"':'' } >轮播</option>
					<option value="COACH" ${param.type eq 'COACH' ?'selected="selected"':'' }>教练</option>
					<option value="CLUB" ${param.type eq 'CLUB' ?'selected="selected"':'' } >场馆</option>
					<option value="COMPETITION" ${param.type eq 'COMPETITION' ?'selected="selected"':'' }>赛事</option>
				</select>
			</li>
			<li>
				<label>是否显示：</label>
				<select name="isShow" id="selectIsShow">
					<option value="" ${param.isShow eq '' ?'selected="selected"':''}>是否显示</option>
					<option value="0" ${param.isShow eq '0' ?'selected="selected"':''}>否</option>
					<option value="1" ${param.isShow eq '1' ?'selected="selected"':''}>是</option>
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
			<li><a class="add" target="navTab" rel="addCarouselNav" href="<c:url value='/carousel/addCarousel'/>" title="添加资讯"><span>添加资讯</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="140px">资讯图片</th>
				<th width="100px">APP是否显示</th>
				<th width="100px">资讯类型</th>
				<th width="100px">资讯标题</th>
				<th width="100px">最后操作时间</th>
				<th width="100px">操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="item" items="${pager.datas}" varStatus="s">
			<tr target="slt_catId" rel="${item.id}" style="height:80px">
				<td><img src="${item.imageUrl}" width="100px;" height="100px;"></td>
				<td><a title="是否显示" target="ajaxTodo" href="<c:url value='/carousel/showCarousel?id=${item.id}&isShow=${item.isShow}'/>" >${item.isShow==1?'显示':'不显示'}</a></td>
				<td>
					<c:choose>
						<c:when test="${item.type eq 'CAROUSEL'}">
							轮播
						</c:when>
						<c:when test="${item.type eq 'COACH'}">
							教练
						</c:when>
						<c:when test="${item.type eq 'CLUB'}">
							场馆
						</c:when>
						<c:when test="${item.type eq 'COMPETITION'}">
							赛事
						</c:when>
						<c:otherwise>
							${item.type}
						</c:otherwise>
					</c:choose>
				</td>
				<td><a title="链接地址" target="_blank" href="${item.linkUrl}">${item.title}</a></td>
				<td><fmt:formatDate value="${item.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>
					<div>
						<a title="删除" target="ajaxTodo" href="<c:url value='/carousel/deleteCarousel?id=${item.id}'/>" class="btnDel">删除</a>
						<a title="编辑" target="navTab" href="<c:url value='/carousel/editCarousel?id=${item.id}'/>" class="btnEdit">编辑</a>
					</div>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>

	<c:import url="../../_frag/pager/panelBar.jsp"></c:import>
</div>