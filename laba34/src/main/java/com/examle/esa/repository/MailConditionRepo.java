package com.examle.esa.repository;

import com.examle.esa.entity.MailCondition;
import com.examle.esa.repository.base.BaseEntityRepo;
import org.springframework.stereotype.Repository;


@Repository
public interface MailConditionRepo extends BaseEntityRepo<MailCondition, Long> {
}
