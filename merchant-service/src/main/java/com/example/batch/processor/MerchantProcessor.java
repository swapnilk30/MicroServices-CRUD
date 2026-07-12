package com.example.batch.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.example.dto.MerchantDto;
import com.example.model.Merchant;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class MerchantProcessor implements ItemProcessor<MerchantDto, Merchant>{

	@Override
	public Merchant process(MerchantDto item) throws Exception {
		
		
		//logic to validations
		
		// Convert DTO to Entity
        Merchant merchant = new Merchant();
        
        merchant.setMerchantId(item.getMerchantId());
        merchant.setMerchant_name(item.getMerchant_name());
        merchant.setInst_id(item.getInst_id());
        merchant.setMerchantWebsiteName(item.getMerchantWebsiteName());
        merchant.setMerchantWebsiteAddress(item.getMerchantWebsiteAddress());
        
        return merchant;
    	
	}

}
