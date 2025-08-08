function selectDate(day,month,year) {
  var monthName;
  if (month == 1) monthName = "Jan";
  if (month == 2) monthName = "Feb";
  if (month == 3) monthName = "Mar";
  if (month == 4) monthName = "Apr";
  if (month == 5) monthName = "May";
  if (month == 6) monthName = "Jun";
  if (month == 7) monthName = "Jul";
  if (month == 8) monthName = "Aug";
  if (month == 9) monthName = "Sep";
  if (month == 10) monthName = "Oct";
  if (month == 11) monthName = "Nov";
  if (month == 12) monthName = "Dec";
  var dateStr = day+'-'+monthName+'-'+year;
  parent.window.returnValue = dateStr;
}
