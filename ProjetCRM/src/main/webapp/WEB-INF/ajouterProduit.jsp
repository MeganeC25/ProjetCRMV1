<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ajouter Client</title>
<link type="text/css" rel="stylesheet"
	href="<c:url value="/inc/style.css" />" />
</head>
<body>
	<c:import url="/WEB-INF/menu.jsp" />

	<form method="POST" action="<c:url value="/ajouterProduit" />">

		<fieldset>
			<legend>Ajouter un produit</legend>

			<c:import url="/WEB-INF/produit_form.jsp" />

		</fieldset>

		<input type="submit" value="Valider" />
		<input type="reset"	value="Remettre à zéro" />

	</form>

</body>
</html>