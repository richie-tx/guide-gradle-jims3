//justin: this is the best way i think to handle the radio buttons in task... since there can be multiple status's... isPersonal is a boolean for now.. but u never know

function toggleButtons(el){
	var taskStatus = el.getAttribute("taskStatus");
	var isPersonal = el.getAttribute("isPersonal");
	if (el.checked){
		
		switch (taskStatus)
		{
			case "ACCEPTED":
			//show("continueButton", 1, "inline")
			//show("acceptButton", 0)
			break
			
			default:
			//show("acceptButton", 1, "inline")
			//show("continueButton", 0)
		}
		
		switch (isPersonal)
		{
			case "1":
			//show("closeTaskButton", 1, "inline")
			//show("transferButton", 0)
			break
			
			default:
			//show("closeTaskButton", 0)
			//show("transferButton", 1, "inline")
		}
								
	}
}