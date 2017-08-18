$(function () {
    $(".btn_reload").click(function () {
        var url = $(this).data("url");
        $.get(url, function (data) {
            art.dialog({
                title: "提示",
                content: data,
                ok: function () {
                    window.location.reload();
                }
            });
        });
    });
});