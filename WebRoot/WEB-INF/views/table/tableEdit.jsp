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
        <ul id="table-menu" class="nav nav-list collapse in">
            <li ><a href="/WirelessOrder/table.do?flag=table">餐桌管理</a></li>
        </ul>

        <a href="#menu-menu" class="nav-header collapsed" data-toggle="collapse"><i class="icon-reorder"></i>菜谱
         </a>
        <ul id="menu-menu" class="nav nav-list collapse">
            <li ><a href="/WirelessOrder/menu.do?flag=menu">菜谱管理</a></li>
        </ul>

        <a href="#order-menu" class="nav-header" data-toggle="collapse"><i class="icon-th-large"></i>订单</a>
        <ul id="order-menu" class="nav nav-list collapse">
            <li ><a href="/WirelessOrder/order.do?flag=order">订单管理</a></li>
        </ul>
    </div>
    

    

    <!-- 内容 开始 -->
 <div class="content">
        
        <div class="header">
            
            <h1 class="page-title">餐桌</h1>
        </div>
        
                <ul class="breadcrumb">
            <li><a href="/WirelessOrder/table.do?flag=table">餐桌</a> <span class="divider">/</span></li>
            <li class="active">信息修改</li>
        </ul>
        <div class="container-fluid">
        <c:if test="${updtbl=='yes'}">
        <div class="row-fluid">

    <div class="alert alert-info">
        <button type="button" class="close" data-dismiss="alert">×</button>
        <strong>小提示：</strong> 保存成功！！
    </div>
</div>
        </c:if>
           
           <!-- begin -->
           <div class="row-fluid sortable">
				<div class="box span12">
					<div class="box-content">
					<div class="page-header" ></div>
						<form class="form-horizontal" action="/WirelessOrder/table.do?flag=updTable" method="POST">
							<fieldset>
						<div class="control-group">
								<label class="control-label">桌  号</label>
								<div class="controls">
								 <input name="tid" class="input-xlarge"  type="hidden" value="${tbl.id}">
								  <span  class="input-xlarge uneditable-input ">${tbl.id}</span>
								</div>
							  </div>
							
							  <div class="control-group">
								<label class="control-label" for="orderid">订 单 号</label>
								<div class="controls">
								  <input name="ordId" class="input-xlarge focused" id="orderid" type="text" value="${tbl.ordId}">
								</div>
							  </div>
							  <div class="control-group">
								<label class="control-label" for="num">人  数</label>
								<div class="controls">
								  <input name="num" class="input-xlarge focused" id="num" type="text" value="${tbl.num}">
								</div>
							  </div>
							  <div class="control-group">
								<label class="control-label" for="desc">描  述</label>
								<div class="controls">
								  <input name="description" class="input-xlarge focused" id="desc" type="text" value="${tbl.description}">
								</div>
							  </div>
							  <div class="control-group info">
								<label class="control-label" for="tflag">餐桌状态</label>
								<div class="controls">
								  <input name="tflag" class="input-xlarge focused" id="tflag" type="text" value="${tbl.flag}">
								  <span class="help-inline">"1"表示空闲 , "0"表示满座</span>
								</div>
							  </div>						
							<div class="form-actions">
								<button type="submit" class="btn btn-primary">保  存</button>
								<a href="/WirelessOrder/table.do?flag=table">
								<input type="button" class="btn" value="返  回">
							  </a>
							  </div>
							</fieldset>
					</div>
						  </form>
					
					</div>
                  <footer>
                        <hr>
                        <p class="pull-right">&copy; 2013.8 <a href="#" target="_blank"> shun_fzll</a></p>
                    </footer>
				</div>
			
			</div>
           
           
           <!-- end -->
            
        </div>
 
                    
                    

    <script type="text/javascript">
    var 	xmlhttp;
        $('.make-switch').on('switch-change', function (e, data){
	//创建xmlHttp  
	createXMLHttpRequest();

	var url="/WirelessOrder/TableServlet?tid="+$(this).attr('id');
	
	xmlhttp.onreadystatechange=state_Change;  
	xmlhttp.open("POST",url,true);  
	xmlhttp.send(null);  
	});


function createXMLHttpRequest() {
	if (window.XMLHttpRequest)
	  {// code for IE7, Firefox, Opera, etc.
	  xmlhttp=new XMLHttpRequest();
	  }
	else if (window.ActiveXObject)
	  {// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  }
}

//处理返回结果
function state_Change()  
{  
 if(xmlhttp.readyState==4)  
 {  
  if(xmlhttp.status==200)  
  {  
	  
  }  
 }  
}  

    </script>
  </body>
</html>
