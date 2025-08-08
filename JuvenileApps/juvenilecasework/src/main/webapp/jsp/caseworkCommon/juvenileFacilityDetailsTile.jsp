<!DOCTYPE HTML>
<%-- User selects the Disposition tab --%>
<%--MODIFICATIONS --%>
<%-- 08/09/2007	Uma Gopinath Create JSP --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ page import="naming.Features" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>

<!--Juvenile Header Information starts  -->
<tiles:importAttribute name="detailsForm"/>  
	<table width="100%" cellpadding="2" cellspacing="1" border='0' class="panel panel-default darkgray" >
		<tr cellspacing="0">
			<td class='formDeLabel' class='paddedFourPix' colspan='12' width='1%' nowrap='nowrap' cellspacing="0" ><div style="text-align:left;float:left;">Juvenile Information</div>
				<logic:equal name="detailsForm" property="action" value="confirm"> 
					<div style="text-align:right;float:right;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="mailto:Data.corrections@hcjpd.hctx.net">Email Data Correction</a></div>
				</logic:equal>
				<logic:equal name="detailsForm" property="action" value="modifyAdmitView"> 
					<logic:notEqual name="detailsForm" property="hideFacilityTraitsLink" value="true"> 
						<div style="text-align:right;float:right;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:newCustomWindow('/<msp:webapp/>displayJuvenileFacilityTraitsSearch.do?submitAction=populateTraits&modifyAdmit=true&juvNum=<bean:write name="detailsForm" property="juvenileNum"/>&supervisionNum=<bean:write name="detailsForm" property="bookingSupervisionNum"/>','traits',1000,800)">Facility Traits</a></div>
					</logic:notEqual>
				</logic:equal>
				
				<logic:equal name="detailsForm" property="action" value="releaseView"> 
					<logic:notEqual name="detailsForm" property="hideFacilityTraitsLink" value="true"> 
						<div style="text-align:right;float:right;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:newCustomWindow('/<msp:webapp/>displayJuvenileFacilityTraitsSearch.do?submitAction=populateTraits&releaseView=true&juvNum=<bean:write name="detailsForm" property="juvenileNum"/>&supervisionNum=<bean:write name="detailsForm" property="bookingSupervisionNum"/>','traits',1000,800)">Facility Traits</a></div>
					</logic:notEqual>
				</logic:equal>
				
				<logic:notEqual name="detailsForm" property="action" value="modifyAdmitView"> 
				<logic:notEqual name="detailsForm" property="action" value="releaseView">
					<logic:notEqual name="detailsForm" property="hideFacilityTraitsLink" value="true"> 
						<div style="text-align:right;float:right;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:newCustomWindow('/<msp:webapp/>displayJuvenileFacilityTraitsSearch.do?submitAction=populateTraits&admitView=true&juvNum=<bean:write name="detailsForm" property="juvenileNum"/>&supervisionNum=<bean:write name="detailsForm" property="bookingSupervisionNum"/>','traits',1000,800)">Facility Traits</a></div>
					</logic:notEqual>
				</logic:notEqual>
				</logic:notEqual>
			</td>
		</tr>
	<tr>
			<td class='formDeLabel' colspan= '1' valign='top' width='4%' nowrap><bean:message key="prompt.juvenile#"/></td>
			<td class='formDe' colspan='5' ><logic:equal name="detailsForm" property="restrictedAccessTrait" value="true"><font color="red"><bean:write name="detailsForm" property="juvenileNum"/></font></logic:equal>
			<logic:notEqual name="detailsForm" property="restrictedAccessTrait" value="true"><bean:write name="detailsForm" property="juvenileNum"/></logic:notEqual></td>
			<td class='formDeLabel' colspan= '1' width='4%' nowrap ><bean:message key="prompt.name"/></td>
			<td class='formDe' colspan='5'>
				<bean:write name="detailsForm" property="profileDetail.formattedName"/> <logic:equal name="detailsForm" property="restrictedAccessTrait" value="true"><span title='RESTRICTED ACCESS'> <font color="red"><b>(RESTRICTED)</b></font></span></logic:equal>
				&nbsp;
				<logic:equal name="juvenileBriefingDetailsForm" property="traitTypeId" value="MER">
					<span style="color:#8417A0; font-weight: bold;" title="<bean:write name="juvenileBriefingDetailsForm" property="traitTypeDescription"/>">(M)</span>
				</logic:equal>
			</td>
		</tr>
		<tr>
			<td class='formDeLabel' colspan='1' ><bean:message key="prompt.dob"/></td>
			<td class='formDe' colspan='1' width='10%'><bean:write name="detailsForm" property="profileDetail.dateOfBirth" formatKey="date.format.mmddyyyy"/></td>
			<td class='formDeLabel' colspan='1' width='1%' nowrap><bean:message key="prompt.age"/></td>
			<td class='formDe' colspan='1' width='10%'><bean:write name="detailsForm" property="age"/></td>
			<td class='formDeLabel' colspan='1' width='1%' nowrap><bean:message key="prompt.sex"/></td>
			<td class='formDe' colspan='1' width='15%'><bean:write name="detailsForm" property="profileDetail.sex"/></td>
			<td class='formDeLabel' colspan='1' nowrap><bean:message key="prompt.primaryLanguage"/></td>
			<td class='formDe' colspan='1' width='15%'><bean:write name="detailsForm" property="primaryLanguageDesc"/></td>
			<td class='formDeLabel' colspan='1'  valign='top' width='1%' nowrap><bean:message key="prompt.ethnicity"/></td>
			<td class='formDe' colspan='1' width='20%'><bean:write name="detailsForm" property="ethnicity"/></td>
			<td class='formDeLabel' colspan='1' width='1%' nowrap><bean:message key="prompt.race"/></td>
			<td class='formDe' width='29%'><bean:write name="detailsForm" property="profileDetail.race"/></td>
		</tr>
	</table>
						
	<div class='spacer'></div>
	<div class='spacer'></div>
	<!--Guardian Information starts  -->
			
	<table width="100%" cellpadding="0" cellspacing="1" class='borderTableBlue' align="center">
		<tr class='facDetailHead'>
			<td colspan='4' align='left' class='paddedFourPix'>Guardian Information
				<logic:empty name="detailsForm" property="guardians">
			 		(No Guardian Information)
				</logic:empty>
			</td>
		</tr>
		<logic:notEmpty name="detailsForm" property="guardians">
			<tr>
				<td>
					<table width="100%" cellpadding="2" cellspacing="1" class='borderTableBlue'>
						<tr class='formDeLabel'>
							<td></td>
							<td></td>
							<td><bean:message key="prompt.name"/></td>
							<td><bean:message key="prompt.relationship"/></td>
							<td nowrap="nowrap"><bean:message key="prompt.oln"/></td>
							<td><bean:message key="prompt.dob"/></td>
							<td><bean:message key="prompt.dh"/></td>
							<td><bean:message key="prompt.visit"/></td>
						</tr>
									 
						<logic:iterate name="detailsForm" property="guardians" id="juvGuardiansIter" indexId="indexer">      			
							<tr class="<% out.print( (indexer.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>" height="100%">
							   <td>
									<logic:equal  name="juvGuardiansIter" property="inHomeStatus" value="true">
										<img title='home' src='/<msp:webapp/>images/home-s.jpg' />
									</logic:equal>
								</td>
								<td>
									<logic:equal  name="juvGuardiansIter" property="primaryContact" value="true">
										<img title='primary contact' src='/<msp:webapp/>images/starBlueIcon.gif' />
									</logic:equal>
								</td>
								<td>
									<bean:write name="juvGuardiansIter" property="nameOfPerson.formattedName"/>
								</td>
								<td><bean:write name="juvGuardiansIter" property="relationshipType"/></td>
								<td>
									 <logic:notEqual name="juvGuardiansIter" property="driverLicenceNumber" value="">																		 																			  
											<bean:write name="juvGuardiansIter" property="driverLicenceNumber"/><bean:write name="juvGuardiansIter" property="driverLicenseState"/>	 
									 </logic:notEqual>
									 <logic:equal name="juvGuardiansIter" property="driverLicenceNumber" value="">
										 	<bean:write name="juvGuardiansIter" property="stateIssuedIdNum"/><bean:write name="juvGuardiansIter" property="stateIssuedIdState"/>
									 </logic:equal>
								</td>
								<td><bean:write name="juvGuardiansIter" property="dob"/></td>
								<td>
									<logic:equal name="juvGuardiansIter" property="detentionHearing" value="true"> YES</logic:equal>
									<logic:notEqual name="juvGuardiansIter" property="detentionHearing" value="true"> NO</logic:notEqual>
								</td>
								<td>
									<logic:equal name="juvGuardiansIter" property="visit" value="true"> YES</logic:equal>
									<logic:notEqual name="juvGuardiansIter" property="visit" value="true"> NO</logic:notEqual>
								</td>	
							</tr>
						</logic:iterate>
					</table>
				</td>
			</tr>
		</logic:notEmpty>
	</table>
		
			
			
  		
   
	
            					

