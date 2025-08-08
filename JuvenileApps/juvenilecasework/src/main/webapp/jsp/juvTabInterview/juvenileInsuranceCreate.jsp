<!DOCTYPE HTML>

<%--MODIFICATIONS --%>
<%-- 02/17/2006		AWidjaja Create JSP--%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>



<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=iso-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- juvenileInsuranceCreate.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<%-- APP JAVASCRIPT FILE --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/benefits.js"></script>


</head>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin=0 leftmargin="0">

<html:form action="/submitJuvenileInsuranceCreate" target="content">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|205">

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
  <tr>
    <td align="center" class="header" ><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - Create Insurance Information</td>
  </tr>
</table>
<%-- END HEADING TABLE --%>

<%-- BEGIN INSTRUCTION TABLE --%>
<br>
<table width="98%" border="0">
  <tr>
    <td align="left">
      <ul>
         <li>Add new insurance information for juvenile and click Add to List button.</li>
        <li>Select Remove hyperlink to remove insurance information just added.</li>
    	<li>Review information and select the Next button to proceed.</li>
      </ul>
    </td>
  </tr>
  <tr>
		<td class="required"><img src="/<msp:webapp/>images/required.gif" title="required" alt="required image" border=0 width=10 height=9> Required Fields</td>		  
	</tr>
</table>
<%-- END INSTRUCTION TABLE --%>

<%-- BEGIN DETAIL TABLE --%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="profileheader"/>
</tiles:insert>

<br>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
  <tr>
    <td valign=top>
    	<table width='100%' border="0" cellpadding="0" cellspacing="0" >
			<tr>
				<td valign=top>
					<tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
						<tiles:put name="tabid" value="interviewinfotab"/>
					</tiles:insert>
				</td>
			</tr>
			<tr>
				<td bgcolor=#33cc66><img src="/<msp:webapp/>images/spacer.gif" width=5></td>
			</tr>
      </table>

      <table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
        <tr>
          <td valign=top><img src="/<msp:webapp/>images/spacer.gif" width=5></td>
        </tr>
        <tr>
        	<td>
        		<table width='98%' border="0" align="center" cellpadding="0" cellspacing="0" >
					<tr>
						<td valign=top>
							<tiles:insert page="../caseworkCommon/interviewInfoTabs.jsp" flush="true">
								<tiles:put name="tabid" value='benefitstab' />
							</tiles:insert>
						</td>
					</tr>
					<tr>
						<td bgcolor=#6699FF><img src="/<msp:webapp/>images/spacer.gif" width=5></td>
					</tr>
              <tr>
                <td>
                  <table align=center width='100%' border="0" cellpadding="2" cellspacing="0" class='borderTableBlue'>
							<tr>
								<td valign=top><img src=/<msp:webapp/>images/spacer.gif width=5></td>
							</tr>
							<tr>
								<td align="center">
										<tiles:insert page="../caseworkCommon/juvenileInsuranceDisplayTile.jsp" flush="true">
											<tiles:put name="tableTitle" value="Previous Insurance Information"/>
											<tiles:put name="insurances" beanName="juvenileBenefitsInsuranceForm" beanProperty="juvInsurances"/>
											<tiles:put name="showhide" value="true"/>
										</tiles:insert>
								</td>
							</tr>

                    <tr><td><img src="/<msp:webapp/>images/spacer.gif" width=5></td></tr>
																			
                  <tr>
							<td>
								<table align=center width='98%' border="0" cellpadding="2" cellspacing="0" class='borderTableBlue'>
          						<tr>
          							<td width='98%' valign=top class=detailHead>Juvenile Insurance Information</td>
          						</tr>
          						<tr>
          							<td align=center>
          								<table border=0 cellpadding=2 cellspacing=1 width='100%'>
          									<tr>
          										<td class=formDeLabel nowrap width="20%"><img src="/<msp:webapp/>images/required.gif" title="required" alt="required image" border=0 width=10 height=9>Insurance Type</td>
          										<td class=formDe>
          											<html:select name="juvenileBenefitsInsuranceForm" property="currentInsurance.insuranceTypeId">
          												<option value="">Please Select</option> 
															<html:optionsCollection name="juvenileBenefitsInsuranceForm" property="insuranceTypeList"  value="code" label="description"/>
          											</html:select>
          										</td>
												</tr>                              
												<tr id='insCarrier' class='hidden'>																																		
													<td class=formDeLabel width="1%" nowrap><img src="/<msp:webapp/>images/required.gif" title="required" alt="required image" border=0 width=10 height=9>Insurance Carrier</td>
													<td class=formDe colspan=3><html:text name="juvenileBenefitsInsuranceForm" property="currentInsurance.insuranceCarrier" size="60"/></td>
												</tr>                              
												<tr id='policyNum' class='hidden'>
													<td class=formDeLabel nowrap>Policy #</td>
													<td class=formDe><html:text name="juvenileBenefitsInsuranceForm" property="currentInsurance.policyNumber" maxlength="64" size="64"/></td>
												</tr>
												<tr>
													<td colspan=4 align='center'>
														<html:submit styleId="juvBenefInsurFormSubmit" property="submitAction" styleId="validateInsuranceAddToList"><bean:message key="button.addToList"/></html:submit>
													</td>
												</tr>
											</table>
										</td>
          						</tr>
          						<tr><td>&nbsp;</td></tr>
          						<tr>
          							<td>
          								<table width='100%' bgcolor=#cccccc cellspacing=1 >
          									<tr bgcolor=#f0f0f0>
          										<td valign=top width='1%'></td>
          										<td nowrap class="subhead" valign=top>Insurance Type</td>
          										<td nowrap class="subhead" valign=top>Policy #</td>
          										<td class="subhead" valign=top>Insurance Carrier</td>
          									</tr>
												
												<%int RecordCounter = 0;
														String bgcolor = "";%>  
												
												<logic:notEmpty name="juvenileBenefitsInsuranceForm" property="newJuvInsurances">
      										<logic:iterate id="insuranceIter" name="juvenileBenefitsInsuranceForm" property="newJuvInsurances">
													<tr class=<%RecordCounter++;
														bgcolor = "alternateRow";
														if (RecordCounter % 2 == 1)
															bgcolor = "normalRow";
														out.print(bgcolor);%>>
														<td valign="top" width='1%'><a href="/<msp:webapp/>submitJuvenileInsuranceCreate.do?submitAction=<bean:message key='button.remove'/>&selectedValue=<%=(RecordCounter-1)%>">Remove</a></td>
														<td class="bodyText" valign=top><bean:write name="insuranceIter" property="insuranceType"/></td>
														<td class="bodyText"><bean:write name="insuranceIter" property="policyNumber"/></td>
														<td class="bodyText"><bean:write name="insuranceIter" property="insuranceCarrier"/></td>
													
													</tr>
												</logic:iterate>
												</logic:notEmpty>
												
          								</table><br>
										</td>
									</tr>
								</table>
                                <%-- BEGIN BUTTON TABLE --%>
								<div class=spacer></div>
								<table border="0" width="100%">
								  <tr>
								    <td align="center">
								      <html:submit property="submitAction"><bean:message key="button.back"/></html:submit>
								      <html:submit property="submitAction" styleId="InsureCreateNext" ><bean:message key="button.next"/></html:submit>
								      <html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
								    </td>
								  </tr>
								</table>
								<div class=spacer></div>
								<%-- END BUTTON TABLE --%> 
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table><br>
    </td>
  </tr>
</table>
<%-- END DETAIL TABLE --%>


</html:form>

<div align=center><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:html>
