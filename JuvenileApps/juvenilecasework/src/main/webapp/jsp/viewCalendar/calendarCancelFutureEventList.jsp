<!DOCTYPE HTML>

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDCodeTableConstants" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="naming.PDCalendarConstants" %>
<%@ page import="naming.UIConstants" %>
<%@ page import="ui.common.UIUtil" %>
<%@ page import="naming.Features" %>

<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG-->
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<msp:nocache />
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/casework_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>


<script type="text/javascript">
function validate(oForm)
{
	var chks = oForm.selectedCancelEvents;

	if( chks )
	{ //checkbox(s) exists
 		var checked = false;
 
   	if( chks.length )
 		{ //multiple checkboxes
   		var len = chks.length;
   		for( var i = 0; i < len; i++)
 			{
 				if( chks[i].checked )
 				{
  					checked = true;
  					break;
 				}
 			}
 		}
 		else
 		{ //single checkbox only
 			checked = chks.checked;
 		}
 		
 		if( !checked )
 		{
 			alert("At least one event needs to be selected.");
 			return false;
 		}
  }

  return true;
}

function checkAll(el, checkboxName)
{
	var theForm = el.form;
	var checkAllName = el.name;
	var objCheckBoxes = document.getElementsByName(checkboxName);
	var countCheckBoxes = objCheckBoxes.length;

	if(el.checked)
	{
		// set the check value for all check boxes
		for(var i = 0; i < countCheckBoxes; i++)
		{
			if (!objCheckBoxes[i].disabled)
			{
				objCheckBoxes[i].checked = true;
			}
		}
	}
	else 
	{
		for(var i = 0; i < countCheckBoxes; i++)
		{
			if (!objCheckBoxes[i].disabled)
			{
				objCheckBoxes[i].checked = false;
			}
		}
	}
}
</script>

<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- calendarCancelFutureEventList.jsp</title>

</head>

<body topmargin="0" leftmargin="0">
<html:form action="/handleFutureCalendarEventsCancel"   target="content">

<!-- BEGIN HEADING TABLE -->
<table width='100%'>
  <tr>
    <td align="center" class="header"><bean:message key="title.juvenileCasework" /> - Cancel
      <logic:equal name="calendarEventListForm" property="searchEvent.searchType" value="<%=PDCalendarConstants.FUTURE_EVENTS_JUVENILE%>">
        <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|200">
        Juvenile
      </logic:equal>		
      <logic:equal name="calendarEventListForm" property="searchEvent.searchType" value="<%=PDCalendarConstants.FUTURE_EVENTS_SVC_PROVIDER%>">
		<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|180">
        Service Provider
      </logic:equal>		
  		Future Event List
    </td>
  </tr>
</table>
<table width="100%">
	<tr>
		<td align="center" class="errorAlert"><html:errors></html:errors></td>
	</tr>
</table>
<!-- END HEADING TABLE -->

<!-- BEGIN INSTRUCTION TABLE -->
<div class='spacer'></div>
<table width="98%" align="center">
  <tr>
    <td>
      <ul id='editInstructions'>
        <li>Select a checkbox for each Event to cancel.</li>
        <li>Select <b>Submit</b> button to view the summary screen.</li>
        <li>Select <b>Back</b> button to return to previous page.</li>
      </ul>
    </td>
  </tr>
</table>
<!-- END INSTRUCTION TABLE -->

<div class='spacer'></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" align="center">
  <tr>
    <td valign='top' align='center'>
			
      <!-- BEGIN Cancel Juv Events TABLE  ... the user will select Juv events displayed in this table -->
      <logic:equal name="calendarEventListForm" property="searchEvent.searchType" value="<%=PDCalendarConstants.FUTURE_EVENTS_JUVENILE%>">
    
     	  <logic:notEmpty name="calendarEventListForm" property="individualDayEvents">
					<bean:size id="size" name="calendarEventListForm" property="individualDayEvents"/>
					<bean:write name="size" /> calendar event(s) found.
     	  </logic:notEmpty>

        <div class='spacer'></div>
        <table align="center" width='98%' cellpadding="2" cellspacing="0" class="borderTableBlue" align="center">
          <tr>
            <td class="detailHead">Future Events for: <bean:write name="juvenileProfileHeader" property="juvenileName"/></td>
          </tr>
          <tr>
            <td>
               <logic:empty name="calendarEventListForm" property="individualDayEvents">
                 <tr>
                   <td colspan='5' class="subHead">No events(s) found.</td>
                 </tr>
          	  	</logic:empty>

          	  	<logic:notEmpty name="calendarEventListForm" property="individualDayEvents">
                	<table width='100%' cellpadding="2" cellspacing="1">
                 		<tr bgcolor='#cccccc' class="subhead">
                   		<jims2:isAllowed requiredFeatures='<%=Features.JCW_CFC_JUV_FUTURE_EVENTS%>'>  
                   			<td nowrap='nowrap' width='1%'>
                   				<input type='checkbox' onclick='javascript:checkAll(this, "selectedCancelEvents");'>
                   			</td> 
                   		</jims2:isAllowed>
                   		<td nowrap='nowrap' align="left">
                   			<bean:message key="prompt.dateTime" />
                    		<jims2:sortResults beanName="calendarEventListForm" results="individualDayEvents" primaryPropSort="eventDate" primarySortType="DATE" defaultSort="true" defaultSortOrder="ASC" sortId="1" />
                   		</td>
							    		<td align="left">
							    			<bean:message key="prompt.eventType" />
											<jims2:sortResults beanName="calendarEventListForm" results="individualDayEvents" primaryPropSort="eventType" primarySortType="STRING" defaultSort="false" defaultSortOrder="ASC" sortId="2" />
							    		</td>
                   		<td align="left">
                   			<bean:message key="prompt.programName" />
                    		<jims2:sortResults beanName="calendarEventListForm" results="individualDayEvents" primaryPropSort="programName" primarySortType="STRING" defaultSort="false" defaultSortOrder="ASC" sortId="3" />
                   		</td>
                   		<td align="left">
                   			<bean:message key="prompt.serviceName" />
                    		<jims2:sortResults beanName="calendarEventListForm" results="individualDayEvents" primaryPropSort="serviceName" primarySortType="STRING" defaultSort="false" defaultSortOrder="ASC" sortId="4" />
                   		</td>
                 		</tr>

			     					<logic:iterate indexId="index" id="namesIndex" name="calendarEventListForm" property="individualDayEvents"> 
			     						<tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>" height="100%">
			     							<jims2:isAllowed requiredFeatures='<%=Features.JCW_CFC_JUV_FUTURE_EVENTS%>'> 
                    			<td align="left">
                    				<html:multibox property="selectedCancelEvents">
	                    				<bean:write name="namesIndex" property="eventId"/>
                    				</html:multibox>
                    			</td>
                    		</jims2:isAllowed>
		       							<td align="left"><bean:write name="namesIndex" property="eventDate" formatKey="datetime.format.mmddyyyyhhmmAMPM"/></td>
		       							<td align="left"><bean:write name="namesIndex" property="eventType"/></td>
               					<td align="left"><bean:write name="namesIndex" property="programName"/></td> 
                   			<td align="left"><bean:write name="namesIndex" property="serviceName"/></td>
                    	</tr>
				     				</logic:iterate>
 	              	</table>
	               </logic:notEmpty>	
	             </td>
	           </tr>
	         </table>
   
   			</logic:equal>	

	      <!-- BEGIN Cancel SP Events TABLE  ... the user will select SP events displayed in this table -->
	      <logic:equal name="calendarEventListForm" property="searchEvent.searchType" value="<%=PDCalendarConstants.FUTURE_EVENTS_SVC_PROVIDER%>">
    
	     	  <logic:notEmpty name="calendarEventListForm" property="individualDayEvents">
						<bean:size id="size" name="calendarEventListForm" property="individualDayEvents"/>
						<bean:write name="size" /> calendar event(s) found.
	     	  </logic:notEmpty>

          <div class='spacer'></div>
          <table align="center" width='98%' cellpadding="2" cellspacing="0" class="borderTableBlue" align="center">
            <tr>
              <td class="detailHead">Future Events for Service Provider: <bean:write name="calendarEventListForm" property="searchEvent.serviceProviderName"/></td> 
            </tr>
            <tr>
              <td>
		          	<logic:empty name="calendarEventListForm" property="individualDayEvents">
                	<tr>
                   	<td colspan='5' class="subHead">No events(s) found.</td>
                 	</tr> 
		          	</logic:empty>

	            	<logic:notEmpty name="calendarEventListForm" property="individualDayEvents">
		          		<table width='100%' cellpadding="2" cellspacing="1" align="center">
	          	    	<tr bgcolor='#cccccc' class='subHead'>
                    	<jims2:isAllowed requiredFeatures='<%=Features.JCW_CFC_SP_FUTURE_EVENTS%>'>  
                    		<td nowrap="nowrap" width='1%'><input type='checkbox' onclick='javascript:checkAll(this, "selectedCancelEvents");'></td>
                    	</jims2:isAllowed>

                    	<td nowrap="nowrap" align="left"><bean:message key="prompt.dateTime" />
                    		<jims2:sortResults beanName="calendarEventListForm" results="individualDayEvents" primaryPropSort="eventDate" primarySortType="DATE" defaultSort="true" defaultSortOrder="ASC" sortId="1" />
                    	</td>
                    	<td align="left">
                    		<bean:message key="prompt.programName" />
                    		<jims2:sortResults beanName="calendarEventListForm" results="individualDayEvents" primaryPropSort="programName" primarySortType="STRING" defaultSort="false" defaultSortOrder="ASC" sortId="4" />
                    	</td>
                    	<td align="left">
                    		<bean:message key="prompt.serviceName" />
                    		<jims2:sortResults beanName="calendarEventListForm" results="individualDayEvents" primaryPropSort="serviceName" primarySortType="STRING" defaultSort="false" defaultSortOrder="ASC" sortId="5" />
                    	</td>
								    	<td align="left">
								    		<bean:message key="prompt.eventType" />
									    	<jims2:sortResults beanName="calendarEventListForm" results="individualDayEvents" primaryPropSort="eventType" primarySortType="STRING" defaultSort="false" defaultSortOrder="ASC" sortId="3" />
								    	</td>
								    	<td align="left">
								    		<bean:message key="prompt.locationUnit" />
									    	<jims2:sortResults beanName="calendarEventListForm" results="individualDayEvents" primaryPropSort="serviceLocationName" primarySortType="STRING" defaultSort="false" defaultSortOrder="ASC" sortId="2" />
								    	</td>
	               		</tr>
    
				    			  <!-- this will be the logic:iterate section -->
		        			  <logic:iterate indexId="index" id="namesIndex" name="calendarEventListForm" property="individualDayEvents"> 
		       				  	<tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>" height="100%">
                       	<jims2:isAllowed requiredFeatures='<%=Features.JCW_CFC_SP_FUTURE_EVENTS%>'> 
                     			<td align="left">
                      			<html:multibox property="selectedCancelEvents">
                      				<bean:write name="namesIndex" property="eventId"/>
                      			</html:multibox>
                      		</td>
                      	</jims2:isAllowed>
			         					<td align="left">
			         						<bean:write name="namesIndex" property="eventDate" formatKey="datetime.format.mmddyyyyhhmmAMPM"/>
			         					</td>
                  			<td align="left">
                  				<bean:write name="namesIndex" property="programName"/>
                  			</td> 
                       	<td align="left"><bean:write name="namesIndex" property="serviceName"/></td>
                       	<td align="left"><bean:write name="namesIndex" property="eventType"/></td>
			         					<td>
			         						<bean:write name="namesIndex" property="serviceLocationName"/>
			         					</td>
                       </tr>
			        			</logic:iterate>
		              </table>
                </logic:notEmpty>	
              </td>
            </tr>
          </table>
          
      </logic:equal>			
		
				<!-- BEGIN BUTTON TABLE -->
        <div class='spacer'></div>
        <table width="100%" align="center">  
          <tr>
            <td align="center">
             	<html:submit property="submitAction"><bean:message key="button.back"></bean:message></html:submit>
              	<logic:notEmpty name="calendarEventListForm" property="individualDayEvents">
              			
              		<logic:equal name="calendarEventListForm" property="searchEvent.searchType" value="<%=PDCalendarConstants.FUTURE_EVENTS_SVC_PROVIDER%>">
              			<jims2:isAllowed requiredFeatures='<%=Features.JCW_CFC_SP_FUTURE_EVENTS%>'> 	
              				<html:submit property="submitAction" onclick="return validate(this.form)">
              					<bean:message key="button.submit"></bean:message>
              				</html:submit>
              			</jims2:isAllowed>
              		</logic:equal> 
              			
              		<logic:equal name="calendarEventListForm" property="searchEvent.searchType" value="<%=PDCalendarConstants.FUTURE_EVENTS_JUVENILE%>">
              			<jims2:isAllowed requiredFeatures='<%=Features.JCW_CFC_JUV_FUTURE_EVENTS%>'> 	
              				<html:submit property="submitAction" onclick="return validate(this.form)">
              					<bean:message key="button.submit"></bean:message>
              				</html:submit>
              			</jims2:isAllowed>
              		</logic:equal>
              			              			
              	</logic:notEmpty>
            	<html:submit property="submitAction"><bean:message key="button.cancel"></bean:message></html:submit>
            </td>
          </tr>
        </table>
        <!-- END BUTTON TABLE -->

    </td>
  </tr>
</table>

</html:form>

<div><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:html>
