<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="address" required="true" type="com.yungdu.us.core.fulfillment.dto.AddressDTO" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="row">
	<div class="col-xs-4">Last Name</div>
	<div class="col-xs-8">${address.lastName}</div>
</div>
<div class="row">
	<div class="col-xs-4">First Name</div>
	<div class="col-xs-8">${address.firstName}</div>
</div>
<div class="row">
	<div class="col-xs-4">Address Line1</div>
	<div class="col-xs-8">${address.addressLine1}</div>
</div>
<div class="row">
	<div class="col-xs-4">Address Line2</div>
	<c:if test="${not empty address.addressLine2}" >
		<div class="col-xs-8">${address.addressLine2}</div>
	</c:if>
</div>
<div class="row">
	<div class="col-xs-4">City</div>
	<div class="col-xs-8">${address.city}</div>
</div>
<div class="row">
	<div class="col-xs-4">Postal Code</div>
	<div class="col-xs-8">${address.postalCode}</div>
</div>
<div class="row">
	<div class="col-xs-4">State Code</div>
	<div class="col-xs-8">${address.stateCode}</div>
</div>
<div class="row">
	<div class="col-xs-4">Phone</div>
	<c:if test="${not empty address.phone}" >
		<div class="col-xs-8">${address.phone}</div>
	</c:if>
</div>
<div class="row">
	<div class="col-xs-4">Email</div>
	<c:if test="${not empty address.email}" >
		<div class="col-xs-8">${address.email}</div>
	</c:if>
</div>
