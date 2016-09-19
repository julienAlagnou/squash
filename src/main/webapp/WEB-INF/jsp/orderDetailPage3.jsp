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
		<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
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
				$scope.error_msg = '';
				$scope.process = function() {
					
					
					$http.put("http://localhost:8080/asyoms/systems/localhost/orders/0000000070/process", {})
					.success(function (response) {
						$scope.error_msg = '';
						$scope.orderStatuses = response.orderStatuses;
					})
					.error(function () {
						alert('error');
					});
				}
				$scope.ship = function() {
					
					
					$http.put("http://localhost:8080/asyoms/systems/localhost/orders/0000000070/ship", {})
					.success(function (response) {
						$scope.error_msg = '';
						$scope.orderStatuses = response.orderStatuses;
					})
					.error(function () {
						alert('error');
					});
				}
				$scope.retur = function() {
					
					
					$http.put("http://localhost:8080/asyoms/systems/localhost/orders/0000000070/return", {})
					.success(function (response) {
						$scope.error_msg = '';
						$scope.orderStatuses = response.orderStatuses;
					})
					.error(function () {
						alert('error');
					});
				}
				
				$http.get("http://localhost:8080/asyoms/systems/localhost/orders/0000000070", {})
				.success(function (omsOrder) {
					$scope.error_msg = '';
					$scope.omsOrder = omsOrder;
					$scope.orderStatuses = omsOrder.orderStatuses;
					$scope.customer = omsOrder.order.customer;
				})
				.error(function () {
					alert('error');
				});
			});
		</script>
	</jsp:attribute>

	<jsp:attribute name="pageCss">
		<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
		<link type="text/css" rel="stylesheet" href="<c:url value='/resources/css/orderDetail.css' />" media="all"/>
	</jsp:attribute>

	<jsp:body>
		<c:set var="omsOrder" value="${orderResource.content}" />
		<section class="container" ng-app="myApp" ng-controller="processCtrl" >
		
			<div class="jumbotron">
				<p>Order ID: {{omsOrder.omsOrderId.orderId}}</p> 
			</div>
			
			<c:set var="shipment" value="${omsOrder.order.shipments[0]}" />
			<div class="row" >
				<section class="col-sm-12 order-field-group">
					<h3 class="section-title" >Items</h3>
					
					<div>
						<oms:orderItem orderItems="${shipment.items}"/>
					</div>
					
				</section>
			</div>
			
			<div class="row" >
				<section class="col-sm-12 order-field-group">
					<h3 class="section-title" >Return Requests</h3>
					
					<oms:orderReturnRequest2 returnRequests="${omsOrder.returnAuthorizations}"/>
				</section>
			</div>
			
			<div class="row" >
				<section class="col-sm-12 order-field-group">
					<h3 class="section-title" >Refund Requests</h3>
					
					<table class="table">
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
			</div>
			
			<div class="row" >
				<section class="col-sm-12 order-field-group">
					<h3 class="section-title" >Order Udpate Sent</h3>
					
					<div class="order-actions-div">
					<c:forEach var="action" items="${orderResource.actions}" >
						
						<input class="action-button" type="button" value="${action.name}" data-url="${action.url}" data-method="${action.method}" />
					</c:forEach>	
				 	</div>
					 
					<oms:orderUpdate2 orderStatuses="${omsOrder.orderStatuses}"/>
					
				</section>
			</div>
			
			<div class="row" >
				<section class="col-sm-12 order-field-group">
					<h3 class="section-title" >Order Udpate Sent 2</h3>
					
					<span class="bg-danger">{{ error_msg }}</span>
					<ul>
						<li ng-repeat="orderAction in orderActions">
							<input type="button" value="{{action.name}}" data-url="{{action.url}}" data-method="{{action.method}}" />
							
						</li>
					</ul>
					
					<input type="button" value="Process" ng-click="process()"/>
					<input type="button" value="Ship" ng-click="ship()"/>
					<input type="button" value="Return" ng-click="retur()"/>
						
						<table class="table orderstatus-table">
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
			
				</section>
			</div>
		
		</section>
	</jsp:body>
</template:master>
