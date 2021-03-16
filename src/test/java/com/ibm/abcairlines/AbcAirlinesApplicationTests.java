package com.ibm.abcairlines;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
public class AbcAirlinesApplicationTests {

	@Test
	void contextLoads() {
	}

	public static String asJsonString(final Object obj) {
		try {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			final ObjectMapper mapper = new ObjectMapper();
			mapper.setDateFormat(df);
			final String jsonContent = mapper.writeValueAsString(obj);
			return jsonContent;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
