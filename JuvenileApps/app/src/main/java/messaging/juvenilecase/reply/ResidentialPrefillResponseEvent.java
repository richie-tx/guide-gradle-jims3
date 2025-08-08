/*
 * Created on Nov 1, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.juvenilecase.reply;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import mojo.km.messaging.ResponseEvent;

/**
 * @author kmurthy
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ResidentialPrefillResponseEvent extends ResponseEvent
{
	private List juvTraitsDetails;
	private List rootTraitList = new ArrayList();
	private List childTraitList = new ArrayList();

	public ResidentialPrefillResponseEvent()
	{
		
		
	}
	
	/**
	 * @return
	 */
	public List getJuvTraitsDetails()
	{
		return juvTraitsDetails;
	}

	/**
	 * @param iterator
	 */
	public void setJuvTraitsDetails(final List iterator)
	{
		juvTraitsDetails = iterator;
	}

	/**
	 * @return
	 */
	public List getRootTraitList()
	{
		return rootTraitList;
	}

	/**
	 * @param list
	 */
	public void setRootTraitList(final TraitTypeResponseEvent list)
	{
		rootTraitList.add(list);
	}

	/**
	 * @return
	 */
	public List getChildTraitList()
	{
		return childTraitList;
	}

	/**
	 * @param list
	 */
	public void setChildTraitList(final List list)
	{
		childTraitList.addAll(list);
	}

}
