$(document).ready(function () {
    console.log(
        "Author：shan lan\r\n" +
        "Mail: misterchou@qq.com\r\n" +
        "Time：2019-03-16\r\n");
});

/*注册*/

function register() {

    // console.log("register-click");
    var registerBtn = $(".registerButton");

    var username = $("#idNumber").val().trim();
    var password = $("#password").val().trim();
    var cpassword = $("#password2").val().trim();
    var email = $("#email").val().trim();

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
    var man = $("#optionsRadios3").is(":checked");
    var gril = $("#optionsRadios4").is(":checked");
    if (!man && !gril) {
        $MB.n_warning("请选择性别！");
        // console.log("请选择性别！");
        return;
    }

    // 专业
    var major = $("#bkmajor option:selected").text();
    if (major === "报考专业") {
        $MB.n_warning("'报考专业'不能为空！");
        return;
    } else {
        // setting value
        $("#bkmajor option:selected").val(major);
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
                    $MB.n_success("同学恭喜你，账号注册成功!");
                    $(".registration-form")[0].reset();
                    alert("同学恭喜你，账号注册成功！");
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


/*表单登陆*/
function login() {
    var username = $("#username").val().trim();
    var password = $("#loginPassword").val().trim();
    var $form = $(".one").find("form");
    // 登陆按钮
    var loginBtn = $(".login");

    if (username === "") {
        $MB.n_warning("请输入账号！");
        return;
    }
    if (password === "") {
        $MB.n_warning("请输入密码！");
        return;
    }
    // 登陆按钮加载特效
    // $loginButton.html("").append("<div class='login-loder'><div class='line-scale'><div></div><div></div><div></div><div></div><div></div></div></div>");

    loginBtn.attr("disabled", true);
    loginBtn.text("正在登陆...");

    $.ajax(
        {
            type: "post",
            url: "/login",
            data: $form.serialize(),
            dataType: "json",
            error: function (data, type, err) {
                if (data.responseJSON != undefined) {
                    // console.log(data.responseJSON.error != undefined);
                    // console.log(JSON.stringify(data.responseJSON.error));
                    $MB.n_danger("error：" + JSON.stringify(data.responseJSON.error));
                }
            },
            success: function (data) {
                // console.log(JSON.stringify(data));
                // alert(JSON.stringify(data));
                if (data.code == 0) {
                    // 如果有url，则跳转该url
                    if ( data.url !== undefined ) {
                        $form[0].reset();
                        window.location.href = data.url;
                    } else {
                        // 重置表单的输入框内容
                        $form[0].reset();
                        window.location.href = '/index';
                        // $form.attr("action", '/index');
                    }
                } else {
                    // if (r.msg !== '验证码不能为空！') reloadCode();
                    $MB.n_warning(data.message);
                }
            },
        }
    );

    loginBtn.attr("disabled", false);
    loginBtn.text("确认登录");
    // return false;
}

/*查询成绩*/
function selectGrande() {
    // console.log($(this).text())
    var id = $("#grandeNumber").val();
    if (id.length < 18) {
        $MB.n_warning("身份证号不足18位，再检查下？");
        return;
    } else if (id.length > 18) {
        $MB.n_warning("身份证号超过18位，再检查下？");
        return;
    }

    var km1 = $(".km1");
    var km1f = $(".km1f");
    var km2 = $(".km2");
    var km2f = $(".km2f");
    var total = $(".total");
    var lq = $(".admissionState");

    if ($(this).text() == "查询") {
        $.ajax({
            type: "post",
            url: "/user/selectGrande",
            data: $(".grande-form").serialize(),
            dataType: "json",
            success: function (data) {
                if (data.code === 0) {
                    $MB.n_success("查询成功！");
                    $(".selectGrandeForm").toggle();
                    $(".grandeTable").toggle();
                    $(".selectGrandeButton").text("重新查询");

                    // 填充
                    km1.text(data.message.km1);
                    km1f.text(data.message.km1f);

                    km2.text(data.message.km2);
                    km2f.text(data.message.km2f);

                    total.text(data.message.km1f + data.message.km2f);

                    if (data.message.lq == null || data.message.lq == "") {
                        lq.text("暂未公布录取结果");
                    }

                } else {
                    $MB.n_warning(data.message);
                }
            },
            erroer: function (r) {
                if (typeof r.message != undefined) {
                    $MB.n_danger(r.message);
                }
            }
        });
    } else if ($(this).text() == "重新查询") {
        $(".selectGrandeForm").toggle();
        $(".grandeTable").toggle();
        $(".selectGrandeButton").text("查询");
    }

}

/* 找回密码 */
function retrievePassword() {
    // console.log($(this).text());

    var password = $("#id");
    var problem = $("#problem");
    var answer = $("#answer");
    var bt = $(".retrievePasswordButton");
    var bttx = $(this).text();

    var id = $("#id").val();
    if (id.length < 18) {
        $MB.n_warning("身份证号不足18位，再检查下？");
        return;
    } else if (id.length > 18) {
        $MB.n_warning("身份证号超过18位，再检查下？");
        return;
    }

    // readOnly
    problem.attr("readonly", true);
    if (bttx == "开始找回") {

        $.ajax(
            {
                type: "post",
                url: "/user/retrievePassword",
                data: $("#retrievePassword-form").serialize(),
                dataType: "json",
                error: function (data, type, err) {
                    if (typeof data.responseJSON != undefined) {
                        $MB.n_danger("error：" + data.message);
                    }
                },
                success: function (data) {
                    // console.log(JSON.stringify(data));
                    // alert(JSON.stringify(data));
                    if (data.code == 0) {

                        problem.val(data.message)
                        $(".problem").toggle();
                        $(".answer").toggle();
                        bt.text("提交答案");
                    } else {
                        $MB.n_warning(data.message);
                    }
                },

            }
        );
    } else if (bttx == "提交答案") {
        // console.log('if (bttx == "提交答案")');
        var pass = $(".password");
        pass.toggle();

        $.ajax(
            {
                type: "post",
                url: "/user/retrievePassword",
                data: $("#retrievePassword-form").serialize(),
                dataType: "json",
                error: function (data, type, err) {
                    if (typeof data.responseJSON != undefined) {
                        $MB.n_danger("error：" + data.message);
                    }
                },
                success: function (data) {
                    //  console.log(JSON.stringify(data));
                    // alert(JSON.stringify(data));
                    if (data.code == 0) {
                        problem.val(data.message)
                        $MB.n_success("请输入新密码.");
                        bt.text("修改密码");
                    } else {
                        $MB.n_warning(data.message);
                    }
                },

            }
        );
    } else if (bttx == "修改密码") {
        //  console.log('if (bttx == "修改密码")');
        var p1 = $("#pw1");
        var p2 = $("#pw2");
        if (p1.val().length < 6) {
            $MB.n_warning("第一次密码小于6位数");
            return;
        }
        if (p2.val().length < 6) {
            $MB.n_warning("第二次输入的密码小于6位数");
            return;
        }

        if (p2.val() != p1.val()) {
            $MB.n_warning("两次输入的密码不相同，请检查");
            return;
        }

        $.ajax(
            {
                type: "post",
                url: "/user/retrievePassword",
                data: $("#retrievePassword-form").serialize(),
                dataType: "json",
                error: function (data, type, err) {
                    if (typeof data.responseJSON != undefined) {
                        $MB.n_danger("error：" + data.message);
                    }
                },
                success: function (data) {
                    //  console.log(JSON.stringify(data));
                    // alert(JSON.stringify(data));
                    if (data.code == 0) {
                        problem.val(data.message)
                        $MB.n_success(data.message);

                        setTimeout(function () {
                            alert("同学，密码已经修改成功，点击确定后将刷新页面");
                            window.location.reload();
                        }, 1500);
                    } else {
                        $MB.n_warning(data.message);
                    }
                },

            }
        );
    }


}
