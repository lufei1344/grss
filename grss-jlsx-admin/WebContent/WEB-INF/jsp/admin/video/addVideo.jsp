<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/include.inc.jsp"%>
<h2 class="contentTitle">添加视频</h2>

<form action="<c:url value='/video/saveVideo?navTabId=videoNav&callbackType=closeCurrent'/>" method="post" class='required-validate pageForm' onsubmit="return iframeCallback(this, navTabAjaxDone);" enctype="multipart/form-data">

<div class="pageFormContent" layoutH="97">
	<div class="unit">
		<label>视频分类</label>
		<select name="catId" id="selectVideoCat" style="width: 59rem" class="required">
			<option value="" selected="selected">请选择视频分类</option>
			<c:forEach items="${videoCats}" var="v">
				<option value="${v.id}">
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
	</div>
	<div class="unit">
		<label>视频名称</label>
		<input type="text" name="name" maxlength="200" size="100" class="required"/>
	</div>
	<div class="unit">
		<label>视频主图</label>
		<input type="file" name="videoImg" maxlength="200" size="100"/>
	</div>
	<div class="unit">
		<label>视频文件</label>
		<input type="file" name="videoFile" maxlength="200" size="100"/>
	</div>
</div>
<div class="formBar">
	<ul>
		<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
		<li><div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div></li>
	</ul>
</div>
</form>
<script type="text/javascript">
	
</script>