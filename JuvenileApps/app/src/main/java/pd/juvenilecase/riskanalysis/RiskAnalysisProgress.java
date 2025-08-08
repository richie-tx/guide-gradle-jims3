package pd.juvenilecase.riskanalysis;

import java.util.Iterator;

import naming.RiskAnalysisConstants;

import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import pd.juvenilecase.rules.RuleGroupConditionView;

/**
 * @roseuid 433D86C400CE
 */
public class RiskAnalysisProgress extends RiskAnalysis
{
	/**
	 * Properties for riskAnalysis
	 * 
	 * @referencedType pd.juvenilecase.RiskAnalysis
	 * @detailerDoNotGenerate false
	 */
	private RiskAnalysis riskAnalysis = null;

	private int totalFamilyRelationship;
	private int totalSupervisionRules;
	private int totalSchoolAttendance;
	private String comments;
	private int totalCurrentAttitude;
	private int totalSchoolBehavior;
	private int supervisionNumber;
	private int totalSupervision;

	private int supervisionMonth;
	private String riskAnalysisId;

	/**
	 * @roseuid 433D86C400CE
	 */
	public RiskAnalysisProgress()
	{
	}

	/**
	 * @return
	 */
	public int getSupervisionMonth()
	{
		fetch();
		return supervisionMonth;
	}

	/**
	 * @return
	 */
	public int getSupervisionNumber()
	{
		fetch();
		return supervisionNumber;
	}

	/**
	 * @return
	 */
	public int getTotalCurrentAttitude()
	{
		fetch();
		return totalCurrentAttitude;
	}

	/**
	 * @return
	 */
	public int getTotalFamilyRelationship()
	{
		fetch();
		return totalFamilyRelationship;
	}

	/**
	 * @return
	 */
	public int getTotalSchoolAttendance()
	{
		fetch();
		return totalSchoolAttendance;
	}

	/**
	 * @return
	 */
	public int getTotalSchoolBehavior()
	{
		fetch();
		return totalSchoolBehavior;
	}

	/**
	 * @return
	 */
	public int getTotalSupervision()
	{
		fetch();
		return totalSupervision;
	}

	/**
	 * @return
	 */
	public int getTotalSupervisionRules()
	{
		fetch();
		return totalSupervisionRules;
	}

	/**
	 * @param i
	 */
	public void setSupervisionMonth(int i)
	{
		if( this.supervisionMonth != i )
		{
			markModified();
		}
		supervisionMonth = i;
	}

	/**
	 * @param i
	 */
	public void setSupervisionNumber(int i)
	{
		if( this.supervisionNumber != i )
		{
			markModified();
		}
		supervisionNumber = i;
	}

	/**
	 * @param i
	 */
	public void setTotalCurrentAttitude(int i)
	{
		if( this.totalCurrentAttitude != i )
		{
			markModified();
		}
		totalCurrentAttitude = i;
	}

	/**
	 * @param i
	 */
	public void setTotalFamilyRelationship(int i)
	{
		if( this.totalFamilyRelationship != i )
		{
			markModified();
		}
		totalFamilyRelationship = i;
	}

	/**
	 * @param i
	 */
	public void setTotalSchoolAttendance(int i)
	{
		if( this.totalSchoolAttendance != i )
		{
			markModified();
		}
		totalSchoolAttendance = i;
	}

	/**
	 * @param i
	 */
	public void setTotalSchoolBehavior(int i)
	{
		if( this.totalSchoolBehavior != i )
		{
			markModified();
		}
		totalSchoolBehavior = i;
	}

	/**
	 * @param i
	 */
	public void setTotalSupervision(int i)
	{
		if( this.totalSupervision != i )
		{
			markModified();
		}
		totalSupervision = i;
	}

	/**
	 * @param i
	 */
	public void setTotalSupervisionRules(int i)
	{
		if( this.totalSupervisionRules != i )
		{
			markModified();
		}
		totalSupervisionRules = i;
	}

	/**
	 * Set the reference value to class :: pd.juvenilecase.RiskAnalysis
	 */
	public void setRiskAnalysisId(String riskAnalysisId)
	{
		if( this.riskAnalysisId == null || !this.riskAnalysisId.equals(riskAnalysisId) )
		{
			markModified();
		}
		riskAnalysis = null;
		this.riskAnalysisId = riskAnalysisId;
	}

	/**
	 * Get the reference value to class :: pd.juvenilecase.RiskAnalysis
	 */
	public String getRiskAnalysisId()
	{
		fetch();
		return riskAnalysisId;
	}

	/**
	 * Initialize class relationship to class pd.juvenilecase.RiskAnalysis
	 */
	private void initRiskAnalysis()
	{
		if( riskAnalysis == null )
		{
			riskAnalysis = (RiskAnalysis)
				new mojo.km.persistence.Reference(
						riskAnalysisId, RiskAnalysis.class).getObject();
		}
	}

	/**
	 * Gets referenced type pd.juvenilecase.RiskAnalysis
	 */
	public RiskAnalysis getRiskAnalysis()
	{
		initRiskAnalysis();
		return riskAnalysis;
	}

	/**
	 * set the type reference for class member riskAnalysis
	 */
	public void setRiskAnalysis(RiskAnalysis riskAnalysis)
	{
		if( this.riskAnalysis == null || !this.riskAnalysis.equals(riskAnalysis) )
		{
			markModified();
		}
		
		if( riskAnalysis.getOID() == null )
		{
			new mojo.km.persistence.Home().bind(riskAnalysis);
		}
		
		setRiskAnalysisId("" + riskAnalysis.getOID());
		this.riskAnalysis = (RiskAnalysis)
				new mojo.km.persistence.Reference(riskAnalysis).getObject();
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
	 * @param string
	 */
	public static int getSupervisionRulesTotal(String casefileID)
	{
		int totalRules = 0;

		Iterator<RuleGroupConditionView> rulesIterator = RuleGroupConditionView.findAll("casefileId", casefileID);
		while( rulesIterator.hasNext() )
		{
			RuleGroupConditionView ruleView = rulesIterator.next();
			if( ruleView.getCompletionStatusId().equalsIgnoreCase("N") )
			{ // check the actual code for non-compliant
				totalRules += 4;
			}
		}
		
		return totalRules;
	}

	/**
	 * @param string
	 * @param string
	 */
	public static Iterator findAll(String attributeName, String attributeValue)
	{
		return new Home().findAll(attributeName, attributeValue, RiskAnalysisProgress.class);
	}

	/**
	 * @param string
	 * @param string
	 */
	public static RiskAnalysisProgress getRiskAnalysisProgressByRiskAnalysisId(String riskAnalysisId)
	{
		RiskAnalysisProgress pro = null;

		Iterator<RiskAnalysisProgress> iter = findAll("riskAnalysisId", riskAnalysisId);
		while( iter.hasNext() )
		{
			pro = iter.next();
		}
		
		return pro;
	}
	static public RiskAnalysisProgress findByRiskAnalysisId(String attributeValue) {
		IHome home = new Home();
		return (RiskAnalysisProgress)home.find(RiskAnalysisConstants.RISK_ANALYSIS_ID, attributeValue, RiskAnalysisProgress.class);
	}
}
