/*
 * Created on Oct 19, 2004
 */
package pd.juvenilewarrant;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import messaging.address.AddressRequestEvent;
import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.juvenilewarrant.CreateJuvenileAssociateEvent;
import messaging.juvenilewarrant.GetJuvenileWarrantByActivationStatusEvent;
import messaging.juvenilewarrant.GetJuvenileWarrantServiceInfoEvent;
import messaging.juvenilewarrant.JuvenileAssociateAddressRequestEvent;
import messaging.juvenilewarrant.SearchJuvenileWarrantActiveOrPendingEvent;
import messaging.juvenilewarrant.SearchJuvenileWarrantEvent;
import messaging.juvenilewarrant.UpdateJuvenileReleaseInfoEvent;
import messaging.juvenilewarrant.UpdateJuvenileReleaseToJPInfoEvent;
import messaging.juvenilewarrant.UpdateJuvenileReleaseToPersonInfoEvent;
import messaging.juvenilewarrant.reply.JJSChargeResponseEvent;
import messaging.juvenilewarrant.reply.JuvenileAssociateAddressResponseEvent;
import messaging.juvenilewarrant.reply.JuvenileAssociateResponseEvent;
import messaging.juvenilewarrant.reply.JuvenileOffenderTrackingChargeResponseEvent;
import messaging.juvenilewarrant.reply.JuvenileWarrantServiceResponseEvent;
import messaging.juvenilewarrant.reply.SummaryOfFactsResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.ResponseEvent;
import mojo.km.messaging.Composite.CompositeRequest;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.util.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileWarrantControllerServiceNames;
import naming.PDJuvenileWarrantConstants;
import naming.PDOfficerProfileConstants;
import pd.address.PDAddressHelper;
import pd.codetable.Code;
import pd.codetable.PDCodeTableHelper;
import pd.codetable.criminal.JuvenileCourt;
import pd.codetable.criminal.OffenseCode;
import pd.contact.agency.Department;
import pd.contact.officer.OfficerProfile;
import pd.contact.officer.PDOfficerProfileHelper;
import pd.juvenilewarrant.helper.JuvenileWarrantWorker;

/**
 * @author ryoung
 */
public final class PDJuvenileWarrantHelper
{

    static private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 
     * @param juvenileNum
     * @return boolean activeWarrant
     */
    private static boolean checkForActiveWarrant(Integer juvenileNum)
    {
        // See if there is an active warrant for this Juvenile (Juv#)
        // If not found, can proceed, as there is no ACTIVE juvenile warrant
        // for this juvenile.
        GetJuvenileWarrantByActivationStatusEvent activeEvent = new GetJuvenileWarrantByActivationStatusEvent();
        activeEvent.setWarrantActivationStatus(PDJuvenileWarrantConstants.WARRANT_ACTIVATION_ACTIVE);
        activeEvent.setJuvenileNum(juvenileNum);

        return JuvenileWarrant.findWarrantsExistForActivationStatus(activeEvent);
    }

    /**
     * 
     * @param juvenileNum
     * @return boolean activeWarrant
     * 
     * this is checked before warrant get activiated
     */
    public static boolean checkForActiveWarrant(Integer juvenileNumber, String daLogNumber, Date dob, String juvenileFirstName,
            String juvenileLastName, Integer referralNumber, String petitionNumber)
    {
        if (juvenileNumber != null)
        {
            // If not found, can proceed, as there is no ACTIVE or NOTACTIVE
            // juvenile warrant
            boolean activeWarrantExists = checkForActiveWarrant(juvenileNumber);
            if (activeWarrantExists)
                return activeWarrantExists;
            return false;
        }
        else
        {
            SearchJuvenileWarrantEvent searchEvent = new SearchJuvenileWarrantEvent();
            //searchEvent.setWarrantActivationStatus(PDJuvenileWarrantConstants.WARRANT_ACTIVATION_ACTIVE);
            searchEvent.setDaLogNumber(daLogNumber);
            // or
            searchEvent.setPetitionNumber(petitionNumber);
            searchEvent.setReferralNumber(referralNumber);
            // or
            searchEvent.setJuvenileFirstName(juvenileFirstName);
            searchEvent.setJuvenileLastName(juvenileLastName);
            if (dob != null)
                searchEvent.setDateOfBirth(dateFormat.format(dob));

            searchEvent.setWarrantActivationStatus(PDJuvenileWarrantConstants.WARRANT_ACTIVATION_ACTIVE);
            return JuvenileWarrant.findWarrantsExistForActivationStatus(searchEvent);

        }

    }

    /**
     * 
     * @param juvenileNum
     * @return boolean notActiveWarrant
     */
    private static boolean checkForNotActiveWarrant(Integer juvenileNum)
    {
        // See if there is an active warrant for this Juvenile (Juv#)
        // If not found, can proceed, as there is no ACTIVE juvenile warrant
        // for this juvenile.
        GetJuvenileWarrantByActivationStatusEvent activeEvent = new GetJuvenileWarrantByActivationStatusEvent();
        activeEvent.setWarrantActivationStatus(PDJuvenileWarrantConstants.WARRANT_ACTIVATION_NOT_ACTIVE);
        activeEvent.setJuvenileNum(juvenileNum);

        return JuvenileWarrant.findWarrantsExistForActivationStatus(activeEvent);
    }

    /**
     * 
     * @param juvenileNum
     * @return boolean notActiveWarrant
     */
    public static boolean checkForNotActiveWarrant(Integer juvenileNumber, String daLogNumber, Date dob,
            String juvenileFirstName, String juvenileLastName, Integer referralNumber, String petitionNumber)
    {
        if (juvenileNumber != null && (!juvenileNumber.equals("")) && (!juvenileNumber.equals("0")))
        {
            // If not found, can proceed, as there is no ACTIVE or NOTACTIVE
            // juvenile warrant
            boolean activeWarrantExists = checkForNotActiveWarrant(juvenileNumber);
            if (activeWarrantExists)
                return activeWarrantExists;
            return false;
        }
        else
        {
            SearchJuvenileWarrantEvent searchEvent = new SearchJuvenileWarrantEvent();
            //searchEvent.setWarrantActivationStatus(PDJuvenileWarrantConstants.WARRANT_ACTIVATION_ACTIVE);
            searchEvent.setDaLogNumber(daLogNumber);
            // or
            searchEvent.setPetitionNumber(petitionNumber);
            searchEvent.setReferralNumber(referralNumber);
            // or
            searchEvent.setJuvenileFirstName(juvenileFirstName);
            searchEvent.setJuvenileLastName(juvenileLastName);
            if (dob != null)
                searchEvent.setDateOfBirth(dateFormat.format(dob));

            searchEvent.setWarrantActivationStatus(PDJuvenileWarrantConstants.WARRANT_ACTIVATION_NOT_ACTIVE);
            return JuvenileWarrant.findActivePendingWarrantsExist(searchEvent);

        }

    }

    /**
     * 
     * @param juvenileNum
     * @return boolean activeWarrant
     * 
     * Determine if any active / pending worrants exists
     * 
     * this is will be check before initiate a warrant
     *  
     */
    public static boolean findExistingWarrant(Integer juvenileNumber, String daLogNumber, int dob, String juvenileFirstName,
            String juvenileLastName, Integer referralNumber, String petitionNumber)
    {
    	SearchJuvenileWarrantActiveOrPendingEvent searchEvent = new SearchJuvenileWarrantActiveOrPendingEvent();
        searchEvent.setDaLogNumber(daLogNumber);
        searchEvent.setJuvenileNum(juvenileNumber);
        searchEvent.setJuvenileFirstName(juvenileFirstName);
        searchEvent.setJuvenileLastName(juvenileLastName);
           
        if (dob >0)
        {
            searchEvent.setDateOfBirth(dateFormat.format(dob));
        }

        Iterator i = JuvenileWarrant.findAll(searchEvent);

        return i.hasNext();
    }
    
    public static boolean findExistingWarrant(Integer juvenileNumber, String daLogNumber, Date dob, String juvenileFirstName,
            String juvenileLastName, Integer referralNumber, String petitionNumber)
    {
    	SearchJuvenileWarrantActiveOrPendingEvent searchEvent = new SearchJuvenileWarrantActiveOrPendingEvent();
        searchEvent.setDaLogNumber(daLogNumber);
        searchEvent.setJuvenileNum(juvenileNumber);
        searchEvent.setJuvenileFirstName(juvenileFirstName);
        searchEvent.setJuvenileLastName(juvenileLastName);
           
        if ( dob != null )
        {
            searchEvent.setDateOfBirth(dateFormat.format(dob));
        }

        Iterator i = JuvenileWarrant.findAll(searchEvent);

        return i.hasNext();
    }

    /**
     * @param associateEvent
     * @return juvenileAssociate
     */
    public static JuvenileAssociate createJuvenileAssociate(CreateJuvenileAssociateEvent associateEvent)
    {
        String warrantNum = associateEvent.getWarrantNum();

        JuvenileAssociate juvenileAssociate = null;
        if (warrantNum != null)
        {
            juvenileAssociate = new JuvenileAssociate();

            setJuvenileAssociateFields(associateEvent, juvenileAssociate);
            IHome home = new Home();
            home.bind(juvenileAssociate);
            //change assocAddressMap to juvenileAssociate
            createJuvenileAssociateAddress(associateEvent, juvenileAssociate);
        }
        return juvenileAssociate;
    }

    /**
     * @param associateEvent
     * @param juvenileAssociate
     */
    private static void createJuvenileAssociateAddress(CompositeRequest associateEvent, JuvenileAssociate juvenileAssociate)
    {
        //get associate number from juvenileAssociate
        String associateNum = juvenileAssociate.getAssociateNum();
        Collection assocAddresses = MessageUtil.compositeToCollection(associateEvent, JuvenileAssociateAddressRequestEvent.class);
        if (assocAddresses != null)
        {
            Iterator i = assocAddresses.iterator();
            while (i.hasNext())
            {
                JuvenileAssociateAddressRequestEvent aaEvent = (JuvenileAssociateAddressRequestEvent) i.next();
                JuvenileAssociateAddress associateAddress = new JuvenileAssociateAddress();

                associateAddress.setAssociateNum(associateNum);

                populateAssociateAddress(aaEvent, associateAddress);

                juvenileAssociate.insertAddresses(associateAddress);
            }
        }
    }
    
    /**
     * @param petition
     * @return Event ce
     */
    public static JJSChargeResponseEvent getJJSChargeResponseEvent(JJSPetition petition)
    {
        JJSChargeResponseEvent ce = new JJSChargeResponseEvent();
        ce.setTopic(PDJuvenileWarrantConstants.JJS_CHARGE_EVENT_TOPIC);

        ce.setJuvenileNum(petition.getJuvenileNum());
        ce.setSeverity(petition.getSeverity());
        ce.setOffenseCodeId(petition.getOffenseCodeId());

        if (petition.getOffenseCodeId() != null && !petition.getOffenseCodeId().equals(""))
        {
            ce.setOffense(petition.getOffenseCode().getLongDescription());
        }
        ce.setAmend(petition.getAmend());
        ce.setPetitionNum(petition.getPetitionNum());
        ce.setPetitionDate(petition.getPetitionDate());
        ce.setReferralNum(petition.getReferralNum());
        ce.setChargeSeqNum(petition.getSequenceNum());
        ce.setCourtId(petition.getCourtId());
        if (petition.getCourtId() != null && !petition.getCourtId().equals(""))
        {
            ce.setCourtId(petition.getCourt().getCode());
            ce.setCourt(petition.getCourt().getDescription());
        }
        if(petition.getStatus()!=null && !petition.getStatus().equals(""))
        {
        	ce.setPetitionStatusId(petition.getStatus());
        	if(petition.getStatus().equalsIgnoreCase("A"))
        		ce.setPetitionStatus(PDJuvenileWarrantConstants.MS_PETITION_STATUS_A);
        	else if(petition.getStatus().equalsIgnoreCase("D"))
        		ce.setPetitionStatus(PDJuvenileWarrantConstants.MS_PETITION_STATUS_D);
        	else if(petition.getStatus().equalsIgnoreCase("F"))
        		ce.setPetitionStatus(PDJuvenileWarrantConstants.MS_PETITION_STATUS_F);
        	else if(petition.getStatus().equalsIgnoreCase("G"))
        		ce.setPetitionStatus(PDJuvenileWarrantConstants.MS_PETITION_STATUS_G);
        	else if(petition.getStatus().equalsIgnoreCase("M"))
        		ce.setPetitionStatus(PDJuvenileWarrantConstants.MS_PETITION_STATUS_M);
        	else if(petition.getStatus().equalsIgnoreCase("N"))
        		ce.setPetitionStatus(PDJuvenileWarrantConstants.MS_PETITION_STATUS_N);
        	else if(petition.getStatus().equalsIgnoreCase("R"))
        		ce.setPetitionStatus(PDJuvenileWarrantConstants.MS_PETITION_STATUS_R);
        	else if(petition.getStatus().equalsIgnoreCase("T"))
        		ce.setPetitionStatus(PDJuvenileWarrantConstants.MS_PETITION_STATUS_T);
        	else if(petition.getStatus().equalsIgnoreCase("V"))
        		ce.setPetitionStatus(PDJuvenileWarrantConstants.MS_PETITION_STATUS_V);
        }
        return ce;
    }

    /**
     * @param address
     * @return are
     */
    public static JuvenileAssociateAddressResponseEvent getJuvenileAssociateAddressResponseEvent(JuvenileAssociateAddress address)
    {

        JuvenileAssociateAddressResponseEvent are = new JuvenileAssociateAddressResponseEvent();
        are.setTopic(PDJuvenileWarrantConstants.JUVENILE_ASSOCIATE_ADDRESS_EVENT_TOPIC);

        are.setAdditionalZipCode(address.getAdditionalZipCode());
        are.setAddressId(address.getOID().toString());
        are.setAddress2(address.getAddress2());
        are.setAptNum(address.getAptNum());
        Code code = null;
        if ((address.getAddressTypeId() != null) && (!(address.getAddressTypeId().equals(""))))
        {
            code = address.getAddressType();
            if (code != null)
            {
                are.setAddressType(code.getDescription());
                are.setAddressTypeId(code.getCode());
            }
        }
        
        JuvenileAssociate associate = null;
        if( address.getAssociateNum() != null){
            
            associate = JuvenileAssociate.find(address.getAssociateNum());
            Code relationshipCode = associate.getRelationshipToJuvenile();
            if (relationshipCode != null)
            {
                are.setRelationshipToJuvenile(relationshipCode.getDescription());
            }
        }
        
        //are.setAptNum
        are.setBadAddress(address.getBadAddress());
        are.setCity(address.getCity());
        if ((address.getCountryId() != null) && (!(address.getCountryId().equals(""))))
        {
            code = address.getCountry();
            if (code != null)
            {
                are.setCountry(code.getDescription());
                are.setCountryId(code.getCode());
            }
        }
        if ((address.getCountyId() != null) && (!(address.getCountyId().equals(""))))
        {
            code = address.getCounty();
            if (code != null)
            {
                are.setCounty(code.getDescription());
                are.setCountyId(code.getCode());
            }
        }
        are.setCurrentAddressInd(address.getCurrentAddressInd());
        are.setKeymap(address.getKeymap());
        are.setLatitude(String.valueOf(address.getLatitude()));
        are.setLongitude(String.valueOf(address.getLongitude()));
        if ((address.getStateId() != null) && (!(address.getStateId().equals(""))))
        {
            code = address.getState();
            if (code != null)
            {
                are.setState(code.getDescription());
                are.setStateId(code.getCode());
            }
        }
        are.setStreetName(address.getStreetName());
        are.setStreetNum(address.getStreetNum());
        if ((address.getStreetTypeId() != null) && (!(address.getStreetTypeId().equals(""))))
        {
            code = address.getStreetType();
            if (code != null)
            {
                are.setStreetType(code.getDescription());
                are.setStreetTypeId(code.getCode());
            }
        }
        are.setZipCode(address.getZipCode());

        String addressStatus = address.getAddressStatus();
        if (addressStatus == null)
        {
            // TODO Change to use (U)NPROCESSED constant
            are.setAddressStatus("U");
        }
        else
        {
            are.setAddressStatus(address.getAddressStatus());
        }

        return are;
    }

    public static JuvenileAssociateResponseEvent getSimpleJuvenileAssociateResponseEvent(JuvenileAssociate juvAssociate)
    {
        JuvenileAssociateResponseEvent jare = new JuvenileAssociateResponseEvent();
        jare.setTopic(PDJuvenileWarrantConstants.JUVENILE_ASSOCIATE_EVENT_TOPIC);
        jare.setAssociateNum(juvAssociate.getAssociateNum());
        jare.setFirstName(juvAssociate.getFirstName());
        jare.setLastName(juvAssociate.getLastName());
        jare.setMiddleName(juvAssociate.getMiddleName());
        jare.setRelationshipToJuvenileId(juvAssociate.getRelationshipToJuvenileId());
        Code relationshipCode = juvAssociate.getRelationshipToJuvenile();
        if (relationshipCode != null)
        {
            jare.setRelationshipToJuvenile(relationshipCode.getDescription());
        }
        jare.setReleasedTo(juvAssociate.getReleasedTo());
        jare.setWarrantNum(juvAssociate.getWarrantNum());
        return jare;
    }

    /**
     * @param juvAssociate
     * @return jare
     */
    public static JuvenileAssociateResponseEvent getJuvenileAssociateResponseEvent(JuvenileAssociate juvAssociate)
    {
        JuvenileAssociateResponseEvent jare = new JuvenileAssociateResponseEvent();
        jare.setAssociateNum(juvAssociate.getAssociateNum());
        jare.setDateOfBirth(juvAssociate.getDateOfBirth());
        jare.setFirstName(juvAssociate.getFirstName());
        Code juvCode = null;
        jare.setLastName(juvAssociate.getLastName());
        jare.setMiddleName(juvAssociate.getMiddleName());
        if (juvAssociate.getRaceId() != null && !juvAssociate.getRaceId().equals(""))
        {
            juvCode = juvAssociate.getRace();
            if (juvCode != null)
            {
                jare.setRaceId(juvCode.getCode());
                jare.setRace(juvCode.getDescription());
            }
        }
        if (juvAssociate.getRelationshipToJuvenileId() != null && !juvAssociate.getRelationshipToJuvenileId().equals(""))
        {
            juvCode = juvAssociate.getRelationshipToJuvenile();
            if (juvCode != null)
            {
                jare.setRelationshipToJuvenileId(juvCode.getCode());
                jare.setRelationshipToJuvenile(juvCode.getDescription());
            }
        }
        jare.setReleasedTo(juvAssociate.getReleasedTo());
        if (juvAssociate.getSexId() != null && !juvAssociate.getSexId().equals(""))
        {
            juvCode = juvAssociate.getSex();
            if (juvCode != null)
            {
                jare.setSexId(juvCode.getCode());
                jare.setSex(juvCode.getDescription());
            }
        }

        if (juvAssociate.getDlStateId() != null && !juvAssociate.getDlStateId().equals(""))
        {
            juvCode = juvAssociate.getDlState();
            if (juvCode != null)
            {
                jare.setDlStateId(juvCode.getCode());
                jare.setDlState(juvCode.getDescription());
            }
        }

        jare.setSsn(juvAssociate.getSsn());
        jare.setHomePhoneNum(juvAssociate.getHomePhone());
        jare.setCellPhoneNum(juvAssociate.getCellPhone());
        jare.setWorkPhoneNum(juvAssociate.getWorkPhone());
        jare.setPager(juvAssociate.getPager());
        jare.setFaxNum(juvAssociate.getFaxNumber());
        jare.setFaxLocation(juvAssociate.getFaxLocation());
        jare.setEmail1(juvAssociate.getEmail1());
        jare.setEmail2(juvAssociate.getEmail2());
        jare.setEmail3(juvAssociate.getEmail3());
        jare.setWarrantNum(juvAssociate.getWarrantNum());
        jare.setDlNumber(juvAssociate.getDlNumber());
        jare.setTopic(PDJuvenileWarrantConstants.JUVENILE_ASSOCIATE_EVENT_TOPIC);
        return jare;
    }

    /**
     * @param jotCharge
     * @return Event jchg
     */
    public static JuvenileOffenderTrackingChargeResponseEvent getJuvenileOffenderTrackingChargeResponseEvent(
            JuvenileOffenderTrackingCharge jotCharge)
    {
        JuvenileOffenderTrackingChargeResponseEvent jchg = new JuvenileOffenderTrackingChargeResponseEvent();
        jchg.setTopic(PDJuvenileWarrantConstants.JUVENILE_OFFENDER_TRACKING_CHARGE_EVENT_TOPIC);

        if ((jotCharge.getOffenseCodeId() != null) && (!(jotCharge.getOffenseCodeId().equals(""))))
        {
            OffenseCode offCode = jotCharge.getOffenseCode();
            if (offCode != null)
            {
                jchg.setOffenseCodeId(offCode.getOffenseCodeId());
                jchg.setOffense(offCode.getDescription());
            }
            else
            {
                jchg.setOffense("Offense Not Found: " + jotCharge.getOffenseCode());
            }
        }
        if ((jotCharge.getCourtId() != null) && (!(jotCharge.getCourtId().equals(""))))
        {
            JuvenileCourt courtCode = jotCharge.getCourt();
            if (courtCode != null)
            {
                jchg.setCourtId(courtCode.getCode());
                jchg.setCourt(courtCode.getDescription());
            }
            else
            {
                jchg.setCourt("Court Not Found: " + jotCharge.getCourt());
            }
        }
        //task 149503
        jchg.setProstIndicator(jotCharge.getProstInd());
        //
        jchg.setChargeId(jotCharge.getOID());
        jchg.setOffenseDate(DateUtil.IntToDate( jotCharge.getOffenseIDate(), DateUtil.DATE_FMT_2) );
        jchg.setChargeSeqNum(jotCharge.getChargeSeqNum());
        jchg.setDaLogNum(jotCharge.getDaLogNum());
        jchg.setPetitionNum(jotCharge.getPetitionNum());
        jchg.setCJISNum(jotCharge.getCJISNum());
        jchg.setCJISSuffixNum(jotCharge.getCJISSuffixNum());
        jchg.setTotalPropertyLossAmount(jotCharge.getTotalPropertyLossAmount());
        String drugInfluence= jotCharge.getDrugInfluence();
        boolean alcoholInfluence=jotCharge.isAlcoholInfluence();
        if(drugInfluence.equals("Y") || alcoholInfluence)
        	jchg.setAlcoholOrDrugInfluence("Yes");
        else
        	jchg.setAlcoholOrDrugInfluence("No");
        Department dept = jotCharge.getLawEnforcementAgency();
        if (dept != null)
        {
            jchg.setLawEnforcementAgency(dept.getDepartmentName());
        }
       if(jotCharge.getCaseTypeGroup().equalsIgnoreCase(PDJuvenileWarrantConstants.DM_CASE_TYPE_GROUP_FGC)||jotCharge.getCaseTypeGroup().equalsIgnoreCase(PDJuvenileWarrantConstants.DM_CASE_TYPE_GROUP_GC)||jotCharge.getCaseTypeGroup().equalsIgnoreCase(PDJuvenileWarrantConstants.DM_CASE_TYPE_GROUP_GG))
       {
       	 	jchg.setGangRelated(true);
       		jchg.setGangIndicator("Y");
       }
       else
       {
       		jchg.setGangRelated(false);
       	    jchg.setGangIndicator("N");
       }
       
       if(jotCharge.getGangRelated()!=null && !jotCharge.getGangRelated().isEmpty() ){ //bug fix 26194
    	   jchg.setGangIndicator(jotCharge.getGangRelated());
       }
       
       if(jotCharge.getWeaponTypeId()!=null && !jotCharge.getWeaponTypeId().equals(""))
       {
       	 	jchg.setWeaponUsed(true);
       	 	jchg.setWeaponTypeId(jotCharge.getWeaponTypeId());// added for user story - 11449
       	 	if(jotCharge.getWeaponType() != null)
       	 	{
       	 		String weaponType = jotCharge.getWeaponType().getDescription();
       	 		if(weaponType.equalsIgnoreCase(PDJuvenileWarrantConstants.DM_OTHER))
       	 			jchg.setWeaponType(jotCharge.getUnlistedWeapon());
       	 		else
       	 			jchg.setWeaponType(weaponType);
       	 	}
       }
       else{
       		jchg.setWeaponUsed(false);
       		jchg.setWeaponType(" ");
       		jchg.setWeaponTypeId(" ");
       }
      
       jchg.setRecType( jotCharge.getRecType() );
        return jchg;
    }

    /**
     * Method used to return JuvenileWarrantServiceResponseEvent from the values
     * in juvenileWarrantService.
     * 
     * @author asrvastava
     * @param juvenileWarrantService
     * @return JuvenileWarrantServiceResponseEvent
     */
    public static JuvenileWarrantServiceResponseEvent getJuvenileWarrantServiceResponseEvent(
            JuvenileWarrantService juvenileWarrantService)
    {
        JuvenileWarrantServiceResponseEvent warrantServiceEvent = new JuvenileWarrantServiceResponseEvent();
        if (juvenileWarrantService.getAirFare() != null)
        {
            warrantServiceEvent.setAirFare(juvenileWarrantService.getAirFare().toString());
        }
        warrantServiceEvent.setBadAddress(juvenileWarrantService.getIsBadAddress());
        warrantServiceEvent.setCity(juvenileWarrantService.getCity());
        warrantServiceEvent.setComments(juvenileWarrantService.getComments());
        if (juvenileWarrantService.getMileage() != null)
        {
            warrantServiceEvent.setMileage(juvenileWarrantService.getMileage().toString());
        }
        if (juvenileWarrantService.getPerDiem() != null)
        {
            warrantServiceEvent.setPerDiem(juvenileWarrantService.getPerDiem().toString());
        }
        warrantServiceEvent.setServiceTimeStamp(juvenileWarrantService.getServiceTimeStamp());
        warrantServiceEvent.setServiceID(juvenileWarrantService.getOID().toString());
        warrantServiceEvent.setStreetName(juvenileWarrantService.getStreetName());
        warrantServiceEvent.setStreetNum(juvenileWarrantService.getStreetNum());
        warrantServiceEvent.setZipCode(juvenileWarrantService.getZipCode());
        warrantServiceEvent.setAdditionalZipCode(juvenileWarrantService.getAdditionalZipCode());
        warrantServiceEvent.setAptNumber(juvenileWarrantService.getApartmentNum());
        // officer info
        warrantServiceEvent.setExecutorOfficerId(juvenileWarrantService.getExecutorOfficerId());

        if (juvenileWarrantService.getExecutorOfficerId() != null)
        {
            OfficerProfile officer = OfficerProfile.find(juvenileWarrantService.getExecutorOfficerId());
            if (officer != null)
            {
                OfficerProfileResponseEvent officerEvent = PDOfficerProfileHelper.getThinOfficerProfileResponseEvent(officer);
                warrantServiceEvent.setExecutorAgencyName(officerEvent.getDepartmentName());
                warrantServiceEvent.setExecutorCellNum(officerEvent.getCellPhone());
                warrantServiceEvent.setExecutorEmail(officerEvent.getEmail());
                warrantServiceEvent.setExecutorFirstName(officerEvent.getFirstName());
                warrantServiceEvent.setExecutorMiddleName(officerEvent.getMiddleName());
                warrantServiceEvent.setExecutorLastName(officerEvent.getLastName());
                warrantServiceEvent.setExecutorPager(officerEvent.getPager());
                warrantServiceEvent.setExecutorPhoneNum(officerEvent.getWorkPhone());
                if (officerEvent.getBadgeNum() != null && !officerEvent.getBadgeNum().equals(""))
                {
                    warrantServiceEvent.setExecutorOfficerId(officerEvent.getBadgeNum());
                    warrantServiceEvent.setExecutorIdType(PDOfficerProfileConstants.BADGE_NUM);
                }
                else
                {
                    warrantServiceEvent.setExecutorOfficerId(officerEvent.getOtherIdNum());
                    warrantServiceEvent.setExecutorIdType(PDOfficerProfileConstants.ID_NUM);
                }
            }
        }

        // service status
        String codeId = juvenileWarrantService.getServiceStatusId();
        Code serviceCode = null;
        if (codeId != null && !codeId.equals(""))
        {
            serviceCode = juvenileWarrantService.getServiceStatus();
            if (serviceCode != null)
            {
                warrantServiceEvent.setServiceStatusId(serviceCode.getCode());
                warrantServiceEvent.setServiceStatus(serviceCode.getDescription());
            }
        }

        // state
        codeId = juvenileWarrantService.getStateId();
        Code stateCode = null;
        if (codeId != null && !codeId.equals(""))
        {
            stateCode = juvenileWarrantService.getState();
            if (stateCode != null)
            {
                warrantServiceEvent.setState(stateCode.getDescription());
            }
        }

        // address type
        codeId = juvenileWarrantService.getAddressTypeId();
        Code addTypeCode = null;
        if (codeId != null && !codeId.equals(""))
        {
            addTypeCode = juvenileWarrantService.getAddressType();
            if (addTypeCode != null)
            {
                warrantServiceEvent.setAddressTypeId(addTypeCode.getCode());
                warrantServiceEvent.setAddressType(addTypeCode.getDescription());
            }
        }
        // streeet type
        codeId = juvenileWarrantService.getStreetTypeId();
        Code stTypeCode = null;
        if (codeId != null && !codeId.equals(""))
        {
            stTypeCode = juvenileWarrantService.getStreetType();
            if (stTypeCode != null)
            {
                warrantServiceEvent.setStreetTypeId(stTypeCode.getCode());
                warrantServiceEvent.setStreetType(stTypeCode.getDescription());
            }
        }
        // county
        codeId = juvenileWarrantService.getCountyId();
        Code cntCode = null;
        if (codeId != null && !codeId.equals(""))
        {
            cntCode = juvenileWarrantService.getAddressType();
            if (cntCode != null)
            {
                warrantServiceEvent.setCountyId(cntCode.getCode());
                warrantServiceEvent.setCounty(cntCode.getDescription());
            }
        }
        warrantServiceEvent.setTopic(PDJuvenileWarrantConstants.JUVENILE_WARRANT_SERVICE_EVENT_TOPIC);
        return warrantServiceEvent;
    }

    public static JuvenileWarrantService findSuccessfulWarrant(String warrantNum)
    {
        GetJuvenileWarrantServiceInfoEvent requestEvent = (GetJuvenileWarrantServiceInfoEvent) EventFactory
                .getInstance(JuvenileWarrantControllerServiceNames.GETJUVENILEWARRANTSERVICEINFO);
        requestEvent.setWarrantNum(warrantNum);
        Iterator services = JuvenileWarrantService.findAll(requestEvent);
        JuvenileWarrantService service = null;
        if (services.hasNext())
        {
            service = (JuvenileWarrantService) services.next();
        }
        return service;
    }

    /**
     * @param summaryOfFacts
     * @return Event sum
     */
    public static SummaryOfFactsResponseEvent getSummaryOfFactsResponseEvent(JuvenileOffenderTracking jot)
    {
        SummaryOfFactsResponseEvent factEvent = new SummaryOfFactsResponseEvent();
        Collection facts = jot.getSummaryOfFacts();
        if (facts != null)
        {
            Iterator i = facts.iterator();
            factEvent.setTopic(PDJuvenileWarrantConstants.SUMMARY_OF_FACTS_EVENT_TOPIC);
            while (i.hasNext())
            {
                JuvenileOffenderTrackingSummaryOfFacts summary = (JuvenileOffenderTrackingSummaryOfFacts) i.next();
                factEvent.addSummaryOfFact(summary.getSeqNum(), summary.getText());
            }
        }
        factEvent.setDaLogNum(jot.getDaLogNum());

        return factEvent;
    }

    /**
     * @param aaEvent
     * @param associateAddress
     */
    private static void populateAssociateAddress(AddressRequestEvent aaEvent, JuvenileAssociateAddress associateAddress)
    {
        associateAddress.setAdditionalZipCode(aaEvent.getAdditionalZipCode());
        associateAddress.setAddressId(aaEvent.getAddressId());
        associateAddress.setAddressTypeId(aaEvent.getAddressTypeId());
        associateAddress.setAptNum(aaEvent.getAptNum());
        associateAddress.setCity(aaEvent.getCity());
        associateAddress.setCountryId(aaEvent.getCountry());
        associateAddress.setCountyId(aaEvent.getCountyId());
        associateAddress.setKeymap(aaEvent.getKeymap());
        if (aaEvent.getLatitude() != null && !aaEvent.getLatitude().equals(""))
        {
            associateAddress.setLatitude(Double.parseDouble(aaEvent.getLatitude()));
        }
        if (aaEvent.getLongitude() != null && !aaEvent.getLongitude().equals(""))
        {
            associateAddress.setLongitude(Double.parseDouble(aaEvent.getLongitude()));
        }
        associateAddress.setStateId(aaEvent.getStateId());
        associateAddress.setStreetName(aaEvent.getStreetName());
        associateAddress.setStreetNum(aaEvent.getStreetNum());
        associateAddress.setStreetTypeId(aaEvent.getStreetTypeId());
        associateAddress.setZipCode(aaEvent.getZipCode());
        associateAddress.setAddressStatus(aaEvent.getAddressStatus());
    }

    /**
     * Will post a CodeResponseEvent with the Caution Topic set for the
     * Caution(Code) parameter
     * 
     * @param caution
     */
    public static void postCaution(Code caution)
    {
        PDCodeTableHelper.postCodeResponseEvent(caution, PDJuvenileWarrantConstants.JUVENILE_WARRANT_CAUTION_EVENT_TOPIC);
    }

    /**
     * will post a CodeResponseEvent for each Caution (Code) in the collection
     * 
     * @param col
     */
    public static void postCautions(Collection col)
    {
        if (col != null)
        {
            Iterator iter = col.iterator();
            while (iter.hasNext())
            {
                Code caution = (Code) iter.next();
                PDJuvenileWarrantHelper.postCaution(caution);
            }
        }
    }

    /**
     * Will post Charge Response Events for each Charge in the colleciton
     * 
     * @param col
     */
    public static void postResponses(Collection responses)
    {
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
        Iterator i = responses.iterator();
        while (i.hasNext())
        {
            ResponseEvent event = (ResponseEvent) i.next();
            dispatch.postEvent(event);
        }
    }

    /**
     * Will Post a Juvenile Assocatie Response Event
     * 
     * @param associate
     */
    public static void postJuvenileAssociate(JuvenileAssociate associate)
    {
        if (associate != null)
        {
            IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
            dispatch.postEvent(PDJuvenileWarrantHelper.getJuvenileAssociateResponseEvent(associate));
        }
    }

    /**
     * Will post Juvenile Associate Response Events for each Juv Associate in
     * the collection. Will also post the Address Response Events if any for
     * each Associate.
     * 
     * @param col
     */
    public static void postJuvenileAssociates(Collection col)
    {
        if (col != null)
        {
            Iterator iter = col.iterator();
            while (iter.hasNext())
            {
                JuvenileAssociate associate = (JuvenileAssociate) iter.next();
                PDJuvenileWarrantHelper.postJuvenileAssociate(associate);
                PDAddressHelper.postAddresses(associate.getAddresses());
            }
        }
    }

    /**
     * @param associateEvent
     * @param juvenileAssociate
     */
    private static void setJuvenileAssociateFields(CreateJuvenileAssociateEvent associateEvent, JuvenileAssociate juvAssociate)
    {
        juvAssociate.setWarrantNum(associateEvent.getWarrantNum());
        juvAssociate.setLastName(associateEvent.getAssociateLastName());
        juvAssociate.setFirstName(associateEvent.getAssociateFirstName());
        juvAssociate.setMiddleName(associateEvent.getAssociateMiddleName());
        juvAssociate.setCellPhone(associateEvent.getCellPhone());
        juvAssociate.setSsn(associateEvent.getAssociateSsn());
        juvAssociate.setDateOfBirth(associateEvent.getAssociateDateOfBirth());
        juvAssociate.setHomePhone(associateEvent.getHomePhone());
        juvAssociate.setWorkPhone(associateEvent.getWorkPhone());
        juvAssociate.setFaxLocation(associateEvent.getFaxLocation());
        juvAssociate.setFaxNumber(associateEvent.getFax());
        juvAssociate.setPager(associateEvent.getPager());
        juvAssociate.setEmail1(associateEvent.getEmail1());
        juvAssociate.setEmail2(associateEvent.getEmail2());
        juvAssociate.setEmail3(associateEvent.getEmail3());
        juvAssociate.setDlNumber(associateEvent.getDlNumber());

        //Set codes for associate
        juvAssociate.setRaceId(associateEvent.getAssociateRace());
        juvAssociate.setRelationshipToJuvenileId(associateEvent.getRelationshipToJuvenile());
        juvAssociate.setSexId(associateEvent.getAssociateSex());
        juvAssociate.setDlStateId(associateEvent.getDlState());
    }

    /**
     * 
     * @param requestEvent
     */
    public static void updateJuvenileReleaseDecision(UpdateJuvenileReleaseInfoEvent requestEvent)
    {
        JuvenileWarrant warrant = JuvenileWarrant.find(requestEvent.getWarrantNum());
        warrant.setReleaseDecisionId(requestEvent.getReleaseDecision());
        warrant.setReleaseDecisionUserId(requestEvent.getLogonId());
        warrant.setReleaseDecisionTimeStamp(requestEvent.getReleaseDecisionTimeStamp());

        // send notification
        warrant.sendReleaseDecisionNotification(warrant);

        JuvenileWarrantWorker worker = new JuvenileWarrantWorker();
        worker.setTransferFromReleaseDecisionTimer(warrant);
        worker.sendFailureToEnterReleaseDetails(warrant, requestEvent.getLogonId());
    }

    /**
     * @author asrvastava
     * 
     * This method records the Juvenile's release to Juvenile Probation Intake
     * @param requestEvent
     */
    public static void updateJuvenileReleaseToJPInfo(UpdateJuvenileReleaseToJPInfoEvent requestEvent)
    {
        JuvenileWarrant warrant = JuvenileWarrant.find(requestEvent.getWarrantNum());
        warrant.setTransferOfficerId(requestEvent.getTransferOfficerId());
        warrant.setTransferOfficerDepartmentId(requestEvent.getTransferOfficerDepartmentId());
        warrant.setTransferLocationId(requestEvent.getTransferLocation());
        warrant.setTransferTimeStamp(requestEvent.getTransferDate());

        //Unregister old notification
        JuvenileWarrantWorker worker = new JuvenileWarrantWorker();
        worker.unregisterReleaseDecisionTime(warrant);
        worker.unregisterFailureToRelesaseDetails(warrant);
    }

    /**
     * @author asrvastava
     * 
     * This method records the Juvenile's release to person
     * @param requestEvent
     */
    public static void updateJuvenileReleaseToPersonInfo(UpdateJuvenileReleaseToPersonInfoEvent requestEvent)
    {
        JuvenileWarrant warrant = JuvenileWarrant.find(requestEvent.getWarrantNum());
        warrant.setTransferTimeStamp(new Date());

        String associateNum = requestEvent.getAssociateId();
        if (associateNum != null && !associateNum.equals(""))
        {
            warrant.setReleaseAssociateNum(associateNum);

            Collection juvAssociates = warrant.getJuvenileAssociates();
            if (juvAssociates != null)
            {
                Iterator i = juvAssociates.iterator();
                while (i.hasNext())
                {
                    JuvenileAssociate associate = (JuvenileAssociate) i.next();
                    if (associate.getAssociateNum().equals(associateNum))
                    {
                        associate.setReleasedTo(PDJuvenileWarrantConstants.RELEASE_TO_YES);
                    }
                }
            }
        }
        else
        {
            // create a new associate
            CreateJuvenileAssociateEvent associateEvent = requestEvent.getAssociateEvent();
            JuvenileAssociate juvenileAssociate = PDJuvenileWarrantHelper.createJuvenileAssociate(associateEvent);
            warrant.setReleaseAssociateNum(juvenileAssociate.getAssociateNum());
            juvenileAssociate.setReleasedTo(PDJuvenileWarrantConstants.RELEASE_TO_YES);
        }

    }  

    public static void sendAssociatesFields(IDispatch dispatch, JuvenileWarrant juvWarrant)
    {
        Collection juvAssociates = juvWarrant.getJuvenileAssociates();
        if (juvAssociates != null)
        {
            Iterator i = juvAssociates.iterator();
            while (i.hasNext())
            {
                JuvenileAssociate associate = (JuvenileAssociate) i.next();
                JuvenileAssociateResponseEvent associateEvent = PDJuvenileWarrantHelper
                        .getSimpleJuvenileAssociateResponseEvent(associate);

                dispatch.postEvent(associateEvent);
            }
        }

    }

    /**
     * Private constructor
     *  
     */
    private PDJuvenileWarrantHelper()
    {
    }

    public static void sendAssociateValues(IDispatch dispatch, IJuvenileAssociatesInfo juvenileAssociatesInfo)
    {
        // If the mother/father/other name is blank do not send an address.

        // Mother - Associate Info and Address
        if (juvenileAssociatesInfo.getMothersLastName() != null && !juvenileAssociatesInfo.getMothersLastName().equals(""))
        {
            JuvenileAssociateResponseEvent associateEvent = juvenileAssociatesInfo.getMother();            
            dispatch.postEvent(associateEvent);
        }

        // Father - Associate Info and Address
        if (juvenileAssociatesInfo.getFathersLastName() != null && !juvenileAssociatesInfo.getFathersLastName().equals(""))
        {
            JuvenileAssociateResponseEvent associateEvent = juvenileAssociatesInfo.getFather();            
            dispatch.postEvent(associateEvent);
        }

        // Other - Associate Info and Address
        if (juvenileAssociatesInfo.getOtherLastName() != null && !juvenileAssociatesInfo.getOtherLastName().equals(""))
        {
            JuvenileAssociateResponseEvent associateEvent = juvenileAssociatesInfo.getOther();
            dispatch.postEvent(associateEvent);
        }
        
       // Alternates - Associate Info and Address
        if (juvenileAssociatesInfo.getAlterLastName() != null && !juvenileAssociatesInfo.getAlterLastName().equals(""))
        {
            JuvenileAssociateResponseEvent associateEvent = juvenileAssociatesInfo.getAlter();
            dispatch.postEvent(associateEvent);
        }

    }

    public static void sendPetitions(IDispatch dispatch, Collection petitions)
    {
        if (petitions != null)
        {
            Iterator p = petitions.iterator();

            while (p.hasNext())
            {
                JJSPetition pet = (JJSPetition) p.next();
                ResponseEvent petitionEvent = pet.valueObject();
                dispatch.postEvent(petitionEvent);
            }
        }

    }

}