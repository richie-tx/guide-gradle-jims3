package pd.supervision.administercompliance;


/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

public class CommunityServiceHistory extends CommunityService
{
	public void setCommunityServiceHistory(){
		this.setHoursCompleted(super.getHoursCompleted());
		this.setHoursOrdered(super.getHoursOrdered());
		this.setNcResponseId(super.getNcResponseId());
	}
}
