/**
 * 
 */
package pd.juvenilecase.riskanalysis;

import java.util.Iterator;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import pd.juvenilecase.riskanalysis.RiskResultGroup;

/** 
 * @author palcocer
 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class RiskFinalScore extends PersistentObject 
{

	private int finalScore;
	private int riskAnalysisId;
	private RiskAnalysis riskAnalysis;
	private int riskResultGroupId;
	private RiskResultGroup riskResultGroup;
	
	public RiskFinalScore() 
	{
		
	}
	
	public static RiskFinalScore find(String riskAnalyFnScr_Id) 
	{
		IHome home = new Home();
		return (RiskFinalScore) home.find(riskAnalyFnScr_Id, RiskFinalScore.class);		
	}
	
	public Iterator findAll() 
	{
		IHome home = new Home();
		return home.findAll(RiskFinalScore.class);
	}
	
	static public Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		return home.findAll(event, RiskFinalScore.class);
	}
	
	public static Iterator findAllByAttributeName(String attributeName, String attributeValue) 
    {
		IHome home = new Home();
		return home.findAll(attributeName, attributeValue, RiskFinalScore.class);
    }

	public int getFinalScore() 
	{
		fetch();
		return finalScore;
	}

	public void setFinalScore(int i) 
	{
		if (this.finalScore != i) 
		{
			markModified();
		}
		finalScore = i;
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