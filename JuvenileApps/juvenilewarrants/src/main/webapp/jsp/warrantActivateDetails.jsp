<!DOCTYPE HTML>
<!-- Used to display request acknowledgement summary resulting from acknowledgement search. -->
<!--MODIFICATIONS -->
<!-- Debbie Williamson	10/06/2004	Create JSP -->
<!-- Leslie Deen		12/06/2004	Revise JSP -->
<!-- CShimek            08/12/2005  Revise to new look and feel -->
<!-- JFisher            08/16/2005  Removed acknowledgement date & time comment block -->
<!-- CShimek    		02/01/2006  Added hidden fields for helpFile -->
<%-- CShimek			12/21/2006  revised helpfile reference value--%>
<%-- CShimek			01/31/2007  #39097 added multiple submit button logic --%>
<%-- CShimek			03/22/2007  #40475 added missing weight unit annotation and logic tags --%>
<%-- CShimek			04/11/2007 	#41031 revised RA href to open in new window --%>
<%-- LDeen				06/04/2007  #42564 changed path to app.js --%>
<%-- LDeen				02/15/2008	#49255 removed extra </html:form> tag --%>
<%-- RYoung             08/6/2015   #27796 MJCW:  Adapt MJCW and Warrants to IE10 and 11 (Juv Profile - Education UI) --%>

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileWarrantConstants" %>
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

<title><bean:message key="title.heading"/> - <bean:message key="title.juvWarrantDTA"/>warrantActivateDetails.jsp</title>
<!-- APP JAVASCRIPT FILE -->
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
</head>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true)">

<html:form action="/submitWarrantActivate" target="content">
	<logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="actARR">
		<input type="hidden" name="helpFile" value="juvwarnt/juvenile_warrants_final.htm#|76">
	</logic:equal>
	<logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="actDTA">
		<input type="hidden" name="helpFile" value="juvwarnt/juvenile_warrants_final.htm#|26">	
	</logic:equal>        
<!-- BEGIN HEADING TABLE -->
<table width=100%>
  <tr>
    <td align="center" class="header" >
        <logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="actARR">
            <bean:message key="title.juvWarrantArrest"/>
        </logic:equal>
        <logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="actDTA">
            <bean:message key="title.juvWarrantDTA"/>
        </logic:equal>
        Activation Details</td>
  </tr>
</table>  
<!-- END HEADING TABLE -->
<br>
<!-- BEGIN INSTRUCTION/REQUIRED FIELDS TABLES -->
<table width="98%" border="0">
   <tr>
     <td>
	  <ul>
        <li>Verify that warrant displayed is correct warrant and select Finish to activate the warrant.</li>
        <li>Select Cancel to exit page without activating the warrant.</li>
 	  </ul>	</td>
  </tr>
</table>  
<!-- END INSTRUCTION/REQUIRED FIELDS TABLE -->
<br>
<!-- BEGIN MAIN BODY TABLE --> 	
<table width=98% border=0 cellspacing=1 cellpadding=4 align=center>
<!-- BEGIN JUVENILE WARRANT INFORMATION BLOCK -->  
	 <tr>
		<td class=detailHead colspan=4 nowrap><bean:message key="prompt.juvenileWarrantInfo" /></td>
	 </tr>
     <tr>			
        <td class=formDeLabel><bean:message key="prompt.warrantNumber"/></td>
        <td class=formDe colspan=3><bean:write name="juvenileWarrantForm" property="warrantNum"/></td>              
     <tr>			
        <td class=formDeLabel><bean:message key="prompt.transactionNumber"/></td>
        <td class=formDe><bean:write name="juvenileWarrantForm" property="transactionNum"/></td>              
        <td class=formDeLabel><bean:message key="prompt.juvenileNumber"/></td>
        <td class=formDe><bean:write name="juvenileWarrantForm" property="juvenileNum"/></td>                
     </tr>
     <tr>
        <td class=formDeLabel><bean:message key="prompt.daLogNumber"/></td>
        <td class=formDe colspan=3><bean:write name="juvenileWarrantForm" property="daLogNum"/></td>              
     </tr>	

<!-- END JUVENILE WARRANT INFORMATION BLOCK -->  	
<tr><td><br></td></tr>
<!-- Summary Of Facts block not in final UC v1.7  -->

<!-- BEGIN CHARGE INFORMATION BLOCK -->
	<tr>
		<td class=detailHead colspan=4 nowrap><bean:message key="prompt.chargeInfo"/></td>
	</tr>
    <logic:empty name="juvenileWarrantForm" property="charges">
		<tr>
			<td class=formDe colspan=4>No Charges Found</td>
		</tr>
		<tr><td><br></td></tr>
	</logic:empty>
	<logic:iterate id="chargeIndex" name="juvenileWarrantForm" property="charges"> 
		<tr>    
			<td class=formDeLabel><bean:message key="prompt.charge" /></td>
			<td class=formDe colspan=3><bean:write name="chargeIndex" property="offense"/></td>
		</tr>
		<tr>	         
			<td class=formDeLabel><bean:message key="prompt.petitionNumber"/></td>
			<td class=formDe colspan=3><bean:write name="chargeIndex" property="petitionNum"/></td>
		</tr>
<%-- sequence number not displayed because it is not a store entity value 			
		<tr>	 
			<td class=formDeLabel nowrap><bean:message key="prompt.chargeSeqNumber"/></td>
			<td class=formDe colspan=3><bean:write name="chargeIndex" property="sequenceNum"/></td>       	         		       
		</tr> --%>
		<tr>
			<td class=formDeLabel><bean:message key="prompt.chargeCourt"/></td>
			<td class=formDe colspan=3><bean:write name="chargeIndex" property="court"/></td>
		</tr>
		<tr>	
			<td class=formDeLabel><bean:message key="prompt.chargeNCICNumber"/></td>
			<td class=formDe colspan=3><bean:write name="chargeIndex" property="offenseCodeId"/></td>
		</tr>
		<tr><td><br></td></tr>
	</logic:iterate>
<!-- END CHARGE INFORMATION BLOCK -->
<!-- BEGIN JUVENILE INFORMATION BLOCK -->
	<tr>
	 	<td class=detailHead colspan=4 nowrap><bean:message key="prompt.juvenileInfo" /></td>
	</tr>
    <tr>
       <td class=formDeLabel><bean:message key="prompt.juvenileName"/></td>
       <td class=formDe colspan="3">
          <%-- <bean:write name="juvenileWarrantForm" property="juvenileName"/> --%>
          <bean:write name="juvenileWarrantForm" property="juvenileName.lastName"/>, 
          <bean:write name="juvenileWarrantForm" property="juvenileName.firstName"/>
          <bean:write name="juvenileWarrantForm" property="juvenileName.middleName"/> 
          <bean:write name="juvenileWarrantForm" property="nameSuffix"/>
       </td>
     </tr>
     <tr>
        <td class=formDeLabel nowrap><bean:message key="prompt.aka"/></td>
        <td class=formDe colspan="3">
           <bean:write name="juvenileWarrantForm" property="aliasName"/>
        </td>
     </tr>
     <tr>
        <td class=formDeLabel><bean:message key="prompt.dateOfBirth"/></td>
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
		<td class=formDeLabel nowrap><bean:message key="prompt.ssn"/></td>
		<td class=formDe colspan=3>
			<bean:write name="juvenileWarrantForm" property="formattedSSN"/></td>
	</tr> 
<!--<tr>
		<td class=formDeLabel><bean:message key="prompt.phone"/></td>
		<td class=formDe colspan=3><bean:write name="juvenileWarrantForm" property="phoneNum.formattedPhoneNumber"/></td>
	</tr>  -->      
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
 	     		<logic:notEqual name="juvenileWarrantForm" property="heightFeet" value="0">
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
		<td class=formDeLabel valign=top><bean:message key="prompt.scarsMarks"/></td>
		<td class=formDe colspan=3>
			<logic:iterate id="scars" name="juvenileWarrantForm" property="scarsAndMarksSelected">
                <bean:write name="scars" property="description" /><br>
           	</logic:iterate>
		</td>
	</tr>
	<tr>
		<td class=formDeLabel valign=top><bean:message key="prompt.tattoos"/></td>
		<td class=formDe colspan=3>
			<logic:iterate id="tattoo" name="juvenileWarrantForm" property="tattoosSelected">
            	<bean:write name="tattoo" property="description" /><br>
            </logic:iterate>
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
        <td class=formDeLabel colspan="2"><bean:message key="prompt.name"/></td>
	    <td class=formDeLabel colspan="2"><bean:message key="prompt.relationshipToJuvenile"/></td>
    </tr>
    <logic:empty name="juvenileWarrantForm" property="associates"> 
        <tr>
           <td class=formDe colspan=4 align="center">No Associates Found</td>
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
	              		<td <% out.print(bgcolor); %> colspan="2" align="left">
<%-- 	              			<a href="/<msp:webapp/>displayAssociateDetails.do?relationshipId=<bean:write name="associate" property="relationshipToJuvenileId"/>">	               --%>
							<a href="javascript:openWindow('/<msp:webapp/>displayAssociateDetails.do?relationshipId=<bean:write name="associate" property="relationshipToJuvenileId"/>');">	               							      	                  
	                  		<bean:write name="associate" property="associateName.lastName" />, <bean:write name="associate" property="associateName.firstName" /> <bean:write name="associate" property="associateName.middleName" /></a>
	                  	</td>
	              </logic:equal>
	              <logic:notEqual name="associate" property="associateNum" value="">
	               		<td <% out.print(bgcolor); %> colspan="2" align="left">
<%-- 	               			<a href="/<msp:webapp/>displayAssociateDetails.do?associateNumber=<bean:write name="associate" property="associateNum"/>">	 --%>
							<a href="javascript:openWindow('/<msp:webapp/>displayAssociateDetails.do?associateNumber=<bean:write name="associate" property="associateNum"/>');">	               							      	               			              
	                  		<bean:write name="associate" property="associateName.lastName" />, <bean:write name="associate" property="associateName.firstName" /> <bean:write name="associate" property="associateName.middleName" /></a>
	                  	</td>
	              </logic:notEqual>      
	        	       <td <% out.print(bgcolor); %> colspan="2" nowrap="nowrap" align="left"><bean:write  name="associate" property="relationshipToJuvenile" /></td>			 
            </tr>
        </logic:iterate> 
    </logic:notEmpty> 
<!-- END EXISTING ASSOCIATE INFORMATION BLOCK -->
 <tr><td><br></td></tr>
<!-- BEGIN LAW ENFORCEMENT INFORMATION BLOCK -->
	 <tr>
	 	<td class=detailHead colspan=4 nowrap><bean:message key="prompt.lawEnforcementInfo" /></td>
	 </tr>
     <tr>
     	<td class=formDeLabel><bean:message key="prompt.officerIdNumber"/></td>
        <td class=formDe><bean:write name="juvenileWarrantForm" property="officerId"/></td>
        <td class=formDeLabel width="1%" nowrap><bean:message key="prompt.officerIdType"/></td>
        <td class=formDe><bean:write name="juvenileWarrantForm" property="officerIdType"/></td> 
     </tr>
     <tr>   
	    <td class=formDeLabel><bean:message key="prompt.department"/></td>
        <td class=formDe colspan=3><bean:write name="juvenileWarrantForm" property="officerAgencyName"/></td>
     </tr>
     <tr>
        <td class=formDeLabel><bean:message key="prompt.officerName"/></td>
        <td class=formDe colspan=3><bean:write name="juvenileWarrantForm" property="officerName.formattedName"/></td>
     </tr>
     <tr>
        <td class=formDeLabel><bean:message key="prompt.workPhone"/></td>
        <td class=formDe><bean:write name="juvenileWarrantForm" property="officerPhoneNum"/></td>
        <td class=formDeLabel><bean:message key="prompt.cellPhone"/></td>
        <td class=formDe><bean:write name="juvenileWarrantForm" property="officerCellNum"/></td>
     </tr>
     <tr>
        <td class=formDeLabel><bean:message key="prompt.pager"/></td>
        <td class=formDe><bean:write name="juvenileWarrantForm" property="officerPager"/></td> 
	    <td class=formDeLabel><bean:message key="prompt.email"/></td>
        <td class=formDe>             
           <bean:write name="juvenileWarrantForm" property="email"/>
        </td>
     </tr>
     <tr>
        <td class=formDeLabel><bean:message key="prompt.affidavitStatement"/></td>
        <td class=formDe colspan=3>
            <bean:write name="juvenileWarrantForm" property="affidavitStatement"/>
        </td>
     </tr>
<!-- END LAW ENFORCEMENT INFORMATION BLOCK -->
 <tr><td><br></td></tr>
<!-- BEGIN WARRANT ORIGINATOR INFORMATION BLOCK -->
	<tr>
		<td class=detailHead colspan=4 nowrap><bean:message key="prompt.warrantOriginatorInfo" /></td>
	</tr>
	<tr> 
		<td class=formDeLabel width="1%" nowrap><bean:message key="prompt.warrantOriginatorName"/></td>
		<td class=formDe colspan=3>
			<bean:write name="juvenileWarrantForm" property="warrantOriginatorName"/>
		</td>
	</tr> 
<!-- END WARRANT ORIGINATOR INFORMATION BLOCK --> 	
</table>
<!-- END MAIN BODY TABLE --> 	
<br>
<!-- BEGIN BUTTON TABLE -->
<table width="98%" border="0" align="center">
	 <tr valign="top">
	       <td align="right" width="48%"> 
		     <html:submit property="submitAction" onclick="return disableSubmit(this, this.form);">
			 <bean:message key="button.finish"></bean:message>
		   	</html:submit>
		
		</td>
</html:form>
		<td align="left">
			<logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="actDTA">
				<html:form action="/displayGenericSearch.do?warrantTypeUI=actDTA"> 			  
					<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);">
						<bean:message key="button.cancel"></bean:message>
					</html:submit>
				</html:form>
			</logic:equal>
			<logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="actARR">
				<html:form action="/displayGenericSearch.do?warrantTypeUI=actARR"> 			  
					<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);">
						<bean:message key="button.cancel"></bean:message>
					</html:submit>
				</html:form>
			</logic:equal>
			<logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="actVOP">
				<html:form action="/displayGenericSearch.do?warrantTypeUI=actVOP"> 			  
					<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);">
						<bean:message key="button.cancel"></bean:message>
					</html:submit>
				</html:form>
			</logic:equal>			
		</td>
      </tr>
    </table>
	 <!-- END BUTTON TABLE -->	

<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>