package com.yx.kafka.exception;

import java.util.HashMap;
import java.util.Map;

public enum MqCode {
    SUCCESS(0, "成功"),
    FAIL(1, "失败");

    private int code;
    private String message;

    private MqCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private static Map<Integer, MqCode> codes = new HashMap();

    public static MqCode valueOf(int code) {
        if (codes.isEmpty()) {
            Map var1 = codes;
            synchronized(codes) {
                if (codes.isEmpty()) {
                    MqCode[] arr$ = values();
                    int len$ = arr$.length;

                    for(int i$ = 0; i$ < len$; ++i$) {
                        MqCode mqCode = arr$[i$];
                        codes.put(mqCode.code, mqCode);
                    }
                }
            }
        }

        return (MqCode)codes.get(code);
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage(Object... args) {
        return args.length < 1 ? this.message : String.format(this.message, args);
    }
}
