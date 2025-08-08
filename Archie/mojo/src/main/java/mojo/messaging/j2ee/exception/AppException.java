package mojo.messaging.j2ee.exception;

import java.io.PrintStream;
import java.io.PrintWriter;

import mojo.km.messaging.exception.ReturnException;

/**
 *
 * @author Hadi Mallah
 */
public class AppException extends ReturnException {

	private Throwable cause;

    public AppException(String msg) {
    	super(msg);
	}

	public AppException(String msg, Throwable cause) {
		super(msg);
		this.cause = cause;
	}

	public String getMessage() {
		if (cause != null) {
			return super.getMessage() + ": " + cause.getMessage();
		}
		else {
			return super.getMessage();
		}
	}

	public void printStackTrace() {
		super.printStackTrace();
		if (cause != null) {
			System.err.print("Root cause: ");
			cause.printStackTrace();
		}
	}

	public void printStackTrace(PrintStream ps) {
		super.printStackTrace(ps);
		if (cause != null) {
			ps.print("Root cause: ");
			cause.printStackTrace(ps);
		}
	}

	public void printStackTrace(PrintWriter pw) {
		super.printStackTrace(pw);
		if (cause != null) {
			pw.print("Root cause: ");
			cause.printStackTrace(pw);
		}
	}

	public Throwable getCause() {
		return cause;
	}

}
