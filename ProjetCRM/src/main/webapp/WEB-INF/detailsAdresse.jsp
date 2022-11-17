<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Details Adresse</title>
<link type="text/css" rel="stylesheet"
	href="<c:url value="/inc/style.css" />" />
</head>
<body>
	<c:import url="/WEB-INF/menu.jsp" />

	<table>
		<thead>
			<tr>
				<td>Rue</td>
				<td>Ville</td>
				<td>Pays</td>
				<td>Code Postal</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${ adresses }" var="adresse">
				<tr>
					<td><c:out value="${ adresses.rue}" /></td>
					<td><c:out value="${ adresses.ville}" /></td>
					<td><c:out value="${ adresses.pays}" /></td>
					<td><c:out value="${ adresses.codePostal}" /></td>
					
				</tr>
			</c:forEach>
		</tbody>
	</table>
