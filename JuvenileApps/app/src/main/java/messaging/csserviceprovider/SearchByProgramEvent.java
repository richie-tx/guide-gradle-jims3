/*
 * Created on Jan 15, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.csserviceprovider;

/**
 * @author cc_cwalters
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SearchByProgramEvent extends SearchServiceProviderEvent 
{
    private String programName;
    private String programGroupCode;
    private String programTypeCode;
    private String isContractProgram;
    private String programStatus;
    
    
    /**
     * @return Returns the isContractProgram.
     */
    public String getIsContractProgram() {
        return isContractProgram;
    }
    /**
     * @param isContractProgram The isContractProgram to set.
     */
    public void setContractProgram(String isContractProgram) {
        this.isContractProgram = isContractProgram;
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
     * @return Returns the programName.
     */
    public String getProgramName() {
        return programName;
    }
    /**
     * @param programName The programName to set.
     */
    public void setProgramName(String programName) {
        this.programName = programName;
    }
    /**
     * @return Returns the programStatus.
     */
    public String getProgramStatus() {
        return programStatus;
    }
    /**
     * @param programStatus The programStatus to set.
     */
    public void setProgramStatus(String programStatus) {
        this.programStatus = programStatus;
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
}
