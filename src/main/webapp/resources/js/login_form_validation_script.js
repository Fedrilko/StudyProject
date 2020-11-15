$(document).ready(function () {

    $("#login_submit").click(function () {

        if ($("#login").val() == "" && $("#password").val() == "") {
            $("#login").css("border", "solid 3px red");
            $("#password").css("border", "solid 3px red");
            $("#login_msg").css("display", "block");
            $("#password_msg").css("display", "block");
        } else if ($("#login").val() == "") {
            $("#login").css("border", "solid 3px red");
            $("#login_msg").css("display", "block");
        } else if ($("#password").val() == "") {
            $("#password").css("border", "solid 3px red");
            $("#password_msg").css("display", "block");
        } else {
            $("#login_form").submit();
        }

    });

});
