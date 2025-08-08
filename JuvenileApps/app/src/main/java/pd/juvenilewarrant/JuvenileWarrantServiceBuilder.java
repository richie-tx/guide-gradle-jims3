/*
 * Created on Jul 18, 2005
 *
 */
package pd.juvenilewarrant;

import messaging.juvenilewarrant.reply.JuvenileWarrantResponseEvent;
import pd.codetable.Code;
import mojo.pattern.IBuilder;

/**
 * @author jfisher
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class JuvenileWarrantServiceBuilder implements IBuilder
{
	private JuvenileWarrant warrant;
	JuvenileWarrantResponseEvent responseEvent;

	public JuvenileWarrantServiceBuilder(JuvenileWarrant warrant)
	{
		this.warrant = warrant;
	}

	/* (non-Javadoc)
	 * @see pd.pattern.IBuilder#build()
	 */
	public void build()
	{
		this.setSimpleValues();
		this.setWarrantCodeValues();
	}

	/**
	* 
	*/
	private void setSimpleValues()
	{
		IBuilder simpleBuilder = new JuvenileWarrantSimpleBuilder(warrant);
		simpleBuilder.build();
		this.responseEvent = (JuvenileWarrantResponseEvent) simpleBuilder.getResult();
	}

	/**
		* 
		*/
	private void setWarrantCodeValues()
	{
		Code code = null;
		if (warrant.getWarrantStatusId() != null && !warrant.getWarrantStatusId().equals(""))
		{
			code = warrant.getWarrantStatus();
			if (code != null)
			{
				this.responseEvent.setWarrantStatusId(code.getCode());
				this.responseEvent.setWarrantStatus(code.getDescription());
			}
		}
		if (warrant.getWarrantTypeId() != null && !warrant.getWarrantTypeId().equals(""))
		{
			code = warrant.getWarrantType();
			if (code != null)
			{
				this.responseEvent.setWarrantTypeId(code.getCode());
				this.responseEvent.setWarrantType(code.getDescription());
			}
		}
		if (warrant.getServiceReturnGeneratedStatusId() != null)
		{
			code = warrant.getServiceReturnGeneratedStatus();
			if (code != null)
			{
				this.responseEvent.setServiceReturnGeneratedStatusId(code.getCode());
				this.responseEvent.setServiceReturnGeneratedStatus(code.getDescription());
			}
		}
		if (warrant.getServiceReturnSignatureStatusId() != null)
		{
			code = warrant.getServiceReturnSignatureStatus();
			if (code != null)
			{
				this.responseEvent.setServiceReturnSignatureStatusId(code.getCode());
				this.responseEvent.setServiceReturnSignatureStatus(code.getDescription());
			}
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
