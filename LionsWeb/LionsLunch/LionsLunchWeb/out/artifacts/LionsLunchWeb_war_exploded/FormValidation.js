/**
 * Created by justinkang on 6/28/16.
 */
// var form = document.getElementById('formSignUp'); // form has to have ID: <form id="formID">
// form.noValidate = true;
// form.addEventListener('submit', function(event) { // listen for form submitting
//     if (!event.target.checkValidity()) {
//         event.preventDefault(); // dismiss the default functionality
//         alert('Please, fill the form'); // error message
//     }
// }, false);


// $("form").submit(function(e) {
//
//     var ref = $(this).find("[required]");
//
//     $(ref).each(function(){
//         if ( $(this).val() == '' )
//         {
//             alert("Required field should not be blank.");
//
//             $(this).focus();
//
//             e.preventDefault();
//             return false;
//         }
//     });  return true;
// });

var form = document.getElementById('formID'); // form has to have ID: <form id="formID">
form.noValidate = true;
form.addEventListener('submit', function(event) { // listen for form submitting
    if (!event.target.checkValidity()) {
        event.preventDefault(); // dismiss the default functionality
        document.getElementById('yournameMsg').innerHTML = document.getElementById('yourname').value == '' ? 'Please, fill Your name' : ''; // Show message
        document.getElementById('yourname').style.borderColor = document.getElementById('yourname').value == '' ? 'darkred' : ''; // color field's border
        if (document.getElementById('yourname').value == '') document.getElementById('yourname').focus(); // Put cursor back to the field
        //alert('Please, fill the form'); // error message
    }
}, false);