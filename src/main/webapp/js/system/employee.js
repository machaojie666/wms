$(function () {
    $("#editForm").validate({
        rules: {
            'emp.name': {
                required: true,
                minlength: 5
            },
            'emp.password': {
                required: true,
                minlength: 5
            },
            repassword: {
                required: true,
                minlength: 5,
                equalTo: '#password'
            },
            'emp.email': {
                email: true
            },
            'emp.age': {
                required: true,
                range: [18, 50]
            }

        },

        messages: {
            'emp.name': {
                required: "不能为空啊",
                minlength: "长度不能小于5"
            },
            'emp.password': {
                required: "密码还想为空?",
                minlength: "长度不能小于5"
            },
            repassword: {
                required: "这个密码也不能为空",
                minlength: "长度跟得上面一样",
                equalTo: '密码要一致'
            },
            'emp.email': {
                email: "按照格式写"
            },
            'emp.age': {
                required: "年龄!!!",
                range: "未满18岁还想看?"
            }
        }
    });
});

$(function () {

    $("#selectAll").click(function () {
        $(".allRoles option").appendTo(".selectRoles");
    });
    $("#deselectAll").click(function () {
        $(".selectRoles option").appendTo(".allRoles");
    });
    $("#select").click(function () {
        $(".allRoles option:selected").appendTo(".selectRoles");
    });
    $("#deselect").click(function () {
        $(".selectRoles option:selected").appendTo(".allRoles");
    });

    $("#editForm").submit(function () {
        $(".selectRoles option").prop("selected",true);
    });

});
// 页面加载完之后,将已分配的权限从左下拉框中移除
$(function () {
    // 获取右下拉框中的权限的id
    var ids = $.map($(".selectRoles option"),function (option) {
        return $(option).val();
    });

    // 循环遍历,判断是否存在右框中
    $.each($(".allRoles option"),function (index,option) {
        // 判断迭代出来的左边的option元素的value是否存在右边的下拉框中(ids 数组中)
        if ($.inArray($(option).val(), ids) >= 0) {
            $(option).remove();
        }
    });
    // 在提交表单时把分配的权限全部选中
    $("#editForm").submit(function () {
        $(".selectRoles option").prop("selected",true);
    });

});