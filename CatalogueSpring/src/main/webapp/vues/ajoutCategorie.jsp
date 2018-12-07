<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="springForm"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <title><spring:message code="titre.listecategories"/></title>
        
         <spring:url value="/resources/styles/style.css" var="styleCSS" />
       <link href="${styleCSS}" rel="stylesheet" />
      
       
       <spring:url value="/resources/js/fonctions.js" var="fonctionsJS" />        
       <script src="${fonctionsJS}"></script>
       
    </head>
    <body>
    
    <springForm:form  method="post" modelAttribute="categorieFormModel" action='<spring:message code="formcategorie.action.creer" />' id="formCategorie">
     <springForm:hidden  path="enCreation"/>
     <springForm:hidden  path="code"/>
     	<fieldset>
     		<legend>Categorie</legend>
			<span>
				<spring:message code="produitform.libelle.libelle" />
				<springForm:input path="nom"/>
	            <springForm:errors path="nom" cssClass="error"/>
	        </span>   
        	<p>      
	        <span>
	            <c:if test="${categorieFormModel.enCreation}">
	            	<input type="button" value="<spring:message code="bouton.creer.libelle"/>" onclick="gereCategorie('<spring:message code="formcategorie.action.creer" />')" class="buttonMod"/>
	            </c:if>
	            <c:if test="${not categorieFormModel.enCreation}">
	            	<input type="button" value="<spring:message code="bouton.modifier.libelle"/>" onclick="gereCategorie('<spring:message code="formcategorie.action.modifier" />')" class="buttonMod" />
	           		<input type="button" value="<spring:message code="bouton.supprimer.libelle"/>" onclick="gereCategorie('<spring:message code="formcategorie.action.supprimer" />')" class="buttonMod" />
	           
	            </c:if>
	         </span>
         
	         <span>   
	                <input type="submit" value="<spring:message code="bouton.annuler.libelle"/>" onclick="gereCategorie('<spring:message code="formcategorie.action.annuler" />')" class="buttonMod" />
			 </span>
	            
        </fieldset>
	</springForm:form>
    
    
        <table border="1" class="tableMod">
            <thead>
                <tr>
                    <th><spring:message code="colonne.produit.cat"/></th>
                    <th style="width: 300px"><spring:message code="titre.listeproduits"/></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${listeCategories}" var="categorie">
                    <tr>
                        <td>
                        	<spring:message code="formcategorie.action.recuperer" var="libelleUrlRecuperer" />
							<c:url value="${libelleUrlRecuperer}" var="url">
                            	<c:param name="idCat" value="${categorie.codeCategorie}"/>
                            </c:url>
                            <a href="${url}">
                                <c:out value="${categorie.nomCategorie}"/>
                            </a>
                        <br>
                        </td>
                        <td>
                        <c:forEach items="${categorie.produits}" var="produit">
                       		 	<c:out value="${produit.designation}"/> <c:out value="${produit.prix}"/> eur. En stock : <c:out value="${produit.quantite}"/><br>
                        </c:forEach>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>