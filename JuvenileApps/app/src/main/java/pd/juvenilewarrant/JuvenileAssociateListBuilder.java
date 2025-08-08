/*
 * Created on Jul 21, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pd.juvenilewarrant;

import naming.PDJuvenileWarrantConstants;
import messaging.juvenilewarrant.reply.JuvenileAssociateResponseEvent;
import pd.codetable.Code;
import mojo.pattern.IBuilder;

/**
 * @author dgibler
 *
 */
public class JuvenileAssociateListBuilder implements IBuilder
{
	private JuvenileAssociateResponseEvent responseEvent;
	private JuvenileAssociate associate;

	/**
	 * 
	 */
	public JuvenileAssociateListBuilder(JuvenileAssociate associate)
	{
		this.associate = associate;
		this.responseEvent = new JuvenileAssociateResponseEvent();
	}

	/* (non-Javadoc)
	 * @see pd.pattern.IBuilder#build()
	 */
	public void build()
	{
		this.responseEvent.setTopic(PDJuvenileWarrantConstants.JUVENILE_ASSOCIATE_EVENT_TOPIC);
		this.responseEvent.setAssociateNum(associate.getAssociateNum());
		this.responseEvent.setFirstName(associate.getFirstName());
		this.responseEvent.setLastName(associate.getLastName());
		this.responseEvent.setMiddleName(associate.getMiddleName());
		Code code = associate.getRelationshipToJuvenile();
		if (code != null){
			this.responseEvent.setRelationshipToJuvenileId(code.getCodeId());
			this.responseEvent.setRelationshipToJuvenile(code.getDescription());
		}
	}

	/* (non-Javadoc)
	 * @see pd.pattern.IBuilder#getResult()
	 */
	public Object getResult()
	{
		return this.responseEvent;
	}

}
