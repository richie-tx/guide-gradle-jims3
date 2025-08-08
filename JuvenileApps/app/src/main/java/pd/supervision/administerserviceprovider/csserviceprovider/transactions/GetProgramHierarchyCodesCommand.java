/*
 * Created on Feb 6, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.supervision.administerserviceprovider.csserviceprovider.transactions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import pd.codetable.SimpleCodeTableHelper;
import pd.codetable.supervision.CSProgramHierarchy;
import messaging.csserviceprovider.reply.CSProgramHierarchyResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;

/**
 * @author cc_cwalters
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetProgramHierarchyCodesCommand implements ICommand {

    /* (non-Javadoc)
     * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
     */
    public void execute(IEvent event)
    {
        // Retrieve program hierarchy objects and compile response event
        Iterator program_hierarchy_iter = CSProgramHierarchy.findAll();
        List program_hierarchies = new ArrayList();
        while (program_hierarchy_iter.hasNext())
        {
            CSProgramHierarchy prog_hierarchy = 
                			(CSProgramHierarchy)program_hierarchy_iter.next();
            
            CSProgramHierarchyResponseEvent prog_hierarchy_response = 
                								new CSProgramHierarchyResponseEvent();
            prog_hierarchy_response.setProgHierarchyCode(prog_hierarchy.getProgHierarchyCode());
            prog_hierarchy_response.setProgHierarchyId(prog_hierarchy.getProgHierarchyId());

            prog_hierarchy_response.setProgramGroupCode(prog_hierarchy.getProgramGroupCode());
            prog_hierarchy_response.setProgramGroupDesc(
              SimpleCodeTableHelper.getDescrByCode("CS_PROGRAM_GROUP", 
            		prog_hierarchy.getProgramGroupCode()));
             
            prog_hierarchy_response.setProgramTypeCode(prog_hierarchy.getProgramTypeCode());
            prog_hierarchy_response.setProgramTypeDesc(
                    SimpleCodeTableHelper.getDescrByCode("CS_PROGRAM_TYPE", 
                  		prog_hierarchy.getProgramTypeCode()));
            
            prog_hierarchy_response.setProgramSubtypeCode(prog_hierarchy.getProgramSubtypeCode());
            prog_hierarchy_response.setProgramSubtypeDesc(
                    SimpleCodeTableHelper.getDescrByCode("CS_PROGRAM_SUBTYPE", 
                  		prog_hierarchy.getProgramSubtypeCode()));
            
            	//add response to collection
            program_hierarchies.add(prog_hierarchy_response);
        }
        
        	//post program hierarchy responses
        MessageUtil.postReplies(program_hierarchies);
    }

    /* (non-Javadoc)
     * @see mojo.km.context.ICommand#onRegister(mojo.km.messaging.IEvent)
     */
    public void onRegister(IEvent event) {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see mojo.km.context.ICommand#onUnregister(mojo.km.messaging.IEvent)
     */
    public void onUnregister(IEvent event) {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see mojo.km.context.ICommand#update(java.lang.Object)
     */
    public void update(Object updateObject) {
        // TODO Auto-generated method stub

    }

}
