<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ page isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <title>Exception</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    </head>
    <body>
        <div class="container">
            <h2 align="center" class="text-primary">PAGE ERREUR</h2>
            <hr />
     
            <!-- Error Code -->
            <div id="errorcode">
            
                <c:if test="${not empty exception.code}">
                    <h3 class="text-info">Code erreur : ${exception.code}</h3>
                </c:if>
                <c:if test="${empty exception.code}">
                    <h3 class="text-info">Code erreur inconnu</h3>
                </c:if>
            </div>
     
            <!-- Error Message -->
            
            <div id="errormessage">
                <c:if test="${not empty exception.message}">
                    <h4 class="text-danger">${exception.message}</h4>
                </c:if>
                 <c:if test="${empty exception.message}">
                    <h3 class="text-info">Message erreur inconnu</h3>
                </c:if>
            </div>
        </div>
    </body>
</html>
