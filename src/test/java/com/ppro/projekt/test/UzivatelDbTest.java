package com.ppro.projekt.test;

import com.ppro.projekt.ProjektApplication;
import com.ppro.projekt.entity.Kniha;
import com.ppro.projekt.entity.Uzivatel;
import com.ppro.projekt.service.InitDbService;
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
public class UzivatelDbTest {

    @Autowired
    private UzivatelDb uzivatelDb;

    @Autowired
    private InitDbService initDbService;

    @Before
    public void initDb() {
        initDbService.initDb();
    }

    @Test
    public void testExistujeUzivatel() {
        Assert.assertTrue(uzivatelDb.existujeUzivatel("test@test.cz"));
    }

    @Test
    public void testVlozUzivatele() {
        uzivatelDb.vlozUzivatele(new Uzivatel("a","a","a","a","a",3,"a@test.cz","a",true,0));
        Assert.assertTrue(uzivatelDb.existujeUzivatel("a@test.cz"));
    }

    @Test
    public void testAutorizace(){
        uzivatelDb.vlozUzivatele(new Uzivatel("a","a","a","a","a",3,"a@test.cz","a",false,0));
        Assert.assertTrue(uzivatelDb.overlogin("a@test.cz","a"));
    }

    @Test
    public void testOdstranUzivatele(){
        Uzivatel uzivatel = new Uzivatel("a","a","a","a","a",3,"a@test.cz","a",true,0);
        uzivatelDb.vlozUzivatele(uzivatel);
        uzivatelDb.odstranUzivatele(uzivatel.getId());
        Assert.assertFalse(uzivatelDb.existujeUzivatel("a@test.cz"));
    }

    @Test
    public void testUlozBlokace(){
        Uzivatel uzivatel = new Uzivatel("a","a","a","a","a",3,"a@test.cz","a",false,0);
        uzivatelDb.vlozUzivatele(uzivatel);
        uzivatelDb.blokovatUzivatele(uzivatel.getId());
        Assert.assertTrue(uzivatelDb.najdiUzivatele("a@test.cz").getBlokace());
    }




}
