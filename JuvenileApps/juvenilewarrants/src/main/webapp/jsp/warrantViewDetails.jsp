<!DOCTYPE HTML>
<!--MODIFICATIONS -->
<!-- 01/20/2005 Hien Rodriguez	Create JSP -->
<!-- 03/22/2005 Leslie Deen		Revise JSP to add logic tags -->
<!-- 06/06/2005	Hien Rodriguez	Change to new look -->
<!-- 08/12/2005	Dawn Gibler	  	Added buttons to create/update associates -->
<%-- 08/23/2005 JFisher	    	Update form fields to reflect use of java.util.Date instead of String --%>
<!-- 10/06/2005 CShimek      	ER#23756 revise titles -->
<!-- 12/27/2005 CShimek      	ER#20420 add Recall Reason -->
<%-- 01/09/2006 DWilliamson   	Changed streetNumber to streetNum per ER #25408 --%>
<%-- 02/01/2006 CShimek       	Added hidden field helpFile --%>
<%-- 02/15/2006 CShimek       	Defect#26005 change prompt Agency to Department in law enforcement block --%>
<%-- 08/08/2006 HRodriguez   	#34030 - Revised the look & labels of Executor/Service Information section. --%>
<%-- 12/21/2006 LDeen	      	Revised help file code --%>
<%-- 03/23/2007	CShimek		  	#40475 revised logic tags for height and weight to not display zero --%>
<%-- 04/11/2007 CShimek			#41031 revised RA href to open in new window --%>
<%-- 06/04/2007 LDeen			#42564 changed path to app.js --%>
<%-- 08/24/2007 RCapestani		#40655 display Court and Judge names for OIC instead of Warrant Originator name--%>
<%-- 09/10/2007 RCapestani		#44003 added Tag LIBRARIES NEEDED FOR CUSTOM JIMS and sort descending into PRIOR SERVICE ATTEMPTS BLOCK --%>
<%-- 11/19/2007 RCapestani		#47089 added offenseDate --%>
<%-- 11/30/2007 RCapestani		#44515 deleted <html:form action="/displayWarrantViewDetails" target="content"> --%>
<%-- 12/22/2008 CShimek			#56140 added 'Close Window" button and logic tags for secondaryAction --%>
<%-- 09/25/2009 RYoung			#62161 rename print button to match prototype --%>
<%-- 08/06/2015 RYoung      #27796 MJCW:  Adapt MJCW and Warrants to IE10 and 11 (Juv Profile - Education UI) --%>

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ page import="naming.PDJuvenileWarrantConstants" %>

<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<!--TAG LIBRARIES NEEDED FOR CUSTOM JIMS -->
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>

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

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<!-- JAVASCRIPT FILES-->
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/warrant_utils.js"></script>

<html:base />
<title><bean:message key="title.heading"/> - warrantViewDetails.jsp</title>
</head>

<body>
<html:form action="/displayWarrantViewSearchResults.do" target="content">
<input type="hidden" name="helpFile" value="juvwarnt/juvenile_warrants_final.htm#|142">
<input type="hidden" name="selectedValue" value="">  	
<!-- BEGIN HEADING TABLE -->
<table width="98%">
    <tr>    
   		<td align="center" class="header"><bean:message key="title.juvWarrant"/>&nbsp;<bean:message key="prompt.details" /></td> 
   	</tr>
</table>
<!-- END HEADING TABLE -->
<br>

<!-- BEGIN WARRANT INFORMATION SECTION -->
<table width="98%" border="0" cellpadding="4" cellspacing="1" align="center">	
	<tr>
		<td class="detailHead" colspan="5"><bean:message key="prompt.warrantInfo" /></td>
	</tr>	
	<tr>
		<td class="formDeLabel"><bean:message key="prompt.warrantNumber" /></td>
		<td class="formDe"><bean:write name="juvenileWarrantForm" property="warrantNum" /></td>			
		<td class="formDeLabel"><bean:message key="prompt.warrantType" /></td>
		<td class="formDe" colspan="2"><bean:write name="juvenileWarrantForm" property="warrantType" /></td>
	</tr>
	<%-- these values for JOT based warrants only --%>
	<tr>
		<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.transactionNumber" /></td>
		<td class="formDe">
			<bean:write name="juvenileWarrantForm" property="transactionNum" /></td>				  
		<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.daLogNumber" /></td>
		<td class="formDe">             				  
			<bean:write name="juvenileWarrantForm" property="daLogNum" /></td>
		<logic:notEmpty name='juvenilePhotoForm' property='mostRecentPhoto'>
			<td class="formDe" rowspan="5" width=21%>
				<a href="javascript:newCustomWindow('/<msp:webapp/>getJuvenilePhoto.do?submitAction=Link&juvenileNumber=<bean:write name="juvenileWarrantForm" property="juvenileNum"/>&selectedValue=<bean:write name="juvenilePhotoForm" property="mostRecentPhoto.photoName"/>','juvPhoto',400,400)" > 
				<img alt="Juvenile Photo" title="Juvenile Photo" src="/<msp:webapp/>getJuvenilePhoto.do?submitAction=Most Recent Photo&juvenileNumber=<bean:write name="juvenileWarrantForm" property="juvenileNum"/>"  height="125" border=1> 
				</a>
			</td>
		</logic:notEmpty>
		<logic:empty name='juvenilePhotoForm' property='mostRecentPhoto'>
			<td class="formDe" rowspan="5" width="21%">
				<img alt="Juvenile Photo Not Available" title="Juvenile Photo" border="1" src="/<msp:webapp/>images/photo_na.gif" height="125"></td>
		</logic:empty>
	</tr>
	<tr>
		<td class="formDeLabel"><bean:message key="prompt.warrantStatus" /></td>
		<td class="formDe">           				
		   <bean:write name="juvenileWarrantForm" property="warrantStatus" />
		</td>		
		<td class="formDeLabel"><bean:message key="prompt.dateIssued" /></td>
		<td class="formDe">
		    <bean:write name="juvenileWarrantForm" property="dateOfIssue" formatKey="date.format.mmddyyyy" />
		</td>				
	</tr>		
	<tr>
		<td class="formDeLabel"><bean:message key="prompt.fileStampDate" /></td>
		<td class="formDe">
		   <bean:write name="juvenileWarrantForm" property="fileStampDate" formatKey="date.format.mmddyyyy" />
		</td>
		<td class="formDeLabel"><bean:message key="prompt.fileStampTime" /></td>
		<td class="formDe">
		   <bean:write name="juvenileWarrantForm" property="fileStampDate" formatKey="time.format.HHmm" />
		</td>
	</tr>
	<tr>
		<td class="formDeLabel"><bean:message key="prompt.fileStampName" /></td>
		<td class="formDe" colspan="3">             				
		    <bean:write name="juvenileWarrantForm" property="fileStampName.formattedName" />
		</td>
	</tr>
	<tr>
		<td class="formDeLabel"><bean:message key="prompt.acknowledgeDate" /></td>
		<td class="formDe">
		   <bean:write name="juvenileWarrantForm" property="warrantAcknowledgementDate" formatKey="date.format.mmddyyyy" />
		</td>
		<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.acknowledgeTime" /></td>
		<td class="formDe">
		   <bean:write name="juvenileWarrantForm" property="warrantAcknowledgementDate" formatKey="time.format.HHmm" />
		</td>
	</tr>
	<tr>
		<td class="formDeLabel"><bean:message key="prompt.acknowledgeStatus" /></td>
		<td class="formDe">
		   <bean:write name="juvenileWarrantForm" property="warrantAcknowledgeStatus" />
		</td>
		<td class="formDeLabel"><bean:message key="prompt.signatureStatus" /></td>
		<td class="formDe" colspan="2">
		    <bean:write name="juvenileWarrantForm" property="warrantSignedStatus" />
		</td>
	</tr>
	<tr>
		<td class="formDeLabel"><bean:message key="prompt.activationDate" /></td>
		<td class="formDe">
		   <bean:write name="juvenileWarrantForm" property="warrantActivationDate" formatKey="date.format.mmddyyyy" />
		</td>
		<td class="formDeLabel"><bean:message key="prompt.activationTime" /></td>
		<td class="formDe" colspan="2">
		    <bean:write name="juvenileWarrantForm" property="warrantActivationDate" formatKey="time.format.HHmm" />
		</td>
	</tr>
	<tr>
		<td class="formDeLabel"><bean:message key="prompt.activationStatus" /></td>
		<td class="formDe">
		    <bean:write name="juvenileWarrantForm" property="warrantActivationStatus" />
		</td>
		<td class="formDeLabel"><bean:message key="prompt.dateRecalled" /></td>
		<td class="formDe" colspan="2">
		    <bean:write name="juvenileWarrantForm" property="recallDate" formatKey="date.format.mmddyyyy" />
		</td>
	</tr>
	<tr>
		<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.recallReason" /></td>
		<td class="formDe" colspan="4">
	  	 	<bean:write name="juvenileWarrantForm" property="recallReason" /> 
		</td>
	</tr>		
	<tr>
		<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.reasonRejectedOrUnsent" /></td>
		<td class="formDe" colspan="4">
		   <bean:write name="juvenileWarrantForm" property="unsendNotSignedReason" />
		</td>
	</tr>

<!-- END WARRANT INFORMATION SECTION -->

	<tr><td colspan="5">&nbsp;</td></tr>
	
<!-- BEGIN CHARGE INFORMATION SECTION -->
	<tr>
		<td class="detailHead" colspan="5"><bean:message key="prompt.chargeInfo" /></td>
	</tr>	
	<!-- display detail info -->		
	<logic:iterate id="chargeIndex" name="juvenileWarrantForm" property="charges">		
		<!-- JOT based warrant -->
		<logic:equal name="chargeIndex" property="referralNum" value="0">
			<tr>
				<td class="formDeLabel"><bean:message	key="prompt.charge" /></td>
				<td class="formDe" colspan="4"><bean:write name="chargeIndex" property="offense" /></td>
			</tr>
			
			<tr>
				<td class="formDeLabel"><bean:message key="prompt.petitionNumber" /></td>
				<td class="formDe">
					<bean:write name="chargeIndex" property="petitionNum" />
				</td>								
				<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.chargeSeqNumber" /></td>							
				<td class="formDe" colspan="2">
	  				<bean:write name="chargeIndex" property="chargeId" />
				</td>
			</tr>
			
			<tr>
				<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.chargeNCICNumber" /></td>
				<td class="formDe">
					<bean:write name="chargeIndex" property="offenseCodeId" />
				</td>								
				<td class="formDeLabel"><bean:message key="prompt.chargeCourt" /></td>
				<td class="formDe"  colspan="2">           								
					 <bean:write name="chargeIndex" property="court" />
				</td>
			<tr>
				<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.offenseDate" /></td>
				<td class="formDe"  colspan="4"><bean:write name="chargeIndex" property="offenseDate" formatKey="date.format.mmddyyyy"/></td>
			</tr>
		</logic:equal>

		<!-- JJS based warrant -->
		<logic:notEqual name="chargeIndex" property="referralNum" value="0">
			<tr>
				<td class="formDeLabel"><bean:message key="prompt.charge" /></td>
				<td class="formDe" colspan="4"><bean:write name="chargeIndex" property="offense" /></td>
			</tr>
			<tr>
				<td class="formDeLabel"><bean:message key="prompt.petitionNumber" /></td>
				<td class="formDe"><bean:write name="chargeIndex" property="petitionNum" /></td>
				<td class="formDeLabel"><bean:message key="prompt.chargeCourt" /></td>
				<td class="formDe"  colspan="2"><bean:write name="chargeIndex" property="court" /></td>
			</tr>
		</logic:notEqual>		
		<tr><td colspan="5">&nbsp;</td></tr>
	</logic:iterate>
<!-- END CHARGE INFORMATION SECTION -->

<!-- BEGIN JUVENILE INFORMATION SECTION -->
	<tr>
		<td class="detailHead" colspan="5"><bean:message key="prompt.juvenileInfo" /></td>
	</tr>	
	
	<tr>
		<td class="formDeLabel"><bean:message key="prompt.juvenileName" /></td>
		<td class="formDe" colspan="4">
		    <bean:write name="juvenileWarrantForm" property="juvenileName.formattedName" />
		    <logic:equal name="juvenileWarrantForm" property="juvRectype" value="I.JUVENILE">
					&nbsp;&nbsp;&nbsp;&nbsp;<font color="Blue" face="verdana"><b><span title='Purged'>P</span></b></font>
				</logic:equal>
				<logic:equal name="juvenileWarrantForm" property="juvRectype" value="S.JUVENILE">
					&nbsp;&nbsp;&nbsp;&nbsp;<font color="Blue" face="verdana"><b><span title='Sealed'>S</span></b></font>
				</logic:equal>
		</td>
	</tr>
	
	<tr>
		<td class="formDeLabel"><bean:message key="prompt.aka" /></td>
		<td class="formDe" colspan="4">			
		    <bean:write name="juvenileWarrantForm" property="aliasName" />
		</td>
	</tr>
	
	<tr>
		<td class="formDeLabel"><bean:message key="prompt.juvenileNumber" />
			<input type="hidden" id="juvenileNum" value="<bean:write name="juvenileWarrantForm" property="juvenileNum" />" />
			<input type="hidden" id="warrantNumber" value="<bean:write name="juvenileWarrantForm" property="warrantNum" />" />
			<input type="hidden" id="firstName" value="<bean:write name="juvenileWarrantForm" property="juvenileName.firstName" />" />
			<input type="hidden" id="lastName" value="<bean:write name="juvenileWarrantForm" property="juvenileName.lastName" />" />
			<input type="hidden" id="jpOfficerEmailAddress" value="<bean:write name="juvenileWarrantForm" property="jpOfficerEmail" />" />
		</td>
		<td class="formDe" colspan="4">			
		    <bean:write name="juvenileWarrantForm" property="juvenileNum" />
		</td>
	</tr>
	
	<tr>
		<td class="formDeLabel"><bean:message key="prompt.ssn" /></td>
		<td class="formDe" colspan="4"><bean:write name="juvenileWarrantForm" property="maskedSSN" />
	</tr>
	
	<tr>
		<td class="formDeLabel"><bean:message key="prompt.dateOfBirth" /></td>
		<logic:notEqual name="juvenileWarrantForm" property="warrantTypeId" value="<%= PDJuvenileWarrantConstants.WARRANT_TYPE_DTA %>">       
			<td class="formDe" colspan=4>
			    <bean:write name="juvenileWarrantForm" property="dateOfBirth" formatKey="date.format.mmddyyyy" />
			</td>
		</logic:notEqual>
		<logic:equal name="juvenileWarrantForm" property="warrantTypeId" value="<%= PDJuvenileWarrantConstants.WARRANT_TYPE_DTA %>">       
			<td class="formDe">
			    <bean:write name="juvenileWarrantForm" property="dateOfBirth" formatKey="date.format.mmddyyyy" />
			</td>				
	   		 <td class="formDeLabel"><bean:message key="prompt.age"/>&nbsp;<bean:message key="prompt.verifiedBy"/></td>
    	   	 <td class="formDe"  colspan="2">                           
	    	   	 <bean:write name="juvenileWarrantForm" property="dateOfBirthSource"/>
			 </td>            
	    </logic:equal>  
				
	</tr>
	<tr>
		<td class="formDeLabel"><bean:message key="prompt.race" /></td>
		<td class="formDe">
		   <bean:write name="juvenileWarrantForm" property="race" />
		</td>
		<td class="formDeLabel"><bean:message key="prompt.sex" /></td>
		<td class="formDe"  colspan="2">		
		   <bean:write name="juvenileWarrantForm" property="sex" />
		</td>
	</tr>
	
	<tr>
		<td class="formDeLabel"><bean:message key="prompt.height" /></td>
		<td class="formDe">
	       <logic:notEqual name="juvenileWarrantForm" property="height" value="">
 	     		<logic:notEqual name="juvenileWarrantForm" property="heightFeet" value="0">
	           		<bean:write name="juvenileWarrantForm" property="heightFeet"/>ft&nbsp;
	           		<bean:write name="juvenileWarrantForm" property="heightInch"/>in&nbsp;
 	           </logic:notEqual> 
	       </logic:notEqual>
		</td>
		<td class="formDeLabel"><bean:message key="prompt.weight" /></td>
		<td class="formDe"  colspan="2">		
			<logic:notEqual name="juvenileWarrantForm" property="weight" value="">
				<logic:notEqual name="juvenileWarrantForm" property="weight" value="0">
					<bean:write name="juvenileWarrantForm" property="weight"/>&nbsp;lbs
				</logic:notEqual>	
			</logic:notEqual>				
		</td>
	</tr>
	<tr>
		<td class="formDeLabel"><bean:message key="prompt.eyeColor" /></td>
		<td class="formDe" colspan="4">	
		   <bean:write name="juvenileWarrantForm" property="eyeColor" />
		</td>
	</tr>
	<tr>
		<td class="formDeLabel"><bean:message key="prompt.hairColor" /></td>
		<td class="formDe" colspan="4">			
		   <bean:write name="juvenileWarrantForm" property="hairColor" />
		</td>
	</tr>
	
	<tr>
		<td class="formDeLabel"><bean:message key="prompt.build" /></td>
		<td class="formDe" colspan="4">		
		   <bean:write name="juvenileWarrantForm" property="build" />
		</td>
	</tr>

	<tr>
		<td class="formDeLabel"><bean:message key="prompt.complexion" /></td>
		<td class="formDe" colspan="4">				
		    <bean:write name="juvenileWarrantForm" property="complexion" />
		</td>
	</tr>
	<tr>
		<td class="formDeLabel" valign="top"><bean:message key="prompt.scarsMarks" /></td>				
		<td class="formDe" colspan="4">
             <logic:iterate id="scars" name="juvenileWarrantForm" property="scarsAndMarksSelected">
                <bean:write name="scars" property="description" /><br>
           	</logic:iterate>
		</td>
	</tr>
	<tr>
		<td class="formDeLabel"><bean:message key="prompt.tattoos" /></td>
	    <td class="formDe" colspan="4">
			<logic:iterate id="tattoo" name="juvenileWarrantForm" property="tattoosSelected">
            	<bean:write name="tattoo" property="description" /><br>
            </logic:iterate>
	    </td>
	</tr>
	<!-- JOT based warrant -->
	<logic:equal name="juvenileWarrantForm" property="referralNum" value="0">
		<tr>
			<td class="formDeLabel"><bean:message key="prompt.fbiNumber" /></td>
			<td class="formDe" colspan="4">				
			    <bean:write name="juvenileWarrantForm" property="fbiNum" />
			</td>
		</tr>	
		<tr>	
			<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.sid" /></td>
			<td class="formDe" colspan="4">				
			    <bean:write name="juvenileWarrantForm" property="sid" />
			</td>
		</tr>
	</logic:equal>
	<tr>
		<td class="formDeLabel"><bean:message key="prompt.schoolDistrict" /></td>
		<td class="formDe" colspan="4">
		    <bean:write name="juvenileWarrantForm" property="schoolDistrictName" />
		</td>
	</tr>
	<tr>
		<td class="formDeLabel"><bean:message key="prompt.schoolName" /></td>
		<td class="formDe" colspan="4">
		    <bean:write name="juvenileWarrantForm" property="schoolName" />
		</td>
	</tr>
	<tr>
		<td class="formDeLabel" valign="top"><bean:message key="prompt.cautions" /></td>
		<td class="formDe" colspan="4">
         	 <logic:iterate id="caution" name="juvenileWarrantForm" property="cautionsSelected">
              		<bean:write name="caution" property="description" /><br>
           	 </logic:iterate>
		</td>				
	</tr>
	<tr>
		<td class="formDeLabel" colspan=5><bean:message key="prompt.otherCautionComments" /></td>
	</tr>
	<tr>
		<td class="formDe" colspan="5">
		    <bean:write name="juvenileWarrantForm" property="cautionComments" />
		</td>
	</tr>
<!-- END JUVENILE INFORMATION SECTION -->

	<tr><td colspan="5">&nbsp;</td></tr>
<!-- BEGIN ASSOCIATE INFORMATION SECTION -->
	<tr>
		<td class="detailHead" colspan="5"><bean:message key="prompt.associatesInfo" />
			<span>&nbsp&nbsp&nbsp<a href="#" onClick="OpenOutlook()" >Email JPO & Data Corrections</a>
		</td>
	</tr>
		
	<!-- display detail info -->
	<tr class="formDeLabel">
		<td width="50%" colspan="2"><bean:message key="prompt.name" /></td>
		<td width="50%" colspan="3"><bean:message key="prompt.relationshipToJuvenile" /></td>
	</tr>
	<logic:empty name="juvenileWarrantForm" property="associates"> 
    	<tr>
      		<td width="2%">&nbsp;</td>
      		<td colspan="3"></td>
    	</tr> 
  	</logic:empty> 
			
	<logic:iterate id="associateIndex" name="juvenileWarrantForm" property="associates" indexId="index"> 
		<logic:notEqual name="associateIndex" property="associateNum" value="1111111"> <!-- do not display dummy family member -->
  		<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
			<td colspan="2" align="left">
				<logic:notEqual name="juvenileWarrantForm" property="secondaryAction" value="popup">
					<a href="javascript:openWindow('/<msp:webapp/>displayAssociateDetails.do?associateNumber=<bean:write name="associateIndex" property="associateNum"/>&juvenileNumber=<bean:write name="juvenileWarrantForm" property="juvenileNum" />&relationshipId=<bean:write name="associateIndex" property="relationshipToJuvenileId"/>');">	               			
						<bean:write name="associateIndex" property="associateName.lastName" />, 
						<bean:write name="associateIndex" property="associateName.firstName" /> 
						<bean:write name="associateIndex" property="associateName.middleName" />
					</a>
				</logic:notEqual>
				<logic:equal name="juvenileWarrantForm" property="secondaryAction" value="popup">
					<bean:write name="associateIndex" property="associateName.lastName" />, 
					<bean:write name="associateIndex" property="associateName.firstName" /> 
					<bean:write name="associateIndex" property="associateName.middleName" />
				</logic:equal>
      		</td>
      		<td colspan="3" align="left"><bean:write name="associateIndex" property="relationshipToJuvenile" /></td>			 
		</tr>
		</logic:notEqual>
	</logic:iterate> 
<!-- END ASSOCIATE INFORMATION SECTION -->
	
	<tr><td colspan="4">&nbsp;</td></tr>
<!-- display only for JOT based warrant -->
  <logic:equal name="juvenileWarrantForm" property="referralNum" value="0">
  <!-- BEGIN LAW ENFORCEMENT INFO SECTION -->
  	<tr>
  		<td class="detailHead" colspan="5"><bean:message key="prompt.lawEnforcementInfo" /></td>
  	</tr>
  	
		<tr>
  		<td class="formDeLabel"><bean:message key="prompt.officerName" /></td>
  		<td class="formDe" colspan="4">							
  			<bean:write name="juvenileWarrantForm" property="officerName" />
  		</td>
  	</tr>
  	
	<tr>
  		<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.officerIdNumber" /></td>
  		<td class="formDe">								
  			<bean:write name="juvenileWarrantForm" property="officerId" />
  		</td>				
  		<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.officerIdType" /></td>
  		<td class="formDe"  colspan="2">								
  			<bean:write name="juvenileWarrantForm" property="officerIdType" />
  		</td>
  	</tr>
  	
	<tr>
  		<td class="formDeLabel"><bean:message key="prompt.department" /></td>
  		<td class="formDe" colspan="4">
  			<bean:write name="juvenileWarrantForm" property="officerAgencyName" />
  		</td>
  	</tr>
  	
	<tr>
  		<td class="formDeLabel"><bean:message key="prompt.oriNumber" /></td>
  		<td class="formDe" colspan="4">							
  			<bean:write name="juvenileWarrantForm" property="leaOriNum" />
  		</td>
  	</tr>
  	
	<tr>
  		<td class="formDeLabel"><bean:message key="prompt.workPhone" /></td>
  		<td class="formDe"><bean:write name="juvenileWarrantForm" property="officerPhoneNum.formattedPhoneNumber" /></td>				
  		<td class="formDeLabel"><bean:message key="prompt.cellPhone" /></td>
  		<td class="formDe" colspan="2"><bean:write name="juvenileWarrantForm" property="officerCellNum.formattedPhoneNumber" /></td>
  	</tr>
  	
	<tr>
  		<td class="formDeLabel"><bean:message key="prompt.pager" /></td>
  		<td class="formDe"><bean:write name="juvenileWarrantForm" property="officerPager.formattedPhoneNumber" /></td>
  		<td class="formDeLabel"><bean:message key="prompt.email" /></td>
  		<td class="formDe" colspan="2">							
  			<bean:write name="juvenileWarrantForm" property="email" />
  		</td>
  	</tr>
  	<tr>
  		<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.affidavitStatement" /></td>
  		<td class="formDe" colspan="4"><bean:write name="juvenileWarrantForm" property="affidavitStatement" /></td>
  	</tr>
  <!-- END LAW ENFORCEMENT INFORMATION SECTION -->
  	<tr><td colspan="5">&nbsp;</td></tr>
  </logic:equal>

<!-- BEGIN OTHER INFORMATION SECTION -->
	<tr>
		<td class="detailHead" colspan="5"><bean:message key="prompt.otherInfo" /></td>
	</tr>
	<logic:equal name="juvenileWarrantForm" property="warrantType" value="ORDER OF IMMEDIATE CUSTODY">
		<tr> 
    		<td class="formDeLabel"><bean:message key="prompt.court"/></td> 
    		<td class="formDe" colspan="4"><bean:write name="juvenileWarrantForm" property="warrantOriginatorCourt"/></td> 
  		</tr>
  		<tr>
  			<td class="formDeLabel"><bean:message key="prompt.judgeName" /></td>
			<td class="formDe" colspan="4"><bean:write name="juvenileWarrantForm" property="warrantOriginatorName" /></td>
		</tr>  			
	</logic:equal>
	<logic:notEqual name="juvenileWarrantForm" property="warrantType" value="ORDER OF IMMEDIATE CUSTODY">
		<tr>
			<td class="formDeLabel"><bean:message key="prompt.warrantOriginatorName" /></td>
			<td class="formDe" colspan="4"><bean:write name="juvenileWarrantForm" property="warrantOriginatorName" /></td>
		</tr>
	</logic:notEqual>
	<tr>
		<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.probationOfficerOfRecord" /></td>
		<td class="formDe" colspan="4"><bean:write name="juvenileWarrantForm" property="probationOfficerOfRecordName" /></td>
	</tr>
<!-- END OTHER INFORMATION SECTION -->

	<tr><td colspan="5">&nbsp;</td></tr>
<!-- BEGIN WARRANT EXECUTOR/SERVICE INFORMATION SECTION -->
	<tr>
  		<td class="detailHead" colspan="5"><bean:message key="prompt.executorServiceInfo" /></td>
  	</tr>
		
	<!-- display sucessful executor/service info -->	
	<logic:equal name="juvenileWarrantForm" property="successfulWarrant" value="true">
	<tr>
      <td class="formDeLabel"><bean:message key="prompt.name" /></td>
      <td class="formDe" colspan="4"><bean:write name="juvenileWarrantForm" property="executorName" /></td>
	</tr>
		
    <tr>
      <td class="formDeLabel"><bean:message key="prompt.officerIdNumber"/></td>
      <td class="formDe"><bean:write name="juvenileWarrantForm" property="executorId"/></td>
      <td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.officerIdType"/></td>
      <td class="formDe" colspan="2"><bean:write name="juvenileWarrantForm" property="executorIdType"/></td> 
    </tr>
		
		<tr>
			<td class="formDeLabel"><bean:message key="prompt.department" /></td>
			<td class="formDe" colspan="4"><bean:write name="juvenileWarrantForm" property="executorDepartmentName" /></td>
		</tr>
		
		<tr>
			<td class="formDeLabel"><bean:message key="prompt.workPhone" /></td>
			<td class="formDe">
				<bean:write name="juvenileWarrantForm" property="executorPhoneNum.formattedPhoneNumber" />
			</td>
			<td class="formDeLabel"><bean:message key="prompt.cellPhone" /></td>
			<td class="formDe" colspan="2">
				<bean:write name="juvenileWarrantForm" property="executorCellNum.formattedPhoneNumber" />
			</td>
		</tr>
		
		<tr>
			<td class="formDeLabel"><bean:message key="prompt.pager" /></td>
			<td class="formDe">
				<bean:write name="juvenileWarrantForm" property="executorPager.formattedPhoneNumber" />
			</td>
			<td class="formDeLabel"><bean:message key="prompt.email" /></td>
			<td class="formDe" colspan="2">
				<bean:write name="juvenileWarrantForm" property="executorEmail" />
			</td>
		</tr>							
		
		<tr>			
			<td class="formDeLabel"><bean:message key="prompt.status" /></td>
			<td class="formDe"><bean:write name="juvenileWarrantForm" property="serviceStatus"/></td>
			<td class="formDeLabel"><bean:message key="prompt.dateTime" /></td>
			<td class="formDe" colspan="2"><bean:write name="juvenileWarrantForm" property="currentServiceDate" formatKey="date.format.mmddyyyy" />
				&nbsp;<bean:write name="juvenileWarrantForm" property="currentServiceDate" formatKey="time.format.HHmm" /></td>
		</tr>
		
		<tr>
			<td class="formDeLabel"><bean:message key="prompt.address" /></td>
			<td class="formDe" colspan="4"><bean:write name="juvenileWarrantForm" property="serviceAddress" /></td>				
		</tr>
		
		<tr>				
			<td class="formDeLabel"><bean:message key="prompt.addressType" /></td>
			<td class="formDe" colspan="4"><bean:write name="juvenileWarrantForm" property="serviceAddressType" /></td>						       		
		</tr>
		
		<tr>				
			<td class="formDeLabel"><bean:message key="prompt.comments" /></td>
			<td class="formDe" colspan="4"><bean:write name="juvenileWarrantForm" property="serviceAttemptComments" /></td>			       		
		</tr>
		<tr><td colspan="5">&nbsp;</td></tr>						
	</logic:equal>
	
	<!-- display unsucessful executor/services info -->			    
	<logic:notEmpty name="juvenileWarrantForm" property="services">
	<jims:sortResults beanName="juvenileWarrantForm" results="services" primaryPropSort="serviceTimeStamp" primarySortType="DATE" hideMe="True" defaultSort="True" defaultSortOrder="DESC" sortId="0" />	        
		<logic:iterate id="servicesIndex" name="juvenileWarrantForm" property="services">		
      	<tr>
        <td class="formDeLabel"><bean:message key="prompt.name" /></td>
				
        <td class="formDe" colspan="4"><%--<bean:write name="servicesIndex" property="executorName" />--%>
          <bean:write name="servicesIndex" property="executorLastName" />, 
          <bean:write name="servicesIndex" property="executorFirstName" /> 
          <bean:write name="servicesIndex" property="executorMiddleName" />
  			</td>
      </tr>
								
      <tr>
        <td class="formDeLabel"><bean:message key="prompt.officerIdNumber"/></td>
        <td class="formDe"><bean:write name="servicesIndex" property="executorOfficerId"/></td>
        <td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.officerIdType"/></td>
        <td class="formDe" colspan="2"><bean:write name="servicesIndex" property="executorIdType"/></td> 
      </tr>
			
			<tr>
				<td class="formDeLabel"><bean:message key="prompt.department" /></td>
				<td class="formDe" colspan="4"><bean:write name="servicesIndex" property="executorAgencyName" /></td>									
			</tr>
			
			<tr>
				<td class="formDeLabel"><bean:message key="prompt.workPhone" /></td>
				<td class="formDe"><%--<bean:write name="servicesIndex" property="executorPhoneNum.formattedPhoneNumber" />--%>
					<bean:write name="servicesIndex" property="executorPhoneNum" /></td>
				<td class="formDeLabel"><bean:message key="prompt.cellPhone" /></td>
				<td class="formDe" colspan="2"><%--<bean:write name="servicesIndex" property="executorCellNum.formattedPhoneNumber" />--%>
					<bean:write name="servicesIndex" property="executorCellNum" /></td>
			</tr>
			
			<tr>
				<td class="formDeLabel"><bean:message key="prompt.pager" /></td>
				<td class="formDe"><%--<bean:write name="servicesIndex" property="executorPager.formattedPhoneNumber" />--%>
					<bean:write name="servicesIndex" property="executorPager" /></td>
				<td class="formDeLabel"><bean:message key="prompt.email" /></td>
				<td class="formDe" colspan="2">
					<logic:equal name="juvenileWarrantForm" property="executorEmail" value=""></logic:equal>
					<bean:write name="servicesIndex" property="executorEmail" />
  			</td>
			</tr>
										
			<tr>			
				<td class="formDeLabel"><bean:message key="prompt.status" /></td>
				<td class="formDe"><bean:write name="servicesIndex" property="serviceStatus"/></td>			
				<td class="formDeLabel"><bean:message key="prompt.dateTime" /></td>
				<td class="formDe" colspan="2"><bean:write name="servicesIndex" property="serviceTimeStamp" formatKey="date.format.mmddyyyy" />
					&nbsp;<bean:write name="servicesIndex" property="serviceTimeStamp" formatKey="time.format.HHmm" /></td>
			</tr>
			
			<tr>
				<td class="formDeLabel"><bean:message key="prompt.address" /></td>
				<td class="formDe" colspan="4"><%--<bean:write name="servicesIndex" property="serviceAddress" />--%>
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
				<td class="formDeLabel"><bean:message key="prompt.addressType" /></td>
				<td class="formDe" colspan="4"><bean:write name="servicesIndex" property="addressType" /></td>						       		
			</tr>

			<tr>				
				<td class="formDeLabel"><bean:message key="prompt.comments" /></td>
				<td class="formDe" colspan="4"><bean:write name="servicesIndex" property="comments" /></td>			       		
			</tr>
			<tr><td class="formDe" colspan="5">&nbsp;</td></tr>	                	 			  
		</logic:iterate>			
	</logic:notEmpty>
<!-- END WARRANT EXECUTOR/SERVICE INFORMATION SECTION -->

<!-- DO NOT DISPLAY ARREST INFORMATION TABLE IF NO ARREST HAS BEEN MADE -->
  <logic:notEmpty name="juvenileWarrantForm" property="arrestDate">
  <!-- BEGIN ARREST INFORMATION SECTION -->
  	<tr>
  		<td class="detailHead" colspan="5"><bean:message key="prompt.arrestInfo" /></td>
  	</tr>
  		
  	<tr>
  		<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.arrestAddress" /></td>
  		<td class="formDe" colspan="4"><bean:write name="juvenileWarrantForm" property="arrestAddress" /></td>
  	</tr>
  	
  	<tr>
  		<td class="formDeLabel"><bean:message key="prompt.arrestDate" /></td>
  		<td class="formDe"><bean:write name="juvenileWarrantForm" property="arrestDate" formatKey="date.format.mmddyyyy" /></td>
  		<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.arrestTime" /></td>
  		<td class="formDe" colspan="2"><bean:write name="juvenileWarrantForm" property="arrestDate" formatKey="time.format.HHmm" /></td>
  	</tr>
  	<tr><td colspan="5">&nbsp;</td></tr>					
  <!-- END ARREST INFORMATION SECTION -->
  </logic:notEmpty>

<!-- DO NOT DISPLAY RELEASE INFORMATION TABLE IF NO RELEASE DECISION HAS BEEN MADE -->
  <logic:notEmpty name="juvenileWarrantForm" property="releaseDecisionDate">
  <!-- BEGIN RELEASE INFORMATION SECTION -->
    <tr>
      	<td class="detailHead" colspan="5"><bean:message key="prompt.releaseInfo" /></td>
    </tr>			
  	
  	<tr> 
      	<td class="formDeLabel"><bean:message key="prompt.releaseOfficer"/></td>
      	<td class="formDe" colspan="4"><bean:write name="juvenileWarrantForm" property="releaseDecisionUserName"/></td>
    </tr>
  		
  	<tr>
  		<td class="formDeLabel"><bean:message key="prompt.releaseDecision" /></td>
  		<td class="formDe" colspan="4"><bean:write name="juvenileWarrantForm" property="releaseDecision" /></td>
  	</tr>
  	
  	<tr>
  		<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.releaseDecisionDate" /></td>
  		<td class="formDe"><bean:write name="juvenileWarrantForm" property="releaseDecisionDate" formatKey="date.format.mmddyyyy" /></td>
  		<td class="formDeLabel"><bean:message key="prompt.releaseDecisionTime" /></td>
  		<td class="formDe" colspan="2"><bean:write name="juvenileWarrantForm" property="releaseDecisionDate" formatKey="time.format.HHmm" /></td>
  	</tr>
  					
  	<tr> 
    	<td class="formDeLabel"><bean:message key="prompt.releasedTo"/></td>
    	<td class="formDe"><bean:write name="juvenileWarrantForm" property="releaseDecisionName"/></td>
    	<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.relationshipToJuvenile"/></td>
    	<td class="formDe" colspan="2"><bean:write name="juvenileWarrantForm" property="releaseRelationshipToJuvenile"/></td>          		          		
  	</tr>
  	
  	<tr>
  		<td class="formDeLabel"><bean:message key="prompt.transferCustodyDate" /></td>
  		<td class="formDe"><bean:write name="juvenileWarrantForm" property="transferCustodyDate" formatKey="date.format.mmddyyyy" /></td>		
  		<td class="formDeLabel"><bean:message key="prompt.transferCustodyTime" /></td>
  		<td class="formDe" colspan="2"><bean:write name="juvenileWarrantForm" property="transferCustodyDate" formatKey="time.format.HHmm" /></td>
  	</tr>
  	<tr><td colspan="5">&nbsp;</td></tr>
  <!-- END RELEASE INFORMATION SECTION -->
  </logic:notEmpty>

</table>
</html:form>
<!-- END 1ST FORM -->

<!-- BUTTON TABLE -->  
<table width="10%" border=0 align="center">
	<logic:notEqual name="juvenileWarrantForm" property="secondaryAction" value="popup">
		<tr align="center">
					  		    						 		   	
	  <!--CANCEL BUTTON FORM-->
	  		<td align="center">    		
				<html:form action="/displayWarrantViewSearchResults.do">
					<html:submit property="submitAction">
						<bean:message key="button.cancel"></bean:message>
					</html:submit>
				</html:form>
			</td>
			 <!--CANCEL BUTTON FORM-->			
	
	 	    <!--BEGIN of the code for Printing/Reporting Functionality -->	
	 	    <logic:equal name="juvenileWarrantForm" property="warrantAcknowledgeStatus" value="PRINTED">
			 	<td align="center">
					<html:form action="/printWarrantTemplateReport" target="new">
						<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);">
							<bean:message key="button.printGenericWarrant"></bean:message>   
						</html:submit>
				 	</html:form>
	 			</td>
			</logic:equal>	 
		 	<!--END of the code for Printing/Reporting Functionality -->		 
		</tr>
	</logic:notEqual>
	<logic:equal name="juvenileWarrantForm" property="secondaryAction" value="popup">		
		<tr>
			<td valign="top" align="center">
	           <input type='button' name='closeWindow' value='Close Window' onclick='window.close()'>
	      	</td>
	    </tr>
	</logic:equal>	      
</table>
<!-- END BUTTONS TABLES -->   

<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>