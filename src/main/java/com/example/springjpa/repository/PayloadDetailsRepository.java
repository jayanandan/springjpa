package com.example.springjpa.repository;

import com.example.springjpa.entity.PayloadEntity;
import org.springframework.data.repository.CrudRepository;

public interface PayloadDetailsRepository extends CrudRepository<PayloadEntity,Long> {
}
