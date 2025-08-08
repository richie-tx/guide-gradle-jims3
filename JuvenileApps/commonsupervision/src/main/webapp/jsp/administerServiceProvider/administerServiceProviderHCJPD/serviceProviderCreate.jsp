<!DOCTYPE HTML>
<!-- User selects the 'Create HCJDP' link on the left UI Navigation -->
<!--MODIFICATIONS -->
<%-- 05/24/2006	 Uma Gopinath	Create JSP --%>
<%-- 08/28/2006  H Rodriguez	ER#34507 Implement new UI Guidelines for all date fields --%>
<%-- 07/17/2007	 Leslie Deen	Defect #43882 - add link to address.js --%>
<%-- 07/20/2007	 Leslie Deen	Defect #43451 - change name length to 100 --%>
<%-- 10/14/2015  Richard Capestani Task #30717 MJCW: IE11 conversion of HCJPD link on UILeftNav (UI - Service Provider-Juvenile link) --%>
<%-- 09/23/2024	 NM	Add MAXYouth in Create and Update --%>

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="org.apache.struts.action.Action" %>
<%@ page import="org.apache.struts.action.ActionErrors" %>
<!--CUSTOM LIBRARIES NEEDED FOR PAGE -->
<%@ taglib uri="/WEB-INF/taglibs-datetime.tld" prefix="dt" %>

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
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>/css/commonsupervision.css" />
<html:base />
<title><bean:message key="title.heading"/>- serviceProviderCreate.jsp</title>

<!--JQUERY FRAMEWORK-->
<%@include file="../../jQuery.fw"%>

<html:javascript formName="providerStartDateForm"/>
<html:javascript formName="serviceProviderForm"/>
<script type="text/javascript" src="/<msp:webapp/>js/casework.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/address.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/casework_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/administerServiceProviderHCJPD/serviceProviderCreate.js"></script>
<script type="text/javascript">

$(function() {
	if(typeof $("#startDate")!= "undefined"){			
		datePickerSingle( $("#startDate"),"Start Date", false);		 
	}
});

</script>

<script type="text/javascript">
//this function show/hides the billing address information if the user checks the Same As Billing checkbox
//params: el - the checkbox object
function showBilling(el)
{
	if (el.checked)
	{
		document.forms[0]["billingAddress.streetNumber"].value = document.forms[0]["mailingAddress.streetNumber"].value;
		document.forms[0]["billingAddress.streetName"].value = document.forms[0]["mailingAddress.streetName"].value;
		document.forms[0]["billingAddress.streetTypeId"].value = document.forms[0]["mailingAddress.streetTypeId"].value;
		document.forms[0]["billingAddress.aptNumber"].value = document.forms[0]["mailingAddress.aptNumber"].value;
		document.forms[0]["billingAddress.city"].value = document.forms[0]["mailingAddress.city"].value;
		document.forms[0]["billingAddress.stateId"].value = document.forms[0]["mailingAddress.stateId"].value;
		document.forms[0]["billingAddress.zipCode"].value = document.forms[0]["mailingAddress.zipCode"].value;
		document.forms[0]["billingAddress.additionalZipCode"].value = document.forms[0]["mailingAddress.additionalZipCode"].value;		
	  show('billing1', 0,"row" );	
	}
	else 
	{
		/*document.forms[0]["billingAddress.streetNumber"].value = "";
		document.forms[0]["billingAddress.streetName"].value = "";
		document.forms[0]["billingAddress.streetTypeId"].value = "";
		document.forms[0]["billingAddress.aptNumber"].value = "";
		document.forms[0]["billingAddress.city"].value = "";
		document.forms[0]["billingAddress.stateId"].value = "";
		document.forms[0]["billingAddress.zipCode"].value = "";
		document.forms[0]["billingAddress.additionalZipCode"].value = "";	*/
		show('billing1', 1,"row");
	}
}

function showBillingWithoutValues(el)
{
	if (el.checked)
	  show('billing1', 0,"row" );	
	else 
	  show('billing1', 1,"row");
}

function getGenericLogons(theForm)
{
	theForm.action = "/<msp:webapp/>displayJuvServiceProviderCreateUpdateSummary.do?submitAction=Find";
	document.forms[0].submit();
}

function load(file,target) 
{
  window.location.href = file;
}

function checkInHouse()
{
	if(document.forms[0].isInHouse[1].checked)
	{
		show('genericLogons', 1, 'row');
	}
	else
	{
		show('genericLogons', 0);
	}
}


</script>
</head>
<!--END HEADER TAG-->
<!--BEGIN BODY TAG-->
<body onload="javascript:showBillingWithoutValues(document.forms[0].check);" onKeyDown="checkEnterKey(event,true)">


<!-- BEGIN FORM -->
<html:form action="/displayJuvServiceProviderCreateUpdateSummary" target="content" focus="providerName">
<logic:equal name="serviceProviderForm" property="actionType" value="createProvider">
  <input type="hidden" name="helpFile" value="commonsupervision/asp_hcjpd/Administer_Service_Provider.htm#|367">          
</logic:equal>
<logic:equal name="serviceProviderForm" property="actionType" value="updateProvider">
  <input type="hidden" name="helpFile" value="commonsupervision/asp_hcjpd/Administer_Service_Provider.htm#|365">               
</logic:equal>
	<div align="center">


	<!-- BEGIN OUTERMOST TABLE -->
	<table width="98%" border="0" cellpadding="0" cellspacing="0" >
		<tr>
			<td valign=top><img src="/<msp:webapp/>images/spacer.gif" height=5></td>    	
	  	</tr>
	  	<tr>
    	<td valign=top align=center>
    	<!-- BEGIN BLUE TAB TABLE -->
			<table width='100%' border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign=top>
						<!--tabs start-->
							<tiles:insert page="../../common/commonSupervisionTabs.jsp" flush="true">
								<tiles:put name="tabid" value="suggestedOrderTab"/>
							</tiles:insert>													
						<!--tabs end-->
					</td>
				</tr>
				<tr>
					<td bgcolor='#6699FF'><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
				</tr>				
			</table>
		<!-- END BLUE TAB TABLE -->
		<!--BEGIN OUTER BLUE TABLE -->
		<table class=borderTableBlue cellpadding=0 cellspacing=0 border=0 width="100%">
				<tr>
				  <td valign=top align=center>
			    <!-- BEGIN HEADING TABLE -->
					<div class=spacer></div>
				<table align="center" >
					<tr>
				      <td class="header" align="center">
				      <logic:equal name="serviceProviderForm" property="actionType" value="createProvider">
				          <bean:message key="prompt.create"/>          
				      </logic:equal>
				       <logic:equal name="serviceProviderForm" property="actionType" value="updateProvider">
				          <bean:message key="prompt.update"/>               
				      </logic:equal>
				         <bean:message key="title.serviceProvider"/>               
				      </td>	
					</tr>
				</table>
				<!-- END HEADING TABLE -->

				<!-- BEGIN INSTRUCTION TABLE --> 
				<table width="98%" align="center" cellpadding=1 cellspacing=1>
					<tr>
				    <td>
				      <ul>
		           	<li>Enter the required fields and click Submit to view summary.</li>     
				      </ul>
				    </td>
				  </tr>
				  
				   <tr>
				     <td class="required"><bean:message key="prompt.3.diamond"/> <bean:message key="prompt.requiredFieldsInstruction" />&nbsp;&nbsp;&nbsp;<bean:message key="prompt.dateFieldsInstruction"/></td>
				   </tr>
				</table>
				<!-- END OF INSTRUCTION TABLE -->
				<!-- BEGIN ERRORS TABLE -->
				<table width="100%">
					<tr>		  
						<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
					</tr>   	  
				</table>
				<!-- END ERRORS TABLE -->
				
				 <!-- BEGIN INNER BLUE TABLE -->
		     <table class=borderTableBlue cellpadding=2 cellspacing=0 border=0 width='98%' align="center">
			     <tr>
				 	 <td class="detailHead" nowrap="nowrap"><bean:message key="title.serviceProvider" /> <bean:message key="prompt.info" /></td>
				  </tr>
			       <tr>
			         <td>
			           <table width="100%" border="0" align="center" cellpadding="2" cellspacing="1">			         		   							       
							<tr>
				             	<td class="formDeLabel"  nowrap="nowrap" width="1%"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.name" /></td>
						    	<td class="formDe" colspan=3><html:text name="serviceProviderForm" property="providerName" size="56" maxlength="100"/></td>
						    </tr> 
				         	 <tr>
  			              		<td class="formDeLabel" nowrap="nowrap" width="1%">
					    		  	<logic:notEqual name="serviceProviderForm" property="actionType" value="updateProvider">
		  			             		<bean:message key="prompt.3.diamond"/>
		  			            	</logic:notEqual>
		  			            <bean:message key="prompt.start" /> <bean:message key="prompt.date" />
		  			          	</td>

	  			              <logic:equal name="serviceProviderForm" property="actionType" value="createProvider">
		  			    		      <td class="formDe">			  			    		    
							    		      <html:text name="serviceProviderForm" property="startDate" styleId="startDate" size="10" maxlength="10"/>						    		      
					    		      </td>
						      </logic:equal>

						     <logic:notEqual name="serviceProviderForm" property="actionType" value="createProvider">
							    		      <td class="formDe"><bean:write name="serviceProviderForm" property="startDate"/></td>
						     </logic:notEqual>
						    		     
						    		      <td class="formDeLabel" nowrap="nowrap" width="1%">
							    		      <logic:notEqual name="serviceProviderForm" property="actionType" value="updateProvider">
				  			              <bean:message key="prompt.3.diamond"/>
				  			            </logic:notEqual>
							    		      <bean:message key="prompt.inHouse" /> 
						    		      </td>

					    		       <logic:equal name="serviceProviderForm" property="actionType" value="createProvider">
						    		      <td class="formDe" >Yes <html:radio name="serviceProviderForm" property="isInHouse" value="Yes"/>
						    		     					No <html:radio name="serviceProviderForm" property="isInHouse" value="No"/></td>
					    		      </logic:equal>

					    		       <logic:equal name="serviceProviderForm" property="actionType" value="updateProvider">
					    		       	  <td class="formDe" ><bean:write name="serviceProviderForm" property="isInHouse"/></td>
					    		     	</logic:equal>
						     </tr> 

						         	<logic:equal name="serviceProviderForm" property="actionType" value="createProvider">
							         	<tr>
						         		  <td  class="formDeLabel"  nowrap="nowrap" width="1%"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.admin" /> <bean:message key="prompt.userId" />
						         		  </td>
								      	  <td class="formDe">
								      		  <html:text name="serviceProviderForm" property="adminContactId" size="5" maxlength="5" styleId="adminContactId"/>
														<%-- <html:select property="adminContactId" name="serviceProviderForm">
														   <html:option value=""><bean:message key="select.generic" /></html:option>  			            	  	
														   <html:optionsCollection property="genericLogonIds" value="logonId" label="logonId"  name="serviceProviderForm"/>
														</html:select>  --%> 
				               		    </td> 
				               		    <td  class="formDeLabel"  nowrap="nowrap" width="1%"><bean:message key="prompt.maxYouth" /></td>
				               		    
								      	<td class="formDe">
								      	   <html:text name="serviceProviderForm" property="maxYouth" size="5" maxlength="5" styleId="maxYouthId"/>
										</td>
				                 <%--  <td  class="formDeLabel"  nowrap="nowrap" width="1%"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.contact" /> <bean:message key="prompt.userId" /></td>

								      	  <td class="formDe">
								      	   			<html:text name="serviceProviderForm" property="providerName" size="5" maxlength="5"/>  <input type=button value="<bean:message key='button.validate' />"
														<html:select property="contactUserId" name="serviceProviderForm">
														   <html:option value=""><bean:message key="select.generic" /></html:option>  			            	  	
														   <html:optionsCollection property="genericLogonIds" value="logonId" label="logonId"  name="serviceProviderForm"/>
														</html:select>
														  
				                  </td>  --%>
						            </tr> 
							        
							        
						         	 </logic:equal>
						         	 <logic:equal name="serviceProviderForm" property="actionType" value="updateProvider">
							         	<tr>
						         		  <td  class="formDeLabel"  nowrap="nowrap" width="1%">
							    		      <logic:notEqual name="serviceProviderForm" property="actionType" value="updateProvider">
				  			              <bean:message key="prompt.3.diamond"/>
				  			            </logic:notEqual>
						         		  	<bean:message key="prompt.admin" /> <bean:message key="prompt.userId" />
						         		  </td>
								      	  <td class="formDe"><bean:write property="adminContactId" name="serviceProviderForm"/></td> 
				                 <%--  <td  class="formDeLabel"  nowrap="nowrap" width="1%">
							    		      <logic:notEqual name="serviceProviderForm" property="actionType" value="updateProvider">
				  			              <bean:message key="prompt.3.diamond"/>
				  			            </logic:notEqual>
					                  <bean:message key="prompt.contact" /> <bean:message key="prompt.userId" />
				                  </td> --%>
								      	  <%-- <td class="formDe">
														<bean:write property="contactUserId" name="serviceProviderForm"/></td> --%> 
														<td  class="formDeLabel"  nowrap="nowrap" width="1%"><bean:message key="prompt.maxYouth" /></td>
				               		    
								      	<td class="formDe">
								      	   <html:text name="serviceProviderForm" property="maxYouth" size="5" maxlength="5" styleId="maxYouthId"/>
										</td>
						            </tr> 							        							         
						         	 </logic:equal>

				         	     <tr>
						              <td class="formDeLabel"  nowrap="nowrap" width="1%">Email</td>
						    		      <td class="formDe" colspan=1><html:text name="serviceProviderForm" property="email" styleId="email" /></td> 
						    		    
						    		     <td class="formDeLabel" nowrap="nowrap" width="1%">Send Email Notification</td>
						    		     	<td class="formDe" >Yes <html:radio name="serviceProviderForm" property="isEmailCheck" value="YES"/>
						    		     		No <html:radio name="serviceProviderForm" property="isEmailCheck" value="NO"/></td>
  			
						    		            
						         	</tr> 
						         	<tr> 
							          <td class="formDeLabel"  nowrap="nowrap" width="1%"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.phone" /></td>
									      <td class="formDe" colspan=3>
									         <html:text name="serviceProviderForm" property="phoneNum.areaCode" size="3" maxlength="3" onkeyup="return autoTab(this, 3);" />-
									         <html:text name="serviceProviderForm" property="phoneNum.prefix" size="3" maxlength="3" onkeyup="return autoTab(this, 3);" />-
									         <html:text name="serviceProviderForm" property="phoneNum.last4Digit" size="4" maxlength="4" onkeyup="return autoTab(this, 4);" />
								         <bean:message key="prompt.ext"/> <html:text name="serviceProviderForm" property="phoneNum.ext" size="4" maxlength="4" onkeyup="return autoTab(this, 4);" />
								      </td>
							        </tr> 
							        <tr> 
							          <td class="formDeLabel"  nowrap="nowrap" width="1%"><bean:message key="prompt.fax" /></td>
									      <td class="formDe" colspan=3>
								         <html:text name="serviceProviderForm" property="fax.areaCode" size="3" maxlength="3" onkeyup="return autoTab(this, 3);" />-
								         <html:text name="serviceProviderForm" property="fax.prefix" size="3" maxlength="3" onkeyup="return autoTab(this, 3);" />-
								         <html:text name="serviceProviderForm" property="fax.last4Digit" size="4" maxlength="4" onkeyup="return autoTab(this, 4);" />							    								         
								      </td>
							        </tr> 
							         <tr>
							              <td class="formDeLabel"  nowrap="nowrap" width="1%"><bean:message key="prompt.website" /></td>
						    		      <td class="formDe" colspan=3><html:text name="serviceProviderForm" property="webSite" size="30" maxlength="50"/></td>    		         
						         	</tr> 
						         	 <tr>
							              <td class="formDeLabel"  nowrap="nowrap" width="1%"><%-- <bean:message key="prompt.ftp" /> --%> Notes</td>
						    		      <td class="formDe" colspan=3><html:textarea name="serviceProviderForm" property="ftp" rows="12" cols="75" /></td>    		         
						         	</tr> 
										</table>						

										<div class=spacer4px></div>
										<table width="100%" border="0" align="center" cellpadding="1" cellspacing="1">
						          <tr>
                        <td class="detailHead" colspan=4>
                          <table width="100%" cellpadding=0 cellspacing=1 border=0>
                            <tr class="detailHead">
                              <td nowrap="nowrap"><bean:message key="prompt.mailingAddress" /></td>
                              <td align=right nowrap="nowrap" class="detailHead"><bean:message key="prompt.addressStatus"/>: 

                               <span class="errorAlert">
																	<%-- TODO replace logic tags with code table values --%>	 
																	<logic:equal name="serviceProviderForm" property="mailingAddrStatus" value="">
												      	       	UNPROCESSED
												   	       </logic:equal>       	    
												   	       <logic:equal name="serviceProviderForm" property="mailingAddrStatus" value="U">
												      	       	UNPROCESSED
												   	       </logic:equal>
												   	       <logic:equal name="serviceProviderForm" property="mailingAddrStatus" value="Y">
												       	       	VALID
												   	       </logic:equal>
												   	       <logic:equal name="serviceProviderForm" property="mailingAddrStatus" value="N">
												       	       	INVALID
												   	       </logic:equal>
												   	       &nbsp;</span>
													   	    </td>
                              	</tr>
	                              <tr class="detailHead">
	                                <td colspan=2 align=center nowrap="nowrap">
	                                 <input type=button value="<bean:message key='button.validate' />" onClick="return validateAddrAction('serviceProviderForm','/<msp:webapp/>validateAddress.do','', 'mailingAddress','/jsp/administerServiceProvider/administerServiceProviderHCJPD/serviceProviderCreate.jsp', true);"></input>
	                                     <html:button property="org.apache.struts.taglib.html.BUTTON" onclick="return displayResearchWindow();">
																		  <bean:message key="button.research"></bean:message>
																   </html:button>	
			                                </td>
			                              </tr>
			                            </table>
			                          </td>
			                        </tr>
												  	  <tr>
															<td class="formDeLabel" nowrap="nowrap" width="1%"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.streetNumber"/></td>										
															<td class="formDeLabel" nowrap="nowrap" ><bean:message key="prompt.3.diamond"/><bean:message key="prompt.streetName"/></td>									
															<td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.streetType"/></td>										
															<td class="formDeLabel" nowrap="nowrap" width="1%"><bean:message key="prompt.aptSuite"/></td>									
													  </tr>
													  <tr>
															<td class="formDe" width="1%"><html:text name="serviceProviderForm" property="mailingAddress.streetNumber" size="15" maxlength="9" /></td>
															<td class="formDe" ><html:text name="serviceProviderForm" property="mailingAddress.streetName" size="30" maxlength="50" /></td>
															<td class="formDe"><html:select	name="serviceProviderForm" property="mailingAddress.streetTypeId" size="1">
																	<option value=""><bean:message key="select.generic"/></option>
											    					<html:optionsCollection name="serviceProviderForm"
																			property="streetTypeList"  value="code" label="description"/>
											  						</html:select></td>
															<td class="formDe"><html:text name="serviceProviderForm"
																	property="mailingAddress.aptNumber" size="10" maxlength="10" /></td>
														</tr>
														<tr>
															<td class="formDeLabel" nowrap="nowrap" width="1%"> <bean:message key="prompt.3.diamond"/><bean:message key="prompt.city"/></td>
															<td class="formDeLabel" nowrap="nowrap"> <bean:message key="prompt.3.diamond"/><bean:message key="prompt.state"/></td>
															<td class="formDeLabel" nowrap="nowrap" colspan=6><bean:message key="prompt.3.diamond"/><bean:message key="prompt.zipCode"/></td>
															
														</tr>	
														<tr>
															<td class="formDe"><html:text name="serviceProviderForm" property="mailingAddress.city" size="20"
																maxlength="25"></html:text></td>
															<td class="formDe">
																<html:select	name="serviceProviderForm" property="mailingAddress.stateId" size="1">
																	<option value="" selected="selected"><bean:message key="select.generic"/></option>
																	<html:optionsCollection name="serviceProviderForm" property="stateList"  value="code" label="description"/>
																</html:select>
															</td>
															<td class="formDe" colspan=6><html:text name="serviceProviderForm"
																property="mailingAddress.zipCode" size="5" maxlength="9"
																onkeyup="return autoTab(this, 5);" /> - <html:text name="serviceProviderForm" property="mailingAddress.additionalZipCode" size="4" maxlength="4"
																			onkeyup="return autoTab(this, 4);" />
															</td> 
						                </tr> 
						              </table> 
						              <!--MAILING ADDRESS TABLE --> 
						              <div class=spacer4px></div> 
						              <!--BILLING ADDRESS TABLE --> 
						              <table width="100%" border="0" align="center" cellpadding="1" cellspacing="1"> 
						                <tr> 
						                  <td class="detailHead" colspan=4>
															   <table width="100%" cellpadding=0 cellspacing=1> 
						                      <tr class="detailHead"> 
						                        <td nowrap="nowrap"><bean:message key="prompt.billingAddress" /> &nbsp;&nbsp;&nbsp;Same as Mailing Address
						                          <input type="checkbox" name="check" onclick="showBilling(this)"></td> 
						                        <td align=right nowrap="nowrap" class="detailHead"><bean:message key="prompt.addressStatus"/>: <span class="errorAlert"> 
						                          <%-- TODO replace logic tags with code table values --%> 
						                          <logic:equal name="serviceProviderForm" property="billingAddrStatus" value=""> UNPROCESSED </logic:equal> <logic:equal name="serviceProviderForm" property="billingAddrStatus" value="U"> UNPROCESSED </logic:equal> <logic:equal name="serviceProviderForm" property="billingAddrStatus" value="Y"> VALID </logic:equal> <logic:equal name="serviceProviderForm" property="billingAddrStatus" value="N"> INVALID </logic:equal> &nbsp; </span> </td> 
						                      </tr> 
						                      <tr class="detailHead"> 
						                        <td colspan=2 align=center nowrap="nowrap"> <input type=button value="<bean:message key='button.validate' />" onClick="return validateAddrAction('serviceProviderForm','/<msp:webapp/>validateAddress.do','', 'billingAddress','/jsp/administerServiceProvider/administerServiceProviderHCJPD/serviceProviderCreate.jsp',true);"> 
						                          </input> 
						                          <html:button property="org.apache.struts.taglib.html.BUTTON" 
														        				 onclick="return displayResearchWindow();"> <bean:message key="button.research"></bean:message> </html:button> </td> 
								                    </tr> 
								                  </table></td> 
								                </tr> 
								                <tr id='billing1' class='hidden'> 
								                  <td> 
								                  	<table> 
								                      <tr > 
								                        <td class="formDeLabel" nowrap="nowrap" width="1%"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.streetNumber"/></td> 
								                        <td class="formDeLabel" nowrap="nowrap" colspan="2"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.streetName"/></td> 
								                      </tr> 
								                      <tr> 
								                        <td class="formDe" width="1%"><html:text name="serviceProviderForm" property="billingAddress.streetNumber" size="15" maxlength="15" /></td> 
								                        <td class="formDe" colspan="2"><html:text name="serviceProviderForm" property="billingAddress.streetName" size="30" maxlength="50" /></td> 
								                      </tr> 
								                      <tr> 
								                        <td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.streetType"/></td> 
								                        <td class="formDeLabel" nowrap="nowrap" width="1%" colspan="2"><bean:message key="prompt.aptSuite"/></td> 
								                      </tr> 
								                      <tr> 
								                        <td class="formDe"><html:select	name="serviceProviderForm" property="billingAddress.streetTypeId" size="1"> <html:option value=""><bean:message key="select.generic"/></html:option> <html:optionsCollection name="serviceProviderForm"
																						property="streetTypeList"  value="code" label="description"/> </html:select></td> 
								                        <td class="formDe" colspan="2"><html:text name="serviceProviderForm"
																				property="billingAddress.aptNumber" size="10" maxlength="10" /></td> 
								                      </tr> 
								                      <tr> 
								                        <td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.city"/></td> 
								                        <td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.state"/></td> 
								                        <td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.zipCode"/></td> 
								                      </tr> 
								                      <tr> 
								                        <td class="formDe"><html:text name="serviceProviderForm" property="billingAddress.city" size="20" maxlength="25"></html:text></td> 
								                        <td class="formDe">
								                        	<html:select	name="serviceProviderForm" property="billingAddress.stateId" size="1"> 
								                        		<html:option value=""><bean:message key="select.generic"/></html:option> 
								                        		<html:optionsCollection name="serviceProviderForm" property="stateList"  value="code" label="description"/> </html:select></td> 
								                        <td class="formDe" nowrap="nowrap">
								                        	<html:text name="serviceProviderForm" property="billingAddress.zipCode" size="5" maxlength="9"
																							onkeyup="return autoTab(this, 5);" /> - <html:text
																							name="serviceProviderForm" property="billingAddress.additionalZipCode" size="4" maxlength="4"
																							onkeyup="return autoTab(this, 4);" />
																				</td> 
								                      </tr> 
							                                
							                    </table> <!--END BILLING ADDRESS TABLE --> 
							                    <!-- END OF INNER BLUE TABLE --> </td> 
							                </tr> 
							              </table> 
							              <!-- END OUTER BLUE TABLE --> 
							              <%-- BEGIN HIDDEN FIELDS FOR ADDRESS VALIDATION --%> 
							              <table width="100%"> 
							                <tr> 
							                  <td> <html:hidden property="validStreetNum" value="" /> <html:hidden property="validStreetName" value="" /> <html:hidden property="validZipCode" value="" /> <html:hidden property="validAddrNum" value="" /> <html:hidden property="inputPage" value="" /> <html:hidden property="currentAddressInd" value="" /> </td> 
							                </tr> 
							              </table> 
							              <%-- ENd HIDDEN FIELDS FOR ADDRESS VALIDATION --%> 
							              <!-- ************************************************************ --> 

              <!-- BEGIN BUTTON TABLE --> 
              <br> 
              <table border="0" width="98%"> 
                  <tr> 
                    <td align="center"> 
                    <html:submit property="submitAction"> <bean:message key="button.back"></bean:message> </html:submit> 
                    <html:submit property="submitAction" styleId="btnNext" onclick="showBilling(document.forms[0].check);"> <bean:message key="button.next"></bean:message> </html:submit> 
                    <html:reset><bean:message key="button.reset"></bean:message></html:reset>
                    <logic:equal name="serviceProviderForm" property="actionType" value="updateProvider"> <logic:equal name="serviceProviderForm" property="statusId" value="P"> <jims2:isAllowed requiredFeatures="CS-ASP-INACTIVATEJUV"> <html:submit property="submitAction"> <bean:message key="button.inactivate"></bean:message> </html:submit> </jims2:isAllowed> </logic:equal> </logic:equal>
                     <html:submit property="submitAction"> <bean:message key="button.cancel"></bean:message> </html:submit></td> 
                  </tr> 
              </table> 
              <!-- end button table --> </td> 
          </tr> 
        </table> 
        <!-- END OUTERMOST TABLE --> 
    </html:form> 
<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
