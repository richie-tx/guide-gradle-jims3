  // Passing in a boolean true for disableEnter will disable the Enter Key
  // Use in conjection with an event such as : onKeyDown="checkEnterKey(event,true)"
  function checkEnterKeyAndSubmit(evt,disableEnter)
  {
    var currentElement = evt.srcElement;
    var key = evt.keyCode;
  
    if(event.ctrlKey == true)
  	{
      if(key == 78)
    	{  // the n key
        alert("Sorry opening a new browser window is not allowed.");
        disableEvent(evt);
  
        return false;
      }	
    }
  
    if( disableEnter == true && key == 13 )
  	{
      if(currentElement != null)
  		{
        if(currentElement.type == 'textarea')
  			{
          // alert("Have a text area don't do anything with the enter key");
          return true;
        }
      }
  
      var foundBtn = getSubmitBtn();
    
      if(foundBtn == null)
    	{
        alert("This page has multiple submit buttons. \n\n  Please select appropriate submit button.");
        disableEvent(evt);
        return false;
      }
      else
    	{
        foundBtn.click();
        return false;
      }
    }
  
    return true;
  } 
   
  function disableEvent(evt)
  {
    evt.returnValue=false; 
    evt.cancel = true;
  }
   
  function getSubmitBtn()
	{
    var subBtnElems = document.getElementsByName("submitAction");
    var btnToFire = null;
    var counter = 0;

    if(subBtnElems == null)
  	{
      return null;
    }
    else
  	{
      for(i = 0; i < subBtnElems.length; i++) 
    	{
        if((subBtnElems[i].value.match("back")) == null && (subBtnElems[i].value.match("Back")) == null && 
          (subBtnElems[i].value.match("BACK")) == null &&  (subBtnElems[i].value.match("cancel")) == null && 
          (subBtnElems[i].value.match("Cancel")) == null && (subBtnElems[i].value.match("CANCEL")) == null && 
          (subBtnElems[i].value.match("reset")) == null && (subBtnElems[i].value.match("Reset")) == null && 
          (subBtnElems[i].value.match("RESET"))== null && (subBtnElems[i].value.match("refresh")) == null && 
          (subBtnElems[i].value.match("Refresh")) == null &&(subBtnElems[i].value.match("REFRESH")) == null )
      	{
          btnToFire = subBtnElems[i];
          counter++;
        }
      }// for
  
      if(counter != 1)
  		{
        return null;
      }
      else
  		{
        return btnToFire;
      }
    }// else
  }

	//this function alerts a message and then redirects to another page
	/*params: msg - message to be alerted
					  location - new page to be redirected to
	*/
	function alertAndGo(msg, location){
		alert(msg);
		window.location.href=location;
	}

	//this function redirects to another page
	/*params: location - new page to be redirected to
	*/
	function goNav(location)
	{
		if (location == "back"){
			history.go(-1);
			}else{
				window.location.href=location;
			}
	}


	//this function renders the tabs for the history area of the Juvenile Casework prototype
	/*params: activeTab - sets the active tab
	*/
	function renderTabs(activeTab)
	{
		document.write("<table border=0 cellpadding=0 cellspacing=0>");
		document.write("<tr>");

		if (activeTab == "MAIN"){
			document.write("<td bgcolor=#33cc66 valign=top><img src=../../images/left_green_active_tab.gif></td>");
			document.write("<td bgcolor=#33cc66 align=center><a href=caseWorkJuvenileProfileResultsDetail.htm class=topNav>MAIN</a></td>");
			document.write("<td bgcolor=#33cc66 valign=top><img src=../../images/right_green_active_tab.gif></td>");
		}else {
			document.write("<td bgcolor=#99ff99 valign=top><img src=../../images/left_green_inactive_tab.gif></td>");
			document.write("<td bgcolor=#99ff99 align=center><a href=caseWorkJuvenileProfileResultsDetail.htm class=topNav>MAIN</a></td>");
			document.write("<td bgcolor=#99ff99 valign=top><img src=../../images/right_green_inactive_tab.gif></td>");
		}

		document.write("<td valign=top><img src=../../images/spacer.gif width=2></td>");

		if (activeTab == "Case"){
		document.write("<td bgcolor=#33cc66 valign=top><img src=../../images/left_green_active_tab.gif></td>");
		document.write("<td bgcolor=#33cc66 align=center><a href=../caseTabCasefile/casefileDetails.htm class=topNav>Casefiles</a></td>");
		document.write("<td bgcolor=#33cc66 valign=top><img src=../../images/right_green_active_tab.gif></td>");
		}else {
			document.write("<td bgcolor=#99ff99 valign=top><img src=../../images/left_green_inactive_tab.gif></td>");
			document.write("<td bgcolor=#99ff99 align=center><a href=../caseTabCasefile/casefileDetails.htm class=topNav>Casefiles</a></td>");
			document.write("<td bgcolor=#99ff99 valign=top><img src=../../images/right_green_inactive_tab.gif></td>");
		}

		document.write("<td valign=top><img src=../../images/spacer.gif width=2></td>");

		if (activeTab == "Rules"){
		document.write("<td bgcolor=#33cc66 valign=top><img src=../../images/left_green_active_tab.gif></td>");
		document.write("<td bgcolor=#33cc66 align=center><a href=../caseTabRules/ruleList.htm class=topNav>Rules</a></td>");
		document.write("<td bgcolor=#33cc66 valign=top><img src=../../images/right_green_active_tab.gif></td>");
		}else {
			document.write("<td bgcolor=#99ff99 valign=top><img src=../../images/left_green_inactive_tab.gif></td>");
			document.write("<td bgcolor=#99ff99 align=center><a href=../caseTabRules/ruleList.htm class=topNav>Rules</a></td>");
			document.write("<td bgcolor=#99ff99 valign=top><img src=../../images/right_green_inactive_tab.gif></td>");
		}

		document.write("<td valign=top><img src=../../images/spacer.gif width=2></td>");

		if (activeTab == "Goals"){
		document.write("<td bgcolor=#33cc66 valign=top><img src=../../images/left_green_active_tab.gif></td>");
		document.write("<td bgcolor=#33cc66 align=center><a href=../caseTabGoals/goalList.htm class=topNav>Goals</a></td>");
		document.write("<td bgcolor=#33cc66 valign=top><img src=../../images/right_green_active_tab.gif></td>");
		}else {
			document.write("<td bgcolor=#99ff99 valign=top><img src=../../images/left_green_inactive_tab.gif></td>");
			document.write("<td bgcolor=#99ff99 align=center><a href=../caseTabGoals/goalList.htm class=topNav>Goals</a></td>");
			document.write("<td bgcolor=#99ff99 valign=top><img src=../../images/right_green_inactive_tab.gif></td>");
		}

		document.write("<td valign=top><img src=../../images/spacer.gif width=2></td>");

		if (activeTab == "Referral"){
		document.write("<td bgcolor=#33cc66 valign=top><img src=../../images/left_green_active_tab.gif></td>");
		document.write("<td bgcolor=#33cc66 align=center><a href=../caseTabReferrals/referralDetails.htm class=topNav>Referral</a></td>");
		document.write("<td bgcolor=#33cc66 valign=top><img src=../../images/right_green_active_tab.gif></td>");
		}else {
			document.write("<td bgcolor=#99ff99 valign=top><img src=../../images/left_green_inactive_tab.gif></td>");
			document.write("<td bgcolor=#99ff99 align=center><a href=../caseTabReferrals/referralDetails.htm class=topNav>Referral</a></td>");
			document.write("<td bgcolor=#99ff99 valign=top><img src=../../images/right_green_inactive_tab.gif></td>");
		}

		document.write("<td valign=top><img src=../../images/spacer.gif width=2></td>");

		if (activeTab == "Program Referral"){
		document.write("<td bgcolor=#33cc66 valign=top><img src=../../images/left_green_active_tab.gif></td>");
		document.write("<td bgcolor=#33cc66 align=center><a href=prSupervisionList.htm class=topNav>Program Referral</a></td>");
		document.write("<td bgcolor=#33cc66 valign=top><img src=../../images/right_green_active_tab.gif></td>");
		}else {
			document.write("<td bgcolor=#99ff99 valign=top><img src=../../images/left_green_inactive_tab.gif></td>");
			document.write("<td bgcolor=#99ff99 align=center><a href=prSupervisionList.htm class=topNav>Program Referral</a></td>");
			document.write("<td bgcolor=#99ff99 valign=top><img src=../../images/right_green_inactive_tab.gif></td>");
		}

		document.write("<td valign=top><img src=../../images/spacer.gif width=2></td>");

		/* id: JIMS200027064; headline: Remove Fees from MJCW, submitter: etran
		
		if (activeTab == "Fees"){
		document.write("<td bgcolor=#33cc66 valign=top><img src=../../images/left_green_active_tab.gif></td>");
		document.write("<td bgcolor=#33cc66 align=center><a href=feesList.htm class=topNav>Fees</a></td>");
		document.write("<td bgcolor=#33cc66 valign=top><img src=../../images/right_green_active_tab.gif></td>");
		}else {
			document.write("<td bgcolor=#99ff99 valign=top><img src=../../images/left_green_inactive_tab.gif></td>");
			document.write("<td bgcolor=#99ff99 align=center><a href=feesList.htm class=topNav>Fees</a></td>");
			document.write("<td bgcolor=#99ff99 valign=top><img src=../../images/right_green_inactive_tab.gif></td>");
		}
		*/

		document.write("<td valign=top><img src=../../images/spacer.gif width=2></td>");

		if (activeTab == "Traits"){
		document.write("<td bgcolor=#33cc66 valign=top><img src=../../images/left_green_active_tab.gif></td>");
		document.write("<td bgcolor=#33cc66 align=center><a href=traitsList.htm class=topNav>Traits</a></td>");
		document.write("<td bgcolor=#33cc66 valign=top><img src=../../images/right_green_active_tab.gif></td>");
		}else {
			document.write("<td bgcolor=#99ff99 valign=top><img src=../../images/left_green_inactive_tab.gif></td>");
			document.write("<td bgcolor=#99ff99 align=center><a href=traitsList.htm class=topNav>Traits</a></td>");
			document.write("<td bgcolor=#99ff99 valign=top><img src=../../images/right_green_inactive_tab.gif></td>");
		}

		document.write("<td valign=top><img src=../../images/spacer.gif width=2></td>");

		if (activeTab == "Interview Info"){
		document.write("<td bgcolor=#33cc66 valign=top><img src=../../images/left_green_active_tab.gif></td>");
		document.write("<td bgcolor=#33cc66 align=center><a href=interviewInfoMedical.htm class=topNav>Interview Info</a></td>");
		document.write("<td bgcolor=#33cc66 valign=top><img src=../../images/right_green_active_tab.gif></td>");
		}else {
			document.write("<td bgcolor=#99ff99 valign=top><img src=../../images/left_green_inactive_tab.gif></td>");
			document.write("<td bgcolor=#99ff99 align=center><a href=interviewInfoMedical.htm class=topNav>Interview Info</a></td>");
			document.write("<td bgcolor=#99ff99 valign=top><img src=../../images/right_green_inactive_tab.gif></td>");
		}

		document.write("</tr>");
		document.write("</table>");
	}

	//this function renders the tabs for the history area of the Juvenile Casework prototype
	/*params: activeTab - sets the active tab
	*/
	function renderCasefileTabs(activeTab)
	{
		document.write("<table border=0 cellpadding=0 cellspacing=0>");
		document.write("<tr>");

		if (activeTab == "Casefile"){
			document.write("<td bgcolor=#6699FF valign=top><img src=../../images/left_active_tab.gif></td>");
			document.write("<td bgcolor=#6699FF align=center><a href=../caseTabCasefile/casefileDetails.htm class=topNav>Casefile</a></td>");
			document.write("<td bgcolor=#6699FF valign=top><img src=../../images/right_active_tab.gif></td>");
		}else {
			document.write("<td bgcolor=#B3C9F5 valign=top><img src=../../images/left_inactive_tab.gif></td>");
			document.write("<td bgcolor=#B3C9F5 align=center><a href=../caseTabCasefile/casefileDetails.htm class=topNav>Casefile</a></td>");
			document.write("<td bgcolor=#B3C9F5 valign=top><img src=../../images/right_inactive_tab.gif></td>");
		}

		document.write("<td valign=top><img src=../../images/spacer.gif width=2></td>");

		if (activeTab == "Rules"){
		document.write("<td bgcolor=#6699FF valign=top><img src=../../images/left_active_tab.gif></td>");
		document.write("<td bgcolor=#6699FF align=center><a href=../caseTabRules/ruleList.htm class=topNav>Rules</a></td>");
		document.write("<td bgcolor=#6699FF valign=top><img src=../../images/right_active_tab.gif></td>");
		}else {
			document.write("<td bgcolor=#B3C9F5 valign=top><img src=../../images/left_inactive_tab.gif></td>");
			document.write("<td bgcolor=#B3C9F5 align=center><a href=../caseTabRules/ruleList.htm class=topNav>Rules</a></td>");
			document.write("<td bgcolor=#B3C9F5 valign=top><img src=../../images/right_inactive_tab.gif></td>");
		}

		document.write("<td valign=top><img src=../../images/spacer.gif width=2></td>");

		if (activeTab == "Goals"){
		document.write("<td bgcolor=#6699FF valign=top><img src=../../images/left_active_tab.gif></td>");
		document.write("<td bgcolor=#6699FF align=center><a href=../caseTabGoals/goalList.htm class=topNav>Goals</a></td>");
		document.write("<td bgcolor=#6699FF valign=top><img src=../../images/right_active_tab.gif></td>");
		}else {
			document.write("<td bgcolor=#B3C9F5 valign=top><img src=../../images/left_inactive_tab.gif></td>");
			document.write("<td bgcolor=#B3C9F5 align=center><a href=../caseTabGoals/goalList.htm class=topNav>Goals</a></td>");
			document.write("<td bgcolor=#B3C9F5 valign=top><img src=../../images/right_inactive_tab.gif></td>");
		}

		document.write("<td valign=top><img src=../../images/spacer.gif width=2></td>");

		if (activeTab == "Referral"){
		document.write("<td bgcolor=#6699FF valign=top><img src=../../images/left_active_tab.gif></td>");
		document.write("<td bgcolor=#6699FF align=center><a href=../caseTabAssignedReferrals/referralList.htm class=topNav>Assigned Referrals</a></td>");
		document.write("<td bgcolor=#6699FF valign=top><img src=../../images/right_active_tab.gif></td>");
		}else {
			document.write("<td bgcolor=#B3C9F5 valign=top><img src=../../images/left_inactive_tab.gif></td>");
			document.write("<td bgcolor=#B3C9F5 align=center><a href=../caseTabAssignedReferrals/referralList.htm class=topNav>Assigned Referrals</a></td>");
			document.write("<td bgcolor=#B3C9F5 valign=top><img src=../../images/right_inactive_tab.gif></td>");
		}

		document.write("<td valign=top><img src=../../images/spacer.gif width=2></td>");

		if (activeTab == "Calendar"){
		document.write("<td bgcolor=#6699FF valign=top><img src=../../images/left_active_tab.gif></td>");
		document.write("<td bgcolor=#6699FF align=center><a href=../caseTabProgramReferrals/programReferralList.htm class=topNav>Program Referral</a></td>");
		document.write("<td bgcolor=#6699FF valign=top><img src=../../images/right_active_tab.gif></td>");
		}else {
			document.write("<td bgcolor=#B3C9F5 valign=top><img src=../../images/left_inactive_tab.gif></td>");
			document.write("<td bgcolor=#B3C9F5 align=center><a href=../caseTabProgramReferrals/programReferralList.htm class=topNav>Program Referral</a></td>");
			document.write("<td bgcolor=#B3C9F5 valign=top><img src=../../images/right_inactive_tab.gif></td>");
		}

		document.write("</table>");
	}


	//this function renders the tabs for the close file closing area of the Juvenile Casework prototype - Juvenile Profile
	/*params: activeTab - sets the active tab
	*/
	function renderJuvenileProfileTabs(activeTab)
	{
		document.write("<table border=0 cellpadding=0 cellspacing=0>");
		document.write("<tr>");

		if (activeTab == "Casefile"){
			document.write("<td bgcolor=#6699FF valign=top><img src=../../common/images/left_active_tab.gif></td>");
			document.write("<td bgcolor=#6699FF align=center><a href=caseWorkCloseCasefileProfileCasefile.htm class=topNav>Casefile</a></td>");
			document.write("<td bgcolor=#6699FF valign=top><img src=../../common/images/right_active_tab.gif></td>");
		}else {
			document.write("<td bgcolor=#B3C9F5 valign=top><img src=../../common/images/left_inactive_tab.gif></td>");
			document.write("<td bgcolor=#B3C9F5 align=center><a href=caseWorkCloseCasefileProfileCasefile.htm class=topNav>Casefile</a></td>");
			document.write("<td bgcolor=#B3C9F5 valign=top><img src=../../common/images/right_inactive_tab.gif></td>");
		}

		document.write("<td valign=top><img src=../../common/images/spacer.gif width=2></td>");

		if (activeTab == "Rules"){
		document.write("<td bgcolor=#6699FF valign=top><img src=../../common/images/left_active_tab.gif></td>");
		document.write("<td bgcolor=#6699FF align=center><a href=caseWorkCloseCasefileProfileSupervision.htm class=topNav>Rules</a></td>");
		document.write("<td bgcolor=#6699FF valign=top><img src=../../common/images/right_active_tab.gif></td>");
		}else {
			document.write("<td bgcolor=#B3C9F5 valign=top><img src=../../common/images/left_inactive_tab.gif></td>");
			document.write("<td bgcolor=#B3C9F5 align=center><a href=caseWorkCloseCasefileProfileSupervision.htm class=topNav>Rules</a></td>");
			document.write("<td bgcolor=#B3C9F5 valign=top><img src=../../common/images/right_inactive_tab.gif></td>");
		}

		document.write("<td valign=top><img src=../../common/images/spacer.gif width=2></td>");

		if (activeTab == "Goals"){
		document.write("<td bgcolor=#6699FF valign=top><img src=../../common/images/left_active_tab.gif></td>");
		document.write("<td bgcolor=#6699FF align=center><a href=caseWorkCloseCasefileGoalList.htm class=topNav>Goals</a></td>");
		document.write("<td bgcolor=#6699FF valign=top><img src=../../common/images/right_active_tab.gif></td>");
		}else {
			document.write("<td bgcolor=#B3C9F5 valign=top><img src=../../common/images/left_inactive_tab.gif></td>");
			document.write("<td bgcolor=#B3C9F5 align=center><a href=caseWorkCloseCasefileGoalList.htm class=topNav>Goals</a></td>");
			document.write("<td bgcolor=#B3C9F5 valign=top><img src=../../common/images/right_inactive_tab.gif></td>");
		}

		document.write("<td valign=top><img src=../../common/images/spacer.gif width=2></td>");

		if (activeTab == "Referral"){
		document.write("<td bgcolor=#6699FF valign=top><img src=../../common/images/left_active_tab.gif></td>");
		document.write("<td bgcolor=#6699FF align=center><a href=caseWorkCloseCasefileProfileReferral.htm class=topNav>Assigned Referrals</a></td>");
		document.write("<td bgcolor=#6699FF valign=top><img src=../../common/images/right_active_tab.gif></td>");
		}else {
			document.write("<td bgcolor=#B3C9F5 valign=top><img src=../../common/images/left_inactive_tab.gif></td>");
			document.write("<td bgcolor=#B3C9F5 align=center><a href=caseWorkCloseCasefileProfileReferral.htm class=topNav>Assigned Referrals</a></td>");
			document.write("<td bgcolor=#B3C9F5 valign=top><img src=../../common/images/right_inactive_tab.gif></td>");
		}

		document.write("<td valign=top><img src=../../common/images/spacer.gif width=2></td>");

		if (activeTab == "Programs"){
		document.write("<td bgcolor=#6699FF valign=top><img src=../../common/images/left_active_tab.gif></td>");
		document.write("<td bgcolor=#6699FF align=center><a href=caseWorkCloseCasefileProfilePrograms.htm class=topNav>Program Referral</a></td>");
		document.write("<td bgcolor=#6699FF valign=top><img src=../../common/images/right_active_tab.gif></td>");
		}else {
			document.write("<td bgcolor=#B3C9F5 valign=top><img src=../../common/images/left_inactive_tab.gif></td>");
			document.write("<td bgcolor=#B3C9F5 align=center><a href=caseWorkCloseCasefileProfilePrograms.htm class=topNav>Program Referral</a></td>");
			document.write("<td bgcolor=#B3C9F5 valign=top><img src=../../common/images/right_inactive_tab.gif></td>");
		}

		document.write("</tr>");
		document.write("</table>");
	}


	//this function renders the tabs for the Conduct Interview area (right after the search results) of the Juvenile Casework prototype
	/*params: activeTab - sets the active tab
	*/
	function renderConductInterviewSetupTabs(activeTab)
	{
		document.write("<table border=0 cellpadding=0 cellspacing=0>");
		document.write("<tr>");

		if (activeTab == "Master"){
			document.write("<td bgcolor=#666666 valign=top><img src=../../common/images/left_grey_enabled_tab.gif></td>");
			document.write("<td bgcolor=#666666 align=center><a href=caseWorkConductInterviewIdInformationMaster.htm class=whiteTopNav>Master</a></td>");
			document.write("<td bgcolor=#666666 valign=top><img src=../../common/images/right_grey_enabled_tab.gif></td>");
		}else {
			document.write("<td bgcolor=#cccccc valign=top><img src=../../common/images/left_grey_disabled_tab.gif></td>");
			document.write("<td bgcolor=#cccccc align=center class=inactiveTab>Master</td>");
			document.write("<td bgcolor=#cccccc valign=top><img src=../../common/images/right_grey_disabled_tab.gif></td>");
		}

		document.write("<td valign=top><img src=../common/images/spacer.gif width=2></td>");

		if (activeTab == "Traits"){
		document.write("<td bgcolor=#666666 valign=top><img src=../../common/images/left_grey_enabled_tab.gif></td>");
		document.write("<td bgcolor=#666666 align=center><a href=caseWorkConductInterviewIdInformationTraits.htm class=whiteTopNav>Traits</a></td>");
		document.write("<td bgcolor=#666666 valign=top><img src=../../common/images/right_grey_enabled_tab.gif></td>");
		}else {
			document.write("<td bgcolor=#cccccc valign=top><img src=../../common/images/left_grey_disabled_tab.gif></td>");
			document.write("<td bgcolor=#cccccc align=center class=inactiveTab>Traits</td>");
			document.write("<td bgcolor=#cccccc valign=top><img src=../../common/images/right_grey_disabled_tab.gif></td>");
		}

		document.write("<td valign=top><img src=../common/images/spacer.gif width=2></td>");

		if (activeTab == "Physical Attributes"){
		document.write("<td bgcolor=#666666 valign=top><img src=../../common/images/left_grey_enabled_tab.gif></td>");
		document.write("<td bgcolor=#666666 align=center><a href=caseWorkConductInterviewIdInformationPhysicalAtt.htm class=whiteTopNav>Physical Characteristics</a></td>");
		document.write("<td bgcolor=#666666 valign=top><img src=../../common/images/right_grey_enabled_tab.gif></td>");
		}else {
			document.write("<td bgcolor=#cccccc valign=top><img src=../../common/images/left_grey_disabled_tab.gif></td>");
			document.write("<td bgcolor=#cccccc align=center class=inactiveTab>Physical Characteristics</td>");
			document.write("<td bgcolor=#cccccc valign=top><img src=../../common/images/right_grey_disabled_tab.gif></td>");
		}

		document.write("<td valign=top><img src=../common/images/spacer.gif width=2></td>");

		if (activeTab == "Contacts"){
		document.write("<td bgcolor=#666666 valign=top><img src=../../common/images/left_grey_enabled_tab.gif></td>");
		document.write("<td bgcolor=#666666 align=center><a href=caseWorkConductInterviewContactInformation.htm class=whiteTopNav>Contacts</a></td>");
		document.write("<td bgcolor=#666666 valign=top><img src=../../common/images/right_grey_enabled_tab.gif></td>");
		}else {
			document.write("<td bgcolor=#cccccc valign=top><img src=../../common/images/left_grey_disabled_tab.gif></td>");
			document.write("<td bgcolor=#cccccc align=center class=inactiveTab>Contacts</td>");
			document.write("<td bgcolor=#cccccc valign=top><img src=../../common/images/right_grey_disabled_tab.gif></td>");
		}
		document.write("</tr>");
		document.write("</table>");
	}

	//this function renders the tabs for the Casefile Closing Court - Common Application area of the Juvenile Casework prototype
	/*params: activeTab - sets the active tab
	*/
	function renderCommonAppTabs(activeTab)
	{
		document.write("<table border=0 cellpadding=0 cellspacing=0>");
		document.write("<tr>");

		if (activeTab == "Abuse"){
		document.write("<td bgcolor=#666666 valign=top><img src=../../common/images/left_grey_enabled_tab.gif></td>");
		document.write("<td bgcolor=#666666 align=center><a href=caseWorkCloseCasefileCommonAppAbuse.htm class=whiteTopNav>Abuse</a></td>");
		document.write("<td bgcolor=#666666 valign=top><img src=../../common/images/right_grey_enabled_tab.gif></td>");
		}else {
			document.write("<td bgcolor=#cccccc valign=top><img src=../../common/images/left_grey_disabled_tab.gif></td>");
			document.write("<td bgcolor=#cccccc align=center class=inactiveTab>Abuse</td>");
			document.write("<td bgcolor=#cccccc valign=top><img src=../../common/images/right_grey_disabled_tab.gif></td>");
		}

		document.write("<td valign=top><img src=../common/images/spacer.gif width=2></td>");

		if (activeTab == "Health"){
		document.write("<td bgcolor=#666666 valign=top><img src=../../common/images/left_grey_enabled_tab.gif></td>");
		document.write("<td bgcolor=#666666 align=center><a href=caseWorkCloseCasefileCommonAppHealth.htm class=whiteTopNav>Health</a></td>");
		document.write("<td bgcolor=#666666 valign=top><img src=../../common/images/right_grey_enabled_tab.gif></td>");
		}else {
			document.write("<td bgcolor=#cccccc valign=top><img src=../../common/images/left_grey_disabled_tab.gif></td>");
			document.write("<td bgcolor=#cccccc align=center class=inactiveTab>Health</td>");
			document.write("<td bgcolor=#cccccc valign=top><img src=../../common/images/right_grey_disabled_tab.gif></td>");
		}

		document.write("<td valign=top><img src=../common/images/spacer.gif width=2></td>");

		if (activeTab == "Medication"){
		document.write("<td bgcolor=#666666 valign=top><img src=../../common/images/left_grey_enabled_tab.gif></td>");
		document.write("<td bgcolor=#666666 align=center><a href=caseWorkCloseCasefileCommonAppMedication.htm class=whiteTopNav>Medication</a></td>");
		document.write("<td bgcolor=#666666 valign=top><img src=../../common/images/right_grey_enabled_tab.gif></td>");
		}else {
			document.write("<td bgcolor=#cccccc valign=top><img src=../../common/images/left_grey_disabled_tab.gif></td>");
			document.write("<td bgcolor=#cccccc align=center class=inactiveTab>Medication</td>");
			document.write("<td bgcolor=#cccccc valign=top><img src=../../common/images/right_grey_disabled_tab.gif></td>");
		}

		document.write("<td valign=top><img src=../common/images/spacer.gif width=2></td>");

		if (activeTab == "Hospitalization"){
		document.write("<td bgcolor=#666666 valign=top><img src=../../common/images/left_grey_enabled_tab.gif></td>");
		document.write("<td bgcolor=#666666 align=center><a href=caseWorkCloseCasefileCommonAppHospitalization.htm class=whiteTopNav>Hospitalization</a></td>");
		document.write("<td bgcolor=#666666 valign=top><img src=../../common/images/right_grey_enabled_tab.gif></td>");
		}else {
			document.write("<td bgcolor=#cccccc valign=top><img src=../../common/images/left_grey_disabled_tab.gif></td>");
			document.write("<td bgcolor=#cccccc align=center class=inactiveTab>Hospitalization</td>");
			document.write("<td bgcolor=#cccccc valign=top><img src=../../common/images/right_grey_disabled_tab.gif></td>");
		}

		document.write("<td valign=top><img src=../common/images/spacer.gif width=2></td>");

		if (activeTab == "Family Status"){
		document.write("<td bgcolor=#666666 valign=top><img src=../../common/images/left_grey_enabled_tab.gif></td>");
		document.write("<td bgcolor=#666666 align=center><a href=caseWorkCloseCasefileCommonAppFamilyStatus.htm class=whiteTopNav>Family Status</a></td>");
		document.write("<td bgcolor=#666666 valign=top><img src=../../common/images/right_grey_enabled_tab.gif></td>");
		}else {
			document.write("<td bgcolor=#cccccc valign=top><img src=../../common/images/left_grey_disabled_tab.gif></td>");
			document.write("<td bgcolor=#cccccc align=center class=inactiveTab>Family Status</td>");
			document.write("<td bgcolor=#cccccc valign=top><img src=../../common/images/right_grey_disabled_tab.gif></td>");
		}

		document.write("<td valign=top><img src=../common/images/spacer.gif width=2></td>");

		if (activeTab == "Family"){
		document.write("<td bgcolor=#666666 valign=top><img src=../../common/images/left_grey_enabled_tab.gif></td>");
		document.write("<td bgcolor=#666666 align=center><a href=caseWorkCloseCasefileCommonAppFamily.htm class=whiteTopNav>Family</a></td>");
		document.write("<td bgcolor=#666666 valign=top><img src=../../common/images/right_grey_enabled_tab.gif></td>");
		}else {
			document.write("<td bgcolor=#cccccc valign=top><img src=../../common/images/left_grey_disabled_tab.gif></td>");
			document.write("<td bgcolor=#cccccc align=center class=inactiveTab>Family</td>");
			document.write("<td bgcolor=#cccccc valign=top><img src=../../common/images/right_grey_disabled_tab.gif></td>");
		}

		document.write("<td valign=top><img src=../common/images/spacer.gif width=2></td>");

		if (activeTab == "School"){
		document.write("<td bgcolor=#666666 valign=top><img src=../../common/images/left_grey_enabled_tab.gif></td>");
		document.write("<td bgcolor=#666666 align=center><a href=caseWorkCloseCasefileCommonAppSchool.htm class=whiteTopNav>School</a></td>");
		document.write("<td bgcolor=#666666 valign=top><img src=../../common/images/right_grey_enabled_tab.gif></td>");
		}else {
			document.write("<td bgcolor=#cccccc valign=top><img src=../../common/images/left_grey_disabled_tab.gif></td>");
			document.write("<td bgcolor=#cccccc align=center class=inactiveTab>School</td>");
			document.write("<td bgcolor=#cccccc valign=top><img src=../../common/images/right_grey_disabled_tab.gif></td>");
		}

		document.write("</tr>");
		document.write("</table>");
	}

	//this function prints the common section of the Juvenile Header Information
	/*params:
	*/
	function JuvHeaderInfo(){
		document.write("<table width=100% border=0 cellpadding=2 cellspacing=1>");
		document.write("<tr class=bodyText>");
		document.write("<td align=right bgcolor=#f0f0f0 valign=top class=headerLabel>Supervision #</td>");
		document.write("<td align=left bgcolor=#ffffff valign=top class=headerData>0000121212</td>");
		document.write("<td align=right bgcolor=#f0f0f0 valign=top class=headerLabel>Probation Officer</td>");
		document.write("<td align=left bgcolor=#ffffff valign=top colspan=3 class=headerData>Smith, Joe</td>");
		document.write("</tr>");
		document.write("<tr class=bodyText>");
		document.write("<td align=right bgcolor=#f0f0f0 valign=top class=headerLabel>Juvenile #</td>");
		document.write("<td align=left bgcolor=#ffffff valign=top class=headerData>1111111</td>");
		document.write("<td align=right bgcolor=#f0f0f0 valign=top class=headerLabel>Juvenile Name</td>");
		document.write("<td align=left bgcolor=#ffffff valign=top colspan=3 class=headerData>James, James Raymond</td>");
		document.write("</tr>");
		document.write("<tr class=bodyText>");
		document.write("<td align=right bgcolor=#f0f0f0 valign=top class=headerLabel>Supervision Type</td>");
		document.write("<td align=left bgcolor=#ffffff valign=top class=headerData>Intake Screening</td>");
		document.write("<td align=right bgcolor=#f0f0f0 valign=top class=headerLabel>Case Status</td>");
		document.write("<td align=left bgcolor=#ffffff valign=top class=headerData>Active</td>");
		document.write("<td align=right bgcolor=#f0f0f0 valign=top class=headerLabel>Expected Supervision End Date</td>");
		document.write("<td align=left bgcolor=#ffffff valign=top class=headerData>12/12/04</td>");
		document.write("</tr>");
		document.write("<tr class=bodyText>");
		document.write("<td align=right bgcolor=#f0f0f0 valign=top class=headerLabel>Current Age</td>");
		document.write("<td align=left bgcolor=#ffffff valign=top class=headerData>12</td>");
		document.write("<td align=right bgcolor=#f0f0f0 valign=top class=headerLabel>Race</td>");
		document.write("<td align=left bgcolor=#ffffff valign=top class=headerData>White</td>");
		document.write("<td align=right bgcolor=#f0f0f0 valign=top class=headerLabel>Sex</td>");
		document.write("<td align=left bgcolor=#ffffff valign=top class=headerData>Male</td>");
		document.write("</tr>");
		document.write("</table>");
	}

	//this function prints the common buttons in the Casefile Area
	/*params:
	*/

	function renderCasefileButtons(){
		document.write("<table align=center cellpadding=6 class=borderTableGrey>");
		document.write("<tr>");
		document.write("<td><img src=../../common/images/blue_arrow.gif hspace=4 align=middle><a href=../scheduleAdmonishmentHearing/admonishmentHearing.htm>Admonishment Hearing</a></td>");
		document.write("<td><img src=../../common/images/blue_arrow.gif hspace=4 align=middle><a href=../financialLetter.htm>Send Financial Letter</a></td>");
		document.write("<td><img src=../../common/images/blue_arrow.gif hspace=4 align=middle><a href=../caseworkSupervisionStandardAssign.htm>Assign Rules</a></td>");
		document.write("<td><img src=../../common/images/blue_arrow.gif hspace=4 align=middle><a href=../calendarEvent/caseWorkCalendarCaseFileDetails.htm>Calendar</a></td>");
		document.write("</tr>");
		document.write("<tr>");
		document.write("<td><img src=../../common/images/blue_arrow.gif hspace=4 align=middle><a href=../conductInterview/caseWorkConductInterviewCaseFileDetails.htm>Conduct Interview</a></td>");
		document.write("<td><img src=../../common/images/blue_arrow.gif hspace=4 align=middle><a href=../processCasePlan/caseWorkCasePlanCaseFileDetails.htm>Process Caseplan</a></td>");
		document.write("<td><img src=../../common/images/blue_arrow.gif hspace=4 align=middle><a href=../closeCasefile/caseWorkCloseCasefileProfileCasefile.htm>Process Casefile Closing</a></td>");
		document.write("<td><img src=../../common/images/blue_arrow.gif hspace=4 align=middle><a href=../recordMentalHealthAssessment/mentalHealthAssessList.htm>MAYSI</a></td>");
		document.write("</tr>");
		document.write("<tr>");
		document.write("<td><img src=../../common/images/blue_arrow.gif hspace=4 align=middle><a href=../drugScreening.htm>Drug Screening</a></td>");
		document.write("<td><img src=../../common/images/blue_arrow.gif hspace=4 align=middle><a href=../benefitsAssessment/benefitsAssessment.htm>Benefits Assessment</a></td>");
		document.write("<td><img src=../../common/images/blue_arrow.gif hspace=4 align=middle><a href=../riskAnalysis.htm>Risk Analysis</a></td>");
		document.write("<td><img src=../../common/images/blue_arrow.gif hspace=4 align=middle><a href=../juvenileProfile/caseWorkJuvenileProfileResultsDetail.htm>Juvenile Profile</a></td>");
		document.write("</tr>");
		document.write("</table>");
	}

	//this function renders the question tabs for the conduct interview area of the Juvenile Casework prototype
	/*params: activeTab - sets the active tab
	*/
	function renderInterviewTabs(activeTab)
	{
		document.write("<table border=0 cellpadding=0 cellspacing=0>");
		document.write("<tr>");

		if (activeTab == "Medical"){
			document.write("<td bgcolor=#666666 valign=top><img src=../../common/images/left_grey_enabled_tab.gif></td>");
			document.write("<td bgcolor=#666666 align=center class=inactiveTab><a href='caseWorkConductInterviewMedical.htm' class=whiteTopNav>Medical</a></td>");
			document.write("<td bgcolor=#666666 valign=top><img src=../../common/images/right_grey_enabled_tab.gif></td>");
		}else {
			document.write("<td bgcolor=#cccccc valign=top><img src=../../common/images/left_grey_disabled_tab.gif></td>");
			document.write("<td bgcolor=#cccccc align=center class=inactiveTab>Medical</td>");
			document.write("<td bgcolor=#cccccc valign=top><img src=../../common/images/right_grey_disabled_tab.gif></td>");
		}

		document.write("<td valign=top><img src=../../common/images/spacer.gif width=2></td>");

		if (activeTab == "Gang"){
			document.write("<td bgcolor=#666666 valign=top><img src=../../common/images/left_grey_enabled_tab.gif></td>");
			document.write("<td bgcolor=#666666 align=center class=inactiveTab><a href='caseWorkConductInterviewGang.htm' class=whiteTopNav>Gang</a></td>");
			document.write("<td bgcolor=#666666 valign=top><img src=../../common/images/right_grey_enabled_tab.gif></td>");
		}else {
			document.write("<td bgcolor=#cccccc valign=top><img src=../../common/images/left_grey_disabled_tab.gif></td>");
			document.write("<td bgcolor=#cccccc align=center class=inactiveTab>Gang</td>");
			document.write("<td bgcolor=#cccccc valign=top><img src=../../common/images/right_grey_disabled_tab.gif></td>");
		}

		document.write("<td valign=top><img src=../../common/images/spacer.gif width=2></td>");

		if (activeTab == "Drugs"){
			document.write("<td bgcolor=#666666 valign=top><img src=../../common/images/left_grey_enabled_tab.gif></td>");
			document.write("<td bgcolor=#666666 align=center class=inactiveTab><a href='caseWorkConductInterviewDrugs.htm' class=whiteTopNav>Drugs</a></td>");
			document.write("<td bgcolor=#666666 valign=top><img src=../../common/images/right_grey_enabled_tab.gif></td>");
		}else {
			document.write("<td bgcolor=#cccccc valign=top><img src=../../common/images/left_grey_disabled_tab.gif></td>");
			document.write("<td bgcolor=#cccccc align=center class=inactiveTab>Drugs</td>");
			document.write("<td bgcolor=#cccccc valign=top><img src=../../common/images/right_grey_disabled_tab.gif></td>");
		}

		document.write("<td valign=top><img src=../../common/images/spacer.gif width=2></td>");

		if (activeTab == "Family"){
			document.write("<td bgcolor=#666666 valign=top><img src=../../common/images/left_grey_enabled_tab.gif></td>");
			document.write("<td bgcolor=#666666 align=center class=inactiveTab><a href='caseWorkConductInterviewFamily1stPage.htm' class=whiteTopNav>Family</a></td>");
			document.write("<td bgcolor=#666666 valign=top><img src=../../common/images/right_grey_enabled_tab.gif></td>");
		}else {
			document.write("<td bgcolor=#cccccc valign=top><img src=../../common/images/left_grey_disabled_tab.gif></td>");
			document.write("<td bgcolor=#cccccc align=center class=inactiveTab>Family</td>");
			document.write("<td bgcolor=#cccccc valign=top><img src=../../common/images/right_grey_disabled_tab.gif></td>");
		}

		document.write("<td valign=top><img src=../../common/images/spacer.gif width=2></td>");

		if (activeTab == "School"){
			document.write("<td bgcolor=#666666 valign=top><img src=../../common/images/left_grey_enabled_tab.gif></td>");
			document.write("<td bgcolor=#666666 align=center class=inactiveTab><a href='caseWorkConductInterviewSchool.htm' class=whiteTopNav>School</a></td>");
			document.write("<td bgcolor=#666666 valign=top><img src=../../common/images/right_grey_enabled_tab.gif></td>");
		}else {
			document.write("<td bgcolor=#cccccc valign=top><img src=../../common/images/left_grey_disabled_tab.gif></td>");
			document.write("<td bgcolor=#cccccc align=center class=inactiveTab>School</td>");
			document.write("<td bgcolor=#cccccc valign=top><img src=../../common/images/right_grey_disabled_tab.gif></td>");
		}

		document.write("<td valign=top><img src=../../common/images/spacer.gif width=2></td>");

		if (activeTab == "Special Interests"){
			document.write("<td bgcolor=#666666 valign=top><img src=../../common/images/left_grey_enabled_tab.gif></td>");
			document.write("<td bgcolor=#666666 align=center class=inactiveTab><a href='caseWorkConductInterviewSpecialInterest.htm' class=whiteTopNav>Special Interest</a></td>");
			document.write("<td bgcolor=#666666 valign=top><img src=../../common/images/right_grey_enabled_tab.gif></td>");
		}else {
			document.write("<td bgcolor=#cccccc valign=top><img src=../../common/images/left_grey_disabled_tab.gif></td>");
			document.write("<td bgcolor=#cccccc align=center class=inactiveTab>Special Interest</td>");
			document.write("<td bgcolor=#cccccc valign=top><img src=../../common/images/right_grey_disabled_tab.gif></td>");
		}

		document.write("<td valign=top><img src=../../common/images/spacer.gif width=2></td>");

		if (activeTab == "Abuse"){
			document.write("<td bgcolor=#666666 valign=top><img src=../../common/images/left_grey_enabled_tab.gif></td>");
			document.write("<td bgcolor=#666666 align=center class=inactiveTab><a href='caseWorkConductInterviewAbuse.htm' class=whiteTopNav>Abuse</a></td>");
			document.write("<td bgcolor=#666666 valign=top><img src=../../common/images/right_grey_enabled_tab.gif></td>");
		}else {
			document.write("<td bgcolor=#cccccc valign=top><img src=../../common/images/left_grey_disabled_tab.gif></td>");
			document.write("<td bgcolor=#cccccc align=center class=inactiveTab>Abuse</td>");
			document.write("<td bgcolor=#cccccc valign=top><img src=../../common/images/right_grey_disabled_tab.gif></td>");
		}

		document.write("<td valign=top><img src=../../common/images/spacer.gif width=2></td>");

		if (activeTab == "Mental"){
			document.write("<td bgcolor=#666666 valign=top><img src=../../common/images/left_grey_enabled_tab.gif></td>");
			document.write("<td bgcolor=#666666 align=center class=inactiveTab><a href='caseWorkConductInterviewMentalHealth.htm' class=whiteTopNav>Mental Health</a></td>");
			document.write("<td bgcolor=#666666 valign=top><img src=../../common/images/right_grey_enabled_tab.gif></td>");
		}else {
			document.write("<td bgcolor=#cccccc valign=top><img src=../../common/images/left_grey_disabled_tab.gif></td>");
			document.write("<td bgcolor=#cccccc align=center class=inactiveTab>Mental Health</td>");
			document.write("<td bgcolor=#cccccc valign=top><img src=../../common/images/right_grey_disabled_tab.gif></td>");
		}

		document.write("<td valign=top><img src=../../common/images/spacer.gif width=2></td>");

		if (activeTab == "Jobs"){
			document.write("<td bgcolor=#666666 valign=top><img src=../../common/images/left_grey_enabled_tab.gif></td>");
			document.write("<td bgcolor=#666666 align=center class=inactiveTab><a href='caseWorkConductInterviewJobs.htm' class=whiteTopNav>Jobs</a></td>");
			document.write("<td bgcolor=#666666 valign=top><img src=../../common/images/right_grey_enabled_tab.gif></td>");
		}else {
			document.write("<td bgcolor=#cccccc valign=top><img src=../../common/images/left_grey_disabled_tab.gif></td>");
			document.write("<td bgcolor=#cccccc align=center class=inactiveTab>Jobs</td>");
			document.write("<td bgcolor=#cccccc valign=top><img src=../../common/images/right_grey_disabled_tab.gif></td>");
		}

		document.write("<td valign=top><img src=../../common/images/spacer.gif width=2></td>");

		if (activeTab == "Benefits"){
			document.write("<td bgcolor=#666666 valign=top><img src=../../common/images/left_grey_enabled_tab.gif></td>");
			document.write("<td bgcolor=#666666 align=center class=inactiveTab><a href='caseWorkConductInterviewBenefits.htm' class=whiteTopNav>Benefits</a></td>");
			document.write("<td bgcolor=#666666 valign=top><img src=../../common/images/right_grey_enabled_tab.gif></td>");
		}else {
			document.write("<td bgcolor=#cccccc valign=top><img src=../../common/../common/images/left_grey_disabled_tab.gif></td>");
			document.write("<td bgcolor=#cccccc align=center class=inactiveTab>Benefits</td>");
			document.write("<td bgcolor=#cccccc valign=top><img src=../../common/../common/images/right_grey_disabled_tab.gif></td>");
		}

		document.write("<td valign=top><img src=../../common/../common/images/spacer.gif width=2></td>");

		document.write("</tr>");
		document.write("</table>");
	}

	//this function renders the tabs for the interview information area under the Juvenile Profile area of the Juvenile Casework prototype
	/*params: activeTab - sets the active tab
	*/
	function renderInterviewInfo(activeTab)
	{
		document.write("<table border=0 cellpadding=0 cellspacing=0>");
		document.write("<tr>");

		if (activeTab == "Medical"){
		document.write("<td bgcolor=#6699FF valign=top><img src=../../common/images/left_active_tab.gif></td>");
		document.write("<td bgcolor=#6699FF align=center><a href=interviewInfoMedical.htm class=topNav>Medical</a></td>");
		document.write("<td bgcolor=#6699FF valign=top><img src=../../common/images/right_active_tab.gif></td>");
		}else {
			document.write("<td bgcolor=#B3C9F5 valign=top><img src=../../common/images/left_inactive_tab.gif></td>");
			document.write("<td bgcolor=#B3C9F5 align=center><a href=interviewInfoMedical.htm class=topNav>Medical</a></td>");
			document.write("<td bgcolor=#B3C9F5 valign=top><img src=../../common/images/right_inactive_tab.gif></td>");
		}

		document.write("<td valign=top><img src=../../common/images/spacer.gif width=2></td>");

		if (activeTab == "Drugs"){
		document.write("<td bgcolor=#6699FF valign=top><img src=../../common/images/left_active_tab.gif></td>");
		document.write("<td bgcolor=#6699FF align=center><a href=interviewInfoDrugs.htm class=topNav>Drugs</a></td>");
		document.write("<td bgcolor=#6699FF valign=top><img src=../../common/images/right_active_tab.gif></td>");
		}else {
			document.write("<td bgcolor=#B3C9F5 valign=top><img src=../../common/images/left_inactive_tab.gif></td>");
			document.write("<td bgcolor=#B3C9F5 align=center><a href=interviewInfoDrugs.htm class=topNav>Drugs</a></td>");
			document.write("<td bgcolor=#B3C9F5 valign=top><img src=../../common/images/right_inactive_tab.gif></td>");
		}

		document.write("<td valign=top><img src=../../common/images/spacer.gif width=2></td>");

		if (activeTab == "Family"){
		document.write("<td bgcolor=#6699FF valign=top><img src=../../common/images/left_active_tab.gif></td>");
		document.write("<td bgcolor=#6699FF align=center><a href=familyConstellation.htm class=topNav>Family</a></td>");
		document.write("<td bgcolor=#6699FF valign=top><img src=../../common/images/right_active_tab.gif></td>");
		}else {
			document.write("<td bgcolor=#B3C9F5 valign=top><img src=../../common/images/left_inactive_tab.gif></td>");
			document.write("<td bgcolor=#B3C9F5 align=center><a href=familyConstellation.htm class=topNav>Family</a></td>");
			document.write("<td bgcolor=#B3C9F5 valign=top><img src=../../common/images/right_inactive_tab.gif></td>");
		}

		document.write("<td valign=top><img src=../../common/images/spacer.gif width=2></td>");

		if (activeTab == "School"){
		document.write("<td bgcolor=#6699FF valign=top><img src=../../common/images/left_active_tab.gif></td>");
		document.write("<td bgcolor=#6699FF align=center><a href=interviewInfoSchool.htm class=topNav>School</a></td>");
		document.write("<td bgcolor=#6699FF valign=top><img src=../../common/images/right_active_tab.gif></td>");
		}else {
			document.write("<td bgcolor=#B3C9F5 valign=top><img src=../../common/images/left_inactive_tab.gif></td>");
			document.write("<td bgcolor=#B3C9F5 align=center><a href=interviewInfoSchool.htm class=topNav>School</a></td>");
			document.write("<td bgcolor=#B3C9F5 valign=top><img src=../../common/images/right_inactive_tab.gif></td>");
		}

		document.write("<td valign=top><img src=../../common/images/spacer.gif width=2></td>");

		if (activeTab == "Abuse"){
		document.write("<td bgcolor=#6699FF valign=top><img src=../../common/images/left_active_tab.gif></td>");
		document.write("<td bgcolor=#6699FF align=center><a href=abuseList.htm class=topNav>Abuse</a></td>");
		document.write("<td bgcolor=#6699FF valign=top><img src=../../common/images/right_active_tab.gif></td>");
		}else {
			document.write("<td bgcolor=#B3C9F5 valign=top><img src=../../common/images/left_inactive_tab.gif></td>");
			document.write("<td bgcolor=#B3C9F5 align=center><a href=abuseList.htm class=topNav>Abuse</a></td>");
			document.write("<td bgcolor=#B3C9F5 valign=top><img src=../../common/images/right_inactive_tab.gif></td>");
		}

		document.write("<td valign=top><img src=../../common/images/spacer.gif width=2></td>");

		if (activeTab == "Mental Health"){
		document.write("<td bgcolor=#6699FF valign=top><img src=../../common/images/left_active_tab.gif></td>");
		document.write("<td bgcolor=#6699FF align=center><a href=mentalHealth.htm class=topNav>Mental Health</a></td>");
		document.write("<td bgcolor=#6699FF valign=top><img src=../../common/images/right_active_tab.gif></td>");
		}else {
			document.write("<td bgcolor=#B3C9F5 valign=top><img src=../../common/images/left_inactive_tab.gif></td>");
			document.write("<td bgcolor=#B3C9F5 align=center><a href=mentalHealth.htm class=topNav>Mental Health</a></td>");
			document.write("<td bgcolor=#B3C9F5 valign=top><img src=../../common/images/right_inactive_tab.gif></td>");
		}

		document.write("<td valign=top><img src=../../common/images/spacer.gif width=2></td>");

		if (activeTab == "Jobs"){
		document.write("<td bgcolor=#6699FF valign=top><img src=../../common/images/left_active_tab.gif></td>");
		document.write("<td bgcolor=#6699FF align=center><a href=jobs.htm class=topNav>Jobs</a></td>");
		document.write("<td bgcolor=#6699FF valign=top><img src=../../common/images/right_active_tab.gif></td>");
		}else {
			document.write("<td bgcolor=#B3C9F5 valign=top><img src=../../common/images/left_inactive_tab.gif></td>");
			document.write("<td bgcolor=#B3C9F5 align=center><a href=jobs.htm class=topNav>Jobs</a></td>");
			document.write("<td bgcolor=#B3C9F5 valign=top><img src=../../common/images/right_inactive_tab.gif></td>");
		}

		document.write("<td valign=top><img src=../../common/images/spacer.gif width=2></td>");

		if (activeTab == "Benefits"){
		document.write("<td bgcolor=#6699FF valign=top><img src=../../common/images/left_active_tab.gif></td>");
		document.write("<td bgcolor=#6699FF align=center><a href=benefitsList.htm class=topNav>Benefits</a></td>");
		document.write("<td bgcolor=#6699FF valign=top><img src=../../common/images/right_active_tab.gif></td>");
		}else {
			document.write("<td bgcolor=#B3C9F5 valign=top><img src=../../common/images/left_inactive_tab.gif></td>");
			document.write("<td bgcolor=#B3C9F5 align=center><a href=beefitsList.htm class=topNav>Benefits</a></td>");
			document.write("<td bgcolor=#B3C9F5 valign=top><img src=../../common/images/right_inactive_tab.gif></td>");
		}

		document.write("</tr>");
		document.write("</table>");
	}

	//this function renders the tabs for the test results under the Program Referral Tab in Juvenile Casefile
	/*params: activeTab - sets the active tab
	*/
	function renderTestResultsTabs(activeTab)
	{
		document.write("<table border=0 cellpadding=0 cellspacing=0>");
		document.write("<tr>");

		if (activeTab == "JT"){
		document.write("<td bgcolor=#ff6666 valign=top><img src=../images/left_red_active_tab.gif></td>");
		document.write("<td bgcolor=#ff6666 align=center><a href=jtTestResults.htm class=topNav>Testing Results</a></td>");
		document.write("<td bgcolor=#ff6666 valign=top><img src=../images/right_red_active_tab.gif></td>");
		}else {
			document.write("<td bgcolor=#ffcccc valign=top><img src=../images/left_red_inactive_tab.gif></td>");
			document.write("<td bgcolor=#ffcccc align=center><a href=jtTestResults.htm class=topNav>Testing Results</a></td>");
			document.write("<td bgcolor=#ffcccc valign=top><img src=../images/right_red_inactive_tab.gif></td>");
		}

		document.write("<td valign=top><img src=../images/spacer.gif width=2></td>");

		if (activeTab == "DSM"){
		document.write("<td bgcolor=#ff6666 valign=top><img src=../images/left_red_active_tab.gif></td>");
		document.write("<td bgcolor=#ff6666 align=center><a href=testResults.htm class=topNav>DSM IV</a></td>");
		document.write("<td bgcolor=#ff6666 valign=top><img src=../images/right_red_active_tab.gif></td>");
		}else {
			document.write("<td bgcolor=#ffcccc valign=top><img src=../images/left_red_inactive_tab.gif></td>");
			document.write("<td bgcolor=#ffcccc align=center><a href=testResults.htm class=topNav>DSM IV</a></td>");
			document.write("<td bgcolor=#ffcccc valign=top><img src=../images/right_red_inactive_tab.gif></td>");
		}

		document.write("<td valign=top><img src=../images/spacer.gif width=2></td>");

		if (activeTab == "IQ"){
		document.write("<td bgcolor=#ff6666 valign=top><img src=../images/left_red_active_tab.gif></td>");
		document.write("<td bgcolor=#ff6666 align=center><a href=IQtestResults.htm class=topNav>  IQ  </a></td>");
		document.write("<td bgcolor=#ff6666 valign=top><img src=../images/right_red_active_tab.gif></td>");
		}else {
			document.write("<td bgcolor=#ffcccc valign=top><img src=../images/left_red_inactive_tab.gif></td>");
			document.write("<td bgcolor=#ffcccc align=center><a href=IQtestResults.htm class=topNav>  IQ  </a></td>");
			document.write("<td bgcolor=#ffcccc valign=top><img src=../images/right_red_inactive_tab.gif></td>");
		}

		document.write("<td valign=top><img src=../images/spacer.gif width=2></td>");

		if (activeTab == "AT"){
		document.write("<td bgcolor=#ff6666 valign=top><img src=../images/left_red_active_tab.gif></td>");
		document.write("<td bgcolor=#ff6666 align=center><a href=ATtestResults.htm class=topNav>Achievement</a></td>");
		document.write("<td bgcolor=#ff6666 valign=top><img src=../images/right_red_active_tab.gif></td>");
		}else {
			document.write("<td bgcolor=#ffcccc valign=top><img src=../images/left_red_inactive_tab.gif></td>");
			document.write("<td bgcolor=#ffcccc align=center><a href=ATtestResults.htm class=topNav>Achievement</a></td>");
			document.write("<td bgcolor=#ffcccc valign=top><img src=../images/right_red_inactive_tab.gif></td>");
		}

		document.write("<td valign=top><img src=../images/spacer.gif width=2></td>");

		if (activeTab == "AF"){
			document.write("<td bgcolor=#ff6666 valign=top><img src=../images/left_red_active_tab.gif></td>");
			document.write("<td bgcolor=#ff6666 align=center><a href=AFtestResults.htm class=topNav>Adaptive Functioning</a></td>");
			document.write("<td bgcolor=#ff6666 valign=top><img src=../images/right_red_active_tab.gif></td>");
		}else {
			document.write("<td bgcolor=#ffcccc valign=top><img src=../images/left_red_inactive_tab.gif></td>");
			document.write("<td bgcolor=#ffcccc align=center><a href=AFtestResults.htm class=topNav>Adaptive Functioning</a></td>");
			document.write("<td bgcolor=#ffcccc valign=top><img src=../images/right_red_inactive_tab.gif></td>");
		}

		document.write("</tr>");
		document.write("</table>");
	}

	//changes the background color of the row where the checkbox is
	//row has incrementing number Id, and checkbox has same value
	//param: chk - the checkbox form object
	function chooseRow(chk) {
		//alternating row scriptlet - first row (RecordCounter) is 1
		var	num = chk.value;

		//create row id
		id = "row" + num;

		var thisRow = document.getElementById(id);

		//checkbox is checked - change background color	- check if alternating row
		//alternating row turned off here...
		if (chk.checked){
			thisRow.className = "selectedRow";
		}else{
			thisRow.className = "";
			}
	}

	//called onload to check if checkboxes are checked and set color
	//param: formNum pass in which form ie:0 for the first
	function maintainChks(formNum){

		//get form which checkboxes are in
	  elem = document.forms[formNum];

	  for(i=0; i<elem.length; i++) {
	    if(elem[i].type == "checkbox"){
	    	if(elem[i].checked){
	    		var	num = elem[i].value;
	    		var id = "row" + num;
	    		document.getElementById(id).className = "selectedRow";
	    	}
	    }
	  }
	}

	//called to confirm the Cancel button
	//param: msg - passed to verify f(x)
	//param: location - where to send the user if they click OK on the confirm box - passed to goNav f(x)

	function cancelConfirm(msg, location){
		if (verify(msg)){
			goNav(location);
		}else {
			return false;
		}
	}

	//called by cancel confirm
	//returns true/false
	//param: msg - message in the confirm box
	function verify(msg){
		return confirm(msg);
	}

	function openPictureWindow(url)
	{
		var newWin = window.open(url, "pictureWindow", "height=220,width=200,toolbar=no,scrollbars=yes,resizable=yes,status=yes,dependent=no");
		newWin.focus();
	}

	function openWindow(url)
	{
		var newWin = window.open(url, "pictureWindow", "height=300,width=400,toolbar=no,scrollbars=yes,resizable=yes,status=yes,dependent=no");
		newWin.focus();
	}

	function showHide(what, hide){
	if (hide == 0)
		{
			document.getElementById(what).className='hidden';
		}else document.getElementById(what).className='visible';
	}
	
	function showHide2(elementName, elementType, hide)
	{
		if (hide == 0)
		{
			document.getElementById(elementName).className='hidden';
		}
		else
		{
			if(elementType=="row")
			{
				document.getElementById(elementName).className='visibleTR';
			}
			else if(elementType=="table")
			{
				document.getElementById(elementName).className='visibleTable';
			}
			else 
			{
				document.getElementById(elementName).className='visible';
			}
		}
	}
	
	//show hide where there is an expand (plus sign) - and u want to show multiple rows simultaneously
 /*params:
 imgName - name of the + or - sign - has to be unique
 rowPrepend - ID prepend of the row - has to be unique
 rowNums - number of rows to hide/show - size of the collection
 path - img path (<msp tag>)
 */

 function showHideMulti(imgName, rowPrepend, rowNums, path){	
  var appendName = rowPrepend + rowNums;

  if (imgName != ""){
  	var currentImage = window.document.images[imgName].src;

	  if (currentImage.indexOf("contract") < 0)
	  {
	   window.document.images[imgName].src=path+"/images/contract.gif";
	  } else{
	   window.document.images[imgName].src=path+"/images/expand.gif";
	  }
	}

  for (var intI=0; intI<rowNums; intI++) {
   appendName = rowPrepend + intI;
   if (document.getElementById(appendName).className=='visibleTR')
   {
    show(appendName,0);    
   } else{
   	show(appendName,1,"row");    
   }
  }

 }
 
 //show hide 
 /*params:
what - the id of the thing to show
hide - 0 = hide 1 = show
format - table, row, or "" (for spans)
 */
 function show(what, hide, format){
	if (hide == 0)
		{
			document.getElementById(what).className='hidden';
		}else if(format=="row"){
				document.getElementById(what).className='visibleTR';
			}else if(format=="table"){
					document.getElementById(what).className='visibleTable';
				}else if(format=="inline"){
					document.getElementById(what).className='visibleInline';
				}else {
						document.getElementById(what).className='visible';
					}
	}
 
function showHideMultiNoExpansion(rowPrepend, rowNums, hide){

  var appendName = rowPrepend + rowNums;

  for (var intI=0; intI<rowNums; intI++) {
   appendName = rowPrepend + intI;
   if (hide == 0)
   {
    show(appendName,0);
   } else{
   	show(appendName,1,"row");
   }
  }

 }


function showSomethingFromDropDown(el, prePend, rowNums)
{
	var selectedValue = el.options[el.selectedIndex].value;
	var prePendToSend = prePend+selectedValue;
	for (var i=0; i<el.length; i++){
		if (selectedValue != el.options[i].value){
			showHideMultiNoExpansion(prePend+el.options[i].value, rowNums, 0)
		}else
			{
				showHideMultiNoExpansion(prePendToSend, rowNums, 1)
			}
	}

}

function changeFormActionURL(theForm, URL, doSubmit)
	{
		theForm.action = URL;
		if(doSubmit){
			theForm.submit();
		}
		return true;
	}