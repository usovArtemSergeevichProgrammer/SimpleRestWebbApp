<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.time.LocalDateTime" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>JSP Title</title>
</head>
<body>
	<!-- DECLARATION -->
	<%! int counter = 0; %>
	<h1> HELLO FROM JSP! 
	<!--  SCRIPTLET  -->
	<%
		// java code is here
		++counter;
		int x = (int) (Math.random() * 6);
		if(x == 5){
	%>
		<!--  STATIC -->
		<h2> YOU WIN</h2>
		<%
		}else{
		%>
		<h2> YOU LOSE</h2>
		<% } %>
	 </h1>
	 <h1>GAME NUMBER #<%=counter%> </h1>
	 <h3>SERVER TIME: <%=LocalDateTime.now() %></h3>
	 <!-- EXPRESSION -->
	 <a href="<%=request.getRequestURI()%>">TRY AGAIN</a>
</body>
</html>