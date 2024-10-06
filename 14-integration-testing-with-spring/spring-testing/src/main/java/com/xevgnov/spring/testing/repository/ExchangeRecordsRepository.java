package com.xevgnov.spring.testing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xevgnov.spring.testing.entity.EcxcangeRecord;
import com.xevgnov.spring.testing.entity.ExchangeRecordId;

public interface ExchangeRecordsRepository extends JpaRepository<EcxcangeRecord, ExchangeRecordId> {
    
}
