<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/include.inc.jsp"%>
<h2 class="contentTitle">添加资讯</h2>

<form action="<c:url value='/carousel/saveCarousel?navTabId=carouselLiNav&callbackType=closeCurrent'/>" method="post" class='required-validate pageForm' onsubmit="return iframeCallback(this, navTabAjaxDone);" enctype="multipart/form-data">

<div class="pageFormContent" layoutH="97">
	<div class="unit">
		<label>资讯类型</label>
		<select name="type" id="selectCarouselType" class="required">
			<option value="" selected="selected">请选择类型</option>
			<option value="CAROUSEL" >轮播</option>
			<option value="COACH" >教练</option>
			<option value="CLUB" >场馆</option>
			<option value="COMPETITION" >赛事</option>
		</select>
	</div>
	<div class="unit">
		<label>资讯标题</label>
		<input type="text" name="title" maxlength="200" size="100" class="required"/>
	</div>
	<div class="unit">
		<label>资讯图片</label>
		<input type="file" name="carouselImg" maxlength="200" size="100"/>
	</div>
	<div class="unit">
		<label>链接地址</label>
		<input type="text" name="linkUrl" maxlength="200" size="100" class="required"/>
	</div>
	<div class="unit">
		<label>是否显示</label>
		<input type="radio" name="isShow" value="1" checked="checked"/>显示
		<input type="radio" name="isShow" value="0"/>不显示
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