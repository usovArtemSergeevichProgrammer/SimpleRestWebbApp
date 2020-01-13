<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit User</title>
<link rel="stylesheet" type="text/css" href="css/login-style.css">
</head>
<body>
<div class="login-page">
  <div class="form">
    <form class="register-form" action="editUser?id=${user.id}" method="post">
    <input type="number" value="${user.id}" />
      <input type="text" placeholder="name" name= "name" value="${user.name}" required/>
      <input type="text" placeholder="email address" name="email" value="${user.email}" required/>
      <!--  -->
      <select required="required" name="user_role">
      	<c:forEach items="${roles}" var ="role">
      	<c:choose>
      	<c:when test="${user.role.name eq role.name}">
      		<option selected="selected">${role.name}</option> 
      	</c:when>
      	<c:otherwise>
      		<option>${role.name}</option>
      	</c:otherwise>
      	</c:choose>
      	</c:forEach>
      </select>
      <input type="submit" value="UPDATE">
    </form>
  </div>
</div>
</body>
</html>