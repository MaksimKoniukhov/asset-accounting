package com.maks.assetaccounting.vaadin.utils;

import java.time.format.DateTimeFormatter;

public class FormatterUtil {

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter
            .ofPattern("yyyy-MM-dd hh:mm");

    private FormatterUtil() {
    }
}
