// 给角色分配权限
$(function () {
    $("#selectAll").click(function () {
        $(".allPermissions option").appendTo($(".selectPermissions"));
    });
    $("#deselectAll").click(function () {
        $(".selectPermissions option").appendTo($(".allPermissions"));
    });

    $("#select").click(function () {
        $(".allPermissions option:selected").appendTo($(".selectPermissions"));
    });
    $("#deselect").click(function () {
        $(".selectPermissions option:selected").appendTo($(".allPermissions"));
    });

});

// 给角色分配菜单
$(function () {
    $("#mselectAll").click(function () {
        console.debug("000000000000")
        $(".allMenus option").appendTo($(".selectMenus"));
    });
    $("#mdeselectAll").click(function () {
        $(".selectMenus option").appendTo($(".allMenus"));
    });

    $("#mselect").click(function () {
        $(".allMenus option:selected").appendTo($(".selectMenus"));
    });
    $("#mdeselect").click(function () {
        $(".selectMenus option:selected").appendTo($(".allMenus"));
    });

});

// 页面加载完之后,将已分配的权限从左下拉框中移除
$(function () {
    // 获取右下拉框中的权限的id
    var ids = $.map($(".selectPermissions option"),function (option) {
        return $(option).val();
    });

    // 循环遍历,判断是否存在右框中
    $.each($(".allPermissions option"),function (index,option) {
        // 判断迭代出来的左边的option元素的value是否存在右边的下拉框中(ids 数组中)
        if ($.inArray($(option).val(), ids) >= 0) {
            $(option).remove();
        }
    });
    // 在提交表单时把分配的权限全部选中
    $("#editForm").submit(function () {
        $(".selectPermissions option").prop("selected",true);
    });

});

// 页面加载完之后,将已分配的菜单从左下拉框中移除
$(function () {
    // 获取右下拉框中的权限的id
    var ids = $.map($(".selectMenus option"),function (option) {
        return $(option).val();
    });

    // 循环遍历,判断是否存在右框中
    $.each($(".allMenus option"),function (index,option) {
        // 判断迭代出来的左边的option元素的value是否存在右边的下拉框中(ids 数组中)
        if ($.inArray($(option).val(), ids) >= 0) {
            $(option).remove();
        }
    });
    // 在提交表单时把分配的权限全部选中
    $("#editForm").submit(function () {
        $(".selectMenus option").prop("selected",true);
    });

});