<!DOCTYPE HTML>
<%-- Used to display casefile details off Casefile Tab --%>
<%-- MODIFICATIONS --%>
<%-- 18 nov 2007 JJose Create JSP --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ page import="naming.Features" %>

<%-- TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>

<%-- LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET --%>


<%-- BEGIN HEADER TAG --%>
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge, chrome=1" />

<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%-- msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />
<title><bean:message key="title.heading"/> - casefileUpdateStatusAndType.jsp</title>

<%-- Javascript for emulated navigation --%>
<script type="text/javascript" src="/<msp:webapp/>js/casework_util.js"></script>
<%-- HELP JAVASCRIPT FILE --%> 
<%--<SCRIPT SRC="../js/help.js" /> --%>
<%-- APP JAVASCRIPT FILE --%>
<%-- tiles:insert page="/js/app.js" / --%>   
</head> 
<%--END HEAD TAG--%>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin=0 leftmargin="0">
<html:form action="handleCasefileCorrection.do" target="content">


<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
  <tr>
    <td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.update"/> <bean:message key="title.casefileStatusAndSupType"/></td>
  </tr>
</table>


<%-- END HEADING TABLE --%>
<table width="100%">
	<tr>		  
		<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
	</tr>   	  
</table>


<%-- BEGIN INSTRUCTION TABLE --%>
<br>
<table width="98%" border="0">
  <tr>
    <td>
      <ul>
        <li>Select to change Casefile Status to 'Pending Closing'.</li>
        <li>Select to change Casefile Supervision Type.</li>
        <li>Select the Next button to view the Summary screen.</li>
      </ul>	
    </td>
  </tr>
</table>
<%-- END INSTRUCTION TABLE --%>

<%-- BEGIN DETAIL TABLE --%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="casefileheader"/>
</tiles:insert>


<div class=spacer></div> 
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td valign=top>

    	<tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
    		<tiles:put name="tabid" value="casefiledetailstab"/>
    		<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
    	</tiles:insert>				

			 <%-- BEGIN DETAIL TABLE --%>
  		<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
  			<tr>
  			  <td valign=top align=center>
    			  
    			  <%-- BEGIN CASEFILE INFO TABS INNER TABLE --%>
  					<div class=spacer></div> 
    			  <table width='98%' border="0" cellpadding="0" cellspacing="0">
  						<tr>
  							<td>
  								<table width='100%' border="0" cellpadding="0" cellspacing="0" >
  									<tr>
  										<td valign=top>
  										<%--tabs start--%>
  											<tiles:insert page="../caseworkCommon/casefileInfoTabs.jsp" flush="true">
  												<tiles:put name="tabid" value="casefiledetailstab"/>
    												<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
  											</tiles:insert>	
  										<%--tabs end--%>
  										</td>
  									</tr>
  									<tr>
									  	<td bgcolor='#33cc66'><img src='../../images/spacer.gif' width=5></td>
  								  </tr>
  						  	</table>

  								<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
  									<tr>
  										<td valign=top align=center>

                        <%-- BEGIN CASEFILE TABLE --%>
												<div class=spacer></div> 
                				<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
                					<tr>
                						<td valign=top class=detailHead>Update Status and Type</td>
                					</tr>
                					<tr>
                						<td>
                							<table width='100%' cellpadding=4 cellspacing=1>
                								<tr>
                									<td class=formDeLabel width='1%' nowrap><bean:message key="prompt.2.diamond"/>Change Casefile Status to Active</td>
                									
                									<td class=formDe> 
                									<logic:equal name="juvenileCasefileForm" property="caseStatusId" value="<%=naming.UIConstants.CASEFILE_CASE_STATUS_CLOSED%>">	
                										<bean:define id="ableToChange" value="true"></bean:define>
                									</logic:equal>
                									<logic:notPresent name="ableToChange">
                										<logic:equal name="juvenileCasefileForm" property="caseStatusId" value="<%=naming.UIConstants.CASEFILE_CASE_STATUS_CLOSING_PENDING%>">	
                											<bean:define id="ableToChange" value="true"></bean:define>
                										</logic:equal>
                									</logic:notPresent>
                									
                  									<logic:present name="ableToChange">
                  										Yes<html:radio property="changeStatusToClosingPending" value="true" /> No<html:radio property="changeStatusToClosingPending" value="false"/>
          													</logic:present>
                  									<logic:notPresent name="ableToChange">
                  										Yes<html:radio property="changeStatusToClosingPending" value="true" disabled="true"/> No<html:radio property="changeStatusToClosingPending" value="false" disabled="true"/>
                  									</logic:notPresent>
                									</td>
                								</tr>
																<tr>
																  <td class=formDeLabel>Current Supervision Category</td>
																	<td class=formDe><bean:write name="casefileCorrectionForm" property="currentSupCatDesc"/></td>
																</tr>
																
                								<tr>
                									<td class=formDeLabel nowrap width="1%"><bean:message key="prompt.2.diamond"/>Change Supervision Type to</td>
                									<td class="formDe">
                                    <html:select property="changeToSupTypeId">
                                      <html:optionsCollection property="supervisionTypes" value="supervisionTypeId" label="supervisionType"/>
                                    </html:select>
																	</td>
                								</tr>
                							</table>
                						</td>
                					</tr>
                				</table><%-- END CASEFILE TABLE --%>

                				<%-- begin button table --%>
												<div class='spacer'></div>
                				<table border="0" cellpadding=1 cellspacing=1 align=center>
                				  <tr>
                            <td align="center">
                              <html:submit property="submitAction" ><bean:message key="button.submit"></bean:message></html:submit>&nbsp;
                              <html:reset property="submitAction" ><bean:message key="button.reset"></bean:message></html:reset>&nbsp;
                              <html:submit property="submitAction" ><bean:message key="button.cancel"></bean:message></html:submit>&nbsp;
                            </td>
                				  </tr>
                				</table>
                				<%-- begin button table --%>

          						</td>
          					</tr>
          				</table><div class='spacer'></div><%-- END CASEFILE TABLE --%>
    						</td>
    					</tr>
    				</table><%-- END CASEFILE TABLE --%>
          </td>
        </tr>
      </table><%-- END CASEFILE TABLE --%>
   	</td>
  </tr>
</table>
<%-- END DETAIL TABLE  --%>

<div align=center><script type="text/javascript">renderBackToTop()</script></div>

</html:form>
<%-- END FORM --%>

</body>
</html:html>

