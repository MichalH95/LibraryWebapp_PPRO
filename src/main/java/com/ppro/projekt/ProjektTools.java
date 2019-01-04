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

    public static String email_rezervace_pripravena_subject = "Vaše rezervace je připravená";

    public static String email_rezervace_pripravena(String nazevKnihy) {
        return
                "Dobrý den,\n" +
                "\n" +
                "dovolujeme si vás informovat, že vaše rezervace na knihu " + nazevKnihy + " je připravena k vyzvednutí.\n" +
                "\n" +
                "Knihovna UHK";
    }

    public static String email_pozdni_vraceni_subject = "Pozdní vrácení knihy";

    public static String email_pozdni_vraceni(String nazevKnihy, Date vypujcenoDo) {
        return
                "Dobrý den,\n" +
                "\n" +
                "dovolujeme si vás upozornit, že vaše výpůjčka knihy " + nazevKnihy + " již přesáhla datum vrácení. Kniha byla vypůjčena do " + vypujcenoDo + ". Bude vám účtována pokuta 20 Kč. Knihu prosíme co nejdříve vraťte.\n" +
                "\n" +
                "Knihovna UHK";
    }

}
