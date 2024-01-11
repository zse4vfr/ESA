<%@ page import="com.example.esa.entity.Pharmacy" %>
<%@ page import="com.example.esa.entity.DrugForPharmacy" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <title>view dish</title>
</head>

<body>
<a href="${pageContext.request.contextPath}/pharmacy">go back</a>
<p/>

<div>
    <p>address : ${o.address}</p>
    <p>head : ${o.head}</p>
    <p>district : ${o.district}</p>
    <p>phone : ${o.phone}</p>
<div/>

<div>
  <%
    Pharmacy pharmacy = (Pharmacy) request.getAttribute("o");
    java.util.List<DrugForPharmacy> list
        = new java.util.ArrayList<>(pharmacy.getDrugForPharmacy());

    for(int i = 0; i < list.size(); i+=1) {
      request.setAttribute("i", i);
      request.setAttribute("name", list.get(i).getDrug().getName());
      request.setAttribute("category", list.get(i).getDrug().getCategory());
      request.setAttribute("quantity", list.get(i).getQuantity() == null ? "-" : list.get(i).getQuantity());
      request.setAttribute("conditions", list.get(i).getDrug().getConditions());
    %>

    <div>
    <p/>
        <div>Drug ${i}</div>
        <div>name : ${name}</div>
        <div>category : ${category}</div>
        <div>quantity : ${quantity}</div>
        <div>conditions : ${conditions}</div>
    <div/>
    <% } %>

<div/>

</body>

</html>