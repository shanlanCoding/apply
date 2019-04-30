$(function () {
    console.log(
        "Author：shan lan\r\n" +
        "Mail: misterchou@qq.com\r\n" +
        "Time：2019-03-16\r\n");
})

function getUser() {
    var id = document.querySelector('body > div > div > div > div > div.col-md-8 > table > tbody > tr:nth-child(3) > td:nth-child(3)').textContent;

    $.ajax(
        {
            type: "post",
            url: "/user/getUser",
            data: {
                id: id
            },
            dataType: "json",
            error: function (data, type, err) {
                alert(JSON.stringify(data));
                if (data.responseJSON != undefined) {
                    console.log(data.responseJSON.error != undefined);
                    console.log(JSON.stringify(data.responseJSON.error));
                    $MB.n_danger("error：" + JSON.stringify(data.responseJSON.error));
                }
            },
            success: function (r) {
                if (r.code === 0) {
                    // console.log(r.message)
                    // alert(JSON.stringify(data.message));
                    $("#email").val(r.message.email);
                    $("#tswt").val(r.message.tswt);
                    $("#mmda").val(r.message.mmda);
                    $("#name").val(r.message.name);
                    if (r.message.gender === "男") {
                        $("#optionsRadios3").attr("checked", "checked");
                    } else if (r.message.gender === "女") {
                        $("#optionsRadios4").attr("checked", "checked");
                    }
                    /*报考专业*/
                    $("#bkmajor option:selected").text(r.message.bkmajor)
                    /*政治面貌*/
                    $("#zzmm option:selected").text(r.message.zzmm);
                    $("#mz").val(r.message.mz);
                    $("#tel").val(r.message.tel);
                    $("#address").val(r.message.address);
                    $("#school").val(r.message.school);
                    $("#schoolc").val(r.message.schoolc);
                    $("#major").val(r.message.major);
                    $("#gkbmh").val(r.message.gkbmh);
                    $("#contactAddress").val(r.message.lxaddress);
                    $("#yb").val(r.message.yb);
                } else {

                    $MB.n_danger(r.message);
                }
            }
        }
    )

}

/*编辑信息*/
function editUser() {

    // $MB.n_warning("请稍等，正在修改中...");
    // disabled Update Button
    $(".editUserButton").addClass("disabled");


    // console.log("register-click");
    var email = $("#email").val().trim();

    // 邮箱校验
    if (email === "") {
        $MB.n_warning("邮箱不能为空！");
        return;
    }

    // 性别校验
    var man = $("#optionsRadios3").is(":checked");
    var gril = $("#optionsRadios4").is(":checked");
    if (!man && !gril) {
        $MB.n_warning("请选择性别！");
        console.log("请选择性别！");
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

    // 政治面貌
    var zzmm = $("#zzmm option:selected").text();
    if (zzmm === "政治面貌") {
        $MB.n_warning("'政治面貌'不能为空！");
        return;
    } else {
        // setting value
        $("#zzmm option:selected").val(zzmm);
    }

    // 高考报名号
    var gkbmh = $("#gkbmh").val().trim();
    if (gkbmh.length !== 14) {
        $MB.n_warning("高考报名号不对，应该为14位数！");
        return;
    }

    $(".registration-form");
    // 发数据
    $.ajax(
        {
            type: "post",
            url: "/user/edit",
            data: $("#register-form").serialize(),
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
                    alert("同学恭喜你，资料修改成功!");
                    // window.location.reload();
                } else {
                    $MB.n_danger(data.message);
                }
            }
        }
    );

}


/*修改密码*/
function updatePassword() {
// .attr("readonly", true); //禁用输入
    // 验证密码
    var password1 = $("#password1").val().trim();
    var password2 = $("#password2").val().trim();

    if (password1.length < 6 && password2.length < 6) {
        $MB.n_warning("新密密码长度不能小于6位数");
        return;
    } else if (password1 !== password2) {
        $MB.n_warning("两次输入的密码不一致，请检查");
        return;
    }

    // disabled button
    $(".updatePasswordButton").attr("disabled", "disabled");

    $.ajax(
        {
            type: "post",
            url: "/user/updatePassword",
            data: $("#updatePassword-form").serialize(),
            dataType: "json",
            error: function (r) {

                // Enable Button
                $(".updatePasswordButton").removeAttr("disabled");

            },
            success: function (data) {
                // Enable Button
                $(".updatePasswordButton").removeAttr("disabled");

                if (data.code === 0) {
                    $MB.n_success("密码成功!");
                    $("#updatePassword-form")[0].reset();
                    alert("密码成功");
                    window.location.reload();
                } else {
                    $MB.n_warning(data.message);
                }
            }
        }
    );


}

/*代码-end*/
