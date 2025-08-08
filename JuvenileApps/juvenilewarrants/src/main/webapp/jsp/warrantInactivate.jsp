<!DOCTYPE HTML>
<!--MODIFICATIONS -->
<%-- 01/26/2005 Hien Rodriguez	Create JSP --%>
<%-- JFisher    08/23/2005  Update form fields to reflect use of java.util.Date instead of String --%>
<%-- CShimek	12/21/2006  revised helpfile reference value--%>
<%-- CShimek	03/22/2007  #40475 added missing weight unit annotation --%>
<%-- CShimek	04/11/2007 	#41031 revised RA href to open in new window --%>
<%-- LDeen		06/04/2007  #42564 changed path to app.js --%>
<%-- CShimek	08/17/2007 	#44540 changed submit button to next button --%>
<%-- RYoung     08/6/2015   #27796 MJCW:  Adapt MJCW and Warrants to IE10 and 11 (Juv Profile - Education UI) --%>

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ page import="naming.PDJuvenileWarrantConstants" %>

<!-- SCRIPTING VARIABLES -->
<jsp:useBean id="codeHelper" scope="application" class="ui.juvenilewarrant.helper.JuvenileWarrantListHelper" />

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<script>
	function getCurrentWarrantStatus()
	{
 		if( "<bean:write name="juvenileWarrantForm" property="warrantStatus" />" == "EXECUTED")
 		{
 	 		return true;
 		}
 	 	return false;
 	}

</script>

<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


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
<title>JIMS2 - warrantInactivate.jsp</title>
<!-- JAVASCRIPT FILES-->
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/warrant_utils.js"></script>

<tiles:insert page="../js/warrantInactivate.js" />
</head>
<!--END HEADER TAG-->

<!--BEGIN BODY TAG-->
<body onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/submitWarrantInactivate" target="content">
  <input type="hidden" name="helpFile" value="juvwarnt/juvenile_warrants_final.htm#|159">

<!-- BEGIN HEADING TABLE -->
<table width="98%">
    <tr>    
   		<td align="center" class="header">Inactivate&nbsp;<bean:message key="title.juvWarrant"/></td> 
   	</tr>
</table>
<!-- END HEADING TABLE -->

<!-- BEGIN ERROR TABLE -->
<table width="98%">
	<tr>
		<td align="center" class="errorAlert"><html:errors></html:errors></td>
	</tr>
</table>
<!-- END ERROR TABLE -->

<!-- BEGIN INSTRUCTION TABLE -->
<table width="98%">
	<tr>
		<td><ul>
			<li>Select Inactivate Reason, then select Next button.</li>
		</ul></td>
	</tr>
	<tr>
		<td class="required"><bean:message key="prompt.1.diamond"/><bean:message key="prompt.requiredFieldsInstruction"/></td>
		<!-- <td class="required">&nbsp;&nbsp;<bean:message key="prompt.requiredFields" /></td>  -->
	</tr>
</table>
<!-- END INSTRUCTION TABLE -->
	
<!-- BEGIN WARRANT INFORMATION TABLE -->
<table width="98%" border="0" cellpadding="2" cellspacing="1" align=center>
	<tr>
		<td class="detailHead" colspan=4>&nbsp;<bean:message key="prompt.warrantInfo" /></td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.warrantNumber" /></td>
		<td class=formDe><bean:write name="juvenileWarrantForm" property="warrantNum" /></td>
		<td class=formDeLabel><bean:message key="prompt.warrantType" /></td>
		<td class=formDe><bean:write name="juvenileWarrantForm" property="warrantType" /></td>
	</tr>
	<%-- these values for JOT based warrants only --%>
	<logic:equal name="juvenileWarrantForm" property="referralNum" value="0">
	  <tr>
		  <td class=formDeLabel><bean:message key="prompt.transactionNumber" /></td>
		  <td class=formDe>
		    <logic:empty name="juvenileWarrantForm" property="transactionNum"></logic:empty>
		    <logic:notEmpty name="juvenileWarrantForm" property="transactionNum">
			    <bean:write name="juvenileWarrantForm" property="transactionNum" />
		    </logic:notEmpty>
		  </td>
		  <td class=formDeLabel><bean:message key="prompt.daLogNumber" /></td>
		  <td class=formDe>
		    <logic:empty name="juvenileWarrantForm" property="daLogNum"></logic:empty>
		    <logic:notEmpty name="juvenileWarrantForm" property="daLogNum">
		    	<bean:write name="juvenileWarrantForm" property="daLogNum" />
		    </logic:notEmpty>
		  </td>
	  </tr>
	</logic:equal>  
<!-- END WARRANT INFORMATION TABLE -->
  <tr><td>&nbsp;</td></tr>
<!-- BEGIN CHARGE INFORMATION TABLE -->
	<tr>
		<td class="detailHead" colspan=4>&nbsp;<bean:message key="prompt.chargeInfo" /></td>
	</tr>
		<!-- display detail info -->
		<%int RecordCounter = 0;%>
	<logic:iterate id="chargeIndex" name="juvenileWarrantForm" property="charges">
		<!-- <tr	<!-- class=<%RecordCounter++;
			if (RecordCounter % 2 == 1)
				out.print("alternateRow");
			else
				out.print("normalRow");%> --> 

				<!-- JOT based warrant -->
<%--					<logic:equal name="chargeIndex" property="referralNum" value="0"> --%>
					<tr>
						<td class=formDeLabel><bean:message	key="prompt.charge" /></td>
						<td class=formDe colspan="3"><bean:write name="chargeIndex" property="offense" /></td>
					</tr>
					<tr>
						<td class=formDeLabel><bean:message key="prompt.petitionNumber" /></td>
						<td class=formDe><bean:write name="chargeIndex" property="petitionNum" /></td>
						<td class=formDeLabel><bean:message key="prompt.chargeSeqNumber" /></td>							
						<td class=formDe><bean:write name="chargeIndex" property="chargeId" /></td>
					</tr>
					<tr>
						<td class=formDeLabel><bean:message key="prompt.chargeNCICNumber" /></td>
						<td class=formDe><bean:write name="chargeIndex" property="offenseCodeId" /></td>
						<td class=formDeLabel><bean:message key="prompt.chargeCourt" /></td>
						<td class=formDe><bean:write name="chargeIndex" property="court" /></td>
					</tr>
					<tr><td>&nbsp;</td></tr>
<%--						</logic:equal>   --%>
		<!-- </tr> -->
	</logic:iterate>
<!-- END CHARGE INFORMATION TABLE -->
  <!-- <tr><td>&nbsp;</td></tr> -->
<!-- BEGIN WARRANT STATUS INFORMATION TABLE -->
	<tr>
		<td class="detailHead" colspan=4>&nbsp;<bean:message key="prompt.warrantStatusInfo" /></td>
 	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.warrantStatus" /></td>
		<td class=formDe>
		   <logic:empty name="juvenileWarrantForm" property="warrantStatusId"></logic:empty>
		   <logic:notEmpty name="juvenileWarrantForm" property="warrantStatusId">
			   <bean:write name="juvenileWarrantForm" property="warrantStatus" />
		   </logic:notEmpty>
		</td>
		<td class=formDeLabel><bean:message key="prompt.dateIssued" /></td>
		<td class=formDe>
			<logic:notEmpty name="juvenileWarrantForm" property="dateOfIssue">
		    	<bean:write name="juvenileWarrantForm" property="dateOfIssue" formatKey="date.format.mmddyyyy" />
		    </logic:notEmpty>
		</td>				
	</tr>		
	<tr>
		<td class=formDeLabel><bean:message key="prompt.fileStampDate" /></td>
		<td class=formDe>
		   <logic:notEmpty name="juvenileWarrantForm" property="fileStampDate">
		   		<bean:write name="juvenileWarrantForm" property="fileStampDate" formatKey="date.format.mmddyyyy" />
		   </logic:notEmpty>
		</td>
		<td class=formDeLabel><bean:message key="prompt.fileStampTime" /></td>
		<td class=formDe>
		   <logic:notEmpty name="juvenileWarrantForm" property="fileStampDate">
			   <bean:write name="juvenileWarrantForm" property="fileStampDate" formatKey="time.format.HHmm" />
		   </logic:notEmpty>
		</td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.fileStampName" /></td>
		<td class=formDe colspan=3>
		    <logic:empty name="juvenileWarrantForm" property="fileStampName"></logic:empty>
		    <logic:notEmpty name="juvenileWarrantForm" property="fileStampName">
			    <bean:write name="juvenileWarrantForm" property="fileStampName.formattedName" />
		    </logic:notEmpty>
		</td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.acknowledgeDate" /></td>
		<td class=formDe>           				
		   <logic:notEmpty name="juvenileWarrantForm" property="warrantAcknowledgementDate">
			   <bean:write name="juvenileWarrantForm" property="warrantAcknowledgementDate" formatKey="date.format.mmddyyyy" />
		   </logic:notEmpty>
		</td>
		<td class=formDeLabel><bean:message key="prompt.acknowledgeTime" /></td>
		<td class=formDe>
		   <logic:notEmpty name="juvenileWarrantForm" property="warrantAcknowledgementDate">
			   <bean:write name="juvenileWarrantForm" property="warrantAcknowledgementDate" formatKey="time.format.HHmm" />
		   </logic:notEmpty>
		</td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.acknowledgeStatus" /></td>
		<td class=formDe>
		   <logic:empty name="juvenileWarrantForm" property="warrantAcknowledgeStatusId"></logic:empty>
		   <logic:notEmpty name="juvenileWarrantForm" property="warrantAcknowledgeStatusId">
			   <bean:write name="juvenileWarrantForm" property="warrantAcknowledgeStatus" />
		   </logic:notEmpty>
		</td>
		<td class=formDeLabel><bean:message key="prompt.signatureStatus" /></td>
		<td class=formDe>
		    <logic:empty name="juvenileWarrantForm" property="warrantSignedStatusId"></logic:empty>
		    <logic:notEmpty name="juvenileWarrantForm" property="warrantSignedStatusId">
			    <bean:write name="juvenileWarrantForm" property="warrantSignedStatus" />
		    </logic:notEmpty>
		</td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.activationDate" /></td>
		<td class=formDe>
	       <logic:notEmpty name="juvenileWarrantForm" property="warrantActivationDate">
			   <bean:write name="juvenileWarrantForm" property="warrantActivationDate" formatKey="date.format.mmddyyyy" />
		   </logic:notEmpty>
		</td>
		<td class=formDeLabel><bean:message key="prompt.activationTime" /></td>
		<td class=formDe>
		    <logic:notEmpty name="juvenileWarrantForm" property="warrantActivationDate">
			    <bean:write name="juvenileWarrantForm" property="warrantActivationDate" formatKey="time.format.HHmm" />
		    </logic:notEmpty>
		</td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.activationStatus" /></td>
		<td class=formDe>
		    <logic:empty name="juvenileWarrantForm" property="warrantActivationStatusId"></logic:empty>
		    <logic:notEmpty name="juvenileWarrantForm" property="warrantActivationStatusId">
			    <bean:write name="juvenileWarrantForm" property="warrantActivationStatus" />
		    </logic:notEmpty>
		</td>
		<td class=formDeLabel><bean:message key="prompt.dateRecalled" /></td>
		<td class=formDe>
		    <logic:notEmpty name="juvenileWarrantForm" property="recallDate">
			    <bean:write name="juvenileWarrantForm" property="recallDate" formatKey="date.format.mmddyyyy" />
		    </logic:notEmpty>
		</td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.reasonRejectedOrUnsent" /></td>
		<td class=formDe colspan="3">
           <logic:empty name="juvenileWarrantForm" property="unsendNotSignedReason"></logic:empty>
           <logic:notEmpty name="juvenileWarrantForm" property="unsendNotSignedReason">
			   <bean:write name="juvenileWarrantForm" property="unsendNotSignedReason" />
		   </logic:notEmpty>
		</td>
	</tr>						
<!-- END WARRANT STATUS INFORMATION TABLE -->

  <tr><td>&nbsp;</td></tr>
<!-- BEGIN JUVENILE INFORMATION TABLE -->
	<tr>
		<td class="detailHead" colspan=4>&nbsp;<bean:message key="prompt.juvenileInfo" /></td>
 	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.juvenileName" /></td>
		<td class=formDe colspan="3">
		    <logic:empty name="juvenileWarrantForm" property="juvenileName"></logic:empty>
		    <logic:notEmpty name="juvenileWarrantForm" property="juvenileName">
		    <bean:write name="juvenileWarrantForm" property="juvenileName.formattedName" />
		    </logic:notEmpty>
		</td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.aka" /></td>
		<td class=formDe colspan="3">
		    <logic:empty name="juvenileWarrantForm" property="aliasName"></logic:empty>
		    <logic:notEmpty name="juvenileWarrantForm" property="aliasName">
		    	<bean:write name="juvenileWarrantForm" property="aliasName" />
		    </logic:notEmpty>
		</td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.juvenileNumber" />
			<input type="hidden" id="juvenileNum" value="<bean:write name="juvenileWarrantForm" property="juvenileNum" />" />
			<input type="hidden" id="warrantNumber" value="<bean:write name="juvenileWarrantForm" property="warrantNum" />" />
			<input type="hidden" id="firstName" value="<bean:write name="juvenileWarrantForm" property="juvenileName.firstName" />" />
			<input type="hidden" id="lastName" value="<bean:write name="juvenileWarrantForm" property="juvenileName.lastName" />" />
			<input type="hidden" id="jpOfficerEmailAddress" value="<bean:write name="juvenileWarrantForm" property="jpOfficerEmail" />" />
		</td>
		<td class=formDe colspan="3">
		    <logic:empty name="juvenileWarrantForm" property="juvenileNum"></logic:empty>
		    <logic:notEmpty name="juvenileWarrantForm" property="juvenileNum">
			    <bean:write name="juvenileWarrantForm" property="juvenileNum" />
		    </logic:notEmpty>
		</td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.ssn" /></td>
		<td class=formDe colspan=3>
			<logic:empty name="juvenileWarrantForm" property="maskedSSN"></logic:empty>
			<logic:notEmpty name="juvenileWarrantForm" property="maskedSSN">
				<bean:write name="juvenileWarrantForm" property="maskedSSN" />
			</logic:notEmpty>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.dateOfBirth" /></td>
		<logic:notEqual name="juvenileWarrantForm" property="warrantTypeId" value="<%= PDJuvenileWarrantConstants.WARRANT_TYPE_DTA %>">       
			<td class=formDe colspan=3>
			    <bean:write name="juvenileWarrantForm" property="dateOfBirth" formatKey="date.format.mmddyyyy" />
			</td>
		</logic:notEqual>
		<logic:equal name="juvenileWarrantForm" property="warrantTypeId" value="<%= PDJuvenileWarrantConstants.WARRANT_TYPE_DTA %>">       
			<td class=formDe>
			    <bean:write name="juvenileWarrantForm" property="dateOfBirth" formatKey="date.format.mmddyyyy" />
			</td>				
	   		 <td class=formDeLabel><bean:message key="prompt.age"/>&nbsp;<bean:message key="prompt.verifiedBy"/></td>
    	   	 <td class=formDe>                           
	    	   	 <bean:write name="juvenileWarrantForm" property="dateOfBirthSource"/>
			 </td>            
	    </logic:equal>  
	</tr>
     <tr>        
        <td class=formDeLabel><bean:message key="prompt.build"/></td>
        <td class=formDe colspan=3>                             
           <bean:write name="juvenileWarrantForm" property="build"/>
        </td>
     </tr>		
	<tr>
		<td class=formDeLabel><bean:message key="prompt.race" /></td>
		<td class=formDe>
                 <logic:empty name="juvenileWarrantForm" property="raceId"></logic:empty>
                 <logic:notEmpty name="juvenileWarrantForm" property="raceId">
			   <bean:write name="juvenileWarrantForm" property="race" />
		   </logic:notEmpty>
		</td>
		<td class=formDeLabel><bean:message key="prompt.sex" /></td>
		<td class=formDe>
		   <logic:empty name="juvenileWarrantForm" property="sexId"></logic:empty>
		   <logic:notEmpty name="juvenileWarrantForm" property="sexId">
			   <bean:write name="juvenileWarrantForm" property="sex" />
		   </logic:notEmpty>
		</td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.height" /></td>
		<td class=formDe>
	       <logic:notEqual name="juvenileWarrantForm" property="height" value="">
 	     		<logic:notEqual name="juvenileWarrantForm" property="heightFeet" value="0">
	           		<bean:write name="juvenileWarrantForm" property="heightFeet"/>ft&nbsp;
	           		<bean:write name="juvenileWarrantForm" property="heightInch"/>in&nbsp;
 	           </logic:notEqual> 
	       </logic:notEqual>
		</td>
		<td class=formDeLabel><bean:message key="prompt.weight" /></td>
		<td class=formDe>		
			<logic:notEqual name="juvenileWarrantForm" property="weight" value="">
				<logic:notEqual name="juvenileWarrantForm" property="weight" value="0">
					<bean:write name="juvenileWarrantForm" property="weight"/>&nbsp;lbs
				</logic:notEqual>	
			</logic:notEqual>	
		</td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.eyeColor" /></td>
		<td class=formDe colspan="3">
		   <bean:write name="juvenileWarrantForm" property="eyeColor" />
		</td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.hairColor" /></td>
		<td class=formDe colspan="3">	
		   <bean:write name="juvenileWarrantForm" property="hairColor" />
		</td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.build" /></td>
		<td class=formDe colspan=3>
		   <bean:write name="juvenileWarrantForm" property="build" />
		</td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.complexion" /></td>
		<td class=formDe colspan="3">
		    <bean:write name="juvenileWarrantForm" property="complexion" />
		</td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.scarsMarks" /></td>				
		<td class=formDe colspan="3">
			<logic:notEmpty name="juvenileWarrantForm" property="scarsAndMarksSelected">
				<logic:iterate id="scar" name="juvenileWarrantForm" property="scarsAndMarksSelected">
					<bean:write name="scar" property="description" /><br>
        		   		</logic:iterate>
	       </logic:notEmpty>
		</td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.tattoos" /></td>
	    <td class=formDe colspan="3">
	       <logic:notEmpty name="juvenileWarrantForm" property="tattoosSelected">
	          <logic:iterate id="tattoo" name="juvenileWarrantForm" property="tattoosSelected">
	             <bean:write name="tattoo" property="description" /><br>
	          </logic:iterate>
	       </logic:notEmpty>
	    </td>
	</tr>
	<!-- JOT based warrant -->
	<logic:equal name="juvenileWarrantForm" property="referralNum" value="0">
		<tr>
			<td class=formDeLabel><bean:message key="prompt.fbiNumber" /></td>
			<td class=formDe>				
			    <bean:write name="juvenileWarrantForm" property="fbiNum" />
			</td>
			<td class=formDeLabel width="1%" nowrap><bean:message key="prompt.sid" /></td>
			<td class=formDe>			
			    <bean:write name="juvenileWarrantForm" property="sid" />
			</td>
		</tr>
	</logic:equal>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.schoolDistrict" /></td>
		<td class=formDe colspan="3">
		    <bean:write name="juvenileWarrantForm" property="schoolDistrictName" />
		</td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.schoolName" /></td>
		<td class=formDe colspan="3">
		    <bean:write name="juvenileWarrantForm" property="schoolName" />
		</td>
	</tr>
	<tr>
		<td valign="top" class=formDeLabel><bean:message key="prompt.cautions" /></td>
		<td class=formDe colspan="3">
	       <logic:notEmpty name="juvenileWarrantForm" property="cautionsSelected">
	          <logic:iterate id="caution" name="juvenileWarrantForm" property="cautionsSelected">
	             <bean:write name="caution" property="description" /><br>
	          </logic:iterate>
	       </logic:notEmpty>
		</td>				
	</tr>
	<tr>
		<td class=formDeLabel colspan=4><bean:message key="prompt.otherCautionComments" /></td>
	</tr>
	<tr>
		<td class=formDe colspan="4">			
		    <bean:write name="juvenileWarrantForm" property="cautionComments" />
		</td>
	</tr>
<!-- END JUVENILE INFORMATION TABLE -->

  <tr><td>&nbsp;</td></tr>
<!-- BEGIN ASSOCIATE INFORMATION TABLES -->
	<tr>
		<td class="detailHead" colspan=4>&nbsp;<bean:message key="prompt.associatesInfo" />
			<span>&nbsp&nbsp&nbsp<a href="#" onClick="OpenOutlook()" >Email JPO & Data Corrections</a>
		</td>
 	</tr>
  <!-- display detail info -->
  <%int RecordCounter2 = 0;%>
	<tr class=formDeLabel>
		<td width="50%" colspan=2><bean:message key="prompt.name" /></td>
		<td width="50%" colspan=2><bean:message key="prompt.relationshipToJuvenile" /></td>
	</tr>		
	<logic:notEmpty name="juvenileWarrantForm" property="associates">
	<logic:iterate id="associateIndex" name="juvenileWarrantForm" property="associates"> 
		<logic:notEqual name="associateIndex" property="associateNum" value="1111111"> <!-- do not display dummy family member -->
	    <tr	class=<%RecordCounter2++;
	      if (RecordCounter2 % 2 == 1)
	      out.print("alternateRow");
	      else
	      out.print("normalRow");%>>
	    
	      <td colspan="2" align="left">
	<%--       <a href="/<msp:webapp/>displayAssociateDetails.do?associateNumber=<bean:write name="associateIndex" property="associateNum"/>"> --%>
				<a href="javascript:openWindow('/<msp:webapp/>displayAssociateDetails.do?associateNumber=<bean:write name="associateIndex" property="associateNum"/>&juvenileNumber=<bean:write name="juvenileWarrantForm" property="juvenileNum" />&relationshipId=<bean:write name="associateIndex" property="relationshipToJuvenileId"/>');">	               							      
	        		<bean:write name="associateIndex" property="associateName.lastName" />, 
	        		<bean:write name="associateIndex" property="associateName.firstName" /> 
	        		<bean:write name="associateIndex" property="associateName.middleName" /></a></td>
	      <td colspan="2" align="left"><bean:write name="associateIndex" property="relationshipToJuvenile" /></td>			 
	    </tr>
	    </logic:notEqual>
  </logic:iterate>
  </logic:notEmpty> 
<!-- END ASSOCIATE INFORMATION TABLES -->
  
	<tr><td>&nbsp;</td></tr>
<%-- this block of information for JOT based warrants only --%>
  <logic:equal name="juvenileWarrantForm" property="referralNum" value="0">
<!-- BEGIN LAW ENFORCEMENT INFO TABLES -->
  	<tr>
  		<td class="detailHead" colspan=4>&nbsp;<bean:message key="prompt.lawEnforcementInfo" /></td>
   	<tr >
  	<tr>
  		<td class=formDeLabel><bean:message key="prompt.officerName" /></td>
  		<td class=formDe colspan="3">								
  		   <bean:write name="juvenileWarrantForm" property="officerName" />
  		</td>
  	</tr>
  	<tr>
  		<td class=formDeLabel><bean:message key="prompt.officerIdNumber" /></td>
  		<td class=formDe>						
  		    <bean:write name="juvenileWarrantForm" property="officerId" />
  		</td>
  		<td class=formDeLabel><bean:message key="prompt.officerIdType" /></td>
  		<td class=formDe>							
  		    <bean:write name="juvenileWarrantForm" property="officerIdType" />
  		</td>
  	</tr>
  	<tr>
  		<td class=formDeLabel><bean:message key="prompt.department" /></td>
  		<td class=formDe colspan="3">								
  		    <bean:write name="juvenileWarrantForm" property="officerAgencyName" />
  		</td>
  	</tr>
  	<tr>
  		<td class=formDeLabel><bean:message key="prompt.oriNumber" /></td>
  		<td class=formDe colspan="3">							
  		    <bean:write name="juvenileWarrantForm" property="leaOriNum" />
  		</td>
  	</tr>
  	<tr>
  		<td class=formDeLabel><bean:message key="prompt.workPhone" /></td>
  		<td class=formDe><bean:write name="juvenileWarrantForm" property="officerPhoneNum.formattedPhoneNumber" /></td>
  		<td class=formDeLabel><bean:message key="prompt.cellPhone" /></td>
  		<td class=formDe><bean:write name="juvenileWarrantForm" property="officerCellNum.formattedPhoneNumber" /></td>
  	</tr>
  	<tr>
  		<td class=formDeLabel><bean:message key="prompt.pager" /></td>
  		<td class=formDe><bean:write name="juvenileWarrantForm" property="officerPager.formattedPhoneNumber" /></td>
  		<td class=formDeLabel><bean:message key="prompt.email" /></td>
  		<td class=formDe>						
  		   <bean:write name="juvenileWarrantForm" property="email" />
  		</td>
  	</tr>
  <!-- BEGIN LAW ENFORCEMENT INFORMATION TABLES -->
    <tr><td>&nbsp;</td></tr>
  </logic:equal>
	
<!-- BEGIN OTHER INFORMATION TABLES -->
	<tr>
		<td class="detailHead" colspan=4>&nbsp;<bean:message key="prompt.otherInfo" /></td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.warrantOriginatorName" /></td>
		<td class=formDe colspan=3>												
		   <bean:write name="juvenileWarrantForm" property="warrantOriginatorName" />
		</td>
	</tr>
	<tr>
		<td class=formDeLabel width="1%" nowrap><bean:message key="prompt.probationOfficerOfRecord" /></td>
		<td class=formDe colspan=3>
		   <bean:write name="juvenileWarrantForm" property="probationOfficerOfRecordName" />
		</td>
	</tr>
<!-- END OTHER INFORMATION TABLES -->

  <tr><td>&nbsp;</td></tr>	
<!-- BEGIN WARRANT INACTIVATE INFORMATION TABLE -->
	<tr>
		<td class="detailHead" colspan=4>&nbsp;<bean:message key="prompt.warrantInactivateInfo" /></td>
	</tr>
  <tr>
    <td class=formDeLabel><bean:message key="prompt.1.diamond" />&nbsp;<bean:message key="prompt.inactivateReason"/></td>
    <td class=formDe colspan=3>
	    <html:select property="recallReasonId">
	      <html:option key="select.generic" value="" />
	      <html:optionsCollection name="codeHelper" property="recallReasons" value="code" label="description" />
	   	</html:select>
	</td>
  </tr>   					
</table>
<!-- END WARRANT INACTIVATE INFORMATION TABLE -->
 
<br>
<!-- BEGIN BUTTON TABLE-->
<table width="98%">
	<tr>
		<td align="center">		
  			<logic:notEmpty name="juvenileWarrantForm" property="warrants">
        		<html:button property="org.apache.struts.taglib.html.BUTTON" onclick="history.go(-1);">
          			<bean:message key="button.back"></bean:message>
        		</html:button>&nbsp;
  			</logic:notEmpty>
			<html:submit property="submitAction" onclick="return fieldCheck(this.form) && disableSubmit(this, this.form);">				
  				<bean:message key="button.next"></bean:message>
  			</html:submit>
  			<html:reset />&nbsp;
  			<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);">
  				<bean:message key="button.cancel"></bean:message>
  			</html:submit>
		</td>
	</tr>
</table>
<!-- END BUTTON TABLE -->
</html:form>
<!-- END FORM -->
<html:errors></html:errors>
<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
