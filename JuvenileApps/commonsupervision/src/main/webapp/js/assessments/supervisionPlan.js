									
									//this function checks for an instance of appropropriate punctuation for the 4 textarea fields on the screen (. ? !)
									function validateCompleteSentence(theForm){

										if (theForm.problem.value != ""){
											if (!checkForPunctuation(theForm.problem.value)){
												alert("Please use proper punctuation in your Problem statement.");
												return false;
											}
										}

										if (theForm.behaviorObjective.value != ""){
											if (!checkForPunctuation(theForm.behaviorObjective.value)){
												alert("Please use proper punctuation in your Behavior Objective statement.");
												return false;
											}
										}

										if (theForm.offenderActionPlan.value != ""){
											if (!checkForPunctuation(theForm.offenderActionPlan.value)){
												alert("Please use proper punctuation in your Offender Action Plan statement.");
												return false;
											}
										}

										if (theForm.csoActionPlan.value != ""){
											if (!checkForPunctuation(theForm.csoActionPlan.value)){
												alert("Please use proper punctuation in your CSO Action Plan statement.");
												return false;
											}
										}
										return true;
									}

									function checkForPunctuation(theVal){
										if (theVal.indexOf(".") == -1 && theVal.indexOf("?") == -1 && theVal.indexOf("!") == -1){
											return false;
										}
										return true;
									}
									
									
									function trimSupPlanFields(theForm){

										theForm.problem.value=trimTextarea(theForm.problem.value);
										theForm.behaviorObjective.value = trimTextarea(theForm.behaviorObjective.value);
										theForm.offenderActionPlan.value = trimTextarea(theForm.offenderActionPlan.value);
										theForm.csoActionPlan.value = trimTextarea(theForm.csoActionPlan.value);

										myReverseTinyMCEFix(theForm.problem);
										myReverseTinyMCEFix(theForm.behaviorObjective);
										myReverseTinyMCEFix(theForm.offenderActionPlan);
										myReverseTinyMCEFix(theForm.csoActionPlan);


										return true;

									}
									
									function validateSupPlanFieldsForBROnly(theForm){										
										if (validateForBROnly(theForm.problem.value, "Problem section is required.") && validateForBROnly(theForm.behaviorObjective.value, "Behavior Objective section is required.")  && validateForBROnly(theForm.offenderActionPlan.value, "Offender Action Plan section is required.") && validateForBROnly(theForm.csoActionPlan.value, "CSO Action Plan section is required.")){
											return true;
										}else {
											return false;
										}
									}