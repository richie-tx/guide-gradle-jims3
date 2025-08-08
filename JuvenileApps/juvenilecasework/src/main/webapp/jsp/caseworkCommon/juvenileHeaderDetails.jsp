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
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="ui.juvenilecase.form.JuvenileCasefileForm"%>
<%@ page import="ui.juvenilecase.form.JuvenileProfileForm" %>
<%@ page import="ui.juvenilecase.casefile.form.JuvenileBriefingDetailsForm" %>
<%@ page import="ui.juvenilecase.UIJuvenileCaseworkHelper" %>

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />

<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/> 
<title><bean:message key="title.heading"/>/juvenileHeaderdetails.jsp</title>
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type="text/javascript" src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type="text/javascript">

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
  var newWin = window.open( "", "guardian", "height=200,width=300,toolbar=no,scrollbars=yes,resizable=no,status=no,location=no,menubar=no,dependent=no");
  newWin.document.write( "<table><tr><td>Guardian Telephone List coming soon!</td></tr><tr><td colspan=2 align=center><input type=button value='Close Window' onclick='javascript:window.close();'></td></tr></table>" );
}
</script>
</head>

<bean:define id="headerType"><tiles:getAsString name="headerType" /></bean:define>
<tiles:importAttribute name="viewOnly" ignore="true"/>

<%-- BEGIN JUVENILE HEADER TABLE --%>
<table align="center" cellpadding="1" cellspacing="0" border="0" width="98%">
	<tr>
		<td bgcolor='#cccccc'>
<%-- BEGIN CASEFILE INFORMATION HEADER TABLE --%>
			<logic:equal name="headerType" value="casefileheader">
				<logic:notEmpty name="juvenileCasefileForm" property="supervisionNum">		
					<table width="100%" border="0" cellpadding="2" cellspacing="1">
            			<tr class="bodyText">
							<td class="formDeLabel" width="30%" nowrap><bean:message key="prompt.caseInfo" />&nbsp;&nbsp;
         			  			<jims2:isAllowed requiredFeatures='<%=Features.JCW_CASEWORKBRIEFINGDETAILSSCREEN%>'>
         			  				<a onclick="spinner()" href="/<msp:webapp/>displayJuvenileBriefingDetails.do?submitAction=Link&juvenileNum=<bean:write name='juvenileCasefileForm' property='juvenileNum'/>&supervisionNum=<bean:write name='juvenileCasefileForm' property='supervisionNum'/>">Casework Briefing</a>
         			  				&nbsp;&nbsp;
         			  			</jims2:isAllowed>
         			  			<jims2:isAllowed requiredFeatures='<%=Features.JCW_SEARCHFACPROF%>'>
         			  				<a onclick="spinner()" href="/<msp:webapp/>displayJuvenileProfileSearchResults.do?isFacility=true&searchType=JNUM&juvenileNum=<bean:write name='juvenileCasefileForm' property='juvenileNum'/>">Facility Briefing</a>
         			  				&nbsp;&nbsp;
         			  			</jims2:isAllowed>
         			  			<jims2:isAllowed requiredFeatures='<%=Features.JCW_SEARCHJUVREFPROF%>'>
									<a href="/<msp:webapp/>displayJuvenileBriefingDetails.do?fromProfile=profileBriefingDetails&submitAction=referralLink&juvenileNum=<bean:write name='juvenileCasefileForm' property='juvenileNum'/>">
										Referral Briefing
									</a>
								</jims2:isAllowed>
         			  		</td>
							<td width="20%" align="right"><a href='mailto:Data.corrections@hcjpd.hctx.net '><u>Email Data Corrections</u></a></td>
              				<td nowrap align="right" width="10%">
				                <logic:notPresent name="viewOnly">
	            					<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_MASTER%>'>
	              						<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_MAS_PI%>'>
	              							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	              							<a href="/<msp:webapp/>displayJuvenileMasterInformation.do?juvnum=<bean:write name="juvenileCasefileForm" property="juvenileNum"/>&fromProfile=profileBriefingDetails">Juvenile Profile</a>&nbsp;&nbsp;	              							
	              						</jims2:isAllowed>
	              					</jims2:isAllowed>
	                			</logic:notPresent> 
	                			&nbsp;
	                		</td>
	                		<td width="6%" align="center">	
				                <a id="showCaseInfoATag" href="javascript:showCaseInfo();">View Case Info</a>&nbsp;&nbsp;
				            </td>
				            <td width="7%" nowrap align="center">    
				                <a href="javascript:openWindow('/<msp:webapp/>displayGuardianInfo.do?juvnum=<bean:write name="juvenileCasefileForm" property="juvenileNum"/>')">View Guardian Info</a> 
			            	</td>
			            </tr>
            			
            		</table>
            		<table width="100%" border="0" cellpadding="2" cellspacing="1">
    				<%-- added Caseload Manager name and reformatted header per ER JIMS200027187 mjt 16feb2006  --%>
	  					<tr>
	  						<td class="headerLabel"><bean:message key="prompt.juvenile" />&nbsp;#</td>
	  						<td class="headerData"><logic:equal name="juvenileCasefileForm" property="restrictedAccess" value="true"><font color="red"><bean:write name="juvenileCasefileForm" property="juvenileNum"/></font></logic:equal>
	  						<logic:notEqual name="juvenileCasefileForm" property="restrictedAccess" value="true"><bean:write name="juvenileCasefileForm" property="juvenileNum"/></logic:notEqual></td>
	  						<td class="headerLabel" nowrap><bean:message key="prompt.juvenileName" /></td>
		  						<td class="headerData" colspan="5"><bean:write name="juvenileCasefileForm" property="juvenileFullName"/>
		  							&nbsp;
		  							<logic:notEqual name="juvenileCasefileForm" property="preferredFirstName" value="">
		  								<span style="font-weight: bold;" title="Preferred First Name">*<bean:write name="juvenileCasefileForm" property="preferredFirstName"/></span>
		  								&nbsp;
		  							</logic:notEqual>		  							
		  							<logic:equal name="juvenileBriefingDetailsForm" property="dualStatus" value="FDS">
		  								<span style="color:purple; font-weight: bold;" title="Former Dual Status">(<bean:write name="juvenileBriefingDetailsForm" property="dualStatus"/>)</span>
		  							</logic:equal>
		  							<logic:equal name="juvenileBriefingDetailsForm" property="dualStatus" value="DS">
		  								<span style="color:purple; font-weight: bold;" title="Dual Status">(<bean:write name="juvenileBriefingDetailsForm" property="dualStatus"/>)</span>
		  							</logic:equal>
		  							&nbsp;
		  							<logic:equal name="juvenileBriefingDetailsForm" property="traitTypeId" value="MER">
		  								<span style="color:#8417A0; font-weight: bold;" title="<bean:write name="juvenileBriefingDetailsForm" property="traitTypeDescription"/>">(M)</span>
		  							</logic:equal>
		  							&nbsp;
		  							<logic:equal name="juvenileCasefileForm" property="restrictedAccess" value="true"><span title='RESTRICTED ACCESS'><font color="red">(RESTRICTED)</font></span></logic:equal>
		  						</td>
	  					</tr>		
	  					<tr>
	  						<td class="headerLabel" width="12%"><bean:message key="prompt.age" /></td>
	  						<td class="headerData"  width="13%"><bean:write name="juvenileCasefileForm" property="currentAge"/></td>
	  						<td class="headerLabel" width="12%"><bean:message key="prompt.race" /></td>
	  						<td class="headerData"  width="13%"><bean:write name="juvenileCasefileForm" property="race"/></td>
	  						<td class="headerLabel" width="12%"><bean:message key="prompt.hispanic" /></td>
	  						<td class="headerData"  width="13%"><bean:write name="juvenileBriefingDetailsForm" property="hispanic"/></td>
	  						<td class="headerLabel" width="12%" nowrap align=left><bean:message key="prompt.sex" /></td>
	  						<td class="headerData"  width="13%"><bean:write name="juvenileCasefileForm" property="sex"/></td>
	  					</tr>							
	  					<tr class="hidden" id="caseInfoRow0">
	  						<td class="headerLabel" nowrap><bean:message key="prompt.probationOfficer" /> </td>
	  						<td class="headerData" nowrap title='<bean:write name="juvenileCasefileForm" property="probationOfficerEmail"/> <bean:write name="juvenileCasefileForm" property="probationOfficerWorkPhone" formatKey="phone.format"/>  <bean:write name="juvenileCasefileForm" property="probationOfficerWorkPhoneExtn"/>'><a href=mailto:'<bean:write name="juvenileCasefileForm" property="probationOfficerEmail"/>'><bean:write name="juvenileCasefileForm" property="probationOfficerFullName"/></a></td>
	  						<td class="headerLabel" nowrap><bean:message key="prompt.caseloadManagerName" /> </td>
	  						<td class="headerData" colspan="3" title='<bean:write name="juvenileCasefileForm" property="caseloadManagerEmail"/> <bean:write name="juvenileCasefileForm" property="caseloadManagerWorkPhone" formatKey="phone.format"/>  <bean:write name="juvenileCasefileForm" property="caseloadManagerWorkPhoneExtn"/>'><a href=mailto:'<bean:write name="juvenileCasefileForm" property="caseloadManagerEmail"/>'><bean:write name="juvenileCasefileForm" property="caseloadManagerName"/></td>
	  					</tr>
	  					<tr  class="hidden" id="caseInfoRow1">
	  						<td class="headerLabel" nowrap><bean:message key="prompt.supervision" />&nbsp;#</td>
	  						<td class="headerData"><bean:write name="juvenileCasefileForm" property="supervisionNum"/>&nbsp;:<bean:write name="juvenileCasefileForm" property="sequenceNum"/></td>
	  						<td class="headerLabel" width="1%" nowrap><bean:message key="prompt.supervisionType" /> </td>
	  						<td class="headerData" colspan="3"><bean:write name="juvenileCasefileForm" property="supervisionType"/></td>					 
	  					</tr>
	  					<tr class="hidden" id="caseInfoRow2">
	  						<td class="headerLabel"><bean:message key="prompt.caseStatus" /></td>
	  						<td class="headerData"><bean:write name="juvenileCasefileForm" property="caseStatus"/></td>						
	  						<td class="headerLabel" width='1%' nowrap><bean:message key="prompt.assignmentDate" /></td>
	  						<td class="headerData"><bean:write name="juvenileCasefileForm" property="assignmentDate" formatKey="date.format.mmddyyyy"/></td>						
	  						<td class="headerLabel" width='1%' nowrap><bean:message key="prompt.expectedSupervisionEndDate" /></td>
	  						<td class="headerData" width='1%' nowrap><bean:write name="juvenileCasefileForm" property="expectedSupervisionEndDate" formatKey="date.format.mmddyyyy"/>&nbsp;&nbsp;&nbsp;
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
  					<table width="100%" border="0" cellpadding="2" cellspacing="1">
  						<tr>
  							<td class="headerLabel" colspan="4" align="center">Error:  No casefile supplied on the request.</td>
  						</tr>
  					</table>				
				</logic:empty>
			</logic:equal> 
<%-- END CASEFILE INFORMATIONHEADER --%>

<%-- BEGIN PROFILE INFORMATION HEADER --%>		
			<logic:equal name="headerType" value="profileheader">
				<logic:notEmpty name="juvenileProfileHeader" property="juvenileNum">
					<table width="100%" border="0" cellpadding="2" cellspacing="1">				
            			<tr class=bodyText>
              				<td class="formDeLabel" width="31%" nowrap align="right" ><bean:message key="prompt.profile" /> <bean:message key="prompt.info" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              					<jims2:isAllowed requiredFeatures='<%=Features.JCW_CASEWORKBRIEFINGDETAILSSCREEN%>'>
              						<a onclick="spinner()" href="/<msp:webapp/>displayJuvenileBriefingDetails.do?submitAction=Link&fromProfile=profileBriefingDetails&juvenileNum=<bean:write name='juvenileProfileHeader' property='juvenileNum'/>">Casework Briefing</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
         			  			</jims2:isAllowed>
         			  			<jims2:isAllowed requiredFeatures='<%=Features.JCW_SEARCHFACPROF%>'>
									&nbsp;	<a onclick="spinner()" href="/<msp:webapp/>displayJuvenileProfileSearchResults.do?isFacility=true&juvenileNum=<bean:write name='juvenileProfileHeader' property='juvenileNum'/>&searchType=JNUM">Facility Briefing</a>
								</jims2:isAllowed>
         			  			
         			  		</td>
         			  		<td class="formDeLabel" width="10%" align="right">
         			  			<jims2:isAllowed requiredFeatures='<%=Features.JCW_SEARCHJUVREFPROF%>'>
	         			  			<a onclick="spinner()" href="/<msp:webapp/>displayJuvenileBriefingDetails.do?fromProfile=profileBriefingDetails&submitAction=referralLink&juvenileNum=<bean:write name='juvenileProfileHeader' property='juvenileNum'/>">   
	         			  				Referral Briefing
	         			  			</a>
         			  			</jims2:isAllowed>
         			  		</td>
         			  		<td class="formDeLabel" width="13%" align="right">
         			  		<jims2:isAllowed requiredFeatures='<%=Features.JCW_PS_SCANNED_DOCUMENTS%>'>    
						      		<a href="javascript:openCustomRestrictiveWindow('http://svpjpdweb1:8086/Ecx.Web/en-US/do/root_HC_JuvenileDetail?appid=root_Juvenile_Documents&d=Juvenile_Documents_Production.tenant1&JuvenileNo=<bean:write name='juvenileProfileHeader' property='juvenileNum'/>',800,900 );"/>Scanned Documents</a>
							  			&nbsp;
						 		 </jims2:isAllowed> 
         			  		</td>
         			  		
							<td class="formDeLabel" width="13%" align="right"><a href='mailto:Data.corrections@hcjpd.hctx.net '><u>Email Data Corrections</u></a></td>
              				<td class="formDeLabel" width="11%" nowrap align="right">
               					<a href="javascript:openWindow('/<msp:webapp/>displayGuardianInfo.do?juvnum=<bean:write name="juvenileProfileHeader" property="juvenileNum"/>')">View Guardian Info</a> 
              				</td>
              				<td class="formDeLabel" nowrap align="right">
               					JPO Of Record - <span title='<bean:write name="juvenileProfileHeader" property="jpoOfRecord"/>'><bean:write name="juvenileProfileHeader" property="jpoOfRecID"/></span></a> 
              				</td>
            			</tr>
            		</table>
            		<table width="100%" border="0" cellpadding="2" cellspacing="1">
	  					<tr>
	  						<td class="headerLabel" width="16%"><bean:message key="prompt.juvenile" />&nbsp;#</td>
	  						<td class="headerData" 	width="27%"><logic:equal name="juvenileProfileHeader" property="restrictedAccess" value="true"><font color="red"><bean:write name="juvenileProfileHeader" property="juvenileNum"/></font></logic:equal>
	  						<logic:notEqual name="juvenileProfileHeader" property="restrictedAccess" value="true"><bean:write name="juvenileProfileHeader" property="juvenileNum"/></logic:notEqual></td>
	  						<td class="headerLabel" width="16%"><bean:message key="prompt.juvenileName" /></td>
		  						<td class="headerData"><bean:write name="juvenileProfileHeader" property="juvenileName"/>
		  							&nbsp;
		  							<logic:notEqual name="juvenileProfileHeader" property="preferredFirstName" value="">
		  								<span style="font-weight: bold;" title="Preferred First Name">*<bean:write name="juvenileProfileHeader" property="preferredFirstName"/></span>
		  								&nbsp;
		  							</logic:notEqual>
		  							<logic:equal name="juvenileBriefingDetailsForm" property="dualStatus" value="FDS">
		  								<span style="color:purple; font-weight: bold;" title="Former Dual Status">(<bean:write name="juvenileBriefingDetailsForm" property="dualStatus"/>)</span>
		  							</logic:equal>
		  							<logic:equal name="juvenileBriefingDetailsForm" property="dualStatus" value="DS">
		  								<span style="color:purple; font-weight: bold;" title="Dual Status">(<bean:write name="juvenileBriefingDetailsForm" property="dualStatus"/>)</span>
		  							</logic:equal>
		  							&nbsp;
		  							<logic:equal name="juvenileBriefingDetailsForm" property="traitTypeId" value="MER">
		  								<span style="color:#8417A0; font-weight: bold;" title="<bean:write name="juvenileBriefingDetailsForm" property="traitTypeDescription"/>">(M)</span>
		  							</logic:equal>
		  							&nbsp;
		  							<logic:equal name="juvenileProfileHeader" property="restrictedAccess" value="true"><span title='RESTRICTED ACCESS'><font color="red">(RESTRICTED)</font></span></logic:equal>
		  						</td>
	  					</tr>
	  				</table>
	  					<table width="100%" border="0" cellpadding="2" cellspacing="1">
	  					<tr>
	  						<td class="headerLabel" nowrap width="16%"><bean:message key="prompt.currentAge" /></td>
	  						<td class="headerData" width="27%">	  						
	  						  <div style="float:left;width:50%;"><bean:write name="juvenileProfileHeader" property="age"/></div>
							  <div style="float:right;width:50%;"><bean:message key="prompt.dob" />:
	  								<bean:write name="juvenileProfileHeader" property="dateOfBirth"/></div>
	  						</td>
	  						<td class="headerLabel" nowrap width="16%"><bean:message key="prompt.juvenile" /> <bean:message key="prompt.status" /></td>
	  						<td class="headerData"><bean:write name="juvenileProfileHeader" property="status"/>&nbsp;&nbsp;&nbsp;
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
	  				<table width="100%" border="0" cellpadding="2" cellspacing="1">
	  					<tr>
	  						<td class="headerLabel" width="100%" align="center">Error:  No juvenile profile supplied on the request.</td>
	  					</tr>
	  				</table>				
				</logic:empty>				
			</logic:equal>
<%-- END PROFILE INFORMATION HEADER --%>	
		</td>
	</tr>
</table>
<%-- END JUVENILE HEADER TABLE --%>