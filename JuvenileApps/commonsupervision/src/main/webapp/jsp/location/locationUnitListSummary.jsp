<!DOCTYPE HTML>
<%-- 06/07/2007	 Debbie Williamson - Create JSP --%>
<%-- 10/13/2015  RYoung  - #30342 MJCW: IE11 conversion of HCJPD link on UILeftNav (UI - Search Location)--%>
<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>

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
<title><bean:message key="title.heading"/> - location/locationUnitListSummary.jsp</title>
<html:javascript formName="locationCreateForm"/>

<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>case_util.js"></script>

<script type="text/javascript">

function determineNextURL()
{
  switch( location.search )
  {
    case "?createSummary":
      goNav('locationUnitListSummary.htm?createConfirm' ) ;
      break;
    case "?updateSummary":
      goNav('locationUnitListSummary.htm?updateConfirm' ) ;
      break;
    default:
      break;
  }
}

function determineCancelURL()
{
 	if( location.search == "?create" )
	{
    goNav('locationDetails.htm?createSummary');
	}
	else
	{
    goNav('locationSearchResults.htm');
	}
}
</script>
</head>

<body topmargin=0 leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true);">
<html:form action="/submitLocationUnitListUpdate" target="content">

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
            <!--tabs start-->
  					<tiles:insert page="../common/commonSupervisionTabs.jsp" flush="true">
  						<tiles:put name="tabid" value=""/>
  					</tiles:insert>						
            <!--tabs end-->
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
          
            <!-- BEGIN HEADING TABLE -->
            <table width='100%'>
                 <logic:equal name="locationForm" property="action" value="createSummary">
                   <tr>
					 <td align="center" class="header">
                         <bean:message key="title.create"/> 
  						 <bean:message key="prompt.locationUnit"/>
                         <bean:message key="prompt.list"/>
                         <bean:message key="title.summary"/>
                     </td>
                   </tr>    
                 </logic:equal>
                 <logic:equal name="locationForm" property="action" value="updateSummary">
                   <tr>   
					 <td align="center" class="header">
                         <bean:message key="title.update"/> 
  						 <bean:message key="prompt.locationUnit"/>
                         <bean:message key="prompt.list"/>
                         <bean:message key="title.summary"/>
                     </td>
                   </tr> 
                 </logic:equal>
                 <logic:equal name="locationForm" property="action" value="createConfirm">
                   <tr> 
					 <td align="center" class="header">
                         <bean:message key="title.create"/> 
  						 <bean:message key="prompt.locationUnit"/>
                         <bean:message key="prompt.list"/>
                         <bean:message key="title.confirmation"/>
                     </td>
                   </tr>
				   <tr>
					 <td class=confirm>Location Units successfully updated.</td>
				   </tr>
                 </logic:equal>
                 <logic:equal name="locationForm" property="action" value="updateConfirm">
                   <tr>
					 <td align="center" class="header">
                         <bean:message key="title.update"/> 
  						 <bean:message key="prompt.locationUnit"/>
                         <bean:message key="prompt.list"/>
                         <bean:message key="title.confirmation"/>
                     </td>
                   </tr>
				   <tr>
					 <td class=confirm>Location Units successfully updated.</td>
				   </tr>
                 </logic:equal>
            </table>
		    <!-- BEGIN ERROR TABLE -->
			<table width=98% align=center>							
				<tr>
					<td align="center" class="errorAlert"><html:errors></html:errors></td>
				</tr>		
			</table>
		    <!-- END ERROR TABLE -->

            <!-- END HEADING TABLE -->

            <!-- BEGIN INSTRUCTION TABLE -->
						<br>
            <table width="98%" border="0">
				<jims:if name="locationForm" property="action" value="createSummary" op="equal">
				<jims:elseif name="locationForm" property="action" value="updateSummary" op="equal">	
				<jims:then> 
            	<tr>
            		<td>
  					<ul>
              			<li>Review information, then select Finish button to view confirmation.</li>
              		</ul>
                    </td>
                </tr>
                </jims:then>
                </jims:elseif>
                </jims:if>
             
				<jims:if name="locationForm" property="action" value="createConfirm" op="equal">
				<jims:elseif name="locationForm" property="action" value="updateConfirm" op="equal">	
				<jims:then> 
            	<tr>
            		<td>
  					<ul>
              			<li>Select Location Search button to return to Location Search.</li>
              		</ul>
  					</td>
            	</tr>
            </table>
            <!-- END INSTRUCTION TABLE -->
  					<!-- location start -->
						<br>
  					<table align="center" width='98%' border="0" cellpadding="2" cellspacing="0" class=borderTableBlue>
  						<tr class="detailHead">
  							<td>
  								<table width='100%' cellpadding=0 cellspacing=1>
  									<tr class=detailHead>
  										<td><bean:message key="prompt.location">&nbsp;<bean:message key="prompt.information"></td>
  									</tr>
  								</table>
  							</td>
  						</tr>

  						<tr>
  							<td>
  								<table width="100%" border=0 cellpadding="4" cellspacing="1">
  									<tr>
  										<td class="formDeLabel" nowrap><bean:message key="prompt.locationId"></td>
  										<td class="formDe"><bean:write name="locationForm" property="locationCode" /></td>
  										<td class="formDeLabel" nowrap width='1%'><bean:message key="prompt.location"></td>
  										<td class="formDe"><bean:write name="locationForm" property="locationName" /></td>
  									</tr>
  									<tr>
  										<td class="formDeLabel" nowrap width='1%'><bean:message key="prompt.inHouse"></td>
  										<td class="formDe"><bean:write name="locationForm" property="inHouse" /></td>
  										<td class="formDeLabel" nowrap><bean:message key="prompt.facilityType"></td>
  										<td class="formDe"><bean:write name="locationForm" property="facilityType" /></td>
  									</tr>
  									<tr>
  										<td class="formDeLabel" nowrap><bean:message key="prompt.phone"></td>
  										<td class="formDe"><bean:write name="locationForm" property="phone" /></td>
  										<td class="formDeLabel" nowrap width='1%'><bean:message key="prompt.status"></td>
  										<td class="formDe"><bean:write name="locationForm" property="status" /></td>
  									</tr>
  									<tr>
  										<td class="detailHead" colspan=4><bean:message key="prompt.address"></td>
  									</tr>

  									<tr>
  										<td colspan=4 class=formDe>
  											<table width="100%" border=0 cellpadding="2" cellspacing="1">
  												<tr>
  													<td class=formDeLabel><bean:message key="prompt.streetNumber"></td>
  													<td class=formDeLabel colspan="2"><bean:message key="prompt.streetName"></td>
  												</tr>
  												<tr>
  													<td class=formDe><bean:write name="locationForm" property="locationAddress.streetNumber" /></td>
  													<td class=formDe colspan="2"><bean:write name="locationForm" property="locationAddress.streetName" /></td>
  												</tr>
  												<tr>
  													<td class=formDeLabel><bean:message key="prompt.streetType"></td>
  													<td class=formDeLabel colspan="2"><bean:message key="prompt.aptSuite"></td>
  												</tr>
  												<tr>
  													<td class=formDe><bean:write name="locationForm" property="locationAddress.streetType" /></td>
  													<td class=formDe colspan="2"><bean:write name="locationForm" property="locationAddress.aptNumber" /></td>
  												</tr>
  
  												<tr>
  													<td class=formDeLabel><bean:message key="prompt.city"></td>
  													<td class=formDeLabel><bean:message key="prompt.state"></td>
  													<td class=formDeLabel><bean:message key="prompt.zipCode"></td>
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
								<td>
									<table width='100%' cellpadding=0 cellspacing=1>
										<tr class=detailHead>
											<td><bean:message key="prompt.locationUnits"></td>
										</tr>
  								</table>
  							</td>
  						</tr>
  						<tr>
  							<td>
									<div class=scrollingDiv200>
  								<table width="100%" border=0 cellpadding="4" cellspacing="1">
  									<tr class=formDeLabel>
  										<td><bean:message key="prompt.locationUnitId"></td>
  										<td><bean:message key="prompt.locationUnit"></td>
  										<td><bean:message key="prompt.phone"></td>
  										<td><bean:message key="prompt.status"></td>
  									</tr>
							  <%int RecordCounter = 0;
							  String bgcolor = "";%>  
							  <input type=hidden id="locationId" name="locationId">
								 <logic:iterate id="locationUnitIndex" name="locationForm" property="locationUnitList">
									<tr class=<%RecordCounter++;
										bgcolor = "alternateRow";
										if (RecordCounter % 2 == 1)
											bgcolor = "normalRow";
										out.print(bgcolor);%>>
  										<td><bean:write name="locationUnitIndex" property="locationUnitId" /></td>
  										<td><bean:write name="locationUnitIndex" property="locationUnitName" /></td>
  										<td><bean:write name="locationUnitIndex" property="phone" /></td>
  										<td><bean:write name="locationUnitIndex" property="status" /></td>
  									</tr>
                                 </logic:iterate>
								</table>
								</div>
							</td>
						</tr>
  					</table>
						<!-- end location unit table -->

   					<!-- begin location unit button table -->
					<table border="0" width="100%">
  						<tr>
  							<td colspan='4' align='center'>
      						<table>
								<jims:if name="locationForm" property="action" value="createSummary" op="equal">
								<jims:elseif name="locationForm" property="action" value="updateSummary" op="equal">	
								<jims:then> 
      							<tr>
       								<td>
       									<html:submit property="submitAction">
											<bean:message key="button.back"></bean:message>
										</html:submit>
										<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);">
											 <bean:message key="button.finish"></bean:message>
										</html:submit>
       									<!--input type="button" value="Finish"  onClick="determineNextURL();"-->
										<html:submit><bean:message key="button.cancel"/></html:submit>
       									<!--input type="button" value="Cancel"  onClick="determineCancelURL();"-->
       								</td>
      							</tr>
                                </jims:then>
                                </jims:elseif>
                                </jims:if>

								<jims:if name="locationForm" property="action" value="createConfirm" op="equal">
								<jims:elseif name="locationForm" property="action" value="updateConfirm" op="equal">	
								<jims:then> 
      							<tr>
       								<td>
                                        <html:submit property="submitAction">
											 <bean:message key="button.locationSearch"></bean:message>
										</html:submit>
       									<!--input type="button" value="Location Search"  onClick="goNav('locationSearch.htm');"-->
       								</td>
      							</tr>
                                </jims:then>
                                </jims:elseif>
                                </jims:if>
      						</table>
      					</td>
  						</tr>
  					</table>

          </td>
        </tr>
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
