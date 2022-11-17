<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Détails Produit</title>
<link type="text/css" rel="stylesheet"
	href="<c:url value="/inc/style.css" />" />
</head>
<body>
	<c:import url="/WEB-INF/menu.jsp" />
	<fmt:setLocale value="fr_FR" />

	<div>

		<table>
			<thead>
				<tr>
					<th>Nom</th>
					<th>Description</th>
					<th>Prix</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><c:out value='${produit.nom}' /></td>
					<td><c:out value='${produit.description}' /></td>
					<td class="text_right"><fmt:formatNumber value="${produit.prix}" type="currency" /></td>
				</tr>
			</tbody>
		</table>

	</div>

</body>
</html>