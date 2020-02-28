package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    private SharesGapUpsRepositary sharesGapUpsRepositary;
    @Autowired
    private CouchbaseTemplate couchbaseTemplate;

    @GetMapping("/getGapUpsByShareName")
    public List<ShareDetails> getGapUpsByShareName() {
        return couchbaseTemplate.findById("MFSL_gap_ups", SharesGapUps.class).getSHAREDETAILS();
    }
}
