package com.example.consumer.controller;

import com.example.consumer.model.Log;
import com.example.consumer.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yamraaj
 */
@RestController
public class LogViewController {

    @Autowired
    private LogService  logService;

    @GetMapping(value = "/logs")
    public Iterable<Log> findAll(){
        return logService.getLogs();
    }
}
