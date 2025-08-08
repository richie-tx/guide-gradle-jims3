/*
 * Created on Feb 6, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.csserviceprovider.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * @author cc_cwalters
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CSProgramHierarchyResponseEvent extends ResponseEvent implements Comparable  
{
	//member variables
    private String progHierarchyId;
    private String progHierarchyCode;
    private String programGroupCode;
    private String programTypeCode;
    private String programSubtypeCode;
    private String programGroupDesc;
    private String programTypeDesc;
    private String programSubtypeDesc;
	
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object o) 
	{
		CSProgramHierarchyResponseEvent compare_object = 
								(CSProgramHierarchyResponseEvent)o;
		if (programGroupDesc.compareTo(compare_object.getProgramGroupDesc()) < 0)
		{
			return -1;
		}
		else
			if (programGroupDesc.compareTo(compare_object.getProgramGroupDesc()) > 0)
			{
				return 1;
			}
			else //program group is equal
			{
				if (programTypeDesc.compareTo(compare_object.getProgramTypeDesc()) < 0)
					return -1;
				else
					if (programTypeDesc.compareTo(compare_object.getProgramTypeDesc()) > 0)
						return 1;
					else	//program group and type are equal
					{
						if (programSubtypeDesc.compareTo(compare_object.getProgramSubtypeDesc()) < 0)
							return -1;
						else
							if (programSubtypeDesc.compareTo(compare_object.getProgramSubtypeDesc()) > 0)
								return 1;
							else	//program group, type, and subtype are equal
								return 0;
					}
			}
	}//end of compareTo()
	
    /**
     * @return Returns the progHierarchyCode.
     */
    public String getProgHierarchyCode() {
        return progHierarchyCode;
    }
    /**
     * @param progHierarchyCode The progHierarchyCode to set.
     */
    public void setProgHierarchyCode(String progHierarchyCode) {
        this.progHierarchyCode = progHierarchyCode;
    }
        /**
         * @return Returns the progHierarchyId.
         */
        public String getProgHierarchyId() {
            return progHierarchyId;
        }
        /**
         * @param progHierarchyId The progHierarchyId to set.
         */
        public void setProgHierarchyId(String progHierarchyId) {
            this.progHierarchyId = progHierarchyId;
        }
    /**
     * @return Returns the programGroupCode.
     */
    public String getProgramGroupCode() {
        return programGroupCode;
    }
    /**
     * @param programGroupCode The programGroupCode to set.
     */
    public void setProgramGroupCode(String programGroupCode) {
        this.programGroupCode = programGroupCode;
    }
    /**
     * @return Returns the programSubtypeCode.
     */
    public String getProgramSubtypeCode() {
        return programSubtypeCode;
    }
    /**
     * @param programSubtypeCode The programSubtypeCode to set.
     */
    public void setProgramSubtypeCode(String programSubtypeCode) {
        this.programSubtypeCode = programSubtypeCode;
    }
    /**
     * @return Returns the programTypeCode.
     */
    public String getProgramTypeCode() {
        return programTypeCode;
    }
    /**
     * @param programTypeCode The programTypeCode to set.
     */
    public void setProgramTypeCode(String programTypeCode) {
        this.programTypeCode = programTypeCode;
    }
	/**
	 * @return Returns the programGroupDesc.
	 */
	public String getProgramGroupDesc() {
		return programGroupDesc;
	}
	/**
	 * @param programGroupDesc The programGroupDesc to set.
	 */
	public void setProgramGroupDesc(String programGroupDesc) {
		this.programGroupDesc = programGroupDesc;
	}
	/**
	 * @return Returns the programSubtypeDesc.
	 */
	public String getProgramSubtypeDesc() {
		return programSubtypeDesc;
	}
	/**
	 * @param programSubtypeDesc The programSubtypeDesc to set.
	 */
	public void setProgramSubtypeDesc(String programSubtypeDesc) {
		this.programSubtypeDesc = programSubtypeDesc;
	}
	/**
	 * @return Returns the programTypeDesc.
	 */
	public String getProgramTypeDesc() {
		return programTypeDesc;
	}
	/**
	 * @param programTypeDesc The programTypeDesc to set.
	 */
	public void setProgramTypeDesc(String programTypeDesc) {
		this.programTypeDesc = programTypeDesc;
	}
}
