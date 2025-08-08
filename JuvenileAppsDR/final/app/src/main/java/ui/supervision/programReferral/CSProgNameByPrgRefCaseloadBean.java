package ui.supervision.programReferral;

import java.util.List;
import java.util.Set;

public class CSProgNameByPrgRefCaseloadBean implements Comparable
{
	private String programName;
	
	private Set programIdsSet;
	private List programIdsBeanList;
	private List progNamePrgReferralList; //CSProgRefCaseloadBean
	
	private List openProgReferralList; //CSProgRefCaseloadBean
	private List allProgReferralList; //CSProgRefCaseloadBean
	
	
	/**
	 * @return the programIdsSet
	 */
	public Set getProgramIdsSet() {
		return programIdsSet;
	}
	/**
	 * @param programIdsSet the programIdsSet to set
	 */
	public void setProgramIdsSet(Set programIdsSet) {
		this.programIdsSet = programIdsSet;
	}
	/**
	 * @return the programIdsBeanList
	 */
	public List getProgramIdsBeanList() {
		return programIdsBeanList;
	}
	/**
	 * @param programIdsBeanList the programIdsBeanList to set
	 */
	public void setProgramIdsBeanList(List programIdsBeanList) {
		this.programIdsBeanList = programIdsBeanList;
	}
	/**
	 * @return the programName
	 */
	public String getProgramName() {
		return programName;
	}
	/**
	 * @param programName the programName to set
	 */
	public void setProgramName(String programName) {
		this.programName = programName;
	}
	/**
	 * @return the progNamePrgReferralList
	 */
	public List getProgNamePrgReferralList() {
		return progNamePrgReferralList;
	}
	/**
	 * @param progNamePrgReferralList the progNamePrgReferralList to set
	 */
	public void setProgNamePrgReferralList(List progNamePrgReferralList) {
		this.progNamePrgReferralList = progNamePrgReferralList;
	}
	/**
	 * @return the openProgReferralList
	 */
	public List getOpenProgReferralList() {
		return openProgReferralList;
	}
	/**
	 * @param openProgReferralList the openProgReferralList to set
	 */
	public void setOpenProgReferralList(List openProgReferralList) {
		this.openProgReferralList = openProgReferralList;
	}
	/**
	 * @return the allProgReferralList
	 */
	public List getAllProgReferralList() {
		return allProgReferralList;
	}
	/**
	 * @param allProgReferralList the allProgReferralList to set
	 */
	public void setAllProgReferralList(List allProgReferralList) {
		this.allProgReferralList = allProgReferralList;
	}
	
	/**
	 * 
	 */
	public int compareTo(Object o) {
		
		if ( o == null ){
			return -1;
		}
		CSProgNameByPrgRefCaseloadBean c = ( CSProgNameByPrgRefCaseloadBean )o;
		
		if (c.getProgramName() == null){
			return -1;
		}		
		if (this.getProgramName() == null){
			return 1;
		}
		return this.getProgramName().compareToIgnoreCase(c.getProgramName());
	}

	
}
