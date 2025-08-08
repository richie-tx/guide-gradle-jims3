<!DOCTYPE HTML>
<%-- User selects the "Drugs" tab in the "Interview Info" tab on Juvenile Profile Detail page --%>
<%--MODIFICATIONS --%>
<%-- 06/15/2005	Hien Rodriguez	Create JSP --%>
<%-- 12/14/2006 Hien Rodriguez  ER#37077 Add Tab & Buttons security features --%>
<%-- 07/20/2009 C Shimek        #61004 added timeout.js  --%>
<%-- 07/10/2012 C Shimek     	#73565 added age > 20 check (juvUnder21) to Add button --%>
<%-- 09/13/2013 C Shimek     	#76047 added confirmUpdate code for Trait Status Update --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ page import="naming.Features" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>




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

<%-- Javascript for emulated navigation --%>
<title><bean:message key="title.heading" /> - juvenileInterviewInfoDrugs.jsp</title>
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/drugs.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<script>
	$(document).ready(function(){
		if ('C' === '<bean:write name="juvenileDrugForm" property="juvenileMasterStatus"/>' ) {
			$("#addDrugTestingInformation").prop("disabled",true)
			$("#addSubstanceAbuse").prop("disabled",true);
			$("#addDrugInformation").prop("disabled",true);
			$("#addMoreTraitsId").prop("disabled",true);
			$("#addMoreTraitsId2").prop("disabled",true);
		}
	})
</script>
</head>
<%--END HEADER TAG--%>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin="0" leftmargin="0">

<%-- BEGIN HEADING TABLE --%>
<table width="98%">
	<tr>
		<td align="center" class="header">
			<logic:notEqual name="juvenileTraitsForm" property="action" value="confirm">
				<logic:notEqual name="juvenileTraitsForm" property="action" value="confirmUpdate">			
					<logic:notEqual name="juvenileDrugForm" property="action" value="confirm">
						<bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - Drug/Substance Abuse 
						<tr>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td>
								<ul>
									<li>Click Add Drug Information button to add more Drugs/Substance Abuse	Information.</li>
								</ul>
							</td>
						</tr>
					</logic:notEqual>
				</logic:notEqual>	
			</logic:notEqual>
			<logic:equal name="juvenileTraitsForm" property="action" value="confirm">
				<bean:message key="title.juvenileCasework" /> - <bean:message key="title.juvenileProfile" /> -&nbsp;Drug/Substance Abuse Confirmation   				
				<tr>
					<td class="confirm">Trait(s) successfully added.</td>
				</tr>
			</logic:equal>
			<logic:equal name="juvenileTraitsForm" property="action" value="confirmUpdate">
				<bean:message key="title.juvenileCasework" /> - <bean:message key="title.juvenileProfile" /> -&nbsp;Drug/Substance Abuse Confirmation   				
				<tr>
					<td class="confirm">Trait status successfully updated.</td>
				</tr>
			</logic:equal>
			<logic:equal name="juvenileDrugForm" property="action" value="confirm">
				<bean:message key="title.juvenileCasework" /> - <bean:message key="title.juvenileProfile" /> -&nbsp;Drug/Substance Abuse Confirmation   				
				<tr>
					<td class="confirm">Drug/Substance Abuse Information successfully added.</td>
				</tr>
			</logic:equal>
		</td>
	</tr>
</table>
<%-- END HEADING TABLE --%>

<%--juv profile header start--%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="profileheader" />
</tiles:insert>

<%-- BEGIN DETAIL TABLE --%>
<div class="spacer"></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td valign="top">
		<table width='100%' border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td valign="top">
					<tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
						<tiles:put name="tabid" value="main" />
						<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
					</tiles:insert>
				</td>
			</tr>
			<tr>
				<td bgcolor='#33cc66'><img src="/<msp:webapp/>images/spacer.gif" width="5" /></td>
			</tr>
		</table>

		<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
			<tr>
				<td valign="top" align="center">

				<div class="spacer"></div>
				<table width='98%' border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td valign="top"><%--tabs start--%> <%--script type='text/javascript'>renderInterviewInfo("Gang")</script--%>
 						<tiles:insert page="../caseworkCommon/interviewInfoTabs.jsp" flush="true">
 							<tiles:put name="tabid" beanName="juvenileTraitsForm" beanProperty="categoryName" />
 							<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
 						</tiles:insert> <%--tabs end--%>
					  </td>
				  </tr>
				
			<tr>
				<td bgcolor='#6699ff'><img src="/<msp:webapp/>images/spacer.gif" width="5" /></td>
			</tr>
		</table>
		
		
		


		<table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
		  
			<tr>
				<td valign="top" align="center">
				  

  				<logic:notEqual name="juvenileDrugForm" property="action" value="confirm">
  					<%-- BEGIN TRAITS TABLE --%>
					<span class="spacer4px"></span>
  					<tiles:insert page="../caseworkCommon/juvenileTraitsSearch.jsp"> 
  						<tiles:put name="actionPath" value="/handleJuvenileProfileTraits"/>
  					</tiles:insert>
  					<%-- END TRAITS TABLE --%>
  				
  				</logic:notEqual> <%-- BEGIN DRUGS TABLE --%> 
  				
				<table width='98%' border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
					<tr class="detailHead">
						<td colspan="9"><a href="javascript:showHideMulti('drugTesting', 'drugTestingInfo', 1, '/<msp:webapp/>');" border=0><img border="0" src="/<msp:webapp/>images/expand.gif" name="drugTesting"></a>&nbsp;&nbsp;Drug Testing</td>
					</tr>
					<tr id="drugTestingInfo0" class="hidden">
						<td  colspan="9">
							<table width='100%' border="0" cellpadding="2" cellspacing="1" >
								<tr id="drugTestingInfo0" class="detailHead">
									<td>Drug Testing Id</td>
									<td>Create Date</td>
									<td>Associated Casefile</td>
									<td>Test Date</td>
									<td>Test Time</td>
									<td>Test Administered</td>
									<td>Test Location</td>
									<td>Administered By</td>
									<td>Substance Tested</td>
									<td>Test Results</td>
									<td>Result Outcome</td>
									<td>Comments</td>
								</tr>
								<logic:iterate id="drugTestingInfo" name="juvenileDrugForm" property="drugTestingInfoList">
									<tr>
										<td class="formDe"><bean:write name="drugTestingInfo" property="drugTestingId"/></td>
										<td class="formDe"><bean:write name="drugTestingInfo" property="createDate" formatKey="date.format.mmddyyyy"/></td>
										<td class="formDe"><bean:write name="drugTestingInfo" property="associateCasefile"/></td>
										<td class="formDe"><bean:write name="drugTestingInfo" property="testDate" formatKey="date.format.mmddyyyy"/></td>
										<td class="formDe"><bean:write name="drugTestingInfo" property="testTime" formatKey="time.format.HHmm"/></td>
										<td class="formDe"><span title="<bean:write name="drugTestingInfo" property="testAdministeredDesc"/>"><bean:write name="drugTestingInfo" property="testAdministered"/></span></td>
										<td class="formDe"><span title="<bean:write name="drugTestingInfo" property="testLocationDesc"/>"><bean:write name="drugTestingInfo" property="testLocation"/></span></td>
										<td class="formDe"><span title="<bean:write name="drugTestingInfo" property="administeredByName"/>"><bean:write name="drugTestingInfo" property="administeredBy"/></span></td>
										<td class="formDe"><span title="<bean:write name="drugTestingInfo" property="substanceTestedDesc"/>"><bean:write name="drugTestingInfo" property="substanceTested"/></span></td>
										<td class="formDe"><span title="<bean:write name="drugTestingInfo" property="drugTestResultsDesc"/>"><bean:write name="drugTestingInfo" property="drugTestResults"/></span></td>
										<td class="formDe"><span title="<bean:write name="drugTestingInfo" property="drugTestResultsResult"/>"><bean:write name="drugTestingInfo" property="drugTestResultsResult"/></span></td>
										<td class="formDe"><bean:write name="drugTestingInfo" property="comments"/></td>
									</tr>
								</logic:iterate>
							</table>
						</td>
					</tr>
					<div class="spacer"></div>
					<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_MAS_ADD_DRUG%>'>
					<tr align="center">
						<td  colspan="9">
							<logic:notEqual name="juvenileDrugForm" property="action" value="confirm">
								<html:form action="/displayJuvenileDrugTestingCreate.do?clr=Y" target="content">
									<input id="addDrugTestingInformation" type="submit" value="Add Drug Testing Information"/>
								</html:form>
							</logic:notEqual>
						</td>
					</tr>
					</jims2:isAllowed>
				</table>
				
				
				<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|173">
					<%-- Begin Pagination Header Tag--%>
					<bean:define id="paginationResultsPerPage" type="java.lang.String">
  				 	<bean:message key="pagination.recordsPerPage"></bean:message>
					</bean:define>

					<pg:pager index="center"  maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>" maxIndexPages="10" export="offset,currentPageNumber=pageNumber" scope="request">

						<input type="hidden" name="pager.offset" value="<%= offset %>">
						<%-- End Pagination header stuff --%>
						<table width='98%' border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
							<%-- display detail header --%>

							<logic:notEmpty name="juvenileDrugForm" property="drugsList">
	 							<tr class="detailHead">
		   							<logic:notEqual name="juvenileDrugForm" property="action" value="confirm">
	    	 							<td><bean:message key="prompt.entryDate" /></td>
	   								</logic:notEqual>
				          	      	<td><bean:message key="prompt.onsetAge" /></td>
					                <td><bean:message key="prompt.drugType" /></td>
				    	            <td><bean:message key="prompt.frequency" /></td>
				        	        <td><bean:message key="prompt.locationOfUse" /></td>
				            	    <td><bean:message key="prompt.amountSpent" /></td>
				                	<td><bean:message key="prompt.degree" /></td>
	 							</tr>
							</logic:notEmpty>

							<logic:empty name="juvenileDrugForm" property="drugsList">
								<tr class="detailHead">
	 								<td colspan="6">No Drug Information Available</td>
								</tr>
							</logic:empty>

							<%-- display detail info --%>
							<logic:notEqual name="juvenileDrugForm" property="action" value="confirm">
								<logic:iterate id="drugsIndex" name="juvenileDrugForm" property="drugsList" indexId="indexer">
									<%-- Begin Pagination item wrap --%>
									<pg:item>
										 <tr class="<%out.print( (indexer.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">
											<td class="formDe"><bean:write name="drugsIndex" property="entryDate" formatKey="date.format.mmddyyyy"/></td>
											<td class="formDe"><bean:write name="drugsIndex" property="onsetAge" /></td>
											<td class="formDe"><bean:write name="drugsIndex" property="drugType" /></td>
											<td class="formDe"><bean:write name="drugsIndex" property="frequency" /></td>
											<td class="formDe"><bean:write name="drugsIndex" property="locationOfUse" /></td>
											<td class="formDe"><bean:write name="drugsIndex" property="amountSpent" /></td>
											<td class="formDe"><bean:write name="drugsIndex" property="degree" /></td>
										</tr>
									</pg:item>
									<%-- End Pagination item wrap --%>
								</logic:iterate>
							</logic:notEqual>

							<logic:equal name="juvenileDrugForm" property="action" value="confirm">
								<logic:iterate id="drugsIndex" name="juvenileDrugForm" property="newDrugInfoList" indexId="indexer">
									<%-- Begin Pagination item wrap --%>
									<pg:item>
										<tr class="<%out.print( (indexer.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">
											<td class="formDe"><bean:write name="drugsIndex" property="onsetAge" /></td>
											<td class="formDe"><bean:write name="drugsIndex" property="drugTypeDesc" /></td>
											<td class="formDe"><bean:write name="drugsIndex" property="frequencyDesc" /></td>
											<td class="formDe"><bean:write name="drugsIndex" property="locationOfUseDesc" /></td>
											<td class="formDe"><bean:write name="drugsIndex" property="amountSpentDesc" /></td>
											<td class="formDe"><bean:write name="drugsIndex" property="degreeDesc" /></td>
										</tr>
									</pg:item>
									<%-- End Pagination item wrap --%>
								</logic:iterate>
							</logic:equal>
								<tr align="center">
							  		<td>
							    		<html:form action="/displayJuvenileDrugsCreate" target="content">
							  	   			<logic:notEqual name="juvenileDrugForm" property="action" value="confirm">
							  	   				<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRF_MAS_DRUG_U%>'>
							  	   					<logic:equal name="juvenileProfileHeader" property="juvUnder21" value="true">
							  	   						<logic:notEqual name="juvenileProfileHeader" property="status" value="CLOSED">
							  							<html:submit styleId="addDrugInformation" property="submitAction"><bean:message key="button.addDrugInformation" /></html:submit>
							  							</logic:notEqual>
							  						<logic:equal name="juvenileProfileHeader" property="status" value="CLOSED">
													<jims2:isAllowed requiredFeatures='<%=Features.JCW_JP_ENABLEMASTERTAB_CLOSEDJUV%>'>	
													<html:submit styleId="addDrugInformation" property="submitAction"><bean:message key="button.addDrugInformation" /></html:submit>
													</jims2:isAllowed>
													</logic:equal>
							  						</logic:equal>	
							  					</jims2:isAllowed>
							  			  	</logic:notEqual>
							 			</html:form>
							  		</td>
								</tr>
						</table>
						
						<table width='98%' border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
							<logic:empty name="juvenileDrugForm" property="substanceAbuseInfoList">
								<tr class="detailHead">
	 								<td colspan="6">No Substance Abuse Information Available</td>
								</tr>
							</logic:empty>
							<logic:notEmpty name="juvenileDrugForm" property="substanceAbuseInfoList">
							<tr class="detailHead">
								<td colspan="9"><a href="javascript:showHideMulti('substanceAbuse', 'substanceAbuseInfo', 1, '/<msp:webapp/>');" border=0><img border="0" src="/<msp:webapp/>images/expand.gif" name="substanceAbuse"></a>&nbsp;&nbsp;Substance Abuse(TJJD Required)</td>
							</tr>
							<tr id="substanceAbuseInfo0" class="hidden">
								<td  colspan="9">
								<table width='100%' border="0" cellpadding="2" cellspacing="1" >
									<tr id="substanceAbuseInfo0" class="detailHead">
										<td>Substance Abuse Id</td>
										<td>Create Date</td>
										<td>Associated Casefile</td>
										<td>Referral Number</td>
										<td>Substance Abuse</td>
										<td>Substance Type</td>
									</tr>
									<logic:iterate id="substanceAbuseInfo" name="juvenileDrugForm" property="substanceAbuseInfoList">
										<tr>
											<td class="formDe"><bean:write name="substanceAbuseInfo" property="substanceAbuseId"/></td>
											<td class="formDe"><bean:write name="substanceAbuseInfo" property="createDate" format="MM/dd/yyyy"/></td>
											<td class="formDe"><bean:write name="substanceAbuseInfo" property="associatedCasefileId"/></td>
											<td class="formDe"><bean:write name="substanceAbuseInfo" property="referralNumber" /></td>
											<td class="formDe"><span title="<bean:write name="substanceAbuseInfo" property="substanceAbuseDesc"/>"><bean:write name="substanceAbuseInfo" property="substanceAbuse" /></span></td>
											<td class="formDe"><span title="<bean:write name="substanceAbuseInfo" property="substanceTypeDesc"/>"><bean:write name="substanceAbuseInfo" property="substanceType" /></span></td>
									</logic:iterate>
								</table>
								</td>
							</tr>
							</logic:notEmpty>
							<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_MAS_ADD_DRUG%>'>
							<tr align="center">
								<td  colspan="9">
										<html:form action="/displaySubstanceAbuseCreate.do?clr=Y" target="content">
											<input id="addSubstanceAbuse" type="submit" value="Add Substance Abuse"/>
										</html:form>
								</td>
							</tr>
							</jims2:isAllowed>
						</table>

						<%-- Begin Pagination navigation Row--%>
						<table align="center">
							<tr>
								<td><pg:index>
									<tiles:insert page="/jsp/jimsPagination.jsp" flush="true">
										<tiles:put name="pagerUniqueName" value="pagerSearch" />
										<tiles:put name="resultsPerPage" value="<%=paginationResultsPerPage%>" />
									</tiles:insert>
								</pg:index></td>
							</tr>
						</table>
						<%-- End Pagination navigation Row--%>
					</pg:pager>
					<%-- End Pagination Closing Tag --%>
				</td>
			</tr>

			<div class="spacer"></div>
			
		
		
		  <%-- BEGIN BUTTON TABLE --%> 
			<tr>
			  <td>
		      	<table align="center">
				    <tr>
						  <td>
		 				    <html:form action="/handleJuvenileDrugsCreate" target="content">
		  					  <logic:equal name="juvenileDrugForm" property="action" value="confirm">
			  						<html:submit property="submitAction"><bean:message key="button.backToList"></bean:message></html:submit>
		  					  </logic:equal>
		 				    </html:form>
						  </td>
				    </tr>
			    </table><%-- END BUTTON TABLE --%> 
			  </td>
			</tr>	
			</table>
			<%-- BEGIN BUTTON TABLE --%>
			<div class="spacer"></div> 
			<table align="center">
			    <tr>
 				    <logic:notEqual name="juvenileDrugForm" property="action" value="confirm">
	  					<td><html:button property="button.back" onclick="history.back();"><bean:message key="button.back" /></html:button></td>
	  					<html:form action="/displayJuvenileMasterInformation" target="content">
	  					   <td><html:submit property="submitAction"><bean:message key="button.cancel" /></html:submit></td>
	  					</html:form>   
  					</logic:notEqual>
				  </td>
			    </tr>
		    </table>
		    <div class="spacer"></div>
		    <%-- END BUTTON TABLE --%> 
		</td>
	</tr>
</table>
<%-- END DETAIL TABLE --%> 
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>