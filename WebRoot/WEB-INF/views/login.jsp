<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>科帮网无线点餐 后台登陆</title>
    

	
    
   <link rel="stylesheet" type="text/css" href="/WirelessOrder/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="/WirelessOrder/css/theme.css">
    <link rel="stylesheet" href="/WirelessOrder/font-awesome/css/font-awesome.css">

	<script src="/WirelessOrder/js/jquery-1.7.2.min.js" type="text/javascript"></script>
    <script src="/WirelessOrder/bootstrap/js/bootstrap.js"></script>
   
	<style type="text/css">
  

      .form-signin {
        max-width: 300px;
        padding: 19px 29px 29px;
        margin: 0 auto 20px;
        background-color:  #fff;
        border: 1px solid #e5e5e5;
        -webkit-border-radius: 5px;
           -moz-border-radius: 5px;
                border-radius: 5px;
        -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.05);
           -moz-box-shadow: 0 1px 2px rgba(0,0,0,.05);
                box-shadow: 0 1px 2px rgba(0,0,0,.05);
      }
      .form-signin .form-signin-heading,
      .form-signin .checkbox {
        margin-bottom: 10px;
      }
      .form-signin input[type="text"],
      .form-signin input[type="password"] {
        font-size: 16px;
        height: auto;
        margin-bottom: 15px;
        padding: 7px 9px;
      }
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
    <div class="navbar">
        <div class="navbar-inner">
                <ul class="nav pull-right">
                    
                </ul>
                <a class="brand" href="index.html"><span class="first">无线点餐</span> <span class="second">系统</span></a>
        </div>
    </div>
    <div class="container" style="margin-top:100px" ">
      <form class="form-signin" action="/WirelessOrder/login.do?flag=login" method="post">
         <div class="page-header" >
          <h2>科帮网无线点餐系统</h2>
        </div>
        <input type="text" class="input-block-level" placeholder="用户名"  name="uname">
        <input type="password" class="input-block-level" placeholder="密码"  name="password">
        <input type="text" name="validateCode" class="input-block" placeholder="请输入验证码" >
       <img src="ValidateCodeServlet"  class="img-rounded" onclick="this.src='ValidateCodeServlet?'+Math.random();" />
        <label class="checkbox">
       <input type="checkbox" value="remember-me" class="right"> 记住我 
        </label>
        
        <button class="btn  btn-primary" type="submit">登陆</button>
         <button class="btn  " type="reset">重置</button>
      </form>
       <footer>
                        <hr>
                        <p class="pull-right">&copy; 2013.8 <a href="#" target="_blank"> shun_fzll</a></p>
                    </footer>

    </div> 
     
  
  </body>
</html>
