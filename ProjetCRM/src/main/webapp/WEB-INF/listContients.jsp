<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Contenu du Panier</title>
	<link type="text/css" rel="stylesheet" href="<c:url value="/inc/style.css" />" />
</head>
<body>
	<c:import url="/WEB-INF/menu.jsp" />
     <fmt:setLocale value="fr_FR"/>
     
    <h2>Contenu du panier numéro <c:out value="${idpanier}" /></h2>

	<a href="<c:url value="/ajouterContient" ><c:param name="idpanier" value="${idpanier}" /></c:url>">
	   <button>Ajouter un produit au panier</button>
	</a>
	
	<div>
		<table class="table_list">
			<thead>
				<tr>
					<th>Nom du produit</th>
					<th>Prix</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="contient" items="${requestScope['listContients']}">
					<tr>
						<td><c:out value='${contient.produit.nom}' /></td>
						<td class="text_right"><fmt:formatNumber value="${contient.produit.prix}" type="currency" /></td>
						<td>
						 | <a href="<c:url value="/supprimerContient"><c:param name="idcontient" value="${contient.id}" /><c:param name="idpanier" value="${contient.panier.id}" /></c:url>">Supprimer</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<br/><br/>
	<span>${ sessionScope.messageContient }</span>
</body>
</html>