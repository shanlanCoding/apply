!function (k) {
    var C = "iCheck", w = C + "-helper", x = "checkbox", A = "radio", H = "checked", p = "un" + H, j = "disabled",
        h = "determinate", D = "in" + h, P = "update", T = "type", F = "click", I = "touchbegin.i touchend.i",
        L = "addClass", M = "removeClass", N = "trigger", Q = "label", v = "cursor",
        S = /ipad|iphone|ipod|android|blackberry|windows phone|opera mini|silk/i.test(navigator.userAgent);

    function U(e, t, i) {
        var a = e[0], s = /er/.test(i) ? D : /bl/.test(i) ? j : H, n = i == P ? {
            checked: a[H],
            disabled: a[j],
            indeterminate: "true" == e.attr(D) || "false" == e.attr(h)
        } : a[s];
        if (/^(ch|di|in)/.test(i) && !n) Z(e, s); else if (/^(un|en|de)/.test(i) && n) $(e, s); else if (i == P) for (var r in n) n[r] ? Z(e, r, !0) : $(e, r, !0); else t && "toggle" != i || (t || e[N]("ifClicked"), n ? a[T] !== A && $(e, s) : Z(e, s))
    }

    function Z(e, t, i) {
        var a = e[0], s = e.parent(), n = t == H, r = t == D, o = t == j, d = r ? h : n ? p : "enabled",
            c = b(e, d + g(a[T])), l = b(e, t + g(a[T]));
        if (!0 !== a[t]) {
            if (!i && t == H && a[T] == A && a.name) {
                var u = e.closest("form"), f = 'input[name="' + a.name + '"]';
                (f = u.length ? u.find(f) : k(f)).each(function () {
                    this !== a && k(this).data(C) && $(k(this), t)
                })
            }
            r ? (a[t] = !0, a[H] && $(e, H, "force")) : (i || (a[t] = !0), n && a[D] && $(e, D, !1)), y(e, n, t, i)
        }
        a[j] && b(e, v, !0) && s.find("." + w).css(v, "default"), s[L](l || b(e, t) || ""), s.attr("role") && !r && s.attr("aria-" + (o ? j : H), "true"), s[M](c || b(e, d) || "")
    }

    function $(e, t, i) {
        var a = e[0], s = e.parent(), n = t == H, r = t == D, o = t == j, d = r ? h : n ? p : "enabled",
            c = b(e, d + g(a[T])), l = b(e, t + g(a[T]));
        !1 !== a[t] && (!r && i && "force" != i || (a[t] = !1), y(e, n, d, i)), !a[j] && b(e, v, !0) && s.find("." + w).css(v, "pointer"), s[M](l || b(e, t) || ""), s.attr("role") && !r && s.attr("aria-" + (o ? j : H), "false"), s[L](c || b(e, d) || "")
    }

    function q(e, t) {
        e.data(C) && (e.parent().html(e.attr("style", e.data(C).s || "")), t && e[N](t), e.off(".i").unwrap(), k(Q + '[for="' + e[0].id + '"]').add(e.closest(Q)).off(".i"))
    }

    function b(e, t, i) {
        if (e.data(C)) return e.data(C).o[t + (i ? "" : "Class")]
    }

    function g(e) {
        return e.charAt(0).toUpperCase() + e.slice(1)
    }

    function y(e, t, i, a) {
        a || (t && e[N]("ifToggled"), e[N]("ifChanged")[N]("if" + g(i)))
    }

    k.fn[C] = function (t, i) {
        var a = 'input[type="' + x + '"], input[type="' + A + '"]', s = k(), e = function (e) {
            e.each(function () {
                var e = k(this);
                s = e.is(a) ? s.add(e) : s.add(e.find(a))
            })
        };
        if (/^(check|uncheck|toggle|indeterminate|determinate|disable|enable|update|destroy)$/i.test(t)) return t = t.toLowerCase(), e(this), s.each(function () {
            var e = k(this);
            "destroy" == t ? q(e, "ifDestroyed") : U(e, !0, t), k.isFunction(i) && i()
        });
        if ("object" != typeof t && t) return this;
        var p = k.extend({checkedClass: H, disabledClass: j, indeterminateClass: D, labelHover: !0}, t), n = p.handle,
            h = p.hoverClass || "hover", v = p.focusClass || "focus", b = p.activeClass || "active", g = !!p.labelHover,
            y = p.labelHoverClass || "hover", m = 0 | ("" + p.increaseArea).replace("%", "");
        return n != x && n != A || (a = 'input[type="' + n + '"]'), m < -50 && (m = -50), e(this), s.each(function () {
            var a = k(this);
            q(a);
            var e, s = this, t = s.id, i = -m + "%", n = 100 + 2 * m + "%", r = {
                    position: "absolute",
                    top: i,
                    left: i,
                    display: "block",
                    width: n,
                    height: n,
                    margin: 0,
                    padding: 0,
                    background: "#fff",
                    border: 0,
                    opacity: 0
                }, o = S ? {position: "absolute", visibility: "hidden"} : m ? r : {position: "absolute", opacity: 0},
                d = s[T] == x ? p.checkboxClass || "i" + x : p.radioClass || "i" + A,
                c = k(Q + '[for="' + t + '"]').add(a.closest(Q)), l = !!p.aria,
                u = C + "-" + Math.random().toString(36).substr(2, 6),
                f = '<div class="' + d + '" ' + (l ? 'role="' + s[T] + '" ' : "");
            l && c.each(function () {
                f += 'aria-labelledby="', this.id ? f += this.id : (this.id = u, f += u), f += '"'
            }), f = a.wrap(f + "/>")[N]("ifCreated").parent().append(p.insert), e = k('<ins class="' + w + '"/>').css(r).appendTo(f), a.data(C, {
                o: p,
                s: a.attr("style")
            }).css(o), p.inheritClass && f[L](s.className || ""), p.inheritID && t && f.attr("id", C + "-" + t), "static" == f.css("position") && f.css("position", "relative"), U(a, !0, P), c.length && c.on(F + ".i mouseover.i mouseout.i " + I, function (e) {
                var t = e[T], i = k(this);
                if (!s[j]) {
                    if (t == F) {
                        if (k(e.target).is("a")) return;
                        U(a, !1, !0)
                    } else g && (/ut|nd/.test(t) ? (f[M](h), i[M](y)) : (f[L](h), i[L](y)));
                    if (!S) return !1;
                    e.stopPropagation()
                }
            }), a.on(F + ".i focus.i blur.i keyup.i keydown.i keypress.i", function (e) {
                var t = e[T], i = e.keyCode;
                return t != F && ("keydown" == t && 32 == i ? (s[T] == A && s[H] || (s[H] ? $(a, H) : Z(a, H)), !1) : void ("keyup" == t && s[T] == A ? !s[H] && Z(a, H) : /us|ur/.test(t) && f["blur" == t ? M : L](v)))
            }), e.on(F + " mousedown mouseup mouseover mouseout " + I, function (e) {
                var t = e[T], i = /wn|up/.test(t) ? b : h;
                if (!s[j]) {
                    if (t == F ? U(a, !1, !0) : (/wn|er|in/.test(t) ? f[L](i) : f[M](i + " " + b), c.length && g && i == h && c[/ut|nd/.test(t) ? M : L](y)), !S) return !1;
                    e.stopPropagation()
                }
            })
        })
    }
}(window.jQuery || window.Zepto);