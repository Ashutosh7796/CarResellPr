package com.spring.jwt.jwt.repository;

import com.spring.jwt.entity.BeadingCAR;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface BeadingCarRepo extends JpaRepository<BeadingCAR, Integer>, JpaSpecificationExecutor<BeadingCAR> {

    List<BeadingCAR> findByUserId (int userId);


}
