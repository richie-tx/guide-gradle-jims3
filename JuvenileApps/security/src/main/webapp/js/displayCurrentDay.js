<!-- displayCurrentDay.js --> 
   var today = new Date();
   var curDay = today.getDate();
   var sel = "";
   var dayValue = "";
   for (var x = 1; x <32; x++){
      sel = "";
      dayValue = x;
      if (x == curDay){
         sel = " selected";
      }   
      if (x < 10){
        dayValue = "0" + x;
      }
      document.write("<option value=" + dayValue + sel +">" + x);
   }