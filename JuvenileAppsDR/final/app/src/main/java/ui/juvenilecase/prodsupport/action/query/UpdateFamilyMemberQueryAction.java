package ui.juvenilecase.prodsupport.action.query;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.family.GetFamilyMembersAdvancedEvent;
import messaging.juvenile.GetDrugTestingInfoEvent;
import messaging.juvenile.reply.DrugTestingResponseEvent;
import messaging.juvenilecase.reply.FamilyMemberListResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileControllerServiceNames;
import naming.JuvenileFamilyControllerServiceNames;
import naming.PDCodeTableConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pd.juvenilecase.family.FamilyMember;

import ui.common.CodeHelper;
import ui.juvenilecase.form.ProdSupportForm;

public class UpdateFamilyMemberQueryAction extends Action
{
    public ActionForward execute(ActionMapping mapping, ActionForm form, 
	    				HttpServletRequest request, 
	    				HttpServletResponse response) throws Exception 
    {
	ProdSupportForm regform = (ProdSupportForm) form;
	String forward = "";
	 /** Check for initial load of this page **/
	String clrChk = request.getParameter("clr");
	if (clrChk != null && clrChk.equalsIgnoreCase("Y")) {
	    regform.clearAllResults();
	    regform.setMsg("");
	    return mapping.findForward("error");
	}      
        
        IHome home = new Home();
        FamilyMember familyMember = FamilyMember.find(regform.getFamilyMemberId());
        
        if(familyMember != null && familyMember.getDateOfBirth() != null)
        {
            familyMember.setDateOfBirthString(DateUtil.dateToString(familyMember.getDateOfBirth(), "MM/dd/yyyy"));
        }    
        
        regform.setFamilyMember(familyMember);
        regform.setFamilyMemberUpdate(familyMember);        
       
        Collection sexCodes = CodeHelper.getSexCodes(true);
        regform.setSexList(sexCodes);
        Collection juvRelationshipCodes = CodeHelper.getRelationshipsToJuvenileCodes(true); 
        regform.setRelationshipToJuvenileList(juvRelationshipCodes);
        Collection stateCodes = CodeHelper.getStateCodes(true);
        regform.setStateList(stateCodes);
        
        List yesnoList = new ArrayList();
        Answer answer1 = new Answer();
        answer1.description = "True";
        answer1.code = true;
        
        Answer answer2 = new Answer();
        answer2.description = "False";
        answer2.code = false;        
        
        yesnoList.add(answer1);
        yesnoList.add(answer2);
        
        regform.setIsIncarceratedList(yesnoList);       
           
        forward = "success";

	return mapping.findForward(forward);
	
    }
    
    public class Answer {
	    private boolean code;
	    private String description;
	    
	    public boolean getCode()
	    {
		return this.code;
	    }
	    
	    public void setCode(boolean code)
	    {
		this.code = code;
	    }
	    
	    public String getDescription()
	    {
		return this.description;
	    }
	    
	    public void setDescription(String description)
	    {
		this.description = description;
	    }
    }
}

