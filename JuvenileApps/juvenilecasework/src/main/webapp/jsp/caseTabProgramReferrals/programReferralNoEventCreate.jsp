<!DOCTYPE HTML>

<%-- ----------------MODIFICATIONS -------------------------- --%>
<%-- 03/31/2011 CShimek		Create jsp --%>
<%-- 11/30/2011 CShimek		#71976 revised Reset button to use scripting --%>
<%-- 09/10/2012	RYOUNG  	# Use standard javascript for comment validation--%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@page import="messaging.administerserviceprovider.reply.ServiceProviderResponseEvent"%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>


<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims" %>
<%@ page import="naming.UIConstants" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="naming.PDCalendarConstants" %>
<%@ page import="naming.ProgramReferralConstants" %>
<%@ page import="ui.common.UIUtil" %>
<%@ page import="naming.PDCodeTableConstants" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="naming.Features" %>
<%@ page import="messaging.administerserviceprovider.reply.ServiceProviderResponseEvent %>
<%@ page import="ui.juvenilecase.programreferral.form.ProgramReferralForm %>




<%--BEGIN HEADER TAG--%>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<meta http-equiv="X-UA-Compatible" content="IE=Edge, chrome=1" />

<msp:nocache />
<meta http-equiv="Content-Language" content="en-us">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;caseTabProgramReferrals - programReferralNoEventCreate.jsp</title>

<link href="/<msp:webapp/>css/casework.css" rel="stylesheet" type="text/css">

<html:base />
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<%-- STRUTS VALIDATION --%>
<html:javascript formName="createProgramReferral"/>

<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/programReferral.js"></script>
<script language="JavaScript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>
<script type='text/javascript'>

function subgroup(id, name)
{
	this.id = id;
	this.name = name;
	this.serviceResponseEvent = new Array();
}
function subProvType(id, inHouse)
{
	this.id = id;
	this.inHouse = inHouse;
}    

var providers = new Array();
var providersType = new Array();
var courtordered = 	"<bean:write name='programReferralForm' property='programReferral.courtOrderedVal' />";
<logic:iterate indexId="groupIterIndex" id="groupIter" name="programReferralForm" property="serviceProviderList">
	providers[<bean:write name="groupIterIndex"/>] = new subgroup("<bean:write name="groupIter" property="juvServProviderId" />", "<bean:write name="groupIter" property="juvServProviderName"/>");
	providersType[<bean:write name="groupIterIndex"/>] = new subProvType("<bean:write name="groupIter" property="juvServProviderId" />", "<bean:write name="groupIter" property="inHouse"/>");

	<logic:iterate indexId="groupIterIndex2" id="groupIter2" name="groupIter" property="serviceResponseEvents">
		var innerGroup = new subgroup("<bean:write name="groupIter2" property="programId" />", ('<bean:write name="groupIter2" property="programName"/>').replace("&#39;", "'").replace("&amp;","&"));
		providers[<bean:write name="groupIterIndex"/>].serviceResponseEvent[<bean:write name="groupIterIndex2"/>] = innerGroup;
	</logic:iterate>
</logic:iterate >
    
function pgSetup()
{
	var jsp = document.getElementsByName("programReferral.juvServiceProviderId");
	var pgms = document.getElementsByName("programReferral.providerProgramId");
	//Bug Fix 14417 starts
	<logic:equal name="programReferralForm" property="programReferral.courtOrderedVal" value="true">
		document.getElementsByName("programReferral.courtOrdered")[0].checked=true;
	</logic:equal>
	<logic:notEqual name="programReferralForm" property="programReferral.courtOrderedVal" value="true">
		document.getElementsByName("programReferral.courtOrdered")[0].checked=false;
	</logic:notEqual>
	//Bug Fix 14417 ends
	show('blk',1);
	show('acp',0);
	show('ten',0); 
	
	if (jsp[0].selectedIndex == 0)
	{
		jsp[0].focus();
		pgms[0].disabled = true;	
	} else {
		var selId = jsp[0].options[jsp[0].selectedIndex].value;
		pgms[0].options.length = 0;
		pgms[0].options[0] = new Option( "Please Select", "", false, false );
		for(i in providers)
		{
			if(providers[i].id == selId)
			{
				for(j in providers[i].serviceResponseEvent)
				{
					pgms[0].options[pgms[0].options.length] = new Option( providers[i].serviceResponseEvent[j].name, providers[i].serviceResponseEvent[j].id);
					break;	
				}
			}
		}
		var pgmLen = pgms[0].options.length;
		if(pgmLen >1)
		{
			pgms[0].disabled = false;
			var pn = document.getElementsByName("programReferral.providerProgramName");
			if (pn[0].value != null && pn[0].value > "")
			{
				for (x=0; x<pgmLen; x++)
				{
					if (pgms[0].options[x].text == pn[0].value)
					{
						pgms[0].selectedIndex = x;
						break;
					}			
				}
			}	
//			pgms[0].value="";
		} else{
			pgms[0].selectedIndex = 0; //reset choice back to default
			pgms[0].value="";
			pgms[0].disabled = true;
		}		
	}

}
function validateInputFields(theForm)
{
	var msg = "";
	var jsp = document.getElementsByName("programReferral.juvServiceProviderId");
	var pn = document.getElementsByName("programReferral.providerProgramId"); 
	var ahr = document.getElementsByName("programReferral.assignedHours");
	var co = document.getElementsByName("programReferral.courtOrdered");
	var cmts = document.getElementsByName("programReferral.comments");

	if (jsp[0].selectedIndex == 0)
	{
		msg = "Service Provider Name selection required.\n";
		jsp[0].focus();
	} else {
		jspName = document.getElementsByName("programReferral.juvServiceProviderName");
		jspName[0].value = jsp[0].options[jsp[0].selectedIndex].text;
	}	
		
	if (pn[0].selectedIndex == 0)
	{
		if (msg == "")
		{
			pn[0].focus();
		}		
		msg += "Program Name selection required.\n";
	}else {
		pnName = document.getElementsByName("programReferral.providerProgramName");
		pnName[0].value = pn[0].options[pn[0].selectedIndex].text;
//		alert(pnName[0].value + " " + pn[0].options[pn[0].selectedIndex].value);
	}	

	var assignHrs = Trim(ahr[0].value);
    var assignedHoursRegex = /^([0-9]{1,3})$/
	if (assignHrs > "")
	{
	    if(assignedHoursRegex.test(assignHrs) == false)
	    {    
			if (msg == "")
			{
				ahr[0].focus();
			}		
	    	msg += "Assigned Hours must be numeric.\n";
	    	ahr[0].focus(); 
	    }	
	} else {
		if (msg == "")
		{
			ahr[0].focus();
		}	
		msg += "Assign Hours is required.\n";
	}	

	if (co[0].checked == false && co[1].checked == false)
	{
		if (msg == "")
		{
			co[0].focus();
		}	
		msg += "Court Ordered selection is required.\n";
	}
	
	var beginDate = document.getElementById('entryDate').value ;
	if (beginDate == "") 
    {
    	msg = "End Date is required.\n";
    	document.getElementById('entryDate').focus();
    }
	if (beginDate != "") {
	    if(checkDateFormat(beginDate))
	    {         
	        msg = "End date is invalid.  The valid format is MM/DD/YYYY.\n";
	        document.getElementById('entryDate').focus();
	    } else {
      	   if (dateIsFutureDate(beginDate))
      	   {	             
          	   msg = "Begin Date cannot be a future date.\n";;
          	   document.getElementById('entryDate').focus();
       	   }
	    }
    }

	if (msg == ""){
		var theStatus = document.getElementsByName("programReferral.referralStatus");
		var fld = document.getElementById("blk").className;
		if (fld == 'visible'){
			theStatus[0].value = '';
		}
		fld = document.getElementById("acp").className;
		if (fld == 'visible'){
			theStatus[0].value = document.getElementById("acpStatusId").value;
		}
		fld = document.getElementById("ten").className;
		if (fld == 'visible'){
			theStatus[0].value = document.getElementById("tenStatusId").value;
		}


		return true;
	}
	
	alert(msg);
	return false;
	
}  

function validateComments(){
	var cmts = document.getElementsByName("programReferral.comments")[0].value;
	var commentRegExp = /^[a-zA-Z0-9][^%_]*$/;
	var msg = "";
    if (cmts.length > 0) {
		if (!commentRegExp.test(cmts)) {
			msg += "Comments must be alphanumeric with no leading spaces and no leading symbols.  All symbols and spaces are allowed after the first alphanumeric character except % and _.";
		    alert(msg);
		    return false;
		}
    }
	return true;
}


function resetInputs()
{
	fld1 = document.getElementsByName("programReferral.juvServiceProviderId");
	fld1[0].selectedIndex = 0;
	fld1[0].focus();
	fld1 = document.getElementsByName("programReferral.providerProgramId");
	fld1[0].selectedIndex = 0;
	fld1[0].disabled = true;
	document.getElementsByName("programReferral.assignedHours")[0].value = "";
	document.getElementsByName("programReferral.comments")[0].value = "";
	//Bug Fix 14417 starts
	var courtOrderedRadios = document.getElementsByName("programReferral.courtOrdered");
	courtOrderedRadios[0].checked=false;
	courtOrderedRadios[1].checked=false;
	//Bug Fix 14417 ends
	
	/* Added for bug 45875 */
	$("#blk").show();
	$("#acp").hide();
	$("#ten").hide();	
	/* Added for bug 45875 */
}

function dateIsFutureDate(dateField)
{
	var mm = dateField.substring(0,2);
	var dd = dateField.substring(3,5);
	var yyyy = dateField.substring(6,10);
	
	var curDate = new Date();
	var curYear = curDate.getFullYear();
	var curMonth = curDate.getMonth() + 1;
	var curDay = curDate.getDate();
	if (curYear < yyyy) {
		return true;
	}
	if (curYear == yyyy) {
		if (curMonth < mm ) {
			return true;
		} else if( (curMonth == mm)  &&  (curDay < dd) ) {
			return true;
		} 
	}
	return false;             
}

function checkDateFormat(dateField)
{
  if (dateField.length != 10)
	{
     return true;
  } 

  var mm = dateField.substring(0,2);
  var dd = dateField.substring(3,5);
  var yyyy = dateField.substring(6,10);
  var sl1 = dateField.substring(2,3);
  var sl2 = dateField.substring(5,6);
  
  var dateRegex =  /^\d{2}$/
  var yearRegex = /^\d{4}$/

  if (dateRegex.test(mm) == false || dateRegex.test(dd) == false || yearRegex.test(yyyy) == false)
  {
     return true;
  }
  if (sl1 != "/" || sl2 != "/") {
    return true;
  }
  if (mm == "00" || mm > 12 || dd == "00" || yyyy == "0000" ) {
    return true;
  }
  
  if( (mm == 1 || mm == 3 || mm == 5 || mm == 7 || mm == 8 || mm == 10 || mm == 12) && (dd > 31) )
  {
    return true;
  }
  
  if( (mm == 4 || mm == 6 || mm == 9 || mm == 11)  && (dd > 30)  ) {
    return true;
  }

  var leapYr = yyyy % 4;
  if( (mm == 2)  &&  ((leapYr == 0 && dd > 29) || leapYr != 0 && dd > 28)  ) {
    return true;
  }
}
</script> 
</head>
<body topmargin='0' leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true);" >
<html:form action="/displayProgramReferralNoEventSummary" target="content">

<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|346">

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
	<tr>
		<td align="center" class="header" ><bean:message key="title.juvenileCasework" />&nbsp;-&nbsp;<bean:message key="title.create" />&nbsp;<bean:message key="prompt.programReferral" /></td>
	</tr>
</table>
<%-- END HEADING TABLE --%>
<%-- BEGIN ERROR TABLE --%>
<table width="98%" border="0" align="center">
	<tr>
		<td align="center" class="errorAlert"><html:errors></html:errors></td>
	</tr>
</table>
<%-- END ERROR TABLE --%>
<div class='spacer'></div>
<%-- BEGIN INSTRUCTION TABLE --%>
<table width="98%">
	<tr>
		<td>
			<ul>
				<li>Enter information as required.</li>
				<li>Select the Spell Check icon for the Comments field to check spelling.</li>	    
				<li>Select the Next button to view the Summary screen.</li>
			</ul>
		</td>
	</tr>
	<tr>
		<td class="required"><img src='/<msp:webapp/>images/required.gif' title='required' alt="required image" hspace='0' vspace='0'>&nbsp;<bean:message key="prompt.requiredFieldsInstruction" /></td>
	</tr>
</table>
<%-- END INSTRUCTION TABLE --%>

<%-- BEGIN HEADER INFO TABLE --%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="casefileheader" />
</tiles:insert> 
<%-- END HEADER INFO TABLE --%>
<div class='spacer'></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td valign="top">
	<%-- 		<html:hidden styleId="actionName" name="programReferralForm" property="programReferral.currentAction.actionName" /> --%>
<%--tabs start--%>
      		<tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
        		<tiles:put name="tabid" value="programreferraltab" />
        		<tiles:put name="casefileid" value='<%=request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM)%>' />
      		</tiles:insert>
<%--tabs end--%>
			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td valign='top' align='center'>  
<%-- BEGIN PROGRAM REFERRAL INPUT TABLE --%>
						<table cellpadding="2" cellspacing="1" width="100%">
							<tr>
								<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.diamond" /><bean:message key="prompt.serviceProviderName" /></td>
								<td class="formDe" colspan="3">
									<html:select name="programReferralForm" property="programReferral.juvServiceProviderId" styleId="juvServiceProviderId" >
		          						<html:option value=""><bean:message key="select.generic" /></html:option>
		          						<html:optionsCollection name="programReferralForm" property="serviceProviderList" value="juvServProviderId" label="juvServProviderName" />					
		          					</html:select>
		          					<html:hidden property="programReferral.juvServiceProviderName" /> 
								</td>
							</tr>	
							<tr>
								<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.diamond" /><bean:message key="prompt.programName" /></td>
								<td class="formDe" colspan="3">
									<html:select name="programReferralForm" property="programReferral.providerProgramId" disabled="false" styleId="providerProgramId">
		          						<html:option value=""><bean:message key="select.generic" /></html:option>
		          						<html:optionsCollection name="programReferralForm" property="programNames" value="programCodeId" label="programName" />					
		          					</html:select>
		          					<html:hidden property="programReferral.providerProgramName" />
								</td>  
							</tr>	  
							<tr>
								<jims:isAllowed requiredFeatures='<%=Features.JCW_CF_PGMREF_B%>'>
									<td class="formDeLabel" width="1%"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.beginDate" /></td>
									<td class="formDe" colspan="3">
									<html:text styleId="entryDate" size="10" maxlength="10" name="programReferralForm" readonly="true" property="programReferral.beginDateStr" /> </td>
								</jims:isAllowed>
								<jims:isAllowed requiredFeatures='<%=Features.JCW_CF_PGMREF_B%>' value="false">
									<td class="formDeLabel" width="1%"><bean:message key="prompt.beginDate" /></td>
									<td class="formDe" colspan="3"><bean:write name="programReferralForm" property="programReferral.beginDateStr" />
								</jims:isAllowed>
							</tr>   
							<tr>
								<td class="formDeLabel" width='1%' nowrap='nowrap'><bean:message key="prompt.diamond" /><bean:message key="prompt.assignedHours" /></td>
								<td class="formDe"><html:text size="3" maxlength="3" name="programReferralForm" property="programReferral.assignedHours" /></td>
								<td class="formDeLabel" nowrap='nowrap' width="1%"><bean:message key="prompt.diamond" /><bean:message key="prompt.courtOrdered" /></td>
								<td class="formDe">
								<%--bug Fix 14417 starts: Changed value to true from false --%>
							 		<html:radio name="programReferralForm" property="programReferral.courtOrdered" value="true" styleId="courtOrderedId1"/><bean:message key="prompt.yes" />
									<html:radio name="programReferralForm" property="programReferral.courtOrdered" value="false" styleId="courtOrderedId2"/><bean:message key="prompt.no" /> 
								</td>
							</tr>
						  	   
							<bean:define id="theReferralStatusMap" name="programReferralForm" property="referralStatusMap" type="java.util.Map" />
							<bean:define id="theReferralStatusDescAccepted" type="java.lang.String" value="" />
							<bean:define id="theReferralStatusDescTentative" type="java.lang.String" value="" />
							<% 
								theReferralStatusDescAccepted =  (String)theReferralStatusMap.get(ProgramReferralConstants.ACCEPTED);
					 			theReferralStatusDescTentative = (String)theReferralStatusMap.get(ProgramReferralConstants.TENTATIVE);
					 			String referralDesc = "";
							%>
							<tr id="blk" class="hidden">
								<td class="formDeLabel" nowrap><bean:message key="prompt.referralStatus" /></td>
								<td class="formDe" colspan="6">&nbsp;
									<input type="hidden" name="blkStatus" id="blkStatusId" value="" />
								</td>
							</tr>	    
							<tr id="acp" class="hidden">
								<td class="formDeLabel" nowrap><bean:message key="prompt.referralStatus" /></td>
								<td class="formDe" colspan="6">
								<% 
									out.print(theReferralStatusDescAccepted);
									referralDesc = theReferralStatusDescAccepted;
								%>
								<input type="hidden" name="acpStatus" id="acpStatusId" value="<%= referralDesc %>" />
								</td>
							</tr>	
							<tr id="ten" class="hidden">
								<td class="formDeLabel" nowrap><bean:message key="prompt.referralStatus" /></td>
								<td class="formDe" colspan="6">
								<%	
									out.print(theReferralStatusDescTentative);
									referralDesc = theReferralStatusDescTentative;
								%>
								<input type="hidden" name="tenStatus" id="tenStatusId" value="<%= referralDesc %>" />
								</td>
							</tr>
							<!-- Modified for bug #45785 -->
							<html:hidden name="programReferralForm" property="programReferral.referralStatus" styleId="refStatus"/>
							
							<tr>
								<td class="formDeLabel" colspan="4" nowrap='nowrap'><bean:message key="prompt.comments" />&nbsp;
									<tiles:insert page="../caseworkCommon/spellCheckTile.jsp" flush="false">
										<tiles:put name="tTextField" value="programReferral.comments"/>
										<tiles:put name="tSpellCount" value="spellBtn1" />
									</tiles:insert> (Max. characters allowed: 1000)
								</td>
							</tr>
							<tr> 
								<td class="formDe" colspan="4">
									<html:textarea name="programReferralForm" property="programReferral.comments" style="width:100%" rows="3" 
										styleId="progRefCommentsId"/>
								</td>
							</tr>
           				</table>
<%-- END PROGRAM REFERRAL --%>
						<div class='spacer'></div>
<%-- BEGIN BUTTON TABLE --%>
						<table width="100%">  <%-- onclick="return validateInputFields(this.form);"  --%>
							<tr>
								<td align="center">
									<html:submit property="submitAction"><bean:message key="button.back"/></html:submit>
									<html:submit property="submitAction" onclick="return validateInputFields(this.form) && validateComments(this.form)"><bean:message key="button.next"/></html:submit> 
									<input type="button" name="resetButton" value="Reset" onclick="resetInputs();" >
									<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
								</td>
							</tr>
						</table>
<%-- END BUTTON TABLE --%>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<%-- END DETAIL TABLE --%>
</html:form>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>