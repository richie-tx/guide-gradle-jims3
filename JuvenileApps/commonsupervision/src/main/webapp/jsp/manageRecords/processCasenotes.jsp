<html>
	<head>
		<title>Common Supervision - processCasenotes.htm</title>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		
		<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
		<script type="text/javascript" src="/<msp:webapp/>case_util.js"></script>
		<script type="text/javascript" src="/<msp:webapp/>common/CalendarPopup.js"></script>
		<script type="text/javascript" src="/<msp:webapp/>common/AnchorPosition.js"></script>
		<!-- Javascript for emulated navigation -->
		<link href="/<msp:webapp/>common/base.css" rel="stylesheet" type="text/css">
		<script src="/<msp:webapp/>common/sorttable.js"></script>
		<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>

	</head>
	<body topmargin=0 leftmargin="0">

		<form name="myForm">
			<div align="center">
				<table width="98%" border="0" cellpadding="0" cellspacing="0" >
					<tr>
						<td valign=top><img src=../common/images/spacer.gif height=5></td>
					</tr>
					<tr>
						<td valign=top>
							<table width=100% border="0" cellpadding="0" cellspacing="0" >
								<tr>
									<td valign=top>
										<!--tabs start-->
										<script type="text/javascript">renderTabs("")</script>
										<!--tabs end-->
									</td>
								</tr>
								<tr>
									<td bgcolor=#6699FF><img src=/<msp:webapp/>common/images/spacer.gif height=5></td>
								</tr>
							</table>
							<table width=100% border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
								<tr>
									<td><img src=/<msp:webapp/>common/images/spacer.gif height=5></td>
								</tr>
								<tr>
									<td valign=top align=center>
										<!-- BEGIN HEADING TABLE -->
										<table width=100%>
											<tr>
												<td align="center" class="header">SPN Split - Process Casenotes</td>
											</tr>
											<tr>
												<td class=confirm>SPN Split Successful</td>
											</tr>
										</table>
										<!-- END HEADING TABLE -->
										<!-- BEGIN INSTURCTION TABLE -->
										<table width="98%" border="0">
											<tr>
												<td>
													<ul>
														<li>Select Casenotes to move from Erroneous SPN to Valid SPN.</li>
													</ul>
												</td>
											</tr>
										</table>
										<!-- END INSTRUCTION TABLE -->

										<!-- supervisee info start -->
										<table width="98%" cellpadding="2" cellspacing="0" class=borderTableBlue>
											<tr class=detailHead>
												<td width=1%><a href="javascript:showHide('spnInfo', 'row')" border=0><img src="/<msp:webapp/>common/images/expand.gif" border=0 name="spnInfo"></a></td>
												<td>
													SPN Split Information
												</td>
											</tr>
											<tr class=hidden id=spnInfoSpan>
												<td colspan=2>
													<table width="100%" cellpadding="4" cellspacing="1">
														<tr>
															<td class="formDeLabelBottomBorder" nowrap width=1%>Erroneous SPN</td>
															<td class="formDeBottomBorder" colspan=3>
																12345678
															</td>
														</tr>
														<tr>
															<td class=formDeLabel>Name</td>
															<td class=formDe>SMITH, JOHN</td>
															<td class=formDeLabel>DOB</td>
															<td class=formDe>03/03/2006</td>
														</tr>
														<tr>
															<td class=formDeLabel>Sex</td>
															<td class=formDe>M</td>
															<td class=formDeLabel width=1%>Race</td>
															<td class=formDe>WHITE</td>
														</tr>
														<tr>
															<td class=formDeLabel>Jail Indicator</td>
															<td class=formDe colspan=3>RELEASED</td>
														</tr>
														<tr>
															<td colspan=4><img src=/<msp:webapp/>common/images/spacer.gif></td>
														</tr>
														<tr>
															<td class="formDeLabelBottomBorder" nowrap>Valid SPN</td>
															<td class="formDeBottomBorder" colspan=3>
																12345677
															</td>
														</tr>
														<tr>
															<td class=formDeLabel>Name</td>
															<td class=formDe>SMITH, JOHN</td>
															<td class=formDeLabel>DOB</td>
															<td class=formDe>03/03/2006</td>
														</tr>
														<tr>
															<td class=formDeLabel>Sex</td>
															<td class=formDe>M</td>
															<td class=formDeLabel>Race</td>
															<td class=formDe>WHITE</td>
														</tr>
														<tr>
															<td class=formDeLabel>Jail Indicator</td>
															<td class=formDe colspan=3>RELEASED</td>
														</tr>
													</table>
												</td>
											</tr>
										</table>
										<!-- supervisee info end -->
										<div class=spacer4px></div>
										
										<!-- orders list start -->
										<table width="98%" cellpadding="2" cellspacing="0" class=borderTableBlue>
											<tr class=detailHead>
												<td>
													SPN's on these orders have been updated to 12345677 
												</td>
											</tr>
											<tr>
												<td>
													<table width="100%" cellpadding="2" cellspacing="1" id="anotherID">
														<tr class="formDeLabel">
															<td>CDI</td>
															<td>Case#</td>
															<td>CRT</td>
															<td>Order Filed</td>
															<td>Order Status</td>
															<td>Version</td>
															<td>Agency</td>
														</tr>
														<tr>
															<td>001</td>
															<td><a href="/<msp:webapp/>processSupOrder/processSuggestedOrderView.htm?viewOnly">12345676 01010</a></td>
															<td>011</td>
															<td>01/01/2003</td>
															<td>Inactive</td>
															<td>Original</td>
															<td>PTS</td>
														</tr>
														<tr class=alternateRow>
															<td>002</td>
															<td><a href="/<msp:webapp/>processSupOrder/processSuggestedOrderView.htm?viewOnly">123456777 01010</a></td>
															<td>011</td>
															<td>01/21/2003</td>
															<td>Inactive</td>
															<td>3rd Amended</td>
															<td>CSCD</td>
														</tr>
													</table>
												</td>
											</tr>
										</table>
										<!-- orders list end -->
										<div class=spacer4px></div>
										<table width="98%" border="0" cellpadding="2" cellspacing="0" class=borderTableBlue>
											<tr>
												<td class=detailHead>Search Casenotes</td>
											</tr>
											<tr>
												<td>
													<table width="100%" border="0" cellspacing=1 cellpadding=2>
														<tr>
															<td class=formDeLabel width=1% nowrap>Date Range</td>
															<td class=formDe>
																<table cellpadding="2" cellspacing="1">
																	<tr>
																		<td class=formDeLabel>Begin</td>
																		<td></td>
																		<td class=formDeLabel>End</td>
																	</tr>
																	<tr class=formDe>
																		<td>
																			<SCRIPT type="text/javascript" ID="js1">
																				var cal1 = new CalendarPopup();
																				cal1.showYearNavigation();
																			</SCRIPT>
																			<input type=text size=10 name=beginDate1>
																		<A HREF="#" onClick="cal1.select(beginDate1,'anchor5','MM/dd/yyyy'); return false;" NAME="anchor5" ID="anchor5" border=0><img border=0 src="/<msp:webapp/>common/images/calendar2.gif" title="(mm/dd/yyyy)" /></A> </td>
																			<td>-</td>
																			<td>
																				<input type=text size=10 name=endDate1>
																				<A HREF="#" onClick="cal1.select(endDate1,'anchor6','MM/dd/yyyy'); return false;" NAME="anchor6" ID="anchor6" border=0><img border=0 src="/<msp:webapp/>common/images/calendar2.gif" title="(mm/dd/yyyy)" /></A>
																				<script>
																					if (location.search!="?previous")
																					document.forms[0].endDate1.value = getCurrentDate();
																				</script>
																			</td>
																		</tr>
																	</table>
																</td>
															</tr>
															
															<tr>
															<td class=formDeLabel></td>
															<td class=formDe><input type="button" value=Submit> <input type="button" value=Refresh onclick=reloadPage()> </td>
														</tr>
														</table>
													</td>
												</tr>
											</table>
											<div class=spacer4px></div>
										<!--casenotes start-->
										<table width="98%" cellpadding="2" cellspacing="1">
											<!--1 casenote start-->
											<tr class="detailHead">
												<td><input type=checkbox name="cn" onclick="uncheckCheckAll(this,'selectAll')">Date/Time</td>
												<td>Subject</td>
												<td>Type</td>
												<td>Contact Method</td>
												<td>Created By</td>
											</tr>
											<tr class=formDe>
												<td>03/10/2006 09:00</td>
												<td>Community Service</td>
												<td>Compliance</td>
												<td>Phone</td>
												<td>Buckman, Brad</td>
											</tr>
											<tr>
												<td class="borderTableBlue" colspan=5>Supervisee reports completing 8 hours community service last month. M4P2 screen checked for hours completed. M4P2 screen shows 0 CS hours completed through 1/31/2006. Ordered to complete 240 hrs.</td>
											</tr>
											<!--this is a separator between the casenotes-->
											<tr>
												<td colspan=5><img src=/<msp:webapp/>common/images/spacer.gif height=5></td>
											</tr>
											<!--1 casenote end-->
											<tr class="detailHead">
												<td><input type=checkbox name="cn" onclick="uncheckCheckAll(this,'selectAll')">Date/Time</td>
												<td>Subject</td>
												<td>Type</td>
												<td>Contact Method</td>
												<td>Created By</td>
											</tr>
											<tr class=formDe>
												<td>02/09/2006 09:00 </td>
												<td valign=top>Employment</td>
												<td valign=top>Supervision</td>
												<td valign=top>In Person</td>
												<td valign=top>Young, Vince <img src=/<msp:webapp/>common/images/clip_image001.gif title="This casenote was autosaved"></td>
											</tr>
											<tr>
												<td colspan=5 class="borderTableBlue">Supervisee reports being a stay home mom and is being supported by her dead husband while she takes care of the kids at home.</td>
											</tr>
											<tr>
												<td colspan=5><img src=/<msp:webapp/>common/images/spacer.gif height=5></td>
											</tr>
											<tr class="detailHead">
												<td><input type=checkbox name="cn" onclick="uncheckCheckAll(this,'selectAll')">Date/Time</td>
												<td>Subject</td>
												<td>Type</td>
												<td>Contact Method</td>
												<td>Created By</td>
											</tr>
											<tr class=formDe>
												<td>02/09/2006 09:00 </td>
												<td valign=top>Fees</td>
												<td valign=top>Supervision</td>
												<td valign=top>In Person</td>
												<td valign=top>System</td>
											</tr>
											<tr>
												<td colspan=5 class="borderTableBlue">Supervisee made $72 fee payment to DCO during this office visit and is current on fees.</td>
											</tr>
										</table>
										<!--casenotes end-->
										<!-- BEGIN BUTTON TABLE -->
										<table border="0" width="100%">
											<tr>
												<td align="center">
													<input type=button value=Next onClick="goNav('processCasenotesSummary.htm')">
													<input type="reset" value="Cancel" name="cancel"  onClick="goNav('spnSplitForm.htm')">
												</td>
											</tr>
										</table>
										<!-- END BUTTON TABLE -->
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>

				<!-- END  TABLE -->
			</div>
			<br>
		</Form>
		<!-- BEGIN NOTES TABLE -->
		<table width="98%">
			<tr>
				<td class="subhead">Notes:</td>
			</tr>
			<tr>
				<td>
					<ol>
						<li>Canceling would create a task for the User to finish this</li>
					</ol>
				</td>
			</tr>
		</table>
		<!-- END NOTES TABLE -->
	<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html>
