<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta http-equiv="x-ua-compatible" content="ie=edge">
  
  <link href="<c:url value="/css/bootstrap.min.css" />" rel="stylesheet">
  <link href="<c:url value="/css/LATAXStyle.css" />" rel="stylesheet">
  <%-- <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css"> --%>
  <%-- <link rel="stylesheet" href="${pageContext.request.contextPath}/css/LATAXStyle.css"> --%>

  <title>Documentum REST API</title>

</head>

<body>

  <div>

  	<div>
		<a href="http://finance.lacity.org/" target="_blank" title="Home">
			<img class="logo-1" src="${pageContext.request.contextPath}/images/finance_logo.png">
		</a>
	</div>

    <div id="background-opacity"></div>

    <div>
    	<img class="banner-1" src="${pageContext.request.contextPath}/images/lacity.jpg">
    </div>

    <h1 class="text-center text-primary mt-5">Welcome to the Open Text Documentum REST API</h1>

  </div>
  
</body>

  <script src="//navbar.lacity.org/global_nav.js"></script>
  
</html>
