//Source file: Z:/java.vob/CommonServiceLayer/logging/LogStream.java

package mojo.km.logging.multicast;

import java.io.OutputStream;
import java.io.PrintStream;

import mojo.km.config.AppProperties;

/**
 *@author Eric A Amundson
 *@version 1.0
 * @modelguid {E42AC842-5109-4ACB-B529-15C1CFDB22ED}
 */
public class MultiCastLogStream extends OutputStream {
	/** Error stream filter value. 
	 * @modelguid {BC818D09-97B8-4653-BC28-95E3EF291B1D}
	 */
	public static final short ERRSTREAM = 0x01;
	/** Standard stream filter value. 
	 * @modelguid {F186B878-9E54-430E-A9F0-B63DEE1F438D}
	 */
	public static final short OUTSTREAM = 0x02;
	/** Application exception filter value. 
	 * @modelguid {D707009E-FD0B-4C4E-8AA4-6E760FF7EE7A}
	 */
	public static final short APPEXCEPTION = 0x04;
	/** Runtime exception filter value. 
	 * @modelguid {1077E9A0-6281-465E-8F16-49C045ABB2DF}
	 */
	public static final short RUNTIMEEXCEPTION = 0x08;
	/** Standard exception filter value. 
	 * @modelguid {CF1C232E-5650-49E6-B126-4B27FDF971B8}
	 */
	public static final short EXCEPTION = 0x10;
	/** if this filter value is set and exception who's filter value is set will print stack trace. 
	 * @modelguid {508FB50C-2DBC-443E-84F0-A7F025417D48}
	 */
	public static final short STACKTRACE = 0x20;
	/** Error throwable filter value. 
	 * @modelguid {A93140CB-5735-4A4E-A38E-2D2CB7DB695D}
	 */
	public static final short ERROR = 0x40;

	/** @modelguid {BF21A4DB-E897-493C-80B6-4EA177D14040} */
	private static final PrintStream SYSTEM_ERR = System.err;

	/** Default constructor 
	 * @modelguid {D7CFF0E1-C5D9-4B9B-AD8C-EF62B9E4B870}
	 */
	public MultiCastLogStream() {
		this((short)1);
	}

	/**
	 *    Constructor
	 *    @param logType - set log type for stream.
	 * @modelguid {BE36EC9E-C004-4639-84BC-8679228DFF51}
	 */
	public MultiCastLogStream(short logType) {
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
	 * @modelguid {1EC32EAE-E17B-4EAC-ACC8-3A4A3891681D}
	 */
	public void write(int b) {
		if ((MultiCastLogStream.getlogging() & mLogType) == 0) {
			return;
		}
		byte[] bytes = new byte[] {(byte)b};
		ContextLog.getInstance().write(bytes);
	}

	/**
	 * Writes <code>b.length</code> bytes from the specified byte array
	 * to this output stream. The general contract for <code>write(b)</code>
	 * is that it should have exactly the same effect as the call <code>write(b, 0, b.length)</code>.
	 * @param      b   the data.
	 * @see OutputStream#write(byte[], int, int)
	 * @exception  IOException  if an I/O error occurs.
	 * @modelguid {BCCBC75E-44B7-4E1E-9ACB-719F6190492F}
	 */
	public void write(byte[] b) {
		ContextLog.getInstance().write(b);
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
	 * @modelguid {322BE6F7-7D2F-4F98-B307-C1C4FFF84B28}
	 */
	public void write(byte[] b, int off, int len) {
		if ((MultiCastLogStream.getlogging() & mLogType) == 0 || len <= 2) {
			return;
		}
		if (b == null) {
			throw new NullPointerException();
		} else if ((off < 0) || (off > b.length) || (len < 0) || ((off + len) > b.length) || ((off + len) < 0)) {
			throw new IndexOutOfBoundsException();
		} else if (len == 0) {
			return;
		}
		byte[] out = new byte[len];
		System.arraycopy(b, off, out, 0, len);
		ContextLog.getInstance().write(out);
	}

	/**
	 * Flushes this output stream and forces any buffered output bytes
	 * to be written out. The general contract of <code>flush</code> is
	 * that calling it is an indication that, if any bytes previously
	 * written have been buffered by the implementation of the output
	 * stream, such bytes should immediately be written to their intended destination. <p>
	 * The <code>flush</code> method of <code>OutputStream</code> does nothing.
	 * @exception  IOException  if an I/O error occurs.
	 * @modelguid {5FA71C9A-DB66-475C-B59C-D553DC194083}
	 */
	public void flush() {
	}

	/**
	 * Closes this output stream and releases any system resources
	 * associated with this stream. The general contract of <code>close</code>
	 * is that it closes the output stream. A closed stream cannot perform output operations and cannot be reopened. <p>
	 * The <code>close</code> method of <code>OutputStream</code> does nothing.
	 * @exception  IOException  if an I/O error occurs.
	 * @modelguid {EF579AF4-1836-4CF7-A1F5-4C0A398F262E}
	 */
	public void close() {
	}

	/**
	 * Stop logging function.
	 *    @param logLevel - filter values or'd together, E.G. ERRSTREAM | OUTSTREAM
	 * @modelguid {8EB95E8D-AD93-4274-923A-49AC9BEFA403}
	 */
	public static synchronized void setlogging(short logLevel) {
		theLogLevel = logLevel;
	}

	/**
	 * Stop logging function.
	 *    @return or'd log filters.
	 * @modelguid {260C9225-E3CE-4FB6-B219-FAB4BD567EC5}
	 */
	public static synchronized short getlogging() {
		return theLogLevel;
	}

	/**
	 *    Prints the character buffer to log file.
	 *    @param buffer - log events msg.
	 *    @modelguid {9E12B32C-3238-4660-A190-C495BAF8AA54}
	 */
	public void println(char[] buffer) {
		if ((MultiCastLogStream.getlogging() & mLogType) == 0 || buffer.length <= 2) {
			return;
		}
		byte[] byteBuffer = (new String(buffer)).getBytes();
		ContextLog.getInstance().write(byteBuffer);
	}

	/** @modelguid {3A0C6244-7AFB-4426-BBE3-024306ACD692} */
	private void init() {
		String lLogFilterCode = AppProperties.getInstance().getProperty("LogFilterCode");
		if (lLogFilterCode != null) {
			try {
				theLogLevel = Short.parseShort(lLogFilterCode);
			} catch (Throwable t) {
			}
		}
	}

	/** @modelguid {E819C339-BB9F-4EA0-AB0F-443128A0176B} */
	public void setLogType(short logType) {
		mLogType = logType;
	}

	/** @modelguid {BC93B340-55EA-4849-B8BA-76C9011A4617} */
	static short theLogLevel = ERRSTREAM | OUTSTREAM | APPEXCEPTION | RUNTIMEEXCEPTION | EXCEPTION | STACKTRACE | ERROR;
	/** @modelguid {D4C9CAB6-7DEE-4136-8511-62B3A06B2357} */
	public short mLogType;
}
