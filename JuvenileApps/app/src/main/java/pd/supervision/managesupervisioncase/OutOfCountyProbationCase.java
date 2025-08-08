//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\managesupervisioncase\\OutOfCountyProbationCase.java

package pd.supervision.managesupervisioncase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import pd.supervision.csts.CSTSHelper;

import messaging.domintf.managesupervisioncase.ISupervisionCase;
import messaging.managesupervisioncase.GetOOCCaseUpdateHistoryEvent;
import messaging.managesupervisioncase.reply.OOCCaseUpdateHistoryTO;
import messaging.managesupervisioncase.reply.ValidateResponseEvent;
import mojo.km.persistence.Home;
import mojo.km.utilities.DateUtil;
import naming.PDCodeTableConstants;

public class OutOfCountyProbationCase extends OutOfCountyCase
{
	private String personId;
	private Date sentenceDate;
	private Date arrestDate;
	private String cJISNum;
	private int confinementYears;
	private int confinementMonths;
	private int confinementDays;
	private int supervisionYears;
	private int supervisionMonths;
	private int supervisionDays;
	private Date pretrialInterventionBeginDate;
	private Date pretrialInterventionEndDate;
	private String stateId;
	private String dispositionTypeId;
	private Date dispositionDate;
	// used for case reactivation
	private String previousDispositionTypeId;
	private String reasonForUpdateId;

	/**
	 * @roseuid 444549AE0255
	 */
	public OutOfCountyProbationCase()
	{

	}

	/**
	 * Access method for the personId property.
	 * 
	 * @return   the current value of the personId property
	 */
	public String getPersonId()
	{
		fetch();
		return personId;
	}

	/**
	 * Sets the value of the personId property.
	 * 
	 * @param aPersonId the new value of the personId property
	 */
	public void setPersonId(String aPersonId)
	{
		if (this.personId == null || !this.personId.equals(aPersonId))
		{
			markModified();
		}
		personId = aPersonId;
	}

	/**
	 * Access method for the sentenceDate property.
	 * 
	 * @return   the current value of the sentenceDate property
	 */
	public Date getSentenceDate()
	{
		fetch();
		return sentenceDate;
	}

	/**
	 * Sets the value of the sentenceDate property.
	 * 
	 * @param aSentenceDate the new value of the sentenceDate property
	 */
	public void setSentenceDate(Date aSentenceDate)
	{
		if (this.sentenceDate == null || !this.sentenceDate.equals(aSentenceDate))
		{
			markModified();
		}
		sentenceDate = aSentenceDate;
	}

	/**
	 * Access method for the arrestDate property.
	 * 
	 * @return   the current value of the arrestDate property
	 */
	public Date getArrestDate()
	{
		fetch();
		return arrestDate;
	}

	/**
	 * Sets the value of the arrestDate property.
	 * 
	 * @param anArrestDate the new value of the arrestDate property
	 */
	public void setArrestDate(Date anArrestDate)
	{
		if (this.arrestDate == null || !this.arrestDate.equals(anArrestDate))
		{
			markModified();
		}
		arrestDate = anArrestDate;
	}

	/**
	 * Access method for the cJISNum property.
	 * 
	 * @return   the current value of the cJISNum property
	 */
	public String getCJISNum()
	{
		fetch();
		return cJISNum;
	}

	/**
	 * Sets the value of the cJISNum property.
	 * 
	 * @param aCJISNum the new value of the cJISNum property
	 */
	public void setCJISNum(String aCJISNum)
	{
		if (this.cJISNum == null || !this.cJISNum.equals(aCJISNum))
		{
			markModified();
		}
		cJISNum = aCJISNum;
	}

	/**
	 * Access method for the confinementYears property.
	 * 
	 * @return   the current value of the confinementYears property
	 */
	public int getConfinementYears()
	{
		fetch();
		return confinementYears;
	}

	/**
	 * Sets the value of the confinementYears property.
	 * 
	 * @param aConfinementYears the new value of the confinementYears property
	 */
	public void setConfinementYears(int aConfinementYears)
	{
		if (confinementYears != aConfinementYears)
		{
			markModified();
		}
		confinementYears = aConfinementYears;
	}

	/**
	 * Access method for the confinementMonths property.
	 * 
	 * @return   the current value of the confinementMonths property
	 */
	public int getConfinementMonths()
	{
		fetch();
		return confinementMonths;
	}

	/**
	 * Sets the value of the confinementMonths property.
	 * 
	 * @param aConfinementMonths the new value of the confinementMonths property
	 */
	public void setConfinementMonths(int aConfinementMonths)
	{
		if (confinementMonths != aConfinementMonths)
		{
			markModified();
		}
		confinementMonths = aConfinementMonths;
	}

	/**
	 * Access method for the confinementDays property.
	 * 
	 * @return   the current value of the confinementDays property
	 */
	public int getConfinementDays()
	{
		fetch();
		return confinementDays;
	}

	/**
	 * Sets the value of the confinementDays property.
	 * 
	 * @param aConfinementDays the new value of the confinementDays property
	 */
	public void setConfinementDays(int aConfinementDays)
	{
		if (confinementDays != aConfinementDays)
		{
			markModified();
		}
		confinementDays = aConfinementDays;
	}

	/**
	 * Access method used for the persistence of confinementLength. This is the 
	 * combination of confinementYears, confinementMonths, and confinementDays.
	 * 
	 * @return   the current value of the confinementLength property in the format: "nnnnnn" 
	 *			 where the first 2 numbers represent the confinementYears 
	 * 			 	   the next 2 numbers represent the confinementMonths
	 *				   and the last 2 numbers represent the confinementDays.
	 */
	public String getConfinementLength()
	{
		fetch();
		String temp = ""+confinementYears;
		if (temp.length() < 2)
		{
			temp = "0" + temp;
		}
		StringBuffer confinementLength = new StringBuffer(temp);
		temp = ""+confinementMonths;
		if (temp.length() < 2)
		{
			temp = "0" + temp;
		}
		confinementLength.append(temp);
		temp = ""+confinementDays;
		if (temp.length() < 2)
		{
			temp = "0" + temp;
		}
		confinementLength.append(temp);
		return confinementLength.toString();
	}

	/**
	 * Used by persistence mechanism.  Sets the values of the confinementYears, confinementMonths, and confinementDays properties.
	 * 
	 * @param aConfinementLength the value of the confinementLength in the format: "nnnnnn" 
	 *			 where the first 2 numbers represent the confinementYears 
	 * 			 	   the next 2 numbers represent the confinementMonths
	 *				   and the last 2 numbers represent the confinementDays.
	 */
	public void setConfinementLength(String aConfinementLength)
	{
		if (aConfinementLength != null && !aConfinementLength.equals(""))
		{
			confinementYears = new Integer(aConfinementLength.substring(0,2)).intValue();
			confinementMonths = new Integer(aConfinementLength.substring(2,4)).intValue();
			confinementDays = new Integer(aConfinementLength.substring(4)).intValue();
		}
	}

	/**
	 * Access method for the supervisionYears property.
	 * 
	 * @return   the current value of the supervisionYears property
	 */
	public int getSupervisionYears()
	{
		fetch();
		return supervisionYears;
	}

	/**
	 * Sets the value of the supervisionYears property.
	 * 
	 * @param aSupervisionYears the new value of the supervisionYears property
	 */
	public void setSupervisionYears(int aSupervisionYears)
	{
		if (supervisionYears != aSupervisionYears)
		{
			markModified();
		}
		supervisionYears = aSupervisionYears;
	}

	/**
	 * Access method for the supervisionMonths property.
	 * 
	 * @return   the current value of the supervisionMonths property
	 */
	public int getSupervisionMonths()
	{
		fetch();
		return supervisionMonths;
	}

	/**
	 * Sets the value of the supervisionMonths property.
	 * 
	 * @param aSupervisionMonths the new value of the supervisionMonths property
	 */
	public void setSupervisionMonths(int aSupervisionMonths)
	{
		if (supervisionMonths != aSupervisionMonths)
		{
			markModified();
		}
		supervisionMonths = aSupervisionMonths;
	}

	/**
	 * Access method for the supervisionDays property.
	 * 
	 * @return   the current value of the supervisionDays property
	 */
	public int getSupervisionDays()
	{
		fetch();
		return supervisionDays;
	}

	/**
	 * Sets the value of the supervisionDays property.
	 * 
	 * @param aSupervisionDays the new value of the supervisionDays property
	 */
	public void setSupervisionDays(int aSupervisionDays)
	{
		if (supervisionDays != aSupervisionDays)
		{
			markModified();
		}
		supervisionDays = aSupervisionDays;
	}

	/**
	 * Access method used for the persistence of confinementLength. This is the 
	 * combination of supervisionYears, supervisionMonths, and supervisionDays.
	 * 
	 * @return   the value of the supervisionLength property in the format: "nnnnnn" 
	 *			 where the first 2 numbers represent the supervisionYears 
	 * 			 	   the next 2 numbers represent the supervisionMonths
	 *				   and the last 2 numbers represent the supervisionDays.
	 */
	public String getSupervisionLength()
	{
		fetch();
		String temp = ""+supervisionYears;
		if (temp.length() < 2)
		{
			temp = "0" + temp;
		}
		StringBuffer supervisionLength = new StringBuffer(temp);
		temp = ""+supervisionMonths;
		if (temp.length() < 2)
		{
			temp = "0" + temp;
		}
		supervisionLength.append(temp);
		temp = ""+supervisionDays;
		if (temp.length() < 2)
		{
			temp = "0" + temp;
		}
		supervisionLength.append(temp);
		return supervisionLength.toString();
	}

	/**
	 * Used by persistence mechanism.  Sets the values of the supervisionYears, supervisionMonths, and supervisionDays properties.
	 * 
	 * @param aSupervisionLength the value of the supervisionYears property in the format: "nnnnnn" 
	 *			 where the first 2 numbers represent the supervisionYears 
	 * 			 	   the next 2 numbers represent the supervisionMonths
	 *				   and the last 2 numbers represent the supervisionDays.
	 */
	public void setSupervisionLength(String aSupervisionLength)
	{
		if (aSupervisionLength != null && !aSupervisionLength.equals(""))
		{
			supervisionYears = new Integer(aSupervisionLength.substring(0,2)).intValue();
			supervisionMonths = new Integer(aSupervisionLength.substring(2,4)).intValue();
			supervisionDays = new Integer(aSupervisionLength.substring(4)).intValue();
		}
	}

	/**
	 * Access method for the pretrialInterventionBeginDate property.
	 * 
	 * @return   the current value of the pretrialInterventionBeginDate property
	 */
	public Date getPretrialInterventionBeginDate()
	{
		fetch();
		return pretrialInterventionBeginDate;
	}

	/**
	 * Sets the value of the pretrialInterventionBeginDate property.
	 * 
	 * @param aPretrialInterventionBeginDate the new value of the pretrialInterventionBeginDate property
	 */
	public void setPretrialInterventionBeginDate(Date aPretrialInterventionBeginDate)
	{
		if (this.pretrialInterventionBeginDate == null || !this.pretrialInterventionBeginDate.equals(aPretrialInterventionBeginDate))
		{
			markModified();
		}
		pretrialInterventionBeginDate = aPretrialInterventionBeginDate;
	}

	/**
	 * Access method for the pretrialInterventionEndDate property.
	 * 
	 * @return   the current value of the pretrialInterventionEndDate property
	 */
	public Date getPretrialInterventionEndDate()
	{
		fetch();
		return pretrialInterventionEndDate;
	}

	/**
	 * Sets the value of the pretrialInterventionEndDate property.
	 * 
	 * @param aPretrialInterventionEndDate the new value of the pretrialInterventionEndDate property
	 */
	public void setPretrialInterventionEndDate(Date aPretrialInterventionEndDate)
	{
		if (this.pretrialInterventionEndDate == null || !this.pretrialInterventionEndDate.equals(aPretrialInterventionEndDate))
		{
			markModified();
		}
		pretrialInterventionEndDate = aPretrialInterventionEndDate;
	}

	/**
	 * Access method for the stateId property.
	 * 
	 * @return   the current value of the stateId property
	 */
	public String getStateId()
	{
		return stateId;
	}

	/**
	 * Sets the value of the stateId property.
	 * 
	 * @param aStateId the new value of the stateId property
	 */
	public void setStateId(String aStateId)
	{
		if (this.stateId == null || !this.stateId.equals(aStateId))
		{
			markModified();
		}
		stateId = aStateId;
	}

	/**
	 * Access method for the dispositionDate property.
	 * 
	 * @return   the current value of the dispositionDate property
	 */
	public Date getDispositionDate()
	{
		fetch();
		return dispositionDate;
	}

	/**
	 * Sets the value of the dispositionDate property.
	 * 
	 * @param aDispositionDate the new value of the dispositionDate property
	 */
	public void setDispositionDate(Date aDispositionDate)
	{
		if (this.dispositionDate == null || !this.dispositionDate.equals(aDispositionDate))
		{
			markModified();
		}
		dispositionDate = aDispositionDate;
	}

	/**
	 * Access method for the filingDate property.
	 * 
	 * @return   the current value of the filingDate property as a date.
	 */
	public Date getFilingDateAsDate()
	{
		//return DateUtil.convertYYYYMMDD(getFilingDate());
	    return DateUtil.stringToDate(getFilingDate(), "yyyyMMdd");
	}

	/**
	 * Sets the value of the dispositionDate property.
	 * 
	 * @param aDispositionDate the new value of the dispositionDate property
	 */
	public void setFilingDate(Date aFilingDate)
	{
		//setFilingDate(DateUtil.convertDateToYYYYMMDD(aFilingDate));
	    setFilingDate(DateUtil.dateToString(aFilingDate, "yyyyMMdd"));
	}

	/**
	 * Access method for the dispositionTypeId property.
	 * 
	 * @return   the current value of the dispositionTypeId property
	 */
	public String getDispositionTypeId()
	{
		fetch();
		return dispositionTypeId;
	}

	/**
	 * Sets the value of the dispositionTypeId property.
	 * 
	 * @param aDispositionTypeId the new value of the dispositionTypeId property
	 */
	public void setDispositionTypeId(String aDispositionTypeId)
	{
		if (this.dispositionTypeId == null || !this.dispositionTypeId.equals(aDispositionTypeId))
		{
			markModified();
		}
		dispositionTypeId = aDispositionTypeId;
	}

	/**
	 * @return Returns the reasonForUpdateId.
	 */
	public String getReasonForUpdateId()
	{
		fetch();
		return reasonForUpdateId;
	}
	
	/**
	 * @param aReasonForUpdateId The reasonForUpdateId to set.
	 */
	public void setReasonForUpdateId(String aReasonForUpdateId)
	{
		if (this.reasonForUpdateId == null || !this.reasonForUpdateId.equals(aReasonForUpdateId))
		{
			markModified();
		}
		this.reasonForUpdateId = aReasonForUpdateId;
	}
	

	/**
	 * Sets all values for creation or update of an OutOfCountyProbationCase.
	 * 
	 * Capturing data elements for electronic state reporting (CSTS) needs to be 
	 * performed when a post trial case is created.  This data is currently stored in 
	 * cics and m204 when a case is initiated (LP93 & LCPS).  This data is transferred 
	 * electonically through the JIMS Legacy system.
	 *
	 * @param oocCase
	 * @roseuid 44342BEC01D0
	 */
	public void updateOutOfCountyCase(ISupervisionCase oocCase)
	{
		boolean isUpdate = false;
		if (oocCase.isCreate()==false){
			isUpdate = true;
		}
		super.updateOutOfCountyCase(oocCase, isUpdate);

		if (this.valid)
		{
			setPersonId(oocCase.getPersonId());
			setSentenceDate(oocCase.getSentenceDate());
			setArrestDate(oocCase.getArrestDate());
			setCJISNum(oocCase.getCjisNum());
			setConfinementDays(oocCase.getConfinementDays());
			setConfinementMonths(oocCase.getConfinementMonths());
			setConfinementYears(oocCase.getConfinementYears());
			setSupervisionDays(oocCase.getSupervisionDays());
			setSupervisionMonths(oocCase.getSupervisionMonths());
			setSupervisionYears(oocCase.getSupervisionYears());
			setPretrialInterventionBeginDate(oocCase.getPretrialInterventionBeginDate());
			setPretrialInterventionEndDate(oocCase.getPretrialInterventionEndDate());
			setStateId(oocCase.getStateId());
			String disposition = oocCase.getDispositionTypeId();
			setDispositionTypeId(disposition);
			setDispositionDate(oocCase.getDispositionDate());
			// If Disposition Type = PTIN, set Instrument Type to PIN. 
			if (disposition.equals(PDCodeTableConstants.PRETRIAL_INTERVENTION))
			{
				setInstrumentTypeId(PDCodeTableConstants.CSCD_PTIN_INSTR_TYPE);
			}
			else
			// If Disposition Type = 'DADJ' or 'PROB',  then 
			//	   If Court = 'INM' or 'OTM', then set Instrument Type to MIN
			//	   If Court = 'INF' or 'OTF', then set Instrument Type to FIN
			//	   If Court = JP court, then set Instrument Type to MIN
			{
				String court = oocCase.getOutOfCountyCaseTypeId();
				if (court.equals(PDCodeTableConstants.IN_STATE_MISD) || court.equals(PDCodeTableConstants.OUT_OF_STATE_MISD) ||
					court.equals(PDCodeTableConstants.HARRIS_COUNTY_TREASURER_OFFICE_MISD) || court.equals(PDCodeTableConstants.CSCD_CSR_JP_CRTS_IN_LIEU_OF_MISD) ||
					court.startsWith("J"))
				{
					setInstrumentTypeId(PDCodeTableConstants.CSCD_MISD_INSTR_TYPE);
				}
				else
				if (court.equals(PDCodeTableConstants.IN_STATE_FELONY) || court.equals(PDCodeTableConstants.OUT_OF_STATE_FELONY))
				{
					setInstrumentTypeId(PDCodeTableConstants.CSCD_FELONY_INSTR_TYPE);
				}

			}

			// check for new or update. If creating, set Defendant status and Case status
			if (newCase(oocCase))
			{
				// Set Filing date to the SupervisionBeginDate.  If the Disposition Type is Pre-trial Intervention, use the 
				// PretrialInterventionBeginDate.
				if (disposition.equals(PDCodeTableConstants.PRETRIAL_INTERVENTION))
				{
					setFilingDate(oocCase.getPretrialInterventionBeginDate());
				}
				else
				{
					setFilingDate(oocCase.getSupervisionBeginDate());
				}
				// If creating, need to set the Defendant Status and Case Status
				setCaseAndDefendantStatus(disposition);
			}
			else
			{
				setReasonForUpdateId(oocCase.getReasonForUpdateId());
				setCaseAndDefendantStatus(disposition);
			}
		}
	}

	/**
	 *  Responsible for applying all validation rules for creating or updating an OutOfCountyProbationCase.
	 * 
	 * - If updating:
	 * All data elements can be modified except CDI and Case#.
	 * 
	 * DispositionDate is only presented for update when the DispositionType has 
	 * changed.  The default in this case is blank.
	 * 
	 * @param oocCase
	 * @return boolean - indicates whether valid or not
	 * @roseuid 44342BEC01D1
	 */
	public boolean validateForUpdate(ISupervisionCase oocCase)
	{
		this.valid = true;
		// Either Original Jurisdictation State or Original Jurisdictation Texas County
		// is required.  Both cannot be entered.
		String state = oocCase.getStateId();
		String county = oocCase.getCountyId();
		if (state != null && !state.equals(""))
		{
			if (county != null && !county.equals(""))
				{
					postFailedValidation("Both Original Jurisdictation State and Original Jurisdictation Texas County cannot be entered.");
					this.valid = false;
				}
		}
		else // state was not entered, so county is required
		{
			if (county == null || county.equals(""))
				{
					postFailedValidation("Either Original Jurisdictation State or Original Jurisdictation Texas County is required.");
					this.valid = false;
				}
		}
		
		String dispositionTypeId = oocCase.getDispositionTypeId();
		int confinementDays = oocCase.getConfinementDays();
		int confinementMonths = oocCase.getConfinementMonths();
		int confinementYears = oocCase.getConfinementYears();

		// Confinement Length is required when DispositionType = Probation.
		if (dispositionTypeId.equalsIgnoreCase(PDCodeTableConstants.STRAIGHT_PROBATION))
		{
			if (confinementDays == 0 && confinementMonths == 0 &&
				confinementYears == 0)
			{
				ValidateResponseEvent vre = new ValidateResponseEvent("Confinement Length is required when DispositionType = Probation.");
				this.valid = false;
			}
		}
		else
		{
			// Confinement Length cannot be entered when DispositionType = Pretrial 
			// Intervention or Deferred.
			confinementDays = 0;
			confinementMonths = 0;
			confinementYears = 0;
		}

		// The pre-trial intervention begin and end dates are required when the disposition 
		// type is pre-trial intervention and cannot be entered for other disposition types.
		if (dispositionTypeId.equalsIgnoreCase(PDCodeTableConstants.PRETRIAL_INTERVENTION))
		{
			if (oocCase.getPretrialInterventionBeginDate() == null ||
				oocCase.getPretrialInterventionEndDate() == null)
			{
				postFailedValidation("The pre-trial intervention begin and end dates are required when the disposition type is Pre-trial Intervention.");
				this.valid = false;
			}
			// The supervision begin and end dates cannot be entered when the disposition type is Pre-trial Intervention.
			setSupervisionBeginDate(null);
			setSupervisionEndDate(null);
		}
		else // disposition type is either Deferred or Straight Probation
		{
			// The pre-trial intervention begin and end dates cannot be entered for disposition types other than Pre-trial Intervention.
			setPretrialInterventionBeginDate(null);
			setPretrialInterventionEndDate(null);

			// The supervision begin and end dates are required when the disposition type is 
			// either Deferred or Straight Probation and cannot be entered for other 
			// disposition types.
			if (oocCase.getSupervisionBeginDate() == null ||
				oocCase.getSupervisionEndDate() == null)
			{
				postFailedValidation("The supervision begin and end dates are required when the disposition type is either Deferred or Straight Probation.");
				this.valid = false;
			}

			// Supervision End date cannot be greater than begin date
			if (oocCase.getSupervisionEndDate().before(oocCase.getSupervisionBeginDate()))
			{
				postFailedValidation("Supervision End date cannot be after the Supervision Begin date.");
				this.valid = false;
			}
			// The Supervision Length is required for disposition type of Probation or 
			// Deferred Adjudication.
			if (oocCase.getSupervisionDays() == 0 && oocCase.getSupervisionMonths() == 0 &&
				oocCase.getSupervisionYears() == 0)
			{
				postFailedValidation("The Supervision Length is required for disposition type of Probation or Deferred Adjudication.");
				this.valid = false;
			}
		}
	 
		// Validation for ConfinementLength and SupervisionLength:
		// YEARS: 01 - 10  (this is because 10 years is the maximum time someone can be 
		// given probation)
		if (confinementYears > 0)
		{
			if (confinementYears > 10)
			{
				postFailedValidation("Confinement years cannot exceed ten (10).");
				this.valid = false;
			}
		}
		// MONTHS: 01 - 12
		if (confinementMonths > 0)
		{
			if (confinementMonths > 12)
			{
				postFailedValidation("Confinement months cannot exceed twelve (12).");
				this.valid = false;
			}
		}
		// DAYS : 01 - 31
		if (confinementDays > 0)
		{
			if (confinementDays > 31)
			{
				postFailedValidation("Confinement days cannot exceed thirty-one (31).");
				this.valid = false;
			}
		}
		
		// Offense date cannot be greater than arrest date
		if (oocCase.getOffenseDate().after(oocCase.getArrestDate()))
		{
			postFailedValidation("Offense date cannot be after the arrest date");
			this.valid = false;
		}
		Date sentenceDate = oocCase.getSentenceDate();
		if (sentenceDate != null)
		{
			// Date of Sentence cannot be less than arrest date and offense date.
			if (sentenceDate.before(oocCase.getArrestDate()) ||
				sentenceDate.before(oocCase.getOffenseDate()))
			{
				postFailedValidation("Sentence date cannot be before the arrest date and offense date.");
				this.valid = false;
			}
			// Transfer in date cannot be less than sentence date.
			if (oocCase.getTransferInDate().before(sentenceDate))
			{
				postFailedValidation("Transfer in date cannot be before the sentence date.");
				this.valid = false;
			}
			// Supervision Date is not valid for disposition type of Pre-trial intervention
			if (!dispositionTypeId.equalsIgnoreCase(PDCodeTableConstants.PRETRIAL_INTERVENTION))
			{
				// Supervision Begin date cannot be less than sentence date.
				if (oocCase.getSupervisionBeginDate().before(sentenceDate))
				{
					postFailedValidation("Supervision Begin date cannot be before the sentence date.");
					this.valid = false;
				}
			}
			// Date of community SupervisionPlacement ??? cannot be less than sentence date.
		}
		return this.valid;
	}

	/**
	 *  Responsible for applying all validation rules for reactivating an OutOfCountyProbationCase.
	 * 
	 * 
	 * Additional  data element level business rules are as follows:
	 * 
	 * 1) Any dates entered cannot be less than the previously set supervision date.
	 * 3) Supervision End date must be greater than Supervision Begin date.
	 * 4) Pretrial Intervention End date must be greater than Pretrial Intervention 
	 * begin date.
	 * 
	 * @param oocCase
	 * @return boolean - indicates whether valid or not
	 * @roseuid 44342BEC01DA
	 */
	public boolean validateForReactivation(ISupervisionCase oocCase)
	{
		this.valid = true;
		/// Validation is done in UI 
	/*	if (oocCase.getSupervisionEndDate() == null || (this.getSupervisionEndDate() != null && oocCase.getSupervisionEndDate().before(this.getSupervisionEndDate())))
		{
			postFailedValidation("error.managesupervisioncase.supervisiondateinvalid");
			this.valid = false;
		}*/
	
		// Not in the requirements Richard Young
		/*if (oocCase.getDispositionDate() == null || (this.getSupervisionEndDate() != null && oocCase.getDispositionDate().before(this.getSupervisionEndDate())))
		{
			postFailedValidation("error.managesupervisioncase.dispositiondateinvalid");
			this.valid = false;
		}*/
		// Enforce the same data element business rules defined for Creation of an OutOfCountyProbationCase.
		// JM - Validation is now done completely in the Struts UI
		///this.valid = validateForUpdate(oocCase);
		return this.valid;
	}

	/**
	 * 
	 * @param oocCase
	 * @roseuid 44342BEC01DB
	 */
	public void reactivate(ISupervisionCase oocCase)
	{
		this.setTransferInDate(oocCase.getTransferInDate());
		this.setContactFirstName(oocCase.getContactFirstName());
		this.setContactMiddleName(oocCase.getContactMiddleName());
		this.setContactLastName(oocCase.getContactLastName());
		this.setContactJobTitle(oocCase.getContactJobTitle());
		this.setContactPhoneNum(oocCase.getContactPhoneNum());
		this.setContactPhoneExt(oocCase.getContactPhoneExt());

			// set the disposition type and date
		this.setDispositionTypeId(oocCase.getDispositionTypeId());
		this.setDispositionDate(oocCase.getDispositionDate());
			
			// set the new Supervision begin / end dates
		this.setSupervisionBeginDate(oocCase.getSupervisionBeginDate());
		this.setSupervisionEndDate(oocCase.getSupervisionEndDate());
			
			
			// Need to set the Defendant Status and Case Status
		this.setCaseAndDefendantStatus(oocCase.getDispositionTypeId());
		CSTSHelper helper = new CSTSHelper();
		
		setUpdateT30Record( helper.isCurrentCaseForJurisdiction( oocCase ) );
		
	}
	
	/**
	 * Closes an OOC case by setting the appropriate values for an 
	 * OutOfCountyProbationCase being closed.
	 * ---> Not used
	 * @param closedStatus
	 */
	public void closeOutOfCountyCase(String closedStatus)
	{
		super.closeOutOfCountyCase(closedStatus);
		
		setDispositionTypeId(closedStatus);
		////setDispositionDate(getDispositionDate());
	}

	/**
	 * 
	 * @param cJISNum
	 * @param caseNum
	 * @return String stating reason why CJIS number is invalid or null (if valid).
	 */
	public static String validateCJISNumber(String cJISNum, String caseNum)
	{
		// first, validate the format of the CJIS number
		//	Validation for CJIS number:
		// First 9 characters must be numeric 
		// 10th character must be numeric or 'X' 
		// 11th character must be 'A', 'C', or 'D'. 
		// Last 3 characters (12, 13, 14) must be numeric
		// 
		// Examples: 
		//     Valid:  
		//         900355047XA001
		//         9003659699A002
		//         9003660530D002
		//  
		//     Invalid:
		//         9001Y6053FD002
		//         9003660530T00E
		
		String message = null; 
		// CJIS is not required, so if blank just return null
		if (cJISNum != null && !cJISNum.equals(""))
		{
			try
			{
				// First 9 characters must be numeric 
				Integer.parseInt(cJISNum.substring(0, 9));
			}
			catch (NumberFormatException nfe)
			{
				//message = "First 9 characters of the CJIS number must be numeric.";
				message = "error.managesupervisioncase.cjisnumfirstten";
				return message;
			}
	
			String tenthChar = cJISNum.substring(9, 10);
			// 10th character must be numeric or 'X' 
			if (!tenthChar.equalsIgnoreCase("X"))
			{
				try
				{
					Integer.parseInt(tenthChar);
				}
				catch (NumberFormatException nfe)
				{
					//message = "10th character of the CJIS number must be numeric or 'X'.";
					message = "error.managesupervisioncase.cjisnumfirstten";
					return message;
				}
			}
	
			if (cJISNum != null && cJISNum.length() > 10){
			    String eleventhChar = cJISNum.substring(10, 11);
			// 11th character must be 'A', 'C', or 'D'. 
			    if (!eleventhChar.equalsIgnoreCase("A") &&
			            !eleventhChar.equalsIgnoreCase("C") &&
			            !eleventhChar.equalsIgnoreCase("D"))
			    {
			        //message = "11th character of the CJIS number must be 'A', 'C', or 'D'.";
			        message = "error.managesupervisioncase.cjisnumlastfour";
			        return message;
			    }
			    try
			    {
			        // Last 3 characters (12, 13, 14) must be numeric
			        Integer.parseInt(cJISNum.substring(11, 14));
			    }
			    catch (NumberFormatException nfe)
			    {
			        //message = "Last 3 characters of the CJIS number must be numeric.";
			        message = "error.managesupervisioncase.cjisnumlastfour";
			        return message;
			    }
			} else {
		        message = "error.managesupervisioncase.cjisnumlastfour";
		        return message;
			}

			// the system will check to ensure that this number is not 
			// attached to another case.
			Iterator cases = findByCJISNum(cJISNum);
			if (cases.hasNext())
			{
				OutOfCountyProbationCase aCase = (OutOfCountyProbationCase)cases.next();
				// if the CJIS number is being entered for a new case, then we can treat it as a duplicate.
				if (caseNum == null || caseNum.equals(""))
				{
					message = "error.managesupervisioncase.duplicatecjisnum";
				}
				else
				// otherwise, make sure we are not evaluating the CJIS number from the case we are updating.
				if (!caseNum.equals(aCase.getCaseNum()))
				{
					message = "error.managesupervisioncase.duplicatecjisnum";
				}
			}
		}
		return message;
	}

	public static Iterator findByCJISNum(String cJISNum)
	{
		// CJIS is not required, so if blank just return
		if (cJISNum != null && !cJISNum.equals(""))
		{
			return new Home().findAll("cJISNum", cJISNum, OutOfCountyProbationCase.class);
		}
		return new ArrayList().iterator();
	}
	
	/**
	 * Gets all values for an OutOfCountyProbationCase.
	 * 
	 * @param oocCase
	 */
	public void fillOutOfCountyCase(ISupervisionCase oocCase, boolean isReactivate)
	{
		super.fillOutOfCountyCase(oocCase, isReactivate);

		oocCase.setPersonId(getPersonId());
		oocCase.setDispositionTypeId(getDispositionTypeId());
		oocCase.setDispositionDate(getDispositionDate());
		oocCase.setSentenceDate(getSentenceDate());
		oocCase.setArrestDate(getArrestDate());
		oocCase.setCjisNum(getCJISNum());
		oocCase.setConfinementDays(getConfinementDays());
		oocCase.setConfinementMonths(getConfinementMonths());
		oocCase.setConfinementYears(getConfinementYears());
		oocCase.setSupervisionDays(getSupervisionDays());
		oocCase.setSupervisionMonths(getSupervisionMonths());
		oocCase.setSupervisionYears(getSupervisionYears());
		oocCase.setPretrialInterventionBeginDate(getPretrialInterventionBeginDate());
		oocCase.setPretrialInterventionEndDate(getPretrialInterventionEndDate());
		oocCase.setStateId(getStateId());

		oocCase.setFilingDate(getFilingDateAsDate());
		oocCase.setInstrumentTypeId(getInstrumentTypeId());
		// if case is being reactivated, we need t oread the history record and get the 
		// previous disposition type.
		if (isReactivate)
		{
			oocCase.setPreviousDispositionTypeId(getPreviousDispositionTypeId());
		}
	}

	/**
	 * returns the collection of OutOfCountyCase Update History objects
	 */
	public Collection getUpdateHistory(boolean createTransferObject)
	{
		Collection updateHistory = new ArrayList();

		// set up the retrieval data
		GetOOCCaseUpdateHistoryEvent getHistoryEvent = new GetOOCCaseUpdateHistoryEvent();
		getHistoryEvent.setCourtDivisionId(getCourtDivisionId());
		getHistoryEvent.setCaseNum(getCaseNum());

		// get the history data and build the return collection
		Iterator historyRecsIter = OutOfCountyCaseHistory.findAll(getHistoryEvent);
		while (historyRecsIter.hasNext())
		{
			OutOfCountyCaseHistory history = (OutOfCountyCaseHistory)historyRecsIter.next();
			if (createTransferObject){
				OOCCaseUpdateHistoryTO historyTO = new OOCCaseUpdateHistoryTO();
				history.fillOutOfCountyCaseHistory(historyTO);
				updateHistory.add(historyTO);
			} else {
				updateHistory.add(history);
			}
		}
		return updateHistory;
	}

	public boolean canBeReactivated()
	{
		//	For CSCD:
		//	This activity enforces the rule that only Out Of County case (cdi = 010) with one of the following reason codes can be reactivated:
		//
		//	CABS  CLOSED - ABSCONDED                                  
		//	CDTH  CLOSED - DEATH OF PROBATIONER
		//	CETR  CLOSED - EARLY TERMINATION
		//	CEXP  CLOSED - PROBATION EXPIRED 
		//	CLAW  CLOSED - LAW VIOLATION 
		//	CMOV  CLOSED - MOVED FROM HARRIS COUNTY    
		//	COTH  CLOSED - OTHER REASON                          
		//	CREJ  CLOSED - SUPERVISION REJECTED  
		//	CREQ  CLOSED - REQUEST OF ORIGINAL JURISDICTION   
		//	CRVK  CLOSED - PROBATION REVOKED
		//	CTEC  CLOSED - TECHNICAL VIOLATION
		if (getDispositionTypeId().equalsIgnoreCase(PDCodeTableConstants.CLOSED_ABSCONDED) ||
			getDispositionTypeId().equalsIgnoreCase(PDCodeTableConstants.CLOSED_DEATH_OF_PROBATIONER) ||
			getDispositionTypeId().equalsIgnoreCase(PDCodeTableConstants.CLOSED_EARLY_TERMINATION) ||
			getDispositionTypeId().equalsIgnoreCase(PDCodeTableConstants.CLOSED_PROBATION_EXPIRED) ||
			getDispositionTypeId().equalsIgnoreCase(PDCodeTableConstants.CLOSED_LAW_VIOLATION) ||
			getDispositionTypeId().equalsIgnoreCase(PDCodeTableConstants.CLOSED_MOVED_FROM_HARRIS_COUNTY) ||
			getDispositionTypeId().equalsIgnoreCase(PDCodeTableConstants.CLOSED_OTHER_REASON) ||
			getDispositionTypeId().equalsIgnoreCase(PDCodeTableConstants.CLOSED_SUPERVISION_REJECTED) ||
			getDispositionTypeId().equalsIgnoreCase(PDCodeTableConstants.CLOSED_REQUEST_OF_ORIGINAL_JURISDICTION) ||
			getDispositionTypeId().equalsIgnoreCase(PDCodeTableConstants.CLOSED_PROBATION_REVOKED) ||
			getDispositionTypeId().equalsIgnoreCase(PDCodeTableConstants.CLOSED_TECHNICAL_VIOLATION))
		{
			return true;
		}
		return false;
	}
	
	/*
	 * The Defendant Status (DST) and Case Status (CST) are set based on the 
	 *  disposition type as follows:
	 * Disposition Types are Deferred Adjudication, Straight Probation, Pre-trial Intervention
	 */
	public void setCaseAndDefendantStatus(String disposition)
	{
		// Set the Defendant Status
		// If Disposition Type = Deferred Adjudication
		//	 DST = A, CST = R
		// If Disposition Type = Straight Probation
		//	 DST = P, CST = R
		// If Disposition Type = Pre-trial Intervention
		//	 DST = V, CST = R
		if (disposition.equals(PDCodeTableConstants.DEFERRED_ADJUDICATION))
		{
			setDefendantStatusId("A");
		}
		else
		if (disposition.equals(PDCodeTableConstants.STRAIGHT_PROBATION))
		{
		   setDefendantStatusId("P");
		}
		else
		if (disposition.equals(PDCodeTableConstants.PRETRIAL_INTERVENTION))
		{
		   setDefendantStatusId("V");
		}
		// set Case Status
		setCaseStatusId("R");

	}
	
	public String getPreviousDispositionTypeId()
	{
		fetch();
		return previousDispositionTypeId;
	}
	
	public void setPreviousDispositionTypeId(String aDispTypeId)
	{
		previousDispositionTypeId = aDispTypeId;
	}
	
}
