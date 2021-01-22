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

  <link href="<c:url value="/css/font-awesome.min.css" />" rel="stylesheet">
  <link href="<c:url value="/css/bootstrap.min.css" />" rel="stylesheet">
  <link href="<c:url value="/css/main.css" />" rel="stylesheet">
  <%-- <link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.min.css"> --%>
  <%-- <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css"> --%>
  <%-- <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css"> --%>
  <title>Documentum REST API</title>

  <jsp:include page="header.jsp"/>

</head>
<body>
<div class="container">

    <div class="card">

        <div class="card-body">

	        <div class="my-5">
			    <br>
    		    <h3 class="text-center">Your PDF File has been downloaded to your Downloads Folder.</h3>
    	   </div>

  		    <div class="row">

    		    <div class="col text-center my-5">
    			    <a class="btn btn-primary mb-5" href="../documentum_search_result.html">Back to Previous</a>
      			    <!-- <button class="btn btn-primary mb-5" type="button" onclick="window.history.back()">Back to Previous</button> -->
      			    <!-- <a class="btn btn-danger mx-4" href="../quit.html">Quit</a> -->
				    <%--
      			    <form action="<c:url value='/logout' />" method="post">
					    <input name="_csrf" type="hidden" value="${_csrf.token}" />
					    <input class="btn btn-danger" name="submit" type="submit" value="Logout" />
				    </form>
      			    --%>
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
