package com.ts.previsao.tempo.utils;

public class CommonsUtils {

    public static String removeXMLMetaData(String xml) {
        return xml.replace("<?xml version='1.0' encoding='ISO-8859-1'?>", "");
    }
}
