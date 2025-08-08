<script Language="Javascript">
   var today = new Date();
   var curDay = document.forms[0].dateOfBirth.value.substring(2,4);
   var sel = "";
/*  curDay should only have a value on update and
    it should be blank on create pages */			
   if (curDay < 1){
	  curDay = today.getDate();
   }   
   for (var x = 1; x <32; x++){
     sel = "";
     if (x == curDay){
       sel = " selected";
     }
     document.write("<option value=" + x + sel +">" + x);
   }
</script>