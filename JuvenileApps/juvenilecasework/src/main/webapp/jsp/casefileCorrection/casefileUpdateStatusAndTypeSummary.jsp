<!DOCTYPE HTML>
<%-- Used to display casefile details off Casefile Tab --%>
<%--MODIFICATIONS --%>
<%-- 05/09/2005		LDeen	 Create JSP --%>
<%-- 03/14/2006     CShimek  Defect# 29612, remove colons from prompts --%>
<%-- 01/09/2007 Hien Rodriguez  ER#37077 Add Tab & Buttons security features --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ page import="naming.Features" %>

<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.UIConstants" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>




<%--BEGIN HEADER TAG--%>
<head>
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
<title><bean:message key="title.heading"/> - casefileUpdatesStatusAndTypeSummary.jsp</title>


<%-- Javascript for emulated navigation --%>
<script type="text/javascript" src="/<msp:webapp/>js/casework_util.js"></script>

<%--HELP JAVASCRIPT FILE --%> 
<%--<SCRIPT SRC="../js/help.js" /> --%>
<%--APP JAVASCRIPT FILE --%>
<%-- tiles:insert page="/js/app.js" / --%>   
</head> 
<%--END HEAD TAG--%>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin=0 leftmargin="0">
<html:form action="handleCasefileCorrection.do" target="content">


<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
  <tr>
    <td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.update"/> <bean:message key="title.casefileStatusAndSupType"/>
      <logic:notEqual name="casefileCorrectionForm" property="secondaryAction" value="<%=UIConstants.CONFIRM%>">
        <bean:message key="title.summary"/>
      </logic:notEqual>

       <logic:equal name="casefileCorrectionForm" property="secondaryAction" value="<%=UIConstants.CONFIRM%>">
         <bean:message key="title.confirmation"/>
       </logic:equal>
    </td>
  </tr>
  <logic:equal name="casefileCorrectionForm" property="secondaryAction" value="<%=UIConstants.CONFIRM%>">
    <tr>
			<td align=center class=confirm>Casefile information successfully corrected.</td>
		</tr>
	</logic:equal>
</table>

<%-- END HEADING TABLE --%>
<table width="100%">
	<tr>		  
		<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
	</tr>   	  
</table>

<!-- BEGIN INSTRUCTION TABLE -->
<div class='spacer'></div>
<table width="98%" border="0">
  <tr>
    <td>
      <ul>
       <logic:notEqual name="casefileCorrectionForm" property="secondaryAction" value="<%=UIConstants.CONFIRM%>">
        <li id='summaryInstruct'>Select the Finish button to save the information.</li>
       
        </logic:notEqual>
         <logic:equal name="casefileCorrectionForm" property="secondaryAction" value="<%=UIConstants.CONFIRM%>">
          <li id='confirmInstruct' >Select the Return to Casefile Info button.</li>
         </logic:equal>
      </ul>	
    </td>
  </tr>
</table>
<!-- END INSTRUCTION TABLE -->

<!-- BEGIN DETAIL TABLE -->
<div class='spacer'></div>
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
					<div class='spacer'></div>
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
								  	<td bgcolor=#33cc66><img src=../../images/spacer.gif width=5></td>
								  </tr>
						  	</table>

								<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
									<tr>
										<td valign=top align=center>

                        <!-- BEGIN CASEFILE TABLE -->
												<div class=spacer></div> 
                				<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
                					<tr>
                						<td valign=top class=detailHead>Update Status and Type</td>
                					</tr>
                					<tr>
                						<td>
                							<table width='100%' cellpadding=4 cellspacing=1>
                								<tr>
                									<td valign=top class=formDeLabel width='1%' nowrap>Change Casefile Status to Active</td>
                									<td valign=top class=formDe colspan=3><bean:write name="casefileCorrectionForm" property="changeStatusToClosingPendingStr"/></td>
                								</tr>
																	<tr>
																  <td class=formDeLabel>Current Supervision Category</td>
																	<td class=formDe><bean:write name="casefileCorrectionForm" property="currentSupCatDesc"/></td>
																</tr>
																
                								<tr>
                									<td valign=top class=formDeLabel nowrap width="1%">Change Supervision Type to</td>
                									<td valign=top class="formDe" colspan=3><bean:write name="casefileCorrectionForm" property="changeToSupTypeDesc"/></td>
                								</tr>
                							</table>
                						</td>
                					</tr>
                				</table><!-- END CASEFILE TABLE -->

                				<!-- begin button table -->
												<div class='spacer'></div>
                				<table border="0" cellpadding=1 cellspacing=1 align=center>
                  				<logic:notEqual name="casefileCorrectionForm" property="secondaryAction" value="<%=UIConstants.CONFIRM%>">
                  				  <tr id='summaryButtons' >
                              <td align="center">
                                 <html:submit property="submitAction" ><bean:message key="button.back"></bean:message></html:submit>&nbsp;
                                 <html:submit property="submitAction" ><bean:message key="button.finish"></bean:message></html:submit>&nbsp;
                                <html:submit property="submitAction" ><bean:message key="button.cancel"></bean:message></html:submit>&nbsp;
                              </td>
                  				  </tr>
                          </logic:notEqual>

                          <logic:equal name="casefileCorrectionForm" property="secondaryAction" value="<%=UIConstants.CONFIRM%>">
                  				  <tr id='confirmButtons' >
                              <td align="center">
                               <html:submit property="submitAction" ><bean:message key="button.returnToCasefile"></bean:message></html:submit>&nbsp;
                              </td>
                  				  </tr>
                  				  </logic:equal>
                  				</table>
                  				<!-- begin button table -->

          						</td>
          					</tr>
          				</table><div class='spacer'></div><!-- END CASEFILE TABLE -->
    						</td>
    					</tr>
    				</table><!-- END CASEFILE TABLE -->
          </td>
        </tr>
      </table><!-- END CASEFILE TABLE -->
   	</td>
  </tr>
</table>
<!-- END DETAIL TABLE  -->


<div align=center><script type="text/javascript">renderBackToTop()</script></div>

</html:form>
<%-- END FORM --%>

</body>
</html:html>

