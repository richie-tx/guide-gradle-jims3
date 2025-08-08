<!DOCTYPE HTML>
<!--MODIFICATIONS -->
<!-- CShimek   	08/16/2005  Revised look and feel -->
<%-- JFisher   	08/23/2005  Update form fields to reflect use of java.util.Date instead of String --%>
<%-- CShimek   	10/04/2005  ER#24012 change assocaite labels to responsible adult --%>
<%-- CShimek   	02/02/2006  Add hidden field for HelpFile name --%>
<%-- CShimek   	03/08/2006  Removed include of warrantJOTCreate.js, not needed on page --%> 
<%-- LDeen	    12/21/2006  Revised help file code --%>
<%-- CShimek	03/22/2007  #40475 added missing weight unit annotation --%>
<%-- CShimek	04/11/2007 	#41031 revised RA href to open in new window --%>
<%-- LDeen		06/07/2007	#42564 changed path to app.js --%>
<%-- RCapestani	09/19/2007	#44731 comment out Juv Phone --%>
<%-- RYoung     08/06/2015 #27796 MJCW:  Adapt MJCW and Warrants to IE10 and 11 (Juv Profile - Education UI) --%>

<html>
<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<!-- SCRIPTING VARIABLES -->
<jsp:useBean id="codeHelper" scope="application" class="ui.juvenilewarrant.helper.JuvenileWarrantListHelper" />

<!-- BEGIN HEADER TAG -->
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
<title><bean:message key="title.heading"/> - Juvenile Warrants - warrantVOPUpdateSummary.jsp</title>

<!--THESE TWO LINES FOR STRUTS & JAVASCRIPTS VALIDATIONS-->
<html:javascript formName="juvenileWarrantForm" />

</head>
<body>
<html:form action="/displayVOPUpdateSummary" target="content" >
<input type="hidden" name="warrantType" value="">
       <logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="updateVOPSuccess">
<input type="hidden" name="helpFile" value="juvwarnt/juvenile_warrants_final.htm#|135">
		</logic:equal>
		<logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="updateVOPConfirm">
<input type="hidden" name="helpFile" value="juvwarnt/juvenile_warrants_final.htm#|133">
        </logic:equal>

<!-- BEGIN HEADING TABLE -->
<table width=100% border=0>
  <tr>
    <td align="center" class="header" ><bean:message key="title.juvWarrantVOP"/>&nbsp; 
        <logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="updateVOPSuccess">
				Summary
		</logic:equal>
		<logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="updateVOPConfirm">
				Confirmation
        </logic:equal>
    
    </td>
  </tr>
</table>
<!-- END HEADING TABLE -->
<!-- BEGIN INSTRUCTION TABLE -->
<table width="98%" border="0" cellpadding="2" cellspacing="0">
  <tr>
		<logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="updateVOPConfirm">
				<td align="center" colspan="2" class="confirm">Juvenile Warrant successfully updated and Request for Signature notification sent.</td>
        </logic:equal>    
  </tr>
</table>
<!-- END INSTRUCTION TABLE -->
<table width=98% border=0 cellspacing=1 cellpadding=4 align=center>
<!-- BEGIN JUVENILE WARRANT INFORMATION BLOCK -->
	<tr>
		<td colspan=4 class="detailHead"><bean:message key="prompt.juvenileWarrantInfo" /></td>
	</tr>
	<tr>
	   <td class=formDeLabel nowrap><bean:message key="prompt.warrantNumber"/></td>
	   <td class=formDe colspan="3"><bean:write name="juvenileWarrantForm" property="warrantNum"/></td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.juvenileNumber"/></td>
		<td class=formDe><bean:write name="juvenileWarrantForm" property="juvenileNum"/></td>
		<td class=formDeLabel width="1%" nowrap><bean:message key="prompt.referralNumber"/></td>
		<td class=formDe><bean:write name="juvenileWarrantForm" property="referralNum"/></td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.probationOfficer"/>&nbsp;of Record</td>
		<td class=formDe colspan=3><bean:write name="juvenileWarrantForm" property="probationOfficerOfRecordName"/></td>
	</tr>
<!-- END WARRANT INFORMATION BLOCK -->
<tr><td><br></td></tr>
<!-- BEGIN CHARGE INFORMATION BLOCK -->
	<tr>
		<td class=detailHead colspan=4><bean:message key="prompt.chargeInfo"/></td>
	</tr>
<%--    <logic:empty name="juvenileWarrantForm" property="chargesSelected">
		<tr>
			<td class=formDe colspan=4>No Charges Found</td>
		</tr>
	</logic:empty> --%>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.charge"/></td>
		<td class=formDe colspan=3><bean:write name="juvenileWarrantForm" property="chargeDescription"/></td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.petitionNumber"/></td>
		<td class=formDe><bean:write name="juvenileWarrantForm" property="petitionNum"/></td>
		<td class=formDeLabel><bean:message key="prompt.court"/></td>
		<td class=formDe><bean:write name="juvenileWarrantForm" property="court"/></td>
	</tr>
<!-- END CHARGE INFORMATION BLOCK -->
<tr><td><br></td></tr>
<!-- BEGIN JUVENILE INFORMATION BLOCK -->
	<tr>
		<td class=detailHead colspan=4><bean:message key="prompt.juvenileWarrantInfo"/></td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.name"/></td>
		<td class=formDe colspan=3>
			<bean:write name="juvenileWarrantForm" property="juvenileName"/>
		</td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.aka"/></td>
		<td class=formDe colspan=3>
			<bean:write name="juvenileWarrantForm" property="aliasName"/>
		</td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.dateOfBirth"/></td>
		<td class=formDe><bean:write name="juvenileWarrantForm" property="dateOfBirth" formatKey="date.format.mmddyyyy" /></td>
		<td class=formDeLabel><bean:message key="prompt.build"/></td>
		<td class=formDe>
			<bean:write name="juvenileWarrantForm" property="build"/>
		</td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.ssn"/></td>
		<td class=formDe colspan=3>
			<bean:write name="juvenileWarrantForm" property="formattedSSN"/></td>
<!-- 	<td class=formDeLabel><bean:message key="prompt.phone"/></td>
		<td class=formDe><bean:write name="juvenileWarrantForm" property="phoneNum"/></td>  -->
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.race"/></td>
		<td class=formDe>
			<bean:write name="juvenileWarrantForm" property="race"/>
		</td>
		<td class=formDeLabel><bean:message key="prompt.sex"/></td>
		<td class=formDe>
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
			<bean:write name="juvenileWarrantForm" property="eyeColor"/>
		</td>
	</tr>
	<tr>	
		<td class=formDeLabel><bean:message key="prompt.hairColor"/></td>
		<td class=formDe colspan=3>
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
		<td class=formDeLabel><bean:message key="prompt.scarsMarks"/></td>
		<td class=formDe colspan=3>
			<logic:iterate id="scars" name="juvenileWarrantForm" property="scarsAndMarksSelected">
                <bean:write name="scars" property="description" /><br>
           	</logic:iterate>
		</td>
	</tr>
	<tr>	
		<td class=formDeLabel><bean:message key="prompt.tattoos"/></td>
		<td class=formDe colspan=3>
			<logic:iterate id="tattoo" name="juvenileWarrantForm" property="tattoosSelected">
            	<bean:write name="tattoo" property="description" /><br>
            </logic:iterate>
		</td>
	</tr>    
	<tr>
		<td class=formDeLabel><bean:message key="prompt.schoolDistrict"/></td>
		<td class=formDe colspan=3><bean:write name="juvenileWarrantForm" property="schoolDistrictName"/></td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.schoolName"/></td>
		<td class=formDe colspan=3><bean:write name="juvenileWarrantForm" property="schoolName"/></td>
	</tr>
	<tr>
		<td class=formDeLabel valign="top"><bean:message key="prompt.cautions"/></td>
		<td class=formDe colspan=3>
			<logic:iterate id="caution" name="juvenileWarrantForm" property="cautionsSelected">
              		<bean:write name="caution" property="description" /><br>
           	 </logic:iterate>
		</td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.otherCautionComments"/></td>
		<td class=formDe colspan=3><bean:write name="juvenileWarrantForm" property="cautionComments"/></td>
	</tr>
<!-- END JUVENILE INFORMATION BLOCK -->
<tr><td><br></td></tr>
<!-- VARIABLES NEEDED FOR ASSOCIATES DISPLAY -->
<%int RecordCounter = 0; 
  String bgcolor = "";%>
<!-- BEGIN EXISTING ASSOCIATE INFORMATION BLOCK -->
	<tr>
	 	<td class=detailHead colspan=4><bean:message key="prompt.juvenileAssociateInformation" /></td>
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
						<bean:write name="associate" property="associateName.lastName"/>, <bean:write name="associate" property="associateName.firstName" /><bean:write name="associate" property="associateName.middleName" /></a>
					</td>
				</logic:equal>
				<logic:notEqual name="associate" property="associateNum" value="">
					<td <% out.print(bgcolor); %> colspan="3" align="left">
					    <a href="javascript:openWindow('/<msp:webapp/>displayAssociateDetails.do?associateNumber=<bean:write name="associate" property="associateNum"/>');"> 						
						<bean:write name="associate" property="associateName.lastName" />, <bean:write name="associate" property="associateName.firstName" /><bean:write name="associate" property="associateName.middleName" /></a>
					</td>
				</logic:notEqual>      
				<td <% out.print(bgcolor); %> colspan="3" align="left"><bean:write  name="associate" property="relationshipToJuvenile" /></td>			 
			</tr>
		</logic:iterate> 
	</logic:notEmpty> 
<!-- END EXISTING ASSOCIATE INFORMATION BLOCK -->
 <tr><td><br></td></tr>
<!-- BEGIN WARRANT ORIGINATOR INFORMATION BLOCK -->
	<tr> <!-- bgcolor="#B3C9F5" -->
		<td class="detailHead" colspan=4><bean:message key="prompt.warrantOriginatorInfo"/></td>
	</tr>
	<tr>
		<td class=formDeLabel nowrap width="1%"><bean:message key="prompt.warrantOriginatorName"/></td>
		<td class=formDe colspan=3><bean:write name="juvenileWarrantForm" property="warrantOriginatorName"/></td> 
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.warrantOriginatorDepartmentName"/></td>
		<td class=formDe colspan=3><bean:write name="juvenileWarrantForm" property="warrantOriginatorAgencyName"/></td> 
	</tr>
<!-- END WARRANT ORIGINATOR INFORMATION BLOCK -->
</table>
</html:form>
<!-- END MAIN BODY TABLE -->
<br>
<!-- BEGIN BUTTON TABLE -->
<table border="0" width="100%">
  <tr>
	<td align="center">
		<logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="updateVOPSuccess">
		<html:form action="/displayVOPUpdateSummary">
			<html:button property="org.apache.struts.taglib.html.BACK" onclick="history.go(-1)"><bean:message key="button.back"></bean:message></html:button>&nbsp;
			<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.finish"></bean:message></html:submit>&nbsp;
			<html:button property="org.apache.struts.taglib.html.CANCEL" onclick="document.location.href='/JuvenileWarrants/displayGenericSearch.do?warrantTypeUI=updateVOP&associateUpdatable=false'">
				<bean:message key="button.cancel"></bean:message>
			</html:button>
		</html:form>
		</logic:equal>

		
		<logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="updateVOPConfirm">
		<table border=0>
		   <tr align=center>
		    
		 	<jims2:isAllowed requiredFeatures="JW-MNG-ASSOC">
			 	<html:form action="/displayWarrantAssociateCreateUpdate.do?action=create">
			 	<td>
 	   			<html:submit property="submitAction">
 	   					<bean:message key="button.createAssociate"></bean:message>
 	   			</html:submit>
 	   			</td>
 				</html:form>
 			</jims2:isAllowed>
 			
 			
 		 	<jims2:isAllowed requiredFeatures="JW-MNG-ASSOC">
				<html:form action="/displayWarrantAssociateCreateUpdate.do?action=update">
				<td>
      				 <html:submit property="submitAction">
       				 <bean:message key="button.updateAssociate"></bean:message>
       				 </html:submit>
       			 </td>
   				 </html:form>
   			   
   			 </jims2:isAllowed>
   			 <html:form action="/displayVOPUpdateSummary" target="content" >
   			 <td>
   			
   			 <html:submit property="submitAction">
       				 <bean:message key="button.mainPage"></bean:message>
       				 </html:submit>
			  <%-- <html:button property="org.apache.struts.taglib.html.CANCEL" 
						 onclick="document.location.href='../security.war/jsp/mainMenu.jsp'">
    			   </html:button> 
    		 --%>
    		
			</td>
			 </html:form>
   		</table>	 
        </logic:equal>
	 </td>
</tr>
</table>

<!-- END BUTTON TABLE -->
<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>