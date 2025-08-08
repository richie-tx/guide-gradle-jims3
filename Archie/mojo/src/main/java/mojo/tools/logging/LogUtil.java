package mojo.tools.logging;

import java.io.PrintWriter;
import java.util.Date;

import mojo.km.util.DateUtil;

public class LogUtil
{
	public static final String INFO = "INFO";
	public static final String WARNING = "WARNING";
	public static final String FATAL = "FATAL";
	public static final String DEBUG = "DEBUG";

	private static PrintWriter out;

	public static void setOut(PrintWriter anOut)
	{
		out = anOut;
	}

	public static void log(String serverity, String msg)
	{
		if (out != null)
		{
			out.print(serverity);
			out.print(" ");
			out.print(DateUtil.dateToString(new Date(), DateUtil.DATETIME_FMT_1));
			out.print(" ");
			out.println(msg);
		}
	}

	public static void log(String serverity, Exception e)
	{
		if (out != null)
		{
			out.print(serverity);
			out.print(" ");
			out.print(DateUtil.dateToString(new Date(), DateUtil.DATETIME_FMT_1));
			out.print(" ");
			out.println("Exception: " + e.getMessage());
			e.printStackTrace(out);
		}
	}

	public static void debug(String msg)
	{
		log(DEBUG, msg);
	}

	public static void info(String msg)
	{
		log(INFO, msg);
	}
}
