package mojo.km.config;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/** @modelguid {B1378ADD-B69C-4A9E-98EB-579A9B1052F6} */
public class StateMachineProperties {
	/** @modelguid {05CE3312-EFDE-4F62-825C-66FE5C955684} */
	private Map properties = new HashMap();
	
	/** @modelguid {42425FD8-4F55-4127-9913-1291EC2B1774} */
	public static StateMachineProperties getInstance() {
		return MojoProperties.getInstance().getStateMachineProperties();
	}


	/** @modelguid {B8A8D370-6957-4A3A-A8C1-E4B4774EDF73} */
	public Iterator getMachines() {
		List lMachines = (List)properties.get("Machines");
		if (lMachines == null) {
			lMachines = new Vector();
		}
		return lMachines.iterator();
	}

	/** @modelguid {2A6A0382-0354-42C9-B72B-11C09A171091} */
	public void addMachine(String aMachine) {
		List lMachines = (List)properties.get("Machines");
		if (lMachines == null) {
			lMachines = new Vector();
			properties.put("Machines", lMachines);
		}
		lMachines.add(aMachine);
	}

	/**
	 * @param clss
	 * @param state
	 * @return   
	 * @modelguid {730656DF-DE32-4FF2-BB77-2284E51468FE}
	 */
	public Iterator getTransitions(Class clss, String state) {
		return getTransitions(clss.getName(),state);
	}
    
	/**
	 * @param clss - class name
	 * @param state - state transition
	 * @return  transition property 
	 * @modelguid {9CC2DB23-E296-4E1B-B72F-844B7EA1FAA8}
	 */
	public Iterator getTransitions(String clss, String state) {
		List lTransitions = (List)properties.get(clss+"."+state);
		if (lTransitions == null) {
			lTransitions = new Vector();
		}
		return lTransitions.iterator();
	}

	/**
	 * @param clss
	 * @return   
	 * @modelguid {574F28D1-85B9-401B-8C04-D6DC05A96621}
	 */
	public String getDefaultStartState(Class clss) {
		return getDefaultStartState(clss.getName());
	}

	/**
	 * @param clss
	 * @return   
	 * @modelguid {55333ACC-2A68-412C-8F3B-156B8D4598D9}
	 */
	public String getDefaultStartState(String clss) {
		return (String)properties.get(clss+".DefaultStartState");
	}

	/**
	 * @param clss
	 * @return   
	 * @modelguid {580E0B23-45C4-4D96-8E44-289DB38B1E2D}
	 */
	public String getStateField(Class clss) {
		return getStateField(clss.getName());
	}

	/**
	 * @param clss
	 * @return   
	 * @modelguid {33A5D792-A28E-4BF5-90A2-8FF22DB869CE}
	 */
	public String getStateField(String clss) {
		return (String)properties.get(clss+".StateField");
	}

	/**
	 * @param clss
	 * @return   
	 * @modelguid {F8918455-0BBB-477B-A989-4CE04B52E3CC}
	 */
	public Iterator getStates(Class clss) {
		return getStates(clss.getName());
	}

	/**
	 * @param clss
	 * @return   
	 * @modelguid {52E44A81-2126-4B81-9941-E73D99630288}
	 */
	public Iterator getStates(String clss) {
		List lStates = (List)properties.get(clss+".States");
		if (lStates == null) {
			lStates = new Vector();
		}
		return lStates.iterator();
	}

	/**
	 * @param lMachineClass
	 * @param lMachineDefaultStartState
	 * @modelguid {8335BF3E-5771-47D4-B119-345187000B75}
	 */
	public void setDefaultStartState(String lMachineClass, String aMachineDefaultStartState) {
		properties.put(lMachineClass+".DefaultStartState", aMachineDefaultStartState);
	}

	/**
	 * @param lMachineClass
	 * @param lMachineStateField
	 * @modelguid {9165697A-4510-4EA2-91BA-7AA6E39CDDAE}
	 */
	public void setStateField(String lMachineClass, String aMachineStateField) {
		properties.put(lMachineClass+".StateField", aMachineStateField);
	}

	/**
	 * @param lMachineClass
	 * @param lStateName
	 * @modelguid {C725D5E5-25CC-416C-88C8-5D864A34373F}
	 */
	public void addState(String lMachineClass, String lStateName) {
		List lStates = (List)properties.get(lMachineClass+".States");
		if (lStates == null) {
			lStates = new Vector();
			properties.put(lMachineClass+".States", lStates);
		}
		lStates.add(lStateName);
	}

	/**
	 * @param lMachineClass
	 * @param lStateName
	 * @param lTransitionToState
	 * @modelguid {E139F61F-3E96-463D-8535-EE514EF8ADB3}
	 */
	public void addTransition(String aMachineClass, String aStateName, String aTransitionToState) {
		List lTransitions = (List)properties.get(aMachineClass+"."+aStateName);
		if (lTransitions == null) {
			lTransitions = new Vector();
			properties.put(aMachineClass+"."+aStateName, lTransitions);
		}
		lTransitions.add(aTransitionToState);
	}
}
