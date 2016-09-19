package com.yungdu.us.squash.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * Default page controller that handles all GET requests that are not handled by any other controllers.
 */
// TODO disable temporarily cause GET on resources end up here
// @Controller
public class DefaultPageController extends AbstractPageController
{
	private static final String NOT_FOUND_PAGE = "notFoundPage";


	/**
	 * Handles all page request that are not handled by any other controller and shows not found page.
	 *
	 * @param model the spring Model
	 * @param request the http request
	 * @param response the http response
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String get(final Model model, final HttpServletRequest request, final HttpServletResponse response)
	{
		return NOT_FOUND_PAGE;
	}


}
