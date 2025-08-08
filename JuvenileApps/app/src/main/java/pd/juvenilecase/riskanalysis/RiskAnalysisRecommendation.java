/**
 * 
 */
package pd.juvenilecase.riskanalysis;

import java.util.Iterator;

import pd.juvenilecase.riskanalysis.RiskResultGroup;
import pd.juvenilecase.riskanalysis.RiskRecommendation;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

/** 

 * @author palcocer
 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class RiskAnalysisRecommendation extends PersistentObject 
{

	private int riskAnalysisId;
	private RiskAnalysis riskAnalysis;
	private int recommendationId;
	private RiskRecommendation recommendation;
	private int riskResultGroupId;
	private RiskResultGroup riskResultGroup;

	public RiskAnalysisRecommendation() 
	{
		
	}
	
	public static RiskAnalysisRecommendation find(String riskAnalyRecom_Id) 
	{
		IHome home = new Home();
		return (RiskAnalysisRecommendation) home.find(riskAnalyRecom_Id, RiskAnalysisRecommendation.class);		
	}
	
	public Iterator findAll() 
	{
		IHome home = new Home();
		return home.findAll(RiskAnalysisRecommendation.class);
	}
	
	static public Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		return home.findAll(event, RiskAnalysisRecommendation.class);
	}
	
	public static Iterator findAllByAttributeName(String attributeName, String attributeValue) 
    {
		IHome home = new Home();
		return home.findAll(attributeName, attributeValue, RiskAnalysisRecommendation.class);
    }
	
	private void initRiskAnalysis() 
	{
		if (riskAnalysis == null) 
		{
			riskAnalysis = (RiskAnalysis) new mojo.km.persistence.Reference(String.valueOf(riskAnalysisId), RiskAnalysis.class).getObject();
		}
	}
	
	public int getRiskAnalysisId() 
	{
		fetch();
		return riskAnalysisId;
	}
	
	public void setRiskAnalysisId(int riskAnalysisId) 
	{
		if (this.riskAnalysisId != riskAnalysisId) 
		{
			markModified();
		}
		riskAnalysis = null;
		this.riskAnalysisId = riskAnalysisId;
	}

	public RiskAnalysis getRiskAnalysis() 
	{
		fetch();
		initRiskAnalysis();
		return riskAnalysis;
	}
	
	public void setRiskAnalysis(RiskAnalysis riskAnalysis)
	{
		if (this.riskAnalysis == null || !this.riskAnalysis.equals(riskAnalysis)) 
		{
			markModified();
		}
		if (riskAnalysis.getOID() == null) 
		{
			new mojo.km.persistence.Home().bind(riskAnalysis);
		}
		setRiskAnalysisId(Integer.parseInt(riskAnalysis.getOID()));
		this.riskAnalysis = (RiskAnalysis) new mojo.km.persistence.Reference(riskAnalysis).getObject();
	}
	
	private void initRecommendation() 
	{
		if (recommendation == null) 
		{
			recommendation = (RiskRecommendation) new mojo.km.persistence.Reference(String.valueOf(recommendationId), RiskRecommendation.class).getObject();
		}
	}
	
	public int getRecommendationId() 
	{
		fetch();
		return recommendationId;
	}
	
	public void setRecommendationId(int recommendationId) 
	{
		if (this.recommendationId != recommendationId) 
		{
			markModified();
		}
		recommendation = null;
		this.recommendationId = recommendationId;
	}

	public RiskRecommendation getRecommendation() 
	{
		fetch();
		initRecommendation();
		return recommendation;
	}
	
	public void setRecommendation(RiskRecommendation recommendation)
	{
		if (this.recommendation == null || !this.recommendation.equals(recommendation)) 
		{
			markModified();
		}
		if (recommendation.getOID() == null) 
		{
			new mojo.km.persistence.Home().bind(recommendation);
		}
		setRecommendationId(Integer.parseInt(recommendation.getOID()));
		this.recommendation = (RiskRecommendation) new mojo.km.persistence.Reference(recommendation).getObject();
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
	
	public void setRiskResultGroupId(int resultGroupId) 
	{
		if (this.riskResultGroupId != resultGroupId) 
		{
			markModified();
		}
		riskResultGroup = null;
		this.riskResultGroupId = resultGroupId;
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
}