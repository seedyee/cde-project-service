package io.cde.project.domain.i18n;

/**
 * @author lcl
 */
public enum Error {
    
    /**
     * 缺少必要参数.
     */
    MISS_REQUIRED_PARAMETER(114);

    /**
     * 用来计算错误码的常量.
     */
    private static final int SERVICE_COUNT = 10000;

    /**
     * 错误码.
     */
    private int code;

    /**
     * 私有构造方法,根据错误编号和服务编号生成错误码.
     *
     * @param code 错误码
     */
    Error(final int code) {
        this.code = ServiceCode.CDE_ACCOUNT_SERVICE.getCode() * SERVICE_COUNT + code;
    }

    /**
     * 获取错误码.
     *
     * @return 返回错误码.
     */
    public int getCode() {
        return code;
    }
}
