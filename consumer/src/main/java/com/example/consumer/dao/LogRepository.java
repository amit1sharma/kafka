package com.example.consumer.dao;

import com.example.consumer.model.Log;
import org.springframework.data.repository.CrudRepository;

public interface LogRepository extends CrudRepository<Log, Long> {


}