<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/include.inc.jsp"%>
<h2 class="contentTitle">添加分类</h2>

<form action="<c:url value='/videoCategory/saveVideoCategory?navTabId=videoCatsNav&callbackType=closeCurrent'/>" method="post" class='required-validate pageForm' onsubmit="return iframeCallback(this, navTabAjaxDone);">

<div class="pageFormContent" layoutH="97">
	<div class="unit">
		<label>父级分类</label>
		<select name="prarentId" id="selectParentCat" style="width: 59rem" onchange="changeCat();">
			<option value="" selected="selected">请选择父级分类</option>
			<c:forEach items="${parentCats}" var="p">
				<option value="${p.id}">
					<c:choose>
						<c:when test="${fn:length(p.cateCode)==4}">
							--${p.name}
						</c:when>
						<c:when test="${fn:length(p.cateCode)==7}">
							----${p.name}
						</c:when>
						<c:otherwise>
							${p.name}
						</c:otherwise>
					</c:choose>
				</option>
			</c:forEach>
		</select>
	</div>
	<div class="unit" id="programaDiv">
		<label>分类所属栏目</label>
		<select name="programaId" id="selectPrograma" style="width: 59rem" class="required">
			<option value="" selected="selected">请选择所属栏目</option>
			<c:forEach items="${selectProgramas}" var="s">
				<option value="${s.id}">${s.name}</option>
			</c:forEach>
		</select>
	</div>
	<div class="unit">
		<label>分类名称</label>
		<input type="text" name="name" maxlength="200" size="100" class="required"/>
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
	function changeCat(){
		var selectCatVal=$("#selectParentCat ").val();
		if(selectCatVal!=""){
			$("#programaDiv").hide();
			$("#selectPrograma").attr("class","");
			$("#selectPrograma").val("");
		}else{
			$("#programaDiv").show();
			$("#selectPrograma").attr("class","required");
		}
	}
</script>