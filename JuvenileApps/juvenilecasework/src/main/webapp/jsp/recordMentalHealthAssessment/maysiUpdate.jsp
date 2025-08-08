<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%--DWilliamson	07/31/2007	Create JSP --%>

<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>


<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
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
<script type="text/javascript" src="/<msp:webapp/>js/casework_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>

<script type="text/javascript">

function validateThisForm( theForm )
{
	if(theForm.reasonNotDoneId.selectedIndex == 0)
	{
		alert = "Identify reason MAYSI was not administered is required." ;
		return false;
	}
	return( true ) ;
}

//imitates the reset button by clearing the text fields only
//param: theForm - the form object 
function clearForm(theForm) 
{
 	// Reset the MAYSI Dropdown
	theForm.reasonNotDoneId.value = "" ;
}

</script>

<title><bean:message key="title.heading" /> - maysiUpdate.jsp</title>
</head>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);" >
<html:form action="/displayNewMAYSISummary" target="content">

<!-- BEGIN HEADING TABLE -->
<table width='100%'>
  <tr>
    <td align="center" class="header"><bean:message key="title.juvenileCasework" />&nbsp;-&nbsp;<bean:message key="title.juvenileProfile" />
                                      <bean:message key="title.update" />
                                      <bean:message key="title.mentalHealthAssessment" /> 
    <td>
  </tr>
</table>
<table width="100%">
	<TBODY>
        <tr><td>&nbsp;</td></tr> 
		<tr>
			<td align="center" class="errorAlert"><html:errors></html:errors></td>
		</tr>
	</TBODY>
</table>

<!-- END HEADING TABLE -->

<!-- BEGIN INSTRUCTION TABLE -->
<br>
<table width="98%" border="0">
	<tr>
		<td>
			<ul>
				<li>Complete Mental Health Assessment Questionaire then select Next button to view Summary.</li>
				<li>Select Back button to return to previous page.</li>
			</ul>
		</td>
	</tr>
	<tr> 
		<td class="required"><span class=diamond></span>&nbsp;Required Fields</td> 
	</tr> 
</table>
<!-- END INSTRUCTION TABLE -->

<!-- BEGIN HEADER INFO TABLE -->
<table align="center" cellpadding=1 cellspacing=0 border=0 width='98%'>
	<tr>
		<td bgcolor='#cccccc'>
<%-- BEGIN JUVENILE HEADER INCLUDE --%>
			<tiles:insert page="/jsp/caseworkCommon/juvenileHeaderDetails.jsp" > 
				<tiles:put name="headerType" value="profileheader"/>
			</tiles:insert>
<%-- END JUVENILE HEADER INCLUDE  --%>
		</td>
	</tr>
</table>
<!-- END HEADER INFO TABLE -->

<br>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
  <tr>
    <td valign=top>
   		<table width='100%' border="0" cellpadding="0" cellspacing="0" >
    		<tr>
    			<td valign=top>
    				<tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
    				<tiles:put name="tabid" value="interviewinfotab"/>
    				<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
    			</tiles:insert>		
    			</td>
    		</tr>
    		<tr>
    			<td bgcolor='#33cc66'><img src="/<msp:webapp/>images/spacer.gif" width=5></td>
    		</tr>
    	    <tr>
    	  	    <td>
      			    <table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
      				    <tr>
      					    <td valign=top><img src="/<msp:webapp/>images/spacer.gif" width=5></td>
      				    </tr>
      				    <tr>
      					    <td valign=top align=center>
  								<table width='98%' border="0" cellpadding="0" cellspacing="0" >
  									<tr>
  										<td valign=top>
  											<tiles:insert page="../caseworkCommon/interviewInfoTabs.jsp" flush="true">
  												<tiles:put name="tabid" value="mentalhealthtab"/>
  												<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
  											</tiles:insert>	
  										</td>
  									</tr>
  									<tr>
  						  			<td bgcolor='#6699FF'><img src="/<msp:webapp/>images/spacer.gif" width=5></td>
 						  			</tr>
  								</table>
                 	            <table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
  									<tr>
  									    <td><img src='../../common/images/spacer.gif' width=5></td>
  									</tr>
                                    <tr>
             					        <td valign=top align=center>
        								    <table width='98%' border="0" cellpadding="0" cellspacing="0" >
        									    <tr>
        										    <td valign=top>
        											    <tiles:insert page="../caseworkCommon/mentalHealthTabs.jsp" flush="true">
        												    <tiles:put name="tabid" value="maysi"/>
        												    <tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
        											    </tiles:insert>	
        										    </td>
        									    </tr>
        									    <tr>
        						  			        <td bgcolor='#ff6666'><img src="/<msp:webapp/>images/spacer.gif" width=5></td>
       						  			       </tr>
        								    </table>   	
											<table width='98%' align=center border="0" cellpadding="0" cellspacing="0" class="borderTableRed"> 
												<tr>
													<td valign=top><img src="/<msp:webapp/>images/spacer.gif" width=5></td>
												</tr>
												<tr>
													<td valign=top align=center>
															<div class='spacer'></div>
														<table align="center" width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
															<tr>
																<td class=detailHead><bean:message key="prompt.mentalHealthAssessment" /></td>
															</tr>
															<tr id="editTable" class=hidden>
																<td align=center>
																	<table cellpadding=4 cellspacing=1 width='100%'>
																		<tr>
																			<td class=formDeLabel width='1%' nowrap><bean:message key="prompt.sex" /></td>
																			<td class=formDe><bean:write name="mentalHealthForm" property="sex"/></td>
																			<td class=formDeLabel width='1%' nowrap><bean:message key="prompt.race" /></td>
																			<td class=formDe><bean:write name="mentalHealthForm" property="race"/></td>
																		</tr>
																		<tr>
																			<td class=formDeLabel width='1%' nowrap><bean:message key="prompt.wasTheMaysiAdministered" />?</td>
																			<td class=formDe colspan=3><bean:write name="mentalHealthForm" property="administer"/></td>
																		</tr>
																		<tr>
																			<td class=formDeLabel width='1%'><span class=diamond></span><bean:message key="prompt.reasonMaysiNotAdministered" /></td>
																			<td class=formDe colspan=3>
                                                                                <html:select property="reasonNotDoneId">
                    											                    <html:option value=""><bean:message key="select.generic" /></html:option>
                     											                    <html:optionsCollection property="reasonsNotDone" value="code" label="description" /> 
                   												                </html:select>
																			</td>
																		</tr>
																		<tr>					
																			<td class=formDeLabel width='1%' nowrap><bean:message key="prompt.referralNumber" /></td>
																			<td class=formDe colspan=3><bean:write name="mentalHealthForm" property="referralNum"/></td>
																		</tr>
																		<tr>	
																			<td class=formDeLabel width='1%' nowrap><bean:message key="prompt.locationUnit" /></td>
																			<td class=formDe colspan=3><bean:write name="mentalHealthForm" property="locationUnit"/></td>
																		</tr>
																		<tr>					
																			<td class=formDeLabel><bean:message key="prompt.howLongHasYouthBeenHere" />?</td>
																			<td class=formDe colspan=3><bean:write name="mentalHealthForm" property="lengthOfStay"/></td>
																		</tr>
																		<tr>	
																			<td class=formDeLabel nowrap><bean:message key="prompt.typeOfFacility" /></td>
																			<td class=formDe colspan=3><bean:write name="mentalHealthForm" property="facilityType"/></td>
																		</tr>
																		<tr>
																			<td nowrap width='1%' class=formDeLabel><bean:message key="prompt.hasYouthTakenMAYSIBefore" />?</td>
																			<td class=formDe colspan=3><bean:write name="mentalHealthForm" property="hasPreviousMAYSI"/></td>                                          
																		</tr>
																	</table>
																</td>
															</tr>
  								                        </table>
                                                        <div class=spacer></div>
														<table border="0" width="100%">
														  <tr>
															<td align="center">
															  <html:submit property="submitAction"><bean:message key="button.back"/></html:submit>
															  <html:submit property="submitAction" onclick="return( validateThisForm(this.form) );">
																	<bean:message key="button.next"></bean:message>
															  </html:submit>
															  <html:button onclick="clearForm(this.form)" property="org.apache.struts.taglib.html.BUTTON"><bean:message key="button.reset" /></html:button>
															  <html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
															  <%--input type="reset" value="Reset" name="refresh" onClick="reloadPage()"--%>
															</td>
														  </tr>
														</table>
                                                        <div class=spacer></div>
                                                    </td>
                                                </tr>
                                            </table>
                                        </td>
                                     </tr>
                                 </table>  
  							</td>
  						</tr>
  					</table>
                </td>
            </tr>
         </table>
      </td>
   </tr>
</table>
<br>
</html:form>
<div align=center><script type="text/javascript">renderBackToTop()</script></div>
<br>
</body>
</html:html>

