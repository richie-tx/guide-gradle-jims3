<!DOCTYPE HTML>
<%-- Used in listing documents generated for a casefile --%>
<%-- 07/08/2008	KISHORE Created the JSP --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="naming.Features" %>




	<%--BEGIN HEAD TAG--%>
	<head>
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
		<title><bean:message key="title.heading"/> - casefileDocuments.jsp</title>
		<%-- Javascript for emulated navigation --%>
		<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
		<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script> 
		<script type='text/javascript' src="/<msp:webapp/>js/date.js"></script> 
	</head> 
	<%--END HEAD TAG--%>
	
	<body onKeyDown="return checkEnterKeyAndSubmit(event,true);" topmargin='0' leftmargin="0">
		<html:form action="/displayJuvenileCasefileDocuments" target="content">
		<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|197">
			<!-- BEGIN HEADING TABLE -->
			<table width='100%'>
			  <tr>
			    <td align="center" class="header" ><bean:message key="title.juvenileCasework"/> - Casefile Documents</td>
			  </tr>
			</table>
			<!-- END HEADING TABLE -->
			
			<!-- BEGIN INSTRUCTION TABLE -->
			<div class='spacer'></div>
			<table width="98%" border="0">
			  <tr>
			    <td>
			      <ul>
			        <li>Select a hyperlinked Document to view it.</li>
			      </ul>
			    </td>
			  </tr>
			</table>
			<!-- END INSTRUCTION TABLE -->
			
			<!-- BEGIN Juv Header TABLE -->
			<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
				<tiles:put name="headerType" value="casefileheader"/>
			</tiles:insert>
			<!-- End Header TABLE -->
			
			<%-- Begin Pagination Header Tag--%>
			<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define> 
			
			<pg:pager index="center" maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
			    maxIndexPages="10" export="offset,currentPageNumber=pageNumber" scope="request">
			<%-- End Pagination header stuff --%>
			
			<!-- BEGIN DETAIL TABLE -->
			<div class='spacer'></div>
			<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0">
			  <tr>
			    <td valign='top'>
				    <!-- Casefiles (1st row) tabs start -->
				    <tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
				    	<tiles:put name="tabid" value="casefiledetailstab"/>
				    	<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
				    </tiles:insert>		
				    <!-- Casefiles tabs end -->
			
		            <table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
			  			<tr>
			  			  <td valign='top' align='center'>
							
			            	<!-- Begin Casefile info (2nd row) Tabs -->
							<div class='spacer'></div>
			        		<table align="center" width='98%' border="0" cellpadding="0" cellspacing="0">
			   					<tr>
			   						<td valign='top'>
			   							<!-- tabs start -->
			   							<tiles:insert page="../caseworkCommon/casefileInfoTabs.jsp" flush="true">
			  								<tiles:put name="tabid" value="documentstab"/>
												<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
			  							</tiles:insert>	
			   							<!-- tabs end -->
			     					</td>
			     			    </tr>
			     				<tr>
			       			 		<td bgcolor='#33cc66'><img src="/<msp:webapp/>images/spacer.gif" width='5'></td>
			      				</tr>
			      			</table>
			            	<!-- End Casefile info (2nd row) Tabs -->
							
							<table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
				            	<tr>
					                <td valign='top' align='center'>
				   						<div class='spacer'></div>
				       					<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
				       						<tr> <!-- table title bar -->
				       							<td valign='top' colspan='2' class='detailHead'>Casefile Documents</td>
				       						</tr>
				     						<tr>
												<td>												
													<table width='100%' cellpadding="2" cellspacing="1">
								<!-- Commented out per defect #61812 so scanned documents link will display even when no other documents exist.  DAW -->					
													<%--logic:empty name="casefileDocumentsForm" property="documents">
															<tr bgcolor="#cccccc">
																<td colspan='6' class="subHead">No document(s) found.</td>
															</tr>
														 </logic:empty>
						   							 	 <logic:notEmpty name="casefileDocumentsForm" property="documents"--%>
															<%-- static header title row --%>
						                 					<tr bgcolor='#cccccc' class='subhead'>
																<td align='left'>
																	Document ID
															<!-- -36213 Add OID to documents --><jims2:sortResults beanName="casefileDocumentsForm" results="documents" primaryPropSort="reportId" primarySortType="INTEGER" sortId="3" />
																</td>
																
																<td align='left'>
																	Generate/Sent Date
																	<jims2:sortResults beanName="casefileDocumentsForm" results="documents" primaryPropSort="entryDate" primarySortType="DATE" defaultSort="true" defaultSortOrder="DESC" sortId="1" />
																</td>
																															
																
																<td align='left'>
																	Type
																	<jims2:sortResults beanName="casefileDocumentsForm" results="documents" primaryPropSort="reportType" primarySortType="STRING" sortId="2" />
																</td>
															</tr>
															 <jims2:isAllowed requiredFeatures='<%=Features.JCW_PS_SCANNED_DOCUMENTS%>'>
																<tr class="alternateRow">
																	<td width="50%">
																		<a href="javascript:openCustomRestrictiveWindow('http://svpjpdweb1:8086/Ecx.Web/en-US/do/root_HC_JuvenileDetail?appid=root_Juvenile_Documents&d=Juvenile_Documents_Production.tenant1&JuvenileNo=<bean:write name="casefileDocumentsForm" property="juvenileId"/>',800,700 );"/>
																			CLICK HERE
																		</a>
																	</td>
																	<td>SCANNED DOCUMENTS</td>
																	<td><td>
																</tr>
															</jims2:isAllowed>
															<logic:iterate id="docsIndex" name="casefileDocumentsForm" property="documents" indexId="index">
																<pg:item>
																	<tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>" height="100%">
																<!-- -36213 Add OID to documents -->	<td ><bean:write name="docsIndex" property="reportId"/></td>
																		<td >
																			<a href="/<msp:webapp/>displayJuvenileCasefileDocuments.do?submitAction=<bean:message key='prompt.other'/>&reportId=<bean:write name="docsIndex" property="reportId"/>&reportType=<bean:write name="docsIndex" property="reportType"/>"><bean:write name="docsIndex" property="entryDate" formatKey="date.format.mmddyyyy"/>
																			</a>
																		</td>
																		<td ><bean:write name="docsIndex" property="reportType"/></td>
																		
																	</tr>
																</pg:item>
															</logic:iterate>
														<%--/logic:notEmpty--%> 
													</table>
													<%-- Begin Pagination navigation Row--%>
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
										    	    <%-- End Pagination navigation Row--%>
												</td>
											 </tr>
							   			 </table>
					                 </td>
		 		                 </tr>
			     				 <tr>
						       	    <td><img src="/<msp:webapp/>images/spacer.gif" width='5'></td>
						      	 </tr>
				     			 <tr>
									<td>
									    <!-- begin button table -->
							       	    <table border="0" cellpadding='1' cellspacing='1' align='center'>
								    	    <tr>
										        <td align="center">
											        <html:submit property="submitAction"><bean:message key="button.back"/></html:submit>
											        <html:submit property="submitAction"><bean:message key="button.returnToCasefile"/></html:submit>                       
											        <html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>    
										        </td>
									        </tr>
								        </table>
								        <!-- end button table -->
									</td>
								</tr>
			     				<tr>
						       	  <td><img src="/<msp:webapp/>images/spacer.gif" width='5'></td>
						      	</tr>
				            </table>
					     </td>
					  </tr>
     				  <tr>
			       		  <td><img src="/<msp:webapp/>images/spacer.gif" width='10'></td>
			      	  </tr>
		            </table>
				 </td>
			   </tr>
			</table>
			<!-- END DETAIL TABLE -->
			
		  <tr>
      		  <td><img src="/<msp:webapp/>images/spacer.gif" width='10'></td>
      	  </tr>
		  <div align='center'><script type="text/javascript">renderBackToTop()</script></div>
		
		<%-- Begin Pagination Closing Tag --%>
		</pg:pager>
		<%-- End Pagination Closing Tag --%>
		</html:form>
	</body>
</html:html>
