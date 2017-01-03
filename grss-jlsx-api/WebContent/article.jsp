<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>


<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <link href="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/js/bootstrap/style.min.css?v=4.1.0" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/bootstrap/jquery.min.js?v=2.1.4"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js?v=3.3.6"></script>
    <script type="text/javascript">
    	function show(){
    		var url = "${pageContext.request.contextPath}/api/getDynamic"
    		var data = new Object();
    		data.token = "";
    		data.postsId = "${param.id}";
    		$.post(url,data,function(redata){
    			if(redata.code == "1"){
    				var o = redata.result[0];
    				$("#title").text(o.title);
    				$("#content").text(o.idea);
    				var pic = "";
    				for(var i=0; i<o.imageUrls.length; i++){
    					pic += "<img class='img-responsive' src='"+o.imageUrls[i]+"'/>";
    				}
    				$("#pic").html(pic);
    				
    				$("#userPhoto")[0].src = o.grssUser.userPhoto;
    				$("#nickName").text(o.grssUser.nikeName);
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
    <div class="wrapper wrapper-content  animated fadeInRight article">
       
                    <div class="ibox-content">
                       	<div class="row">
                            <div class="col-lg-12">

                                <h2>用户：<a href="http://101.201.44.233/static/file/share/share.html">客户端下载</a></h2>
                                <div class="social-feed-box">
                                    <div class="social-avatar">
                                        <a href="#" class="pull-left">
                                            <img alt="image" id="userPhoto" src="">
                                        </a>
                                        <div class="media-body">
                                            <a href="#" id="nickName">
                                                </a>
                                        </div>
                                    </div>
                                </div>
								<div class="social-body">
                                        <p>
                                            
                                        </p>
                                    </div>
                            </div>
                        </div>
                        <div class="text-center article-title">
                            <h2 id="title">
                                </h2>
                        </div>
                        <p id="content">
                        </p>
                        <p id="pic">
                        </p>
                        <hr>

                        


                    </div>
                    
                    <div class="navbar-fixed-bottom">
                    <div class="row" style="background-color: white;">
                    	<div class="col-xs-6"><h3>您的移动健身教练</h3></div>
                    	<div class="col-xs-6">
                    		<a class="btn" href="http://url.cn/28QETW3">马上下载</a>
                    		<a class="btn btn-primary">打开</a>
                    	</div>
                    </div>
                    </div>
               

    </div>
</body>


</html>
