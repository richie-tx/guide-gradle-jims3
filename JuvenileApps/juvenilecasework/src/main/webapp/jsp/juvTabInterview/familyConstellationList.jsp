<!DOCTYPE HTML>

<%-- User selects the "Family" tab in the "Interview Info" tab on Juvenile Profile Detail page --%>
<%--MODIFICATIONS --%>
<%-- 09/19/2005	Justin Jose	Create JSP --%>
<%-- 12/11/2006 Hien Rodriguez  ER#37077 Add Tab & Buttons security features  --%>
<%-- 07/17/2009 C Shimek        #61004 added timeout.js  --%>
<%-- 07/10/2012 C Shimek        #73565 added age > 20 check (juvUnder21) to Add/Create buttons --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ page import="naming.Features" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>




<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<msp:nocache />

<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base/>

<%-- Javascript for emulated navigation --%>
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/juvTabInterview/familyConstellationGeneral.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>



<title><bean:message key="title.heading"/> - familyConstellationList.jsp</title>

</head>
<%--END HEADER TAG--%>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin='0' leftmargin="0">
<html:form action="/displayFamilyConstellationDetailsCreate?action=create">

<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|227">

<%-- BEGIN HEADING TABLE --%>
<table width="98%">
 	<tr>
   		<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - <bean:message key="prompt.family"/> <bean:message key="prompt.constellation"/> <bean:message key="prompt.list"/></td>	  	    	 
 	</tr>  	
</table>
<table width="100%">
	<tr>		  
		<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
	</tr>   	  
</table>
<%-- END HEADING TABLE --%>
<%-- BEGIN CREATE MESSAGE TABLE --%> 
<table align="center" width="100%">
	<tr>
		<logic:present name="confirmFamilyCreate">
			<td align="center" class="confirm">
				<bean:message key="prompt.family"/> <bean:message key="prompt.number"/>: <bean:write name="juvenileFamilyForm" property="currentConstellation.familyNumber"/>
				<br>
				<bean:message key="prompt.family"/> <bean:message key="prompt.constellation"/> 
				<bean:message key="prompt.successfully"/> <bean:message key="prompt.created"/>
			</td>
		</logic:present>
	</tr>
</table>
<%-- END CREATE MESSAGE TABLE --%> 
<%-- BEGIN INSTRUCTION TABLE --%> 
<table width="98%" border="0"> 
	<tr> 
		<td>
			<div align="left">Constellation List Section:</div> 
			<ul> 
				<li>Click the Family # hyperlink to view family constellation details</li> 
				
				<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRF_FAM_GI_U%>'>
					<li>Select Create New Constellation button to create a new family constellation.</li>
				</jims2:isAllowed>
				<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRF_FAMILY_U%>'>
					<li>Select the Update Active Constellation button to update the current active constellation.</li>  
				</jims2:isAllowed>
				
				<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRF_FAM_GI_U%>'>
					<li>Select Update Guardians button to change guardians (Note: This will result in a new constellation being created). </li>  
				</jims2:isAllowed> 
			</ul> 
			<div align="left">Current Constellation Section:</div> 
			<ul> 
				<li> Click Member # hyperlink to view member details.</li> 
			</ul>
		</td> 
	</tr> 
</table>  
<%-- END INSTRUCTION TABLE --%>
<%-- BEGIN JUVENILE PROFILE HEADER TABLE --%> 
<table align="center" cellpadding='1' cellspacing='0' border='0' width='100%'> 
	<tr> 
		<td>
			<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
				<tiles:put name="headerType" value="profileheader"/>
			</tiles:insert>
		</td> 
	</tr> 
</table>
<%-- END JUVENILE PROFILE HEADER TABLE --%> 
<div class='spacer'></div>
<%-- BEGIN DETAIL TABLE --%>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td valign='top'>
    	<%-- BEGIN GREEN TABS TABLE --%>
			<table width='100%' border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign='top'>
						<%--tabs start--%>
						<tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
							<tiles:put name="tabid" value="family"/>
							<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
						</tiles:insert>	
						<%--tabs end--%>
					</td>
				</tr>
				<tr>
					<td bgcolor='#33cc66'><img src="/<msp:webapp/>images/spacer.gif" width='5'></td>
				</tr>
			</table>
<%-- END GREEN TABS TABLE --%>
<%-- BEGIN TAB GREEN BORDER TABLE --%>
			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
				<tr>
					<td valign='top' align='center'>
						<%-- BEGIN INTERVIEW INFO TABS OUTER TABLE --%>
						<table width='98%' border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td>
<%-- BEGIN constellation List TABLE --%>
								  <div class='spacer'></div>
								  <table width="100%" border="0" cellpadding="2" cellspacing="0" class='borderTableBlue'> 
										<tr bgcolor='#f0f0f0'> 
										  <td align="left" class="detailHead" colspan="2"><bean:message key="prompt.constellation"/> <bean:message key="prompt.list"/></td> 
										</tr> 
										<tr> 
										  <td align='center'> 
										  	  <table width='100%' cellpadding='2' cellspacing='1'> 
												  <logic:empty name="juvenileFamilyForm" property="constellationList">
												  	<tr bgcolor='#cccccc'> 
															<td colspan="4" class='subHead'>No Constellations Available </td> 
												  	</tr> 
												  </logic:empty>

												  <logic:notEmpty name="juvenileFamilyForm" property="constellationList">
													  <tr bgcolor='#cccccc' class='subHead'> 
															<td width='1%' nowrap='nowrap'><bean:message key="prompt.family"/> #</td> 
															<td><bean:message key="prompt.entryDate"/></td> 
															<td width='1%' ><bean:message key="prompt.active"/></td> 
															<td><bean:message key="prompt.guardian"/> <bean:message key="prompt.name"/>(s)</td> 
													  </tr> 
										  
													  <logic:iterate indexId="index" id="myConstellations" name="juvenileFamilyForm" property="constellationList">
														  <tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>"> 
																<td>
																	<a onclick="spinner();" href="/<msp:webapp/>displayFamilyConstellationDetails.do?submitAction=Details&selectedValue=<bean:write name="myConstellations" property="familyNumber"/>">
																		<bean:write name="myConstellations" property="familyNumber"/></a>
																</td> 
																<td><bean:write name="myConstellations" property="entryDate"/></td> 
																<td><bean:write name="myConstellations" property="activeYesNo"/></td> 
																<td><bean:write name="myConstellations" property="guardianNames"/></td> 
														  </tr> 
													  </logic:iterate>
												  </logic:notEmpty>	  
												</table>
											</td> 
										</tr> 
									</table>
	
									<div class='spacer'></div> 
									<div class='paddedFourPix' align="center">
									
									<input id='juvStatus' type='hidden' value='<bean:write name="juvenileFamilyForm" property="isClosedJuvStatus"/>' >									 			
									<input id='juvHasConstellation' type='hidden' value='<bean:write name="juvenileFamilyForm" property="youthHasConstellation"/>' >	
									
									  <logic:equal name="juvenileProfileHeader" property="juvUnder21" value="true">
									<logic:notEqual name="juvenileProfileHeader" property="status" value="CLOSED">
								  			<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRF_FAM_GI_U%>'>
										 		<html:submit property="submitAction" styleId="createNewConstellation"><bean:message key="button.createNewConstellation"></bean:message></html:submit>
											</jims2:isAllowed>
	  								 	<logic:notEmpty name="juvenileFamilyForm" property="constellationList">
	  								 		<logic:equal name="juvenileFamilyForm" property="hasActiveConstellation" value="true">
	  										 	<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRF_FAMILY_U%>'>	  										 		
	  													<input align='center' type="button" value="<bean:message key='button.updateActiveConstellation'/>" id="updateActiveCons" data-href="/<msp:webapp/>displayFamilyConstellationDetailsUpdate.do?submitAction=<bean:message key='button.updateActiveConstellation'/>&action=update">
	  											 </jims2:isAllowed>
	  											 <jims2:isAllowed requiredFeatures='<%=Features.JCW_PRF_FAM_GI_U%>'>
	    												<html:submit property="submitAction" styleId="confirmUpdateGuardians"><bean:message key="button.updateGuardians"></bean:message></html:submit>
	  										 	</jims2:isAllowed>
	  										 	 <jims2:isAllowed requiredFeatures='<%=Features.JCW_PRF_FAMILY_U%>'>
	    											<input align='center' type="button" value="<bean:message key='button.dynamics'/>" id="dynamics" data-href="/<msp:webapp/>displayManageFamilyConstellationDynamics.do?submitAction=<bean:message key='button.go'/>&action=update">
	  										 	</jims2:isAllowed>
	  								 		</logic:equal>
	  								 	</logic:notEmpty>
	  								</logic:notEqual> 
	  								 	<logic:equal name="juvenileProfileHeader" property="status" value="CLOSED">
											<jims2:isAllowed requiredFeatures='<%=Features.JCW_JP_ENABLEMASTERTAB_CLOSEDJUV%>'>
											
	  								 	<logic:notEmpty name="juvenileFamilyForm" property="constellationList">
	  								 		<logic:equal name="juvenileFamilyForm" property="hasActiveConstellation" value="true">
	  								 		<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRF_FAM_GI_U%>'>
										 		<html:submit property="submitAction" styleId="createNewConstellation"><bean:message key="button.createNewConstellation"></bean:message></html:submit>
											</jims2:isAllowed>
	  										 	<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRF_FAMILY_U%>'>
	  												<input align='center' type="button" value="<bean:message key='button.updateActiveConstellation'/>" id="updateActiveCons" data-href="/<msp:webapp/>displayFamilyConstellationDetailsUpdate.do?submitAction=<bean:message key='button.updateActiveConstellation'/>&action=update">
	  											 </jims2:isAllowed>
	  											 <jims2:isAllowed requiredFeatures='<%=Features.JCW_PRF_FAM_GI_U%>'>
	    											<html:submit property="submitAction" styleId="confirmUpdateGuardians"><bean:message key="button.updateGuardians"></bean:message></html:submit>
	  										 	</jims2:isAllowed>
	  										 	 <jims2:isAllowed requiredFeatures='<%=Features.JCW_PRF_FAMILY_U%>'>
	    											<input align='center' type="button" value="<bean:message key='button.dynamics'/>" id="dynamics" data-href="/<msp:webapp/>displayManageFamilyConstellationDynamics.do?submitAction=<bean:message key='button.go'/>&action=update">
	  										 	</jims2:isAllowed>
	  								 		</logic:equal>
	  								 		</logic:notEmpty>
											</jims2:isAllowed>
											</logic:equal>
	  								  </logic:equal>
							        </div>
								  <%-- End Constellation List TABLE --%> 
                  								  
   								<logic:notEmpty name="juvenileFamilyForm" property="currentConstellation">
									  <%-- BEGIN Current Constellation TABLE --%> 
	 								  <div class='spacer'></div> 
									  <table width='100%' cellspacing='2' cellpadding='0' class='borderTableBlue'> 
											<tr> 
											  <td width="100%" valign='top' class="detailHead"><bean:message key="prompt.current"/> <bean:message key="prompt.constellation"/> - <bean:message key="prompt.family"/> #
											  	<bean:write name="juvenileFamilyForm" property="currentConstellation.familyNumber"/>
											  </td> 
											</tr> 
													
											<tr> 
											  <td colspan="2"> 
												  <table width='100%' cellspacing='1'> 
											  	      <logic:empty name="juvenileFamilyForm" property="currentConstellation.memberList">
													  	<tr bgcolor='#cccccc'> 
																<td colspan="6" class='subHead'>No Members Available</td> 
													  	</tr> 
													  </logic:empty>
	
													  <logic:notEmpty name="juvenileFamilyForm" property="currentConstellation.memberList">
														 <nested:nest property="currentConstellation">
														    <% int finCtr=0; %>
															<nested:iterate indexId="index" id="memberList" property="memberList">
															  <% finCtr++; %>
															  <tr>
															    <td>
															       <table width="100%"  cellspacing='1' cellpadding='2' class='borderTableGrey'>															          
																	  <tr class='formDeLabel'> 
																		<td width="10%" nowrap='nowrap'><bean:message key="prompt.member"/> #</td> 
																		<td width="30%"><bean:message key="prompt.name"/></td> 
																		<td width="20%"><bean:message key="prompt.relationship"/></td> 
																		<td width="5%"><bean:message key="prompt.guardian"/></td> 
																		<%-- <td width="5%"><bean:message key="prompt.deceased"/></td> 
																		<td width="5%"><bean:message key="prompt.incarcerated"/></td>  --%>
																		<td><bean:message key="prompt.SSN"/></td> 
																		<td width="10%"><bean:message key="prompt.oln"/></td>
																		<td width="5%"><bean:message key="prompt.dob"/></td>
																		<td width="5%"><bean:message key="prompt.dh"/></td>
																		<td width="5%"><bean:message key="prompt.visit"/></td>
																      </tr>
												                      <tr class="formDe"> 
																			<td width="10%">
																				<logic:notEmpty name="memberList" property="suspiciousMember">
																					<logic:notEqual name="memberList" property="suspiciousMember" value="">
		 																				<bean:message key="prompt.suspiciousMember"/>
																					</logic:notEqual>
																				</logic:notEmpty>
																				<a onclick="spinner();" href="/<msp:webapp/>displayFamilyMemberDetails.do?submitAction=Details&selectedValue=<bean:write name='memberList' property='memberNumber'/>&clearFamAction=true">
																					<bean:write name="memberList" property="memberNumber"/></a>
																			</td>
																			<td>
																				<bean:write name="memberList" property="memberName.formattedName"/>
																				<logic:equal name="memberList" property="primaryContact" value="true"> <img src='../../images/starBlueIcon.gif'></logic:equal>
																			</td> 
																			<td><bean:write name="memberList" property="relationshipToJuv"/></td> 
																			<td><bean:write name="memberList" property="guardianYesNo"/></td> 
																			<%-- <td><bean:write name="memberList" property="deceasedYesNo"/></td> 
																			<td><bean:write name="memberList" property="incarceratedYesNo"/></td>
																			 --%>
																			 <td nowrap="nowrap"><bean:write name="memberList" property="completeSSN"/></td>
																			 
																			 <td nowrap="nowrap">
																			 
																				 <logic:notEqual name="memberList" property="driverLicenseNum" value="">																		 																			  
																				 	<bean:write name="memberList" property="driverLicenseNum"/>   <bean:write name="memberList" property="driverLicenseState"/>	 
																				 </logic:notEqual>
																				 <logic:equal name="memberList" property="driverLicenseNum" value="">
																				 	<bean:write name="memberList" property="stateIssuedIdNum"/>   <bean:write name="memberList" property="stateIssuedIdState"/>
																				 </logic:equal>
																			 </td>	
																			 
																			 <td class="formDe"><bean:write name="memberList" property="dateOfBirth" /></td>
																			 
																			 
																			 <td>
																				<logic:equal name="memberList" property="detentionHearing" value="true"> YES</logic:equal>
																				<logic:notEqual name="memberList" property="detentionHearing" value="true"> NO</logic:notEqual>
																			</td>
																			<td>
																				<logic:equal name="memberList" property="detentionVisitation" value="true"> YES</logic:equal>
																				<logic:notEqual name="memberList" property="detentionVisitation" value="true"> NO</logic:notEqual>
																			</td>																		 
																			
																	  </tr>
																  <logic:equal name="memberList" property="guardianYesNo" value="YES" >
																  	<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_MAS_BEN_INS_FAMFIN%>'>
																	  <tr> 
											                            <td valign="top" class="detailHead" colspan="9"><a id="showHideMultyRow" data-spanname="guardianFinancialInfo<%out.print(finCtr);%>" data-format="row" data-path="/<msp:webapp/>"><img border='0' src="/<msp:webapp/>images/expand.gif" name="guardianFinancialInfo<%out.print(finCtr); %>"></a>&nbsp;<bean:message key="prompt.family"/> <bean:message key="prompt.financialInformation"/> of <bean:message key="prompt.guardian"/></td> 
											                          </tr>
											                          <tr class="hidden" id="guardianFinancialInfo<%out.print(finCtr); %>Span">
											               			    <td valign="top" colspan="9">
											                               <tiles:insert page="../caseworkCommon/familyFinancialInfo.jsp" flush="false">
											                                   <tiles:put name="familyFinancialInfo" beanName="juvenileFamilyForm" beanProperty="currentGuardian" />
											                               </tiles:insert> 
											                            </td>
											                          </tr>
											                         </jims2:isAllowed>
											                      </logic:equal>
											                   </table>
											                </td>
											             </tr> 
													   </nested:iterate>
													</nested:nest>
												</logic:notEmpty>
											  </table>
													<%--END Current Constellation TABLE --%> 
										  
								<%-- END INTERVIEW INFO TABS OUTER TABLE --%>
								<div class='spacer'></div> 
                    <table width='100%' cellspacing="0" cellpadding="2" border="0" class="borderTableBlue"> 
                      <tr> 
                        <td valign="top" class="detailHead"><a href="javascript:showHideMulti('dynamics', 'dchar', 1,'/<msp:webapp/>');" ><img border='0' src="/<msp:webapp/>images/expand.gif" name="dynamics"></a>&nbsp;<bean:message key="prompt.family"/> <bean:message key="prompt.constellation"/> <bean:message key="prompt.dynamics"/></td> 
                      </tr> 
                      
                     <tr id="dchar0" class='hidden'> 
                        <td>
                          <table width='100%' bgcolor="#cccccc" cellspacing="1"> 
                       <logic:empty name="juvenileFamilyForm" property="currentConstellation.traitList">
                         		 <tr bgcolor="#f0f0f0"> 
                              		<td  colspan="5" class="subhead">No Traits Listed</td> 
                         		</tr>
                         	</logic:empty>
                         	<%int RecordCounter = 0;
		   										String bgcolor = "";%>
                         	<logic:notEmpty name="juvenileFamilyForm" property="currentConstellation.traitList">
                            <tr bgcolor="#f0f0f0"> 
                             <td width="10%" class="subhead"><bean:message key="prompt.entryDate"/></td> 
                              <td class="subhead"><bean:message key="prompt.dynamic"/></td> 
                              <td class="subhead"><bean:message key="prompt.dynamic"/> <bean:message key="prompt.level"/></td> 
                              <td class="subhead"><bean:message key="prompt.dynamic"/> <bean:message key="prompt.status"/></td> 
                              <td class="subhead"><bean:message key="prompt.dynamic"/> <bean:message key="prompt.comments"/> </td> 
                             </tr> 
                           
                            <logic:iterate id="traitsList" name="juvenileFamilyForm" property="currentConstellation.traitList">
        								  <tr class=<%RecordCounter++;
        										bgcolor = (RecordCounter % 2 == 1) ? "normalRow" : "alternateRow";
        										out.print(bgcolor);%>
													> 
                            <td valign="top"><bean:write name="traitsList" property="entryDate"/></td> 
                            <td valign="top"><bean:write name="traitsList" property="traitDesc" /></td>
           									<td valign="top"><bean:write name="traitsList" property="traitLevel" /></td>
           									<td valign="top"><bean:write name="traitsList" property="traitStatus" /></td>
           									<td><bean:write name="traitsList" property="traitComments" /></td>
                          </tr> 
                        </logic:iterate>
                       </logic:notEmpty>
                      </table>
                      </td></tr></table>
                      </td> 
	           					        </tr> 
	           				        </table>
								</logic:notEmpty>
</html:form> 

								<%-- BEGIN BUTTON TABLE --%> 
								<table border="0" align='center' >
									<tr>
								    <td align="center">
								   		<html:button property="org.apache.struts.taglib.html.BUTTON" styleId="conListBack"><bean:message key="button.back"></bean:message></html:button>
								    </td>
										<html:form action="/displayJuvenileMasterInformation.do">
										<td align="center"><html:submit><bean:message key="button.cancel"></bean:message></html:submit></td></html:form>
									</tr>
								</table>
								<%-- END BUTTON TABLE --%> 
								<div class='spacer'></div>
							</td> 
				  		</tr> 
				  	</table>
						<%-- END TAB GREEN BORDER TABLE --%> 
					</td> 
				</tr> 
			</table>
		</td> 
	</tr> 
</table>
<%-- END DETAIL TABLE --%> 

<html:hidden name="juvenileFamilyForm" property="secondaryAction" value="" />

<div class='spacer'></div>

<div align='center'><script type="text/javascript">renderBackToTop()</script></div></body>

</html:html>

