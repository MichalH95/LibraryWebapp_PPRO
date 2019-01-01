package com.ppro.projekt;

import java.time.ZoneId;
import java.util.Date;

public class ProjektTools {

    public static String bytesToHex(byte[] bytes) {
        StringBuffer result = new StringBuffer();
        for (byte byt : bytes) result.append(Integer.toString((byt & 0xff) + 0x100, 16).substring(1));
        return result.toString();
    }

    public static Date datePlusDays(Date date, int days) {
        return Date.from(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().plusDays(days).atZone(ZoneId.systemDefault()).toInstant());
    }

}
