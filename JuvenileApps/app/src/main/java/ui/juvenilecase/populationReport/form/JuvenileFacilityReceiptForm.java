package ui.juvenilecase.populationReport.form;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import messaging.codetable.criminal.reply.JuvenileFacilityResponseEvent;
import messaging.juvenilecase.reply.JuvenileFacilityHistoricalReceiptsResponseEvent;

import org.apache.struts.action.ActionForm;

/**
 * @author rcarter
 * 
 */
public class JuvenileFacilityReceiptForm extends ActionForm
{
	private String juvenileNumber;
	private String juvenileName;
	private String juvenileRace;
	private String juvenileSex;
	private String juvenileDateOfBirth;	
	private String facilityId;
	private List<JuvenileFacilityHistoricalReceiptsResponseEvent> historicalReceipts;
	private int numReceipts;

    // values for search page
    private List facilities;

	public JuvenileFacilityReceiptForm()
	{

	}

	public void clear()
	{
		juvenileNumber="";
		juvenileName="";
		juvenileRace="";
		juvenileSex="";
		juvenileDateOfBirth="";
		facilityId = "";
		historicalReceipts = new ArrayList();
		numReceipts=0;
	}

	/**
	 * 
	 * @return
	 */
	public String getJuvenileNumber() {
		return juvenileNumber;
	}

	/**
	 * 
	 * @param juvenileNumber
	 */
	public void setJuvenileNumber(String juvenileNumber) {
		this.juvenileNumber = juvenileNumber;
	}

	/**
	 * 
	 * @return
	 */
	public String getJuvenileName() {
		return juvenileName;
	}

	/**
	 * 
	 * @param juvenileName
	 */
	public void setJuvenileName(String juvenileName) {
		this.juvenileName = juvenileName;
	}

	/**
	 * 
	 * @return
	 */
	public String getJuvenileRace() {
		return juvenileRace;
	}

	/**
	 * 
	 * @param juvenileRace
	 */
	public void setJuvenileRace(String juvenileRace) {
		this.juvenileRace = juvenileRace;
	}

	/**
	 * 
	 * @return
	 */
	public String getJuvenileSex() {
		return juvenileSex;
	}

	/**
	 * 
	 * @param juvenileSex
	 */
	public void setJuvenileSex(String juvenileSex) {
		this.juvenileSex = juvenileSex;
	}

	/**
	 * 
	 * @return
	 */
	public String getJuvenileDateOfBirth() {
		return juvenileDateOfBirth;
	}

	/**
	 * 
	 * @param juvenileDateOfBirth
	 */
	public void setJuvenileDateOfBirth(String juvenileDateOfBirth) {
		this.juvenileDateOfBirth = juvenileDateOfBirth;
	}

	/**
	 * @return the facilityId
	 */
	public String getFacilityId() {
		return facilityId;
	}

	/**
	 * @param facilityId the facilityId to set
	 */
	public void setFacilityId(String facilityId) {
		this.facilityId = facilityId;
	}

	/**
	 * @return the facilities
	 */
	public List getFacilities() {
		return facilities;
	}

	/**
	 * @param facilities the facilities to set
	 */
	public void setFacilities(List facilities) {
		this.facilities = facilities;
	}

	/**
	 * 
	 * @return
	 */
	public List<JuvenileFacilityHistoricalReceiptsResponseEvent> getHistoricalReceipts() {
		return historicalReceipts;
	}

	/**
	 * 
	 * @param historicalReceipts
	 */
	public void setHistoricalReceipts(
			List<JuvenileFacilityHistoricalReceiptsResponseEvent> historicalReceipts) {
		this.historicalReceipts = historicalReceipts;
	}

	/**
	 * 
	 * @return
	 */
	public int getNumReceipts() {
		return numReceipts;
	}

	/**
	 * 
	 * @param numReceipts
	 */
	public void setNumReceipts(int numReceipts) {
		this.numReceipts = numReceipts;
	}
	
	
	
	
	
}
