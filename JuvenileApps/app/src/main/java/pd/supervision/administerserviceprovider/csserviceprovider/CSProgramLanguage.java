/*
 * Created on Jan 18, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.supervision.administerserviceprovider.csserviceprovider;

import messaging.csserviceprovider.ProgramProgramLanguageEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import mojo.km.utilities.CollectionUtil;

/**
 * @author cc_cwalters
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CSProgramLanguage extends PersistentObject 
{
    private String programLanguageId;
    private String programId;
    private String languageCode;
    
    
    /**
     * @return Returns the programLanguageId.
     */
    public String getProgramLanguageId() {
        return programLanguageId;
    }
    /**
     * @param programLanguageId The programLanguageId to set.
     */
    public void setProgramLanguageId(String programLanguageId) {
        this.programLanguageId = programLanguageId;
    }
    
    /**
     * @return Returns the languageCode.
     */
    public String getLanguageCode() {
        return languageCode;
    }
    /**
     * @param languageCode The languageCode to set.
     */
    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }
    /**
     * @return Returns the programId.
     */
    public String getProgramId() {
        return programId;
    }
    /**
     * @param programId The programId to set.
     */
    public void setProgramId(String programId) {
        this.programId = programId;
    }
    
    /**
     * Find CSProgramLanguage by OID
     */
    public static CSProgramLanguage find(String programLanguageId)
	{
	    	//initialize lookup objects
	    CSProgramLanguage programLanguage = null;
		IHome home = new Home();

			//use delegate to locate given program languages entity
		programLanguage = (CSProgramLanguage) home.find(programLanguageId, CSProgramLanguage.class);
		return programLanguage;
	}//end of find()    

    /**
     * Find CSProgramLanguage by OID
     */
    public static CSProgramLanguage findByProgramAndLanguage(
    		String programId, String programLanguageCode)
	{
	    	//initialize lookup objects
	    CSProgramLanguage programLanguage = null;
		IHome home = new Home();
		
			//set up event for retrieving program language for given program
		ProgramProgramLanguageEvent 
			prog_language_event = new ProgramProgramLanguageEvent();
		prog_language_event.setProgramId(programId);
		prog_language_event.setLanguageCode(programLanguageCode);


			//use delegate to locate given program language entity
		programLanguage = (CSProgramLanguage)
			(CollectionUtil.iteratorToList(
					(home.findAll(prog_language_event, 
							CSProgramLanguage.class)))).get(0);
		return programLanguage;
	}//end of find() 
    
	/**
	 * Bind entity to database thus creating an OID
	 *
	 */
    public void bind()
    {
        IHome home = new Home();
        home.bind(this);
    }//end of bind()
	
}//end of CSProgramLanguage 
