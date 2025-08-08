<!DOCTYPE HTML>
<!-- This is the JIMS2 welcome page -->
<!-- Modifications -->
<!-- 03/16/2006	CShimek  #29680 Revised content in Helpful Information -->
<!-- 04/03/2006	CShimek  #30242 Revised Helpful Information content to point to pdf document -->
<!-- 10/22/2007	CShimek  #46317 added Search Task and Notification hrefs -->
<!-- 10/25/2007	Jjose  JIMS200046448 Fixing my notices off the main page -->
<!-- 09/18/2009	L Deen #62066 Add PASO Instructions for CLOs link page -->
<!-- 04/12/2010	L Deen #64153 remove manuals & demos link from this screen -->
<!-- 06/10/2010 L Deen #65923 Revise news & notes section to add CSCD Faqs-->
<!-- 06/25/2010 L Deen Revise news & notes section to add IE settings & System Downtimes-->
<%-- 06/15/2012 D James #73337 Revise news & notes section to add link for MJCW FAQs--%>


<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<!-- Jquery Framework Local Reference -->

<!-- UI smoothness Theme -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/jquery-ui.min.css" />


<script type="text/javascript" src="/<msp:webapp/>js/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/jquery-ui.min.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/jquery-ui.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/jquery.validate.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/jquery.validate.min.js"></script> 
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/JuvenileCasework/js/casework.js"></script>
<%@ page import="naming.Features"%>

<html>

<head>
<base href="${pageContext.request.contextPath}/"/> <%--To load the (up and down sort)arrow images on the page load//User Story 36464//Task 41689  --%>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title>loginLogout/mainMenu.jsp</title>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/appshell.css" />
<link rel="stylesheet" type="text/css" href="/JuvenileCasework/css/casework.css" />
<msp:nocache />

<%-- script language="JavaScript" src="/<msp:webapp/>js/login.js"></script --%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<%-- Do not move the script below it is need for the dhtmlwiindow.js to function properly --%>
<script type="text/javascript">
var correctedPath='/<msp:webapp/>';
</script>

<script type="text/javascript" src="/<msp:webapp/>js/dhtmlwindow.js"></script>
<script type="text/javascript">
function getParameterByName(name, url) {
    if (!url) {
      url = document.location.href;
    }
    name = name.replace(/[\[\]]/g, "\\$&");
    var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
        results = regex.exec(url);
    if (!results) return null;
    if (!results[2]) return '';
    return decodeURIComponent(results[2].replace(/\+/g, " "));
}

$(document).ready(function () {
	var sortId = getParameterByName("sortId");
	if(sortId!=null)
	{
		if(sortId==6||sortId==7||sortId==8||sortId==9||sortId==10)
		{
			window.sessionStorage.setItem("isCaseFilesActive", "true");
		}
			
		var isActive = window.sessionStorage.getItem("isCaseFilesActive");
		if(isActive!=null && isActive=="true")
		{
			var spanName = "tasksExpandActive";
			var appendName = spanName + "Span";
			window.document.images[spanName].src = correctedPath+"images/contract.gif";
			document.getElementById(appendName).className = 'visibleTR';
		}
	}
	else
	{
		window.sessionStorage.removeItem("isCaseFilesActive");
		
	}
	//code below added for image issue on load for casefile information. U.S 36464; Task 41689
	 $("img").each( function(){
			if($(this).attr('src')=='../images/arrow_up.gif'){
				  $(this).attr({src: '../appshell/images/arrow_up.gif'});
			}
			if($(this).attr('src')=='../images/arrow_down.gif'){
				  $(this).attr({src: '../appshell/images/arrow_down.gif'});
			}
		}); 
});
//show hide where there is an expand (plus sign)
/*params: spanName - name of the span to hide or show - image has same id as span -"Span"
*/
//This function will toggle all of the named checkboxes the same value as the parent checkbox (theElement)
function toggleCheckAll(theElement, name) 
{ 
	theForm = theElement.form;
	var list = document.getElementsByName(name);
 
	for(i=0; i<list.length; i++)
	{
		if(theElement.checked == true)
			list[i].checked="true";
		else
			list[i].checked=null;
	}     
}
	
function reverseToggleCheckAll(theElementName, name) 
{ 
	var theElement=document.getElementById(theElementName);
	theForm = theElement.form;
	var list = document.getElementsByName(name);
 	var totalChecked=0;
 	var totalToCheck=0;
	for(i=0; i<list.length; i++)
	{
		
		if(list[i].type=="checkbox"){
			if(list[i].name==theElementName){
			}
			else{
				
				if(list[i].checked){
					totalChecked++;
				}
				else{
					theElement.checked=null;
					return;
				}
			}
		}
	}    
	theElement.checked=true;
}

function showHide(spanName, format, path)
{
	var appendName = spanName + "Span";
	var theClassName = document.getElementById(appendName).className;
	if( theClassName == 'visible' || 
	    theClassName == 'visibleTR' || 
			theClassName == 'visibleTable')
	{
		window.document.images[spanName].src = path+"images/expand.gif";
		document.getElementById(appendName).className = 'hidden';
	}
	else 
	{
	  window.document.images[spanName].src = path+"images/contract.gif";
		if( format == "row")
		{
			document.getElementById(appendName).className = 'visibleTR';
		}
		else if( format == "table")
		{
			document.getElementById(appendName).className = 'visibleTable';
		}
		else 
		{
			document.getElementById(appendName).className = 'visible';
		}
	}
}

//this function opens a new window
//param: url - location address of new window
function openWindow(url)
{
	var newWin = window.open(url, "noticeWindow", "height=300,width=500,toolbar=no,scrollbars=yes,resizable=yes,status=yes,dependent=no");
	newWin.focus();
}

function openCustomRestrictiveWindow(url,heightSize, widthSize)
{
	var newWin = window.open(url, "pictureWindow", "height="+heightSize+",width="+widthSize+",toolbar=no,scrollbars=yes,resizable=no,status=no,location=no,menubar=no,dependent=no");
	newWin.moveTo(200,200);
	newWin.focus();
}

function checkSelected(fldName)
{
	var selValue = 'notices';
	if( fldName == 'selectedTasks')
	{	
		selValue = 'tasks';
	}	

	var sels = document.getElementsByName(fldName);
	if(sels.length == 0)
	{
		alert("No " + selValue + " available to Remove.");
		return false;
	}

	for(i = 0; i < sels.length; i++)
	{
		if (sels[i].checked == true)
		{
			return true;
		}
	}
	alert("No " + selValue + " selected to Remove.");	
	return false;
}
</script>

</head>

<body topmargin="0" leftmargin="0">

<!-- HELP FILE FIELD -->
<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|169">

<!-- BEGIN HEADING TABLE -->
<table width="100%">
	<tr>
		<td align="center" class="header">WELCOME to JIMS2</td>
	</tr>
</table>

<table width='100%'>
	<tr>
		<td colspan='2' align="center" class="confirm"><bean:write name="loginForm" property="confirmMessage" /></td>
	</tr>
	<tr> 
		<td colspan='2' align="center" class="errorAlert"><bean:write name="loginForm" property="rolesMessage" /></td>
	</tr>
</table>

<div class="spacer"></div>
<table width="100%" align="center">
	<tr>
		<td>
  		<ul>
  			<li>To access an application, use the Navigation Tree on the left.</li>
  			<li>Click on + to expand a list.</li>
  			<li>Click on an underlined link to access a page.</li>
  			<li>Help is available in the top right corner after you are logged in.</li>
  		</ul>
		</td>
	</tr>
</table>
<!-- END HEADING TABLE -->

<!-- BEGIN NEWS TABLE -->
<table align="center" width="70%" cellspacing="0" cellpadding="2" class="borderTableBlue" align="center">
	<tr>
		<td colspan="2" class="detailHead" align="center">NEWS &amp; NOTICES</td>
	</tr>
	<tr>
		<td align="left">
			<table width="100%" cellspacing="1" cellpadding="4">
	  		<tr class="formDeLabel"></tr>
	  		<tr>
	  			<td>
	  				<p>Questions:  <a href="mailto:Data.Corrections@hcjpd.hctx.net">Data.Corrections@hcjpd.hctx.net</a></p>
	  			</td>
	  		</tr>
			<tr>
				<td>
					<p>Production Releases: Click here to view <a href="https://webapps.harriscountytx.gov/ReleaseNotes/applications" target="_blank">Release Notes</a></p>
				</td>
			</tr>
			<tr class="formDeLabel">
					<td>Scheduled System Downtimes</td>
		  	</tr>
				<tr>
					<td>Monday - Friday  	5:00 am - 6:00 am</td>
				</tr>
				<tr>
					<td>Saturday - Sunday  	7:00 am - 8:00 am</td>
				</tr>

			</table>
		</td>
	</tr>
</table>
<!-- END NEWS TABLE -->

<jims2:isAllowed requiredFeatures='<%=Features.JCW_PROCESS_TASKS%>'>

<!-- BEGIN TASK TABLE -->
<html:form action="/closeTaskAction" target="content">

	<%-- only 1 Search for Tasks and Notices should display for any user --%>
	<div class='spacer'></div>
	<table cellpadding='2' cellspacing='0' width='98%' align="center">
		<tr>
			<td align="left">
				<jims2:isAllowed requiredFeatures='<%=Features.JCW_NS_SEARCHALL%>'>
  				<bean:define id="searchAll" value="Y" />
  				<a href="/JuvenileCasework/displayNotificationSearch.do?submitAction=Link&amp;allowOnlyYours=false">
  					 Search for Tasks and Notices</a>
  				</jims2:isAllowed> 
				
			<logic:notPresent name="searchAll">
  				<jims2:isAllowed requiredFeatures='<%=Features.JCW_NS_SEARCHIND%>'>
  					<a href="/JuvenileCasework/displayNotificationSearch.do?submitAction=Link&amp;allowOnlyYours=true">
  					  Search for Tasks and Notices</a>
  				</jims2:isAllowed>
  			</logic:notPresent>
			</td>
		</tr>
	</table>

	<br>
<!-- Casefiles TABLE -->
	<logic:notEqual name="loginForm" property="userType" value="genericSP">
		<table cellpadding='2' cellspacing='0' width='98%' class="borderTableBlue" align="center" border='0'>
				<tr class="detailHead" border='0'>
				<td width='1%'>					
					<a href="javascript:showHide('pendingCasefile', 'row', '/<msp:webapp/>')" border = '0'><img border='0' src="/<msp:webapp/>images/contract.gif" name="pendingCasefile" alt="contract"/></a>				
				</td> 
				<td width="99%" border = '0'>Pending Casefiles( <bean:write name="loginForm" property="pendingFileCount" />  )</td>
				</tr>
		</table>
		<logic:notEmpty name="loginForm" property="pendingJuveniles">
			<table cellpadding='2' cellspacing='0' width='98%' class="borderTableBlue" align="center" border='0'>
			 <tr id="pendingCasefileSpan" class="visibleTR" width='100%' border='0'>
			 	<td colspan='2' width='100%'>
					<table cellpadding='2' cellspacing='1' width='100%' border='0'>
					<tr class="formDeLabel" border='0'>
						<td>   
							<bean:message key="prompt.supervision" />&nbsp;#
							<jims2:sortResults beanName="loginForm" results="pendingJuveniles" primaryPropSort="supervisionNum" primarySortType="INTEGER" defaultSortOrder="ASC" sortId="4" levelDeep="1" />				
	          			</td>
	          			<td>   
	  						<bean:message key="prompt.juvenileNumber" />
	  						<jims2:sortResults beanName="loginForm" results="pendingJuveniles" primaryPropSort="juvenileNum" primarySortType="INTEGER" defaultSortOrder="ASC" sortId="2" levelDeep="1" />				
	          			</td>
	          			<td> 
							<bean:message key="prompt.expected" />&nbsp;<bean:message key="prompt.endDate" />  
	            			<jims2:sortResults beanName="loginForm" results="pendingJuveniles" primaryPropSort="supervisionEndDate" primarySortType="DATE" defaultSortOrder="ASC" sortId="3" levelDeep="1" />
	          			</td>     
						<td> 
							<bean:message key="prompt.juvenileName" />  
	            			<jims2:sortResults beanName="loginForm" results="pendingJuveniles" primaryPropSort="juvenileLastName" primarySortType="STRING" defaultSortOrder="ASC" sortId="1" levelDeep="1" defaultSort="true"/>
	          			</td>
	          			<td>   
	  						<bean:message key="prompt.supervisionType" />
	            			<jims2:sortResults beanName="loginForm" results="pendingJuveniles" primaryPropSort="supervisionType" primarySortType="STRING" defaultSortOrder="ASC" sortId="5" levelDeep="1" />
	          			</td>          			
					</tr>
					<logic:iterate id="pending" name="loginForm" property="pendingJuveniles" indexId='indexer'> 
	        				<tr class="<%out.print( (indexer.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">
		    					<td><a class="supervisionNumId" href="/JuvenileCasework/handleCasefileActivation.do?submitAction=Link&juvenileNum=<bean:write name='pending' property='juvenileNum'/>&casefileID=<bean:write name='pending' property='supervisionNum'/>" data-href=""><bean:write name='pending' property='supervisionNum'/></a></td>
								<td><bean:write name="pending" property="juvenileNum"/></td>
		    					<td><bean:write name="pending" property="supervisionEndDate" formatKey="date.format.mmddyyyy"/></td>
		    					<td><bean:write name="pending" property="juvenileLastName"/>,<bean:write name="pending" property="juvenileFirstName"/></td>
		    					<td><bean:write name="pending" property="supervisionType"/></td>
		    				</tr>
	  		  		</logic:iterate>
	  		  	</table>
	  			</td>
			 </tr>
	   		</table>
	   	</logic:notEmpty>
  <div class='spacer'></div>
 	<!-- Active Casefiles -->
  	<table cellpadding='2' cellspacing='0' width='98%' class="borderTableBlue" align="center" border='0'>
			<tr class="detailHead">
			<td width='1%'><a href="javascript:showHide('tasksExpandActive', 'row','/<msp:webapp/>')" border='0'><img border='0' src="/<msp:webapp/>images/expand.gif" 	name="tasksExpandActive" alt="expand"></a></td>
			<td class="expand">Active Casefiles ( <bean:write name='loginForm' property='activeFileCount' /> )</td>
			</tr>
	</table>
	<table cellpadding='2' cellspacing='0' width='98%' class="borderTableBlue" align="center" border='0'>
		<tr id="tasksExpandActiveSpan" class="hidden">
			<td colspan='2'>
			<table cellpadding='2' cellspacing='1' width='100%'>
			
			<tr class="formDeLabel">
					<td>   
						<bean:message key="prompt.supervision" />&nbsp;#
							<jims2:sortResults beanName="loginForm" results="activeJuveniles" primaryPropSort="supervisionNum" primarySortType="INTEGER" defaultSortOrder="ASC" sortId="7" levelDeep="1"/>				
          			<td>   
  						<bean:message key="prompt.juvenileNumber" />
	  						<jims2:sortResults beanName="loginForm" results="activeJuveniles" primaryPropSort="juvenileNum" primarySortType="INTEGER" defaultSortOrder="ASC" sortId="8" levelDeep="1"/>				
          			</td>
          			<td> 
						<bean:message key="prompt.expected" />&nbsp;<bean:message key="prompt.endDate" />  
	            			<jims2:sortResults beanName="loginForm" results="activeJuveniles" primaryPropSort="supervisionEndDate" primarySortType="DATE" defaultSortOrder="DESC" sortId="6" levelDeep="1"  defaultSort="true"/>
          			</td>     
					<td> 
						<bean:message key="prompt.juvenileName" />  
	            			<jims2:sortResults beanName="loginForm" results="activeJuveniles" primaryPropSort="juvenileLastName" primarySortType="STRING" defaultSortOrder="ASC" sortId="9" levelDeep="1"/>
          			</td>
          			<td>   
  						<bean:message key="prompt.supervisionType" />
	            			<jims2:sortResults beanName="loginForm" results="activeJuveniles" primaryPropSort="supervisionType" primarySortType="STRING" defaultSortOrder="ASC" sortId="10" levelDeep="1"/>
          			</td>          			
			</tr>
			<logic:iterate id="active" name="loginForm" property="activeJuveniles" indexId='indexer'> 
         	<tr class="<%out.print( (indexer.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">
	    					<td><a class="supervisionNumId" onclick="spinner()" href=" /JuvenileCasework/displayJuvenileCasefileDetails.do?submitAction=Link&juvenileNum=<bean:write name='active' property='juvenileNum'/>&supervisionNum=<bean:write name='active' property='supervisionNum'/>" data-href=""><bean:write name='active' property='supervisionNum'/></a></td>
							<td><bean:write name="active" property="juvenileNum"/></td>
	    					<td><bean:write name="active" property="supervisionEndDate" formatKey="date.format.mmddyyyy"/></td>
	    					<td><bean:write name="active" property="juvenileLastName"/>,<bean:write name="active" property="juvenileFirstName"/></td>
	    					<td><bean:write name="active" property="supervisionType"/></td>
	    	</tr>
  		  	</logic:iterate>
		</table>
		</td>
	 </tr>
	</table>
	
</logic:notEqual>
	<div class='spacer'></div>
	<table cellpadding='2' cellspacing='0' width='98%' class="borderTableBlue" align="center">
		<tr class="detailHead">
			<td width='1%'><a	href="javascript:showHide('tasksExpand', 'row','/<msp:webapp/>')" border='0'><img border='0' src="/<msp:webapp/>images/expand.gif" 	name="tasksExpand" alt="expand"></a></td>
			<td>Juvenile Probation - My Tasks ( <bean:write name='loginForm' property='taskCount' /> )</td>
		</tr>
	</table>
		<table cellpadding='2' cellspacing='0' width='98%' class="borderTableBlue" align="center">		
		<tr id="tasksExpandSpan" class="hidden">
			<td colspan='2'>
			<table cellpadding='2' cellspacing='1' width='100%'>

				<logic:empty name="loginForm" property="taskList">
					<tr class="formDeLabel">
						<td colspan='4'>No Tasks.</td>
					</tr>
				</logic:empty>

				<logic:notEmpty name="loginForm" property="taskList">
					<tr class="formDeLabel">
						<td width='1%'><input type="checkbox" onclick='toggleCheckAll(this, "selectedTasks");' name="checkAllTasks" value=""></td>
						<td width='10%'>Date/Time</td>
						<td>Title</td>
						<td width='10%'>Status</td>
					</tr>

					<logic:iterate indexId="index" id="taskIndex" name="loginForm"
						property="taskList">
						<tr
							class='<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>'>
							<td valign="top"><html:multibox name="loginForm" property="selectedTasks" onclick="document.getElementById('checkAllTasks').checked=false;">
								<bean:write name="taskIndex" property="task.taskId" />
							</html:multibox></td>
							<td valign="top" nowrap="nowrap"><bean:write name="taskIndex" property="task.submittedDate" formatKey="datetime.format.mmddyyyyHHmm" /></td>
							<td valign="top">
								<a href="/appshell/acceptTask.do?taskId=<bean:write name='taskIndex' property='task.taskId'/>">
 								<bean:write name="taskIndex" property="task.taskSubject" /></a></td>
 							<logic:equal name="taskIndex" property="task.status" value="SUBMITTED">	
							    <td valign="top">New</td>
							</logic:equal>
							<logic:equal name="taskIndex" property="task.status" value="ACCEPTED">	
							    <td valign="top">Viewed</td>
							</logic:equal>
						</tr>
					</logic:iterate>

					<tr>
						<td colspan='4' align='center'>
							<html:submit property="submitAction" onclick="return checkSelected('selectedTasks')">
							<bean:message key="button.removeSelected" />
  						</html:submit>
						</td>
					</tr>
				</logic:notEmpty>
			</table>
			</td>
		</tr>
	  </table>
</html:form>
<!-- END TASK TABLE -->

</jims2:isAllowed>

<jims2:isAllowed requiredFeatures='<%=Features.JCW_PROCESS_NOTICES%>'>

<!-- BEGIN NOTICES TABLE -->
<html:form action="/closeNotificationAction" target="content">
	<div class='spacer'></div>
	<table cellpadding='2' cellspacing='0' width='98%' class="borderTableBlue" align="center">
		<tr class="detailHead">
			<td width='1%'>
			  <a href="javascript:showHide('noticesExpand', 'row','/<msp:webapp/>')" border='0'><img border='0' src="/<msp:webapp/>images/expand.gif" name="noticesExpand" alt=""></a></td>
			<td>Juvenile Probation - My Notices ( <bean:write name="loginForm" property="noticeListSize" /> )</td>
		</tr>
	</table>
		<table cellpadding='2' cellspacing='0' width='98%' class="borderTableBlue" align="center">	
		<tr id="noticesExpandSpan" class="hidden">
			<td colspan='2'>
			<table cellpadding='2' cellspacing='1' width='100%'>

				<logic:empty name="loginForm" property="noticeList">
					<tr class="formDeLabel">
						<td colspan='3'>No Notices.</td>
					</tr>
				</logic:empty>

				<logic:notEmpty name="loginForm" property="noticeList">
					<tr class="formDeLabel">
						<td width='1%'><input type="checkbox" onclick='toggleCheckAll(this, "selectedNotices");' name="checkAllNotices" value=""></td>
						<td width='10%'>Date/Time</td>
						<td>Title</td>
					</tr>

					<logic:iterate indexId="index" id="noticeIndex" name="loginForm" property="noticeList">
						<tr class='<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>'>
							<td><html:multibox name="loginForm" property="selectedNotices" onclick="document.getElementById('checkAllNotices').checked=false;">
								<bean:write name="noticeIndex" property="notification.notificationId" />
							</html:multibox></td>
							<td nowrap="nowrap"><bean:write name="noticeIndex" property="notification.sentDate" formatKey="datetime.format.mmddyyyyHHmm" /></td>
							<td>
							<div id='not<bean:write name="noticeIndex" property="notification.notificationId"/>' class="hidden">
							<div class="dhtmlWindowContent"><bean:write name="noticeIndex" property="notification.message" /></div>
							</div>
							<a href="javascript:openCustomRestrictiveWindow('/<msp:webapp/>displayNoticeMessage.do?submitAction=Link&amp;notificationId=<bean:write name='noticeIndex' property='notification.notificationId'/>', 250, 400);">
							<bean:write name="noticeIndex" property="notification.subject" /></a>
							</td>
						</tr>
					</logic:iterate>

					<tr>
						<td colspan='3' align='center'>
							<html:submit property="submitAction" onclick="return checkSelected('selectedNotices')">
  							<bean:message key="button.removeCustomRules" />
  						</html:submit>
						</td>
					</tr>

				</logic:notEmpty>
			</table>
			</td>
		</tr>
	</table>
</html:form>
<!-- BEGIN NOTICES TABLE -->

</jims2:isAllowed>

</body>
</html>

