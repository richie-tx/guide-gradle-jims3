/*
 * Created on Feb 2, 2006
 *
 */
package ui.juvenilecase.helper;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;

import naming.PDJuvenileCaseConstants;

import ui.juvenilecase.form.JuvenilePhysicalCharacteristicsForm;

import messaging.juvenile.reply.JuvenilePhysicalAttributesResponseEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import mojo.pattern.ui.IFormDirector;

/**
 * @author Jim Fisher
 */
public class JuvenilePhysicalCharacteristicsFormDirector implements IFormDirector
{
	private static final String	FORM_KEY	= "juvenilePhysicalCharacteristicsForm";
	private JuvenilePhysicalCharacteristicsForm	form;

	/*
	 * 
	 */
	public JuvenilePhysicalCharacteristicsFormDirector(HttpServletRequest aRequest)
	{
		this.initFromSession(aRequest);
	}

	/*
	 * 
	 */
	public JuvenilePhysicalCharacteristicsFormDirector(JuvenilePhysicalCharacteristicsForm form)
	{
		this.form = form;
	}

	public ActionForm getForm()
	{
		return this.form;
	}

	/*
	 * (non-Javadoc)
	 * @seemojo.pattern.IFormDirector#initFromSession(javax.servlet.http.
	 * HttpServletRequest)
	 */
	public void initFromSession(HttpServletRequest aRequest)
	{
		this.form = (JuvenilePhysicalCharacteristicsForm) aRequest.getSession().getAttribute(FORM_KEY);
		if (this.form == null)
		{
			this.form = new JuvenilePhysicalCharacteristicsForm();
			aRequest.getSession().setAttribute(FORM_KEY, this.form);
		}

	}

	/**
	 * @param juvenileNum
	 */
	public void setJuvenileNum(String juvenileNum)
	{
		this.form.setJuvenileNum(juvenileNum);
	}

	/**
	 * @param replyEvent
	 */
	public void setPhysicalCharacteristics(CompositeResponse replyEvent)
	{
		List<JuvenilePhysicalAttributesResponseEvent> physicalChars = MessageUtil.compositeToList(replyEvent, PDJuvenileCaseConstants.JUVENILE_PHYSICAL_CHARACTERISTICS_TOPIC);

		if (physicalChars != null)
		{
			// sort list based on entryDate
			Collections.sort(physicalChars);

			// put collection in a map to locate when entrydate selection is changed
			Map map = new HashMap();
			int len = physicalChars.size();
			for( int i = 0; i < len; i++ )
			{
				JuvenilePhysicalAttributesResponseEvent phyAttRespEvent = physicalChars.get(i);
				map.put(phyAttRespEvent.getEntryDate(), phyAttRespEvent);
			}

			form.setPhysicalCharacteristics(physicalChars);
			form.setPhysicalCharacteristicsMap(map);
		}
	}
}
