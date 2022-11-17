<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="menu">
	<h1>Gestion d'Amazin</h1>
	<ul>

		<li><a href="<c:url value="/" />">Accueil</a></li>
		<li><a href="<c:url value="/ListProduits" />">Produit</a></li>
		<li><a href="<c:url value="/listPaniers" />">Panier</a></li>
		<li><a href="<c:url value="/listPaiements" />">Paiements</a></li>
		<!-- <li><a href="<c:url value="/" />">Mon compte</a></li> -->

	</ul>
</div>