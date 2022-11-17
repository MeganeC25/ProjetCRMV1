<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<label for="clientPaiement">Client</label>
<select id="clientPaiement" name="client">
	<c:forEach items="${ clients}" var="client">
		<option value="${clients.id}"
			${clients.id == paiement.client.id ? "selected" : "" }><c:out value="${clients.nom}${clients.prenom}" /></option>
	</c:forEach>
</select>
<span class="erreur"> ${erreurs['clientPaiement']}</span>
<br />

<label for="numCartePaiement">Num�ro de carte</label>
<input id="numCartePaiement" name="numCarte" type="number" value="<c:out value="${paiement.numCarte}"/>" />
<span class="erreur">${erreurs['numCarte']}</span>
<br />

<label for="codeConfPaiement">Code confidentiel</label>
<input id="codeConfPaiement" name="codeConf" type="number" value="<c:out value="${paiement.codeConf}"/>" />
<span class="erreur">${erreurs['codeConf']}</span>
<br />

<label for="banquePaiement">Banque</label>
<input id="banquePaiement" name="banque" type="text" value="<c:out value="${paiement.banque}"/>" />
<span class="erreur">${erreurs['banque']}</span>
<br />


<span class="erreur">${ erreurs['paiement'] }</span>
<p class="erreur">${ resultat }</p>
<br/>

<input type="submit" value="Valider" />
<input type="reset" value="Remettre � z�ro" />
<br />

