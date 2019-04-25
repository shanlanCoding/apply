package cn.gobyte.apply.domain;

import java.util.HashMap;

/**
 * TODO: 返回消息到页面的类；描述了各种返回状态。
 *
 * @author shanLan misterchou@qq.com
 * @date 2019/4/21 22:45
 */
public class ResponseBo extends HashMap<String, Object> {

    private static final long serialVersionUID = -8713837118340960775L;

    /**
     * 成功
     */
    private static final Integer SUCCESS = 0;
    /**
     * 警告
     */
    private static final Integer WARN = 1;
    /**
     * 异常 失败
     */
    private static final Integer FAIL = 500;
    /**
     * 未认证
     */
    private static final Integer UNAUTHORIZED = 401;
    /**
     * 超频
     */
    private static final Integer OVERCLOCKING = 666;

    /**
     * TODO: 成功状态的方法。该方法是一个构造方法，所以如果需要返回消息成功的状态，直接new该类即可返回。例如：new ResponseBo()
     *
     * @param
     * @return :
     * @author shanLan misterchou@qq.com
     * @date 2019/4/21 22:44
     */
    public ResponseBo() {
        put("code", SUCCESS);
        put("message", "操作成功");
    }

    /**
     * TODO: 返回错误状态500
     *
     * @param message 返回的消息
     * @return cn.gobyte.apply.domain.ResponseBo: 该方法封装了一些返回状态。本方法返回失败状态码500，消息为message传入进来的内容
     * @author shanLan misterchou@qq.com
     * @date 2019/4/22 19:16
     */
    public static ResponseBo error(Object message) {
        ResponseBo responseBo = new ResponseBo();
        responseBo.put("code", FAIL);
        responseBo.put("message", message);
        return responseBo;
    }

    public static ResponseBo warn(Object message) {
        ResponseBo responseBo = new ResponseBo();
        responseBo.put("code", WARN);
        responseBo.put("message", message);
        return responseBo;
    }

    public static ResponseBo ok(Object message) {
        ResponseBo responseBo = new ResponseBo();
        responseBo.put("code", SUCCESS);
        responseBo.put("message", message);
        return responseBo;
    }

    /**
     * TODO: 跳转到之前引发登录跳转的链接；例如访问a链接被跳转到登陆页面login，那么登陆成功以后应该再跳转到a链接
     *
     * @param message 需要发送消息
     * @param url     url
     * @return cn.gobyte.apply.domain.ResponseBo:
     * @author shanLan misterchou@qq.com
     * @date 2019/4/23 5:32
     */
    public static ResponseBo ok(Object message, String url) {
        /*
        构造方法里已经有
         put("code", SUCCESS);
        put("message", "操作成功");
         */
        ResponseBo responseBo = new ResponseBo();
        //但是hashMap的特性之一是：key相同会替换，所以我们在下面添加元素的内容的时候，仅需要保证key一致就不会出错。
        responseBo.put("code", SUCCESS);
        responseBo.put("message", message);
        responseBo.put("url", url);
        return responseBo;
    }

    public static ResponseBo unAuthorized(Object message) {
        ResponseBo responseBo = new ResponseBo();
        responseBo.put("code", UNAUTHORIZED);
        responseBo.put("message", message);
        return responseBo;
    }

    public static ResponseBo overClocking(Object message) {
        ResponseBo responseBo = new ResponseBo();
        responseBo.put("code", OVERCLOCKING);
        responseBo.put("message", message);
        return responseBo;
    }

    /**
     * TODO: 返回成功状态
     *
     * @param
     * @return cn.gobyte.apply.domain.ResponseBo:
     * @author shanLan misterchou@qq.com
     * @date 2019/4/21 22:44
     */
    public static ResponseBo ok() {
        return new ResponseBo();
    }

    public static ResponseBo error() {
        return ResponseBo.error("");
    }

    @Override
    public ResponseBo put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
