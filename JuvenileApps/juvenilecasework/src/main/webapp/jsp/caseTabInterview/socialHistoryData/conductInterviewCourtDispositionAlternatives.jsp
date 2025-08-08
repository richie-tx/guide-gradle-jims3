<!DOCTYPE HTML>

<%--MODIFICATIONS --%>
<%-- 10/19/2006		AWidjaja Create JSP--%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="ui.common.UIUtil" %>



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

<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- conductInterviewCourtDispositionAlternatives.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<!--JQUERY FRAMEWORK-->
<%@include file="../../jQuery.fw"%>

<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<html:javascript formName="dispositionAlternatives"/>

<script type='text/javascript' >

function textLimit(field, maxlen) 
{
	if (field.value.length > maxlen + 1)
	{
  		alert("Your input has been truncated to " + maxlen +" characters, the maximum allowed.");
	}

	if (field.value.length > maxlen)
	{
  		field.value = field.value.substring(0, maxlen);
	}
} 

</script>

</head>

<html:form action="/submitCourtDispositionAlternatives" target="content"> 
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin='0' leftmargin="0"> 


<%-- BEGIN HEADING TABLE --%> 
<table width='100%'> 
  <tr> 
  	<td align="center" class="header">Juvenile Casework - Conduct Interview<br>Social History Data - Court Disposition Alternatives
  		<logic:equal name="state" value="summary">
  			Summary
  		</logic:equal>
  		<logic:equal name="state" value="confirm">
  			Confirmation
  		</logic:equal>
  	</td>
  </tr>
  <logic:equal name="state" value="confirm">
	  <tr>
	    <td align='center' class='confirm'>Court Disposition Alternatives successfully created.</td>
	  </tr>
	</logic:equal>
</table> 
<%-- END HEADING TABLE --%> 

<%-- BEGIN INSTRUCTION TABLE --%> 
<div class='spacer'></div>
<table width="98%" border="0"> 
  <tr> 
  	<td> 
  		<ul>
  			<logic:equal name="state" value="display">
  				<li>Enter information, then select the Next button.</li>
  				<li>Select Back button to return to the previous page.</li>
  			</logic:equal>
  			<logic:equal name="state" value="summary">
  				<li>Verify information is correct, then select Finish button to save Court Disposition Alternatives.</li>
  				<li>Click Back button to return to the previous page.</li>
  			</logic:equal>
  		</ul>
  	</td>
  </tr> 
</table> 
<%-- END INSTRUCTION TABLE --%> 

<div class='spacer'></div>

<%-- BEGIN HEADER INFO TABLE --%>
<tiles:insert page="../../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
<tiles:put name="headerType" value="casefileheader"/>
</tiles:insert>
<%-- END HEADER INFO TABLE --%>

<div class='spacer'></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
  <tr>
  	<td valign='top'>
  		<%-- BEGIN TAB TABLE --%>
			<tiles:insert page="../../caseworkCommon/casefileTabs.jsp" flush="true">
				<tiles:put name="tabid" value="casefiledetailstab"/>	
				<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
			</tiles:insert>	

  		<%-- BEGIN BORDER TABLE BLUE TABLE --%>
  		<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
  			<tr>
  				<td valign='top' align='center'>

    				<div class='spacer'></div>
  				  <table width='98%' border="0" cellpadding="0" cellspacing="0">
    					<tr>
    						<td>
    							<table width='100%' border="0" cellpadding="0" cellspacing="0" >
    								<tr>
    									<td valign='top'>
	    									<%--tabs start--%>
    										<tiles:insert page="../../caseworkCommon/casefileInfoTabs.jsp" flush="true">
    											<tiles:put name="tabid" value="interviewtab"/>
     												<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
    										</tiles:insert>	
	    									<%--tabs end--%>
    									</td>
    								</tr>
    								<tr>
  								  	<td bgcolor='#33cc66'><img src="/<msp:webapp/>images/spacer.gif" width='5'></td>
  								  </tr>
  					  		</table>

    							<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
    								<tr>
    									<td valign='top' align='center'>
  				
                				<div class='spacer'></div>						
                					<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
                						<tr>
                							<td valign='top'>
                								<table width='100%' border="0" cellpadding="0" cellspacing="0" >
                									<tr>
                										<td valign='top'>
                											<%--tabs start--%>
                											<tiles:insert page="../../caseworkCommon/socialHistoryTabs.jsp" flush="true">
                												<tiles:put name="tabid"><bean:write name="socialHistoryForm" property="currentTab"/></tiles:put>
                											</tiles:insert>
                											<%--tabs end--%>
                										</td>
                									</tr>
                									<tr><td bgcolor='#ff6666'><img src="/<msp:webapp/>images/spacer.gif" width='5'></td></tr>
                								</table>
                
                								<%--begin outer blue border --%>
                								<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableRed">
                									<tr>
                										<td valign='top' align='center'>

               												<div class='spacer'></div>												
                											<table width='98%' border="0" cellpadding="2" cellspacing="1" class='borderTableBlue'>
                												<tr class='detailHead'>
	                												<logic:equal name="state" value="display">
	                													<td colspan='2'>Court Disposition Alternatives (Max. characters allowed for each textbox: 32000)</td>
	                												</logic:equal>
	                												
	                												<logic:notEqual name="state" value="display">
	                													<td colspan='2'>Court Disposition Alternatives</td>
	                												</logic:notEqual>
                												</tr>
                
                												<logic:equal name="state" value="display">
                													<tr class="alternateRow">
                														<td width='1%'>1.&nbsp;
                                              <tiles:insert page="../../caseworkCommon/spellCheckTile.jsp" flush="false">
                                                <tiles:put name="tTextField" value="socialHistoryData.courtDispositionAlternative1" />
                                    						<tiles:put name="tSpellCount" value="spellBtn1" />
                                              </tiles:insert>
																						</td>
                														<td class="formDe"><html:textarea name="socialHistoryForm" property="socialHistoryData.courtDispositionAlternative1" rows="3" style="width:100%" onmouseout="textLimit(this, 32000);" onkeyup="textLimit(this, 32000);" /></td>
                													</tr>
                													<tr>
                														<td>2.&nbsp;
                                              <tiles:insert page="../../caseworkCommon/spellCheckTile.jsp" flush="false">
                                                <tiles:put name="tTextField" value="socialHistoryData.courtDispositionAlternative2" />
                                    						<tiles:put name="tSpellCount" value="spellBtn2" />
                                              </tiles:insert>
																						</td>
                														<td class="formDe"><html:textarea name="socialHistoryForm" property="socialHistoryData.courtDispositionAlternative2" rows="3" style="width:100%" onmouseout="textLimit(this, 32000);" onkeyup="textLimit(this, 32000);" /></td>
                													</tr>
                													<tr class="alternateRow">
                														<td>3.&nbsp;
                                              <tiles:insert page="../../caseworkCommon/spellCheckTile.jsp" flush="false">
                                                <tiles:put name="tTextField" value="socialHistoryData.courtDispositionAlternative3" />
                                    						<tiles:put name="tSpellCount" value="spellBtn3" />
                                              </tiles:insert>
																						</td>
                														<td class="formDe"><html:textarea name="socialHistoryForm" property="socialHistoryData.courtDispositionAlternative3" rows="3" style="width:100%" onmouseout="textLimit(this, 32000);" onkeyup="textLimit(this, 32000);" /></td>
                													</tr>
                												</logic:equal>
                
                												<logic:notEqual name="state" value="display">
                													<tr class="alternateRow">
                														<td width="1%" class="formDeLabel" valign="top">1.</td>
                														<td align="left"><bean:write name="socialHistoryForm" property="socialHistoryData.courtDispositionAlternative1"/></td>
                													</tr>
               														<tr>
                														<td class="formDeLabel" valign="top">2.</td>
                														<td align="left"><bean:write name="socialHistoryForm" property="socialHistoryData.courtDispositionAlternative2"/></td>
                													</tr>
               														<tr class="alternateRow">
                														<td class="formDeLabel" valign="top">3.</td> 
                														<td align="left"><bean:write name="socialHistoryForm" property="socialHistoryData.courtDispositionAlternative3"/></td>
                													</tr>
                												</logic:notEqual>
                											</table>
                
                  										<%-- BEGIN BUTTON TABLE --%>
                  										<div class='spacer'></div>
                  		                <table width="100%">
                  											<logic:equal name="state" value="display">
          							                	<tr>
         								                  	<td align="center">
         								                    	<html:submit property="submitAction"><bean:message key="button.back"/></html:submit>
         								                  		<html:submit property="submitAction" onclick="return validateDispositionAlternatives(this.form);"><bean:message key="button.next"/></html:submit> 
         								                    	<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
         								                  	</td>
          							                	</tr>
                  											</logic:equal>
                											
                  											<logic:equal name="state" value="summary">
                  												<tr>
                  													<td align="center">
                  													  <html:submit property="submitAction"><bean:message key="button.back"/></html:submit>
                  													  <html:submit property="submitAction"><bean:message key="button.finish"/></html:submit> 
                  													  <html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
                  													</td>
                  												</tr>
                  											</logic:equal>
                											
                  											<logic:equal name="state" value="confirm">
                  												<tr>
        								                    <td align="center">
                  														<input type="submit" name="submitAction" value="<bean:message key='button.backToSocialHistoryData'/>"  onclick="changeFormActionURL('/<msp:webapp/>handleSocialHistoryData.do?submitAction=Link&tabId=jpoData', false);">
                  													</td>
                  												</tr>
                  											</logic:equal> 
         						                  </table><%-- END BUTTON TABLE --%>
                									</td>
                								</tr>
                							</table>
                						</td>
                					</tr>
                				</table>
                				<div class='spacer'></div>
                			</td>
                		</tr>
                	</table>
                </td>
              </tr>
            </table>	
            <div class='spacer'></div>
          </td>
        </tr>
      </table>
      <div class='spacer'></div>
    </td>
  </tr>
</table>
<%-- END DISPOSTION TABLE --%>

</body>

</html:form>

<div align='center'><script type="text/javascript">renderBackToTop()</script></div>

</html:html>

