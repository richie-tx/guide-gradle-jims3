<!DOCTYPE HTML>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="elogic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp"%>
<%@ page import="naming.PDJuvenileCaseConstants"%>
<%@ page import="naming.PDCodeTableConstants"%>
<%@ page import="ui.common.UIUtil" %>
<!-- Changes for JIMS200077276 Starts -->
<%@ page import="naming.Features"%>
<!-- Changes for JIMS200077276 ends -->

<!-- LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!-- BEGIN HEADER TAG -->
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<msp:nocache />
<!-- Checks to make sure if the user is logged in. -->

<!-- msp:login / -->

<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=iso-8859-1" pageEncoding="ISO-8859-1"%>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />

<html:base />

<title><bean:message key="title.heading" /> -createJuvenileSummary.jsp</title>


<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type="text/javascript" src="/<msp:webapp/>js/referral/createJuvenileSummary.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>


<script type='text/javascript'>
var harrisCountyDropDownValue = <%=PDCodeTableConstants.HARRIS_COUNTY%>;
</script>
</head>

<!-- BEGIN BODY TAG -->
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);" topmargin='0' leftmargin="0">
<!-- BEGIN FORM TAG -->
<html:form action="/displayCreateJuvenileSummary" target="content">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Create_Juvenile.htm#|5">
	<br/>
<!-- BEGIN HEADING TABLE -->
	<table width='100%'>
		<tr>
			<logic:equal name="juvenileReferralForm" property="action" value="createJuvenileSummary">
				<td align="center" class="header">Process Referrals - Create Juvenile Confirmation</td>
			</logic:equal>
			<logic:equal name="juvenileReferralForm" property="action" value="next">
				<td align="center" class="header">Process Referrals - Create Juvenile Summary</td>
			</logic:equal>
			<logic:equal name="juvenileReferralForm" property="action" value="updateJuvenile">
				<td align="center" class="header">Process Referrals - Update Juvenile Summary</td>
			</logic:equal>
			<logic:equal name="juvenileReferralForm" property="action" value="updateJuvenileSummary">
				<td align="center" class="header">Process Referrals - Update Juvenile Confirmation</td>
			</logic:equal>
		</tr>
	</table>
<!-- END HEADING TABLE -->
 <logic:messagesPresent message="true"> 
			<table width="100%">
				<tr>		  
					<td align="center" class="messageAlert"><html:messages id="message" message="true"><bean:write name="message"/></html:messages>
					<logic:equal name="juvenileReferralForm" property="action" value="createJuvenileSummary"> <bean:write name="juvenileReferralForm"  property="juvenileNum"/></logic:equal>
					</td>		  
				</tr>   	  
			</table>
		</logic:messagesPresent> 

<!-- BEGIN ERROR TABLE -->
	<table width="98%" border="0" align="center">
		<tr>
			<td align="center" class="errorAlert"><html:errors></html:errors></td>
		</tr>
	</table>
<!-- END ERROR TABLE -->

	<!-- BEGIN TAB BLUE BORDER TABLE -->
	<table cellpadding='2' cellspacing='1' border='0' width="99%" class="borderTableBlue" align="center">
		<tr>
			<td valign='top' class='referralDetailHead' colspan="7"><bean:message key="prompt.juvenileDemographics" /></td>
		</tr>
		<logic:equal name="juvenileReferralForm" property="action" value="updateJuvenile">
		<tr>
			<td class="formDeLabel" valign='top' nowrap='nowrap'><bean:message key="prompt.juvenile" /></td>
			<td class='formDe' colspan='7'><bean:write name="juvenileReferralForm"  property="juvenileNum"/></td> 
		</tr>
		</logic:equal>
		<logic:equal name="juvenileReferralForm" property="action" value="updateJuvenileSummary">
		<tr>
			<td class="formDeLabel" valign='top' nowrap='nowrap' width=1%><bean:message key="prompt.juvenile" /></td>
			<td class='formDe' colspan='7'><bean:write name="juvenileReferralForm"  property="juvenileNum"/></td> 
		</tr>
		</logic:equal>
		<tr>
			<td class="formDeLabel" valign='top' nowrap='nowrap'><bean:message key="prompt.name" /></td>
			<td class='formDe' colspan='7'><bean:write name="juvenileReferralForm"  property="formattedName"/></td>
		</tr>
		<tr>
			<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.race" /></td>
			<td  class='formDe' width=20%><bean:write name="juvenileReferralForm" property="race" /></td>
			<td class='formDeLabel' width=1%><bean:message key="prompt.sex" /></td>
			<td class='formDe' colspan='4'><bean:write name="juvenileReferralForm" property="sex"/></td>
		</tr>
	
		<tr>
			<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.dateOfBirth" /></td>
			<td class='formDe' width=10%><bean:write name="juvenileReferralForm"  property="dateOfBirth" /></td>
			
			<td class='formDeLabel' nowrap='nowrap' valign="top"><span>Real DOB</span></td>
			<td class='formDe' valign="top" colspan="1" width="10%">
				<bean:write name="juvenileReferralForm"  property="realDOB" />
			</td>
			
			<td class='formDeLabel' nowrap='nowrap' width=1%><bean:message key="prompt.ssn" /></td>
			<td class='formDe' valign="top" colspan='4'>
				<logic:notEmpty name="juvenileReferralForm"  property="SSN.SSN1">***-**-<bean:write name="juvenileReferralForm"  property="SSN.SSN3" /> &nbsp;
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
			<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.comments" /></td>
			<td class='formDe' colspan="7"><bean:write name="juvenileReferralForm"  property="comments" /></td>
		</tr>
		
		<!-- Juvenile Address Information Section starts -->
		<tr class='referralDetailHead'>
			<td colspan="7"><bean:message key="prompt.juvenileAddressInfo" /></td>
		</tr>
		<tr>
			<td class="formDeLabel" nowrap> <bean:message key="prompt.address" /></td>
			<td class="formDe" colspan="7" nowrap >
				<bean:write name="juvenileReferralForm" property="juvFormattedAddress"/>
				<logic:notEmpty name="juvenileReferralForm" property="juvAddress.validated">
					<logic:equal name="juvenileReferralForm" property="juvAddress.validated" value="Y">
							<img src="/<msp:webapp/>images/green_check.gif" alt="greenCheck" />
					</logic:equal>
					<logic:equal name="juvenileReferralForm" property="juvAddress.validated" value="N">
							<img src="/<msp:webapp/>images/red_x.gif" alt="redx" />
					</logic:equal>
				</logic:notEmpty>
			</td>
		</tr>
		<tr>
		
	<!-- Juvenile Address Information Section completed -->
	<!-- Juvenile Education Section Starts -->
		<tr class='referralDetailHead'><td colspan="7"><bean:message key="prompt.juvenileEducationInformation" /></td></tr>
		<tr>	
			<td class='formDeLabel'><bean:message key="prompt.school" />&nbsp;<bean:message key="prompt.district" /></td>
			<td class='formDe' width=20%><bean:write name="juvenileReferralForm" property="schoolDistrictDescription"/></td>
			<td class='formDeLabel'  width=1%><bean:message key="prompt.school" />&nbsp;<bean:message key="prompt.name" /></td>
			<td class='formDe' colspan='4'>
				<bean:write name="juvenileReferralForm" property="schoolName"/>
				<logic:notEqual name="juvenileReferralForm" property="instructionType" value="">:<bean:write name="juvenileReferralForm" property="instructionType"/></logic:notEqual>		
			</td>
		</tr>	
		<tr>
			<%-- <td class='formDeLabel'><bean:message key="prompt.enrollmentStatus" /></td>
			<td class='formDe' colspan='2'><bean:write name="juvenileReferralForm" property="exitTypeDescription"/></td> --%>
			<td class='formDeLabel'><bean:message key="prompt.schoolAttendanceStatus" /></td>
			<td class='formDe' width=20%><bean:write name="juvenileReferralForm" property="attendanceStatusDescription"/></td>
			<td class='formDeLabel' width=1%><bean:message key="prompt.currentGradeLevel" /></td>	
			<td class='formDe' colspan='4'><bean:write name="juvenileReferralForm" property="gradeLevelDescription"/></td>
		</tr>	
		<tr>
			<td class='formDeLabel'><bean:message key="prompt.programAttending" /></td>
			<td class='formDe' colspan='7'><bean:write name="juvenileReferralForm" property="programAttendingDescription"/></td>
		</tr>	
		
		<tr class='referralDetailHead'>
			<td colspan="7"><bean:message key="prompt.legalParentsOrGuardianInfo" /></td>
		</tr>
		<tr>
			<td class='formDeLabel' width="30%" colspan="1"><bean:message key="prompt.name" /></td>
			<td class='formDeLabel'  width="1%"><bean:message key="prompt.incarcerated"/><br/>Or<br/><bean:message key="prompt.deceased"/></td>
			<td class='formDeLabel' width="10%"><bean:message key="prompt.relationship"/></td>
			<td class='formDeLabel' width="30%"><bean:message key="prompt.address"/></td>
			<td class='formDeLabel' width="5%">Phone Type</td>
			<td class='formDeLabel'  width="40%" nowrap>Phone No.</td>
			<td class='formDeLabel'  width="9%" nowrap>SSN</td>
		</tr>
		<!-- Comes within a loop -->
		<logic:iterate id="memBeans" name="juvenileReferralForm" property="memberDetailsBeanList" indexId="index">
			<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
				<td valign="top"><bean:write name="memBeans" property="formattedName"/></td>
				<td valign="top"><span title="<bean:write name="memBeans" property="incarceratedOrDeceasedDesc"/>" ><bean:write name="memBeans" property="incarceratedOrDeceased"/></span></td>
				<td valign="top"><span title="<bean:write name="memBeans" property="relationshipDesc"/>"><bean:write name="memBeans" property="relationshipId"/></span></td>
				<td wrap  valign="top">
					<bean:write name="memBeans" property="formattedAddress"/>&nbsp;
						<logic:notEmpty name="memBeans" property="memberAddress.validated">
							<logic:equal name="memBeans" property="memberAddress.validated" value="Y">
								<img src="/<msp:webapp/>images/green_check.gif" alt="greenCheck" />
							</logic:equal>
							<logic:equal name="memBeans" property="memberAddress.validated" value="N">
								<img src="/<msp:webapp/>images/red_x.gif" alt="redx" />
							</logic:equal>
						</logic:notEmpty>
				</td>
				<td  valign="top">
					<span title="<bean:write name="memBeans" property="phoneTypeDesc"/>" ><bean:write name="memBeans" property="phoneType"/></span>
				</td>
				<td valign="top" width="20%" nowrap>
				<logic:notEmpty name="memBeans" property="formattedPhone" >
					 <bean:write name="memBeans" property="formattedPhone"/>
					 <logic:notEmpty name="memBeans" property="phoneIndDesc" >
					 (<bean:write name="memBeans" property="phoneIndDesc"/>)
					 </logic:notEmpty>
				 </logic:notEmpty>
				 <logic:equal name="juvenileReferralForm" property="action" value="updateJuvenile">
				 		<logic:empty name="memBeans" property="formattedPhone" >
				 		<logic:notEmpty name="memBeans" property="contactPhoneNumber.areaCode">
				 			<bean:write name="memBeans" property="contactPhoneNumber.areaCode"/>-<bean:write name="memBeans" property="contactPhoneNumber.prefix"/>-<bean:write name="memBeans" property="contactPhoneNumber.last4Digit"/>
							<logic:notEmpty name="memBeans" property="contactPhoneNumber.ext">
							EXT</logic:notEmpty>
							<bean:write name="memBeans" property="contactPhoneNumber.ext"/>
							<logic:notEmpty name="memBeans" property="phoneIndDesc">
							(<bean:write name="memBeans" property="phoneIndDesc"/>)
							</logic:notEmpty>
						</logic:notEmpty>
						</logic:empty>
				 </logic:equal>
				  <logic:equal name="juvenileReferralForm" property="action" value="updateJuvenileSummary">
				  	<logic:empty name="memBeans" property="formattedPhone" >
				 		<logic:notEmpty name="memBeans" property="contactPhoneNumber.areaCode">
				 		<bean:write name="memBeans" property="contactPhoneNumber.areaCode"/>-<bean:write name="memBeans" property="contactPhoneNumber.prefix"/>-<bean:write name="memBeans" property="contactPhoneNumber.last4Digit"/>
							<logic:notEmpty name="memBeans" property="contactPhoneNumber.ext">
							EXT</logic:notEmpty>
							<bean:write name="memBeans" property="contactPhoneNumber.ext"/>
							<logic:notEmpty name="memBeans" property="phoneIndDesc">
							(<bean:write name="memBeans" property="phoneIndDesc"/>)
							</logic:notEmpty>
						</logic:notEmpty>
					</logic:empty>
				 </logic:equal>
				</td>
				<td valign="top" nowrap><bean:write name="memBeans" property="formattedSSN"/></td>
			</tr>
		</logic:iterate>
		<!-- Comes within a loop -->
		
		<tr class='referralDetailHead'><td colspan="7"><bean:message key="prompt.fileCheckout" /></td></tr>
		<tr>
			<td class='formDeLabel' nowrap='nowrap' width="1%"><bean:message key="prompt.checkedOutTo"/></td>
			<td class='formDe' colspan="2"  width="5%"><span title="<bean:write name="juvenileReferralForm" property="checkedOutToDesc" />" colspan="2"><bean:write property="checkedOutTo" name="juvenileReferralForm" /></span></td>
			<td class='formDeLabel' nowrap='nowrap' width="1%"><bean:message key="prompt.dateOut"/></td>
			<td class='formDe' colspan="1" width="50%"><bean:write name="juvenileReferralForm" property="dateOut" /></td>
			<td class='formDeLabel' width="1%" nowrap='nowrap'><bean:message key="prompt.lastActionBy"/></td>
			<td class='formDe' colspan="2" width="50%"><span title="<bean:write name="juvenileReferralForm" property="lastActionByDesc" />"><bean:write name="juvenileReferralForm" property="lastActionBy" /></span></td>
		</tr>
		<tr>
			<td class='formDeLabel' nowrap='nowrap' width="5%"><bean:message key="prompt.lastUpdate"/></td>
			<td class='formDe' colspan="2" width="25%"><bean:write name="juvenileReferralForm" property="lastUpdate" /></td>
			<td class='formDeLabel' nowrap='nowrap' colspan="1"><bean:message key="prompt.operator"/></td>
			<td class='formDe' colspan="5"><span title="<bean:write name="juvenileReferralForm" property="operatorDesc" />"><bean:write name="juvenileReferralForm"property="operator"/></span></td>
		</tr>
	</table>
		<html:hidden styleId="newJuvNum" name="juvenileReferralForm" property="juvenileNum"/>
		<html:hidden styleId="action" name="juvenileReferralForm" property="action"/>
<!-- Juvenile Demographics Section Completed -->

<!-- BEGIN BUTTON TABLE -->
	<div class='spacer'></div> 
	<table width="98%" border="0" align="center">
		<tr>
			<td align="center">
				<%-- <logic:notEqual name="juvenileReferralForm" property="action" value="createJuvenileSummary">	 --%>
				<logic:equal name="juvenileReferralForm" property="action" value="next">					
					<html:button property="submitAction" styleId="backBtn"><bean:message key="button.back"></bean:message></html:button>
					<input type="submit" name="submitAction" onclick="spinner()" id="createJuvenileSubmitBtn" value="<bean:message key="button.finish" />">&nbsp;
			<%-- 	<input type="button" name="createJuvenile" id="createJuvenileSubmitBtn" value="<bean:message key="button.finish"/>">&nbsp;
 					<html:submit property="submitAction" styleId="createJuvenileSubmitBtn"><bean:message key="button.finish" /></html:submit> --%>
					<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
				</logic:equal>
				<logic:equal name="juvenileReferralForm" property="action" value="updateJuvenile">					
					<html:button property="submitAction" styleId="backBtn"><bean:message key="button.back"></bean:message></html:button>
					<input type="submit" name="submitAction" id="createJuvenileSubmitBtn" value="<bean:message key="button.finish" />">&nbsp;
					<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
				</logic:equal>
				<logic:equal name="juvenileReferralForm" property="action" value="createJuvenileSummary">					
					<input type="submit" onclick="spinner()" name="submitAction" value="<bean:message key='button.referralBriefing'/>" id="referralBriefingBtn">
					<input type="submit" name="submitAction" onclick="spinner()" value="<bean:message key='button.searchJuvenile'/>" id="searchJuvenileBtn">
				</logic:equal>
				
				<logic:equal name="juvenileReferralForm" property="action" value="updateJuvenileSummary">					
					<input type="submit" onclick="spinner()" name="submitAction" value="<bean:message key='button.referralBriefing'/>" id="referralBriefingBtn">
					<input type="submit" onclick="spinner()" name="submitAction" value="<bean:message key='button.searchJuvenile'/>" id="searchJuvenileBtn">
				</logic:equal>
			</td>
		</tr>
	</table>
	<!-- END DETAIL TABLE -->
	</html:form>
</body>
</html:html>
