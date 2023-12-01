package org.example.service;

import org.example.domain.Hive;
import org.example.dto.BeeInfo;
import org.example.dto.HiveInfo;
import org.example.dto.HiveSaveCommand;
import org.example.exception.HiveNotFoundException;
import org.example.repository.HiveRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class HiveService {
    private final HiveRepository hiveRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public HiveService(HiveRepository hiveRepository, ModelMapper modelMapper) {
        this.hiveRepository = hiveRepository;
        this.modelMapper = modelMapper;
    }

    public HiveInfo saveHive(HiveSaveCommand command) {
        Hive hive = modelMapper.map (command, Hive.class);
        Hive savedHive = hiveRepository.save (hive);
        return modelMapper.map (savedHive, HiveInfo.class);
    }

    public List<HiveInfo> listHive() {
        List<Hive> hiveList = hiveRepository.findAll ();
        List<HiveInfo> hiveInfoList = new ArrayList<> ();
        hiveList.forEach (hive -> {
            HiveInfo hiveInfo = modelMapper.map (hive, HiveInfo.class);
            List<BeeInfo> beeInfoList = hive.getBees ().stream ()
                    .map (bee -> modelMapper.map (bee, BeeInfo.class))
                    .collect (Collectors.toList ());
            hiveInfo.setBees (beeInfoList);
            hiveInfoList.add (hiveInfo);
        });
        return hiveInfoList;
    }

    public Hive findHiveById(Integer hiveId) {
        Optional<Hive> hiveOptional = hiveRepository.findById (hiveId.longValue ());
        if (hiveOptional.isEmpty ()) {
            throw new HiveNotFoundException (hiveId);
        }
        return hiveOptional.get ();
    }
}
