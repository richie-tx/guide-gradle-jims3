<!DOCTYPE HTML>

<%--MODIFICATIONS --%>
<%-- 09/19/2006		AWidjaja Create JSP--%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<%@ page import="naming.PDJuvenileCaseConstants" %>




<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge, chrome=1" />

<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- goalUpdate.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<%-- Javascript for emulated navigation --%>
<html:javascript formName="caseplanValidateForm"/>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script language="JavaScript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casetabCaseplan/updatePlacementInfo.js"></script>

<script type='text/javascript'>

function validatePage( tForm )
{
	var rtn = validateFields(tForm) ;

	return( rtn ) ;
}

</script>

</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin='0' leftmargin="0">
<html:form action="/displayUpdatePlacementInfoSummary" target="content"> 

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
  <tr>
    <td align="center" class="header">Juvenile Casework - Process Caseplan - 
		<logic:equal name="caseplanForm" property="action" value="create">
			Create
			<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|68"> 
		</logic:equal>

		<logic:equal name="caseplanForm" property="action" value="update">
			Update
			<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|72">
		</logic:equal>
		Placement Plan</td>
  </tr>
</table>
<%-- END HEADING TABLE --%>

<%-- BEGIN INSTRUCTION TABLE --%>
<div class='spacer'></div>
<table width="98%" border="0">
	<tr>
		<td class="bodyText">
			<ul id='editInstruct'>
				<li>Enter information, then select <b>Next</b> button to view Summary.</li>
			</ul>
    </td>
  </tr>
  <tr id='reqFieldsInstruct'> 
    <td class="required"><bean:message key="prompt.diamond" />&nbsp;Required Fields</td> 
  </tr> 
</table>
<%-- END INSTRUCTION TABLE --%>

<%-- BEGIN HEADER INFO TABLE --%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="casefileheader"/>
</tiles:insert>
<%-- END HEADER INFO TABLE --%>

<%-- BEGIN DETAIL TABLE --%>
<div class='spacer'></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0">
	<tr>
    <td valign='top'>

  		<tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
  			<tiles:put name="tabid" value="goalstab"/>
  			<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
  		</tiles:insert>				

			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td valign='top' align='center'>

					<div class='spacer'></div>
					<table width='98%' border="0" cellpadding="0" cellspacing="0" >
						<tr>
							<td valign='top'>
  							<tiles:insert page="../caseworkCommon/casePlanTabs.jsp" flush="true">
  								<tiles:put name="tabid" value="Caseplan"/>
  								<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
  							</tiles:insert>				
						  </td>
						</tr>
					  <tr>
					 		<td bgcolor='#33cc66'><img src="/<msp:webapp/>images/spacer.gif" width='5'></td>
					 	</tr>
					</table>
					
					<table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
  					<tr>
  		        <td valign='top' align='center'>
    						<%-- BEGIN Today's Interview TABLE --%>
    						<div class='spacer'></div>
    						<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
    							<tr>
    								<td class='detailHead' colspan='2' nowrap='nowrap'><bean:message key="prompt.placement"/> <bean:message key="prompt.info"/></td>
    							</tr>
    							<tr>
    								<td>
    									<%-- BEGIN PLACEMENT DATA TABLE --%>				
    									<table align='center' width='100%' cellpadding="2" cellspacing="1">
    										<tr>
    											<td class='formDeLabel' nowrap='nowrap'>
  													<bean:message key="prompt.diamond" />&nbsp;<bean:message key="prompt.entry"/> <bean:message key="prompt.date"/>
													</td>
    											<td class='formDe' colspan='3'>
    												<html:text styleId="entryDate" size="10" name="caseplanForm" property="currentPlacementInfo.entryDate"  maxlength="10"/>
						                  <script type="text/javascript" ID="js0">
						                    var cal1 = new CalendarPopup();
						                    cal1.showYearNavigation();
						                  </script>
    												<a href="#" onClick="cal1.select(document.forms[0].entryDate,'anchor1','MM/dd/yyyy'); return false;" NAME="anchor1" ID="anchor1" border='0'><img border='0' src="/<msp:webapp/>images/calendar2.gif" title="mm/dd/yyyy" align="middle"/></a>
    											</td>  								
    										</tr>								
    										<tr>
  			  								<td class="formDeLabel" colspan='3'>Is the closest facility available which best meets the child's specific needs?</td>
  			  								<td class="formDe" >Yes<html:radio name="caseplanForm" property="currentPlacementInfo.closestFacilityAvailable" value="YES"/> No<html:radio name="caseplanForm" property="currentPlacementInfo.closestFacilityAvailable" value="NO"/></td>
  			  							</tr>
  			  							<tr>
  			  								<td class="formDeLabel" colspan='3'>Is the least restrictive environment available which best meets the child's specific needs?</td>
  			  								<td class="formDe" >Yes<html:radio name="caseplanForm" property="currentPlacementInfo.leastRestrictiveEnv" value="YES"/> No<html:radio name="caseplanForm" property="currentPlacementInfo.leastRestrictiveEnv" value="NO"/></td>
  			  							</tr>				
  			  							<tr>
  			  								<td class="formDeLabel" colspan='3'>Was proximity to the child's former school district taken into consideration when selecting this facility?</td>
  			  								<td class="formDe" colspan='3'>Yes<html:radio name="caseplanForm" property="currentPlacementInfo.proximityConsidered" value="YES"/> No<html:radio name="caseplanForm" property="currentPlacementInfo.proximityConsidered" value="NO"/></td>
  			  							</tr>
			  			
  			  							<tr><td class='spacer4px'></td></tr>
  			  			
  			  							<tr>
  			  								<td class="formDeLabel" colspan='4'>
													  <bean:message key="prompt.diamond" />&nbsp;Explain why this child requires placement. Discuss the child's behavior and family situation
        									  &nbsp;
                  					<tiles:insert page="../caseworkCommon/spellCheckTile.jsp" flush="false">
                  						<tiles:put name="tTextField" value="currentPlacementInfo.reasonChildRequiresPlacement" />
                  						<tiles:put name="tSpellCount" value="spellBtn1" />
                						</tiles:insert>
                						(Max. characters allowed: 1000)
													</td>
  			  							</tr>
  			  							<tr>
  			  								<td class="formDe" valign='top' colspan='4'><html:textarea name="caseplanForm" property="currentPlacementInfo.reasonChildRequiresPlacement" style="width:100%" rows="3" onmouseout="textCounter(this, 1000);" onkeydown="textCounter(this,1000)"/></td>
  			  							</tr>
  			  			
  			  							<tr>
  			  								<td class="formDeLabel" colspan='4'>
														<bean:message key="prompt.diamond" />&nbsp;Explain what specific services are being provided to safely meet the the child's needs
        									  &nbsp;
                  					<tiles:insert page="../caseworkCommon/spellCheckTile.jsp" flush="false">
                  						<tiles:put name="tTextField" value="currentPlacementInfo.specificServicesProvided" />
                  						<tiles:put name="tSpellCount" value="spellBtn2" />
                						</tiles:insert>
                						(Max. characters allowed: 1000)
													</td>
  			  							</tr>
  			  							<tr>
  			  								<td class="formDe" valign='top' colspan='4'><html:textarea name="caseplanForm" property="currentPlacementInfo.specificServicesProvided" style="width:100%" rows="3" onmouseout="textCounter(this, 1000);" onkeydown="textCounter(this,1000)"/></td>
  			  							</tr>
  			  			
  			  							<tr>
  			  								<td class="formDeLabel" colspan='4'>
														<bean:message key="prompt.diamond" />&nbsp;If the child is placed outside of Texas, explain why this is in the best interest of the child
        									  &nbsp;
                  					<tiles:insert page="../caseworkCommon/spellCheckTile.jsp" flush="false">
                  						<tiles:put name="tTextField" value="currentPlacementInfo.reasonChildIsPlacedOutsideOfTexas" />
                  						<tiles:put name="tSpellCount" value="spellBtn3" />
                						</tiles:insert>
                						(Max. characters allowed: 1000)
													</td>
  			  							</tr>
  			  							<tr>
  			  								<td class="formDe" valign='top' colspan='4'><html:textarea name='caseplanForm' property="currentPlacementInfo.reasonChildIsPlacedOutsideOfTexas" style="width:100%" rows="3" onmouseout="textCounter(this, 1000);" onkeydown="textCounter(this,1000)"/></td>
  			  							</tr>
  			  			
  			  							<tr><td class='spacer4px'></td></tr>
  			  			
  			  							<tr>
  			  								<td class="formDeLabel" width='1%' nowrap='nowrap'><bean:message key="prompt.facility"/></td>
  			  								<td class="formDe" colspan='3'><bean:write name="caseplanForm" property="currentPlacementInfo.facilityStr"/>
  			  								</td>
  			  							</tr>
  			  							<tr>
  			  								<td class="formDeLabel" nowrap='nowrap'><bean:message key="prompt.facilityReleaseReason"/></td>
  			  								<td class="formDe" colspan='3'>
			  										<html:select name="caseplanForm" property="currentPlacementInfo.facilityReleaseReasonId">
  														<html:option value=""><bean:message key="select.generic" /></html:option>
  														<html:optionsCollection name="caseplanForm" property="facilityReleaseReasonList" value="code" label="description" />
  													</html:select>
  			  								</td>
  			  							</tr>
  			  							<tr>
  			  								<td class="formDeLabel" width='1%' nowrap='nowrap'>
  													<bean:message key="prompt.diamond" />&nbsp;<bean:message key="prompt.expectedReleaseDate"/>
													</td>
    											<td class='formDe' colspan='3'>
  			                    <html:text styleId="releaseDate" size="10" name="caseplanForm" property="currentPlacementInfo.expectedReleaseDate"  maxlength="10"/>
  			                    <script type="text/javascript" ID="js1">
  			                      var cal1 = new CalendarPopup();
  			                      cal1.showYearNavigation();
  			                    </script>
  			                    <a href="#" onClick="cal1.select(document.forms[0].releaseDate,'anchor2','MM/dd/yyyy'); return false;" NAME="anchor2" ID="anchor2" border='0'><img border='0' src="/<msp:webapp/>images/calendar2.gif" title="mm/dd/yyyy" align="middle"/></a>
    											</td>
    			  						</tr>
    			  						<tr>		
  			  								<td class="formDeLabel" nowrap='nowrap'>
													  <bean:message key="prompt.diamond" />&nbsp;<bean:message key="prompt.levelOfCare"/>
													</td>
  			  								<td class="formDe" colspan='3'>
    												<html:select name="caseplanForm" property="currentPlacementInfo.levelOfCareId">
  														<html:option value=""><bean:message key="select.generic" /></html:option>
															<html:optionsCollection name="caseplanForm" property="levelOfCareList" value="code" label="description" />
  													</html:select>
  							  				</td>
  							  			</tr>
  							  			<tr>		
    											<td class="formDeLabel" width='1%' nowrap='nowrap'>
  													<bean:message key="prompt.diamond" />&nbsp;<bean:message key="prompt.permanencyPlan"/>
													</td>
    											<td class="formDe" colspan="3">
    												<html:select name="caseplanForm" property="currentPlacementInfo.permanencyPlanId">
  														<html:option value=""><bean:message key="select.generic" /></html:option>
 															<html:optionsCollection name="caseplanForm" property="permanencyListPlan" value="code" label="description" />
  													</html:select>
    											</td>
    										</tr>
    										<tr>
    											<td class="formDeLabel" colspan="4"><bean:message key="prompt.special"/> <bean:message key="prompt.notes"/>
  												  &nbsp;
                  					<tiles:insert page="../caseworkCommon/spellCheckTile.jsp" flush="false">
                  						<tiles:put name="tTextField" value="currentPlacementInfo.specialNotes"/>
                  						<tiles:put name="tSpellCount" value="spellBtn4" />
                						</tiles:insert> 
                						(Max. characters allowed: 255)
													</td>
    										</tr>
    										<tr>
    											<td class="formDe" colspan="4">
    												<html:textarea name="caseplanForm" property="currentPlacementInfo.specialNotes" style="width:100%" rows="3" onmouseout="textCounter(this, 255);" onkeydown="textCounter(this,255)"/>
    											</td>
    										</tr>						
    									</table><%-- END PLACEMENT DATA TABLE --%>

											</td>
										</tr>	
									</table>

 									<%-- BEGIN BUTTONS TABLE --%>
 									<div class='spacer'></div>
 									<table width="100%">
								  	<tr>
								    	<td align="center">
 												<html:submit property="submitAction" onclick="return validatePage(this.form);"><bean:message key="button.next"/></html:submit>
   												<html:reset><bean:message key="button.reset"></bean:message></html:reset>
   												<html:submit property="submitAction"><bean:message key="button.cancel"/>
												</html:submit>
								    	</td>
								  	</tr>
 									</table>
 									<%-- END BUTTONS TABLE --%>
 									<div class='spacer'></div>
								</td>
							</tr>	
						</table>
						<div class='spacer'></div>
					</td>
				</tr>
			</table>
			<div class='spacer'></div>
		</td>
	</tr>
</table>
<%-- END DETAIL TABLE --%>

</html:form>

<div align='center'><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:html>
