<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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

            <form:form class="main-form" modelAttribute="account">

                <fieldset class="form-group mt-5 mb-3">
                    <legend>Please Enter a 10-Digit or 15-Digit Account Number</legend>
			        <br>

                    <div class="form-group">
                        <label class="form-control-label" for="Account Number">Account Number</label>
                        <form:input class="form-control" placeholder="0123456789" path="accountNumber" /> <form:errors path="accountNumber" />
                        <small class="form-text text-muted">
                            This should be your 10-digit or 15-digit account number.
                        </small>
                   </div> <!-- form-group -->
                   <legend>Please Enter the Number of Documents to Retrieve</legend>
			       <br>
			       <div class="form-group">
                       <label class="form-control-label" for="Number of Documents">Number of Documents</label>
                       <form:input class="form-control" placeholder="10 - 300" path="numberOfDocument" /> <form:errors path="numberOfDocument" />
                       <small class="form-text text-muted">
                           This should be a number between 10 - 300.
                       </small>
                   </div> <!-- form-group -->

                </fieldset> <!-- fieldset -->

  			<div class="row">

    			<div class="col text-center">    			
      				<button class="btn btn-primary mb-5" type="Submit" name="button">Search</button>
    			</div>

 			</div>

            </form:form>

        </div>

    </div>

    <div class="my-5">
    	<br>
    </div>

</div><!-- container -->

<script src="<c:url value="/js/jquery.min.js" />"></script>
<script src="<c:url value="/js/popper.min.js" />"></script>
<script src="<c:url value="/js/bootstrap.min.js" />"></script>
<%-- <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script> --%>
<%-- <script src="${pageContext.request.contextPath}/js/popper.min.js"></script> --%>
<%-- <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script> --%>

<script>

$(document).ready(function() {
	$('.btn-primary').on('click', function() {
	    var $this = $(this);
	    var loadingText = `<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span> Loading...`;
	    /* var loadingText = `<span class="fa fa-circle-o-notch fa-spin"></span> Loading...`; */
	    if ($(this).html() !== loadingText) {
	      $this.data('original-text', $(this).html());
	      $this.html(loadingText);
	    }
/*   	    setTimeout(function() {
	      $this.html($this.data('original-text'));
	    }, 12000); */
	});
});

</script>

<jsp:include page="footer.jsp"/>

</body>
</html>
