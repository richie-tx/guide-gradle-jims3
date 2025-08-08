package pd.supervision.administerprogramreferrals.transactions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import naming.CSAdministerProgramReferralsConstants;

import messaging.administerprogramreferrals.GetReferralFormDataEvent;
import messaging.administerprogramreferrals.GetReferralFormFieldsEvent;
import messaging.administerprogramreferrals.reply.ReferralFormFieldResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.CollectionUtil;
import mojo.km.utilities.MessageUtil;
import pd.supervision.administerprogramreferrals.CSProgramReferralHelper;
import pd.supervision.administerprogramreferrals.CSReferralFormField;
import pd.supervision.administerprogramreferrals.CSReferralFormFieldData;


public class GetReferralFormFieldsCommand implements ICommand
{

	public void execute(IEvent event) throws Exception
	{
		GetReferralFormFieldsEvent reqEvent = (GetReferralFormFieldsEvent)event;
		
		boolean isRefFormDataExist = false;
		
		String referralFormId = reqEvent.getReferralFormId();
		
		GetReferralFormDataEvent refFormDataEvent = new GetReferralFormDataEvent();
		refFormDataEvent.setProgRefId(reqEvent.getProgramReferralId());
		refFormDataEvent.setReferalFormId(referralFormId);
		
		List refFormDataList = CollectionUtil.iteratorToList(CSReferralFormFieldData.findAll(refFormDataEvent));
		
		if(refFormDataList!=null && refFormDataList.size()>0)
		{		
			isRefFormDataExist = true;
			
			Iterator refFormDataIter = refFormDataList.iterator();
//			multiple data rows for a single field are present only in case of CHECKBOX
			HashMap fieldIdAndDataMap = new HashMap();
			if(refFormDataIter != null)
			{
				while(refFormDataIter.hasNext())
				{
					CSReferralFormFieldData fieldData = (CSReferralFormFieldData)refFormDataIter.next();
					if(fieldIdAndDataMap.containsKey(fieldData.getRefFormFieldId()))
					{
						ArrayList fieldDataList = (ArrayList)fieldIdAndDataMap.get(fieldData.getRefFormFieldId());
						fieldDataList.add(fieldData);
					}
					else
					{
						ArrayList fieldDataList = new ArrayList();
						fieldDataList.add(fieldData);
						fieldIdAndDataMap.put(fieldData.getRefFormFieldId(), fieldDataList);
					}
				}
			}
			
			Set fieldIdsSet = fieldIdAndDataMap.keySet();
			Iterator refFormfieldSetIter = fieldIdsSet.iterator();
			if(refFormfieldSetIter != null)
			{
				while(refFormfieldSetIter.hasNext())
				{
					String fieldId = (String)refFormfieldSetIter.next();
					List eachfieldDataList = (List)fieldIdAndDataMap.get(fieldId);
					
					CSReferralFormFieldData referralFormFieldData = (CSReferralFormFieldData)eachfieldDataList.get(0);
					
					if(referralFormFieldData.getResponseUIControlType().equalsIgnoreCase(CSAdministerProgramReferralsConstants.UI_CNTRL_TYPE_CHECKBOX))
					{
						ReferralFormFieldResponseEvent responseEvent = CSProgramReferralHelper.getRefFormFieldRespEvent(eachfieldDataList, reqEvent.getProgramReferralId());
						MessageUtil.postReply(responseEvent);
					}
					else
					{
						ReferralFormFieldResponseEvent responseEvent = CSProgramReferralHelper.getRefFormFieldRespEvent(referralFormFieldData, reqEvent.getProgramReferralId());
						MessageUtil.postReply(responseEvent);
					}
					
				}
			}
		}
		if(!isRefFormDataExist)
		{
			Iterator fieldIter = CSReferralFormField.findAll("referralFormId", referralFormId);
			while(fieldIter.hasNext())
			{
				CSReferralFormField refFormField = (CSReferralFormField)fieldIter.next();
				ReferralFormFieldResponseEvent responseEvent = CSProgramReferralHelper.getRefFormFieldRespEvent(refFormField, reqEvent.getProgramReferralId());
				MessageUtil.postReply(responseEvent);
			}
		}
	}
}
