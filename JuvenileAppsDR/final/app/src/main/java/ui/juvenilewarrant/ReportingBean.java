/*
 * Created on Sep 7, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.juvenilewarrant;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import mojo.km.utilities.DateUtil;

/**
 * @author kmurthy
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ReportingBean
{
    private String affidavitStatement = "";

    private String agency = "";

    private String ageVerification = "";

    private String associateAddresses = "";

    private String associateNameReleasedTo = "";

    private String cautionComments = "";

    private String cautionsSelected = "";

    private String chargeCode = "";

    private String chargeDescription = "";

    private String complexionId = "";

    private String courtDescription = "";

    private String courtId = "";
    
    private String courtWithOrdinal = "";

    private Date currentServiceDate;

    private Date dateOfBirth;
    
    private String dateOfBirthString;

    private String degree = "";

    private String executorDepartmentName = "";

    private String executorName = "";
    
    private String eyeColor = "";

    private Date fileStampDate;

    private String firstName = "";
    
    private String hairColor = "";
    
    private String height = "";

    private String juvenileAge = "";

    private String lastName = "";
    
    private String level = "";

    private String middleName = "";

    private Date offenseDate;

    private String offenseDateString;

    private String officerAgencyName = "";

    private String officerName = "";

    private String petitionNum = "";

    private String race = "";
    
    private String scars = "";

    private String sexId = "";

    private String suffix = "";
    
    private String tattoos = "";

    private Date warrantActivationDate;

    private String warrantNum = "";

    private String warrantOriginatorJudge = "";

    private String warrantOriginatorName = "";

    private String warrantType = "";

    private String weight = "";
    
    private String reportName = "";
    
    /**
     * @return
     */
    public String getAffidavitStatement()
    {
        return affidavitStatement;
    }

    /**
     * @return
     */
    public String getAgency()
    {
        return agency;
    }

    /**
     * @return Returns the ageVerification.
     */
    public String getAgeVerification()
    {
        return ageVerification;
    }

    /**
     * @return
     */
    public String getAssociateAddresses()
    {
        return associateAddresses;
    }

    /**
     * @return
     */
    public String getAssociateNameReleasedTo()
    {
        return associateNameReleasedTo;
    }

    /**
     * @return
     */
    public String getCautionComments()
    {
        return cautionComments;
    }

    /**
     * @return
     */
    public String getCautionsSelected()
    {
        return cautionsSelected;
    }

    /**
     * @return
     */
    public String getChargeCode()
    {
        return chargeCode;
    }

    /**
     * @return
     */
    public String getChargeDescription()
    {
        return chargeDescription;
    }

    /**
     * @return
     */
    public String getComplexionId()
    {
        return complexionId;
    }

    /**
     * @return Returns the courtDescription.
     */
    public String getCourtDescription()
    {
        return courtDescription;
    }

    /**
     * @return
     */
    public String getCourtId()
    {
        return courtId;
    }

    /**
     * @return
     */
    public Date getCurrentServiceDate()
    {
        return currentServiceDate;
    }

    /**
     * @return
     */
    public String getDateOfBirth()
    {
        return DateFormat.getDateInstance(DateFormat.LONG).format(dateOfBirth);
        //return dateOfBirth;
    }

    /**
     * @return
     */
    public String getDegree()
    {
        return degree;
    }

    /**
     * @return
     */
    public String getExecutorDepartmentName()
    {
        return executorDepartmentName;
    }

    /**
     * @return
     */
    public String getExecutorName()
    {
        return executorName;
    }

    /**
     * @return
     */
    public Date getFileStampDate()
    {
        return fileStampDate;
    }

    /**
     * Used for the Reporting functionality
     * 
     * @return string
     */
    public String getFileStampDateforReport()
    {
        String fileStampDateStr = "";
        if (fileStampDate != null)
        {
            fileStampDateStr = DateUtil.dateToString(this.fileStampDate,
                    "MMMM dd, yyyy");
        }

        return fileStampDateStr;
    }

    /**
     * @return
     */
    public String getFirstName()
    {
        return firstName;
    }

	/**Returns the juveniles full name First Middle Last Suffix
	 * @return
	 */
	public String getNameFirstMiddleLastSuffix(){
	    
	    StringBuffer buffer = new StringBuffer(50);
	    if(getFirstName() != null && !getFirstName().equals(""))
	    {
	        buffer.append(getFirstName());
	        buffer.append(" ");
	    }
	    if(getMiddleName() != null && !getMiddleName().equals(""))
	    {
	        buffer.append(getMiddleName());
	        buffer.append(" ");
	    }
	    if(getLastName() != null && !getLastName().equals(""))
	    {
	        buffer.append(getLastName());
	    }
	    if(getSuffix() != null && !getSuffix().equals(""))
	    {
	        buffer.append(" ");
	        buffer.append(getSuffix());
	    }
	    String nameFirstMiddleLastSuffix = buffer.toString();
	    return nameFirstMiddleLastSuffix;
	}
	
    /**
     * @return
     */
    public String getHeight()
    {
        return height;
    }

    /**
     * @return
     */
    public String getJuvenileAge()
    {
        return juvenileAge;
    }

    /**
     * @return
     */
    public String getLastName()
    {
        return lastName;
    }

    /**
     * @return
     */
    public String getLevel()
    {
        return level;
    }

    /**
     * @return
     */
    public String getMiddleName()
    {
        return middleName;
    }

    /**
     * @return Returns the offenseDate.
     */
    public Date getOffenseDate()
    {

        return offenseDate;
    }

    /**
     * 
     * @return
     */

    public String getOffenseDateAsString()
    {
        if (offenseDateString == null)
        {
            offenseDateString = "";
        }
        return offenseDateString;

    }

    /**
     * @return
     */
    public String getOfficerAgencyName()
    {
        return officerAgencyName;
    }

    /**
     * @return
     */
    public String getOfficerName()
    {
        return officerName;
    }

    /**
     * @return
     */
    public String getPetitionNum()
    {
        return petitionNum;
    }

    /**
     * @return
     */
    public String getRace()
    {
        return race;
    }

    /**
     * @return
     */
    public String getSexId()
    {
        return sexId;
    }

    /**
     * @return
     */
    public String getSuffix()
    {
        return suffix;
    }

    /**
     * @return
     */
    public Date getWarrantActivationDate()
    {
        return warrantActivationDate;
    }

    /**
     * Used for the Reporting functionality
     * 
     * @return string
     */
    public String getWarrantActivationDateforReport()
    {
        String activationDateStr = "";
        if (warrantActivationDate != null)
        {
            activationDateStr = DateUtil.dateToString(
                    this.warrantActivationDate, "MMMM dd, yyyy");
        }

        return activationDateStr;
    }

    /**
     * @return
     */
    public String getWarrantNum()
    {
        return warrantNum;
    }

    /**
     * @return Returns the warrantOriginatorJudge.
     */
    public String getWarrantOriginatorJudge()
    {
        return warrantOriginatorJudge;
    }

    /**
     * @return
     */
    public String getWarrantOriginatorName()
    {
        return warrantOriginatorName;
    }

    /**
     * Used for the Reporting functionality
     * 
     * @return string
     */
    public String getWarrantServiceDateforReport()
    {
        String serviceDateStr = "";
        if (currentServiceDate != null)
        {
            serviceDateStr = DateUtil.dateToString(this.currentServiceDate,
                    "MMMM dd, yyyy");
        }

        return serviceDateStr;
    }

    /**
     * @return
     */
    public String getWarrantType()
    {
        return warrantType;
    }

    /**
     * @return
     */
    public String getWeight()
    {
        return weight;
    }

    /**
     * @param string
     */
    public void setAffidavitStatement(String string)
    {
        if (string != null)
            affidavitStatement = string;
    }

    /**
     * @param string
     */
    public void setAgency(String string)
    {
        if (string != null)
            agency = string;
    }

    /**
     * @param ageVerification
     *            The ageVerification to set.
     */
    public void setAgeVerification(String ageVerification)
    {
        this.ageVerification = ageVerification;
    }

    /**
     * @param string
     */
    public void setAssociateAddresses(String string)
    {
        if (string != null)
            associateAddresses = string;
    }

    /**
     * @param string
     */
    public void setAssociateNameReleasedTo(String string)
    {
        if (string != null)
            associateNameReleasedTo = string;
    }

    /**
     * @param string
     */
    public void setCautionComments(String string)
    {
        cautionComments = string;
    }

    /**
     * @param string
     */
    /*
     * public void setAssociateAddresses(Collection associates) { if (associates ==
     * null || associates.isEmpty()) { associateAddresses = ""; } else {
     * StringBuffer sb = new StringBuffer(); JuvenileAssociateBean assoc = null;
     * Iterator ite = associates.iterator(); while (ite.hasNext()) { assoc =
     * (JuvenileAssociateBean) ite.next();
     * sb.append(assoc.getAssociateAddresses()); sb.append(", "); }
     * 
     * associateAddresses = sb.toString(); } }
     */

    /**
     * @param string
     */
    public void setCautionsSelected(String cautionsSelected)
    {
        this.cautionsSelected = cautionsSelected;
    }

    /**
     * @param string
     */
    public void setChargeCode(String string)
    {
        if (string != null)
            chargeCode = string;
    }

    /**
     * @param string
     */
    public void setChargeDescription(String string)
    {
        if (string != null)
            chargeDescription = string;
    }

    /**
     * @param string
     */
    public void setComplexionId(String string)
    {
        if (string != null)
            complexionId = string;
    }

    /**
     * @param courtDescription
     *            The courtDescription to set.
     */
    public void setCourtDescription(String courtDescription)
    {
        this.courtDescription = courtDescription;
    }
    
    /**
     * 
     * @return
     */
    public String getDateOfBirthString() {
		return dateOfBirthString;
	}
    
    /**
     * 
     * @param dateOfBirthString
     */
	public void setDateOfBirthString(String dateOfBirthString) {
		this.dateOfBirthString = dateOfBirthString;
	}

	/**
     * @param string
     */
    public void setCourtId(String string)
    {
        if (string != null)
            courtId = string;
    }

    public String getCourtWithOrdinal() {
		return courtWithOrdinal;
	}

	public void setCourtWithOrdinal(String courtWithOrdinal) {
		this.courtWithOrdinal = courtWithOrdinal;
	}

	/**
     * @param date
     */
    public void setCurrentServiceDate(Date date)
    {
        if (date != null)
            currentServiceDate = date;
    }

    /**
     * @param string
     */
    public void setDateOfBirth(Date dob)
    {
        if (dob != null)
        {

            if (juvenileAge == null || juvenileAge.equals(""))
            {
                Calendar dateOfBirth = new GregorianCalendar();
                dateOfBirth.setTime(dob);

                // Create a calendar object with today's date
                Calendar today = Calendar.getInstance();

                int age = today.get(Calendar.YEAR)
                        - dateOfBirth.get(Calendar.YEAR);

                // added JMF - 9/8/2005
                Calendar birthDay = Calendar.getInstance();
                birthDay.set(Calendar.MONTH, dateOfBirth.get(Calendar.MONTH));
                birthDay.set(Calendar.DATE, dateOfBirth.get(Calendar.DATE));
                if (today.before(birthDay))
                {
                    age--;
                }
                this.dateOfBirth = dob;
                juvenileAge = String.valueOf(age);
            }
        }
    }

    /**
     * @param string
     */
    public void setDegree(String string)
    {
        if (string != null)
            degree = string;
    }

    /**
     * @param string
     */
    public void setExecutorDepartmentName(String string)
    {
        executorDepartmentName = string;
    }

    /**
     * @param string
     */
    public void setExecutorName(String string)
    {
        if (string != null)
            executorName = string;
    }

    /**
     * @param date
     */
    public void setFileStampDate(Date date)
    {
        if (date != null)
            fileStampDate = date;
    }

    /**
     * @param string
     */
    public void setFirstName(String string)
    {
        if (string != null)
            firstName = string;
    }

    /**
     * @param string
     */
    public void setHeight(String string)
    {
        if (string != null)
            height = string;
    }

    /**
     * @param string
     */
    public void setJuvenileAge(String string)
    {
        juvenileAge = string;
    }

    /**
     * @param string
     */
    public void setLastName(String string)
    {
        if (string != null)
            lastName = string;
    }

    /**
     * @param string
     */
    public void setLevel(String string)
    {
        if (string != null)
        {
            if (string.equalsIgnoreCase("F"))
            {
                string = "Felony";

            }
            else
            {
                string = "Misdemeanor";
            }
        }
        level = string;
    }

    /**
     * @param string
     */
    public void setMiddleName(String string)
    {
        if (string != null)
            middleName = string;
    }

    /**
     * @param offenseDate
     *            The offenseDate to set.
     */
    public void setOffenseDate(Date offenseDate)
    {
        this.offenseDate = offenseDate;
    }

    /**
     * 
     * @param string
     */
    public void setOffenseDateAsString(String aDateString)
    {
        this.offenseDateString = aDateString;
    }

    /**
     * @param string
     */
    public void setOfficerAgencyName(String string)
    {
        if (string != null)
            officerAgencyName = string;
    }

    /**
     * @param string
     */
    public void setOfficerName(String string)
    {
        if (string != null)
            officerName = string;
    }

    /**
     * @param string
     */
    public void setPetitionNum(String string)
    {
        if (string != null)
            petitionNum = string;
    }

    /**
     * @param string
     */
    public void setRace(String string)
    {
        if (string != null)
            race = string;
    }

    /**
     * @param string
     */
    public void setSexId(String string)
    {
        if (string != null)
            sexId = string;
    }

    /**
     * @param string
     */
    public void setSuffix(String string)
    {
        if (string != null)
            suffix = string;
    }

    public String getScars() {
		return scars;
	}

	public void setScars(String scars) {
		this.scars = scars;
	}

	public String getTattoos() {
		return tattoos;
	}

	public void setTattoos(String tattoos) {
		this.tattoos = tattoos;
	}

	/**
     * @param date
     */
    public void setWarrantActivationDate(Date date)
    {
        if (date != null)
            warrantActivationDate = date;
    }

    /**
     * @param string
     */
    public void setWarrantNum(String string)
    {
        if (string != null)
            warrantNum = string;
    }

    /**
     * @param warrantOriginatorJudge
     *            The warrantOriginatorJudge to set.
     */
    public void setWarrantOriginatorJudge(String warrantOriginatorJudge)
    {
        this.warrantOriginatorJudge = warrantOriginatorJudge;
    }

    /**
     * @param string
     */
    public void setWarrantOriginatorName(String string)
    {
        if (string != null)
            warrantOriginatorName = string;
    }

    /**
     * @param string
     */
    public void setWarrantType(String string)
    {
        if (string != null)
            warrantType = string;
    }

    public String getEyeColor() {
		return eyeColor;
	}

	public void setEyeColor(String eyeColor) {
		this.eyeColor = eyeColor;
	}

	public String getHairColor() {
		return hairColor;
	}

	public void setHairColor(String hairColor) {
		this.hairColor = hairColor;
	}

	public String getOffenseDateString() {
		return offenseDateString;
	}

	public void setOffenseDateString(String offenseDateString) {
		this.offenseDateString = offenseDateString;
	}

	/**
     * @param string
     */
    public void setWeight(String string)
    {
        if (string != null)
            weight = string;
    }

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		if (reportName != null){
			this.reportName = reportName;
		}else{
			this.reportName = "";
		}
	}
    
    
}
