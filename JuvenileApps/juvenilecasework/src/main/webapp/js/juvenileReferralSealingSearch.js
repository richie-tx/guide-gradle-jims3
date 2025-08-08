$(document)
		.ready(
				function() 
				{
					var action = $("#action").val();
					var actionError = $("#actionError").val();
					var cursorPosition = $("#cursorPosition").val();
					var currDate = new Date(); // current Date
					var recType = $("#hdnRectype").val();
					
					if (typeof $("#sealedDate") != "undefined") {
						datePickerSingle($("#sealedDate"), "Date", false);
					}		
					
					
					var selectedVal = $("form input:radio");
					
					$.each(selectedVal, function(index) { // loops through all
						// the radio
						// buttons.						
						if (typeof radio !== 'undefined'
								&& $(this).val() !== '') { // 
							//alert(selectedVal);
							$(this).prop("checked", 'checked');
						}
					});
					
					// enter key
					$(document).bind("keydown", function(event) 
							{
								if (event.which == 13) 
								{
									validate();
								}
							});

				});
