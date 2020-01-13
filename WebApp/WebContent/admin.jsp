<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin page</title>
<style type="text/css">
body {
  background: #6971a0; /* fallback for old browsers */
  background: -webkit-linear-gradient(right, #6971a0, #8DC26F);
  background: -moz-linear-gradient(right,#6971a0, #8DC26F);
  background: -o-linear-gradient(right, #6971a0, #8DC26F);
  background: linear-gradient(to left, #6971a0, #8DC26F);
  font-family: "Roboto", sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;      
}

.form input {
  font-family: "Roboto", sans-serif;
  outline: 0;
  background: #6971a0;
  width: 5%;
  border: 0;
  margin: 0 0 15px;
  padding: 15px;
  box-sizing: border-box;
  font-size: 14px;
}
table {
font-family: "Lucida Sans Unicode", "Lucida Grande", Sans-Serif;
font-size: 14px;
border-radius: 10px;
border-spacing: 0;
text-align: center;
margin: auto;

}
th {
background: #BCEBDD;
color: white;
text-shadow: 0 1px 1px #2D2020;
padding: 10px 20px;
}
th, td {
border-style: solid;
border-width: 0 1px 1px 0;
border-color: white;
}
th:first-child, td:first-child {
text-align: left;
}
th:first-child {
border-top-left-radius: 10px;
}
th:last-child {
border-top-right-radius: 10px;
border-right: none;
}
td {
padding: 10px 20px;
background: #F8E391;
}
tr:last-child td:first-child {
border-radius: 0 0 0 10px;
}
tr:last-child td:last-child {
border-radius: 0 0 10px 0;
}
tr td:last-child {
border-right: none;
}
</style>
</head>
<body>

    <table border="1">
        <tr><!-- GET HEADERS DYNAMICALLY -->
            <c:forEach items="${fieldNames}" var="fname">
                <th>${fname}</th>
            </c:forEach>
            
        </tr>
        
        <!-- GET DATA DYNAMICALLY -->
    
    <c:forEach items="${users}" var="user">
        <tr>
        	<td>${user.id}</td>
        	<td>${user.name}</td>
        	<td>${user.pass}</td>
        	<td>${user.email}</td>
        	<td>${user.isActive}</td>
        	<td>${user.role.name}</td>
        	<td><a href='editUser?id=${user.id}'>UPDATE</a> </td>
        	
        	<c:if test="${logged_user.id != user.id}">        	
        	<td><a href='admin?id=${user.id}&command=D'>DELETE</a> </td>
 			<td><a href='admin?id=${user.id}&command=A'>${user.isActive ? "DE-ACTIVATE" : "ACTIVATE"}</a></td>
 			</c:if>
        </tr>
    </c:forEach>
</table>
</body>
</html>