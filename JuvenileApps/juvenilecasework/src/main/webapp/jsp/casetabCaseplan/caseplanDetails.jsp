<!DOCTYPE HTML>

<%--MODIFICATIONS --%>
<%-- 09/19/2006		AWidjaja Create JSP--%>
<%-- 01/17/2007 Hien Rodriguez  ER#37077 Add Tab & Buttons security features --%>
<%-- 04/19/2012 CShimek  		#73232 added allowUpdate edit to buttons for closed casefile status  --%>
<%-- 07/11/2012 CShimek     	#73565 added age > 20 check (juvUnder21) to Add/Update style buttons --%>
<%-- 08/20/2013 CShimek     	Corrected page display alignment(left justified instead of centered) when page used for Profile display -- found while testing #75843 --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="elogic" %>
<%@ page import="naming.Features" %>

<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="naming.UIConstants" %>
<%@ page import="naming.CasePlanConstants" %>
<%@ page import="org.apache.struts.action.ActionErrors" %>



<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/> 

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

<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- caseplanDetails.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>

<script type='text/javascript'>
$(document).ready(function(){
	sessionStorage.removeItem("timeFrame");
	sessionStorage.removeItem("isFosterCare");
	sessionStorage.removeItem("guardianExp");
	sessionStorage.removeItem("juvenileExp");
	sessionStorage.removeItem("determinateSentence");
})

function setSupervisionNum(supervisionNum)
{
	
	var element=document.getElementById('hiddenVal');
	element.value=supervisionNum;	
//	console.log(element.value);
	
}
function loadView(file, selectVal)
{
	var myURL = file + "&selectedValue=" + selectVal;		
	
	load( myURL, top.opener );
	window.close();
}

function load(file,target) 
{
    window.location.href = file;
}

function promptForNewCasefile()
{
	var myVal = true;

	<logic:equal name="caseplanForm" property="currentCaseplan.status" value="ACKNOWLEDGED"> //value changed from 'Signed' as apart of ER id 11203
  	myVal = confirm("This action will create a new version of the caseplan. Do you wish to proceed?");
	</logic:equal>

	return myVal;
}

function acceptOrRejectCaseplan( tForm )
{
  var form = tForm.form ;
	var forwardURL = "/<msp:webapp/>submitCLMReview.do?submitAction=" +tForm.value ;

	form.action = forwardURL;
	form.submit();
}
//Preselect the latest active Residential Supervision type casefile 
function setSelect()
{
	
		var num=0;
		var found=0;	
		var element1='<bean:write name="caseplanForm" property="supervisionTypeDescription"/>';
		var element2="";		
		//var element3=document.getElementById('hiddenVal').value;alert('<bean:write name="caseplanForm" property="selectedValue"/>');
		var index=0;
		var recNum=0;
		var val=document.getElementsByName('twoLane');	
	
		
			<logic:equal name="caseplanForm" property="found" value="true">
			 found=1;
			</logic:equal>
			<logic:notEmpty name="caseplanForm" property="casefiles">
			<logic:iterate id="cases" name="caseplanForm" property="casefiles" indexId="index">
				index++;
				<logic:equal name="caseplanForm" property="residential" value="true">
					<logic:equal name="cases" property="supervisionCategory" value="<%=UIConstants.CASEFILE_SUPERVISION_CAT_POST_ADJ_RES %>">
						if(num==0)
							num++;
					</logic:equal>
				</logic:equal>
				<logic:notEqual name="caseplanForm" property="residential" value="true">
					element2="<bean:write name="cases" property="supervisionType"/>"
					
					if(element1==element2)
					{
						recNum=index;
						
					}
				</logic:notEqual>
				
			</logic:iterate>
			</logic:notEmpty>
		
			
			if(val.length>0 && num!=0)	
			{
				val[num-1].checked=true;
				document.getElementById('hiddenVal').value=val[num-1].value;
			}
			else if(val.length>0 && recNum!=0)
			{
				val[recNum-1].checked=true;
				document.getElementById('hiddenVal').value=val[recNum-1].value;
			}
			else if(val.length==1 && num==0 && (recNum!=0 || found==1))
			{
				val[0].checked=true;
				document.getElementById('hiddenVal').value=val[0].value;
			}
	
	

}

</script>

</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin='0' leftmargin="0" onload="setSelect();">
<html:form action="/handleCaseplan" target="content">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|64"> 

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
	<logic:equal name="caseplanForm" property="action" value="viewDetail">
		<tr>
			<td align="center" class="header">Juvenile Casework - Process Caseplan - Active Caseplan Version Details</td>
		</tr>
	</logic:equal>
	<logic:notEqual name="caseplanForm" property="action" value="viewDetail">
		<tr>
			<td align="center" class="header">Juvenile Casework - Copy Caseplan - Latest Approved Caseplan Details</td>
		</tr>
	</logic:notEqual>
	<tr>
		<td align="center" class="header">
			<logic:equal name="status" value="summary"><bean:message key="prompt.summary"/></logic:equal>
			<logic:equal name="status" value="confirm"><bean:message key="prompt.confirmation"/></logic:equal>
		</td>
	</tr>
	<logic:present name="statusReport">
		<logic:equal name="statusReport" value="confirmFinal">
			<tr id='confMessage'><td align='center' class='confirm'>Final Caseplan Generated.</td></tr>
		</logic:equal>
	</logic:present>
	 <logic:equal name="caseplanForm" property="action" value="finish">
	 <tr id='confMessage'><td align='center' class='confirm'>Caseplan successfully copied.</td></tr>
	 </logic:equal>
</table>
<%-- END HEADING TABLE --%>

<div class='spacer'></div>
<table width="100%">
	  <tr>
		<td align="center" class="errorAlert"><html:errors></html:errors></td></td> 
	</tr>	  
</table>
<div class='spacer'></div>

<%-- BEGIN INSTRUCTION TABLE --%>
<table width="98%" border="0">
  <tr>
    <td>
      <ul>
      <logic:equal name="caseplanForm" property="action" value="viewDetail">
      	<logic:equal name="juvenileCasefileForm" property="juvUnder21" value="true">
	        <li>Select Goal # hyperlink to view details and modify the goal.</li>
	        <li>Select Add New Goal button to add a new goal.</li>
			<logic:equal name="caseplanForm" property="currentCaseplan.status" value="IN REVIEW">
				<%-- this is for the CLM --%>
				<logic:equal name="caseplanForm" property="inReviewForCLM" value="true">
			        <li>Select the Accept or Reject button, as appropriate.</li>
				</logic:equal>
			</logic:equal>
		</logic:equal>	
      	<logic:equal name="juvenileCasefileForm" property="juvUnder21" value="false">
	        <li>Select Goal # hyperlink to view details.</li>
		</logic:equal>
	</logic:equal>
	<logic:equal name="caseplanForm" property="action" value="copy">
		<li>Click on hyperlinked supervision number to view casefile details.</li>
		<li>Select casefile then click Submit button to copy caseplan.</li>
		<li>Select Back button to return to previous page.</li>
	</logic:equal>
	<logic:equal name="caseplanForm" property="action" value="submit">
	  	<li>Review information, then select Finish button to save information.</li>
	    <li>Select Back button to return to previous page to change information.</li>
	</logic:equal>
	<logic:equal name="caseplanForm" property="action" value="finish">	
	    <li>Select Back To Caseplan Details button to return to Caseplan page.</li>
	</logic:equal>
      </ul>
    </td>
  </tr>
</table>
<%-- END INSTRUCTION TABLE --%>
<% String borderClass1 = "borderTableBlue"; 
   String borderClass2 = "borderTableGreen"; 
   String fillColor1 = "#33cc66"; 
   String fillColor2 = "#6699FF"; %>
<logic:equal name="caseplanForm" property="juvProfile" value="true">
	<%  borderClass1 = "borderTableGreen"; 
		borderClass2 = "borderTableBlue"; 
		fillColor1 = "#6699FF"; 
		fillColor2 = "#33cc66"; %>
</logic:equal>
<table align="center" cellpadding='1' cellspacing='0' border='0' width='98%'>
	<tr>
		<td>
<%-- BEGIN CASEFILE/PROFILE HEADER INFO --%>
			<logic:notEqual name="caseplanForm" property="juvProfile" value="true">
				<tiles:insert page="/jsp/caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
    				<tiles:put name="headerType" value="casefileheader" />
  				</tiles:insert>
		  	</logic:notEqual>
		  	<logic:equal name="caseplanForm" property="juvProfile" value="true">
				<tiles:insert page="/jsp/caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
					<tiles:put name="headerType" value="profileheader"/>
				</tiles:insert> 
			</logic:equal>
<%-- BEGIN CASEFILE/PROFILE HEADER INFO --%>
			<div class='spacer'></div>
<%-- BEGIN CASEFILE/MASTER TABS TABLE --%>
			<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top">
						<logic:notEqual name="caseplanForm" property="juvProfile" value="true">
							<tiles:insert page="/jsp/caseworkCommon/casefileTabs.jsp" flush="true">
								<tiles:put name="tabid" value="goalstab" />
								<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
							</tiles:insert>
						</logic:notEqual>
						<logic:equal name="caseplanForm" property="juvProfile" value="true">
					    	<tiles:insert page="/jsp/caseworkCommon/juvenileProfileTabs.jsp" flush="true">
	                			<tiles:put name="tabid" value="goalstab" />
	                			<tiles:put name="juvnum" value='<%=request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>'/>
							</tiles:insert> 
					    </logic:equal>
					</td>
				</tr>
				<logic:equal name="caseplanForm" property="juvProfile" value="true">
				 	<tr>
						<td bgcolor='<%=fillColor2%>'><img src="/<msp:webapp/>images/spacer.gif" width="5" alt=""></td>
					</tr>  
				</logic:equal>
				<tr>
					<td>		
<%-- BEGIN CASEPLAN TABS ALIGNMENT TABLE --%>
						<table width='100%' border="0" cellpadding="0" cellspacing="0" class="<%=borderClass1%>">
							<tr>
								<td valign="top" align="center">
									<div class='spacer'></div>
					  				<table width='98%' border="0" cellpadding="0" cellspacing="0">
					  					<tr>
					  						<td valign="top">
					  							<logic:notEqual name="caseplanForm" property="juvProfile" value="true">
													<tiles:insert page="/jsp/caseworkCommon/casePlanTabs.jsp" flush="true">
						   								<tiles:put name="tabid" value="Caseplan" />
						   								<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
						   							</tiles:insert>
					    						</logic:notEqual>
					    						<logic:equal name="caseplanForm" property="juvProfile" value="true">
					    							<tiles:insert page="/jsp/caseworkCommon/juvenileCasePlanTabs.jsp" flush="true">
	              						  				<tiles:put name="tabid" value="Caseplan"/>
	              						  				<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
	              						  			</tiles:insert>
					    						</logic:equal>
											</td>
					  					</tr>
					  					<tr>
					  						<td bgcolor='<%=fillColor1%>'><img src="/<msp:webapp/>images/spacer.gif" width="5" alt=""></td>
					  					</tr>
										<tr>
											<td>
	                						  	<table width='100%' border="0" cellpadding="0" cellspacing="0" class="<%=borderClass2%>">
		                  							<tr>
		                  								<td valign="top" align="center">
		            										<div class='spacer'></div>     
<%-- BEGIN CASEPLAN DETAILS TABLE --%>
		                    								<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
		                    									<tr>
		                    										<td class='detailHead'> Caseplan Details</td>
		                    									</tr>
		                    									<tr>
			                    									<logic:equal name="caseplanForm" property="caseplanExist" value="Y">
			                    										<td>								
			                    											<table width='100%' border="0" cellpadding="4" cellspacing="1">
			                    												<tr>
			                    													<td nowrap='nowrap' class="formDeLabel" width='1%'><bean:message key="prompt.casePlan"/> <bean:message key="prompt.status"/></td>
			                    													<td class="formDe"><bean:write name="caseplanForm" property="currentCaseplan.status"/></td>
			                    												</tr>
			                    												<tr>
			                    													 <td nowrap='nowrap' class="formDeLabel" width='1%'><bean:message key="prompt.createDate"/></td>
			                    													 <td class="formDe"><bean:write name="caseplanForm" property="currentCaseplan.createDate"/></td>
			                    											   </tr>
			                    											</table>
			                    										</td>
			                    									</logic:equal>
																	<logic:equal name="caseplanForm" property="caseplanExist" value="N">
																		<td class="formDeLabel" colspan='4'>No Caseplan available for this case</td> 
																	</logic:equal>
		                    									</tr>
		                    								</table>
<%-- END CASEPLAN DETAILS TABLE --%>                
	                    									<logic:equal name="caseplanForm" property="caseplanExist" value="Y">
		                    									<div class='spacer'></div>
<%-- BEGIN GOALS DETAILS TABLE --%> 	                    								
			                    								<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
			                    									<tr>
			                    										<td class='detailHead'><bean:message key="prompt.goals"/></td>
			                    									</tr>
			                    									<tr>
			                    										<td>
<%-- BEGIN GOALS LIST TABLE --%> 	                    										
			                    											<table cellpadding='2' cellspacing='1' width='100%'>
			                    												<tr bgcolor='#cccccc'>
			                    													<td class='subHead' align="left"><bean:message key="prompt.goalNumber"/></td>
			                    													<td class='subHead' align="left"><bean:message key="prompt.entryDate"/></td>
			                    													<td class='subHead' align="left"><bean:message key="prompt.update"/><bean:message key="prompt.date"/></td>
			                    													<td class='subHead' align="left"><bean:message key="prompt.status"/></td>
			                    													<td class='subHead' align="left"><bean:message key="prompt.goal"/></td>
			                    												</tr>
			                    
			                    												<logic:notEmpty name="caseplanForm" property="currentCaseplan.goalList">
			                    													<logic:iterate indexId="index" id="goalsIndex" name="caseplanForm" property="currentCaseplan.goalList">
				                    													<tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">
				                    														<td>						
				                    															<a href="javascript:loadView('/<msp:webapp/>displayGoalDetails.do?submitAction=Link&selectedValue=<bean:write name="goalsIndex" property="goalID"/>')"><bean:write name="goalsIndex" property="goalID"/></a>																		
				                    														</td>
				                    														<td><bean:write name="goalsIndex" property="entryDate" formatKey="date.format.mmddyyyy"/></td> 
				                    														<td><bean:write name="goalsIndex" property="goalStatusChangeDate" formatKey="date.format.mmddyyyy"/></td>
				                    														<td><bean:write name="goalsIndex" property="statusId"/></td>
				                    														<td><bean:write name="goalsIndex" property="goalDescription"/></td>										
				                    													</tr>
				                    												</logic:iterate>
			                    												</logic:notEmpty>		
			                    											</table>
<%-- END GOALS LIST TABLE --%> 	                    											
		                    											</td>
		                    										</tr>							
		                    									</table>
<%-- END GOALS DETAILS TABLE --%>                     									
		                   									</logic:equal>
		                   															
		                  									<logic:notEqual name="caseplanForm" property="placementInfoExist" value="N">
		                  										<div class='spacer'></div>
																<table width='98%' border='0' cellpadding='0' cellspacing='0'>
																	<tr>
																		<td>
																			<tiles:insert page="/jsp/casetabCaseplan/placementInfoTile.jsp" flush="true">
																				<tiles:put name="placementInfo" beanName="caseplanForm" beanProperty="currentPlacementInfo"/>	
																			</tiles:insert>
																		</td>
																	</tr>
																</table>
		                  									</logic:notEqual>
		                  									
		                  									<% String item = "juvenileCasefileForm";  %>
															<logic:equal name="caseplanForm" property="juvProfile" value="true">
																<% item = "juvenileProfileHeader"; %>	
															</logic:equal>
															
															<logic:notEqual name="caseplanForm" property="action" value="viewDetail">
																<logic:notEqual name="caseplanForm" property="action" value="<%=CasePlanConstants.CLM_REVIEW_IN_PROGRESS %>">
																	<div class='spacer'></div>
<%-- BEGIN COPY CASEPLAN TABLE --%>															
																	<table align="center" width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
																		<tr>
																			<td class=detailHead><bean:message key="prompt.copy" /> <bean:message key="prompt.casePlan" /> - <bean:message key="prompt.select" /> <bean:message key="prompt.casefile" /></td>
																		</tr>
																		<tr>
																			<td>
<%-- BEGIN COPY CASEPLAN LIST TABLE --%> 																	
																				<table cellpadding='2' cellspacing='1' width='100%'>
																					<logic:notEmpty name="caseplanForm" property="casefiles">
																						<tr bgcolor='#cccccc'>
																							<td width='1%'></td>
																							<td class=subHead align="left"><bean:message key="prompt.supervision" />&nbsp;#</td>
																							<td class=subHead align="left"><bean:message key="prompt.sequence" />&nbsp;#</td>
																							<td class=subHead align="left"><bean:message key="prompt.probationOfficer" />&nbsp;<bean:message key="prompt.name" /></td>
																							<td class=subHead align="left"><bean:message key="prompt.supervision" />&nbsp;<bean:message key="prompt.type" /></td>
																							<td class=subHead align="left"><bean:message key="prompt.caseStatus" /></td>
																						</tr>
																						
																						<logic:equal name="caseplanForm" property="action" value="copy">
																							<logic:iterate id="cases" name="caseplanForm" property="casefiles" indexId="index">
																								<tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">
																									<td width='1%'>
																										<logic:equal name="caseplanForm" property="residential" value="true">
																											<logic:equal name="cases" property="supervisionCategory" value="<%=UIConstants.CASEFILE_SUPERVISION_CAT_POST_ADJ_RES %>">
																												<input type="radio" name="twoLane" value='<bean:write name="cases" property="supervisionNum"/>' onclick="setSupervisionNum('<bean:write name="cases" property="supervisionNum"/>')"  />
																											</logic:equal>
																										</logic:equal>
																										<logic:notEqual name="caseplanForm" property="residential" value="true">
																											<logic:notEqual name="cases" property="supervisionCategory" value="<%=UIConstants.CASEFILE_SUPERVISION_CAT_POST_ADJ_RES %>">
																												<input type="radio" name="twoLane" value='<bean:write name="cases" property="supervisionNum"/>' onclick="setSupervisionNum('<bean:write name="cases" property="supervisionNum"/>')" />
																											</logic:notEqual>
																										</logic:notEqual>
																										<logic:equal name="caseplanForm" property="residential" value="true">
																											<logic:notEqual name="cases" property="supervisionCategory" value="<%=UIConstants.CASEFILE_SUPERVISION_CAT_POST_ADJ_RES %>">
																												<input type="radio" name="twoLane" value='<bean:write name="cases" property="supervisionNum"/>' onclick="setSupervisionNum('<bean:write name="cases" property="supervisionNum"/>')" disabled='disabled'/>
																											</logic:notEqual>
																										</logic:equal>
																										<logic:notEqual name="caseplanForm" property="residential" value="true">
																											<logic:equal name="cases" property="supervisionCategory" value="<%=UIConstants.CASEFILE_SUPERVISION_CAT_POST_ADJ_RES %>">
																												<input type="radio" name="twoLane" value='<bean:write name="cases" property="supervisionNum"/>' onclick="setSupervisionNum('<bean:write name="cases" property="supervisionNum"/>')" disabled='disabled'/>
																											</logic:equal>
																										</logic:notEqual>
																						        	</td>
																		      																																								                    	
																			        				<td><a href="javascript:openWindow('/JuvenileCasework/displayJuvenileProfileCasefileDetails.do?casefileId=<bean:write name="cases" property="supervisionNum"/>')">
																			        					<bean:write name="cases" property="supervisionNum"/> </a>
																									</td>
																									<td><bean:write name="cases" property="sequenceNum"/></td>
																									<td><bean:write name="cases" property="probationOfficer"/></td>
																									<td><bean:write name="cases" property="supervisionType"/></td>
																									<td><bean:write name="cases" property="caseStatus"/></td>
																			        			</tr>
																							</logic:iterate>
																						</logic:equal>
																						<logic:notEqual name="caseplanForm" property="action" value="copy">
																							<logic:notEmpty name="caseplanForm" property="selectedCasefile">
																						  		<tr>
																						  			<td width='1%'></td>																																		                    	
																		        					<td>
																		        						<bean:write name="caseplanForm" property="selectedCasefile.supervisionNum"/>
																									</td>
																									<td><bean:write name="caseplanForm" property="selectedCasefile.sequenceNum"/></td>
																									<td><bean:write name="caseplanForm" property="selectedCasefile.probationOfficer"/></td>
																									<td><bean:write name="caseplanForm" property="selectedCasefile.supervisionType"/></td>
																									<td><bean:write name="caseplanForm" property="selectedCasefile.caseStatus"/></td>
																							  	</tr>
																						  	</logic:notEmpty>
																						</logic:notEqual>
																					</logic:notEmpty>
																					<logic:empty name="caseplanForm" property="casefiles">
																						<tr>
																	                		<td colspan="5">There are no other active casefiles associated with this juvenile.</td>
																	                    </tr>
																					</logic:empty>
																				</table>
<%-- BEGIN COPY CASEPLAN LIST TABLE --%> 																		
																			</td>
																		</tr>
																	</table>
<%-- END COPY CASEPLAN TABLE --%>																
																	<html:hidden property="selectedValue" styleId="hiddenVal"/>
																	<html:hidden property="supervisionType" styleId="hiddenNum"/>
																</logic:notEqual>							
															</logic:notEqual>
<%-- BEGIN BUTTON TABLE --%>    
		                  									<table width="100%">
			                  									<logic:equal name="caseplanForm" property="action" value="viewDetail">
			                  										<logic:equal name="<%=item%>" property="juvUnder21" value="true"> 
				                  										<logic:equal name="caseplanForm" property="allowUpdates" value="true">
					                										<logic:notEqual name="caseplanForm" property="juvProfile" value="true">
																				<jims2:isAllowed requiredFeatures='<%=Features.JCW_CF_GOALS_U%>'>
																					<tr>
																						<td align="center">
																							<logic:notEqual name="caseplanForm" property="currentCaseplan.status" value="IN REVIEW">  
																								<html:submit property="submitAction" onclick="return promptForNewCasefile()"><bean:message key="button.addNewGoal"/></html:submit>	
																							</logic:notEqual>
																						
																							<logic:equal name="caseplanForm" property="caseplanExist" value="Y">		
																								<html:submit property="submitAction"><bean:message key="button.jpoReview"/></html:submit>
																								<logic:equal name="caseplanForm" property="currentCaseplan.status" value="PENDING">
																									<html:submit property="submitAction"><bean:message key="button.requestCLMReview"/></html:submit>
																								</logic:equal>
																								<logic:equal name="caseplanForm" property="currentCaseplan.status" value="MODIFIED">
																									<html:submit property="submitAction"><bean:message key="button.requestCLMReview"/></html:submit>
																								</logic:equal>
																					
																								<logic:equal name="caseplanForm" property="currentCaseplan.status" value="IN REVIEW">
																									<%-- this is for the JPO --%>
																									<logic:equal name="caseplanForm" property="inReviewForCLM" value="false">
																										<html:submit property="submitAction"><bean:message key="button.requestCLMReview"/></html:submit>
																									</logic:equal>
																									<%-- this is for the CLM --%>
																									<logic:equal name="caseplanForm" property="inReviewForCLM" value="true">
																										<input type="button" value="Accept" onclick="acceptOrRejectCaseplan(this);" />			
																										<input type="button" value="Reject" onclick="acceptOrRejectCaseplan(this);" />			
																									</logic:equal>
																								</logic:equal>
																				
																								<logic:equal name="caseplanForm" property="supervisionCategoryId" value="<%=UIConstants.CASEFILE_SUPERVISION_CAT_POST_ADJ_RES%>">
																									<logic:notEqual name="caseplanForm" property="currentCaseplan.status" value="IN REVIEW">  
																										<logic:equal name="caseplanForm" property="placementInfoExist" value="N">
																											<html:submit property="submitAction" onclick="return promptForNewCasefile()"><bean:message key="button.addPlacement"/></html:submit>
																										</logic:equal>
																										<logic:notEqual name="caseplanForm" property="placementInfoExist" value="N">
																											<html:submit property="submitAction" onclick="return promptForNewCasefile()"><bean:message key="button.modifyPlacement"/></html:submit>
																										</logic:notEqual>
																									</logic:notEqual>
																								</logic:equal>
																							</logic:equal>
																					
																							<logic:notEqual name="caseplanForm" property="currentCaseplan.status" value="IN REVIEW">  
																								<logic:notEqual name="caseplanForm" property="caseplanExist" value="Y">
																									<logic:equal name="caseplanForm" property="supervisionCategoryId" value="<%=UIConstants.CASEFILE_SUPERVISION_CAT_POST_ADJ_RES%>">
																										<html:submit property="submitAction" onclick="return promptForNewCasefile()"><bean:message key="button.addPlacement"/></html:submit>
																									</logic:equal>
																								</logic:notEqual>
																							</logic:notEqual>
																				
																							<%-- <logic:equal name="caseplanForm" property="caseplanExist" value="Y">
																								<html:submit property="submitAction"><bean:message key="button.generateCaseplan"/></html:submit>
																							</logic:equal> --%>
																								
																					 	</td>
																					 	</td>
																					 	
																					</tr>
																				</jims2:isAllowed>
																			</logic:notEqual>
																		</logic:equal>
																	</logic:equal>	
																</logic:equal>	
		         	  								  			<tr>
		         	  										    	<td align="center">
			         	  										    	<logic:notEqual name="caseplanForm" property="action" value="finish">
			           	  													<html:submit property="submitAction"><bean:message key="button.back"/></html:submit>
								           	  								 	<!-- 													
									  												<jims2:isAllowed requiredFeatures='<%=Features.JCW_CF_CASEPLAN_CP%>'>						  													
																						<html:submit property="submitAction"><bean:message key="button.copyCaseplan"/></html:submit>																		
																					</jims2:isAllowed>											
																				-->
																			<logic:equal name="caseplanForm" property="action" value="copy">
																				<logic:notEmpty name="caseplanForm" property="casefiles">
																					<bean:size id="casefilescount" name="caseplanForm" property="casefiles"/>
																					<logic:notEqual name="casefilescount" value="1">																		
																						<html:submit property="submitAction" onclick="return validateRadios(this, 'twoLane', 'Select a casefile to proceed.')"><bean:message key="button.submit"/></html:submit>
																					</logic:notEqual>
																					<logic:equal name="casefilescount" value="1">	
																						<logic:equal name="caseplanForm" property="found" value="true">
																							<html:submit property="submitAction"><bean:message key="button.submit"/></html:submit>
																						</logic:equal>
																					</logic:equal>
																				</logic:notEmpty>																	
																			</logic:equal>
																			<logic:equal name="caseplanForm" property="action" value="submit">	
																				<logic:notEmpty name="caseplanForm" property="casefiles">																
																					<html:submit property="submitAction" ><bean:message key="button.finish"/></html:submit>
																				</logic:notEmpty>																	
																			</logic:equal>
		             														<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
		             													</logic:notEqual>
			             												<logic:equal name="caseplanForm" property="action" value="finish">
			             													<html:submit property="submitAction"><bean:message key="button.backToCaseplanDetails"/></html:submit>
			             												</logic:equal>
								  								    </td>
								  								</tr>
							  								</table>
<%-- END BUTTON TABLE --%>					  								
															<div class='spacer'></div>
														</td>
													</tr>
												</table>
<%-- END PROFILE CASEPLAN TABS BORDER TABLE --%>										
												<div class='spacer'></div>
											</td>
										</tr>
									</table>
<%-- END PROFILE TABS BORDER TABLE --%> 								
								</td>
							</tr>
						</table>
<%-- END CASEPLAN TABS ALIGNMENT TABLE --%>					
					</td>
				</tr>
			</table>
<%-- END CASEFILE/MASTER TABS TABLE --%>			
		</td>
	</tr>
</table>
<%-- BEGIN CASEFILE TABS TABLE --%>
	</td>
</tr>
</table>
<%-- END DETAIL TABLE --%>
<div class='spacer'></div>
<logic:present name="statusReport">
	<logic:equal name="statusReport" value="confirmFinal">
    	<script type="text/javascript">
     		goNav('/<msp:webapp/>handleGenerateCaseplan.do?submitAction=<bean:message key="button.print"/>&selectedValue=<bean:write name="caseplanForm" property="selectedValue"/>');
    	</script>
  	</logic:equal>
</logic:present>
<%-- BEGIN URL TABLE - the following JS call does NOT display buttons, but a group of hyperlinked menu options --%>
</html:form>
<div class='spacer' align='center'><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>