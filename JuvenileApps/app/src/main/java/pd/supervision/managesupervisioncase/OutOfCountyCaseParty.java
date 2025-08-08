// C:\\views\\CommonSupervision\\app\\src\\pd\\criminalcase\\CaseParty.java
package pd.supervision.managesupervisioncase;

import pd.criminalcase.CaseParty;
import pd.criminalcase.CriminalCase;
import pd.supervision.Factory.OutOfCountyCaseFactory;

public class OutOfCountyCaseParty extends CaseParty
{

	/**
	 * @roseuid 4507099A023C
	 */
	public OutOfCountyCaseParty() {

	}

	/**
	 * Initialize class relationship to class pd.supervision.managesupervisioncase.OutOfCountyCase
	 * 
	 * @see CaseParty#initCriminalCase()
	 */
	protected void initCriminalCase()
	{
		if (criminalCase == null)
		{
			String caseId = getCriminalCaseId();

			criminalCase = (CriminalCase) new mojo.km.persistence.Reference(
					caseId,
					OutOfCountyCaseFactory.getClass(caseId.substring(3)),
						(mojo.km.persistence.PersistentObject) this,
						"criminalCase").getObject();
		}
	}
	
}
