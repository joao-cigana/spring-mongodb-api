package com.joaocigana.mongoapi.controller.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

public class URL {

    public static String decodeParam(String text){
        try {
            return URLDecoder.decode(text, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    public static LocalDateTime convertDate(String strDate, LocalDateTime defaultDate){
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        try {
        return LocalDateTime.parse(strDate, dateTimeFormat);
        } catch (Exception ex){
            return defaultDate;
        }
    }

}
