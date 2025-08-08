<!DOCTYPE HTML>
<!-- Used to search for Security Administrator Roles. -->
<!-- MODIFICATIONS -->
<!-- CShimek 03/30/2005	 Create JSP -->
<!-- CShimek 02/13/2006  Corrected prompt values for required field indicator --> 
<!-- CShimek 03/16/2006  #29708 removed required field markers and revised instruction for either field for search --> 
<!-- CShimek 03/30/2006  Per ER#26357, revised reset button to refresh button -->
<!-- CShimek 07/10/2006  Activity #32909, relocate error message between instructions and search block -->
<!-- CShimek 01/12/2007  #38306 add multiple submit functionality  -->
<!-- CShimek 06/26/2007  added pagination -->
<!-- LDeen	 08/01/2007  Defect #44219 removed call to validation.xml -->
<!-- CShimek 02/05/2009  #56860 add Back to Top  -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>
<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<!--BEGIN HEADER TAG-->
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />

<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/security.css" />

<html:base /> 
<title><bean:message key="title.heading"/> - roleSASearch.jsp</title>

<%-- Defect #44219<html:javascript formName="SARoleForm" /> --%>

<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/saRoles/roleSASearch.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/wildCardSearch.js" ></script>

</head>
<!--END HEADER TAG-->

<!--BEGIN BODY TAG-->
<body onKeyDown="return checkEnterKeyAndSubmit(event,true)">

<!-- BEGIN HEADING TABLE -->
<table align="center" width="100%">
	<tr>
		<td align="center" class="header">
			<bean:message key="prompt.sa"/> <bean:message key="prompt.role"/> <bean:message key="title.search"/>
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
<!-- BEGIN INSTRUCTION TABLE -->
<table width="98%" border="0">
	<tr>
		<td>
			<ul>
				<li>Enter Role Name or Agency Name then select Find to search.</li>
				<li>Select Create New SA Role to go to create page.</li>
			</ul>
		</td>
	</tr>
</table>
<!-- END INSTRUCTION TABLE -->
			
<!-- BEGIN OVERALL TABLE -->
<table width="100%" border="0" cellpadding="0" cellspacing="0">
<html:form action="/displaySARoleSearchResults" target="content" focus="roleName">	
<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|153">	
    <tr>
		<td width="98%" align="center" valign="top">
			<table class="borderTableBlue" cellpadding="2" cellspacing="0" width="98%">
				<tr>
					<td class="detailHead"><bean:message key="prompt.searchSARole" /></td>
				</tr>
				<tr>
					<td align="center">
<!-- BEGIN SEARCH TABLE -->
						<table border="0" cellspacing="1" cellpadding="2" width="100%">
							<tr>
								<td class="formDeLabel">
								     <bean:message key="prompt.roleName"/></td>
								<td class="formDe"><html:text property="roleName" size="30" maxlength="30"/></td>								
							</tr>
							<tr>  
								<td class="formDeLabel" nowrap><bean:message key="prompt.agencyName"/></td>
								<td class="formDe">
								  <html:text property="agencyName" size="62" maxlength="60"/>
	        				     </td>
	        			    </tr>
	        			    <tr>
	        			       <td class="formDeLabel"></td>
	        			       <td class="formDe">	        
	  	                         <html:submit property="submitAction" onclick="return validateFields(this.form) && disableSubmit(this, this.form);">
	  	                         	<bean:message key="button.find"></bean:message>
	  	                         </html:submit> 
	  	                         <html:submit property="submitAction"><bean:message key="button.refresh"></bean:message></html:submit> 	  	                         
							   </td>								
							</tr>
						</table>
<!-- END SEARCH TABLE -->
					</td>
				</tr>
<!-- BEGIN PAGINATION HEADER TAG -->
<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define> 

<pg:pager
    index="center"
    maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
    maxIndexPages="10"
    export="offset,currentPageNumber=pageNumber"
    scope="request">
 <%--   <input type="hidden" name="pager.offset" value="<%= offset %>"> --%>
<!-- END PAGINATION HEADER TAG -->				
<!--  BEGIN ALLSECURITYADMINROLES ONLY DISPLAY - should on be 1 row display -->	
				<logic:empty name="roleSAForm" property="roles">
                	<logic:notEmpty name="roleSAForm" property="allSecurityAdminRoles">
		                <tr>
        			        <td>
								<table width="98%" cellpadding="0" cellspacing="0" >		
									<tr><td>&nbsp;</td></tr>
									<tr>
									    <td align="center">
											<bean:write name="roleSAForm" property="searchResultSize"/> search results found. 			    
									    </td>
									</tr>
		    						<tr>
						    			<td>		
											<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTable">	
	    		            					<tr>
											    	<td align="center">
			    				        	       	    <table border="0" width="100%" cellspacing="1" cellpadding="4">
														    <tr class="formDeLabel">
											    				<td><bean:message key="prompt.saRoleName"/>
															        <jims:sortResults beanName="roleSAForm" results="allSecurityAdminRoles" 
                					                		            primaryPropSort="roleName" primarySortType="STRING" defaultSort="true" 
                    	        				            		    defaultSortOrder="ASC" sortId="1" levelDeep="1" />
                    	        				            	 </td>
											    				<td><bean:message key="prompt.agency"/>
															        <jims:sortResults beanName="roleSAForm" results="allSecurityAdminRoles" 
                							        	                primaryPropSort="agencyName" primarySortType="STRING" defaultSort="false" 
                        		    						            defaultSortOrder="ASC" sortId="2" levelDeep="1" />
																</td>
															    <td width="1%"></td>
														    </tr>
														    <logic:iterate id="allSecAdminIndex" name="roleSAForm" property="allSecurityAdminRoles">
																<pg:item>
																	<tr bgcolor="#ccffcc">
										 								<td><a href="/<msp:webapp/>handleSARoleSelection.do?action=view&roleId=<bean:write name="allSecAdminIndex" property="roleId"/>" title='View <bean:write name="allSecAdminIndex" property="roleName"/> details'><bean:write name="allSecAdminIndex" property="roleName"/></a></td>
																 		<td>&nbsp;</td>
																		<td><a href="/<msp:webapp/>handleSARoleSelection.do?action=update&roleId=<bean:write name="allSecAdminIndex" property="roleId"/>" title='Edit <bean:write name="allSecAdminIndex" property="roleName"/>'><bean:message key="prompt.edit"/></a></td> 
																	</tr>
																</pg:item>
															</logic:iterate>
					    			            	    </table>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</td>
						</tr>			
					</logic:notEmpty>	                	    
          		</logic:empty>
<!--  END ALLSECURITYADMINROLES ONLY DISPLAY -->	                
<!--  BEGIN ROLES ONLY DISPLAY -->
				<logic:notEmpty name="roleSAForm" property="roles"> 
               		<tr>
                		<td>			
							<table width="100%" cellpadding="0" cellspacing="0">                				
								<tr><td>&nbsp;</td></tr>
								<tr>
								    <td align="center">
										<bean:write name="roleSAForm" property="searchResultSize"/> search results found.
								    </td>
								</tr>
					    		<tr>
		    						<td>		
										<table width="100%" border="0" cellpadding="0" cellspacing="0" class='borderTable'>	
						        	        <tr>
											    <td align="center">
				        			           	    <table border="0" width="100%" cellspacing="1" cellpadding="4">
													    <tr class="formDeLabel">
							    							<td><bean:message key="prompt.saRoleName"/>
														        <jims:sortResults beanName="roleSAForm" results="roles" 
            			    				                        primaryPropSort="roleName" primarySortType="STRING" defaultSort="true" 
                        			        				        defaultSortOrder="ASC" sortId="1" /></td>
														    <td><bean:message key="prompt.agency"/>
											    			    <jims:sortResults beanName="roleSAForm" results="roles" 
			                                				        primaryPropSort="agencyName" primarySortType="STRING" defaultSort="false" 
							                                        defaultSortOrder="ASC" sortId="2" /></td>
														    <td width="1%"></td>
													    </tr>
													    <logic:iterate id="allSecAdminIndex" name="roleSAForm" property="allSecurityAdminRoles">
															<tr bgcolor="#ccffcc">
														 		<td><a href="/<msp:webapp/>handleSARoleSelection.do?action=view&roleId=<bean:write name="allSecAdminIndex" property="roleId"/>" title='View <bean:write name="allSecAdminIndex" property="roleName"/> details'><bean:write name="allSecAdminIndex" property="roleName"/></a></td>
							 									<td>&nbsp;</td>
																<td><a href="/<msp:webapp/>handleSARoleSelection.do?action=update&roleId=<bean:write name="allSecAdminIndex" property="roleId"/>" title='Edit <bean:write name="allSecAdminIndex" property="roleName"/>'><bean:message key="prompt.edit"/></a></td> 
															</tr>
														</logic:iterate> 
								        			    <logic:iterate id="saRoleNamesIndex" name="roleSAForm" property="roles" indexId="index">
															<pg:item>
																<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
																	<td><a href="/<msp:webapp/>handleSARoleSelection.do?action=view&roleId=<bean:write name="saRoleNamesIndex" property="roleId"/>" title='View <bean:write name="saRoleNamesIndex" property="roleName"/> details' ><bean:write name="saRoleNamesIndex" property="roleName"/></a></td>
																	<td><bean:write name="saRoleNamesIndex" property="agencyName"/></td>
																	<td><a href="/<msp:webapp/>handleSARoleSelection.do?action=update&roleId=<bean:write name="saRoleNamesIndex" property="roleId"/>" title='Edit <bean:write name="saRoleNamesIndex" property="roleName"/>' ><bean:message key="prompt.edit"/></a>&nbsp;|&nbsp;<a href="/<msp:webapp/>handleSARoleSelection.do?action=delete&roleId=<bean:write name="saRoleNamesIndex" property="roleId"/>" title='Delete <bean:write name="saRoleNamesIndex" property="roleName"/>' ><bean:message key="prompt.delete"/></a></td>
																</tr>
															</pg:item>
											            </logic:iterate>
                							        </table>
			                					</td>
            			    				</tr>   
                							<tr>
                								<td>
<!-- BEGIN PAGINATION NAVIGATOIN TABLE -->							
													<table align="center">
														<tr>
															<td>
																<pg:index>
																	<tiles:insert page="/jsp/jimsPagination.jsp" flush="true">
																		<tiles:put name="pagerUniqueName" value="pagerSearch"/>
																		<tiles:put name="resultsPerPage" value="<%=paginationResultsPerPage%>"/>
																	</tiles:insert>
																</pg:index>
															</td>
														</tr>
													</table>
<!-- END PAGINATION NAVIGATOIN TABLE -->	
												</td>
											</tr>
										</table>	
								    </td>
								</tr>
							</table>
						</td>
					</tr>
				</logic:notEmpty>
<!-- END ROLES ONLY DISPLAY -->			    
		    </table>
<!-- END BLUE BORDER TABLE -->		    
		</td>
	</tr>
	<tr>
		<td>
<!-- BEGIN BUTTON TABLE -->	
			<table align="center" width="98%">
				<tr>
					<td align="center">
						<html:submit property="submitAction" onclick="disableSubmit(this, this.form);">
							<bean:message key="button.createNewSARole"></bean:message>
						</html:submit>
					</td>
				</tr> 
			</table>
<!-- END BUTTON TABLE -->
		</td>
	</tr>
<!-- BEGIN PAGINATION CLOSING TAG -->
</pg:pager>
<!-- END PAGINATION CLOSING TAG -->
</html:form>	
</table>
<!-- END OVERALL TABLE -->
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>