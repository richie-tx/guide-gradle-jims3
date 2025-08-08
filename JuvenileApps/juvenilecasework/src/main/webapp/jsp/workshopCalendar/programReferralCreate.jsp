<!DOCTYPE HTML>

<%-- ----------------MODIFICATIONS -------------------------- --%>
<%-- 05/2006  DAW	Create jsp --%>
<%-- 05/20/10 LDeen	ER#64892 fix begin date so that user must use calendar to enter date --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>

<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.UIConstants" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="naming.PDCalendarConstants" %>
<%@ page import="ui.common.UIUtil" %>



<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<msp:nocache />
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<title>juvenileCaseWork/caseTabCalendar/programReferralCreate.jsp</title>
<!-- Javascript for emulated navigation -->
<link href="/<msp:webapp/>css/casework.css" rel="stylesheet" type="text/css">

<html:base />

<%-- STRUTS VALIDATION --%>
<%-- <html:javascript formName="scheduleNewEventForm"/> --%>

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/caseCalendar.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/AnchorPosition.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>

<script type="text/javascript">
function validateScheduleNewEventForm(theForm){
	var message="";
	var check=false;
	var assignedHoursVal = theForm["programReferral.assignedHours"].value;
	var courtOrderVal = theForm["programReferral.courtOrdered"];
	if(assignedHoursVal == ""){
		alert("Assigned Hours is required");
		return false
	} 
	if(courtOrderVal[0].checked == false && courtOrderVal[1].checked == false ){
		alert("Court Ordered is required");
		return false;
	}  
	if(!validateComments(this.form)){
		return false;
	}
}

function validateComments(theForm){
	var cmts = document.getElementsByName("programReferral.comments")[0].value;
	var commentRegExp = /^[a-zA-Z0-9][^%_]*$/;
	var msg = "";
    if (cmts.length > 0) {
		if (!commentRegExp.test(cmts)) {
			msg += "Comments must be alphanumeric with no leading spaces and no leading symbols.  All symbols and spaces are allowed after the first alphanumeric character except % and _.";
		    alert(msg);
		    return false;
		}
    }
	return true;
}

</script>

</head>

<body topmargin='0' leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true);">
<html:form action="/submitProgramReferralfromWorkshop" target="content">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|346">

<!-- BEGIN HEADING TABLE -->
<table width='100%'>
  <td align="center" class="header" ><bean:message key="title.juvenileCasework" />&nbsp;-&nbsp;Workshop Calendar&nbsp;-&nbsp;<bean:message key="title.create" />&nbsp;<bean:message key="prompt.programReferral" /></td>
</table>
<table width="100%">
	<tr>
		<td align="center" class="errorAlert"><html:errors></html:errors></td>
	</tr>
</table>
<!-- END HEADING TABLE -->


<!-- BEGIN INSTRUCTION TABLE -->
<div class='spacer'></div>
<table width="98%">
  <tr>
    <td>
      <ul>
        <li>Enter information as required.</li>
        <li>Enter date through the calendar icon.</li>	    
        <li>Select the Submit button to view the Summary screen.</li>
      </ul>
    </td>
  </tr>
  <tr>
		<td class="required"><img src='/<msp:webapp/>images/required.gif' title='required' alt="required image" hspace='0' vspace='0'>&nbsp;<bean:message key="prompt.requiredFieldsInstruction" /></td>
	</tr>
</table>

<!-- END INSTRUCTION TABLE -->

<!-- BEGIN HEADER INFO TABLE -->
<%-- <tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
  <tiles:put name="headerType" value="casefileheader" />
</tiles:insert>  --%>
<!-- END HEADER INFO TABLE -->


<div class='spacer'></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
  <tr>
    <td valign='top'>
      <%--tabs start--%>
      <%-- <tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
        <tiles:put name="tabid" value="calendartab" />
        <tiles:put name="casefileid" value='<%=request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM)%>' />
      </tiles:insert> --%>
      <%--tabs end--%>

      <table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
      <tr>
      <td>
      <table align="center" width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
    				<%-- added Caseload Manager name and reformatted header per ER JIMS200027187 mjt 16feb2006  --%>
	  					<tr>
         							<td class='detailHead'><bean:message key="prompt.juvenile" />&nbsp;<bean:message key="prompt.details" /></td> 
         				</tr>
         				<tr>
      				    	<td valign='top' align='center'>
      				    		<table cellpadding='2' cellspacing='1' width='100%'>		
			  						<tr>			  					
					  					<td class="formDeLabel">Juvenile Number</td>
					  					<td class="formDe"><bean:write name="scheduleNewEventForm" property="juvenileNum"/></td>
					  					<td class="formDeLabel">Juvenile Name</td>
					  					<td class="formDe"><bean:write name="scheduleNewEventForm" property="juvenileFullName"/></td>
					  					<td class="formDeLabel">DOB</td>
					  					<td class="formDe"><bean:write name="scheduleNewEventForm" property="birthDate"/></td>					  					
					  					<td class="formDeLabel">Casefile ID</td>
					  					<td class="formDe"><bean:write name="scheduleNewEventForm" property="caseFileId"/></td>			  						
			  						</tr>	
	  							</table>
	  						</td>
	  					</tr>						
	  		</table>
      </td>
      </tr>
        <tr>
          <td valign='top' align='center'>                

            <div class='spacer'></div>
            <table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
              <tr>
                <td width='1%' class='detailHead'><a href="javascript:showHideMulti('attendance', 'achar', 1,'/<msp:webapp/>');"><img border='0' src="/<msp:webapp/>images/expand.gif" name="attendance"></a>&nbsp;Juvenile Past Attendance for Program</td>
              </tr>

              <tr id="achar0" class='hidden'>
        				<td>
                  <!-- BEGIN ATTENDEE TABLE -->
                  <table align="center" width="100%" cellpadding="2" cellspacing="1">  
                  	<tr>
          						<logic:empty name="scheduleNewEventForm" property="programReferral.existingReferrals">
          							<td valign='top' align="left">Juvenile has no past attendance for program</td>
          						</logic:empty>

          						<logic:notEmpty name="scheduleNewEventForm" property="programReferral.existingReferrals">
                  		<td valign='top'>
                  			<table width='100%' cellpadding="2" cellspacing="1">
                  				<tr bgcolor='#cccccc' class='subHead'>
                  					<td align="left"><bean:message key="prompt.programReferral#" /></td>
                  					<td align="left"><bean:message key="prompt.date" /></td>
                  					<td align="left"><bean:message key="prompt.service" /></td>
                  					<td align='center'><bean:message key="prompt.attended" /></td>
                  					<td align='center'><bean:message key="prompt.absent" /></td>
                  					<td align='center'><bean:message key="prompt.excused" /></td>
                  				</tr>

                  			<logic:iterate indexId='index' id="programRef" name="scheduleNewEventForm" property="programReferral.existingReferrals" >
          							    <tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">
                    					<td align="left"><bean:write name="programRef" property="programReferralId"/></td>
                    					<td align="left"><bean:write name="programRef" property="startDate" formatKey="datetime.format.mmddyyyyHHmm"/></td>
            									<td align="left"><bean:write name="programRef" property="serviceName"/></td>
            									<td align="center">			
            										<logic:equal name="programRef" property="attendanceStatusCd" value="<%=PDCalendarConstants.JUV_ATTEND_STATUS_ATTENDED%>">x</logic:equal>						
            									</td>	
            									<td align="center">			
            										<logic:equal name="programRef" property="attendanceStatusCd" value="<%=PDCalendarConstants.JUV_ATTEND_STATUS_ABSENT%>">x</logic:equal>						
            									</td>		
            									<td align="center">			
            										<logic:equal name="programRef" property="attendanceStatusCd" value="<%=PDCalendarConstants.JUV_ATTEND_STATUS_EXCUSED%>">x</logic:equal>						
            									</td>	
                    				</tr>
							</logic:iterate>     
                        </table>
                  		</td>
						</logic:notEmpty>
                  	</tr>
                  </table>
                </td>
              </tr>
            </table><!-- END ATTENDEE TABLE -->

            <!--program referral start-->
		   			<div class='spacer'></div>
		   			<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
		   				<tr>
		   					<td class='detailHead'><bean:message key="prompt.programReferral" />&nbsp;<bean:message key="prompt.details" /> - <bean:message key="prompt.program" />:&nbsp;<bean:write name="scheduleNewEventForm" property="programName"/></td>
		   				</tr>
		   			   <tr>
		  				   <td>
			  				   <table cellpadding='2' cellspacing='1' width='100%'>
		                            <tr>
		    						  <td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.beginDate" /></td>		    								
		    						  <td class="formDe"><bean:write name="scheduleNewEventForm" property="programReferral.beginDateStr"/></td>
		    						   <td class="formDeLabel" width='1%' nowrap='nowrap'><img src="/<msp:webapp/>images/required.gif" title="Required" alt="required icon" border='0' width='10' height='9'><bean:message key="prompt.assignedHours" /></td>
		    						   <td class="formDe"><html:text size="5" maxlength="3" name="scheduleNewEventForm" property="programReferral.assignedHours" /></td>
		    						</tr>
			
		  						 <tr>
		  							 <td class="formDeLabel" nowrap='nowrap'><img src="/<msp:webapp/>images/required.gif" title="Required" alt="required icon" border='0' width='10' height='9'><bean:message key="prompt.courtOrdered" /></td>
		  							 <td class="formDe">
		  							 	<html:radio name="scheduleNewEventForm" property="programReferral.courtOrdered" value="yes"/><bean:message key="prompt.yes" />
		  							    <html:radio name="scheduleNewEventForm" property="programReferral.courtOrdered" value="no"/><bean:message key="prompt.no" />
		  							 </td>							    
		  							 <td class="formDeLabel" nowrap='nowrap'><bean:message key="prompt.referralStatus" /></td>
		  							 <td class="formDe"><bean:write name="scheduleNewEventForm" property="programReferral.referralState.description"/></td>
		  						 </tr>
			
		  						 <tr>
		  							 <td class="formDeLabel" colspan="4" nowrap='nowrap'><bean:message key="prompt.comments" />
		                    &nbsp;
		                    <tiles:insert page="../caseworkCommon/spellCheckTile.jsp" flush="false">
		                      <tiles:put name="tTextField" value="programReferral.comments" />
		          						<tiles:put name="tSpellCount" value="spellBtn1" />
		                    </tiles:insert>
		                    (Max. characters allowed: 1000)
		  							 </td>
		  						 </tr>
		  						 <tr>
		  							 <td class="formDe" colspan="4"><html:textarea name="scheduleNewEventForm" property="programReferral.comments" style="width:100%" rows="3" styleId="programReferalComment"/></td>
		  						 </tr>
		             </table>
		           </td>
		         </tr>
		       </table>
  				<!-- program referral end -->

            <!-- BEGIN BUTTON TABLE -->
						<div class='spacer'></div>
            <table width="100%">
        			  <tr>
        				<td align="center">
        				  <html:submit property="submitAction" styleId="programReferalSubmit"><bean:message key="button.submit"/></html:submit>
        				  <html:reset><bean:message key="button.reset"></bean:message></html:reset>
        				  <html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
        				</td>
        			  </tr>
            </table><!-- END BUTTON TABLE -->

          </td>
        </tr>
      </table>
    </td>
  </tr>
</table>
<!-- END DETAIL TABLE -->

</html:form>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:html>
