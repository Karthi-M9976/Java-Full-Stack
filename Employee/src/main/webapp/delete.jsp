<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="EmployeeServlet" method="post">
  <input type="hidden" name="mess" value="delete">
Enter id to delete:<input type = "number" name="id">
<button type="submit">Delete</button> 

</form>

</body>
</html>