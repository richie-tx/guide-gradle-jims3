/*
 * Created on Jul 10, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package mapping.criminal;

import messaging.codetable.GetJuvenileMedicationTypeCodesEvent;
import mojo.km.messaging.IEvent;

/**
 * @author cc_vnarsingoju
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class SearchMedicationTypeCodeWhereClause {

	public String getSearchMedicationTypeCodeClause(IEvent anEvent) {
		GetJuvenileMedicationTypeCodesEvent mev = (GetJuvenileMedicationTypeCodesEvent) anEvent;
		StringBuffer buf = new StringBuffer();
		String beginWhereClause = " ";

		if (mev.getMedicationTypeId() != null && mev.getMedicationTypeId().trim().indexOf("*") != -1) {
			buf.append("MEDICATION_ID LIKE UPPER('" + mev.getMedicationTypeId().trim().replace('*', '%') + "%')");
		} else if (mev.getMedicationTypeId() != null && !"".equals(mev.getMedicationTypeId().trim())
				&& !"*".equals(mev.getMedicationTypeId())) {
			buf.append("MEDICATION_ID LIKE UPPER('" + mev.getMedicationTypeId().trim() + "%')");
		} else {
			buf.append("MEDICATION_ID LIKE '%'");
		}

		if (mev.getDosageAdmin() != null && mev.getDosageAdmin().trim().indexOf("*") != -1) {
			buf.append(" AND DOSAGEADMIN LIKE UPPER('" + mev.getDosageAdmin().trim().replace('*', '%') + "%')");
		} else if (mev.getDosageAdmin() != null && !"".equals(mev.getDosageAdmin().trim())
				&& !"*".equals(mev.getDosageAdmin())) {
			buf.append(" AND DOSAGEADMIN LIKE UPPER('" + mev.getDosageAdmin().trim() + "%')");
		}

		if (mev.getStrength() != null && mev.getStrength().trim().indexOf("*") != -1) {
			buf.append(" AND STRENGTH LIKE UPPER('" + mev.getStrength().trim().replace('*', '%') + "%')");
		} else if (mev.getStrength() != null && !"".equals(mev.getStrength()) && !"*".equals(mev.getStrength().trim())) {
			buf.append(" AND STRENGTH LIKE UPPER('" + mev.getStrength().trim() + "%')");
		}

		if (mev.getUsage() != null && mev.getUsage().trim().indexOf("*") != -1) {
			buf.append(" AND USAGE LIKE UPPER('" + mev.getUsage().trim().replace('*', '%') + "%')");
		} else if (mev.getUsage() != null && !"".equals(mev.getUsage()) && !"*".equals(mev.getUsage().trim())) {
			buf.append(" AND USAGE LIKE UPPER('" + mev.getUsage().trim() + "%')");
		}

		if (mev.getMedication() != null && mev.getMedication().trim().indexOf("*") != -1) {
			buf.append(" AND MEDICATION LIKE UPPER('" + mev.getMedication().trim().replace('*', '%') + "%')");
		} else if (mev.getMedication() != null && !"".equals(mev.getMedication().trim())
				&& !"*".equals(mev.getMedication())) {
			buf.append(" AND MEDICATION LIKE UPPER('" + mev.getMedication().trim() + "%')");
		} else {
			buf.append(" AND MEDICATION LIKE '%'");
		}

		return buf.toString();
	}
	
	
	/* Deleted Code */
	
	/*
	 * if (mev.getMedicationTypeId() != null &&
	 * "*".equals(mev.getMedicationTypeId().trim())) { buf.append("MEDICATION_ID
	 * LIKE '%'"); } else if (mev.getMedicationTypeId() != null &&
	 * !"".equals(mev.getMedicationTypeId().trim()) &&
	 * !"*".equals(mev.getMedicationTypeId())) { buf.append("MEDICATION_ID LIKE
	 * UPPER('" + mev.getMedicationTypeId().trim() + "%')"); }
	 *  
	 */

	/*
	 * if (mev.getDosageAdmin() != null &&
	 * "*".equals(mev.getDosageAdmin().trim())) { buf.append(" AND DOSAGEADMIN
	 * LIKE '%'"); } else if (mev.getDosageAdmin() != null &&
	 * !"".equals(mev.getDosageAdmin().trim()) &&
	 * !"*".equals(mev.getDosageAdmin())) { buf.append(" AND DOSAGEADMIN LIKE
	 * UPPER('" + mev.getDosageAdmin().trim() + "%')"); }
	 */
	/*
	 * if (mev.getStrength() != null && "*".equals(mev.getStrength().trim())) {
	 * buf.append(" AND STRENGTH LIKE '%'"); } else if (mev.getStrength() !=
	 * null && !"".equals(mev.getStrength()) &&
	 * !"*".equals(mev.getStrength().trim())) { buf.append(" AND STRENGTH LIKE
	 * UPPER('" + mev.getStrength().trim() + "%')"); }
	 */

	/*
	 * if (mev.getUsage() != null && "*".equals(mev.getUsage().trim())) {
	 * buf.append(" AND USAGE LIKE '%'"); } else if (mev.getUsage() != null &&
	 * !"".equals(mev.getUsage()) && !"*".equals(mev.getUsage().trim())) {
	 * buf.append(" AND USAGE LIKE UPPER('" + mev.getUsage().trim() + "%')"); }
	 */
	/*
	 * if (mev.getMedication() != null &&
	 * "*".equals(mev.getMedication().trim())) { buf.append(" AND MEDICATION
	 * LIKE '%'"); } else if (mev.getMedication() != null &&
	 * !"".equals(mev.getMedication()) &&
	 * !"*".equals(mev.getMedication().trim())) { buf.append(" AND MEDICATION
	 * LIKE UPPER('" + mev.getMedication().trim() + "%')"); }
	 */
	
}
