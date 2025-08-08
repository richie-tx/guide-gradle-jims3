<!DOCTYPE HTML>
<!-- Used to update juvenile warrant service Status page 2 of 2. -->
<!-- MODIFICATIONS -->
<%-- CShimek	 02/25/2005	Create JSP --%>
<%-- CShimek     08/12/2005  Revised Look and Feel --%>
<%-- JFisher     08/23/2005  Update form fields to reflect use of java.util.Date instead of String --%>
<%-- CShimek     10/04/2005  ER#24012 change assocaite labels to responsible adult --%>
<%-- DWilliamson 01/09/2006  Er #25408 change streetNumber to streetNum --%>
<%-- CShimek     02/02/2006  Add hidden field for HelpFile name --%>
<%-- CShimek     02/14/2006  Defect#26016 Add address validation note --%>
<%-- CShimek     03/07/2006  Correct problem of current date not displaying in service date field --%>
<%-- RYoung      04/05/2006  Defect#29565 Add service date validation  --%> 
<%-- CShimek     05/12/2006  #30640 - Display county select with Harris as default when state select is Texas otherwise 
									disable county select and show Please Select --%>
<%-- CShimek     05/12/2006  #30663 - Correct check for comment field to not exceed 250 characters -- textcounter in app.js not working. --%>
<%-- HRodriguez  08/08/2006  #34030 - Revised the look & labels of Prior/Current Service Attempt sections. --%>
<%-- CShimek     11/09/2006  #36895 - Revised service time to JIMS2 standard of hh:mm input --%>
<%-- LDeen	     12/21/2006  Revised help file code --%>
<%-- CShimek 	 01/05/2007  #37732 - Revised action name for cancel button to use generic search action --%>
<%-- CShimek     03/23/2007  Add missing disablebutton found while testing height/weight defect --%>
<%-- LDeen		 06/04/2007  #42564 changed path to app.js --%>
<%-- LDeen		 06/04/2007  #40649 added include for address.js --%>
<%-- CShimek     06/13/2007  #42801 moved embedded scripts into warrantServiceStatusUpdate1.js --%>
<%-- RCapestani  07/23/2007  #44003 added Tag LIBRARIES NEEDED FOR CUSTOM JIMS and sort descending into PRIOR SERVICE ATTEMPTS BLOCK --%>
<%-- RYoung      11/29/2007  #46544 Omit left/right scrolling and changed the labels --%>
<%-- RYoung     08/06/2015 #27796 MJCW:  Adapt MJCW and Warrants to IE10 and 11 (Juv Profile - Education UI) --%>

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<!--TAG LIBRARIES NEEDED FOR CUSTOM JIMS -->
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>

<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->

<!-- SCRIPTING VARIABLES -->
<jsp:useBean id="codeHelper" scope="application" class="ui.juvenilewarrant.helper.JuvenileWarrantListHelper" />


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

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<html:base />
<title>JIMS2 - warrantServiceStatusUpdate2.jsp</title>

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<!--THESE TWO LINES FOR STRUTS & JAVASCRIPTS VALIDATIONS-->
<html:javascript formName="juvenileWarrantForm" />  
<!-- JAVASCRIPT FILES-->
<script type="text/javascript" src="/<msp:webapp/>js/warrants.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/address.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/warrant_utils.js"></script>

<script type="text/javascript">
$(function() {

    $("#servDate").datepicker({
	      changeMonth: true,
	      changeYear: true,
	      onClose: function( selectedDate ) {
	        $( "#servDate" ).datepicker( "option", "minDate", selectedDate );
	    }
    });
 });
</script>
<!-- main calendar program -->

<script type="text/javascript"  src="/<msp:webapp/>js/warrantServiceStatusUpdate2.js" ></script>
</head>

<!--BEGIN BODY TAG-->
<body onload="setStateCounty()" && onKeyDown="checkEnterKey(event,true)">
<html:form action="/displayWarrantServiceStatusSummary" target="content">
 <!-- <input type="hidden" name="selectedBadAddress" value="">  -->  
<input type="hidden" name="helpFile" value="juvwarnt/juvenile_warrants_final.htm#|136">		

<!-- BEGIN HEADING TABLE -->
<table width="98%">
  <tr>
    <td align="center" class="header" ><bean:message key="title.update"/>
                                       <bean:message key="title.juvWarrantWarrantServiceStatus"/></td>
  </tr>
</table>
<!-- END HEADING TABLE -->

<br>
<!-- BEGIN INSTURCTION TABLE -->
<table width="98%">
  <tr>
    <td>
      <ul>
        <li>Enter required and other fields.</li>
        <li>Select Next button to view summary.</li> 
       	<li>Only addresses in the Harris County Geographic Information System can be validated.</li>         
      </ul>
    </td>
  </tr>
  <tr><td class="required"><bean:message key="prompt.1.diamond"/><bean:message key="prompt.requiredFieldsInstruction"/></td></tr>
  <tr><td class="required"><bean:message key="prompt.1.diamond"/><bean:message key="prompt.1.diamond"/>
  	<bean:message key="prompt.requiredFieldsInstruction"/> if Responsible Adult Address not selected.</td>
	</tr>  
  <tr><td class="required"><bean:message key="prompt.1.diamond"/><bean:message key="prompt.1.diamond"/><bean:message key="prompt.1.diamond"/>
  	<bean:message key="prompt.requiredFieldsInstruction"/> if Warrant Service Status is unsuccessful.</td>
  </tr>
  <tr><td class="required"><bean:message key="prompt.dateFieldsInstruction"/></td></tr>
</table>
<!-- END INSTRUCTION TABLE -->

<br>
<!-- BEGIN MAIN BODY TABLE -->
<table width=98% border=0 cellspacing=1 cellpadding=4 align=center>
<!-- BEGIN GENERAL INFORMATION BLOCK -->
	<tr>
		<td class=detailHead colspan=4 nowrap><bean:message key="prompt.generalInfo" /></td>
	</tr>

	<tr>
		<td class=formDeLabel><bean:message key="prompt.warrantNumber"/></td>
		<td class=formDe>           
			<bean:write name="juvenileWarrantForm" property="warrantNum"/>
		</td>
		<td class=formDeLabel><bean:message key="prompt.warrantStatus"/></td>
		<td class=formDe>           
			<bean:write name="juvenileWarrantForm" property="warrantStatus"/>
		</td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.warrantType"/></td>
		<td class=formDe colspan=3>                      
			<bean:write name="juvenileWarrantForm" property="warrantType"/>
		</td>          
	</tr>
	<tr>
		<td class=formDeLabel width="1%" nowrap><bean:message key="prompt.warrantActivationStatus"/></td>
		<td class=formDe colspan=3>
			<bean:write name="juvenileWarrantForm" property="warrantActivationStatus"/>
		</td>          
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.juvenileName"/>
			<input type="hidden" id="juvenileNum" value="<bean:write name="juvenileWarrantForm" property="juvenileNum" />" />
			<input type="hidden" id="warrantNumber" value="<bean:write name="juvenileWarrantForm" property="warrantNum" />" />
			<input type="hidden" id="firstName" value="<bean:write name="juvenileWarrantForm" property="juvenileName.firstName" />" />
			<input type="hidden" id="lastName" value="<bean:write name="juvenileWarrantForm" property="juvenileName.lastName" />" />
			<input type="hidden" id="jpOfficerEmailAddress" value="<bean:write name="juvenileWarrantForm" property="jpOfficerEmail" />" />
		</td>          
		<td class=formDe colspan=3>
		<%-- <bean:write name="juvenileWarrantForm" property="juvenileName"/> --%>
			<bean:write name="juvenileWarrantForm" property="juvenileName.lastName"/>, 
			<bean:write name="juvenileWarrantForm" property="juvenileName.firstName"/>
			<bean:write name="juvenileWarrantForm" property="juvenileName.middleName"/> 
			<bean:write name="juvenileWarrantForm" property="nameSuffix"/>
		</td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.warrantOriginatorName"/></td>
		<td class=formDe colspan=3>
			<bean:write name="juvenileWarrantForm" property="warrantOriginatorName"/>
		</td>          
	</tr>
<!-- END GENERAL INFORMATION BLOCK -->

<tr><td>&nbsp;</td></tr>
<!-- BEGIN LAW ENFORCEMENT/EXECUTOR INFORMATION BLOCK -->
  <tr>
    <td class=detailHead colspan=4 nowrap><bean:message key="prompt.executorInfo" /></td>
  </tr>
  <tr>
    <td class=formDeLabel><bean:message key="prompt.executorName"/></td>          
    <td class=formDe colspan=3><bean:write name="juvenileWarrantForm" property="executorName"/></td>
  </tr>
  <tr>
    <td class=formDeLabel><bean:message key="prompt.officerIdNumber"/></td>
    <td class=formDe>
      <bean:write name="juvenileWarrantForm" property="executorId"/>
    </td>
    <td class=formDeLabel width="1%" nowrap><bean:message key="prompt.officerIdType"/></td>
    <td class=formDe>                            
      <bean:write name="juvenileWarrantForm" property="executorIdType"/>
    </td> 
  </tr>
  <tr>   
    <td class=formDeLabel><bean:message key="prompt.department"/></td>
    <td class=formDe colspan=3>                             
      <bean:write name="juvenileWarrantForm" property="executorDepartmentName"/>
    </td>
  </tr>            
  <tr>
    <td class=formDeLabel><bean:message key="prompt.workPhone"/></td>
    <td class=formDe><bean:write name="juvenileWarrantForm" property="executorPhoneNum"/></td>
    <td class=formDeLabel><bean:message key="prompt.cellPhone"/></td>
    <td class=formDe><bean:write name="juvenileWarrantForm" property="executorCellNum"/></td>
  </tr>
  <tr>
    <td class=formDeLabel><bean:message key="prompt.pager"/></td>
    <td class=formDe><bean:write name="juvenileWarrantForm" property="executorPager"/></td>
    <td class=formDeLabel><bean:message key="prompt.email"/></td>
    <td class=formDe>              
       <bean:write name="juvenileWarrantForm" property="executorEmail"/>
    </td>
  </tr>            
<!-- END LAW ENFORCEMENT/EXECUTOR INFORMATION BLOCK -->

<tr><td>&nbsp;</td></tr>
<!-- BEGIN OPTIONAL COST INFORMATION BLOCK -->
    <tr>
  		<td class=detailHead colspan=4><bean:message key="prompt.serviceCostInfo" /></td>
  	</tr>
    <tr>
       <td class=formDeLabel><bean:message key="prompt.mileage" /></td>
       <td class=formDe><html:text name="juvenileWarrantForm" property="costMileage" size="4" maxlength="4"/></td>
       <td class=formDeLabel><bean:message key="prompt.airFare" /></td>
       <td class=formDe><html:text name="juvenileWarrantForm" property="costAirFare" size="7" maxlength="7"/></td>
     </tr>
    <tr>  
       <td class=formDeLabel><bean:message key="prompt.perDiem" /></td>
       <td class=formDe colspan=3><html:text name="juvenileWarrantForm" property="costPerDiem" size="6" maxlength="6"/></td>
    </tr>
      
<!-- END OPTIONAL COST INFORMATION TABLE -->

<tr><td>&nbsp;</td></tr>
<!-- BEGIN PRIOR SERVICE ATTEMPTS BLOCK -->
   <tr>
      <td class="detailHead" colspan=4><bean:message key="prompt.priorServiceAttempts" /></td>
   </tr>
   
	 <logic:empty name="juvenileWarrantForm" property="services">
      <tr><td class=formDe colspan=4>No prior service attempts found</td></tr>
      <tr><td>&nbsp;</td></tr>
   </logic:empty>
	 
  <logic:notEmpty name="juvenileWarrantForm" property="services">
  <jims:sortResults beanName="juvenileWarrantForm" results="services" primaryPropSort="serviceTimeStamp" primarySortType="DATE" hideMe="True" defaultSort="True" defaultSortOrder="DESC" sortId="0" />
    <logic:iterate id="priorAddressIndex" name="juvenileWarrantForm" property="services"> 
      <tr>
        <td class=formDeLabel><bean:message key="prompt.status" /></td>
        <td class=formDe><bean:write name="priorAddressIndex" property="serviceStatus"/></td>
      	<td class=formDeLabel><bean:message key="prompt.dateTime" /></td>
      	<td class=formDe><bean:write name="priorAddressIndex" property="serviceTimeStamp" formatKey="date.format.mmddyyyy" />&nbsp;
        <bean:write name="priorAddressIndex" property="serviceTimeStamp" formatKey="time.format.HHmm" /></td>
      </tr>
      <tr>     				
        <td class=formDeLabel><bean:message key="prompt.address" /></td>
        <td class=formDe colspan=3><bean:write name="priorAddressIndex" /></td>   
      </tr>
      <tr>
        <td class=formDeLabel nowrap><bean:message key="prompt.comments" /></td>
        <td class=formDe colspan=3><bean:write name="priorAddressIndex" property="comments"/></td>
      </tr>
      <tr><td>&nbsp;</td></tr>
    </logic:iterate>   
  </logic:notEmpty>
<!-- END PRIOR SERVICE ATTEMPTS BLOCK -->

<!-- BEGIN CURRENT SERVICE ATTEMPT BLOCK -->
  <tr>
    <td class="detailHead" colspan=4><bean:message key="prompt.currentServiceAttempt" /></td>
  </tr>
<!-- BEGIN SERVICE DATA AREA -->
  <tr>
    <td class=formDeLabel><bean:message key="prompt.1.diamond"/><bean:message key="prompt.status" /></td>
    <td class=formDe colspan="3">
      <html:select property="currentWarrantServiceStatus">
        <html:option value=""><bean:message key="select.generic" /></html:option>
        <html:optionsCollection name="codeHelper" property="serviceStatuses" value="code" label="description" />
      </html:select></td>
   </tr>  
   <tr>
    <td class=formDeLabel><bean:message key="prompt.1.diamond"/><bean:message key="prompt.date" /></td>
    <td class=formDe width="1%" nowrap>
    	<html:text name="juvenileWarrantForm" property="currentServiceDateString" size="10" maxlength="10" styleId="servDate"/>
    </td>
     <td class=formDeLabel><bean:message key="prompt.1.diamond"/><bean:message key="prompt.time" /></td>
	<td class=formDe><html:text name="juvenileWarrantForm" property="currentServiceTime" size="5" maxlength="5"/> <b> (hh:mm)</b></td>    
  </tr>		
  <tr>
  	<td colspan=4>
		 <input type="hidden" name="activationDate" value=<bean:write name="juvenileWarrantForm" property="warrantActivationDate" formatKey="date.format.mmddyyyy" />>
 		 <input type="hidden" name="issueDate" value=<bean:write name="juvenileWarrantForm" property="dateOfIssue" formatKey="date.format.mmddyyyy" />>
	</td>
  
  </tr>
<!-- END SERVICE DATA BLOCK -->

	<tr><td colspan="5">&nbsp;</td></tr>
<!-- BEGIN ASSOCIATE INFORMATION SECTION -->
	<tr>
		<td class="detailHead" colspan="5"><bean:message key="prompt.associatesInfo" />
			<span>&nbsp&nbsp&nbsp<a href="#" onClick="OpenOutlook()" >Email JPO & Data Corrections</a>
		</td>
	</tr>
		
	<!-- display detail info -->
	<tr class="formDeLabel">
		<td width="50%" colspan="2"><bean:message key="prompt.name" /></td>
		<td width="50%" colspan="3"><bean:message key="prompt.relationshipToJuvenile" /></td>
	</tr>
	<logic:empty name="juvenileWarrantForm" property="associates"> 
    	<tr>
      		<td width="2%">&nbsp;</td>
      		<td colspan="3"></td>
    	</tr> 
  	</logic:empty> 
			
	<logic:iterate id="associateIndex" name="juvenileWarrantForm" property="associates" indexId="index"> 
		<logic:notEqual name="associateIndex" property="associateNum" value="1111111"> <!-- do not display dummy family member -->
  		<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
			<td colspan="2" align="left">
				
					<logic:notEqual name="juvenileWarrantForm" property="secondaryAction" value="popup">
						<a href="javascript:openWindow('/<msp:webapp/>displayAssociateDetails.do?associateNumber=<bean:write name="associateIndex" property="associateNum"/>&juvenileNumber=<bean:write name="juvenileWarrantForm" property="juvenileNum" />&relationshipId=<bean:write name="associateIndex" property="relationshipToJuvenileId"/>');">	               			
							<bean:write name="associateIndex" property="associateName.lastName" />, 
							<bean:write name="associateIndex" property="associateName.firstName" /> 
							<bean:write name="associateIndex" property="associateName.middleName" />
						</a>
					</logic:notEqual>
					<logic:equal name="juvenileWarrantForm" property="secondaryAction" value="popup">
						<bean:write name="associateIndex" property="associateName.lastName" />, 
						<bean:write name="associateIndex" property="associateName.firstName" /> 
						<bean:write name="associateIndex" property="associateName.middleName" />
					</logic:equal>
				
      		</td>
      		<td colspan="3" align="left">

      				<bean:write name="associateIndex" property="relationshipToJuvenile" />

      		</td>			 
		</tr>
		</logic:notEqual>
	</logic:iterate> 
<!-- END ASSOCIATE INFORMATION SECTION -->

<!-- =======================  NEW ADDRESS SERVICE BLOCK    ========================== -->

					<tr>	
						<td class=detailHead colspan=4><bean:message key="prompt.associateAddress" />
							
						</td>
					</tr>
					
				<logic:iterate id="associate" name="juvenileWarrantForm" property="associates" indexId="index"> 
					
					<logic:notEmpty name="associate" property="associateAddresses">
					
						<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
				  			<td class=formDeLabel colspan=4>
				  			<bean:write name="associate" property="relationshipToJuvenile" />
				  			<input type="hidden" name="currentAssociateId" value="<bean:write name="associate" property="associateNum" />" />
				  			</td>	
							<td align="left">
						
								<logic:iterate id="associateAddress" name="associate" property="associateAddresses" indexId="index">								
								
									<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
										<td class=formDeLabel align="center">
											<input type="radio" name="selectedAssociateAddressId" value="<bean:write name="associateAddress" property="addressId" />" >
											<input type="hidden" name="selectedAssociateId" value="<bean:write name="associate" property="associateNum" />" />
										</td>
										<td align="left" colspan=2> 
										
											<logic:notEmpty name="associateAddress" property="streetNumber">
												<bean:write name="associateAddress" property="streetNumber" />
											</logic:notEmpty>
											<logic:notEmpty name="associateAddress" property="streetName">
												<bean:write name="associateAddress" property="streetName" />
											</logic:notEmpty>
											<logic:notEmpty name="associateAddress" property="aptNumber">
												<bean:write name="associateAddress" property="aptNumber" />
											</logic:notEmpty>
											<logic:notEmpty name="associateAddress" property="city">
												<bean:write name="associateAddress" property="city" />
											</logic:notEmpty>	
											<logic:notEmpty name="associateAddress" property="stateId">
												<bean:write name="associateAddress" property="stateId" />
											</logic:notEmpty>
											<logic:notEmpty name="associateAddress" property="zipCode">
												<bean:write name="associateAddress" property="zipCode" />
											</logic:notEmpty>
												<br>
											<logic:notEmpty name="associateAddress" property="addressType">
												<span>Address Type: </span><bean:write name="associateAddress" property="addressType" />
											</logic:notEmpty>		
											</td>
									</tr>
									<tr>
									<td class=formDeLabel></td> 
									<td class=formDeLabel><bean:message key="prompt.badAddress"/>?</td>
									<td class=formDe colspan=3>
										&nbsp;
										<input type="checkbox" id="chkBadAddress" name="selectedBadAddress" value="<bean:write name="associateAddress" property="addressId" />" /> 
										
									</td>
								</tr>
								</logic:iterate>								
								
	      					</td>      				 
						</tr>
					
					</logic:notEmpty>
				
	</logic:iterate> 
	
		

<!-- ===================================END NEW ADDRESS SERVICE BLOCK =============================== -->


<!-- BEGIN SERVICE ADDRESS BLOCK -->
 <tr> 
	 <td colspan=5>
			 <table width=100% border=0 cellspacing=1 cellpadding=4>
			 
			 <!--
				<tr>	
					<td class=detailHead colspan=4><bean:message key="prompt.associateAddress" /></td>
				</tr>
				<logic:empty name="juvenileWarrantForm" property="associateServiceAddresses">
					<tr>
						<td class=formDe colspan=4></td>
					</tr>
				</logic:empty>
				<% int AddrCounter = 0; %>
				<bean:define id="addrSel" name="juvenileWarrantForm" property="selectedAssociateAddressId" type="java.lang.String"/> 
				<logic:notEmpty name="juvenileWarrantForm" property="associateServiceAddresses">
					<logic:iterate id="associateAddressIndex" name="juvenileWarrantForm" property="associateServiceAddresses">        	      
						<tr>
						<td class=formDeLabel>
							
						</td>
							
						</tr>
						<tr>
							<td class=formDeLabel align="center">	
								<logic:equal name="associateAddressIndex" property="addressId" value="<%= addrSel %>" >	   
									<input type="radio" name="selectedAssociateAddressId" value=<bean:write name='associateAddressIndex' property='addressId'/> checked >
								</logic:equal>	
								<logic:notEqual name="associateAddressIndex" property="addressId" value="<%= addrSel %>" >	   
									<input type="radio" name="selectedAssociateAddressId" value=<bean:write name='associateAddressIndex' property='addressId'/> >
								</logic:notEqual>	

							</td>
			
							<td align="left" colspan=2>
								<bean:write name="associateAddressIndex" property="streetNumber" />
								<bean:write name="associateAddressIndex" property="streetName" />
								<bean:write name="associateAddressIndex" property="aptNumber" />
								<bean:write name="associateAddressIndex" property="city" />
								<bean:write name="associateAddressIndex" property="stateId" />	
								<bean:write name="associateAddressIndex" property="zipCode" />	
								<bean:write name="associateAddressIndex" property="addressType" />	
							</td>
						</tr>
						<tr>
							<td class=formDeLabel></td> 
							<td class=formDeLabel><bean:message key="prompt.badAddress"/>?</td>
							<td class=formDe colspan=3>&nbsp;<input type="checkbox" name=<bean:write name="associateAddressIndex" property="addressId"/> ></td>
						</tr>
					</logic:iterate>
				</logic:notEmpty>
				
			-->	
	<tr>
    	<td class="detailHead" colspan=4>
	    	<table width=100% align=right border=0 cellpadding=0 cellspacing=0>
    			<tr> 	
				    <td class="detailHead" nowrap><bean:message key="prompt.serviceAddressNotListedAsAssociate"/></td> 
				    <!--    
					<td class="detailHead" align=right><bean:message key="prompt.addressStatus"/>&nbsp;</td>
					-->
	        	    <td class="errorAlert">        	    
	        	       <logic:equal name="juvenileWarrantForm" property="addressStatus" value="U">
	        	       	UNPROCESSED
	        	       </logic:equal>
	        	       <logic:equal name="juvenileWarrantForm" property="addressStatus" value="Y">
	        	       	VALID
	        	       </logic:equal>
	        	       <logic:equal name="juvenileWarrantForm" property="addressStatus" value="N">
	        	       	INVALID
	        	       </logic:equal></td>
		        </tr>
		        <!-- 
			        <tr>
						<td colspan=3 align=center>	
							<input type="button" value="<bean:message key='button.validate' />" name="submitAction" 
								   onClick="return changeAddrFormActionURL('juvenileWarrantForm','/<msp:webapp/>validateAddressWarrants.do','', '/jsp/warrantServiceStatusUpdate2.jsp', true);" />
		    	    <html:button property="org.apache.struts.taglib.html.BUTTON" onclick="return displayResearchWindow();"> <bean:message key="button.research" /></html:button>
						</td>        
			        </tr>
		         -->
		      </table>
	     </td>       
	 </tr>
	 			<tr>
	 				<td class=formDeLabel align="center">
	 					<input type="checkbox" id="addressId" name="addressId" value="newAddress" >
	 				</td>               
					<td class=formDeLabel width="1%" nowrap colspan=3><bean:message key="prompt.1.diamond"/><bean:message key="prompt.1.diamond"/>Associate</td>
				</tr>
				<logic:notEmpty name="juvenileWarrantForm" property="associates">
				<tr>
					<td class=formDe></td>                
					<td class=formDe>
						<html:select name="juvenileWarrantForm" styleId="associateIdDropDown" property="associateId">
							<html:option value=""><bean:message key="select.generic" /></html:option>  			            	  	
							<html:optionsCollection name="juvenileWarrantForm" property="associates" value="associateNum" label="associateNumAndRelationship" />
						</html:select> 
					</td>
					<td class=formDe colspan=2>
						<span>&nbsp</span> 
					</td> 
				</tr>  
				<tr>
					<td class=formDeLabel align="center">            			
            			
            			<!--  
						<logic:equal name="juvenileWarrantForm" property="addressStatus" value="Y">
 							<input type="radio" name="selectedAssociateAddressId" value="newAddress" checked>  
						</logic:equal>
						
           			    <logic:notEqual name="juvenileWarrantForm" property="addressStatus" value="Y">
  						<logic:notEmpty name="juvenileWarrantForm" property="associateServiceAddresses">
  							<html:radio property="selectedAssociateAddressId" value="newAddress" />  
  						</logic:notEmpty> 
  						<logic:empty name="juvenileWarrantForm" property="associateServiceAddresses">
  							<input type="radio" name="selectedAssociateAddressId" value="newAddress" checked>  
  						</logic:empty>
						</logic:notEqual>
						-->
					</td>
					<td class=formDeLabel width="1%" nowrap><bean:message key="prompt.1.diamond"/><bean:message key="prompt.1.diamond"/><bean:message key="prompt.streetNumber"/></td>
					<td class=formDeLabel colspan="2"><bean:message key="prompt.1.diamond"/><bean:message key="prompt.1.diamond"/><bean:message key="prompt.streetName"/></td>
					<tr>
				<tr> 
					<td class=formDe></td> 
					<td class=formDe>
						<html:text name="juvenileWarrantForm" styleId="streetNum" property="streetNum" size="6" maxlength="8" />
					</td>
					<td class=formDe colspan="2">
						<html:text name="juvenileWarrantForm" styleId="streetName" property="streetName" size="20" maxlength="20" />
					</td>
					
				</tr>
				
				<tr>  
					<td class=formDe></td>  
	   				<td class=formDeLabel><bean:message key="prompt.streetType"/></td>
	  				<td class=formDeLabel colspan="2"><bean:message key="prompt.apartmentNumber"/></td>
				</tr>
				
				<tr>
				<td class=formDe></td> 
				<td class=formDe>
						<html:select property="streetType">
							<html:option value=""><bean:message key="select.generic" /></html:option>  			            	  	
							<html:optionsCollection name="codeHelper" property="streetTypes" value="code" label="description" />
						</html:select>  
					</td> 
					<td  class=formDe colspan="2">
						<html:text name="juvenileWarrantForm" styleId="aptNumber" property="apartmentNum" size="6" maxlength="10" />
					</td>
				</tr>
				<tr>
					<td class=formDeLabel></td>                
					<td class=formDeLabel><bean:message key="prompt.1.diamond"/><bean:message key="prompt.1.diamond"/><bean:message key="prompt.city"/></td>
					<td class=formDeLabel><bean:message key="prompt.1.diamond"/><bean:message key="prompt.1.diamond"/><bean:message key="prompt.state"/></td>
					<td class=formDeLabel><bean:message key="prompt.1.diamond"/><bean:message key="prompt.1.diamond"/><bean:message key="prompt.zipCode"/></td>
				</tr>
				<tr> 
					<td class=formDe></td>                
					<td class=formDe>
						<html:text name="juvenileWarrantForm" styleId="city" property="city" size="20" maxlength="20" />
					</td>
					<td class=formDe>
					<html:select name="juvenileWarrantForm" property="state" size="1" styleId="state" onchange="javascript:enableCounty(this, this.name)">
							<html:option value=""><bean:message key="select.generic" /></html:option>  			            	  	
							<html:optionsCollection name="codeHelper" property="states" value="code" label="description" />
						</html:select>   
					</td>
					<td class=formDe>
						<html:text name="juvenileWarrantForm" styleId="zipCode" property="zipCode" size="5" maxlength="5" />
						<html:text name="juvenileWarrantForm" styleId="zipCode2" property="zipCode2" size="5" maxlength="5" />	             
					</td>
				</tr>
				<tr>
					<td class=formDeLabel>&nbsp;</td>                
					<td class=formDeLabel><bean:message key="prompt.addressType"/></td>
					<td class=formDeLabel colspan=2><bean:message key="prompt.county"/></td>
				</tr>
				<tr>
					<td class=formDe></td>                
					<td class=formDe>
						<html:select name="juvenileWarrantForm" styleId="addressType" property="addressType">
							<html:option value=""><bean:message key="select.generic" /></html:option>  			            	  	
							<html:optionsCollection name="codeHelper" property="addressTypes" value="code" label="description" />
						</html:select> 
					</td>
					<td class=formDe colspan=2>
						<html:select name="juvenileWarrantForm" property="county" styleId="county" >
							<html:option value=""><bean:message key="select.generic" /></html:option>  			            	  	
							<html:optionsCollection name="codeHelper" property="counties" value="code" label="description" />
						</html:select>  
					</td> 
				</tr>
				
				</logic:notEmpty>
				<logic:notEqual name="juvenileWarrantForm" property="validAddressMessage" value="">
			    	<tr>
		        		 <td colspan=4 bgcolor=yellow><bean:write name="juvenileWarrantForm" property="validAddressMessage" /></td>
   	 				</tr>
				</logic:notEqual>				
		</table>
	</td>
	</tr>
<!-- END SERVICE ADDRESS BLOCK -->	
<!-- BEGIN COMMENTS BLOCK-->
    <tr>
      <td class="detailHead" colspan=4 nowrap>
    	<bean:message key="prompt.1.diamond"/><bean:message key="prompt.1.diamond"/><bean:message key="prompt.1.diamond"/>
		<bean:message key="prompt.comments"/>
  	  </td>
    </tr>
    <tr>
      <td class=formDe colspan=4>
        <html:textarea property="currentServiceAttemptComments" style="width:100%" rows="3" onkeyup="return textCount(this,'Service Attempt Comments',250);"></html:textarea>
      </td>
    </tr>
<!-- END COMMENTS BLOCK -->
</table>
<!-- END MAIN TABLE -->
<%-- BEGIN HIDDEN FIELDS FOR ADDRESS VALIDATION --%>
	<table width=100%>
		<tr>
			<td>
			  <html:hidden property="validStreetNum" value="" />
  			  <html:hidden property="validStreetName" value="" /> 
			  <html:hidden property="validZipCode" value="" />
			  <html:hidden property="validAddrNum" value="" />
		  	  <html:hidden property="inputPage" value="" />
			</td>
		</tr>	  
	</table>
<%-- ENd HIDDEN FIELDS FOR ADDRESS VALIDATION --%>
<br>
<!-- BEGIN BUTTON TABLE -->
<table border="0" align="center">
<TBODY>
  <tr valign="top">
    <td>
<%--      <html:button
				property="org.apache.struts.taglib.html.BUTTON"
				onclick="goNav('back');">
				<bean:message key="button.back"></bean:message>
		  </html:button>&nbsp; --%> 
      <html:submit property="submitAction" onclick="return disableSubmit(this, this.form)">
      		<bean:message key="button.back" />
      </html:submit>&nbsp;
       <html:submit styleId="submitActionNext" property="submitAction" onclick="return validateNewAddress(this.form) && validateFormFields(this.form) && checkDates(this.form) && disableSubmit(this, this.form)">  <%--  && checkDates(this.form)" && checkValidTime(this.form))"> --%>
      		<bean:message key="button.next" />
      </html:submit>&nbsp;
    </td>  
    <td>
      <html:reset><bean:message key="button.reset"/></html:reset>&nbsp;
	</td>
    </html:form>
    <!-- END 1st FORM -->
     <html:form action="/displayGenericSearch.do?warrantTypeUI=warrantService&associateUpdatable=false"
     			onsubmit="clearExecutorFields(this)">
     	<td>		
        <html:hidden property="userId" /><html:hidden property="agencyId" /><html:hidden property="officerId" /><html:hidden property="officerIdTypeId" />
        <html:submit><bean:message key="button.cancel" /></html:submit>
       </td> 
     </html:form>
  
  </tr>
  </TBODY>
</table>
<!-- END BUTTON TABLE -->

<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
