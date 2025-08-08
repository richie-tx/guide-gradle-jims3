<!DOCTYPE HTML>
<!--This JSP will be used to create, update additional addresses.-->
<!--MODIFICATIONS -->
<%-- Hien Rodriguez	06/22/2004	Create JSP --%>
<%-- Leslie Deen	02/22/2005	Revise JSP for struts design --%>
<%-- CShimek        02/14/2006  Defect#26016 Add address validation note --%>
<%-- CShimek        02/15/2006  Defect#27034 Add required field checks and indicators --%>
<%-- CShimek        04/12/2006  Defect#30141 Revised page to match prototypes and other small display items --%> 
<%-- CShimek        05/11/2006  #30640 - Display county select with Harris as default when state select is Texas otherwise 
									disable county select and show Please Select --%>
<%-- CShimek		12/21/2006  revised helpfile reference value--%>
<%-- CShimek 		03/01/2007	#39097 added multiple submit button logic --%>
<%-- CShimek 		04/12/2007	#41031 removed target=content in form tag to allow page to display in popup window --%>
<%-- LDeen		 	06/04/2007  #40649 added include for address.js --%>
<%-- RYoung         08/6/2015   #27796 MJCW:  Adapt MJCW and Warrants to IE10 and 11 (Juv Profile - Education UI) --%>

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
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/warrants.css" />
<html:base />
<title><bean:message key="title.heading" /> - warrantAssociateAddress.jsp </title>

<!--JAVASCRIPT FILES --> 

<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/address.js"></script>
<script type="text/javascript"  src="/<msp:webapp/>js/warrantAssociateAddress.js" ></script>

</head>
<!--END HEADER TAG-->

<!--BEGIN BODY TAG-->
<body onload="setStateCounty()" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<!-- BEGIN FORM -->
<html:form action="/handleWarrantAssociateAddresses">
    	<input type="hidden" name="helpFile" value="juvwarnt/juvenile_warrants_final.htm#|168">

<!-- BEGIN HEADING TABLE -->
	<table width="100%">
      <tr>
        <td align="center" class="header"><bean:message key="title.juvWarrantAssociate"/>&nbsp;Addresses</td>
      </tr>
	 </table>
<!-- END HEADING TABLE -->

<!-- BEGIN INSTRUCTION TABLE -->
<table width="98%" border="0">
   <tr>
     <td>
	  <ul>
        <li>To Create: Enter address information then select Add button.</li>
        <li>To Update: Change any address information for all addresses to be updated.</li>
        <li>Select Next button to view Address Create/Update Summary.</li>
	   	<li>Only addresses in the Harris County Geographic Information System can be validated.</li> 		  
 	  </ul>
 	 </td>
  </tr>
	<tr>
	  <td class="required"><bean:message key="prompt.1.diamond"/><bean:message key="prompt.requiredFieldsInstruction"/></td>
	</tr>
</table>
<!-- END INSTRUCTION TABLE -->

<!-- BEGIN ASSOCIATE HEADER TABLE -->
<table width="80%" border="0" align="center">
   <tr>
     <td class="subhead" align="right">
	  <bean:message key="prompt.associateName"/>:&nbsp;
	 </td>
	 <td>
	  <bean:write name="juvenileAssociateForm" property="associateName.lastName"/>, <bean:write name="juvenileAssociateForm" property="associateName.firstName"/> <bean:write name="juvenileAssociateForm" property="associateName.middleName"/></td>
  </tr>
</table>
<!-- END ASSOCIATE HEADER TABLE -->

<!-- BEGIN CONTENT TABLE -->
<!-- BEGIN CREATE SECTION -->
<table width="98%" border="0" cellpadding="2" cellspacing="0" align="center" class="borderTableBlue">
	<tr>
		<td class=detailHead nowrap><bean:message key="title.createAddress" /></td>
	</tr>
	<tr bgcolor="#f0f0f0">
		<td>
       		<table width="100%" border="0" cellpadding="0" cellspacing="2">		
				<tr>																			
					<td class="formDeLabel"><bean:message key="prompt.1.diamond"/><bean:message key="prompt.streetNumber"/></td>
					<td class="formDeLabel" colspan="2"><bean:message key="prompt.1.diamond"/><bean:message key="prompt.streetName"/></td>
				</tr>
	  			<tr>						    			  	
					<td><html:text name="juvenileAssociateForm" property="newAddress.streetNum" size="6" maxlength="6"/></td>
					<td colspan="2"><html:text name="juvenileAssociateForm" property="newAddress.streetName" size="30" maxlength="30"/></td>					
				</tr>				
				<tr>	
					<td class="formDeLabel"><bean:message key="prompt.streetType"/></td>
					<td class="formDeLabel" colspan="2"><bean:message key="prompt.apartmentNumber"/></td>					
				</tr>
				<tr>	
					<td>
						<html:select name="juvenileAssociateForm" property="newAddress.streetTypeId">
	                  	<html:option value=""><bean:message key="select.generic" /></html:option>
			          	<html:optionsCollection name="codeHelper" property="streetTypes" value="code" label="description"/> 
	  	            	</html:select>
	  	            </td>
					<td colspan="2"><html:text name="juvenileAssociateForm" property="newAddress.aptNum" size="6" maxlength="6"/></td>			
				</tr>
            	<tr>											
					<td class="formDeLabel"><bean:message key="prompt.1.diamond"/><bean:message key="prompt.city"/></td>
					<td class="formDeLabel"><bean:message key="prompt.1.diamond"/><bean:message key="prompt.state"/></td>
					<td class="formDeLabel"><bean:message key="prompt.1.diamond"/><bean:message key="prompt.zipCode"/></td>
				</tr>
				<tr>					
					<td><html:text name="juvenileAssociateForm" property="newAddress.city" size="15" maxlength="15"/></td>
					<td><html:select name="juvenileAssociateForm" property="newAddress.stateId" onchange="javascript:enableCounty(this, this.name)">
	                  	<html:option value=""><bean:message key="select.generic" /></html:option>
			          	<html:optionsCollection name="codeHelper" property="states" value="code" label="description"/> 
	  	            	</html:select></td>
					<td>
						<html:text name="juvenileAssociateForm" property="newAddress.zipCode" size="5" maxlength="5"/>-
	   					<html:text name="juvenileAssociateForm" property="newAddress.additionalZipCode" size="4" maxlength="4"/>
	   				</td>					
				</tr>
				<tr>						   													
					<td class="formDeLabel"><bean:message key="prompt.addressType"/></td>
					<td class="formDeLabel" colspan="2"><bean:message key="prompt.county"/></td>
				</tr>
				<tr>																	
    				<td><html:select name="juvenileAssociateForm" property="newAddress.addressTypeId">
	                  	<html:option value=""><bean:message key="select.generic" /></html:option>
			          	<html:optionsCollection name="codeHelper" property="addressTypes" value="code" label="description"/> 
	  	            	</html:select></td>
    				<td colspan="2">
    					<html:select name="juvenileAssociateForm" property="newAddress.countyId">
	        	          	<html:option value=""><bean:message key="select.generic" /></html:option>
			    	      	<html:optionsCollection name="codeHelper" property="counties" value="code" label="description"/> 
  	    	        	</html:select>
	  	    	     </td>					
				</tr>				
				<tr>															
					<td align="center" colspan="3">  
						<html:submit property="submitAction" onclick="return createAddressValidate(this.form)">
						  <bean:message key="button.add"></bean:message>
             			</html:submit>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<!-- END CREATE SECTION -->
<br>
<!-- VARIABLES NEEDED FOR DISPLAY -->
<%int RecordCounter = 0; 
  String bgcolor = "";
  int AddrIndex = -1; %>
<!-- BEGIN UPDATE SECTION -->
<table width="98%" border="0" cellpadding="2" cellspacing="1" align="center" class="borderTableBlue">
	<tr bgcolor="#f0f0f0">
		<td>
       		<table width=100% align=right border=0 cellpadding=0 cellspacing=0>
       		<!--<table width="100%" border="0" cellpadding="0" cellspacing="2">-->		
				<logic:iterate id="associateAddresses" name="juvenileAssociateForm" property="associateAddresses"> 
				<tr>
					<td class="detailHead" colspan=6>
						<!--<table width="100%" border="0" cellpadding="0" cellspacing="2">	-->
						<table width=100%>
							<tr>
								<td class="detailHead" nowrap>&nbsp;<bean:message key="title.updateAddresses" />&nbsp;
									<input type=submit value="<bean:message key='button.validate' />" name="submitAction" onClick="return changeAddrFormActionURL('juvenileAssociateForm','/<msp:webapp/>validateAddress.do',<% AddrIndex++; out.print(AddrIndex);  %>, '/jsp/warrantAssociateAddress.jsp', true);"></input>
							    	<html:button property="org.apache.struts.taglib.html.BUTTON" 
						  				 onclick="return displayResearchWindow();">
										  <bean:message key="button.research"></bean:message>
								    </html:button>
								</td> 
								<td class="detailHead" nowrap><bean:message key="prompt.addressStatus"/>:</td>
						   	    <td class="errorAlert">
<%-- TODO replace logic tags with code table values --%>	        	    
			 	  	    		   <logic:equal name="associateAddresses" property="addressStatus" value="U">
    	  				    		   	UNPROCESSED
					   		       </logic:equal>
   						    	   <logic:equal name="associateAddresses" property="addressStatus" value="Y">
			       			    	   	VALID
					   		       </logic:equal>
   						    	   <logic:equal name="associateAddresses" property="addressStatus" value="N">
       			    				   	INVALID
					   	    	   </logic:equal>
						   	    </td>		  
							</tr>
						</table>
					</td>
				</tr>	
                <tr class="alternateRow">      
					<td>
						<table width="100%" border="0" cellpadding="0" cellspacing="2">	
							<tr>																		
								<td class="formDeLabel"><bean:message key="prompt.1.diamond"/><bean:message key="prompt.streetNumber"/></td>
								<td class="formDeLabel" colspan="2"><bean:message key="prompt.1.diamond"/><bean:message key="prompt.streetName"/></td>
							</tr>
							<tr>						    			  	
								<td><html:text name="associateAddresses" indexed="true" property="streetNum" size="6" maxlength="6"/></td>
								<td colspan="2"><html:text name="associateAddresses" indexed="true" property="streetName" size="30" maxlength="30"/></td>					
							</tr>
							<tr>
								<td class="formDeLabel"><bean:message key="prompt.streetType"/></td>
								<td class="formDeLabel" colspan="2"><bean:message key="prompt.apartmentNumber"/></td>					
							</tr>
							<tr>
								<td>
									<html:select name="associateAddresses" indexed="true" property="streetTypeId">
										<html:option value=""><bean:message key="select.generic" /></html:option>
										<html:optionsCollection name="codeHelper" property="streetTypes" value="code" label="description"/> 
									</html:select>
								</td>
								<td colspan="2"><html:text name="associateAddresses" indexed="true" property="aptNum" size="6" maxlength="6"/></td>			
							</tr>
							<tr>											
								<td class="formDeLabel"><bean:message key="prompt.1.diamond"/><bean:message key="prompt.city"/></td>
								<td class="formDeLabel"><bean:message key="prompt.1.diamond"/><bean:message key="prompt.state"/></td>
								<td class="formDeLabel"><bean:message key="prompt.1.diamond"/><bean:message key="prompt.zipCode"/></td>
							</tr>
							<tr>					
								<td><html:text name="associateAddresses" indexed="true" property="city" size="15" maxlength="15"/></td>
								<td>
									<html:select name="associateAddresses" indexed="true" property="stateId" onchange="javascript:enableCounty(this, this.name)"> 
										<html:option value=""><bean:message key="select.generic" /></html:option>
										<html:optionsCollection name="codeHelper" property="states" value="code" label="description"/> 
									</html:select>
								</td>
								<td>
									<html:text name="associateAddresses" indexed="true" property="zipCode" size="5" maxlength="5"/>-
									<html:text name="associateAddresses" indexed="true" property="additionalZipCode" size="4" maxlength="4"/>
								</td>					
							</tr>
							<tr>						   													
								<td class="formDeLabel"><bean:message key="prompt.addressType"/></td>
								<td class="formDeLabel" colspan="2"><bean:message key="prompt.county"/></td>
							</tr>
							<tr>																	
								<td>
									<html:select name="associateAddresses" indexed="true" property="addressTypeId">
										<html:option value=""><bean:message key="select.generic" /></html:option>
										<html:optionsCollection name="codeHelper" property="addressTypes" value="code" label="description"/> 
									</html:select>
								</td>
								<td colspan="2">
							       <html:select name="associateAddresses" indexed="true" property="countyId">
									     <html:option value=""><bean:message key="select.generic"/></html:option>
									     <html:optionsCollection name="codeHelper" property="counties" value="code" label="description"/> 
									</html:select>								
								</td>					
							</tr>
						</table>
					</td>
				</tr>
				<logic:notEqual name="associateAddresses" property="addressMessage" value="">
					<tr>
						<td colspan="3" bgcolor=yellow><bean:write name="associateAddresses" property="addressMessage" /></td>
					</tr>
				</logic:notEqual>
				<tr><td>&nbsp;</td></tr>
				</logic:iterate> 				
			</table>
		</td>
	</tr>
</table>
<!-- END UPDATE SECTION -->
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
<!-- END CONTENT TABLE -->
<br>
<!-- BEGIN BUTTON TABLE -->
<table border="0" width="100%">
	<tr>
<!--		<td align="right" width="55%"> -->
		<td align="center"> 		
			<html:submit property="submitAction" onclick="return addressEntryValidate(this.form)">
				<bean:message key="button.next" />
			</html:submit>&nbsp;
			<html:reset><bean:message key="button.reset" /></html:reset>&nbsp;
			<html:submit property="submitAction">
				<bean:message key="button.cancel" />
			</html:submit>
		</td>
</html:form>
<!--CLOSE OUT FIRST FORM -->
		<td>
<!-- 1 button for Create Associate Address page --> 	
		<logic:equal name="juvenileAssociateForm" property="action" value="cancelCreate">
			<html:form action="/displayWarrantAssociateCreateUpdate.do?action=create"> 
				<html:submit>
					<bean:message key="button.cancel"></bean:message>
				</html:submit>
			</html:form> 
		</logic:equal>
<!-- 1 button for Update Associate Address page --> 	
		<logic:equal name="juvenileAssociateForm" property="action" value="cancelUpdate">	
			<html:form action="/displayWarrantAssociateCreateUpdate.do?action=update"> 
				<html:submit>
					<bean:message key="button.update"></bean:message>
				</html:submit>
			</html:form> 
		</logic:equal>
		</td>
	</tr>
</table>
<!-- END BUTTON TABLE -->

<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>