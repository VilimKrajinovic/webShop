package com.vkraji.webShop.models.logs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogService {

    @Autowired
    LogRepository logRepository;

    public Log save(Log log){
        return logRepository.save(log);
    }

    public List<Log> getAllLogs(){
        return logRepository.findAll();
    }
}
