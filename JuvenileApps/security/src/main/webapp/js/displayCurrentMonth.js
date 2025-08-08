<!-- displayCurrentMonth.js --> 
   var monthLit = new Array ("January","February","March","April","May","June","July","August","September","October","November","December");
   var today = new Date();
   var curMon = today.getMonth();
   var monLitName = "";
   var monValue = 0;
   var sel = "";
   for (var x = 0; x <11; x++){
     sel = "";
     monLitName = monthLit[x];
     monValue = x + 1;
     if (x == curMon){
       sel = " selected";
     }
     if (x < 10){
       monValue = "0" + monValue;
     }
     document.write("<html:option value=" + monValue + sel + ">" + monLitName + "</html:option>");
  }
 