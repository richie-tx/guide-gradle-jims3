<!-- TextArea maxlength JavaScript -->
<script language=javascript>

function textCounter(field, maxlimit) {
  if (field.value.length > maxlimit) {
     alert('Maximum text length reached for this field');
     field.value = field.value.substring(0, maxlimit);
  } else {
     maxlimit = maxlimit - field.value.length;
  }
}

</script>