<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <title>New pharmacy</title>
</head>

<body>
<form action="${pageContext.request.contextPath}/pharmacy/create" method="post">
<div>
<div>
      <label>
        address
        <input name="address" required>
      </label>
      <label>
         head
         <input name="head" required>
      </label>
      <label>
        district
        <input name="district" required>
      </label>
      <label>
       phone
        <input name="phone" required>
      </label>
  <div/>
<div/>

<div>

    <%
    Integer drCount = Integer.parseInt((String)request.getAttribute("drCount"));
    for(int i = 0; i < drCount; i+=1) {
      request.setAttribute("i", i);
    %>
    <div>
          <label>
            ingredient
            <select name="drug${i}">
                <c:forEach items="${allDrugs}" var="dr">
                    <option value="${dr.uniqueId}">${dr.name}</option>
                </c:forEach>
            </select>
          </label>
        <label>
            name
            <input name="name${i}" required>
        </label>
        <label>
            category
            <input name="category${i}" required>
        </label>
        <label>
            quantity
            <input name="quantity${i}" type="number" required>
        </label>
        <label>
            conditions
          <input name="conditions${i}" required>
          </label>
    <div/>
    <% } %>

<div/>

  <div>
    <button type="submit">create</button>
  </div>
</form>
</body>

</html>