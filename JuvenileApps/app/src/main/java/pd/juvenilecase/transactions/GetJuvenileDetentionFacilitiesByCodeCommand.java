//Source file: C:\\views\\dev\\app\\src\\pd\\juvenilecase\\transactions\\GetJuvenileCasefilePetitionCommand.java

package pd.juvenilecase.transactions;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import messaging.juvenilecase.GetJuvenileDetentionFacilitiesByCodeEvent;
import messaging.juvenilecase.reply.JuvenileDetentionFacilitiesResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.util.DateUtil;
import naming.PDCodeTableConstants;
import pd.codetable.SimpleCodeTableHelper;
import pd.juvenile.JuvenileCore;
import pd.juvenilecase.JJSFacility;
import ui.common.ComplexCodeTableHelper;
import ui.juvenilecase.facility.JuvenileFacilityHelper;

public class GetJuvenileDetentionFacilitiesByCodeCommand implements ICommand
{

	/**
	 * @roseuid 42A9A3040091
	 */
	public GetJuvenileDetentionFacilitiesByCodeCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 42A99B990128
	 */
	public void execute(IEvent event)
	{
		GetJuvenileDetentionFacilitiesByCodeEvent pet = (GetJuvenileDetentionFacilitiesByCodeEvent) event;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		Iterator iter1 = JJSFacility.findAll(pet);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date currDate = new Date();
		String today = sdf.format(currDate);
		Calendar cal = Calendar.getInstance();
		cal.setTime(DateUtil.getCurrentDate());
		cal.add(Calendar.DAY_OF_YEAR,-1);
		String yesterday = sdf.format(cal.getTime());
		while(iter1.hasNext())
		{
			JJSFacility fac = (JJSFacility)iter1.next();					
			if(fac != null){						
				JuvenileDetentionFacilitiesResponseEvent resp = new JuvenileDetentionFacilitiesResponseEvent();
				resp.setDetainedFacility( resp.getDetainedFacility());
				String secureStatus="";
				if(fac.getSecureStatus()!=null)
				{							
					if(fac.getSecureStatus().equalsIgnoreCase("N"))
						secureStatus= "NON-SECURE";
					else
						secureStatus="SECURE";
				}
				
				resp.setSecureStatus(fac.getSecureStatus());
				resp.setAdmitReason(fac.getAdmitReason());
				resp.setReasonDescription(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.DETENTION_REASON, fac.getAdmitReason())+"-"+secureStatus);		
				if(pet.getSearchType() != null && pet.getSearchType().equalsIgnoreCase("RS"))
				{
					//if they were released yesterday or today - counted in resident status
				
					String admitDate = sdf.format(fac.getAdmitDate());					
					
					if( (fac.getDetentionStatus()!=null && (fac.getDetentionStatus().equalsIgnoreCase("D") || fac.getDetentionStatus().equalsIgnoreCase("P") 
							|| fac.getDetentionStatus().equalsIgnoreCase("V") || fac.getDetentionStatus().equalsIgnoreCase("R"))
							&& (admitDate.equals(today) || admitDate.equals(yesterday)))
					|| (fac.getDetentionStatus()!=null && (fac.getDetentionStatus().equalsIgnoreCase("E") || fac.getDetentionStatus().equalsIgnoreCase("N") || fac.getDetentionStatus().equalsIgnoreCase("T")))
					
					|| ( (fac.getDetentionStatus()==null || fac.getDetentionStatus().equals("")) &&  ( fac.getReleaseDate()!=null && (sdf.format(fac.getReleaseDate()).equals(today) ||  sdf.format(fac.getReleaseDate()).equals(yesterday))  ))
					
					|| (fac.getDetentionStatus()!=null && (fac.getDetentionStatus().equalsIgnoreCase("D") || fac.getDetentionStatus().equalsIgnoreCase("P"))
							&& (fac.getReturnReason()!=null) 
							&& (fac.getReturnDate()!=null &&(sdf.format(fac.getReturnDate()).equals(today) || sdf.format(fac.getReturnDate()).equals(yesterday)))   )
					|| (fac.getLastChangeDate()!=null && (sdf.format(fac.getLastChangeDate()).equals(today) || sdf.format(fac.getLastChangeDate()).equals(yesterday)) 
							&& (admitDate.equals(today) || admitDate.equals(yesterday) ) 
							&& (fac.isReasonChangeFlag() || fac.isLocationChangeFlag() || fac.isSecureIndicatorChangeFlag())    )
					|| (fac.getDetentionStatus()!=null && (fac.getDetentionStatus().equalsIgnoreCase("D") || fac.getDetentionStatus().equalsIgnoreCase("P") )
							&& ( ((fac.getReturnReason()!=null && !fac.getReturnReason().equals("")) && (fac.getReturnDate()!=null && ( sdf.format(fac.getReturnDate()).equals(today) || sdf.format(fac.getReturnDate()).equals(yesterday)))  ) || (admitDate.equals(today) || admitDate.equals(yesterday)))    )
					)					
						
						{
							resp.setReferralNumber(fac.getReferralNumber());
							if(fac.getReleaseAuthorizedBy()!=null)
								resp.setRelAuthByDesc(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.RELEASED_BY_AUTHORITY,fac.getReleaseAuthorizedBy()));
							if(fac.getReleaseBy()!=null)
								resp.setRelByDesc(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.RELEASED_BY_AUTHORITY,fac.getReleaseBy()));
							if(fac.getReleaseTo()!=null)
								resp.setRelToDesc(ComplexCodeTableHelper.getDescrByCode(ComplexCodeTableHelper.getReleasedFromDetentionCodes(),fac.getReleaseTo()));
							resp.setReleaseAuthorizedBy(fac.getReleaseAuthorizedBy());
							resp.setReleaseBy(fac.getReleaseBy());
							resp.setReleaseTo(fac.getReleaseTo());
							resp.setAdmitDate(fac.getAdmitDate());
							resp.setAdmitTime(fac.getAdmitTime().substring(0, 5).replaceAll(":", ""));
							resp.setFloorNum(fac.getFloorNum());
							resp.setUnit(fac.getUnit());
							resp.setRoomNum(fac.getRoomNum());
							resp.setReleaseDate(fac.getReleaseDate());
							if(fac.getReleaseTime()!=null)
								resp.setReleaseTime(fac.getReleaseTime().substring(0, 5).replaceAll(":", ""));
							resp.setFacilityStatus(fac.getDetentionStatus());
							resp.setLastChangeDate(fac.getLastChangeDate());
							resp.setLocationChangeFlag(fac.isLocationChangeFlag());
							resp.setSecureIndicatorChangeFlag(fac.isSecureIndicatorChangeFlag());
								resp.setReasonChangeFlag(fac.isReasonChangeFlag());
							resp.setOtherChangeFlag(fac.isOtherChangeFlag());
							resp.setLcTime(JuvenileFacilityHelper.getHoursMinsFromTime(fac.getLcTime()));
							resp.setMultipleOccupyUnit(fac.getMultipleOccupyUnit());
							resp.setReturnReason(fac.getReturnReason());
						/*	if(fac.getReturnReason()!=null)
								resp.setReturnReason(SimpleCodeTableHelper.getDescrByCode("RETURN_REASON", fac.getReturnReason()));*/
							resp.setReturnDate(fac.getReturnDate());
							resp.setReturnTime(JuvenileFacilityHelper.getHoursMinsFromTime(fac.getReturnTime()));
							resp.setAdmitAuthority(fac.getAdmitAuthority());
							resp.setChangeExplanation(fac.getChangeExplanation());
							if(fac.getDetentionStatus()!=null && fac.getDetentionStatus().equalsIgnoreCase("E"))
							{
								resp.setRelocationDate(fac.getRelocationDate());
								
							}
						}
						else
							continue;
					
				}
				if(fac.getDetentionStatus()!=null)
				{
					if(fac.getDetentionStatus().equalsIgnoreCase("v"))
						resp.setSecureStatus("v");
					else
						resp.setSecureStatus(fac.getSecureStatus());
				}
				pet.setJuvenileNum(fac.getJuvenileNumber());
				pet.setReferralNumber(fac.getReferralNumber());
				//Iterator iter2 = JuvenileCore.findAll(pet);
				
				JuvenileCore juv = JuvenileCore.findCore(fac.getJuvenileNumber());		
				
			//	while(iter2.hasNext())
				//{
					//JuvenileCore juv = (JuvenileCore)iter2.next();
					if(juv!=null)
					{
													
						resp.setJuvName(juv.getFirstName(), juv.getMiddleName(), juv.getLastName());
						resp.setJuvSex(juv.getSexId());
						if(pet.getSearchType() != null && pet.getSearchType().equalsIgnoreCase("RS"))
						{
							resp.setJuvNum(juv.getJuvenileNum());
							resp.setAge(juv.getAgeInYears(juv.getDateOfBirth()));
							resp.setJuvRace(juv.getRaceId());
						}
						if(juv.getRaceId()!=null && !juv.getRaceId().equals("") )
						{
							if(juv.getHispanicInd()!=null && juv.getRaceId().equalsIgnoreCase("w") && juv.getHispanicInd().equalsIgnoreCase("y"))
							{
								resp.setJuvRace("L");
								
							}
							else
								resp.setJuvRace(juv.getRaceId());
						}
						dispatch.postEvent(resp);
					}
				
				
					
				//}
			}
		}				
	
	
	}
	

	/**
	 * @param event
	 * @roseuid 42A99B990131
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 42A99B990133
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param anObject
	 * @roseuid 42A99B99013A
	 */
	public void update(Object anObject)
	{

	}
}
