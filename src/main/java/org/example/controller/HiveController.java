package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.dto.HiveInfo;
import org.example.dto.HiveSaveCommand;
import org.example.service.HiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/hive")
@Slf4j
public class HiveController {
    private final HiveService hiveService;

    @Autowired
    public HiveController(HiveService hiveService) {
        this.hiveService = hiveService;
    }

    @PostMapping
    public ResponseEntity<HiveInfo> saveHive(@Valid @RequestBody HiveSaveCommand command) {
        log.info ("Http request, POST / /api/hive, body: " + command.toString ());
        HiveInfo hiveInfo = hiveService.saveHive (command);
        return new ResponseEntity<> (hiveInfo, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<HiveInfo>> findAll() {
        log.info ("Http request, GET / /api/hive");
        List<HiveInfo> hiveList = hiveService.listHive ();
        return new ResponseEntity<> (hiveList, HttpStatus.OK);
    }

}
