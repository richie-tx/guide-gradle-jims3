<!-- Used to create release to information. -->
<!--MODIFICATIONS -->
<%-- CShimek	01/13/2005	Create JSP --%>
<%-- CShimek    02/02/2006  Add hidden fields for HelpFile name --%>
<%-- CShimek    03/07/2006  Correct problem of current date not displaying in transfer date field --%> 
<%-- LDeen	    12/21/2006  Revised help file code --%>
<%-- CShimek    02/07/2007  Revised evalSearch1() to set option select: was not being set after returning from department search --%>
<%-- CShimek	03/22/2007  #40475 added missing weight unit annotation --%>
<%-- LDeen		06/04/2007  #42564 changed path to app.js --%>
<%-- RCapestani 10/02/2007  #36271 added onchange ="clearDeptName()" onkeypress="clearDeptName() --%>
<%-- RCapestani 10/04/2007  #45622 added <bean:message key="prompt.1.diamond"/> to key="prompt.searchBy" --%>
<%-- RCapestani 10/02/2007  #36271 modified evalSearch(el) to set officer values to "" and added onkeypress="clearDeptName()to officer search --%>
<%-- DWilliamson 04/17/2008 #51016 Increase Officer ID field to 11. --%>
<%-- RYoung     08/06/2015 #27796 MJCW:  Adapt MJCW and Warrants to IE10 and 11 (Juv Profile - Education UI) --%>
 
<!DOCTYPE HTML>
<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<!-- SCRIPTING VARIABLES -->
<jsp:useBean id="codeHelper" scope="application" class="ui.juvenilewarrant.helper.JuvenileWarrantListHelper" />

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

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<!-- JAVASCRIPT FILES-->
<script type="text/javascript" src="/<msp:webapp/>js/warrants.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
<!-- main calendar program -->
<script type="text/javascript" src="/<msp:webapp/>js/date.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/PopupWindow.js"></script>
<tiles:insert page="../js/warrantReleaseToJP.js" />
<script>
var searchType = '<bean:write name="juvenileWarrantForm" property="search"/>';
var getAction = '<bean:write name="juvenileWarrantForm" property="action"/>';
var getDept = '<bean:write name="juvenileWarrantForm" property="transferOfficerDepartmentName"/>';


function show(what, hide, format){
		if (hide == 0)
		{
			document.getElementById(what).className = 'hidden';
		}
		else if(format == "row")
		{
			document.getElementById(what).className = 'visibleTR';
		}
		else if(format=="table")
		{
			document.getElementById(what).className = 'visibleTable';
		}
		else if(format == "formDe")
		{
			document.getElementById(what).className = 'formDe';
		}
		else 
		{			
			document.getElementById(what).className = 'visible';
		}
	}
function evalSearch(el){

	if (el.search.options[ el.search.selectedIndex ].value == 'userSearch' ){
		el.transferOfficerId.value="";
		el.transferOfficerDepartmentId.value="";
		el.transferOfficerIdType.selectedIndex = 0;
		show('byUserId', 1,"row");
		show('byOfficerId', 0,"row");
		show('officerDept',0,"row");
		if(document.getElementById("deptName").lastChild != null)
			document.getElementById("deptName").lastChild.nodeValue="";
	}

	else if (el.search.options[ el.search.selectedIndex ].value == 'officerSearch'){
		show('byOfficerId', 1,"row");	
		show('byUserId', 0,"row");
		show('officerDept',1,"row");
		el.transferOfficerDepartmentId.value="";
		el.transferOfficerIdType.selectedIndex.value = 0;
		el.transferOfficerLogonId.value="";
		if(document.getElementById("deptName").lastChild != null)
			document.getElementById("deptName").lastChild.nodeValue="";
	}
	else{
		show('byOfficerId', 0,"row");	
		show('byUserId', 0,"row");	
		show('officerDept',0,"row");
		if(document.getElementById("deptName").lastChild != null)
			document.getElementById("deptName").lastChild.nodeValue="";
	}

}

function evalSearch1(el){

	if (searchType == 'userSearch' || getAction =='userDepartment' || getAction =='userDeptTransfer'){
		show('byUserId', 1,"row");
		show('byOfficerId', 0,"row");	
		show('officerDept',0,"row");
		el.search.selectedIndex = 1;		
	}
	else if (searchType == 'officerSearch' || getAction =='officerDepartment' || getAction =='officerDeptTransfer'){
		
		show('byOfficerId', 1,"row");	
		show('byUserId', 0,"row");	
		show('officerDept',1,"row");	
		el.search.selectedIndex = 2;
	}
	
	else{
		show('byOfficerId', 0,"row");	
		show('byUserId', 0,"row");	
		show('officerDept',0,"row");	
	}
	
}
function trim(textbox) {
  if (textbox) {
    while (textbox.value.substring(0,1) == " ") {
      textbox.value = textbox.value.substring(1);
    }
  }
}
function loadDepartment(file, actionVal){
	
	var theForm = document.forms[0];
	
	if(theForm.search.options[ document.forms[0].search.selectedIndex ].value == 'userSearch' || document.getElementById('byUserId').className == 'visibleTR')
	{
		trim(theForm.transferOfficerLogonId);
		if(theForm.transferOfficerLogonId.value =="")
		{
			alert("User ID must be entered to find department.");
	       theForm.transferOfficerLogonId.focus();
	       return false;
		}
	}
	else if(theForm.search.options[ document.forms[0].search.selectedIndex ].value == 'officerSearch' || document.getElementById('byOfficerId').className == 'visibleTR')
	{
		trim(theForm.transferOfficerId);
		if(theForm.transferOfficerId.value =="")
		{
			alert("Officer ID must be entered to find department.");
	       theForm.transferOfficerId.focus();
	       return false;
		}
		if(theForm.transferOfficerIdType.value =="")
		{
			alert("Officer ID Type must be selected to find department.");
	       theForm.transferOfficerIdType.focus();
	       return false;
		}
	}
	
		//var theForm = document.forms[0];	
		//var element=document.getElementById('hiddenVal');
		//element.value=actionVal;
		var myURL =file + "&selectedValue=" + actionVal;				
		load(myURL,top.opener);
		window.close();
}
function load(file,target) {
   
        window.location.href = file;
}
function validateDepartment() {
	
var thisForm = document.forms[0];
trim(thisForm.transferOfficerDepartmentId);
if(thisForm.transferOfficerDepartmentId.value=="")
{
alert("Department code has to be provided for Validation.");
		thisForm.transferOfficerDepartmentId.focus();		    
	    return false;
}
	return true;
}

function changeActionURL(formName, URL, doSubmit){
	var myForm=document.getElementsByName(formName);
	myForm[0].action = URL;
	if(doSubmit){
		myForm[0].submit();
	}
	return true;
}
</script>

<script type="text/javascript">
$(function() {

    $("#transDate").datepicker({
	      changeMonth: true,
	      changeYear: true,
	      onClose: function( selectedDate ) {
	        $( "#transDate" ).datepicker( "option", "minDate", selectedDate );
	    }
    });
 });
</script>
<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/warrants.css" />
<html:base />
<title><bean:message key="title.heading"/> - warrantReleaseToJP.jsp</title>
</head>
<!--END HEADER TAG-->

<!--BEGIN BODY TAG-->
<body  onload="javascript:evalSearch1(document.forms[0]);">
<html:form action="/displayReleaseToJPSummary" target="content">
<input type="hidden" name="helpFile" value="juvwarnt/juvenile_warrants_final.htm#|105">		

<!-- BEGIN HEADING TABLE -->
 <table align="center">
   <tr>
     <td class="header" align="center">
       <bean:message key="title.juvWarrantReleaseToJP"/> Information
     </td>
   </tr>
</table>
<!-- END HEADING TABLE -->
<!-- BEGIN INSTRUCTION TABLE -->
 <table border="0" width="98%" align=center>
   <tr>
     <td>
       <ul><li>Enter Transfer Information and select Next button to continue.</li></ul>
     </td>
   </tr>
</table>
<!-- END INSTRUCTION TABLE -->
<!-- BEGIN REQUIRED TABLE -->
<table border="0" width="98%" align=center>	
	<tr><td class="required"><bean:message key="prompt.1.diamond"/><bean:message key="prompt.requiredFieldsInstruction"/>&nbsp;<bean:message key="prompt.dateFieldsInstruction"/></td></tr>	
	<tr>
      <td align="center" style="color: red"><b><html:errors></html:errors></b></td>
    </tr>
</table>
<!-- END REQUIRED TABLE -->
  <table align="center" cellspacing="1" width=98% cellpadding=4 border=0>
  <!-- BEGIN JUVENILE INFORMATION TABLE -->
  	<tr>
		<td colspan=4 class=detailHead nowrap><bean:message key="prompt.juvenileInfo" /></td>
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
        <td class="formDe" colspan=3><bean:write name="juvenileWarrantForm" property="aliasName"/></td>
     </tr>
     <tr>
        <td class="formDeLabel"><bean:message key="prompt.dateOfBirth"/></td>
        <td class="formDe"><bean:write name="juvenileWarrantForm" property="dateOfBirth" formatKey="date.format.mmddyyyy" /></td>
        <td class="formDeLabel" width=1% nowrap><bean:message key="prompt.juvenileNumber"/></td>
        <td class="formDe"><bean:write name="juvenileWarrantForm" property="juvenileNum"/></td>
     </tr>
     <tr>
        <td class="formDeLabel"><bean:message key="prompt.race"/></td>
        <td class="formDe"><bean:write name="juvenileWarrantForm" property="race"/></td>
        <td class="formDeLabel"><bean:message key="prompt.sex"/></td>
        <td class="formDe"><bean:write name="juvenileWarrantForm" property="sex"/></td>
     </tr>
     <tr>
        <td class="formDeLabel"><bean:message key="prompt.height"/></td>
        <td class="formDe">
       		<logic:notEqual name="juvenileWarrantForm" property="height" value="">
     			<logic:notEqual name="juvenileWarrantForm" property="heightFeet" value="0">
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
        <td class="formDeLabel"><bean:message key="prompt.scarsMarks"/></td>
        <td class="formDe" colspan="3">
           <logic:notEmpty name="juvenileWarrantForm" property="scarsAndMarksSelected">
             <logic:iterate id="scars" name="juvenileWarrantForm" property="scarsAndMarksSelected">
                <bean:write name="scars" property="description" /><br>
           	</logic:iterate>
           </logic:notEmpty>
        </td>
     </tr>
     <tr>
        <td class="formDeLabel"><bean:message key="prompt.tattoos"/></td>
        <td class="formDe" colspan="3">
            <logic:notEmpty name="juvenileWarrantForm" property="tattoosSelected">
              	<logic:iterate id="tattoo" name="juvenileWarrantForm" property="tattoosSelected">
                  		<bean:write name="tattoo" property="description" /><br>
               	</logic:iterate>
            </logic:notEmpty>
        </td>
     </tr>
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
<!-- END JUVENILE INFORMATION TABLE -->
    <tr><td colspan=4><br></td></tr>
    <!-- BEGIN WARRANT INFORMATION TABLE -->
    <tr>
		<td class=detailHead nowrap colspan=4><bean:message key="prompt.juvenileWarrantInfo" /></td>
	</tr>
	<tr>
        <td class="formDeLabel"><bean:message key="prompt.warrantNumber"/></td>          
        <td class="formDe"><bean:write name="juvenileWarrantForm" property="warrantNum"/></td>
        <td class="formDeLabel"><bean:message key="prompt.warrantStatus"/></td>
        <td class="formDe"><bean:write name="juvenileWarrantForm" property="warrantStatus"/></td>
     </tr>
     <tr>
        <td class="formDeLabel"><bean:message key="prompt.warrantType"/></td>
        <td class="formDe" colspan="3">
           <bean:write name="juvenileWarrantForm" property="warrantType"/>
        </td>
     </tr>
     <tr>
        <td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.warrantOriginatorName"/></td>
        <td class="formDe" colspan="3">
           <bean:write name="juvenileWarrantForm" property="warrantOriginatorName"/>
        </td>
     </tr>
     <!-- END WARRANT INFORMATION TABLE -->
     <tr><td colspan=4><br></td></tr>
     <%-- JJS has single charge, JOT can have multiple charges -- need to add logic tags? --%>
				<!-- BEGIN CHARGE INFORMATION TABLE -->						
				<tr>
					<td colspan=4 class=detailHead nowrap><bean:message key="prompt.chargeInfo"/></td>
				</tr>
			<logic:empty name="juvenileWarrantForm" property="charges">
	        <tr>
          		<td colspan=4>No charges.</td>
       		</tr>
     		</logic:empty>
     		<logic:notEmpty name="juvenileWarrantForm" property="charges">
		    <logic:iterate id="chargeIndex" name="juvenileWarrantForm" property="charges"> 
	        <tr>    
	         	<td class=formDeLabel><bean:message key="prompt.charge" /></td>
	         	<td class=formDe colspan=3><bean:write name="chargeIndex" property="offense"/></td>
	       </tr>
	       <tr>	         
	         	<td class=formDeLabel><bean:message key="prompt.petitionNumber"/></td>
	 			<td class=formDe colspan=3><bean:write name="chargeIndex" property="petitionNum"/></td>
	       </tr>
	       <tr>  				
	  			<td class="formDeLabel"><bean:message key="prompt.chargeCourt"/></td> 
	  			<td class=formDe colspan=3><bean:write name="chargeIndex" property="court"/></td>
	  		</tr> 				
	  		<tr>
	  			<td class="formDeLabel"><bean:message key="prompt.chargeNCICNumber"/></td>
		   		<td class=formDe colspan=3><bean:write name="chargeIndex" property="offenseCodeId"/></td>
	   		</tr>
	   		<tr><td colspan=4><br></td></tr>
	       	</logic:iterate>
	       	</logic:notEmpty>
           <!-- end Charge--> 
            <!-- BEGIN SERVICE INFORMATION TABLE -->
            <tr>
				<td colspan=4 class=detailHead nowrap><bean:message key="prompt.serviceInfo" /></td>
			</tr>
			<tr>
            	<td class="formDeLabel"><bean:message key="prompt.serviceAddress"/></td>          
            	<td class="formDe" colspan="3"><bean:write name="juvenileWarrantForm" property="serviceAddress"/></td>             
            </tr>
            <tr>    
                <td class="formDeLabel"><bean:message key="prompt.serviceStatus"/></td>
                <td class="formDe" colspan="3">
                   <bean:write name="juvenileWarrantForm" property="serviceStatus"/>
                </td>
             </tr>
            <tr>
                <td class="formDeLabel"><bean:message key="prompt.serviceDate"/></td>          
                <td class="formDe"><bean:write name="juvenileWarrantForm" property="currentServiceDate" formatKey="date.format.mmddyyyy" /></td>
                <td class="formDeLabel"><bean:message key="prompt.serviceTime"/></td>
                <td class="formDe"><bean:write name="juvenileWarrantForm" property="currentServiceDate" formatKey="time.format.HHmm" /></td>
             </tr>   
             <!-- END SERVICE INFORMATION TABLE -->  
             <tr><td colspan=4><br></td></tr>
             <!-- BEGIN ARREST INFORMATION TABLE -->
             <tr>
				<td class=detailHead nowrap colspan=4><bean:message key="prompt.arrestInfo" /></td>
			 </tr>
             <tr>
                <td class="formDeLabel"><bean:message key="prompt.arrestAddress"/></td>          
                <td colspan=3 class="formDe">
                   <bean:write name="juvenileWarrantForm" property="arrestAddress"/>
                </td>
	         </tr>
	            <tr>
                <td class="formDeLabel"><bean:message key="prompt.arrestDate"/></td>          
                <td class="formDe"><bean:write name="juvenileWarrantForm" property="arrestDate" formatKey="date.format.mmddyyyy" /></td>
                <td class="formDeLabel"><bean:message key="prompt.arrestTime"/></td>
                <td class="formDe"><bean:write name="juvenileWarrantForm" property="arrestDate" formatKey="time.format.HHmm" /></td>
             </tr>     
             <!-- END ARREST INFORMATION TABLE -->  
             <tr><td colspan=4><br></td></tr>
             <!-- BEGIN EXECUTOR INFORMATION -->
             <tr>
				<td colspan=4 class=detailHead nowrap><bean:message key="prompt.executorInfo" /></td>
			</tr>
			<tr>
	            <td class="formDeLabel"><bean:message key="prompt.officerName"/></td>          
	            <td class="formDe" colspan="3">
	                   <bean:write name="juvenileWarrantForm" property="executorName.formattedName"/>
	            </td>
	        </tr>
	        <tr>
	               <td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.officerIdNumber"/></td>
	               <td class="formDe"><bean:write name="juvenileWarrantForm" property="executorId"/></td>
	               <td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.officerIdType"/></td>
	               <td class="formDe"><bean:write name="juvenileWarrantForm" property="executorIdType"/></td> 
	            </tr>
	            <tr>   
		           <td class="formDeLabel"><bean:message key="prompt.department"/></td>
	               <td class="formDe" colspan="3"><bean:write name="juvenileWarrantForm" property="executorDepartmentName"/></td>
	            </tr>            
	            <tr>
	               <td class="formDeLabel"><bean:message key="prompt.workPhone"/></td>
	               <td class="formDe"><bean:write name="juvenileWarrantForm" property="executorPhoneNum"/></td>
	               <td class="formDeLabel"><bean:message key="prompt.cellPhone"/></td>
	               <td class="formDe"><bean:write name="juvenileWarrantForm" property="executorCellNum"/></td>
	            </tr>
	            <tr>
	               <td class="formDeLabel"><bean:message key="prompt.pager"/></td>
	               <td class="formDe"><bean:write name="juvenileWarrantForm" property="executorPager"/></td>
		           <td class="formDeLabel"><bean:message key="prompt.email"/></td>
	               <td class="formDe"><bean:write name="juvenileWarrantForm" property="executorEmail"/></td>
	            </tr>   
	            <!-- END EXECUTOR INFORMATIN TABLE -->  
	            <tr><td colspan=4><br></td></tr>
	            <!-- BEGIN RELEASE DECISION INFORMATION TABLES -->
	            <tr>
					<td colspan=4 class=detailHead nowrap><bean:message key="prompt.releaseDecisionInfo" /></td>
				</tr>
				<tr>
	                <td class="formDeLabel"><bean:message key="prompt.jpOfficerName"/></td>          
	                <td class="formDe" colspan="3">
	                   <bean:write name="juvenileWarrantForm" property="releaseDecisionUserName"/>
	                </td>
	            </tr>
	            <tr>
	                <td class="formDeLabel"><bean:message key="prompt.releaseDecision"/></td>          
	                <td class="formDe" colspan="3">
	                   <bean:write name="juvenileWarrantForm" property="releaseDecision"/>
	                </td>
	            </tr>
	            <tr>
	                <td class="formDeLabel"><bean:message key="prompt.releaseDecisionDate"/></td>          
	                <td class="formDe">
	                	<bean:write name="juvenileWarrantForm" property="releaseDecisionDate" formatKey="date.format.mmddyyyy" />
	                </td>
	                <td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.releaseDecisionTime"/></td>
	                <td class="formDe">
	              		<bean:write name="juvenileWarrantForm" property="releaseDecisionDate" formatKey="time.format.HHmm" />
	    		    </td> 
	            </tr>       
		    <%-- hidden field needed to transfer date and time validation --%>
	            <tr>
	            	<td colspan=4>
		            	<input type="hidden" name="releaseDate" value=<bean:write name="juvenileWarrantForm" property="releaseDecisionDate" formatKey="date.format.mmddyyyy" /> >
		            	<input type="hidden" name="releaseTime" value=<bean:write name="juvenileWarrantForm" property="releaseDecisionDate" formatKey="time.format.HHmm" /> >
					</td>
	            </tr>
<!-- END RELEASE DECISION INFORMATION TABLES -->
				<tr><td colspan=4><br></td></tr>
<!-- BEGIN TRANSFER INFORMATION TABLE -->
			<tr>
				<td class=detailHead nowrap colspan=4><bean:message key="prompt.transferInfo" /></td>
			</tr>
			<!-- BEGIN INQUIRY TABLE -->
			<tr>
				<td colspan="4">
					<table align="center" width="100%" border="0" cellpadding="2" cellspacing="1" class=borderTableBlue>
						<tr>
							<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.1.diamond"/><bean:message key="prompt.searchBy" /></td>
							<td class="formDe">
								<html:select name="juvenileWarrantForm" property="search" onchange="evalSearch(this.form)">
								  <option value="">Please Select</option>
								  <option value=userSearch>User ID</option>
								  <option value=officerSearch>Officer ID</option>			 
								</html:select>
							</td>
						</tr>
					</table>
				</td>
			</tr>			

			<tr>
				<td colspan="4">
					<table align="center" width="100%" border="0" cellpadding="2" cellspacing="1" class=borderTableBlue>
						<tr id="byUserId" class=hidden>
	        				<td class=formDeLabel><bean:message key="prompt.mjw.diamond"/><bean:message key="prompt.userId"/></td>
	        				<td class=formDe colspan="3">
	        					<html:text property="transferOfficerLogonId" size="10" maxlength="5" onchange ="clearDeptName()" onkeypress="clearDeptName()"/>
		    	    			<html:submit property="submitAction" onclick=" return loadDepartment('/<msp:webapp/>displayReleaseToJPSummary.do?submitAction=findDepartment', 'userDepartment');">
									<bean:message key="button.findDepartment"></bean:message>
								</html:submit>
							</td>	
			  			</tr>
	  			
	  					<tr id="byOfficerId" class=hidden>
			                <td class="formDeLabel" width=1% nowrap><bean:message key="prompt.1.diamond"/><bean:message key="prompt.officerIdNumber"/></td>
	    		            <td class="formDe"><html:text property="transferOfficerId" size="11" maxlength="11" onkeypress="clearDeptName()"/>
	                		</td>
	            		    <td class="formDeLabel" width=1% nowrap><bean:message key="prompt.1.diamond"/><bean:message key="prompt.officerIdType"/></td>
			                <td class="formDe">
				  	           <html:select property="transferOfficerIdType">
	   	        		           <html:option value=""><bean:message key="select.generic" /></html:option>  			            	  	
	                    		   <html:optionsCollection name="codeHelper" property="officerIdTypes" value="code" label="description" />
				                </html:select>  
				            </td>    
	            		 </tr>
	           	  
		            	<tr id="officerDept" class=hidden>
								<td class=formDeLabel valign="top" nowrap width=1%><bean:message key="prompt.1.diamond"/>
									<bean:message key="prompt.department" /> <bean:message key="prompt.code" />
								</td>
								<td class=formDe colspan="3">
									<table width="100%" border="0" cellpadding=1 cellspacing=0>
										<tr>
											<td>                       		
												<html:text name="juvenileWarrantForm" property="transferOfficerDepartmentId" size="3" maxlength="3" />
											</td>
											<td>	
												<html:submit property="submitAction" onclick="return validateDepartment();">
													<bean:message key="button.validateDepartmentCode"></bean:message>
												</html:submit>
											</td>
										</tr>
										<tr>
										<td></td>
										<td>&nbsp;Or&nbsp;
											<a href="javascript:changeActionURL('juvenileWarrantForm', '/<msp:webapp/>displayDepartmentSearch.do', true);"><bean:message key="prompt.searchForDepartments" /></a>
										</td>
									</tr>
								</table>	
							</td>
					</tr>
				<tr>
	                <td class="formDeLabel"><bean:message key="prompt.department"/> <bean:message key="prompt.name"/></td>
	                <td class="formDe" colspan="3" id="deptName"><bean:write name="juvenileWarrantForm" property="transferOfficerDepartmentName"/></td>  
            	</tr>	 
        
				<tr> 
	                <td class="formDeLabel"><bean:message key="prompt.1.diamond"/><bean:message key="prompt.transferDate"/></td>          
	                <td class="formDe" colspan=3>
	                	<html:text property="transferCustodyDateString" size="10" maxlength="10" styleId="transDate"/>
		            </td>
		         </tr>
		         <tr>    
		               <td class="formDeLabel"><bean:message key="prompt.1.diamond"/><bean:message key="prompt.transferTime"/></td>          
		               <td class="formDe" colspan=3><html:text property="transferCustodyTimeString" size="5" maxlength="5"/> (hh:mm)</td>
		         </tr>
		         <tr>    
		               <td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.1.diamond"/><bean:message key="prompt.transferLocation"/></td>
		               <td class="formDe" colspan="3">
			  	           <html:select property="transferLocationId">
		   	                   <html:option value=""><bean:message key="select.generic" /></html:option>  			            	  	
		                       <html:optionsCollection name="codeHelper" property="transferLocations" value="code" label="description" />
			                </html:select>  
				      </td>    
				</tr>
			</table>
			<br>         
<!-- END TRANSFER INFORMATION TABLE -->
  </table>
  <br>       

<!-- BEGIN BUTTON TABLE -->
<table width="98%" border="0" align="center"> 
	<tr valign="top">
	  <td align="right" width="55%">
		<logic:notEmpty name="juvenileWarrantForm" property="warrants">
	         <html:button
				property="org.apache.struts.taglib.html.BUTTON"
				onclick="history.go(-1);">
				<bean:message key="button.back"></bean:message>
			 </html:button>&nbsp;
		</logic:notEmpty>
	    <html:submit property="submitAction" onclick="return validateFields(this.form)  &&  checkReleaseDate(this.form)"><bean:message key="button.next"></bean:message></html:submit>&nbsp;
		<html:reset />&nbsp;
	  </td>
	</html:form>
	<!-- END 1st FORM -->
	  <td width="55%" align="justify" colspan="1">	
 		<html:form action="/displayGenericSearch.do?warrantTypeUI=releaseToJP"> 			  
			<html:submit property="submitAction">
				<bean:message key="button.cancel"></bean:message>
			</html:submit>
		</html:form>    
	 </td>
	</tr>
</table>
<!-- END BUTTON TABLE -->


<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>