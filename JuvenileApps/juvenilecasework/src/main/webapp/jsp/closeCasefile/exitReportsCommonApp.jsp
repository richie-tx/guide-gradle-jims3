<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%-- 05/0/2007		palcocer JSP --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="naming.Features" %>




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
<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<!--JQUERY FRAMEWORK-->
 <%@include file="../jQuery.fw"%> 
 <script>
 	function displaySpinner(id){
		spinner();
		$.ajax({
	        url: '/JuvenileCasework/displayExitReports.do?submitAction=View&selectedExitPlanId='+id,
	        type: 'post',
	        success: function(data, textStatus , xhr) {
	         	if (200 == xhr.status){
	         		$(".overlay").remove();
	         	}
	        }
	    });
 	}
</script>

<html:base />

</head>

<%--BEGIN BODY TAG--%>
<body  topmargin=0 leftmargin="0">
<html:form action="displayExitReports.do" target="content">

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
 <tr>
   <td align="center" class="header"><bean:message key="title.juvenileCasework"/> - Close Casefile - Common App Report List</td>	     
 </tr>
</table>
<%-- END HEADING TABLE --%>

<%-- BEGIN INSTRUCTION TABLE --%>
<table width="98%" border="0">
	<tr>
		<td>
			<ul>
				<li>Click on any of the report date to view report details.</li>							
			</ul>
		</td>
	</tr>
</table>
<%-- END INSTRUCTION TABLE --%>

<%-- BEGIN HEADER INFO TABLE --%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="casefileheader"/>
</tiles:insert>
<%-- END HEADER INFO TABLE --%>

<%-- Begin DETAIL TABLE --%>
<div class=spacer></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
 	<tr>
   	<td valign=top>
			<tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
  				<tiles:put name="tabid" value="commonApp" />
  				<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
			</tiles:insert>

			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
 	  		<tr>
   				<td valign=top align=center>
     		    <%-- Begin Common App Detail Tabs --%>
  					<div class=spacer></div>
          	<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
            	<tr>
          	   	<td valign=top>
            			<table width='100%' border="0" cellpadding="0" cellspacing="0" >
              			<tr>
            					<td valign=top>
            						<tiles:insert page="../caseworkCommon/commonAppFormTabs.jsp" flush="true">
        									<tiles:put name="tabid" value="ViewHistoryReport"/>
        									<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
        								</tiles:insert>	
    									</td>
                    </tr>
                    <tr>
            					<td bgcolor='#ff6666'><img src='/<msp:webapp/>images/spacer.gif' width=5></td>
            				</tr>
            			 </table>
    
                	 <table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableRed">
                  		<tr>
                   			<td valign=top align=center>
      										<div class=spacer></div>
    											<%-- BEGIN CASEFILE TABLE --%>
      										<table align="center" width='98%' cellpadding="2" cellspacing="0" class="borderTableBlue">
      											<tr>
      												<td class=detailHead>Common App Report List</td>
      											</tr>
      											<tr>
      												<td valign=top align=center>
      													<table width='100%' cellpadding=4 cellspacing="1">
      														<tr>
      															<td class="formDeLabel" width="1%" nowrap>Date Generated</td>
      															<td class="formDeLabel">Report Type</td>
      														</tr>
      												
      														<logic:iterate id="exitPlanIter" name="commonAppForm" property="exitPlanList">
      															<tr>
      																<td class="formDe" width="1%" nowrap>
      																	<a onclick="displaySpinner('<bean:write name='exitPlanIter' property='commonAppDocId'/>')" href="/<msp:webapp/>displayExitReports.do?submitAction=View&selectedExitPlanId=<bean:write name='exitPlanIter' property='commonAppDocId'/>">
      																	<bean:write name="exitPlanIter" property="createDate" formatKey="date.format.mmddyyyy"/></a>
      																	<bean:write name="exitPlanIter" property="createDate" formatKey="time.format.hhmma"/>
      																</td>
      																<td class="formDe" colspan=3><bean:write name="exitPlanIter" property="docTypeDescription"/></td>
      															</tr>
      														</logic:iterate>	
      															
      													</table>
      												</td>
      											</tr>
      											<tr>
      												<td align=center>
      													<br/>
      													<html:submit property="submitAction"><bean:message key="button.back"></bean:message></html:submit>
      													<html:submit property="submitAction"><bean:message key="button.cancel"></bean:message></html:submit>
      													<div class=spacer></div>
      												</td>
      											</tr>
      										</table>		
    											<%-- END CASEFILE TABLE --%>
                  				<div class=spacer></div>
                		   	</td>
                  		</tr>
                  	</table>
                    <%-- End Common App Detail Tabs --%>
                  	<div class=spacer></div>
                	</td>
            		</tr>
          		</table>
           	</td>
          </tr>
        </table>
        <%-- End Common App Tabs --%>
        <div class=spacer></div>
  		</td>
  	</tr>
</table>
		   		
<div class=spacer></div>


</html:form>
<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
