<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Détails du moyen de paiement</title>
<link type="text/css" rel="stylesheet"
	href="<c:url value="/inc/style.css" />" />
</head>
<body>
	<c:import url="/WEB-INF/menu.jsp" />

	<table>
		<thead>
			<tr>
				<td>Client</td>
				<td>Banque</td>
				<td>Numéro de carte</td>
				<td>Code confidentiel</td>
			</tr>
		</thead>
		<tbody>

			<tr>
				<td><c:out value="${ paiement.client.prenom} ${ paiement.client.nom}" /></td>
				<td><c:out value="${ paiement.banque}" /></td>
				<td><c:out value="${ paiement.numCarte}" /></td>
				<td><c:out value="${ paiement.codeConf}" /></td>
				
				<td><a href="<c:url value="/ModifierPaiement"><c:param name="id" value="${paiement.id}" /></c:url>"> Modifier </a></td>
				<td><a href="<c:url value="/SupprimerPaiement"><c:param name="id" value="${paiement.id}" /></c:url>"> Supprimer </a></td>
			</tr>

		</tbody>
	</table>

</body>
</html>