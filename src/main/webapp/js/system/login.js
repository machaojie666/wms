$(function () {
    $("#login_sub").click(function () {
        $("#submitForm").submit();
    });
    // 按回车键登录
    $('#pwd').keypress(enterLogin).keydown(enterLogin);
});
function enterLogin(e) { // 传入 event
    var e = e || window.event;
    if (e.keyCode == 13) {
        $("#submitForm").submit();
    }
}