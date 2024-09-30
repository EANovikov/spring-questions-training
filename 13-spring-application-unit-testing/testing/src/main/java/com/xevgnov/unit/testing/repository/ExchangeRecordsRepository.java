package com.xevgnov.unit.testing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xevgnov.unit.testing.entity.EcxcangeRecord;
import com.xevgnov.unit.testing.entity.ExchangeRecordId;

public interface ExchangeRecordsRepository extends JpaRepository<EcxcangeRecord, ExchangeRecordId> {
    
}
