package org.openengine.pureengine.domain;

public class CommonUtil {

    public static final String DOMAIN_ROOT = "src/main/resources/db/pureengine/csv/";

    public static String [] parseCsv(String csvRecord) {
        return csvRecord.split(",");
    }
}
