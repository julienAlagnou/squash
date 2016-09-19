<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="orderStatuses" required="true" type="java.util.List" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<table class="table orderstatus-table">
	<tr>
		<th>Update Type</th>
		<th>Status</th>
		<th>Time</th>
		<th>Item No</th>
		<th>Qty</th>
		<th>Sku</th>
	</tr>
	<c:forEach var="orderStatus" items="${orderStatuses}">
	
	<tr>
		<td>${orderStatus.type}</td>
		<td>${orderStatus.status}</td>
		<td>${orderStatus.time}</td>
	</tr>
	
		<c:choose>
			<c:when test="${orderStatus.type == 'PROCESS'}">
				
				<c:forEach var="item" items="${orderStatus.orderStatusItems}">
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td>${item.itemNo}</td>
						<td>${item.quantity}</td>
						<td>${item.product.sku}</td>
					</tr>
				</c:forEach>
				
			</c:when>
			<c:when test="${orderStatus.type == 'SHIP'}">
				
				<c:forEach var="item" items="${orderStatus.delivery.deliveryItems}">
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td>${item.orderItem.itemNo}</td>
						<td>${item.quantityShipped}</td>
						<td>${item.orderItem.product.sku}</td>
					</tr>
				</c:forEach>
				
			</c:when>
			<c:when test="${orderStatus.type == 'RETURN_REQUEST'}">
				
				<c:forEach var="item" items="${orderStatus.returnDto.returnItems}">
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td>${item.orderItem.itemNo}</td>
						<td>${item.quantityReturned}</td>
						<td>${item.orderItem.product.sku}</td>
					</tr>
				</c:forEach>
				
			</c:when>
		</c:choose>
	
	</c:forEach>
</table>

