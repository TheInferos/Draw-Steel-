package com.drawsteel.controller;

import com.drawsteel.model.Culture;
import com.drawsteel.service.CultureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cultures")
public class CultureController extends BaseController<Culture> {
    
    @Autowired
    public CultureController(CultureService cultureService) {
        super(cultureService);
    }
}
