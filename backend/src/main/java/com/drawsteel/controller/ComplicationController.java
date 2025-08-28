package com.drawsteel.controller;

import com.drawsteel.model.Complication;
import com.drawsteel.service.ComplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/complications")
public class ComplicationController extends BaseController<Complication> {
    
    private final ComplicationService complicationService;
    
    @Autowired
    public ComplicationController(ComplicationService complicationService) {
        super(complicationService);
        this.complicationService = complicationService;
    }
    
    @GetMapping("/with-benefit")
    public ResponseEntity<List<Complication>> getComplicationsWithBenefit() {
        List<Complication> complications = complicationService.getComplicationsWithBenefit();
        return ResponseEntity.ok(complications);
    }
    
    @GetMapping("/with-drawback")
    public ResponseEntity<List<Complication>> getComplicationsWithDrawback() {
        List<Complication> complications = complicationService.getComplicationsWithDrawback();
        return ResponseEntity.ok(complications);
    }
    
    @GetMapping("/without-benefit-or-drawback")
    public ResponseEntity<List<Complication>> getComplicationsWithoutBenefitOrDrawback() {
        List<Complication> complications = complicationService.getComplicationsWithoutBenefitOrDrawback();
        return ResponseEntity.ok(complications);
    }
    
    @GetMapping("/with-combined-benefit-drawback")
    public ResponseEntity<List<Complication>> getComplicationsWithCombinedBenefitDrawback() {
        List<Complication> complications = complicationService.getComplicationsWithCombinedBenefitDrawback();
        return ResponseEntity.ok(complications);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<Complication>> searchComplications(@RequestParam String term) {
        List<Complication> complications = complicationService.searchComplications(term);
        return ResponseEntity.ok(complications);
    }
    
    @GetMapping("/search-benefit-drawback")
    public ResponseEntity<List<Complication>> searchByBenefitOrDrawback(@RequestParam String term) {
        List<Complication> complications = complicationService.searchByBenefitOrDrawback(term);
        return ResponseEntity.ok(complications);
    }
    
    @GetMapping("/search-combined-benefit-drawback")
    public ResponseEntity<List<Complication>> searchByCombinedBenefitDrawback(@RequestParam String term) {
        List<Complication> complications = complicationService.searchByCombinedBenefitDrawback(term);
        return ResponseEntity.ok(complications);
    }
    
    @GetMapping("/search-all-benefit-drawback")
    public ResponseEntity<List<Complication>> searchByAllBenefitDrawbackFields(@RequestParam String term) {
        List<Complication> complications = complicationService.searchByAllBenefitDrawbackFields(term);
        return ResponseEntity.ok(complications);
    }
}
