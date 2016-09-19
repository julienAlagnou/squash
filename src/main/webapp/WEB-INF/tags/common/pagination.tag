<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="page" required="true" type="org.springframework.data.domain.Page" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<form id="pagination-form" >
	<input type="hidden" id="page" value="${page.number}" />
	<input type="hidden" id="size" value="${page.size}" />
</form>

<c:set var="startRecordNo" value="${page.numberOfElements eq 0 ? page.number * page.size : page.number * page.size + 1}" />
<c:set var="endRecordNo" value="${page.number * page.size + page.numberOfElements}" />

<p>
	<span id="start-record-no" >${startRecordNo}</span>
	<span> - </span>
	<span id="end-record-no">${endRecordNo}</span>
	<span> of </span>
	<span id="total-record-no">${page.totalElements}</span>
	
	<span id="page-id-list" style="float: right;" >
		<c:forEach var="pageId" begin="1" end="${page.totalPages}">
			<a class="pagination-link" href="" onclick="searchItemsAtPage(${pageId - 1});">${pageId}</a>
		</c:forEach>
	</span>
</p>