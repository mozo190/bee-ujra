package org.example.service;

import lombok.extern.slf4j.Slf4j;
import org.example.domain.Bee;
import org.example.domain.Hive;
import org.example.dto.BeeInfo;
import org.example.dto.BeeSaveCommand;
import org.example.dto.BeeUpdateCommand;
import org.example.exception.BeeNotFoundException;
import org.example.repository.BeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class BeeService {
    private final BeeRepository beeRepository;
    private final HiveService hiveService;
    private final ModelMapper modelMapper;

    @Autowired
    public BeeService(BeeRepository beeRepository, HiveService hiveService, ModelMapper modelMapper) {
        this.beeRepository = beeRepository;
        this.hiveService = hiveService;
        this.modelMapper = modelMapper;
    }

    public BeeInfo saveBee(BeeSaveCommand command) {
        Bee bee = modelMapper.map (command, Bee.class);
        Hive hiveById = hiveService.findHiveById (command.getHiveId ());
        bee.setHive (hiveById);
        Bee savedBee = beeRepository.save (bee);
        return modelMapper.map (savedBee, BeeInfo.class);
    }

    public List<BeeInfo> listBees() {
        return beeRepository.findAll ().stream ()
                .map (bee -> modelMapper.map (bee, BeeInfo.class))
                .collect (Collectors.toList ());
    }

    public BeeInfo getById(Integer id) {
        return modelMapper.map (findBeeById (id), BeeInfo.class);
    }

    private Bee findBeeById(Integer id) {
        Optional<Bee> beeOptional = beeRepository.findById (id.longValue ());
        if (beeOptional.isEmpty ()) {
            throw new BeeNotFoundException (id);
        }
        return beeOptional.get ();
    }

    public BeeInfo updateBee(Integer id, BeeUpdateCommand command) {
        Bee bee = findBeeById (id);
        modelMapper.map (command, bee);
        return modelMapper.map (bee, BeeInfo.class);
    }
}
