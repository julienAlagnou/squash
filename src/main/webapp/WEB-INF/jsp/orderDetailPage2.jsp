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
				.success(function (omsOrder) {
					alert('heho');
					$scope.omsOrder = omsOrder;
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
		
		<div class="row" >
			<section class="col-sm-5 order-field-group" >
			
				<h3 class="section-title" >Order Ids</h3>
				
				<div class="row">
					<div class="col-xs-4">Order Id</div>
					<div class="col-xs-8"><strong>{{omsOrder.omsOrderId.orderId}}</strong></div>
				</div>
				
				<div class="row">
					<div class="col-xs-4">System Id</div>
					<div class="col-xs-8">{{omsOrder.omsOrderId.systemId}}</div>
				</div>
				
				<div class="row">
					<div class="col-xs-4">Status</div>
					<div class="col-xs-8 order-status"><span class="bg-primary" >{{omsOrder.status}}</span></div>
				</div>
				
			</section>
			
			<div class="col-sm-1"></div>
			
			<section class="col-sm-5 order-field-group">
			
				<h3 class="section-title" >General Information</h3>
				
				
				<div class="row">
					<div class="col-xs-4">Site Id</div>
					<div class="col-xs-8">{{omsOrder.order.siteId}}</div>
				</div>
				
				<div class="row">
					<div class="col-xs-4">Parent Order Id</div>
					<div class="col-xs-8">{{omsOrder.order.parentOrderId}}</div>
				</div>
				
				<div class="row">
					<div class="col-xs-4">Creation Time</div>
					<div class="col-xs-8">{{omsOrder.order.creationTime}}</div>
				</div>
				
			</section>
			
			
		</div>
		
		
		<div class="row">
			
			<section class="col-sm-5 order-field-group">
				<h3 class="section-title" >Customer</h3>
				
				<div class="row">
					<div class="col-xs-4">Id</div>
					<div class="col-xs-8">{{customer.id}}</div>
				</div>
				
				<div class="row">
					<div class="col-xs-4">Customer No</div>
					<div class="col-xs-8">{{customer.customerNo}}</div>
				</div>
				
				<div class="row">
					<div class="col-xs-4">Email</div>
					<div class="col-xs-8">{{customer.email}}</div>
				</div>
				
				<div class="row">
					<div class="col-xs-4">Type</div>
					<div class="col-xs-8">{{customer.type}}</div>
				</div>
				
				<div class="row">
					<div class="col-xs-4">FirstName</div>
					<div class="col-xs-8">{{customer.firstName}}</div>
				</div>
				
				<div class="row">
					<div class="col-xs-4">LastName</div>
					<div class="col-xs-8">{{customer.lastName}}</div>
				</div>
				
			</section>
			
			<div class="col-sm-1"></div>
			
			<section class="col-sm-5 order-field-group">
				<h3 class="section-title" >Order Totals</h3>
				
				<c:set var="currencyIsocode" value="${omsOrder.order.currency}" />
				
				<div class="row">
					<div class="col-xs-4">Total Amount</div>
					<div class="col-xs-8">${currencyIsocode} ${omsOrder.order.totalAmount}</div>
				</div>
				
				<div class="row">
					<div class="col-xs-4">Total Tax Amount</div>
					<div class="col-xs-8">${currencyIsocode} ${omsOrder.order.totalTaxAmount}</div>
				</div>
				
				<div class="row">
					<div class="col-xs-4">Grand Total Amount</div>
					<div class="col-xs-8">${currencyIsocode} ${omsOrder.order.grandTotalAmount}</div>
				</div>
				
			</section>
			
			
		</div>
		
		<c:set var="shipment" value="${omsOrder.order.shipments[0]}" />
		<div class="row">
			<section class="col-sm-5 order-field-group">
				<h3 class="section-title" >Ship To</h3>
				
				
				<oms:address2 address="${shipment.shipToAddress}"/>
				
			</section>
			
			<div class="col-sm-1"></div>
			
			<section class="col-sm-5 order-field-group">
				<h3 class="section-title" >Bill To</h3>
				
				<c:set var="payment" value="${omsOrder.order.payments[0]}" />
				<oms:address2 address="${payment.billToAddress}"/>
				
			</section>
		</div>
		
		<div class="row">
			<section class="col-sm-5 order-field-group">
				<h3 class="section-title" >Shipment/Payment</h3>
				
				<div class="row">
					<div class="col-xs-4">Shipping Mode</div>
					<div class="col-xs-8">${shipment.shippingMode}</div>
				</div>
				<div class="row">
					<div class="col-xs-4">Payment Type</div>
					<div class="col-xs-8">${payment.type}</div>
				</div>
				<div class="row">
					<div class="col-xs-4">Signature Required</div>
					<div class="col-xs-8">${shipment.signatureRequired}</div>
				</div>
				
			</section>
		</div>
		
		
		<div class="row" >
			<section class="col-sm-12 shipment-section order-field-group">
				<h3 class="section-title" >Items</h3>
				
				<div>
					<oms:orderItem orderItems="${shipment.items}"/>
				</div>
				
			</section>
		</div>
		
		
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
	
		</section>
		
		
	</section>
	</jsp:body>
</template:master>
