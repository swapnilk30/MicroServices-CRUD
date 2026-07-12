package com.example.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchedulerService {
	
	@Scheduled(fixedRate = 3000)
	public void executeTask() {
        System.out.println("Task Executed");
    }

}
