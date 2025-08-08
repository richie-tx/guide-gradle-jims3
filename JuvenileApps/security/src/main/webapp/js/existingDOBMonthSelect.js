<script Language="Javascript">
   var monthLit = new Array ("January","February","March","April","May","June","July","August","September","October","November","December");
   var today = new Date();
   var curMon = document.forms[0].dateOfBirth.value.substring(0,2) - 1;
   var monName = "";
   var monValue = 0;
   var sel = "";
   for (var x = 0; x <11; x++){
      sel = "";
      monName = monthLit[x];
      monValue = x + 1;
      if (x == curMon){
        sel = " selected";
      }
      document.write("<option value=" + monValue + sel +">" + monName);
   }
</script>
