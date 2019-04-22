$(document).ready(function () {

});

/*注册*/

function register() {

    // console.log("register-click");

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
        console.log("请选择性别！");
        return;
    }

    // 专业
    var major = $("#mcode option:selected").text();
    if (major === "报考专业") {
        $MB.n_warning("'报考专业'不能为空！");
        return;
    } else {
        // setting value
        $("#mcode option:selected").val(major);
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

                    // alert("同学恭喜你，账号注册成功！");
                    // window.location.reload();
                } else {
                    $MB.n_warning(data.msg);
                }
            }
        }
    );
}


/*表单登陆*/
function login() {
    var username = $("#username").val().trim();
    var password = $("#loginPassword").val().trim();
    var $form = $(".one").find("form");
    // 登陆按钮
    var $loginButton = $(".login");

    if (username === "") {
        $MB.n_warning("请输入账号！");
        return;
    }
    if (password === "") {
        $MB.n_warning("请输入密码！");
        return;
    }
    // 登陆按钮加载特效
    $loginButton.html("").append("<div class='login-loder'><div class='line-scale'><div></div><div></div><div></div><div></div><div></div></div></div>");
    $.ajax(
        {
            type: "post",
            url: "/login",
            data: $form.serialize(),
            dataType: "json",
            success: function(data) {
                console.log("data=" + JSON.stringify(data));
                // alert(JSON.stringify(data));
                if (data.code === 0) {
                    // 重置表单的输入框内容
                    $form[0].reset();
                    window.location.href = '/index';
                    // $form.attr("action", '/index');
                } else {
                    // if (r.msg !== '验证码不能为空！') reloadCode();
                    $MB.n_warning(data.msg);
                    $loginButton.text("确认登录");
                }
            }
        }
    );
    // return false;
}
