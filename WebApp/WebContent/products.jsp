<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Products page</title>
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
    
    <c:forEach items="${products}" var="product">
        <tr>
        	<td>${product.id}</td>
        	<td>${product.name}</td>
        	<td>${product.detail}</td>
        	<td><img src="${product.imgPath}" ></td> 
        </tr>
        </c:forEach>
        </table>
</body>
</html>