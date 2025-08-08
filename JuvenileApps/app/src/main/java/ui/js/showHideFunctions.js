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

	function show(what, hide, format){
	if (hide == 0)
		{
			document.getElementById(what).className='hidden';
		}else if(format=="row"){
				document.getElementById(what).className='visibleTR';
			}else if(format=="table"){
					document.getElementById(what).className='visibleTable';
				}else {
						document.getElementById(what).className='visible';
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