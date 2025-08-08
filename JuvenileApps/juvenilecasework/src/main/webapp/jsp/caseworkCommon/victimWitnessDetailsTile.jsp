<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%-- User selects the Disposition tab --%>
<%--MODIFICATIONS --%>
<%-- 08/09/2007	Uma Gopinath Create JSP --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ page import="naming.Features" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>

<tiles:importAttribute name="petitionDetailsForm"/>   

<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td valign=top>

  		<!-- BEGIN DISPOSITION INFO -->
  		<table align="center" width='98%' border="0" cellpadding="4" cellspacing="1" class="borderTableBlue">
  			<tr>
  				<td valign=top colspan=4 class=detailHead>Victim / Witness Information</td>
  			</tr>
  			<tr>
  				<td valign=top class=formDeLabel width='1%' nowrap>Name</td>
  				<td valign=top class=formDe colspan='3'><bean:write name="petitionDetailsForm" property="victimWitnessRec.name"/></td>
  			</tr>
  			<tr>
  				<td valign=top class=formDeLabel width='1%' nowrap>Association Type</td>
  				<td valign=top class=formDe><bean:write name="petitionDetailsForm" property="victimWitnessRec.associationType"/></td>
  				<td valign=top class=formDeLabel width='1%' nowrap>Sequence #</td>
  				<td valign=top class=formDe><bean:write name="petitionDetailsForm" property="victimWitnessRec.sequenceNum"/></td>
  			</tr>
  			<tr>
  				<td valign=top class=formDeLabel width='1%' nowrap>Relationship to Juvenile</td>
  				<td valign=top class=formDe colspan='3'><bean:write name="petitionDetailsForm" property="victimWitnessRec.relationshipToJuvenile"/></td>
  			</tr>
  			<tr>
  				<td valign=top class=formDeLabel width='1%' nowrap>Transaction #</td>
  				<td valign=top class=formDe><bean:write name="petitionDetailsForm" property="victimWitnessRec.transactionNum"/></td>
  				<td valign=top class=formDeLabel width='1%' nowrap>DA Log #</td>
  				<td valign=top class=formDe><bean:write name="petitionDetailsForm" property="victimWitnessRec.daLogNum"/></td>
  			</tr>
  			<tr>
  				<td valign=top class=formDeLabel width='1%' nowrap>Date of Birth</td>
  				<td valign=top class=formDe><bean:write name="petitionDetailsForm" property="victimWitnessRec.dateOfBirth" formatKey="date.format.mmddyyyy"/></td>
  				<td valign=top class=formDeLabel width='1%' nowrap>Age</td>
  				<td valign=top class=formDe><bean:write name="petitionDetailsForm" property="victimWitnessRec.age"/></td>
  			</tr>
  			<tr>
  				<td valign=top class=formDeLabel width='1%' nowrap>Driver's License #</td>
  				<td valign=top class=formDe><bean:write name="petitionDetailsForm" property="victimWitnessRec.dlNumber"/></td>
  				<td valign=top class=formDeLabel width='1%' nowrap>Driver's License State</td>
  				<td valign=top class=formDe><bean:write name="petitionDetailsForm" property="victimWitnessRec.dlState"/></td>
  			</tr>
  			<tr>
  				<td valign=top class=formDeLabel width='1%' nowrap>Social Security #</td>
  				<td valign=top class=formDe><bean:write name="petitionDetailsForm" property="victimWitnessRec.ssn"/></td>
  				<td valign=top class=formDeLabel width='1%' nowrap>Is the state the complainant?</td>
  				<td valign=top class=formDe><jims2:if name="petitionDetailsForm" property="victimWitnessRec.theStateTheComplainant" value="true" op="equal"><jims2:then>Yes</jims2:then>
  																				<jims2:else>No</jims2:else></jims2:if></td>
  			</tr>
  			<tr>
  				<td valign=top class=formDeLabel width='1%' nowrap>Other ID Numbers</td>
  				<td valign=top class=formDe colspan='3'><bean:write name="petitionDetailsForm" property="victimWitnessRec.otherIDNumbers"/></td>
  			</tr>

        <!-- BEGIN ADDRESS SECTION -->
        <tr><td><div class='spacer'></div></td></tr>
        <tr>
          <td class=detailHead colspan="4">Address Information</td>
        </tr>
        <tr>
          <td class=formDeLabel width="1%" nowrap>Street Number</td>
          <td class=formDe><bean:write name="petitionDetailsForm" property="victimWitnessRec.streetNum"/></td>
          <td class=formDeLabel width="1%" nowrap>Street Name</td>
          <td class=formDe><bean:write name="petitionDetailsForm" property="victimWitnessRec.streetName"/></td>
        </tr>
        <tr>
          <td class=formDeLabel>Street Type</td>
          <td class=formDe></td>
          <td class=formDeLabel>Apt/Suite</td>
          <td class=formDe><bean:write name="petitionDetailsForm" property="victimWitnessRec.aptNum"/></td>
        </tr>
        <tr>
          <td class=formDeLabel>City</td>
          <td class=formDe><bean:write name="petitionDetailsForm" property="victimWitnessRec.city"/></td>
          <td class=formDeLabel>State</td>
          <td class=formDe><bean:write name="petitionDetailsForm" property="victimWitnessRec.state"/></td>
        </tr>
        <tr>
          <td class=formDeLabel>Zip Code</td>
          <td class=formDe><bean:write name="petitionDetailsForm" property="victimWitnessRec.zip"/></td>
  				<td valign=top class=formDeLabel width='1%' nowrap>Phone #</td>
  				<td valign=top class=formDe><bean:write name="petitionDetailsForm" property="victimWitnessRec.phone.formattedPhoneNumber"/></td>
        </tr>
        <tr>
          <td class=formDeLabel>Address Type</td>
          <td class=formDe></td>
          <td class=formDeLabel>County</td>
          <td class=formDe ></td>
        </tr>

        <!-- BEGIN EMPLOYER SECTION -->
        <tr><td><div class='spacer'></div></td></tr>
        <tr>
          <td class=detailHead colspan="4">Employer Information</td>
        </tr>
        <tr>
          <td class=formDeLabel width="1%" nowrap>Employer</td>
          <td class=formDe colspan='3'><bean:write name="petitionDetailsForm" property="victimWitnessRec.employer"/></td>
        </tr>
        <tr>
          <td class=formDeLabel width="1%" nowrap>Occupation</td>
          <td class=formDe colspan='3'><bean:write name="petitionDetailsForm" property="victimWitnessRec.occupation"/></td>
        </tr>
		<logic:equal name="petitionDetailsForm" property="victimWitnessRec.otherInd" value="E">
		<tr><td valign=top class=formDe><img src='/<msp:webapp/>images/spacer.gif' width=1></td></tr>
       <tr>
         <td class=formDeLabel width="1%" nowrap>Street Number</td>
         <td class=formDe><bean:write name="petitionDetailsForm" property="victimWitnessRec.otherStreetNumber"/></td>
         <td class=formDeLabel width="1%" nowrap>Street Name</td>
         <td class=formDe><bean:write name="petitionDetailsForm" property="victimWitnessRec.otherStreetName"/></td>
       </tr>
       <tr>
         <td class=formDeLabel>Street Type</td>
         <td class=formDe></td>
         <td class=formDeLabel>Apt/Suite</td>
         <td class=formDe><bean:write name="petitionDetailsForm" property="victimWitnessRec.otherAptNum"/></td>
       </tr>
       <tr>
         <td class=formDeLabel>City</td>
         <td class=formDe><bean:write name="petitionDetailsForm" property="victimWitnessRec.otherCity"/></td>
         <td class=formDeLabel>State</td>
         <td class=formDe><bean:write name="petitionDetailsForm" property="victimWitnessRec.otherState"/></td>
       </tr>
       <tr>
         <td class=formDeLabel>Zip Code</td>
         <td class=formDe><bean:write name="petitionDetailsForm" property="victimWitnessRec.otherZip"/></td>
 				<td valign=top class=formDeLabel width='1%' nowrap>Phone #</td>
 				<td valign=top class=formDe><bean:write name="petitionDetailsForm" property="victimWitnessRec.otherPhone.formattedPhoneNumber"/></td>
       </tr>
       <tr>
         <td class=formDeLabel>Address Type</td>
         <td class=formDe></td>
         <td class=formDeLabel>County</td>
         <td class=formDe ></td>
       </tr>
	</logic:equal>

	<logic:equal name="petitionDetailsForm" property="victimWitnessRec.otherInd" value="O">
      <!-- BEGIN OTHER ADDRESSES SECTION -->
      <tr><td><br></td></tr>
      <tr>
        <td class=detailHead colspan="4">Other Address Information</td>
      </tr>
     <tr>
         <td class=formDeLabel width="1%" nowrap>Street Number</td>
         <td class=formDe><bean:write name="petitionDetailsForm" property="victimWitnessRec.otherStreetNumber"/></td>
         <td class=formDeLabel width="1%" nowrap>Street Name</td>
         <td class=formDe><bean:write name="petitionDetailsForm" property="victimWitnessRec.otherStreetName"/></td>
       </tr>
       <tr>
         <td class=formDeLabel>Street Type</td>
         <td class=formDe></td>
         <td class=formDeLabel>Apt/Suite</td>
         <td class=formDe><bean:write name="petitionDetailsForm" property="victimWitnessRec.otherAptNum"/></td>
       </tr>
       <tr>
         <td class=formDeLabel>City</td>
         <td class=formDe><bean:write name="petitionDetailsForm" property="victimWitnessRec.otherCity"/></td>
         <td class=formDeLabel>State</td>
         <td class=formDe><bean:write name="petitionDetailsForm" property="victimWitnessRec.otherState"/></td>
       </tr>
       <tr>
         <td class=formDeLabel>Zip Code</td>
         <td class=formDe><bean:write name="petitionDetailsForm" property="victimWitnessRec.otherZip"/></td>
 				<td valign=top class=formDeLabel width='1%' nowrap>Phone #</td>
 				<td valign=top class=formDe><bean:write name="petitionDetailsForm" property="victimWitnessRec.otherPhone.formattedPhoneNumber"/></td>
       </tr>
       <tr>
         <td class=formDeLabel>Address Type</td>
         <td class=formDe></td>
         <td class=formDeLabel>County</td>
         <td class=formDe ></td>
       </tr>
	</logic:equal>
</table>
  
<div class='spacer'></div>
<table border="0" cellpadding=1 cellspacing=1 align=center>
  <tr>
    <td align="center"><input type=button value='Close Window' onclick="window.close();"></td>
  </tr>
</table>

	