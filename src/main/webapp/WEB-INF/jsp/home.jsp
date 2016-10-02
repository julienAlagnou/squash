<%@ page language="java" contentType="text/html; charset=US-ASCII" 
	pageEncoding="US-ASCII"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/template"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<template:master pageTitle="Home">

	<jsp:attribute name="pageScripts">

	</jsp:attribute>

	<jsp:attribute name="pageCss">
	
	</jsp:attribute>

	<jsp:body>

		<h1>Welcome!</h1>

		<p>Click <a href="/squash/transaction-lookup">here</a> to see your transaction.</p>
	</jsp:body>
</template:master>
