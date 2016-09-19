<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="orderItems" required="true" type="java.util.List" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<table class="table-condensed">
	<tr>
		<th>Item No</th>
		<th>Quantity</th>
		<th>Sku</th>
		<th>Product Name</th>
		<th>Original Unit Amount</th>
		<th>Unit Discount Amount</th>
		<th>Unit Amount</th>
		<th>original Total Amount</th>
		<th>Total Discount Amount</th>
		<th>Total Amount</th>
		<th>Total Tax Amount</th>
	</tr>
	<c:forEach var="orderItem" items="${orderItems}">
	<tr>
		<td>${orderItem.itemNo}</td>
		<td>${orderItem.quantity}</td>
		<td>${orderItem.product.sku}</td>
		<td>${orderItem.product.name}</td>
		<td>${orderItem.originalUnitAmount}</td>
		<td>${orderItem.unitDiscountAmount}</td>
		<td>${orderItem.unitAmount}</td>
		<td>${orderItem.originalTotalAmount}</td>
		<td>${orderItem.totalDiscountAmount}</td>
		<td>${orderItem.totalAmount}</td>
		<td>${orderItem.totalTaxAmount}</td>
	</tr>
	</c:forEach>
</table>

