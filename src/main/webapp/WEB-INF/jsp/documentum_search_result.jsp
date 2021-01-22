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
  <link href="<c:url value="/css/jquery.dataTables.min.css" />" rel="stylesheet">
  <link href="<c:url value="/css/main.css" />" rel="stylesheet">
  <%-- <link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.min.css"> --%>
  <%-- <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css"> --%>
  <%-- <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery.dataTables.min.css"> --%>
  <%-- <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css"> --%>
  <title>Documentum REST API</title>

  <jsp:include page="header.jsp"/>

</head>
<body>
<div class="container">

	<h3 class="text-center text-primary my-5">Legal Name: &emsp; <c:out value="${documentumObjects[0].legalName}"/></h3>

    <div class="card">

        <div class="card-body">

            <h3 class="text-center mt-5 mb-3">Below is a list of available document(s) for download</h3>

            <div class="table">
                <table id="myTable" class="table table-striped table-bordered">
                    <thead class="thead-dark">
                        <tr>
                            <th class="text-center">Document Object ID</th>
                            <th class="text-center">Document Sub Type</th>
					        <!-- <th class="text-center">Correspondence_ID</th> -->
                            <th class="text-center">LA Tax Number</th>
                            <th class="text-center">Operations</th>
                        </tr>
                    </thead>
                    <tbody>
            	        <c:forEach items="${documentumObjects}" var="object" varStatus="myIndex">
                        <tr>
                            <td class="text-center">${object.objectID}</td>
                            <td class="text-left">${object.docSubType}</td>
					        <!-- <td class="text-center">${object.correspondenceID}</td> -->
                            <td class="text-center">${object.laTaxNumber}</td>
                            <th class="text-center" scope="row">
                    	        <%-- <a class="btn btn-success" href="documentum_download.html?id=${myIndex.index}">Download</a> --%>
                    	        <a class="btn btn-info mx-3" href="documentum_view/${myIndex.index}.html">View</a>
                    	        <a class="btn btn-success" href="documentum_download/${myIndex.index}.html">Download</a>
					        </th>
                        </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

  	         <div class="row mt-3">

    	         <div class="col text-center">
    		        <a class="btn btn-primary mb-5" href="documentum_search.html">Return to Search Page</a>
      		        <!-- <button class="btn btn-primary mb-5" type="button" onclick="window.history.back()">Back to Previous</button> -->
      		        <!-- <a class="btn btn-danger mb-5 mx-4" href="quit.html">Quit</a> -->
			        <%--
      		        <form action="<c:url value='/logout' />" method="post">
				        <input name="_csrf" type="hidden" value="${_csrf.token}" />
				        <input class="btn btn-danger mb-5" name="submit" type="submit" value="Logout" />
			        </form>
			        --%>
    	         </div>

 	         </div>

        </div>

    </div>

    <div class="my-5">
        <br>
    </div>

</div><!-- container -->

<script src="<c:url value="/js/jquery.min.js" />"></script>
<script src="<c:url value="/js/popper.min.js" />"></script>
<script src="<c:url value="/js/jquery.dataTables.min.js" />"></script>
<script src="<c:url value="/js/bootstrap.min.js" />"></script>

<%-- <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script> --%>
<%-- <script src="${pageContext.request.contextPath}/js/popper.min.js"></script> --%>
<%-- <script src="${pageContext.request.contextPath}/js/jquery.dataTables.min.js"></script> --%>
<%-- <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script> --%>

<script>

$(document).ready(function() {
	$('.btn-info').on('click', function() {
	    var $this = $(this);
	    var loadingText = `<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span> Displaying...`;
	    /* var loadingText = `<span class="fa fa-circle-o-notch fa-spin"></span> Downloading`; */
	    if ($(this).html() !== loadingText) {
	      $this.data('original-text', $(this).html());
	      $this.html(loadingText);
	    }
/*   	    setTimeout(function() {
	      $this.html($this.data('original-text'));
	    }, 8000); */
	});
});

$(document).ready(function() {
	$('.btn-success').on('click', function() {
	    var $this = $(this);
	    var loadingText = `<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span> Downloading...`;

	    if ($(this).html() !== loadingText) {
	      $this.data('original-text', $(this).html());
	      $this.html(loadingText);
	    }
	    
	    setTimeout(function() {
			$this.html($this.data('original-text'));
		}, 5000);
	});
});

$(document).ready(function() {
    $('#myTable').dataTable();
});

</script>

<jsp:include page="footer.jsp"/>

</body>
</html>
