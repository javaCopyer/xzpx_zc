package com.xzpx_zc.spring.view;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonGenerator;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.fasterxml.jackson.databind.SerializationFeature;

public class MappingJackson2JsonViewEx extends MappingJackson2JsonView{

	@Override
	protected Object filterModel(Map<String, Object> model) {
		for(Map.Entry<String, Object> entrys : model.entrySet()) {
			System.out.println("键：" + entrys.getKey());
			System.out.println("值：" + entrys.getValue());
		}
		return super.filterModel(model);
	}


	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Object value = filterModel(model);
        com.fasterxml.jackson.core.JsonGenerator generator =
            this.getObjectMapper().getJsonFactory().createJsonGenerator(response.getOutputStream());

this.getObjectMapper().configure(SerializationFeature.INDENT_OUTPUT,true);  
        this.getObjectMapper().writeValue(generator, value);
	}

	
	
	

	

	
}
