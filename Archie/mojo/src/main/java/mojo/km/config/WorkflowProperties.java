package mojo.km.config;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/** @modelguid {07D3B06C-D43D-4540-98D9-88D792678C80} */
public class WorkflowProperties extends GenericProperties {
	/** @modelguid {D2302ED5-6811-4AFD-B338-35D1161AE2C1} */
	public static final String NAME = "name";
	/** @modelguid {0FEC7B09-2E99-4AE7-B182-68A5A653860F} */
	public List classes = new Vector();
	
	/** @modelguid {DC98A6AF-6F2C-470E-B49C-05F9F4879DE7} */
	public String getName() {
		return getProperty(NAME);
	}
	
	/** @modelguid {7FC4489C-CD0D-42CF-B320-FB97A67F8BA7} */
	public void setName(String aName) {
		setProperty(NAME, aName);
	}
	
	/** @modelguid {074D6CA9-2DE0-43F0-8F80-8E4BDD5D6613} */
	public void addClass(String aClassname) {
		classes.add(aClassname);
	}
	
	/** @modelguid {9C912DC7-5396-4B54-B279-FA4EE5AEF139} */
	public Iterator getClasses() {
		return classes.iterator();
	}
}
