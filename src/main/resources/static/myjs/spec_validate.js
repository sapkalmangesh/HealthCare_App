$(document).ready (function (){
            //1. hide error section
            $("#specCodeError").hide();
            $("#specNameError").hide();
            $("#specNotesError").hide();

            //2. define error variable
            var specCodeError = false;
            var specNameError = false;
            var specNotesError = false;

            //3. validate function
            function validate_specCode(){
                var val = $("#specCode").val();
                var exp =/^[A-Z]{4,15}$/;
                if(val==""){
                    $("#specCodeError").show();
                    $("#specCodeError").html("* <b>Code</b> can not be empty");
                    $("#specCodeError").css('color','red');
                    specCodeError = false;

                }else if(!exp.test(val)){
                    $("#specCodeError").show();
                    $("#specCodeError").html("* <b>Code</b> can be 4-15 chars only");
                    $("#specCodeError").css('color','red');
                    specCodeError = false;
                }else{
                    $("#specCodeError").hide();
                    specCodeError = true;
                }
                return specCodeError;
            }

            function validate_specName(){
                var val=$("#specName").val();
                var exp=/^[A-Z,a-z,0-9\s\.]{8,50}$/;
                if(val==""){
                  $("#specNameError").show();
                  $("#specNameError").html("*<b>Name</b> can not be empty");
                  $("#specNameError").css('color','red');
                  specNameError = false;

                }else if(!exp.test(val)){
                  $("#specNameError").show();
                  $("#specNameError").html("*<b>Name</b> can be 8-50 chars only");
                  $("#specNameError").css('color','red');
                  specNameError = false;

                }else{
                  $("#specNameError").hide();
                  specNameError = true;
                }
                return specNameError;
            }
            function validate_specNote(){
                var val=$("#specNotes").val();
                var exp=/^[A-Z,a-z,0-9\s\.\,\-\']{10,250}$/;
                if(val==""){
                  $("#specNotesError").show();
                  $("#specNotesError").html("*<b>Note</b> can not be empty");
                  $("#specNotesError").css('color','red');
                  specNotesError = false;

                }else if(!exp.test(val)){
                  $("#specNotesError").show();
                  $("#specNotesError").html("*<b>Note</b> can be10-250 chars only");
                  $("#specNotesError").css('color','red');
                  specNotesErrorr = false;

                }else{
                  $("#specNotesError").hide();
                  specNotesError = true;
                }
                return specNotesError;
            }

            //4. link with action event
            $("#specCode").keyup(function(){
               $(this).val($(this).val().toUpperCase());
                validate_specCode();
            });

            $("#specName").keyup(function(){
              validate_specName();
            });

            $("#specNotes").keyup(function(){
              validate_specNote();
            });

            //5. on submit
            $("#specForm").submit(function(){
                validate_specCode();
                validate_specName();
                validate_specNote();
                 if(specCodeError && specNameError && specNotesError) return true;
                 else return false;
            });
        });