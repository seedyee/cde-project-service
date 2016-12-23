package io.cde.project.domain.i18n;

/**
 * @author lcl
 */
public enum ServiceCode {

    /**
     * 项目服务.
     */
    CDE_ACCOUNT_SERVICE(101),
    /**
     * 系统.
     */
    SYSTEM(999);

    /**
     * 服务编号.
     */
    private int code;

    /**
     * 构造方法.
     *
     * @param code 服务代码
     */
    ServiceCode(final int code) {
        this.code = code;
    }

    /**
     * 获取服务编号.
     *
     * @return 返回服务代码.
     */
    public int getCode() {
        return code;
    }
}
