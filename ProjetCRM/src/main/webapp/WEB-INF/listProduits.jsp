<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Liste clients</title>
	<link type="text/css" rel="stylesheet" href="<c:url value="/inc/style.css" />" />
</head>
<body>
	<c:import url="/WEB-INF/menu.jsp" />
	<fmt:setLocale value="fr_FR"/>
	
	<a href="<c:url value="/ajouterProduit" />"><button>Ajouter un produit</button></a>
	
	<div>
		<table>
			<thead>
				<tr>
					<th>Nom</th>
					<th>Description</th>
					<th>Prix</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="produit" items="${requestScope['listProduits']}">
					<tr>
						<td><c:out value='${produit.nom}' /></td>
						<td><c:out value='${produit.description}' /></td>
						<td class="text_right"><fmt:formatNumber value="${produit.prix}" type="currency" /></td>
						<td>
						   <a href="<c:url value="/detailsProduit"><c:param name="id" value="${produit.id}" /></c:url>">Voir</a>
						   <a href="<c:url value="/modifierProduit"><c:param name="id" value="${produit.id}" /></c:url>">Modifier</a>
						 | <a href="<c:url value="/supprimerProduit"><c:param name="id" value="${produit.id}" /></c:url>">Supprimer</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<br/><br/>
	<span>${ sessionScope.messageProduit }</span>
</body>
</html>