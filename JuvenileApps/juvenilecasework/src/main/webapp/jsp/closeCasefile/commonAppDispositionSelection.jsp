<!DOCTYPE HTML>
<%-- Used in closing of a casefile --%>
<%--MODIFICATIONS --%>
<%-- 11/29/2005		Justin Jose	Created JSP --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>




<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
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
<title><bean:message key="title.heading" /> - commonAppDispositionSelection.jsp</title>

<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<%-- APP JAVASCRIPT FILE --%>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<%--HELP JAVASCRIPT FILE --%>
<%--<SCRIPT SRC="../js/help.js" /> --%>
</head>
<%--END HEAD TAG--%>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin="0" leftmargin="0">
<html:form action="displayCommonAppCourtDispositions.do" target="content">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|80">

<%-- BEGIN HEADING TABLE --%>
<table width="100%">
	<tr>
		<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.close"/> <bean:message key="title.casefile"/> 
		- <bean:message key="title.commonApp"/> - <bean:message key="title.dispositionOrder"/> <bean:message key="title.selection"/>
    </td>
	</tr>
</table>

<table width="100%">
	<tr>
		<td align="center" class="errorAlert"><html:errors></html:errors></td>
	</tr>
</table>
<%-- END HEADING TABLE --%>

<%-- Begin Pagination Header Tag--%>
<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define> 

<pg:pager
    index="center"
    maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
    maxIndexPages="10"
    export="offset,currentPageNumber=pageNumber"
    scope="request">
    <input type="hidden" name="pager.offset" value="<%= offset %>">
<%-- End Pagination header stuff --%>

<%-- BEGIN INSTRUCTION TABLE --%>
<table width="98%">
 	<tr>
   	<td>
		  <ul>
       	<logic:notEmpty name="commonAppForm" property="dispositions">
        	<li>Select radio button to select Disposition.</li>
        	<li>Click "Link" to view Disposition Details.</li>
        	<li>Click "Save and Continue" to save disposition selection.</li>
        </logic:notEmpty>

        <logic:empty name="commonAppForm" property="dispositions">
        	<li>A disposition must exist in order to enter Common App Exit Report</li>
        </logic:empty>
    	</ul>
		</td>
	</tr>
</table>
<%-- END INSTRUCTION TABLE --%>

<%-- BEGIN HEADER INFO TABLE --%>

<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
<tiles:put name="headerType" value="casefileheader" />
</tiles:insert>
<%-- END HEADER INFO TABLE --%>

<%-- BEGIN DETAIL TABLE --%>
<div class=spacer></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
 	<tr>
   	<td valign=top>
			<tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
				<tiles:put name="tabid" value="closing" />
				<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
			</tiles:insert>

			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
		  	<tr>
					<td valign=top align=center>
            <%-- Begin Common App Tabs --%>
            <div class=spacer></div>
        		<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
      		  	<tr>
      		    	<td valign=top>
      		    		<table width='100%' border="0" cellpadding="0" cellspacing="0" >
      						<tr>
      							<td valign=top>
										  <tiles:insert page="../caseworkCommon/casefileClosingTabs.jsp" flush="true">
            						<tiles:put name="tabid" value="CommonApp" />
            						<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
              				</tiles:insert>
										</td>
      						</tr>
     					 	 <tr>
     					  		<td bgcolor='#33cc66'><img src='/<msp:webapp/>images/spacer.gif' width='5'></td>
   					  	</tr>
     					</table>
     					<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
   					  	<tr>
        					<td valign=top align=center>
                    <%-- Begin Common App Detail Tabs --%>
                    <div class=spacer></div>
                		<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
                		  	<tr>
                		    	<td valign=top>
                		    		<table width='100%' border="0" cellpadding="0" cellspacing="0" >
                						<tr>
                							<td valign=top>
															  <tiles:insert page="../caseworkCommon/commonAppFormTabs.jsp" flush="true">
                      						<tiles:put name="tabid" value="Disposition" />
                      						<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
                      					</tiles:insert>
													    </td>
                						</tr>
                					 	 <tr>
                					  		<td bgcolor='#ff6666'><img src='/<msp:webapp/>images/spacer.gif' width='5'></td>
                					  	</tr>
                					</table>

                					<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableRed">
               					  	<tr>
                    					<td valign=top align=center>
                                <%-- BEGIN Search Results Summary TABLE --%>
                                <table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
                                 	<tr>
                                   	<td valign=top align=center>
                                			
                                			<logic:empty name="commonAppForm" property="dispositions">
                                  			<table align="left">
                                  				<tr>
                                			     	<td align="left">
                              				  		<b>  
                                              No &nbsp;<bean:message key="prompt.dispositions"/> <bean:message key="prompt.found"/> </b>
                                  					</td>
                                  			  	</tr>
                                  			</table>
                                 			</logic:empty>

                                 			<logic:notEmpty name="commonAppForm" property="dispositions">
                                  			<table>
                                  				<tr>
                                			     	<td align="center">
                                				  		<bean:size id="searchResultsSize" name="commonAppForm" property="dispositions"/>
                                				  		<b>  
                                            <bean:write name="searchResultsSize"/> &nbsp;<bean:message key="prompt.dispositions"/> <bean:message key="prompt.found"/> </b>
                                					</td>
                                			  	</tr>
                                			</table>
                          			
                                			<table width='98%' cellpadding="2" cellspacing="1" class="borderTableBlue">
                                				<tr>
                                					<td class=detailHead width='1%'></td>
                                					<td class=detailHead><bean:message key="prompt.petitionNumber"/></td>
                                					<td class=detailHead><bean:message key="prompt.court"/> <bean:message key="prompt.dateTime"/></td>
                                					<td class=detailHead><bean:message key="prompt.court"/></td>
                                					<td class=detailHead><bean:message key="prompt.disposition"/> <bean:message key="prompt.date"/></td>
                                					<td class=detailHead><bean:message key="prompt.hearing"/> <bean:message key="prompt.type"/></td>
                                					<td class=detailHead><bean:message key="prompt.judgement"/> <bean:message key="prompt.date"/></td>
                                				</tr>
                                				<bean:define id="selectedDispValue" name="commonAppForm" property="selectedDisposition" type="java.lang.String"/>
      
                                				<logic:iterate id="dispositionsList" name="commonAppForm" property="dispositions" indexId="index">
                                				 <%-- Begin Pagination item wrap --%>
                                    		  <pg:item>
                                    				<tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>"> 
                                    					<td>
                                    					  <logic:equal name="dispositionsList" property="dispositionNumber" value="<%=selectedDispValue%>">
                                    					    <input type="radio" name="selectedValue" value='<bean:write name="dispositionsList" property="dispositionNumber"/>' checked/>
                                    					  </logic:equal>
                                    					  <logic:notEqual name="dispositionsList" property="dispositionNumber" value="<%=selectedDispValue%>">
                                    					    <input type="radio" name="selectedValue" value='<bean:write name="dispositionsList" property="dispositionNumber"/>'/>
                                    					  </logic:notEqual>
                                    					</td>  
                                    					<td>
      																				  <a href="javascript:openWindow('/<msp:webapp/>displayFamilyMemberDetails.do?submitAction=Details&selectedValue=<bean:write name="dispositionsList" property="dispositionNumber"/>')">
                                    						<bean:write name="dispositionsList" property="petititionNumber"/></a>
      																				</td>
                                    					<td><bean:write name="dispositionsList" property="courtDate_Time"/></td>
                                    					<td><bean:write name="dispositionsList" property="courtNumber"/></td>
                                    					<td><bean:write name="dispositionsList" property="dispositionDate"/></td>
                                    					<td><bean:write name="dispositionsList" property="hearingType"/></td>
                                    					<td><bean:write name="dispositionsList" property="judgementDate"/></td>
                                    				</tr>
                                    			</pg:item>
                                    		  <%-- End Pagination item wrap --%>
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
                                		</logic:notEmpty>
                            
                               		</td>
                              	</tr>
                              </table>
                              <%-- END Search Results Summary TABLE --%>
                              <div class=spacer></div>
                            </td>
                 			  	</tr>
                   			</table>
                   		</td>
                 	  </tr>
                  </table>
                  <%-- End Common App Detail Tabs --%>
                  <div class=spacer></div>
                </td>
      		  	</tr>
      			</table>
       		</td>
        </tr>
      </table>
      <div class=spacer></div>
      <%-- End Common App Tabs --%>
			</td>
	  	</tr>
		</table>
 		</td>
  </tr>
</table>
<%-- End Casefile App Tabs --%>
<%-- END DETAIL TABLE --%>


<%-- BEGIN BUTTON TABLE --%>
<div class=spacer></div>
<table width="100%">
 	<tr>
   	<td align="center">
   		<html:submit property="submitAction"><bean:message key="button.back"></bean:message></html:submit>
   		<logic:notEmpty name="commonAppForm" property="dispositions">
  		 		<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.saveAndContinue"></bean:message></html:submit>
   		</logic:notEmpty>
			<html:submit property="submitAction"><bean:message key="button.cancel"></bean:message></html:submit>

   	</td>
 	</tr>
</table>
<%-- END BUTTON TABLE --%>

<div class=spacer></div>


<%-- Begin Pagination Closing Tag --%>
</pg:pager>
<%-- End Pagination Closing Tag --%>

</html:form>

<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>

