//
jQuery.ajaxSettings.traditional = true;
$(function () {
    // 页面加载成功后将所有复选框取消选中状态
    $(":checkbox").prop("checked", false);
    // 点击新增按钮跳转到input页面
    $(".btn_input").click(function () {
        window.location.href = $(this).data("url");
    });

    $(".btn_page").click(function () {
        // 获取当前页的数据设置到表单中
        var page = $(this).data("page") || $("input[name='qo.currentPage']").val();

        // 使用data("xxx")获取元素data-xxx属性上的值
        $("input[name='qo.currentPage']").val(page);
        $("#searchForm").submit();
    });
    $("select[name='qo.pageSize']").change(function () {
        $("#searchForm"). submit();
    });

    // 批量删除
    $(".btn_batchDelete").click(function () {
        var url = $(this).data("url");

        // 获取到选中的复选框
        var checkedBox = $(".acb:checked");

        // 获取到复选框对应的id的值. map返回的是jQuery对象,需要get() 转成对应的DOM对象
        var ids = checkedBox.map(function (index, item) {
            return $(item).data("id");
        }).get();

        if (ids.length == 0) {
            art.dialog({
                title: "温馨提示",
                content: "啥都不选还点删除?",
                ok: true
            });
            return
        }

        art.dialog({
            title: "温馨提示",
            content: "确定要删除吗?",
            ok: function () {
                $.get(url, {
                    ids:ids
                }, function (data) {
                    art.dialog({
                        title: "温馨提示",
                        content: data,
                        ok: function () {
                            // 删除成功,刷新页面
                            window.location.reload();
                        },
                        icon:"smile"
                    });

                });
            },
            cancel: true,
            okVal: "确定",
            cancelVal: "取消",
            icon: "warning"
        });
    });
    // 删除
    $(".btn_delete").click(function () {
        var id = $(this).data("id");
        var url = $(this).data("url")

        art.dialog({
            title: "温馨提示",
            content: "确定要删除吗?",
            ok: function () {
                $.get(url, function (data) {
                    art.dialog({
                        title: "温馨提示",
                        content: data,
                        ok: function () {
                            // 删除成功,刷新页面
                            window.location.reload();
                        }
                    });

                });
            },
            cancel: true,
            okVal: "确定",
            cancelVal: "取消",
            icon: "warning"
        });
    });
    // 审核
    $(".btn_audit").click(function () {
        var id = $(this).data("id");
        var url = $(this).data("url")

        art.dialog({
            title: "温馨提示",
            content: "确定要审核吗?",
            ok: function () {
                $.get(url, function (data) {
                    art.dialog({
                        title: "温馨提示",
                        content: data,
                        ok: function () {
                            // 审核成功,刷新页面
                            window.location.reload();
                        }
                    });

                });
            },
            cancel: true,
            okVal: "确定",
            cancelVal: "取消",
            icon: "warning"
        });
    });

    // 全选/全不选
    $("#all").change(function () {
        var check = $(this).prop("checked");
        $(".acb").prop("checked", check);
    });
});

$(function () {
    $("tbody tr").mouseover(function () {
        $(this).css("background-color","#D8D8D8");
    });
    $("tbody tr").mouseout(function () {
        $(this).css("background-color","#FFFFFF");
    });

});
