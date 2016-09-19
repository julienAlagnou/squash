<%@ page language="java" contentType="text/html; charset=US-ASCII" 
	pageEncoding="US-ASCII"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/template"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="pagination" tagdir="/WEB-INF/tags/common" %>


<template:master pageTitle="Transaction Lookup">

	<jsp:attribute name="pageScripts">
		<script type="text/javascript" src="<c:url value='/resources/js/jquery-2.1.3.js' />"></script>
		<script type="text/javascript" src="<c:url value='/resources/js/common-search.js' />"></script>
		<script type="text/javascript" src="<c:url value='/resources/js/transaction-search.js' />"></script>
		<script type="text/javascript" src="<c:url value='/resources/js/order-actions.js' />"></script>
	</jsp:attribute>

	<jsp:attribute name="pageCss">
	
	</jsp:attribute>

	<jsp:body>
		<section class="search-section">
			<form:form id="search-form" modelAttribute="transactionLookupData">
				
				<fieldset>
									
					<label for="transactionId">Transaction ID</label>
					<form:input type="text" id="transactionId" path="transactionId" />
					<br>
					
					<label for="description">Description</label>
					<form:input type="text" id="description" path="description" />
					<br>
					
					<label for="jiveStatus">Jive Status</label>
					<form:select path="jiveStatus" items="${jiveStatuses}">  
					</form:select>
					<br>
					
					<input type="button" value="Search" onclick="searchItemsAtPage(0)" />
				</fieldset>
			</form:form>
		</section>
		
		<section class="search-result-section" >
			<pagination:pagination page="${ordersPage}"/>
			
			<table id="search-result-table" border="1" style="width:100%">
				
			</table>
		</section>
	</jsp:body>
</template:master>
