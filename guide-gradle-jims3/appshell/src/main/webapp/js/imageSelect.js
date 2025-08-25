var imageNames = new Array();
var selectedImgNum = -1;
var theSwitch=false;
var theObj=null
var selObj=null

function highlightImg(obj) {
	if(selObj!=obj){
      	obj.style.backgroundColor = "#E0E0E0";
		obj.style.cursor="hand"
      	obj.style.color = "white";
		theObj=obj;
	}
}


function lowlightImg(obj) {
	if(selObj!=obj){
  		obj.style.backgroundColor = "";
  		obj.style.color = "black";
	}
}

function unselectLight(obj) {
  		selObj.style.backgroundColor = "";
  		selObj.style.color = "black";
}

function selectLight(path,targ) {
	if(selObj!=null)
		unselectLight();
	theObj.style.backgroundColor = "#FFFF66";
      theObj.style.color = "white";
	selObj=theObj;
   if (path && targ) {
   	convert='self.parent.'+targ+'.location="'+path+'"';
   	eval(convert);
   }
}


function registerImages() {
	var imgs = document.images;
	for (var i = 0; i < imgs.length; i++) {
		imageNames[i] = basicName(imgs[i].src);
	}
registerKeys();
}


function basicName(imgName) {
  var newImgName = null;
  if (imgName.lastIndexOf("_") > -1)
    newImgName = imgName.substring(0, imgName.lastIndexOf("_"));
  return newImgName;
}

function selectImg(name) {
	var imgNum = getImageIndexForName(name);
	if (selectedImgNum != -1) {
		deselectImg(selectedImgNum);
	}
	document.images[imgNum].src = imageNames[imgNum]+"_Selected.gif";
	selectedImgNum = imgNum;
}

function deselectImg(imgNum) {
	document.images[imgNum].src = imageNames[imgNum]+"_Down.gif";
}

function rollOver(name) {
	var imgNum = getImageIndexForName(name);
	if (imgNum != selectedImgNum)
		document.images[imgNum].src = imageNames[imgNum]+"_Up.gif";
	else
		document.images[imgNum].src = imageNames[imgNum]+"_Selected.gif";
}

function rollOut(name) {
	var imgNum = getImageIndexForName(name);
	if (imgNum != selectedImgNum)
		document.images[imgNum].src = imageNames[imgNum]+"_Down.gif";
	else
		document.images[imgNum].src = imageNames[imgNum]+"_Selected.gif";
}

function getImageIndexForName(name) {
	for (var i = 0; i < document.images.length; i++) {
		if (document.images[i].name == name)
			return i;
	}
	return -1;


}


//-------------------------keyCommands

var arrayOfTR=null

function registerKeys() {
document.onkeydown = keyDown;
arrayOfTR=document.all.tags("tr")
}

function keyDown(e) {
var ieKey = event.keyCode;
//alert(ieKey)
if (ieKey == 85) 
		Up();

if (ieKey == 68) 
		Down();

 //if (ieKey == 13)  
	    //trySelect();

}

function trySelect(){
	if(theObj){
		if(theObj.id){
			var tempString=new String(theObj.id)
			var idString=""
			for(var x=3;x<tempString.length;x++){
				idString=idString+tempString.charAt(x);
			}
			select(idString);
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