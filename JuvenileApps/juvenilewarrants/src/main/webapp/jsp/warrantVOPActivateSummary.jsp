<!DOCTYPE HTML>
<!-- Used to display request acknowledgement summary resulting from acknowledgement search. -->
<!--MODIFICATIONS -->
<%-- Debbie Williamson	02/25/2005	Create JSP --%>
<%-- CShimek    08/12/2005  Revised to new look and feel --%>
<%-- JFisher    08/23/2005  Update form fields to reflect use of java.util.Date instead of String --%>
<%-- CShimek    10/04/2005  ER#24012 change assocaite labels to responsible adult --%>
<%-- CShimek    02/01/2006  Added hidden fields for HelpFile access --%>
<%-- CShimek    11/10/2006  Defect#36161 Added logic tags for confirmation messages based on status --%>
<%-- LDeen	    12/21/2006  Revised help file code --%>
<%-- CShimek	03/22/2007  #40475 added missing weight unit annotation --%>
<%-- LDeen		03/27/2007  Fixed width="1%" attribute in wrong place --%>
<%-- CShimek	04/11/2007 	#41031 revised RA href to open in new window --%>
<%-- LDeen		05/08/2007 	#41859 changed court to chargeCourt so correct court would display --%>
<%-- LDeen		06/07/2007	#42564 changed path to app.js --%>
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
<!-- JAVASCRIPT FILES-->
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
<html:base />
<title><bean:message key="title.heading"/> - Juvenile Warrants - warrantVOPActivateSummary.jsp</title>
</head>
<body>

<html:form action="/submitWarrantVOPActivate" target="content">
	<logic:equal name="juvenileWarrantForm" property="action" value="summary"> 
<input type="hidden" name="helpFile" value="juvwarnt/juvenile_warrants_final.htm#|148">
	</logic:equal>
	<logic:equal name="juvenileWarrantForm" property="action" value="confirm"> 
<input type="hidden" name="helpFile" value="juvwarnt/juvenile_warrants_final.htm#|145">
	</logic:equal>

<!-- BEGIN HEADING TABLE -->
<logic:equal name="juvenileWarrantForm" property="action" value="summary"> 
   <table align="center">
     <tr>
        <td class="header"><bean:message key="title.juvWarrantVOP"/> Activation Summary</td>
     </tr>
   </table>
   <!-- BEGIN MESSAGE TABLE --> 
   <table width="98%">
	<tr>
     	<td><ul>
        	<li>Select Finish button to assign activation status.</li>
        	<li>Clicking on Existing Responsible Adult name displays full address and contact information. </li>   	
      	</ul></td>
  	</tr>
  </table>
   <!-- END MESSAGE TABLE --> 
</logic:equal>
<logic:equal name="juvenileWarrantForm" property="action" value="confirm">
   <table align="center">
     <tr>
        <td class="header"><bean:message key="title.juvWarrantVOP"/> Activation Confirmation</td>  
     </tr>
   </table>
   <!-- BEGIN MESSAGE TABLE --> 
   <table width="98%" border="0" align="center">
   <tr>
     <td align="center" class="confirm">
     <logic:equal name="juvenileWarrantForm" property="warrantActivationStatusId" value="US">
	 	Warrant Status set to Unsend and notification has been sent.
     </logic:equal>	
     <logic:equal name="juvenileWarrantForm" property="warrantActivationStatusId" value="AC">
	 	Warrant Status set to Active and notification has been sent.
     </logic:equal>	
     <logic:equal name="juvenileWarrantForm" property="warrantActivationStatusId" value="RJ">
	 	Warrant Status set to Reject and notification has been sent.
     </logic:equal>	

     </td>
   </tr>
   </table>
   <!-- END MESSAGE TABLE --> 
</logic:equal>
<!-- END HEADING TABLE -->

<!-- BEGIN MAIN BODY TABLE -->
<table width=98% border=0 cellspacing=1 cellpadding=4 align=center>
<!-- BEGIN JUVENILE WARRANT INFORMATION BLOCK -->
   <tr>
      <td class=detailHead colspan="4"><bean:message key="prompt.warrantInfo"/></td>
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
		<td class=formDe colspan=3><bean:write name="juvenileWarrantForm" property="probationOfficerOfRecordName"/></td>
	</tr>
	<tr>
		<td class=formDeLabel width="1%"><bean:message key="prompt.warrantOriginatorName"/></td>
		<td class=formDe colspan=3><bean:write name="juvenileWarrantForm" property="warrantOriginatorName"/></td>
	</tr>
<!-- END WARRANT INFORMATION BLOCK -->
<tr><td><br></td></tr>
<!-- BEGIN JUVENILE INFORMATION TABLE -->
	<tr class="detailHead">
		<td class="subhead" colspan="5"><bean:message key="prompt.juvenileInfo"/></td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.name"/></td>
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
		<td class=formDeLabel nowrap><bean:message key="prompt.ssn"/></td>
		<td class=formDe colspan=3><bean:write name="juvenileWarrantForm" property="formattedSSN"/></td>
	</tr> 	
	<tr> 
		<td class=formDeLabel><bean:message key="prompt.phone"/></td>
		<td class=formDe colspan=3><bean:write name="juvenileWarrantForm" property="phoneNum.formattedPhoneNumber"/></td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.race"/></td>
		<td class=formDe>
			<logic:equal name="juvenileWarrantForm" property="race" value="">&nbsp;</logic:equal>                
			<bean:write name="juvenileWarrantForm" property="race"/>
		</td>
		<td class=formDeLabel><bean:message key="prompt.sex"/></td>
		<td class=formDe>
			<logic:equal name="juvenileWarrantForm" property="sex" value="">&nbsp;</logic:equal>                
			<bean:write name="juvenileWarrantForm" property="sex"/>
		</td>
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
			<bean:write name="juvenileWarrantForm" property="complexion"/>
		</td>
	</tr>   
	<tr>
		<td class=formDeLabel valign=top><bean:message key="prompt.scarsMarks"/></td>
		<td class=formDe colspan=3>
			<logic:notEmpty name="juvenileWarrantForm" property="scarsAndMarksSelected">
				<logic:iterate id="scars" name="juvenileWarrantForm" property="scarsAndMarksSelected">
					<bean:write name="scars" property="description" /><br>
				</logic:iterate>
			</logic:notEmpty>			
		</td>
	</tr>
	<tr>
		<td class=formDeLabel valign=top><bean:message key="prompt.tattoos"/></td>
		<td class=formDe colspan=3>
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
			<bean:write name="juvenileWarrantForm" property="fbiNum"/>
		</td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.stateIdNumber"/></td>
		<td class=formDe colspan=3>
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
             <logic:iterate id="caution" name="juvenileWarrantForm" property="cautionsSelected">
              		<bean:write name="caution" property="description" /><br>
           	 </logic:iterate>
		</td>
	</tr>
	<tr>
		<td class=formDeLabel valign=top nowrap><bean:message key="prompt.otherCautionComments"/></td>
		<td class=formDe colspan=3>
			<logic:equal name="juvenileWarrantForm" property="cautionComments" value="">&nbsp;</logic:equal>                
			<bean:write name="juvenileWarrantForm" property="cautionComments"/>
		</td>
	</tr>
<!-- END JUVENILE INFORMATION BLOCK -->
<tr><td><br></td></tr>
<!-- BEGIN EXISTING ASSOCIATE INFORMATION BLOCK -->
	<tr>
	 	<td class=detailHead colspan=4 nowrap><bean:message key="prompt.juvenileAssociateInformation" /></td>
	</tr>
<!-- VARIABLES NEEDED FOR ASSOCIATE DISPLAY -->
 <% int RecordCounter = 0; 
    String bgcolor = "";%>
    <tr>
        <td class=formDeLabel colspan="3"><bean:message key="prompt.name"/></td>
	    <td class=formDeLabel colspan="3"><bean:message key="prompt.relationshipToJuvenile"/></td>
    </tr>
    <logic:empty name="juvenileWarrantForm" property="associates"> 
        <tr>
           <td class=formDe colspan=4 align="center">No Responsible Adults Found</td>
        </tr> 
    </logic:empty>    
<%-- not required as part of interation #7   ?associateNumber=10--%> 
    <logic:notEmpty name="juvenileWarrantForm" property="associates">   
        <logic:iterate id="associate" name="juvenileWarrantForm" property="associates"> 
            <% RecordCounter++;
               bgcolor = "class=formDe";
               if (RecordCounter % 2 == 1)
                  bgcolor = ""; %>
            <tr>      
	              <logic:equal name="associate" property="associateNum" value="">
	              		<td <% out.print(bgcolor); %> align="left">
					          <a href="javascript:openWindow('/<msp:webapp/>displayAssociateDetails.do?relationshipId=<bean:write name="associate" property="relationshipToJuvenileId"/>');"> 
		                  		<bean:write name="associate" property="associateName.lastName" />, <bean:write name="associate" property="associateName.firstName" /> <bean:write name="associate" property="associateName.middleName" /></a>
	                  	</td>
	              </logic:equal>
	              <logic:notEqual name="associate" property="associateNum" value="">
	               		<td <% out.print(bgcolor); %> colspan="3" align="left">
					          <a href="javascript:openWindow('/<msp:webapp/>displayAssociateDetails.do?associateNumber=<bean:write name="associate" property="associateNum"/>');">	               							      	               			              	               		               
	        	          		<bean:write name="associate" property="associateName.lastName" />, <bean:write name="associate" property="associateName.firstName" /> <bean:write name="associate" property="associateName.middleName" /></a>
	                  	</td>
	              </logic:notEqual>      
	        	       <td <% out.print(bgcolor); %> colspan=2 nowrap="nowrap" align="left"><bean:write  name="associate" property="relationshipToJuvenile" /></td>			 
            </tr>
        </logic:iterate> 
    </logic:notEmpty> 
<!-- END EXISTING ASSOCIATE INFORMATION BLOCK -->
 <tr><td><br></td></tr>
<!-- BEGIN STATUS UPDATE INFORMATION BLOCK -->
   <tr class="detailHead">
      <td class="subhead" colspan="5">Activation&nbsp;<bean:message key="prompt.status"/>&nbsp;
      <bean:message key="prompt.info"/></td>
   </tr>
   <tr>
     <td class=formDeLabel nowrap><bean:message key="prompt.asstDistrictAttorneyName"/></td>
     <td class=formDe colspan=3><bean:write name="juvenileWarrantForm" property="asstDistrictAttorneyName"/></td>
   </tr>	 
   <tr>
     <td class=formDeLabel>Activation&nbsp;<bean:message key="prompt.status"/></td>
	 <td class=formDe colspan=3><bean:write name="juvenileWarrantForm" property="warrantActivationStatus"/></td>
  </tr>
  <tr>
     <td class=formDeLabel valign=top><bean:message key="prompt.reasonRejectedOrUnsent"/></td>
     <td class=formDe colspan=3><bean:write name="juvenileWarrantForm" property="unsendNotSignedReason"/></td>            
  </tr>
<!-- END STATUS UPDATE BLOCK -->  
</table>
<!-- END MAIN BODY TABLE -->
<br>
<!-- BEGIN BUTTON TABLE -->
<table width="98%" border="0" align="center">
	<tr valign="top" align="center">  
	<td>
		<logic:equal name="juvenileWarrantForm" property="action" value="confirm">
       	<html:submit property="submitAction">
       				 <bean:message key="button.mainPage"></bean:message>
       				 </html:submit>
			  <%-- <html:button property="org.apache.struts.taglib.html.CANCEL" 
						 onclick="document.location.href='../security.war/jsp/mainMenu.jsp'">
    			   </html:button> 
    		 --%>
		</logic:equal>
		<logic:notEqual name="juvenileWarrantForm" property="action" value="confirm">
			<html:submit property="submitAction">
				<bean:message key="button.back" />
			</html:submit>&nbsp;
			<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);">
				<bean:message key="button.finish" />
			</html:submit>&nbsp;
		</logic:notEqual>
	</td>
	</tr>
</table>
<!-- END BUTTON TABLE -->
</html:form>  
<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>