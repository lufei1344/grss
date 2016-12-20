<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/include.inc.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="Edge" />
<title>教练随行管理系统</title>

<link href="<c:url value='/styles/dwz/themes/default/style.css'/>" rel="stylesheet" type="text/css" />
<link href="<c:url value='/styles/dwz/themes/css/core.css'/>" rel="stylesheet" type="text/css" />
<link href="<c:url value='/styles/management/misc.css'/>" rel="stylesheet" type="text/css" />

<script src="<c:url value='/styles/dwz/js/speedup.js'/>" type="text/javascript"></script>
<script src="<c:url value='/styles/dwz/js/jquery-1.7.2.js'/>" type="text/javascript"></script>
<script src="<c:url value='/styles/dwz/js/jquery.cookie.js'/>" type="text/javascript"></script>
<script src="<c:url value='/styles/dwz/js/jquery.validate.js'/>" type="text/javascript"></script>
<script src="<c:url value='/styles/dwz/js/jquery.bgiframe.js'/>" type="text/javascript"></script>
<script src="<c:url value='/styles/xheditor/xheditor-1.2.1.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/styles/xheditor/xheditor_lang/zh-cn.js'/>" type="text/javascript"></script>

<script src="<c:url value='/styles/dwz/js/dwz.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/styles/dwz/js/dwz.regional.zh.js'/>" type="text/javascript"></script>
<script src="<c:url value='/styles/management/misc.js'/>" type="text/javascript"></script>
<script src="<c:url value='/styles/dwz/js/viewZoom.js'/>" type="text/javascript"></script>
<!-- <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=4XSkCSYP5zvsU8OHhFblakR6TmSCk11d"></script> -->
<script type="text/javascript">
$(function(){
	DWZ.init("<c:url value='/styles/dwz/dwz.frag.xml'/>", {
		//loginTitle:"Login",	// 弹出登录对话框
		loginUrl:"<c:url value='../login.jsp' />",	// 跳到登录页面
		pageInfo:{pageNum:"pageNum", numPerPage:"pageSize", orderField:"orderField", orderDirection:"orderDirection"}, 
		debug:false,	// 调试模式 【true|false】
		callback:function(){
			initEnv();
			$("#themeList").theme({themeBase:"<c:url value='/styles/dwz/themes'/>"});
		}
	});
});

</script>
</head>

<body scroll="no">

</div>
	<div id="layout">
		<div id="header">
			<div class="headerNav">
				<!-- <a class="logo" href="javascript:void(0)">Logo</a> -->
				<ul class="nav">
					<li><a href="<c:url value='/'/>" target="website">首页</a></li>
					<li><a href="<c:url value='/passport/signout.do'/>">注销</a></li>
				</ul>
				<ul class="themeList" id="themeList">
					<li theme="default"><div class="selected">blue</div></li>
					<li theme="green"><div>green</div></li>
					<li theme="purple"><div>purple</div></li>
					<li theme="silver"><div>silver</div></li>
					<li theme="azure"><div>天蓝</div></li>
				</ul>
			</div>
		</div>
		
		<div id="leftside">
			<div id="sidebar_s">
				<div class="collapse">
					<div class="toggleCollapse"><div></div></div>
				</div>
			</div>
			<div id="sidebar">
				<div class="toggleCollapse"><h2>菜单</h2><div>collapse</div></div>
				<div class="accordion" fillSpace="sideBar">
				<c:choose>
					<c:when test="${SESSION_AUTHENTICATION.grssRole.roleType eq 'CLUB'}">
					<div class="accordionHeader">
						<h2><span>Folder</span>系统管理</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<li><a href="<c:url value='/admin/sys/user/myInfoDetail'/>" target="navTab" rel="sysUserLiNav">个人资料</a></li>
						</ul>
					</div>
						<div class="accordionHeader">
							<h2><span>Folder</span>场馆管理</h2>
						</div>
						<div class="accordionContent">
							<ul class="tree treeFolder">
								<li><a href="<c:url value='/admin/club/listClub.do'/>" target="navTab" rel="listClubNav">场馆列表</a></li>
							</ul>
						</div>
					</c:when>
					<c:otherwise>
						<div class="accordionHeader">
						<h2><span>Folder</span>用户管理</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<li><a href="<c:url value='/admin/user/listUser.do'/>" target="navTab" rel="listUserNav">用户列表</a></li>
							<li><a href="<c:url value='/admin/coach/listCoach.do'/>" target="navTab" rel="listCoachNav">教练列表</a></li>
							<li><a href="<c:url value='/admin/audit/listAudit.do'/>" target="navTab" rel="listAuditNav">待审核列表</a></li>
							<li><a href="<c:url value='/admin/drawMoney/listDrawMoney.do'/>" target="navTab" rel="drawMoneyNav">提现管理</a></li>
						</ul>
					</div>
					
					<div class="accordionHeader">
						<h2><span>Folder</span>场馆管理</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<li><a href="<c:url value='/admin/club/listClub.do'/>" target="navTab" rel="listClubNav">场馆列表</a></li>
						</ul>
					</div>
					
					<div class="accordionHeader">
						<h2><span>Folder</span>系统管理</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<li><a href="<c:url value='/admin/sys/user/myInfoDetail'/>" target="navTab" rel="sysUserLiNav">个人资料</a></li>
							<li><a href="<c:url value='/admin/sys/user/listSysUser'/>" target="navTab" rel="sysUserLiNav">系统用户列表</a></li>
							<li><a href="<c:url value='/admin/sys/message/listSysMessage'/>" target="navTab" rel="listSysMessageNav">系统消息列表</a></li>
						</ul>
					</div>
					
					<div class="accordionHeader">
						<h2><span>Folder</span>其它</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<li><a href="<c:url value='/carousel/listCarousel'/>" target="navTab" rel="carouselLiNav">资讯管理</a></li>
							<li><a href="<c:url value='/posts/list'/>" target="navTab" rel="newsLiNav">帖子管理</a></li>
							<li><a href="<c:url value='/community/listCommunity'/>" target="navTab" rel="newsLiNav">社区管理</a></li>
						</ul>
						<ul class="tree treeFolder">
							<li><a href="<c:url value='/videoCategory/listVideoCategory.do'/>" target="navTab" rel="videoCatsNav">视频分类管理</a></li>
						</ul>
						<ul class="tree treeFolder">
							<li><a href="<c:url value='/video/listVideo.do'/>" target="navTab" rel="videoNav">视频管理</a></li>
						</ul>
					</div>
					
					<div class="accordionHeader">
						<h2><span>Folder</span>订单管理</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<li><a href="<c:url value='/admin/order/list'/>" target="navTab" rel="newsLiNav">订单管理</a></li>
							<li><a href="<c:url value='/order/setTime/orderMissTime'/>" target="navTab" rel="newsLiNav">订单失效时间管理</a></li>
						</ul>
					</div>
					</c:otherwise>
				</c:choose>
					
					
					
					
				</div>
				
			</div>
		</div>
		<div id="container">
			<div id="navTab" class="tabsPage">
				<div class="tabsPageHeader">
					<div class="tabsPageHeaderContent"><!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
						<ul class="navTab-tab">
							<li tabid="main" class="main"><a href="javascript:void(0)"><span><span class="home_icon">My Home</span></span></a></li>
						</ul>
					</div>
					<div class="tabsLeft">left</div><!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
					<div class="tabsRight">right</div><!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
					<div class="tabsMore">more</div>
				</div>
				<ul class="tabsMoreList">
					<li><a href="javascript:void(0)">My Home</a></li>
				</ul>
				<div class="navTab-panel tabsPageContent layoutBox">
					<div>
						<div class="accountInfo">
							<c:set var="contextUser" value=""></c:set>
							<div class="right">
								<p><%-- <fmt:formatDate value="${now}" pattern="dd MMMM yyyy, EEEE"/> --%></p>
							</div>
							<%-- <p><span>Welcome, ${contextUser.nickname}</span></p>
							<p>${contextUser.email}</p> --%>
						</div>
						
						<div class="pageFormContent" layoutH="80">
							<%-- <p>
								<label>用户名:</label><span class="unit">${contextUser.username}</span>
							</p>
							<p>
								<label>姓名:</label><span class="unit">${contextUser.nickname}</span>
							</p>

							<p>
								<label>电话:</label><span class="unit">${contextUser.phone}</span>
							</p>
							<p>
								<label>Email:</label><span class="unit">${contextUser.email}</span>
							</p> --%>
							
						</div>

					</div>
				</div>
			</div>
		</div>

	</div>
	
	<div id="footer"></div>


</body>
</html>