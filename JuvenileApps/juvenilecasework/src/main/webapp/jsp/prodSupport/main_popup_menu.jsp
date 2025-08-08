<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%-- Used to display main menu for production support --%>
<%--MODIFICATIONS --%>
<%-- mlawles	Create JSP --%>
<%-- 08/29/2013 ugopinath	modify JSP --%>
<%-- 04/24/2014 rcarter	remove new window code and change look and feel. Added changes to support Bootstrap --%>
<%-- 01/04/2016	RCarter	   #32190 html 5 compliance effort and jquery 5 (when required) --%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="elogic" %>
<%@ page import="naming.Features" %>


<head>
    <!--PAGE META DECLARATION-->
    <meta http-equiv="content-type" content="text/html;charset=utf-8" />
    <meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
    <meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="pragma" content="no-cache" />
	<meta name="ROBOTS" content="NOINDEX">
	<title>Juvenile Casework -/prodSupport/main_popu_menu.jsp</title>
	<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
	<!--JQUERY FRAMEWORK-->
	<%@include file="../jQuery.fw"%>
	<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
	<!--JQUERY FRAMEWORK-->
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.10.4/themes/smoothness/jquery-ui.css" />
 	<STYLE type="text/css">    
  	

	 .prodSupportTbl tr th {
	 	padding-top: 30px;
	 	padding-bottom: 5px;
	 	padding-left: 10px;
	 }
	 
	 .prodSupportTbl tr td {
	 	padding-right: 80px;
	 	padding-left: 10px;
	 }
	 
	  .prodSupportTbl tr td div {
	  	margin-top: 3px;
	  }
	 
	  
	
	 
	 
	
 	</STYLE>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.10.4/jquery-ui.min.js"></script>
	<!--BOOTSTRAP CSS FRAMEWORK-->
    <link rel="stylesheet" href="https://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap-theme.min.css">
    <script src="https://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
    <!--FONT AWESOME FRAMEWORK-->
    <link href="https://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet">
    <!--GOOGLE FONT FRAMEWORK-->
    <script src="https://ajax.googleapis.com/ajax/libs/webfont/1.5.2/webfont.js"></script>
    <script>
              WebFont.load({
                     google: {
                           families: ['Roboto', 'Open Sans']
                     }
              });
     </script>
     <!--GLOBAL APPLICATION JAVASCRIPT INJECTION-->
	<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
	<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
	<script type="text/javascript">
	function checkSelection(Item) {
		if (document.getElementById(Item).selectedIndex==0)
		{
			alert("You must select a valid operation.");
			return false;	
		}
		if(true)
		{
			spinner();
		}
			var val = document.getElementById(Item).options[document.getElementById(Item).selectedIndex].value;
			document.forms[0].selectedMenuItem.value =val;
			 var webApp = "/<msp:webapp/>";
			 var url = webApp + "MainMenu.do?selectedMenuItem="+val ;
			 var newWin = window.open(url, "pictureWindow", "height=1300,width=1070,toolbar=no,scrollbars=yes,resizable=yes,status=yes,dependent=no");
				newWin.focus();
				$(".overlay").remove();
			return false;
			
	}
	
	function closePopupMenu(){	
		 window.close();
		return false;
	}
	
	function setToZero(){

	document.getElementById("menuOptionsBox").selectedIndex=0;
	document.getElementById("menuOptionsBox2").selectedIndex=0;
	document.getElementById("menuOptionsBox3").selectedIndex=0;
	document.getElementById("menuOptionsBox4").selectedIndex=0;
	document.getElementById("menuOptionsBox5").selectedIndex=0;
	document.getElementById("menuOptionsBox6").selectedIndex=0;
	document.getElementById("menuOptionsBox7").selectedIndex=0;
	document.getElementById("menuOptionsBox8").selectedIndex=0;	
	document.getElementById("menuOptionsBox9").selectedIndex=0;
	document.getElementById("menuOptionsBox10").selectedIndex=0;
	document.getElementById("menuOptionsBox11").selectedIndex=0;
	document.getElementById("menuOptionsBox12").selectedIndex=0;
	document.getElementById("menuOptionsBox13").selectedIndex=0;
	document.getElementById("menuOptionsBox14").selectedIndex=0;
	document.getElementById("menuOptionsBox15").selectedIndex=0;
	document.getElementById("menuOptionsBox16").selectedIndex=0;
	document.getElementById("menuOptionsBox17").selectedIndex=0;
	document.getElementById("menuOptionsBox18").selectedIndex=0;
	
	}
	</script>

<html:base />
</head>

<body class="ContentFrameInjection" onload="setToZero();">

<html:form styleId="ProdSupportForm" action="/MainMenu" >
<input type="hidden" name="selectedMenuItem" value="" />

<h2 align="center">
PRODUCTION SUPPORT MAIN MENU
</h2>

<hr>

<div style="text-align: center;">  
		<h1 class="panel-title"><b>Choose From Menu Below:</b></h1>
</div>
<table align="center" class="prodSupportTbl">
	<tr class="title">
		<th>
			Juvenile and Referral Processing
		</th>
		<th>
			Casefile Operations
		</th>
	</tr>
	<tr>
		<td>
			<div style=" width: 500px;  overflow: auto;">
				<div style="display: inline-block;"><b>Update:</b> </div>
				<div style="display: inline-block;">
					<html:select name="ProdSupportForm" property="menuOptionsBox" styleId="menuOptionsBox" style="width:300px">
						<html:option value="-1">Select an Action...</html:option>
						<jims:isAllowed requiredFeatures="<%=Features.JCW_PS_UPDATEJUVENILEMASTER%>"> 	
						<html:option value="updateJuvenileMasterRecord">Update Juvenile Master Record</html:option>
						</jims:isAllowed>
						<jims:isAllowed requiredFeatures="<%=Features.JCW_PS_REFERRALOFFENSEPROCESSING%>">
						<html:option value="updateReferralOffenseRecord">Update Referral Offense </html:option>
						</jims:isAllowed>
						<jims:isAllowed requiredFeatures="<%=Features.JCW_PS_UPDATEREFERRAL%>"> 
						<html:option value="updateMsReferral">Update Referral Master</html:option>
						</jims:isAllowed>
						<jims:isAllowed requiredFeatures="<%=Features.JCW_PS_INTAKEHISTORY%>">
						<html:option value="viewIntakeHistoryRecords">Update Intake History Record</html:option>
						</jims:isAllowed>
						<html:option value="viewTransferredOffenseRecords">Update Transferred Offenses</html:option>
						<html:option value="viewVOPRecords">Update VOP Details </html:option>
						<html:option value="unpurgeJuvenileMasterRecord">Un-Purge Juvenile Master Record</html:option>
						<html:option value="unsealJuvenileMasterRecord">Un-Seal Juvenile or Referral Record</html:option>
					</html:select>
				</div>
				<div style="display: inline-block;">
					<html:submit styleId="menuOptionsBoxSubmit" style="width: 6em" value="Go" onclick="return checkSelection('menuOptionsBox')"/>
				</div>
			</div>
			<div>
				<div style="display: inline-block;"><b>Delete:</b> </div>
				<div style="display: inline-block;  margin-left:5px;">
					<html:select name="ProdSupportForm" property="menuOptionsBox16" styleId="menuOptionsBox16" style="width:300px">
						<html:option value="-1">Select an Action...</html:option>
						<jims:isAllowed requiredFeatures="<%=Features.JCW_PS_REFERRALDELETION%>">
							<html:option value="deleteMsReferral">Delete Referral</html:option>
						</jims:isAllowed>
						<jims:isAllowed requiredFeatures="<%=Features.JCW_PS_REFERRALDELETION%>">
							<html:option value="deleteReferralOffense">Delete Referral Offense</html:option>
						</jims:isAllowed>
						<html:option value="deleteIntakeHistoryRecords">Delete Intake History Record</html:option>
					</html:select>
				</div>
				<div style="display: inline-block;">
					<html:submit styleId="menuOptionsBox16Submit" style="width: 6em" value="Go" onclick="return checkSelection('menuOptionsBox16')"/>
				</div>
			</div>		
		</td>
		<td>
			<div style=" width: 500px; overflow: auto;">
				<div style="display: inline-block;"><b>Update:</b></div>
				<div style="display: inline-block;">
					<html:select name="ProdSupportForm" property="menuOptionsBox2" styleId="menuOptionsBox2" style="width:300px">
						<html:option value="-1">Select an Action...</html:option>
						<html:option value="caseupdate">Update Casefile</html:option>
						<html:option value="closingupdate">Update Casefile Closing</html:option>
						<html:option value="modifyjpo">Update Juvenile Probation Officer</html:option>
						<html:option value="moveCommonAppReport">Move Common App Report</html:option>
					</html:select>
				</div>
				<div style="display: inline-block;">
					<html:submit styleId="menuOptionsBox2Submit" style="width: 6em" value="Go" onclick="return checkSelection('menuOptionsBox2')"/>
				</div>
			</div>
			<div>
				<div style="display: inline-block;"><b>Delete:</b></div>
				<div style="display: inline-block; margin-left:5px;">
					<html:select name="ProdSupportForm" property="menuOptionsBox3" styleId="menuOptionsBox3" style="width:300px">
						<html:option value="-1">Select an Action...</html:option>
						<html:option value="casemerge">Merge Two Casefiles</html:option>
						<html:option value="casefiledelete">Delete Casefiles</html:option>
						<html:option value="casedelclosing">Delete Casefile Closing</html:option>
						<html:option value="casedeldoc">Delete Casefile Documents</html:option>		
					</html:select>
				</div>
				<div style="display: inline-block;">
					<html:submit styleId="menuOptionsBox3Submit" style="width: 6em" value="Go" onclick="return checkSelection('menuOptionsBox3')"/>
				</div>
			</div>
		</td>
	</tr>
	<tr class="title">
		<th>
			Family
		</th>
		<th>
			Program Referral Operations 
		</th>
	</tr>
	<tr>
		<td>
			<div>
				<div style="display: inline-block;"><b>Update:</b> </div>
				<div style="display: inline-block;">
					<html:select name="ProdSupportForm" property="menuOptionsBox17" styleId="menuOptionsBox17" style="width:300px">
						<html:option value="-1">Select an Action...</html:option>						
						<jims:isAllowed requiredFeatures="<%=Features.JCW_PS_UPDATEFAMILYMEMBERDETAILS%>">
							<html:option value="updatefamilyMember">Update Family Member </html:option>	
						</jims:isAllowed>				
						<html:option value="updatefamilyConstellation">Update Family Constellation</html:option>
						<html:option value="updatefamilyMemberRelation">Update Family Member Relation</html:option>						
					</html:select>
				</div>
				<div style="display: inline-block;">
					<html:submit styleId="menuOptionsBox17Submit" style="width: 6em" value="Go" onclick="return checkSelection('menuOptionsBox17')"/>
				</div>
			</div>	
			<div>
				<div style="display: inline-block;"><b>Delete:</b> </div>
				<div style="display: inline-block;  margin-left:5px;">
					<html:select name="ProdSupportForm" property="menuOptionsBox18" styleId="menuOptionsBox18" style="width:300px">
						<html:option value="-1">Select an Action...</html:option>
						<html:option value="deletefamilyMember">Delete Family Member</html:option>
						<html:option value="deleteFamilyConstellation">Delete Family Constellation</html:option>
					</html:select>
				</div>
				<div style="display: inline-block;">
					<html:submit styleId="menuOptionsBox18Submit" style="width: 6em" value="Go" onclick="return checkSelection('menuOptionsBox18')"/>
				</div>
			</div>	
		</td>
		<td>
			<div>
				<div style="display: inline-block;"><b>Update:</b> </div>
				<div style="display: inline-block;">
					<html:select name="ProdSupportForm" property="menuOptionsBox6" styleId="menuOptionsBox6" style="width:300px">
						<html:option value="-1">Select an Action...</html:option>
						<html:option value="updaterefer">Update a Program Referral Status Code</html:option>
						<html:option value="updatereferdate">Update Program Referral Dates, Controlling Referral and Casefile</html:option>
						<jims:isAllowed requiredFeatures="<%=Features.JCW_PS_EVENTMOVEPROGRAM%>">
						<html:option value="moveEvent">Move Event to New Program</html:option>
						</jims:isAllowed>
						<!--<html:option value="moverefer">Move a Program Referral to a New Casefile</html:option>-->
					</html:select>
				</div>
				<div style="display: inline-block;">
					<html:submit styleId="menuOptionsBox6Submit" style="width: 6em" value="Go" onclick="return checkSelection('menuOptionsBox6')"/>
				</div>
			</div>	
			<div>
				<div style="display: inline-block;"><b>Delete:</b> </div>
				<div style="display: inline-block; margin-left:5px;">
					<html:select name="ProdSupportForm" property="menuOptionsBox7" styleId="menuOptionsBox7" style="width:300px">
						<html:option value="-1">Select an Action...</html:option>
						<html:option value="referdelete">Delete a Program Referral Record</html:option>	
						<html:option value="commentdelete">Delete Program Comments</html:option>
					</html:select>
				</div>
				<div style="display: inline-block;">
					<html:submit styleId="menuOptionsBox7Submit" style="width: 6em" value="Go" onclick="return checkSelection('menuOptionsBox7')"/>
				</div>
			</div>	
		</td>
	</tr>
	<tr class="title">
		<th>
			Assignment Operations
		</th>
		<th>
			Casefile - Casework Entry Operations 
		</th>
	</tr>
	<tr>
		<td>
			<div>
				<div style="display: inline-block;"><b>Update:</b> </div>
				<div style="display: inline-block;">
					<html:select name="ProdSupportForm" property="menuOptionsBox4" styleId="menuOptionsBox4" style="width:300px">
						<html:option value="-1">Select an Action...</html:option>
						<html:option value="caseaddreferral">Add an Assignment Record</html:option>
						<html:option value="assignupdate">Update an Assignment Record</html:option>		
					</html:select>
				</div>
				<div style="display: inline-block;">
					<html:submit styleId="menuOptionsBox4Submit" style="width: 6em" value="Go" onclick="return checkSelection('menuOptionsBox4')"/>
				</div>
			</div>	
			<div>
				<div style="display: inline-block;"><b>Delete:</b> </div>
				<div style="display: inline-block;  margin-left:5px;">
					<html:select name="ProdSupportForm" property="menuOptionsBox5" styleId="menuOptionsBox5" style="width:300px">
						<html:option value="-1">Select an Action...</html:option>
						<html:option value="assigndelete">Delete an Assignment Record</html:option>
					</html:select>
				</div>
				<div style="display: inline-block;">
					<html:submit styleId="menuOptionsBox5Submit" style="width: 6em" value="Go" onclick="return checkSelection('menuOptionsBox5')"/>
				</div>
			</div>	
		</td>
		<td>
			<div>
				<div style="display: inline-block;"><b>Update:</b> </div>
				<div style="display: inline-block;">
					<html:select name="ProdSupportForm" property="menuOptionsBox10" styleId="menuOptionsBox10" style="width:300px">
						<html:option value="-1">Select an Action...</html:option>
						<html:option value="assessupdate">Update a Risk Assessment Record</html:option>
						<html:option value="serveventmodify">Update a Service Event Attendance Record</html:option>
						<html:option value="updatecalevent">Update Calendar Event Details</html:option>
						<html:option value="updatepactref">Update PACT Referral Details</html:option> 
						<jims:isAllowed requiredFeatures="<%=Features.JCW_PS_UPDATEACTIVITY%>">
						<html:option value="activityupdate">Update an Activity Record</html:option>
						<html:option value="eventTransferToAnotherInstructor">Event Transfer to Another Instructor</html:option>
						</jims:isAllowed>
					</html:select>
				</div>
				<div style="display: inline-block;">
					<html:submit styleId="menuOptionsBox10Submit" style="width: 6em" value="Go" onclick="return checkSelection('menuOptionsBox10')"/>
				</div>
			</div>	
			<div>
				<div style="display: inline-block;"><b>Delete:</b> </div>
				<div style="display: inline-block;  margin-left:5px;">
					<html:select name="ProdSupportForm" property="menuOptionsBox11" styleId="menuOptionsBox11" style="width:300px">
						<html:option value="-1">Select an Action...</html:option>
						<html:option value="assessdelete">Delete a Risk Assessment Record</html:option>
						<html:option value="activitydelete">Delete an Activity Record</html:option>
						<html:option value="deletecalevent">Delete a Calendar Service Event</html:option>
						<html:option value="removejuvcalevent">Remove Juvenile from Calendar Service Event</html:option>
						<html:option value="deleteEventsByInstructor"> Delete Events by Instructor</html:option>		
					</html:select>
				</div>
				<div style="display: inline-block;">
					<html:submit styleId="menuOptionsBox11Submit" style="width: 6em" value="Go" onclick="return checkSelection('menuOptionsBox11')"/>
				</div>
			</div>	
		</td>
	</tr>
	<tr class="title">
		<th>Profile - Casework Entry Operations</th>
		<th>
			Court Operations
		</th>
	</tr>
	<tr>
		<td>
			<div>
				<div style="display: inline-block;"><b>Update:</b> </div>
				<div style="display: inline-block;">
					<html:select name="ProdSupportForm" property="menuOptionsBox8" styleId="menuOptionsBox8" style="width:300px">
						<html:option value="-1">Select an Action...</html:option>
						<html:option value="viewmaysidetail">View all MAYSI Detail Records for a Juvenile</html:option>
						<html:option value="updatemaysi">Update a MAYSI Assessment Record</html:option>			
						<html:option value="updatemaysidetail">Update a MAYSI Detail Record</html:option>
						<html:option value="movemaysi">Move a MAYSI Assessment to Another JuvenileID</html:option>												
						<html:option value="movemaysidetail">Move a MAYSI Detail Record to Another JuvenileID</html:option>
						<html:option value="updateschoolhist">Update a School History Record</html:option>
						<html:option value="updatedrugtesting">Update a Drug Testing Record</html:option>
						<html:option value="updatetestingsession">Update a Testing Session Record</html:option>	
						<html:option value="updatetrait">Update a Trait Record</html:option>
						<html:option value="updateSubstanceAbuse">Update Substance Abuse</html:option>		
					</html:select>
				</div>
				<div style="display: inline-block;">
					<html:submit styleId="menuOptionsBox8Submit" style="width: 6em" value="Go" onclick="return checkSelection('menuOptionsBox8')"/>
				</div>
			</div>	
			<div>
				<div style="display: inline-block;"><b>Delete:</b> </div>
				<div style="display: inline-block;  margin-left:5px;">
					<html:select name="ProdSupportForm" property="menuOptionsBox9" styleId="menuOptionsBox9" style="width:300px">
						<html:option value="-1">Select an Action...</html:option>
						<html:option value="deletemaysi">Delete a MAYSI Assessment Record</html:option>			
						<html:option value="deletemaysidetail">Delete a MAYSI Detail Record</html:option>
						<html:option value="deleteschoolhist">Delete a School History Record</html:option>
						<html:option value="deletetrait">Delete a Juvenile Trait Record</html:option>
						<html:option value="deletedrugtesting">Delete a Drug Testing Record</html:option>			
					</html:select>
				</div>
				<div style="display: inline-block;">
					<html:submit styleId="menuOptionsBox9Submit" style="width: 6em" value="Go" onclick="return checkSelection('menuOptionsBox9')"/>
				</div>
			</div>	
		</td>
		<td>
			<div>
				<div style="display: inline-block;"><b>Update:</b> </div>
				<div style="display: inline-block;">
					<html:select name="ProdSupportForm" property="menuOptionsBox14" styleId="menuOptionsBox14" style="width:300px">
						<html:option value="-1">Select an Action...</html:option>
						<jims:isAllowed requiredFeatures="<%=Features.JCW_PS_UPDATEDETENTIONCOURTRECORD%>"> 
						<html:option value="updateDetentionCourtRecord">Update Detention Court Record</html:option>
						</jims:isAllowed>
						<jims:isAllowed requiredFeatures="<%=Features.JCW_PS_UPDATEDISTRICTCOURTCALENDARRECORD%>"> 
						<html:option value="updateDistrictCourtCalendarRecord">Update District Court Calendar Record</html:option>
						</jims:isAllowed>
						<jims:isAllowed requiredFeatures="<%=Features.JCW_PS_ANCILLARYCOURT%>"> 
						<html:option value="updateAncillaryCalendar">Update Ancillary Calendar</html:option>
						</jims:isAllowed>
						<jims:isAllowed requiredFeatures="<%=Features.JCW_PS_UPDATEPETITIONDETAILS%>"> 	
						<html:option value="viewPetitionDetails">Update Petition Details</html:option>
						</jims:isAllowed>
						<jims:isAllowed requiredFeatures="<%=Features.JCW_PS_UPDATELASTATTORNEY%>"> 	
						<html:option value="updateLastAttorney">Update Last Attorney Details</html:option>
						</jims:isAllowed>
					</html:select>
				</div>
				<div style="display: inline-block;">
					<html:submit styleId="menuOptionsBox14Submit" style="width: 6em" value="Go" onclick="return checkSelection('menuOptionsBox14')"/>
				</div>
			</div>
			<div>
				<div style="display: inline-block;"><b>Delete:</b> </div>
				<div style="display: inline-block;  margin-left:5px;">
					<html:select name="ProdSupportForm" property="menuOptionsBox15" styleId="menuOptionsBox15" style="width:300px">
						<html:option value="-1">Select an Action...</html:option>
						<jims:isAllowed requiredFeatures="<%=Features.JCW_PS_ANCILLARYCOURT%>"> 
						<html:option value="deleteAncillaryCourtCalendar">Delete Ancillary Court Calendar Record</html:option>
				 		</jims:isAllowed>
				 		<jims:isAllowed requiredFeatures="<%=Features.JCW_PS_DELETECOURT%>"> 
							<html:option value="deleteDistrictCourtCalendar">Delete District Court Calendar Records</html:option>
				 		</jims:isAllowed>
				 		<jims:isAllowed requiredFeatures="<%=Features.JCW_PS_DELETECOURT%>">
				 			<html:option value="deleteDetentionCourtCalendar">Delete Detention Court Calendar Records</html:option>	
				 		</jims:isAllowed>
				 		<jims:isAllowed requiredFeatures="<%=Features.JCW_PS_DELETECOURT%>"> 
							<html:option value="deletePetitionRecord">Delete Petition Record</html:option>
				 		</jims:isAllowed>
				 </html:select>
					
				</div>
				<div style="display: inline-block;">
					<html:submit styleId="menuOptionsBox15Submit" style="width: 6em" value="Go" onclick="return checkSelection('menuOptionsBox15')"/>
				</div>
			</div>	
		</td>
	</tr>
	<tr class="title">
		<jims:isAllowed requiredFeatures="<%=Features.JCW_PS_UPDATEHEADER%>"> 
			<th>Facility Operations </th>
		</jims:isAllowed>
		<th>
		
		</th>
	</tr>
	<tr>
		<jims:isAllowed requiredFeatures="<%=Features.JCW_PS_UPDATEHEADER%>"> 
		<td>
			<div>
				<div style="display: inline-block;"><b>Update:</b> </div>
				<div style="display: inline-block;">
					<html:select name="ProdSupportForm" property="menuOptionsBox12" styleId="menuOptionsBox12" style="width:300px">
						<html:option value="-1">Select an Action...</html:option>
						<html:option value="updatefacilityheader">Update a Facility Header Record</html:option>
						<html:option value="updatefacilitydetention">Update a Facility Detention Record</html:option>
					</html:select>
				</div>
				<div style="display: inline-block;">
					<html:submit styleId="menuOptionsBox12Submit" style="width: 6em" value="Go" onclick="return checkSelection('menuOptionsBox12')"/>
				</div>
			</div>	
			<div>
				<div style="display: inline-block;"><b>Delete:</b> </div>
				<div style="display: inline-block;  margin-left:5px;">
					<html:select name="ProdSupportForm" property="menuOptionsBox13" styleId="menuOptionsBox13" style="width:300px">
						<html:option value="-1">Select an Action...</html:option>						
						<jims:isAllowed requiredFeatures="<%=Features.JCW_PS_DELETEDETENTIONRECORD%>"> 
						<html:option value="deletefacilitydetention">Delete a Facility Detention Record</html:option>						
						</jims:isAllowed>
					</html:select>
				</div>
				<div style="display: inline-block;">
					<html:submit styleId="menuOptionsBox13Submit" style="width: 6em" value="Go" onclick="return checkSelection('menuOptionsBox13')"/>
				</div>
			</div>	
		</td>
		</jims:isAllowed>
		<td>
			<!-- new section goes here -->	
		</td>
	</tr>
</table>

<hr>
<table align="center" border="0" width="500">
<br/>
	<tr>
		<td colspan="2" align="center">
			<html:form method="post" action="/DisplayProductionSupportMainPopup.do">
				<html:submit value="Close Production Support Screen" onclick="return closePopupMenu()"/>
			</html:form>
		</td>
	</tr>
</table>    
<br/>
</html:form>
</body>
</html:html>
