<html>
	<head>
		<title>Common Supervision - processCasenotesSummary.htm</title>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		
		<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
		<script type="text/javascript" src="/<msp:webapp/>case_util.js"></script>
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
						<td valign=top><img src=/<msp:webapp/>common/images/spacer.gif height=5></td>
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
												<td align="center" class="header">SPN Split - Process Casenotes Summary</td>
											</tr>
										</table>
										<!-- END HEADING TABLE -->
										<!-- BEGIN INSTURCTION TABLE -->
										<table width="98%" border="0">
											<tr>
												<td>
													<ul>
														<li>Review selected Casenotes to move from Erroneous SPN to Valid SPN.</li>
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
										<!--casenotes start-->					
										<table width="98%" cellpadding="2" cellspacing="1">
											<!--1 casenote start-->
											<tr class=formDeLabel id=supPd1>
												<td colspan=5>Supervision Period - VALID SPN 10/11/2002-10/11/2003</td>
											</tr>
											<tr class="detailHead">
												<td>Date/Time</td>
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
											<tr class=formDeLabel id=supPd2>
												<td colspan=5>Supervision Period - VALID SPN 10/11/2002-10/11/2003</td>
											</tr>
											<tr class="detailHead">
												<td>Date/Time</td>
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
										</table>
										<script>
											//if the user had to select which supervision period to assign CN to:
											if (period!="notAllChains"){
												show("supPd1", 0)
												show("supPd2", 0)
											}
										</script>
										<!--casenotes end-->				
										<!-- BEGIN BUTTON TABLE -->
										<table border="0" width="100%">
											<tr>
												<td align="center">
													<input type="reset" value="Back" name="back"  onClick="goNav('back')">
													<input type=button value=Finish onClick="goNav('confirmation.htm?processCasenote')">
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
