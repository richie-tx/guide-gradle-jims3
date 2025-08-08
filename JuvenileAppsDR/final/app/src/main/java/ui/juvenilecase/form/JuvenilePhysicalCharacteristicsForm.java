/*
 * Created on Jun 24, 2005
 *
 */
package ui.juvenilecase.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import messaging.codetable.reply.CodeResponseEvent;
import messaging.codetable.reply.ICode;
import messaging.juvenile.reply.JuvenilePhysicalAttributesResponseEvent;
import naming.PDCodeTableConstants;

import org.apache.commons.collections.FastArrayList;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import ui.common.CodeHelper;
import ui.common.UIUtil;
import ui.contact.party.helper.UIPartyHelper;
import ui.supervision.programReferral.ReferralTypeBean;

/**
 * @author asrvastava
 *  
 */
public class JuvenilePhysicalCharacteristicsForm extends ActionForm
{
    private String action;

    private String build;

    private String buildId;

    private boolean changeSelection;

    private String entryDate;

    private String eyeColor;

    private String eyeColorId;

    private String hairColor;

    private String hairColorId;

    private boolean heightEstimated;

    private String heightFeet;

    private String heightInch;

    private String juvenileNum;

    private String otherTattooComments;

    private List physicalCharacteristics = new ArrayList();

    private Map physicalCharacteristicsMap = new HashMap();

    private JuvenilePhysicalAttributesResponseEvent selectedBean;

    private String[] selectedTattoos;

    private String weight;

    private boolean weightEstimated;
    
    private String allTatoosDesc = "";
    
    private String allOtherTattooComments = "";
    
    private String allScars = "";
    
    private Collection allTatoosDescWithCode;
    
    private Collection allOtherTattooCommentsWithCode;
    
    private Collection allScarsWithCode;
    
    private Collection scarsAndMarksTypes = new FastArrayList();
    
    private Collection tattoosTypes = new FastArrayList();
    
    private String newOtherTattooComments;
    
    private String[] selectedTattoosArray;
    
    private String[] selectedScarsArray;

    private List juvenileAliasList = null; 
    
    private String displayTattoos = "display";
    
    private String aliasType = null;
    
    private String status;
    
    private String fromFacility; //for US 38802

    public void clear()
    {
        heightInch = "";
        weight = "";
        hairColor = "";
        heightFeet = "";
        entryDate = "";
        build = "";
        eyeColor = "";        
        buildId = "";
        eyeColorId = "";
        hairColorId = "";
        action = "";
        otherTattooComments = "";
        //Added for BUG REPORT #29003
        newOtherTattooComments = "";
        heightEstimated = false;
        weightEstimated = false;
        status = "";
    }

    
    
    /* (non-Javadoc)
	 * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.ServletRequest)
	 */
//	public void reset(ActionMapping mapping, javax.servlet.http.HttpServletRequest request)
//    {
//		String resetCheckboxes = request.getParameter("scarCheckBoxes");
//		if((resetCheckboxes != null) && (resetCheckboxes.trim().equalsIgnoreCase("true")))
//		{
//			if(scarsAndMarksTypes!=null)
//			{
//				for(int loopX=0;loopX<scarsAndMarksTypes.size(); loopX++)
//				{
//					JuvenilePhysicalCharacteristicsHelperForm myBean=(JuvenilePhysicalCharacteristicsHelperForm)scarsAndMarksTypes.get(loopX);
//					myBean.setSelected(false);
//				}
//			}
//		}
//		
//		
//    }
    
    
    /**
     * @return
     */
    public String getAction()
    {
        return action;
    }

	public Collection getAliasType(){
		return CodeHelper.getCodes(PDCodeTableConstants.NAME_TYPES, true);
	}



	public void setAliasType(String aliasType) {
		this.aliasType = aliasType;
	}



	/**
     * @return
     */
    public String getBuild()
    {
        return build;
    }

    /**
     * @return
     */
    public String getBuildId()
    {
        return buildId;
    }

    public List getBuilds()
    {
        return CodeHelper.getBuildCodes();
    }

    /**
     * @return
     */
    public String getEntryDate()
    {
        return entryDate;
    }

    /**
     * get the sorted entry dates
     * 
     * @return
     */
    public List getEntryDates()
    {

        List entryDates = new ArrayList();
        int len = physicalCharacteristics.size();
        for (int i = 0; i < len; i++)
        {
            JuvenilePhysicalAttributesResponseEvent respEvent = (JuvenilePhysicalAttributesResponseEvent) physicalCharacteristics
                    .get(i);
            entryDates.add(respEvent.getEntryDate());
        }
        return entryDates;
    }

    /**
     * @return
     */
    public String getEyeColor()
    {
        return eyeColor;
    }

    /**
     * @return
     */
    public String getEyeColorId()
    {
        return eyeColorId;
    }

    public List getEyeColors()
    {
        return CodeHelper.getEyeColorCodes();
    }

    /**
     * @return
     */
    public String getHairColor()
    {
        return hairColor;
    }

    /**
     * @return
     */
    public String getHairColorId()
    {
        return hairColorId;
    }

    public List getHairColors()
    {
        return CodeHelper.getHairColorCodes();
    }

    /**
     * @return
     */
    public boolean getHeightEstimated()
    {
        return heightEstimated;
    }

    /**
     * @return
     */
    public String getHeightFeet()
    {
        return heightFeet;
    }

    /**
     * @return
     */
    public String getHeightInch()
    {
        return heightInch;
    }

    /**
     * @return
     */
    public String getJuvenileNum()
    {
        return juvenileNum;
    }

    /**
     * @return
     */
    public String getOtherTattooComments()
    {
        return otherTattooComments;
    }

    /**
     * @return
     */
    public List getPhysicalCharacteristics()
    {
        return physicalCharacteristics;
    }

    /**
     * @return
     */
    public Map getPhysicalCharacteristicsMap()
    {
        return physicalCharacteristicsMap;
    }

    /**
     * get the selected bean. This method is called from the form to display physical
     * attributes from the date selected in the dropdown box
     * 
     * @return
     */
    public JuvenilePhysicalAttributesResponseEvent getSelectedBean()
    {
        return selectedBean;
    }

    /**
     * @return
     */
    public String[] getSelectedTattoos()
    {
        return selectedTattoos;
    }

    /**
     * @return
     */
    public List getTattoos()
    {
        return CodeHelper.getTattoos(true);
    }

    /**
     * @return TattooDescription
     */
    public String getTattoosDescription()
    {

        String tattoosDescription = null;
        if (this.selectedTattoos != null && this.selectedTattoos.length > 0)
        {
            List selectedTattoos = CodeHelper.getSelectedCodeDescriptions(CodeHelper.getTattoos(false), this.selectedTattoos);

            tattoosDescription = UIUtil.separateByDelim(selectedTattoos, ", ");
        }
        return tattoosDescription;
    }

    /**
     * @return
     */
    public String getUpdatedSelection()
    {
        if (changeSelection == true)
        {
            // set the selected bean
            selectedBean = (JuvenilePhysicalAttributesResponseEvent) physicalCharacteristicsMap.get(entryDate);
            changeSelection = false;
        }
        else
        {
            // it is being called for the first time, get the first obeject from
            // the collection
            if (physicalCharacteristics.size() > 0)
            {
                selectedBean = (JuvenilePhysicalAttributesResponseEvent) physicalCharacteristics.get(0);
            }
        }

        return "";
    }

    /**
     * @return
     */
    public String getWeight()
    {
        return weight;
    }

    /**
     * @return
     */
    public boolean getWeightEstimated()
    {
        return weightEstimated;
    }

    /**
     * @return
     */
    public boolean isChangeSelection()
    {
        return changeSelection;
    }

    /**
     * Sets the descriptions of the dropdown codes to be displayed on the summary page.
     */
    public void processCodeDescriptions()
    {
        CodeResponseEvent evt;
        String desc;
        if (this.buildId != null && !this.buildId.equals(""))
        {
            desc = CodeHelper.getCodeDescription(PDCodeTableConstants.BUILD, this.buildId);
            setBuild(desc);
        }
        if (this.hairColorId != null && !this.hairColorId.equals(""))
        {
            desc = CodeHelper.getCodeDescription(PDCodeTableConstants.HAIR_COLOR, this.hairColorId);
            setHairColor(desc);
        }
        if (this.eyeColorId != null && !this.eyeColorId.equals(""))
        {
            desc = CodeHelper.getCodeDescription(PDCodeTableConstants.EYE_COLOR, this.eyeColorId);
            setEyeColor(desc);
        }
    }

    /**
     * @param string
     */
    public void setAction(String string)
    {
        action = string;
    }

    /**
     * @param string
     */
    public void setBuild(String string)
    {
        build = string;
    }

    /**
     * @param string
     */
    public void setBuildId(String string)
    {
        buildId = string;
    }

    /**
     * @param b
     */
    public void setChangeSelection(boolean b)
    {
        changeSelection = b;
    }

    /**
     * @param date
     */
    public void setEntryDate(String date)
    {
        entryDate = date;
    }

    /**
     * @param string
     */
    public void setEyeColor(String string)
    {
        eyeColor = string;
    }

    /**
     * @param string
     */
    public void setEyeColorId(String string)
    {
        eyeColorId = string;
    }

    /**
     * @param string
     */
    public void setHairColor(String string)
    {
        hairColor = string;
    }

    /**
     * @param string
     */
    public void setHairColorId(String string)
    {
        hairColorId = string;
    }

    /**
     * @param heightEst
     */
    public void setHeightEstimated(boolean heightEst)
    {
        if (heightEst)
            heightEstimated = true;
    }

    /**
     * @param string
     */
    public void setHeightFeet(String string)
    {
        heightFeet = string;
    }

    /**
     * @param string
     */
    public void setHeightInch(String string)
    {
        heightInch = string;
    }

    /**
     * @param string
     */
    public void setJuvenileNum(String string)
    {
        juvenileNum = string;
    }

    /**
     * @param string
     */
    public void setOtherTattooComments(String string)
    {
        otherTattooComments = string;
    }

    /**
     * @param collection
     */
    public void setPhysicalCharacteristics(List collection)
    {
        int len = collection.size();
        for (int i = 0; i < len; i++)
        {
            JuvenilePhysicalAttributesResponseEvent respEvent = (JuvenilePhysicalAttributesResponseEvent) collection.get(i);
            String desc;
            if (respEvent.getBuild() != null && !respEvent.getBuild().equals(""))
            {
                desc = CodeHelper.getCodeDescription(PDCodeTableConstants.BUILD, respEvent.getBuild());
                respEvent.setBuild(desc);
            }
            if (respEvent.getHairColor() != null && !respEvent.getHairColor().equals(""))
            {
                desc = CodeHelper.getCodeDescription(PDCodeTableConstants.HAIR_COLOR, respEvent.getHairColor());
                respEvent.setHairColor(desc);
            }
            if (respEvent.getEyeColor() != null && !respEvent.getEyeColor().equals(""))
            {
                desc = CodeHelper.getCodeDescription(PDCodeTableConstants.EYE_COLOR, respEvent.getEyeColor());
                respEvent.setEyeColor(desc);
            }
        }
        this.physicalCharacteristics = collection;
    }

    /**
     * @param map
     */
    public void setPhysicalCharacteristicsMap(Map map)
    {
        physicalCharacteristicsMap = map;
    }

    public void setProperties(JuvenilePhysicalAttributesResponseEvent responseEvent)
    {
        heightInch = responseEvent.getHeightInch();
        juvenileNum = responseEvent.getJuvenileNum();
        weight = responseEvent.getWeight();
        hairColor = responseEvent.getHairColor();
        heightFeet = responseEvent.getHeightFeet();
        build = responseEvent.getBuild();
        eyeColor = responseEvent.getEyeColor();
        entryDate = responseEvent.getEntryDateAsString();
    }

    /**
     * @param event
     */
    public void setSelectedBean(JuvenilePhysicalAttributesResponseEvent event)
    {
        selectedBean = event;
    }

    /**
     * @param strings
     */
    public void setSelectedTattoos(String[] strings)
    {
        selectedTattoos = strings;
    }

    /**
     * @param string
     */
    public void setWeight(String string)
    {
        weight = string;
    }

    /**
     * @param weightEst
     */
    public void setWeightEstimated(boolean weightEst)
    {
        if (weightEst)
        {
            weightEstimated = true;
        }
    }

	public String getAllTatoosDesc() {
		return allTatoosDesc;
	}

	public void setAllTatoosDesc(String allTatoosDesc) {
		this.allTatoosDesc = allTatoosDesc;
	}

	public String getAllOtherTattooComments() {
		if(allOtherTattooComments != null)
			return allOtherTattooComments;
		else 
			return "";
		
	}

	public void setAllOtherTattooComments(String allOtherTattooComments) {
		this.allOtherTattooComments = allOtherTattooComments;
	}

	public String getAllScars() {
		return allScars;
	}

	public void setAllScars(String allScars) {
		this.allScars = allScars;
	}

	public Collection getAllTatoosDescWithCode() {
		return allTatoosDescWithCode;
	}

	public void setAllTatoosDescWithCode(Collection allTatoosDescWithCode) {
		this.allTatoosDescWithCode = allTatoosDescWithCode;
	}

	public Collection getAllOtherTattooCommentsWithCode() {
		return allOtherTattooCommentsWithCode;
	}

	public void setAllOtherTattooCommentsWithCode(
			Collection allOtherTattooCommentsWithCode) {
		this.allOtherTattooCommentsWithCode = allOtherTattooCommentsWithCode;
	}

	public Collection getAllScarsWithCode() {
		return allScarsWithCode;
	}

	public void setAllScarsWithCode(Collection allScarsWithCode) {
		this.allScarsWithCode = allScarsWithCode;
	}
	
	
	/**
	 * 
	 * @return The scars and marks types.
	 */
	
	public Collection getTattoosTypes( )		{
		
		
		try {
		Iterator allcodes = CodeHelper.getTattoos( true ).iterator() ;
		tattoosTypes = new FastArrayList();
		while(allcodes.hasNext()){
			ICode code = (ICode)allcodes.next();
			String singleCode = code.getCode();
			JuvenilePhysicalCharacteristicsHelperForm helper = new JuvenilePhysicalCharacteristicsHelperForm();
			helper.setCode(code.getCode());
			helper.setDescriptions(code.getDescription());
			if(getAllTatoosDescWithCode() != null){
			Iterator selected = getAllTatoosDescWithCode().iterator();
			while(selected.hasNext()){
				String selectedCode = ((ICode) selected.next()).getCode(); 
				if(selectedCode.equals(singleCode)){				
					helper.setSelectedTattoos(true);
					break;
				}
			}
			}
			this.tattoosTypes.add(helper);
		}
		}catch(Exception e){
			e.printStackTrace();
		}

		return tattoosTypes ;
	}



	public Collection getScarsAndMarksTypes() { 
		
		try {
			Iterator allscars = CodeHelper.getScarMarks( true ).iterator() ;
			this.scarsAndMarksTypes = new FastArrayList();
			while(allscars.hasNext()){ 
				ICode code = (ICode)allscars.next();
				String singleCode = code.getCode();
				JuvenilePhysicalCharacteristicsHelperForm helper = new JuvenilePhysicalCharacteristicsHelperForm();
				helper.setCode(code.getCode());
				helper.setDescriptions(code.getDescription());		
				if(getAllScarsWithCode() != null){
				Iterator selected = getAllScarsWithCode().iterator();
				while(selected.hasNext()){
					String selectedCode = ((ICode) selected.next()).getCode(); 
					if(selectedCode.equals(singleCode)){				
						helper.setSelectedScars(true);						
						break;
					}
				}
				}
				this.scarsAndMarksTypes.add(helper);
			}
			}catch(Exception e){
				e.printStackTrace();
			}

		return scarsAndMarksTypes;
	}



	public void setScarsAndMarksTypes(Collection scarsAndMarksTypes) {
		this.scarsAndMarksTypes = scarsAndMarksTypes;
	}



	public String getNewOtherTattooComments() {
		return newOtherTattooComments;
	}



	public void setNewOtherTattooComments(String newOtherTattooComments) {
		this.newOtherTattooComments = newOtherTattooComments;
	}



	public String[] getSelectedTattoosArray() {
		return selectedTattoosArray;
	}



	public void setSelectedTattoosArray(String[] selectedTattoosArray) {
		this.selectedTattoosArray = selectedTattoosArray;
	}



	public String[] getSelectedScarsArray() {
		return selectedScarsArray;
	}



	public void setSelectedScarsArray(String[] selectedScarsArray) {
		this.selectedScarsArray = selectedScarsArray;
	}



	public void setTattoosTypes(Collection tattoosTypes) {
		this.tattoosTypes = tattoosTypes;
	}


    public List getJuvenileAliasList() {
		return juvenileAliasList;
	}

	public void setJuvenileAliasList(List juvenileAliasList) {
		this.juvenileAliasList = juvenileAliasList;
	}



//	public String getDisplayTattoos() {
//		return displayTattoos;
//	}
//
//
//
//	public void setDisplayTattoos(String displayTattoos) {
//		this.displayTattoos = displayTattoos;
//	}



	public String getDisplayTattoos() {
		if(((getAllScars() != null && !getAllScars().equals(""))) || (getAllTatoosDesc() != null && !getAllTatoosDesc().equals(""))){
			return "display";
		} else {
			return "";
		}		
	}
	
	public void setDisplayTattoos(String displayTattoos) {}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public String getFromFacility() {
		return fromFacility;
	}



	public void setFromFacility(String fromFacility) {
		this.fromFacility = fromFacility;
	}
	
}
