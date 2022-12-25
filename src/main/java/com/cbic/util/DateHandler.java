package com.cbic.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class DateHandler extends StdDeserializer<Date>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 988796033231911598L;

	public DateHandler() {
		this(null);
	}
	
	public DateHandler(Class<?> clazz) {
		super(clazz);
	}

	@Override
	public Date deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException {
		// TODO Auto-generated method stub
		String date = jsonParser.getText();
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			return simpleDateFormat.parse(date);
		}
		catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

}
