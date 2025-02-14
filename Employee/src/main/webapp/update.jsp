<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Update User</title>
</head>
<body>
    <h2>Update User</h2>

    <form action="EmployeeServlet" method="post">
        <input type="hidden" name="mess" value="put">
        ID: <input type="number" name="id" required><br><br>
        New Name: <input type="text" name="name" required><br><br>
        New Email: <input type="email" name="email" required><br><br>
        New Dept: <input type="text" name="dept" required>
        <input type="submit" value="Update User">
    </form>
</body>
</html>
