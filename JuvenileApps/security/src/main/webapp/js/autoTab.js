<!-- JavaScript - Button Check -->
<script language=javascript>
function autoTab(input,len) {
   if(input.value.length >= len){
      input.value = input.value.slice(0, len);
      input.form[(getIndex(input)+1) % input.form.length].focus();
   }
return true;
}
function getIndex(input) {
   var index = -1;
   var i = 0;
   var found = false;
   while (i < input.form.length && index == -1)
   if (input.form[i] == input)index = i;
     else i++;
   return index;
} 

</script>