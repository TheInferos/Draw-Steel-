package com.drawsteel.controller;

import com.drawsteel.model.Kit;
import com.drawsteel.service.KitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kits")
public class KitController extends BaseController<Kit> {
    
    @Autowired
    public KitController(KitService kitService) {
        super(kitService);
    }
}
