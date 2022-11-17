<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
	

<label for="idClient">Client : </label>
<select id="idClient" name="idClient">
	<c:forEach items="${ client }" var="client">
		<option value="${ client.id}" ${ adresse.id == client.adresse.id ? "selected" : "" }><c:out value="${ client.nom}" /> <c:out value="${ client.prenom}"/> 
		<c:out value="${ client.mail}"/><c:out value="${ client.nomSociete}"/><c:out value="${ client.telephone}" /><c:out value="${ client.adresse.id}" />
		<c:out value="${ client.genre}" /><c:out value="${ client.etat}" /></option>
	</c:forEach>
</select>
<br/>

<label for="nomClient">Nom : </label>
<input id="nomClient" name="nomClient" type="text" value="<c:out value="${ client.nom }" />" />
<br/>

<label for="prenomClient">Prenom : </label>
<input id="prenomClient" name="prenomClient" type="text" value="<c:out value="${ client.prenom }" />" />
<br/>

<label for="mailClient">Mail : </label>
<input id="mailClient" name="mailClient" type="text" value="<c:out value="${ client.mail }" />" />
<br/>

<label for="nomSocieteClient">Nom de Societe : </label>
<input id="nomSocieteClient" name="nomSocieteClient" type="text" value="<c:out value="${ nomSociete.nom }" />" />
<br/>

<label for="telephoneClient">Telephone : </label>
<input id="telephoneClient" name="telephoneClient" type="number" value="<c:out value="${ telephone.nom }" />" />
<br/>

<label for="adresseClient">Adresse : </label>
<input id="adresseClient" name="adresseClient" type="text" value="<c:out value="${ client.adresse.id }" />" />
<br/>

<label for="genreClient">Genre : </label>
<input id="genreClient" name="genreClient" type="number" value="<c:out value="${ client.genre }" />" />
<br/>

<label for="etatClient">Etat : </label>
<input id="etatClient" name="etatClient" type="number" value="<c:out value="${ client.etat }" />" />
<br/>
