<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="utils.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LOGIN</title>
<link rel="stylesheet" type="text/css" href="css/login-style.css">
</head>
<body>
<script type="text/javascript">
$('.message a').click(function(){
	   $('form').animate({height: "toggle", opacity: "toggle"}, "slow");
	});
</script>

<div class="login-page">
  <div class="form">
    <form class="login-form" action="login" method="post">
    <p class="message">${error_msg}</p>
      <input type="text" placeholder="username" name="usr"/>
      <input type="password" placeholder="password" name="psw"/>
      <input type="submit" value="login"> 
      <p class="message">Not registered? <a href="reg.html">Create an account</a></p>
    </form>
  </div>
</div>
</div>
	<% 
	String userName =  request.getParameter("usr");
    String userPass = CryptoUtils.encrypt(request.getParameter("psw"));
    %>
<main>

</main>
</body>
</html>