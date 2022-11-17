<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Détails Panier</title>
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
					<th>Id Panier</th>
					<th>Societe Client</th>
					<th>Nom</th>
					<th>Prenom</th>
					<th>Téléphone</th>
					<th>Email</th>
					<th>Adresse</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><c:out value='${panier.id}' /></td>
					<td><c:out value='${panier.client.nomSociete}' /></td>
					<td><c:out value='${panier.client.nom}' /></td>
					<td><c:out value='${panier.client.prenom}' /></td>
					<td><c:out value='${panier.client.telephone}' /></td>
					<td><c:out value='${panier.client.mail}' /></td>
					<td>
					    <c:out value='${panier.client.adresse.rue}' /> 
					    <c:out value='${panier.client.adresse.codePostal}' />
					    <c:out value='${panier.client.adresse.ville}' /> 
					    <c:out value='${panier.client.adresse.pays}' />
					 </td>
				</tr>
			</tbody>
		</table>

	</div>

</body>
</html>