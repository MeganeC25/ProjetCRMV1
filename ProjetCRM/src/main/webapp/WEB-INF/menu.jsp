<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="menu">
	<h1>Gestion d'Amazin</h1>
	<ul>

		<li><a href="<c:url value="/" />">Accueil</a></li>
		<li><a href="<c:url value="/ListProduits" />">Produit</a></li>
		<li><a href="<c:url value="/listPanier" />">Panier</a></li>
		<!-- <li><a href="<c:url value="/" />">Mon compte</a></li> -->

	</ul>
</div>