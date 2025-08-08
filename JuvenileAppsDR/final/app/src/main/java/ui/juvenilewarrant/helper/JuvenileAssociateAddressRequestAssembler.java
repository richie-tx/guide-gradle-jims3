/*
 * Created on Sep 19, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.juvenilewarrant.helper;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import ui.common.CodeHelper;

import messaging.juvenilewarrant.reply.JuvenileAssociateAddressResponseEvent;

/**
 * @author cshimek
 *
 */
public class JuvenileAssociateAddressRequestAssembler
{
	private HttpServletRequest request;
	private String propertyName;
	private Object result;
	private Collection addresses;
	private boolean single;

	public JuvenileAssociateAddressRequestAssembler(
		HttpServletRequest aRequest,
		String propertyName,
		Collection addresses,
		boolean single)
	{
		this.request = aRequest;
		this.propertyName = propertyName;
		this.addresses = addresses;
		this.single = single;
	}

	public void assemble()
	{
		if (addresses != null)
		{
			Iterator i = addresses.iterator();

			int j = 0;

			while (i.hasNext())
			{
				JuvenileAssociateAddressResponseEvent evt = (JuvenileAssociateAddressResponseEvent) i.next();
				evt.setStreetNum(this.getParmValue(propertyName, "streetNum", j));
				evt.setStreetName(this.getParmValue(propertyName, "streetName", j));
				evt.setStreetTypeId(this.getParmValue(propertyName, "streetTypeId", j));
				evt.setAptNum(this.getParmValue(propertyName, "aptNum", j));
				evt.setCity(this.getParmValue(propertyName, "city", j));
				evt.setStateId(this.getParmValue(propertyName, "stateId", j));
				evt.setZipCode(this.getParmValue(propertyName, "zipCode", j));
				evt.setAdditionalZipCode(this.getParmValue(propertyName, "additionalZipCode", j));
				evt.setAddressTypeId(this.getParmValue(propertyName, "addressTypeId", j));

				// TODO Use code constant for "(U)NPROCESSED" 				
				if (evt.getAddressStatus() == null)
				{
					evt.setAddressStatus("U");
				}

				if ("".equals(evt.getStreetTypeId()) == false)
				{
					String streeType =
						CodeHelper.getCodeDescriptionByCode(
							CodeHelper.getStreetTypeCodes(false),
							evt.getStreetTypeId());
					evt.setStreetType(streeType);
				}else {
					evt.setStreetType("");
				}


				evt.setCountyId(this.getParmValue(propertyName, "countyId", j));
				if ("".equals(evt.getCountyId()) == false)
				{
					String county =
						CodeHelper.getCodeDescriptionByCode(CodeHelper.getCountyCodes(false), evt.getCountyId());
					evt.setCounty(county);
				}else {
					evt.setCounty("");
				}

				if ("".equals(evt.getStateId()) == false)
				{
					String state =
						CodeHelper.getCodeDescriptionByCode(CodeHelper.getStateCodes(false), evt.getStateId());
					evt.setState(state);
				}

				if ("".equals(evt.getAddressTypeId()) == false)
				{
					String addressType =
						CodeHelper.getCodeDescriptionByCode(
							CodeHelper.getAddressTypeCodes(false),
							evt.getAddressTypeId());
					evt.setAddressType(addressType);
				}else {
					evt.setAddressType("");
				}

				j++;
			}
		}
	}

	private String getParmValue(String propertyName, String parm, int index)
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append(propertyName);
		buffer.append("[");
		buffer.append(index);
		buffer.append("].");
		buffer.append(parm);
		String value = request.getParameter(buffer.toString());
		return value;
	}

	public Object getResult()
	{
		return this.addresses;
	}
}
