<!DOCTYPE HTML>
<%-- 06/07/2007	 Debbie Williamson - Create JSP --%>
<%-- 10/13/2015  RYoung  - #30342 MJCW: IE11 conversion of HCJPD link on UILeftNav (UI - Search Location)--%>
<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="elogic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>

<%-- TAB LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ page import="naming.PDJuvenileCaseConstants"%>
<%@ page import="naming.Features" %>
<%@ page import="ui.common.UIUtil" %>



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
<title><bean:message key="title.heading"/> - location/locationUnitUpdate.jsp</title>
<%-- <html:javascript formName="locationUnitForm"/> --%>
<%@include file="../jQuery.fw"%>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/case_util.js"></script> 
<script type="text/javascript" src="/<msp:webapp/>js/locationUnit.js"></script>

<script type="text/javascript"> 
$(document).ready(function () {
	
    if ( sessionStorage.getItem("officerProfile") != null
    		&& sessionStorage.getItem("officerProfile").length > 0 ) {
    	$("#officerProfileFlag").val( sessionStorage.getItem("officerProfile") );
    	sessionStorage.removeItem("officerProfile");
    }
    
    if ( sessionStorage.getItem("interviewCal") != null
    		&& sessionStorage.getItem("interviewCal").length > 0 ) {
    	$("#interviewCalFlag").val( sessionStorage.getItem("interviewCal") );
    	sessionStorage.removeItem("interviewCal");
    }
    
    if ( sessionStorage.getItem("maysi") != null
    		&& sessionStorage.getItem("maysi").length > 0 ) {
    	$("#chkmaysiValue").val( sessionStorage.getItem("maysi") );
    	sessionStorage.removeItem("maysi");
    }
    
    if ( sessionStorage.getItem("drugTesting") != null
    		&& sessionStorage.getItem("drugTesting").length > 0 ) {
    	$("#chkdrugValue").val( sessionStorage.getItem("drugTesting") );
    	sessionStorage.removeItem("drugTesting");
    }
	
	if($("#chkmaysiValue").val()=='1')
		$("#chkMaysi").prop("checked",true);	
	else if($("#chkmaysiValue").val()=='0')
		$("#chkMaysi").prop("checked",false);
	
	if($("#chkdrugValue").val()=='1')
		$("#chkDrug").prop("checked",true);
	else if($("#chkdrugValue").val()=='0')
		$("#chkDrug").prop("checked",false);
	
	if ($("#officerProfileFlag").val() == "1"){
		$("#officerProfile").prop("checked", true)
	} else {
		$("#officerProfile").prop("checked", false)
	}
	
	
	if ($("#interviewCalFlag").val() == "1"){
		$("#interviewCal").prop("checked", true)
	} else {
		$("#interviewCal").prop("checked", false)
	}
	
	$("input[id^='chkMaysi']").on('change', function () {
		if(document.getElementById('chkMaysi').checked== true)
		  {
			 $("#chkmaysiValue").val('1');	
			 //$("#chkMaysi").prop("checked")=false;
		  }
		if(document.getElementById('chkMaysi').checked== false)
		  {
			 $("#chkmaysiValue").val('0');			 
		  }
	});
	$("input[id^='chkDrug']").on('change', function () {
		if(document.getElementById('chkDrug').checked== true)
		  {
			 $("#chkdrugValue").val('1');
			 //$("#chkMaysi").prop("checked")=false;
		  }
		if(document.getElementById('chkDrug').checked== false)
		  {
			 $("#chkdrugValue").val('0');			 
		  }
	});
	
	$("#officerProfile").click(function() {
	    if( $(this).is(":checked") ) {
	    	$("#officerProfileFlag").val("1");
	    } 
	    
	    if( !$(this).is(":checked") )  {
	    	$("#officerProfileFlag").val("0");
	    }
	     
	});
	
	$("#interviewCal").click(function() {
		
	    if( $(this).is(":checked") )  {
	    	$("#interviewCalFlag").val("1");
	    }
	    
	    if ( !$(this).is(":checked") )  {
	    	$("#interviewCalFlag").val("0");
	    }
	    	
	});
	
	$("#nextBtn").click(function(){
		sessionStorage.setItem("officerProfile", $("#officerProfileFlag").val() );
		sessionStorage.setItem("interviewCal", $("#interviewCalFlag").val());
		sessionStorage.setItem("maysi", $("#chkmaysiValue").val());
		sessionStorage.setItem("drugTesting", $("#chkdrugValue").val());
	})
});
</script>

<body topmargin=0 leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/displayLocationUnitUpdateSummary" target="content" focus="locUnit.locationUnitName">
<input type="hidden" name="helpFile" value="commonsupervision/Location/Administer_Location.htm#|408">

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
              <tr>
                <td align="center" class="header"><bean:message key="title.update"/>&nbsp;<bean:message key="prompt.locationUnit"/></td>
              </tr>
            </table>
            <!-- END HEADING TABLE -->

            <!-- BEGIN INSTRUCTION TABLE -->
            <table width="98%" border="0">
            	<tr>
            		<td>
  								<ul>
              			<li>Edit information as required and select Next button to view summary screen.</li>
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
  					<!-- location start -->
  					<table align="center" width='98%' border="0" cellpadding="2" cellspacing="0" class=borderTableBlue>
  						<tr class="detailHead">
  							<td>
  								<table width='100%' cellpadding=0 cellspacing=1>
  									<tr class=detailHead>
                      <td width='1%' class=detailHead> <a href="javascript:showHideMulti('location', 'pchar', 1, '/<msp:webapp/>')"><img border=0 src="/<msp:webapp/>/images/expand.gif" name="location"></a></td>
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
  										<td class='formDeLabel' width='1%' nowrap>MAYSI</td><%-- <bean:message key="prompt.maysi"/> --%>
  										<td class="formDe">
  										<input type="checkbox" id='chkMaysi'>		 											
			 							<html:hidden styleId='chkmaysiValue' name="locationForm" property="locUnit.maysiFlag"/>
  										</td>
  										<td class='formDeLabel' width='1%' nowrap>
  											Officer Profile
  										</td>
  										<td class="formDe">
  											<input type="checkbox" name="officerProfile" id="officerProfile"/>
  											<html:hidden styleId="officerProfileFlag" name="locationForm"	property="locUnit.officerProfileFlag"  />
  										</td>
    								</tr>
  									<tr>
    									<td class='formDeLabel' width='1%' nowrap><bean:message key="prompt.2.diamond"/><bean:message key="prompt.locationUnit"/></td>
  										<td class="formDe width='50%'"><html:text name="locationForm" property="locUnit.locationUnitName" size="50" maxlength="50"/></td>
  										<td class='formDeLabel' width='1%' nowrap>Drug Testing</td><%-- <bean:message key="prompt.drugTesting"/> --%>
  										<td class="formDe">
  										<input type="checkbox" id='chkDrug'>		 											
			 							<html:hidden styleId='chkdrugValue' name="locationForm" property="locUnit.drugFlag"/>
  										</td>
  										<td class="formDe"></td>
										<td class="formDe"></td>
    								</tr>
  										
  									<tr>
  										<td class="formDeLabel" nowrap><bean:message key="prompt.phone"/></td>
  										<td class="formDe">
												<html:text name="locationForm" property="locUnit.phoneNumber.areaCode" size="3" maxlength="3" onkeyup="return autoTab(this, 3);" />-
  										        <html:text name="locationForm" property="locUnit.phoneNumber.prefix" size="3" maxlength="3" onkeyup="return autoTab(this, 3);" />-
  										        <html:text name="locationForm" property="locUnit.phoneNumber.last4Digit" size="4" maxlength="4" onkeyup="return autoTab(this, 4);" />
											
  										</td>
  										<td class="formDeLabel" width='1%' nowrap >
  											Interview/Calendar
  										</td>
  										<td class="formDe">
  											<input type="checkbox" name="interviewCal" id="interviewCal"/>
  											<html:hidden styleId="interviewCalFlag"  name="locationForm" property="locUnit.interviewCalFlag"   />
  										</td>
  										<td class="formDe"></td>
										<td class="formDe"></td>
  									</tr>

          					<!-- begin location unit button table -->
        						<tr>
        							<td colspan='6' align='center'>
            						<table>
            							<tr>
             								<td>
												<html:submit styleId="backBtn" property="submitAction">
													<bean:message key="button.back"></bean:message>
												</html:submit>
												<html:submit styleId="nextBtn" property="submitAction" onclick="return  validateEditLocationUnit();">												
													 <bean:message key="button.next"></bean:message>
												</html:submit>
												<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
             									<!-- input type="button" value="Next" onClick="goNav('locationUnitDetail.htm?sum&action=update&misc=sum');"-->
             									<!-- input type="button" value="Cancel"  onClick="goNav('locationCreateUpdate.htm?mod');"-->
             								</td>
            							</tr>
            						</table>
            					</td>
        						</tr>
  								</table>
  							</td>
  						</tr>
						</table><br>
   					<!-- end location unit table -->
          </td>
        </tr>
      </table><br>
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

