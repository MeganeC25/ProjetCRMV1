<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    
<fmt:setLocale value="fr_FR"/>
<input type="hidden" id="idcontient" name="idcontient" value="<c:out value="${contient.id}" />" />
<input type="hidden" id="idpanier" name="idpanier" value="<c:out value="${idpanier}" />" />    
    
<label for="idproduit">Produit</label>
<select id="idproduit" name="idproduit" title="Choisir un produit dans la liste" required>
<c:forEach items="${listProduits}" var="produit" varStatus="infoBoucle">
	<option value='<c:out value="${produit.id}" />'>
	   <c:out value="${produit.nom}" /> - <fmt:formatNumber value="${produit.prix}" type="currency" />
    </option>
</c:forEach>
</select>
<span class="erreur">${erreurs['client']}</span>

<br/>
<span class="erreur">${erreurs['produit']}</span>