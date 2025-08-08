package ui.juvenilecase.programreferral;

import java.util.HashMap;
import java.util.List;

import messaging.juvenilewarrant.reply.PetitionResponseEvent;

public class ExhibitBPrintBean
{
    String juvenileName;
    String courtId;
    List<PetitionResponseEvent> petitions;
    String listServices;
    String listAndExplain;
    String childRemoval;
    HashMap<String, Integer> programs;
    HashMap<String, Integer> services;
    String selectedPetitions;
    String selectedPrograms;
    String selectedServices;
    
    public String getJuvenileName()
    {
        return juvenileName;
    }
    public void setJuvenileName(String juvenileName)
    {
        this.juvenileName = juvenileName;
    }
    public String getCourtId()
    {
        return courtId;
    }
    public void setCourtId(String courtId)
    {
        this.courtId = courtId;
    }
    public List<PetitionResponseEvent> getPetitions()
    {
        return petitions;
    }
    public void setPetitions(List<PetitionResponseEvent> petitions)
    {
        this.petitions = petitions;
    }
    public String getListServices()
    {
        return listServices;
    }
    public void setListServices(String listServices)
    {
        this.listServices = listServices;
    }
    public String getListAndExplain()
    {
        return listAndExplain;
    }
    public void setListAndExplain(String listAndExplain)
    {
        this.listAndExplain = listAndExplain;
    }
    public String getChildRemoval()
    {
        return childRemoval;
    }
    public void setChildRemoval(String childRemoval)
    {
        this.childRemoval = childRemoval;
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
    public String getSelectedPrograms()
    {
        return selectedPrograms;
    }
    public void setSelectedPrograms(String selectedPrograms)
    {
        this.selectedPrograms = selectedPrograms;
    }
    public String getSelectedServices()
    {
        return selectedServices;
    }
    public void setSelectedServices(String selectedServices)
    {
        this.selectedServices = selectedServices;
    }
    public String getSelectedPetitions()
    {
        return selectedPetitions;
    }
    public void setSelectedPetitions(String selectedPetitions)
    {
        this.selectedPetitions = selectedPetitions;
    }
    
    
   
    
    
    
    

}
