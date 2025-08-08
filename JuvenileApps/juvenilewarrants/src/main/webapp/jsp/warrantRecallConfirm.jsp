<!DOCTYPE HTML>
<!--This JSP will be used to recall warrants.-->
<!--MODIFICATIONS -->
<%-- HRodriguez		02/08/2005	Create JSP --%>
<%-- LDeen			03/23/2005	Revise jsp to add logic tags --%>
<%-- HRodriguez		06/06/2005	Change to new look --%>
<%-- JFisher    	08/23/2005  Update form fields to reflect use of java.util.Date instead of String --%>
<%-- DWilliamson    01/09/2006  Changed streetNumber to streetNum per ER #25408 --%>
<%-- CShimek        02/08/2006  Added hidden helpFile field --%>
<%-- CShimek		12/21/2006  revised helpfile reference value--%>
<%-- CShimek		03/22/2007  #40475 added missing weight unit annotation --%>
<%-- CShimek		04/11/2007 	#41031 revised RA href to open in new window --%>
<%-- LDeen			06/04/2007  #42564 changed path to app.js --%>
<%-- CShimek		08/17/2007 	#44540 revised page to also function as summary page --%>
<%-- RYoung         08/06/2015  #27796 MJCW:  Adapt MJCW and Warrants to IE10 and 11 (Juv Profile - Education UI) --%>

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page import="naming.PDJuvenileWarrantConstants" %>
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
<title><bean:message key="title.heading" /> - warrantRecallConfirm.jsp</title>
<!-- JAVASCRIPT FILES-->
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
</head>
<!--END HEADER TAG-->
<body>

<html:form action="/submitWarrantRecall" target="content">
<logic:equal name="juvenileWarrantForm" property="action" value="summary">
	<input type="hidden" name="helpFile" value="juvwarnt/juvenile_warrants_final.htm#|210">	
</logic:equal>	
<logic:notEqual name="juvenileWarrantForm" property="action" value="summary">
	<input type="hidden" name="helpFile" value="juvwarnt/juvenile_warrants_final.htm#|101">	
</logic:notEqual>	

<!-- BEGIN HEADING TABLE -->
<table width="98%">
	<tr>
		<td align="center" class="header">
			Recall&nbsp;<bean:message key="title.juvWarrant"/>
   			<logic:equal name="juvenileWarrantForm" property="action" value="summary">
   				<bean:message key="prompt.summary" />
   			</logic:equal>	
   			<logic:notEqual name="juvenileWarrantForm" property="action" value="summary">
   				<bean:message key="prompt.confirmation" />
   			</logic:notEqual>				
		</td>
	</tr>
</table>
<!-- END HEADING TABLE -->

<!-- BEGIN INSTRUCTION TABLE -->
<table width="98%">
   	<tr>
   		<logic:equal name="juvenileWarrantForm" property="action" value="summary">
			<td>
				<ul>
					<li>Verify that information is correct and select Finish button to recall Warrant.</li>				
					<li>If any changes are needed, select Back button.</li>
				</ul>
			</td>   				
   		</logic:equal>	
   		<logic:notEqual name="juvenileWarrantForm" property="action" value="summary">
   			<td align="center" class=confirm>Juvenile Warrant successfully recalled.</td>
   		</logic:notEqual>	   	
  	</tr>	
</table>
<!-- END INSTRUCTION TABLE -->

<table width="98%" border="0" cellpadding="4" cellspacing="1" align="center">	
<!-- BEGIN WARRANT INFORMATION SECTION -->
	<tr>
		<td class="detailHead" colspan="4"><bean:message key="prompt.warrantInfo" /></td>
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
			<td class=formDeLabel width=1% nowrap><bean:message key="prompt.transactionNumber" /></td>
			<td class=formDe>
				<bean:write name="juvenileWarrantForm" property="transactionNum" /></td>				  
			<td class=formDeLabel width=1% nowrap><bean:message key="prompt.daLogNumber" /></td>
			<td class=formDe>
				<bean:write name="juvenileWarrantForm" property="daLogNum" /></td>
		</tr>
	</logic:equal>
<!-- END WARRANT INFORMATION SECTION -->
	<tr><td colspan="4">&nbsp;</td></tr>
	
<!-- BEGIN CHARGE INFORMATION SECTION -->
	<tr>
		<td class="detailHead" colspan="4"><bean:message key="prompt.chargeInfo" /></td>
	</tr>	
	<!-- display detail info -->		
	<logic:notEmpty name="juvenileWarrantForm" property="charges">	
	<logic:iterate id="chargeIndex" name="juvenileWarrantForm" property="charges">		
		<!-- JOT based warrant -->
<%--	<logic:equal name="chargeIndex" property="referralNum" value="0"> --%>
		<logic:notEqual name="juvenileWarrantForm" property="warrantTypeId" value="OIC">
		<logic:notEqual name="juvenileWarrantForm" property="warrantTypeId" value="VOP">
			<tr>
				<td class=formDeLabel><bean:message	key="prompt.charge" /></td>
				<td class=formDe colspan="3"><bean:write name="chargeIndex" property="offense" /></td>
			</tr>
			<tr>
				<td class=formDeLabel><bean:message key="prompt.petitionNumber" /></td>
				<td class=formDe>
					<bean:write name="chargeIndex" property="petitionNum" />
				</td>								
				<td class=formDeLabel width=1% nowrap><bean:message key="prompt.chargeSeqNumber" /></td>							
				<td class=formDe>
	  				<bean:write name="chargeIndex" property="chargeId" />
				</td>
			</tr>
			<tr>
				<td class=formDeLabel width=1% nowrap><bean:message key="prompt.chargeNCICNumber" /></td>
				<td class=formDe>
					<bean:write name="chargeIndex" property="offenseCodeId" />
				</td>								
				<td class=formDeLabel><bean:message key="prompt.chargeCourt" /></td>
				<td class=formDe>
					 <bean:write name="chargeIndex" property="court" />
				</td>
			</tr>
		</logic:notEqual>			
		</logic:notEqual>			
<%--	</logic:equal> --%>

		<!-- JJS based warrant -->
<%--	<logic:notEqual name="chargeIndex" property="referralNum" value="0"> --%>
		<logic:equal name="juvenileWarrantForm" property="warrantTypeId" value="OIC">
			<tr>
				<td class=formDeLabel><bean:message key="prompt.charge" /></td>
				<td class=formDe colspan="3"><bean:write name="chargeIndex" property="offense" /></td>
			</tr>
			<tr>
				<td class=formDeLabel><bean:message key="prompt.petitionNumber" /></td>
				<td class=formDe><bean:write name="chargeIndex" property="petitionNum" /></td>
				<td class=formDeLabel><bean:message key="prompt.court" /></td>
				<td class=formDe><bean:write name="chargeIndex" property="court" /></td>
			</tr>
		</logic:equal>
		<logic:equal name="juvenileWarrantForm" property="warrantTypeId" value="VOP">
			<tr>
				<td class=formDeLabel><bean:message key="prompt.charge" />:</td>
				<td class=formDe colspan="3"><bean:write name="chargeIndex" property="offense" /></td>
			</tr>
			<tr>
				<td class=formDeLabel><bean:message key="prompt.petitionNumber" /></td>
				<td class=formDe><bean:write name="chargeIndex" property="petitionNum" /></td>
				<td class=formDeLabel><bean:message key="prompt.court" /></td>
				<td class=formDe><bean:write name="chargeIndex" property="court" /></td>
			</tr>
		</logic:equal>
<%--	</logic:notEqual>		--%>

		<bean:size id="chargesSize" name="juvenileWarrantForm" property="charges"/>
		<%--if more than one charge, render spacer row --%>
		<logic:greaterThan name="chargesSize" value="1">
			<tr><td class=formDe colspan="4">&nbsp;</td></tr>
		</logic:greaterThan>
	</logic:iterate>
	</logic:notEmpty>
<!-- END CHARGE INFORMATION SECTION -->
	<tr><td colspan="4">&nbsp;</td></tr>
	
<!-- BEGIN WARRANT STATUS INFORMATION SECTION -->
	<tr>
		<td class="detailHead" colspan="4"><bean:message key="prompt.warrantStatusInfo" /></td>
	</tr>	
	<tr>
		<td class=formDeLabel><bean:message key="prompt.warrantStatus" /></td>
		<td class=formDe>
		   <bean:write name="juvenileWarrantForm" property="warrantStatus" />
		</td>		
		<td class=formDeLabel><bean:message key="prompt.dateIssued" /></td>
		<td class=formDe>
		    <bean:write name="juvenileWarrantForm" property="dateOfIssue" formatKey="date.format.mmddyyyy" />
		</td>				
	</tr>		
	<tr>
		<td class=formDeLabel><bean:message key="prompt.fileStampDate" /></td>
		<td class=formDe>
			<bean:write name="juvenileWarrantForm" property="fileStampDate" formatKey="date.format.mmddyyyy" />
		</td>
		<td class=formDeLabel width=1% nowrap><bean:message key="prompt.fileStampTime" /></td>
		<td class=formDe>
		   <bean:write name="juvenileWarrantForm" property="fileStampDate" formatKey="time.format.HHmm" />
		</td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.fileStampName" /></td>
		<td class=formDe colspan="3">
		    <bean:write name="juvenileWarrantForm" property="fileStampName.formattedName" />
		</td>
	</tr>
	<!-- display only for JOT based Directive to Apprehend warrant -->
	<logic:equal name="juvenileWarrantForm" property="warrantTypeId" value="DTA">
	<tr>
		<td class=formDeLabel><bean:message key="prompt.acknowledgeDate" /></td>
		<td class=formDe><bean:write name="juvenileWarrantForm" property="warrantAcknowledgementDate" formatKey="date.format.mmddyyyy" /></td>
		<td class=formDeLabel><bean:message key="prompt.acknowledgeTime" /></td>
		<td class=formDe><bean:write name="juvenileWarrantForm" property="warrantAcknowledgementDate" formatKey="time.format.HHmm" /></td>
	</tr>
	</logic:equal>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.signatureStatus" /></td>
		<td class=formDe><bean:write name="juvenileWarrantForm" property="warrantSignedStatus" /></td>
		
		<!-- display nothing if not JOT based Directive to Apprehend warrant -->
		<logic:notEqual name="juvenileWarrantForm" property="warrantTypeId" value="DTA">
			<td class=formDe colspan="2">&nbsp;</td>
		</logic:notEqual>		
		<!-- display only for JOT based Directive to Apprehend warrant -->
		<logic:equal name="juvenileWarrantForm" property="warrantTypeId" value="DTA">		
			<td class=formDeLabel><bean:message key="prompt.acknowledgeStatus" /></td>
			<td class=formDe><bean:write name="juvenileWarrantForm" property="warrantAcknowledgeStatus" /></td>
		</logic:equal>		
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.activationDate" /></td>
		<td class=formDe><bean:write name="juvenileWarrantForm" property="warrantActivationDate" formatKey="date.format.mmddyyyy" /></td>
		<td class=formDeLabel><bean:message key="prompt.activationTime" /></td>
		<td class=formDe><bean:write name="juvenileWarrantForm" property="warrantActivationDate" formatKey="time.format.HHmm" /></td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.activationStatus" /></td>
		<td class=formDe><bean:write name="juvenileWarrantForm" property="warrantActivationStatus" /></td>
		<td class=formDeLabel><bean:message key="prompt.dateRecalled" /></td>
		<td class=formDe><bean:write name="juvenileWarrantForm" property="recallDate" formatKey="date.format.mmddyyyy" /></td>
	</tr>						
	<tr>
		<td class=formDeLabel width=1% nowrap><bean:message key="prompt.reasonRejectedOrUnsent" /></td>
		<td class=formDe colspan="3">
           <logic:equal name="juvenileWarrantForm" property="unsendNotSignedReason" value=""></logic:equal>               						
		   <bean:write name="juvenileWarrantForm" property="unsendNotSignedReason" />
		</td>
	</tr>						
<!-- END WARRANT STATUS INFORMATION SECTION -->
	<tr><td colspan="4">&nbsp;</td></tr>
	
<!-- BEGIN JUVENILE INFORMATION SECTION -->
	<tr>
		<td class="detailHead" colspan="4"><bean:message key="prompt.juvenileInfo" /></td>
	</tr>	
	<tr>
		<td class=formDeLabel><bean:message key="prompt.juvenileName" /></td>
		<td class=formDe colspan="3">
		    <logic:equal name="juvenileWarrantForm" property="juvenileName" value=""></logic:equal>				
		    <bean:write name="juvenileWarrantForm" property="juvenileName.formattedName" />
		</td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.aka" /></td>
		<td class=formDe colspan="3">
		    <logic:equal name="juvenileWarrantForm" property="aliasName" value=""></logic:equal>				
		    <bean:write name="juvenileWarrantForm" property="aliasName" />
		</td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.juvenileNumber" /></td>
		<td class=formDe colspan="3">
		    <logic:equal name="juvenileWarrantForm" property="juvenileNum" value=""></logic:equal>				
		    <bean:write name="juvenileWarrantForm" property="juvenileNum" />
		</td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.ssn" /></td>
		<td class=formDe colspan="3">
			<bean:write name="juvenileWarrantForm" property="maskedSSN" />
<!-- 	<td class=formDeLabel><bean:message key="prompt.phone" /></td>
		<td class=formDe>
		    <logic:equal name="juvenileWarrantForm" property="phoneNum" value=""></logic:equal>				
		   <bean:write name="juvenileWarrantForm" property="phoneNum.formattedPhoneNumber" />
		</td>  -->
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
		<td class=formDeLabel><bean:message key="prompt.build" /></td>
		<td class=formDe colspan=3>
		    <logic:equal name="juvenileWarrantForm" property="build" value=""></logic:equal>				
		   <bean:write name="juvenileWarrantForm" property="build" />
		</td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.race" /></td>
		<td class=formDe>
		    <logic:equal name="juvenileWarrantForm" property="race" value=""></logic:equal>				
		   <bean:write name="juvenileWarrantForm" property="race" />
		</td>
		<td class=formDeLabel><bean:message key="prompt.sex" /></td>
		<td class=formDe>
		    <logic:equal name="juvenileWarrantForm" property="sex" value=""></logic:equal>				
		   <bean:write name="juvenileWarrantForm" property="sex" />
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
		    <logic:equal name="juvenileWarrantForm" property="eyeColor" value=""></logic:equal>				
		   <bean:write name="juvenileWarrantForm" property="eyeColor" />
		</td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.hairColor" /></td>
		<td class=formDe colspan="3">
		    <logic:equal name="juvenileWarrantForm" property="hairColor" value=""></logic:equal>				
		   <bean:write name="juvenileWarrantForm" property="hairColor" />
		</td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.complexion" /></td>
		<td class=formDe colspan="3">
		    <logic:equal name="juvenileWarrantForm" property="complexion" value=""></logic:equal>				
		    <bean:write name="juvenileWarrantForm" property="complexion" />
		</td>
	</tr>
	<tr>
		<td class=formDeLabel valign="top"><bean:message key="prompt.scarsMarks" /></td>				
		<td class=formDe colspan="3">
		    <logic:iterate id="scars" name="juvenileWarrantForm" property="scarsAndMarksSelected">
                <bean:write name="scars" property="description" /><br>
           	</logic:iterate>
		</td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.tattoos" /></td>
	    <td class=formDe colspan="3">
		    <logic:iterate id="tattoo" name="juvenileWarrantForm" property="tattoosSelected">
            	<bean:write name="tattoo" property="description" /><br>
            </logic:iterate>
	    </td>
	</tr>
	<!-- JOT based warrant -->
	<logic:equal name="juvenileWarrantForm" property="referralNum" value="0">
		<tr>
			<td class=formDeLabel><bean:message key="prompt.fbiNumber" /></td>
			<td class=formDe>
		    	<logic:equal name="juvenileWarrantForm" property="fbiNum" value=""></logic:equal>					
			    <bean:write name="juvenileWarrantForm" property="fbiNum" />
			</td>
			<td class=formDeLabel width=1% nowrap><bean:message key="prompt.sid" /></td>
			<td class=formDe>
		    	<logic:equal name="juvenileWarrantForm" property="sid" value=""></logic:equal>					
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
		    <logic:equal name="juvenileWarrantForm" property="schoolName" value=""></logic:equal>
		    <bean:write name="juvenileWarrantForm" property="schoolName" />
		</td>
	</tr>
	<tr>
		<td class=formDeLabel valign="top"><bean:message key="prompt.cautions" /></td>
		<td class=formDe colspan="3">
		    <logic:iterate id="caution" name="juvenileWarrantForm" property="cautionsSelected">
              		<bean:write name="caution" property="description" /><br>
           	 </logic:iterate>
		</td>				
	</tr>
	<tr>
		<td class=formDeLabel width=1% nowrap><bean:message key="prompt.otherCautionComments" /></td>
		<td class=formDe colspan="3">		
		    <bean:write name="juvenileWarrantForm" property="cautionComments" />
		</td>
	</tr>
<!-- END JUVENILE INFORMATION SECTION -->
	<tr><td colspan="4">&nbsp;</td></tr>
	
<!-- BEGIN ASSOCIATE INFORMATION SECTION -->
	<tr>
		<td class="detailHead" colspan="4"><bean:message key="prompt.associatesInfo" /></td>
	</tr>
		
	<!-- display detail info -->
	<%int RecordCounter2 = 0;%>		
	<tr class=formDeLabel>
		<td width=50% colspan="2"><bean:message key="prompt.name" /></td>
		<td width=50% colspan="2"><bean:message key="prompt.relationshipToJuvenile" /></td>
	</tr>
	<logic:iterate id="associateIndex" name="juvenileWarrantForm" property="associates"> 
    	<tr	class=<%RecordCounter2++;
			if (RecordCounter2 % 2 == 1)
				out.print("alternateRow");
			else
				out.print("normalRow");%>>
	           
			<td colspan="2" align="left">
<%-- 			<a href="/<msp:webapp/>displayAssociateDetails.do?associateNumber=<bean:write name="associateIndex" property="associateNum"/>"> --%>
				<a href="javascript:openWindow('/<msp:webapp/>displayAssociateDetails.do?associateNumber=<bean:write name="associateIndex" property="associateNum"/>');">	               							
	            <bean:write name="associateIndex" property="associateName.lastName" />, 
	            <bean:write name="associateIndex" property="associateName.firstName" /> 
	            <bean:write name="associateIndex" property="associateName.middleName" /></a></td>
	        <td colspan="2" align="left"><bean:write name="associateIndex" property="relationshipToJuvenile" /></td>			 
		</tr>
	</logic:iterate> 
<!-- END ASSOCIATE INFORMATION SECTION -->
	<tr><td colspan="4">&nbsp;</td></tr>
	
<%-- this block of information for JOT based warrants only --%>
<logic:equal name="juvenileWarrantForm" property="referralNum" value="0">
<!-- BEGIN LAW ENFORCEMENT INFO SECTION -->
	<tr>
		<td class="detailHead" colspan="4"><bean:message key="prompt.lawEnforcementInfo" /></td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.officerName" /></td>
		<td class=formDe colspan="3">
			<logic:equal name="juvenileWarrantForm" property="officerName" value=""></logic:equal>								
			<bean:write name="juvenileWarrantForm" property="officerName" />
		</td>
	</tr>
	<tr>
		<td class=formDeLabel width=1% nowrap><bean:message key="prompt.officerIdNumber" /></td>
		<td class=formDe>
			<logic:equal name="juvenileWarrantForm" property="officerId" value=""></logic:equal>								
			<bean:write name="juvenileWarrantForm" property="officerId" />
		</td>				
		<td class=formDeLabel width=1% nowrap><bean:message key="prompt.officerIdType" /></td>
		<td class=formDe>
			<logic:equal name="juvenileWarrantForm" property="officerIdType" value=""></logic:equal>								
			<bean:write name="juvenileWarrantForm" property="officerIdType" />
		</td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.department" /></td>
		<td class=formDe colspan="3">
			<logic:equal name="juvenileWarrantForm" property="officerAgencyName" value=""></logic:equal>								
			<bean:write name="juvenileWarrantForm" property="officerAgencyName" />
		</td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.oriNumber" /></td>
		<td class=formDe colspan="3">
			<logic:equal name="juvenileWarrantForm" property="leaOriNum" value=""></logic:equal>								
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
			<logic:equal name="juvenileWarrantForm" property="email" value=""></logic:equal>								
			<bean:write name="juvenileWarrantForm" property="email" />
		</td>
	</tr>
<!-- BEGIN LAW ENFORCEMENT INFORMATION SECTION -->
	<tr><td colspan="4">&nbsp;</td></tr>
</logic:equal>

<!-- BEGIN OTHER INFORMATION SECTION -->
	<tr>
		<td class="detailHead" colspan="4"><bean:message key="prompt.otherInfo" /></td>
	</tr>	
	<tr>
		<td class=formDeLabel><bean:message key="prompt.warrantOriginatorName" /></td>
		<td class=formDe colspan="3">
			<logic:equal name="juvenileWarrantForm" property="warrantOriginatorName" value=""></logic:equal>												
			<bean:write name="juvenileWarrantForm" property="warrantOriginatorName" />
		</td>
	</tr>
	<tr>
		<td class=formDeLabel width=1% nowrap><bean:message key="prompt.probationOfficerOfRecord" /></td>
		<td class=formDe colspan="3">
			<logic:equal name="juvenileWarrantForm" property="probationOfficerOfRecordName" value=""></logic:equal>												
			<bean:write name="juvenileWarrantForm" property="probationOfficerOfRecordName" />
		</td>
	</tr>
<!-- END OTHER INFORMATION SECTION -->
	<tr><td colspan="4">&nbsp;</td></tr>

<!-- BEGIN WARRANT EXECUTOR/SERVICE INFORMATION SECTION -->
	<tr>
		<td class="detailHead" colspan="4"><bean:message key="prompt.executorServiceInfo" /></td>
	</tr>
	<!-- display sucessful executor/service info -->	
	<logic:equal name="juvenileWarrantForm" property="successfulWarrant" value="true">    	
		<tr>
   			<td class=formDeLabel><bean:message key="prompt.name" /></td>
			<td class=formDe colspan="3"><bean:write name="juvenileWarrantForm" property="executorName" /></td>
		</tr>					
		<tr>
           <td class=formDeLabel><bean:message key="prompt.officerIdNumber"/></td>
           <td class=formDe><bean:write name="juvenileWarrantForm" property="executorId"/></td>
           <td class=formDeLabel width=1% nowrap><bean:message key="prompt.officerIdType"/></td>
           <td class=formDe><bean:write name="juvenileWarrantForm" property="executorIdType"/></td> 
        </tr>
		<tr>
			<td class=formDeLabel><bean:message key="prompt.department" /></td>
			<td class=formDe colspan="3"><bean:write name="juvenileWarrantForm" property="executorDepartmentName" /></td>
		</tr>
		<tr>
			<td class=formDeLabel><bean:message key="prompt.workPhone" /></td>
			<td class=formDe>
				<bean:write name="juvenileWarrantForm" property="executorPhoneNum.formattedPhoneNumber" /></td>
			<td class=formDeLabel><bean:message key="prompt.cellPhone" /></td>
			<td class=formDe>
				<bean:write name="juvenileWarrantForm" property="executorCellNum.formattedPhoneNumber" /></td>
		</tr>
		<tr>
			<td class=formDeLabel><bean:message key="prompt.pager" /></td>
			<td class=formDe>
				<bean:write name="juvenileWarrantForm" property="executorPager.formattedPhoneNumber" /></td>
			<td class=formDeLabel><bean:message key="prompt.email" /></td>
			<td class=formDe>
				<logic:equal name="juvenileWarrantForm" property="executorEmail" value=""></logic:equal>
				<bean:write name="juvenileWarrantForm" property="executorEmail" /></td>
		</tr>							
		<tr>			
			<td class=formDeLabel><bean:message key="prompt.serviceStatus" /></td>
			<td class=formDe colspan="3"><bean:write name="juvenileWarrantForm" property="serviceStatus"/></td>				
		</tr>
		<tr>			
			<td class=formDeLabel><bean:message key="prompt.serviceDate" /></td>
			<td class=formDe>	
				<logic:notEmpty name="juvenileWarrantForm" property="currentServiceDate">
					<bean:write name="juvenileWarrantForm" property="currentServiceDate" formatKey="date.format.mmddyyyy" />
				</logic:notEmpty>
			</td>
			<td class=formDeLabel><bean:message key="prompt.serviceTime" /></td>
			<td class=formDe>
				<logic:notEmpty name="juvenileWarrantForm" property="currentServiceDate">
					<bean:write name="juvenileWarrantForm" property="currentServiceDate" formatKey="time.format.HHmm" />
				</logic:notEmpty>
			</td>			
		</tr>
		<tr>
			<td class=formDeLabel><bean:message key="prompt.address" /></td>
			<td class=formDe colspan="3"><bean:write name="juvenileWarrantForm" property="serviceAddress" /></td>				
		</tr>
		<tr>				
			<td class=formDeLabel><bean:message key="prompt.addressType" /></td>
			<td class=formDe colspan="3"><bean:write name="juvenileWarrantForm" property="serviceAddressType" /></td>						       		
		</tr>
		<tr>				
			<td class=formDeLabel width=1% nowrap><bean:message key="prompt.serviceAttemptComments" /></td>
			<td class=formDe colspan="3"><bean:write name="juvenileWarrantForm" property="serviceAttemptComments" /></td>			       		
		</tr>
		<tr><td class=formDe colspan="4">&nbsp;</td></tr>						
	</logic:equal>
	
	<!-- display unsucessful executor/services info -->			    
	<logic:notEmpty name="juvenileWarrantForm" property="services">	        
		<logic:iterate id="servicesIndex" name="juvenileWarrantForm" property="services">		
		    <tr>
       			<td class=formDeLabel><bean:message key="prompt.name" /></td>
				<td class=formDe colspan="3"><%--<bean:write name="servicesIndex" property="executorName" />--%>
					<bean:write name="servicesIndex" property="executorLastName" />, 
   					<bean:write name="servicesIndex" property="executorFirstName" /> 
   					<bean:write name="servicesIndex" property="executorMiddleName" /></td>
			</tr>					
			<tr>
               	<td class=formDeLabel><bean:message key="prompt.officerIdNumber"/></td>
               	<td class=formDe><bean:write name="servicesIndex" property="executorOfficerId"/></td>
             	<td class=formDeLabel width=1% nowrap><bean:message key="prompt.officerIdType"/></td>
               	<td class=formDe><bean:write name="servicesIndex" property="executorIdType"/></td> 
            </tr>
			<tr>
				<td class=formDeLabel><bean:message key="prompt.department" /></td>
				<td class=formDe colspan="3"><bean:write name="servicesIndex" property="executorAgencyName" /></td>									
			</tr>
			<tr>
				<td class=formDeLabel><bean:message key="prompt.workPhone" /></td>
				<td class=formDe><%--<bean:write name="servicesIndex" property="executorPhoneNum.formattedPhoneNumber" />--%>
					<bean:write name="servicesIndex" property="executorPhoneNum" /></td>
				<td class=formDeLabel><bean:message key="prompt.cellPhone" /></td>
				<td class=formDe><%--<bean:write name="servicesIndex" property="executorCellNum.formattedPhoneNumber" />--%>
					<bean:write name="servicesIndex" property="executorCellNum" /></td>
			</tr>
			<tr>
				<td class=formDeLabel><bean:message key="prompt.pager" /></td>
				<td class=formDe><%--<bean:write name="servicesIndex" property="executorPager.formattedPhoneNumber" />--%>
					<bean:write name="servicesIndex" property="executorPager" /></td>
				<td class=formDeLabel><bean:message key="prompt.email" /></td>
				<td class=formDe>
					<logic:equal name="juvenileWarrantForm" property="executorEmail" value=""></logic:equal>
					<bean:write name="servicesIndex" property="executorEmail" /></td>
			</tr>							
			<tr>			
				<td class=formDeLabel><bean:message key="prompt.serviceStatus" /></td>
				<td class=formDe colspan="3"><bean:write name="servicesIndex" property="serviceStatus"/></td>				
			</tr>
			<tr>			
				<td class=formDeLabel><bean:message key="prompt.serviceDate" /></td>
				<td class=formDe>
					<logic:notEmpty name="servicesIndex" property="serviceTimeStamp">
						<bean:write name="servicesIndex" property="serviceTimeStamp" formatKey="date.format.mmddyyyy" />
					</logic:notEmpty>
				</td>
				<td class=formDeLabel><bean:message key="prompt.serviceTime" /></td>
				<td class=formDe>
					<logic:notEmpty name="servicesIndex" property="serviceTimeStamp">
						<bean:write name="servicesIndex" property="serviceTimeStamp" formatKey="time.format.HHmm" />
					</logic:notEmpty>
				</td>
			</tr>
			<tr>
				<td class=formDeLabel><bean:message key="prompt.address" /></td>
				<td class=formDe colspan="3"><%--<bean:write name="servicesIndex" property="serviceAddress" />--%>
					<bean:write name="servicesIndex" property="streetNum" />
					<bean:write name="servicesIndex" property="streetName" />
					<bean:write name="servicesIndex" property="streetType" />
					<bean:write name="servicesIndex" property="aptNumber" />
					<bean:write name="servicesIndex" property="city" />,
					<bean:write name="servicesIndex" property="state" />
					<bean:write name="servicesIndex" property="zipCode" />
					<bean:write name="servicesIndex" property="additionalZipCode" />			
				</td>				
			</tr>
			<tr>				
				<td class=formDeLabel><bean:message key="prompt.addressType" /></td>
				<td class=formDe colspan="3"><bean:write name="servicesIndex" property="addressType" /></td>						       		
			</tr>
			<tr>				
				<td class=formDeLabel width=1% nowrap><bean:message key="prompt.serviceAttemptComments" /></td>
				<td class=formDe colspan="3"><bean:write name="servicesIndex" property="comments" /></td>			       		
			</tr>
			<tr><td class=formDe colspan="4">&nbsp;</td></tr>	                	 			  
		</logic:iterate>			
	</logic:notEmpty>
<!-- END WARRANT EXECUTOR/SERVICE INFORMATION SECTION -->
	<tr><td colspan="4">&nbsp;</td></tr>
	
<!-- BEGIN ARREST INFORMATION SECTION -->
	<tr>
		<td class="detailHead" colspan="4"><bean:message key="prompt.arrestInfo" /></td>
	</tr>	
	<tr>
		<td class=formDeLabel width=1% nowrap><bean:message key="prompt.arrestAddress" /></td>
		<td class=formDe colspan="3"><bean:write name="juvenileWarrantForm" property="arrestAddress" /></td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.arrestDate" /></td>
		<td class=formDe>
			<bean:write name="juvenileWarrantForm" property="arrestDate" formatKey="date.format.mmddyyyy" />
		</td>
		<td class=formDeLabel width=1% nowrap><bean:message key="prompt.arrestTime" /></td>
		<td class=formDe>
			<bean:write name="juvenileWarrantForm" property="arrestDate" formatKey="time.format.HHmm" />
		</td>
	</tr>					
<!-- END ARREST INFORMATION SECTION -->
	<tr><td colspan="4">&nbsp;</td></tr>

<!-- BEGIN RELEASE INFORMATION SECTION -->
	<tr>
		<td class="detailHead" colspan="4"><bean:message key="prompt.releaseInfo" /></td>
	</tr>			
	<tr> 
     	<td class=formDeLabel><bean:message key="prompt.releaseOfficer"/></td>
      	<td class=formDe colspan="3"><bean:write name="juvenileWarrantForm" property="releaseDecisionUserName"/></td>
   	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.releaseDecision" /></td>
		<td class=formDe colspan="3"><bean:write name="juvenileWarrantForm" property="releaseDecision" /></td>
	</tr>
	<tr>
		<td class=formDeLabel width=1% nowrap><bean:message key="prompt.releaseDecisionDate" /></td>
		<td class=formDe>
			<bean:write name="juvenileWarrantForm" property="releaseDecisionDate" formatKey="date.format.mmddyyyy" />
		</td>
		<td class=formDeLabel><bean:message key="prompt.releaseDecisionTime" /></td>
		<td class=formDe><bean:write name="juvenileWarrantForm" property="releaseDecisionDate" formatKey="time.format.HHmm" /></td>
	</tr>				
	<tr> 
  		<td class=formDeLabel><bean:message key="prompt.releasedTo"/></td>
  		<td class=formDe><bean:write name="juvenileWarrantForm" property="releaseDecisionName"/></td>
  		<td class=formDeLabel width=1% nowrap><bean:message key="prompt.relationshipToJuvenile"/></td>
  		<td class=formDe><bean:write name="juvenileWarrantForm" property="releaseRelationshipToJuvenile"/></td>          		          		
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.transferCustodyDate" /></td>
		<td class=formDe><bean:write name="juvenileWarrantForm" property="transferCustodyDate" formatKey="date.format.mmddyyyy" /></td>		
		<td class=formDeLabel><bean:message key="prompt.transferCustodyTime" /></td>
		<td class=formDe><bean:write name="juvenileWarrantForm" property="transferCustodyDate" formatKey="time.format.HHmm" /></td>
	</tr>
<!-- END RELEASE INFORMATION SECTION -->
<tr><td colspan="4">&nbsp;</td></tr>

<!-- BEGIN WARRANT RECALL INFORMATION SECTION -->
	<tr>
		<td class="detailHead" colspan="4"><bean:message key="prompt.warrantRecallInfo" /></td>
	</tr>				
	<tr>
		<td class=formDeLabel><bean:message key="prompt.recallReason" /></td>
		<td class=formDe colspan="3"><bean:write name="juvenileWarrantForm" property="recallReason" /></td>														
	</tr>
<!-- END WARRANT RECALL INFORMATION SECTION -->
</table>
<br>
<!-- BEGIN BUTTON TABLE-->
<table width="98%">
	<tr>
		<td align="center">	
   			<logic:equal name="juvenileWarrantForm" property="action" value="summary">
        		<html:button property="org.apache.struts.taglib.html.BUTTON" onclick="history.go(-1);">
          			<bean:message key="button.back"></bean:message>
        		</html:button>&nbsp;
				<html:submit property="submitAction" onclick="disableSubmit(this, this.form);">				
  					<bean:message key="button.finish"></bean:message>
  				</html:submit>&nbsp;
	  			<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);">
  					<bean:message key="button.cancel"></bean:message>
  				</html:submit>
   			</logic:equal>	
   			<logic:notEqual name="juvenileWarrantForm" property="action" value="summary">
				<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)">
    	   			 <bean:message key="button.mainPage"></bean:message>
       			</html:submit>
   			</logic:notEqual>				
			  <%-- <html:button property="org.apache.struts.taglib.html.CANCEL" 
						 onclick="document.location.href='../security.war/jsp/mainMenu.jsp'">
    			   </html:button> 
    		 --%>
		</td>
	</tr>
</table>
<!-- END BUTTON TABLE -->
</html:form>
<!-- END FORM -->
<html:errors></html:errors>
<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
