package com.yungdu.us.squash.controllers;

import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.yungdu.us.squash.SquashConstants;
import com.yungdu.us.squash.controllers.util.HttpRequestUtils;
import com.yungdu.us.squash.utils.exceptions.ElementAlreadyExistsException;

/**
 * Base controller for all Rest controllers. Provides common functionality for all Rest controllers.
 */
@RestController
public class AbstractRestController extends AbstractController
{

	private static final Logger LOG = LoggerFactory.getLogger(AbstractRestController.class);

	/**
	 * Generic error handling for resource not found. Returns Http status 404.
	 *
	 * @param exception - exception cached
	 * @param request - request that generated the exception
	 */
	@ExceptionHandler(NoSuchElementException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String handleResourceNotFound(final NoSuchElementException exception, final HttpServletRequest request)
	{
		LOG.info("Resource not found at URL '{}'", request.getRequestURI(), exception);

		return HttpStatus.NOT_FOUND.getReasonPhrase();
	}

	/**
	 * Generic error handling for methods notifying that a resource is in an illegal State to perform the request. Returns Http
	 * status 409.
	 *
	 * @param exception - exception cached
	 * @param request - request that generated the exception
	 */
	@ExceptionHandler({ IllegalStateException.class, IllegalArgumentException.class, ElementAlreadyExistsException.class })
	@ResponseStatus(value = HttpStatus.CONFLICT)
	public String handleResourceAlreadyExists(final Exception exception, final HttpServletRequest request)
	{
		LOG.info("Request cannot be performed due to Illegal state of the resource at URL '{}'", request.getRequestURI(), exception);

		return exception.getMessage();
	}

	/**
	 * Generic error handling for any unhandled exception thrown in the Rest controllers. Returns Http status 500 with error
	 * summary in the body.
	 *
	 * @param exception - exception cached
	 * @param request - request that generated the exception
	 */
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public String handleAnyOtherException(final Exception exception, final HttpServletRequest request)
	{
		final StringBuilder sb = new StringBuilder();
		sb.append("Web Service exception caught for ");
		HttpRequestUtils.readRequestDetails(request, sb);
		sb.append(" with headers {").append(SquashConstants.LINE_SEPARATOR);
		HttpRequestUtils.readHttpHeaders(request, sb);
		sb.append("}");

		LOG.warn(sb.toString(), exception);
		sb.append(" message = ").append(exception.getMessage()).append(SquashConstants.LINE_SEPARATOR)
				.append(ExceptionUtils.getStackTrace(exception));
		return sb.toString();

		// TODO return simple reason phrase once DEV is done
		// return HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
	}
}
