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
import sun.invoke.empty.Empty;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
        ProjektApplication.class,
        H2TestProfileJPAConfig.class})
@ActiveProfiles("test")
public class KnihaDbTest {

    @Autowired
    private UzivatelDb uzivatelDb;

    @Autowired
    private SpravaDb spravaDb;

    @Autowired
    private InitDbService initDbService;

    @Before
    public void initDb() {
        initDbService.initDb();
    }

    @Test
    public void testVlozKnihu(){
        Kniha kniha = new Kniha("Test knihy","Pouze pro testování","test","14.01.2019",155,"Albatros","11",1,"CZ");
        spravaDb.vlozKnihu(kniha);
        Assert.assertNotNull(spravaDb.najdiKnihu("Test knihy"));
    }

    @Test
    public void testUpravKnihu(){
        Kniha kniha = new Kniha("Test knihy","Pouze pro testování","test","14.01.2019",155,"Albatros","11",1,"CZ");
        spravaDb.vlozKnihu(kniha);
        spravaDb.upravKnihu(kniha.getId(),"Nový název","Cz","test","Albatros","14.01.2019","11",1,155,"CZ");
        Assert.assertNotNull(spravaDb.najdiKnihu("Nový název"));
    }

    @Test
    public void testOdstranKnihu()
    {
        Kniha kniha = new Kniha("Test knihy","Pouze pro testování","test","14.01.2019",155,"Albatros","11",1,"CZ");
        spravaDb.vlozKnihu(kniha);
        spravaDb.odstranKnihu(kniha.getId());
        Assert.assertFalse(spravaDb.existujeKniha("Test knihy"));
    }

}
