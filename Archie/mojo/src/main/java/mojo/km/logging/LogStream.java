//Source file: Z:/java.vob/CommonServiceLayer/logging/LogStream.java

package mojo.km.logging;

import java.io.OutputStream;
import java.io.PrintStream;

import mojo.km.config.AppProperties;
import mojo.km.config.LogProperties;
import mojo.km.logging.mojo.*;

/**
 * Responsible for handling the logging of messages to various log locations.
 * 
 * For each server in the Mojo.xml the configuration of the server process can
 * be configured to run different logging adapters.  There is a  server entry
 * that looks as follows:
 *
 *	<Server contextClassName="mojo.km.context.InternalQueueContext.InternalQueueContextManager" name="SwingController">
 *     <EventManager>
 *     </EventManager>
 *     <Session>
 *     </Session>
 *      <LogManager>
 *         <LoggerAdapter value="mojo.km.logging.log4j.MojoLogStream"/>
 *         <APPEXCEPTION value="ERROR"/>
 *         <ERROR value="ERROR"/>
 *         <ERRSTREAM value="ERROR"/>
 *         <OUTSTREAM value="INFO"/>
 *         <RUNTIMEEXCEPTION value="DEBUG"/>
 *         <STACKTRACE value="DEBUG"/>
 *         <EXCEPTION value="FATAL"/>
 *       </LogManager>
 *   </Server>
 * The property for <b>LoggerAdapter</b> defines which adapter maybe used for
 * that server.  The above current entry utilizes jakarta's log4j framework for
 * making log entries.
 *@author Eric A Amundson
 *@version 1.0
 * @modelguid {CBB9B3B8-1405-49E7-BAD6-661676E6BFCB}
 */
public class LogStream extends OutputStream implements ILogStream {
	/** @modelguid {6764C16A-7733-4094-9FBA-C0C1439E0C26} */
	private static PrintStream SYSTEM_ERR = System.err;

	/** Default constructor 
	 * @modelguid {B75DC60E-CAE1-4410-8F91-CEBC6A44B07E}
	 */
	public LogStream() {
		this((short)1);
	}

	/**
	 *    Constructor
	 *    @param logType - set log type for stream.
	 * @modelguid {1957D277-9975-4614-9AF2-022E7D1C56F5}
	 */
	public LogStream(short logType) {
		setLogType(logType);
		init();
	}

	/**
	 * Writes the specified byte to this output stream. The general
	 * contract for <code>write</code> is that one byte is written to the output stream. The byte to be written is the eight
	 * low-order bits of the argument <code>b</code>. The 24 high-order bits of <code>b</code> are ignored. <p>
	 * Subclasses of <code>OutputStream</code> must provide an implementation for this method.
	 * @param      b   the <code>byte</code>.
	 * @exception  IOException  if an I/O error occurs. In particular, an <code>IOException</code> may be thrown if the
	 * output stream has been closed.
	 * @modelguid {3D79DEC2-4C66-4ED9-88E7-7C1E1496CA23}
	 */
	public void write(int b) {
		try {
			if ((LogStream.getlogging() & mLogType) == 0) {
				return;
			}
			if (adapter != null) {
				adapter.write(b);
			} else {
				SYSTEM_ERR.write(b);
			}
		} catch (Throwable t) {
			SYSTEM_ERR.println("Error occurred while writing to a moJo log stream.  Attempted to write: " + b);
			t.printStackTrace(SYSTEM_ERR);
			loadFallbackAdapter();
		}
	}

	/**
	 * Writes <code>b.length</code> bytes from the specified byte array
	 * to this output stream. The general contract for <code>write(b)</code>
	 * is that it should have exactly the same effect as the call <code>write(b, 0, b.length)</code>.
	 * @param      b   the data.
	 * @see OutputStream#write(byte[], int, int)
	 * @exception  IOException  if an I/O error occurs.
	 * @modelguid {8FBAFFF2-0F47-43C0-8A9F-4739F4D219C2}
	 */
	public void write(byte[] b) {
		try {
			super.write(b);
		} catch (Throwable t) {
			SYSTEM_ERR.println("Error occurred while writing to a moJo log stream.  Attempted to write: " + b);
			t.printStackTrace(SYSTEM_ERR);
			loadFallbackAdapter();
		}
	}

	/**
	 * Writes <code>len</code> bytes from the specified byte array starting at offset <code>off</code> to this output stream.
	 * The general contract for <code>write(b, off, len)</code> is that
	 * some of the bytes in the array <code>b</code> are written to the
	 * output stream in order; element <code>b[off]</code> is the first
	 * byte written and <code>b[off+len-1]</code> is the last byte written by this operation. <p>
	 * The <code>write</code> method of <code>OutputStream</code> calls
	 * the write method of one argument on each of the bytes to be
	 * written out. Subclasses are encouraged to override this method and provide a more efficient implementation. <p>
	 * If <code>b</code> is <code>null</code>, a <code>NullPointerException</code> is thrown. <p>
	 * If <code>off</code> is negative, or <code>len</code> is negative, or
	 * <code>off+len</code> is greater than the length of the array
	 * <code>b</code>, then an <tt>IndexOutOfBoundsException</tt> is thrown.
	 * @param      b     the data.
	 * @param      off   the start offset in the data.
	 * @param      len   the number of bytes to write.
	 * @exception  IOException  if an I/O error occurs. In particular,
	 * an <code>IOException</code> is thrown if the output stream is closed.
	 * @modelguid {7A0B0790-24CE-4BDC-863F-E46163441B02}
	 */
	public void write(byte[] b, int off, int len) {
		try {
			if ((LogStream.getlogging() & mLogType) == 0 || len <= 2) {
				return;
			}
			if (adapter != null) {
				adapter.write(b, off, len);
			} else {
				SYSTEM_ERR.write(b, off, len);
			}
		} catch (Throwable t) {
			SYSTEM_ERR.println("Error occurred while writing to a moJo log stream.  Attempted to write: " + b + ", OFFSET=" + off + ", LENGTH=" + len);
			t.printStackTrace(SYSTEM_ERR);
			loadFallbackAdapter();
		}
	}

	/**
	 * Flushes this output stream and forces any buffered output bytes
	 * to be written out. The general contract of <code>flush</code> is
	 * that calling it is an indication that, if any bytes previously
	 * written have been buffered by the implementation of the output
	 * stream, such bytes should immediately be written to their intended destination. <p>
	 * The <code>flush</code> method of <code>OutputStream</code> does nothing.
	 * @exception  IOException  if an I/O error occurs.
	 * @modelguid {2CCB9940-B4A3-4A67-AD19-47F5B490FEF4}
	 */
	public void flush() {
		if (adapter != null) {
			adapter.flush();
		}
	}

	/**
	 * Closes this output stream and releases any system resources
	 * associated with this stream. The general contract of <code>close</code>
	 * is that it closes the output stream. A closed stream cannot perform output operations and cannot be reopened. <p>
	 * The <code>close</code> method of <code>OutputStream</code> does nothing.
	 * @exception  IOException  if an I/O error occurs.
	 * @modelguid {10F82E44-B566-4A20-A312-0BAB65B0EAE9}
	 */
	public void close() {
		if (adapter != null) {
			adapter.close();
		}
	}

	/**
	 * Stop logging function.
	 *    @param logLevel - filter values or'd together, E.G. ERRSTREAM | OUTSTREAM
	 * @modelguid {77A5A04F-E6CD-4BBC-8504-B14DA481C60F}
	 */
	public static synchronized void setlogging(short logLevel) {
		theLogLevel = logLevel;
	}

	/**
	 * Stop logging function.
	 *    @return or'd log filters.
	 * @modelguid {85E3A408-2220-4599-8915-F02850B7F4A6}
	 */
	public static synchronized short getlogging() {
		return theLogLevel;
	}

	/**
	 *    Prints the character buffer to log file.
	 *    @param buffer - log events msg.
	 *    @modelguid {11E6897C-57D0-4809-8E14-CF6BDE984AA8}
	 */
	public void println(char[] buffer) {
		try {
			if ((LogStream.getlogging() & mLogType) == 0 || buffer.length <= 2) {
				return;
			}
			if (adapter != null) {
				adapter.println(buffer);
			} else {
				SYSTEM_ERR.println(buffer);
			}
		} catch (Throwable t) {
			SYSTEM_ERR.println("Error occurred while writing to a moJo log stream.  Attempted to write: " + new String(buffer));
			t.printStackTrace(SYSTEM_ERR);
			loadFallbackAdapter();
		}
	}

	/** @modelguid {98B83FD4-FB1A-4CDE-9384-6F089CBFE5F8} */
	private void init() {
		if (adapter == null) {
			String lLogFilterCode = AppProperties.getInstance().getProperty("LogFilterCode");
			if (lLogFilterCode != null) {
				try {
					theLogLevel = Short.parseShort(lLogFilterCode);
				} catch (Throwable t) {
				}
			}
			String lLogAdapter = LogProperties.getInstance().getProperty("LoggerAdapter");
			if (lLogAdapter == null) {
				lLogAdapter = "mojo.km.logging.log4j.MojoLogStream";
			}
			loadAdapter(lLogAdapter);
		}
	}

	/** @modelguid {0FBAAFAB-1E56-4FD1-84A2-BCFE8EC91C03} */
	private void loadFallbackAdapter() {
		if (adapter instanceof MojoStream) {
			SYSTEM_ERR.println("Falling back to default System.err");
			adapter = null;
		} else {
			SYSTEM_ERR.println("Falling back to moJo log adapter");
			loadAdapter(MOJO_LOG_ADAPTER);
		}
	}

	/** @modelguid {F1A94B17-A918-4339-AC59-26E135334C70} */
	private void loadAdapter(String anAdapterName) {
		try {
			Class adapterClass = Class.forName(anAdapterName);
			adapter = (ILogStream)adapterClass.newInstance();
			adapter.setLogType(mLogType);
		} catch (Throwable t) {
			SYSTEM_ERR.println("Error loading logger adapter: ");
			t.printStackTrace(SYSTEM_ERR);
			if (!MOJO_LOG_ADAPTER.equals(anAdapterName)) {
				SYSTEM_ERR.println("Defaulting to mojo log adapter");
				loadAdapter(MOJO_LOG_ADAPTER);
			}
		}
	}

	/** @modelguid {2551A185-AB71-4C3D-96DF-FA43569F6339} */
	public void setLogType(short logType) {
		mLogType = logType;
	}

	/** @modelguid {049E56AD-91ED-4342-9CF4-1D7F187D987B} */
	static short theLogLevel = (short) (ERRSTREAM | OUTSTREAM | APPEXCEPTION | RUNTIMEEXCEPTION | EXCEPTION | STACKTRACE | ERROR);
	/** @modelguid {04A83CBB-9933-444A-AF74-743FE32657BA} */
	ILogStream adapter;
	/** @modelguid {F4763235-7481-415B-86A1-C561210FA4B6} */
	public short mLogType;
	/** @modelguid {08F94F57-7C79-4139-9B00-711516A7BCED} */
	private static final String MOJO_LOG_ADAPTER = "mojo.km.logging.mojo.MojoStream";
}
