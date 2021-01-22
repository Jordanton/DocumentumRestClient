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
  <%-- <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css"> --%>
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto Condensed">
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lato">

</head>

<div style="background:#212121; color:#FFF; margin:0; width:100%">
    <div class="row">
        <div class="col-sm-4">
            <h4 style="font-weight:bold; padding-top:10px; padding-bottom:10px">Connect With Us</h4>
            <p>
            	<a href="http://business.lacity.org/" target="_blank">
            		<img alt="LA Business Portal" title="LA Business Portal" src="${pageContext.request.contextPath}/images/LABusinessPortal_Banner.jpg" style="max-width:100%; height:auto">
            	</a>
            </p>
        </div>
    
        <div class="col-sm-4">
            <h4 style="font-weight:bold; padding-top:10px; padding-bottom:10px">Disclaimer</h4>
            <p>Non-financial information such as name, business address (including home-based businesses), mailing address, etc., contained in your City of Los Angeles tax and permit records, is subject to public disclosure under provisions of the California Public Records Act, Government Code Section 6250 et seq.&nbsp; Your residential information may also be subject to public disclosure if that location is utilized for business and/or mailing purposes.   </p>
        </div>
        
        <div class="col-sm-4">
            <h4 style="font-weight:bold; padding-top:10px; padding-bottom:10px">Contact Us</h4>  
            <table class="table table-borderless" style="color:#FFF; ">
            <tr><td valign="top"><i class="fa fa-map-marker"></i></td><td valign="top">200 N. Spring Street, Los Angeles, CA 90012</td></tr>
            <tr><td valign="top"><i class="fa fa-phone"></i></td><td valign="top">Call 311</td></tr>
            <tr><td valign="top"><i class="fa fa-envelope"></i></td><td valign="top"><a href="http://finance.lacity.org/submit-feedback" target="_blank" style="color:#FFF; text-decoration:underline;">Submit Feedback</a></td></tr>
            </table>
        </div>
    </div>

    <div class="row" style="padding:30px">
        <div class="col-sm-12">
            <p align="center">&#169; Copyright 2018 City of Los Angeles. All rights reserved.&nbsp;&nbsp;
            <a href="http://disclaimer.lacity.org/disclaimer.htm" target="_blank" style="color:#FFF; text-decoration:underline;">Disclaimer</a> &nbsp;| &nbsp;
            <a href="http://disclaimer.lacity.org/privacy.htm" target="_blank" style="color:#FFF; text-decoration:underline;">Privacy Policy</a>
            </p>
        </div>
    </div>
</div>

</html>