<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
  <head>
    <title>无线点餐后台</title>
    
    <link rel="stylesheet" type="text/css" href="/WirelessOrder/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="/WirelessOrder/css/theme.css">
    <link rel="stylesheet" href="/WirelessOrder/font-awesome/css/font-awesome.css">
    

	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script src="/WirelessOrder/bootstrap/js/bootstrap.js"></script>
   
    <link rel="stylesheet" href="/WirelessOrder/bootstrap/css/bootstrap-switch.css" />
    <script src="/WirelessOrder/bootstrap/js/bootstrap-switch.js"></script>


    <!-- Demo page code -->

    <style type="text/css">
        #line-chart {
            height:300px;
            width:800px;
            margin: 0px auto;
            margin-top: 1em;
        }
        .brand { font-family: georgia, serif; }
        .brand .first {
            color: #ccc;
            font-style: italic;
        }
        .brand .second {
            color: #fff;
            font-weight: bold;
        }
    </style>

    
  </head>
  <body class=""> 
  <!--<![endif]-->
    
        <div class="navbar">
        <div class="navbar-inner">
                <ul class="nav pull-right">
                    
                    <li><a href="#" class="hidden-phone visible-tablet visible-desktop" role="button">设置</a></li>
                    <li id="fat-menu" class="dropdown">
                        <a href="#" role="button" class="dropdown-toggle" data-toggle="dropdown">
                            <i class="icon-user"></i>${user.username}
                            <i class="icon-caret-down"></i>
                        </a>

                        <ul class="dropdown-menu">
                            <li><a tabindex="-1" href="/WirelessOrder/home.do?flag=grzx">个人中心</a></li>
                            <li class="divider"></li>
                            <li><a tabindex="-1" href="/WirelessOrder/login.do?flag=logout">退出</a></li>
                        </ul>
                    </li>
                    
                </ul>
                <a class="brand" href="/WirelessOrder/home.do?flag=home"><span class="first">无线点餐</span> <span class="second">后台</span></a>
        </div>
    </div>
    


    
    <div class="sidebar-nav">
        <form class="search form-inline">
            <input type="text" placeholder="Search...">
        </form>

        <a href="#dashboard-menu" class="nav-header" data-toggle="collapse"><i class="icon-dashboard"></i>首页</a>
        <ul id="dashboard-menu" class="nav nav-list collapse ">
            <li><a href="/WirelessOrder/home.do?flag=home">报表</a></li>
            <li><a href="/WirelessOrder/home.do?flag=grzx">个人中心</a></li>
        </ul>

        <a href="#table-menu" class="nav-header" data-toggle="collapse"><i class="icon-table"></i>餐桌
        </a>
        <ul id="table-menu" class="nav nav-list collapse">
            <li ><a href="/WirelessOrder/table.do?flag=table">餐桌管理</a></li>
        </ul>

        <a href="#menu-menu" class="nav-header collapsed" data-toggle="collapse"><i class="icon-reorder"></i>菜谱
         </a>
        <ul id="menu-menu" class="nav nav-list collapse in">
            <li ><a href="/WirelessOrder/menu.do?flag=menu">菜谱管理</a></li>
        </ul>

        <a href="#order-menu" class="nav-header" data-toggle="collapse"><i class="icon-th-large"></i>订单</a>
        <ul id="order-menu" class="nav nav-list collapse">
            <li ><a href="/WirelessOrder/order.do?flag=order">订单管理</a></li>
        </ul>
    </div>
    
    <!-- 内容 开始 -->
<div class="content">
        		 	<div id="myModal" class="modal hide fade">
					  <div class="modal-header">
					    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					    <h3>警告</h3>
					  </div>
					  <div  class="modal-body">
					    <p>你确定要删除吗？</p>
					  </div>
					  <div class="modal-footer">
								<a href="#" class="btn"  data-dismiss="modal" aria-hidden="true">关闭</a>
    							<a id="delbtn2"  href="#" class="btn btn-primary"> 确  认</a>
					  </div>
					</div>
        <div class="header">
            <h1 class="page-title">菜谱</h1>
        </div>
        
                <ul class="breadcrumb">
            <li><a href="/WirelessOrder/home.do?flag=home">主页</a> <span class="divider">/</span></li>
            <li class="active">菜谱</li>
        </ul>

        <div class="container-fluid">
            <div class="row-fluid">
            <div class="row-fluid">
                <div class="btn-toolbar">
                    <a href="/WirelessOrder/menu.do?flag=menuAdd" role="button" class="btn btn-primary" data-toggle="modal">
                    <i class="icon-plus"></i> 添 加</a>
                <div class="btn-group">
                </div>
                </div>
                <c:if test="${menuAdd=='yes'}">
				    <div class="alert alert-info">
				        <button type="button" class="close" data-dismiss="alert">×</button>
				        <strong>小提示：</strong> 添加成功！！
				    </div>
			      </c:if>
                
            </div>
            
                <div class="well">
                    <ul class="thumbnails">
                     <div class="row-fluid">  
                    <c:forEach items="${menulist}" var="menu" begin="0"  end="3">
                                    
                        <li class="span3">
                            <div class="thumbnail">
                                <img src="/WirelessOrder/images/${menu.pic}" alt="" width="220" height="170">
                                <h3>${menu.name}</h3>                             
                                <a href="/WirelessOrder/menu.do?flag=menuPre&id=${menu.id}" class="btn btn-primary"> 预 览</a>
                                <a  class="btn btn-info" href="/WirelessOrder/menu.do?flag=menuEdit&id=${menu.id}">
                                <i class="icon-edit icon-white"></i>修改
                                </a>
              					<button  onclick="del(this)"  delid="${menu.id}" pNow="${pNow}" class="btn btn-danger">
              					<i class="icon-trash icon-white"></i> 删除
              					</button>
                     
                            </div>
                        </li>
                        </c:forEach>
                        </div>
                        <div class="page-header" ></div>   
                           <div class="row-fluid">  
                    <c:forEach items="${menulist}" var="menu" begin="4"  end="8">
                                    
                        <li class="span3">
                            <div class="thumbnail">
                                <img src="/WirelessOrder/images/${menu.pic}" alt="" width="220" height="170">
                                <h3>${menu.name}</h3>                             
                                <a href="#" class="btn btn-primary"> 预 览</a>
                                <a  class="btn btn-info" href="/WirelessOrder/menu.do?flag=menuEdit&id=${menu.id}">
                                <i class="icon-edit icon-white"></i>修改
                                </a>
              					<button  onclick="del(this)"  delid="${menu.id}" pNow="${pNow}" class="btn btn-danger">
              					<i class="icon-trash icon-white"></i> 删除
              					</button>
                     
                            </div>
                        </li>
                        </c:forEach>
                        </div>
                    </ul>
                </div>
                <div class="pagination">
      <ul>
    	<c:if test="${pre=='yes'}">
        <li><a href="/WirelessOrder/menu.do?flag=menu&pageNow=${pNow-1}">前一页</a></li>
    	</c:if>
        <c:forEach var="i" begin="1" end="${pageCount}">
        <li>
        <a href="/WirelessOrder/menu.do?flag=menu&pageNow=${i }">${i }</a>
        </li>
        </c:forEach>
        <c:if test="${next=='yes'}">
        <li><a href="/WirelessOrder/menu.do?flag=menu&pageNow=${pNow+1}">后一页</a></li>
        </c:if>
    </ul>
</div>  
			<footer>
			<hr>
			<p class="pull-right">
				&copy; 2013.8 <a href="#" target="_blank"> shun_fzll</a>
			</p>
			</footer>
            </div>
        </div>
    </div>




			<script type="text/javascript">
				function del(obj) {
					var delid = $(obj).attr("delid");
					var pNow = $(obj).attr("pNow");
					var delurl = "/WirelessOrder/menu.do?flag=delMenu&delid="
							+ delid + "&pageNow=" + pNow;
					$("#myModal").modal("show").on("shown", function() {
						$("#delbtn2").attr('href', delurl);
					});
				}
			</script>
  </body>
</html>
