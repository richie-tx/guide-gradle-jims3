<!DOCTYPE HTML>
<%-- 06/07/2007	 Debbie Williamson - Create JSP --%>
<%-- 10/13/2015  RYoung  - #30342 MJCW: IE11 conversion of HCJPD link on UILeftNav (UI - Search Location)--%>
<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>

<%-- TAB LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>




<%--BEGIN HEADER TAG--%>
<head>
<msp:nocache />

<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/csbase.css" />
<html:base />
<title><bean:message key="title.heading"/> - location/locationUnitDetail.jsp</title>
<html:javascript formName="locationCreateForm"/>
<%@include file="../jQuery.fw"%>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/case_util.js"></script>

<script type="text/javascript">
$(document).ready(function(){
	$("#finishBtn, #cancelBtn").click(function(){
    	sessionStorage.removeItem("maysi");
    	sessionStorage.removeItem("drugTesting");
    	sessionStorage.removeItem("interviewCal");
    	sessionStorage.removeItem("officerProfile");
    	
    })
})

function onloadForm()
{
	if( period == "yes" )
	{
	  show( 'futureEventsTable', 1, "table" ) ;
	}
}

function buildURL()
{
  var url = "locationUnitDetail.htm?" +action +"&action=" +action +"&misc=conf" ; 
  goNav( url );
}

</script>
</head>

<body topmargin=0 leftmargin="0">
<html:form action="/submitLocationUnitUpdate" target="content">
<logic:equal name="locationForm" property="action" value="update">
	<logic:equal name="locationForm" property="misc" value="summary">
	   <input type="hidden" name="helpFile" value="commonsupervision/Location/Administer_Location.htm#|409">
	</logic:equal>   
    <logic:equal name="locationForm" property="misc" value="confirm">
       <input type="hidden" name="helpFile" value="commonsupervision/Location/Administer_Location.htm#|410">
    </logic:equal>   
</logic:equal>
<logic:equal name="locationForm" property="action" value="activate">
	<logic:equal name="locationForm" property="misc" value="summary">
	   <input type="hidden" name="helpFile" value="commonsupervision/Location/Administer_Location.htm#|413">
	</logic:equal>   
	<logic:equal name="locationForm" property="misc" value="confirm">
	   <input type="hidden" name="helpFile" value="commonsupervision/Location/Administer_Location.htm#|414">
	</logic:equal>    
</logic:equal>
<logic:equal name="locationForm" property="action" value="inactivate">
	<logic:equal name="locationForm" property="misc" value="summary">
	   <input type="hidden" name="helpFile" value="commonsupervision/Location/Administer_Location.htm#|411">
	</logic:equal>   
	<logic:equal name="locationForm" property="misc" value="confirm">
	   <input type="hidden" name="helpFile" value="commonsupervision/Location/Administer_Location.htm#|412">
	</logic:equal>    
</logic:equal>

<div align="center">

<table width="98%" border="0" cellpadding="0" cellspacing="0" >
  <tr>
    <td valign=top><img src='/<msp:webapp/>images/spacer.gif' height=5></td>
  </tr>
  <tr>
    <td valign=top>

      <table width='100%' border="0" cellpadding="0" cellspacing="0" >
        <tr>
          <td valign=top>
            <!--tabs start-->
  					<tiles:insert page="../common/commonSupervisionTabs.jsp" flush="true">
  						<tiles:put name="tabid" value=""/>
  					</tiles:insert>						
            <!--tabs end-->
          </td>
        </tr>
        <tr>
          <td bgcolor='#6699ff'><img src='/<msp:webapp/>images/spacer.gif' height=5></td>
        </tr>
      </table>

      <table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
        <tr>
          <td><img src='/<msp:webapp/>images/spacer.gif' height=5></td>
        </tr>
        <tr>
          <td valign=top align=center>
          
            <!-- BEGIN HEADING TABLE -->
            <table width='100%'>
              <tr>
                <td align="center" class="header">
					<logic:equal name="locationForm" property="action" value="update">
						<bean:message key="title.update"/> 
					</logic:equal>
					<logic:equal name="locationForm" property="action" value="activate">
						<bean:message key="prompt.activate"/> 
					</logic:equal>
					<logic:equal name="locationForm" property="action" value="inactivate">
						<bean:message key="prompt.inactivate"/> 
					</logic:equal>
					<bean:message key="prompt.locationUnit"/>
					<logic:equal name="locationForm" property="misc" value="summary">
						<bean:message key="title.summary"/> 
					</logic:equal>
					<logic:equal name="locationForm" property="misc" value="confirm">
						<bean:message key="title.confirmation"/> 
					</logic:equal>
				</td>
              </tr>
            </table>
            <!-- END HEADING TABLE -->
          <logic:equal name="locationForm" property="misc" value="confirm">
            <table width="98%" border="0">
            	<tr>
            		<td class=confirm>Location Unit successfully
						<logic:equal name="locationForm" property="action" value="update">
							    updated.
						</logic:equal>
						<logic:equal name="locationForm" property="action" value="activate">
							    activated.
						</logic:equal>
						<logic:equal name="locationForm" property="action" value="inactivate">
							    inactivated.
						</logic:equal>
					</td>
            	</tr>
            </table>
          </logic:equal> 
            <!-- BEGIN INSTRUCTION TABLE -->
            <table width="98%" border="0">
            	<tr>
            		<td>
					  <logic:equal name="locationForm" property="misc" value="summary">
							<logic:equal name="locationForm" property="action" value="inactivate">
								<ul id='inactivateInstruction' class='hidden'>
									<li>Review information as required and select Finish button to Inactivate this Location Unit.</li>
									<li>Select the Back button to change information.</li>
								</ul>
							</logic:equal>
							<logic:equal name="locationForm" property="action" value="activate">
								<ul id='activateInstruction' class='hidden'>
									<li>Review information as required and select Finish button to Activate this Location Unit.</li>
									<li>Select the Back button to change information.</li>
								</ul>
							</logic:equal>
							<logic:equal name="locationForm" property="action" value="update">
								<ul id='summaryInstruction' class='hidden'>
									<li>Review information as required and select Finish button to save information.</li>
									<li>Select the Back button to change information.</li>
								</ul>
							</logic:equal>
					  </logic:equal>
					  <logic:equal name="locationForm" property="misc" value="confirm">
								<ul id='confirmInstruction' class='hidden'>
									<li>Select the Location Unit List button to return to Location Unit List</li>
								</ul>
					  </logic:equal>
  					</td>
            	</tr>
            </table>
            <bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define> 
		<pg:pager
			index="center"
			maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
			maxIndexPages="10"
			export="offset,currentPageNumber=pageNumber"
			scope="request">
			<input type="hidden" name="pager.offset" value="<%= offset %>">
  					<!-- location start -->
  					<table align="center" width='98%' border="0" cellpadding="2" cellspacing="0" class=borderTableBlue>
  						<tr class="detailHead">
  							<td>
  								<table width='100%' cellpadding=0 cellspacing=1>
  									<tr class=detailHead>
                      <td width='1%' class=detailHead>
                      <a href="javascript:showHideMulti('location', 'pchar', 1, '/<msp:webapp/>')">
                      <img border=0 src="/<msp:webapp/>images/expand.gif" name="location"></a></td>
  										<td><bean:message key="prompt.location"/>&nbsp;<bean:message key="prompt.information"/></td>
  									</tr>
  								</table>
  							</td>
  						</tr>

  						<tr id="pchar0" class='hidden'>
  							<td>
  								<table width="100%" border=0 cellpadding="4" cellspacing="1">
  									<tr>
  										<td class="formDeLabel" nowrap><bean:message key="prompt.locationID"/></td>
  										<td class="formDe"><bean:write name="locationForm" property="locationCd" /></td>
  										<td class="formDeLabel" nowrap width='1%'><bean:message key="prompt.location"/></td>
  										<td class="formDe"><bean:write name="locationForm" property="locationName" /></td>
  									</tr>
  									<tr>
  										<td class="formDeLabel" nowrap width='1%'><bean:message key="prompt.inHouse"/></td>
  										<td class="formDe"><bean:write name="locationForm" property="isInHouse" /></td>
  										<td class="formDeLabel" nowrap><bean:message key="prompt.facility"/>&nbsp;<bean:message key="prompt.type"/></td>
  										<td class="formDe"><bean:write name="locationForm" property="facilityType" /></td>
  									</tr>
  									<tr>
  										<td class="formDeLabel" nowrap><bean:message key="prompt.phone"/></td>
  										<td class="formDe"><bean:write name="locationForm" property="phoneNumber" /></td>
  										<td class="formDeLabel" nowrap width='1%'><bean:message key="prompt.status"/></td>
  										<td class="formDe"><bean:write name="locationForm" property="status" /></td>
  									</tr>
  									<tr>
  										<td class="detailHead" colspan=4><bean:message key="prompt.address"/></td>
  									</tr>

  									<tr>
  										<td colspan=4 class=formDe>
  											<table width="100%" border=0 cellpadding="2" cellspacing="1">
  												<tr>
  													<td class=formDeLabel><bean:message key="prompt.streetNumber"/></td>
  													<td class=formDeLabel colspan="2"><bean:message key="prompt.streetName"/></td>
  												</tr>
  												<tr>
  													<td class=formDe><bean:write name="locationForm" property="locationAddress.streetNumber" /></td>
  													<td class=formDe colspan="2"><bean:write name="locationForm" property="locationAddress.streetName" /></td>
  												</tr>
  												<tr>
  													<td class=formDeLabel><bean:message key="prompt.streetType"/></td>
  													<td class=formDeLabel colspan="2"><bean:message key="prompt.aptSuite"/></td>
  												</tr>
  												<tr>
  													<td class=formDe><bean:write name="locationForm" property="locationAddress.streetType" /></td>
  													<td class=formDe colspan="2"><bean:write name="locationForm" property="locationAddress.aptNumber" /></td>
  												</tr>
  
  												<tr>
  													<td class=formDeLabel><bean:message key="prompt.city"/></td>
  													<td class=formDeLabel><bean:message key="prompt.state"/></td>
  													<td class=formDeLabel><bean:message key="prompt.zipCode"/></td>
  												</tr>
  												<tr>
  													<td class=formDe><bean:write name="locationForm" property="locationAddress.city" /></td>
  													<td class=formDe><bean:write name="locationForm" property="locationAddress.state" /></td>
  													<td class=formDe><bean:write name="locationForm" property="locationAddress.zipCode" />-<bean:write name="locationForm" property="locationAddress.additionalZipCode" /></td>
  												</tr>
  											</table>
  										</td>
  									</tr>
  								</table>
  							</td>
  						</tr>
  					</table>
  					<!-- location end -->

						<br>
  					<table align="center" width='98%' border="0" cellpadding="2" cellspacing="0" class=borderTableBlue>
  						<tr class="detailHead">
  							<td><bean:message key="prompt.locationUnit"/></td>
							</tr>
							<tr>
								<td>
        					<!-- begin location unit table -->
  								<table width='100%' cellpadding='2' cellspacing='1'>
    								<tr>
    									<td class='formDeLabel' width='1%' nowrap><bean:message key="prompt.locationUnitId"/></td>
  										<td class="formDe" width='50%'><bean:write name="locationForm" property="locUnit.juvUnitCd" /></td>
  										<td class='formDeLabel' width='1%' nowrap>MAYSI</td>
  										<td class="formDe">
  										<logic:equal name="locationForm" property="locUnit.maysiFlag" value="1">Yes</logic:equal>
				   						<logic:equal name="locationForm" property="locUnit.maysiFlag" value="0">No</logic:equal>
  										</td>
  										<td class='formDeLabel' width='1%' nowrap>
  											Officer Profile
  										</td>
  										<td class="formDe">
  											<logic:equal name="locationForm" property="locUnit.officerProfileFlag" value="1">Yes</logic:equal>
				   							<logic:equal name="locationForm" property="locUnit.officerProfileFlag" value="0">No</logic:equal>
  										</td>
    								</tr>
  									<tr>
    									<td class='formDeLabel' width='1%' nowrap><bean:message key="prompt.locationUnit"/></td>
  										<td class="formDe" width='50%'><bean:write name="locationForm" property="locUnit.locationUnitName" /></td>
  										<td class='formDeLabel' width='1%' nowrap>Drug Testing</td><%-- <bean:message key="prompt.drugTesting"/> --%>
  										<td class="formDe">
  										
  										<logic:equal name="locationForm" property="locUnit.drugFlag" value="1">Yes</logic:equal>
				   						<logic:equal name="locationForm" property="locUnit.drugFlag" value="0">No</logic:equal>
  										</td>
  										<td class="formDe"></td>
										<td class="formDe"></td>
    								</tr>
  										
  									<tr>
  										<td class="formDeLabel" nowrap><bean:message key="prompt.phone"/></td>
  										<td class="formDe">
  										<logic:notEmpty name="locationForm" property="locUnit.phoneNumber.areaCode">
  										<bean:write name="locationForm" property="locUnit.phoneNumber.areaCode"/> -
  										<bean:write name="locationForm" property="locUnit.phoneNumber.prefix"/> -
  										<bean:write name="locationForm" property="locUnit.phoneNumber.last4Digit"/>
										</logic:notEmpty></td>
										<td class="formDeLabel" width='1%' nowrap >
  											Interview/Calendar
  										</td>
  										<td class="formDe">
  											<logic:equal name="locationForm" property="locUnit.interviewCalFlag" value="1">Yes</logic:equal>
				   							<logic:equal name="locationForm" property="locUnit.interviewCalFlag" value="0">No</logic:equal>
  										</td>
  										<td class="formDe"></td>
										<td class="formDe"></td>
  									</tr>
								</table>
							</td>
						</tr>
					</table>
					 <br>
   					<!-- end location unit table -->

          </td>
        </tr>
       
      </table>
		
		
  		
  			<!-- BEGIN summary CONFLICTING EVENTS TABLE -->
  			<br>
  			<logic:equal name="locationForm" property="action" value="inactivate">
  			<logic:notEmpty name="locationForm" property="serviceEventsList">
  			<table align="center" width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue">
  				<tr>
  					<td class="detailHead">Future Service Events Scheduled At This Location Unit</td>
  				</tr>
  				<tr>
  					<td valign=top align=center>
  						<table width='100%' cellpadding="4" cellspacing="1">
  							<tr bgcolor='#cccccc'>
  								<td class="subHead"><bean:message key="prompt.program"/></td>
  								<td class="subHead"><bean:message key="prompt.service"/></td>
  								<td class="subHead"><bean:message key="prompt.serviceProvider"/></td>
  								<td class="subHead"><bean:message key="prompt.dateTime"/></td>
  								<td class="subHead"><bean:message key="prompt.#Scheduled"/></td>
  							</tr>
		  					<%int RecordCounter = 0;
							String bgcolor = "";%>  
							<!-- Deleted Code -->
							<logic:iterate id="serviceEventsIndex" name="locationForm" property="serviceEventsList">
								<!-- Begin Pagination item wrap -->
								<pg:item>
									<tr
										class=<%RecordCounter++;
										bgcolor = "alternateRow";
										if (RecordCounter % 2 == 1)
											bgcolor = "normalRow";
										out.print(bgcolor);%>>
										<td><bean:write name="serviceEventsIndex" property="eventType" /></td>
										<td><bean:write name="serviceEventsIndex" property="serviceName" /></td>
										<td><bean:write name="serviceEventsIndex" property="serviceProviderName" /></td>
										<td><bean:write name="serviceEventsIndex" property="eventDate" formatKey="date.format.mmddyyyy"/></td>
										<td><bean:write name="serviceEventsIndex" property="currentEnrollment"/></td>
									</tr>
								</pg:item>
							</logic:iterate>
						    </logic:notEmpty>  	  	
  						</table>
  					</td>
  				</tr>
  			</table>
  			</logic:equal>
  			<!-- END CONFLICTING EVENTS TABLE formatKey="time.format.hhmma"-->
  		
<!-- Begin Pagination navigation Row-->
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
<!-- End Pagination navigation Row-->
<!-- Begin Pagination Closing Tag -->
</pg:pager>
<!-- End Pagination Closing Tag -->			
        
        

        
  	<table border="0" width="100%">
		<tr>
		<td colspan='4' align='center'>
  		<table>
		  <logic:equal name="locationForm" property="misc" value="summary">
  			<tr>
				<td>
					<html:submit property="submitAction">
						<bean:message key="button.back"></bean:message>
					</html:submit>
					<html:submit styleId="finishBtn" property="submitAction" onclick="return disableSubmit(this, this.form);">
						 <bean:message key="button.finish"></bean:message>
					</html:submit>
					<html:submit styleId="cancelBtn" property="submitAction"><bean:message key="button.cancel"/></html:submit>
					<!--input type="button" value="Finish" onClick="buildURL();"-->
					<!--input type="button" value="Cancel"  onClick="goNav('locationCreateUpdate.htm?mod');"-->
				</td>
  			</tr>
          </logic:equal>
		  <logic:equal name="locationForm" property="misc" value="confirm">
  			<tr>
				<td>
					<html:submit property="submitAction">
						 <bean:message key="button.locationUnitList"></bean:message>
					</html:submit>
					<!--input type="button" value="Location Unit List" onClick="goNav('locationUnitListUpdate.htm?mod');"-->
				</td>
  			</tr>
          </logic:equal>
  		</table>  		
    </td>
  </tr>
</table>
<!-- END  TABLE -->
</div>

</html:form>

<br>
<div align=center><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:html>

