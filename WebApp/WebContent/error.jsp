<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ERROR</title>
<link rel="stylesheet" type="text/css" href="css/error.css">

</head>
<body>
    <form action="errorHandler" method="post">
        <div class="wrapper">
            <div class="box">
                <h1>555</h1>
                <p>${error_msg}</p>
                <p>&#58;&#40;</p>
                <p>
                    <a href="/">Let me try again!</a>
                </p>
            </div>
            <br>
            
           
        </div>
    </form>
    <button onclick="showDetailMessage()">SHOW MSG</button>
    <div class="box" id="msg" hidden="true">${error_detail_msg}</div>
    
    <script type="text/javascript">
   
    
    function showDetailMessage() {
        var x = document.getElementById("msg");
        x.hidden = false;
        
        if (x.style.display === "none") {
            
            x.style.display = "block";
        } else {
            x.style.display = "none";
        }
    }
</script>

</body>


</html>
