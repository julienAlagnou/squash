<%@ page language="java" contentType="text/html; charset=US-ASCII" 
	pageEncoding="US-ASCII"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/template"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="oms" tagdir="/WEB-INF/tags/common" %>


<template:master pageTitle="Order Detail Page">

	<jsp:attribute name="pageScripts">
		<script src= "http://ajax.googleapis.com/ajax/libs/angularjs/1.3.14/angular.min.js"></script>
		<script type="text/javascript" src="<c:url value='/resources/js/jquery-2.1.3.js' />"></script>
		<script type="text/javascript" src="<c:url value='/resources/js/order-actions.js' />"></script>
		<script type="text/javascript" src="<c:url value='/resources/js/order-detail.js' />"></script>
		
		<script>
		
			var app1 = angular.module('myApp', []);
			
			/* app.factory('action', function() {
			    var items = [];
			    var myBasketService = {};
			    
			    myBasketService.process = function(item) {
			        items.push(item);
			    };
			    myBasketService.removeItem = function(item) {
			        var index = items.indexOf(item);
			        items.splice(index, 1);
			    };
			    myBasketService.items = function() {
			        return items;
			    };
			    
			    return myBasketService;
			}); */
			
			app1.controller('processCtrl', function($scope, $http) {
				$scope.newItem = {};
				$scope.process = function() {
					
					
					$http.put("http://localhost:8080/asyoms/systems/localhost/orders/0000000065/process", {})
					.success(function (response) {
						alert('heho');
						$scope.orderStatuses = response.orderStatuses;
					})
					.error(function () {
						alert('error');
					});
				}
				
				$scope.call = function() {
					alert('hello');
				}
				
				$http.get("http://localhost:8080/asyoms/systems/localhost/orders/0000000065", {})
				.success(function (response) {
					alert('heho');
					$scope.orderActions = response.actions;
				})
				.error(function () {
					alert('error');
				});
			});
		</script>
	</jsp:attribute>

	<jsp:attribute name="pageCss">
		<link type="text/css" rel="stylesheet" href="<c:url value='/resources/css/orderDetail.css' />" media="all"/>
	</jsp:attribute>

	<jsp:body>
		<c:set var="omsOrder" value="${orderResource.content}" />
		<section ng-app="myApp">
		<section class="ids-section" >
		
			<h3 class="section-title" >Order Ids</h3>
			
			<table class="keyvaluedisplay-table">
				<tr>
					<td>Order Id</td>
					<td><strong>${omsOrder.omsOrderId.orderId}</strong></td>
				</tr>
				<tr>
					<td>System Id</td>
					<td>${omsOrder.omsOrderId.systemId}</td>
				</tr>
			</table>
			
			
		</section>
		
		<section class="general-info-section">
		
			<h3 class="section-title" >General Information</h3>
			
			<table class="keyvaluedisplay-table">
				<tr>
					<td>Status</td>
					<td class="order-status" >${omsOrder.status}</td>
				</tr>
				<tr>
					<td>Site Id</td>
					<td>${omsOrder.order.siteId}</td>
				</tr>
				<tr>
					<td>Parent Order Id</td>
					<td>${omsOrder.order.parentOrderId}</td>
				</tr>
				<tr>
					<td>CreationTime</td>
					<td>${omsOrder.order.creationTime}</td>
				</tr>
			</table>
			
		</section>
		
		<section class="customer-section">
			<h3 class="section-title" >Customer informations</h3>
			
			<c:set var="customer" value="${omsOrder.order.customer}" />
			<table class="keyvaluedisplay-table">
				<tr>
					<td class="keyvaluedisplay-key">Id</td>
					<td class="keyvaluedisplay-value" >${customer.id}</td>
				</tr>
				<tr>
					<td>Customer No</td>
					<td>${customer.customerNo}</td>
				</tr>
				<tr>
					<td>Email</td>
					<td>${customer.email}</td>
				</tr>
				<tr>
					<td>Type</td>
					<td>${customer.type}</td>
				</tr>
				<c:if test="${not empty customer.firstName}">
				<tr>
					<td>FirstName</td>
					<td>${customer.firstName}</td>
				</tr>
				</c:if>
				<c:if test="${not empty customer.lastName}">
				<tr>
					<td>LastName</td>
					<td>${customer.lastName}</td>
				</tr>
				</c:if>
			</table>
			
		</section>
		
		<section class="totals-section">
			<h3 class="section-title" >Order Totals</h3>
			
			<c:set var="currencyIsocode" value="${omsOrder.order.currency}" />
			<table class="keyvaluedisplay-table">
				<tr>
					<td>Total Amount</td>
					<td>${currencyIsocode} ${omsOrder.order.totalAmount}</td>
				</tr>
				<tr>
					<td>Total Tax Amount</td>
					<td>${currencyIsocode} ${omsOrder.order.totalTaxAmount}</td>
				</tr>
				<tr>
					<td>Grand Total Amount</td>
					<td>${currencyIsocode} ${omsOrder.order.grandTotalAmount}</td>
				</tr>
			</table>
			
		</section>
		
		<section class="shipment-section">
			<h3 class="section-title" >Shipment</h3>
			
			<c:set var="shipment" value="${omsOrder.order.shipments[0]}" />
			<table class="keyvaluedisplay-table">
				<tr>
					<td>Shipping Mode</td>
					<td>${shipment.shippingMode}</td>
				</tr>
				<tr>
					<td>Signature Required</td>
					<td>${shipment.signatureRequired}</td>
				</tr>
				
			</table>
			
			<oms:address address="${shipment.shipToAddress}"/>
			<oms:orderItem orderItems="${shipment.items}"/>
			
		</section>
		
		<section class="order-return-section">
			<h3 class="section-title" >Return Requests</h3>
			
			<oms:orderReturnRequest returnRequests="${omsOrder.returnAuthorizations}"/>
		</section>
		
		<section class="order-refund-section">
			<h3 class="section-title" >Refund Requests</h3>
			
			<table class="orderrefundrequest-table">
				<tr>
					<th>ID</th>
					<th>Time</th>
					<th>Refund Amount</th>
					<th>Refund Tax Amount</th>
					<th>Reason</th>
					<th>Reason Code</th>
					<th>Comments</th>
				</tr>
				<c:forEach var="refundRequest" items="${omsOrder.refundRequests}">
					<tr>
						<td>${refundRequest.id}</td>
						<td>${refundRequest.time}</td>
						<td>${refundRequest.refundAmount}</td>
						<td>${refundRequest.refundTaxAmount}</td>
						<td>${refundRequest.reason}</td>
						<td>${refundRequest.reasonCode}</td>
						<td>${refundRequest.comments}</td>
					</tr>
				</c:forEach>
			</table>

		</section>
		
		<section class="order-update-section">
			<h3 class="section-title" >Order Udpate Sent</h3>
			
			<div class="order-actions-div">
			<c:forEach var="action" items="${orderResource.actions}" >
				
				<input class="action-button" type="button" value="${action.name}" data-url="${action.url}" data-method="${action.method}" />
			</c:forEach>	
		 	</div>
			 
			<oms:orderUpdate orderStatuses="${omsOrder.orderStatuses}"/>
			
		</section>
		
		<section class="order-update-section">
			<h3 class="section-title" >Order Udpate Sent 2</h3>
			
			<div ng-controller="processCtrl" >
				<ul>
					<li ng-repeat="orderAction in orderActions">
						<input type="button" value="{{action.name}}" data-url="{{action.url}}" data-method="{{action.method}}" />
						
					</li>
				</ul>
				
				
			<input type="button" value="CreateItem" ng-click="call()"/>
			
				<table  class="orderstatus-table">
					<tr>
						<th>Update Type</th>
						<th>Status</th>
						<th>Time</th>
						<th>Item No</th>
						<th>Qty</th>
						<th>Sku</th>
						<th><input type="button" value="Process" ng-click="process()"/></th>
					</tr>
					<tr ng-repeat="orderStatus in orderStatuses">
						<td>{{ orderStatus.type }}</td>
						<td>{{ orderStatus.status }}</td>
						<td>{{ orderStatus.time }}</td>
					</tr>
				</table>	
		 	</div>
	
	
		</section>
		
		
		<section class="payment-section">
			<h3 class="section-title" >Payment</h3>
			
			<c:set var="payment" value="${omsOrder.order.payments[0]}" />
			<table class="keyvaluedisplay-table">
				<tr>
					<td>Type</td>
					<td>${payment.type}</td>
				</tr>
				
			</table>
			
			<oms:address address="${payment.billToAddress}"/>
		</section>
	</section>
	</jsp:body>
</template:master>
