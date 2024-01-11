package com.examle.esa.repository;

import com.examle.esa.entity.LogEvent;
import com.examle.esa.repository.base.BaseEntityRepo;
import org.springframework.stereotype.Repository;


@Repository
public interface LogEventRepo extends BaseEntityRepo<LogEvent, Long> {
}