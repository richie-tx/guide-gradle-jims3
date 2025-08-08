// Source file:
// C:\\views\\CommonSupervision\\app\\src\\pd\\security\\authentication\\transactions\\CreateJIMS2AccountCommand.java

package pd.security.authentication.transactions;

import java.util.Date;

import messaging.authentication.CreateJIMS2AccountEvent;
import messaging.security.authentication.reply.JIMS2AccountResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.security.IUserInfo;
import naming.PDSecurityConstants;
import naming.ResponseLocatorConstants;
import pd.common.ResponseContextFactory;
import pd.common.ResponseCreator;
import pd.exception.ReflectionException;
import pd.security.JIMS2Account;
import pd.security.JIMS2AccountType;
import pd.security.PDSecurityHelper;

public class CreateJIMS2AccountCommand implements ICommand {

    /**
     * @roseuid 4399BF210352
     */
    public CreateJIMS2AccountCommand() {

    }

    /**
     * @param event
     * @roseuid 439711A802BF
     */
    public void execute(IEvent event) {
        CreateJIMS2AccountEvent cjaEvent = (CreateJIMS2AccountEvent) event;
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
        JIMS2Account j2Logon = null;
        JIMS2AccountResponseEvent resp = new JIMS2AccountResponseEvent();
        //See if there is an existing JIMS2Account with the same JIMS2LogonId
        {
            j2Logon = new JIMS2Account();
            //86322
         //   j2Logon.setPasswordQuestionId(cjaEvent.getPasswordQuestionId());
            j2Logon.setLastName(cjaEvent.getLastName());
            j2Logon.setFirstName(cjaEvent.getFirstName());
            j2Logon.setMiddleName(cjaEvent.getMiddleName());
            j2Logon.setJIMS2LogonId(cjaEvent.getJIMS2LogonId());
         //   j2Logon.setPassword(cjaEvent.getJIMS2Password());
            j2Logon.setCreateJIMS2UserID(cjaEvent.getJIMS2LogonId());
            //j2Logon.setCreateUserID(cjaEvent.getLogonId());
           // j2Logon.setAnswer(cjaEvent.getAnswer());
            j2Logon.setDepartmentId(cjaEvent.getDepartmentId());
            String status = cjaEvent.getStatus();
            if(status == null || status.equals("")){
            	j2Logon.setStatus(PDSecurityConstants.ACTIVE);
            }else{
            	j2Logon.setStatus(cjaEvent.getStatus());
            }
            IUserInfo user = (IUserInfo) PDSecurityHelper.getUser();
            StringBuffer userName = new StringBuffer();
            if (user != null) {
                userName.append(user.getLastName());
                userName.append(", ");
                userName.append(user.getFirstName());
                userName.append(" ");
                userName.append(user.getMiddleName());
            }else{
                userName.append(cjaEvent.getLastName());
                userName.append(", ");
                userName.append(cjaEvent.getFirstName());
                userName.append(" ");
                userName.append(cjaEvent.getMiddleName());
                
            }
            j2Logon.setActivatedBy(userName.toString());
            j2Logon.setActivatedDate(new Date());
            IHome home = new Home();
            home.bind(j2Logon);
            
            JIMS2AccountType j2AccountType = new JIMS2AccountType();
            j2AccountType.setUserAccountTypeId(cjaEvent.getJIMS2AccountTypeId());
            j2AccountType.setUserAccountOID(cjaEvent.getJIMS2AccountTypeOID());
            j2AccountType.setJIMS2AccountId(j2Logon.getOID().toString());
            j2AccountType.setLogonId(cjaEvent.getLogonId());
            if(cjaEvent.getUserID()!=null && cjaEvent.getLogonId()==null){
        	 j2AccountType.setLogonId(cjaEvent.getUserID());
            }
            j2AccountType.setCreateJIMS2UserID(cjaEvent.getJIMS2LogonId());
          //  j2AccountType.setCreateUserID(cjaEvent.getLogonId());

            j2Logon.insertJIMS2AccountType(j2AccountType);
            resp.setAccountCreated(true);
            resp.setJimsLogonId(cjaEvent.getLogonId()); // bug 85297
            resp.setJIMS2LogonId(cjaEvent.getJIMS2LogonId());
           dispatch.postEvent(resp);
        }

        if (cjaEvent.isCreate()) {
            ResponseContextFactory respFac = new ResponseContextFactory();
            ResponseCreator aCreator = null;
            try {
                aCreator = (ResponseCreator) respFac
                        .lookup(ResponseLocatorConstants.JIMS2ACCOUNT_RESPONSE_LOCATOR);
            } catch (ReflectionException e) {
                e.printStackTrace();
            }
            resp = (JIMS2AccountResponseEvent) aCreator.create(j2Logon);
            dispatch.postEvent(resp);
        }
    }

    /**
     * @param event
     * @roseuid 439711A802C1
     */
    public void onRegister(IEvent event) {

    }

    /**
     * @param event
     * @roseuid 439711A802C3
     */
    public void onUnregister(IEvent event) {

    }

    /**
     * @param updateObject
     * @roseuid 4399BF210371
     */
    public void update(Object updateObject) {

    }
}