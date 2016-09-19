package com.yungdu.us.squash.controllers;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.support.RequestContextUtils;


/**
 * Base controller for all page controllers. Provides common functionality for all page controllers.
 */
public abstract class AbstractPageController extends AbstractController
{
	private final static Logger LOG = LoggerFactory.getLogger(AbstractPageController.class);

	public static final String REDIRECT_PREFIX = "redirect:";
	public static final String FORWARD_PREFIX = "forward:";

	private static final String ERROR_PAGE = "errorPage";

	/**
	 * Generic error handling for page controller actions. Shows error page.
	 *
	 * @param exception - exception cached
	 * @param request - request that generated the exception
	 */
	@ExceptionHandler(Exception.class)
	public String handleException(final Exception exception, final HttpServletRequest request)
	{
		LOG.error("Frontend exception handler for {}", exception.getMessage(), exception);
		final FlashMap outputFlashMap = RequestContextUtils.getOutputFlashMap(request);

		// message and stack trace
		outputFlashMap.put("message", exception.getMessage());
		outputFlashMap.put("stacktrace", org.apache.commons.lang3.exception.ExceptionUtils.getStackTrace(exception));

		return ERROR_PAGE;
	}
}
