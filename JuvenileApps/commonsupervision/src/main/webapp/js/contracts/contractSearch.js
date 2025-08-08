/* script for contractSearch.jsp */
//show hide where there is an expand (plus sign)
/*params: spanName - name of the span to hide or show - image has same id as span -"Span"
 */
$(document)
		.ready(
				function() {
					function showHide(spanName, format, path) {

						var appendName = spanName + "Span";
						var theClassName = document.getElementById(appendName).className;
						if (theClassName == 'visible'
								|| theClassName == 'visibleTR'
								|| theClassName == 'visibleTable') {
							window.document.images[spanName].src = path
									+ "images/expand.gif";
							document.getElementById(appendName).className = 'hidden';
						} else {
							window.document.images[spanName].src = path
									+ "images/contract.gif";
							if (format == "row") {
								document.getElementById(appendName).className = 'visibleTR';
							} else if (format == "table") {
								document.getElementById(appendName).className = 'visibleTable';
							} else {
								document.getElementById(appendName).className = 'visible';
							}
						}
					}
					$("#btnSubmit")
							.click(
									function() {
										var theForm = document.forms[0];
										var Name = Trim(theForm.searchContractName.value);
										var Num = Trim(theForm.searchContractNum.value);
										var pgmFundingDesc = Trim(theForm.searchProgramFundingDesc.value);
										var startDateFrom = Trim(theForm.startDateFrom.value);
										var startDateTo = Trim(theForm.startDateTo.value);
										var str = Name + Num + pgmFundingDesc
												+ startDateFrom + startDateTo;
										var result = false;
										var msg = "";
										var strutsVal=true;
										theForm.searchContractName.value = Name;
										theForm.searchContractNum.value = Num;
										theForm.searchProgramFundingDesc.value = pgmFundingDesc;
										theForm.startDateFrom.value = startDateFrom;
										theForm.startDateTo.value = startDateTo;
										if (str == ""
												&& theForm.showExpired.checked == false
												&& theForm.contractTypeId.selectedIndex == 0) {
											alert("At least 1 search value required for search.");
											theForm.searchContractName.focus();
											return false;
										}
										if (theForm.searchContractName.value != "") {
											if (!validateContractForm(theForm))
												{
													strutsVal=false;
													return false;
												}
										}
										if (startDateFrom > "") {
											result = validateDate(startDateFrom);
											if (result == false) {
												msg = "From Date is invalid date or wrong date format.\n";
												theForm.startDateFrom.focus();
											}
										}
										if (startDateTo > "") {
											result = validateDate(startDateTo);
											if (result == false) {
												if (msg == "") {
													theForm.startDateTo.focus();
												}
												msg += "To Date is invalid date or wrong date format.\n";
											}
										}
										if (msg == "") {
											if (startDateFrom > ""
													&& startDateTo == "") {
												msg = "To Date required for date range search.\n";
												theForm.startDateTo.focus();
											}
											if (startDateFrom == ""
													&& startDateTo > "") {
												msg = "From Date required for date range search.\n";
												theForm.startDateFrom.focus();
											}
											if (startDateFrom > ""
													&& startDateTo > "") {
												var fromDateValues = startDateFrom
														.split('/');
												var toDateValues = startDateTo
														.split('/');
												// array elements 0=month 1=day
												// 2=year
												var fromDate = fromDateValues[2]
														+ fromDateValues[0]
														+ fromDateValues[1];
												var toDate = toDateValues[2]
														+ toDateValues[0]
														+ toDateValues[1];
												if (fromDate > toDate) {
													msg = "From Date cannot be future date to To date.\n";
													theForm.startDateFrom
															.focus();
												}
											}
										}

										if (msg != "") {
											alert(msg);
											return false;
										}
										if (msg == ""&&strutsVal==true)
											{
												return true;
											}
									});

					function validateDate(fldValue) {
						var regDate = /^\d{1,2}\/\d{1,2}\/\d{4}$/;
						var result = regDate.test(fldValue, regDate);
						if (result == false) {
							return false;
						}
						var dValues = fldValue.split('/');
						var mon = dValues[0];
						var day = dValues[1];
						var leapYear = 0;
						if (mon > 12 || mon == 0) {
							return false;
						}
						if (day > 31 || day == 0) {
							return false;
						}
						if (mon == 4 || mon == 6 || mon == 9 || mon == 11) {
							if (day > 30) {
								return false;
							}
						}
						if (mon == 2) {
							leapYear = dValues[2] % 4;
							if (leapYear == 0 && day > 29) {
								return false;
							}
							if (leapYear > 0 && day > 28) {
								return false;
							}
						}
						return true;
					}

					function switchText(theLink, theLiteralRowID) {
						if (document.getElementById(theLink).innerHTML == "View") {
							show(theLiteralRowID, 1, 'row');
							document.getElementById(theLink).innerHTML = "Hide"
						} else {
							show(theLiteralRowID, 0);
							document.getElementById(theLink).innerHTML = "View"
						}
					}

					// show - basic show/hide functionality
					/*
					 * params: what - id of what to show or hide hide - 0=hide
					 * 1=show format - table/row - no entry standard Block
					 */

					function show(what, hide, format) {
						if (hide == 0) {
							document.getElementById(what).className = 'hidden';
						} else if (format == "row") {
							document.getElementById(what).className = 'visibleTR';
						} else if (format == "table") {
							document.getElementById(what).className = 'visibleTable';
						} else if (format == "inline") {
							document.getElementById(what).className = 'visibleInline';
						} else {
							document.getElementById(what).className = 'visible';
						}
					}

					function checkSelect(theForm) {
						var sel = document.getElementsByName("selectionMade");
						if (sel.length == 0) {
							alert("At least 1 value must be selected to continue.");
							return false;
						}
						return true;
					}

					function allContractsSelect(el, checkboxName) {
						var theForm = el.form;
						var checkAllName = el.name;
						var objCheckBoxes = document
								.getElementsByName(checkboxName);
						var countCheckBoxes = objCheckBoxes.length;

						for ( var i = 0; i < countCheckBoxes; i++)
							if (objCheckBoxes[i].checked == false) {
								objCheckBoxes[i].checked = true;
							} else {
								objCheckBoxes[i].checked = false;
							}
					}
				});
