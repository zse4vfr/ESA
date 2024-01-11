<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <title>New drug</title>
</head>

<body>
<form action="${pageContext.request.contextPath}/drug/create" method="post">
  <label>
    name
    <input name="name" required>
  </label>
  <label>
    category
    <input name="category" required>
  </label>
  <label>
    quantity
    <input name="quantity" type="number" required>
  </label>
  <label>
    conditions
    <input name="conditions" required>
  </label>
  <div>
    <button type="submit">create</button>
  </div>
</form>
</body>

</html>