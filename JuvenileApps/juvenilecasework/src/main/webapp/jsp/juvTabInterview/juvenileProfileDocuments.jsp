<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%-- Used in listing documents generated for a juvenile --%>
<%-- 03/04/2013	CShimek  	Created the JSP --%>
<%-- 03/20/2013 CShimek     #75311 revised page to work as stand alone page instead of Education Tab extension page --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims2" %>
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
	<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
	<html:base />
	<title><bean:message key="title.heading"/> - juvenileProfileDocuments.jsp</title>
	<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
	<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script> 
	<script type='text/javascript' src="/<msp:webapp/>js/date.js"></script> 
	<script type='text/javascript'>
		function check4DocError()
		{
			str = document.getElementById("docError").value;
			if (str > "")
			{
				alert(str);
			}
			document.getElementById("docError").value = "";
		}
	</script>
	
</head> 
<%--END HEAD TAG--%>
	
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);" topmargin='0' leftmargin="0" onload="check4DocError();">
<html:form action="/displayJuvenileProfileDocuments" target="content">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|00">
<input type="hidden" name="documentError" value="<bean:write name="casefileDocumentsForm" property="errorMsg"/>" id="docError" /> 
<!-- BEGIN HEADING TABLE -->
<table width='100%'>
	<tr>
		<td align="center" class="header" ><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - <bean:message key="prompt.documents"/> </td>
	</tr>
</table>
<!-- END HEADING TABLE -->
<div class='spacer'></div>
<!-- BEGIN INSTRUCTION TABLE -->
<table width="98%" border="0">
	<tr>
		<td>
			<ul>
				<li>Select a hyperlinked Document date to view it.</li>
			</ul>
		</td>
	</tr>
</table>
<!-- END INSTRUCTION TABLE -->
<!-- BEGIN Juv Header TABLE -->
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="profileheader"/>
</tiles:insert>
<!-- End Header TABLE -->
<%-- Begin Pagination Header Tag--%>
	<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define> 
			
	<pg:pager index="center" maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
		    maxIndexPages="10" export="offset,currentPageNumber=pageNumber" scope="request">
<%-- End Pagination header stuff --%>
	<div class='spacer'></div>			
<!-- BEGIN DETAIL TABLE -->
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
  		<td valign='top'>
<%-- BEGIN GREEN TABS TABLE --%>  		
    		<table width='100%' border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign='top'>
						<tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
							<tiles:put name="tabid" value="interviewinfotab"/>
							<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
						</tiles:insert>	
					</td>
				</tr>
				<tr>
					<td bgcolor='#33cc66'><img src="/<msp:webapp/>images/spacer.gif" width="5"></td>
				</tr>
			</table>
<%-- END GREEN TABS TABLE --%> 
<%-- BEGIN GREEN BORDER TABLE --%> 			
			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
				<tr>
					<td valign='top' align="center">
						<div class="spacer"></div>	
<%-- BEGIN BLUE TABS TABLE --%> 											
						<table width='98%' border="0" cellpadding="0" cellspacing="0" >
							<tr>
								<td valign='top'>
									<tiles:insert page="/jsp/caseworkCommon/juvenileProfileCasefileTabs.jsp" flush="true">
    									<tiles:put name="tabid" value="document"/>
    									<tiles:put name="juvnum" value='<%=request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>'/>
    								</tiles:insert>	
								</td>
							</tr>
							<tr>
								<td bgcolor='#6699ff'><img src="/<msp:webapp/>images/spacer.gif" width="5"></td>
					  	   </tr>
            			</table>
<%-- END BLUE TABS TABLE --%>
<%-- BEGIN BLUE BORDER TABLE --%> 
			   			<table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
							<tr>
								<td valign="top" align="center">
									<div class="spacer"></div>
			       					<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
			       						<tr> <!-- table title bar -->
			       							<td valign='top' colspan='2' class='detailHead'>Juvenile Documents</td>
			       						</tr>
			       						<logic:empty name="casefileDocumentsForm" property="documents">
			       							<tr>
			       								<td>No Documents found for this Juvenile Profile.</td>
			       							</tr>
			       						</logic:empty>
			       						<logic:notEmpty name="casefileDocumentsForm" property="documents">
				     						<tr>
												<td>												
													<table width='100%' cellpadding="2" cellspacing="1">
					                 					<tr bgcolor='#cccccc' class='subhead'>
															<!-- -36213 Add OID to documents start -->
															<td align='left'>
																	Document ID
																	<jims2:sortResults beanName="casefileDocumentsForm" results="documents" primaryPropSort="documentId" primarySortType="INTEGER" sortId="1" />
																</td>
																<!-- -36213 Add OID to documents end  -->
															<td>
																Generate Date
																<jims2:sortResults beanName="casefileDocumentsForm" results="documents" primaryPropSort="entryDate" primarySortType="DATE" defaultSort="true" defaultSortOrder="DSC" sortId="2" />
															</td>
															<td>
																Type
																<jims2:sortResults beanName="casefileDocumentsForm" results="documents" primaryPropSort="documentTypeDesc" primarySortType="STRING" sortId="3" />
															</td>
														</tr>
														<logic:iterate id="docsIndex" name="casefileDocumentsForm" property="documents" indexId="index">
															<pg:item>
																<tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">
															<!-- -36213 Add OID to documents -->	<td ><bean:write name="docsIndex" property="documentId"/></td>
																	<td width="30%">
																		<a href="/<msp:webapp/>displayJuvenileProfileDocuments.do?submitAction=<bean:message key='prompt.view'/>&documentId=<bean:write name="docsIndex" property="documentId"/>&documentType=<bean:write name="docsIndex" property="documentTypeId"/>"><bean:write name="docsIndex" property="entryDate" formatKey="datetime.format.mmddyyyyHHmmAMPM"/>
																		</a>
																	</td>
																	<td><bean:write name="docsIndex" property="documentTypeDesc"/></td>
																</tr>
															</pg:item>
														</logic:iterate>
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
										</logic:notEmpty>	 
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
										       		 <%--Bug Fix:13105 --%>
											        <html:button property="button.back" onclick="history.back();"><bean:message key="button.back" /></html:button> 
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