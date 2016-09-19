/**
 *
 */
package com.yungdu.us.squash.controllers.util;

import java.util.Enumeration;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.yungdu.us.squash.SquashConstants;

/**
 */
public final class HttpRequestUtils
{

	public static void readRequestDetails(final HttpServletRequest request, final StringBuilder sb)
	{
		String queryString = request.getQueryString();
		if (queryString == null)
		{
			queryString = "";
		}

		final String requestUri = request.getRequestURI();

		final String securePrefix = request.isSecure() ? "s" : " ";
		final String methodPrefix = request.getMethod().substring(0, 1);

		sb.append(securePrefix).append(methodPrefix).append(" [").append(requestUri).append("] [").append(queryString).append("] ");
	}

	public static void readHttpHeaders(final HttpServletRequest request, final StringBuilder sb)
	{
		final Enumeration<?> headers = request.getHeaderNames();
		while (headers.hasMoreElements())
		{
			final String headerName = (String) headers.nextElement();
			sb.append("HEADER ").append(headerName).append("=").append(request.getHeader(headerName))
					.append(SquashConstants.LINE_SEPARATOR);
		}
	}

	public static void readCookies(final HttpServletRequest request, final StringBuilder sb)
	{
		final Cookie[] cookies = request.getCookies();
		if (cookies != null)
		{
			for (final Cookie cookie : cookies)
			{
				sb.append("COOKIE Name: [").append(cookie.getName()).append("] Path: [").append(cookie.getPath())
						.append("] Value: [").append(cookie.getValue()).append("]").append(SquashConstants.LINE_SEPARATOR);
			}
		}
	}
}
