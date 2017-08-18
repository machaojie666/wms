var setting = {
    callback: {
        beforeClick: function (treeId, treeNode) {
            $("#rightMain").attr("src", treeNode.path);
            return false;
        }
    },
    data: {
        simpleData: {
            enable: true,
            idKey: "id",
            pIdKey: "pId",
            rootPId: ""
        }
    }
};

//加载当前日期
function loadDate() {
    var time = new Date();
    var myYear = time.getFullYear();
    var myMonth = time.getMonth() + 1;
    var myDay = time.getDate();
    if (myMonth < 10) {
        myMonth = "0" + myMonth;
    }
    document.getElementById("day_day").innerHTML = myYear + "." + myMonth + "." + myDay;
}

/**
 * 隐藏或者显示侧边栏
 *
 **/
function switchSysBar(flag) {
    var side = $('#side');
    var left_menu_cnt = $('#left_menu_cnt');
    if (flag == true) {	// flag==true
        left_menu_cnt.show(500, 'linear');
        side.css({width: '280px'});
        $('#top_nav').css({width: '77%', left: '304px'});
        $('#main').css({left: '280px'});
    } else {
        if (left_menu_cnt.is(":visible")) {
            left_menu_cnt.hide(10, 'linear');
            side.css({width: '60px'});
            $('#top_nav').css({width: '100%', left: '60px', 'padding-left': '28px'});
            $('#main').css({left: '60px'});
            $("#show_hide_btn").find('img').attr('src', '/images/common/nav_show.png');
        } else {
            left_menu_cnt.show(500, 'linear');
            side.css({width: '280px'});
            $('#top_nav').css({width: '77%', left: '304px', 'padding-left': '0px'});
            $('#main').css({left: '280px'});
            $("#show_hide_btn").find('img').attr('src', '/images/common/nav_hide.png');
        }
    }
}

$(function () {
    loadDate();

    // 显示侧边栏
    switchSysBar(true);

    // 显示隐藏侧边栏
    $("#show_hide_btn").click(function () {
        switchSysBar();
    });
});
$(function () {
    $(".system_url").click(function () {
        var url = $(this).data("url");
        $("#rightMain").attr("src", url);
    });

});

// 为li绑定点击事件
$(function () {
    $("#TabPage2 li").click(function () {
        //  先将所有li的class的selected样式删除
        $("#TabPage2 li").removeClass("selected");
        // 为当前li添加属性
        $(this).addClass("selected");
        // 将所有的图片地址设置为1.jpg的格式
        $.each($("#TabPage2 img"), function (index, item) {
            $(item).prop("src", "/images/common/" + (index + 1) + ".jpg");

        });
        // 切换当前li中的图片
        $(this).find("img").prop("src", "/images/common/" + ($(this).index() + 1) + "_hover.jpg");
        // 修改模块中的图片
        $("#nav_module img").prop("src", "/images/common/module_" + ($(this).index() + 1) + ".png")

        // 加载模块数据
        loadMenu($(this).data("rootmenu"));
    });

});

// 菜单树
var setting = {
    data: {
        simpleData: {
            enable: true
        }
    },
    callback: {
        onClick: function (event, treeId, treeNode) {
            if (treeNode.action) {
                // 发送请求
                $("#rightMain").prop("src", treeNode.action + ".action");
            }
        }
    },
    // 使用一步加载菜单
    async: {
        enable: true,
        url:"/systemMenu_getMenusByParentSn.action",
        // 请求时携带的参数
        autoParam:["sn=qo.parentSn"],
    }
};
var business = [
    {id: 11, pId: 1, name: "业务模块",isParent:true,sn:"business"},
];
var system = [
    {id: 12, pId: 1, name: "系统模块",isParent:true,sn:"system"},
];
var chart = [
    {id: 13, pId: 1, name: "报表模块",isParent:true,sn:"chart"},
];
var zNodes = {
    business: business,
    system: system,
    chart: chart
};
$(document).ready(function () {
    $.fn.zTree.init($(".ztree"), setting, zNodes.business);
});

// 在点击菜单节点的时候调用该函数去加载对应的菜单树
function loadMenu(modelName) {
    // 传递的是字符串,需要用[]取出对象中的属性的值 不能用.modelName
    $.fn.zTree.init($(".ztree"), setting, zNodes[modelName]);

}