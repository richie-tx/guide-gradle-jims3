/**
 * 
 */
package pd.juvenilecase.riskanalysis;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import naming.PDConstants;

import messaging.riskanalysis.reply.CategoryResponseEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import mojo.km.util.CollectionUtil;
import pd.juvenilecase.riskanalysis.RiskFormula;
import pd.juvenilecase.riskanalysis.RiskResultGroup;

/** 
 * @author palcocer
 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class RiskCategory extends PersistentObject
{
	
	private String formulaId;
	private RiskFormula formula;
	private int riskResultGroupId;
    private RiskResultGroup riskResultGroup;
    private String version;
    private String categoryName;
    private String description;
    private String modificationReason;
    private Collection questions;
    
    public RiskCategory() 
    {
    	super();
	}
 
    public RiskCategory (RiskCategory category){
    	this.setCategoryName(category.getCategoryName());
    	this.setCreateTimestamp(new Timestamp(new Date().getTime()));
    	this.setDescription(category.getDescription());
    	this.setFormulaId(null);
    	this.setRiskResultGroupId(category.getRiskResultGroupId());
    	this.setVersion(category.getNextVersion());
    }
    
    private void initFormula() 
	{
		if (formula == null) 
		{
			formula = (RiskFormula) new mojo.km.persistence.Reference(String.valueOf(formulaId), RiskFormula.class).getObject();
		}
	}
	
	public String getFormulaId() 
	{
		fetch();
		return formulaId;
	}
	
	public void setFormulaId(String formulaId) 
	{
		if (this.formulaId == null || !this.formulaId.equals(formulaId))
		{
			markModified();
		}
		formula = null;
		this.formulaId = formulaId;
	}

	public RiskFormula getFormula() 
	{
		fetch();
		initFormula();
		return formula;
	}
	
	public void setFormula(RiskFormula formula)
	{
		if (this.formula == null || !this.formula.equals(formula)) 
		{
			markModified();
		}
		if (formula.getOID() == null) 
		{
			new mojo.km.persistence.Home().bind(formula);
		}
		setFormulaId(formula.getOID());
		this.formula = (RiskFormula) new mojo.km.persistence.Reference(formula).getObject();
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
	
	public String getVersion() 
	{
		fetch();
		return version;
	}

	public void setVersion(String string) 
	{
		if (this.version == null || !this.version.equals(string)) 
		{
			markModified();
		}
		version = string;
	}
	
	public String getCategoryName() 
	{
		fetch();
		return categoryName;
	}

	public void setCategoryName(String string) 
	{
		if (this.categoryName == null || !this.categoryName.equals(string)) 
		{
			markModified();
		}
		categoryName = string;
	}
	
	public String getDescription() 
	{
		fetch();
		return description;
	}

	public void setDescription(String string) 
	{
		if (this.description == null || !this.description.equals(string)) 
		{
			markModified();
		}
		description = string;
	}
	public String getModificationReason() 
	{
		fetch();
		return modificationReason;
	}

	public void setModificationReason(String string) 
	{
		if (this.modificationReason == null || !this.modificationReason.equals(string)) 
		{
			markModified();
		}
		modificationReason = string;
	}
	
	public static List findAll(String attributeName, String attributeValue){
		
		IHome home = new Home();
		Iterator iter = home.findAll(attributeName, attributeValue, RiskCategory.class);
		List <RiskCategory> categoryList = CollectionUtil.iteratorToList(iter);
		home = null;
		iter = null;
		return categoryList;
	}
	
	public static List findAll(IEvent anEvent){
		IHome home = new Home();
		Iterator iter = home.findAll(anEvent, RiskCategory.class);
		home = null;
		return CollectionUtil.iteratorToList(iter);
	}
	
	public CategoryResponseEvent getResponseEvent(){
		
		CategoryResponseEvent cre = new CategoryResponseEvent();
		cre.setCategoryName(this.getCategoryName());
		cre.setDescription(this.getDescription());
		if (this.getFormulaId() != null && !this.getFormulaId().equals(PDConstants.BLANK)){
			cre.setFormulaId(this.getFormulaId());
			RiskFormula rf = this.getFormula();
			cre.setFormulaStatusCd(rf.getStatusCd());
			cre.setUpdatable(rf.isUpdatable());
			List categories = RiskCategory.findAll("formulaId", this.getFormulaId());
			if (categories.size() > 1){
				cre.setTotalCategoriesTiedToFormulaGreaterThan1(true);
			} else {
				cre.setTotalCategoriesTiedToFormulaGreaterThan1(false);
			}
			rf = null;
		} else {
			cre.setFormulaStatusCd(PDConstants.BLANK);
			cre.setUpdatable(true);
			cre.setTotalCategoriesTiedToFormulaGreaterThan1(true);
		}
		cre.setModificationReason(this.getModificationReason());
		cre.setRiskResultGroupId(Integer.valueOf(this.getRiskResultGroupId()).toString());
		cre.setVersion(this.getVersion());
		cre.setCategoryId(this.getOID());
		cre.setEntryDate(this.getCreateTimestamp());
		cre.setModifyDate(this.getUpdateTimestamp());
		
		return cre;
	}
	
	public static RiskCategory find(String oid){
		
		IHome home = new Home();
		RiskCategory category = (RiskCategory) home.find(oid, RiskCategory.class);
		home = null;
		return(category);
	}
	
	private void initQuestions() 
	{
		if (questions == null) 
		{
			if (this.getOID() == null) 
			{
				new mojo.km.persistence.Home().bind(this);
			}
			questions = new mojo.km.persistence.ArrayList(
					RiskQuestions.class,
					"riskCategoryId", "" + getOID());
		}
	}

	public Collection getQuestions()
	{
		initQuestions();
		return questions;
	}

	public void insertQuestions(
			RiskQuestions anObject)
	{
		initQuestions();
		questions.add(anObject);
	}

	public void removeQuestions(
			RiskQuestions anObject)
	{
		initQuestions();
		questions.remove(anObject);
	}

	public void clearQuestions() 
	{
		questions = null;
	}
	
	public String getNextVersion() {
		
		int newVersionNum = 1;
		
		if (this.version != null && !this.version.equals(PDConstants.BLANK)){
			int thisVersionNum = Integer.parseInt(this.version);
			newVersionNum = thisVersionNum + 1;
		}
		
		return Integer.toString(newVersionNum);
	}

}