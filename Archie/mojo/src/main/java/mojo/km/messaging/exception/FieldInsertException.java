package mojo.km.messaging.exception;

/**
 *   Exception class for field insertion exceptions.
 *   Creation date: (5/17/00 12:47:35 PM)
 *   @author: D. Marple
 * @modelguid {FDE804BB-B1CD-4822-9719-DB63A8DCE2D6}
 */
public class FieldInsertException extends RuntimeException {
    /**
     *	 FieldInsertException constructor comment.
     * @modelguid {5B5D481D-2CF2-4EF1-B94A-0B065913B2D3}
     */
    public FieldInsertException() {
        super();
    }

    /**
     *	 FieldInsertException constructor comment.
     *	 @param s java.lang.String
     * @modelguid {D40250C4-75A4-41BC-94C8-F0CCD2BA448D}
     */
    public FieldInsertException(String s) {
        super(s);
    }
}
