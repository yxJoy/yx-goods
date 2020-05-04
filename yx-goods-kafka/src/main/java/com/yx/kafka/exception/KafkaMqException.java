package com.yx.kafka.exception;

public class KafkaMqException extends Exception {
    private int code;

    public KafkaMqException(int code) {
        this.code = code;
    }

    public KafkaMqException(String message, int code) {
        super(message);
        this.code = code;
    }

    public KafkaMqException(MqCode code, Object... args) {
        super(code.getMessage(args));
        this.code = code.getCode();
    }

    public KafkaMqException(MqCode code, Throwable cause, Object... args) {
        super(code.getMessage(args), cause);
        this.code = code.getCode();
    }

    public KafkaMqException(String message, Throwable cause, int code) {
        super(message, cause);
        this.code = code;
    }

    public KafkaMqException(Throwable cause, int code) {
        super(cause);
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
