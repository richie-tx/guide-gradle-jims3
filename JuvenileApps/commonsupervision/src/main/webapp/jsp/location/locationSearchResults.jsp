<!DOCTYPE HTML>
<%-- 10/03/2005	 Debbie Williamson - Create JSP --%>
<!-- jjose	08/13/2007	ER#JIMS200044394-ADD Pagination -->
<!-- CShimek 09/21/2007 #45433 added MapQuest link to address -->
<%-- RYoung  10/13/2015 #30342 MJCW: IE11 conversion of HCJPD link on UILeftNav (UI - Search Location)--%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ page import="naming.Features" %>
<%-- TAB LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>



<%--BEGIN HEADER TAG--%>
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/commonsupervision.css" />
<html:base />
<title><bean:message key="title.heading" /> - location/locationSearchResults.jsp</title>

<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script><tiles:insert page="../../js/common_supervision_util.js" flush="true"/></script>
<script type="text/javascript" src="/<msp:webapp/>case_court_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/address.js"></script>
<%-- JavaScript for emulated navigation --%>
<script type="text/javascript" src="/<msp:webapp/>js/case_util.js"></script>


<script type="text/javascript">
function checkStatus(theValue,locId)
{
  document.getElementById("locationId").value = locId;
  if ( theValue == "A")
  {
  	show("inactivateButton", 1, "inline")
  	//show("updateButton", 1, "inline")  commented out for #JIMS200043650
  	show("manageLocationUnitsButton", 1, "inline")
  	show("activateButton", 0)
  }
  else 
  {
  	show("activateButton", 1, "inline")
  	//show("updateButton", 1, "inline")  commented out for #JIMS200043650
  	show("manageLocationUnitsButton", 1, "inline")
  	show("inactivateButton", 0)		
  }
}

function submitSelectedItem(action,locId,theForm)
{
  url = "/<msp:webapp/>handleLocationSelection.do?submitAction=" +action ;		 
  url = url + "&locationId=" + locId;
  changeFormActionURL1(theForm, url,true);
}

function viewJuvLocUnits(action,locId,theForm)
{
  url = "/<msp:webapp/>handleLocationUnitListUpdate.do?submitAction=" +action ;		 
  url = url + "&locationId=" + locId ;
  changeFormActionURL1(theForm, url, true);
}

function changeFormActionURL1(theForm, URL, doSubmit)
{
  theForm.action = URL;
  if(doSubmit)
	{
  	theForm.submit();
  }
  return true;
}

function clearRadios(radioObj) 
{
	var radioLength = radioObj.length;

	for( var i = 0; i < radioLength; i++) 
  {
		radioObj[i].checked = false;
	}
}
</script>
</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true)" topmargin=0 leftmargin="0" onload="clearRadios(document.forms[0].elements['loc']);">
<html:form action="/handleLocationSelection" target="content">

<%-- Begin Pagination Header Tag--%>
<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define> 
<pg:pager index="center" maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
    maxIndexPages="10" export="offset,currentPageNumber=pageNumber" scope="request">

<input type="hidden" name="pager.offset" value="<%= offset %>">
<%-- End Pagination header stuff --%>

<input type="hidden" name="helpFile" value="commonsupervision/Location/Administer_Location.htm#|395">

<div align="center">
<table width="98%" border="0" cellpadding="0" cellspacing="0" >
  <tr>
    <td valign=top><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
  </tr>
  <tr>
    <td valign=top>

      <table width='100%' border="0" cellpadding="0" cellspacing="0" >
        <tr>
          <td valign=top>
            <%--blue tabs start--%>
      				<tiles:insert page="../common/commonSupervisionTabs.jsp" flush="true">
      					<tiles:put name="tabid" value=""/>
      				</tiles:insert>						
            <%--blue tabs end--%>
          </td>
        </tr>
        <tr>
          <td bgcolor='#6699ff'><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
        </tr>
      </table>

      <table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
        <tr>
          <td><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
        </tr>
        <tr>
          <td valign=top align=center>

            <%-- BEGIN HEADING TABLE --%>
            <table width='100%'>
              <tr>
                <td align="center" class="header" ><bean:message key="prompt.location" /> <bean:message key="prompt.searchResults" /></td>
              </tr>
            </table>
            <%-- END HEADING TABLE --%>
        		<%-- BEGIN ERROR TABLE --%>
      		  <table width='98%' align=center>							
        			<tr>
        				<td align="center" class="errorAlert"><html:errors></html:errors></td>
        			</tr>		
      		  </table>
        		<%-- END ERROR TABLE --%>

            <%-- BEGIN INSTRUCTION TABLE --%>
            <table width="98%" border="0">
              <tr>
                <td>
                  <ul>
                    <li>Select location hyperlink to view location details.</li>
                    <li>Select radio button next to location to Update or Inactivate a location.</li>
                    <li>Select Create Location to create a new location.</li>
                    <li>Select View All next to a service provider to view all service providers with that location.</li>
                  </ul>
                </td>
              </tr>
            </table>
            <%-- END INSTRUCTION TABLE --%>																					

            <%-- BEGIN DETAIL TABLE --%>
      		  <table>
        			<tr>
      				  <td align="center">
      						<bean:size id="locationSize" name="locationForm" property="locationSearchResults"/>
      						<b><bean:write name="locationSize"/></b>&nbsp; location(s) found in search results.
								</td>            					
        			</tr>
      		  </table>            												

            <table width="98%" border="0" cellpadding="2" cellspacing="1" class=borderTableBlue>
              <tr class="detailHead">
                <td width='1%'></td>
                <td align="left">
                  <bean:message key="prompt.name" />
                  <jims2:sortResults beanName="locationForm" results="locationSearchResults" primaryPropSort="locationName" primarySortType="STRING" defaultSort="true" defaultSortOrder="ASC" sortId="1" />
                </td>
                <td align="left">
                  <bean:message key="prompt.service" /><nobr><bean:message key="prompt.provider"/>(s)</nobr>
                  <jims2:sortResults beanName="locationForm" results="locationSearchResults" primaryPropSort="serviceProviderName" primarySortType="STRING" defaultSortOrder="ASC" sortId="2" />
                </td>
                <td align="left">
								  <bean:message key="prompt.locationID" />
                  <jims2:sortResults beanName="locationForm" results="locationSearchResults" primaryPropSort="locationCd" primarySortType="STRING"  defaultSortOrder="ASC" sortId="3" />
				</td>
                <td align="left">
								  <bean:message key="prompt.location" />&nbsp;<bean:message key="prompt.status" />
                  <jims2:sortResults beanName="locationForm" results="locationSearchResults" primaryPropSort="status" primarySortType="STRING" defaultSortOrder="ASC" sortId="4" />
                </td>
                <td align="left">
								  <bean:message key="prompt.inHouse" />
                  <jims2:sortResults beanName="locationForm" results="locationSearchResults" primaryPropSort="inHouse" primarySortType="STRING" defaultSortOrder="ASC" sortId="5" />
                </td>
                <td align="left">
								  <bean:message key="prompt.address" />
                  <jims2:sortResults beanName="locationForm" results="locationSearchResults" primaryPropSort="streetNum" primarySortType="STRING" defaultSortOrder="ASC" sortId="6" />
                </td>
              </tr>

        			<%int RecordCounter = 0;
          			String bgcolor = "";%>  
          			<input type=hidden id="locationId" name="locationId" value="">

          			<logic:notEmpty name="locationForm" property="locationSearchResults">	
          				<logic:iterate id="location" name="locationForm" property="locationSearchResults">
          				 <pg:item>
          					<tr class=<%RecordCounter++;
          						bgcolor = "alternateRow";
          						if (RecordCounter % 2 == 1)
          							bgcolor = "normalRow";
          						out.print(bgcolor);%>>
          					  <td width='1%'>
          							<input type=radio name="loc" value='<bean:write name="location" property="statusId"/>'  onclick="checkStatus(this.value,<bean:write name="location" property="locationId"/>)" />
                      		  </td>
          					  <td align="left"><a href="/<msp:webapp/>handleLocationSelection.do?submitAction=View&locationId=<bean:write name="location" property="locationId" />">
          					  	  <bean:write name="location" property="locationName" /></a>
          					  </td>
  
          					  <td align="left"><bean:write name="location" property="serviceProviderName" />&nbsp;
								  <logic:notEmpty name="location" property="serviceProviderName">
								    <a href="javascript:openWindow('/<msp:webapp/>handleLocationSelection.do?submitAction=<bean:message key="button.viewAll" />&locationId=<bean:write name="location" property="locationId" />')"><bean:message key="prompt.viewAll" /></a>
								  </logic:notEmpty>
							  </td>
      						  <td align="left"><bean:write name="location" property="locationCd" /></td>
           					  <td align="left"><bean:write name="location" property="status" /></td>
           					  <td align="left"><jims2:displayBoolean name="location" property="inHouse" trueValue="YES" falseValue="NO"/></td>
        					  <td align="left">
    							 <a href="javascript:openMapquest('<bean:write name="location" property="streetNum" />', '<bean:write name="location" property="streetName" /> ', '<bean:write name="location" property="city" />', '<bean:write name="location" property="stateId" />', '<bean:write name="location" property="zipCode" />', '','')" title="Click to open Mapquest">       					  
       					  		 <bean:write name="location" property="streetNum" />
                  				 <bean:write name="location" property="streetName" />,&nbsp;<bean:write name="location" property="aptNumber" /><br>
      							 <bean:write name="location" property="city" />,
       	          				 <bean:write name="location" property="stateId" />&nbsp;
           	      				 <bean:write name="location" property="zipCode" /></a> 
      						  </td>
          					</tr>
        					</pg:item>
        				</logic:iterate>
        			</logic:notEmpty> 				
            </table>
            <%-- END DETAIL TABLE --%>

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

            <%-- BEGIN BUTTON TABLE --%>
            <table border="0" width="100%">
              <tr>
                <td align="center">
                  <jims2:isAllowed requiredFeatures='<%=Features.CS_LOC_CREATE%>'>
        			    <html:submit property="submitAction"><bean:message key="button.createLocation"></bean:message></html:submit>
        		  </jims2:isAllowed>
        		  <jims2:isAllowed requiredFeatures='<%=Features.CS_LOC_INACTIVATE%>'>	    
                        <input type="button" name="submitAction" value="Inactivate Location" onClick="submitSelectedItem('<bean:message key="button.inactivateLocation"/>',this.form.locationId.value,this.form)" class=hidden id="inactivateButton"/>
                  </jims2:isAllowed>
                  <jims2:isAllowed requiredFeatures='<%=Features.CS_LOC_ACTIVATE%>'>      
                        <input type="button" name="submitAction" value="Activate Location" onClick="submitSelectedItem('<bean:message key="button.activateLocation"/>',this.form.locationId.value,this.form)" class=hidden id="activateButton"/>
                  </jims2:isAllowed>                      
                  <input type="button" name="submitAction" value="Manage Location Units" onClick="viewJuvLocUnits('<bean:message key="button.manageLocationUnits"/>',this.form.locationId.value,this.form)" class=hidden id="manageLocationUnitsButton"/>
                </td>
              </tr>
              <tr>
                <td align="center">
          				<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.back"/></html:submit>&nbsp;
          				<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.cancel"></bean:message></html:submit>																					
      		      </td>
              </tr>
            </table>
            <%-- END BUTTON TABLE --%>
          </td>
        </tr>
      </table>
			<div class='spacer'></div>
    </td>
  </tr>
</table>
</div>
<%-- Begin Pagination Closing Tag --%>
</pg:pager>
<%-- End Pagination Closing Tag --%>

</html:form>

<div align=center><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:html>

