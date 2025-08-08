// Source file:
// C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administerserviceprovider\\administerlocation\\transactions\\CreateUpdateJuvLocationUnitCommand.java

package pd.supervision.administerserviceprovider.administerlocation.transactions;

import pd.supervision.administerserviceprovider.administerlocation.JuvLocationUnit;
import messaging.administerlocation.CreateUpdateJuvLocationUnitEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;

/**
 * @author cc_vnarsingoju
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class CreateUpdateJuvLocationUnitCommand implements ICommand {

	/**
	 * @roseuid 466466460078
	 */
	public CreateUpdateJuvLocationUnitCommand() {

	}

	/**
	 * IF isCreate is true then it will create a new location unit or else it
	 * will update the existing JuvenileLocationUnit.
	 * 
	 * @param event
	 * @roseuid 4664621D0195
	 */
	public void execute(IEvent event) {
		CreateUpdateJuvLocationUnitEvent locUnitEvent = (CreateUpdateJuvLocationUnitEvent) event;
		if (locUnitEvent.isCreate()) {
			JuvLocationUnit juvLocationUnit = new JuvLocationUnit();
			juvLocationUnit.setLocationUnitName(locUnitEvent.getLocationUnitName());
			juvLocationUnit.setJuvUnitCd(locUnitEvent.getJuvUnitCd());
			juvLocationUnit.setUnitStatusId(locUnitEvent.getUnitStatusCd());
			juvLocationUnit.setLocationId(locUnitEvent.getLocationId());
			if (locUnitEvent.getPhone() != null) {
				juvLocationUnit.setPhoneNumber(locUnitEvent.getPhone().getPhoneNumber());
			}
			/*juvLocationUnit.setMaysiFlag(locUnitEvent.getMaysiFlag()); ADD IF NEEDED WHILE CREATING
			juvLocationUnit.setDrugFlag(locUnitEvent.getDrugFlag());*/
		} else {
			String locationUnitId = locUnitEvent.getLocationUnitId();
			JuvLocationUnit locationUnit = JuvLocationUnit.find(locationUnitId);
			locationUnit.setLocationUnitName(locUnitEvent.getLocationUnitName());
			locationUnit.setMaysiFlag(locUnitEvent.getMaysiFlag());
			locationUnit.setDrugFlag(locUnitEvent.getDrugFlag());
			if (locUnitEvent.getPhone() != null) {
				locationUnit.setPhoneNumber(locUnitEvent.getPhone().getPhoneNumber());
			}
			locationUnit.setInterviewCalFlag( locUnitEvent.getInterviewCalFlag()  );
			locationUnit.setOfficerProfileFlag( locUnitEvent.getOfficerProfileFlag() );
		}

	}

	/**
	 * @param event
	 * @roseuid 4664621D0197
	 */
	public void onRegister(IEvent event) {

	}

	/**
	 * @param event
	 * @roseuid 4664621D0199
	 */
	public void onUnregister(IEvent event) {

	}

	/**
	 * @param anObject
	 * @roseuid 4664621D01A6
	 */
	public void update(Object anObject) {

	}

}
