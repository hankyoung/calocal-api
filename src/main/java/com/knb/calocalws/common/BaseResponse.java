package com.knb.calocalws.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse {
    private Object data;
    private String message;
    private String errorCode;

    public BaseResponse(Object data, String message) {
        this.data = data;
        this.message = message;
        this.errorCode = "00";
    }

    public BaseResponse(String message, String errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }
}
