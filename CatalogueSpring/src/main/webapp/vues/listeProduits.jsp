<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
        <table border="1" class="tableMod">
            <thead>
                <tr>
                    <th><spring:message code="colonne.produit.ref"/></th>
                    <th><spring:message code="colonne.produit.des"/></th>
                    <th><spring:message code="colonne.produit.prix"/></th>
                    <th><spring:message code="colonne.produit.qte"/></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${listeProduits}" var="produit">
                    <tr>
                        <td><c:out value="${produit.reference}"/></td>
                        <td><c:out value="${produit.designation}"/></td>
                        <td><c:out value="${produit.prix}"/></td>
                        <td><c:out value="${produit.quantite}"/></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>