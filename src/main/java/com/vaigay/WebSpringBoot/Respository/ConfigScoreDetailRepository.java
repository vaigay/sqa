package com.vaigay.WebSpringBoot.Respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vaigay.WebSpringBoot.Entity.ConfigScoreDetail;

public interface ConfigScoreDetailRepository extends JpaRepository<ConfigScoreDetail,Long>{
	List<ConfigScoreDetail> findByConfigScore_SubjectId(long id);
}
