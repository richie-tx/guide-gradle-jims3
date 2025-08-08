<!DOCTYPE HTML>
<!-- Used to display request acknowledgement summary resulting from acknowledgement search. -->
<!--MODIFICATIONS -->
<%-- DWilliamson	02/25/2005	Create JSP --%>
<%-- CShimek    	08/11/2005  Revise to new look and feel --%>
<%-- JFisher    	08/23/2005  Update form fields to reflect use of java.util.Date instead of String --%>
<%-- CShimek    	10/04/2005  ER#24012 change assocaite labels to responsible adult --%>
<%-- CShimek    	02/01/2006  Added hidden field for HelpFile access --%>
<%-- LDeen			12/21/2006  Revised help file code --%>
<%-- CShimek		03/22/2007  #40475 added missing weight unit annotation --%>
<%-- CShimek		04/11/2007  #41031 revised RA href to open in new window --%>
<%-- LDeen			05/08/2007  #41859 changed court to chargeCourt so correct court would display --%>
<%-- LDeen			05/31/2007  #42499 added nowrap attribute to File Stamp Name --%>
<%-- LDeen			06/04/2007	#42564 changed path to app.js --%>
<%-- RYoung     08/06/2015 #27796 MJCW:  Adapt MJCW and Warrants to IE10 and 11 (Juv Profile - Education UI) --%>

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->

<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/warrants.css" />
<html:base />
<title><bean:message key="title.heading"/> - warrantVOPActivateDetails.jsp</title>
<!-- JAVASCRIPT FILES-->
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
<tiles:insert page="/js/warrantVOPActivateDetails.js" />

</head>
<body>
<html:form action="/displayWarrantVOPActivateSummary" target="content">
<input type="hidden" name="helpFile" value="juvwarnt/juvenile_warrants_final.htm#|146">

<!-- BEGIN HEADING TABLE -->
<table width=100%>
  <tr>
    <td align="center" class="header" ><bean:message key="title.juvWarrantVOP"/>&nbsp;Activation Details</td>
  </tr>
</table>
<!-- END HEADING TABLE -->
	<!-- BEGIN HEADING/REQUIRED INFORMATION TABLE --> 
	 <table width="98%" border="0" align="center">
   <tr>
     <td>
	  <ul>
        <li>Enter the required information and click Next button to view Summary.</li>
	    <li>Clicking on Existing Responsible Adult name displays full address and contact information.</li>
	    </ul>
     </td>
  </tr>
</table>
<table width=98%>
  <tr>
		<td class="required"><bean:message key="prompt.1.diamond"/><bean:message key="prompt.requiredFieldsInstruction"/><%-- bean:message key="prompt.requiredFields"/ --%></td>      
  </tr>
</table>
<!-- END HEADING/REQUIRED INFORMATION TABLE --> 

<% int RecordCounter = 0; 
   String bgcolor = ""; %>
<!-- BEGIN MAIN BODY TABLE -->
<table width=98% border=0 cellspacing=1 cellpadding=4 align=center>
<!-- BEGIN WARRANT INFORMATION BLOCK -->
	<tr>
		<td class="detailHead" colspan=4><bean:message key="prompt.warrantInfo"/></td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.warrantNumber"/></td>
		<td class=formDe><bean:write name="juvenileWarrantForm" property="warrantNum"/></td>
		<td class=formDeLabel><bean:message key="prompt.court"/></td>
		<td class=formDe><bean:write name="juvenileWarrantForm" property="chargeCourt"/></td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.petitionNumber"/></td>
		<td class=formDe><bean:write name="juvenileWarrantForm" property="petitionNum"/></td>
		<td class=formDeLabel><bean:message key="prompt.referralNumber"/></td>
		<td class=formDe><bean:write name="juvenileWarrantForm" property="referralNum"/></td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.charge"/></td>
		<td class=formDe colspan=3><bean:write name="juvenileWarrantForm" property="chargeDescription"/></td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.fileStampDate"/></td>
		<td class=formDe><bean:write name="juvenileWarrantForm" property="fileStampDate" formatKey="date.format.mmddyyyy" /></td>
		<td class=formDeLabel width="1%" nowrap><bean:message key="prompt.fileStampName"/></td>
		<td class=formDe><bean:write name="juvenileWarrantForm" property="fileStampName"/></td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.probationOfficerOfRecord"/>&nbsp;Name</td>
		<td class=formDe colspan=3><bean:write name="juvenileWarrantForm" property="probationOfficerOfRecordName" /></td>
	</tr>
	<tr>
		<td class=formDeLabel width="1%"><bean:message key="prompt.warrantOriginatorName"/></td>
		<td class=formDe colspan=3><bean:write name="juvenileWarrantForm" property="warrantOriginatorName"/></td>
	</tr>
<!-- END WARRANT INFORMATION BLOCK -->
<tr><td><br></td></tr>
<!-- BEGIN JUVENILE INFORMATION BLOCK -->
	<tr class="detailHead">
		<td class="subhead" colspan="5"><bean:message key="prompt.juvenileInfo"/></td>
	</tr>
	<tr>
		<td class=formDeLabel nowrap><bean:message key="prompt.name"/></td>
		<td class=formDe colspan=3><bean:write name="juvenileWarrantForm" property="juvenileName"/></td>
	</tr>
	<tr>	
		<td class=formDeLabel><bean:message key="prompt.juvenileNumber"/></td>
		<td class=formDe colspan=3><bean:write name="juvenileWarrantForm" property="juvenileNum"/></td> 
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.aka"/></td>
		<td class=formDe colspan=3><bean:write name="juvenileWarrantForm" property="aliasName"/></td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.dateOfBirth"/></td>
		<td class=formDe><bean:write name="juvenileWarrantForm" property="dateOfBirth" formatKey="date.format.mmddyyyy" /></td>
		<td class=formDeLabel><bean:message key="prompt.build"/></td>
		<td class=formDe><bean:write name="juvenileWarrantForm" property="build"/></td> 
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.ssn"/></td>
		<td class=formDe colspan=3><bean:write name="juvenileWarrantForm" property="formattedSSN"/></td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.race"/></td>
		<td class=formDe><bean:write name="juvenileWarrantForm" property="race"/></td>
		<td class=formDeLabel><bean:message key="prompt.sex"/></td>
		<td class=formDe><bean:write name="juvenileWarrantForm" property="sex"/></td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.height"/></td>
		<td class=formDe>
	       	<logic:notEqual name="juvenileWarrantForm" property="height" value="">
				<logic:notEqual name="juvenileWarrantForm" property="height" value="0">	
	           		<bean:write name="juvenileWarrantForm" property="heightFeet"/>ft&nbsp;
	           		<bean:write name="juvenileWarrantForm" property="heightInch"/>in&nbsp;
				</logic:notEqual>	           		
	       	</logic:notEqual>
		</td>
		<td class=formDeLabel><bean:message key="prompt.weight"/></td>
		<td class=formDe>
			<logic:notEqual name="juvenileWarrantForm" property="weight" value="">
				<logic:notEqual name="juvenileWarrantForm" property="weight" value="0">			
					<bean:write name="juvenileWarrantForm" property="weight"/>&nbsp;lbs
				</logic:notEqual>
			</logic:notEqual>
		</td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.eyeColor"/></td>
		<td class=formDe colspan=3>
			<logic:equal name="juvenileWarrantForm" property="eyeColor" value="">&nbsp;</logic:equal>
			<bean:write name="juvenileWarrantForm" property="eyeColor"/>
		</td>
	</tr>
	<tr> 
		<td class=formDeLabel><bean:message key="prompt.hairColor"/></td>
		<td class=formDe colspan=3>
			<logic:equal name="juvenileWarrantForm" property="hairColor" value="">&nbsp;</logic:equal>
			<bean:write name="juvenileWarrantForm" property="hairColor"/>
		</td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.complexion"/></td>
		<td class=formDe colspan=3>
			<logic:equal name="juvenileWarrantForm" property="complexion" value="">&nbsp;</logic:equal>
			<bean:write name="juvenileWarrantForm" property="complexion"/></td>
	</tr>
	<tr>
		<td class=formDeLabel valign=top><bean:message key="prompt.scarsMarks"/></td>
		<td class=formDe colspan=3>
			<logic:empty name="juvenileWarrantForm" property="scarsAndMarksSelected">&nbsp;</logic:empty>
			<logic:notEmpty name="juvenileWarrantForm" property="scarsAndMarksSelected">
				<logic:iterate id="scars" name="juvenileWarrantForm" property="scarsAndMarksSelected">
					<bean:write name="scars" property="description" /><br>
				</logic:iterate>
			</logic:notEmpty>
		</td>
	</tr>
	<tr> 
		<td class=formDeLabel  valign=top><bean:message key="prompt.tattoos"/></td>
		<td class=formDe colspan=3>
			<logic:empty name="juvenileWarrantForm" property="tattoosSelected">&nbsp;</logic:empty>
			<logic:notEmpty name="juvenileWarrantForm" property="tattoosSelected">
				<logic:iterate id="tattoo" name="juvenileWarrantForm" property="tattoosSelected">
					<bean:write name="tattoo" property="description" /><br>
				</logic:iterate>
			</logic:notEmpty>
		</td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.fbiNumber"/></td>
		<td class=formDe colspan=3>
			<logic:equal name="juvenileWarrantForm" property="fbiNum" value="">&nbsp;</logic:equal>
			<bean:write name="juvenileWarrantForm" property="fbiNum"/>
		</td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.stateIdNumber"/></td>
		<td class=formDe colspan=3>
			<logic:equal name="juvenileWarrantForm" property="sid" value="">&nbsp;</logic:equal>
			<bean:write name="juvenileWarrantForm" property="sid"/>
		</td>
	</tr> 
	<tr>
		<td class=formDeLabel><bean:message key="prompt.schoolDistrict"/></td>
		<td class=formDe colspan=3>
			<bean:write name="juvenileWarrantForm" property="schoolDistrictName"/>
		</td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.schoolName"/></td>
		<td class=formDe colspan=3>
			<bean:write name="juvenileWarrantForm" property="schoolName"/>
		</td>
	</tr>
	<tr>
		<td class=formDeLabel valign=top><bean:message key="prompt.cautions"/></td>
		<td class=formDe colspan=3>
			<logic:empty name="juvenileWarrantForm" property="cautionsSelected">&nbsp;</logic:empty>
			<logic:notEmpty name="juvenileWarrantForm" property="cautionsSelected">
				<logic:iterate id="caution" name="juvenileWarrantForm" property="cautionsSelected">
					<bean:write name="caution" property="description" /><br>
				</logic:iterate>
			</logic:notEmpty>
		</td>
	</tr>
	<tr>
		<td class=formDeLabel valign=top nowrap><bean:message key="prompt.otherCautionComments"/></td>
		<td class=formDe colspan=3>
			<bean:write name="juvenileWarrantForm" property="cautionComments"/>
		</td>
	<tr>
<!-- END JUVENILE INFORMATION TABLE -->
<tr><td><br></td></tr>
<!-- BEGIN EXISTING ASSOCIATE INFORMATION TABLES -->
	<tr >
		<td class="detailHead" colspan=4><bean:message key="prompt.juvenileAssociateInfo"/></td>
	</tr>
	<tr>
		<td class=formDeLabel colspan="3"><bean:message key="prompt.name"/></td>
		<td class=formDeLabel colspan="3"><bean:message key="prompt.relationshipToJuvenile"/></td>
	</tr>
	<logic:empty name="juvenileWarrantForm" property="associates"> 
		<tr>
			<td class=formDe colspan=4 align="center">No Responsible Adults Found</td>
		</tr> 
	</logic:empty>    
<%-- not required as part of interation #7   ?associateNumber=10 --%>  
	<logic:notEmpty name="juvenileWarrantForm" property="associates">   
		<logic:iterate id="associate" name="juvenileWarrantForm" property="associates"> 
			<% RecordCounter++;
               bgcolor = "class=formDe";
               if (RecordCounter % 2 == 1)
                  bgcolor = ""; %>
			<tr>                  
				<td <% out.print(bgcolor); %> colspan="3" align="left">
			        <a href="javascript:openWindow('/<msp:webapp/>displayAssociateDetails.do?associateNumber=<bean:write name="associate" property="associateNum"/>');">
					<bean:write name="associate" property="associateName.lastName" />,&nbsp; 
					<bean:write name="associate" property="associateName.firstName" /> 
					<bean:write name="associate" property="associateName.middleName" /></a></td>
				<td <% out.print(bgcolor); %> colspan="2" nowrap="nowrap" align="left"><bean:write  name="associate" property="relationshipToJuvenile" /></td>			 
			</tr>
		</logic:iterate> 
	</logic:notEmpty> 
<!-- END EXISTING ASSOCIATE INFORMATION TABLES -->
<tr><td><br></td></tr>
<!-- BEGIN STATUS UPDATE INFORMATION TABLES -->
   <tr>
      <td class=detailHead colspan=4>Activation&nbsp;<bean:message key="prompt.status"/>&nbsp;<bean:message key="prompt.info"/></td>
   </tr>
   <tr>
     <td class=formDeLabel nowrap><bean:message key="prompt.asstDistrictAttorneyName"/></td>
     <td class=formDe colspan=3><bean:write name="juvenileWarrantForm" property="asstDistrictAttorneyName"/></td>
   </tr>	 
   <tr>
     <td class=formDeLabel><bean:message key="prompt.1.diamond"/>Activation&nbsp;<bean:message key="prompt.status"/></td>
	 <td class=formDe colspan=3>
	 	<html:select property="warrantActivationStatusId">
  	       <html:option value=""><bean:message key="select.generic" /></html:option>
		   <html:option value="AC">Activate</html:option>
		   <html:option value="RJ">Reject</html:option>
		   <html:option value="US">Unsend</html:option>  	       
	    </html:select>	   
	 </td>
  </tr>
  <tr>
     <td class=formDeLabel valign=top><bean:message key="prompt.reasonRejectedOrUnsent"/></td>
     <td class=formDe colspan=3>
     	<html:textarea property="unsendNotSignedReason" 
     				   style="width:100%" 
     				   rows="5">
 		</html:textarea>
     </td>            
  </tr>
<!-- END STATUS UPDATE BLOCK -->  
</table>
<br>
<!-- BEGIN BUTTON TABLE -->
<table border="0" width="100%">
  <tr>
    <td align="center">
		<html:submit property="submitAction" onclick="return buttonCheck('N', this.form)" ><bean:message key="button.next"></bean:message></html:submit>
		<html:reset><bean:message key="button.reset"></bean:message></html:reset>
	    <logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="actDTA">
			<html:submit property="submitAction" onclick="return buttonCheck('C', this.form)" >
				<bean:message key="button.cancel"></bean:message>
			</html:submit>
	 	</logic:equal>
	 	<logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="actARR">
			<html:submit property="submitAction" onclick="return buttonCheck('C', this.form)" >
				<bean:message key="button.cancel"></bean:message>
			</html:submit>
	 	</logic:equal>
	 	<logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="actVOP">
			<html:submit property="submitAction" onclick="return buttonCheck('C',this.form)" >
				<bean:message key="button.cancel"></bean:message>
			</html:submit>
	 	</logic:equal>
	 	<logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="actVOPSummary">
			<html:submit property="submitAction" onclick="return buttonCheck('C',this.form)" >
				<bean:message key="button.cancel"></bean:message>
			</html:submit>
	 	</logic:equal>
    </td>
  </tr>
</table>
<!-- END BUTTON TABLE -->
</html:form>
<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>