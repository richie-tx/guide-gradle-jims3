<!DOCTYPE HTML>

<%--MODIFICATIONS --%>
<%-- 02/15/2006		AWidjaja Create JSP--%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<%@ page import="naming.Features" %>

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

<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- juvenileBenefitsCreate.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<%-- APP JAVASCRIPT FILE --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/benefits.js"></script>
<html:javascript formName="juvenileBenefitsInsuranceForm" />

</head>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin=0 leftmargin="0">

<html:form action="/submitJuvenileBenefitsCreate" target="content">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|204">

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
  <tr>
    <td align="center" class="header" ><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - Create Benefits Information</td>
  </tr>
</table>
<%-- END HEADING TABLE --%>

<%-- BEGIN INSTRUCTION TABLE --%>
<br>
<table width="98%" border="0">
  <tr>
    <td align="left">
      <ul>
       <li>Add new benefit information for juvenile and click Add to List button.</li>
        <li>Select Remove hyperlink to remove benefit information just added.</li>
    	<li>Review information and select the Next button to proceed.</li>
    	<li>Select the Save & Add Insurance button to add insurance information.</li>    	
      </ul>	
    </td>
  </tr>
  <tr>
    <td class="required"><img src="/<msp:webapp/>images/required.gif" title="required" alt="required image" border=0 width=10 height=9> Required Fields</td>		  
	</tr>
</table>
<%-- END INSTRUCTION TABLE --%>

<%-- BEGIN JUVENILE HEADER TABLE --%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="profileheader"/>
</tiles:insert>
<%-- END JUVENILE HEADER TABLE --%>

<%-- BEGIN DETAIL TABLE --%>
<br>
<table width="98%" border="0" cellpadding="0" cellspacing="0" align=center>
	<tr>
		<td>
			<%-- begin green tabs (1st row) --%>
			<table width='100%' border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign=top>
						<tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
							<tiles:put name="tabid" value="interviewinfotab"/>
						</tiles:insert>
					</td>
				</tr>
				<tr>
					<td bgcolor=#33cc66><img src=/<msp:webapp/>images/spacer.gif width=5></td>
				</tr>
			</table><%-- end green tabs --%>

      <%-- begin outer green border --%>
      <table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
        <tr><%-- ### change the next line, if required, to the proper path --%>
          <td valign=top><img src=/<msp:webapp/>images/spacer.gif width=5></td>
        </tr>
        <tr>
          <td valign=top align=center>
            <%-- begin blue tabs (2nd row) --%>
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
            </table><%--end blue tabs --%>

            <%--begin outer blue border --%>
            <table width='98%' border="0" cellpadding="2" cellspacing="0" class='borderTableBlue'>
								<tr><td><img src="/<msp:webapp/>images/spacer.gif" width=5></td></tr>
              <tr>
                <td>
                  <tiles:insert page="../caseworkCommon/juvenileBenefitsDisplayTile.jsp" flush="true">
                  <tiles:put name="tableTitle" value="Previous Benefits Information"/>
                  <tiles:put name="benefits" beanName="juvenileBenefitsInsuranceForm" beanProperty="juvBenefits"/>
                  <tiles:put name="showhide" value="true"/>
                  </tiles:insert>
                </td>
              </tr>
		
              <tr><td><img src="/<msp:webapp/>images/spacer.gif" width=5></td></tr>
	
              <tr>
                <td colspan=4>
                  <%-- ### insert the data table here --%>
                  <logic:notEqual name="juvenileProfileHeader" property="status" value="CLOSED">
                  <table align=center width='98%' border="0" cellpadding="2" cellspacing="0" class='borderTableBlue'>
      							<tr>
      								<td width='98%' valign=top class=detailHead>Juvenile Benefits Information</td>
      							</tr>
      							<tr>
      								<td align=center>
      									<table border=0 cellpadding=2 cellspacing=1 width='100%'>
      										<tr>
  													<td class="formDeLabel" width="12%" nowrap><img src="/<msp:webapp/>images/required.gif" title="Required" alt="required icon" border=0 width=10 height=9>Eligible for Benefits</td>
  													<td class="formDe">Yes<html:radio name="juvenileBenefitsInsuranceForm" property="currentBenefit.eligibleForBenefits" value="true"/>No<html:radio name="juvenileBenefitsInsuranceForm" property="currentBenefit.eligibleForBenefits" value="false"/></td>
  													<td class="formDeLabel"  width="10%" nowrap><img src="/<msp:webapp/>images/required.gif" title="Required" alt="required icon" border=0 width=10 height=9>Receiving Benefits</td>
  													<td class="formDe" colspan="8">Yes<html:radio name="juvenileBenefitsInsuranceForm" property="currentBenefit.receivingBenefits" value="true" styleId="benefitsYes"/>No<html:radio name="juvenileBenefitsInsuranceForm" property="currentBenefit.receivingBenefits" value="false" styleId="benefitsNo"/></td>
      										</tr>
      									
      										<tr class="formDe">      											
	      											<td class=formDeLabel><img src="/<msp:webapp/>images/required.gif" title="required" alt="required image" border=0 width=10 height=9>Type of Eligibility</td>
	      											<td class=formDe>
	      												<html:select name="juvenileBenefitsInsuranceForm" property="currentBenefit.eligibilityTypeId">
	  															<option value="">Please Select</option> 
	  															<html:optionsCollection name="juvenileBenefitsInsuranceForm" property="benefitEligibilityList"  value="code" label="description"/>
	  														</html:select>
	      											</td>
	      											<td  colspan="8">
		      											<table class="hidden" id="receivingBenefitsId">  	      								
			      											<td class=formDeLabel nowrap><img src="/<msp:webapp/>images/required.gif" title="required" alt="required image" border=0 width=10 height=9>Received By</td>
			      											<td class=formDe>
			      											
			      												<html:select name="juvenileBenefitsInsuranceForm" property="currentBenefit.receivedBy" styleId="receivedById">
			      													<logic:iterate id="receivedByIter" name="juvenileBenefitsInsuranceForm" property="benefitsReceivers">
			      														<option value="<bean:write name='receivedByIter' property='formattedName'/>">
			      															<bean:write name="receivedByIter" property="formattedName"/>
				      														<logic:notEmpty name="receivedByIter" property="relationship">
				      															(<bean:write name="receivedByIter" property="relationship"/>)
				      														</logic:notEmpty>
			      														</option>			      													
			      													</logic:iterate>			      												
			      												</html:select>
			      												
			      											</td>
			      											<td class=formDeLabel nowrap>Amount</td>
			      											<td class=formDe><html:text size="4" maxlength="7" property="currentBenefit.receivedAmt" styleId="receivedAmtId"/></td>
			      											<td class=formDeLabel nowrap>ID Number</td>
			      											<td class=formDe><html:text size="15" maxlength="64" property="currentBenefit.idNumber"/></td>
				      									</table>
				      								</td>
      										</tr>
  												<tr>
  													<td colspan=8 align=center>
  														<html:submit property="submitAction" styleId="benefitsAddToList"><bean:message key="button.addToList"/></html:submit>
  													</td>
  												</tr>		
      									</table>
    									</td>
    								</tr>

    								<tr><td>&nbsp;</td></tr>

    								<tr>
    									<td>
      									<table width="100%" bgcolor="#CCCCCC" cellspacing="1">
      										<tr bgcolor="#F0F0F0">
      											<td valign="top" width="10%"></td>
      											<td class="subhead" valign="top">Eligibility Type</td>	
      											<td class="subhead" valign="top">Eligible</td>
      											<td class="subhead" valign="top">Receiving</td>
      											<td class="subhead" valign="top">Received By</td>
      											<td class="subhead" valign="top">Amount</td>
      											<td class="subhead" valign="top">ID No.</td>
      										</tr>
												
  												<%int RecordCounter = 0;
  														String bgcolor = "";%>  
  												
  												<logic:notEmpty name="juvenileBenefitsInsuranceForm" property="newJuvBenefits">
        										<logic:iterate id="benefitsIter" name="juvenileBenefitsInsuranceForm" property="newJuvBenefits">
  													<tr class=<%RecordCounter++;
  														bgcolor = "alternateRow";
  														if (RecordCounter % 2 == 1)
  															bgcolor = "normalRow";
  														out.print(bgcolor);%>>
  														<td valign="top"><a href="/<msp:webapp/>submitJuvenileBenefitsCreate.do?submitAction=<bean:message key='button.remove'/>&selectedValue=<%=(RecordCounter-1)%>">Remove</a></td>
  														<td valign="top"><bean:write name="benefitsIter" property="eligibilityType"/></td>
  														<td>
  															<logic:equal name="benefitsIter" property="eligibleForBenefits" value="true">Yes</logic:equal>
  															<logic:notEqual name="benefitsIter" property="eligibleForBenefits" value="true">No</logic:notEqual>
  														</td>
  														<td>
  															<logic:equal name="benefitsIter" property="receivingBenefits" value="true">Yes</logic:equal>
  															<logic:notEqual name="benefitsIter" property="receivingBenefits" value="true">No</logic:notEqual>
  														</td>
  														<td><bean:write name="benefitsIter" property="receivedBy"/></td>
  														<td><logic:equal name="benefitsIter" property="receivingBenefits" value="true"><bean:write name="benefitsIter" property="receivedAmt"/></logic:equal></td>
  														<td><bean:write name="benefitsIter" property="idNumber"/></td>
  													</tr>
  												</logic:iterate>
  												</logic:notEmpty>
       									</table><br>
      								</td>
      							</tr>
  								</table>
  								</logic:notEqual>
  								<logic:equal name="juvenileProfileHeader" property="status" value="CLOSED">
								<jims2:isAllowed requiredFeatures='<%=Features.JCW_JP_ENABLEMASTERTAB_CLOSEDJUV%>'>
								    <table align=center width='98%' border="0" cellpadding="2" cellspacing="0" class='borderTableBlue'>
      							<tr>
      								<td width='98%' valign=top class=detailHead>Juvenile Benefits Information</td>
      							</tr>
      							<tr>
      								<td align=center>
      									<table border=0 cellpadding=2 cellspacing=1 width='100%'>
      										<tr>
  													<td class="formDeLabel" width="12%" nowrap><img src="/<msp:webapp/>images/required.gif" title="Required" alt="required icon" border=0 width=10 height=9>Eligible for Benefits</td>
  													<td class="formDe">Yes<html:radio name="juvenileBenefitsInsuranceForm" property="currentBenefit.eligibleForBenefits" value="true"/>No<html:radio name="juvenileBenefitsInsuranceForm" property="currentBenefit.eligibleForBenefits" value="false"/></td>
  													<td class="formDeLabel"  width="10%" nowrap><img src="/<msp:webapp/>images/required.gif" title="Required" alt="required icon" border=0 width=10 height=9>Receiving Benefits</td>
  													<td class="formDe" colspan="8">Yes<html:radio name="juvenileBenefitsInsuranceForm" property="currentBenefit.receivingBenefits" value="true" styleId="benefitsYes"/>No<html:radio name="juvenileBenefitsInsuranceForm" property="currentBenefit.receivingBenefits" value="false" styleId="benefitsNo"/></td>
      										</tr>
      									
      										<tr class="formDe">      											
	      											<td class=formDeLabel><img src="/<msp:webapp/>images/required.gif" title="required" alt="required image" border=0 width=10 height=9>Type of Eligibility</td>
	      											<td class=formDe>
	      												<html:select name="juvenileBenefitsInsuranceForm" property="currentBenefit.eligibilityTypeId">
	  															<option value="">Please Select</option> 
	  															<html:optionsCollection name="juvenileBenefitsInsuranceForm" property="benefitEligibilityList"  value="code" label="description"/>
	  														</html:select>
	      											</td>
	      											<td  colspan="8">
		      											<table class="hidden" id="receivingBenefitsId">  	      								
			      											<td class=formDeLabel nowrap><img src="/<msp:webapp/>images/required.gif" title="required" alt="required image" border=0 width=10 height=9>Received By</td>
			      											<td class=formDe>
			      											
			      												<html:select name="juvenileBenefitsInsuranceForm" property="currentBenefit.receivedBy" styleId="receivedById">
			      													<logic:iterate id="receivedByIter" name="juvenileBenefitsInsuranceForm" property="benefitsReceivers">
			      														<option value="<bean:write name='receivedByIter' property='formattedName'/>">
			      															<bean:write name="receivedByIter" property="formattedName"/>
				      														<logic:notEmpty name="receivedByIter" property="relationship">
				      															(<bean:write name="receivedByIter" property="relationship"/>)
				      														</logic:notEmpty>
			      														</option>			      													
			      													</logic:iterate>			      												
			      												</html:select>
			      												
			      											</td>
			      											<td class=formDeLabel nowrap>Amount</td>
			      											<td class=formDe><html:text size="4" maxlength="7" property="currentBenefit.receivedAmt" styleId="receivedAmtId"/></td>
			      											<td class=formDeLabel nowrap>ID Number</td>
			      											<td class=formDe><html:text size="15" maxlength="64" property="currentBenefit.idNumber"/></td>
				      									</table>
				      								</td>
      										</tr>
  												<tr>
  													<td colspan=8 align=center>
  														<html:submit property="submitAction" styleId="benefitsAddToList"><bean:message key="button.addToList"/></html:submit>
  													</td>
  												</tr>		
      									</table>
    									</td>
    								</tr>

    								<tr><td>&nbsp;</td></tr>

    								<tr>
    									<td>
      									<table width="100%" bgcolor="#CCCCCC" cellspacing="1">
      										<tr bgcolor="#F0F0F0">
      											<td valign="top" width="10%"></td>
      											<td class="subhead" valign="top">Eligibility Type</td>	
      											<td class="subhead" valign="top">Eligible</td>
      											<td class="subhead" valign="top">Receiving</td>
      											<td class="subhead" valign="top">Received By</td>
      											<td class="subhead" valign="top">Amount</td>
      											<td class="subhead" valign="top">ID No.</td>
      										</tr>
												
  												<%int RecordCounter = 0;
  														String bgcolor = "";%>  
  												
  												<logic:notEmpty name="juvenileBenefitsInsuranceForm" property="newJuvBenefits">
        										<logic:iterate id="benefitsIter" name="juvenileBenefitsInsuranceForm" property="newJuvBenefits">
  													<tr class=<%RecordCounter++;
  														bgcolor = "alternateRow";
  														if (RecordCounter % 2 == 1)
  															bgcolor = "normalRow";
  														out.print(bgcolor);%>>
  														<td valign="top"><a href="/<msp:webapp/>submitJuvenileBenefitsCreate.do?submitAction=<bean:message key='button.remove'/>&selectedValue=<%=(RecordCounter-1)%>">Remove</a></td>
  														<td valign="top"><bean:write name="benefitsIter" property="eligibilityType"/></td>
  														<td>
  															<logic:equal name="benefitsIter" property="eligibleForBenefits" value="true">Yes</logic:equal>
  															<logic:notEqual name="benefitsIter" property="eligibleForBenefits" value="true">No</logic:notEqual>
  														</td>
  														<td>
  															<logic:equal name="benefitsIter" property="receivingBenefits" value="true">Yes</logic:equal>
  															<logic:notEqual name="benefitsIter" property="receivingBenefits" value="true">No</logic:notEqual>
  														</td>
  														<td><bean:write name="benefitsIter" property="receivedBy"/></td>
  														<td><logic:equal name="benefitsIter" property="receivingBenefits" value="true"><bean:write name="benefitsIter" property="receivedAmt"/></logic:equal></td>
  														<td><bean:write name="benefitsIter" property="idNumber"/></td>
  													</tr>
  												</logic:iterate>
  												</logic:notEmpty>
       									</table><br>
      								</td>
      							</tr>
  								</table>
								</jims2:isAllowed>
								</logic:equal>
  								<%-- end data table area: last statments should be a table/br pair --%>
  								<%-- ****************************************************************** --%>
  								<%-- BEGIN BUTTON TABLE --%>
								<div class=spacer></div>
								<table border="0" width="100%">
								  <tr>
								    <td align="center">
								      <html:submit property="submitAction"><bean:message key="button.back"/></html:submit>
								      <html:submit property="submitAction" styleId="BenefitCreateAdd" ><bean:message key="button.saveAndAddInsurance"/></html:submit>
									  <html:submit property="submitAction"><bean:message key="button.next"/></html:submit>
								      <html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
								    </td>
								  </tr>
								</table>
								<div class=spacer></div>
								<%-- END BUTTON TABLE --%>
                </td>
              </tr>
            </table><br><%-- end outer blue --%>
          </td>
        </tr>
      </table><%-- end outer green --%>
    </td>
  </tr>
</table>
<%-- END DETAIL TABLE --%>

</html:form>

<div align=center><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:html>

