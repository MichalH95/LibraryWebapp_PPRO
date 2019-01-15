package com.ppro.projekt.test;

import com.ppro.projekt.ProjektApplication;
import com.ppro.projekt.entity.Kniha;
import com.ppro.projekt.entity.Rezervace;
import com.ppro.projekt.entity.Uzivatel;
import com.ppro.projekt.service.InitDbService;
import com.ppro.projekt.service.SpravaDb;
import com.ppro.projekt.service.UzivatelDb;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
        ProjektApplication.class,
        H2TestProfileJPAConfig.class})
@ActiveProfiles("test")
public class RezervaceDbTest {

    @Autowired
    private InitDbService initDbService;

    @Before
    public void initDb() {
        initDbService.initDb();
    }

    @Autowired
    private UzivatelDb uzivatelDb;

    @Autowired
    private SpravaDb spravaDb;


    @Test
    public void testRezervace() {
        Kniha kniha = new Kniha("Test knihy", "Pouze pro testování", "test", "14.01.2019", 155, "Albatros", "11", 0, "cz");
        spravaDb.vlozKnihu(kniha);
        Uzivatel uzivatel = new Uzivatel("a", "a", "a", "a", "a", 3, "a@test.cz", "a", false, 0);
        uzivatelDb.vlozUzivatele(uzivatel);
        Rezervace rezervace = new Rezervace(1, new Date(), new Date());
        rezervace.setKniha(kniha);
        rezervace.setUzivatel(uzivatel);
        spravaDb.vlozRezervaci(rezervace);
        Assert.assertTrue(uzivatelDb.existujeRezervace("a@test.cz"));
    }

}
