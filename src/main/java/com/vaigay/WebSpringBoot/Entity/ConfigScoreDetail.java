package com.vaigay.WebSpringBoot.Entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class ConfigScoreDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String name;
	private int percent;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "configSocre_id", updatable = true, insertable = true)
	private ConfigScore configScore;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPercent() {
		return percent;
	}

	public void setPercent(int percent) {
		this.percent = percent;
	}

	public ConfigScore getConfigScore() {
		return configScore;
	}

	public void setConfigScore(ConfigScore configScore) {
		this.configScore = configScore;
	}
}	
