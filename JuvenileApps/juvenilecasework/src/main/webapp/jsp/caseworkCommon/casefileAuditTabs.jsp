
<%-- Manages Tabs for Juvenile Casefiles Audit level stuff--%>
<%-- 07/18/2011	C Shimek	Create JSP --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="naming.Features" %> 

<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title><bean:message key="title.heading"/>/casefileAuditTabs.jsp</title>
</head>

<table border='0' cellpadding='0' cellspacing='0'>
	<tr>
		<bean:define id="tab">
			<tiles:getAsString name="tabid" />
		</bean:define>
		<tiles:importAttribute name="casefileid"/>
			
		<logic:equal name="casefileid" value="">
			<bean:define id="requestParam"><%=PDJuvenileCaseConstants.CASEFILEID_PARAM%>=<bean:write name="juvenileCasefileForm" property="supervisionNum"/>&supervisionNum=<bean:write name="juvenileCasefileForm" property="supervisionNum"/></bean:define>
		</logic:equal>
		<logic:notEqual name="casefileid" value="">
			<bean:define id="requestParam"><%=PDJuvenileCaseConstants.CASEFILEID_PARAM%>=<bean:write name="casefileid" />&supervisionNum=<bean:write name="juvenileCasefileForm" property="supervisionNum"/></bean:define>
		</logic:notEqual>
		
  		<logic:equal name="tab" value="CasefileAuditDetails">
      		<td bgcolor='#ff6666' valign="top"><img src='/<msp:webapp/>images/left_red_active_tab.gif'></td>
		    <td bgcolor='#ff6666' align="center" class="topNav"><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileCasefileDetails.do?<bean:write name="requestParam" />" class="topNav"><bean:message key="prompt.casefile" /> <bean:message key="prompt.audit" /></a></td>
		    <td bgcolor='#ff6666' valign="top"><img src='/<msp:webapp/>images/right_red_active_tab.gif'></td>				
  		</logic:equal>

  		<logic:notEqual name="tab" value="CasefileAuditDetails">
  			<td bgcolor='#ffcccc' valign="top"><img src='/<msp:webapp/>images/left_red_inactive_tab.gif'></td>
  			<td bgcolor='#ffcccc' align="center" class="topNav"><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileCasefileDetails.do?<bean:write name="requestParam" />" class="topNav"><bean:message key="prompt.casefile" /> <bean:message key="prompt.audit" /></a></td>
  			<td bgcolor='#ffcccc' valign="top"><img src='/<msp:webapp/>images/right_red_inactive_tab.gif'></td>
  		</logic:notEqual>	
  	
  		<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" width="2"></td>	
        <jims2:isAllowed requiredFeatures='<%=Features.JCW_CFV_NONCOMPLIANCE%>'>
	  		<logic:equal name="tab" value="NonComplianceNoticeDetails">
	      		<td bgcolor='#ff6666' valign="top"><img src='/<msp:webapp/>images/left_red_active_tab.gif'></td>
	      		<td bgcolor='#ff6666' align="center" class="topNav"><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileCasefileNonComplianceNoticeList.do?submitAction=Link" class="topNav" >Non-Compliance Notice</a></td>
	      		<td bgcolor='#ff6666' valign="top"><img src='/<msp:webapp/>images/right_red_active_tab.gif'></td>				
	  		</logic:equal>
	
	  		<logic:notEqual name="tab" value="NonComplianceNoticeDetails">
	  			<td bgcolor='#ffcccc' valign="top"><img src='/<msp:webapp/>images/left_red_inactive_tab.gif'></td>
	  			<td bgcolor='#ffcccc' align="center" class="topNav"><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileCasefileNonComplianceNoticeList.do?submitAction=Link" class=topNav>Non-Compliance Notice</a></td>
	  			<td bgcolor='#ffcccc' valign="top"><img src='/<msp:webapp/>images/right_red_inactive_tab.gif'></td>
	  		</logic:notEqual>
	  		<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" width="2"></td>	
		</jims2:isAllowed>  	
	</tr>
</table>
