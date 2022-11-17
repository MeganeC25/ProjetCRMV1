<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Modifier Adresse</title>
<link type="text/css" rel="stylesheet"
	href="<c:url value="/inc/style.css" />" />
</head>
<body>
	<c:import url="/WEB-INF/menu.jsp"></c:import>
	
	<form method="POST" action="<c:url value="/modifierAdresse" />">

		<fieldset>
			<legend>Ajouter une adresse</legend>

			<c:import url="/WEB-INF/adresse_form.jsp" />

		</fieldset>

		<input type="submit" value="Valider" /> <input type="reset"
			value="Remettre à zéro" />

	</form>
</body>
</html>