<!DOCTYPE HTML>
<!-- User selects the 'Create HCJDP' link on the left UI Navigation -->
<!--MODIFICATIONS -->
<!-- 05/24/2006 Uma Gopinath Create JSP -->
<!-- 08/28/2006  Hien Rodriguez  ER#34507 Implement new UI Guidelines for all date fields -->
<!-- 09/20/2006 Uma Gopinath Update/Inactivate flow added for ASP part 2-->
<!-- 09/11/2007 C. Shimke		 #45035 remove extraneous commas from instruction -->
<!-- 10/14/2015 R. Capestani  #30717 MJCW: IE11 conversion of HCJPD link on UILeftNav (UI - Service Provider-Juvenile link) -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>

<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>


<!--CUSTOM LIBRARIES NEEDED FOR PAGE -->
<%@ taglib uri="/WEB-INF/taglibs-datetime.tld" prefix="dt" %>

<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET --> 


<!--BEGIN HEADER TAG-->
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/> 

<msp:nocache />

<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">


<!-- STYLE SHEET LINK -->

<html:base />
<title><bean:message key="title.heading"/> - programCreateUpdate.jsp</title>
<html:javascript formName="programForm"/>

<!--JQUERY FRAMEWORK-->
<%@include file="../../jQuery.fw"%>

<link rel="stylesheet" type="text/css" href="/<msp:webapp/>/css/commonsupervision.css" />

<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/administerServiceProviderHCJPD/programCreate.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/case_util.js"></script>

<script type="text/javascript">

$(function() {
	
	if(typeof $("#startDate")!= "undefined"){			
		datePickerSingle( $("#startDate"),"Start Date", false);		 
	}
	if(typeof $("#endDate")!= "undefined"){			
		datePickerSingle( $("#endDate"),"End Date", false);		 
	}
	if(typeof $("#fundStartDate")!= "undefined"){			
		datePickerSingle( $("#fundStartDate"),"Fund Start Date", false);		 
	}
	
	//Discontinue Date
	if(typeof $("#discontinueDate") != "undefined"){
		datePickerSingle($("#discontinueDate"),"Discontinue Date",false);
	}
	
	var currentFundStartDate =  new Date('<bean:write name="serviceProviderForm" property="currentProgram.fundStartDate"/>');
	
	
	$("#programFundSourceCd").on("change",function(){
		if($("#actionType").val()!="undefined"  && $("#actionType").val() == "updateProgram")
		{			
		//	var newListing = "<bean:write name='serviceProviderForm' property='currentProgram.currentSourceFund.programSourceFundId'/>";
			var myStartDate="<bean:write name='serviceProviderForm' property='currentProgram.startDate'/>";			
			if(typeof $("#startDate").val()!="undefined")
				$("#fundStartDate").val($("#startDate").val());	
			else
				$("#fundStartDate").val(myStartDate);
			return false;
		}
		
	});
	$("#startDate").on("change", function(){
		
		var oldListing = "<bean:write name='serviceProviderForm' property='currentProgram.currentSourceFund.programSourceFundCd'/>";
		var newListing = $("#programFundSourceCd").val();
		if(oldListing!=newListing)
		{
					
			$("#fundStartDate").val($("#startDate").val());	
			return false;
		}
	});
	$("#pgmId").on("change", function(){
		
		var numRegExp = /^[0-9]*$/;
		var pgm=$("#pgmId").val();
		if (numRegExp.test(pgm,numRegExp) == false){
			alert("Contract Database OID must be numeric.");
			$("#pgmId").focus();
			$("#pgmId").val("");
			return false;
		}
	});
	
	/*$("#endDate").on("change", function(){
		var oldListing = "<bean:write name='serviceProviderForm' property='currentProgram.currentSourceFund.programSourceFundCd'/>";		
		if(oldListing != null || oldListing != "")
			$("#fundEndDate").val($("#endDate").val());	
		
	});*/
	
	/* $("#nextId").on("click", function(){
		var oldListing = "<bean:write name='serviceProviderForm' property='currentProgram.currentSourceFund.programSourceFundCd'/>";
		var newListing = $("#programFundSourceCd").val();
		if(typeof $("#startDate")== "undefined" || $("#startDate").val()==null){
			//alert(thisForm["currentProgram.fundStartDate"].value);
			if($("#fundStartDate").val() != ""
					&& $("#actionType").val() == "updateProgram" )
			{
				
				var fundStartDate= new Date($("#fundStartDate").val());
				var myStartDate='<bean:write name="serviceProviderForm" property="currentProgram.startDate"/>';	
				var progStartDate = new Date(myStartDate);
				
				if(progStartDate > fundStartDate)
				{
					  alert("Fund Start Date cannot be before Program Start date.");
					  $("#fundStartDate").focus();
					  return false;
				}
				
				
				
				
			}
		}
		
		if(oldListing!=newListing)
		{
			if($("#fundStartDate").val() != ""
				&& $("#actionType").val() == "updateProgram"  )
			{
				var fundStartDate= new Date($("#fundStartDate").val());

				if ( fundStartDate.getTime() <=  currentFundStartDate.getTime() ) {
					
					alert("New Fund Start Date must be after the current active Fund Start Date.");
					  $("#fundStartDate").focus();
					  return false;
				}
				
			}
					
			
		}
	}); */
	

	var spform = document.forms[0];
	var transferredProgRef = spform["currentProgram.transferredProgRef"]; 
	var transferredProgRefYes = spform["transferredProgRefYes"]; 
	var transferredProgRefNo = spform["transferredProgRefNo"]; 
	var supervisionCategoriesContainer = document.getElementById("supervisionCategoriesContainer");
		
		if(transferredProgRefYes.checked === true){
			transferredProgRef.value = "1";
			supervisionCategoriesContainer.style.visibility = "visible";
		}			
		
		if(transferredProgRefNo.checked === true){
			transferredProgRef.value = "0";
			supervisionCategoriesContainer.style.visibility = "hidden";
		}
		

		transferredProgRefYes.addEventListener('change', function(){
				
			if(transferredProgRefYes.checked){
				transferredProgRef.value = "1";
				transferredProgRefNo.checked = false;
					
				supervisionCategoriesContainer.style.visibility = "visible";
			}			
				
		});

		transferredProgRefNo.addEventListener('change', function(){
				
			if(transferredProgRefNo.checked){
				transferredProgRef.value = "0";
				transferredProgRefYes.checked = false;
					
				supervisionCategoriesContainer.style.visibility = "hidden";
			}
				
		});
	
	
});

</script>
</head>
<!--END HEADER TAG-->


<!--BEGIN BODY TAG-->
<body topmargin='0' leftmargin="0" onKeyDown="checkEnterKey(event,true)">
<!-- BEGIN FORM -->
<html:form action="/displayJuvProviderProgramCreateUpdateSummary" target="content" focus="currentProgram.programName">


<logic:equal name="serviceProviderForm" property="actionType" value="addProgram">
  <input type="hidden" name="helpFile" value="commonsupervision/asp_hcjpd/Administer_Service_Provider.htm#|373">
</logic:equal>

<logic:equal name="serviceProviderForm" property="actionType" value="updateProgram">
  <input type="hidden" name="helpFile" value="commonsupervision/asp_hcjpd/Administer_Service_Provider.htm#|371">
</logic:equal>


<div align="center">
<table width="98%" border="0" cellpadding="0" cellspacing="0" >
  <tr>
    <td valign='top'><bean:message key="prompt.3.spacer"/></td>
  </tr>
  <tr>
    <td valign='top'>
      <table width='100%' border="0" cellpadding="0" cellspacing="0" >
        <tr>
          <td valign='top'>
            <!--tabs start-->
            <tiles:insert page="../../common/commonSupervisionTabs.jsp" flush="true">
              <tiles:put name="tabid" value="suggestedOrderTab"/>
            </tiles:insert>													
            <!--tabs end-->
          </td>
        </tr>
        <tr>
          <td bgcolor='#6699FF'><img src="/<msp:webapp/>images/spacer.gif" height='5'></td>
        </tr>			
      </table>

      <table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
        <tr>
          <td><bean:message key="prompt.3.spacer"/></td>
        </tr>
        <tr>
          <td valign='top' align='center'>
            <!-- BEGIN HEADING TABLE -->
            <table width='100%'>
              <tr>
                <td align="center" class="header">
                  <logic:equal name="serviceProviderForm" property="actionType" value="addProgram"><bean:message key="prompt.add"/></logic:equal>
                  <logic:equal name="serviceProviderForm" property="actionType" value="updateProgram"><bean:message key="prompt.update"/></logic:equal>
                  <bean:message key="prompt.program"/>
								</td>
              </tr>
            </table>
            <!-- END HEADING TABLE -->

            <!-- BEGIN INSTRUCTION TABLE -->
            <table width="98%" border="0" cellpadding='1' cellspacing='1'>
              <tr>
                <td>
                  <ul>
                    <li>Enter the required fields and click Next.</li>
                  </ul>
                </td>
              </tr>
              <tr>
                <td class="required"><bean:message key="prompt.3.diamond"/> <bean:message key="prompt.requiredFieldsInstruction" />&nbsp;&nbsp;&nbsp;<bean:message key="prompt.dateFieldsInstruction"/></td>
              </tr>
            </table>
                
           	<!-- BEGIN ERRORS TABLE -->
    				<table width="100%">
    					<tr>		  
    						<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
    					</tr>   	  
    				</table>
    				<!-- END ERRORS TABLE -->
				
            <!-- BEGIN  TABLE -->
            <table cellpadding='1' cellspacing='0' border='0' width='98%'>
  						<tr>
    						<td bgcolor='#cccccc'>
      						<table width='100%' border='0' cellpadding='2' cellspacing='1'>
        						<tr>
        							<td class="formDeLabel" width='1%' nowrap='nowrap'>Provider <bean:message key="prompt.name" /></td>
        							<td colspan='3' class="formDe"><bean:write name="serviceProviderForm" property="providerName"/></td>
        						</tr>
        						<tr>
        							<td class="formDeLabel"><bean:message key="prompt.start" /> <bean:message key="prompt.date" /></td>
        							<td class="formDe"><bean:write name="serviceProviderForm" property="startDate" formatKey="date.format.mmddyyyy"/></td>
        							<td class="formDeLabel" width='1%' nowrap='nowrap'><bean:message key="prompt.inHouse" /></td>
        							<td class="formDe"><bean:write name="serviceProviderForm" property="isInHouse"/></td>
        						</tr>
        					</table>
      					</td>
    					</tr>
  					</table>
				
            <br>
            <table width='98%' cellpadding='2' cellspacing='0' class='borderTableBlue'>
              <tr>
                <td class='detailHead'> <bean:message key="prompt.program"/> <bean:message key="prompt.info"/> </td>
              </tr>
              <tr>
                <td>
                  <table width='100%' cellpadding='2' cellspacing='1'>
                    	<tr>
                      		<td class='formDeLabel' nowrap='nowrap' width='1%'><bean:message key="prompt.3.diamond"/><bean:message key="prompt.name"/></td>
                      		<td class='formDe'><html:text name="serviceProviderForm" styleId="txtPgmname" property="currentProgram.programName" size="60" maxlength="100"/></td>
                      		<td class='formDeLabel' nowrap='nowrap' width='1%'>Maximum Youth In Program</td>
                     		<td class='formDe'><html:text name="serviceProviderForm" styleId="maxYouth" property="currentProgram.maxYouth" size="5" maxlength="2"/></td>
    					</tr>
    					<tr>
                      		 <td class='formDeLabel' nowrap='nowrap' width='1%'><bean:message key="prompt.3.diamond"/><bean:message key="prompt.targetIntervention"/></td>
                      <td class='formDe'>
                      	<!-- 
                        <logic:equal name="serviceProviderForm" property="currentProgram.statusId" value="P">
                          <html:select property="currentProgram.targetInterventionId" name="serviceProviderForm">
          									<html:option value=""><bean:message key="select.generic" /></html:option>  			            	  	
          									<html:optionsCollection property="targetInterventionList" value="code" label="description"  name="serviceProviderForm"/>
          								</html:select>  
          							</logic:equal>
          							<logic:notEqual name="serviceProviderForm" property="currentProgram.statusId" value="P">
          								 <bean:write name="serviceProviderForm" property="currentProgram.targetIntervention"/>
          							</logic:notEqual>       -->
          					  <html:select property="currentProgram.targetInterventionId" styleId="tarIntid" name="serviceProviderForm">
          									<html:option value=""><bean:message key="select.generic" /></html:option>  			            	  	
          									<html:optionsCollection property="targetInterventionList" value="code" label="description"  name="serviceProviderForm"/>
          								</html:select>  
                      </td>
	                    	<td class='formDeLabel' nowrap='nowrap' width='1%'>TJJD EDI Code</td>
                      		<td class='formDe'><html:text name="serviceProviderForm" styleId="tjjdEdiCode" property="currentProgram.tjjdEdiCode" size="10" maxlength="4"/></td>
                    	</tr>
                    
                    <tr>
                     <td class='formDeLabel' nowrap='nowrap' width='1%'><bean:message key="prompt.3.diamond"/><bean:message key="prompt.code" /></td>
	                      	<logic:equal name="serviceProviderForm" property="currentProgram.statusId" value="P">
	                        	<td class='formDe' ><html:text styleId="programCode" name="serviceProviderForm" property="currentProgram.programCode" size="10" maxlength="10"/></td>
							</logic:equal>
	                   	  	<logic:notEqual name="serviceProviderForm" property="currentProgram.statusId" value="P">
	                        	<td class='formDe'><bean:write name="serviceProviderForm" property="currentProgram.programCode"/></td>
	                    	</logic:notEqual>
                      <td class='formDeLabel' nowrap='nowrap' width='1%'>
	                    		Program Referral Transferable ?
	                    	</td>
	                    	<td class='formDe'>
	                    		<html:radio  name="serviceProviderForm" property="currentProgram.transferredProgRef" value="1" styleId="transferredProgRefYes" /> Yes
	                    		<html:radio  name="serviceProviderForm" property="currentProgram.transferredProgRef" value="0" styleId="transferredProgRefNo" /> No
	                    	</td>
	                    	<input id="transferredProgRef" type="hidden" value="<bean:write name="serviceProviderForm" property="currentProgram.transferredProgRef"/>" />
                    </tr>

                    <tr>
                      <td class='formDeLabel' width='1%' nowrap='nowrap'><bean:message key="prompt.3.diamond"/><bean:message key="prompt.state"/> <bean:message key="prompt.program"/> <bean:message key="prompt.code"/></td>
                      <td class='formDe'>
                      <!-- 
                        <logic:equal name="serviceProviderForm" property="currentProgram.statusId" value="P">
                          <html:select property="currentProgram.stateProgramCodeId" name="serviceProviderForm">
          								  <html:option value=""><bean:message key="select.generic" /></html:option>  			            	  	
          								  <html:optionsCollection property="stateProgramCodeList" value="code" label="description"  name="serviceProviderForm"/>
          								</html:select>
          							</logic:equal>
          							<logic:notEqual name="serviceProviderForm" property="currentProgram.statusId" value="P">
          								 <bean:write name="serviceProviderForm" property="currentProgram.stateProgramCode"/>
          							</logic:notEqual>		-->	
      					    <html:select property="currentProgram.stateProgramCodeId" styleId="statePgmcode" name="serviceProviderForm">
 								  <html:option value=""><bean:message key="select.generic" /></html:option>  			            	  	
 								  <html:optionsCollection property="stateProgramCodeList" value="code" label="description"  name="serviceProviderForm"/>
 								</html:select>						
                      </td>
                      	<td colspan="2" rowspan="4" class="formDe">
									<div id="supervisionCategoriesContainer">
										<logic:notEmpty name="serviceProviderForm" property="currentProgram.supervisionCategories">
											<div class='formDeLabel' style="width: 65%" >Supervision Category for Transfer</div>
											<html:select name="serviceProviderForm" property="currentProgram.selectedSupervisionCategories" size="6" multiple="true" styleId="selectedSupervisionCategories" >
												<html:option key="select.generic" value="" disabled="true" />
												<html:optionsCollection name="serviceProviderForm" property="currentProgram.supervisionCategories" value="code" label="description" />
											</html:select>
										</logic:notEmpty>
									</div>
					 </td>
                    </tr>	
                    <tr>
                      <td class='formDeLabel' width='1%' nowrap='nowrap'><bean:message key="prompt.3.diamond"/><bean:message key="prompt.program"/> <bean:message key="prompt.type"/> <bean:message key="prompt.code"/></td>
                      <td class='formDe'>                      
      					    <html:select property="currentProgram.typeProgramCodeId" styleId="typePgmcode" name="serviceProviderForm">
 								  <html:option value=""><bean:message key="select.generic" /></html:option>  			            	  	
 								  <html:optionsCollection property="typeProgramCodeList" value="code" label="description"  name="serviceProviderForm"/>
 								</html:select>						
                      </td>
                      
                    </tr>	                      	                      	                     
                    <tr>
                     
                      <td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.3.diamond"/><bean:message key="prompt.program"/> <bean:message key="prompt.start"/> <bean:message key="prompt.date"/></td>
                      <td class='formDe'>   
                     
                      <html:hidden  name="serviceProviderForm" property="actionType" styleId="actionType"/>
                       
                        <logic:equal name="serviceProviderForm" property="currentProgram.statusId" value="P"> 
                          <html:text name="serviceProviderForm" property="currentProgram.startDate" styleId="startDate" size="10" maxlength="10"/>
                          
                      	</logic:equal>
                      	  
                      	<logic:notEqual name="serviceProviderForm" property="currentProgram.statusId" value="P">
                      		
								<bean:write name="serviceProviderForm" property="currentProgram.startDate"/>
						</logic:notEqual>
					</td>	
					
                    </tr>
                     <tr> <!--//added for U.S #11099-->
                      <td class='formDeLabel'><bean:message key="prompt.3.diamond"/><bean:message key="prompt.createProgram"/></td>
                      <td class='formDe'>
                        <html:select property="currentProgram.programScheduleTypeId" styleId="createPgm" name="serviceProviderForm">
          					<html:option value=""><bean:message key="select.generic" /></html:option>  			            	  	
          					<html:optionsCollection property="programScheduleTypeList" value="code" label="description"  name="serviceProviderForm"/>
          				</html:select>  
                      </td>
                      
                    </tr>
                     <!--//added for U.S #11376-->
                    <tr>
                      <td class='formDeLabel'><bean:message key="prompt.3.diamond"/><bean:message key="prompt.program"/> <bean:message key="prompt.location"/></td>
                      <td colspan='3' class='formDe'>
                        <html:select property="currentProgram.programLocationCd" styleId="pgmLocation" name="serviceProviderForm">
          					<html:option value=""><bean:message key="select.generic" /></html:option>  			            	  	
          					<html:optionsCollection property="programLocationList" value="code" label="description"  name="serviceProviderForm"/>
          				</html:select>  
                      </td>
                    </tr>
                    <tr>
                      <td class='formDeLabel'><bean:message key="prompt.3.diamond"/><bean:message key="prompt.category"/>
                      <td class='formDe'>
                        <html:select property="currentProgram.programCategoryCd" styleId="pgmCategory" name="serviceProviderForm">
          					<html:option value=""><bean:message key="select.generic" /></html:option>  			            	  	
          					<html:optionsCollection property="programCategoryList" value="code" label="description"  name="serviceProviderForm"/>
          				</html:select>  
                      </td>
                      <logic:equal name="serviceProviderForm" property="actionType" value="updateProgram">
	                      <td class='formDeLabel' nowrap='nowrap' width="5%">Discontinue Date</td>
	                      <td class='formDe'>
	                        <html:text name="serviceProviderForm" property="currentProgram.discontinueDate" styleId="discontinueDate" size="10" maxlength="10"/>                       
	                      </td>
                      </logic:equal>
                      <logic:equal name="serviceProviderForm" property="actionType" value="addProgram">
	                      <td colspan='2' class='formDe' nowrap='nowrap'>&nbsp;</td>
                      </logic:equal>
                    </tr>
                    </tr>
                         <tr>
                      <td class='formDeLabel'><bean:message key="prompt.3.diamond"/><bean:message key="prompt.sourceFund"/></td>
                      <td class='formDe'>
                        <html:select property="currentProgram.programFundSourceCd" name="serviceProviderForm" styleId="programFundSourceCd">
          					<html:option value=""><bean:message key="select.generic" /></html:option>  			            	  	
          					<html:optionsCollection property="fundSourceList" value="code" label="description"  name="serviceProviderForm"/>
          				</html:select>  
                      </td>
                      <td class='formDeLabel' nowrap='nowrap' width="5%"><bean:message key="prompt.program"/> <bean:message key="prompt.end"/> <bean:message key="prompt.date"/></td>
                      <td class='formDe'>
                        <html:text name="serviceProviderForm" property="currentProgram.endDate" styleId="endDate" size="10" maxlength="10"/>                       
                      </td>
                    </tr>
                    <tr>                     
                      <td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.3.diamond"/><bean:message key="prompt.fund"/> <bean:message key="prompt.start"/> <bean:message key="prompt.date"/></td>
                      <td class='formDe'><html:text name="serviceProviderForm" property="currentProgram.fundStartDate" styleId="fundStartDate" size="10" maxlength="10"/></td>
                      <td class='formDeLabel' nowrap='nowrap' width="5%">Contract Database OID</td> <!-- bean:message key="prompt.contractId"/>    -->                                          
                      <td class="formDe"><html:text name="serviceProviderForm" property="currentProgram.programID" styleId="pgmId" size="6" maxlength="6" /><%-- <bean:write name="serviceProviderForm" pattern="^[0-9]*$" property="programID"/> --%></td>
					  
					  <!--<td class='formDeLabel' nowrap='nowrap' width="5%"><bean:message key="prompt.fund"/> <bean:message key="prompt.end"/> <bean:message key="prompt.date"/></td>
                      <td class='formDe'><html:text name="serviceProviderForm" property="currentProgram.fundEndDate" styleId="fundEndDate" size="10" maxlength="10"/></td>-->
                    </tr>
                    <tr>
                      <td class='formDeLabel' colspan='4'><bean:message key="prompt.3.diamond"/><bean:message key="prompt.description"/>
               					<tiles:insert page="../../common/spellCheckTile.jsp" flush="false">
               						<tiles:put name="tTextField" value="currentProgram.description"/>
               						<tiles:put name="tSpellCount" value="spellBtn1" />
             						</tiles:insert> 
                      </td>
                    </tr>
                    <tr>
                      <td class='formDe' colspan='4'><html:textarea rows="8" style="width:100%" name="serviceProviderForm" property="currentProgram.description" onkeyup="textCounter(this,1000)"></html:textarea></td>
                    </tr>
                  </table>
                </td>
              </tr>
            </table>

						<!-- BEGIN BUTTON TABLE -->
            <br>
            <table border="0">
  						<tr>
  							<td align="center">
    						  <html:submit property="submitAction"><bean:message key="button.back"></bean:message></html:submit>
   								<html:submit property="submitAction" onclick="return validateFields();" styleId="nextId"><bean:message key="button.next"></bean:message></html:submit>
   								<html:reset><bean:message key="button.reset"></bean:message></html:reset>
								
  						 		<logic:equal name="serviceProviderForm" property="actionType" value="updateProgram">
  						 			 <logic:equal name="serviceProviderForm" property="currentProgram.statusId" value="P">
  						 			 <jims2:isAllowed requiredFeatures="CS-ASP-INACTIVATEJUV">  
  										<html:submit property="submitAction">
  											<bean:message key="button.inactivate"></bean:message>
  										</html:submit>
  										</jims2:isAllowed> 										
  									</logic:equal>
  								</logic:equal>

  								<html:submit property="submitAction"><bean:message key="button.cancel"></bean:message></html:submit>
  							</td>
  						</tr>
            </table>
            <!-- END BUTTON TABLE -->
          </td>
        </tr>
       
      </table><br>
    </td>
  </tr>
  <logic:notEmpty name="serviceProviderForm" property="currentProgram.funds">
  	 <tr>
        	<td>
        		  <table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
        		  	 <tr class=detailHead>
                          <td>Program Source Fund History</td>
                     </tr>
                     <tr>
                          <td>
                            <table width="100%" cellpadding="2" cellspacing="1" align="center">
                              <tr class=formDeLabel>
	                                <td valign=top><bean:message key="prompt.entryDate" /></td>
	                                <td valign=top><bean:message key="prompt.sourceFund"/></td>
	                                <td valign=top><bean:message key="prompt.fund"/> <bean:message key="prompt.start"/> <bean:message key="prompt.date"/></td>
	                                <td valign=top><bean:message key="prompt.fund"/> <bean:message key="prompt.end"/> <bean:message key="prompt.date"/></td>	
	                                <td valign=top><bean:message key="prompt.status"/></td>                               
	                          </tr>
	                      
	                  				 <% int RecordCounter=0;
	                                  int locCounter=0;
				                      String bgcolor="";
				                      String selectUser=""; %>
	                              <logic:iterate name="serviceProviderForm" id="fundsIndex" property="currentProgram.funds">
	                              	 <tr class= <% RecordCounter++;
				                     		bgcolor = "alternateRow";                      
	            				     		if (RecordCounter % 2 == 1)
				                         	bgcolor = "normalRow";
	               				     		out.print(bgcolor); %>>			                             
			                                <td><bean:write name="fundsIndex" property="fundEntryDate" formatKey="date.format.mmddyyyy"/></td>
			                                <td><bean:write name="fundsIndex" property="programSourceFund"/></td>
			                                <td><bean:write name="fundsIndex" property="fundStartDate" formatKey="date.format.mmddyyyy"/></td>
			                                <td><bean:write name="fundsIndex" property="fundEndDate" formatKey="date.format.mmddyyyy"/></td>
			                                <td><bean:write name="fundsIndex" property="fundStatus"/></td>
			                          </tr>
			                         
	                              </logic:iterate>
	                            </table>
	                       </td>
	                  </tr>
        		  </table>
        	</td>
        </tr>
  </logic:notEmpty>
</table>
</div>
<!-- END  TABLE -->

</html:form>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div></body>

</html:html>

