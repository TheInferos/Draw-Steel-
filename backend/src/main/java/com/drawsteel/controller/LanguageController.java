package com.drawsteel.controller;

import com.drawsteel.model.Language;
import com.drawsteel.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/languages")
public class LanguageController extends BaseController<Language> {
    
    @Autowired
    public LanguageController(LanguageService languageService) {
        super(languageService);
    }
}
