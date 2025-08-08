<!DOCTYPE HTML>
<!--MODIFICATIONS -->
<!-- Used for Order of Immediated Custody(OIC) and Violation of Probation Warrant(VOP) -->
<%-- 05/18/2004	 CShimek  	  Create JSP --%>
<%-- 10/08/2004	 HRodriguez	  Modify JSP --%>
<%-- 12/16/2004  LDeen		  Revise JSP to make work and match form and response events --%>
<%-- 03/17/2005  CShimek      Added missing required field indicator to court in Warrant Originator Information block, 
                              per defect JIMS200019449 --%>
<%-- 08/08/2005  CShimek      Revised required field diamond --%>
<%-- 08/11/2005  CShimek      Revised to new look and feel --%>
<%-- 08/23/2005  JFisher      Update form fields to reflect use of java.util.Date instead of String --%>     
<%-- 08/25/2005  DGibler	  Changed name in validateRadios() invocation from selectedCharges to chargeSeqNum to get javascript to work --%>    
<%-- 10/04/2005  CShimek      ER#24012 change assocaite labels to responsible adult --%>
<%-- 01/09/2006  CShimek      Make School District and Name editable ER resulting from defect 21604 --%>
<%-- 02/01/2006  CShimek      Added hidden field helpFile for each warrant type --%>
<%-- 02/07/2006  LDeen	      Changed to prompt.1.diamond --%>
<%-- 03/20/2006  CShimek	  Defect#29837 increase width on scars and marks drop to view full description --%>
<%-- 04/13/2006  CShimek      Defect#29770 remove value="" from heightFeet and heightInch fields --%>
<%-- 07/06/2006  CShimek      Defect#32593 added edit to blank Height feet and inches if both values = 0 --%>
<%-- 12/21/2006  CShimek      Revised helpfile reference value--%>
<%-- 01/25/2007  CShimek      #38263 add script and logic tags to save and set selected charge when Go button is selected --%>
<%-- 01/26/2007  CShimek      #38262 revised RA hyperlink to use ChangeFormURL instead of direct link to action --%>
<%-- 01/30/2007	 CShimek	  #39097 added multiple submit button logic --%>
<%-- 03/23/2007  CShimek      #40475 revised js calls for height and weight to use new script  --%>
<%-- 04/11/2007  CShimek	  #41031 revised RA href to open in new window --%>
<%-- 04/26/2007  CShimek	  #41444 added onload script to display caution comments field if other caution selected --%>
<%-- 06/04/2007	 LDeen		  #42564 changed path to app.js --%>
<%-- 12/18/2009	 LDeen		  Fixed javascript error --%>
<%-- 01/06/2010	 RCapestani	  Fixed javascript error --%>
<%-- 01/08/2010	 Ldeen		  Fixed javascript error-Defect#63227 --%>
<%-- 08/6/2015   RYoung       #27796 MJCW:  Adapt MJCW and Warrants to IE10 and 11 (Juv Profile - Education UI) --%>

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!-- SCRIPTING VARIABLES -->
<jsp:useBean id="codeHelper" scope="application" class="ui.juvenilewarrant.helper.JuvenileWarrantListHelper" />

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

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
<title><bean:message key="title.heading"/> - warrantJJSCreate.jsp</title>

<!--THESE TWO LINES FOR STRUTS & JAVASCRIPTS VALIDATIONS-->
<html:javascript formName="juvenileWarrantForm" />

<!-- JAVASCRIPT FILES-->
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/warrant_utils.js"></script>

<tiles:insert page="../js/warrantJJSCreate.js"/>

<!-- Javascript for Court Drop Down List and to populate the warrantOriginatorJudge field with corresponding Judge Name -->
<script type="text/javascript">
var courtArray = new Array();
var i=1;
<logic:iterate name="codeHelper" property="courts" id="courtIndex"> 
courtArray[i++] = "<bean:write name="courtIndex" property="judgeName"/>";
</logic:iterate>

//this function displays the judge name based on the court selected in the drop down
//it is also called onLoad - to render the correct judge name
function fillText(el){
	var theForm;
	var num;
	var val;
	//if function was called from the select - el is passed in
	if(el != null)
	{
		theForm = el.form;
	  num = el.selectedIndex;
   	val = el.value;
   }else{ //function is called onload - hardcode the form and the form element - 'court id'
   	theForm = document.forms[0];
   	num = theForm.courtId.selectedIndex;
   	val = theForm.courtId.value;
   }      
   //val is "" if "Please Select" is selected 
	if (val != "")
	{
		document.getElementById("judgeNameLabel").innerHTML = "Judge Name";
		if (courtArray[num] != "Not Available"){
			document.getElementById("<bean:message key="prompt.judgeName"/>").innerHTML = courtArray[num];
		} else {
			document.getElementById("<bean:message key="prompt.judgeName"/>").innerHTML = "";
		}	
		theForm.warrantOriginatorJudge.value=courtArray[num];
	}else {
		document.getElementById("judgeNameLabel").innerHTML = "";
		document.getElementById("<bean:message key="prompt.judgeName"/>").innerHTML = "";
		theForm.warrantOriginatorJudge.value=null;
	}
}

function resetSchoolName(){
	var schoolDropDown=document.getElementById("schoolCodeId");
	schoolDropDown.selectedIndex = 0;
}

function showOtherCaution(val)
{
 if(val){
    show('otherCaution', 1,"row");
    show('otherCaution2', 1,"row");
 }else{
 	show('otherCaution', 0,"row");
 	show('otherCaution2', 0,"row");
 }
}

function checkOtherCautionSelect(val)
{
// display caution comments if "OTHER" is selected	
	var cautions = document.getElementsByName("selectedCautions");
	for (x=0; x<cautions.length; x++){
		if (cautions[x].value == "OT" &&  cautions[x].checked == true){
		    show('otherCaution', 1,"row");
	    	show('otherCaution2', 1,"row");
		}
	}
}	

function checkChargeSeqNum(theForm)
{
	var chrgSeqNums = document.getElementsByName("chargeSeqNum");
	for (x=0; x<chrgSeqNums.length; x++){
		if(chrgSeqNums[x].checked == true){
			theForm.selectedCharge.value = chrgSeqNums[x].value;
		}
	}
}

        
/* function openWindow(url)
*{
*	alert('openWindow \n' + url);
	var newWin = window.open(url, "pictureWindow", "height=400,width=600,toolbar=no,scrollbars=yes,resizable=yes,status=yes,dependent=no");
	newWin.focus();
}
function openWindowX(formName, URL, doSubmit)
{
	alert(URL);
//	var myForm=document.getElementsByName(formName)[0];
//	myForm.action = URL;
	var newWin = window.open(URL, "pictureWindow", "height=400,width=600,toolbar=no,scrollbars=yes,resizable=yes,status=yes,dependent=no");
	newWin.focus();
}
function changeFormActionURLX(formName, URL, doSubmit){
	var myForm=document.getElementsByName(formName)[0];
		
	myForm.action = URL;
	if(doSubmit){
		myForm.submit();
	}
//	return true;
} */
</script>
</head>
<!--END HEADER TAG-->

<!--BEGIN BODY TAG-->
<% 
	// Use a Java scriplet so that the BODY tags match
	// Assume that the warrantTypeUI is not VOP
	String onload = "fillText();setPageFocus();checkOtherCautionSelect()"; 
%>
<logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="vop">
	<% onload = "setPageFocus();checkOtherCautionSelect()"; %>
</logic:equal>

<!--BEGIN BODY TAG-->


<html:form action="/displayJuvWarrantSummary" target="content">
  	<logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="oic">
	    <input type="hidden" name="helpFile" value="juvwarnt/juvenile_warrants_final.htm#|59">
   	</logic:equal>
	<logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="vop">
		<input type="hidden" name="helpFile" value="juvwarnt/juvenile_warrants_final.htm#|67">
		<input type="hidden" name="phoneNum.areaCode" value="" />
		<input type="hidden" name="phoneNum.prefix" value="" />
		<input type="hidden" name="phoneNum.4Digit" value="" />
	</logic:equal> 
	<input type="hidden" name="selectedCharge" value="">

<!-- BEGIN HEADING TABLE -->
<table width="98%" align="center">	
	<tr>
		<td align="center" class="header">		
      		<logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="oic">	      		
        		Initiate <bean:message key="title.juvWarrantOIC"/>      		      				
         	</logic:equal>
        	<logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="vop">      		
         		Initiate <bean:message key="title.juvWarrantVOP"/>        	
          	</logic:equal>
         </td>
     </tr>
</table>
<!-- END HEADING TABLE -->    
					
<!-- BEGIN INSTRUCTION/REQUIRED INFORMATION TABLE --> 
<table width="98%" align="center">	
	<tr>
        <td>
           <ul>	       
	   		 <li>Information is retrieved from the Juvenile Justice System (JJS).</li>
			 <li>Clicking on Existing Responsible Adult name displays full address and contact information.</li>		
			 <li>Make changes as needed and select Next button to view summary.</li>
           </ul>
         </td>
     </tr>
</table>
<table width="98%" align="center">
    <tr>
		<td class="required"><bean:message key="prompt.1.diamond"/><bean:message key="prompt.requiredFieldsInstruction"/></td>      
	</tr>
</table>
<!-- END INSTRUCTION/REQUIRED INFORMATION TABLE -->

<!-- BEGIN ERROR TABLE -->
<table width="98%">
	<tr>
		<td align="center" class="errorAlert"><html:errors /></td>
	</tr>
</table>
<!-- END ERROR TABLE -->

<!-- BEGIN MAIN BODY TABLE -->
<table width="98%" border="0" cellspacing="1" cellpadding="4" align="center">
<!-- BEGIN WARRANT INFORMATION BLOCK -->
	<tr>
		<td class="detailHead" colspan="4"><bean:message key="prompt.juvenileWarrantInfo" /></td>
	</tr>
	<tr>
		<td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.juvenileNumber"/></td>
		<td class="formDe"><bean:write name="juvenileWarrantForm" property="juvenileNum" />
			<input type="hidden" id="juvenileNum" value="<bean:write name="juvenileWarrantForm" property="juvenileNum" />" />
			<input type="hidden" id="warrantNumber" value="<bean:write name="juvenileWarrantForm" property="warrantNum" />" />
			<input type="hidden" id="firstName" value="<bean:write name="juvenileWarrantForm" property="juvenileName.firstName" />" />
			<input type="hidden" id="lastName" value="<bean:write name="juvenileWarrantForm" property="juvenileName.lastName" />" />
			<input type="hidden" id="jpOfficerEmailAddress" value="<bean:write name="juvenileWarrantForm" property="jpOfficerEmail" />" />
		</td>
		<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.referralNumber"/></td>
		<td class="formDe"><bean:write name="juvenileWarrantForm" property="referralNum"/></td>
	</tr>
	<tr>
		<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.probationOfficer"/> of Record</td>
		<td class="formDe" colspan="3"><bean:write name="juvenileWarrantForm" property="probationOfficerOfRecordName"/></td>
	</tr>
<!-- END WARRANT INFORMATION TABLE -->
<tr><td><br></td></tr> 
<!-- BEGIN CHARGE INFORMATION TABLE -->
	<tr>
	   <td colspan="4">
		  <table width="100%" cellpadding="4" cellspacing="1"> 
			<tr>
	 			<td class="detailHead" colspan="3"><bean:message key="prompt.chargeInfo"/></td>
			</tr>
			<logic:empty name="juvenileWarrantForm" property="charges">
				<tr>
					<td class="formDe" colspan="3">No Charges Found</td>
				</tr>
				<tr><td><br></td></tr>
			</logic:empty>
			<logic:notEmpty name="juvenileWarrantForm" property="charges">	
				<bean:define id="selCharge" name="juvenileWarrantForm" property="selectedCharge" type="java.lang.String"/>					
	            <logic:iterate id="chargeIndex" name="juvenileWarrantForm" property="charges"> 
   					<tr>
						<td class="formDeLabel" align="center" width="1%" nowrap="nowrap">Select</td>
						<td class="formDeLabel"><bean:message key="prompt.charge"/></td>
						<td class="formDe"><bean:write name="chargeIndex" property="offense"/></td>					
		 			</tr>
		 			<tr>
	    				<td class="formDeLabel" align="center">
	    					<logic:equal name="juvenileWarrantForm" property="onlyCharge" value="true">
		    					<input type="radio" name="chargeSeqNum" value="<bean:write name="chargeIndex" property="sequenceNum"/>" checked/>
	    					</logic:equal>
	    					<logic:notEqual name="juvenileWarrantForm" property="onlyCharge" value="true">
 	    						<logic:equal name="chargeIndex" property="sequenceNum" value="<%= selCharge %>" >
									<input type="radio" name="chargeSeqNum" value="<bean:write name="chargeIndex" property="sequenceNum"/>" checked/>
								</logic:equal>
								<logic:notEqual name="chargeIndex" property="sequenceNum" value="<%= selCharge %>" >
									<input type="radio" name="chargeSeqNum" value="<bean:write name="chargeIndex" property="sequenceNum"/>" />
								</logic:notEqual>  	
							</logic:notEqual>
	    				</td>
		         		<td class="formDeLabel"><bean:message key="prompt.petitionNumber"/></td>
	     				<td class="formDe"><bean:write name="chargeIndex" property="petitionNum"/></td>
	     			</tr>
   					<tr>
	    				<td class="formDeLabel"></td>
	    				<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.chargeSeqNumber"/></td>
	    				<td class="formDe"><bean:write name="chargeIndex" property="sequenceNum"/></td>              
		  			</tr>
	   				<tr>	         
		             	<td class="formDeLabel"></td>
      					<td class="formDeLabel"><bean:message key="prompt.chargeCourt"/></td>
      					<td class="formDe"><bean:write name="chargeIndex" property="court"/></td>
		            </tr>
   					 <tr>
      					<td class="formDeLabel"></td>
      					<td class="formDeLabel"><bean:message key="prompt.chargeNCICNumber"/></td>
      					<td class="formDe"><bean:write name="chargeIndex" property="offenseCodeId"/></td>
	   				</tr>
   					<tr><td><br></td></tr>
	   			</logic:iterate> 
	   		</logic:notEmpty>	
		  </table>
		</td>
	</tr>
<!-- END CHARGE INFORMATION BLOCK --> 
<!-- these solve js issue with validation.xml - since these fields are not present for OIC -->
	<logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="oic">
	<tr>
		<td colspan="4">  	
			<input type="hidden" name="phoneNum.areaCode" value="" />
			<input type="hidden" name="phoneNum.prefix" value="" />
			<input type="hidden" name="phoneNum.4Digit" value="" />   
		</td>
	</tr> 
	</logic:equal> 		
<!-- BEGIN JUVENILE INFORMATION BLOCK --> 
	<tr>
		<td class="detailHead" colspan="4"><bean:message key="prompt.juvenileInfo" /></td>
	</tr>
	<tr>
		<td class="formDeLabel"><bean:message key="prompt.juvenileName"/></td>
		<td class="formDe" colspan="3">
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
		<td class="formDe">
			<bean:write name="juvenileWarrantForm" property="dateOfBirth" formatKey="date.format.mmddyyyy" />
		</td>
		<td class="formDeLabel"><bean:message key="prompt.build"/></td>
		<td class="formDe" colspan="3">
           <logic:notEqual name="juvenileWarrantForm" property="build" value="">                               
                    <bean:write name="juvenileWarrantForm" property="build"/>
           </logic:notEqual>
        </td>
	</tr>
	<tr>
		<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.ssn"/></td>
		<td class="formDe" colspan="3">
			<logic:notEqual name="juvenileWarrantForm" property="formattedSSN" value="">
			<bean:write name="juvenileWarrantForm" property="formattedSSN"/>
			</logic:notEqual>
		</td>
	</tr> 
	<tr>
		<td class="formDeLabel"><bean:message key="prompt.race"/></td>
		<td class="formDe">
			<logic:notEqual name="juvenileWarrantForm" property="race" value="">                
			<bean:write name="juvenileWarrantForm" property="race"/>
			</logic:notEqual>
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
			<logic:notEqual name="juvenileWarrantForm" property="eyeColor" value="">                
			<bean:write name="juvenileWarrantForm" property="eyeColor"/>
			</logic:notEqual>
		</td>
	</tr>
	<tr>   
		<td class="formDeLabel"><bean:message key="prompt.hairColor"/></td>
		<td class="formDe" colspan="3">
			<logic:notEqual name="juvenileWarrantForm" property="hairColor" value="">                
			<bean:write name="juvenileWarrantForm" property="hairColor"/>
			</logic:notEqual>
		</td>
	</tr> 
	<tr>
		<td class="formDeLabel"><bean:message key="prompt.complexion"/></td>
		<td class="formDe" colspan="3">
			<logic:notEqual name="juvenileWarrantForm" property="complexion" value="">                
			<bean:write name="juvenileWarrantForm" property="complexion"/>
			</logic:notEqual>
		</td>
	</tr> 
	<%-- JMF - 3/20/2006 - Category not being persisted - JIMS200029289 --%>
	<tr>
		<td class="formDeLabel" valign="top"><bean:message key="prompt.current"/> <bean:message key="prompt.scarsMarks"/></td>
		<td class="formDe" colspan="3">		
			<logic:notEqual name="juvenileWarrantForm" property="originalScarsString" value="">                
			<bean:write name="juvenileWarrantForm" property="originalScarsString"/>
			</logic:notEqual>
		</td>
	</tr>
	<tr>
		<td class="formDeLabel" valign="top"><bean:message key="prompt.additional"/> <bean:message key="prompt.scarsMarks"/></td>
		<td class="formDe" colspan="3">		
			<html:select property="selectedScars" size="10" multiple="true">	      	
				<html:optionsCollection property="scarsMarksCodes" value="code" label="description" />
			</html:select>   
		</td>
	</tr>
	<tr>
		<td class="formDeLabel" valign="top"><bean:message key="prompt.current"/> <bean:message key="prompt.tattoos"/></td>
		<td class="formDe" colspan="3">
 			<logic:notEqual name="juvenileWarrantForm" property="originalTattoosString" value="">                
			<bean:write name="juvenileWarrantForm" property="originalTattoosString"/>
			</logic:notEqual>
		</td>
	</tr>
	<tr>
		<td class="formDeLabel" valign="top"><bean:message key="prompt.additional"/> <bean:message key="prompt.tattoos"/></td>
		<td class="formDe" colspan="3">
 			<html:select property="selectedTattoos" size="10" multiple="true">          	  	
 				<html:optionsCollection property="tattoosCodes" value="code" label="description" />
			</html:select>
		</td>
	</tr>
	<tr>
    	<td class="formDeLabel"><bean:message key="prompt.schoolDistrict"/></td>
	    <td class="formDe" colspan="3">
 	      <html:select property="schoolDistrictId" onchange="resetSchoolName()">
    	    <html:option value=""><bean:message key="select.generic" /></html:option>
			<html:optionsCollection name="juvenileWarrantForm" property="schoolDistricts" value="districtCode" label="districtDescription" />
    	  </html:select> 
      		<html:submit property="submitAction" onclick="checkChargeSeqNum(this.form);">
	        	<bean:message key="button.go" ></bean:message>
    		</html:submit> 
	    </td> 
	</tr>
	<tr>
    	<td class="formDeLabel"><bean:message key="prompt.schoolName"/></td>
	    <td colspan="3" class="formDe">
		    <logic:notEmpty name="juvenileWarrantForm" property="schools">
    		  	<html:select property="schoolCodeId" styleId="schoolCodeId">
        			<html:option value=""><bean:message key="select.generic" /></html:option>
					<html:optionsCollection name="juvenileWarrantForm" property="schools" value="schoolCode" label="schoolDescription" />
    	  		</html:select> 
    	 	</logic:notEmpty> 
		    <logic:empty name="juvenileWarrantForm" property="schools">
    		  	<html:select property="schoolCodeId" styleId="schoolCodeId">
        			<html:option value=""><bean:message key="select.generic" /></html:option>
    	  		</html:select> 
    	 	</logic:empty> 
	    </td>
  	</tr>
    <tr>
        <td class="formDeLabel" colspan="4">
            <bean:message key="prompt.cautions"/>
        </td>
    </tr>
	<INPUT type="hidden" name="cautionCheckboxClear" value="true" />
	<logic:iterate id="caut" name="codeHelper" property="cautions">
	   <logic:notEqual name="caut" property="code" value="OT">
		<tr>
			<td class="formDe" colspan="4">
				<html:multibox property="selectedCautions">
					<bean:write name="caut" property="code" /> 
				</html:multibox>
				<bean:write name="caut" property="description"/> 
<%--              <% counter = counter + 1;  if(counter==2) { counter = 0;%>
                       <p/>
                       <%}%> --%>
			</td>
		</tr>
	  </logic:notEqual>	   
	</logic:iterate>
	<tr>
	   <td class="formDe" colspan="4"><html:multibox property="selectedCautions" onclick="return showOtherCaution(this.checked)">
						              OT 
				                  </html:multibox>OTHER</td>
	</tr> 
	<tr id="otherCaution" class="hidden">
		<td class="formDeLabel" colspan="4"><bean:message key="prompt.otherCautionComments"/></td>
	</tr>
	<tr id="otherCaution2" class="hidden">	
		<td class="formDe" colspan="4">
			<html:textarea property="cautionComments" style="width:100%" rows="5" onkeypress="return textCounter(this.form.cautionComments,100);"></html:textarea>
		</td>
	</tr> 
<!-- END JUVENILE INFORMATION TABLE --> 
<tr><td><br></td></tr>
<!-- BEGIN EXISTING ASSOCIATE INFORMATION BLOCK -->
	<tr>
	 	<td colspan="4" class="detailHead"><bean:message key="prompt.juvenileAssociateInformation" />
	 	<span>&nbsp&nbsp&nbsp<a href="#" onClick="OpenOutlook()" >Email JPO & Data Corrections</a></td>
	</tr>
     <tr>
        <td class="formDeLabel" colspan="2"><bean:message key="prompt.name"/></td>
        <td class="formDeLabel" colspan="2"><bean:message key="prompt.relationshipToJuvenile"/></td>
    </tr>
    <logic:empty name="juvenileWarrantForm" property="associates"> 
    	<tr>
        	<td class="formDe" colspan="2">No Responsible Adults Found</td>
            <td class="formDe" colspan="2">&nbsp;</td>
        </tr> 
     </logic:empty>    
<%-- not required as part of interation #7   ?associateNumber=10--%> 
<!-- VARIABLES NEEDED FOR DISPLAY -->
<% int RecordCounter = 0; 
   String bgcolor = ""; %>
     <logic:notEmpty name="juvenileWarrantForm" property="associates">   
        <logic:iterate id="associate" name="juvenileWarrantForm" property="associates"> 
        <logic:notEqual name="associate" property="associateNum" value="1111111"> <!-- do not display dummy family member -->
           <% RecordCounter++;
              bgcolor = "class=formDe";
              if (RecordCounter % 2 == 1)
                 bgcolor = ""; %>
           <tr>
<%--            	          <td <% out.print(bgcolor); %> colspan=2><a href="/<msp:webapp/>displayAssociateDetails.do?relationshipId=<bean:write name="associate" property="relationshipToJuvenileId"/>&warrantType=<bean:write name="juvenileWarrantForm" property="warrantTypeUI"/>"> --%>
	          <td <% out.print(bgcolor); %> colspan="2" align="left">
<%-- 	          		<a href="javascript:openWindowX('juvenileWarrantForm', '/<msp:webapp/>displayJuvWarrantSummary.do?submitAction=Link&relationshipId=<bean:write name="associate" property="relationshipToJuvenileId"/>&warrantType=<bean:write name="juvenileWarrantForm" property="warrantTypeUI"/>', false);"> --%>
	          		<a href="javascript:openWindow('/<msp:webapp/>displayJuvWarrantSummary.do?submitAction=Link&relationshipId=<bean:write name="associate" property="relationshipToJuvenileId"/>&warrantType=<bean:write name="juvenileWarrantForm" property="warrantTypeUI"/>&juvenileNumber=<bean:write name="juvenileWarrantForm" property="juvenileNum" />&associateNumber=<bean:write name="associate" property="associateNum" />');">	          		
	              <bean:write name="associate" property="associateName.lastName" />, <bean:write name="associate" property="associateName.firstName" /> <bean:write name="associate" property="associateName.middleName" /></a></td>
	          <td <% out.print(bgcolor); %> colspan="2" nowrap="nowrap" align="left"><bean:write  name="associate" property="relationshipToJuvenile" /></td>			 
           </tr>
           </logic:notEqual>
         </logic:iterate> 
      </logic:notEmpty> 
<!-- END EXISTING ASSOCIATE INFORMATION BLOCK -->
<tr><td><br></td></tr>
<!-- ONLY ASK USER TO CHOOSE WARRANT ORIGINATOR IF INITIALIZING OIC -->
<!-- BEGIN WARRANT ORIGINATOR INFORMATION BLOCK FOR OIC WARRANT -->
	<logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="oic">	      		
		<tr>
			<td class="detailHead" colspan="4"><bean:message key="prompt.warrantOriginatorInfo" /></td>
		</tr>
		<tr>
			<td class="formDeLabel"><bean:message key="prompt.1.diamond"/> <bean:message key="prompt.court"/></td>
			<td class="formDe" colspan="3">
 				<html:select property="courtId" onchange="fillText(this)">
					<html:option key="select.generic" value="" />	  	
					<html:optionsCollection name="codeHelper" property="courts" value="code" label="description" />
				</html:select> 
			</td>
		</tr>
		<tr>	
			<td class="formDeLabel"><span id="judgeNameLabel"></span></td>
			<td class="formDe" colspan="3"><span id="<bean:message key="prompt.judgeName"/>"></span><html:hidden property="warrantOriginatorJudge" /></td>
		</tr>
	</logic:equal>
<!-- END WARRANT ORIGINATOR INFORMATION BLOCK FOR OIC WARRANT --> 
<!-- BEGIN WARRANT ORIGINATOR INFORMATION BLOCK FOR VOP WARRANT -->
	<logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="vop">	      		
		<tr>
			<td class="detailHead" colspan="4"><bean:message key="prompt.warrantOriginatorInfo" /></td>
		</tr>
		<tr>
			<td class="formDeLabel"><bean:message key="prompt.warrantOriginatorName"/></td>
			<td class="formDe" colspan="3"><bean:write name="juvenileWarrantForm" property="warrantOriginatorName"/></td> 
		</tr>
		<tr>
			<td class="formDeLabel"><bean:message key="prompt.warrantOriginatorDepartmentName"/></td>
			<td class="formDe" colspan="3"><bean:write name="juvenileWarrantForm" property="warrantOriginatorAgencyName"/></td> 
		</tr>         
	</logic:equal>
<!-- END WARRANT ORIGINATOR INFORMATION BLOCK FOR VOP WARRANT --> 
</table>  
<br>
<!-- BEGIN BUTTON TABLE --> 
<table width="98%" border="0" align="center">
	<tr valign="top">
		<td align="right" width="45%">				
		  	<logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="vop">	
				<html:submit property="submitAction" onclick="return alertTextAreaToLarge(this.form.cautionComments,100,'Other Caution Comments') && validateJuvenileFields(this.form) && validateRadios(this.form, 'chargeSeqNum', 'At least 1 charge must be selected.') && validateFields(this.form,'vop');">
				  	<bean:message key="button.next"></bean:message>
			  	</html:submit>			  
			</logic:equal>  	
		  	<logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="oic">
				<html:submit property="submitAction" onclick="return alertTextAreaToLarge(this.form.cautionComments,100,'Other Caution Comments') && validateJuvenileFields(this.form) && validateRadios(this.form, 'chargeSeqNum', 'At least 1 charge must be selected.') && validateFields(this.form,'oic');">
				  	<bean:message key="button.next"></bean:message>
			  	</html:submit> 			  
			</logic:equal>  	

		  	<html:reset>
			  	<bean:message key="button.reset"></bean:message>
		  	</html:reset>
		</td>
		<td align="left">  	
</html:form>
<!-- END FORM -->		  	
		  	<logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="oic">	
		        <html:form action="/displayJJSSearch.do?warrantTypeUI=oic"> 
                   <html:submit>
                       <bean:message key="button.cancel"></bean:message>
                   </html:submit>
                </html:form> 
		    </logic:equal>
		    <logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="vop">	
		        <html:form action="/displayJJSSearch.do?warrantTypeUI=vop"> 
                   <html:submit>
                       <bean:message key="button.cancel"></bean:message>
                   </html:submit>
                </html:form> 
		    </logic:equal>
	  		<%--html:button property="org.apache.struts.taglib.html.CANCEL" 
		   		onclick="document.location.href='../security.war/jsp/mainMenu.jsp'">
				<bean:message key="button.cancel"></bean:message>
		  	</html:button--%>    		  			  
		</td>	  
	</tr>
</table>
<!-- END BUTTON TABLE -->
<!--  ERRORS  -->
<html:errors></html:errors>
<%-- This is to make the <body> tags match --%>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>

</html:html>