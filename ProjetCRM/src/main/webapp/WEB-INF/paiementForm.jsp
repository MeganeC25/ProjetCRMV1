<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<label for="clientPaiement">Client</label>
<select id="clientPaiement" name="client">
	<c:forEach items="${ clients}" var="client">
		<option value="${clients.id}"
			${clients.id == paiement.client.id ? "selected" : "" }>
			<c:out value="${clients.nom}${clients.prenom}" /></option>
	</c:forEach>
</select>
<br />

<label for="numCartePaiement">Numéro de carte</label>
<input id="numCartePaiement" name="numCarte" type="number"
	value="<c:out value="${paiement.numCarte}"/>" />
<br />

<label for="codeConfPaiement">Code confidentiel</label>
<input id="codeConfPaiement" name="codeConf" type="number"
	value="<c:out value="${paiement.codeConf}"/>" />
<br />

<label for="banquePaiement">Banque</label>
<input id="banquePaiement" name="banque" type="text"
	value="<c:out value="${paiement.banque}"/>" />
<br />

<input type="submit" value="Valider" />
<input type="reset" value="Remettre à zéro" />
<br />

