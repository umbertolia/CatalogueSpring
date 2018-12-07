<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="springForm"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <title><spring:message code="titre.listeproduits"/></title>
        
        
       <spring:url value="/resources/styles/style.css" var="styleCSS" />
       <link href="${styleCSS}" rel="stylesheet" />
      
       
       <spring:url value="/resources/js/fonctions.js" var="fonctionsJS" />        
       <script src="${fonctionsJS}"></script>
       
       
    </head>
    <body>
    
     <springForm:form  method="post" modelAttribute="produitFormModel" action='<spring:message code="formproduit.action.creer" />' id="formProduit">
     <springForm:hidden  path="enCreation"/>
     <fieldset>
     	<legend>Produit</legend>
     	<span> 
     		<span class="libelleForm"><spring:message code="produitform.libelle.codecat" /> </span>  
	        <springForm:select path="refCategorie">
	        <springForm:options items="${listeCategories}"  itemValue="codeCategorie" itemLabel="nomCategorie"/>
	        </springForm:select>
	        <springForm:errors path="refCategorie" cssClass="error"/>  
		</span>
		<p>
		<span>
			<spring:message code="produitform.libelle.ref" />
        	<c:if test="${produitFormModel.enCreation}">
            	<springForm:input path="ref"/>
            </c:if>
            <c:if test="${not produitFormModel.enCreation}">
            	<springForm:input path="ref" readonly="true"/>
            </c:if>
			<springForm:errors path="ref" cssClass="error"/>
		</span>
		<p>
		<span>
			<spring:message code="produitform.libelle.libelle" />
			<springForm:input path="libelle"/>
            <springForm:errors path="libelle" cssClass="error"/>
        </span>   
        <p>
        <span> 
            <spring:message code="produitform.libelle.prix" />
            <springForm:input path="prix"/>
            <springForm:errors path="prix" cssClass="error"/>
         </span>  
         <p>
         <span>  
            <spring:message code="produitform.libelle.qte" />
            <springForm:input path="qte"/>
            <springForm:errors path="qte" cssClass="error"/>
        </span>    
         <p>
        <span>
            <c:if test="${produitFormModel.enCreation}">
            	<input type="button" value="<spring:message code="bouton.creer.libelle"/>" onclick="gereProduit('<spring:message code="formproduit.action.creer" />')" class="buttonMod"/>
            </c:if>
            <c:if test="${not produitFormModel.enCreation}">
            	<input type="button" value="<spring:message code="bouton.modifier.libelle"/>" onclick="gereProduit('<spring:message code="formproduit.action.modifier" />')" class="buttonMod" />
            </c:if>
         </span>
         
         <span>   
                <input type="submit" value="<spring:message code="bouton.annuler.libelle"/>" onclick="gereProduit('<spring:message code="formproduit.action.annuler" />')" class="buttonMod" />
		 </span>
            
        </fieldset>
        </springForm:form>
    
        <table border="1" class="tableMod">
            <thead>
                <tr>
                	<th><spring:message code="colonne.produit.cat"/></th>
                    <th><spring:message code="colonne.produit.ref"/></th>
                    <th><spring:message code="colonne.produit.des"/></th>
                    <th><spring:message code="colonne.produit.prix"/></th>
                    <th><spring:message code="colonne.produit.qte"/></th>
                </tr>
            </thead>
            <tbody class="tableMod">
                <c:forEach items="${listeProduits}" var="produit">
                    <tr>
                    	<td><c:out value="${produit.categorie.nomCategorie}"/></td>
                        <td><c:out value="${produit.reference}"/></td>
                        <td><c:out value="${produit.designation}"/></td>
                        <td><c:out value="${produit.prix}"/></td>
                        <td><c:out value="${produit.quantite}"/></td>
                        <td>
                        	<spring:message code="formproduit.action.supprimer" var="libelleUrlSupprimer" />
                        	<c:url value="${libelleUrlSupprimer}" var="url">
                                <c:param name="refProduit" value="${produit.reference}"/>
                            </c:url>
                            <a href="${url}">
                                <spring:message code="bouton.supprimer.libelle" />
                            </a>
                        </td>
                         <td>
                         	<spring:message code="formproduit.action.recuperer" var="libelleUrlRecuperer" />
                        	<c:url value="recupererProduit" var="url">
                                <c:param name="refProduit" value="${produit.reference}"/>
                            </c:url>
                            <a href="${url}">
                                <spring:message code="bouton.modifier.libelle" />
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>