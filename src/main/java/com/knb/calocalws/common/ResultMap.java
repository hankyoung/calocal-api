package com.knb.calocalws.common;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class ResultMap {
    private Map<String, Object> map = new HashMap<>();

    public ResultMap(String key, Object value) {
        map.put(key, value);
    }
}
