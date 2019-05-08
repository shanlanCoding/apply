var validator;
var $userAddForm = $("#user-add-form");
var $rolesSelect = $userAddForm.find("select[name='rolesSelect']");
var $roles = $userAddForm.find("input[name='roles']");

$(function () {
    validateRule();


    /*审核状态文本*/
    $("input[name='status']").change(function () {
        var checked = $(this).is(":checked");
        var $status_label = $("#status");
        if (checked) $status_label.html('已审核');
        else $status_label.html('审核不通过');
    });

    $("#user-add .btn-save").click(function () {
        var name = $(this).attr("name");
        getDept();
        var validator = $userAddForm.validate();
        var flag = validator.form();
        if (flag) {
            if (name === "save") {
                $.post(ctx + "user/add", $userAddForm.serialize(), function (r) {
                    if (r.code === 0) {
                        closeModal();
                        $MB.n_success(r.msg);
                        $MB.refreshTable("userTable");
                    } else $MB.n_danger(r.msg);
                });
            }
            if (name === "update") {
                $.post(ctx + "user/update", $userAddForm.serialize(), function (r) {
                    if (r.code === 0) {
                        closeModal();
                        $MB.n_success(r.msg);
                        $MB.refreshTable("userTable");
                    } else $MB.n_danger(r.msg);
                });
            }
        }
    });

    $("#user-add .btn-close").click(function () {
        closeModal();
    });

});

// 获取用户信息，填充到遮罩层里
function getGrade(obj) {
    var selected = $("#userTable").bootstrapTable('getSelections');
    var id = $(obj).parent().siblings().children('.idNumber').text();

    
    if (!typeof id == 'undefined') {
        id = selected[0].id;
    } else if (!typeof obj == 'undefined') {
        id = $(obj).parent().siblings().children('.idNumber').text();
    }
    // console.log("id=" + id);

    $.ajax(
        {
            type: "post",
            url: "/grade/getGrade",
            data: {
                id: id
            },
            dataType: "json",
            error: function (data, type, err) {
                alert(JSON.stringify(data));
                if (data.responseJSON != undefined) {
                    // console.log(data.responseJSON.error != undefined);
                    // console.log(JSON.stringify(data.responseJSON.error));
                    $MB.n_danger("error：" + JSON.stringify(data.responseJSON.error));
                }
            },
            success: function (r) {
                if (r.code === 0) {
                    // console.log(r.message)
                    // alert(JSON.stringify(data.message));
                    $("#name").val(r.message.name);
                    $("#idNumber").val(r.message.id);
                    $("#zkzh").val(r.message.zkzh);
                    /*报考专业*/
                    $("#bkmajor option:selected").text(r.message.bkmajor)

                    $("#km1").val(r.message.km1);
                    $("#km1f").val(r.message.km1f);

                    $("#km2").val(r.message.km2);
                    $("#km2f").val(r.message.km2f);

                    $("#total").val(r.message.total);
                    // 录取结果
                    if (r.message.lq == "" || r.message.lq == null ) {
                        $("#lq option:selected").text("暂无结果");
                    } else {
                        $("#lq option:selected").text(r.message.lq);
                    }
                } else {
                    $MB.n_danger(r.message);
                }
            }
        }
    );

}

/*编辑用户信息*/
function editUser() {

    // $MB.n_warning("请稍等，正在修改中...");
    // disabled Update Button
    // $(".editUserButton").addClass("disabled");


    // console.log("register-click");
    var name = $("#name").val().trim();

    // 姓名校验
    if (name === "") {
        $MB.n_warning("姓名不能为空！");
        return;
    }

    // 专业
    var bkmajor = $("#bkmajor option:selected").text();
    if (bkmajor === "报考专业") {
        $MB.n_warning("'报考专业'不能为空！");
        return;
    } else {
        // setting value
        $("#bkmajor option:selected").val(bkmajor);
    }

    // 考试科目1
    var km1 = $("#km1").val().trim();
    if (km1 === "") {
        $MB.n_warning("'考试科目1'不能为空！");
        return;
    }
    // 科目1分数
    var km1f = $("#km1f").val().trim();
    if (km1f.length === '') {
        $MB.n_warning("科目1分数，不能为空！");
        return;
    }
    // 考试科目2
    var km2 = $("#km2").val().trim();
    if (km2 === "") {
        $MB.n_warning("'考试科目2'不能为空！");
        return;
    }
    // 科目2分数
    var km1f = $("#km2f").val().trim();
    if (km1f.length === '') {
        $MB.n_warning("科目2分数，不能为空！");
        return;
    }
    // 总分
    var total = $("#total").val().trim();
    if (total.length === '') {
        $MB.n_warning("总分，不能为空！");
        return;
    }

    // 录取状态
    var lq = $("#lq option:selected").text();
    if (lq === "录取结果") {
        $MB.n_warning("'录取结果'不能为空！");
        return;
    } else {
        // setting value
        $("#lq option:selected").val(lq);
    }

    $(".registration-form");
    // 发数据
    $.ajax(
        {
            type: "post",
            url: "/grade/edit",
            data: $("#edtiGrande-form").serialize(),
            dataType: "json",
            error: function (data, type, err) {
                // alert(JSON.stringify(data));
                if (data.responseJSON != undefined) {
                    console.log(data.responseJSON.error != undefined);
                    console.log(JSON.stringify(data.responseJSON.error));
                    $MB.n_danger("error：" + JSON.stringify(data.responseJSON.error));
                }
            },
            success: function (data) {
                if (data.code === 0) {
                    $MB.n_success("同学恭喜你，资料修改成功!");
                    // $(".registration-form")[0].reset();
                    // alert("同学恭喜你，资料修改成功!");
                    // window.location.reload();
                    $("#edtiGrande-form").find(".btn.btn-secondary").click();
                    refresh();
                } else {
                    $MB.n_danger(data.message);
                }
            }
        }
    );

}

/* 新增用户 */
function register(obj) {
    // console.log(this);


    // console.log("register-click");
    // 监听保存按钮
    var registerBtn = $(".registerButton");

    var username = $("#addIdNumber").val().trim();
    var password = $("#password").val().trim();
    var cpassword = $("#password2").val().trim();
    var email = $("#addEmail").val().trim();

    // 身份号校验
    if (username === "") {
        $MB.n_warning("身份证号不能为空！");
        return;
    } else if (username.length > 18) {
        $MB.n_warning("身份证号长度不能超过18个字符！");
        return;
    } else if (username.length < 18) {
        $MB.n_warning("身份证号长度不能少于18个字符！");
        return;
    }

    //密码校验
    if (password === "") {
        $MB.n_warning("密码不能为空！");
        return;
    }
    if (cpassword === "") {
        $MB.n_warning("请再次输入密码！");
        return;
    }
    if (cpassword !== password) {
        $MB.n_warning("两次密码输入不一致！");
        return;
    }

    // 邮箱校验
    if (email === "") {
        $MB.n_warning("邮箱不能为空！");
        return;
    }

    // 性别校验
    var man = $("#addOptionsRadios3").is(":checked");
    var gril = $("#addOptionsRadios4").is(":checked");
    if (!man && !gril) {
        $MB.n_warning("请选择性别！");
        // console.log("请选择性别！");
        return;
    }

    // 专业
    var major = $("#addBkmajor option:selected").text();
    if (major === "报考专业") {
        $MB.n_warning("'报考专业'不能为空！");
        return;
    } else {
        // setting value
        $("#addBkmajor option:selected").val(major);
    }

    // 政治面貌
    var zzmm = $("#addZzmm option:selected").text();
    if (zzmm === "政治面貌") {
        $MB.n_warning("'政治面貌'不能为空！");
        return;
    } else {
        // setting value
        $("#addZzmm option:selected").val(zzmm);
    }
    // 高考报名号
    var gkbmh = $("#addGkbmh").val().trim();
    if (gkbmh.length != 14) {
        $MB.n_warning("高考报名号不对，应该为14位数！");
        return;
    }

    // disabled button
    registerBtn.attr("disabled", true);
    registerBtn.text("正在注册...");


    // $(".registration-form");
    // 发数据
    $.ajax(
        {
            type: "post",
            url: "/register",
            data: $("#register-form").serialize(),
            dataType: "json",
            success: function (data) {
                if (data.code === 0) {
                    $MB.n_success("账号添加成功!");
                    $(".registration-form")[0].reset();
                    alert("账号添加成功，确定后将会刷新页面");
                    window.location.reload();
                } else {
                    $MB.n_warning(data.message);
                }
            }
        }
    );

    // Enable Button
    $(".registerButton").removeAttr("disabled");
    registerBtn.text("注册");
}

function closeModal() {
    $("#user-add-button").attr("name", "save");
    validator.resetForm();
    // $rolesSelect.multipleSelect('setSelects', []);
    $rolesSelect.multipleSelect("refresh");
    $userAddForm.find("input[name='username']").removeAttr("readonly");
    $userAddForm.find(".user_password").show();
    $userAddForm.find("input[name='status']").prop("checked", true);
    $("#user-add-modal-title").html('新增用户');
    $("#status").html('可用');
    $MB.resetJsTree("deptTree");
    $MB.closeAndRestModal("user-add");

}

function validateRule() {
    var icon = "<i class='zmdi zmdi-close-circle zmdi-hc-fw'></i> ";
    validator = $userAddForm.validate({
        rules: {
            username: {
                required: true,
                minlength: 3,
                maxlength: 10,
                remote: {
                    url: "user/checkUserName",
                    type: "get",
                    dataType: "json",
                    data: {
                        username: function () {
                            return $("input[name='username']").val().trim();
                        },
                        oldusername: function () {
                            return $("input[name='oldusername']").val().trim();
                        }
                    }
                }
            },
            email: {
                email: true
            },
            roles: {
                required: true
            },
            ssex: {
                required: true
            }
        },
        errorPlacement: function (error, element) {
            if (element.is(":checkbox") || element.is(":radio")) {
                error.appendTo(element.parent().parent());
            } else {
                error.insertAfter(element);
            }
        },
        messages: {
            username: {
                required: icon + "请输入用户名",
                minlength: icon + "用户名长度3到10个字符",
                remote: icon + "用户名已经存在"
            },
            roles: icon + "请选择用户角色",
            email: icon + "邮箱格式不正确",
            ssex: icon + "请选择性别"
        }
    });
}

/**
 * 根据id清空表单
 * @param id
 */
function clearForm(id) {
    // 清空表单
    $(':input', '#' + id)
        .not(':button, :submit, :reset, :hidden')
        .val('')
        .removeAttr('checked')
        .removeAttr('selected');
}
