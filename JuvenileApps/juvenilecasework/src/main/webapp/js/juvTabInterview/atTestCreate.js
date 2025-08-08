<!-- JavaScript - Button Check -->

function validateCreateFields(){
    var thisForm = document.forms[0];
	Trim(thisForm["atRec.testDate"].value);
  	Trim(thisForm["atRec.arithmeticGradeLevel"].value);
  	Trim(thisForm["atRec.arithmeticScore"].value);
  	Trim(thisForm["atRec.readingGradeLevel"].value);
  	Trim(thisForm["atRec.readingScore"].value); 
  	Trim(thisForm["atRec.spellingGradeLevel"].value);
  	Trim(thisForm["atRec.spellingScore"].value);
  	
  	if ( thisForm["atRec.readingScore"].value >"" &&  thisForm["atRec.readingGradeLevel"].value =="") {
		alert("Word Reading Level is required if Word Reading Score is entered.");
		thisForm["atRec.readingGradeLevel"].focus();
		return false;
	}
  	
  	if ( thisForm["atRec.readingScore"].value =="" &&  thisForm["atRec.readingGradeLevel"].value >"") {
		alert("Word Reading Score is required if Word Reading Level is entered.");
		thisForm["atRec.readingScore"].focus();
		return false;
	}
  	  	 	
  	if ( thisForm["atRec.readingCompositeScore"].value =="" &&  thisForm["atRec.readingCompositeLevel"].value >"") {
		alert("Reading Composite Score is required if Reading Composite Level is entered.");
		thisForm["atRec.readingCompositeScore"].focus();
		return false;
	}
  	
  	if ( thisForm["atRec.readingCompositeScore"].value >"" &&  thisForm["atRec.readingCompositeLevel"].value =="") {
		alert("Reading Composite Level is required if Reading Composite Score is entered.");
		thisForm["atRec.readingCompositeLevel"].focus();
		return false;
	}
  	
  	if ( thisForm["atRec.spellingScore"].value =="" &&  thisForm["atRec.spellingGradeLevel"].value >"") {
		alert("Spelling Score is required if Spelling Grade Level is entered.");
		thisForm["atRec.spellingScore"].focus();
		return false;
	}
  	
  	if ( thisForm["atRec.spellingScore"].value >"" &&  thisForm["atRec.spellingGradeLevel"].value =="") {
		alert("Spelling Grade Level is required if Spelling Score is entered.");
		thisForm["atRec.spellingGradeLevel"].focus();
		return false;
	}
  	
  	if ( thisForm["atRec.sentenceCompletionScore"].value =="" &&  thisForm["atRec.sentenceCompletionLevel"].value >"") {
		alert("Sentence Comprehension Score is required if Sentence Comprehension Level is entered.");
		thisForm["atRec.sentenceCompletionScore"].focus();
		return false;
	}
  	
  	if ( thisForm["atRec.sentenceCompletionScore"].value >"" &&  thisForm["atRec.sentenceCompletionLevel"].value =="") {
		alert("Sentence Comprehension Level is required if Sentence Comprehension Score is entered.");
		thisForm["atRec.sentenceCompletionLevel"].focus();
		return false;
	}
	return(validateAchievementFields(thisForm));	
}






