package com.knb.calocalws.common;

import java.util.Locale;

public interface MessageByLocaleService {
    String getMessage(String code, Object[] args, Locale locale);

    String getMessage(String code, Object[] args);

    String getMessage(String code);
}
