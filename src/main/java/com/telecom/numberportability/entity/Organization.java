package com.telecom.numberportability.entity;

import javax.persistence.*;

@Entity
@Table(name = "organization")
public class Organization {

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "name",unique = true)
	private String name;

	@Column(name = "start_range")
	private Long startRange;
	@Column(name = "end_range")
	private Long endRange;

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Long getStartRange() {
		return startRange;
	}

	public Long getEndRange() {
		return endRange;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}


}
