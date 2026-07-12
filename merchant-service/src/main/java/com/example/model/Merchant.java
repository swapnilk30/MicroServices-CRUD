package com.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "merchants")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Merchant {
	
	@Id
	private int merchant_id;
	
	private String merchant_name;

	private long inst_id;

	private String merchantWebsiteName;

	private String merchantWebsiteAddress;

}
