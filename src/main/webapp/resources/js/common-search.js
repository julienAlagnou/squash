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

	var resource = getPageResourceConfiguration();

	// update page id textfield
	$("#pagination-form").find("#page").val(pageNo);

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
			var itemList = pageData.content;
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

/**
 * @param pageData					Pagination information.
 * @param pageData.number			Current page index.
 * @param pageData.size				Nb of elements per page (same for all pages).
 * @param pageData.totalElements	Total number of elements in the result set.
 * @param pageData.totalPages		Total number of pages.
 * @param numberOfElmtsInPage		Nb of elements in the current page (different for last page).
 */
function updatePaginationInfo(pageData, numberOfElmtsInPage) {

	console.log('page info. number:' + pageData.number + ' size:' + pageData.size + ' totalElements:' + pageData.totalElements + ' totalPages:' + pageData.totalPages + ' numberOfElmtsInPage:' + numberOfElmtsInPage);

	// update record number information
	var startRecordNo = (numberOfElmtsInPage != 0) ? pageData.number * pageData.size + 1 : pageData.number * pageData.size;
	var endRecordNo = pageData.number * pageData.size + numberOfElmtsInPage;
	var totalRecordNo = pageData.totalElements;

	$("#start-record-no").html(startRecordNo);
	$("#end-record-no").html(endRecordNo);

	var totalRecordNoSelector = $("#total-record-no");
	totalRecordNoSelector.html(totalRecordNo);

	$("#pagination-form").find("#page").val(pageData.number);
	totalRecordNoSelector.find("#size").val(pageData.size);

	// update page navigation links
	var pageIdListSelector = $('#page-id-list');
	pageIdListSelector.html('');

	var previousPageIndex = pageData.number > 0 ? pageData.number - 1 : 0;
	var lastPageIndex = pageData.totalPages - 1;
	var nextPageIndex = pageData.number < lastPageIndex ? pageData.number + 1 : pageData.number;

	pageIdListSelector.append('<a class="pagination-link" href="" onclick="searchItemsAtPage(0);" > << </a>&nbsp;')
		.append('<a class="pagination-link" href="" onclick="searchItemsAtPage(' + previousPageIndex + ');" > < </a>&nbsp;')
		.append('<a class="pagination-link" href="" onclick="searchItemsAtPage(' + pageData.number + ');" >' + (pageData.number + 1) + '</a>&nbsp;')
		.append('<a class="pagination-link" href="" onclick="searchItemsAtPage(' + nextPageIndex + ');" > > </a>&nbsp;')
		.append('<a class="pagination-link" href="" onclick="searchItemsAtPage(' + lastPageIndex + ');" > >> </a>&nbsp;')

}
