/**
 * 
 */
package pd.organization.transactions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import pd.organization.OrganizationService;
import pd.supervision.supervisionstaff.Organization;
import pd.transferobjects.helper.OrganizationHelper;
import messaging.organization.GetDivisionForAgencyEvent;
import messaging.organization.GetProgramUnitsForDivisionEvent;
import messaging.organization.reply.GetDivisionForAgencyResponseEvent;
import messaging.organization.reply.GetProgramUnitResponseEvent;
import messaging.transferobjects.OrganizationTO;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.CollectionUtil;
import mojo.km.utilities.MessageUtil;

/**
 * @author cc_cwalters
 *
 */
public class GetProgramUnitsForDivisionCommand implements ICommand 
{

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception 
	{
		GetProgramUnitsForDivisionEvent 
			program_units_for_division_event = 
				(GetProgramUnitsForDivisionEvent) event ; 
		
			//retrieve program units for selected division
		List<Organization> program_units_list = 
			CollectionUtil.iteratorToList((OrganizationService.getOrganizationService().
				getProgramUnitsForDivision(
						program_units_for_division_event.getDivisionId())).iterator());

			//loop through program unit list and add to response list
		List<GetProgramUnitResponseEvent> 
			program_unit_responses = new ArrayList<GetProgramUnitResponseEvent>();
		for (int i=0;i<program_units_list.size();i++)
		{
			program_unit_responses.add(
				OrganizationHelper.convertProgramUnitToResponseEvent(
						program_units_list.get(i)));
		}

			//post response objects
   		MessageUtil.postReplies(program_unit_responses);

	}

}
