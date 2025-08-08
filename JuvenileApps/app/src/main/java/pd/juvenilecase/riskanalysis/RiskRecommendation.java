package pd.juvenilecase.riskanalysis;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import pd.codetable.ICodetable;
import messaging.riskanalysis.reply.FormulaRecommendationResponseEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

public class RiskRecommendation extends PersistentObject implements ICodetable 
{
	
	private String assessmentType;
	private boolean custody;
	private int maxValue;
	private int minValue;
	private String recommendation;
	private int riskResultGroupId;
	private RiskResultGroup riskResultGroup;
	private String riskFormulaId;
	private RiskFormula riskFormula;
	private String recommendationName;


	public RiskRecommendation() 
	{
	}
	
	public RiskRecommendation(RiskRecommendation riskRecommendation){
		this.setAssessmentType(riskRecommendation.getAssessmentType());
		this.setCustody(riskRecommendation.isCustody());
		this.setMaxValue(riskRecommendation.getMaxValue());
		this.setMinValue(riskRecommendation.getMinValue());
		this.setRiskResultGroupId(riskRecommendation.getRiskResultGroupId());
		this.setCreateTimestamp(new Timestamp(new Date().getTime()));
		this.setRiskFormulaId(null);
		this.setRecommendationName(riskRecommendation.getRecommendationName());
		this.setRecommendation(riskRecommendation.getRecommendation());
	}
	public static RiskRecommendation find(String riskRecommend_ID) 
	{
		IHome home = new Home();
		return (RiskRecommendation) home.find(riskRecommend_ID, RiskRecommendation.class);		
	}
	
	public Iterator findAll() 
	{
		IHome home = new Home();
		return home.findAll(RiskRecommendation.class);
	}
	
	static public Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		return home.findAll(event, RiskRecommendation.class);
	}

	public static Iterator findAllByAssessmentType(String attributeName, String attributeValue) 
	{
		IHome home = new Home();
		return home.findAll(attributeName, attributeValue,
				RiskRecommendation.class);
	}
	
	public static Iterator findAll(String attributeName, String attributeValue){
		IHome home = new Home();
		return home.findAll(attributeName, attributeValue,
				RiskRecommendation.class);
	}

	public String getAssessmentType() 
	{
		fetch();
		return assessmentType;
	}

	public int getMaxValue() 
	{
		fetch();
		return maxValue;
	}

	public int getMinValue() 
	{
		fetch();
		return minValue;
	}

	public String getRecommendation() 
	{
		fetch();
		return recommendation;
	}

	public void setAssessmentType(String string) 
	{
		if (this.assessmentType == null || !this.assessmentType.equals(string)) {
			markModified();
		}
		assessmentType = string;
	}

	public void setMaxValue(int i) 
	{
		if (this.maxValue != i) {
			markModified();
		}
		maxValue = i;
	}

	public void setMinValue(int i) 
	{
		if (this.minValue != i) {
			markModified();
		}
		minValue = i;
	}

	public void setRecommendation(String string) 
	{
		if (this.recommendation == null || !this.recommendation.equals(string)) {
			markModified();
		}
		recommendation = string;
	}

	public boolean isCustody() 
	{
		fetch();
		return custody;
	}

	public void setCustody(boolean b) 
	{
		if (this.custody != b) {
			markModified();
		}
		custody = b;
	}

	public void inActivate() {
		// TODO Auto-generated method stub
	}


	private void initRiskResultGroup() 
	{
		if (riskResultGroup == null) 
		{
			riskResultGroup = (RiskResultGroup) new mojo.km.persistence.Reference(String.valueOf(riskResultGroupId), RiskResultGroup.class).getObject();
		}
	}
	
	public int getRiskResultGroupId() 
	{
		fetch();
		return riskResultGroupId;
	}
	
	public void setRiskResultGroupId(int riskResultGroupId) 
	{
		if (this.riskResultGroupId != riskResultGroupId) 
		{
			markModified();
		}
		riskResultGroup = null;
		this.riskResultGroupId = riskResultGroupId;
	}

	public RiskResultGroup getRiskResultGroup() 
	{
		fetch();
		initRiskResultGroup();
		return riskResultGroup;
	}
	
	public void setRiskResultGroup(RiskResultGroup riskResultGroup)
	{
		if (this.riskResultGroup == null || !this.riskResultGroup.equals(riskResultGroup)) 
		{
			markModified();
		}
		if (riskResultGroup.getOID() == null) 
		{
			new mojo.km.persistence.Home().bind(riskResultGroup);
		}
		setRiskResultGroupId(Integer.parseInt(riskResultGroup.getOID()));
		this.riskResultGroup = (RiskResultGroup) new mojo.km.persistence.Reference(riskResultGroup).getObject();
	}
	
	private void initRiskFormula() 
	{
		if (riskFormula == null) 
		{
			riskFormula = (RiskFormula) new mojo.km.persistence.Reference(riskFormulaId, RiskFormula.class).getObject();
		}
	}
	
	public String getRiskFormulaId() 
	{
		fetch();
		return riskFormulaId;
	}
	
	public void setRiskFormulaId(String riskFormulaId) 
	{
		if (this.riskFormulaId == null || !this.riskFormulaId.equals(riskFormulaId)) 
		{
			markModified();
		}
		riskFormula = null;
		this.riskFormulaId = riskFormulaId;
	}

	public RiskFormula getRiskFormula() 
	{
		fetch();
		initRiskFormula();
		return riskFormula;
	}
	
	public void setRiskFormula(RiskFormula riskFormula)
	{
		if (this.riskFormula == null || !this.riskFormula.equals(riskFormula)) 
		{
			markModified();
		}
		if (riskFormula.getOID() == null) 
		{
			new mojo.km.persistence.Home().bind(riskFormula);
		}
		setRiskFormulaId(riskFormula.getOID());
		this.riskFormula = (RiskFormula) new mojo.km.persistence.Reference(riskFormula).getObject();
	}
	public String getRecommendationName() {
		fetch();
		return recommendationName;
	}

	public void setRecommendationName(String recommendationName) {
		if (this.recommendationName == null || !this.recommendationName.equals(recommendationName)) {
			markModified();
		}
		this.recommendationName = recommendationName;
	}

	public FormulaRecommendationResponseEvent getResponseEvent(){
		FormulaRecommendationResponseEvent re = new FormulaRecommendationResponseEvent();
		re.setAssessmentTypeName(this.getAssessmentType());
		re.setCustody(this.isCustody());
		re.setEntryDate(this.getCreateTimestamp());
		re.setFormulaId(this.getRiskFormulaId());
		re.setMaxScore(this.getMaxValue());
		re.setMinScore(this.getMinValue());
		re.setRecommendationName(this.getRecommendationName());
		re.setResultGroup(Integer.toString(this.getRiskResultGroupId()));
		re.setResultGroupDisplayDesc(this.getRiskResultGroup().getDisplayDescription());
		re.setRecommendationId(this.getOID());
		re.setRecommendationDesc(this.getRecommendation());
		return re;
		
	}
}
