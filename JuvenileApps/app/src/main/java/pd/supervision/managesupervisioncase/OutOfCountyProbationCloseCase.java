package pd.supervision.managesupervisioncase;

/**
 * 
 * @author cc_bjangay
 *
 */
public class OutOfCountyProbationCloseCase extends OutOfCountyProbationCase
{
//	created this class to override the save callback query for OOC(CSCD).CloseCase/UpdateCaseClosure uses this entity 
//	to save closure information and invokes P.J2CASCA.U2.CASE.SUMMARY.
	
	private String legacyJZTActionIndicator;
	private String legacyT30ActionIndicator;
	private String receivingCounty;
	
	public String getLegacyJZTActionIndicator() {
		return legacyJZTActionIndicator;
	}
	public String getLegacyT30ActionIndicator() {
		return legacyT30ActionIndicator;
	}
	public String getReceivingCounty() {
		return receivingCounty;
	}
	public void setLegacyJZTActionIndicator(String legacyJZTActionIndicator) {
		if (this.legacyJZTActionIndicator == null || !this.legacyJZTActionIndicator.equals(legacyJZTActionIndicator)){
			markModified();
		}
		this.legacyJZTActionIndicator = legacyJZTActionIndicator;
	}
	public void setLegacyT30ActionIndicator(String legacyT30ActionIndicator) {
		if (this.legacyT30ActionIndicator == null || !this.legacyT30ActionIndicator.equals(legacyT30ActionIndicator)){
			markModified();
		}

		this.legacyT30ActionIndicator = legacyT30ActionIndicator;
	}
	public void setReceivingCounty(String receivingCounty) {
		if (this.receivingCounty == null || !this.receivingCounty.equals(receivingCounty)){
			markModified();
		}
		
		this.receivingCounty = receivingCounty;
	}


}
