package com.vaigay.WebSpringBoot.Respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.vaigay.WebSpringBoot.Entity.ConfigScore;
import com.vaigay.WebSpringBoot.Entity.ConfigScoreDetail;

@Service
public interface ConfigScoreRepository extends JpaRepository<ConfigScore, Long>{
	
	
	ConfigScore findBySubjectId(long id);
	
	
}
