<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="returnRequests" required="true" type="java.util.List" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<table class="table">
	<tr>
		<th>ID</th>
		<th>Rma No</th>
		<th>Type</th>
		<th>Time</th>
		<th>Include Shipping Cost</th>
		<th>Item No</th>
		<th>Qty</th>
		<th>Sku</th>
	</tr>
	<c:forEach var="returnRequest" items="${returnRequests}">
	
	<tr>
		<td>${returnRequest.id}</td>
		<td>${returnRequest.rma}</td>
		<td>${returnRequest.type}</td>
		<td>${returnRequest.returnTime}</td>
		<td>${returnRequest.includeShippingCosts}</td>
	</tr>

	<c:forEach var="item" items="${returnRequest.returnItems}">
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${item.itemNo}</td>
			<td>${item.quantityReturned}</td>
			<td>${item.orderItem.product.sku}</td>
		</tr>
	</c:forEach>
	
	</c:forEach>
</table>

