<!-- displayCurrentYear.js -->
    var today = new Date();
    var year = today.getFullYear();
    var yr = 0;
    for (var x = 0; x <120; x++){
       yr = year - x;
       document.write("<option value=" + yr + ">" + yr);
    }