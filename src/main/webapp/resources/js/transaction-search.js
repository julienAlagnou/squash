var pageResourceConfig = new ResourceConfiguration("/squash/users/jal/accounts/default/transactions", "Transaction Lookup", "/squash/transaction-lookup");

var transactionPageUrlPrefix = "/squash/transaction-lookup/{}"

$(document).ready(function() {
	searchItemsAtPage(0);
});

/*
 * Clean and fill the table #search-result-table with the add a raw per order in the result.
 */
function renderSearchResult(accountTransactions) {

	// clean table
	$('#search-result-table').html('');

	// populate table with result
	$.each(accountTransactions, function(i, accountTransaction) {

		var transactionPageUrl = transactionPageUrlPrefix.replace("{}", accountTransaction.transactionId);
		var transactionIdCol = $("<td></td>").html('<a href="' + transactionPageUrl + '">' + accountTransaction.transactionId + '</a>');
		var descriptionCol = $("<td></td>").text(accountTransaction.description);
		var jivedCol = $("<td class='orderStatus'></td>").text(accountTransaction.jived);
		var amountCol = $("<td></td>").text(accountTransaction.amount);

		var actionColElmt = $("<td></td>");

		renderActions(accountTransaction.actions, actionColElmt);

		var row = $("<tr></tr>").append(transactionIdCol, descriptionCol, jivedCol, amountCol, actionColElmt);
		$('#search-result-table').append(row);
	});
}

$("#search-result-table").on('click', ".action-button", function() {

	var actionUrl = $(this).data("url");
	var actionMethod = $(this).data("method");

	var requestData = {

	};

	console.log(actionMethod + " on URL: " + actionUrl);

	var actionColElmt = $(this).parent();
	var orderStatusElmt = actionColElmt.siblings(".orderStatus");

	$.ajax({
		type : actionMethod,
		url : actionUrl,
		data : JSON.stringify(requestData),
		dataType : "json",
		contentType : "application/json; charset=utf-8",
		success : function(omsOrder) {
			// update order status
			orderStatusElmt.html(omsOrder.status);
			// update list of actions
			renderActions(omsOrder.actions, actionColElmt)
		}
	}).fail(function() {
		alert("error ");
	});
});
