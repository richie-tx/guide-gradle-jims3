package pd.juvenile;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import messaging.codetable.person.reply.ScarsMarksTattoosCodeResponseEvent;
import messaging.codetable.reply.CodeResponseEvent;
import messaging.juvenile.reply.JuvenileProfileDetailResponseEvent;
import messaging.juvenilecase.GetJuvenileDetentionFacilitiesEvent;
import messaging.juvenilecase.reply.JuvenileCasefileResponseEvent;
import messaging.juvenilecase.reply.JuvenileDetentionFacilitiesResponseEvent;
import messaging.officer.GetOfficerProfilesEvent;
import messaging.productionsupport.GetProductionSupportCasefilesByJuvEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.util.CollectionUtil;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import mojo.pattern.IBuilder;
import naming.PDCodeTableConstants;
import naming.PDJuvenileConstants;
import naming.ProductionSupportControllerServiceNames;
import pd.codetable.criminal.JuvenileFacility;
import pd.codetable.criminal.JuvenileMasterStatus;
import pd.codetable.person.ScarsMarksTattoosCode;
import pd.contact.officer.OfficerProfile;
import pd.juvenilecase.JJSFacility;
import pd.juvenilecase.JJSHeader;
import pd.juvenilecase.JJSJuvenile;
import pd.juvenilecase.JuvenileCaseHelper;
import pd.juvenilecase.JuvenileCasefile;
import pd.km.util.Name;
import pd.supervision.programreferral.JuvenileProgramReferralAssignmentHistory;
import ui.common.CodeHelper;
import ui.common.SimpleCodeTableHelper;
import ui.common.UIUtil;
import pd.codetable.Code;

public class JuvenileBuilder implements IBuilder
{
	private Juvenile juv;

	private JuvenileCore juvCore;
	
	private JuvenileAlias juvenileAlias;

	private JuvenileProfileDetailResponseEvent response;

	private Map<String, String> statusCodeLookup = new HashMap<String, String>();
	
	public JuvenileBuilder(Juvenile juv, JuvenileCore juvCore)
	{
		this();
		this.juv = juv;
		this.juvCore = juvCore;
	}
	
	public JuvenileBuilder(Juvenile juv, JuvenileAlias juvenileAlias) {
		this();
		this.juv = juv;
		this.juvenileAlias = juvenileAlias; 
	}

	public JuvenileBuilder()
	{
		this.clear();
		this.statusCodeLookup = new HashMap<String, String>();
	}

	public void setStatusCodes()
	{
		Collection codes = Code.findAll(PDCodeTableConstants.JUVENILE_PROFILE_STATUS);

		Iterator i = codes.iterator();

		while (i.hasNext())
		{
			Code code = (Code) i.next();
			this.statusCodeLookup.put(code.getCode(), code.getDescription());
		}
	    /*Iterator statusIter = JuvenileMasterStatus.findAll();
	    while (statusIter.hasNext())
	    {
			JuvenileMasterStatus status = (JuvenileMasterStatus) statusIter.next();
			this.statusCodeLookup.put(status.getCode(), status.getDescription());
	    }*/

	}
	public void buildLight()
	{
		this.setCore();
		//this.setNonCore();
	}

	public void build()
	{
	    	this.clear();
		this.setCore();
		this.setNonCore();
	}
	 public Collection getCounties()
	    {
	        return CodeHelper.getCountyCodes();
	    }

	public JuvenileProfileDetailResponseEvent setCore()
	{
		response.setTopic(PDJuvenileConstants.JUVENILE_PROFILE_DETAIL_TOPIC);
		if(juvCore != null){
			response.setFirstName(nvl(juvCore.getFirstName()));
			response.setJuvenileNum(nvl(juvCore.getJuvenileNum()));
			response.setLastName(nvl(juvCore.getLastName()));
			response.setMiddleName(nvl(juvCore.getMiddleName()));
			response.setPreferredFirstName(nvl(juvCore.getPreferredFirstName()));
			response.setNameSuffix(nvl(juvCore.getNameSuffix()));
			response.setRaceId( juvCore.getRaceId() );
			response.setOriginalRaceId( juvCore.getOriginalRaceId() );
			response.setRace(juvCore.getRaceCodeDescription());
			response.setSex(juvCore.getSexCodeDescription());
			response.setSexId(juvCore.getSexId());
			response.setDateOfBirth(juvCore.getDateOfBirth());
			response.setRealDOB(juvCore.getRealDOB());
			response.setJuvenileType(nvl(juvCore.getJuvenileType()));
			response.setHispanic(juvCore.getHispanicInd()); //U.S 88526
			response.setMasterStatusId(juvCore.getStatusId());
			response.setLastActionBy(juvCore.getLastActionBy());
			
			if( juvCore.getStatusId() != null ){
			    
			    response.setMasterStatus( JuvenileBuilder.getMasterStatusDesc( juvCore.getStatusId()  ));
			}else{
			    response.setMasterStatus(""); 
			}
			
			response.setOperator(juvCore.getOperator());
			response.setLcuser(juvCore.getLcuser());
			response.setCheckedOutTo(juvCore.getCheckOutTo());
			response.setCheckedOutDate(juvCore.getCheckedOutDate());//bug fix: 89372
			//  06/08/2012 - revised max age from 18 to 20 per ER 71590
			if (juvCore.getAgeInYears(juvCore.getDateOfBirth()) >= 21){
				response.setUpdatable(false);
			} else {
				response.setUpdatable(true);
			}
			//User Story 39892
			if(juvCore.getSSN()!=null && !juvCore.getSSN().equals(""))
			{
				//check if the ssn is a repeat of a single character
				String ssn = juvCore.getSSN();
				String firstChar = ssn.substring(0, 1);
				String matchingChars[] = ssn.split(firstChar+ "+");
				boolean repeatChars = (matchingChars.length == 0);
				  if (!repeatChars)//Individual has never had a social security number.
				  {
					  response.setSSN("XXXXX"+juvCore.getSSN().substring(5));
				  }
				  else 
					  response.setSSN(juvCore.getSSN());
			}
			
			response.setCompleteSSN(juvCore.getSSN());
			//response.setSSN(juvCore.getSSN());
			Name name = new Name(response.getFirstName(), response.getMiddleName(), response.getLastName(), response.getNameSuffix() );
			response.setFormattedName(name.getFormattedName());
			
			JuvenileDetentionFacilitiesResponseEvent facilityResp = getJuvenileFacility(juvCore.getJuvenileNum());
			if(facilityResp!=null)
			{
				response.setDetentionStatusId(facilityResp.getDetentionStatus());
				response.setDetentionStatus(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.FACILITY_STATUS,facilityResp.getDetentionStatus()));
				response.setReleaseDate(facilityResp.getReleaseDateStr());
				response.setNexthearingDate(facilityResp.getNexthearingDate());
				//Defect 53065 - JJS only displays facility if there is a status.
				if(facilityResp.getDetentionStatus()!=null && !facilityResp.getDetentionStatus().equals(""))
				{
					response.setDetentionFacility(facilityResp.getDetainedFacilityDesc());
					response.setDetentionFacilityId(facilityResp.getDetainedFacility());
					//task 173551
					response.setFloorNum(facilityResp.getFloorNum());
					response.setUnitNum(facilityResp.getUnit());
					response.setRoomNum(facilityResp.getRoomNum());					
				}				
			}
			/*Iterator<JJSHeader> jjs_headerItr = JJSHeader.findAll("juvenileNumber", juvCore.getJuvenileNum()); //bug fix 39505
			if(jjs_headerItr!=null){
				if(jjs_headerItr.hasNext()){
					JJSHeader jjs_headerInfo = jjs_headerItr.next();
					if (jjs_headerInfo != null)
					{
						if (jjs_headerInfo.getFacilityStatus() != null && !jjs_headerInfo.getFacilityStatus().equals("")) {
							response.setDetentionFacility(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.JUVENILE_DETENTION_FACILITY,jjs_headerInfo.getFacilityCode()));
							response.setDetentionFacilityId(jjs_headerInfo.getFacilityCode());
							response.setDetentionStatus(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.FACILITY_STATUS,jjs_headerInfo.getFacilityStatus()));
							response.setDetentionStatusId(jjs_headerInfo.getFacilityStatus());
						}
					}
				}
			}*/
			response.setStudentId(juvCore.getOID()); //code clean up # bug 23603
			//User Story 70421 Referrals Conversion
			response.setAddressId(String.valueOf(juvCore.getAddressId()));
			response.setJuvAddress(juvCore.getJuvAddress());
			response.setJuvCounty(juvCore.getJuvCounty());//for empty strings
			CodeResponseEvent evt = null;
			 if (juvCore.getJuvCounty() != null && !juvCore.getJuvCounty().equals(""))
			        {
			            evt = UIUtil.findCodeResponseEvent(getCounties().iterator(), juvCore.getJuvCounty());
			            if(evt != null){
			            response.setJuvCounty(evt.getDescription());
			            }
			        }else if (juvCore.getJuvCounty()== null){
			            response.setJuvCounty("");
			        }
			response.setJuvSchoolDist(juvCore.getSchoolDistrictId());
			response.setJuvSchoolName(juvCore.getSchoolId());
			response.setSsn1(juvCore.getSsn1());
			response.setSsn2(juvCore.getSsn2());
			response.setSsnRel1(juvCore.getSsnRel1());
			response.setSsnRel2(juvCore.getSsnRel2());
			if(juvCore.getRecType() != null)
			    response.setRecType(juvCore.getRecType().toUpperCase()); //Bug #77723
			response.setComments(juvCore.getComments());
			response.setYouthDeathReason(juvCore.getYouthDeathReason());
			response.setYouthDeathVerification(juvCore.getYouthDeathVerification());
			response.setDeathDate(juvCore.getDeathDate());
			response.setDeathAge(juvCore.getDeathAge());
			response.setPurgeBoxNum(juvCore.getPurgeBoxnum());
			response.setPurgeSerNum(juvCore.getPurgeSernum());
			response.setPurgeComments(juvCore.getPurgeComments());
			
			Collection<JuvenileCasefile>casefiles = juvCore.getCaseFiles();
			List<JuvenileCasefile>casefileList = new ArrayList();
			String latestCasefileId 	= "";
			String supervisionTypeCd	= "";
			String latestSeqNum 		= "";
			String officerId		= "";
			int activeCasefileCount = 0;
			casefileList.addAll(casefiles);
			
			
			List<JuvenileCasefile>activeCasefiles = new ArrayList<>();
			List<JuvenileCasefile>inactiveCasefiles = new ArrayList<>();
			
			for (int i = 0; i < casefileList.size() ; i++ ){
			    if ( "A".equals( casefileList.get(i).getCaseStatusId() )) {
				activeCasefileCount++;
				if ( activeCasefileCount == 1) {
				    
				    latestCasefileId 	= casefileList.get(i).getCasefileId() ;
				    supervisionTypeCd 	= casefileList.get(i).getSupervisionTypeId();
				    latestSeqNum	= casefileList.get(i).getSequenceNumber();
				    officerId		= casefileList.get(i).getProbationOfficerId();
				}
				
				if ( activeCasefileCount > 1 
					&& Integer.parseInt( latestSeqNum ) < Integer.parseInt( casefileList.get(i).getSequenceNumber() ) ) {
				 
				    latestCasefileId 	= casefileList.get(i).getCasefileId() ;
				    supervisionTypeCd 	= casefileList.get(i).getSupervisionTypeId();
				    latestSeqNum	= casefileList.get(i).getSequenceNumber();
				    officerId		= casefileList.get(i).getProbationOfficerId();
				}
				
			    } else {
				inactiveCasefiles.add( casefileList.get(i) );
			    }
			}
			
			
			if ( latestCasefileId == ""
				&& inactiveCasefiles.size() > 0 ){
			    
			    Collections.sort(inactiveCasefiles, Collections.reverseOrder( new Comparator<JuvenileCasefile>(){
				@Override
				public int compare(JuvenileCasefile c1, JuvenileCasefile c2){
				    return c1.getSequenceNumber().compareTo(c2.getSequenceNumber());
				}
			    }));
				 
			    latestCasefileId 	= inactiveCasefiles.get(0).getCasefileId() ;
			    supervisionTypeCd 	= inactiveCasefiles.get(0).getSupervisionTypeId();
			    latestSeqNum	= inactiveCasefiles.get(0).getSequenceNumber();
			    officerId		= inactiveCasefiles.get(0).getProbationOfficerId();
			}
			
			if(officerId !=null
				&& officerId.length() > 0 )
			{
			    	OfficerProfile officerProfile = OfficerProfile.find(officerId);
				if ( officerProfile != null )
				{
					Name officerName = new Name(officerProfile.getFirstName(), officerProfile.getMiddleName(), officerProfile.getLastName());
					response.setJpoOfRecord(officerName.getFormattedName());
					response.setJpoOfRecID(officerProfile.getLogonId());
					response.setJpoEmail(officerProfile.getEmail());
					response.setJpoPhoneNumber(officerProfile.getWorkPhoneNum());
				}
			}
			if ( supervisionTypeCd != null
				&& supervisionTypeCd.length() > 0 ) {
			    response.setSprvsionTypeCd( supervisionTypeCd );
			    response.setSprvsionType( CodeHelper.getCodeDescription(PDCodeTableConstants.JUVENILE_CASEFILE_SUPERVISION_TYPE , supervisionTypeCd) );
			}
			
			response.setLiveWithTjjd( juvCore.getLiveWithTjjd() );
			
			if ( response.getLiveWithTjjd() != null
				&& response.getLiveWithTjjd().length() > 0 ) {
			    response.setLiveWithTjjdDesc( CodeHelper.getCodeDescription(PDCodeTableConstants.YOUTH_LIVES_WITH, response.getLiveWithTjjd() ) );
			}
			
			//US 186179
			response.setUpdateUser(juvCore.getUpdateUser());
			response.setUpdateDate(juvCore.getUpdateDate());
			
		} else if(juvenileAlias != null){
			
			response.setFirstName(juvenileAlias.getFirstName());
			response.setJuvenileNum(juvenileAlias.getJuvenileNum());
			response.setLastName(juvenileAlias.getLastName());
			response.setMiddleName(juvenileAlias.getMiddleName());
			response.setNameSuffix(juvenileAlias.getNameSuffix());
			response.setRaceId(juvenileAlias.getRaceId());
			response.setRace(juvenileAlias.getRaceCodeDescription());
			response.setSex(juvenileAlias.getSexCodeDescription());
			response.setSexId(juvenileAlias.getSexId());
			response.setDateOfBirth(juvenileAlias.getDateOfBirth());
			response.setJuvenileType(juvenileAlias.getJuvenileType());
			//add master status to response for alias record too by a call to jjs_ms_juvenile
			if( "P".equalsIgnoreCase( juvenileAlias.getJuvenileType() )){
			    response.setPreferredFirstName("*" + juvenileAlias.getAliasNotes() );
			}
			//  06/08/2012 - revised max age from 18 to 20 per ER 71590
			if (juvenileAlias.getAgeInYears(juvenileAlias.getDateOfBirth()) >= 21){
				response.setUpdatable(false);
			} else {
				response.setUpdatable(true);
			}
			Name name = new Name(response.getFirstName(), response.getMiddleName(), response.getLastName(), response.getNameSuffix());
			response.setFormattedName(name.getFormattedName());
		
			JuvenileDetentionFacilitiesResponseEvent facilityResp = getJuvenileFacility(juvenileAlias.getJuvenileNum());
			if(facilityResp!=null)
			{
				response.setDetentionStatusId(facilityResp.getDetentionStatus());
				response.setDetentionStatus(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.FACILITY_STATUS,facilityResp.getDetentionStatus()));
				response.setReleaseDate(facilityResp.getReleaseDateStr());
				//Defect 53065 - JJS only displays facility if there is a status.
				if(facilityResp.getDetentionStatus()!=null && !facilityResp.getDetentionStatus().equals(""))
				{
					response.setDetentionFacility(facilityResp.getDetainedFacilityDesc());
					response.setDetentionFacilityId(facilityResp.getDetainedFacility());
				}				
			}
		
			/*
			Iterator<JJSHeader> jjs_headerItr = JJSHeader.findAll("juvenileNumber", juvenileAlias.getJuvenileNum()); //bug fix:39505
			if(jjs_headerItr!=null){
				if(jjs_headerItr.hasNext()){
					JJSHeader jjs_headerInfo = jjs_headerItr.next();
					if (jjs_headerInfo != null)
					{
						if (jjs_headerInfo.getFacilityStatus() != null && !jjs_headerInfo.getFacilityStatus().equals("")) {
							response.setDetentionFacility(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.JUVENILE_DETENTION_FACILITY,jjs_headerInfo.getFacilityCode()));
							response.setDetentionFacilityId(jjs_headerInfo.getFacilityCode());
							response.setDetentionStatus(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.FACILITY_STATUS,jjs_headerInfo.getFacilityStatus()));
							response.setDetentionStatusId(jjs_headerInfo.getFacilityStatus());
						}
					}
				}
			}*/
			response.setStudentId(juvenileAlias.getJuvenileNum()); //code clean up # bug 23603
			
			
		}
		
		return response;
	}

	public void setNonCore()
	{
		if (juv != null)
		{
			response.setAlienNumber(juv.getAlienNumber());
			response.setBirthCity(juv.getBirthCityId());
			response.setBirthCountry(juv.getBirthCountryId());
			response.setBirthCounty(juv.getBirthCountyId());
			response.setBirthState(juv.getBirthStateId());
			response.setComplexion(juv.getComplexionId());
			response.setDateSenttoDPS(juv.getDateSenttoDPS());
			response.setDHSNumber(juv.getDHSNumber());
			response.setDNASampleNumber(juv.getDNASampleNumber());
			response.setDriverLicenseNumber(juv.getDriverLicenseNumber());
			response.setDriverLicenseState(juv.getDriverLicenseStateId());
			response.setEthnicity(juv.getEthnicityId());
			response.setFBINumber(juv.getFBINumber());
			response.setHispanic(juv.getHispanic()); //U.S 88526
			response.setMultiracial(juv.isMultiracial());
			response.setNationality(juv.getNationalityId());
			response.setNatualEyeColor(juv.getNatualEyeColorId());
			response.setNaturalHairColor(juv.getNaturalHairColorId());
			response.setPEIMSId(juv.getPEIMSId());
			response.setTSDSId(juv.getPEIMSId());
			response.setIsUSCitizen(juv.getIsUSCitizenId());
			response.setPrimaryLanguage(juv.getPrimaryLanguageId());
			response.setReligion(juv.getReligionId());
			response.setSecondaryLanguage(juv.getSecondaryLanguageId());
			response.setSID(juv.getSIDNumber());
			response.setSONumber(juv.getSONumber());
			response.setEducationId(juv.getEducationId());
			//ER- 76951 changes
			response.setStudentId(juv.getStudentId());
			response.setVerifiedDOB(juv.isVerifiedDOB());
			response.setDriverLicenseClassId(juv.getDriverLicenseClassId());
			response.setDriverLicenseExpireDate(juv.getDriverLicenseExpireDate());
			response.setAdopted(juv.isAdopted());
			response.setFailedPlacements(juv.getFailedPlacements());
			response.setAdoptionComments(juv.getAdoptionComments());
			response.setScarsAndMarks(getScarsMarksForDetailResponse());
			response.setMentalHealthServices(juv.isMentalHealthServices());
			//US 40492
			response.setJpoOfRecID(juv.getJpoOfRecId());			
			response.setPassportNum(juv.getPassportNum());
			response.setCountryOfIssuanceId(juv.getCountryOfIssuanceId());
			response.setPassportExpirationDate(juv.getPassportExpirationDate());
			
			//get the officer name
			if(juv.getJpoOfRecId()!=null && !juv.getJpoOfRecId().isEmpty())
			{
				GetOfficerProfilesEvent event = new GetOfficerProfilesEvent();
				event.setUserID(juv.getJpoOfRecId());
				event.setStatus("A");
				Iterator iter = OfficerProfile.findAll(event);
				if (iter.hasNext())
				{
					OfficerProfile officerProfile = (OfficerProfile) iter.next();
					Name officerName = new Name(officerProfile.getFirstName(), officerProfile.getMiddleName(), officerProfile.getLastName());
					response.setJpoOfRecord(officerName.getFormattedName());
				}
			}
			else
			{
			    response.setJpoOfRecord(null);
			    response.setJpoEmail(null);//response.setJpoEmail("Data.Corrections@hcjpd.hctx.net");
			    response.setJpoPhoneNumber(null);
			}
		
			if (juv != null && juv.getJuvenileNum() != null)
			{
				JJSJuvenile myJJSInfo = juv.getJjsJuvInfo();
				
				if (myJJSInfo != null)
				{
					response.setPurgeFlag(myJJSInfo.getPurgeFlag());
					
					JuvenileDetentionFacilitiesResponseEvent facilityResp = getJuvenileFacility(juv.getJuvenileNum());
					if(facilityResp!=null)
					{
						response.setDetentionStatusId(facilityResp.getDetentionStatus());
						response.setDetentionStatus(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.FACILITY_STATUS,facilityResp.getDetentionStatus()));
						//add release date to response
						response.setReleaseDate(facilityResp.getReleaseDateStr());
						//Defect 53065 - JJS only displays facility if there is a status.
						if(facilityResp.getDetentionStatus()!=null && !facilityResp.getDetentionStatus().equals(""))
						{
							response.setDetentionFacility(facilityResp.getDetainedFacilityDesc());
							response.setDetentionFacilityId(facilityResp.getDetainedFacility());
						}				
					}
					/*
					Iterator<JJSHeader> jjs_headerItr = JJSHeader.findAll("juvenileNumber", juvCore.getJuvenileNum()); //bug fix #40652 
					if(jjs_headerItr!=null){
						if(jjs_headerItr.hasNext()){
							JJSHeader jjs_headerInfo = jjs_headerItr.next();
							if (jjs_headerInfo!=null&& jjs_headerInfo.getFacilityStatus() != null && !jjs_headerInfo.getFacilityStatus().equals("")) {
								response.setDetentionFacility(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.JUVENILE_DETENTION_FACILITY,jjs_headerInfo.getFacilityCode()));
								response.setDetentionFacilityId(jjs_headerInfo.getFacilityCode());
								response.setDetentionStatus(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.FACILITY_STATUS,jjs_headerInfo.getFacilityStatus()));
								response.setDetentionStatusId(jjs_headerInfo.getFacilityStatus());
							}
						}
					}*/
					/* bug fix #40652 response.setDetentionStatusId(myJJSInfo.getDetentionStatusId());
					response.setDetentionStatus(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.FACILITY_STATUS,myJJSInfo.getDetentionStatusId()));*/
//					Defect 53065 - JJS only displays facility if there is a status.  Modified this code to do the same.
					/*if (myJJSInfo.getDetentionStatusId() != null && !myJJSInfo.getDetentionStatusId().equals("")) {
						response.setDetentionFacilityId(myJJSInfo.getDetentionFacilityId());
						response.setDetentionFacility(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.JUVENILE_DETENTION_FACILITY,myJJSInfo.getDetentionFacilityId()));
					}*/
				}
				
				List<JuvenileCasefileResponseEvent>casefiles = new ArrayList<JuvenileCasefileResponseEvent>();
				Iterator<JuvenileCasefile>casefileIter = JuvenileCasefile.findAll("juvenileId", juv.getJuvenileNum());
				while( casefileIter.hasNext() ) {
				    JuvenileCasefile casefile = (JuvenileCasefile) casefileIter.next();
					JuvenileCasefileResponseEvent casefileResponse =
						JuvenileCaseHelper.getJuvenileCasefileResponse(casefile);
					casefiles.add(casefileResponse);
				}
					
				if ( casefiles != null
					&& casefiles.size() > 0 ){
				    Collections.sort(casefiles, new Comparator<JuvenileCasefileResponseEvent>(){
					    @Override
					    public int compare(JuvenileCasefileResponseEvent c1, JuvenileCasefileResponseEvent c2 ){
						return c2.getSupervisionNum().compareTo(c1.getSupervisionNum());
					    }
				    });
				    response.setCasefiles(casefiles);
				    
				}
				
			}
		}
	}

	public List getScarsMarksForDetailResponse()
	{
		int len = juv.getScarsAndMarks().size();
		List scarsAndMarksRet = new ArrayList(len);
		Iterator s = juv.getScarsAndMarks().iterator();
		while (s.hasNext())
		{
			ScarsMarksTattoosCode code = (ScarsMarksTattoosCode) s.next();
			ScarsMarksTattoosCodeResponseEvent scarEvent = new ScarsMarksTattoosCodeResponseEvent();
			code.fill(scarEvent);
			scarsAndMarksRet.add(scarEvent);
		}
		return scarsAndMarksRet;
	}

	public Object getResult()
	{
		return this.response;
	}

	public void setJuvenileCore(JuvenileCore juvenileCore)
	{
		this.juvCore = juvenileCore;
	}
	public void setJuvenileAlias(JuvenileAlias juvenileAlias){
		this.juvenileAlias = juvenileAlias;
	}

	public void clear()
	{
		this.response = new JuvenileProfileDetailResponseEvent();
	}

	public void setStatus(String status)
	{
		this.response.setStatusId(status);
		String desc = this.statusCodeLookup.get(status);
		//String desc = this.getMasterStatusDesc(status);
		this.response.setStatus(desc);
	}
	public void setmasterStatus(String status)
	{
		this.response.setMasterStatusId(status);
		String desc = this.getMasterStatusDesc(status);
		this.response.setMasterStatus(desc);
	}
	/**
	 * Get facilty name and id from header
	 * @param juvNum
	 * @return
	 */
	public static JuvenileDetentionFacilitiesResponseEvent getJuvenileFacility(String juvNum)
	{
		Iterator<JJSHeader>  jjsHeaderItr = JJSHeader.findAll("juvenileNumber", juvNum) ;
		JuvenileDetentionFacilitiesResponseEvent detentionFacility = new JuvenileDetentionFacilitiesResponseEvent();
		if (jjsHeaderItr.hasNext()) {
			JJSHeader header =  jjsHeaderItr.next();
			detentionFacility.setDetentionStatus(header.getFacilityStatus());
			detentionFacility.setDetainedFacility(header.getFacilityCode());
			detentionFacility.setDetainedFacilityDesc(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.JUVENILE_DETENTION_FACILITY,header.getFacilityCode())); //bug fix #51529
			//task 173552
			if (header.getNextHearingDate() != null )
        		{
        		    if (DateUtil.compare(header.getNextHearingDate(), DateUtil.getCurrentDate(), DateUtil.DATE_FMT_1) < 0)
        		    {
        			detentionFacility.setNexthearingDate(null);
        		    }
        		    else
        			detentionFacility.setNexthearingDate(header.getNextHearingDate());
        		}
			//detentionFacility.setNexthearingDate(header.getNextHearingDate());
			// add code to get the release date from jjs_detention for the highest seqnum and juvnum
			GetJuvenileDetentionFacilitiesEvent facilityDetailsEvt = new GetJuvenileDetentionFacilitiesEvent();
			facilityDetailsEvt.setJuvenileNum(header.getJuvenileNumber());
			facilityDetailsEvt.setReferralNum(header.getReferralNumber());
			Iterator<JJSFacility> facilityItr = JJSFacility.findAll(facilityDetailsEvt);
			
			while (facilityItr.hasNext())
			{
			    JJSFacility fac = (JJSFacility) facilityItr.next();
			   if(header.getLastSequenceNumber().equals(fac.getFacilitySequenceNumber()))
			   {			       
			       if (fac.getReleaseDate() != null)
			       {
				   SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
				   //detentionFacility.setReleaseDate(fac.getReleaseDate());
				   detentionFacility.setReleaseDateStr(sdf.format(fac.getReleaseDate()));
				   
			       }			       
			       //task 173551
			       else
			       {
				   //check for JJS_COD_FACILITY.vendor=IL
				   JuvenileFacility facility = null;
				   if(fac.getDetainedFacility()!=null && !fac.getDetainedFacility().isEmpty())
				   {
        				   facility = JuvenileFacility.find("code",fac.getDetainedFacility());	
        				   if(facility!=null && facility.getVendor().equalsIgnoreCase("IL"))
        				   {
                				   detentionFacility.setFloorNum(fac.getFloorNum());
                				   detentionFacility.setUnit(fac.getUnit());
                				   detentionFacility.setRoomNum(fac.getRoomNum());
        				   }
				   }
			       }
				   
			   }
			       
			}
		}
		return detentionFacility;
	}
	
	public static String getMasterStatusDesc(String statusId) 
	{
		String masterStatus="";
		JuvenileMasterStatus juvenileMasterStatus = null;
		if ( statusId != null &&
			statusId.length() > 0  ) {
		    juvenileMasterStatus = JuvenileMasterStatus.find("code",statusId);
		    if ( juvenileMasterStatus != null ) {
			masterStatus =  juvenileMasterStatus.getDescription();
		    }
		}
		/*switch (statusId){
		    case "P" :
			masterStatus =  "PROBATION";
			break;
		    case "H" :
			masterStatus =  "HOLD";
			break;
		    case "I" :
			masterStatus =  "DEFERRED";
			break;
		    case "A" :
			masterStatus =  "ACTIVE";
			break;
		    case "C" :
			masterStatus =  "CLOSED";
			break;
		    default:
			masterStatus =  "";
		}
		*/
		return masterStatus;
	}
	
	
		

	private String nvl(String value){
	    
	    return ( value != null && value.length() > 0) ? value : "";
	}
	
	private String nvl(String value1, String value2){
	    
	    return ( value1 != null && value1.length() > 0) ? value1 : value2;
	}
	
	


}
