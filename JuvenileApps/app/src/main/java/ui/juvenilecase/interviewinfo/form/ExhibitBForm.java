package ui.juvenilecase.interviewinfo.form;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import messaging.juvenilewarrant.reply.PetitionResponseEvent;
import messaging.programreferral.reply.ProgramReferralResponseEvent;

import org.apache.struts.action.ActionForm;

public class ExhibitBForm extends ActionForm
{
    private String currentTab = "";
    private List<PetitionResponseEvent>petitions;
    private String selectedReferral;
    private List<ProgramReferralResponseEvent>programServices;
    private String firstCircQuestion;
    private String secondCircQuestion;
    private String thirdCircQuestion;
    private String juvenileNum;
    private String courtId;
    private List<String>selectedReferrals;
    private List<ProgramService> programServiceList;
    HashMap<String, Integer> programs;
    HashMap<String, Integer> services;
    

    public ExhibitBForm(){
	petitions = new ArrayList<PetitionResponseEvent>();
    }
    public String getCurrentTab()
    {
        return currentTab;
    }

    public List<PetitionResponseEvent> getPetitions()
    {
        return petitions;
    }

    public void setPetitions(List<PetitionResponseEvent> petitions)
    {
        this.petitions = petitions;
    }

    public void setCurrentTab(String currentTab)
    {
        this.currentTab = currentTab;
    }
    
    public String getSelectedReferral()
    {
        return selectedReferral;
    }
    public void setSelectedReferral(String selectedReferral)
    {
        this.selectedReferral = selectedReferral;
    }
    
    public List<ProgramReferralResponseEvent> getProgramServices()
    {
        return programServices;
    }
    public void setProgramServices(List<ProgramReferralResponseEvent> programServices)
    {
        this.programServices = programServices;
    }
    
    
    public String getFirstCircQuestion()
    {
        return firstCircQuestion;
    }
    public void setFirstCircQuestion(String firstCircQuestion)
    {
        this.firstCircQuestion = firstCircQuestion;
    }
    public String getSecondCircQuestion()
    {
        return secondCircQuestion;
    }
    public void setSecondCircQuestion(String secondCircQuestion)
    {
        this.secondCircQuestion = secondCircQuestion;
    }
    public String getThirdCircQuestion()
    {
        return thirdCircQuestion;
    }
    public void setThirdCircQuestion(String thirdCircQuestion)
    {
        this.thirdCircQuestion = thirdCircQuestion;
    }
    
    
    public String getJuvenileNum()
    {
        return juvenileNum;
    }
    public void setJuvenileNum(String juvenileNum)
    {
        this.juvenileNum = juvenileNum;
    }
    
    
    public String getCourtId()
    {
        return courtId;
    }
    public void setCourtId(String courtId)
    {
        this.courtId = courtId;
    }
    
    
    public List<String> getSelectedReferrals()
    {
        return selectedReferrals;
    }
    public void setSelectedReferrals(List<String> selectedReferrals)
    {
        this.selectedReferrals = selectedReferrals;
    }
    
    
    public HashMap<String, Integer> getPrograms()
    {
        return programs;
    }
    public void setPrograms(HashMap<String, Integer> programs)
    {
        this.programs = programs;
    }
    public HashMap<String, Integer> getServices()
    {
        return services;
    }
    public void setServices(HashMap<String, Integer> services)
    {
        this.services = services;
    }
    
    
    
    public List<ProgramService> getProgramServiceList()
    {
        return programServiceList;
    }
    public void setProgramServiceList(List<ProgramService> programServiceList)
    {
        this.programServiceList = programServiceList;
    }
    public void clearAllResults(){
	currentTab = "";
	juvenileNum = "";
	selectedReferral = "";
	firstCircQuestion = "";
	secondCircQuestion = "";
	thirdCircQuestion = "";
	programServices = new ArrayList<ProgramReferralResponseEvent>();
	petitions = new ArrayList<PetitionResponseEvent>();
	programServiceList = new ArrayList<>();
	selectedReferrals = new ArrayList<>();
	programs = new HashMap<>();
	services = new HashMap<>();
    }
    
    
}
