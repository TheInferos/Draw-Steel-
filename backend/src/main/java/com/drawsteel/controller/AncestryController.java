package com.drawsteel.controller;

import com.drawsteel.model.Ancestry;
import com.drawsteel.service.AncestryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ancestries")
public class AncestryController extends BaseController<Ancestry> {
    
    @Autowired
    public AncestryController(AncestryService ancestryService) {
        super(ancestryService);
    }
}
