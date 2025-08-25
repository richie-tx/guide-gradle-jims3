function validateAdd() {
  if (document.AddForm.OID.selectedIndex == -1) {
    alert('You must select a specimen before adding it.');
    return false;
  } else
  if (document.EditForm.OID.options.length >= 10) {
    alert('A test cannot be associated with more than 10 specimen types.');
    return false;
  }
}

function validateEdit() {
  if (document.EditForm.OID.selectedIndex == -1) {
    alert('You must select a specimen before editing it.');
    return false;
  }
}

function validateDelete() {
  if (document.EditForm.OID.selectedIndex == -1) {
    alert('You must select a specimen before deleting it.');
    return false;
  } else 
  if (document.EditForm.OID.options.length <= 1) {
    alert('A test must be associated with at least one specimen type.');
    return false;
  } else {
    document.EditForm.Topic.value='DeleteTestSpecimen';
    document.EditForm.ReplyTopic.value='DisplayTestSpecimenList';
  }
}