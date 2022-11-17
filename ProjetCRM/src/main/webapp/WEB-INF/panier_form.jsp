<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<input type="hidden" id="idpanier" name="idpanier" value="<c:out value="${panier.id}" />" />    
    
<label for="idclient">Client</label>
<select id="idclient" name="idclient" title="Choisir un client dans la liste" required>
<c:forEach items="${listClients}" var="client" varStatus="infoBoucle">
	<option value='<c:out value="${client.id}" />'  <c:out default="" value="${panier.client.id == client.id ? 'selected' : ''}"/> >
	   <c:out value="${client.nomSociete}" /> - <c:out value="${client.nom}" /> <c:out value="${client.prenom}" />
    </option>
</c:forEach>
</select>
<span class="erreur">${erreurs['client']}</span>

<br/>
<span class="erreur">${erreurs['panier']}</span>
