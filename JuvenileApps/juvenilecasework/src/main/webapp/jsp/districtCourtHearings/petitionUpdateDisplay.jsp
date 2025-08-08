<!DOCTYPE HTML>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ page import="org.apache.struts.action.Action" %> 
<%@ page import="org.apache.struts.action.ActionMessages" %>
<%@ page import="org.apache.struts.action.ActionErrors" %>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ page import="naming.Features" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>



<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/> 
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<meta http-equiv="x-UA-Compatible" content="IE=edge, chrome=1"/>

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/districtCourtHearings/petitionUpdateDisplay.js"></script>

<title><bean:message key="title.heading" />/petitionUpdateDisplay.jsp</title>

</head>
<%--END HEAD TAG--%>
<%--BEGIN BODY TAG--%>
<body>
	<html:form action="/submitJuvenileDistrictCourtPetitionUpdate" target="content" styleId="courtHearingForm">
		<!-- HELP FILE -->
		<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|264">

		<table width='100%'>
			<tr>
				<td align="center"><h2>Process Juvenile Court Hearings<br/><br/>Petition Update</h2></td>
			</tr>
		</table>
		 <!-- BEGIN Error Message Table -->
		 <logic:messagesPresent message="true"> 
			<table width="100%">
				<tr>		  
					<td align="center" class="messageAlert"><html:messages id="message" message="true"><bean:write name="message"/></html:messages></td>		  
				</tr>   	  
			</table>
		</logic:messagesPresent> 
		
		<!-- BEGIN ERROR TABLE -->
		<table width="100%">
			<tr>
				<td align="center" class="errorAlert"><html:errors></html:errors></td>
			</tr>
		</table>
	<!-- END ERROR TABLE -->
  
  <!-- END Error Message Table -->
		<br />
		<table width="98%" border="0">
			<tr>
				<td>
					<ul>
						<li>Only the most recent petition record can be modified.</li>
						<li>Subpoenas can be re-generated for an existing Initial Setting record.</li>
						<li>Subpoenas will be generated in .pdf format.  Physical print must be initiated from pdf pop-up.</li>
					</ul>
				</td>
			</tr>
		</table>
		<%-- END INSTRUCTION TABLE --%>
	<!-- Juvenile Information  starts-->
		<table width="98%" border="0" cellpadding="0" cellspacing="0" align="center" class="borderTableBlue" colspan="5">
			<tr>
				<td>
					<table width='100%' cellpadding="2" cellspacing="1">
						<tr>
							<td class='formDeLabel' width="5%" nowrap="nowrap"><bean:message key="prompt.juvNo"/></td>
							<td class='formDeLabel'  width="5%" nowrap="nowrap"><bean:message key="prompt.refNo"/></td>
							<td class='formDeLabel'  width="40%"><bean:message key="prompt.name"/></td>
							<td class='formDeLabel' width="10%" nowrap="nowrap"><bean:message key="prompt.DOB"/></td>
							<td class='formDeLabel'  width="1%" nowrap="nowrap"><bean:message key="prompt.verify"/></td>
							<td class='formDeLabel'  width="5%" nowrap="nowrap"><bean:message key="prompt.age"/></td>
						</tr>
						<tr>
							<td class='formDe' ><bean:write name="courtHearingForm" property="profileDetail.juvenileNum"/></td>
							<td class='formDe' ><bean:write name="courtHearingForm" property="referralNumber"/></td>
							<td class='formDe' nowrap="nowrap" ><bean:write name="courtHearingForm" property="juvenileName"/></td>
							<td class='formDe' ><bean:write name="courtHearingForm" property="profileDetail.dateOfBirth" formatKey="date.format.mmddyyyy"/></td>
							<td class='formDe'><logic:equal name="courtHearingForm" property="profileDetail.verifiedDOB" value="true"><span title="Yes">Y</span></logic:equal><logic:equal name="courtHearingForm" property="profileDetail.verifiedDOB" value="false"><span title="No">N</span></logic:equal></td>
							<td class='formDe' ><bean:write name="courtHearingForm" property="juvAge"/></td>	
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<!-- Juvenile Information ends-->
		<br/>
		
		<table width="98%" border="0" cellpadding="0" cellspacing="0" align="center" class="borderTableBlue">          
		 	<tr>
				<td>
			  		<table width='100%' >
		 				<tr class="crtDetailHead">   
		    				<td>   
								<bean:message key="prompt.2.diamond"/><bean:message key="prompt.status"/>
		          			</td>
		          			<td>   
								<bean:message key="prompt.2.diamond"/><bean:message key="prompt.filingDate"/>
		          			</td>
		          			<td> 
								<bean:message key="prompt.2.diamond"/><bean:message key="prompt.type"/>
		          			</td>
		          			<td> 
								<bean:message key="prompt.2.diamond"/><bean:message key="prompt.petitionNumber"/>
		          			</td>
			          		 <td>
		  						<bean:message key="prompt.amendmentNumber"/>
			          		</td>
			          		<td>
		  						<bean:message key="prompt.amendmentDate"/>
			          		</td>
			          		 <td>
		  						<bean:message key="prompt.2.diamond"/><bean:message key="prompt.allegation"/>
			          		</td>
							<td> 
								<center><bean:message key="prompt.subpoenas"/><br/>J-F-M-O</center>
		          			</td>
						</tr>
			    		<tr>
			    			<td class='formDe' width="5%" valign="top">
			    				<html:select name="courtHearingForm" property="petitionStatus" styleId="petitionStatus">
									<html:option key="select.generic" value="" />
									<html:optionsCollection property="petitionStatuses" value="code" label="descriptionWithCode"/> 				
								</html:select>
			    			</td>
			    			<td class='formDe' valign="top"><html:text name="courtHearingForm" property="filingDate" styleId="filingDate"  maxlength="10" size="10"/></td>
							<td class='formDe' valign="top">
								<html:select name="courtHearingForm" property="petitionType" styleId="petitionType">
									<html:option key="select.generic" value="" />
									<html:optionsCollection property="petitionTypes" value="code" label="descriptionWithCode"/> 				
								</html:select>
							</td>
							<td class='formDe' valign="top"><html:text name="courtHearingForm" property="petitionNumber" styleId="petitionNumber"  maxlength="10" size="10"/></td>
							<td class='formDe' valign="top">
			    				<html:select name="courtHearingForm" property="petitionAmendment" styleId="petitionAmendment">
									<html:option key="select.generic" value="" />
									<html:optionsCollection property="petitionAmendments" value="code" label="description"/> 				
								</html:select>
			    			</td>
			    			<td class='formDe' valign="top"><html:text name="courtHearingForm" property="amendmentDate" styleId="amendmentDate"  maxlength="10" size="10"/></td>
			    			<td class='formDe'>
								<html:text name="courtHearingForm" property="petitionAllegation" styleId="allegation"  maxlength="6" size="6"/>  <br/><br/>
								<html:button property="submitAction" styleId="validateOffenseBtn"><bean:message key="button.validateOffense"/></html:button> <br/>
								<html:button property="submitAction" styleId="searchForOffenseBtn"><bean:message key="button.searchForOffense"/></html:button>
								<input type="hidden" name="validateMsg" value="<bean:write name="courtHearingForm" property="validateMsg" />"  id="valOffMsgId" />
							</td>
							<html:hidden styleId="isFatherIncarceratedId" name="courtHearingForm" property="isFatherIncarcerated"/>
							<html:hidden styleId="isMotherIncarceratedId" name="courtHearingForm" property="isMotherIncarcerated"/>
							<html:hidden styleId="isFatherDeceasedId" name="courtHearingForm" property="isFatherDeceased"/>
							<html:hidden styleId="isMotherDeceasedId" name="courtHearingForm" property="isMotherDeceased"/>
							<td class='formDe'  nowrap="nowrap" valign="top">
								<logic:iterate indexId="idx" id="subpoenasToBePrintedIdx" name="courtHearingForm" property="subpoenasToBePrinted">
								<logic:equal name="subpoenasToBePrintedIdx" property="code" value="F">
									<logic:equal name="courtHearingForm" property="isFatherDeceased" value="Y">
									<input type="radio" name="selectedSubpoenasToBePrinted" disabled="true" id="<bean:write name="subpoenasToBePrintedIdx" property="code"/>" value="<bean:write name="subpoenasToBePrintedIdx" property="code"/>"><span title="Deceased"><bean:write name="subpoenasToBePrintedIdx" property="description"/></span></input>
									</logic:equal>
									<logic:notEqual name="courtHearingForm" property="isFatherDeceased" value="Y">
									<logic:equal name="courtHearingForm" property="isFatherIncarcerated" value="Y">
									<input type="radio" name="selectedSubpoenasToBePrinted" disabled="true" id="<bean:write name="subpoenasToBePrintedIdx" property="code"/>" value="<bean:write name="subpoenasToBePrintedIdx" property="code"/>"><span title="Incarcerated"><bean:write name="subpoenasToBePrintedIdx" property="description"/></span></input>
									</logic:equal>
									<logic:notEqual name="courtHearingForm" property="isFatherIncarcerated" value="Y">
									<input type="radio" name="selectedSubpoenasToBePrinted" id="<bean:write name="subpoenasToBePrintedIdx" property="code"/>" value="<bean:write name="subpoenasToBePrintedIdx" property="code"/>"><bean:write name="subpoenasToBePrintedIdx" property="description"/></input>
									</logic:notEqual>
									</logic:notEqual>
								</logic:equal>
								<logic:equal name="subpoenasToBePrintedIdx" property="code" value="M">
									<logic:equal name="courtHearingForm" property="isMotherDeceased" value="Y">
									<input type="radio" name="selectedSubpoenasToBePrinted" disabled="true"  id="<bean:write name="subpoenasToBePrintedIdx" property="code"/>" value="<bean:write name="subpoenasToBePrintedIdx" property="code"/>"><span title="Deceased"><bean:write name="subpoenasToBePrintedIdx" property="description" /></span></input>
									</logic:equal>
									<logic:notEqual name="courtHearingForm" property="isMotherDeceased" value="Y">
									<logic:equal name="courtHearingForm" property="isMotherIncarcerated" value="Y">
									<input type="radio" name="selectedSubpoenasToBePrinted" disabled="true"  id="<bean:write name="subpoenasToBePrintedIdx" property="code"/>" value="<bean:write name="subpoenasToBePrintedIdx" property="code"/>"><span title="Incarcerated"><bean:write name="subpoenasToBePrintedIdx" property="description" /></span></input>
									</logic:equal>
									<logic:notEqual name="courtHearingForm" property="isMotherIncarcerated" value="Y">
									<input type="radio" name="selectedSubpoenasToBePrinted" id="<bean:write name="subpoenasToBePrintedIdx" property="code"/>" value="<bean:write name="subpoenasToBePrintedIdx" property="code"/>"><bean:write name="subpoenasToBePrintedIdx" property="description"/></input>
									</logic:notEqual>
									</logic:notEqual>
								</logic:equal>
								<logic:equal name="subpoenasToBePrintedIdx" property="code" value="J">
									<input type="radio" name="selectedSubpoenasToBePrinted" id="<bean:write name="subpoenasToBePrintedIdx" property="code"/>" value="<bean:write name="subpoenasToBePrintedIdx" property="code"/>"><bean:write name="subpoenasToBePrintedIdx" property="description"/></input>
								</logic:equal>
								<logic:equal name="subpoenasToBePrintedIdx" property="code" value="O">
									<input type="radio" name="selectedSubpoenasToBePrinted" id="<bean:write name="subpoenasToBePrintedIdx" property="code"/>" value="<bean:write name="subpoenasToBePrintedIdx" property="code"/>"><bean:write name="subpoenasToBePrintedIdx" property="description"/></input>
								</logic:equal>
								</logic:iterate> 
							</td>
						</tr>
			  	 	</table>
				</td>
			</tr>	
		</table>
		<br/>
		<table width="98%" border="0" cellpadding="0" cellspacing="0" align="center" class="borderTableBlue">
			<tr>
				<td>
					<table width='100%' cellpadding="2" cellspacing="1">
						<tr>
							<td class='formDeLabel' width="5%" nowrap="nowrap"><bean:message key="prompt.cert" /></td>
							<td class='formDe'><html:text name="courtHearingForm" property="cert" styleId="cert" maxlength="1" size="1" /></td>
							<td class='formDeLabel' width="5%" nowrap><bean:message key="prompt.2.diamond" /><bean:message key="prompt.plaintiff" /></td>
							<td class='formDe'><html:text name="courtHearingForm" property="plaintiff" styleId="plaintiff" maxlength="50" size="50" /></td>
							<td class='formDeLabel' width="5%" nowrap="nowrap"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.preparationDate" /></td>
							<td class='formDe'><html:text name="courtHearingForm" property="preparationDate" styleId="preparationDate" maxlength="10" size="10" /></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<!-- buttons -->
		<table width="100%" border="0">
			<tr>
				<td align="center">
					<jims2:isAllowed requiredFeatures='<%=Features.JCW_DELETE_PETITION%>'>
						<html:button property="submitAction" styleId="deletePetitionBtn"><bean:message key="button.deletePetition" /></html:button>
					</jims2:isAllowed> 
					<html:button property="submitAction" styleId="submitBtn"><bean:message key="button.submit" /></html:button> 
					<input id="printSubpoenasBtn" type="button" name="generateFinal"  value="<bean:message key='button.printSubpoenas'/>"/>
			</tr>
			<tr>
				<td align="center">
					<html:button property="submitAction" styleId="courtMainMenuBtn"><bean:message key="button.courtMainMenu"></bean:message></html:button>
					<html:button property="submitAction" styleId="back"><bean:message key="button.back"></bean:message></html:button>
					<html:button property="submitAction" styleId="submitReferralInquiry"><bean:message key="button.referralSummary"></bean:message></html:button>
					<html:button property="submitAction" styleId="courtActivity"><bean:message key="button.courtActivityByYouth2"></bean:message></html:button>					
				</td>				
			</tr>
		</table>

		<!-- html hidden fields starts -->
	 	<html:hidden styleId="referralDate" name="courtHearingForm" property="referralDate"/>
	 	<html:hidden styleId="action" name="courtHearingForm" property="action"/>
	 	<html:hidden styleId="actionError" name="courtHearingForm" property="actionError"/>
	 	<html:hidden styleId="validateMsg" name="courtHearingForm" property="validateMsg"/>
	 	<html:hidden styleId="finalDispEntered" name="courtHearingForm" property="finalDispEntered"/>
		<html:hidden styleId="cursorPosition" name="courtHearingForm" property="cursorPosition"/>
		<html:hidden styleId="currAction" name="courtHearingForm" property="currAction"/>
		<html:hidden styleId="prevAction" name="courtHearingForm" property="prevAction"/>
		<html:hidden styleId="selectedSubpoenas" name="courtHearingForm" property="selectedSubpoenas"/>
		
		<logic:empty name="courtHearingForm" property="careGiver">
			<input type="hidden" id="filterCareGiver" name="courtHearingForm" value="careGiver"/>
		</logic:empty>
		<logic:empty name="courtHearingForm" property="guardian">
			<input type="hidden" id="filterGuardian" name="courtHearingForm" value="guardian"/>
		</logic:empty>
		<logic:empty name="courtHearingForm" property="mother">
			<input type="hidden" id="filterMother" name="courtHearingForm" value="mother"/>
		</logic:empty>
		<logic:empty name="courtHearingForm" property="father">
			<input type="hidden" id="filterFather" name="courtHearingForm" value="father"/>
		</logic:empty>
		<input type="hidden" id="holidaysList" name="courtHearingForm" value='<bean:write name="courtHearingForm" property="holidaysList"/>'/>
	</html:form>
	<div id="pdf-download" style="display:none">
 	<!-- iframe created dynamically. -->
	</div>
	<div align='center'>
		<script type="text/javascript">renderBackToTop()</script>
	</div>
</body>
</html:html>