package pd.juvenilecase.interviewinfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import mojo.km.persistence.Home;


/**
* @roseuid 448EC1ED011F
*/
public class InterviewEventView extends Interview
{
	
	private String attendanceStatusCd;
	private String juvenileId;
	private int taskCount;
	
	
	/**
	 *
	 */
	public static Interview find( String interviewId )
	{
		return (Interview)new Home().find( interviewId, InterviewEventView.class );
	}
	
	/**
	 *
	 */
	public static Iterator findAllForJuvenile( String juvenileId )
	{	
		return new Home().findAll( "juvenileId", juvenileId, InterviewEventView.class );
	}

	
	/**
	 *
	 */
	public static Iterator findAllForCasefile( String casefileId )
	{
		return new Home().findAll( "casefileId", casefileId, InterviewEventView.class );
	}

	/**
	 *
	 */
	public static Interview findLastForCasefile( String casefileId )
	{
		Iterator iter = new Home().findAll( "casefileId", casefileId, InterviewEventView.class );
		List list = new ArrayList();
		while ( iter.hasNext() )
		{
			list.add( iter.next() );
		}
		if ( list.size() > 0 )
		{
			Collections.sort( list );
			return (Interview)list.get( list.size()-1 );
		}
		return null;
	}
	
	public String getAttendanceStatusCd() {
		fetch();
		return attendanceStatusCd;
	}	
	
	public void setAttendanceStatusCd(String attendanceStatusCd) {
		if (this.attendanceStatusCd == null || !this.attendanceStatusCd.equals(attendanceStatusCd)) 
		{
			this.attendanceStatusCd = attendanceStatusCd;
		}
	}	
	
	public String getJuvenileId() {
		fetch();
		return juvenileId;
	}
	public void setJuvenileId(String juvenileId) {
		if (this.juvenileId == null || !this.juvenileId.equals(juvenileId)) 
		{
			this.juvenileId = juvenileId;
		}
	}
	
	public int getTaskCount() {
		fetch();
		return taskCount;
	}
	public void setTaskCount(int taskCount) {
		this.taskCount = taskCount;
	}
}
