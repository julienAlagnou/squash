package com.yungdu.us.commons.lang;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.base.Throwables;
import com.google.common.io.Files;

/**
 *
 */
public class IOUtils {

	private static final Logger LOG = Logger.getLogger(IOUtils.class);

	public static String readFile(final String fileName)
	{
		final File file = new File(IOUtils.class.getResource(fileName).getFile());
		return readFile(file);
	}

	public static String readFile(final File file)
	{
		try
		{
			final List<String> lines = Files.readLines(file, Charsets.UTF_8);
			return Joiner.on(System.getProperty("line.separator")).join(lines);
		}
		catch (final IOException e)
		{
			throw new IllegalArgumentException(e);
		}
	}

	/**
	 * Creates a new temporary file. IO exceptions are wrapped in runtime exceptions because they are not recoverable.
	 *
	 * @param prefix The prefix string to be used in generating the file's name; must be at least three characters long
	 * @param suffix The suffix string to be used in generating the file's name; may be <code>null</code>, in which case the suffix
	 *           <code>".tmp"</code> will be used
	 * @return temporary file
	 * @see File#createTempFile(String, String)
	 */
	public static File createTemporaryFile(final String prefix, final String suffix)
	{
		try
		{
			// create temporary file
			return File.createTempFile(prefix, suffix);
		}
		catch (final IOException e)
		{
			Throwables.propagate(e);
			return null; // never happens
		}
	}

	public static void closeQuietlyButLog(final Closeable closeable)
	{
		if (closeable != null)
		{
			try
			{
				closeable.close();
			}
			catch (final IOException e)
			{
				LOG.warn(e.getMessage(), e);
			}
		}
	}
}
