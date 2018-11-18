package com.ppro.projekt.web;

import com.ppro.projekt.service.InitDbService;
import com.ppro.projekt.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BaseController {

    private UniversityService universityService;
    private InitDbService initDbService;

    public BaseController(@Autowired UniversityService universityService,
                          @Autowired InitDbService initDbService) {
        this.universityService = universityService;
        this.initDbService = initDbService;
    }

    @RequestMapping("/")
    @ResponseBody
    public String zobrazeni() {

        initDbService.initDb();

        return "Univerzita v databazi: \n" + universityService.findAllUniversitys().get(0).toString();
    }

}
