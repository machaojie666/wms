$(function () {
    $("[name='stockIncomeBill.vdate'],[name='orderBill.vdate']").addClass("Wdate").click(function () {
        WdatePicker();
    });
    // 清空当前行的数据
    function removeVal(currentTr) {
        currentTr.find("[tag=pid]").val("");
        currentTr.find("[tag=name]").val("");
        currentTr.find("[tag=costPrice]").val("");
        currentTr.find("[tag=number]").val("");
        currentTr.find("[tag=remark]").val("");
        currentTr.find("[tag=brand]").html("");
        currentTr.find("[tag=amount]").html("");
    }
    // 添加一行明细
    $(".appendRow").click(function () {
        var newTr = $("#edit_table_body tr:first").clone();
        $("#edit_table_body").append(newTr);
        removeVal(newTr);
    });
    // 统一绑定事件,body里的每行都会有此事件
    $("#edit_table_body").on("click", ".removeItem", function () {
        var currentTr = $(this).closest("tr");
        // 如果tbody中的行数大于1,删除
        if ($("#edit_table_body tr").size() > 1) {
            // 删除当前行
            currentTr.remove();
        } else {
            // 如果只剩一行,清空当前行的数据,不删除
            removeVal(currentTr);
        }
    }).on("click", ".searchproduct", function () { // 点击放大镜,弹出模态框,选择商品
        var currentTr = $(this).closest("tr");
        var url = "/product_selectProductList.action";
        $.dialog.open(url, {
            title: "货品列表",
            width: 900,
            height: 500,
            close: function () {
                // 接收子窗口传回的数据 jsonString,product的属性
                var json = $.dialog.data("jsonString");
                if (json) {
                    // 回填数据
                    currentTr.find("[tag=pid]").val(json.id);
                    currentTr.find("[tag=name]").val(json.name);
                    currentTr.find("[tag=costPrice]").val(json.costPrice);
                    currentTr.find("[tag=brand]").html(json.brandName);
                }
            }
        });
        // 价格和数量改变时计算金额小计
    }).on("change", "[tag='costPrice'],[tag='number']", function () {
        var currentTr = $(this).closest("tr"); // 当前所在行
        var costPrice = currentTr.find("[tag='costPrice']").val();
        var number = currentTr.find("[tag='number']").val();
        // 价格和数量不为空时
        if (costPrice && number) {
            // 计算出金额小计的值,保留两位小数
            currentTr.find("[tag='amount']").html(costPrice * number).toFixed(2);
        } else {
            currentTr.find("[tag='amount']").html(0);
        }
    });
});
