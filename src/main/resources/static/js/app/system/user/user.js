$(function () {
    // 初始化表格

    var $userTableForm = $(".user-table-form");
    var $id = "1";
    var settings = {
        url: ctx + "user/list",
        pageSize: 10,
        // 获取筛选用户的参数
        queryParams: function (params) {
            return {
                pageSize: params.limit,
                pageNum: params.offset / params.limit + 1,
                id: $userTableForm.find("input[name='id']").val().trim(),
                name: $userTableForm.find("input[name='name']").val(),
                state: $userTableForm.find("select[name='state']").val()
            };
        },
        columns: [{
            checkbox: true
        }, {
            field: 'userId',
            visible: false
        }, {
            field: 'name',
            title: '姓名'
        }, {
            field: 'id',
            title: '身份证',
            formatter: function (value, row, index) {
                $id = value;
                return '<span class="idNumber">' + value + '</span>';
            }
        }, {
            field: 'email',
            title: '邮箱'
        }, {
            field: 'bkmajor',
            title: '报考科目'
        }, {
            field: 'gender',
            title: '性别',
            formatter: function (value, row, index) {
                if (value === '男') return '男';
                else if (value === '女') return '女';
                else return '保密';
            }
        }, {
            field: 'creatTime',
            title: '创建时间'
        }, {
            field: 'state',
            title: '状态',
            formatter: function (value, row, index) {
                // var id = $("#userTable").bootstrapTable('getSelections')[0].id;
                if (value === '审核通过') {
                    return '<span style="cursor:pointer;" title="点击审核" class="badge badge-success" ' +
                        'onclick="">审核通过</span>';
                }
                if (value === '未审核') {
                    return '<span style="cursor:pointer;" class="badge badge-warning" onclick="console.log(this)">未审核</span>';
                }
                return '<span style="cursor:pointer;" class="badge badge-danger" onclick="console.log(this)">审核失败</span>';
            }
        }, {
            field: 'editRows',
            title: '编辑',
            formatter: function (val, row, index) {
                return '<button type="button" class="btn btn-success" onclick="getUser(this)" data-target="#modal-container-981481" data-toggle="modal" >修改</button>'
            }
        }

        ]
    };
    console.log($id);
    $MB.initTable('userTable', settings);
});

/**
 * 查找该行的身份证号，并进行审核
 * @param obj this对象
 */
function audit(obj) {
    var id = $(obj).parent().siblings().children('.idNumber').text();
    // console.log("id====" + id);
    ajax({
        url: "",
        data: {
            id: id
        },
        dataType: "json",
        error: function (r) {
        },
        success: function (r) {
        }
    });
}

function search() {
    $MB.refreshTable('userTable');
}

function refresh() {
    $(".user-table-form")[0].reset();
    $MB.refreshTable('userTable');
}

function deleteUsers() {
    var selected = $("#userTable").bootstrapTable('getSelections');
    var selected_length = selected.length;
    var contain = false;
    if (!selected_length) {
        $MB.n_warning('请勾选需要删除的用户！');
        return;
    }
    var ids = "";
    for (var i = 0; i < selected_length; i++) {
        ids += selected[i].userId;
        if (i !== (selected_length - 1)) ids += ",";
        if (userName === selected[i].username) contain = true;
    }
    if (contain) {
        $MB.n_warning('勾选用户中包含当前登录用户，无法删除！');
        return;
    }

    $MB.confirm({
        text: "确定删除选中用户？",
        confirmButtonText: "确定删除"
    }, function () {
        $.post(ctx + 'user/delete', {"ids": ids}, function (r) {
            if (r.code === 0) {
                $MB.n_success(r.msg);
                refresh();
            } else {
                $MB.n_danger(r.msg);
            }
        });
    });
}

function exportUserExcel() {
    $.post(ctx + "user/excel", $(".user-table-form").serialize(), function (r) {
        if (r.code === 0) {
            window.location.href = "file/download?fileName=" + r.msg + "&delete=" + true;
        } else {
            $MB.n_warning(r.msg);
        }
    });
}

function exportUserCsv() {
    $.post(ctx + "user/csv", $(".user-table-form").serialize(), function (r) {
        if (r.code === 0) {
            window.location.href = "file/download?fileName=" + r.msg + "&delete=" + true;
        } else {
            $MB.n_warning(r.msg);
        }
    });
}