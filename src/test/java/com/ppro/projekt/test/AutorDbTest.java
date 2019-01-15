package com.ppro.projekt.test;

import com.ppro.projekt.ProjektApplication;
import com.ppro.projekt.entity.Kniha;
import com.ppro.projekt.service.InitDbService;
import com.ppro.projekt.service.SpravaDb;
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
public class AutorDbTest {

    @Autowired
    private InitDbService initDbService;

    @Before
    public void initDb() {
        initDbService.initDb();
    }

    @Autowired
    private SpravaDb spravaDb;

    @Test
    public void testVlozAutora() {
        Kniha kniha = new Kniha("Test knihy", "Pouze pro testování", "test", "14.01.2019", 155, "Albatros", "11", 1, "CZ");
        spravaDb.vlozKnihu(kniha);
        spravaDb.vlozAutora(kniha.getId(), "Tester", "Test");
        Assert.assertTrue(spravaDb.existujeAutor("Tester"));
    }

}
