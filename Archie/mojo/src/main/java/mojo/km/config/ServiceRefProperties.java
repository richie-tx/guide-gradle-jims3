package mojo.km.config;

import java.util.Iterator;

/** @modelguid {BAAC8BCD-0F4E-49BD-AA13-CF02CC0567AA} */
public class ServiceRefProperties extends GenericProperties {
	/** @modelguid {AF299B7B-B90E-44A4-B126-947802E95420} */
	public static final String NAME = "name";
	/** @modelguid {732F5195-6176-49D3-855E-BA6580505E11} */
    public static final String DISPATCH = "dispatch";

	/** @modelguid {E1814539-4DD7-4E33-87F6-31EE58B4B666} */
	public ServiceRefProperties() {
	}
    
	/** @modelguid {060BBB31-9652-437A-AD1E-79BE5D835AFF} */
	public ServiceRefProperties(ServiceRefProperties aCopy) {
		for (Iterator i = aCopy.getProperties(); i.hasNext(); ) {
			String lPropName = (String)i.next();
			String lPropValue = aCopy.getProperty(lPropName);
			setProperty(lPropName, lPropValue);
		}
	}

	/**
	 * @return String
	 * @modelguid {80DDDB87-7B9F-4A68-97C6-8622852A9E19}
	 */
	public String getDispatchClass() {
		return getProperty(DISPATCH);
	}
	/**
	 * @return String
	 * @modelguid {C27A58A7-54E7-430C-AF9F-45DC690F4BDE}
	 */
	public String getName() {
		return getProperty(NAME);
	}
	/**
	 * @param lServiceName
	 * @modelguid {FC40F397-C4AF-4344-B02C-F430C14CDE26}
	 */
	public void setName(String lServiceName) {
		setProperty(NAME, lServiceName);
		
	}
	/**
	 * @param lDispatch
	 * @modelguid {FF00EDF1-C9F4-49CB-AED2-D663F2CD0F5F}
	 */
	public void setDispatch(String lDispatch) {
		setProperty(DISPATCH, lDispatch);
		
	}
}
