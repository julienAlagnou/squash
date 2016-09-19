<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="address" required="true" type="com.yungdu.us.core.fulfillment.dto.AddressDTO" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<table class="keyvaluedisplay-table">
				<tr>
					<td>Last Name</td>
					<td>${address.lastName}</td>
				</tr>
				<tr>
					<td>First Name</td>
					<td>${address.firstName}</td>
				</tr>
				<tr>
					<td>Address Line1</td>
					<td>${address.addressLine1}</td>
				</tr>
				<c:if test="${not empty address.addressLine2}" >
				<tr>
					<td>Address Line2</td>
					<td>${address.addressLine2}</td>
				</tr>
				</c:if>
				<tr>
					<td>City</td>
					<td>${address.city}</td>
				</tr>
				<tr>
					<td>Postal Code</td>
					<td>${address.postalCode}</td>
				</tr>
				<tr>
					<td>State Code</td>
					<td>${address.stateCode}</td>
				</tr>
				<c:if test="${not empty address.phone}" >
				<tr>
					<td>Phone</td>
					<td>${address.phone}</td>
				</tr>
				</c:if>
				<c:if test="${not empty address.email}" >
				<tr>
					<td>Email</td>
					<td>${address.email}</td>
				</tr>
				</c:if>
				
			</table>