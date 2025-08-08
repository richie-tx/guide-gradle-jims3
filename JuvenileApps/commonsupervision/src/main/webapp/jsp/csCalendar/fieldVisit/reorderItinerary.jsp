<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 01/30/2008	 Aswin Widjaja - Create JSP -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@page import="naming.UIConstants"%>
<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />
<title><bean:message key="title.heading" /> - csCalendar - reorderItinerary.jsp</title>
<!--THESE TWO LINES FOR STRUTS & JAVASCRIPTS VALIDATIONS-->
<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>


<script>
	function openMapquest(streetNum, street, city, state, zip, country){
		openWindow('http://www.mapquest.com/maps/map.adp?address='+streetNum+'+'+street+'+&city='+city+'&state='+state+'&zipcode='+zip+'&country='+country+'&cid=lfmaplink')
	}
</script>
</head>
<body topmargin="0" leftmargin="0">
	<form name="myForm">
		<div align="center">
			<table width="98%" border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
				</tr>
				<tr>
					<td valign="top">
						<table width=100% border="0" cellpadding="0" cellspacing="0" >
							<tr>
								<td valign="top">
									<!--tabs start-->
									<tiles:insert page="../../common/commonSupervisionTabs.jsp" flush="true">
										<tiles:put name="tab" value="calendarTab"/>
									</tiles:insert>	
									<!--tabs end-->
								</td>
							</tr>
							<tr>
								<td bgcolor=#6699FF><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
							</tr>
						</table>
						<table width=100% border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
							<tr>
								<td><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
							</tr>
							<tr>
								<td valign="top" align=center>
									<!-- BEGIN HEADING TABLE -->
									<table width=100%>
										<tr>
											<td align="center" class="header">CSCD - Reorder Field Visit Itinerary - <script>
												document.write(getCurrentDate())
											</script></td>
										</tr>
									</table>
									<!-- END HEADING TABLE -->
									<!-- BEGIN INSTRUCTION TABLE -->
									<table width="98%" border="0">
										<tr>
											<td>
												<ul>
													<li>Click field visit Order # to view details.</li>
												</ul>
											</td>
										</tr>
									</table>
									<!-- END INSTRUCTION TABLE -->
									<!--tabs start-->
									<tiles:insert page="../../csCalendar/fieldVisit/fieldVisitItineraryTile.jsp" flush="true">
										<tiles:put name="itinerary" beanName="csCalendarFVForm" beanProperty="currentItinerary" />
									</tiles:insert>
									<!--tabs end-->
									<div class=spacer4px></div>

									<div class=borderTableBlue style="width:98%">
										<table width='100%' cellpadding="2" cellspacing="1">
											<tr class="formDeLabel">
												<td align=center>#</td>
												<td>Name / SPN <nobr><img src="../../common/images/arrow_up.gif"><img src="../../common/images/arrow_down.gif"></nobr></td>
												<td>SSN <nobr><img src="../../common/images/arrow_up.gif"><img src="../../common/images/arrow_down.gif"></nobr></td>
												<td>Purpose <nobr><img src="../../common/images/arrow_up.gif"><img src="../../common/images/arrow_down.gif"></nobr></td>
												<td>Time <nobr><img src="../../common/images/arrow_up.gif"><img src="../../common/images/arrow_down.gif"></nobr></td>
												<td>Address <nobr><img src="../../common/images/arrow_up.gif"><img src="../../common/images/arrow_down.gif"></nobr></td>
												<td>Zip <nobr><img src="../../common/images/arrow_up.gif"><img src="../../common/images/arrow_down.gif"></nobr></td>
												<td>Key Map <nobr><img src="../../common/images/arrow_up.gif"><img src="../../common/images/arrow_down.gif"></nobr></td>
												<td>Type <nobr><img src="../../common/images/arrow_up.gif"><img src="../../common/images/arrow_down.gif"></nobr></td>
											</tr>
											<tr>
												<td align=center class=itineraryOrder>
													<input type="text" name="order" size="1" value="1">
												</td>
												<td>Smith, John
													<div>12346578</div>
												</td>
												<td>132-33-3231</td>
												<td>Verify Address</td>
												<td>1:00PM - 2:00PM</td>
												<td><a href="javascript:openMapquest('406', 'Caroline St.', 'Houston', 'TX', '77002', '')" title="Click to open Mapquest">
													<div>12121 Westheimer St</div>
													<div>Houston, TX 77019</div>
												</a>
												<div>713-454-9999</div>
											</td>
											<td>77019</td>
											<td>430-S</td>
											<td>Regular</td>
										</tr>
										<tr>
											<td valign="top" colspan=11 class=borderTopGrey>
												<table cellpadding="2" cellspacing="1" border=0 width=100% >
													<tr>
														<td class="formDeLabel" width="1%">Offenses</td>
														<td width=70%>POSSESS MARIJUANA, POSSESS LSD</td>
														<td class="formDeLabel" width="1%">Outcome</td>
														<td width="28%">Complete</td>
													</tr>
													<tr>
														<td class="formDeLabel" width="1%">Caution</td>
														<td colspan=3>Dangerous Dog in area</td>
													</tr>
													<tr>
														<td class="formDeLabel" width="1%">Noteworthy Conditions</td>
														<td colspan=3><div>No internet at residence.</div></td>
													</tr>
												</table>
											</td>
										</tr>
										<tr class="alternateRow">
											<td align=center class=itineraryOrder><input type="text" name="order" size="1" value="2"></td>
											<td>Doe, Jane
												<div>23654789</div>
											</td>
											<td>132-44-3231</td>
											<td>Routine</td>
											<td>2:10PM - 3:00PM</td>
											<td> <a href="javascript:openMapquest('406', 'Caroline St.', 'Houston', 'TX', '77002', 'US')" title="Click to open Mapquest">
												<div>406 Caroline St.</div>
												<div>Houston, TX 77002</div>
											</a>
											<div>713-111-9999</div>
										</td>
										<td>77018</td>
										<td>311-A</td>
										<td>Regular</td>
									</tr>
									<tr class=alternateRow>
										<td valign="top" colspan=11 class=borderTopGrey>
											<table cellpadding="2" cellspacing="1" width=100%>
												<tr>
													<td class="formDeLabel" width="1%">Offenses</td>
													<td width="70%">POSSESS MARIJUANA</td>
													<td class="formDeLabel" width="1%">Outcome</td>
													<td width="28%">Complete</td>
												</tr>
												<tr>
													<td class="formDeLabel" width="1%">Caution</td>
													<td colspan=3>Crazy Ex-husband</td>
												</tr>
												<tr>
													<td class="formDeLabel" width="1%">Noteworthy Conditions</td>
													<td colspan=3></td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td align=center class=itineraryOrder><input type="text" name="order" size="1" value="3"></td>
										<td>Everett, Mike
											<div>98745612</div>
										</td>
										<td>132-33-1456</td>
										<td>Routine</td>
										<td>3:10PM - 4:00PM</td>
										<td><a href="javascript:openMapquest('406', 'Caroline St.', 'Houston', 'TX', '77002', 'US')" title="Click to open Mapquest">
											<div>999 Johnston St</div>
											<div> Houston, TX 77029
												<div>
													<div>713-333-3431</div>
												</div>
											</div>
										</a></td>
										<td>77029</td>
										<td></td>
										<td>Regular</td>
									</tr>
									<tr>
										<td valign="top" colspan=11 class=borderTopGrey>
											<table cellpadding="2" cellspacing="1" width=100%>
												<tr>
													<td class="formDeLabel" width="1%">Offenses</td>
													<td width="70%">POSSESS MARIJUANA</td>
													<td class="formDeLabel" width="1%">Outcome</td>
													<td width="28%">Scheduled</td>
												</tr>
												<tr>
													<td class="formDeLabel" width="1%">Caution</td>
													<td colspan=3></td>
												</tr>
												<tr>
													<td class="formDeLabel" width="1%">Noteworthy Conditions</td>
													<td colspan=3><div>No internet at residence.</div><div>No contact with children outside of own biological children.</div></td>
												</tr>
											</table>
										</td>
									</tr>
									<tr class="alternateRow">
										<td align=center class=itineraryOrder><input type="text" name="order" size="1" value="4"></td>
										<td>Moore, Michael
											<div>45617845</div>
										</td>
										<td>132-33-7894</td>
										<td>Routine</td>
										<td>4:10PM - 5:00PM</td>
										<td><a href="javascript:openMapquest('406', 'Caroline St.', 'Houston', 'TX', '77002', 'US')" title="Click to open Mapquest">
											<div>987 Carolines St</div>
											<div> Houston, TX 77039
												<div>
													<div>713-888-9999</div>
												</div>
											</div>
										</a></td>
										<td>77039</td>
										<td>111-B</td>
										<td>Sex Offender<br>
										Child Safety Zone (residence)</td>
									</tr>
									<tr class=alternateRow>
										<td valign="top" colspan=11 class=borderTopGrey>
											<table cellpadding="2" cellspacing="1" width=100%>
												<tr>
													<td class="formDeLabel" width="1%">Offenses</td>
													<td width="70%">SEXUAL ASSAULT CHILD UNDER 14, FELONIOUS STUPIDITY</td>
													<td class="formDeLabel" width="1%">Outcome</td>
													<td width="28%">Scheduled</td>
												</tr>
												<tr>
													<td class="formDeLabel" width="1%">Caution</td>
													<td colspan=3></td>
												</tr>
												<tr>
													<td class="formDeLabel" width="1%">Noteworthy Conditions</td>
													<td colspan=3></td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</div>

							<!-- END DETAIL TABLE -->
							<!-- BEGIN BUTTON TABLE -->
							<table border="0" width="100%">
								<tr>
									<td align="center">
										<input type="button" value="Back" name="back" onClick="goNav('back')">
										<input type="button" value="Save & Continue" name="sc" onClick="goNav('fieldVisitEventsList.htm?itinReorder')">
										<input type="button" value="Cancel" name="cancel" onClick="goNav('fieldVisitEventsList.htm')">
									</td>
								</tr>
							</table>
							<!-- END BUTTON TABLE -->
						</td>
					</tr>
				</table><br>
			</td>
		</tr>
	</table>
</td>
</tr>
</table>
</div>
<br>
</Form>
<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
