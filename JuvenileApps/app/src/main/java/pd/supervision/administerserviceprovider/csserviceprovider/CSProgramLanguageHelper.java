/*
 * Created on Jan 31, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.supervision.administerserviceprovider.csserviceprovider;

/**
 * @author cc_cwalters
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CSProgramLanguageHelper 
{

    public static CSProgramLanguage createProgramLanguage(String programId, String programLanguageCode)
    {
        CSProgramLanguage new_language = new CSProgramLanguage();
        
        	//set program language attributes
        new_language.setLanguageCode(programLanguageCode);
        new_language.setProgramId(programId);
        
        	//save program language entity
        new_language.bind();
        return new_language;
        
    }//end of createProgramLanguage()
}//end of CSProgramLanguageHelper
