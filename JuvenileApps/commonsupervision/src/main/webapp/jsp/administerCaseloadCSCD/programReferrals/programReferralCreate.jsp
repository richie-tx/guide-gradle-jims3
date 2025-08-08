<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--MODIFICATIONS -->
<!-- 01/24/2008 Debbie Williamson - Converted PT to JSP -->
<!-- 12/22/2009 C Shimek          - #63239 Added coding for Print Packet flow -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nest"%>
<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>

<%@ page import="naming.CSAdministerProgramReferralsConstants" %>

<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />
<title><bean:message key="title.heading" /> - admininsterCaseloadCSCD/programReferrals/programReferralCreate.jsp</title>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/AnchorPosition.js"></script>

<script>
function validate()
{
	var myAction = "<bean:write name="cscProgRefForm" property="action" />"; 
	var oneReferralSelected = false;
	
	if(myAction == 'create')
	{
		var availableRefTypesLen = document.getElementsByName("refTypeCount")[0].value;
		
		for(var index=0; index < availableRefTypesLen; index++)
		{
			elementName = "availableReferralTypesList[" + index + "].selected";
			
			var  refType = document.getElementsByName(elementName)[0];
			if(refType.checked == true)
			{
				oneReferralSelected = true;
				break;
			}
		}
	}
	else if(myAction == 'updateInit')
	{
		var refTypeRadioElemts = document.getElementsByName("referralTypeCode");
		var refTypeLen = refTypeRadioElemts.length;
		
		for(var i=0; i < refTypeLen; i++)
		{
			if(refTypeRadioElemts[i].checked)
			{
				oneReferralSelected = true;
				break;
			}
		}
	}
		
	if(oneReferralSelected == false)
	{
		alert("Please select at least 1 Referral Type.");
		return false;
	}
	
	return true;
}
</script>
</head>
<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true);">
<html:form action="/displayProgRefRefTypeSelect" target="content">
<div align="center">
<table width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
	</tr>
	<tr>
		<td valign="top">
			<table width="100%" border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign="top">
						<!--tabs start-->
						<tiles:insert page="../../common/commonSupervisionTabs.jsp" flush="true">
							<tiles:put name="tab" value="caseloadTab" />
						</tiles:insert> 
						<!--tabs end-->
					</td>
				</tr>
				<tr>
					<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
			</table>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
				<tr>
					<td valign="top" align="center">
						<!--header area start-->
						<table width="98%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td bgcolor="#cccccc" colspan="2">
									<!--header start-->
									<tiles:insert page="../../common/caseloadHeaderCase.jsp" flush="true">
									</tiles:insert> 
									<!--header end-->
								</td>
							</tr>
							<tr>
								<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
							</tr>
						</table>
						<!--header area end-->
						<!--casefile tabs start-->
						<table width="98%" border="0" cellpadding="0" cellspacing="0" >
							<tr>
								<td valign="top">
									<!--tabs start-->
									<tiles:insert page="../../common/caseloadCSCDSubTabs.jsp" flush="true">
										<tiles:put name="tab" value="ProgramReferralsTab" />
									</tiles:insert>
									<!--tabs end-->
								</td>
							</tr>
							<tr>
								<td bgcolor="#33cc66"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
							</tr>
						</table>
						<table width="98%" border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
							<tr>
								<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
							</tr>
							<tr>
								<td valign="top" align="center">
									<!-- BEGIN HEADING TABLE -->
									<table width="100%">
										<tr>
											<td align="center" class="header"><bean:message key="title.CSCD"/>&nbsp;-
											<logic:equal name="cscProgRefForm" property="action" value="<%=CSAdministerProgramReferralsConstants.ACTION_UPDATE_INIT%>">
												<bean:message key="title.update"/><input type="hidden" name="helpFile" value="commonsupervision/CSCD_Program_Referrals/CSCD_Program_Referrals.htm#|9">
											</logic:equal>
											<logic:equal name="cscProgRefForm" property="action" value="<%=CSAdministerProgramReferralsConstants.ACTION_CREATE%>">
												<logic:notEqual name="cscProgRefForm" property="printPacketFlow" value="true">
													<bean:message key="prompt.initiate"/><input type="hidden" name="helpFile" value="commonsupervision/CSCD_Program_Referrals/CSCD_Program_Referrals.htm#|3">
												</logic:notEqual>
												<logic:equal name="cscProgRefForm" property="printPacketFlow" value="true">
													<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Program_Referrals/CSCD_Program_Referrals.htm#|3">
												</logic:equal>	
											</logic:equal> 
											<bean:message key="prompt.programReferral"/>&nbsp;-&nbsp;<bean:message key="prompt.referralTypes"/></td>
										</tr>
									</table>
									<%-- BEGIN ERROR TABLE --%>
									<table width="98%" align="center">
										<tr>
											<td align="center" class="errorAlert"><html:errors></html:errors></td>
										</tr>
									</table>								
									<!-- END ERROR TABLE -->
									<table width="98%" border="0" cellpadding="2" cellspacing="1" align="center">
										<tr>
											<td><ul>
												<li>Select Referral Type<logic:equal name="cscProgRefForm" property="action" value="<%=CSAdministerProgramReferralsConstants.ACTION_CREATE%>">(s)</logic:equal>
											   		<logic:notEqual name="cscProgRefForm" property="printPacketFlow" value="true">
											    		and click Save & Continue.</li>
											   		</logic:notEqual> 
											   		<logic:equal name="cscProgRefForm" property="printPacketFlow" value="true">
											    		and click Next.</li>
											   		</logic:equal> 
											    </ul>
                                            </td>
										</tr>
										<tr>
											<td class="required"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.requiredFieldsInstruction"/></td>
										</tr>
									</table>
									<!-- END HEADING TABLE -->
									
									<!-- reset Service Provider checkboxes -->
									<input type="hidden" name="clearRefTypeCheckBoxes" value="true" />
							
									<!--Conditions List start-->
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td colspan="1">
												<!--conditions list tile start-->
												<tiles:insert page="prConditionsListTile.jsp" flush="true">
													<tiles:put name="condList" beanName="cscProgRefForm" beanProperty="conditionsList"></tiles:put>
												</tiles:insert> 
												<!--conditions list tile end-->
											</td>
										</tr>
									</table>
									<!--Conditions List end-->
									<!-- New Groups Referrals Begin -->
									<div class="spacer4px"></div>
									<table width="98%" cellpadding="0" cellspacing="0" border="0">
										<tr>
											<td class="formDeLabel" nowrap valign="top"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.referralTypes"/></td>
										</tr>
										<tr>
											<td valign="top">
												<table width="100%" cellpadding="2" cellspacing="0" class="borderTableBlue">
													<tr>
														<td colspan="2">
															<div class="scrollingDiv200">
																<% int recordCounter=0;
																   int groupCounter =0;
																   String bgcolor=""; %>
																	<table width="100%" border="0" cellpadding="2" cellspacing="1">
																		<nest:iterate id="groupList" name="cscProgRefForm" property="availableReferralGroupDescriptionList">
																			<bean:define id="groupDesc" name="groupList" type="java.lang.String"/>
																			<%groupCounter++; // up the outer loop record which represents group container%>
																			<tr class="detailHead">
																				<td width="1%"><a href="javascript:showHideMultipleReferrals('<%=groupCounter%>', 'row', '/<msp:webapp/>' )"><img border="0" src="/<msp:webapp/>images/expand.gif" id="<%=groupCounter%>"></a></td>
																				<td><bean:write name="groupList" /></td>
																			</tr>
																			<nest:iterate id="referralList" name="cscProgRefForm" property="availableReferralTypesList"> 
																		            <bean:define id="referralBean" name="referralList" type="ui.supervision.programReferral.ReferralTypeBean"/>   
																					<% 
																					// check for inner and outer loop match of the group name to only group referral beans under matching group name
																					if(groupDesc != null && referralBean.getReferralGroupDesc() != null && groupDesc.equals(referralBean.getReferralGroupDesc())){ 
																					recordCounter++; // up the inner loop record which represents total records overall 
																					%>
																					<tr id='<%=groupCounter%>Span' class='hidden <%=groupCounter%>Span <% bgcolor = "alternateRow";                      
																						  if (recordCounter % 2 == 1)
																							  bgcolor = "normalRow";
																						   out.print(bgcolor); %>'>
																						<td colspan="2">
																							<logic:equal name="cscProgRefForm" property="action" value="<%=CSAdministerProgramReferralsConstants.ACTION_UPDATE_INIT%>">
																								<html:radio idName="referralList" property="referralTypeCode" value="referralTypeCode" />
																							</logic:equal>
																							<logic:equal name="cscProgRefForm" property="action" value="<%=CSAdministerProgramReferralsConstants.ACTION_CREATE%>">  
																							   <nest:checkbox property="selected" value="true"/>
																							</logic:equal>   
																							<bean:write name="referralList" property="referralTypeDesc" />
																						</td>
																					</tr>
																					<%}// end of check for looping %>
																			</nest:iterate>
																		</nest:iterate>
																		<input type="hidden" name="refTypeCount" value="<%=recordCounter %>"/>
																	</table>
																</div>
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>																					
									<!-- New Groups Referrals End -->
									<!-- BEGIN BUTTON TABLE -->
									<table border="0" cellpadding="2" cellspacing="1">
										<tr>
											<td align="center">
												<html:submit property="submitAction"> <bean:message key="button.back" /></html:submit>
												<logic:equal name="cscProgRefForm" property="printPacketFlow" value="true">
													<html:submit property="submitAction" onclick="return validate() && disableSubmit(this, this.form);"> <bean:message key="button.next" /></html:submit>
												</logic:equal>
												<logic:notEqual name="cscProgRefForm" property="printPacketFlow" value="true">
													<html:submit property="submitAction" onclick="return validate() && disableSubmit(this, this.form);"> <bean:message key="button.saveContinue" /></html:submit>
												</logic:notEqual>
												<html:submit property="submitAction"> <bean:message key="button.cancel" /></html:submit>
											</td>
										</tr>
									</table>
									<!-- END BUTTON TABLE -->
								</td>
							</tr>
						</table>
						<div class="spacer4px"></div>
					</td>
				</tr>
			</table>
			<br>
		</td>
	</tr>
</table>
<br>
<!--casefile tabs end-->
<!-- END  TABLE -->
<!--Notice the change in the Header since the system is now in the context of the SPN and CASE -->
</div>
<br>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
