var imageNames = new Array();
var selectedImgNum = -1;
var theSwitch=false;
var theObj=null
var selObj=null

// we need to focus the frame
this.focus();

function highlightImg(obj) {
	if(selObj!=obj){
      	obj.style.backgroundColor = "#E0E0E0";
		obj.style.cursor="hand"
      	obj.style.color = "white";
		//currentSelected=theObj;
		theObj=obj;
	}
	
}




function lowlightImg(obj) {
	if(selObj!=obj){
  		obj.style.backgroundColor = "";
  		obj.style.color = "black";
	}
}

function unselectLight() {
  		selObj.style.backgroundColor = "";
  		selObj.style.color = "black";
}

function selectLight() {
	if(selObj!=null)
		unselectLight();

	theObj.style.backgroundColor = "#FFFF66";
      theObj.style.color = "white";
	selObj=theObj;
   
}


function registerImages() {
registerKeys() 
}



//-------------------------keyCommands

var arrayOfTR=null

function registerKeys() {
document.onkeydown = keyDown;
arrayOfTR=document.all.tags("tr")
}

function keyDown(e) {
var ieKey = event.keyCode;
if (ieKey == 85) 
		Up();

if (ieKey == 68) 
		Down();

 //if (ieKey == 13)  
	    //trySelect();

}

function trySelect(){
	var idString=""
	var missing=""
	var received=""
	var liNumber=""
	var oidNumber=""
	var ordered=""
	if(!theObj)
		 fixNull()
	if(theObj){
		if(theObj.id){
			var tempString=new String(theObj.id)
			for(var x=3;x<tempString.length;x++){
				idString=idString+tempString.charAt(x);
			}
			// get var to disable buttons
			received=idString.charAt(0);
			missing=idString.charAt(1);
			ordered=idString.charAt(2);
			// get li number
			for(x=3;x<5;x++){
				liNumber=liNumber+idString.charAt(x);
			}
			// get oid
			for(var x=6;x<tempString.length;x++){
				oidNumber=oidNumber+idString.charAt(x);
			}
			// select
			select(oidNumber,liNumber);
			if(received==0||missing==0)
				ableButtons()
				
			if(ordered==0)
			  ableOrderButton()
			return false;
		}
	}
}

function fixNull(){
// this selects the first item if none is selected
	for(var x=0;x<arrayOfTR.length;x++){
		if(arrayOfTR[x].id){
					theObj=arrayOfTR[x];
					selObj=arrayOfTR[x];
					x=arrayOfTR.length+1
		}		
	}
}

function Up() {
	theObj=selObj;
	var theSelection=null
	for(var x=0;x<arrayOfTR.length;x++){
				 if(arrayOfTR[x]==theObj)
				 theSelection=x-1
	}
	theObj=arrayOfTR[theSelection]
	lowlightImg(selObj)
	trySelect()
}


function Down() {
	theObj=selObj;
	var theSelection=null
	for(var x=0;x<arrayOfTR.length;x++){
				 if(arrayOfTR[x]==theObj)
				 theSelection=x+1
		 }
	theObj=arrayOfTR[theSelection]
	lowlightImg(selObj)
	trySelect()
}