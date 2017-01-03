<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>用户信息</title>
    <link href="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/js/bootstrap/style.min.css?v=4.1.0" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/bootstrap/jquery.min.js?v=2.1.4"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js?v=3.3.6"></script>
    <script type="text/javascript">
    	function show(){
    		var url = "${pageContext.request.contextPath}/api/getCoach"
    		var data = new Object();
    		data.token = "";
    		data.id = "${param.id}";
    		$.post(url,data,function(redata){
    			if(redata.code == "1"){
    				var o = redata.result[0];
    				$("#nickName").text(o.nikeName);
    				$("#userPhoto")[0].src = o.userPhoto;
    				$("#userDesc").text(o.userDesc);
    				$("#club").text(o.toClubName);
    				$("#regDate").text(o.regDate.substring(0,10));
    				
    				$("#userPhone").text(o.userPhone);
    				$("#level").text(o.userLevel+"级");
    			}else{
    				alert(redata.message);
    			}
    		});
    	}
    	$(function(){
    		show();
    		//window.location.href = "jlsx.apk";
    	});
    </script>
</head>

<body class="gray-bg" style="background-color: #77343E;">
    <div class="wrapper wrapper-content  animated fadeInRight">
       
                <div class="ibox ">

                    <div class="ibox-content">
                        <div class="tab-content">
                            <div id="contact-1" class="tab-pane active">
                                <div class="row m-b-lg">
                                    <div class="col-lg-4 text-center">
                                        <h2 id="nickName"></h2>

                                        <div class="m-b-sm">
                                            <img alt="image" class="img-circle" id="userPhoto" src="" style="width: 62px">
                                        </div>
                                    </div>
                                    <div class="col-lg-8">
                                        <h3>
                                                个人签名
                                            </h3>
                                        <p id="userDesc">

                                        </p>
                                        
                                    </div>
                                </div>
                                <div class="client-detail">
                                    <div class="full-height-scroll">

                                        <strong>信息</strong>

                                        <ul class="list-group clear-list">
                                            <li class="list-group-item fist-item">
                                                <span class="pull-right" id="club"></span> 所在场所
                                            </li>
                                            <li class="list-group-item">
                                                <span class="pull-right" id="regDate"></span>注册日期
                                            </li>
                                            <li class="list-group-item">
                                                <span class="pull-right" id="userPhone"></span>电话
                                            </li>
                                            <li class="list-group-item">
                                                <span class="pull-right" id="level"> 11:06 </span>级别
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                   
            </div>
        </div>
    </div>

  
</body>
</html>
