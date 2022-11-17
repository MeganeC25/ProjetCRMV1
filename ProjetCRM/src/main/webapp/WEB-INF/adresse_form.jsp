<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
	

<label for="idAdresse">Adresse : </label>
<select id="idAdresse" name="idAdresse">
	<c:forEach items="${ adresse }" var="adresse">
		<option value="${ adresse.id}" ${ adresse.id == client.adresse.id ? "selected" : "" }><c:out value="${ adresse.rue}" /> <c:out value="${ adresse.ville}"/> <c:out value="${ adresse.codePostal}"/><c:out value="${ adresse.pays}"/></option>
	</c:forEach>
</select>
<br/>

<label for="rueAdresse">Rue : </label>
<input id="rueAdresse" name="rueAdresse" type="text" value="<c:out value="${ adresse.rue }" />" />
<br/>

<label for="villeAdresse">Ville : </label>
<input id="villeAdresse" name="villeAdresse" type="text" value="<c:out value="${ adresse.ville }" />" />
<br/>

<label for="codePostalAdresse">Code postal : </label>
<input id="codePostalAdresse" name="codePostalAdresse" type="number" value="<c:out value="${ adresse.codePostal }" />" />
<br/>

<label for="paysAdresse">Pays : </label>
<input id="paysAdresse" name="paysAdresse" type="text" value="<c:out value="${ adresse.pays }" />" />
<br/>