package ui.juvenilecase.form;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;

import messaging.juvenilecase.reply.FamilyConstellationMemberFinancialResponseEvent;
import mojo.km.utilities.DateUtil;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import ui.common.CodeHelper;
import ui.common.Name;
import ui.common.UIUtil;
import ui.juvenilecase.BenefitsAssessmentCalculator;
import ui.juvenilecase.UIJuvenileLoadCodeTables;

/**
 * @author glyons
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ProcessBenefitsAssessmentForm extends ActionForm
{

    public static final String PARENTAL_DEPRIVATION_CHECKBOXES = "parentalDeprivationCheckBoxes";

    public static final String INFO_SOURCE = "infoSource";

    private Collection requestList = new ArrayList(); //List of

    // RequestedBenefitsAssessmentRequestEvent

    private BenefitAssessment currentAssessment = new BenefitAssessment();

    private Collection previousBenefitsAssessments = new ArrayList();

    private Collection previousJuvenileBenefits = new ArrayList();

    private FamilyConstellationMemberFinancialResponseEvent guardianFinancialInfo = new FamilyConstellationMemberFinancialResponseEvent();

    private Collection employmentInfoList = null;

    private static Collection relationshipsToJuvenileCodeTable = new ArrayList();

    private static Collection incomeSourceCodeTable = new ArrayList();

    private static Collection benefitsAssessmentInfoSourceCodeTable = new ArrayList();

    public static final String VIEW = "VIEW";

    public static final String VIEW_REVIEW = "VIEW_REVIEW";

    public static final String REVIEW = "REVIEW";

    private String summaryMode = "SUMMARY";

    private boolean isReview = false;

    private boolean isView = false;

    private Name titleIVeOfficerName = new Name();

    private String titleIVeOfficerId = "";

    public ProcessBenefitsAssessmentForm()
    {
        UIJuvenileLoadCodeTables.getInstance().setJuvenileMemberForm(new JuvenileMemberForm());
    }

    public void reset(ActionMapping mapping, javax.servlet.http.HttpServletRequest request)
    {
        String checkBoxName = request.getParameter("checkBoxName");

        if (checkBoxName != null)
        {
            BenefitAssessment.TitleIVEQuestion question = currentAssessment.getQuestion();

            if (checkBoxName.equals(PARENTAL_DEPRIVATION_CHECKBOXES))
            {
                question.clear();
                /*
                 * question.setOneParentIsStepparent(false);
                 * question.setDeathOrAbsence(false);
                 * question.setIncapacityOrDisabilityOfParent(false);
                 * question.setPrimaryWageEarnerUnderemployment(false);
                 * question.setPweWorkedLessThen100Hours(false);
                 * question.setPweIncomeLessThanUnderemployedLimit(false);
                 * 
                 * question.setWasChildLivingWithParent(false);
                 * 
                 * 
                 * 
                 * 
                 * question.setPweWorkedIrregularLessThan100HoursAvg(false);
                 */

            }
            else if (checkBoxName.equals(INFO_SOURCE))
            {
                question.setSelectedInfoSourceId(null);
            }
        }
        super.reset(mapping, request);

    }

    //clears the form
    public void clear()
    {
        requestList.clear();
        currentAssessment = new BenefitAssessment();
        previousBenefitsAssessments.clear();
        previousJuvenileBenefits.clear();
        guardianFinancialInfo = new FamilyConstellationMemberFinancialResponseEvent();
        relationshipsToJuvenileCodeTable.clear();
        benefitsAssessmentInfoSourceCodeTable.clear();

        summaryMode = "SUMMARY";

        isReview = false;
        isView = false;

        titleIVeOfficerName = new Name();
        titleIVeOfficerId = "";

    }

    /**
     * @return
     */
    public Collection getRequestList()
    {
        return requestList;
    }

    /**
     * @param collection
     */
    public void setRequestList(Collection collection)
    {
        requestList = collection;
    }

    /**
     * @return
     */
    public BenefitAssessment getCurrentAssessment()
    {
        return currentAssessment;
    }

    /**
     * @param info
     */
    public void setCurrentAssessment(BenefitAssessment info)
    {
        currentAssessment = info;
    }

    public String getFamilyNumber()
    {
        BenefitAssessment bene = getCurrentAssessment();
        for (Iterator iter = bene.getListOfGuardians().iterator(); iter.hasNext();)
        {
            Guardian g = (Guardian) iter.next();
            // Get the number from the first one.
            return g.getFamilyNumber();
        }
        return "";
    }

    /** **************** All inner classes are here ************************ */
    public static class BenefitAssessment
    {
        private String assessmentId = "";

        private String juvNumber = "";

        private String casefileId = "";

        private Name juvName = new Name();

        private String jpoName = "";

        private String requesterName = "";

        private Date requestDate = null;
        private String requestDateStatus="";

        private String requestTime = "";

        private Collection listOfGuardians = new ArrayList();

        private Guardian guardian = new Guardian();

        TitleIVEQuestion question = new TitleIVEQuestion();

        //To be completed By JPD Staff only
        private boolean initialOrderRemovalContainBestInterest = false;

        private boolean reasonableEffortsMadeWithin60DaysOfChildRemoval = false;

        private boolean courtOrderIncludeFindingChildCareAndPlacement = false;

        private boolean childMeetAFDCRequirement = false;

        // Eligibility determination
        private boolean isEligibleForMedicaid = false;

        private boolean isReceivingMedicaid = false;

        private boolean isEligibleForTitleIVe = false;

        private boolean isReceivingTitleIVe = false;

        private String comments = "";

        //custom method
        public String getGuardiansNames()
        {
            StringBuffer guardiansNames = new StringBuffer();

            if (listOfGuardians != null && listOfGuardians.size() > 0)
            {
                for (Iterator iter = listOfGuardians.iterator(); iter.hasNext();)
                {
                    Guardian g = (Guardian) iter.next();
                    if (g.getName() != null && g.getName().getFormattedName().length() > 0)
                    {
                        guardiansNames.append(g.getName().getFormattedName());
                        if (iter.hasNext())
                            guardiansNames.append(" / ");
                    }
                }
            }
            return guardiansNames.toString();
        }
        
        

        /**
         * @return
         */
        public Collection getListOfGuardians()
        {
            return listOfGuardians;
        }

        /**
         * @return
         */
        public Name getJuvName()
        {
            return juvName;
        }

        /**
         * @return
         */
        public String getJuvNumber()
        {
            return juvNumber;
        }

        /**
         * @param collection
         */
        public void setListOfGuardians(Collection collection)
        {
            if (collection != null)
                listOfGuardians = collection;
        }

        /**
         * @param name
         */
        public void setJuvName(Name name)
        {
            if (name != null)
                juvName = name;
        }

        /**
         * @return Returns the jpoName.
         */
        public String getJpoName()
        {
            return jpoName;
        }

        /**
         * @return Returns the requestDate.
         */
        public Date getRequestDate()
        {
            return requestDate;
        }
        
        private void calculateRequestDateStatus(){
        	String toReturn="";
        	if(requestDate!=null){
        		GregorianCalendar todayDate=new GregorianCalendar();
        		todayDate.add(Calendar.DATE,-7);
        		if(requestDate.before(todayDate.getTime())){
        			todayDate.add(Calendar.DATE,-23);
        			if(requestDate.before(todayDate.getTime())){
        				toReturn=UIConstants.OVER_30_DAYS;
        			}
        			else{
        				toReturn=UIConstants.OVER_7_DAYS;
        			}
        		}
        	}
        	requestDateStatus=toReturn;
        }
        
        public String getRequestDateStatus(){
        	return requestDateStatus;
        }

        /**
         * @return Returns the requestTime.
         */
        public String getRequestTime()
        {
            if (requestDate != null)
            {
                requestTime = DateUtil.dateToString(requestDate, UIConstants.TIME24_FMT_1);

            }
            else
            {
                requestTime = "";
            }
            return requestTime;
        }

        /**
         * @param jpoName
         *            The jpoName to set.
         */
        public void setJpoName(String jpoName)
        {
            this.jpoName = jpoName;
        }

        /**
         * @param requestDate
         *            The requestDate to set.
         */
        public void setRequestDate(Date requestDate)
        {
            this.requestDate = requestDate;
            calculateRequestDateStatus();
        }

        /**
         * @param requestTime
         *            The requestTime to set.
         */
        public void setRequestTime(String requestTime)
        {
            this.requestTime = requestTime;

            if (requestTime != null && requestTime.length() > 0)
            {
                requestDate = DateUtil.stringToDate(requestTime, UIConstants.DATE_FMT_1);
            }
        }

        /**
         * @param string
         */
        public void setJuvNumber(String string)
        {
            if (string != null)
                juvNumber = string;
        }

        /**
         * @return
         */
        public String getAssessmentId()
        {
            return assessmentId;
        }

        /**
         * @param string
         */
        public void setAssessmentId(String string)
        {
            if (string != null)
                assessmentId = string;
        }

        public void clear()
        {
            assessmentId = "";
            juvNumber = "";
            juvName = new Name();
            listOfGuardians.clear();

            Guardian guardian = new Guardian();
            question = new TitleIVEQuestion();

            //To be completed By JPD Staff only
            initialOrderRemovalContainBestInterest = false;
            reasonableEffortsMadeWithin60DaysOfChildRemoval = false;
            courtOrderIncludeFindingChildCareAndPlacement = false;
            childMeetAFDCRequirement = false;

            isEligibleForMedicaid = false;
            isReceivingMedicaid = false;
            isEligibleForTitleIVe = false;
            isReceivingTitleIVe = false;

            comments = "";
        }

        public class TitleIVEQuestion
        {
            private Date dateTaken = new Date();

            //-- TAKEN FROM USER INPUT
            //Question 1
            private boolean isLegalResident = false;

            //Question 2
            private boolean oneParentIsStepparent = false;

            private boolean deathOrAbsence = false;

            private boolean incapacityOrDisabilityOfParent = false;

            private boolean primaryWageEarnerUnderemployment = false;

            private boolean pweWorkedLessThen100Hours = false;

            private boolean pweIncomeLessThanUnderemployedLimit = false;

            private String pweRelationshipToJuvenileId = "";

            private String pweRelationshipToJuvenile = "";

            private boolean pweWorkedSteadyLessThan100Hours = false;

            private boolean pweWorkedIrregularLessThan100HoursAvg = false;

            private double pweGrossMonthlyIncomeForOver100Hours = 0;

            //Question 3
            private boolean wasChildLivingWithParent = false;

            //Question 4
            private double afdcIncomeStepparentsMonthlyGross = 0;

            private double afdcIncomeStepparentWorkRelatedExpenses = 0;

            private double afdcIncomeStepparentOtherMonthlyIncome = 0;

            private double afdcIncomeStepparentMonthyPaymentsToDependent = 0;

            private double afdcIncomeStepparentMonthyAlimonyChildSupport = 0;

            private int afdcIncomeStepparentNoncertifiedCount = 0;

            // ---- CALCULATED, and will be saved as persistent data
            //Question 4
            private boolean afdcIncomeLimitsMet = false;

            private int afdcIncomeCertifiedGroupSize = 0;

            private int afdcIncomeCertifiedGroupParentsSize = 0;

            private double afdcIncomeCertifiedGroupLimit = 0;

            private double afdcIncomeStepparentCountableEarnedMonthy = 0;

            private double afdcIncomeStepparentTotalCountableMonthy = 0;

            private double afdcIncomeStepparentAllowanceAmount = 0;

            private double afdcIncomeStepparentAppliedIncome = 0;

            private double afdcIncomeTotalMonthy = 0;

            private double afdcIncomeTotalCountable = 0;

            //Question 5
            private boolean under10KLimit = false;

            //Question6
            private boolean childMeetsEligibilityCriteria = false;

            private Collection afdcIncomeWorksheetItems = new ArrayList();

            private String[] selectedInfoSourceId = null;

            private String infoSource = "";

            private BenefitsAssessmentCalculator beneCalc = null;

            public TitleIVEQuestion()
            {
            }

            public void clear()
            {
                isLegalResident = false;

                oneParentIsStepparent = false;
                deathOrAbsence = false;
                incapacityOrDisabilityOfParent = false;
                primaryWageEarnerUnderemployment = false;
                pweWorkedLessThen100Hours = false;
                pweIncomeLessThanUnderemployedLimit = false;
                pweRelationshipToJuvenileId = "";
                pweRelationshipToJuvenile = "";
                pweWorkedSteadyLessThan100Hours = false;
                pweWorkedIrregularLessThan100HoursAvg = false;
                pweGrossMonthlyIncomeForOver100Hours = 0;

                wasChildLivingWithParent = false;

                afdcIncomeStepparentsMonthlyGross = 0;
                afdcIncomeStepparentWorkRelatedExpenses = 0;
                afdcIncomeStepparentOtherMonthlyIncome = 0;

                afdcIncomeStepparentMonthyPaymentsToDependent = 0;
                afdcIncomeStepparentMonthyAlimonyChildSupport = 0;
                afdcIncomeStepparentNoncertifiedCount = 0;

                afdcIncomeLimitsMet = false;
                afdcIncomeCertifiedGroupSize = 0;
                afdcIncomeCertifiedGroupParentsSize = 0;
                afdcIncomeCertifiedGroupLimit = 0;
                afdcIncomeStepparentCountableEarnedMonthy = 0;
                afdcIncomeStepparentTotalCountableMonthy = 0;

                afdcIncomeStepparentAllowanceAmount = 0;
                afdcIncomeStepparentAppliedIncome = 0;
                afdcIncomeTotalMonthy = 0;
                afdcIncomeTotalCountable = 0;

                under10KLimit = false;

                childMeetsEligibilityCriteria = false;

            }

            /**
             * @return String
             */
            // 7 july 2006 - mjt - defect 32824 - changed text to mixed case
            // (Yes/No)
            public String getParentalDepExist()
            {
                if (deathOrAbsence || incapacityOrDisabilityOfParent || primaryWageEarnerUnderemployment)
                    return "Yes";
                else
                    return "No";
            }

            public Date getDateTaken()
            {
                return dateTaken;
            }

            public void setDateTaken(Date date)
            {
                dateTaken = date;
            }

            /**
             * @return
             */
            public String getIsOneParentIsStepparentDisp()
            {
                return UIUtil.getYesNo(this.oneParentIsStepparent, false);
            }

            /**
             * @return
             */
            public String getIsDeathOrAbsenceDisp()
            {
                return UIUtil.getYesNo(this.deathOrAbsence, false);
            }

            /**
             * @return
             */
            public String getIsIncapacityOrDisabilityOfParentDisp()
            {
                return UIUtil.getYesNo(this.incapacityOrDisabilityOfParent, false);
            }

            /**
             * @return
             */
            public String getIsLegalResidentDisp()
            {
                return UIUtil.getYesNo(this.isLegalResident, false);
            }

            /**
             * @return
             */
            public String getIsPrimaryWageEarnerUnderemploymentDisp()
            {
                return UIUtil.getYesNo(this.primaryWageEarnerUnderemployment, false);
            }

            public String getIsPweWorkedLessThen100HoursDisp()
            {
                return UIUtil.getYesNo(this.pweWorkedLessThen100Hours, false);
            }

            public String getIsPweIncomeLessThanUnderemployedLimitDisp()
            {
                return UIUtil.getYesNo(this.pweIncomeLessThanUnderemployedLimit, false);
            }

            /**
             * @return
             */
            public String getIsPweWorkedIrregularLessThan100HoursAvgDisp()
            {
                return UIUtil.getYesNo(this.pweWorkedIrregularLessThan100HoursAvg, false);
            }

            /**
             * @return
             */
            public String getIsPweWorkedSteadyLessThan100HoursDisp()
            {
                return UIUtil.getYesNo(this.pweWorkedSteadyLessThan100Hours, false);
            }

            public String getWasChildLivingWithParentDisp()
            {
                return UIUtil.getYesNo(this.wasChildLivingWithParent, false);
            }

            /**
             * @return
             */
            public Collection getAfdcIncomeWorksheetItems()
            {
                return afdcIncomeWorksheetItems;
            }

            /**
             * @return
             */
            public String getInfoSource()
            {
                return infoSource;
            }

            /**
             * @return
             */
            public BenefitsAssessmentCalculator getBeneCalc()
            {
                return beneCalc;
            }

            public void setBeneCalc(ProcessBenefitsAssessmentForm form)
            {
                if (form == null)
                    beneCalc = null;
                else
                    beneCalc = new BenefitsAssessmentCalculator(form);
            }

            /**
             * @return
             */
            public double getAfdcIncomeStepparentMonthyAlimonyChildSupport()
            {
                return afdcIncomeStepparentMonthyAlimonyChildSupport;
            }

            /**
             * @return
             */
            public double getAfdcIncomeStepparentMonthyPaymentsToDependent()
            {
                return afdcIncomeStepparentMonthyPaymentsToDependent;
            }

            /**
             * @return
             */
            public int getAfdcIncomeStepparentNoncertifiedCount()
            {
                return afdcIncomeStepparentNoncertifiedCount;
            }

            /**
             * @return
             */
            public double getAfdcIncomeStepparentOtherMonthlyIncome()
            {
                return afdcIncomeStepparentOtherMonthlyIncome;
            }

            /**
             * @return
             */
            public double getAfdcIncomeStepparentsMonthlyGross()
            {
                return afdcIncomeStepparentsMonthlyGross;
            }

            /**
             * @return
             */
            public double getAfdcIncomeStepparentWorkRelatedExpenses()
            {
                return afdcIncomeStepparentWorkRelatedExpenses;
            }

            /**
             * @return
             */
            public boolean isDeathOrAbsence()
            {
                return deathOrAbsence;
            }

            /**
             * @return
             */
            public boolean isIncapacityOrDisabilityOfParent()
            {
                return incapacityOrDisabilityOfParent;
            }

            /**
             * @return
             */
            public boolean isLegalResident()
            {
                return isLegalResident;
            }

            /**
             * @return
             */
            public boolean isOneParentIsStepparent()
            {
                return oneParentIsStepparent;
            }

            /**
             * @return
             */
            public boolean isPrimaryWageEarnerUnderemployment()
            {
                return primaryWageEarnerUnderemployment;
            }

            /**
             * @return
             */
            public double getPweGrossMonthlyIncomeForOver100Hours()
            {
                return pweGrossMonthlyIncomeForOver100Hours;
            }

            /**
             * @return
             */
            public boolean isPweIncomeLessThanUnderemployedLimit()
            {
                return pweIncomeLessThanUnderemployedLimit;
            }

            /**
             * @return
             */
            public String getPweRelationshipToJuvenile()
            {
                return pweRelationshipToJuvenile;
            }

            /**
             * @return
             */
            public String getPweRelationshipToJuvenileId()
            {
                return pweRelationshipToJuvenileId;
            }

            /**
             * @return
             */
            public boolean isPweWorkedIrregularLessThan100HoursAvg()
            {
                return pweWorkedIrregularLessThan100HoursAvg;
            }

            /**
             * @return
             */
            public boolean isPweWorkedLessThen100Hours()
            {
                return pweWorkedLessThen100Hours;
            }

            /**
             * @return
             */
            public boolean isPweWorkedSteadyLessThan100Hours()
            {
                return pweWorkedSteadyLessThan100Hours;
            }

            /**
             * @return
             */
            public String[] getSelectedInfoSourceId()
            {
                return selectedInfoSourceId;
            }

            /**
             * @return
             */
            public boolean isWasChildLivingWithParent()
            {
                return wasChildLivingWithParent;
            }

            /**
             * @param d
             */
            public void setAfdcIncomeStepparentMonthyAlimonyChildSupport(double d)
            {
                afdcIncomeStepparentMonthyAlimonyChildSupport = d;
            }

            /**
             * @param d
             */
            public void setAfdcIncomeStepparentMonthyPaymentsToDependent(double d)
            {
                afdcIncomeStepparentMonthyPaymentsToDependent = d;
            }

            /**
             * @param i
             */
            public void setAfdcIncomeStepparentNoncertifiedCount(int i)
            {
                afdcIncomeStepparentNoncertifiedCount = i;
            }

            /**
             * @param d
             */
            public void setAfdcIncomeStepparentOtherMonthlyIncome(double d)
            {
                afdcIncomeStepparentOtherMonthlyIncome = d;
            }

            /**
             * @param d
             */
            public void setAfdcIncomeStepparentsMonthlyGross(double d)
            {
                afdcIncomeStepparentsMonthlyGross = d;
            }

            /**
             * @param d
             */
            public void setAfdcIncomeStepparentWorkRelatedExpenses(double d)
            {
                afdcIncomeStepparentWorkRelatedExpenses = d;
            }

            /**
             * @param b
             */
            public void setDeathOrAbsence(boolean b)
            {
                deathOrAbsence = b;
            }

            /**
             * @param b
             */
            public void setIncapacityOrDisabilityOfParent(boolean b)
            {
                incapacityOrDisabilityOfParent = b;
            }

            /**
             * @param b
             */
            public void setLegalResident(boolean b)
            {
                isLegalResident = b;
            }

            /**
             * @param b
             */
            public void setOneParentIsStepparent(boolean b)
            {
                oneParentIsStepparent = b;
            }

            /**
             * @param b
             */
            public void setPrimaryWageEarnerUnderemployment(boolean b)
            {
                primaryWageEarnerUnderemployment = b;
            }

            /**
             * @param d
             */
            public void setPweGrossMonthlyIncomeForOver100Hours(double d)
            {
                pweGrossMonthlyIncomeForOver100Hours = d;
            }

            /**
             * @param b
             */
            public void setPweIncomeLessThanUnderemployedLimit(boolean b)
            {
                pweIncomeLessThanUnderemployedLimit = b;
            }

            /**
             * @param string
             */
            public void setPweRelationshipToJuvenile(String string)
            {
                pweRelationshipToJuvenile = string;
            }

            /**
             * @param string
             */
            public void setPweRelationshipToJuvenileId(String string)
            {
                pweRelationshipToJuvenileId = string;
            }

            /**
             * @param b
             */
            public void setPweWorkedIrregularLessThan100HoursAvg(boolean b)
            {
                pweWorkedIrregularLessThan100HoursAvg = b;
            }

            /**
             * @param b
             */
            public void setPweWorkedLessThen100Hours(boolean b)
            {
                pweWorkedLessThen100Hours = b;
            }

            /**
             * @param b
             */
            public void setPweWorkedSteadyLessThan100Hours(boolean b)
            {
                pweWorkedSteadyLessThan100Hours = b;
            }

            /**
             * @param strings
             */
            public void setSelectedInfoSourceId(String[] strings)
            {
                selectedInfoSourceId = strings;
            }

            /**
             * @param b
             */
            public void setWasChildLivingWithParent(boolean b)
            {
                wasChildLivingWithParent = b;
            }

            /**
             * @return
             */
            public double getAfdcIncomeCertifiedGroupLimit()
            {
                if (beneCalc != null)
                {
                    afdcIncomeCertifiedGroupLimit = beneCalc.getIncomeLimitForCertifiedGroup();
                }

                return afdcIncomeCertifiedGroupLimit;
            }

            /**
             * @return
             */
            public int getAfdcIncomeCertifiedGroupParentsSize()
            {
                if (beneCalc != null)
                {
                    afdcIncomeCertifiedGroupParentsSize = beneCalc.getParentsInCertifiedGroup();
                }

                return afdcIncomeCertifiedGroupParentsSize;
            }

            /**
             * @return
             */
            public int getAfdcIncomeCertifiedGroupSize()
            {
                if (beneCalc != null)
                {
                    afdcIncomeCertifiedGroupSize = beneCalc.getTotalInCertifiedGroup();
                }
                return afdcIncomeCertifiedGroupSize;
            }

            /**
             * @return
             */
            public boolean isAfdcIncomeLimitsMet()
            {
                if (beneCalc != null)
                {
                    afdcIncomeLimitsMet = beneCalc.isGrossIncomeBelowAFDCLimit();
                }
                return afdcIncomeLimitsMet;
            }

            /**
             * @return
             */
            public double getAfdcIncomeStepparentAllowanceAmount()
            {
                if (beneCalc != null)
                {
                    afdcIncomeStepparentAllowanceAmount = beneCalc.getStepparentNoncertifiedAllowance();
                }
                return afdcIncomeStepparentAllowanceAmount;
            }

            /**
             * @return
             */
            public double getAfdcIncomeStepparentAppliedIncome()
            {
                if (beneCalc != null)
                {
                    afdcIncomeStepparentAppliedIncome = beneCalc.getStepparentAppliedIncome();
                }
                return afdcIncomeStepparentAppliedIncome;
            }

            /**
             * @return
             */
            public double getAfdcIncomeStepparentCountableEarnedMonthy()
            {
                if (beneCalc != null)
                {
                    afdcIncomeStepparentCountableEarnedMonthy = beneCalc.getStepparentCountableMonthlyIncome();
                }
                return afdcIncomeStepparentCountableEarnedMonthy;
            }

            /**
             * @return
             */
            public double getAfdcIncomeStepparentTotalCountableMonthy()
            {
                if (beneCalc != null)
                {
                    afdcIncomeStepparentTotalCountableMonthy = beneCalc.getStepparentTotalMonthlyIncome();
                }
                return afdcIncomeStepparentTotalCountableMonthy;
            }

            /**
             * @return
             */
            public double getAfdcIncomeTotalCountable()
            {
                if (beneCalc != null)
                {
                    afdcIncomeTotalCountable = beneCalc.getTotalCountableIncome();
                }
                return afdcIncomeTotalCountable;
            }

            /**
             * @return
             */
            public double getAfdcIncomeTotalMonthy()
            {
                if (beneCalc != null)
                {
                    afdcIncomeTotalMonthy = beneCalc.getMonthlyGrossForCertifiedGroup();
                }
                return afdcIncomeTotalMonthy;
            }

            /**
             * @return
             */
            public boolean isChildMeetsEligibilityCriteria()
            {
                if (beneCalc != null)
                {
                    childMeetsEligibilityCriteria = beneCalc.childAppearsToMeetAFDCEligibility();
                }
                return childMeetsEligibilityCriteria;
            }

            /**
             * @return
             */
            public boolean isUnder10KLimit()
            {
                if (beneCalc != null)
                {
                    under10KLimit = beneCalc.isGroupUnder10kLimit();
                }
                return under10KLimit;
            }

            /**
             * @param d
             */
            public void setAfdcIncomeCertifiedGroupLimit(double d)
            {
                afdcIncomeCertifiedGroupLimit = d;
            }

            /**
             * @param i
             */
            public void setAfdcIncomeCertifiedGroupParentsSize(int i)
            {
                afdcIncomeCertifiedGroupParentsSize = i;
            }

            /**
             * @param i
             */
            public void setAfdcIncomeCertifiedGroupSize(int i)
            {
                afdcIncomeCertifiedGroupSize = i;
            }

            /**
             * @param b
             */
            public void setAfdcIncomeLimitsMet(boolean b)
            {
                afdcIncomeLimitsMet = b;
            }

            /**
             * @param d
             */
            public void setAfdcIncomeStepparentAllowanceAmount(double d)
            {
                afdcIncomeStepparentAllowanceAmount = d;
            }

            /**
             * @param d
             */
            public void setAfdcIncomeStepparentAppliedIncome(double d)
            {
                afdcIncomeStepparentAppliedIncome = d;
            }

            /**
             * @param d
             */
            public void setAfdcIncomeStepparentCountableEarnedMonthy(double d)
            {
                afdcIncomeStepparentCountableEarnedMonthy = d;
            }

            /**
             * @param d
             */
            public void setAfdcIncomeStepparentTotalCountableMonthy(double d)
            {
                afdcIncomeStepparentTotalCountableMonthy = d;
            }

            /**
             * @param d
             */
            public void setAfdcIncomeTotalCountable(double d)
            {
                afdcIncomeTotalCountable = d;
            }

            /**
             * @param d
             */
            public void setAfdcIncomeTotalMonthy(double d)
            {
                afdcIncomeTotalMonthy = d;
            }

            /**
             * @param collection
             */
            public void setAfdcIncomeWorksheetItems(Collection collection)
            {
                afdcIncomeWorksheetItems = collection;
            }

            /**
             * @param b
             */
            public void setChildMeetsEligibilityCriteria(boolean b)
            {
                childMeetsEligibilityCriteria = b;
            }

            /**
             * @param string
             */
            public void setInfoSource(String string)
            {
                infoSource = string;
            }

            /**
             * @param b
             */
            public void setUnder10KLimit(boolean b)
            {
                under10KLimit = b;
            }

        }

        /**
         * @return
         */
        public Guardian getGuardian()
        {
            return guardian;
        }

        /**
         * @return
         */
        public TitleIVEQuestion getQuestion()
        {
            return question;
        }

        /**
         * @param guardian
         */
        public void setGuardian(Guardian guardian)
        {
            this.guardian = guardian;
        }

        /**
         * @param question
         */
        public void setQuestion(TitleIVEQuestion question)
        {
            this.question = question;
        }

        /**
         * @return
         */
        public boolean isChildMeetAFDCRequirement()
        {
            return childMeetAFDCRequirement;
        }

        /**
         * @return
         */
        public boolean isCourtOrderIncludeFindingChildCareAndPlacement()
        {
            return courtOrderIncludeFindingChildCareAndPlacement;
        }

        /**
         * @return
         */
        public boolean isInitialOrderRemovalContainBestInterest()
        {
            return initialOrderRemovalContainBestInterest;
        }

        /**
         * @return
         */
        public boolean isReasonableEffortsMadeWithin60DaysOfChildRemoval()
        {
            return reasonableEffortsMadeWithin60DaysOfChildRemoval;
        }

        /**
         * @param b
         */
        public void setChildMeetAFDCRequirement(boolean b)
        {
            childMeetAFDCRequirement = b;
        }

        /**
         * @param b
         */
        public void setCourtOrderIncludeFindingChildCareAndPlacement(boolean b)
        {
            courtOrderIncludeFindingChildCareAndPlacement = b;
        }

        /**
         * @param b
         */
        public void setInitialOrderRemovalContainBestInterest(boolean b)
        {
            initialOrderRemovalContainBestInterest = b;
        }

        /**
         * @param b
         */
        public void setReasonableEffortsMadeWithin60DaysOfChildRemoval(boolean b)
        {
            reasonableEffortsMadeWithin60DaysOfChildRemoval = b;
        }

        //For display purpose only
        public String getChildMeetAFDCRequirementDisp()
        {
            return UIUtil.getYesNo(childMeetAFDCRequirement, false);
        }

        public String getCourtOrderIncludeFindingChildCareAndPlacementDisp()
        {
            return UIUtil.getYesNo(courtOrderIncludeFindingChildCareAndPlacement, false);
        }

        public String getInitialOrderRemovalContainBestInterestDisp()
        {
            return UIUtil.getYesNo(initialOrderRemovalContainBestInterest, false);
        }

        public String getReasonableEffortsMadeWithin60DaysOfChildRemovalDisp()
        {
            return UIUtil.getYesNo(reasonableEffortsMadeWithin60DaysOfChildRemoval, false);
        }

        /**
         * @return
         */
        public boolean isEligibleForMedicaid()
        {
            return isEligibleForMedicaid;
        }

        /**
         * @return
         */
        public boolean isEligibleForTitleIVe()
        {
            return isEligibleForTitleIVe;
        }

        /**
         * @return
         */
        public boolean isReceivingMedicaid()
        {
            return isReceivingMedicaid;
        }

        /**
         * @return
         */
        public boolean isReceivingTitleIVe()
        {
            return isReceivingTitleIVe;
        }

        /**
         * @param b
         */
        public void setEligibleForMedicaid(boolean b)
        {
            isEligibleForMedicaid = b;
        }

        /**
         * @param b
         */
        public void setEligibleForTitleIVe(boolean b)
        {
            isEligibleForTitleIVe = b;
        }

        /**
         * @param b
         */
        public void setReceivingMedicaid(boolean b)
        {
            isReceivingMedicaid = b;
        }

        /**
         * @param b
         */
        public void setReceivingTitleIVe(boolean b)
        {
            isReceivingTitleIVe = b;
        }

        //for display purpose only
        public String getEligibleForTitleIVeDisplay()
        {
            return UIUtil.getYesNo(isEligibleForTitleIVe, false);
        }

        public String getEligibleForMedicaidDisp()
        {
            return UIUtil.getYesNo(isEligibleForMedicaid, false);
        }

        public String getReceivingMedicaidDisp()
        {
            return UIUtil.getYesNo(isReceivingMedicaid, false);
        }

        public String getReceivingTitleIVeDisp()
        {
            return UIUtil.getYesNo(isReceivingTitleIVe, false);
        }

        /**
         * @return
         */
        public String getComments()
        {
            return comments;
        }

        /**
         * @param string
         */
        public void setComments(String string)
        {
            comments = string;
        }

        /**
         * @return
         */
        public String getCasefileId()
        {
            return casefileId;
        }

        /**
         * @param string
         */
        public void setCasefileId(String string)
        {
            casefileId = string;
        }

        /**
         * @return Returns the requesterName.
         */
        public String getRequesterName()
        {
            return requesterName;
        }

        /**
         * @param requesterName
         *            The requesterName to set.
         */
        public void setRequesterName(String requesterName)
        {
            this.requesterName = requesterName;
        }
    }

    public static class Guardian
    {

        private Name name = new Name();

        private String phoneNumber = "";

        private String phoneType = "";

        private String memberNumber = "";

        private String familyNumber = "";

        private String relationship = "";

        private String constellationMemberId = "";

        /**
         * @return
         */
        public Name getName()
        {
            return name;
        }

        /**
         * @return
         */
        public String getPhoneNumber()
        {
            return phoneNumber;
        }

        /**
         * @return
         */
        public String getPhoneType()
        {
            return phoneType;
        }

        /**
         * @param name
         */
        public void setName(Name name)
        {
            if (name != null)
                this.name = name;
        }

        /**
         * @param string
         */
        public void setPhoneNumber(String string)
        {
            if (string != null)
            {
                phoneNumber = UIUtil.formatPhoneNum(string);
            }
            else
                phoneNumber = "";
        }

        /**
         * @param string
         */
        public void setPhoneType(String string)
        {
            if (string != null)
                phoneType = string;
        }

        /**
         * @return
         */
        public String getMemberNumber()
        {
            return memberNumber;
        }

        /**
         * @return
         */
        public String getFamilyNumber()
        {
            return familyNumber;
        }

        /**
         * @return
         */
        public String getRelationship()
        {
            return relationship;
        }

        /**
         * @param string
         */
        public void setMemberNumber(String string)
        {
            if (string != null)
                memberNumber = string;
        }

        /**
         * @param string
         */
        public void setFamilyNumber(String string)
        {
            if (string != null)
                familyNumber = string;
        }

        /**
         * @param string
         */
        public void setRelationship(String string)
        {
            if (string != null)
                relationship = string;
        }

        public void clear()
        {
            name = new Name();
            phoneNumber = "";
            phoneType = "";
            memberNumber = "";
            relationship = "";
        }

        /**
         * @return Returns the constellationMemberNum.
         */
        public String getConstellationMemberId()
        {
            return constellationMemberId;
        }

        /**
         * @param constellationMemberNum
         *            The constellationMemberNum to set.
         */
        public void setConstellationMemberId(String constellationMemberNum)
        {
            this.constellationMemberId = constellationMemberNum;
        }
    }

    public static class IndividualIncomeDetermination implements Comparable
    {
		/* (non-Javadoc)
		 * @see java.lang.Comparable#compareTo(java.lang.Object)
		 */
		public int compareTo(Object aIncoming) {
			int compareVal=1;
			if(aIncoming==null || this.isJuvenile() || !(aIncoming instanceof IndividualIncomeDetermination)){
				compareVal=-1;
			}
			else{
				IndividualIncomeDetermination incomingIID=(IndividualIncomeDetermination)aIncoming;
				if(incomingIID.isJuvenile()){
					compareVal=1;
				}
				else{
					compareVal=this.getName().compareTo(incomingIID.getName());
				}
			}
			return compareVal;
		}
        private String memberId = "";

        private String name = "";

        private int age = 0;

        private String relationshipToJuvenileId = "";

        private String relationshipToJuvenile = "";

        private String comments = "";

        private String incomeSourceId = "";

        private String incomeSource = "";

        private double grossMonthyIncome = 0;
        
        private boolean juvenile=false;
        
        private String juvenileNumber="";

        public void clear()
        {
        	juvenileNumber="";
            memberId = "";
            name = "";
            age = 0;
            relationshipToJuvenileId = "";
            relationshipToJuvenile = "";
            comments = "";
            incomeSourceId = "";
            incomeSource = "";
            grossMonthyIncome = 0;
            juvenile=false;
        }

        /**
         * @return
         */
        public int getAge()
        {
            return age;
        }

        /**
         * @return
         */
        public String getComments()
        {
            return comments;
        }

       

        /**
         * @return
         */
        public String getIncomeSource()
        {
            incomeSource = CodeHelper.getCodeDescriptionByCode(new ProcessBenefitsAssessmentForm()
                    .getIncomeSourceCodeTable(), incomeSourceId);
            if (incomeSource == null)
                return "";
            else
                return incomeSource;

        }

        /**
         * @return
         */
        public String getIncomeSourceId()
        {
            return incomeSourceId;
        }

        /**
         * @return
         */
        public String getMemberId()
        {
            return memberId;
        }

        /**
         * @return
         */
        public String getName()
        {
            return name;
        }

        /**
         * @return
         */
        public String getRelationshipToJuvenile()
        {
            relationshipToJuvenile = CodeHelper.getCodeDescriptionByCode(new ProcessBenefitsAssessmentForm()
                    .getRelationshipsToJuvenileCodeTable(), relationshipToJuvenileId);
            if (relationshipToJuvenile == null)
                return relationshipToJuvenileId;
            else
                return relationshipToJuvenile;
        }
        
        public String getRelationshipToJuvenileAFDC()
        {
            relationshipToJuvenile = CodeHelper.getCodeDescription(
            		PDCodeTableConstants.RELATIONSHIP_JUVENILE_AFDC, relationshipToJuvenileId);
            if (relationshipToJuvenile == null)
                return relationshipToJuvenileId;
            else
                return relationshipToJuvenile;
        }

        /**
         * @return
         */
        public String getRelationshipToJuvenileId()
        {
            return relationshipToJuvenileId;
        }

        /**
         * @param i
         */
        public void setAge(int i)
        {
            age = i;
        }

        /**
         * @param string
         */
        public void setComments(String string)
        {
            comments = string;
        }

       

        /**
         * @param string
         */
        public void setIncomeSourceId(String string)
        {
            incomeSourceId = string;
        }

        /**
         * @param string
         */
        public void setMemberId(String string)
        {
            memberId = string;
        }

        /**
         * @param string
         */
        public void setName(String string)
        {
            name = string;
        }

        /**
         * @param string
         */
        public void setRelationshipToJuvenileId(String string)
        {
            relationshipToJuvenileId = string;
        }

		/**
		 * @return Returns the juvenile.
		 */
		public boolean isJuvenile() {
			boolean toReturn=false;
			if(juvenileNumber!=null && !juvenileNumber.equals("") && memberId!=null && !memberId.equals("") ){
				if(juvenileNumber.equals(memberId) && (relationshipToJuvenileId==null || relationshipToJuvenileId.equals(""))){
					toReturn=true;
				}
			}
			return toReturn;
		}
		/**
		 * @param juvenile The juvenile to set.
		 */
		public void setJuvenile(boolean juvenile) {
			this.juvenile = juvenile;
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
		/**
		 * @return Returns the grossMonthyIncome.
		 */
		public double getGrossMonthyIncome() {
			return grossMonthyIncome;
		}
		/**
		 * @param grossMonthyIncome The grossMonthyIncome to set.
		 */
		public void setGrossMonthyIncome(double grossMonthyIncome) {
			this.grossMonthyIncome = grossMonthyIncome;
		}
    }

    /**
     * @return
     */
    public FamilyConstellationMemberFinancialResponseEvent getGuardianFinancialInfo()
    {
        return guardianFinancialInfo;
    }

    /**
     * @return
     */
    public Collection getPreviousBenefitsAssessments()
    {
        return previousBenefitsAssessments;
    }

    /**
     * @param guardian
     */
    public void setGuardianFinancialInfo(FamilyConstellationMemberFinancialResponseEvent guardian)
    {
        guardianFinancialInfo = guardian;
    }

    /**
     * @param collection
     */
    public void setPreviousBenefitsAssessments(Collection collection)
    {
        previousBenefitsAssessments = collection;
    }

    /**
     * @return
     */
    public Collection getIncomeSourceCodeTable()
    {
        if (incomeSourceCodeTable == null || incomeSourceCodeTable.isEmpty())
        {
            incomeSourceCodeTable = CodeHelper.getCodes(PDCodeTableConstants.INCOME_SOURCE, true);
        }

        return incomeSourceCodeTable;
    }

    /**
     * @return
     */
    public Collection getRelationshipsToJuvenileCodeTable()
    {
        if (relationshipsToJuvenileCodeTable == null || relationshipsToJuvenileCodeTable.isEmpty())
            relationshipsToJuvenileCodeTable = CodeHelper.getRelationshipsToJuvenileCodes(true);
        return relationshipsToJuvenileCodeTable;
    }
    
    public Collection getRelationshipsToJuvenileAFDCCodeTable()
    {
    	return CodeHelper.getCodes(PDCodeTableConstants.RELATIONSHIP_JUVENILE_AFDC, true);
    }


    /**
     * @return
     */
    public Collection getBenefitsAssessmentInfoSourceCodeTable()
    {
        if (benefitsAssessmentInfoSourceCodeTable == null || benefitsAssessmentInfoSourceCodeTable.isEmpty())
        {
            /*
             * defect 31265 - mjt - 6-july-2006 - changed the following line:
             * benefitsAssessmentInfoSourceCodeTable =
             * CodeHelper.getBenefitsAssessmentSourceCodes(true); to the
             * following, so the selection is alpha sorted:
             */

            benefitsAssessmentInfoSourceCodeTable = CodeHelper.getCodes(
                    PDCodeTableConstants.BENEFITS_ASSESSMENT_SOURCE, true);
        }

        return benefitsAssessmentInfoSourceCodeTable;
    }

    /**
     * @return
     */
    public String getSummaryMode()
    {
        return summaryMode;
    }

    /**
     * @param string
     */
    public void setSummaryMode(String string)
    {
        summaryMode = string;
    }

    /**
     * @return
     */
    public boolean isReview()
    {
        return isReview;
    }

    /**
     * @param b
     */
    public void setReview(boolean b)
    {
        isReview = b;
    }

    /**
     * @return
     */
    public boolean isView()
    {
        return isView;
    }

    /**
     * @param b
     */
    public void setView(boolean b)
    {
        isView = b;
    }

    /**
     * @return
     */
    public String getTitleIVeOfficerId()
    {
        return titleIVeOfficerId;
    }

    /**
     * @return
     */
    public Name getTitleIVeOfficerName()
    {
        return titleIVeOfficerName;
    }

    /**
     * @param string
     */
    public void setTitleIVeOfficerId(String string)
    {
        titleIVeOfficerId = string;
    }

    /**
     * @param name
     */
    public void setTitleIVeOfficerName(Name name)
    {
        titleIVeOfficerName = name;
    }

    /**
     * @return
     */
    public Collection getPreviousJuvenileBenefits()
    {
        return previousJuvenileBenefits;
    }

    /**
     * @param collection
     */
    public void setPreviousJuvenileBenefits(Collection collection)
    {
        previousJuvenileBenefits = collection;
    }

    /**
     * @return
     */
    public String getCasefileId()
    {
        String casefileId = null;
        // TODO Auto-generated method stub
        return casefileId;
    }

    /**
     * @return Returns the employmentInfoList.
     */
    public Collection getEmploymentInfoList()
    {
        return employmentInfoList;
    }

    /**
     * @param employmentInfoList
     *            The employmentInfoList to set.
     */
    public void setEmploymentInfoList(Collection employmentInfoList)
    {
        this.employmentInfoList = employmentInfoList;
    }
}