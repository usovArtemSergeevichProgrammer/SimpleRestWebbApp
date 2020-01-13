<%@page import="static utils.AppConstants.*"%>
<%@page import=" model.User" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CRAZY USERS!</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css" />
<script src="${pageContext.request.contextPath}/js/main.js"></script>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>

<div class="svg-container">
    <!-- I crated SVG with: https://codepen.io/anthonydugois/pen/mewdyZ -->
    <svg viewbox="0 0 800 400" class="svg">
      <path id="curve" fill="#50c6d8" d="M 800 300 Q 400 350 0 300 L 0 0 L 800 0 L 800 300 Z">
      </path>
    </svg>
  </div>
  <%! User user = null; %>
  
  <% user = (User)session.getAttribute(LOGGED_USER); %>
  <main>
    <% if(user != null){%>
    <p><font color=red>Hello,  <%=user.getName()%>!</font></p>
    <p><a href=<%=request.getContextPath()+"/logout"%>> LOG OUT </a></p>
    <% } else { %>
    <p><font color=red>Hello,  Stranger!</font></p>
    <p><a href=<%=request.getContextPath()+"/login"%>> LOG IN </a></p>
    <%}%>
  </main>
  
<jsp:include page="footer.jsp"></jsp:include>
  
</body>
</html>