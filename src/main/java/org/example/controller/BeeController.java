package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.dto.BeeInfo;
import org.example.dto.BeeSaveCommand;
import org.example.dto.BeeUpdateCommand;
import org.example.service.BeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/bee")
@Slf4j
public class BeeController {

    private final BeeService beeService;

    @Autowired
    public BeeController(BeeService beeService) {
        this.beeService = beeService;
    }

    @PostMapping
    public ResponseEntity<BeeInfo> saveBee(@Valid @RequestBody BeeSaveCommand command) {
        log.info ("Http request, POST / /api/bee, body: " + command.toString ());
        BeeInfo beeInfo = beeService.saveBee (command);
        return new ResponseEntity<> (beeInfo, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BeeInfo>> findAllBee() {
        log.info ("Http request, GET / /api/hive");
        List<BeeInfo> beeList = beeService.listBees ();
        return new ResponseEntity<> (beeList, HttpStatus.OK);
    }

    @GetMapping("/{beeId}")
    public ResponseEntity<BeeInfo> findById(@PathVariable("beeId") Integer id) {
        log.info ("Http request, GET / /api/bee/{beeId} with variable: " + id);
        BeeInfo bee = beeService.getById (id);
        return new ResponseEntity<> (bee, HttpStatus.OK);
    }

    @PutMapping("/{beeId}")
    public ResponseEntity<BeeInfo> update(@PathVariable("beeId") Integer id,
                                          @Valid @RequestBody BeeUpdateCommand command) {
        log.info ("Http request, PUT /api/bee/{beeId} body: " + command.toString () + " with variable: " + id);
        BeeInfo bee = beeService.updateBee (id, command);
        return new ResponseEntity<> (bee, HttpStatus.OK);
    }
}
