package com.example.batch.writer;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.model.Merchant;
import com.example.repository.MerchantRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MerchantWriter implements ItemWriter<Merchant> {
	
	@Autowired
    private MerchantRepository merchantRepository;
	
	
	@Override
	public void write(Chunk<? extends Merchant> items) throws Exception {
		log.info("Writing {} merchants to database", items.size());
		merchantRepository.saveAll(items);
	}

}
