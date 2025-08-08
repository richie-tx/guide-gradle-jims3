<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 03/10/2010	 RCapestani - Create JSP -->
<!-- 05/12/2010	 RCapestani - add Supplemental Document lists -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>
<%@page import="naming.Features"%>
<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />
<title><bean:message key="title.heading" /> - posttrial/supplementalDocuments.jsp</title>
<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>

<script type="text/javascript">
 function selectSingleCase(){
    var rb = document.getElementsByName("selectedCaseValue");
    if (rb.length == 1){
          rb[0].checked = true;
    }     
}
	
</script>
</head>
<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)" onload="selectSingleCase()">
<html:form action="/handleActiveCasesSelection" target="content">
<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Close_Supervision/HC_Case/Close_Supervision_HC_Case.htm#|1">

<div align="center">
	<table width="98%" border="0" cellpadding="0" cellspacing="0" >
		<tr>
	    	<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
	  	</tr>
	  	<tr>
	    	<td valign="top">
<!-- BEGIN BLUE TABS TABLE -->    	
	<table width="100%" border="0" cellpadding="0" cellspacing="0" >
		<tr>
			<td valign="top">
<!--tabs start-->
				<tiles:insert page="../common/commonSupervisionTabs.jsp" flush="true"></tiles:insert>
<!--tabs end  -->
			</td>
		</tr>
		<tr>
			<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
		</tr>
	</table>
<!-- END BLUE TABS TABLE --> 
<!-- BEGIN BLUE BORDER TABLE --> 			
	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
		<tr>
			<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
		</tr>
		<tr>
			<td valign="top" align="center">
<!-- BEGIN HEADING TABLE -->
				<table width="100%">
					<tr>							
						<td align="center" class="header">
						    <bean:message key="title.CSCD" /> - <bean:message key="prompt.supplementalDocuments" />										
						</td>						
					</tr>
				</table>
<!-- END HEADING TABLE -->

<!-- BEGIN ERROR TABLE -->
				<table width="98%" align="center">							
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
								<li>Click document name to print report.</li>
								<li>If document name is not a <u><b>Hyperlink</b></u>, (underlined and bold) use FileMaker Pro to print report.</li>
							</ul>
						</td>
					</tr>										
				</table>
<!-- END INSTRUCTION TABLE -->
				<% int RecordCtr=0;
				   int bgCtr=0; 
				   String bgcolor="";
				   String lr=""; %>
<!-- BEGIN SUPPLEMENTAL DOCUMENTS TABLE  -->
			    <table width="98%" cellpadding="2" cellspacing="0">
				    <tr>
				    	<td width="100%">
<!-- BEGIN CORRESPONDENCE BLOCK TABLE  -->
				       		<table width="100%" cellpadding="2" cellspacing="0" border="0" class="borderTableBlue">
				           		<tr class="detailHead">
				            		<td>Correspondence Forms</td>
				           		</tr>
				           		<tr>
				             		<td>
<!-- BEGIN CORRESPONDENCE FORMS TABLE  -->
				             			<table width="100%" border="0" cellpadding="2" cellspacing="1">
											<logic:iterate id="docIndex" name="supplementalDocumentsForm" property="correspondenceList" indexId="index">
												<% RecordCtr++; 
													lr = "L";
													if (RecordCtr % 2 == 0){
														lr = "R";
													}
												%>
												<% if(lr == "L" ) { %>
													<% bgCtr++;
														bgcolor = "alternateRow";                      
														if (bgCtr % 2 == 1){
															bgcolor = "normalRow";
														}  %>	
											 		<tr class=<%= bgcolor %> >
										 				<td width="50%">
										 					<logic:notEqual name="docIndex" property="url" value="">
											 					<a href="javascript:openWindow('<bean:write name="docIndex" property="url"/>') ">
														   			<bean:write name="docIndex" property="formTitle" />
																</a>
															</logic:notEqual>
															<logic:equal name="docIndex" property="url" value="">
											 						<bean:write name="docIndex" property="formTitle" />
															</logic:equal>
											 			</td>	
												<% } %>	
												<% if(lr == "R" ) { %>
														<td width="50%">
															<logic:notEqual name="docIndex" property="url" value="">
											 					<a href="javascript:openWindow('<bean:write name="docIndex" property="url"/>') ">
														   			<bean:write name="docIndex" property="formTitle" />
																</a>
															</logic:notEqual>
															<logic:equal name="docIndex" property="url" value="">
											 						<bean:write name="docIndex" property="formTitle" />
															</logic:equal>
														</td>
													</tr>
												<% } %>	
											</logic:iterate>
											<% if(lr == "L" ) { %>
											     <td>&nbsp;</td>
											     </tr>
											<% } %>	
				             			</table>
<!-- END CORRESPONDENCE FORMS  TABLE  --></td>
				           		</tr>
				       		</table>
<!-- END CORRESPONDENCE BLOCK TABLE  -->
				        </td>
				    </tr>
<!------------------------------------------------------------------->
			      	<tr>
				        <td>
<!-- BEGIN CTI BLOCK TABLE  -->
					        <table width="100%" cellpadding="2" cellspacing="0" border="0" class="borderTableBlue">
					            <tr class="detailHead">
					            	<td>CTI</td>
					            </tr>
					            <tr >
					              	<td>
					              		<% RecordCtr=0;
										 bgCtr=0; 
										 bgcolor="";
										 lr=""; %>
<!-- BEGIN CTI FORMS TABLE  -->
							            <table width="100%" border="0" cellpadding="2" cellspacing="1">
							                <logic:iterate id="docIndex" name="supplementalDocumentsForm" property="ctiList" indexId="index">
												<% RecordCtr++; 
													lr = "L";
													if (RecordCtr % 2 == 0){
														lr = "R";
													}
												%>
												<% if(lr == "L" ) { %>
													<% bgCtr++;
														bgcolor = "alternateRow";                      
														if (bgCtr % 2 == 1){
															bgcolor = "normalRow";
														}  %>	
									  			<tr class=<%= bgcolor %> >
										  			<td width="50%">
										  				<logic:notEqual name="docIndex" property="url" value="">
										 					<a href="javascript:openWindow('<bean:write name="docIndex" property="url"/>') ">
													   			<bean:write name="docIndex" property="formTitle" />
															</a>
														</logic:notEqual>
														<logic:equal name="docIndex" property="url" value="">
										 						<bean:write name="docIndex" property="formTitle" />
														</logic:equal>
												 	</td>	
												<% } %>	
												<% if(lr == "R" ) { %>
													<td width="50%">
														<logic:notEqual name="docIndex" property="url" value="">
										 					<a href="javascript:openWindow('<bean:write name="docIndex" property="url"/>') ">
													   			<bean:write name="docIndex" property="formTitle" />
															</a>
														</logic:notEqual>
														<logic:equal name="docIndex" property="url" value="">
										 						<bean:write name="docIndex" property="formTitle" />
														</logic:equal>
													</td>
												</tr>
												<% } %>	
							   				</logic:iterate>
							   				<% if(lr == "L" ) { %>
											     <td>&nbsp;</td>
											     </tr>
											<% } %>	
					                	</table>
<!-- END CTI FORMS TABLE  -->
					                </td>
					            </tr>
				          	</table>
<!-- END CTI BLOCK TABLE  -->
			          	</td>
			      	</tr>
<!------------------------------------------------------------------->
			      	<tr>
			      		<td>
<!-- BEGIN DEFENDANT BLOCK TABLE  -->
			        		<table width="100%" cellpadding="2" cellspacing="0" border="0" class="borderTableBlue">
					            <tr class="detailHead">
					            	<td>Defendant Forms</td>
					            </tr>
					            <tr>
					            	<td>
						              	<% RecordCtr=0;
										 bgCtr=0; 
										 bgcolor="";
										 lr=""; %>
<!-- BEGIN DEFENDANT FORMS TABLE  -->
						                <table width="100%" border="0" cellpadding="2" cellspacing="1">
							                <logic:iterate id="docIndex" name="supplementalDocumentsForm" property="defendantFormsList" indexId="index">
												<% RecordCtr++; 
													lr = "L";
													if (RecordCtr % 2 == 0){
														lr = "R";
													}
												%>
												<% if(lr == "L" ) { %>
													<% bgCtr++;
														bgcolor = "alternateRow";                      
														if (bgCtr % 2 == 1){
															bgcolor = "normalRow";
														}  %>	
									  			<tr class=<%= bgcolor %> >
										  			<td width="50%">
										  				<logic:notEqual name="docIndex" property="url" value="">
										 					<a href="javascript:openWindow('<bean:write name="docIndex" property="url"/>') ">
													   			<bean:write name="docIndex" property="formTitle" />
															</a>
														</logic:notEqual>
														<logic:equal name="docIndex" property="url" value="">
										 						<bean:write name="docIndex" property="formTitle" />
														</logic:equal>
												 	</td>	
												<% } %>	
												<% if(lr == "R" ) { %>
													<td width="50%">
														<logic:notEqual name="docIndex" property="url" value="">
										 					<a href="javascript:openWindow('<bean:write name="docIndex" property="url"/>') ">
													   			<bean:write name="docIndex" property="formTitle" />
															</a>
														</logic:notEqual>
														<logic:equal name="docIndex" property="url" value="">
										 						<bean:write name="docIndex" property="formTitle" />
														</logic:equal>
													</td>
												</tr>
												<% } %>
							   				</logic:iterate>
							   				<% if(lr == "L" ) { %>
											     <td>&nbsp;</td>
											     </tr>
											<% } %>
						                </table>
<!-- END DEFENDANT FORMS TABLE  -->
					                </td>
					            </tr>
				        	</table>
<!-- END DEFENDANT BLOCK TABLE  -->
			            </td>
			      	</tr>
<!------------------------------------------------------------------->
			      	<tr>
			        	<td>
<!-- BEGIN ELM BLOCK TABLE  -->
			        		<table width="100%" cellpadding="2" cellspacing="0" border="0" class="borderTableBlue">
					            <tr class="detailHead">
					            	<td>ELM Forms</td>
					            </tr>
					            <tr>
					            	<td>
						              	<% RecordCtr=0;
										 bgCtr=0; 
										 bgcolor="";
										 lr=""; %>
<!-- BEGIN ELM FORMS TABLE  -->
						              	<table width="100%" border="0" cellpadding="2" cellspacing="1">
						                  	<logic:iterate id="docIndex" name="supplementalDocumentsForm" property="elmFormsList" indexId="index">
												<% RecordCtr++; 
													lr = "L";
													if (RecordCtr % 2 == 0){
														lr = "R";
													}
												%>
												<% if(lr == "L" ) { %>
													<% bgCtr++;
														bgcolor = "alternateRow";                      
														if (bgCtr % 2 == 1){
															bgcolor = "normalRow";
														}  %>	
									  			<tr class=<%= bgcolor %> >
										  			<td width="50%">
										  				<logic:notEqual name="docIndex" property="url" value="">
										 					<a href="javascript:openWindow('<bean:write name="docIndex" property="url"/>') ">
													   			<bean:write name="docIndex" property="formTitle" />
															</a>
														</logic:notEqual>
														<logic:equal name="docIndex" property="url" value="">
										 						<bean:write name="docIndex" property="formTitle" />
														</logic:equal>
												 	</td>	
												<% } %>	
												<% if(lr == "R" ) { %>
													<td width="50%">
														<logic:notEqual name="docIndex" property="url" value="">
										 					<a href="javascript:openWindow('<bean:write name="docIndex" property="url"/>') ">
													   			<bean:write name="docIndex" property="formTitle" />
															</a>
														</logic:notEqual>
														<logic:equal name="docIndex" property="url" value="">
										 						<bean:write name="docIndex" property="formTitle" />
														</logic:equal>
													</td>
												</tr>
												<% } %>	
						   					</logic:iterate>
						   					<% if(lr == "L" ) { %>
											     <td>&nbsp;</td>
											     </tr>
											<% } %>
						                </table>
<!-- END ELM FORMS TABLE  -->
									</td>
			            		</tr>
			          		</table>
<!-- END ELM BLOCK TABLE  -->
			            </td>
			      	</tr>
<!------------------------------------------------------------------->
			      	<tr>
			        	<td>
<!-- BEGIN FINANCIAL BLOCK TABLE  -->
			        		<table width="100%" cellpadding="2" cellspacing="0" border="0" class="borderTableBlue">
					            <tr class="detailHead">
					            	<td>Financial Forms</td>
					            </tr>
					            <tr>
					            	<td>
						              	<% RecordCtr=0;
										 bgCtr=0; 
										 bgcolor="";
										 lr=""; %>
<!-- BEGIN FINANCIAL FORMS TABLE  -->
			              				<table width="100%" border="0" cellpadding="2" cellspacing="1">
			                  				<logic:iterate id="docIndex" name="supplementalDocumentsForm" property="financialList" indexId="index">
												<% RecordCtr++; 
													lr = "L";
													if (RecordCtr % 2 == 0){
														lr = "R";
													}
												%>
												<% if(lr == "L" ) { %>
													<% bgCtr++;
														bgcolor = "alternateRow";                      
														if (bgCtr % 2 == 1){
															bgcolor = "normalRow";
														}  %>	
									  			<tr class=<%= bgcolor %> >
										  			<td width="50%">
										  				<logic:notEqual name="docIndex" property="url" value="">
										 					<a href="javascript:openWindow('<bean:write name="docIndex" property="url"/>') ">
													   			<bean:write name="docIndex" property="formTitle" />
															</a>
														</logic:notEqual>
														<logic:equal name="docIndex" property="url" value="">
										 						<bean:write name="docIndex" property="formTitle" />
														</logic:equal>
												 	</td>	
												<% } %>	
												<% if(lr == "R" ) { %>
													<td width="50%">
														<logic:notEqual name="docIndex" property="url" value="">
										 					<a href="javascript:openWindow('<bean:write name="docIndex" property="url"/>') ">
													   			<bean:write name="docIndex" property="formTitle" />
															</a>
														</logic:notEqual>
														<logic:equal name="docIndex" property="url" value="">
										 						<bean:write name="docIndex" property="formTitle" />
														</logic:equal>
													</td>
												</tr>
												<% } %>	
			   								</logic:iterate>
			   								<% if(lr == "L" ) { %>
											     <td>&nbsp;</td>
											     </tr>
											<% } %>
			                  			</table>
<!-- END FINANCIAL FORMS TABLE  -->
					                </td>
					        	</tr>
					        </table>
<!-- END FINANCIAL BLOCK TABLE  -->
			            </td>
			      	</tr>
<!------------------------------------------------------------------->
			      	<tr>
			        	<td>
<!-- BEGIN MISCELLANEOUS BLOCK TABLE  -->
			        		<table width="100%" cellpadding="2" cellspacing="0" border="0" class="borderTableBlue">
					            <tr class="detailHead">
					            	<td>Miscellaneous</td>
					            </tr>
					            <tr>
					            	<td>
						              	<% RecordCtr=0;
										 bgCtr=0; 
										 bgcolor="";
										 lr=""; %>
<!-- BEGIN MISCELLANEOUS FORMS TABLE  -->
						                <table width="100%" border="0" cellpadding="2" cellspacing="1">
						                  	<logic:iterate id="docIndex" name="supplementalDocumentsForm" property="miscellaneousList" indexId="index">
												<% RecordCtr++; 
													lr = "L";
													if (RecordCtr % 2 == 0){
														lr = "R";
													}
												%>
												<% if(lr == "L" ) { %>
													<% bgCtr++;
														bgcolor = "alternateRow";                      
														if (bgCtr % 2 == 1){
															bgcolor = "normalRow";
														}  %>	
									  			<tr class=<%= bgcolor %> >
										  			<td width="50%">
										  				<logic:notEqual name="docIndex" property="url" value="">
										 					<a href="javascript:openWindow('<bean:write name="docIndex" property="url"/>') ">
													   			<bean:write name="docIndex" property="formTitle" />
															</a>
														</logic:notEqual>
														<logic:equal name="docIndex" property="url" value="">
										 						<bean:write name="docIndex" property="formTitle" />
														</logic:equal>
												 	</td>	
												<% } %>	
												<% if(lr == "R" ) { %>
													<td width="50%">
														<logic:notEqual name="docIndex" property="url" value="">
										 					<a href="javascript:openWindow('<bean:write name="docIndex" property="url"/>') ">
													   			<bean:write name="docIndex" property="formTitle" />
															</a>
														</logic:notEqual>
														<logic:equal name="docIndex" property="url" value="">
										 						<bean:write name="docIndex" property="formTitle" />
														</logic:equal>
													</td>
												</tr>
												<% } %>	
			   								</logic:iterate>
			   								<% if(lr == "L" ) { %>
											     <td>&nbsp;</td>
											     </tr>
											<% } %>
			                			</table>
<!-- END MISC FORMS TABLE  -->
			                		</td>
			            		</tr>
			          		</table>
<!-- END MISC BLOCK TABLE  -->
			            </td>
			      	</tr>
<!------------------------------------------------------------------->
			      	<tr>
			        	<td>
<!-- BEGIN PROBATIONER BLOCK TABLE  -->
			        		<table width="100%" cellpadding="2" cellspacing="0" border="0" class="borderTableBlue">
					            <tr class="detailHead">
					            	<td>Probationer Monthly Report</td>
					            </tr>
					            <tr>
					            	<td>
						              	<% RecordCtr=0;
										 bgCtr=0; 
										 bgcolor="";
										 lr=""; %>
<!-- BEGIN PROBATIONER FORMS TABLE  -->
							            <table width="100%" border="0" cellpadding="2" cellspacing="1">
							                <logic:iterate id="docIndex" name="supplementalDocumentsForm" property="probationerMonthlyReportList" indexId="index">
												<% RecordCtr++; 
													lr = "L";
													if (RecordCtr % 2 == 0){
														lr = "R";
													}
												%>
												<% if(lr == "L" ) { %>
													<% bgCtr++;
														bgcolor = "alternateRow";                      
														if (bgCtr % 2 == 1){
															bgcolor = "normalRow";
														}  %>	
									  			<tr class=<%= bgcolor %> >
										  			<td width="50%">
										  				<logic:notEqual name="docIndex" property="url" value="">
										 					<a href="javascript:openWindow('<bean:write name="docIndex" property="url"/>') ">
													   			<bean:write name="docIndex" property="formTitle" />
															</a>
														</logic:notEqual>
														<logic:equal name="docIndex" property="url" value="">
										 						<bean:write name="docIndex" property="formTitle" />
														</logic:equal>
												 	</td>	
												<% } %>	
												<% if(lr == "R" ) { %>
													<td width="50%">
														<logic:notEqual name="docIndex" property="url" value="">
										 					<a href="javascript:openWindow('<bean:write name="docIndex" property="url"/>') ">
													   			<bean:write name="docIndex" property="formTitle" />
															</a>
														</logic:notEqual>
														<logic:equal name="docIndex" property="url" value="">
										 						<bean:write name="docIndex" property="formTitle" />
														</logic:equal>
													</td>
												</tr>
												<% } %>	
			   								</logic:iterate>
			   								<% if(lr == "L" ) { %>
											     <td>&nbsp;</td>
											     </tr>
											<% } %>
			          					</table>
<!-- END PROBATIONER FORMS TABLE  -->
			                		</td>
			            		</tr>
			          		</table>
<!-- END PROBATIONER BLOCK TABLE  -->
			            </td>
			      	</tr>
<!------------------------------------------------------------------->
			      	<tr>
			        	<td>
<!-- BEGIN REFERRAL BLOCK TABLE  -->
			        		<table width="100%" cellpadding="2" cellspacing="0" border="0" class="borderTableBlue">
					            <tr class="detailHead">
					            	<td>Referral Forms</td>
					            </tr>
					            <tr>
					            	<td>
						              	<% RecordCtr=0;
										 bgCtr=0; 
										 bgcolor="";
										 lr=""; %>
<!-- BEGIN REFERRAL FORMS TABLE  -->
							            <table width="100%" border="0" cellpadding="2" cellspacing="1">
							                <logic:iterate id="docIndex" name="supplementalDocumentsForm" property="referralFormsList" indexId="index">
												<% RecordCtr++; 
													lr = "L";
													if (RecordCtr % 2 == 0){
														lr = "R";
													}
												%>
												<% if(lr == "L" ) { %>
													<% bgCtr++;
														bgcolor = "alternateRow";                      
														if (bgCtr % 2 == 1){
															bgcolor = "normalRow";
														}  %>	
									  			<tr class=<%= bgcolor %> >
										  			<td width="50%">
										  				<logic:notEqual name="docIndex" property="url" value="">
										 					<a href="javascript:openWindow('<bean:write name="docIndex" property="url"/>') ">
													   			<bean:write name="docIndex" property="formTitle" />
															</a>
														</logic:notEqual>
														<logic:equal name="docIndex" property="url" value="">
										 						<bean:write name="docIndex" property="formTitle" />
														</logic:equal>
												 	</td>	
												<% } %>	
												<% if(lr == "R" ) { %>
													<td width="50%">
														<logic:notEqual name="docIndex" property="url" value="">
										 					<a href="javascript:openWindow('<bean:write name="docIndex" property="url"/>') ">
													   			<bean:write name="docIndex" property="formTitle" />
															</a>
														</logic:notEqual>
														<logic:equal name="docIndex" property="url" value="">
										 						<bean:write name="docIndex" property="formTitle" />
														</logic:equal>
													</td>
												</tr>
												<% } %>	
						   					</logic:iterate>
						   					<% if(lr == "L" ) { %>
											     <td>&nbsp;</td>
											     </tr>
											<% } %>
						                </table>
<!-- END REFERRAL FORMS TABLE  -->
			                		</td>
			            		</tr>
			           		</table>
<!-- END REFERRAL BLOCK TABLE  -->
			            </td>
			      	</tr>
<!------------------------------------------------------------------->
			      	<tr>
			      		<td>
<!-- BEGIN SEX OFFENDER BLOCK TABLE  -->
			      			<table width="100%" cellpadding="2" cellspacing="0" border="0" class="borderTableBlue">
					            <tr class="detailHead">
					            	<td>Sex Offender Forms</td>
					            </tr>
					            <tr>
					            	<td>
						              	<% RecordCtr=0;
										 bgCtr=0; 
										 bgcolor="";
										 lr=""; %>
<!-- BEGIN SEX OFFENDER FORMS TABLE  -->
			            				<table width="100%" border="0" cellpadding="2" cellspacing="1">
							                <logic:iterate id="docIndex" name="supplementalDocumentsForm" property="sexOffenderList" indexId="index">
												<% RecordCtr++; 
													lr = "L";
													if (RecordCtr % 2 == 0){
														lr = "R";
													}
												%>
												<% if(lr == "L" ) { %>
													<% bgCtr++;
														bgcolor = "alternateRow";                      
														if (bgCtr % 2 == 1){
															bgcolor = "normalRow";
														}  %>	
									  			<tr class=<%= bgcolor %> >
										  			<td width="50%">
										  				<logic:notEqual name="docIndex" property="url" value="">
										 					<a href="javascript:openWindow('<bean:write name="docIndex" property="url"/>') ">
													   			<bean:write name="docIndex" property="formTitle" />
															</a>
														</logic:notEqual>
														<logic:equal name="docIndex" property="url" value="">
										 						<bean:write name="docIndex" property="formTitle" />
														</logic:equal>
												 	</td>	
												<% } %>	
												<% if(lr == "R" ) { %>
													<td width="50%">
														<logic:notEqual name="docIndex" property="url" value="">
										 					<a href="javascript:openWindow('<bean:write name="docIndex" property="url"/>') ">
													   			<bean:write name="docIndex" property="formTitle" />
															</a>
														</logic:notEqual>
														<logic:equal name="docIndex" property="url" value="">
										 						<bean:write name="docIndex" property="formTitle" />
														</logic:equal>
													</td>
												</tr>
												<% } %>	
						   					</logic:iterate>
						   					<% if(lr == "L" ) { %>
											     <td>&nbsp;</td>
											     </tr>
											<% } %>
						                </table>
<!-- END SEX OFFENDER FORMS TABLE -->
									</td>
								</tr>
							</table>
<!-- END SEX OFFENDER BLOCK TABLE  -->
			          	</td>
			      	</tr>
<!------------------------------------------------------------------->
			      	<tr>
			      		<td>
<!-- BEGIN SPECIAL PROGRAMS BLOCK TABLE  -->	                
							<table width="100%" cellpadding="2" cellspacing="0" border="0" class="borderTableBlue">
					            <tr class="detailHead">
					            	<td>Special Programs Forms</td>
					            </tr>
					            <tr>
					            	<td>
						              	<% RecordCtr=0;
										 bgCtr=0; 
										 bgcolor="";
										 lr=""; %>
<!-- BEGIN SPECIAL PROGRAMS FORMS TABLE  -->
			              				<table width="100%" border="0" cellpadding="2" cellspacing="1">
							                <logic:iterate id="docIndex" name="supplementalDocumentsForm" property="specialProgramsList" indexId="index">
												<% RecordCtr++; 
													lr = "L";
													if (RecordCtr % 2 == 0){
														lr = "R";
													}
												%>
												<% if(lr == "L" ) { %>
													<% bgCtr++;
														bgcolor = "alternateRow";                      
														if (bgCtr % 2 == 1){
															bgcolor = "normalRow";
														}  %>	
									  			<tr class=<%= bgcolor %> >
										  			<td width="50%">
										  				<logic:notEqual name="docIndex" property="url" value="">
										 					<a href="javascript:openWindow('<bean:write name="docIndex" property="url"/>') ">
													   			<bean:write name="docIndex" property="formTitle" />
															</a>
														</logic:notEqual>
														<logic:equal name="docIndex" property="url" value="">
										 						<bean:write name="docIndex" property="formTitle" />
														</logic:equal>
												 	</td>	
												<% } %>	
												<% if(lr == "R" ) { %>
													<td width="50%">
														<logic:notEqual name="docIndex" property="url" value="">
										 					<a href="javascript:openWindow('<bean:write name="docIndex" property="url"/>') ">
													   			<bean:write name="docIndex" property="formTitle" />
															</a>
														</logic:notEqual>
														<logic:equal name="docIndex" property="url" value="">
										 						<bean:write name="docIndex" property="formTitle" />
														</logic:equal>
													</td>
												</tr>
												<% } %>	
						   					</logic:iterate>
						   					<% if(lr == "L" ) { %>
											     <td>&nbsp;</td>
											     </tr>
											<% } %>
						                </table>
<!-- END SPECIAL PROGRAMS FORMS TABLE  -->
						            </td>
						        </tr>
					        </table>
<!-- END SPECIAL PROGRAMS BLOCK TABLE  -->
			            </td>
			      	</tr>
<!------------------------------------------------------------------->
			      	<tr>
				        <td>
<!-- BEGIN TRANSFER BLOCK TABLE  -->
			        		<table width="100%" cellpadding="2" cellspacing="0" border="0" class="borderTableBlue">
					            <tr class="detailHead">
					            	<td>Transfer Unit Forms</td>
					            </tr>
					            <tr>
					            	<td>
						              	<% RecordCtr=0;
										 bgCtr=0; 
										 bgcolor="";
										 lr=""; %>
<!-- BEGIN TRANSFER FORMS TABLE  -->
						              	<table width="100%" border="0" cellpadding="2" cellspacing="1">
							                <logic:iterate id="docIndex" name="supplementalDocumentsForm" property="transferUnitList" indexId="index">
												<% RecordCtr++; 
													lr = "L";
													if (RecordCtr % 2 == 0){
														lr = "R";
													}
												%>
												<% if(lr == "L" ) { %>
													<% bgCtr++;
														bgcolor = "alternateRow";                      
														if (bgCtr % 2 == 1){
															bgcolor = "normalRow";
														}  %>	
									  			<tr class=<%= bgcolor %> >
										  			<td width="50%">
										  				<logic:notEqual name="docIndex" property="url" value="">
										 					<a href="javascript:openWindow('<bean:write name="docIndex" property="url"/>') ">
													   			<bean:write name="docIndex" property="formTitle" />
															</a>
														</logic:notEqual>
														<logic:equal name="docIndex" property="url" value="">
										 						<bean:write name="docIndex" property="formTitle" />
														</logic:equal>
												 	</td>	
												<% } %>	
												<% if(lr == "R" ) { %>
													<td width="50%">
														<logic:notEqual name="docIndex" property="url" value="">
										 					<a href="javascript:openWindow('<bean:write name="docIndex" property="url"/>') ">
													   			<bean:write name="docIndex" property="formTitle" />
															</a>
														</logic:notEqual>
														<logic:equal name="docIndex" property="url" value="">
										 						<bean:write name="docIndex" property="formTitle" />
														</logic:equal>
													</td>
												</tr>
												<% } %>	
						   					</logic:iterate>
						   					<% if(lr == "L" ) { %>
											     <td>&nbsp;</td>
											     </tr>
											<% } %>
						                </table>
<!-- END TRANSFER FORMS TABLE  -->
			                  		</td>
			            		</tr>
			          		</table>
<!-- END TRANSFER BLOCK TABLE  -->
			          	</td>
			    	</tr>
<!------------------------------------------------------------------->
			        <tr>
			        	<td>
<!-- BEGIN TRAVEL PERMITS BLOCK TABLE  -->
			        		<table width="100%" cellpadding="2" cellspacing="0" border="0" class="borderTableBlue">
					            <tr class="detailHead">
					            	<td>Travel Permits Forms</td>
					            </tr>
					            <tr>
					            	<td>
						              	<% RecordCtr=0;
										 bgCtr=0; 
										 bgcolor="";
										 lr=""; %>
<!-- BEGIN TRAVEL PERMITS FORMS TABLE  -->
							            <table width="100%" border="0" cellpadding="2" cellspacing="1">
							                <logic:iterate id="docIndex" name="supplementalDocumentsForm" property="travelPermitsList" indexId="index">
												<% RecordCtr++; 
													lr = "L";
													if (RecordCtr % 2 == 0){
														lr = "R";
													}
												%>
												<% if(lr == "L" ) { %>
													<% bgCtr++;
														bgcolor = "alternateRow";                      
														if (bgCtr % 2 == 1){
															bgcolor = "normalRow";
														}  %>	
									  			<tr class=<%= bgcolor %> >
										  			<td width="50%">
										  				<logic:notEqual name="docIndex" property="url" value="">
										 					<a href="javascript:openWindow('<bean:write name="docIndex" property="url"/>') ">
													   			<bean:write name="docIndex" property="formTitle" />
															</a>
														</logic:notEqual>
														<logic:equal name="docIndex" property="url" value="">
										 						<bean:write name="docIndex" property="formTitle" />
														</logic:equal>
												 	</td>	
												<% } %>	
												<% if(lr == "R" ) { %>
													<td width="50%">
														<logic:notEqual name="docIndex" property="url" value="">
										 					<a href="javascript:openWindow('<bean:write name="docIndex" property="url"/>') ">
													   			<bean:write name="docIndex" property="formTitle" />
															</a>
														</logic:notEqual>
														<logic:equal name="docIndex" property="url" value="">
										 						<bean:write name="docIndex" property="formTitle" />
														</logic:equal>
													</td>
												</tr>
												<% } %>	
						   					</logic:iterate>
						   					<% if(lr == "L" ) { %>
											     <td>&nbsp;</td>
											     </tr>
											<% } %>
						                </table>
<!-- END TRAVEL PERMITS FORMS TABLE  -->
						            </td>
						        </tr>
						    </table>
<!-- END TRAVEL PERMITS BLOCK TABLE  -->
			            </td>
			        </tr>
<!------------------------------------------------------------------->
			        <tr>
			        	<td>
<!-- BEGIN WAIVER BLOCK TABLE  -->        	
			        		<table width="100%" cellpadding="2" cellspacing="0" border="0" class="borderTableBlue">
					            <tr class="detailHead">
					            	<td>Waivers Forms</td>
					            </tr>
					            <tr>
					            	<td>
						              	<% RecordCtr=0;
										 bgCtr=0; 
										 bgcolor="";
										 lr=""; %>
<!-- BEGIN WAIVER FORMS TABLE  -->
			              				<table width="100%" border="0" cellpadding="2" cellspacing="1">
							                <logic:iterate id="docIndex" name="supplementalDocumentsForm" property="waiversList" indexId="index">
												<% RecordCtr++; 
													lr = "L";
													if (RecordCtr % 2 == 0){
														lr = "R";
													}
												%>
												<% if(lr == "L" ) { %>
													<% bgCtr++;
														bgcolor = "alternateRow";                      
														if (bgCtr % 2 == 1){
															bgcolor = "normalRow";
														}  %>	
									  			<tr class=<%= bgcolor %> >
										  			<td width="50%">
										  				<logic:notEqual name="docIndex" property="url" value="">
										 					<a href="javascript:openWindow('<bean:write name="docIndex" property="url"/>') ">
													   			<bean:write name="docIndex" property="formTitle" />
															</a>
														</logic:notEqual>
														<logic:equal name="docIndex" property="url" value="">
										 						<bean:write name="docIndex" property="formTitle" />
														</logic:equal>
												 	</td>	
												<% } %>	
												<% if(lr == "R" ) { %>
													<td width="50%">
														<logic:notEqual name="docIndex" property="url" value="">
										 					<a href="javascript:openWindow('<bean:write name="docIndex" property="url"/>') ">
													   			<bean:write name="docIndex" property="formTitle" />
															</a>
														</logic:notEqual>
														<logic:equal name="docIndex" property="url" value="">
										 						<bean:write name="docIndex" property="formTitle" />
														</logic:equal>
													</td>
												</tr>
												<% } %>	
						   					</logic:iterate>
						   					<% if(lr == "L" ) { %>
											     <td>&nbsp;</td>
											     </tr>
											<% } %>
						                </table>
<!-- END WAIVER FORMS TABLE  -->
					                </td>
					            </tr>
					        </table>
<!-- END WAIVER BLOCK TABLE  -->
			            </td>
			        </tr>
			    </table>
<!-- END SUPPLEMENTAL DOCUMENTS TABLE  -->
	        </td>
	    </tr>
	    <tr>
	        <td width="100%">
<!-- BEGIN BUTTON TABLE -->
				<table border="0" width="100%">
					<tr>
						<td align="center">
						    <html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"><bean:message key="button.back"/></html:submit>
						</td>
					</tr>										  	
				</table>
<!-- END BUTTON TABLE -->
			</td>
		</tr>
	</table>
	<br>
	<br>
</td>
</tr>
</table>
<!-- END BLUE BORDER TABLE -->				
</div>

</html:form>
<div align="center">[<a href="#top">Back to Top</a>]</div>
</body>
</html:html>