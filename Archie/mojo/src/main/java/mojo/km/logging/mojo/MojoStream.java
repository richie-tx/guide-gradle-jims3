//Source file: Z:/java.vob/CommonServiceLayer/logging/LogStream.java

package mojo.km.logging.mojo;

import mojo.km.logging.ILogStream;

/**
 * Responsible for handling the logging of messages to various log locations.
 *@author Eric A Amundson
 *@version 1.0
 * @modelguid {BAD737AA-DF96-4EC3-B835-D0FB297D9212}
 */
public class MojoStream implements ILogStream {
    /**
     *    Constructor
     *    @param logType - set log type for stream.
     * @modelguid {A4BFB1B0-1BAC-4AB9-9228-BD0EF8052F5C}
     */
    public MojoStream(short logType) {
        mLogType = logType;
    }

    /** Default constructor 
     * @modelguid {41ADE566-7EF1-4E6A-B1DC-D1E2BCDB42C3}
     */
    public MojoStream() {
    }

    /**
     * Writes the specified byte to this output stream. The general
     * contract for <code>write</code> is that one byte is written to the output stream. The byte to be written is the eight
     * low-order bits of the argument <code>b</code>. The 24 high-order bits of <code>b</code> are ignored. <p>
     * Subclasses of <code>OutputStream</code> must provide an implementation for this method.
     * @param      b   the <code>byte</code>.
     * @exception  IOException  if an I/O error occurs. In particular, an <code>IOException</code> may be thrown if the
     * output stream has been closed.
     * @modelguid {09029555-1065-46EA-9D91-00A3D0D33827}
     */
    public void write(int b) {
        byte[] bytes = new byte[1];
        bytes[0] = (byte)b;
        ContextLog.getInstance().write(bytes);
    }

    /**
     * Writes <code>b.length</code> bytes from the specified byte array
     * to this output stream. The general contract for <code>write(b)</code>
     * is that it should have exactly the same effect as the call <code>write(b, 0, b.length)</code>.
     * @param      b   the data.
     * @see java.io.OutputStream#write(byte[], int, int)
     * @exception  IOException  if an I/O error occurs.
     * @modelguid {37DD5619-EB57-4AD9-B0FE-9246A8A94221}
     */
    public void write(byte[] b) {
        System.out.println( b );
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
     * @modelguid {9AB32BA3-484A-4983-8B57-962B13DBC01D}
     */
    public void write(byte[] b, int off, int len) {
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
     * @modelguid {62EE006A-45E2-43C9-82D6-2E7BBC92741C}
     */
    public void flush() {
    }

    /**
     * Closes this output stream and releases any system resources
     * associated with this stream. The general contract of <code>close</code>
     * is that it closes the output stream. A closed stream cannot perform output operations and cannot be reopened. <p>
     * The <code>close</code> method of <code>OutputStream</code> does nothing.
     * @exception  IOException  if an I/O error occurs.
     * @modelguid {D37E15D2-BA06-45FF-8F0A-AEBE5AF318B5}
     */
    public void close() {
    }


    /**
     *    Prints the character buffer to log file.
     *    @param buffer - log events msg.
     *    @modelguid {2E943F1A-5849-4FA2-89D6-23371BCF84CB}
     */
    public void println(char[] buffer) {
        byte[] byteBuffer = (
            new String(buffer)).getBytes();
        ContextLog.getInstance().write(byteBuffer);
    }

	/** @modelguid {5DBFB204-7D3F-4199-8285-221EDC589FDF} */
    public void setLogType(short logType){ mLogType = logType; }

	/** @modelguid {15B7984A-E575-4F92-A2CB-96BED49BB4C8} */
    public void setThrowable(Throwable throwable){ }

	/** @modelguid {D3831FD5-FC94-44A4-A09A-8BD06EADF392} */
    public short mLogType = 1;
}
