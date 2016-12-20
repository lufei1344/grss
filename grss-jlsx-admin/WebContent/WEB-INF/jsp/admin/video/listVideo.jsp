<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/include.inc.jsp"%>
<c:import url="../../_frag/pager/pagerForm.jsp"></c:import>
<style>
	.grid .gridTbody td div{
		height:100%;
	}
</style>
<form method="post" rel="pagerForm" action="<c:url value='/video/listVideo.do'/>" onsubmit="return navTabSearch(this)">
<div class="pageHeader">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>视频名称：</label>
				<input type="text" name="videoName" value="${param.videoName}"/>
			</li>
			<li>
				<label>视频分类：</label>
				<select name="cateCode" id="selectVideoCat">
					<option value="" >请选择分类</option>
					<c:forEach items="${videoCats}" var="v">
						<option value="${v.cateCode}" ${param.cateCode eq v.cateCode ?'selected="selected"':''}>
							<c:choose>
								<c:when test="${fn:length(v.cateCode)==4}">
									--${v.name}
								</c:when>
								<c:when test="${fn:length(v.cateCode)==7}">
									----${v.name}
								</c:when>
								<c:when test="${fn:length(v.cateCode)==10}">
									------${v.name}
								</c:when>
								<c:otherwise>
									${v.name}
								</c:otherwise>
							</c:choose>
						</option>
					</c:forEach>
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
			<li><a class="add" target="navTab" rel="addVideoNav" href="<c:url value='/video/addVideo'/>" title="添加视频"><span>添加视频</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="140px">视频名称</th>
				<th width="100px">所属分类</th>
				<th width="100px">预览</th>
				<th width="100px">视频主图</th>
				<th width="100px">上传时间</th>
				<th width="100px">修改时间</th>
				<th width="100px">操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="item" items="${pager.datas}" varStatus="s">
			<tr target="slt_catId" rel="${item.id}" style="height:80px">
				<td>${item.name}</td>
				<td>${item.catName}</td>
				<td><video controls="controls"><source src="${item.url}">您的浏览器不支持视频播放插件，推荐使用谷歌内核浏览器</video></td>
				<td><img src="${item.imgUrl}" width="100px;" height="100px;"></td>
				<td><fmt:formatDate value="${item.uploadDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td><fmt:formatDate value="${item.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>
					<div>
						<a title="删除" target="ajaxTodo" href="<c:url value='/video/deleteVideo?id=${item.id}'/>" class="btnDel">删除</a>
						<a title="编辑" target="navTab" href="<c:url value='/video/editVideo?id=${item.id}'/>" class="btnEdit">编辑</a>
					</div>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>

	<c:import url="../../_frag/pager/panelBar.jsp"></c:import>
</div>