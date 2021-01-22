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
  <link href="<c:url value="/css/main.css" />" rel="stylesheet">
  <%-- <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css"> --%>
  <%-- <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css"> --%>
  <title>Documentum REST API</title>

  <jsp:include page="header.jsp"/>

</head>
<body>
<div class="container">

    <div class="card">

        <div class="card-body">

	        <div>
		        <h2 class="text-center text-danger my-5">You are not authorized to access the Documentum's REST API!</h2>
	        </div>

            <div class="row mt-3">

               <div class="col text-center">
                   <a class="btn btn-primary my-5" href="documentum_search.html">Return to Search Page</a>
               </div>

            </div>

        </div>

    </div>

    <div class="my-5">
    	<br>
    	<br>
    </div>

</div><!-- container -->

<script src="<c:url value="/js/jquery.min.js" />"></script>
<script src="<c:url value="/js/popper.min.js" />"></script>
<script src="<c:url value="/js/bootstrap.min.js" />"></script>
<%-- <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script> --%>
<%-- <script src="${pageContext.request.contextPath}/js/popper.min.js"></script> --%>
<%-- <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script> --%>

<jsp:include page="footer.jsp"/>

</body>
</html>
