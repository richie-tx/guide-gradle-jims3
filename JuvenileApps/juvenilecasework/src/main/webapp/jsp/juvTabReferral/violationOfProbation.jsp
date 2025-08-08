<!DOCTYPE HTML>

<%-- To display Violation of Probation (VOP) tab in Juvenile Casework --%>
<%--MODIFICATIONS --%>
<%-- 05/22/2023 NM US 119194 Create JSP  --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="elogic" %>

<%@ page import="naming.Features" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<%--BEGIN HEADER TAG--%>
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta charset="UTF-8" />
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

<title><bean:message key="title.heading"/> - juvTabReferral - violationOfProbation.jsp</title>

<script type='text/javascript' src="/<msp:webapp/>/js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>/js/vopOffenseReferralCreate.js"></script>

</head>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);" topmargin="0" leftmargin="0"> 
<html:form action="/handleJuvenileProfileVOPsAction"  target="content" >

<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|105"> 

<%-- BEGIN HEADING TABLE --%> 
<table width='100%'> 
	<tr> 
		<td align="center" class="header" >Juvenile Casework - Juvenile Profile - Violation of Probation</td> 
	</tr> 
<%-- 	<tr>
		<td class="confirm"><bean:write name="transferredOffenseForm" property="confirmMsg" /></td>
	</tr> --%>
</table> 
<%-- END HEADING TABLE --%> 

<div class='spacer'></div>
<%-- BEGIN INSTRUCTION TABLE --%> 
<table width="98%" border="0">
	<tr>
		<td>
			<ul>
				<li>Select a referral by choosing a radio button, then click Add VOP Details button to add VOP details.</li> 
			</ul>
		</td>
	</tr>
</table>
<%-- END INSTRUCTION TABLE --%> 
<%-- BEGIN JUVENILE HEADER INCLUDE --%> 
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="profileheader"/>
</tiles:insert>
<%-- END JUVENILE HEADER INCLUDE  --%> 
<div class=spacer></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" > 
	<tr> 
<%--tabs start--%> 
		<td valign="top">
			<tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
	        	<tiles:put name="tabid" value="referralstab"/>
	         	<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
			</tiles:insert>	
		</td></tr>
		<tr>  			
		  	<td bgcolor='#33cc66' height="5"></td>
		</tr>
<%--tabs end--%> <tr><td>
			<table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
				<tr>
					<td valign="top" align="center">
						<div class=spacer></div>
  						<table width='98%' border="0" cellpadding="0" cellspacing="0">
  							<tr>
  								<td valign="top">
									<tiles:insert page="/jsp/caseworkCommon/referralTabs.jsp" flush="true">
    								</tiles:insert>
								</td>
  							</tr>
  							<tr>
	  							<td bgcolor='#6699FF' height="5"></td>
	  						</tr>
  						</table>
<%-- BEGIN DETAIL TABLE --%> 
						<table width='98%' border="0" cellpadding="0" cellspacing="1" class="borderTableBlue"> 
			        		<tr> 
			    		  		<td valign="top" align="center"> 
									<div class='spacer'></div>
<%-- BEGIN OF REFERRALS TABLE--%> 
									<table width='98%' border="0" cellpadding="2" cellspacing="3" class="borderTableBlue"> 
					  					<tr> 
											<td class="detailHead">Referrals Requiring VOP Entry</td> 
										</tr> 
					  						<tr bgcolor='#cccccc'> 
					  						<logic:empty name="vopOffenseForm" property="referralsVOP">
					    						<td colspan="4" class="subHead">No Referrals Available for VOP</td> 
					  				  		</logic:empty>
					  				  		</tr> 
					  					<logic:notEmpty name="vopOffenseForm" property="referralsVOP">
								        <tr>
								        	<td> 
								        		<table cellpadding="0" cellspacing="3" width='100%'> 
													<tr bgcolor='#cccccc'> 
														<td></td>
														<td align='left' class="subHead" width='1%' nowrap><bean:message key="prompt.referralNumber" /> 
														</td> 
														<td align='left' class="subHead"><bean:message key="prompt.vop"/>s </td> 
														<td align='left' class="subHead"><bean:message key="prompt.petitionNumber"/> </td>
														<td align='left' class="subHead"><bean:message key="prompt.ref"/> <bean:message key="prompt.date"/></td>		             
													</tr> 
					        						<logic:iterate id="referralsVOP" name="vopOffenseForm" property="referralsVOP" indexId="indexer"> 
						        						
					            						<tr class="<%out.print((indexer.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>" height="100%">
														<logic:empty name="referralsVOP" property="closeDate">
															<td width='1%'>
															<input type="radio" name="selectedRef4VOP" value=<bean:write name='referralsVOP' property='referralNumber'/> styleId="referralId"></td>
																<td><bean:write name="referralsVOP" property="referralNumber"/></td>
					             								<td width='45%'>
					             									<logic:equal name="referralsVOP" property="offensesAvailable" value="true">
						     											<logic:iterate id="offenseList" name="referralsVOP" property="offenses" indexId="index"> 
				    		 												<span title='<bean:write name="offenseList" property="offenseDescription"/>'>
				     															<bean:write name="offenseList" property="offenseCodeId"/>
				     															<logic:notEqual name="referralsVOP" property="offenseCollectionSize" value="<%=index.toString()%>">
				     															,
				     															</logic:notEqual>
				     														</span>
				     													</logic:iterate>
				     												</logic:equal>
					             								</td>
					             							<td><bean:write name="referralsVOP" property="petitionNumber" format="MM/dd/yyyy"/></td>
					          								<td><bean:write name="referralsVOP" property="referralDate" format="MM/dd/yyyy"/></td>
							          					</logic:empty>
							          					</tr>
													</logic:iterate> 
								         		</table> 
								        	</td>
								        	</tr></logic:notEmpty>
								    </table>
								    <table width='98%' border="0" cellpadding="2" cellspacing="3" class="borderTableBlue">
								    	<tr> 
											<td class="detailHead" colspan = "6">Referrals with Prior VOP Entry</td> 
											<td class="detailHead" colspan = "4">New Incoming Charge Details</td> 
										</tr>
										<logic:empty name="vopOffenseForm" property="existingVOPs">
					  					<tr bgcolor='#cccccc'> 
					    					<td colspan="10" class="subHead">No Exisitng VOP Referrals</td> 
					  					</tr> 
					  					</logic:empty> 
					  					<logic:notEmpty name="vopOffenseForm" property="existingVOPs">
										<tr bgcolor='#cccccc'> 
											<td align='left' class="subHead" width='1%' nowrap><bean:message key="prompt.refNo" /></td> 
											<td align='left' class="subHead"><bean:message key="prompt.vopShort"/> <bean:message key="prompt.offenseCode"/></td> 
											<td align='left' class="subHead"><bean:message key="prompt.ref"/> <bean:message key="prompt.date"/></td>	
											<td align='left' class="subHead"><bean:message key="prompt.vopShort"/> <bean:message key="prompt.petitionNumber"/>
											<td align='left' class="subHead"> <bean:message key="prompt.inCountry"/><br /><bean:message key="prompt.referral"/></td>
											<td align='left' class="subHead"><bean:message key="prompt.petitionNumber"/> </td>
											<td align='left' class="subHead"> <bean:message key="prompt.offense"/> <bean:message key="prompt.charge"/></td>
											<td align='left' class="subHead"> <bean:message key="prompt.offense"/> <bean:message key="prompt.charge"/> <bean:message key="prompt.date"/></td>
											<td align='left' class="subHead"> <bean:message key="prompt.adult"/> <bean:message key="prompt.indicator"/></td>
											<td align='left' class="subHead"> <bean:message key="prompt.location"/> <bean:message key="prompt.indicator"/></td>		             
										</tr> 
										
										
										<logic:iterate id="existingVOPs" name="vopOffenseForm" property="existingVOPs" indexId="indexer2">
										<tr class="<%out.print((indexer2.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>" height="100%"> 
											<td><bean:write name="existingVOPs" property="referralNum"/></td>
											<%-- <td><bean:write name="existingVOPs" property="vopOffenseCode"/></td> --%>
										<td><logic:notEmpty name="existingVOPs" property="offenses">
											<logic:iterate id="offenseList" name="existingVOPs" property="offenses" indexId="index"> 
				    		 					<span title='<bean:write name="offenseList" property="shortDescription"/>'>
				     								<bean:write name="offenseList" property="offenseCode"/>
				     									<logic:notEqual name="existingVOPs" property="offenseCollectionSizeJCVOP" value="<%=index.toString()%>">
				     									,
				     									</logic:notEqual>
				     							</span>
				     						</logic:iterate>
				     					</logic:notEmpty>
				     					</td>
											<td><bean:write name="existingVOPs" property="referralDate" format="MM/dd/yyyy"/></td>
											<td><bean:write name="existingVOPs" property="vopOffensePetitionNum"/></td>
											<td><bean:write name="existingVOPs" property="inCCountyOrigPetitionedRefNum"/></td>
											<td><bean:write name="existingVOPs" property="petitionNum"/></td>
											<td><bean:write name="existingVOPs" property="offenseCharge"/></td>
											<td><bean:write name="existingVOPs" property="offenseChargeDate" format="MM/dd/yyyy"/></td>
											<td><bean:write name="existingVOPs" property="adultIndicatorStr"/></td>
											<td><bean:write name="existingVOPs" property="locationIndicator"/></td>
										</logic:iterate>
										</tr>
										</logic:notEmpty>
									 </table>  
									<%-- <html:hidden styleId="selectedRef" name="VOPOffenseForm" property="selectedReferral"/>
									<html:hidden styleId="selectedRef4VOP" name="assignedReferralsForm" property="selectedRef4VOP"/> --%>	
<%-- END OF REFERRALS TABLE--%> 
									<div class='spacer'></div>
<%-- BEGIN BUTTON TABLE --%>
									<table border="0" cellpadding="1" cellspacing="1" align="center"> 
										<tr width='98%'>
											<td align="center">
												<html:button property="button.back" onclick="history.back();"><bean:message key="button.back"></bean:message></html:button>			
												<logic:notEmpty name="vopOffenseForm" property="referralsVOP">
												<jims2:isAllowed requiredFeatures='<%=Features.JCW_CF_VOP_CREATE%>'>
													<html:submit property="submitAction" styleId="addVOPDetailsBtn"><bean:message key="button.vopDetailsAdd" /></html:submit>
												</jims2:isAllowed>
												</logic:notEmpty>
												<input type="button" value="Cancel" onClick="spinner(); goNav('/<msp:webapp/>displayJuvenileMasterInformation.do')">
											</td>
										</tr>
									</table>
<%-- END BUTTON TABLE --%>
									<div class='spacer'></div> 
								</td>
							</tr>
						</table>
<%-- END DETAIL TABLE --%> 						
						<div class='spacer'></div>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<div class='spacer4px'></div>

</html:form> 
<div align=center><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>