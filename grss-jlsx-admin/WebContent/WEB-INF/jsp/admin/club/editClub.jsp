<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/include.inc.jsp"%>
<h2 class="contentTitle">修改场馆信息</h2>

<form action="<c:url value='/admin/club/updateClub?navTabId=listClubNav&callbackType=closeCurrent'/>" method="post" class='required-validate pageForm' onsubmit="return iframeCallback(this, navTabAjaxDone);" enctype="multipart/form-data">
<input name="clubId" type="text" hidden="hidden" value="${club.clubId}">
<div class="pageFormContent" layoutH="97">
	<div class="unit">
		<label>场馆名称</label>
		<input type="text" name="clubName" value="${club.clubName}" maxlength="200" size="100"/>
	</div>
	<div class="unit">
		<label>场馆电话</label>
		<input type="text" name="clubTel" value="${club.clubTel}"  maxlength="200" size="100"/>
	</div>
	<div class="unit">
		<label>场馆类型</label>
		<select name="type" style="width: 59rem">
			<option value="Gym" ${club.type eq 'Gym' ? 'selected="selected"' : ''}>健身房</option>
			<option value="Swimming" ${club.type eq 'Swimming' ? 'selected="selected"' : ''}>游泳房</option>
			<option value="FitStudio" ${club.type eq 'FitStudio' ? 'selected="selected"' : ''}>健身工作室</option>
			<option value="DanceStudio" ${club.type eq 'DanceStudio' ? 'selected="selected"' : ''}>舞蹈工作室</option>
		</select>
	</div>
	<div class="unit">
		<label>所在省份</label>
		<input type="text" name="province" value="${club.province}" maxlength="200" size="100" value="北京"/>
	</div>
	<div class="unit">
		<label>所在城市</label>
		<input type="text" name="city" value="${club.city}" maxlength="200" size="100" value="北京"/>
	</div>
	<div class="unit">
		<label>所在区县</label>
		<select name="area" style="width: 59rem">
			<option value="东城区" ${club.area eq '东城区' ? 'selected="selected"' : ''}>东城区</option>
			<option value="西城区" ${club.area eq '西城区' ? 'selected="selected"' : ''}>西城区</option>
			<option value="朝阳区" ${club.area eq '朝阳区' ? 'selected="selected"' : ''}>朝阳区</option>
			<option value="海淀区" ${club.area eq '海淀区' ? 'selected="selected"' : ''}>海淀区</option>
			<option value="石景山区" ${club.area eq '石景山区' ? 'selected="selected"' : ''}>石景山区</option>
			<option value="丰台区" ${club.area eq '丰台区' ? 'selected="selected"' : ''}>丰台区</option>
			<%-- <option value="宣武区" ${club.area eq '宣武区' ? 'selected="selected"' : ''}>宣武区</option>
			<option value="崇文区" ${club.area eq '崇文区' ? 'selected="selected"' : ''}>崇文区</option> --%>
			<option value="顺义区" ${club.area eq '顺义区' ? 'selected="selected"' : ''}>顺义区</option>
			<option value="怀柔区" ${club.area eq '怀柔区' ? 'selected="selected"' : ''}>怀柔区</option>
			<option value="密云县" ${club.area eq '密云县' ? 'selected="selected"' : ''}>密云县</option>
			<option value="延庆县" ${club.area eq '延庆县' ? 'selected="selected"' : ''}>延庆县</option>
			<option value="昌平区" ${club.area eq '昌平区' ? 'selected="selected"' : ''}>昌平区</option>
			<option value="平谷区" ${club.area eq '平谷区' ? 'selected="selected"' : ''}>平谷区</option>
			<option value="门头沟区" ${club.area eq '门头沟区' ? 'selected="selected"' : ''}>门头沟区</option>
			<option value="房山区" ${club.area eq '房山区' ? 'selected="selected"' : ''}>房山区</option>
			<option value="通州区" ${club.area eq '通州区' ? 'selected="selected"' : ''}>通州区</option>
			<option value="大兴区" ${club.area eq '大兴区' ? 'selected="selected"' : ''}>大兴区</option>
		</select>
	</div>
	<div class="unit">
		<label>详细地址</label>
		<input type="text" name="clubAddress" value="${club.clubAddress}" maxlength="200" size="100"/>
	</div>
	<%-- <div class="unit">
		<label>场馆精度</label>
		<input type="text" name="longitude" value="${club.longitude}" maxlength="200" size="100"/>
	</div>
	<div class="unit">
		<label>场馆纬度</label>
		<input type="text" name="latitude" value="${club.latitude}" maxlength="200" size="100"/>
	</div> --%>
	<div class="unit">
		<label>场馆描述</label>
		<textarea rows="10" cols="98" name="clubDesc">${club.clubDesc}</textarea>
	</div>
	<div class="unit">
		<label>场馆主图</label>
		<img src="${club.clubImg}" style="width: 30rem">
		<input type="file" name="clubMainImg" value="${club.clubImg}" maxlength="200" size="100"/>
	</div>
	<div class="unit">
		<label>场馆小图标</label>
		<img src="${club.clubIco}" style="width: 30rem">
		<input type="file" name="clubIcoImg" maxlength="200" size="100"/>
	</div>
	<c:forEach var="img" items="${club.clubImgs}" varStatus="s">
		<c:choose>
			<c:when test="${(s.index + 1) <= 1}">
				<div class="unit">
					<label>场馆个性图片</label>
					<img src="${img}" style="width: 30rem">
					<input type="file" name="imagesMap[${img}]" maxlength="200" size="100"/>
					<input type="button" onclick="add(event)" value="添加">
				</div>
			</c:when>
			<c:otherwise>
				<div class="unit">
					<label>&nbsp;</label>
					<img src="${img}" style="width: 30rem">
					<input type="file" name="imagesMap[${img}]" value="${img}" maxlength="200" size="100"/>
					<input type="button" onclick="subtract(event)" value="移除">
				</div>
			</c:otherwise>		
		</c:choose>
	</c:forEach>
	
</div>
<div class="formBar">
	<ul>
		<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
		<li><div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div></li>
	</ul>
</div>
</form>
	<input type="text" hidden="hidden" value="${imgsLength}" id="tempInput">
	<div class="unit" id="tempDiv">
		<label>&nbsp;</label>
		<input type="file" name="imagesMap[imagekey]" maxlength="200" size="100"/>
		<input type="button" onclick="subtract(event)" value="移除">
	</div>
<script type="text/javascript">
	function add(e){
		var value = parseInt($("#tempInput").val()) + 1;
		$("#tempInput").val(value);
		$(e.target).parent().append("<div class='unit'>" + $("#tempDiv").html().replace("imagesMap[imagekey]","imagesMap[imagekey"+value+"]") + "</div>");
	}
	function subtract(e){
		var value = parseInt($("#tempInput").val()) - 1;
		$("#tempInput").val(value);
		console.log($(e.target).parent().html());
		$(e.target).parent().remove();
	}
</script>