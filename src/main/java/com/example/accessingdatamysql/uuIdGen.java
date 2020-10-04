package com.example.accessingdatamysql;

import java.util.UUID;
import com.fasterxml.uuid.Generators;

import org.springframework.stereotype.Component;

@Component
public class uuIdGen {
	
	public String getUUid() {
		UUID uuid1 = Generators.timeBasedGenerator().generate();
		return uuid1.toString();
		
	}

}
