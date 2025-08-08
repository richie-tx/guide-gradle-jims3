package pd.supervision.administerprogramreferrals.transactions;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import naming.CSAdministerProgramReferralsConstants;

import messaging.administerprogramreferrals.GetReferralFormDataEvent;
import messaging.administerprogramreferrals.SaveReferralFormEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.CollectionUtil;
import pd.supervision.administerprogramreferrals.CSProgramReferralHelper;
import pd.supervision.administerprogramreferrals.CSReferralFormFieldData;
import pd.supervision.administerprogramreferrals.CSReferralFormFieldOption;

public class SaveReferralFormCommand implements ICommand
{

	public void execute(IEvent event) throws Exception 
	{
		SaveReferralFormEvent saveRefFormEvt = (SaveReferralFormEvent)event;
		
		boolean isRefFormDataExist = false;
		
		String referralFormId = saveRefFormEvt.getReferralFormId();
		
		GetReferralFormDataEvent refFormDataEvent = new GetReferralFormDataEvent();
		refFormDataEvent.setProgRefId(saveRefFormEvt.getProgramReferralId());
		refFormDataEvent.setReferalFormId(referralFormId);
		Iterator refFormDataIter = CSReferralFormFieldData.findAll(refFormDataEvent);
		if(refFormDataIter != null)
		{
			HashMap fieldIdDataMap = new HashMap();
			HashMap fldIdUsrEntOptNameMap = new HashMap();
			HashMap fieldOptionsMap = new HashMap();
			
			CSProgramReferralHelper.populateFieldMaps(saveRefFormEvt.getFieldDataList(), fieldIdDataMap, fldIdUsrEntOptNameMap);
			
			while(refFormDataIter.hasNext())
			{
				isRefFormDataExist = true;
				
				CSReferralFormFieldData fieldData = (CSReferralFormFieldData)refFormDataIter.next();
				if(fieldData.getResponseUIControlType().equalsIgnoreCase(CSAdministerProgramReferralsConstants.UI_CNTRL_TYPE_CHECKBOX))
				{
					String fieldId = fieldData.getRefFormFieldId();
					if(!fieldOptionsMap.containsKey(fieldId))
					{
						List fieldOptionList = CollectionUtil.iteratorToList(CSReferralFormFieldOption.findAll("referralFormFieldId", fieldId));
						fieldOptionsMap.put(fieldId, fieldOptionList);
					}
					
					List selectedOptionsList = (List)fieldIdDataMap.get(fieldData.getRefFormFieldId());
					if(selectedOptionsList.contains(fieldData.getRefFormFieldValue()))
					{
						fieldData.setIsOptionSelected(true);
						
						String fieldDataOptionId = fieldData.getRefFormFieldValue();
						
						List availOptionList = (List)fieldOptionsMap.get(fieldId);
						Iterator optIter = availOptionList.iterator();
						while(optIter.hasNext())
						{
							CSReferralFormFieldOption option = (CSReferralFormFieldOption)optIter.next();
							if(option.getOID().equalsIgnoreCase(fieldDataOptionId))
							{
								if(option.getIsOptionEditable())
								{
									fieldData.setUserEnteredOptionName((String)fldIdUsrEntOptNameMap.get(fieldData.getRefFormFieldId()));
								}
							}
						}
					}
					else
					{
						fieldData.setIsOptionSelected(false);
						fieldData.setUserEnteredOptionName(null);
					}
				}
				else
				{
					fieldData.setRefFormFieldValue((String)fieldIdDataMap.get(fieldData.getRefFormFieldId()));
					fieldData.setUserEnteredOptionName((String)fldIdUsrEntOptNameMap.get(fieldData.getRefFormFieldId()));
				}
			}
		}
		
		if(!isRefFormDataExist)
		{
			CSProgramReferralHelper.saveReferralForm(saveRefFormEvt);
		}
	}

}
