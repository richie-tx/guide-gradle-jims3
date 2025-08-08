<!--MODIFICATIONS -->
<!-- Used for Arrest, Directive To Apprehend and Probable Cause Warrants where info is retrieved from JOT -->
<%-- CShimek	05/19/2004	Create JSP --%>
<%-- LDeen		06/15/2004	Revised per SUC Ver 1.7 --%>
<%-- CShimek    08/05/2005  Revised caution and caution comments to match caseworks style --%>
<%-- CShimek    08/08/2005  Revised Law Enforcement Block required field indicators --%>
<%-- JFisher    08/23/2005  Update form fields to reflect use of java.util.Date instead of String --%>
<%-- DGibler    08/31/2005  Added logonId to officer info --%>
<%-- CShimek    10/04/2005  ER#24012 change assocaite labels to responsible adult --%>
<%-- CShimek    01/06/2006  Made SSN editable per ER resulting from defect #21604 --%>
<%-- CShimek    02/01/2006  Added and Revised hidden field helpFile for each warrant type --%>
<%-- LDeen	    03/07/2006  Added width="1%" to juvenile number-2nd column --%>
<%-- RYoung	    04/07/2006  Justin helped me fixed the check box for selected charges --%>
<%-- CShimek    06/19/2006  Defect#32443 Revised max length of affidavit from 32765 to 30000 to fit data table --%>
<%-- HRodriguez 09/21/2006  ER#35269 Revised Law Enforcement Information section. --%>
<%-- HRodriguez 11/08/2006  Defect#36522 Reset other fields when user switch Search By 
     fields and display OfficerId after clicking back. --%>
<%-- CShimek    12/13/2006  Correct problem of single charge not being automatically selected, used Steven suggested code --%>     
<%-- CShimek    12/21/2006  Revised helpfile reference value--%>
<%-- CShimek    01/09/2007  #37769 corrected js to not clear affidavitt field when search by changes. --%>
<%-- CShimek    01/24/2007  #38536 revised evalSearch() to edit selectedCautions "OTHER" field to be checked. --%>
<%-- CShimek    03/01/2007  #40011 and 39975 made quick fix to disable hyperlinks on Resp. Adults. Okayed by QA.  ER to come for hyperlink to go to pop-up --%>
<%-- CShimek    03/05/2007  #40011 and #39975 revised hyperlink to use same code as JJS create warrant which solves the loss of data entered problem - still need ER change --%>
<%-- CShimek    03/14/2007  #39975 corrected problem of disappearing dept. Name after user selected RA to view and returned to this page --%>
<%-- CShimek    03/15/2007  #40135 added required field marker to Search By field --%>
<%-- CShimek   	03/23/2007  #40475 revised js calls for height and weight to use new script  --%>
<%-- CShimek 	04/11/2007  #41031 revised RA href to open in new window --%>
<%-- LDeen		06/04/2007  #42564 changed path to app.js --%>
<%-- LDeen		06/13/2007  #42564 added include for showHide.js --%>
<%-- RYoung		07/20/2007  #39039 changed ncicNum back to offenseCodeId --%>
<%-- RCapestani	08/08/2007	#43487 commented Juvenile Phone number out so it is not captured in the initiate warrants --%>
<%-- CShimek	08/15/2007  #44481 added agencyName hidden field as part of focus setting in js --%>
<%-- RCapestani	03/12/2008	#49409 modified to limit and check affidavit size to 3200 characters  --%>
<%-- DWilliamson 04/17/2008 #51016 Increase Officer ID field to 11. --%>
<%-- RYoung     08/05/2015  #27796 MJCW:  Adapt MJCW and Warrants to IE10 and 11 (Juv Profile - Education UI) --%>

<!DOCTYPE HTML>

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<!-- SCRIPTING VARIABLES -->
<jsp:useBean id="codeHelper" scope="application" class="ui.juvenilewarrant.helper.JuvenileWarrantListHelper"/>

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
<title><bean:message key="title.heading"/> - warrantJOTCreate.jsp</title>

<!-- STRUTS JAVASCRIPT VALIDATION -->
<html:javascript formName="juvenileWarrantForm" />

<!-- JAVASCRIPT FILES-->
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/showHideFunctions.js"></script>
<tiles:insert page="../js/warrantJOTCreate.js" />

<script>
var warrantTypeSc = '<bean:write name="juvenileWarrantForm" property="warrantTypeUI"/>';
</script>

</head>

<!--BEGIN BODY TAG-->
<body  onload="javascript:evalSearch(document.forms[0],false),checkHeightWeightInputs();" && onKeyDown="checkEnterKey(event,true)">
   <html:hidden name="juvenileWarrantForm" property="action" styleId="hiddenVal"/>
<html:form action="/displayJuvWarrantSummary"  target="content" >
  	<logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="arr">
	    <input type="hidden" name="helpFile" value="juvwarnt/juvenile_warrants_final.htm#|57">
   	</logic:equal>
   	<logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="dta">
	   <input type="hidden" name="helpFile" value="juvwarnt/juvenile_warrants_final.htm#|53">
   	</logic:equal>
   	<logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="pc">
	   <input type="hidden" name="helpFile" value="juvwarnt/juvenile_warrants_final.htm#|63">
   	</logic:equal>

<input type="hidden" name="warrantType" value="" />

<!-- BEGIN HEADING TABLE -->
 <table align="center" >
    <tr>
      <td align="center" class="header">
          <logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="arr">
            Initiate <bean:message key="title.juvWarrantArrest"/>
          </logic:equal>
          <logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="dta">
           Initiate  <bean:message key="title.juvWarrantDTA"/>
          </logic:equal>
          <logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="pc">
           Initiate <bean:message key="title.juvWarrantPC"/>
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
          <li>Information is retrieved from the Juvenile Offender Tracking (JOT) system.</li>
	      <li>Clicking on Existing Responsible Adult name displays full address information.</li>
	      <li>Make changes as needed and select Next button to view summary. </li>
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
<table width='98%' border=0 cellspacing=1 cellpadding=4 align=center>
<!-- BEGIN WARRANT INFORMATION BLOCK -->
	<tr>
	 	<td class=detailHead colspan=4 nowrap><bean:message key="prompt.juvenileWarrantInfo" /></td>
	</tr>
    <tr>
        <td class=formDeLabel nowrap><bean:message key="prompt.transactionNumber"/></td>
        <td class=formDe><bean:write name="juvenileWarrantForm"  property="transactionNum"/></td>
        <td class=formDeLabel nowrap width='1%'><bean:message key="prompt.juvenileNumber"/></td>
        <td class=formDe>             
           <bean:write name="juvenileWarrantForm" property="juvenileNum"/>
        </td>
     </tr>
     <tr>
        <td class=formDeLabel><bean:message key="prompt.daLogNumber"/></td>
        <td colspan=3 class=formDe><bean:write name="juvenileWarrantForm" property="daLogNum"/></td>
     </tr>
<!-- END JUVENILE WARRANT INFORMATION BLOCK -->
<tr><td><br></td></tr>
<!-- BEGIN CHARGE INFORMATION BLOCK -->
	<tr>
	   <td colspan=4>
		  <table width='100%' cellpadding=4 cellspacing=1 border=0> 
			<tr>
	 			<td class=detailHead colspan=3 nowrap><bean:message key="prompt.chargeInfo"/></td>
			</tr>
			<logic:empty name="juvenileWarrantForm" property="charges">
				<tr>
					<td class=formDe colspan=3>No Charges Found</td>
				</tr>
				<tr><td><br></td></tr>				
			</logic:empty>			
			<logic:notEmpty name="juvenileWarrantForm" property="charges">
	            <logic:iterate id="chargeIndex" name="juvenileWarrantForm" property="charges"> 
    	            <tr value="offenseCodeId" >    
			         <td align=center class=formDeLabel width='1%'>Select</td>
		    	     <td class=formDeLabel><bean:message key="prompt.charge" /></td>
    	        	 <td class=formDe><bean:write name="chargeIndex" property="offense"/></td>
		           </tr>
		           <tr>
	    	          <td align="center" class=formDeLabel>
            	       <%--<input type="checkbox" name="selectedCharges" value="<bean:write name="chargeIndex" property="sequenceNum"/>" <logic:equal name="juvenileWarrantForm" property="onlyCharge" value="true">checked</logic:equal>>
                	 	--%>
                		<bean:define id="seqVal" name="chargeIndex" property="sequenceNum"/>
                		
                	 	<html:multibox property="selectedCharges"><bean:write name="seqVal"/></html:multibox>
                	  </td>  
			          <td class=formDeLabel width='1%' nowrap><bean:message key="prompt.petitionNumber"/></td>
   					  <td class=formDe><bean:write name="chargeIndex" property="petitionNum"/></td>
   					</tr>
	    	       <tr>
      				  <td class=formDeLabel></td>
      				  <td class=formDeLabel><bean:message key="prompt.chargeCourt"/></td>
      			  	<td class=formDe><bean:write name="chargeIndex" property="court"/></td>
	   				</tr>
					<tr>
 						<td class=formDeLabel></td> 
     					<td class=formDeLabel nowrap><bean:message key="prompt.chargeNCICNumber"/></td> 					    				  				
       					<td class=formDe><bean:write name="chargeIndex" property="offenseCodeId"/></td>
	   				</tr>
	   				<tr>
 						<td class=formDeLabel></td> 
     					<td class=formDeLabel nowrap><bean:message key="prompt.offenseDate"/></td> 					    				  				
       					<td class=formDe><bean:write name="chargeIndex" property="offenseDate" formatKey="date.format.mmddyyyy"/></td>
	   				</tr>
					<tr><td><br></td></tr>	   				
		       </logic:iterate> 
				<logic:equal name="juvenileWarrantForm" property="onlyCharge" value="true">
					<script>document.forms[0].selectedCharges.checked = true;</script>
				</logic:equal>		       
	       </logic:notEmpty>
         </table>
       </td>
    </tr>
<!-- END CHARGE INFORMATION BLOCK -->

<!-- BEGIN SUMMARY OF FACTS BLOCK -->	
	<tr>
	 	<td class=detailHead colspan=4 nowrap><bean:message key="prompt.summaryOfFactsInfo" /></td>
	</tr>
    <logic:equal name="juvenileWarrantForm" property="summOfFactsDisplaySize" value="Partial">
	    <tr>
		    <td class=formDe colspan=4>
                    <bean:write name="juvenileWarrantForm" property="first4SummaryOfFact" />
            </td>
        </tr>
        <tr>
        	<td class=formDe colspan=4><a href="/<msp:webapp/>displaySummaryOfFacts.do">More...</a></td>
        </tr>	
    </logic:equal>
    <logic:equal name="juvenileWarrantForm" property="summOfFactsDisplaySize" value="None">
   	    <tr>
		    <td class=formDe colspan=4>&nbsp;</td>
		</tr>    
    </logic:equal> 
    <logic:equal name="juvenileWarrantForm" property="summOfFactsDisplaySize" value="Full"> 
       <tr>
	      <td class=formDe colspan=4>
             <bean:write name="juvenileWarrantForm" property="completeSummaryOfFact" />
          </td>
       </tr>                                                 
    </logic:equal> 

<!-- END SUMMARY OF FACTS BLOCK -->
<tr><td><br></td></tr>

<!-- BEGIN JUVENILE INFORMATION BLCOK -->
	<tr>
		<td colspan=4 class=detailHead nowrap><bean:message key="prompt.juvenileInfo" /></td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.juvenileName"/></td>
		<td class=formDe colspan=3>
			<bean:write name="juvenileWarrantForm" property="juvenileName.lastName"/>, 
			<bean:write name="juvenileWarrantForm" property="juvenileName.firstName"/>
			<bean:write name="juvenileWarrantForm" property="juvenileName.middleName"/> 
			<bean:write name="juvenileWarrantForm" property="nameSuffix"/>
		</td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.aka"/></td>
		<td class=formDe colspan=3>
			<logic:equal name="juvenileWarrantForm" property="aliasName" value="">&nbsp;</logic:equal>                
			<bean:write name="juvenileWarrantForm" property="aliasName"/>
		</td>
	</tr>
	<tr>
	<logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="dta">
		<td class=formDeLabel><bean:message key="prompt.dateOfBirth"/></td>
		<td class=formDe>
			<bean:write name="juvenileWarrantForm" property="dateOfBirth" formatKey="date.format.mmddyyyy" />
		</td>
   		<td class=formDeLabel width="1%" nowrap><bean:message key="prompt.mjw.diamond"/><bean:message key="prompt.age"/>&nbsp;<bean:message key="prompt.verifiedBy"/></td>
			<td class=formDe>
				<html:text property="dateOfBirthSource" size="25" maxlength="40" />
			</td>
    </logic:equal>
	<logic:notEqual name="juvenileWarrantForm" property="warrantTypeUI" value="dta">
		<td class=formDeLabel><bean:message key="prompt.dateOfBirth"/></td>
		<td class=formDe colspan="3">
			<bean:write name="juvenileWarrantForm" property="dateOfBirth" formatKey="date.format.mmddyyyy" />
		</td>
    </logic:notEqual>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.build"/></td>
		<td class=formDe colspan="3">
			<html:select property="buildId">
				<html:option value=""><bean:message key="select.generic" /></html:option>
				<html:optionsCollection name="codeHelper" property="builds" value="code" label="description" /> 
			</html:select> 
		</td>
	</tr>
	<tr>
		<td class=formDeLabel width='1%' nowrap><bean:message key="prompt.ssn"/></td>
		<td class=formDe colspan=3>                             	             
			<html:text property="SSN1" size="3" maxlength="3" onkeyup="return autoTab(this, 3);"/>-
			<html:text property="SSN2" size="2" maxlength="2" onkeyup="return autoTab(this, 2);"/>-
			<html:text property="SSN3" size="4" maxlength="4" onkeyup="return autoTab(this, 4);"/>			
		</td> 
	</tr>
<%--	#43487 comment Juvenile Phone number out so it is not captured in the initiate warrants
 	<tr>	 
		<td class=formDeLabel><bean:message key="prompt.phone"/></td> 
		<td class=formDe colspan=3>
			<html:text property="phoneNum.areaCode" size="3" maxlength="3" onkeyup="return autoTab(this, 3);" /> -
			<html:text property="phoneNum.prefix" size="3" maxlength="3" onkeyup="return autoTab(this, 3);" /> -
			<html:text property="phoneNum.4Digit" size="4" maxlength="4" onkeyup="return autoTab(this, 4);" />  
		</td>
	</tr>
--%>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.race"/></td>
		<td class=formDe>                        
			<bean:write name="juvenileWarrantForm" property="race"/>
		</td>
		<td class=formDeLabel width='1%'><bean:message key="prompt.sex"/></td>
		<td class=formDe>                            
			<bean:write name="juvenileWarrantForm" property="sex"/>
		</td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.height"/></td>
		<td class=formDe>
			<html:text property="heightFeet" size="1" maxlength="1"/> ft
			<html:text property="heightInch" size="2" maxlength="2"/> in
		</td>
		<td class=formDeLabel width='1%'><bean:message key="prompt.weight"/></td>
		<td class=formDe><html:text property="weight" size="3" maxlength="3"/> lbs</td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.eyeColor"/></td>
		<td class=formDe colspan="3">
			<html:select property="eyeColorId">
				<html:option value=""><bean:message key="select.generic" /></html:option>  	                	  	
				<html:optionsCollection name="codeHelper" property="eyeColors" value="code" label="description" />
			</html:select> 
		</td> 
	</tr>
	<tr>   
		<td class=formDeLabel><bean:message key="prompt.hairColor"/></td>
		<td class=formDe colspan="3">
			<html:select property="hairColorId">
				<html:option value=""><bean:message key="select.generic" /></html:option>  	                	  	
				<html:optionsCollection name="codeHelper" property="hairColors" value="code" label="description" />
			</html:select>  
		</td> 
	</tr> 
	<tr>
		<td class=formDeLabel><bean:message key="prompt.complexion"/></td>
		<td class=formDe colspan="3">
			<html:select property="complexionId">
				<html:option value=""><bean:message key="select.generic" /></html:option>  	                	  	
				<html:optionsCollection name="codeHelper" property="complexions" value="code" label="description" />
			</html:select> 
		</td>
	</tr>
	<%-- JMF - 3/20/2006 - Category not being persisted - JIMS200029289 --%>
	<tr>
		<td valign="top" class=formDeLabel><bean:message key="prompt.scarsMarks"/></td>
		<td class=formDe colspan=3>
			<html:select property="selectedScars" size="10" multiple="true">
				<html:optionsCollection property="scarsMarksCodes" value="code" label="description" />
			</html:select>    
		</td>
	</tr>
	<tr>
		<td valign="top" class=formDeLabel><bean:message key="prompt.tattoos"/></td>
		<td class=formDe colspan=3>
			<html:select property="selectedTattoos" size="10" style="width:220;" multiple="true">
				<html:optionsCollection property="tattoosCodes" value="code" label="description" />
			</html:select> 
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
		<td class=formDeLabel colspan=4 valign="top"><bean:message key="prompt.cautions"/></td>
	</tr>
		<INPUT type="hidden" name="cautionCheckboxClear" value="true" />
		<%-- need to work on getting 2 checkboxes per row --%>
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
	   <td class=formDe colspan=4><html:multibox property="selectedCautions" onclick="return showOtherCaution(this.checked)">
					              OT 
			                  </html:multibox>OTHER</td>
	</tr>
	<tr id="otherCaution" class=hidden>
		<td class=formDeLabel colspan=4 ><bean:message key="prompt.otherCautionComments"/></td>
	</tr>
	<tr id="otherCaution2" class=hidden>   
		<td class=formDe colspan=4 >
			<html:textarea property="cautionComments" style="width:100%" rows="5" onkeypress="return textCounter(this.form.cautionComments,100);"></html:textarea>
		</td>
	</tr>
<!-- END JUVENILE INFORMATION TABLE -->
<tr><td><br></td></tr> 
<!-- BEGIN EXISTING RESPONSIBLE ADULT INFORMATION TABLE -->
	<tr>
	 	<td colspan=4 class=detailHead nowrap><bean:message key="prompt.juvenileAssociateInformation" /></td>
	</tr>
     <tr>
        <td class=formDeLabel colspan=2 width='1%'><bean:message key="prompt.name"/></td>
        <td class=formDeLabel colspan=2><bean:message key="prompt.relationshipToJuvenile"/></td>
    </tr>
    <logic:empty name="juvenileWarrantForm" property="associates"> 
    	<tr>
        	<td class=formDe colspan=2>No Responsible Adults Found</td>
            <td class=formDe colspan=2>&nbsp;</td>
        </tr> 
     </logic:empty>    
<%-- not required as part of interation #7   ?associateNumber=10--%> 
<!-- VARIABLES NEEDED FOR DISPLAY -->
<% int RecordCounter = 0; 
   String bgcolor = ""; %>
     <logic:notEmpty name="juvenileWarrantForm" property="associates">   
        <logic:iterate id="associate" name="juvenileWarrantForm" property="associates"> 
           <% RecordCounter++;
              bgcolor = "class=formDe";
              if (RecordCounter % 2 == 1)
                 bgcolor = ""; %>
           <tr>
<%-- field commented out as quick fix for defect #40011 and 39975 for deployment. ER needs to be made to use pop-up window like what is done in MJCW --%>           
           		<td <% out.print(bgcolor); %> colspan=2 align="left">
       				<a href="javascript:openWindow('/<msp:webapp/>displayJuvWarrantSummary.do?submitAction=Link&relationshipId=<bean:write name="associate" property="relationshipToJuvenileId"/>&warrantType=<bean:write name="juvenileWarrantForm" property="warrantTypeUI"/>');">	          		
               		<bean:write name="associate" property="associateName.lastName" />, <bean:write name="associate" property="associateName.firstName" /> <bean:write name="associate" property="associateName.middleName" /></a>
               	</td>
				<td <% out.print(bgcolor); %> colspan=2 nowrap align="left"><bean:write  name="associate" property="relationshipToJuvenile" /></td>			 
           </tr>
         </logic:iterate> 
      </logic:notEmpty> 
<!-- END EXISTING RESPONSIBLE ADULT INFORMATION TABLE -->
<tr><td><br></td></tr>
<!-- BEGIN LAW ENFORCEMENT INFORMATION TABLE -->
	<tr>
	 	<td class=detailHead colspan=4 nowrap><bean:message key="prompt.lawEnforcementInfo" /></td>
	</tr>
	<!-- BEGIN INQUIRY TABLE -->
	<tr>
		<td colspan="4">
			<table align="center" width="100%" border="0" cellpadding="2" cellspacing="1" class=borderTableBlue>
				<tr>
					<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.1.diamond"/><bean:message key="prompt.searchBy" /></td>
					<td class="formDe">
						<html:select name="juvenileWarrantForm" property="search" onchange="evalSearch(this.form,true)">
						  <html:option value="">Please Select</html:option>
						  <html:option value="userSearch">USER ID</html:option>
						  <html:option value="officerSearch">OFFICER ID</html:option>			 
						</html:select>						
					</td>
					<td><html:hidden name="juvenileWarrantForm" property="officerAgencyName" />	</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td colspan="4">
			<table align="center" width="100%" border="0" cellpadding="2" cellspacing="1" class=borderTableBlue>
				<tr id="byUserId" class=hidden>
	        		<td class=formDeLabel><bean:message key="prompt.1.diamond"/><bean:message key="prompt.userId"/></td>
	        		<td class=formDe colspan="3">
	        			<html:text property="userId" size="10" maxlength="5" onchange ="clearDeptName()" onkeypress="clearDeptName()"/>
	        			<html:submit property="submitAction" onclick=" return loadDepartment('/<msp:webapp/>displayJuvWarrantSummary.do?submitAction=findDepartment', 'userDepartment');">
							<bean:message key="button.findDepartment"></bean:message>
						</html:submit>
						<html:hidden name="juvenileWarrantForm" property="officerAgencyId" />
					</td>
	  			</tr>
  			
	  			<tr id="byOfficerId" class=hidden>
	                <td class="formDeLabel" width='1%' nowrap><bean:message key="prompt.1.diamond"/><bean:message key="prompt.officerIdNumber"/></td>
	                <td class="formDe"><html:text property="officerId" size="11" maxlength="11"/></td>
	                <td class="formDeLabel"><bean:message key="prompt.1.diamond"/><bean:message key="prompt.officerIdType"/></td>
	                <td class="formDe">
	                	<html:select property="officerIdTypeId">
	               			<html:option value=""><bean:message key="select.generic" /></html:option>  			            	  	
	               			<html:optionsCollection name="codeHelper" property="officerIdTypes" value="code" label="description" />
	           			</html:select></td>    
	            </tr>
           	  
	        	<tr id="officerDept" class=hidden>
					<td class=formDeLabel valign="top" nowrap width='1%'><bean:message key="prompt.1.diamond"/>
							<bean:message key="prompt.department" /> <bean:message key="prompt.code" />
					</td>
					<td class=formDe colspan="3">
						<table width="100%" border="0" cellpadding=1 cellspacing=0>
							<tr>
								<td>                       		
									<html:text name="juvenileWarrantForm" property="officerAgencyId" size="3" maxlength="3" />
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
									<a href="javascript:changeFormActionURL('juvenileWarrantForm', '/<msp:webapp/>displayDepartmentSearch.do', true);"><bean:message key="prompt.searchForDepartments" /></a>
								</td>
							</tr>
						</table>	
					</td>
				</tr>
				<tr>
	                <td class="formDeLabel" nowrap width='1%'><bean:message key="prompt.department"/> <bean:message key="prompt.name"/></td>
	                <td class="formDe" colspan="3" id="deptNameTd"><bean:write name="juvenileWarrantForm" property="officerAgencyName"/></td>  
	        	</tr>
	        	<tr>
			       <td class=formDeLabel colspan=4 nowrap><bean:message key="prompt.1.diamond"/><bean:message key="prompt.affidavitStatement"/></td>
			    </tr>
			    <tr>   
			       <td class=formDe colspan=4> 
			           <html:textarea property="affidavitStatement" style="width:100%" rows="5" onkeypress="return textCounter(this.form.affidavitStatement,3200);"></html:textarea>
				   </td>
			    </tr>
			</table>
       </td>
   </tr> 
<!-- END LAW ENFORCEMENT INFORMATION TABLE -->
<tr><td><br></td></tr>
<!-- BEGIN WARRANT ORIGINATOR INFORMATION TABLES -->
	<tr>
	 	<td class=detailHead colspan=4 nowrap><bean:message key="prompt.warrantOriginatorInfo" /></td>
	</tr>
    <tr>
       <td class=formDeLabel ><bean:message key="prompt.warrantOriginatorName"/></td>
       <td class=formDe colspan=3><bean:write name="juvenileWarrantForm" property="warrantOriginatorName"/></td> 
    </tr>
    <tr>
       <td class=formDeLabel><bean:message key="prompt.warrantOriginatorDepartmentName"/></td>
       <td class=formDe colspan=3><bean:write name="juvenileWarrantForm" property="warrantOriginatorAgencyName"/></td> 
    </tr>
</table>
<!-- END WARRANT ORIGINATOR INFORMATION  TABLES -->
  <br> 
<!-- BEGIN BUTTON TABLE -->
<table width="98%" border="0" align="center">
	 <tr valign="top">
	        <td align="right" width="45%">
	          <html:button property="submitAction" onclick="return (alertTextAreaToLarge(this.form.cautionComments,100,'Other Caution Comments') && alertTextAreaToLarge(this.form.affidavitStatement,3200,'Affidavit Statement') && validateJuvenileFields(this.form) && validateJuvenileWarrantForm(this.form) && checkBoxEdits(this.form) && validateLawEnforcementInfo(this.form) && validateDOBSource(warrantTypeSc,this.form)) && disableSubmit(this, this.form);">
	                <bean:message key="button.next"></bean:message>
	          </html:button>&nbsp;
		      <html:reset>
		         <bean:message key="button.reset"></bean:message>
              </html:reset>&nbsp;
		    </td>
		    <td align="left">
     </html:form>	
        <logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="arr">	
		<html:form action="/displayJOTSearch.do?warrantTypeUI=arr"> 
             <html:submit>
                <bean:message key="button.cancel"></bean:message>
             </html:submit>
        </html:form> 
		</logic:equal>
        <logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="dta">	
		<html:form action="/displayJOTSearch.do?warrantTypeUI=dta"> 
             <html:submit>
                <bean:message key="button.cancel"></bean:message>
             </html:submit>
        </html:form> 
		</logic:equal>
        <logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="pc">	
		<html:form action="/displayJOTSearch.do?warrantTypeUI=pc"> 
             <html:submit> 
                <bean:message key="button.cancel"></bean:message>
             </html:submit>
        </html:form> 
		</logic:equal>
	 </td>
	 </tr>
</table>
<!-- END BUTTON TABLE -->
<%-- /html:form --%>	
<!-- END FORM -->

<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
