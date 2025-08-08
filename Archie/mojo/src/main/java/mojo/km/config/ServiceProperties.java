package mojo.km.config;

import java.util.*;

/** @modelguid {C56D6045-37B8-425B-9109-0A3C3B32DF26} */
public class ServiceProperties extends GenericProperties {
	/** @modelguid {70F1E724-FEFA-40E9-9D0D-BDB922661F09} */
	public static final String NAME = "name";
	/** @modelguid {A0A10C72-4BB2-4950-9B8B-C568DD93249F} */
    public static final String EVENT = "event";
	/** @modelguid {EE01A050-517D-4593-9F41-00FB896D01CE} */
    private List commands = new Vector();
    
	public static final String CHECKPOINT = "checkPoint";

	/** @modelguid {3959806E-9E74-471C-8914-0B8A1B59DB43} */
	public void addCommand(String aCommandName) {
    	commands.add(aCommandName);
    }

	/** @modelguid {170F7804-8198-415F-99FC-5DC03B3DD648} */
	public Iterator getCommands() {
    	return commands.iterator();
    }
    
	/** @modelguid {DAF59A35-20C0-4F77-8773-03F9BB694B67} */
    public String getName() {
    	return getProperty(NAME);
    }
    
	/** @modelguid {5937E8FE-E31B-456D-A987-90256B7DB1B5} */
    public String getEventName() {
    	return getProperty(EVENT);
    }
    
    public void setCheckPoint( String trueFalse ) {
    	setProperty(CHECKPOINT, trueFalse);
    }
    
    public boolean isCheckPoint()
	{
		if (getProperty(CHECKPOINT) != null && getProperty(CHECKPOINT).equals("true"))
		{
			return true;
		}
		return false;
	}

	/**
	 * @param lServiceName
	 * @modelguid {874837A9-0DE4-4505-AFBB-EE46FF7B31D8}
	 */
	public void setName(String aServiceName) {
		setProperty(NAME, aServiceName);
	}
	
	/** @modelguid {A8EE5949-7801-4746-9757-55EE0CDF4F65} */
	public void setEventName(String anEventName) {
		setProperty(EVENT, anEventName);
	}
}
