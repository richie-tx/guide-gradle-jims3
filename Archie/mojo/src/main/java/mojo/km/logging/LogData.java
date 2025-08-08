package mojo.km.logging;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * This class encapsulates the data to be logged.  It is a loosely typed set of properties
 * so that any amount or kind of data can be submitted for logging.  This should satisfy
 * any logging mechanism that might actually be used.
 *  
 * @author Saleem Shafi
 * @modelguid {90D7474D-B99C-4A1A-B3A4-EC38E618602C}
 */
public class LogData {
	/** @modelguid {F50FF4B8-9B83-4189-BA21-9CCC5EEB2D90} */
	public static final String LEVEL = "level";
	/** @modelguid {4606DE44-54BF-4433-8EA1-DCB7DFACDB1A} */
	public static final String MESSAGE = "message";
	/** @modelguid {AC53DB5D-FF71-45C0-9794-8F7790EA000B} */
	public static final String THROWABLE = "throwable";
	/** @modelguid {04ACD278-3D3F-4442-A63B-A116106B296C} */
	public static final String CLASS = "class";
	/** @modelguid {16475277-8967-4502-8AF6-ED91A190F7D3} */
	public static final String METHOD = "method";
	/** @modelguid {E0DE3B78-4623-4089-BD69-47BD7E6E19A5} */
	public static final String PARAMS = "params";
	/** @modelguid {00C71E3B-D583-4579-9C0F-071865F60176} */
	public static final String RESULT = "result";
	/** @modelguid {E3F95EC9-2524-4405-8465-E4065EF8435C} */
	public static final String TRACETYPE = "trace type";
	/** @modelguid {618B7CED-F3BF-46B0-B371-C49564C817E0} */
	public static final String EXITING = "exiting";
	/** @modelguid {D1EE9D3B-9FF1-4715-BABD-E79393B305EB} */
	public static final String ENTERING = "entering";
	
	/** @modelguid {FCE8CE37-DF8D-4577-A5C0-8AC9D0D8D6B6} */
	private Map data = new HashMap();
	
	/**
	 * Returns the data identified by the given key.  Any String can be used as a
	 * key, but the standard keys are provided in globals constants of this class.
	 * Whatever key you use needs to be coordinated with the logging adapter used
	 * since that is what will need to interpret your data.
	 * 
	 * @param aKey the key of the data 
	 * @return the data for the given key
	 * @modelguid {491C13BA-64D2-4465-AFC8-3E3DDD8DEE11}
	 */
	public Serializable getData(String aKey) {
		return (Serializable)data.get(aKey);
	}
	
	/**
	 * Sets a specific piece of data for the logging event.  Any String can be used
	 * as a key and any Serializable object can be stored.  The standard keys are 
	 * provided in globals constants of this class.  Whatever key you use needs to 
	 * be coordinated with the logging adapter used since that is what will need to 
	 * interpret your data.
	 * 
	 * @param aKey the key for the data
	 * @param aValue the value of the data
	 * @modelguid {3BA80CAD-5F5C-4B7F-92B8-A0C28F3C5B4D}
	 */
	public void setData(String aKey, Serializable aValue) {
		data.put(aKey, aValue);
	}
}
