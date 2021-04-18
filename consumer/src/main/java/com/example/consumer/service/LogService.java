package com.example.consumer.service;

import com.example.consumer.dto.MessageDto;
import com.example.consumer.model.Log;

public interface LogService {
    void saveLog(MessageDto dto);

    Iterable<Log> getLogs();
}
