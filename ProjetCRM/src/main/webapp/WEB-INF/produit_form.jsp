<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<input type="hidden" id="idproduit" name="idproduit" value="<c:out value="${produit.id}" />" />    
    
<label for="nom">Nom</label>
<input type="text" id="nom" name="nom" value="<c:out value="${produit.nom}" />" size="255" required />
<span class="erreur">${erreurs['nom']}</span>
<br/>

<label for="description">Description</label>
<input type="text" id="description" name="description" value="<c:out value="${produit.description}" />" />
<span class="erreur">${erreurs['description']}</span>
<br/>

<label for="prix">Prix</label>
<input type="text" id="prix" name="prix" value="<c:out value="${produit.prix}" />" pattern="^[0.9]{*}.[0.9]{*}$" title="Format monetaire attendu" required />
<span class="erreur">${erreurs['prix']}</span>
<br/>

<br/>
<span class="erreur">${erreurs['produit']}</span>
