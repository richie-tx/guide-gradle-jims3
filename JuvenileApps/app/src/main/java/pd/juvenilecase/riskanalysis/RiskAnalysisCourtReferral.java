package pd.juvenilecase.riskanalysis;

import java.util.Date;
import java.util.Iterator;
import mojo.km.persistence.PersistentObject;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;

public class RiskAnalysisCourtReferral extends PersistentObject {
	
	private int riskAnalysisId;
	private RiskAnalysis riskAnalysis = null;
	private Date enteredDate;
	private int collateralVisits; 
    private int face2FaceVisits;
    private String courtDispositionTJPC;
    private String jjsCourtDecision;
    private String jjsCourtDisposition;

	public RiskAnalysisCourtReferral() 
	{
		
	}
	
	public static RiskAnalysisCourtReferral find(String riskAnalysisCourtReferral_Id) 
	{
		IHome home = new Home();
		return (RiskAnalysisCourtReferral) home.find(riskAnalysisCourtReferral_Id, RiskAnalysisCourtReferral.class);		
	}
	
	public Iterator findAll() 
	{
		IHome home = new Home();
		return home.findAll(RiskAnalysisCourtReferral.class);
	}
	
	static public Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		return home.findAll(event, RiskAnalysisCourtReferral.class);
	}
	
	public static Iterator findAllByAttributeName(String attributeName, String attributeValue) 
    {
		IHome home = new Home();
		return home.findAll(attributeName, attributeValue, RiskAnalysisCourtReferral.class);
    }

	public void setRiskAnalysisId(int riskAnalysisId) 
	{
		if (this.riskAnalysisId != riskAnalysisId) {
			markModified();
		}
		riskAnalysis = null;
		this.riskAnalysisId = riskAnalysisId;
	}
	
	public int getRiskAnalysisId() 
	{
		fetch();
		return riskAnalysisId;
	}
	
	private void initRiskAnalysis() 
	{
		if (riskAnalysis == null) {
			riskAnalysis = (RiskAnalysis) new mojo.km.persistence.Reference(String.valueOf(riskAnalysisId), RiskAnalysis.class).getObject();
		}
	}
	
	public RiskAnalysis getRiskAnalysis()
	{
		initRiskAnalysis();
		return riskAnalysis;
	}
	
	public void setRiskAnalysis(RiskAnalysis riskAnalysis)
	{
		if (this.riskAnalysis == null || !this.riskAnalysis.equals(riskAnalysis)) 
		{
			markModified();
		}
		if (riskAnalysis.getOID() == null) {
			new mojo.km.persistence.Home().bind(riskAnalysis);
		}
		setRiskAnalysisId(Integer.parseInt(riskAnalysis.getOID()));
		this.riskAnalysis = (RiskAnalysis) new mojo.km.persistence.Reference(riskAnalysis).getObject();
	}
	
	public Date getEnteredDate()
	{
		fetch();
		return enteredDate;
	}

	public void setEnteredDate(Date date) 
	{
		if (this.enteredDate == null || !this.enteredDate.equals(date)) 
		{
			markModified();
		}
		enteredDate = date;
	}

	public void setJjsCourtDisposition(String string) 
	{
		if (this.jjsCourtDisposition == null || !this.jjsCourtDisposition.equals(string)) 
		{
			markModified();
		}
		jjsCourtDisposition = string;
	}

	public String getJjsCourtDisposition() 
	{
		fetch();
		return jjsCourtDisposition;
	}

	public void setJjsCourtDecision(String string) 
	{
		if (this.jjsCourtDecision == null || !this.jjsCourtDecision.equals(string)) 
		{
			markModified();
		}
		jjsCourtDecision = string;
	}

	public String getJjsCourtDecision() 
	{
		fetch();
		return jjsCourtDecision;
	}

	public void setCourtDispositionTJPC(String string) 
	{
		if (this.courtDispositionTJPC == null || !this.courtDispositionTJPC.equals(string)) 
		{
			markModified();
		}
		courtDispositionTJPC = string;
	}

	public String getCourtDispositionTJPC() 
	{
		fetch();
		return courtDispositionTJPC;
	}

	public void setFace2FaceVisits(int i) 
	{
		if (this.face2FaceVisits != i) 
		{
			markModified();
		}
		face2FaceVisits = i;
	}

	public int getFace2FaceVisits() 
	{
		fetch();
		return face2FaceVisits;
	}

	public void setCollateralVisits(int i) 
	{
		if (this.collateralVisits != i) 
		{
			markModified();
		}
		collateralVisits = i;		
	}

	public int getCollateralVisits() 
	{
		fetch();
		return collateralVisits;
	}

}
