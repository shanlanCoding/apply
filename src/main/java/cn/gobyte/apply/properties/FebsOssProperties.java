package cn.gobyte.apply.properties;

public class FebsOssProperties {

    private FebsQiniuProperties qiniu = new FebsQiniuProperties();

    public FebsQiniuProperties getQiniu() {
        return qiniu;
    }

    public void setQiniu(FebsQiniuProperties qiniu) {
        this.qiniu = qiniu;
    }
}
