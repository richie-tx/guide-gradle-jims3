   package mojo.km.messaging.exception;

/**
   Exception class for schema load exception.
   Creation date: (7/6/00 9:31:25 AM)
   @author: D. Marple
 * @modelguid {B22FB22F-BFCF-43D6-AF91-52508BA8945E}
 */
   public class SchemaLoadException extends Exception {
      	  /**
	 SchemaLoadException constructor.
	 * @modelguid {0E7A001B-A1A5-44C2-9130-0B295882088F}
	 */
	  public SchemaLoadException() {
		 super();
	  }
   /**
	 SchemaLoadException constructor.
	 @param s java.lang.String
	 * @modelguid {440B5D5B-EBAF-410F-9CFB-4BC6866940E4}
	 */
	  public SchemaLoadException(String s) {
		 super(s);
	  }
   }