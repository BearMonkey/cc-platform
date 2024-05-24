package org.monkey.wxpay.exception;

/**
 * 微信支付异常
 * @author cc
 */
public class WxPayException extends Exception{
    public WxPayException() {
        super();
    }

    public WxPayException(String message) {
        super(message);
    }

    public WxPayException(String message, Throwable cause) {
        super(message, cause);
    }

    public WxPayException(Throwable cause) {
        super(cause);
    }

    protected WxPayException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
