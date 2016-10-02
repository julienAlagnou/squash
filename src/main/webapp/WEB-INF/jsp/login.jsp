<%@ page language="java" contentType="text/html; charset=US-ASCII" 
	pageEncoding="US-ASCII"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/template"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<template:master pageTitle="Login">

	<jsp:attribute name="pageScripts">

	</jsp:attribute>

	<jsp:attribute name="pageCss">
	
	</jsp:attribute>

	<jsp:body>
		<form action="/squash/login" method="post">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

			<div><label> User Name : <input type="text" name="username"/> </label></div>
			<div><label> Password: <input type="password" name="password"/> </label></div>
			<div><input type="submit" value="Sign In"/></div>
		</form>
	</jsp:body>
</template:master>
