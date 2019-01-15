package com.ppro.projekt.test;

import com.ppro.projekt.ProjektApplication;
import com.ppro.projekt.entity.Kniha;
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

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
        ProjektApplication.class,
        H2TestProfileJPAConfig.class})
@ActiveProfiles("test")
public class VypujckaDbTest {

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
    public void testVypujcky() {
        Kniha kniha = new Kniha("Test knihy", "Pouze pro testování", "test", "14.01.2019", 155, "Albatros", "11", 1, "CZ");
        spravaDb.vlozKnihu(kniha);
        uzivatelDb.nastavitVypujcku(kniha.getId(), "test@test.cz");
        Assert.assertTrue(uzivatelDb.existujeVypujcka("test@test.cz"));
    }
}
