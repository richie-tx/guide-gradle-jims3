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