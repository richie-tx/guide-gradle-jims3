<!-- Used for Arrest, Directive To Apprehend and Probable Cause Warrants Confirmation -->
<!--MODIFICATIONS -->
<!-- CShimek	05/17/2004	Create JSP -->
<!-- LDeen		12/16/2004	Revise JSP -->
<!-- CShimek    08/11/2005  Revised to new look and feel --> 
<!-- CShimek    08/11/2005  ER 23735, change warrant number to display with confirm class -->
<%-- JFisher    08/23/2005  Update form fields to reflect use of java.util.Date instead of String --%>
<%-- CShimek    10/04/2005  ER#24012 change associate labels to responsible adult --%>
<%-- CShimek    02/01/2006  Added and Revised hidden field helpFile for each warrant type --%>
<%-- LDeen	    03/07/2006  Added width="1%" to juvenile number & referral number-2nd column --%>
<%-- CShimek	12/21/2006  revised helpfile reference value--%>
<%-- CShimek	03/23/2007  #40475 revised logic tags for height and weight to not display zero --%>
<%-- CShimek 	04/11/2007  #41031 revised RA href to open in new window and RA buttons to close open window when selected --%>
<%-- LDeen		06/04/2007  #42564 changed path to app.js --%>
<%-- RCapestani 11/20/2007  #45649, #45706, #45707 added ?associateNum= to action=create&details=false --%>
<%-- RYoung		06/24/2009  #60523 Added logic around the confirm messages to display an error message --%>
<%-- RYoung     08/6/2015   #27796 MJCW:  Adapt MJCW and Warrants to IE10 and 11 (Juv Profile - Education UI) --%>

<!DOCTYPE HTML>
<html>
<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
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
<title><bean:message key="title.heading"/> - warrantConfirm.jsp</title>
<!-- JAVASCRIPT LINK FILES-->
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript">
function closePopUP()
{
	if(typeof(newWin)== "undefined"){
		return true;
	}
	if (newWin && newWin.open && !newWin.closed){
		newWin.close();
	}
	return true;
}
</script>
</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/submitJuvWarrantCreate" target="content">
  	<logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="arr">
	    <input type="hidden" name="helpFile" value="juvwarnt/juvenile_warrants_final.htm#|51">
   	</logic:equal>
   	<logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="dta">
	   <input type="hidden" name="helpFile" value="juvwarnt/juvenile_warrants_final.htm#|54">
   	</logic:equal>
   	<logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="pc">
	   <input type="hidden" name="helpFile" value="juvwarnt/juvenile_warrants_final.htm#|64">
   	</logic:equal>
    <logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="vop">
		<input type="hidden" name="helpFile" value="juvwarnt/juvenile_warrants_final.htm#|68">
    </logic:equal>
    <logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="oic">
	    <input type="hidden" name="helpFile" value="juvwarnt/juvenile_warrants_final.htm#|60">
    </logic:equal> 
<!-- BEGIN HEADING TABLE -->
  <table width="100%">
     <tr>
       <td align="center" class="header"> Initiate 
          <logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="arr">
            <bean:message key="title.juvWarrantArrest"/>
          </logic:equal>
          <logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="dta">
            <bean:message key="title.juvWarrantDTA"/>
          </logic:equal>
          <logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="pc">
            <bean:message key="title.juvWarrantPC"/>
          </logic:equal>
          <logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="vop">
            <bean:message key="title.juvWarrantVOP"/>
          </logic:equal>
          <logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="oic">
            <bean:message key="title.juvWarrantOIC"/>
          </logic:equal>
          Confirmation
       </td>	
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
<!-- BEGIN WARRANT CONFIRM TABLE -->
<table width="98%" border="0" cellspacing="1" cellpadding="4" align="center">
	<logic:notPresent name="ERROR_MESSAGE">
	    <tr>
	      <td align="right" width="50%" class="warrantNumber"><bean:message key="prompt.warrantNumber"/></td>
	      <td width="50%" class="warrantNumber" align="left"><bean:write name="juvenileWarrantForm" property="warrantNum"/></td>
	    </tr>
	    <logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="dta">	    
		<tr>
	      <td align="center" colspan="2" class="confirm">Juvenile Warrant successfully created and Request for Acknowledgement notification sent.</td>
	    </tr>
		</logic:equal>
		 <logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="arr">	    
		<tr>
	      <td align="center" colspan="2" class="confirm">Juvenile Warrant successfully created and Request for Activation notification sent.</td>
	    </tr>
		</logic:equal>
		<logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="pc">	    
		<tr>
	      <td align="center" colspan="2" class="confirm">Juvenile Warrant successfully created and notification sent that Juvenile is wanted.</td>
	    </tr>
		</logic:equal>
		<logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="oic">	    
		<tr>
	      <td align="center" colspan="2" class="confirm">Juvenile Warrant successfully created and Request for Signature notification sent.</td>
	    </tr>
		</logic:equal>
		<logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="vop">	    
		<tr>
	      <td align="center" colspan="2" class="confirm">Juvenile Warrant successfully created and Request for Activation notification sent.</td>
	    </tr>
		</logic:equal>
	</logic:notPresent>
</table>
<!-- END WARRANT CONFIRM TABLE -->	  

<table width="98%" border="0" cellspacing="1" cellpadding="4" align="center">
<!-- BEGIN JUVENILE WARRANT INFORMATION BLOCK -->
	<tr>
	 	<td class="detailHead" colspan="4"><bean:message key="prompt.juvenileWarrantInfo" /></td>
	</tr>
    <logic:equal name="juvenileWarrantForm" property="JOT" value="true">	    
         <tr>
            <td class="formDeLabel"><bean:message key="prompt.transactionNumber"/></td>
            <td class="formDe"><bean:write name="juvenileWarrantForm" property="transactionNum"/></td>
            <td class="formDeLabel" nowrap="nowrap" width="1%"><bean:message key="prompt.juvenileNumber"/></td>
            <td class="formDe">              
               <bean:write name="juvenileWarrantForm" property="juvenileNum"/>
            </td>
         </tr>
         <tr>
            <td class="formDeLabel"><bean:message key="prompt.daLogNumber"/></td>
            <td class="formDe" colspan="3"><bean:write name="juvenileWarrantForm" property="daLogNum"/></td>
         </tr>
    </logic:equal>  
    <logic:equal name="juvenileWarrantForm" property="JJS" value="true">
         <tr>
            <td class="formDeLabel"><bean:message key="prompt.juvenileNumber"/></td>
            <td class="formDe"><bean:write name="juvenileWarrantForm" property="juvenileNum"/></td>
            <td class="formDeLabel" nowrap="nowrap" width="1%"><bean:message key="prompt.referralNumber"/></td>
            <td class="formDe"><bean:write name="juvenileWarrantForm" property="referralNum"/></td>
         </tr>
         <tr>
            <td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.probationOfficer"/> of Record</td>
            <td class="formDe" colspan="3"><bean:write name="juvenileWarrantForm" property="probationOfficerOfRecordName" /></td>
         </tr>     
        </logic:equal> 	
<!-- END JUVENILE WARRANT INFORMATION TABLE -->
<tr><td><br></td></tr>
<!-- BEGIN CHARGE INFORMATION BLOCK -->
	<tr>
		<td class="detailHead" colspan="4"><bean:message key="prompt.chargeInfo"/></td>
	</tr>
    <logic:empty name="juvenileWarrantForm" property="chargesSelected">
		<tr>
			<td class="formDe" colspan="4">No Charges Found</td>
		</tr>
		<tr><td><br></td></tr>
	</logic:empty>
	<logic:notEmpty name="juvenileWarrantForm" property="chargesSelected">
	<logic:iterate id="chargeIndex" name="juvenileWarrantForm" property="chargesSelected"> 
		<tr>    
			<td class="formDeLabel"><bean:message key="prompt.charge" /></td>
			<td class="formDe" colspan="3"><bean:write name="chargeIndex" property="offense"/></td>
		</tr>
		<tr>	         
			<td class="formDeLabel"><bean:message key="prompt.petitionNumber"/></td>
			<td class="formDe" colspan="3"><bean:write name="chargeIndex" property="petitionNum"/></td>
		</tr>
		<tr>	 
			<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.chargeSeqNumber"/></td>
			<td class="formDe" colspan="3"><bean:write name="chargeIndex" property="sequenceNum"/></td>       	         		       
		</tr>
		<tr>
			<td class="formDeLabel"><bean:message key="prompt.chargeCourt"/></td>
			<td class="formDe" colspan="3"><bean:write name="chargeIndex" property="court"/></td>
		</tr>
		<tr>	
			<td class="formDeLabel"><bean:message key="prompt.chargeNCICNumber"/></td>
			<td class="formDe" colspan="3"><bean:write name="chargeIndex" property="offenseCodeId"/></td>
		</tr>
		<tr><td><br></td></tr>
	</logic:iterate>
	</logic:notEmpty>
<!-- END CHARGE INFORMATION BLOCK -->

<!-- BEGIN SUMMARY OF FACTS BLOCK ONLY NEEDED FOR JOT WARRANTS-->
<logic:equal name="juvenileWarrantForm" property="JOT" value="true">	
<%-- table width="98%" border="0" cellspacing="0" cellpadding="2" align="center" class="borderTableBlue" --%>
	<tr>
	 	<td class="detailHead" colspan="4"><bean:message key="prompt.summaryOfFactsInfo" /></td>
	</tr>
    <logic:equal name="juvenileWarrantForm" property="summOfFactsDisplaySize" value="Partial">
	    <tr>
            <td class="formDe" colspan="4"> 
           	    <bean:write name="juvenileWarrantForm" property="first4SummaryOfFact" />
            </td>
		</tr>
		<tr>            
            <td class="formDe" colspan="4"><a href="/<msp:webapp/>displaySummaryOfFacts.do">More...</a></td>
		</tr>            
    </logic:equal>
    <logic:equal name="juvenileWarrantForm" property="summOfFactsDisplaySize" value="None">
        	<tr>
            	<td class="formDe" colspan="4"></td>
            <tr>
    </logic:equal> 
    <logic:equal name="juvenileWarrantForm" property="summOfFactsDisplaySize" value="Full"> 
	   	    <tr>                                                 
    	        <td class="formDe" colspan="4">
        	      	<bean:write name="juvenileWarrantForm" property="completeSummaryOfFact" />
            	</td>
		    </tr>            	
    </logic:equal> 
<tr><td><br></td></tr>
</logic:equal> 	
<!-- END SUMMARY OF FACTS BLOCK -->

<!-- BEGIN JUVENILE INFORMATION BLOCK -->
	<tr>
	 	<td class="detailHead" colspan="4"><bean:message key="prompt.juvenileInfo" /></td>
	</tr>
    <tr>
       <td class="formDeLabel"><bean:message key="prompt.juvenileName"/></td>
       <td class="formDe" colspan="3">
          <%-- <bean:write name="juvenileWarrantForm" property="juvenileName"/> --%>
          <bean:write name="juvenileWarrantForm" property="juvenileName.lastName"/>, 
          <bean:write name="juvenileWarrantForm" property="juvenileName.firstName"/>
          <bean:write name="juvenileWarrantForm" property="juvenileName.middleName"/> 
          <bean:write name="juvenileWarrantForm" property="nameSuffix"/>
       </td>
     </tr>
     <tr>
        <td class="formDeLabel"><bean:message key="prompt.aka"/></td>
        <td class="formDe" colspan="3">                            
           <bean:write name="juvenileWarrantForm" property="aliasName"/>
        </td>
     </tr>
     <tr>
        <td class="formDeLabel"><bean:message key="prompt.dateOfBirth"/></td>
		<logic:notEqual name="juvenileWarrantForm" property="warrantTypeUI" value="dta">       
			<td class="formDe" colspan="3">
			    <bean:write name="juvenileWarrantForm" property="dateOfBirth" formatKey="date.format.mmddyyyy" />
			</td>
		</logic:notEqual>
		<logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="dta">       
			<td class="formDe">
			    <bean:write name="juvenileWarrantForm" property="dateOfBirth" formatKey="date.format.mmddyyyy" />
			</td>				
	   		 <td class="formDeLabel"><bean:message key="prompt.age"/>&nbsp;<bean:message key="prompt.verifiedBy"/></td>
    	   	 <td class="formDe">                           
	    	   	 <bean:write name="juvenileWarrantForm" property="dateOfBirthSource"/>
			 </td>            
	    </logic:equal>   				   
	 </tr>        
     <tr> 
        <td class="formDeLabel"><bean:message key="prompt.build"/></td>
        <td class="formDe" colspan="3">                           
           <bean:write name="juvenileWarrantForm" property="build"/>
        </td>
     </tr>
	<tr>
		<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.ssn"/></td>
		<td class="formDe" colspan="3">
			<bean:write name="juvenileWarrantForm" property="formattedSSN"/></td>
	</tr> 
<!-- 	<logic:equal name="juvenileWarrantForm" property="JOT" value="true">
		<tr>
			<td class="formDeLabel"><bean:message key="prompt.phone"/></td>
			<td class=formDe colspan="3"><bean:write name="juvenileWarrantForm" property="phoneNum.formattedPhoneNumber"/></td>
		</tr>      
	</logic:equal>     -->
	<tr>
		<td class="formDeLabel"><bean:message key="prompt.race"/></td>
		<td class="formDe">           
			<bean:write name="juvenileWarrantForm" property="race"/>
		</td>
		<td class="formDeLabel"><bean:message key="prompt.sex"/></td>
		<td class="formDe">             
			<bean:write name="juvenileWarrantForm" property="sex"/>
		</td>
	</tr>
	<tr>
		<td class="formDeLabel"><bean:message key="prompt.height"/></td>
		<td class="formDe">
	       <logic:notEqual name="juvenileWarrantForm" property="height" value="">
 	     		<logic:notEqual name="juvenileWarrantForm" property="height" value="0">
	           		<bean:write name="juvenileWarrantForm" property="heightFeet"/>ft&nbsp;
	           		<bean:write name="juvenileWarrantForm" property="heightInch"/>in&nbsp;
 	           </logic:notEqual> 
	       </logic:notEqual>
		</td>
		<td class="formDeLabel"><bean:message key="prompt.weight"/></td>
		<td class="formDe">
			<logic:notEqual name="juvenileWarrantForm" property="weight" value="">
				<logic:notEqual name="juvenileWarrantForm" property="weight" value="0">
					<bean:write name="juvenileWarrantForm" property="weight"/>&nbsp;lbs
				</logic:notEqual>	
			</logic:notEqual>			
		</td>
	</tr>
	<tr>
		<td class="formDeLabel"><bean:message key="prompt.eyeColor"/></td>
		<td class="formDe" colspan="3">            
			<bean:write name="juvenileWarrantForm" property="eyeColor"/>
		</td>
	</tr>
	<tr>
		<td class="formDeLabel"><bean:message key="prompt.hairColor"/></td>
		<td class="formDe" colspan="3">             
			<bean:write name="juvenileWarrantForm" property="hairColor"/>
		</td>
	</tr>
	<tr>
		<td class="formDeLabel"><bean:message key="prompt.complexion"/></td>
		<td class="formDe" colspan="3">              
			<bean:write name="juvenileWarrantForm" property="complexion"/>
		</td>
	</tr>   
	<tr>
		<td class="formDeLabel" valign="top"><bean:message key="prompt.scarsMarks"/></td>
		<td class="formDe" colspan="3">
			<logic:iterate id="scars" name="juvenileWarrantForm" property="scarsAndMarksSelected">
                <bean:write name="scars" property="description" /><br>
           	</logic:iterate>
		</td>
	</tr>
	<tr>
		<td class="formDeLabel" valign="top"><bean:message key="prompt.tattoos"/></td>
		<td class="formDe" colspan="3">
			<logic:iterate id="tattoo" name="juvenileWarrantForm" property="tattoosSelected">
            	<bean:write name="tattoo" property="description" /><br>
            </logic:iterate>
		</td>
	</tr>
	<logic:equal name="juvenileWarrantForm" property="JOT" value="true">
		<tr>
			<td class="formDeLabel"><bean:message key="prompt.fbiNumber"/></td>
			<td class="formDe" colspan="3">            
				<bean:write name="juvenileWarrantForm" property="fbiNum"/>
			</td>
		</tr>
		<tr>
			<td class="formDeLabel"><bean:message key="prompt.stateIdNumber"/></td>
			<td class="formDe" colspan="3">             
				<bean:write name="juvenileWarrantForm" property="sid"/>
		</td>
	</tr>
	</logic:equal> 	             
	<tr>
		<td class="formDeLabel"><bean:message key="prompt.schoolDistrict"/></td>
		<td class="formDe" colspan="3">
			<bean:write name="juvenileWarrantForm" property="schoolDistrictName"/>
		</td> 
	</tr>
	<tr>        
		<td class="formDeLabel"><bean:message key="prompt.schoolName"/></td>
		<td class="formDe" colspan="3">
			<bean:write name="juvenileWarrantForm" property="schoolName"/>
		</td> 
	</tr>  
	<tr>
		<td class="formDeLabel" valign="top"><bean:message key="prompt.cautions"/></td>
		<td class="formDe" colspan="3">
			<logic:iterate id="caution" name="juvenileWarrantForm" property="cautionsSelected">
              		<bean:write name="caution" property="description" /><br>
           	 </logic:iterate>
		</td>
	</tr>
	<tr>
		<td class="formDeLabel" valign="top"><bean:message key="prompt.otherCautionComments"/></td>
		<td class="formDe" colspan="3">                
			<bean:write name="juvenileWarrantForm" property="cautionComments"/>
		</td>
	</tr>
<!-- END JUVENILE INFORMATION BLOCK -->
<tr><td><br></td></tr>
<!-- BEGIN EXISTING RESPONSIBLE ADULT INFORMATION BLOCK -->
	<tr>
	 	<td class="detailHead" colspan="4"><bean:message key="prompt.juvenileAssociateInformation" /></td>
	</tr>
<!-- VARIABLES NEEDED FOR ASSOCIATE DISPLAY -->
 <% int RecordCounter = 0; 
    String bgcolor = "";%>
    <tr>
        <td class="formDeLabel" colspan="2"><bean:message key="prompt.name"/></td>
	    <td class="formDeLabel" colspan="2"><bean:message key="prompt.relationshipToJuvenile"/></td>
    </tr>
    <logic:empty name="juvenileWarrantForm" property="associates"> 
        <tr>
           <td class="formDe" colspan="4" align="center">No Responsible Adult Found</td>
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
	                  		<bean:write name="associate" property="associateName.lastName" />, <bean:write name="associate" property="associateName.firstName" />&nbsp;<bean:write name="associate" property="associateName.middleName" /></a>
	                  	</td>
	              </logic:equal>
	              <logic:notEqual name="associate" property="associateNum" value="">
	               		<td <% out.print(bgcolor); %> colspan="2" align="left">
	          				<a href="javascript:openWindow('/<msp:webapp/>displayJuvWarrantSummary.do?submitAction=Link&relationshipId=<bean:write name="associate" property="relationshipToJuvenileId"/>&warrantType=<bean:write name="juvenileWarrantForm" property="warrantTypeUI"/>');">	          		
	                  		<bean:write name="associate" property="associateName.lastName" />, <bean:write name="associate" property="associateName.firstName" /> <bean:write name="associate" property="associateName.middleName" /></a>
	                  	</td>
	              </logic:notEqual>      
	        	       <td <% out.print(bgcolor); %> colspan="2" align="left"><bean:write  name="associate" property="relationshipToJuvenile" /></td>			 
            </tr>
        </logic:iterate> 
    </logic:notEmpty> 
<!-- END EXISTING RESPONSIBLE ADULT INFORMATION BLOCK -->
 <tr><td><br></td></tr>
<!-- BEGIN LAW ENFORCEMENT INFO BLOCK -->
<logic:equal name="juvenileWarrantForm" property="JOT" value="true">  
	 <tr>
	 	<td class="detailHead" colspan="4"><bean:message key="prompt.lawEnforcementInfo" /></td>
	 </tr>
     <tr>
        <td class="formDeLabel"><bean:message key="prompt.badgeNumber"/></td>
        <td class="formDe"><bean:write name="juvenileWarrantForm" property="officerBadgeNumber"/></td>
        <td class="formDeLabel"><bean:message key="prompt.otherIdNumber"/></td>
        <td class="formDe"><bean:write name="juvenileWarrantForm" property="officerOtherIdNumber"/></td> 
     </tr>
     <tr>   
        <td class="formDeLabel"><bean:message key="prompt.department"/></td>
        <td class="formDe" colspan="3"><bean:write name="juvenileWarrantForm" property="officerAgencyName"/></td>
     </tr>
     <tr>
        <td class="formDeLabel"><bean:message key="prompt.officerName"/></td>
        <td class="formDe" colspan="3"><bean:write name="juvenileWarrantForm" property="officerName.formattedName"/></td>
     </tr>
     <tr>
        <td class="formDeLabel"><bean:message key="prompt.workPhone"/></td>
        <td class="formDe"><bean:write name="juvenileWarrantForm" property="officerPhoneNum"/></td>
        <td class="formDeLabel"><bean:message key="prompt.cellPhone"/></td>
        <td class="formDe"><bean:write name="juvenileWarrantForm" property="officerCellNum"/></td>
     </tr>
     <tr>
        <td class="formDeLabel"><bean:message key="prompt.pager"/></td>
        <td class="formDe"><bean:write name="juvenileWarrantForm" property="officerPager"/></td>
        <td class="formDeLabel"><bean:message key="prompt.email"/></td>
        <td class="formDe">
              <bean:write name="juvenileWarrantForm" property="email"/>
        </td>
     </tr>
     <tr>
        <td class="formDeLabel" valign="top"><bean:message key="prompt.affidavitStatement"/></td>
        <td class="formDe" colspan="3"><bean:write name="juvenileWarrantForm" property="affidavitStatement"/></td>
     </tr>
<tr><td><br></td></tr>
</logic:equal>
<!-- END LAW ENFORCEMENT INFORMATION BLOCK -->
<!-- BEGIN WARRANT ORIGINATOR INFORMATION BLOCK -->
	<tr>
	 	<td class="detailHead" colspan="4"><bean:message key="prompt.warrantOriginatorInfo" /></td>
	</tr>
	<logic:equal name="juvenileWarrantForm" property="JOT" value="true">
		<tr>
			<td class="formDeLabel"><bean:message key="prompt.warrantOriginatorName"/></td>
			<td class="formDe" colspan="3"><bean:write name="juvenileWarrantForm" property="warrantOriginatorName"/></td> 
		</tr>
		<tr> 
			<td class="formDeLabel"><bean:message key="prompt.warrantOriginatorDepartmentName"/></td>
			<td class="formDe" colspan="3"><bean:write name="juvenileWarrantForm" property="warrantOriginatorAgencyName"/></td> 
		</tr>
	</logic:equal> 	
	<logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="oic">
		<tr>
			<td class="formDeLabel"><bean:message key="prompt.court"/></td>
			<td class="formDe" colspan="3"><bean:write name="juvenileWarrantForm" property="warrantOriginatorCourt"/></td>
		</tr>
		<tr>	
			<td class="formDeLabel"><bean:message key="prompt.judgeName"/></td>		   
			<td class="formDe" colspan="3">
					<bean:write name="juvenileWarrantForm" property="warrantOriginatorJudge"/>
			</td>             
		</tr> 
	</logic:equal>
    <!-- VOP warrant is originated by user, while OIC warrant is originated from a court -->
    <!--TODO: need to combine with JOT warrant type as both display same values -->
	<logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="vop">
		<tr>
			<td class="formDeLabel"><bean:message key="prompt.warrantOriginatorName"/></td>
			<td class="formDe" colspan="3"><bean:write name="juvenileWarrantForm" property="warrantOriginatorName"/></td> 
		</tr>
		<tr>
			<td class="formDeLabel"><bean:message key="prompt.warrantOriginatorDepartmentName"/></td>
			<td class="formDe" colspan="3"><bean:write name="juvenileWarrantForm" property="warrantOriginatorAgencyName"/></td> 
		</tr>
	</logic:equal> 	
<!-- END WARRANT ORIGINATOR INFORMATION BLOCK -->
</table>
<br>
<!-- BEGIN BUTTON TABLE -->
<table class="buttonsG">
   <tr>
		<logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="dta"> 
		    <logic:equal name="juvenileWarrantForm" property="warrantAcknowledgeStatus" value="PRINTED"> 
      			<td>
			  		<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)">
       				 <bean:message key="button.mainPage"></bean:message>
       				 </html:submit>
		        </td>    
	      	</logic:equal>
		    <logic:notEqual name="juvenileWarrantForm" property="warrantAcknowledgeStatus" value="PRINTED"> 	      	
      			<td align="right">
			  		<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)">
       				 <bean:message key="button.mainPage"></bean:message>
       				 </html:submit>
		        </td>    
	    	</logic:notEqual>  	
      	</logic:equal>
 		<logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="oic">    
      		<td align="right">
		  		<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)">
       				 <bean:message key="button.mainPage"></bean:message>
       				 </html:submit>
	        </td>    
      	</logic:equal>
		<logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="arr">    
      		<td align="right">
		  		<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)">
       				 <bean:message key="button.mainPage"></bean:message>
       				 </html:submit>
	        </td>    
      		
      	</logic:equal>      	     
		<logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="pc">    
      		<td align="right">
		  		<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)">
       				 <bean:message key="button.mainPage"></bean:message>
       				 </html:submit>
	        </td>    
      	</logic:equal>      	     
		<logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="vop">    
      		<td align="right">
		  		<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)">
       				 <bean:message key="button.mainPage"></bean:message>
       				 </html:submit>
	        </td>    
      	</logic:equal>      	     
</html:form>
 	<!--Start of the code for Printing/Reporting Functionality -->	
    <logic:equal name="juvenileWarrantForm" property="warrantAcknowledgeStatus" value="PRINTED"> 	
		<logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="dta">  
		<!--value="DIRECTIVE TO APPREHEND" --> 
				<html:form action="/printWarrantTemplateReport" target="new">
				<td align="left">
					<html:submit property="submitAction">
						<bean:message key="button.printDTAWarrant"></bean:message>   
					</html:submit>
				</td>
				</html:form>
		</logic:equal>
	</logic:equal>	
    <logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="oic"> 
    	<!-- value="ORDER OF IMMEDIATE CUSTODY" --> 
		<html:form action="/printWarrantTemplateReport" target="new">
			<td align="left">
				<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)">
					<bean:message key="button.printOICWarrant"></bean:message>   
				</html:submit>
			</td>
		</html:form>
	</logic:equal>
	<logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="arr"> 
	    <!--value="ARREST WARRANT" --> 
		<html:form action="/printWarrantTemplateReport" target="new">
			<td align="left">
				<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)">
					<bean:message key="button.printArrestWarrant"></bean:message>   
				</html:submit>
			</td>				
		</html:form>
	</logic:equal> 
 	<!--End of the code for Printing/Reporting Functionality -->	
	</tr>
	<tr>
    <logic:notEmpty name="juvenileWarrantForm" property="associates"> 
	 	<td align="right">
<%--	<jims2:isAllowed requiredFeatures="JW-MNG-ASSOC">
			<html:form action="/displayWarrantAssociateCreateUpdate.do?action=create&details=false?associateNum=">
 	   			<html:submit property="submitAction" onclick="return closePopUP() && disableSubmit(this, this.form)">
					<bean:message key="button.createAssociate"></bean:message>
				</html:submit>
		       <script type="text/javascript">
			       	document.write("<input type='hidden' name='flowInd' value=" + window.name + ">");
		       </script>				
			</html:form>
</jims2:isAllowed>	 --%>
	 	</td> 
 	<td valign="top" align="left">
<%-- 	<jims2:isAllowed requiredFeatures="JW-MNG-ASSOC">
		 	<html:form action="/displayWarrantAssociateCreateUpdate.do?action=updateList&details=false">
	    	   <html:submit property="submitAction" onclick="return closePopUP() && disableSubmit(this, this.form)">
	        	<bean:message key="button.updateAssociate"></bean:message>
		       </html:submit>
		       <script type="text/javascript">
			       	document.write("<input type='hidden' name='flowInd' value=" + window.name + ">");
		       </script>		       
			</html:form>
	</jims2:isAllowed> --%>
	    </td>
	 </logic:notEmpty> 
<!-- if no existing responsible adults, do not display update button -->	   
    <logic:empty name="juvenileWarrantForm" property="associates"> 
	 	<td align="right">
<%--	<jims2:isAllowed requiredFeatures="JW-MNG-ASSOC"> --%>
			<html:form action="/displayWarrantAssociateCreateUpdate.do?action=create&details=false">
 	   			<html:submit property="submitAction" onclick="return closePopUP() && disableSubmit(this, this.form)">
					<bean:message key="button.createAssociate"></bean:message>
				</html:submit>
			    <script type="text/javascript">
			       	document.write("<input type='hidden' name='flowInd' value=" + window.name + ">");
			    </script>				
			</html:form>
<%--	</jims2:isAllowed>	 --%>
	 	</td>
	 </logic:empty>  	 
   </tr>
</table>
<!-- END BUTTON TABLE -->	
		</jims2:isAllowed>
<!-- END FORM -->
<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>