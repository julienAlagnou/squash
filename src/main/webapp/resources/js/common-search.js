/*
 * Class ResourceConfiguration 
 * It represents the configuration to access and display the searched resource.
 */
var ResourceConfiguration = function(resourceUrl, targetPageTitle, targetPageUrlPrefix) {
	this.resourceUrl = resourceUrl;
	this.targetPageTitle = targetPageTitle;
	this.targetPageUrlPrefix = targetPageUrlPrefix;
	this.requestParams = {};
};

/*
 * Returns the query string of the page displaying the found resource.
 */
ResourceConfiguration.prototype.getTargetPageQueryString = function() {
	var urlQueryParam = "?";
	$.each(this.requestParams, function(key, value) {
		urlQueryParam += key + '=' + encodeURIComponent(value) + '&';
	});
	urlQueryParam = urlQueryParam.substr(0, urlQueryParam.length - 1);

	console.log("URL queryString: " + urlQueryParam);
	return urlQueryParam;
};

/*
 * Returns the full URL of the page displaying the found resource.
 */
ResourceConfiguration.prototype.getTargetPageUrl = function() {
	var targetPageUrl = this.targetPageUrlPrefix + this.getTargetPageQueryString();
	console.log("Target page URL: " + targetPageUrl);
	return targetPageUrl;
};

/*
 * Manages back/forward browser button. Reload the content.
 */
window.onpopstate = function(e) {

	if (e.state == null) {
		return;
	}

	// load Data
	loadAndRenderData(e.state);

};

function getPageResourceConfiguration() {
	if (!pageResourceConfig) {
		console.error("The page doesn't define js variable pageResourceConfig");
	}

	return pageResourceConfig;
}

function searchItemsAtPage(pageNo) {

	resource = getPageResourceConfiguration();

	// update page id textfield
	$("#pagination-form #page").val(pageNo);

	// submit form
	searchItems(resource);

	event.preventDefault();
	return false;
}

function searchItems(resource) {

	// build search URL and request Object
	resource.requestParams = {};
	$('#search-form *').filter(':input:not(:button,:submit)').each(function() {
		resource.requestParams[$(this).attr('id')] = $(this).val();
	});

	// add pagination params
	$('#pagination-form *').filter(':input:not(:button,:submit)').each(function() {
		resource.requestParams[$(this).attr('id')] = $(this).val();
	});

	// call rest service
	loadAndRenderData(resource);

	// push state for browser address bar & history
	window.history.pushState(resource, resource.targetPageTitle, resource.getTargetPageUrl());
}

function loadAndRenderData(resource) {
	$.getJSON(resource.resourceUrl, resource.requestParams).done(function(pageData) {

		// update table and pagination information
		if (pageData.content) {
			// itemList = pageData._embedded[Object.keys(pageData._embedded)[0]];
			itemList = pageData.content;
			renderSearchResult(itemList);
			updatePaginationInfo(pageData.page, itemList.length);
		} else {
			console.log("undefined elmt pageData._embedded in response");
			renderSearchResult([]);
			updatePaginationInfo(pageData.page, 0);
		}

		// update search form fields
		fillSearchForm(resource.requestParams);

	}).fail(function() {
		console.error("error loading and rendering resource");
	});
}

function fillSearchForm(data) {
	$.each(data, function(id, value) {
		$('#' + id).val(value);
	});
}

function updatePaginationInfo(data, numberOfElmtsInPage) {
	// update pagination information
	var startRecordNo = (numberOfElmtsInPage != 0) ? data.number * data.size + 1 : data.number * data.size;
	var endRecordNo = data.number * data.size + numberOfElmtsInPage;
	var totalRecordNo = data.totalElements;

	$("#start-record-no").html(startRecordNo);
	$("#end-record-no").html(endRecordNo);
	$("#total-record-no").html(totalRecordNo);

	$("#pagination-form #page").val(data.number);
	$("#total-record-no #size").val(data.size);
	console.log('page info: ' + data.number + ' ' + data.size);

	$('#page-id-list').html('');
	for (i = 0; i < data.totalPages; i++) {
		$('#page-id-list').append(
				'<a class="pagination-link" href="" onclick="searchItemsAtPage(' + i + ');" >' + (i + 1) + '</a>&nbsp;');
	}
}
