<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Liste clients</title>
       <link type="text/css" rel="stylesheet" href="<c:url value="/inc/style.css" />" />
</head>
<body>
	<c:import url="/WEB-INF/menu.jsp" />

	<table>
		<thead>
			<tr>
				<td>Nom</td>
				<td>Prenom</td>
				<td>Adresse</td>
				<td>Nom de societe</td>
				<td>Mail</td>
				<td>Telephone</td>
				<td>Etat</td>
				<td>Genre</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${ client }" var="client">
				<tr>
					<td><c:out value="${ client.nom}" /></td>
					<td><c:out value="${ client.prenom}" /></td>
					<td><c:out
							value="${ client.adresse.rue}${ client.adresse.ville}${ client.adresse.pays}${ client.adresse.codePostal}" /></td>
					<td><c:out value="${ client.nomSociete}" /></td>
					<td><c:out value="${ client.mail}" /></td>
					<td><c:out value="${ client.telephone}" /></td>
					<td><c:out value="${ client.etat}" /></td>
					<td><c:out value="${ client.genre}" /></td>
					<td>
					<a	href="<c:url value="/DetailsClient"><c:param name="id" value="${ client.id}" /></c:url>">Voir</a>
					<a	href="<c:url value="/ModifierClient"><c:param name="id" value="${ client.id}" /></c:url>">Modifier</a>
					<a	href="<c:url value="/SupprimerClient"><c:param name="id" value="${ client.id}" /></c:url>">Supprimer</a>-->
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</body>
</html>