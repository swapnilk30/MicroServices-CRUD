package com.example.dto;

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
public class MerchantDto {
	
	private int merchantId;

	private String merchant_name;

	private long inst_id;

	private String cur_code;

	private String mrch_ctg;

	private String chargeflg;

	private String cvd2_flg;

	private String view_hldr_flag;

	private String merchantWebsiteName;

	private String merchantWebsiteAddress;

}
