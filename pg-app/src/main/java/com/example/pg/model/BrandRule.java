package com.example.pg.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BrandRule {

	private String brandRuleId;

	private String brandId;

	private String instrumentType;

	private Integer checkDigit;

	private Integer expDate;

	private Integer cvc;

	private Integer avs;

	private Integer pinDebit;

	private String extConnId;

	private String directoryPwd;

	private String directoryCertificate;

	private Integer cardParticipationCache;

	private String brandAcquirerBin;

	private long instId;


}
