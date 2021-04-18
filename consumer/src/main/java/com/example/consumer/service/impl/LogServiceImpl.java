package com.example.consumer.service.impl;

import com.example.consumer.dao.LogRepository;
import com.example.consumer.dto.MessageDto;
import com.example.consumer.model.Log;
import com.example.consumer.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogRepository logRepository;

    @Override
    public void saveLog(MessageDto dto){
        Log log = new Log(dto.getTime(),dto.getAppName(), dto.getContextId(),dto.getMessage(),dto.getLogLevel());
        logRepository.save(log);
    }

    @Override
    public Iterable<Log> getLogs(){
        return logRepository.findAll();
    }
}
