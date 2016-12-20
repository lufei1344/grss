<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/include.inc.jsp"%>
<h2 class="contentTitle">添加场馆</h2>

<form action="<c:url value='/admin/club/saveClub?navTabId=listClubNav&callbackType=closeCurrent'/>" method="post" class='required-validate pageForm' onsubmit="return iframeCallback(this, navTabAjaxDone);" enctype="multipart/form-data">

<div class="pageFormContent" layoutH="97">
	<div class="unit">
		<label>场馆名称</label>
		<input type="text" name="clubName" maxlength="200" size="100"/>
	</div>
	<div class="unit">
		<label>场馆电话</label>
		<input type="text" name="clubTel" maxlength="200" size="100"/>
	</div>
	<div class="unit">
		<label>场馆类型</label>
		<select name="type" style="width: 59rem">
			<option value="Gym">健身房</option>
			<option value="Swimming">游泳</option>
			<option value="SwimmingAndGym">游泳健身</option>
			<option value="Yoga">瑜伽</option>
			<option value="FitStudio">健身工作室</option>
			<option value="DanceStudio">舞蹈工作室</option>
			<option value="Badminton">羽毛球</option>
		</select>
	</div>
	<div class="unit">
		<label>所在省份</label>
		<input type="text" name=province maxlength="200" size="100" value="北京"/>
	</div>
	<div class="unit">
		<label>所在城市</label>
		<input type="text" name="city" maxlength="200" size="100" value="北京"/>
	</div>
	<div class="unit">
		<label>所在区县</label>
		<select name="area" style="width: 59rem">
			<option value="东城区">东城区</option>
			<option value="西城区">西城区</option>
			<option value="朝阳区">朝阳区</option>
			<option value="海淀区">海淀区</option>
			<option value="石景山区">石景山区</option>
			<option value="丰台区">丰台区</option>
			<!-- <option value="宣武区">宣武区</option>
			<option value="崇文区">崇文区</option> -->
			<option value="顺义区">顺义区</option>
			<option value="怀柔区">怀柔区</option>
			<option value="密云县">密云县</option>
			<option value="延庆县">延庆县</option>
			<option value="昌平区">昌平区</option>
			<option value="平谷区">平谷区</option>
			<option value="门头沟区">门头沟区</option>
			<option value="房山区">房山区</option>
			<option value="通州区">通州区</option>
			<option value="大兴区">大兴区</option>
		</select>
	</div>
	<div class="unit">
		<label>详细地址</label>
		<input type="text" name="clubAddress" maxlength="200" size="100"/>
	</div>
	<!-- <div class="unit">
		<label>场馆精度</label>
		<input type="text" name="longitude" maxlength="200" size="100"/>
	</div>
	<div class="unit">
		<label>场馆纬度</label>
		<input type="text" name="latitude" maxlength="200" size="100"/>
		<a class="button" href="../map.html" target="_blank"><span>打开窗口6</span></a>
	</div> -->
	<div class="unit">
		<label>场馆描述</label>
		<textarea rows="10" cols="98" name="clubDesc"></textarea>
	</div>
	<div class="unit">
		<label>场馆主图</label>
		<input type="file" name="clubMainImg" maxlength="200" size="100"/>
	</div>
	<div class="unit">
		<label>场馆小图标</label>
		<input type="file" name="clubIcoImg" maxlength="200" size="100"/>
	</div>
	<div class="unit">
		<label>场馆个性图片</label>
		<input type="text" hidden="hidden" value="0" id="tempInput">
		<input type="file" name="images[0]" maxlength="200" size="100"/>
		<input type="button" onclick="add(event)" value="添加">
		
	</div>
</div>
<div class="formBar">
	<ul>
		<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
		<li><div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div></li>
	</ul>
</div>
</form>
	<div class="unit" id="tempDiv">
		<label>&nbsp;</label>
		<input type="file" name="images[0]" maxlength="200" size="100"/>
		<input type="button" onclick="subtract(event)" value="移除">
	</div>
<script type="text/javascript">
	function add(e){
		var value = parseInt($("#tempInput").val()) + 1;
		$("#tempInput").val(value)
		$(e.target).parent().append("<div class='unit'>" + $("#tempDiv").html().replace("images[0]","images["+value+"]") + "</div>");
	}
	function subtract(e){
		$(e.target).parent().remove();
	}
</script>