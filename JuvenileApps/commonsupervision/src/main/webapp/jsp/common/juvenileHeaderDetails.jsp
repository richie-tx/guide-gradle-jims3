<!DOCTYPE HTML>
<%-- Used to display juvenile header on all JSPs --%>
<%--MODIFICATIONS --%>
<%-- 05/09/2005		LDeen	Create JSP --%>
<%-- 06/15/2005     DWilliamson added header logic for Juvenile Profile --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ page import="naming.Features" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>



<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>

	<msp:nocache />
	<%-- Checks to make sure if the user is logged in. --%>
	<%--msp:login / --%>
	<meta http-equiv="Content-Language" content="en-us">
	<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
	<meta name="GENERATOR" content="IBM WebSphere Studio">
	<meta http-equiv="Content-Style-Type" content="text/css">
	<%-- STYLE SHEET LINK --%>
	<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/commonsupervision.css" />
	<html:base />
	<title>Common Supervision - common/juvenileHeaderDetails.jsp</title>
<script type="text/javascript" src="/<msp:webapp/>js/casework_util.js"></script>
<script type="text/javascript">

  /* 27 jul 2006 - mjt - hack to use as a static variable 
			 so we can toggle the hidden row off and on
  */
var showCaseToggle = 1;

  /* 27 jul 2006 - mjt - simply used to show or hide the row
  */
	function showCaseInfo()
	{
    show( 'caseInfoRow0', showCaseToggle, 'row' ) ;
    show( 'caseInfoRow1', showCaseToggle, 'row' ) ;
    show( 'caseInfoRow2', showCaseToggle, 'row' ) ;
		if( showCaseToggle == 1 )
		{
	 		document.getElementById( 'showCaseInfoATag' ).innerHTML = "Hide Case Info" ;			 
		}
		else
		{
	 		document.getElementById( 'showCaseInfoATag' ).innerHTML = "View Case Info" ;			 
		}
		showCaseToggle = showCaseToggle ? 0 : 1 ;
	}
	
	function showGuardianInfo()
	{
    //openCustomRestrictiveWindow( "",400,400);
	}
</script>
	
</head> 

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin=0 leftmargin="0">
<bean:define id="headerType"><tiles:getAsString name="headerType" /></bean:define>

<table align="center" cellpadding=1 cellspacing=0 border=0 width='98%'>
	<tr>
		<td bgcolor='#cccccc'>
			<%-- Casefile Header Start --%>		
			<%-- BEGIN JUVENILE HEADER TABLE --%>
			<logic:equal name="headerType" value="casefileheader">
				<logic:notEmpty name="juvenileCasefileForm" property="supervisionNum">		
  				<table width='100%' border=0 cellpadding=2 cellspacing=1>
            <tr class=bodyText>
              <td class=formDeLabel colspan='2'>Casefile Information</td>

              <td class=formDeLabel colspan='4' nowrap align="right">
                <jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_MASTER%>'>
            			<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_MAS_PI%>'>
            				<a href="/JuvenileCasework/displayJuvenileMasterInformation.do?juvnum=<bean:write name="juvenileCasefileForm" property="juvenileNum"/>">Juvenile Profile</a>&nbsp;&nbsp;
            			</jims2:isAllowed>
            		</jims2:isAllowed>
                <a id='showCaseInfoATag' href='javascript:showCaseInfo();'>View Case Info</a>&nbsp;&nbsp;
                <a href="javascript:openWindow('/JuvenileCasework/displayGuardianInfo.do?juvnum=<bean:write name="juvenileCasefileForm" property="juvenileNum"/>')">
                	View Guardian Info
                </a> &nbsp;&nbsp;
              <%-- <logic:equal name="programReferralForm" property="programReferral.referralState.status" value="AC">   --%>
      		 <jims2:isAllowed requiredFeatures='<%=Features.JCW_CF_ACT_PRV_V%>'>
      		    <a href="/JuvenileCasework/displayActivities.do?submitAction=<bean:message key='button.link'/>&supervisionNum=<bean:write name="juvenileCasefileForm" property="supervisionNum"/>&programrefStatus=<bean:write name="programReferralForm" property="programReferral.referralState.status"/>">
                View Activities
                </a>
              </jims2:isAllowed>
              <%-- </logic:equal> --%>
                
    					</td>
            </tr>

    				<%-- added Caseload Manager name and reformatted header per ER JIMS200027187 mjt 16feb2006  --%>
  					<tr>
  						<td class=headerLabel><bean:message key="prompt.juvenile" />&nbsp;#</td>
  						<td class=headerData><bean:write name="juvenileCasefileForm" property="juvenileNum"/></td>
  						<td class=headerLabel><bean:message key="prompt.juvenileName" /> </td>
  						<td class=headerData colspan=3><bean:write name="juvenileCasefileForm" property="juvenileFullName"/></td>
  					</tr>		
  					<tr>
  						<td class=headerLabel><bean:message key="prompt.age" /></td>
  						<td class=headerData><bean:write name="juvenileCasefileForm" property="currentAge"/></td>
  						<td class=headerLabel><bean:message key="prompt.race" /></td>
  						<td class=headerData><bean:write name="juvenileCasefileForm" property="race"/></td>
  						<td class=headerLabel width='1%' nowrap align=left><bean:message key="prompt.sex" /></td>
  						<td class=headerData ><bean:write name="juvenileCasefileForm" property="sex"/></td>
  					</tr>							
  					<tr class=hidden id='caseInfoRow0'>
  						<td class=headerLabel nowrap><bean:message key="prompt.probationOfficer" /> </td>
  						<td class=headerData title='<bean:write name="juvenileCasefileForm" property="probationOfficerEmail"/>  <bean:write name="juvenileCasefileForm" property="probationOfficerWorkPhone" formatKey="phone.format"/> <bean:message key="prompt.ext" /> <bean:write name="juvenileCasefileForm" property="probationOfficerWorkPhoneExtn"/>'><a href=mailto:'<bean:write name="juvenileCasefileForm" property="probationOfficerEmail"/>'><bean:write name="juvenileCasefileForm" property="probationOfficerFullName"/></a></td>
  						<td class=headerLabel nowrap><bean:message key="prompt.caseloadManagerName" /> </td>
  						<td class=headerData colspan=3 title='<bean:write name="juvenileCasefileForm" property="caseloadManagerEmail"/> &nbsp; <bean:write name="juvenileCasefileForm" property="caseloadManagerPhone" formatKey="phone.format"/>'>
  							<a href=mailto:'<bean:write name="juvenileCasefileForm" property="caseloadManagerEmail"/>'>
  								<bean:write name="juvenileCasefileForm" property="caseloadManagerName"/>
  							</a>
  						</td>
  					</tr>
  					<tr  class=hidden id='caseInfoRow1'>
  						<td class=headerLabel nowrap><bean:message key="prompt.supervision" />&nbsp;#</td>
  						<td class=headerData><bean:write name="juvenileCasefileForm" property="supervisionNum"/>&nbsp;:<bean:write name="juvenileCasefileForm" property="sequenceNum"/></td>
  						<td class=headerLabel width='1%' nowrap><bean:message key="prompt.supervisionType" /> </td>
  						<td class=headerData colspan=3><bean:write name="juvenileCasefileForm" property="supervisionType"/></td>					 
  					</tr>
  					<tr class=hidden id='caseInfoRow2'>
  						<td class=headerLabel><bean:message key="prompt.caseStatus" /></td>
  						<td class=headerData><bean:write name="juvenileCasefileForm" property="caseStatus"/></td>						
  						<td class=headerLabel width='1%' nowrap><bean:message key="prompt.assignmentDate" /></td>
  						<td class=headerData><bean:write name="juvenileCasefileForm" property="assignmentDate" formatKey="date.format.mmddyyyy"/></td>						
  						<td class=headerLabel width='1%' nowrap><bean:message key="prompt.expectedSupervisionEndDate" /></td>
  						<td class=headerData width='1%' nowrap><bean:write name="juvenileCasefileForm" property="expectedSupervisionEndDate" formatKey="date.format.mmddyyyy"/>&nbsp;&nbsp;&nbsp;
  						<logic:notEmpty name="juvenileCasefileForm" property="detentionFacilityId">
  						<logic:notEqual name="juvenileCasefileForm" property="detentionFacilityId" value="">
  						<bean:write name="juvenileCasefileForm" property="detentionFacilityId"/>:
  						<bean:write name="juvenileCasefileForm" property="detentionStatusId"/>
  						</logic:notEqual>
  						</logic:notEmpty>
  						
  						</td>						
  					</tr>
  				</table>	
				</logic:notEmpty>	

				<logic:empty name="juvenileCasefileForm" property="supervisionNum">
  				<table width='100%' border=0 cellpadding=2 cellspacing=1>
  					<tr>
  						<td class=headerLabel colspan=4 align=center>Error:  No casefile supplied on the request.</td>
  					</tr>
  				</table>				
				</logic:empty>
			</logic:equal> 
			<%-- END JUVENILE HEADER TABLE --%>

			<%-- START JUVENILE PROFILE TABLE --%>		
			<logic:equal name="headerType" value="profileheader">
				<logic:notEmpty name="juvenileProfileHeader" property="juvenileNum">				
  				<table width='100%' border=0 cellpadding=2 cellspacing=1>
            <tr class=bodyText>
              <td class=formDeLabel colspan='4'>Profile Information</td>
              <td class=formDeLabel colspan='2' nowrap align="right"><a href='javascript:showGuardianInfo();'>View Guardian Info</a></td>
            </tr>
  					<tr>
  						<td class=headerLabel><bean:message key="prompt.juvenile" />&nbsp;#</td>
  						<td class=headerData><bean:write name="juvenileProfileHeader" property="juvenileNum"/></td>
  						<td class=headerLabel ><bean:message key="prompt.juvenileName" /> </td>
  						<td class=headerData colspan=3><bean:write name="juvenileProfileHeader" property="juvenileName"/></td>
  					</tr>
  					<tr>
  						<td class=headerLabel width='1%' nowrap><bean:message key="prompt.currentAge" /></td>
  						<td class=headerData><bean:write name="juvenileProfileHeader" property="age"/></td>
  						<td class=headerLabel width='1%' nowrap><bean:message key="prompt.juvenile" /> <bean:message key="prompt.status" /></td>
  						<td class=headerData colspan=3><bean:write name="juvenileProfileHeader" property="status"/>&nbsp;&nbsp;&nbsp;
  						<logic:notEmpty name="juvenileProfileHeader" property="detentionFacilityId">
  						<logic:notEqual name="juvenileProfileHeader" property="detentionFacilityId" value="">
  						<bean:write name="juvenileProfileHeader" property="detentionFacilityId"/>:
  						<bean:write name="juvenileProfileHeader" property="detentionStatusId"/>
  						</logic:notEqual>
  						</logic:notEmpty>
  						</td>
  					</tr>
  
  				</table>
				</logic:notEmpty>

				<logic:empty name="juvenileProfileHeader" property="juvenileNum">
  				<table width='100%' border=0 cellpadding=2 cellspacing=1>
  					<tr>
  						<td class=headerLabel colspan=4 align=center>Error:  No juvenile profile supplied on the request.</td>
  					</tr>
  				</table>				
				</logic:empty>				
			</logic:equal>
			<%-- END JUVENILE PROFILE TABLE --%>	
		</td>
	</tr>
</table>
<%-- END JUVENILE HEADER TABLE --%>

</body>
</html:html>

