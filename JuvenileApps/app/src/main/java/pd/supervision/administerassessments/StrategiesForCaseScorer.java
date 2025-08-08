package pd.supervision.administerassessments;

import java.util.Iterator;
import java.util.List;

import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import mojo.km.utilities.CollectionUtil;
import pd.codetable.Code;

/**
 * 
 * @roseuid 47ACD775007F
 */
public class StrategiesForCaseScorer extends PersistentObject
{
	private String questionNum;
	private int ansA;
	private int ansB;
	private int ansC;
	private int ansD;
	private int noAns;
	private int ansE;
	private int ansF;
	/**
	 * Properties for total
	 */
	private Code total = null;
	private String totalId;

	/**
	 * 
	 * @roseuid 47ACD775007F
	 */
	public StrategiesForCaseScorer()
	{
	}

	/**
	 * 
	 * @roseuid 479101BF0035
	 */
	public void getWeightsForSCSClassifications()
	{
		fetch();
	}

	/**
	 * 
	 * @return Returns the ansA.
	 */
	public int getAnsA()
	{
		fetch();
		return ansA;
	}

	/**
	 * 
	 * @param ansA The ansA to set.
	 */
	public void setAnsA(int ansA)
	{
		if (this.ansA != ansA)
		{
			markModified();
		}
		this.ansA = ansA;
	}

	/**
	 * 
	 * @return Returns the ansB.
	 */
	public int getAnsB()
	{
		fetch();
		return ansB;
	}

	/**
	 * 
	 * @param ansB The ansB to set.
	 */
	public void setAnsB(int ansB)
	{
		if (this.ansB != ansB)
		{
			markModified();
		}
		this.ansB = ansB;
	}

	/**
	 * 
	 * @return Returns the ansC.
	 */
	public int getAnsC()
	{
		fetch();
		return ansC;
	}

	/**
	 * 
	 * @param ansC The ansC to set.
	 */
	public void setAnsC(int ansC)
	{
		if (this.ansC != ansC)
		{
			markModified();
		}
		this.ansC = ansC;
	}

	/**
	 * 
	 * @return Returns the ansD.
	 */
	public int getAnsD()
	{
		fetch();
		return ansD;
	}

	/**
	 * 
	 * @param ansD The ansD to set.
	 */
	public void setAnsD(int ansD)
	{
		if (this.ansD != ansD)
		{
			markModified();
		}
		this.ansD = ansD;
	}

	/**
	 * 
	 * @return Returns the ansE.
	 */
	public int getAnsE()
	{
		fetch();
		return ansE;
	}

	/**
	 * 
	 * @param ansE The ansE to set.
	 */
	public void setAnsE(int ansE)
	{
		if (this.ansE != ansE)
		{
			markModified();
		}
		this.ansE = ansE;
	}

	/**
	 * 
	 * @return Returns the ansF.
	 */
	public int getAnsF()
	{
		fetch();
		return ansF;
	}

	/**
	 * 
	 * @param ansF The ansF to set.
	 */
	public void setAnsF(int ansF)
	{
		if (this.ansF != ansF)
		{
			markModified();
		}
		this.ansF = ansF;
	}

	/**
	 * 
	 * @return Returns the noAns.
	 */
	public int getNoAns()
	{
		fetch();
		return noAns;
	}

	/**
	 * 
	 * @param noAns The noAns to set.
	 */
	public void setNoAns(int noAns)
	{
		if (this.noAns != noAns)
		{
			markModified();
		}
		this.noAns = noAns;
	}

	/**
	 * 
	 * @return Returns the questionNum.
	 */
	public String getQuestionNum()
	{
		fetch();
		return questionNum;
	}

	/**
	 * 
	 * @param questionNum The questionNum to set.
	 */
	public void setQuestionNum(String questionNum)
	{
	    if (this.questionNum == null || !this.questionNum.equals(questionNum))
		{
			markModified();
		}
		this.questionNum = questionNum;
	}

	/**
	 * 
	 * @return Returns the total.
	 */
	public Code getTotal()
	{
		initTotal();
		return total;
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 */
	public void setTotalId(String totalId)
	{
		if (this.totalId == null || !this.totalId.equals(totalId))
		{
			markModified();
		}
		total = null;
		this.totalId = totalId;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 */
	public String getTotalId()
	{
		fetch();
		return totalId;
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initTotal()
	{
		if (total == null)
		{
			total = (Code) new mojo.km.persistence.Reference(totalId, Code.class).getObject();
		}
	}
	/**
	 * @param attrName
	 * @param attrValue
	 * @return
	 */
	public static List findAll(String attrName, String attrValue){
	    IHome home = new Home();
	    Iterator iter = home.findAll(attrName, attrValue, StrategiesForCaseScorer.class);
	    return CollectionUtil.iteratorToList(iter);
	}
	/**
	 * @return
	 */
	public static List findAll(){
	    IHome home = new Home();
	    Iterator iter = home.findAll(StrategiesForCaseScorer.class);
	    return CollectionUtil.iteratorToList(iter);
	}
	
	/**
	 * @param attrName
	 * @param attrValue
	 * @return
	 */
	public static StrategiesForCaseScorer find(String oid){
	    IHome home = new Home();
	    return (StrategiesForCaseScorer) home.find(oid, StrategiesForCaseScorer.class);
	}
}
