<!DOCTYPE HTML>
<%-- User selects the "Add New Alias" button from Juvenile Profile Master Details Main tab --%>
<%--MODIFICATIONS --%>
<%-- 07/20/2009 C Shimek    #61004 added timeout.js  --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/struts-nested.tld" prefix="nest"%>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="naming.PDCodeTableConstants" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>




<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<%--FOR STRUTS & JAVASCRIPTS VALIDATIONS--%>
<html:javascript formName="juvenileAliasInfoForm" />

<%-- Javascript for emulated navigation --%>
<title><bean:message key="title.heading"/> - juvenileAliasInfoCreate.jsp</title>

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/juvenileProfileInfo.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
</head> 
<%--END HEADER TAG--%>

<%--BEGIN BODY TAG and FORM --%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin='0' leftmargin="0">

<html:form action="/displayJuvenileAliasInfoCreateSummary" target="content">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|302">

<%-- BEGIN HEADING TABLE --%>
<table width="98%">
 	<tr>
   	<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - Create Alias</td> 
 	</tr>  	
</table>
<%-- END HEADING TABLE --%>


<%-- BEGIN INSTRUCTION TABLE --%>
<table width="98%" border="0">
	<tr>
	  <td>
	    <ul>
				<li>Please enter required fields. </li>
				<li>Select the Next button to view the Summary screen</li> 
	    </ul>
	  </td>  
	</tr>
  <tr> 
	  <td class="required"><bean:message key="prompt.2.diamond"/>&nbsp;Required Fields</td> 
	</tr> 
</table>
<%-- END INSTRUCTION TABLE --%>

<%--juv profile header start--%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
  <tiles:put name="headerType" value="profileheader"/>
</tiles:insert>
<%--juv profile header end--%>

<%-- BEGIN DETAIL TABLE --%>  
<div class="spacer"></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
   	<td valign="top">
   		<table width='100%' border="0" cellpadding="0" cellspacing="0" >
     		<tr>
     			<td valign="top">
       			<tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
       			<tiles:put name="tabid" value="maintab"/>
       			<tiles:put name="juvNumId" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
       			</tiles:insert>	
     			</td>
     		</tr>
    		<tr>
     			<td bgcolor="#33cc66"><img src="/<msp:webapp/>images/spacer.gif" width="5"></td>
    		</tr>
  		</table>

			
			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
				<tr>
					<td valign="top" align="center">	
						<%-- BEGIN ALIAS INFO TABS OUTER TABLE --%>
						<div class="spacer"></div>
						<table width='98%' border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td>
									<%-- BEGIN ALIAS INFO TABS INNER TABLE --%>
									<table width='100%' border="0" cellpadding="0" cellspacing="0" >
										<tr>
											<td valign="top">
											<%--tabs start--%>
												<tiles:insert page="../caseworkCommon/interviewInfoTabs.jsp" flush="true">
													<tiles:put name="tabid" value="profileInfo"/>
													<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
												</tiles:insert>	
											<%--tabs end--%>
											</td>
										</tr>
										<tr>
									  	<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" width="5"></td>
								  	</tr>
							  	</table>
									<%-- END ALIAS INFO TABS INNER TABLE --%>

								 <table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
      							<tr>
        							<td valign="top" align="center">
          							<!-- BEGIN ALIAS INFO TABLE -->
												<div class='spacer'></div>
         								<table width='98%' border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
       										<tr>
       											<td class="detailHead">Alias Information</td>
       										</tr>
       										<tr>
       											<td valign="top">
			        								<table width='100%' cellpadding='2' cellspacing='1' border="0">
                       					<tr> 
                         					<td class="formDeLabel" valign="top" nowrap="nowrap">Alias Name</td> 
                         					<td valign="top" class="formDe">
			      												<table border='0' cellspacing='1' width='100%'> 
                           						<tr> 
                           							<td class="formDeLabel" colspan='2'><bean:message key="prompt.2.diamond"/>Last</td> 
                           						</tr> 
                           						<tr> 
                           							<td class="formDe" colspan='2'><input type="text" name="lastName" size="30" maxlength="30"></td> 
                           						</tr> 
                           						<tr> 
                           							<td class="formDeLabel">First</td> 
                           							<td class="formDeLabel">Middle</td> 
                           						</tr> 
                           						<tr> 
                           							<td class="formDe"><input type="text" name="firstName" size="28" maxlength="28" value="" ></td> 
                           							<td class="formDe"><input type="text" name="middleName" size="12" maxlength="12" value=""></td> 
                           						</tr> 
                         						</table>
			      											</td> 
                       					</tr> 
			        									<tr>
			        										<td class="formDeLabel"><bean:message key="prompt.2.diamond"/>Type</td>
			        										<td class="formDe">
																		<html:select size="1" name="juvenileAliasInfoForm" property="juvenileType" >
																		<html:option value=""><bean:message key="select.generic" /></html:option>
																		<html:optionsCollection name="juvenileAliasInfoForm" property="aliasCodes" value="code" label="descriptionWithCode" />
																		</html:select>
																	</td>
			        									</tr>
			        									<tr>
			        										<td width="1%" class="formDeLabel">Notes</td>
			        										<td class="formDe"><input type="text" name="notes" size="50" maxlength="50"></td>
			        									</tr>
			        								</table>
			        							</td>
			        						</tr>
                  			</table><!-- END PHYSICAL CHARACTERISTICS INFO TABLE -->

                        <!-- BEGIN BUTTON TABLE -->
                  			<div class="spacer"></div>
                  			<table border="0" width="100%">
                    			<tr>
                     				<td align="center">
															<html:button property="button.back" styleId="aliasCreateBack">
																<bean:message key="button.back"></bean:message>
													  	</html:button>				
															<html:submit property="submitAction" styleId="aliasValidation">				
																<bean:message key="button.next"></bean:message>
															</html:submit>
															<html:submit property="submitAction">
																<bean:message key="button.cancel"></bean:message>
															</html:submit>
                    				</td>
			                    </tr>
			                  </table>
			                  <div class="spacer"></div>
	        		          <!-- END BUTTON TABLE -->
											</td>
		              	</tr> 
			            </table>
									<div class="spacer"></div>
									<%-- END ALIAS INFO TABS OUTER TABLE --%>
     						</td>
     					</tr>
   					</table>
    				</td>
  				</tr>
			</table>
		</td>
	</tr>
</table>
<%-- END DETAIL TABLE --%>

<%-- END FORM --%>
</html:form>
</body>

<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</html:html>
