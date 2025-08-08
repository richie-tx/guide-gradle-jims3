<!DOCTYPE HTML>
<%-- 06/07/2007	 Debbie Williamson - Create JSP --%>
<%-- 09/24/2007  C Shimek			 #45346 add code so location units list displays in sorted order by loc. unit Id. --%>
<%-- 10/13/2015  RYoung  - #30342 MJCW: IE11 conversion of HCJPD link on UILeftNav (UI - Search Location)--%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ page import="naming.Features" %>
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
<title><bean:message key="title.heading"/> - location/locationUnitListUpdate.jsp</title>

<html:javascript formName="locationUnitForm"/>
<%@include file="../jQuery.fw"%>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/locationUnit.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/case_util.js"></script>

<script type="text/javascript">
$(document).ready(function(){
    	sessionStorage.removeItem("maysi");
    	sessionStorage.removeItem("drugTesting");
    	sessionStorage.removeItem("interviewCal");
    	sessionStorage.removeItem("officerProfile");
})
</script>
</head>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true)" topmargin=0 leftmargin="0">

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
  					<%--tabs start--%>
  					<tiles:insert page="../common/commonSupervisionTabs.jsp" flush="true">
  						<tiles:put name="tabid" value=""/>
  					</tiles:insert>						
  					<%--tabs end--%>
            <!--tabs start-->
            <%--script type='text/javascript'>renderTabs("")</script--%>
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
              <tr>
                <td align="center" class="header">
				<!-- 				
					<logic:equal name="locationForm" property="action" value="create">  <bean:message key="prompt.create"/> </logic:equal>
				    <logic:equal name="locationForm" property="action" value="update">  <bean:message key="prompt.update"/> </logic:equal>
				 -->
				    <bean:message key="prompt.update"/>
				    <bean:message key="prompt.location"/>
				    <bean:message key="prompt.unit"/>
				    <bean:message key="prompt.list"/>
				</td>
              </tr>
            </table>
           
          
            
            <logic:equal name="locationForm" property="action" value="create">
            <table width="98%" border="0">
            	<tr>
            		<td class=confirm>Location Unit successfully created. </td>
				</tr>
			</table>
			</logic:equal>
            <!-- END HEADING TABLE -->
		   
		   
		    
            <!-- BEGIN INSTRUCTION TABLE -->
            <table width="98%" border="0">
            	<tr>
            		<td>
  					  <ul>
              			<li>Enter Location Unit information and select Add Location Unit button to add new Location Unit.</li>
              			<li>Select Activate or Inactivate hyperlink to change the status for that Location Unit.</li>
              			<li>Select Edit hyperlink to change that Location Unit information.</li>
              		  </ul>
  					</td>
            	</tr>
            </table>

		<!-- BEGIN ERROR TABLE -->
			<table width=98% align=center>							
				<TBODY>
				<tr>
					<td align="center" class="errorAlert"><html:errors></html:errors></td>
				</tr>		
				</TBODY>
			</table>
		    <!-- END ERROR TABLE -->
	<html:form action="/handleLocationUnitListUpdate" target="content" focus="locUnit.juvUnitCd">
<input type="hidden" name="helpFile" value="commonsupervision/Location/Administer_Location.htm#|407">	    
  					<!-- location start -->
  					<table align="center" width='98%' border="0" cellpadding="2" cellspacing="0" class=borderTableBlue>
  						<tr class="detailHead">
  							<td>
  								<table width='100%' cellpadding=0 cellspacing=1>
  									<tr class=detailHead>
                      <td width='1%' class=detailHead>
                      <a href="javascript:showHideMulti('location', 'pchar', 1, '/<msp:webapp/>')">
                      <img border=0 src="/<msp:webapp/>images/expand.gif" name="location"> </a></td>
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
  													<td class=formDeLabel><bean:message key="prompt.streetNum"/></td>
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

						<!-- begin location unit table -->
						<br>
						<table align="center" width='98%' border="0" cellpadding="2" cellspacing="0" class=borderTableBlue>
							<tr id='locationUnitEdit'>
								<td>
									<table width='100%' cellpadding='2' cellspacing='1'>
										<tr class=detailHead>
											<td colspan='4'><bean:message key="prompt.locationUnit"/></td>
										</tr>
  									<tr>
  										<td class='formDeLabel' width='1%' nowrap><bean:message key="prompt.2.diamond"/><bean:message key="prompt.locationUnitId"/></td>
											<td class="formDe"><html:text name="locationForm" property="locUnit.juvUnitCd" size="7" maxlength="7"/></td>
  										<td class='formDeLabel' width='1%' nowrap><bean:message key="prompt.2.diamond"/><bean:message key="prompt.locationUnit"/></td>
											<td class="formDe"><html:text name="locationForm" property="locUnit.locationUnitName" size="50" maxlength="50"/></td>
  									</tr>
										
										<tr>
											<td class="formDeLabel" nowrap><bean:message key="prompt.phone"/></td>
											<td class="formDe" colspan="3">
												<html:text name="locationForm" property="locUnit.phoneNumber.areaCode" size="3" maxlength="3" onkeyup="return autoTab(this, 3);" />-
  										        <html:text name="locationForm" property="locUnit.phoneNumber.prefix" size="3" maxlength="3" onkeyup="return autoTab(this, 3);" />-
  										        <html:text name="locationForm" property="locUnit.phoneNumber.last4Digit" size="4" maxlength="4" onkeyup="return autoTab(this, 4);" />
											</td>
										</tr>
        						<!-- begin location unit button table -->
        						<jims2:isAllowed requiredFeatures='<%=Features.CS_LOC_CREATE%>'>
      							<tr>
      								<td colspan='4' align='center'>
          							<table>
          								<tr>
                                            <html:submit property="submitAction" onclick="return  validateLocationUnitForm(this.form) && validateLocationUnit()">
                                          
  								                <bean:message key="button.addLocationUnit"></bean:message>
  								            </html:submit>
          								</tr>
          							</table>
          						</td>
      							</tr>
      							</jims2:isAllowed>
									</table>
								</td>
							</tr>
  					</table>
  					
  					<!-- Begin -->
  					<br>
  					<logic:equal name="locationForm" property="action" value="add">
	  					<logic:notEmpty name="locationForm" property="newLocUnitList">
	  					<%int flag = 0;
						  String bgcolor = "";%>  
						<table align="center" width='98%' border="0" cellpadding="2" cellspacing="0" class=borderTableBlue>
							<tr id='locationUnitEdit'>
								<td>
									<table width='100%' cellpadding='2' cellspacing='1'>
										<tr class=detailHead>
											<td colspan='4'><bean:message key="prompt.new"/>&nbsp;<bean:message key="prompt.locationUnit"/></td>
										</tr>
									</table>
									
									<table width="100%" border=0 cellpadding="4" cellspacing="1">
	  									<tr class=formDeLabel height="100%">
	  										<td width='1%'> </td>
	  										<td>
	  											<bean:message key="prompt.locationUnitId"/>
                  							<jims2:sortResults beanName="locationForm" results="newLocUnitList" primaryPropSort="juvUnitCd" primarySortType="STRING" defaultSort="true" defaultSortOrder="ASC" sortId="A1" hideMe="true" />  												  											
	  										</td>
	  										<td><bean:message key="prompt.locationUnit"/></td>
	  										<td><bean:message key="prompt.phone"/></td>
	  									</tr>
								
									<logic:iterate id="locUnitIndex" name="locationForm" property="newLocUnitList">
										<tr class=<%flag++;
										bgcolor = "alternateRow";
										if (flag % 2 == 1)
										bgcolor = "normalRow";
										out.print(bgcolor);%> height="100%">
										<td width='1%' nowrap>
									    <a href="/<msp:webapp/>handleLocationUnitListUpdate.do?submitAction=Remove&locationUnitId=<bean:write name="locUnitIndex" property="juvUnitCd"/>" title="Remove <bean:write name="locUnitIndex" property="juvUnitCd"/> Details">Remove</a></td>
										<td><bean:write name="locUnitIndex" property="juvUnitCd" /></td>
										<td><bean:write name="locUnitIndex" property="locationUnitName" /></td>
										<td><bean:write name="locUnitIndex" property="phoneNumber" /></td>
										</tr>
									</logic:iterate>
										
										<!-- code remove button deleted -->
										
										
									</table>
								</td>
							</tr>
  						</table>
  						</logic:notEmpty>
  				</logic:equal>
  					<!-- End -->
  					
  					
    </html:form>
	<html:form action="/submitLocationUnitListUpdate" target="content">					
  					<br>
  					<logic:notEmpty name="locationForm" property="locationUnitList">	
						<table align="center" width='98%' border="0" cellpadding="2" cellspacing="0" class=borderTableBlue>
							<tr class="detailHead">
								<td>
									<table width='100%' cellpadding=0 cellspacing=1>
										<tr class=detailHead>
											<td><bean:message key="prompt.locationUnits"/></td>
										</tr>
	  								</table>
	  							</td>
	  						</tr>
	  						<tr>
	  							<td>
									<div class=scrollingDiv200>
  								<table width="100%" border=0 cellpadding="4" cellspacing="1">
  									<tr class=formDeLabel height="100%">
  										
  										<td>
  											<bean:message key="prompt.locationUnitId"/>
                  							<jims2:sortResults beanName="locationForm" results="locationUnitList" primaryPropSort="juvUnitCd" primarySortType="STRING" defaultSort="true" defaultSortOrder="ASC" sortId="C1" hideMe="true" />  											
  										</td>
  										<td><bean:message key="prompt.locationUnit"/></td>
  										<td><bean:message key="prompt.phone"/></td>
  										<td><bean:message key="prompt.locationUnit"/>&nbsp;<bean:message key="prompt.status"/></td>
										<td width='1%' nowrap><bean:message key="prompt.change"/>&nbsp;<bean:message key="prompt.status"/></td>
										<td width='1%'><bean:message key="prompt.edit"/></td>
  									</tr>
							<!--  <input type=hidden id="locationId" name="locationId" value="">  -->
							  
							  <%int RecordCounter = 0;
							  String bgcolor = "";%>  
							  
							 <!-- Deleted Condition --> 
							  
								 <logic:iterate id="locationUnitIndex" name="locationForm" property="locationUnitList">
								 <tr class=<%RecordCounter++;
									bgcolor = "alternateRow";
									if (RecordCounter % 2 == 1)
										bgcolor = "normalRow";
									out.print(bgcolor);%> height="100%">
										
								 		<td>								 			
								 			<a href="javascript:openWindow('/<msp:webapp/>handleLocationSelection.do?submitAction=<bean:message key="button.viewAll" />&locationId=<bean:write name="locationUnitIndex" property="locationId" />&juvLocationUnitId=<bean:write name="locationUnitIndex" property="juvLocationUnitId" />&locationUnitName=<bean:write name="locationUnitIndex" property="locationUnitName" />&locationUnitPage=true')">
								 			<bean:write name="locationUnitIndex" property="juvUnitCd" /></a>
								 		</td>
  										<td><bean:write name="locationUnitIndex" property="locationUnitName" /></td>
  										<td><bean:write name="locationUnitIndex" property="phoneNumber" /></td>
										<td><bean:write name="locationUnitIndex" property="status" /></td>
										<td>
										   <jims2:isAllowed requiredFeatures='<%=Features.CS_LOC_ACTIVATE%>'>
										      <logic:equal name="locationUnitIndex" property="status" value="ACTIVE">
										         <a href="/<msp:webapp/>handleLocationUnitListUpdate.do?submitAction=Inactivate&period=yes&locationUnitId=<bean:write name="locationUnitIndex" property="juvLocationUnitId"/>" title="Inactivate <bean:write name="locationUnitIndex" property="locationUnitName"/> Details">Inactivate</a>
										      </logic:equal>
										   </jims2:isAllowed>
										   <jims2:isAllowed requiredFeatures='<%=Features.CS_LOC_INACTIVATE%>'>
										      <logic:equal name="locationUnitIndex" property="status" value="INACTIVE">
											    <a href="/<msp:webapp/>handleLocationUnitListUpdate.do?submitAction=Activate&locationUnitId=<bean:write name="locationUnitIndex" property="juvLocationUnitId"/>" title="Activate <bean:write name="locationUnitIndex" property="locationUnitName"/> Details">Activate</a>
									          </logic:equal>
									       </jims2:isAllowed>
									    </td>
									    <td width='1%'>
									       <jims2:isAllowed requiredFeatures='<%=Features.CS_LOC_CREATE%>'>
									           <a href="/<msp:webapp/>handleLocationUnitListUpdate.do?submitAction=Update&locationUnitId=<bean:write name="locationUnitIndex" property="juvLocationUnitId"/>" title="Edit <bean:write name="locationUnitIndex" property="locationUnitName"/> Details">Edit</a>
									       </jims2:isAllowed>
									    </td>
									</tr>
  								 </logic:iterate>
                            
								
								</table>
								</div>
							</td>
						</tr>
  				 	
  					</table>
						<!-- end location unit table -->
 </logic:notEmpty>
   					<!-- begin location unit button table -->
						<table border="0" width="100%">
  						<tr>
  							<td colspan='4' align='center'>
      						<table>
      							<tr>
       								<td>
       									<html:submit property="submitAction">
											<bean:message key="button.back"></bean:message>
										</html:submit>
										<logic:notEmpty name="locationForm" property="newLocUnitList">
											<html:submit property="submitAction">
												 <bean:message key="button.finish"></bean:message>
											</html:submit>
										</logic:notEmpty>
										<html:submit property="submitAction">
											<bean:message key="button.cancel"/>
										</html:submit>
       									<!--input type="button" value="Next"  onClick="determineNextURL();">
       									<input type="button" value="Cancel"  onClick="determineCancelURL();"-->
       								</td>
      							</tr>
      						</table>
      					</td>
  					</tr>
  				</table>
 </html:form> 				
  				
  				
          </td>
        </tr>
      </table>
    </td>
  </tr>
</table>
<!-- END  TABLE -->
</div>



<br>
<div align=center><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:html>

