<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <title>Drug</title>
</head>

<body>
<h1>All drugs</h1>
<a href="${pageContext.request.contextPath}/drug/create">Create new drug</a>

<br/>

<table>

    <thead>
    <tr>
        <td>№</td>
        <td>name</td>
        <td>delete</td>
    </tr>
    </thead>

    <tbody>
    <c:forEach items="${drugs}" var="a" varStatus="idx">
        <tr>
            <td>${idx.getIndex()+1}</td>
            <td>${a.name}</td>
            <td>
                <form action="${pageContext.request.contextPath}/drug/delete" method="post">
                    <label>
                        <input name="id" value="${a.uniqueId}" hidden>
                    </label>
                    <div>
                        <button type="submit">delete</button>
                    </div>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>