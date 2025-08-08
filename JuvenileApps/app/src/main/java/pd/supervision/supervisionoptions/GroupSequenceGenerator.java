package pd.supervision.supervisionoptions;

import java.util.Iterator;

import messaging.supervisionoptions.BasicGenerateNameEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

/**
* @roseuid 43037645001F
*/
public class GroupSequenceGenerator extends PersistentObject {
	private String agencyId;
	private String groupId;
	private String type;
	private int sequenceNum;



	/**
	 * PRECONDITION:
	 * 
	 * 	Properties agentId, groupId, and type must be set with correct values.
	 * 
	* @return Iterator Condition
	* @param event
	*/
	static public GroupSequenceGenerator find( IEvent event )
	{
		IHome home = new Home();
		Iterator iter = home.findAll( new BasicGenerateNameEvent((BasicGenerateNameEvent)event), GroupSequenceGenerator.class);
		if ( iter.hasNext() )
		{
			// Look for an existing generator.
			return (GroupSequenceGenerator)iter.next(); 
		}
		else
		{
			// Create a new generator for this Agency/Group/Type.
			BasicGenerateNameEvent genEvt = (BasicGenerateNameEvent)event; 
			GroupSequenceGenerator gen = new GroupSequenceGenerator();
			
			gen.agencyId = genEvt.getAgencyId();
			gen.groupId = genEvt.getGroupId();
			gen.type = genEvt.getType();
			gen.sequenceNum = 1;
			gen.markModified();
			
			return gen;
		}
	}

	/**
	* @roseuid 43037645001F
	*/
	public GroupSequenceGenerator() {
	}
	
	/**
	 * 
	 */
	public int nextSequence()
	{
		fetch();
		int tmp = sequenceNum;
		sequenceNum++;
		markModified();
		return tmp;		
	}

	/**
	 * 
	 */
	public String nextSequenceAsString( int maxPaddedLength )
	{
		int seq = nextSequence();
		String str = Integer.toString(seq);
		StringBuffer sb = new StringBuffer();
		while (sb.length() + str.length() < maxPaddedLength )
		{
			sb.append("0");
		}
		sb.append(str);
		return sb.toString();		
	}

	public int getSequenceNum()
	{
		fetch();
		return sequenceNum;
	}

	public void setSequenceNum( int aSeq )
	{
		sequenceNum = aSeq;
		markModified();
	}

	/**
	 * 
	 */
	public void setAgencyId( String anId ) {
		agencyId = anId;
		markModified();
	}
	/**
	* Access method for the agencyId property.
	* @return the current value of the agencyId property
	*/
	public String getAgencyId() {
		fetch();
		return agencyId;
	}
	/**
	 * 
	 */
	public void setGroupId( String anId ) {
		groupId = anId;
		markModified();
	}
	/**
	* Access method for the groupId property.
	* @return the current value of the groupId property
	*/
	public String getGroupId() {
		fetch();
		return groupId;
	}
	/**
	 * 
	 */
	public void setType( String aType ) {
		type = aType;
		markModified();
	}
	/**
	* Access method for the type property.
	* @return the current value of the type property
	*/
	public String getType() {
		fetch();
		return type;
	}
	
	
	/**
	* @return Iterator Condition
	* @param event
	*/
	static public Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		return home.findAll(event, GroupSequenceGenerator.class);
	}

	/**
	* @param oid
	* @roseuid 42F79A3902DE
	*/
	static public GroupSequenceGenerator find(String oid) 
	{
		IHome home = new Home();
		return (GroupSequenceGenerator)home.find( oid, GroupSequenceGenerator.class );
	}
	
}
