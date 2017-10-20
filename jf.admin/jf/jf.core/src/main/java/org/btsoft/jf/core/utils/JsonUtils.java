package org.btsoft.jf.core.utils;

import java.io.IOException;
import java.io.OutputStream;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

public class JsonUtils {

	private static ObjectMapper mapper=new ObjectMapper();
	
	
	public static String objectToJsonStr(Object value) throws JsonGenerationException, JsonMappingException, IOException{
		return mapper.writeValueAsString(value);
	}
	
	public static String objectToJsonStr(Object value,boolean ignoreNull) throws JsonGenerationException, JsonMappingException, IOException{
		mapper.setSerializationInclusion(Inclusion.NON_EMPTY);
		String result=mapper.writeValueAsString(value);
		mapper.setSerializationInclusion(Inclusion.ALWAYS);
		return result;
	}
	
	public static void writeValue(OutputStream out,Object value) throws JsonGenerationException, JsonMappingException, IOException{
		mapper.writeValue(out, value);
		if(out!=null){
			out.close();
			out.flush();
		}
	}
}
