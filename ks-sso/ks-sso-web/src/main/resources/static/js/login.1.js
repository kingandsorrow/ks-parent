$(function () {

    // input iCheck
    $('input').iCheck({
        checkboxClass: 'icheckbox_square-blue',
        radioClass: 'iradio_square-blue',
        increaseArea: '20%' // optional
    });

    $(document).on("click", ".goLogin", function () {
        $("#form").submit(function (e) {
            alert("登录中..");
        });
    })


});
