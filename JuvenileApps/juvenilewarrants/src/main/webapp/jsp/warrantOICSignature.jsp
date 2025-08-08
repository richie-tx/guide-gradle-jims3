<!--MODIFICATIONS -->
<%-- Used for Request Signature for OIC Warrants --%>
<%-- CShimek	05/19/2004	Create JSP --%>
<%-- LDeen		06/15/2004	Revised per SUC Ver 1.7 --%>
<%-- JFisher    08/23/2005  Update form fields to reflect use of java.util.Date instead of String --%>
<%-- CShimek	10/06/2005	ER#23756 revise titles --%>
<%-- CShimek    02/01/2006  Added hidden field for HelpFile access --%>
<%-- CShimek    06/05/2006  #31773 Add Court display above judges name --%>
<%-- CShimek	12/21/2006  Revised helpfile reference value --%>
<%-- CShimek	03/22/2007  #40475 added missing weight unit annotation --%>
<%-- LDeen		06/04/2007  #42564 changed path to app.js --%>
<%-- RYoung     08/06/2015  #27796 MJCW:  Adapt MJCW and Warrants to IE10 and 11 (Juv Profile - Education UI) --%>

<!DOCTYPE HTML>
<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<!-- SCRIPTING VARIABLES -->
<jsp:useBean id="codeHelper" scope="application" class="ui.juvenilewarrant.helper.JuvenileWarrantListHelper" /><!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />

<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/warrants.css" />
<html:base />
<title>JIMS2 - warrantOICSignature.jsp</title>
<!--THESE TWO LINES FOR STRUTS & JAVASCRIPTS VALIDATIONS-->
<html:javascript formName="juvenileWarrantForm" />
<!-- JAVASCRIPT FILES-->
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
  <tiles:insert page="../js/warrantOICSignatureUpdate.js" />
</head>
<!--BEGIN BODY TAG-->
<body> 
<html:form action="/displayWarrantOICSignatureSummary" focus="signatureCommandId" target="content"> 
<input type="hidden" name="helpFile" value="juvwarnt/juvenile_warrants_final.htm#|124"> 
<!-- BEGIN HEADING TABLE --> 
<table align="center" > 
  <tr> 
    <td class="header" align="center">Update&nbsp;<bean:message key="title.juvWarrantOICSignature"/></td> 
  </tr> 
</table> 
<!-- END HEADING TABLE --> 
<!-- BEGIN INSTRUCTION/REQUIRED INFORMATION TABLE --> 
<table width="98%" align="center"> 
  <tr> 
    <td> <ul> 
        <li>Select Signature Option and click Submit to view summary.</li> 
      </ul></td> 
  </tr> 
</table> 
<!-- BEGIN HEADING/REQUIRED INFORMATION TABLE --> 
<table width=98%> 
  <tr> 
    <td class="required"><bean:message key="prompt.1.diamond"/><bean:message key="prompt.requiredFieldsInstruction"/></td> 
  </tr> 
  <tr> 
    <td align="center" style="color: red"><html:errors></html:errors></td> 
  </tr> 
  <tr>
  		<logic:equal name="juvenileWarrantForm" property="chargeCourt" value="">
  			<td align="center" style="color: red"><html:errors>OIC Warrant is missing the charge court. Please correct.</html:errors></td> 
  		</logic:equal>
  </tr>
</table> 
<!-- END HEADING/REQUIRED INFORMATION TABLE --> 
<!-- BEGIN JUVENILE WARRANT INFORMATION TABLE --> 
<table width="98%" border="0" cellspacing="1" cellpadding="2" align="center"> 
  <tr> 
    <td class=detailHead nowrap colspan=4><bean:message key="prompt.juvenileWarrantInfo" /></td> 
  </tr> 
  <tr> 
    <td class=formDeLabel><bean:message key="prompt.warrantNumber"/></td> 
    <td class=formDe><bean:write name="juvenileWarrantForm" property="warrantNum"/></td> 
    <td class=formDeLabel><bean:message key="prompt.chargeCourt"/></td> 
    <td class=formDe><bean:write name="juvenileWarrantForm" property="chargeCourt"/></td> 
  </tr> 
  <tr> 
    <td class=formDeLabel><bean:message key="prompt.petitionNumber"/></td> 
    <td class=formDe><bean:write name="juvenileWarrantForm" property="petitionNum"/></td> 
    <td class=formDeLabel width="1%" nowrap><bean:message key="prompt.referralNumber"/></td> 
    <td class=formDe><bean:write name="juvenileWarrantForm" property="referralNum"/></td> 
  </tr> 
  <tr> 
    <td class=formDeLabel width="1%" nowrap><bean:message key="prompt.probationOfficerOfRecord"/></td> 
    <td class=formDe colspan="3"><bean:write name="juvenileWarrantForm" property="probationOfficerOfRecordName"/></td> 
  </tr> 
<tr> 
    <td class=formDeLabel width="1%" nowrap><bean:message key="prompt.warrantOriginatorName"/></td> 
    <td class=formDe colspan="3"><bean:write name="juvenileWarrantForm" property="warrantOriginatorName"/></td> 
  </tr> 
  <!-- END JUVENILE WARRANT INFORMATION TABLE --> 
  <tr><td>&nbsp;</td></tr> 
  <tr> 
    <td>&nbsp;</td> 
  </tr> 
  <!-- BEGIN JUVENILE INFORMATION TABLES --> 
  <tr> 
    <td class=detailHead nowrap colspan=4><bean:message key="prompt.juvenileInfo" /></td> 
  </tr> 
  <tr> 
    <td class=formDeLabel><bean:message key="prompt.juvenileName"/></td> 
    <td class=formDe colspan="3"> <%-- <bean:write name="juvenileWarrantForm" property="juvenileName"/> --%> 
      <bean:write name="juvenileWarrantForm" property="juvenileName.lastName"/>, <bean:write name="juvenileWarrantForm" property="juvenileName.firstName"/> <bean:write name="juvenileWarrantForm" property="juvenileName.middleName"/> <bean:write name="juvenileWarrantForm" property="nameSuffix"/> </td> 
  </tr> 
  <tr> 
    <td class=formDeLabel><bean:message key="prompt.aka"/></td> 
    <td class=formDe colspan="3"><bean:write name="juvenileWarrantForm" property="aliasName"/></td> 
  </tr> 
  <tr> 
    <td class=formDeLabel><bean:message key="prompt.dateOfBirth"/></td> 
    <td class=formDe colspan=3> <bean:write name="juvenileWarrantForm" property="dateOfBirth" formatKey="date.format.mmddyyyy" /> </td> 
  </tr> 
  <tr> 
    <td class=formDeLabel><bean:message key="prompt.ssn"/></td> 
    <td class=formDe colspan="3"><bean:write name="juvenileWarrantForm" property="formattedSSN"/></td> 
  </tr> 
  <tr> 
    <td class=formDeLabel><bean:message key="prompt.race"/></td> 
    <td class=formDe><bean:write name="juvenileWarrantForm" property="race"/></td> 
    <td class=formDeLabel><bean:message key="prompt.sex"/></td> 
    <td class=formDe><bean:write name="juvenileWarrantForm" property="sex"/></td> 
  </tr> 
  <tr> 
    <td class=formDeLabel><bean:message key="prompt.height"/></td> 
    <td class=formDe> <logic:notEqual name="juvenileWarrantForm" property="height" value=""> <logic:notEqual name="juvenileWarrantForm" property="heightFeet" value="0"> <bean:write name="juvenileWarrantForm" property="heightFeet"/>ft&nbsp; <bean:write name="juvenileWarrantForm" property="heightInch"/>in&nbsp; </logic:notEqual> </logic:notEqual> </td> 
    <td class=formDeLabel><bean:message key="prompt.weight"/></td> 
    <td class=formDe> <logic:notEqual name="juvenileWarrantForm" property="weight" value=""> <logic:notEqual name="juvenileWarrantForm" property="weight" value="0"> <bean:write name="juvenileWarrantForm" property="weight"/>&nbsp;lbs </logic:notEqual> </logic:notEqual> </td> 
  </tr> 
  <tr> 
    <td class=formDeLabel><bean:message key="prompt.eyeColor"/></td> 
    <td class=formDe colspan="3"> <bean:write name="juvenileWarrantForm" property="eyeColor"/> </td> 
  </tr> 
  <tr> 
    <td class=formDeLabel><bean:message key="prompt.hairColor"/></td> 
    <td class=formDe colspan="3"> <bean:write name="juvenileWarrantForm" property="hairColor"/> </td> 
  </tr> 
  <tr> 
    <td class=formDeLabel><bean:message key="prompt.build"/></td> 
    <td class=formDe colspan=3><bean:write name="juvenileWarrantForm" property="build"/></td> 
  </tr> 
  <tr> 
    <td class=formDeLabel><bean:message key="prompt.complexion"/></td> 
    <td class=formDe colspan="3"> <bean:write name="juvenileWarrantForm" property="complexion"/> </td> 
  </tr> 
  <tr> 
    <td valign="top" class=formDeLabel><bean:message key="prompt.scarsMarks"/></td> 
    <td class=formDe colspan="3"> <logic:notEmpty name="juvenileWarrantForm" property="scarsAndMarksSelected"> <logic:iterate id="scars" name="juvenileWarrantForm" property="scarsAndMarksSelected"> <bean:write name="scars" property="description" /><br> 
      </logic:iterate> </logic:notEmpty> </td> 
  </tr> 
  <tr> 
    <td valign="top" class=formDeLabel><bean:message key="prompt.tattoos"/></td> 
    <td class=formDe colspan="3"> <logic:notEmpty name="juvenileWarrantForm" property="tattoosSelected"> <logic:iterate id="tattoo" name="juvenileWarrantForm" property="tattoosSelected"> <bean:write name="tattoo" property="description" /><br> 
      </logic:iterate> </logic:notEmpty> </td> 
  </tr> 
  <tr> 
    <td class=formDeLabel><bean:message key="prompt.schoolDistrict"/></td> 
    <td class=formDe colspan="3"> <bean:write name="juvenileWarrantForm" property="schoolDistrictName"/> </td> 
  </tr> 
  <tr> 
    <td class=formDeLabel><bean:message key="prompt.schoolName"/></td> 
    <td class=formDe colspan="3"> <bean:write name="juvenileWarrantForm" property="schoolName"/> </td> 
  </tr> 
  <tr> 
    <td valign="top" class=formDeLabel><bean:message key="prompt.cautions"/></td> 
    <td class=formDe colspan="3"> <logic:notEmpty name="juvenileWarrantForm" property="cautionsSelected"> <logic:iterate id="caution" name="juvenileWarrantForm" property="cautionsSelected"> <bean:write name="caution" property="description" /><br> 
      </logic:iterate> </logic:notEmpty> </td> 
  </tr> 
  <tr> 
    <td class=formDeLabel colspan=4><bean:message key="prompt.otherCautionComments"/></td> 
  </tr> 
  <tr> 
    <td class=formDe colspan=4> <bean:write name="juvenileWarrantForm" property="cautionComments"/> </td> 
  </tr> 
  <!-- END JUVENILE INFORMATION TABLES --> 
  <tr> 
    <td>&nbsp;</td> 
  </tr> 
  <!-- BEGIN SIGNATURE STATUS UPDATE INFORMATION TABLE --> 
  <tr class="detailHead"> 
    <!-- bgcolor="#B3C9F5" --> 
    <td colspan=4><bean:message key="prompt.signatureStatus" /> <bean:message key="prompt.update" /></td> 
  </tr> 
  <tr> 
    <td class=formDeLabel><bean:message key="prompt.court"/></td> 
    <td class=formDe colspan="3"><bean:write name="juvenileWarrantForm" property="warrantOriginatorCourt"/></td> 
  </tr> 
  <tr> 
    <td class=formDeLabel><bean:message key="prompt.judgeName"/></td> 
    <td class=formDe colspan="3"><bean:write name="juvenileWarrantForm" property="warrantOriginatorJudge"/></td> 
  </tr> 
  <tr> 
    <td class=formDeLabel><bean:message key="prompt.1.diamond" />&nbsp;<bean:message key="prompt.signatureOption"/></td> 
    <td class=formDe colspan="3"><html:select property="signatureCommandId"> <html:option value=""><bean:message key="select.generic" /></html:option> <html:optionsCollection name="codeHelper" property="signatureCommands" value="code" label="description" /> </html:select> </td> 
  </tr> 
  <tr> 
    <td class=formDeLabel colspan=4><bean:message key="prompt.reasonNotSigned"/></td> 
  </tr> 
  <tr> 
    <td class=formDe colspan="4"><html:textarea property="unsendNotSignedReason" style="width:100%" rows="3"></html:textarea></td> 
  </tr> 
</table> 
<br> 
<!-- BEGIN BUTTON TABLE --> 
<table  width="98%" border="0" align="center"> 
  <tr valign="top"> 
  	<logic:notEqual name="juvenileWarrantForm" property="chargeCourt" value="">
    	<td align="right" width="45%"> <html:submit property="submitAction" onclick="return fieldCheck();"> <bean:message key="button.submit"></bean:message> </html:submit>&nbsp; 
	 </td> 
    </logic:notEqual>
    <td align="left" colspan="1"> <%--  	  <html:form action="/displayGenericSearch.do?warrantTypeUI=reqSigOIC"> --%> 
      <html:reset> <bean:message key="button.reset" /> </html:reset>&nbsp;
      <html:submit property="submitAction"> <bean:message key="button.cancel" /> </html:submit> </td> 
  </tr>  
</table> 
<!-- END BUTTON TABLE --> 
</html:form> <br> 
<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>