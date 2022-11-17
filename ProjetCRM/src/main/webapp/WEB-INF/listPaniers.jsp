<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Liste Paniers</title>
	<link type="text/css" rel="stylesheet" href="<c:url value="/inc/style.css" />" />
</head>
<body>
	<c:import url="/WEB-INF/menu.jsp" />

	<a href="<c:url value="/ajouterPanier" />"><button>Ajouter un panier</button></a>
	
	<div>
		<table>
			<thead>
				<tr>
					<th>Id Panier</th>
					<th>Société</th>
					<th>Client</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="panier" items="${requestScope['listPaniers']}">
					<tr>
						<td><c:out value='${panier.id}' /></td>
						<td><c:out value='${panier.client.nomSociete}' /></td>
						<td><c:out value='${panier.client.nom}' /> <c:out value='${panier.client.prenom}' /></td>
						<td>
						   <a href="<c:url value="/detailsPanier"><c:param name="id" value="${panier.id}" /></c:url>">Voir</a>
						   <a href="<c:url value="/modifierPanier"><c:param name="id" value="${panier.id}" /></c:url>">Modifier</a>
						 | <a href="<c:url value="/supprimerPanier"><c:param name="id" value="${panier.id}" /></c:url>">Supprimer</a>
						 | <a href="<c:url value="/listContients"><c:param name="idpanier" value="${panier.id}" /></c:url>">Contenu</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<br/><br/>
	<span>${ sessionScope.messagePanier }</span>
</body>
</html>