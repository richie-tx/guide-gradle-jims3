<script Language="Javascript">
   var monthLit = new Array ("January","February","March","April","May","June","July","August","September","October","November","December");
   var today = new Date();
   var curMon = document.forms[0].dateOfBirth.value.substring(0,2) - 1;			   
   var monName = "";
   var monValue = 0;
   var sel = "";
/*  curMon should only have a value on update and
    it should be blank on create pages  */				
   if (curMon < 1){
	  curMon = today.getMonth();
   }   
   for (var x = 0; x <11; x++){
     sel = "";
     monLitName = monthLit[x];
	 monValue = x + 1;
   	 if (x == curMon){
   	   sel = " selected";
   	 }
   	 document.write("<option value=" + monValue + sel +">" + monLitName);
   }
</script>
