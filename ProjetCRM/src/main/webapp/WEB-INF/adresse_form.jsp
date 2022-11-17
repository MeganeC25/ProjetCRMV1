<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
	

<label for="rueAdresse">Rue : </label>
<input id="rueAdresse" name="rue" type="text" value="<c:out value="${ adresse.rue }" />" />
<br/>

<label for="villeAdresse">Ville : </label>
<input id="villeAdresse" name="ville" type="text" value="<c:out value="${ adresse.ville }" />" />
<br/>

<label for="codePostalAdresse">Code postal : </label>
<input id="codePostalAdresse" name="codePostal" type="number" value="<c:out value="${ adresse.codePostal }" />" />
<br/>

<label for="paysAdresse">Pays : </label>
<input id="paysAdresse" name="pays" type="text" value="<c:out value="${ adresse.pays }" />" />
<br/>