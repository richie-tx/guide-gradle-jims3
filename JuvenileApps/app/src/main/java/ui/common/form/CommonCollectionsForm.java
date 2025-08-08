/*
 * Created on Jun 25, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.common.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import javax.servlet.ServletRequest;
//import javax.servlet.http.HttpServletRequest;

import messaging.codetable.person.reply.ScarsMarksTattoosCodeResponseEvent;
import mojo.km.utilities.MessageUtil;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * @author ldeen
 *
 * This form contains all the attributes needed to 
 * create, update, and retrieve a juvenile warrant.
 */
public final class CommonCollectionsForm extends ActionForm
{
	private static CommonCollectionsForm thisInstance;

	/* Collections for address */
	private Collection states;
	private Collection streetTypes;
	private Collection addressTypes;

	/*code table collections */
	private Collection builds;
	private Collection cautions;
	private Collection complexions;
	private Collection courts;
	private Collection eyeColors;
	private Collection ethnicities;
	private Collection juvenileEthnicities;
	private Collection hairColors;
	private Collection races;
	private Collection scarsAndMarks;
	private Collection sexes;
	private Collection tattoos;

	/**
	 * @return CommonCollectionsForm
	 */
	public static CommonCollectionsForm getInstance()
	{
		if (thisInstance == null)
		{
			thisInstance = new CommonCollectionsForm();
		}
		return thisInstance;
	}

	/**
	 * 
	 */
	private CommonCollectionsForm()
	{
		states = new ArrayList();
		streetTypes = new ArrayList();
		addressTypes = new ArrayList();
		builds = new ArrayList();
		cautions = new ArrayList();
		complexions = new ArrayList();
		courts = new ArrayList();
		eyeColors = new ArrayList();
		ethnicities = new ArrayList();
		hairColors = new ArrayList();
		races = new ArrayList();
		scarsAndMarks = new ArrayList();
		sexes = new ArrayList();
		tattoos = new ArrayList();
		juvenileEthnicities= new ArrayList();
	}

	/**
	 * 
	 */
	public void clear()
	{
		states.clear();
		streetTypes.clear();
		addressTypes.clear();
		builds.clear();
		cautions.clear();
		complexions.clear();
		courts.clear();
		eyeColors.clear();
		ethnicities.clear();
		hairColors.clear();
		races.clear();
		scarsAndMarks.clear();
		sexes.clear();
		tattoos.clear();
		juvenileEthnicities.clear();
	}

	/**
	 * @see ActionForm#reset(ActionMapping, ServletRequest)
	 */
	public void reset(ActionMapping aMapping, ServletRequest aRequest)
	{
		super.reset(aMapping, aRequest);
		clear();
	}

	/**
	 * @return Collection
	 */
	public Collection getBuilds()
	{
		builds = MessageUtil.processEmptyCollection(builds);
		Collections.sort((ArrayList) builds);
		return builds;
	}

	/**
	 * @return Collection
	 */
	public Collection getCautions()
	{
		cautions = MessageUtil.processEmptyCollection(cautions);
		Collections.sort((ArrayList) cautions);
		return cautions;
	}

	/**
	 * @return Collection
	 */
	public Collection getComplexions()
	{
		complexions = MessageUtil.processEmptyCollection(complexions);
		Collections.sort((ArrayList) complexions);
		return complexions;
	}

	/**
	 * @return Collection
	 */
	public Collection getCourts()
	{
		courts = MessageUtil.processEmptyCollection(courts);
		Collections.sort((ArrayList) courts);
		return courts;
	}

	/**
	 * @return Collection
	 */
	public Collection getEyeColors()
	{
		eyeColors = MessageUtil.processEmptyCollection(eyeColors);
		Collections.sort((ArrayList) eyeColors);
		return eyeColors;
	}

	/**
	 * @return Collection
	 */
	public Collection getHairColors()
	{
		hairColors = MessageUtil.processEmptyCollection(hairColors);
		Collections.sort((ArrayList) hairColors);
		return hairColors;
	}

	/**
	 * @return Collection
	 */
	public Collection getRaces()
	{
		races = MessageUtil.processEmptyCollection(races);
		Collections.sort((ArrayList) races);
		return races;
	}

	/**
	 * @return Collection
	 */
	public Collection getScarsAndMarks()
	{
		scarsAndMarks = MessageUtil.processEmptyCollection(scarsAndMarks);
		Collections.sort((ArrayList) scarsAndMarks);
		return scarsAndMarks;
	}

	/**
	 * @return Collection
	 */
	public Collection getSexes()
	{
		sexes = MessageUtil.processEmptyCollection(sexes);
		Collections.sort((ArrayList) sexes);
		return sexes;
	}

	/**
	 * @return Collection
	 */
	public Collection getTattoos()
	{
		tattoos = MessageUtil.processEmptyCollection(tattoos);
		Collections.sort((ArrayList) tattoos);
		return tattoos;
	}

	/**
	 * @param collection
	 */
	public void setBuilds(Collection collection)
	{
		builds = collection;
	}

	/**
	 * @param collection
	 */
	public void setCautions(Collection collection)
	{
		cautions = collection;
	}

	/**
	 * @param collection
	 */
	public void setComplexions(Collection collection)
	{
		complexions = collection;
	}

	/**
	 * @param collection
	 */
	public void setCourts(Collection collection)
	{
		courts = collection;
	}

	/**
	 * @param collection
	 */
	public void setEyeColors(Collection collection)
	{
		eyeColors = collection;
	}

	/**
	 * @param collection
	 */
	public void setHairColors(Collection collection)
	{
		hairColors = collection;
	}

	/**
	 * @param collection
	 */
	public void setRaces(Collection collection)
	{
		races = collection;
	}

	/**
	 * @param collection
	 */
	public void setScarsAndMarks(Collection collection)
	{
		scarsAndMarks = collection;
	}

	/**
	 * @param collection
	 */
	public void setSexes(Collection collection)
	{
		sexes = collection;
	}

	/**
	 * @param collection
	 */
	public void setTattoos(Collection collection)
	{
		tattoos = collection;
	}

	/**
	 * @return Collection
	 */
	public Collection getAddressTypes()
	{
		addressTypes = MessageUtil.processEmptyCollection(addressTypes);
		Collections.sort((ArrayList) addressTypes);
		return addressTypes;
	}

	/**
	 * @return Collection
	 */
	public Collection getStates()
	{
		states = MessageUtil.processEmptyCollection(states);
		Collections.sort((ArrayList) states);
		return states;
	}

	/**
	 * @return Collection
	 */
	public Collection getStreetTypes()
	{
		streetTypes = MessageUtil.processEmptyCollection(streetTypes);
		Collections.sort((ArrayList) streetTypes);
		return streetTypes;
	}

	/**
	 * @param collection
	 */
	public void setAddressTypes(Collection collection)
	{
		addressTypes = collection;
	}

	/**
	 * @param collection
	 */
	public void setStates(Collection collection)
	{
		states = collection;
	}

	/**
	 * @param collection
	 */
	public void setStreetTypes(Collection collection)
	{
		streetTypes = collection;
	}

	/**
	 * @return String
	 */
	public Collection getEthnicities()
	{
		Collections.sort((ArrayList) ethnicities);
		return ethnicities;
	}

	/**
	 * @param collection
	 */
	public void setEthnicities(Collection collection)
	{
		ethnicities = collection;
	}
	
	/**
	 * @return String
	 */
	public Collection getJuvenileEthnicities()
	{
		Collections.sort((ArrayList) juvenileEthnicities);
		return juvenileEthnicities;
	}

	/**
	 * @param collection
	 */
	public void setJuvenileEthnicities(Collection collection)
	{
		juvenileEthnicities = collection;
	}
	
	//	DPA: Added to get description from scarsmarkstattoo event
	/**
	 * @param id The id to be matched
	 * @param scars If true, the scars collection is searched, else tattoos collection is searched.
	 * @return String
	 */
	public String getScarsTattooDescription(String id, boolean scars)
	{
		Iterator codesIterator = null;
		if (scars)
		{
			codesIterator = scarsAndMarks.iterator();
		}
		else
		{
			codesIterator = tattoos.iterator();
		}

		while (codesIterator.hasNext())
		{
			ScarsMarksTattoosCodeResponseEvent codeEvt =
				(ScarsMarksTattoosCodeResponseEvent) codesIterator.next();
			if (codeEvt.getCode().equals(id))
			{
				return codeEvt.getDescription();
			}
		}
		return "";
	}

}
