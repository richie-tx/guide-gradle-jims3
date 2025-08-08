<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--MODIFICATIONS -->
<!-- 01/22/2008 Debbie Williamson - Converted PT to JSP -->
<!-- 08/04/2009 C Shimek          - #61345 Added onload to check for selected radio button and simulate onclick -->
<!-- 11/18/2009 C Shimek          - #62476 Added selectedCaseId as hidden field  -->
<!-- 12/22/2009 C Shimek          - #63239 Added Print Packet button -->
<!-- 02/24/2010 D Gibler          - Added entry/exit without restriction to button handler -->
<!-- 02/26/2010 RYoung            - #64083 Fixed the casenote and PrintAppointment JAvascript -->
<!-- 03/04/2010 D Gibler		  - removed render of exit referral button if referral is in initiated state. -->
<!-- 04/12/2010 RYoung            - Fixed the Buttons to display when only one referral selected onload -->
<!-- 08/11/2010 C Shimek          - #66250 added feature check to Casenote Create button -->
<!-- 01/05/2011 RCapestani        - #66145 Fixed the Exit Referral button to display when an initiated referral does not have a begin date-->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>

<!--CUSTOM LIBRARIES NEEDED FOR PAGE -->
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>

<%@ page import="naming.Features" %>

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
<title><bean:message key="title.heading" /> - admininsterCaseloadCSCD/programReferrals/referralsList.jsp</title>
<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>

<script>
function showMyButtons(radioElem, initVal, openVal, exitVal, autoReferral, providerName, isSentToState, removeExtEntWRestrc, removeExtEntWithoutRestrc, beginDateAsStr)
{
	hideAllButtons();
	show('allButtons', '1', 'span');

	if(beginDateAsStr=="")
	{
		show('exitReferralButton', '1', 'row');
	}

	if(initVal=="true")
	{
		if(document.getElementById('updateButton') != null)
		{
			show('updateButton', '1', 'row');
		}
		if(document.getElementById('viewButton') != null)
		{
			show('viewButton', '1', 'row');
		}
		if(providerName!=null && providerName!="")
		{
			if(document.getElementById('submitReferralButton') != null)
			{
				show('submitReferralButton', '1', 'row');
			}
		}
		if(document.getElementById('deleteButton') != null)
		{
			show('deleteButton', '1', 'row');
		}
		/* shouldn't be able to exit if referral has not been submitted 
		if(document.getElementById('exitReferralButton') != null)
		{
			show('exitReferralButton', '1', 'row');
		}*/
		show('generateFormButton', '1', 'row');
		show('printApptButton', '1', 'row');
		show('printPacketButton', '1', 'row');
		show('casenoteButtons', '1', 'row');
	}
	if(openVal=="true")
	{
		if(document.getElementById('updateButton') != null)
		{
			show('updateButton', '1', 'row');
		}
		if(document.getElementById('viewButton') != null)
		{
			show('viewButton', '1', 'row');
		}
		if(document.getElementById('exitReferralButton') != null)
		{
			show('exitReferralButton', '1', 'row');
		}
		if(document.getElementById('reReferralButton') != null)
		{
			show('reReferralButton', '1', 'row');
		}
		if(document.getElementById('removeEntryButton') != null)
		{
			if((removeExtEntWithoutRestrc=="true"))
			{
				show('removeEntryButton', '1', 'row');
			}
			else if((removeExtEntWRestrc=="true") && (isSentToState=="false"))
			{
				show('removeEntryButton', '1', 'row');
			}
			else if(removeExtEntWRestrc=="false")
			{
				show('removeEntryButton', '1', 'row');
			}
		}
		show('generateFormButton', '1', 'row');
		show('printApptButton', '1', 'row');
		show('printPacketButton', '1', 'row');
		show('casenoteButtons', '1', 'row');
	}
	if(exitVal=="true")
	{
		if(document.getElementById('updateButton') != null)
		{
			show('updateButton', '1', 'row');
		}
		if(document.getElementById('viewButton') != null)
		{
			show('viewButton', '1', 'row');
		}
		if(document.getElementById('removeExitButton') != null)
		{
			if((removeExtEntWithoutRestrc=="true"))
			{
				show('removeExitButton', '1', 'row');
			}
			else if((removeExtEntWRestrc=="true") && (isSentToState=="false"))
			{
				show('removeExitButton', '1', 'row');
			}
			else if(removeExtEntWRestrc=="false")
			{
				show('removeExitButton', '1', 'row');
			}
		}
		show('generateFormButton', '1', 'row');
		show('casenoteButtons', '1', 'row');
	}
}

function showMidButtons()
{
	show('generateFormButton', '1', 'row');
	show('printApptButton', '1', 'row');
	show('casenoteButtons', '1', 'row');
}

function hideAllButtons()
{
	if(document.getElementById('updateButton') != null)
	{
	show('updateButton', '0', 'row');
	}
	if(document.getElementById('viewButton') != null)
	{
		show('viewButton', '0', 'row');
	}
	if(document.getElementById('deleteButton') != null)
	{
		show('deleteButton', '0', 'row');
	}
	if(document.getElementById('exitReferralButton') != null)
	{
		show('exitReferralButton', '0', 'row');
	}
	if(document.getElementById('submitReferralButton') != null)
	{
		show('submitReferralButton', '0', 'row');
	}
	if(document.getElementById('reReferralButton') != null)
	{
		show('reReferralButton', '0', 'row');
	}
	if(document.getElementById('removeEntryButton') != null)
	{
		show('removeEntryButton', '0', 'row');
	}
	if(document.getElementById('removeExitButton') != null)
	{
		show('removeExitButton', '0', 'row');
	}
	
	show('generateFormButton', '0', 'row');
	show('printApptButton', '0', 'row');
	show('printPacketButton', '0', 'row');
	show('casenoteButtons', '0', 'row');
}

function printAppointment()
{
	var selectedProgRefId = ""; 
	var progRefradioObjects = document.getElementsByName('selectedValue');
	var len = progRefradioObjects.length;
	var webApp = "/<msp:webapp/>";

	for(var i=0; i < len; i++)
	{
		var progRefradioObj = progRefradioObjects[i];
		if(progRefradioObj!=null && progRefradioObj.checked)
		{
			selectedProgRefId = progRefradioObj.value;
			openWindow(webApp + "displayProgRefAppointment.do?submitAction=Link&selectedValue=" + progRefradioObjects[i].value);
		}
	}
}

function printPacket()
{
	alert("Print Packet");
	return true;
}

function viewCasenotes()
{
      var progRefradioObjects = document.getElementsByName('selectedValue');
      var webApp = "/<msp:webapp/>";
      
      for(var i=0; i < progRefradioObjects.length; i++)
      {
            if(progRefradioObjects[i]!=null && progRefradioObjects[i].checked)
            {
                  openWindow(webApp + "displayProgRefCasenote.do?submitAction=View&selectedValue=" + progRefradioObjects[i].value);
            }
      }
}

// this function may need to be changed if page is accessed after referral data is updated
// currently back, cancel and create casenote access this page after initial access from tab
function checkRadioSelect()
{
	var rb = document.getElementsByName("selectedValue");
	if (rb.length > 0)
	{
		for (x = 0; x <rb.length; x++)
		{
			if (rb[x].checked == true)
			{
				rb[x].click();
				break;
			}	
		}		
	}
}
function setSelectedCaseId(caseId)
{
	var fv =document.getElementsByName("selectedCaseId")
	if (fv.length > 0){
		fv[0].value = caseId;
	}	
}

function checkForSingleResult() {
    var rbs = document.getElementsByName("selectedValue");
	if (rbs.length == 1){
		rbs[0].checked = true;
		document.getElementById(rbs[0].id).click();
	}	
}
</script>
</head>

<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true);" onload="checkRadioSelect();checkForSingleResult()">

<%-- Begin Pagination Header Tag--%>
<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define> 
<pg:pager index="center" maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
    maxIndexPages="10" export="offset,currentPageNumber=pageNumber" scope="request">
  <input type="hidden" name="pager.offset" value="<%= offset %>">
<%-- End Pagination header stuff --%>

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
						<tiles:insert page="../../common/superviseeHeader.jsp" flush="true">
						</tiles:insert> 
						<!--header area end-->
					</td>
				</tr>
				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
				<tr>
					<td align="center">
						
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
																			  <bean:message key="title.programReferral"/>&nbsp;<bean:message key="prompt.list"/>&nbsp;</td>
										<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Program_Referrals/CSCD_Program_Referrals.htm#|1">
										</tr>
										<tr>
											<td class="confirm">
												<logic:present name="confirmMsg">
													<bean:write name="confirmMsg" />
												</logic:present>
										    </td>
									    </tr>
									</table>
									<!-- END HEADING TABLE -->
									<%-- BEGIN ERROR TABLE --%>
									<table width="98%" align="center">
										<tr>
											<td align="center" class="errorAlert"><html:errors></html:errors></td>
										</tr>
									</table>								
									<!-- END ERROR TABLE -->
									<!-- BEGIN INSTRUCTION TABLE -->
									<table width="98%" border="0" cellpadding="2" cellspacing="1">
										<tr>
											<td>
												<ul>
													<li>To filter program referrals, select filtering options. Click Filter.</li>
													<li>Select a program referral and click appropriate button, or click Initiate Referral.</li>
												</ul>
											</td>
										</tr>
									</table>
									<!-- END INSTRUCTION TABLE -->
									<!--programs list start-->																
									<table width="98%" cellpadding="0" cellspacing="0" border="0"><html:form action="/displayProgRefList" target="content">
										<tr>
											<td width="100%" valign="top">
												<table width="100%" cellpadding="2" cellspacing="0" class="borderTableBlue">
													<tr class="detailHead">
														<td><bean:message key="prompt.filter" />&nbsp;<bean:message key="prompt.programs" /></td>
													</tr>
													<tr>
														<td>
															<table width="100%" cellpadding="4" cellspacing="1">
																<tr>
																  <td class="formDeLabel" nowrap="nowrap" 1%"><bean:message key="prompt.status" /></td>
																  <td class="formDe">
																  	<html:checkbox name="cscProgRefSearchForm" property="initated" /><bean:message key="prompt.initiated" />
                                                                    <html:checkbox name="cscProgRefSearchForm" property="open" /><bean:message key="prompt.open" /> 
                                                                    <html:checkbox name="cscProgRefSearchForm" property="exited" /><bean:message key="prompt.exited" />
																</td>
																  <td class="formDeLabel" nowrap="nowrap" width="1%"><bean:message key="prompt.case#" /></td>
																  <td class="formDe">
																	 <html:select size="1" name="cscProgRefSearchForm" property="caseNum">
																		<html:option value=""><bean:message key="select.generic" /></html:option>
																		<html:optionsCollection name="cscProgRefSearchForm" property="casesList" label="caseNums" value="caseNums"/> 
																	 </html:select>
																  </td>
																</tr>
																<tr>
																	<td class="formDeLabel" nowrap="nowrap"></td>
																	<td class="formDe" colspan="3">
																		<html:submit property="submitAction"><bean:message key="button.filter"  /></html:submit>
																		<html:submit property="submitAction"><bean:message key="button.refresh" /> </html:submit>
																	  <input type="hidden" name="clearStatusBoxes" value="true"/> 
																   </td>
																</tr>
															</table>
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</html:form></table>
									
									<div class="spacer4px"></div> 
									
									<table border="0">
										<tr>
											<td><b><bean:write name="cscProgRefSearchForm" property="progRefSearchCount"/></b> search results for Program Referrals</td>
										</tr>
									</table>
									
									<div class="borderTableBlue" style="width:98%">
									<html:form action="/handleProgRefSelection" target="content">
									<table width="100%" border="0" cellpadding="2" cellspacing="1" >
										<tr class="detailHead">
											<td width="1%"></td>
											<td><bean:message key="prompt.case#" />
												<jims2:sortResults beanName="cscProgRefSearchForm" results="filteredProgReferralsList" primaryPropSort="caseNum" primarySortType="STRING"  defaultSortOrder="ASC" sortId="1" levelDeep="3"/></td>
											<td><bean:message key="prompt.referralType" />
												<jims2:sortResults beanName="cscProgRefSearchForm" results="filteredProgReferralsList" primaryPropSort="referralTypeDesc" primarySortType="STRING"  defaultSortOrder="ASC" sortId="2" levelDeep="3"/></td>
											<td><bean:message key="prompt.serviceProvider" />
												<jims2:sortResults beanName="cscProgRefSearchForm" results="filteredProgReferralsList" primaryPropSort="serviceProvidername" primarySortType="STRING"  defaultSortOrder="ASC" sortId="3" levelDeep="3"/></td>
											<td><bean:message key="prompt.programId" />
												<jims2:sortResults beanName="cscProgRefSearchForm" results="filteredProgReferralsList" primaryPropSort="programIdentifier" primarySortType="STRING"  defaultSortOrder="ASC" sortId="4" levelDeep="3"/></td>
											<td><bean:message key="prompt.referralDate" />
												<jims2:sortResults beanName="cscProgRefSearchForm" results="filteredProgReferralsList" primaryPropSort="referralDateAsStr" primarySortType="DATE"  defaultSortOrder="ASC" sortId="5" levelDeep="3"/></td>
											<td><bean:message key="prompt.beginDate" />
												<jims2:sortResults beanName="cscProgRefSearchForm" results="filteredProgReferralsList" primaryPropSort="beginDateAsStr" primarySortType="DATE"  defaultSortOrder="ASC" sortId="6" levelDeep="3"/></td>
											<td><bean:message key="prompt.endDate" />
												<jims2:sortResults beanName="cscProgRefSearchForm" results="filteredProgReferralsList" primaryPropSort="endDateAsStr" primarySortType="DATE"  defaultSortOrder="ASC" sortId="7" levelDeep="3"/></td>
										</tr>
										 <logic:iterate id="referralIndex" name="cscProgRefSearchForm" property="filteredProgReferralsList" indexId="index">
										  <pg:item>
											<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
												<td align="center"><input type='radio' name='selectedValue' value='<bean:write name="referralIndex" property="referralId"/>' id='<bean:write name="referralIndex" property="referralId"/>' onclick='setSelectedCaseId("<bean:write name="referralIndex" property="criminalCaseId"/>");showMyButtons(this,"<bean:write name='referralIndex' property='initiated'/>","<bean:write name='referralIndex' property='open'/>","<bean:write name='referralIndex' property='exited'/>","<bean:write name='referralIndex' property='autoReferral'/>","<bean:write name='referralIndex' property='serviceProvidername'/>","<bean:write name='referralIndex' property='sentToState'/>","<bean:write name='cscProgRefForm' property='removeExtNEntryWithRestrc'/>","<bean:write name='cscProgRefForm' property='removeExtNEntryWithOutRestrc'/>","<bean:write name="referralIndex" property="beginDateAsStr" />")'/>
											<%-- 		<input type="hidden" name="" value='<bean:write name='referralIndex' property='initiated'/>","<bean:write name='referralIndex' property='open'/>","<bean:write name='referralIndex' property='exited'/>","<bean:write name='referralIndex' property='autoReferral'/>","<bean:write name='referralIndex' property='serviceProvidername'/>","<bean:write name='referralIndex' property='sentToState'/>","<bean:write name='cscProgRefForm' property='removeExtNEntryWithRestrc'/>"' /> --%>
												</td>
												<td><bean:write name="referralIndex" property="caseNum"/></td>
												<td><bean:write name="referralIndex" property="referralTypeDesc"/>
													<logic:equal name="referralIndex" property="autoReferral" value="true">
														<img src="/<msp:webapp/>images/starBlueIcon.gif" title="Automatic Program Referral" />
													</logic:equal>
												</td>
												<td><bean:write name="referralIndex" property="serviceProvidername"/></td>
												<td><bean:write name="referralIndex" property="programIdentifier"/></td>
												<td><bean:write name="referralIndex" property="referralDateAsStr" /></td>
												<td><bean:write name="referralIndex" property="beginDateAsStr" /></td>
												<td><bean:write name="referralIndex" property="endDateAsStr" /></td>
											</tr>
											
										  </pg:item>
										 </logic:iterate>
										 <input type="hidden" name="selectedCaseId" value="" /> 
										</table>
									</div>
									<div id="legendArea" align="left">&nbsp;&nbsp;<img src="/<msp:webapp/>images/starBlueIcon.gif" title="Automatic Program Referral"> indicates Automatic Program Referral</div>
									<%-- Begin Pagination navigation Row--%>
										<table align="center">
											<tr>
											<td>
												<pg:index>
													<tiles:insert page="/jsp/jimsPagination.jsp" flush="true">
														<tiles:put name="pagerUniqueName" value="pagerSearch"/>
														<tiles:put name="resultsPerPage" value="<%=paginationResultsPerPage%>"/>
													</tiles:insert>
												</pg:index>
											</td>
											</tr>
										</table>
										<%-- End Pagination navigation Row--%>									
									<!--programs list end-->
									
										<!-- BEGIN BUTTON TABLE -->
										<table border="0" cellpadding="1" cellspacing="0">
											<tr>
												<td align="center">
													<span id="allButtons" class="hidden">
														<jims2:isAllowed requiredFeatures='<%=Features.CSCD_PRG_REF_UPDATE%>'>	
															<html:submit property="submitAction" styleId="updateButton"> <bean:message key="button.update" /></html:submit>
														</jims2:isAllowed>
														<jims2:isAllowed requiredFeatures='<%=Features.CSCD_PRG_REF_VIEW%>'>		
															<html:submit property="submitAction" styleId="viewButton"> <bean:message key="button.view" /></html:submit>
														</jims2:isAllowed>	
														<jims2:isAllowed requiredFeatures='<%=Features.CSCD_PRG_REF_SUBMIT%>'>	
															<html:submit property="submitAction" styleClass="hidden" styleId="submitReferralButton"> <bean:message key="button.submitReferral" /></html:submit>
														</jims2:isAllowed>	
														<jims2:isAllowed requiredFeatures='<%=Features.CSCD_PRG_REF_DELETE%>'>	
															<html:submit property="submitAction" styleId="deleteButton"> <bean:message key="button.delete" /></html:submit>
														</jims2:isAllowed>	
														<jims2:isAllowed requiredFeatures='<%=Features.CSCD_PRG_REF_EXIT%>'>	
															<html:submit property="submitAction" styleClass="hidden" styleId="exitReferralButton"> <bean:message key="button.exitReferral" /></html:submit>
														</jims2:isAllowed>
														<jims2:isAllowed requiredFeatures='<%=Features.CSCD_PRG_REF_REREFER%>'>		
															<html:submit property="submitAction" styleClass="hidden" styleId="reReferralButton"> <bean:message key="button.reReferral" /></html:submit>
														</jims2:isAllowed>
														<jims2:isAllowed requiredFeatures='<%=Features.CSCD_PRG_REF_REM_EXT_ENT_WITH_RESTRC%>'>		
															<html:submit property="submitAction" styleClass="hidden" styleId="removeEntryButton"> <bean:message key="button.removeEntry" /></html:submit>
															<html:submit property="submitAction" styleClass="hidden" styleId="removeExitButton"> <bean:message key="button.removeExit" /></html:submit>
														</jims2:isAllowed>
														<jims2:isAllowed requiredFeatures='<%=Features.CSCD_PRG_REF_REM_EXT_ENT_WITHOUT_RESTRC%>'>		
															<html:submit property="submitAction" styleClass="hidden" styleId="removeEntryButton"> <bean:message key="button.removeEntry" /></html:submit>
															<html:submit property="submitAction" styleClass="hidden" styleId="removeExitButton"> <bean:message key="button.removeExit" /></html:submit>
														</jims2:isAllowed>	
													</span>
												</td>
											</tr>
											<tr>
												<td align="center">
													<html:submit property="submitAction" styleClass="hidden" styleId="generateFormButton"> <bean:message key="button.generateForm" /></html:submit>
													<html:button property="submitAction" styleClass="hidden" styleId="printApptButton" onclick="printAppointment();"><bean:message key="button.printAppt" /></html:button>
													<span id="printPacketButton" class="hidden">
														<html:submit property="submitAction"><bean:message key="button.printPacket" /></html:submit>
													</span>
													<span id="casenoteButtons" class="hidden">
														<jims2:isAllowed requiredFeatures='<%=Features.CS_CASENOTE_CREATE%>'>
															<html:submit property="submitAction"> <bean:message key="button.createCasenote" /></html:submit>
														</jims2:isAllowed>	
														<html:button property="submitAction" onclick="viewCasenotes();"><bean:message key="button.viewCasenotes" /></html:button>
													</span>
												</td>
											</tr>
											<tr>
												<td align="center">
													<logic:notPresent name="confirmMsg">
														<html:submit property="submitAction"> <bean:message key="button.back" /></html:submit>
													</logic:notPresent>
													<jims2:isAllowed requiredFeatures='<%=Features.CSCD_PRG_REF_INITIATE%>'>
														<html:submit property="submitAction"> <bean:message key="button.initiateNewReferral" /></html:submit>
													</jims2:isAllowed>	
													<html:submit property="submitAction"> <bean:message key="button.cancel" /></html:submit>
												</td>
											</tr>
										</table>
									<!-- END BUTTON TABLE -->
									</html:form></td>
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

</div>
<br>
</pg:pager>

<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
