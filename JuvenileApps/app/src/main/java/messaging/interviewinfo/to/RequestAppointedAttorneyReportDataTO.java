package messaging.interviewinfo.to;

import java.util.ArrayList;
import java.util.List;


/**
 *
 */
public class RequestAppointedAttorneyReportDataTO 
{
	// Section 1
	private String juvenileName = "";
	private String juvenileNumber = "";
	
	private String petitionNumber = "";
	private String courtName = "";
	private String courtDate = "";
	
	// Secton 5
	private String notes = "";
	private String probationOfficer = "";
	private String jpoSupervisor = "";

	private List guardianHomeInfo = new ArrayList();		// GuardianHomeInfo 

	/**
	 *
	 */
	public RequestAppointedAttorneyReportDataTO()
	{
	}
	
	
	/**
	 * @return Returns the courtDate.
	 */
	public String getCourtDate() {
		return courtDate;
	}
	/**
	 * @param courtDate The courtDate to set.
	 */
	public void setCourtDate(String courtDate) {
		this.courtDate = courtDate;
	}
	/**
	 * @return Returns the courtName.
	 */
	public String getCourtName() {
		return courtName;
	}
	/**
	 * @param courtName The courtName to set.
	 */
	public void setCourtName(String courtName) {
		this.courtName = courtName;
	}
	/**
	 * @return Returns the jpoSupervisor.
	 */
	public String getJpoSupervisor() {
		return jpoSupervisor;
	}
	/**
	 * @param jpoSupervisor The jpoSupervisor to set.
	 */
	public void setJpoSupervisor(String jpoSupervisor) {
		this.jpoSupervisor = jpoSupervisor;
	}
	/**
	 * @return Returns the juvenileName.
	 */
	public String getJuvenileName() {
		return juvenileName;
	}
	/**
	 * @param juvenileName The juvenileName to set.
	 */
	public void setJuvenileName(String juvenileName) {
		this.juvenileName = juvenileName;
	}
	/**
	 * @return Returns the notes.
	 */
	public String getNotes() {
		return notes;
	}
	/**
	 * @param notes The notes to set.
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}
	/**
	 * @return Returns the petitionNumber.
	 */
	public String getPetitionNumber() {
		return petitionNumber;
	}
	/**
	 * @param petitionNumber The petitionNumber to set.
	 */
	public void setPetitionNumber(String petitionNumber) {
		this.petitionNumber = petitionNumber;
	}
	
	
	/**
	 * @return Returns the probatiobOfficer.
	 */
	public String getProbationOfficer() {
		return probationOfficer;
	}
	/**
	 * @param probatiobOfficer The probatiobOfficer to set.
	 */
	public void setProbationOfficer(String probationOfficer) {
		this.probationOfficer = probationOfficer;
	}
	
	/**
	 * @return Returns the guardianInfo.
	 */
	public List getGuardianHomeInfo() {
		return guardianHomeInfo;
	}
	
	
	/**
	 * 
	 */
	public static class GuardianHomeInfo
	{
		// Section 2
		private List guardianInfo = new ArrayList();		// GuardianInfo 
		
		private String totalSupportingIncome = "";
		
		// Section 3
		private String dependents = "";
		
		// Section 4
		private String housePaymentExpenses = "";
		private String utilitiesExpenses = "";
		private String foodExpenses = "";
		private String schoolExpenses = "";
		private String childSupportPaid = "";
		private String medicalExpenses = "";
		private String insuranceExpense = "";
		private String propertyValue = "";
		private String intangibleValue = "";	// Stocks
		private String savings = "";
		private String childSupportReceived = "";
		private String otherIncome = "";
		private String foodStamps = "";
		private String TANF = "";
		private String total = "";
		private String ssi = "";
		
		private String guardianRelationToJuvenile = "";
		
		
		/**
		 * @return Returns the childSupportPaid.
		 */
		public String getChildSupportPaid() {
			return childSupportPaid;
		}
		/**
		 * @param childSupportPaid The childSupportPaid to set.
		 */
		public void setChildSupportPaid(String childSupportPaid) {
			this.childSupportPaid = childSupportPaid;
		}

		/**
		 * @return Returns the dependents.
		 */
		public String getDependents() {
			return dependents;
		}
		/**
		 * @param dependents The dependents to set.
		 */
		public void setDependents(String dependents) {
			this.dependents = dependents;
		}
		/**
		 * @return Returns the foodExpenses.
		 */
		public String getFoodExpenses() {
			return foodExpenses;
		}
		/**
		 * @param foodExpenses The foodExpenses to set.
		 */
		public void setFoodExpenses(String foodExpenses) {
			this.foodExpenses = foodExpenses;
		}
		/**
		 * @return Returns the foodStamps.
		 */
		public String getFoodStamps() {
			return foodStamps;
		}
		/**
		 * @param foodStamps The foodStamps to set.
		 */
		public void setFoodStamps(String foodStamps) {
			this.foodStamps = foodStamps;
		}
		/**
		 * @return Returns the guardianRelationToJuvenile.
		 */
		public String getGuardianRelationToJuvenile() {
			return guardianRelationToJuvenile;
		}
		/**
		 * @param guardianRelationToJuvenile The guardianRelationToJuvenile to set.
		 */
		public void setGuardianRelationToJuvenile(String guardianRelationToJuvenile) {
			this.guardianRelationToJuvenile = guardianRelationToJuvenile;
		}
		/**
		 * @return Returns the housePaymentExpenses.
		 */
		public String getHousePaymentExpenses() {
			return housePaymentExpenses;
		}
		/**
		 * @param housePaymentExpenses The housePaymentExpenses to set.
		 */
		public void setHousePaymentExpenses(String housePaymentExpenses) {
			this.housePaymentExpenses = housePaymentExpenses;
		}
		/**
		 * @return Returns the insuranceExpense.
		 */
		public String getInsuranceExpense() {
			return insuranceExpense;
		}
		/**
		 * @param insuranceExpense The insuranceExpense to set.
		 */
		public void setInsuranceExpense(String insuranceExpense) {
			this.insuranceExpense = insuranceExpense;
		}
		/**
		 * @return Returns the intangibleValue.
		 */
		public String getIntangibleValue() {
			return intangibleValue;
		}
		/**
		 * @param intangibleValue The intangibleValue to set.
		 */
		public void setIntangibleValue(String intangibleValue) {
			this.intangibleValue = intangibleValue;
		}

		/**
		 * @return Returns the medicalExpenses.
		 */
		public String getMedicalExpenses() {
			return medicalExpenses;
		}
		/**
		 * @param medicalExpenses The medicalExpenses to set.
		 */
		public void setMedicalExpenses(String medicalExpenses) {
			this.medicalExpenses = medicalExpenses;
		}
		
		/**
		 * @return Returns the otherIncome.
		 */
		public String getOtherIncome() {
			return otherIncome;
		}
		/**
		 * @param otherIncome The otherIncome to set.
		 */
		public void setOtherIncome(String otherIncome) {
			this.otherIncome = otherIncome;
		}

		/**
		 * @return Returns the propertyValue.
		 */
		public String getPropertyValue() {
			return propertyValue;
		}
		/**
		 * @param propertyValue The propertyValue to set.
		 */
		public void setPropertyValue(String propertyValue) {
			this.propertyValue = propertyValue;
		}
		/**
		 * @return Returns the savings.
		 */
		public String getSavings() {
			return savings;
		}
		/**
		 * @param savings The savings to set.
		 */
		public void setSavings(String savings) {
			this.savings = savings;
		}
		/**
		 * @return Returns the schoolExpenses.
		 */
		public String getSchoolExpenses() {
			return schoolExpenses;
		}
		/**
		 * @param schoolExpenses The schoolExpenses to set.
		 */
		public void setSchoolExpenses(String schoolExpenses) {
			this.schoolExpenses = schoolExpenses;
		}
		/**
		 * @return Returns the tANF.
		 */
		public String getTANF() {
			return TANF;
		}
		/**
		 * @param tanf The tANF to set.
		 */
		public void setTANF(String tanf) {
			TANF = tanf;
		}
		/**
		 * @return Returns the total.
		 */
		public String getTotal() {
			return total;
		}
		/**
		 * @param total The total to set.
		 */
		public void setTotal(String total) {
			this.total = total;
		}
		/**
		 * @return Returns the totalSupportingIncome.
		 */
		public String getTotalSupportingIncome() {
			return totalSupportingIncome;
		}
		/**
		 * @param totalSupportingIncome The totalSupportingIncome to set.
		 */
		public void setTotalSupportingIncome(String totalSupportingIncome) {
			this.totalSupportingIncome = totalSupportingIncome;
		}
		/**
		 * @return Returns the utilitiesExpenses.
		 */
		public String getUtilitiesExpenses() {
			return utilitiesExpenses;
		}
		/**
		 * @param utilitiesExpenses The utilitiesExpenses to set.
		 */
		public void setUtilitiesExpenses(String utilitiesExpenses) {
			this.utilitiesExpenses = utilitiesExpenses;
		}

		/**
		 * @return Returns the guardianInfo.
		 */
		public List getGuardianInfo() {
			return guardianInfo;
		}

		/**
		 * 
		 */
		public static class GuardianInfo
		{
			// Section 2
			private String guardian = "";
			private String residesWith = "";
			private String address = "";
			private String employmentTitle = "";
			private String hasOtherIncome = "___";
			private String phone = "";
			private String annualNetIncome = "";
			private String annualGrossIncome = "";
			private String incomeOfOtherAdult = "";

			
			/**
			 * @return Returns the address1.
			 */
			public String getAddress() {
				return address;
			}
			/**
			 * @param address1 The address1 to set.
			 */
			public void setAddress(String address) {
				this.address = address;
			}
			/**
			 * @return Returns the annualGrossIncome1.
			 */
			public String getAnnualGrossIncome() {
				return annualGrossIncome;
			}
			/**
			 * @param annualGrossIncome1 The annualGrossIncome1 to set.
			 */
			public void setAnnualGrossIncome(String annualGrossIncome) {
				this.annualGrossIncome = annualGrossIncome;
			}

			/**
			 * @return Returns the annualNetIncome1.
			 */
			public String getAnnualNetIncome() {
				return annualNetIncome;
			}
			/**
			 * @param annualNetIncome1 The annualNetIncome1 to set.
			 */
			public void setAnnualNetIncome(String annualNetIncome) {
				this.annualNetIncome = annualNetIncome;
			}
			
			/**
			 * @return Returns the employmentTitle1.
			 */
			public String getEmploymentTitle() {
				return employmentTitle;
			}
			/**
			 * @param employmentTitle1 The employmentTitle1 to set.
			 */
			public void setEmploymentTitle(String employmentTitle) {
				this.employmentTitle = employmentTitle;
			}

			/**
			 * @return Returns the guardian1.
			 */
			public String getGuardian() {
				return guardian;
			}
			/**
			 * @param guardian1 The guardian1 to set.
			 */
			public void setGuardian(String guardian) {
				this.guardian = guardian;
			}
			
			/**
			 * @return Returns the hasOtherIncome1.
			 */
			public String getHasOtherIncome() {
				return hasOtherIncome;
			}
			/**
			 * @param hasOtherIncome1 The hasOtherIncome1 to set.
			 */
			public void setHasOtherIncome(String hasOtherIncome) {
				this.hasOtherIncome = hasOtherIncome;
			}
			
			/**
			 * @return Returns the incomeOfOtherAdult1.
			 */
			public String getIncomeOfOtherAdult() {
				return incomeOfOtherAdult;
			}
			/**
			 * @param incomeOfOtherAdult1 The incomeOfOtherAdult1 to set.
			 */
			public void setIncomeOfOtherAdult(String incomeOfOtherAdult) {
				this.incomeOfOtherAdult = incomeOfOtherAdult;
			}

			/**
			 * @return Returns the phone1.
			 */
			public String getPhone() {
				return phone;
			}
			/**
			 * @param phone1 The phone1 to set.
			 */
			public void setPhone(String phone) {
				this.phone = phone;
			}
			
			/**
			 * @return Returns the residesWith1.
			 */
			public String getResidesWith() {
				return residesWith;
			}
			/**
			 * @param residesWith1 The residesWith1 to set.
			 */
			public void setResidesWith(String residesWith) {
				this.residesWith = residesWith;
			}
		
		}
		
		/**
		 * @return Returns the childSupportReceived.
		 */
		public String getChildSupportReceived() {
			return childSupportReceived;
		}
		/**
		 * @param childSupportReceived The childSupportReceived to set.
		 */
		public void setChildSupportReceived(String childSupportReceived) {
			this.childSupportReceived = childSupportReceived;
		}
		
		/**
		 * @return Returns the ssi.
		 */
		public String getSsi() {
			return ssi;
		}
		/**
		 * @param ssi The ssi to set.
		 */
		public void setSsi(String ssi) {
			this.ssi = ssi;
		}
	}
	
	/**
	 * @return Returns the juvenileNumber.
	 */
	public String getJuvenileNumber() {
		return juvenileNumber;
	}
	/**
	 * @param juvenileNumber The juvenileNumber to set.
	 */
	public void setJuvenileNumber(String juvenileNumber) {
		this.juvenileNumber = juvenileNumber;
	}
}
