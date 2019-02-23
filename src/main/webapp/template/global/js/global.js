$(document).ready(function () {
    enableOrDisableDeleteAll();
    autoCheck();
    autoCheckboxAll();
});

function enableOrDisableDeleteAll() {
    // $('input[type=checkbox]').click(function () {
    // 	if($('input[type=checkbox]:checked').length > 0) {
    // 		$('#btnDelete').prop("disabled",false);
    // 		alert(1);
    // 	} else {
    // 		$('#btnDelete').prop("disabled",true);
    // 	}
    // 	alert($('input[type=checkbox]:checked').length);
    // });
    $(':checkbox').click(function () {
        if($(':checkbox:checked' ).length > 0) {
            $('#btnDelete').prop("disabled" , false)
        } else {
            $('#btnDelete').prop("disabled" , true)
        }
    });
}
function autoCheck() {
    $('#checkAll').change(function () {
        if(this.checked) {
            $('tbody').find(':checkbox').prop('checked',true);
        } else {
            $('tbody').find(':checkbox').prop("checked",false);
            $('#btnDelete').prop("disabled" , true);
        }

    });
}
function autoCheckboxAll() {
    $('tbody').find(':checkbox').click(function () {
        var totalCheckbox = $('tbody').find(':checkbox').length;
        var totalcheckboxChecked = $('tbody').find(':checkbox:checked').length;
        if(totalCheckbox == totalcheckboxChecked) {
            $('#checkAll').prop('checked',true);
        } else {
            $('#checkAll').prop('checked',false);
        }
    });
}