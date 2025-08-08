<!DOCTYPE HTML>
<html>

<!--MODIFICATIONS -->
<%-- JFisher    08/23/2005  Update form fields to reflect use of java.util.Date instead of String --%>
<%-- CShimek    08/24/2005  Revised display(look) of charge information block to match other warrant create/update pages --%>
<%-- CShimek    10/04/2005  ER#23756 change titles --%>
<%-- CShimek    02/01/2006  Added hidden field for HelpFile access --%>
<%-- CShimek	03/20/2006  Defect#29837 increase width on scars and marks drop to view full description --%>
<%-- LDeen MJT  12/07/2006  Revised javascript for Other Caution text field --%>
<%-- CShimek    12/13/2006  defect#37679 removed validateJuvenileWarrantForm(this.form)from next button and revised js to handle height and weight edits --%>
<%-- CShimek	12/21/2006  Revised helpfile reference value --%>
<%-- CShimek   	03/23/2007  #40475 revised js calls for height and weight to use new script  --%>
<%-- CShimek	04/11/2007 	#41031 revised RA href to open in new window --%>
<%-- LDeen		06/04/2007  #42564 changed path to app.js --%>
<%-- RYoung     08/06/2015  #27796 MJCW:  Adapt MJCW and Warrants to IE10 and 11 (Juv Profile - Education UI) --%>

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<!-- SCRIPTING VARIABLES -->
<jsp:useBean id="codeHelper" scope="application" class="ui.juvenilewarrant.helper.JuvenileWarrantListHelper" />

<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

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
<html:base />
<title><bean:message key="title.heading"/> - warrantOICUpdate.jsp</title>

<!--THESE TWO LINES FOR STRUTS & JAVASCRIPTS VALIDATIONS-->
<html:javascript formName="juvenileWarrantForm" />
<!-- JAVASCRIPT FILES-->
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
<tiles:insert page="../js/warrantOICUpdate.js"/>
<script type="text/javascript">
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

function showNotes()
{
	if( document[0].cautionCheckbox.checked )
	{
	    show('otherCaution', 1,"row");
	    show('otherCaution2', 1,"row");
    }	
}

function resetSchoolName(){
	var schoolDropDown=document.getElementById("schoolCodeId");
	schoolDropDown.selectedIndex = 0;
}

function customSchoolCheck(checkDistrict){
	var schoolCodeIdVar=document.getElementById("schoolCodeId").value;
	var schoolDistrictIdVar=document.getElementById("schoolDistrictId").value;
	if(checkDistrict){
		if(schoolDistrictIdVar==""){
			alert("School District must be selected first.");
			return false;
		}
	}
	if(schoolCodeIdVar=="" && schoolDistrictIdVar >""){
		alert("School Name must be selected if school district is selected.");
		document.getElementById("schoolCodeId").focus();
		return false;
	}
	return true;
}
</script>
</head>
<!--BEGIN BODY TAG--> 
<!-- onload="fillText(); not needed on update -->
<body onload="setPageFocus(),checkHeightWeightInputs();">
<html:form action="/displayWarrantOICUpdateConfirm" target="content" >
<input type="hidden" name="warrantType" value="">
<input type="hidden" name="helpFile" value="juvwarnt/juvenile_warrants_final.htm#|128">
<!-- BEGIN HEADING TABLE -->
<table width=100%>
  <tr>
    <td align="center" class="header" ><bean:message key="prompt.update"/>&nbsp;<bean:message key="title.juvWarrantOIC" /></td>
  </tr>
</table>
<!-- END HEADING TABLE -->
  
<!-- BEGIN INSTRUCTION TABLE -->
<table width="98%" align="center" border="0">
  <tr>
    <td>
      <ul>
        <li>Make changes as needed and select Finish button to update warrant and view confirmation.</li>
        <li>Clicking on Existing Associate name displays full address and contact information.</li>
      </ul>	
    </td>
  </tr>
	
  <!-- commented out the following since none of the editable fields are mandatory 
	<tr>
	  <td class="required"><bean:message key="prompt.1.diamond"/><bean:message key="prompt.requiredFieldsInstruction"/></td>
  </tr> 
	-->
</table>
<!-- END INSTRUCTION TABLE -->

<!-- BEGIN WARRANT INFORMATION TABLE -->
<table width="98%" align="center" border="0" cellpadding="2" cellspacing="1">
  <tr class="detailHead">
      <td colspan="4" class="detailHead">&nbsp;Juvenile&nbsp;<bean:message key="prompt.warrantInfo" /></td>
  </tr>

  <tr>
    <td class=formDeLabel><bean:message key="prompt.warrantNumber"/></td>
    <td class=formDe colspan="3"><bean:write name="juvenileWarrantForm" property="warrantNum"/></td>
  </tr>
  <tr>
    <td class=formDeLabel><bean:message key="prompt.juvenileNumber"/></td>
    <td class=formDe><bean:write name="juvenileWarrantForm" property="juvenileNum"/></td>
    <td class=formDeLabel nowrap width="1%"><bean:message key="prompt.referralNumber"/></td>
    <td class=formDe><bean:write name="juvenileWarrantForm" property="referralNum"/></td>
  </tr>
  <tr>
    <td class=formDeLabel nowrap><bean:message key="prompt.probationOfficer"/> of Record</td>
    <td colspan="3" class=formDe><bean:write name="juvenileWarrantForm" property="probationOfficerOfRecordName"/></td>
  </tr>
<!-- END WARRANT INFORMATION TABLE -->

  <tr><td>&nbsp;</td></tr>
<!-- BEGIN CHARGE INFORMATION TABLE -->
	<tr>
	   <td colspan=4>
		  <table width=100% cellpadding=4 cellspacing=1> 
			<tr class="detailHead">
			    <td class="subhead" colspan="4">&nbsp;<bean:message key="prompt.charge" />&nbsp;<bean:message key="prompt.info" /></td>
		   </tr>
			<logic:empty name="juvenileWarrantForm" property="charges">
				<tr>
					<td class=formDe colspan=3>No Charges Found</td>
				</tr>
				<tr><td><br></td></tr>
			</logic:empty>	
  <%--int RecordCounter = 0; String bgcolor = "";--%>
  			<logic:notEmpty name="juvenileWarrantForm" property="charges">
    <!-- <td> -->
		    <logic:iterate id="chargeIndex" name="juvenileWarrantForm" property="charges"> 
      <%--tr class= <% RecordCounter++; bgcolor = "alternateRow";
        if (RecordCounter % 2 == 1)
          bgcolor = "normalRow";
        out.print(bgcolor);  %>	
      --%>    
		      <tr>
		        <td class=formDeLabel width=1% align=center nowrap><bean:message key="prompt.select"/></td>
		        <td class=formDeLabel width=1%><bean:message key="prompt.charge"/></td>
		        <td class=formDe><bean:write name="chargeIndex" property="offense"/></td>
		      </tr>
          
		      <tr>
        		<td class=formDeLabel align=center>
   			    	<html:radio name="juvenileWarrantForm" property="selectedCharge" idName="chargeIndex" value="sequenceNum" />
        		</td>
		        <td class=formDeLabel nowrap><bean:message key="prompt.petitionNumber"/></td>
		        <td class=formDe><bean:write name="chargeIndex" property="petitionNum"/></td>
		      </tr>
		      
		      <tr>
	    		<td class=formDeLabel></td>
	    		<td class=formDeLabel width=1% nowrap><bean:message key="prompt.chargeSeqNumber"/></td>
	    		<td class=formDe><bean:write name="chargeIndex" property="sequenceNum"/></td>              
		  	 </tr>
	    
		      <tr>
				<td class=formDeLabel>&nbsp;</td>
        		<td class=formDeLabel><bean:message key="prompt.chargeCourt"/></td>
		        <td class=formDe><bean:write name="chargeIndex" property="court"/></td>
		      </tr>
		      
		      <tr>
      			<td class=formDeLabel></td>
      			<td class=formDeLabel><bean:message key="prompt.chargeNCICNumber"/></td>
      			<td class=formDe><bean:write name="chargeIndex" property="offenseCodeId"/></td>
	   		 </tr>
  			  <tr><td><br></td></tr>			
	    </logic:iterate>
	    </logic:notEmpty>
		  </table>
		</td>
	</tr>	
<!-- END CHARGE INFORMATION TABLE -->
<!-- these solve js issue with validation.xml - since these fields are not present for OIC -->
	<logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="oic">
	<tr>
		<td colspan=4>  	
			<input type=hidden name=phoneNum.areaCode value="" />
			<input type=hidden name=phoneNum.prefix value="" />
			<input type=hidden name=phoneNum.4Digit value="" />   
		</td>
	</tr> 
	</logic:equal> 	
<!-- BEGIN JUVENILE INFORMATION TABLE -->
  <tr class="detailHead">
    <td class="subhead" colspan="4">&nbsp;<bean:message key="prompt.juvenileInfo" /></td>
  </tr>
  <tr>
    <td class=formDeLabel><bean:message key="prompt.juvenileName"/></td>
    <td class=formDe colspan="3">
      <bean:write name="juvenileWarrantForm" property="juvenileName.lastName"/>, 
      <bean:write name="juvenileWarrantForm" property="juvenileName.firstName"/>
      <bean:write name="juvenileWarrantForm" property="juvenileName.middleName"/> 
      <bean:write name="juvenileWarrantForm" property="nameSuffix"/>
    </td>
  </tr>
	
  <tr>
    <td class=formDeLabel><bean:message key="prompt.aka"/></td>
    <td class=formDe colspan="3">
      <logic:equal name="juvenileWarrantForm" property="aliasName" value=""></logic:equal>                
      <bean:write name="juvenileWarrantForm" property="aliasName"/>
    </td>
  </tr>

  <tr>
    <td class=formDeLabel><bean:message key="prompt.dateOfBirth"/></td>
    <td class=formDe>
      <bean:write name="juvenileWarrantForm" property="dateOfBirth" formatKey="date.format.mmddyyyy" />
    </td>
	<td class=formDeLabel><bean:message key="prompt.build"/></td>
	<td class=formDe>
      <html:select property="buildId">
     	 <html:option value=""><bean:message key="select.generic" /></html:option>
	      <html:optionsCollection name="codeHelper" property="builds" value="code" label="description" /> 
      </html:select>
	</td>      
  </tr> 

  <tr>
    <td class=formDeLabel width=1% nowrap><bean:message key="prompt.ssn"/></td>
    <td colspan="3" class=formDe>
      <html:text property="SSN1" size="3" maxlength="3" onkeyup="return autoTab(this, 3);"/>-
      <html:text property="SSN2" size="2" maxlength="2" onkeyup="return autoTab(this, 2);"/>-
      <html:text property="SSN3" size="4" maxlength="4" onkeyup="return autoTab(this, 4);"/>
  	</td>
  </tr>
	     
  <tr>
    <td class=formDeLabel><bean:message key="prompt.race"/></td>
    <td class=formDe><bean:write name="juvenileWarrantForm" property="race"/></td>
    <td class=formDeLabel><bean:message key="prompt.sex"/></td>
    <td class=formDe><bean:write name="juvenileWarrantForm" property="sex"/></td>
  </tr>
	
  <tr>
    <td class=formDeLabel><bean:message key="prompt.height"/></td>
    <td class=formDe nowrap>
       <html:text name="juvenileWarrantForm" property="heightFeet" size="3" maxlength="1"/> ft
       <html:text name="juvenileWarrantForm" property="heightInch" size="3" maxlength="2"/> in
	</td>
    <td class=formDeLabel><bean:message key="prompt.weight"/></td>
    <td class=formDe><html:text property="weight" size="3" maxlength="3"/> lbs</td>
  </tr>
	
  <tr>
    <td class=formDeLabel><bean:message key="prompt.eyeColor"/></td>
    <td colspan=3 class=formDe>
      <html:select property="eyeColorId">
	      <html:option value=""><bean:message key="select.generic" /></html:option>  	                	  	
	      <html:optionsCollection name="codeHelper" property="eyeColors" value="code" label="description" />
      </html:select> 
    </td>
	</tr>
	<tr> 
    <td class=formDeLabel><bean:message key="prompt.hairColor"/></td>
    <td colspan=3 class=formDe>
      <html:select property="hairColorId">
      <html:option value=""><bean:message key="select.generic" /></html:option>  	                	  	
      <html:optionsCollection name="codeHelper" property="hairColors" value="code" label="description" />
      </html:select>  
    </td> 
  </tr> 
<%-- moved build to same line as Date of Birth to match other warrant create/update pages --%>
<%--  <tr>
    <td class=formDeLabel><bean:message key="prompt.build"/></td>
    <td colspan=3 class=formDe>
      <html:select property="buildId">
      <html:option value=""><bean:message key="select.generic" /></html:option>
      <html:optionsCollection property="builds" value="code" label="description" /> 
      </html:select> 
    </td>
	</tr> --%>
	<tr>
    <td class=formDeLabel width=1%><bean:message key="prompt.complexion"/></td>
    <td colspan=3 class=formDe>
      <html:select property="complexionId">
      <html:option value=""><bean:message key="select.generic" /></html:option>  	                	  	
      <html:optionsCollection name="codeHelper" property="complexions" value="code" label="description" />
      </html:select> 
    </td>
  </tr>
  <%-- JMF - 3/20/2006 - Category not being persisted - JIMS200029289 --%>
  <tr>
    <td valign="top" class=formDeLabel><bean:message key="prompt.scarsMarks"/></td>
    <td colspan=3 class=formDe>
      <html:select property="selectedScars" size="6" multiple="true">
			<html:optionsCollection property="scarsMarksCodes" value="code" label="description" />
      </html:select>    
    </td>
	</tr>
	<tr>
    <td valign="top" class=formDeLabel><bean:message key="prompt.tattoos"/></td>
    <td colspan=3 class=formDe>
      <html:select property="selectedTattoos" size="6" multiple="true">
		<html:optionsCollection property="tattoosCodes" value="code" label="description" />
      </html:select> 
    </td>
  </tr>         

  <tr>
    <td class=formDeLabel><bean:message key="prompt.schoolDistrict"/></td>
    <td class=formDe colspan="3">
      <html:select property="schoolDistrictId" onchange="resetSchoolName()">
        <html:option value=""><bean:message key="select.generic" /></html:option>
		<html:optionsCollection name="juvenileWarrantForm" property="schoolDistricts" value="districtCode" label="districtDescription" />
      </html:select>
      <html:submit property="submitAction">
        <bean:message key="button.go"></bean:message>
      </html:submit> 
    </td> 
  </tr>

  <tr>
    <td class=formDeLabel><bean:message key="prompt.schoolName"/></td>
    <td colspan="3" class=formDe>
      <html:select property="schoolCodeId" styleId="schoolCodeId">
        <html:option value=""><bean:message key="select.generic" /></html:option>
         <logic:notEmpty name="juvenileWarrantForm" property="schools">
		<html:optionsCollection name="juvenileWarrantForm" property="schools" value="schoolCode" label="schoolDescription" />
      </logic:notEmpty>
      </html:select>
     </td>
  </tr>

  <%--tr>
    <td valign="top" class=formDeLabel>Cautions</td>
    <td colspan="3" class=formDe>
      <html:select property="selectedCautions" size="6" style="width:300;" multiple="true" onchange="checkedOther('1')">
      <html:optionsCollection name="codeHelper" property="cautions" value="code" label="description" />
      </html:select> 
    </td>
  </tr--%>
  <tr>
	<td class=formDeLabel colspan=4 valign="top"><bean:message key="prompt.cautions"/></td>
  </tr>
  <input type="hidden" name="cautionCheckboxClear" value="true" />
	<logic:iterate id="cautIndex" name="codeHelper" property="cautions">
	  <logic:notEqual name="cautIndex" property="code" value="OT">
		<tr>
			<td class=formDe colspan=4>
				<html:multibox property="selectedCautions">
					<bean:write name="cautIndex" property="code" /> 
				</html:multibox>
				<bean:write name="cautIndex" property="description"/> 
			</td>
		</tr>
	  </logic:notEqual>	   
	</logic:iterate>
  <tr>
     <td class=formDe colspan=4>
     <html:multibox styleId='cautionCheckbox' property="selectedCautions" onclick="return showOtherCaution(this.checked)">
					              OT</html:multibox>OTHER</td>
  </tr>
  <tr id="otherCaution" class=hidden><td colspan=4 class=formDeLabel><bean:message key="prompt.otherCautionComments"/></td></tr>
  <tr id="otherCaution2" class=hidden>
  	<script>showNotes();</script>
    <td colspan="4" class=formDe>
      <html:textarea property="cautionComments" style="width:100%" rows="3" onkeyup="textCounter(this.form.cautionComments,250);"></html:textarea>
    </td>
  </tr>  
<!-- END JUVENILE INFORMATION TABLE -->

  <tr><td>&nbsp;</td></tr>
<!-- VARIABLES NEEDED FOR DISPLAY -->
  <%int RecordCounter = 0; 
    String bgcolor = "";%>
<!-- BEGIN EXISTING ASSOCIATE INFORMATION TABLE -->
	<tr>
	 	<td colspan=4 class=detailHead nowrap>&nbsp;<bean:message key="prompt.existingAssociateInfo" /></td>
	</tr>
  <tr>
    <td colspan=2 class=formDeLabel><bean:message key="prompt.name"/></td>
    <td colspan=2 class=formDeLabel><bean:message key="prompt.relationshipToJuvenile"/></td>
  </tr>
	
  <logic:empty name="juvenileWarrantForm" property="associates"><tr><td colspan="2" class=formDe></td></tr></logic:empty>
	    
  <%-- not required as part of interation #7 --%> 
  <logic:notEmpty name="juvenileWarrantForm" property="associates">   
    <logic:iterate id="associate" name="juvenileWarrantForm" property="associates"> 
      <tr class= <% RecordCounter++;
          bgcolor = "normalRow";
          if (RecordCounter % 2 == 1)
          bgcolor = "alternateRow";
          out.print(bgcolor);  %>	align="left">
       
        <td colspan=2>
			<a href="javascript:openWindow('/<msp:webapp/>displayAssociateDetails.do?associateNumber=<bean:write name="associate" property="associateNum"/>');">	               							      	               			                       
    	      <bean:write name="associate" property="associateName.lastName" />, <bean:write name="associate" property="associateName.firstName" /> <bean:write name="associate" property="associateName.middleName" /></a>
        </td>			 
        <td colspan=2 nowrap="nowrap" align="left"><bean:write  name="associate" property="relationshipToJuvenile" /></td>	
      </tr>
    </logic:iterate> 
  </logic:notEmpty>
<!-- END EXISTING ASSOCIATE INFORMATION TABLE -->

  <tr><td>&nbsp;</td></tr>
<!-- BEGIN WARRANT ORIGINATOR INFORMATION TABLE -->
   <tr class="detailHead"> <!-- bgcolor="#B3C9F5" -->
      <td class="subhead" colspan="4">&nbsp;<bean:message key="prompt.warrantOriginatorInfo"/></td>
   </tr>
   <tr>
      <td class=formDeLabel><bean:message key="prompt.court"/></td>
      <td class=formDe colspan=3><bean:write name="juvenileWarrantForm" property="warrantOriginatorCourt"/></td>
   </tr>
   <tr>   
      <td class=formDeLabel><bean:message key="prompt.judgeName"/></td>
      <td class=formDe colspan=3><bean:write name="juvenileWarrantForm" property="warrantOriginatorJudge"/></td>
   </tr>
</table>
<!-- END WARRANT ORIGINATOR INFORMATION TABLE -->

<br>
<!-- BEGIN BUTTON TABLE -->
<table border="0" width="100%">
  <tr>
    <td align="center">  <%-- && validateJuvenileWarrantForm(this.form) --%>
      <html:submit property="submitAction" 
      			   onclick="return validateJuvenileFields(this.form) && checkCautionOther(this.form) && customSchoolCheck(false);">
        <bean:message key="button.next"></bean:message>
      </html:submit>&nbsp;
      <html:reset />&nbsp;
      <html:button property="org.apache.struts.taglib.html.CANCEL" 
			       onclick="document.location.href='/JuvenileWarrants/displayGenericSearch.do?warrantTypeUI=updateOIC&associateUpdatable=false'">
	        <bean:message key="button.cancel" />
      </html:button>
    </td>
  </tr>
</table>
<!-- END BUTTON TABLE -->
</html:form>
<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html>