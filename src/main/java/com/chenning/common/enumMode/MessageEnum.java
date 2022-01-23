package com.chenning.common.enumMode;


import java.io.Serializable;

/**
 * @author nchen
 * @version 1.0
 * @date 2021/3/2 15:33
 */
public class MessageEnum implements Serializable {
    public enum SendType {
        MAIL(new String("邮件")), DDING(new String("钉钉"));
        private String value;
        SendType(String value) {
            this.value = value;
        }
        public String getValue() {
            return this.value;
        }
    }
    public enum MessageType {
        ISSUE(new String("风险问题")),INDICATOR(new String("指标")),
        OTHERS(new String("其他"));
        private String value;
        MessageType(String value) {
            this.value = value;
        }
        public String getValue() {
            return this.value;
        }
    }
    public enum SendResult {
        SUCCESS(new Integer(1)),ERROR(new Integer(2));
        private Integer state;
        SendResult(Integer state) {
            this.state = state;
        }
        public Integer getValue() {
            return this.state;
        }
    }

}
