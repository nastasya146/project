package com.myapp.database.repository;

import com.myapp.database.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository <Status, Long>{
}
