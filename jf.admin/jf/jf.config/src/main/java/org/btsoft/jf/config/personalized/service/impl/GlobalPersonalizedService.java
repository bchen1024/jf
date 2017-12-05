package org.btsoft.jf.config.personalized.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.btsoft.jf.config.personalized.dao.IPersonalizedDao;
import org.btsoft.jf.config.personalized.entity.PersonalizedConstant;
import org.btsoft.jf.config.personalized.entity.PersonalizedVO;
import org.btsoft.jf.config.personalized.service.IGlobalPersonalizedService;
import org.btsoft.jf.config.property.dao.IPropertyDao;
import org.btsoft.jf.config.property.entity.PropertyVO;
import org.btsoft.jf.core.content.ICurrentService;
import org.btsoft.jf.core.content.RequestContext;
import org.btsoft.jf.core.utils.CollectionUtils;
import org.btsoft.jf.core.utils.JsonUtils;
import org.btsoft.jf.core.utils.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * @ClassName GlobalPersonalizedService
 * @Description 全局个性化设置
 * @author bchen
 * @created 2017年9月24日 下午11:26:34
 */
@Named
public class GlobalPersonalizedService implements ICurrentService,IGlobalPersonalizedService {
	
	@Inject
	private IPersonalizedDao dao;
	
	@Inject
	private IPropertyDao propertyDao;
	
	@Override
	public Map<String, Object> findCurrentData() {
		Map<String, Object> current=new HashMap<String, Object>();
		Map<String,Object> global=new HashMap<String,Object>();
		
		//获取系统字典全局配置数据
		PropertyVO property=new PropertyVO();
		property.setPropertyType(PersonalizedConstant.GLOBAL);
		List<PropertyVO> propertys=propertyDao.findPropertys(property);
		//将数据字典全局配置结果转换成Map
		if(!CollectionUtils.isNullOrEmpty(propertys)) {
			for (PropertyVO propertyVO : propertys) {
				if(!StringUtils.isEmpty(propertyVO.getPropertyKey()) && 
						!StringUtils.isEmpty(propertyVO.getPropertyValue())) {
					global.put(propertyVO.getPropertyKey(), propertyVO.getPropertyValue());
				}
			}
		}
		
		//个性化全局配置覆盖数据字典全局配置
		global.putAll(findGlobalPersonalized());
		
		current.put(PersonalizedConstant.GLOBAL, global);
		return current;
	}

	@Override
	public void saveGlobalPersonalized(HashMap<String, Object> content) {
		Map<String, Object> global=findGlobalPersonalized();
		global.putAll(content);
		PersonalizedVO personalized=new PersonalizedVO();
		personalized.setPersonalizedKey(PersonalizedConstant.GLOBAL);
		dao.deletePersonalized(personalized);
		try {
			personalized.setPersonalizedContent(JsonUtils.objectToJsonStr(global));
			dao.savePersonalized(personalized);
			if(content.containsKey("currentLanguage")){
				RequestContext.getCurrent().setCurrentLanguage(content.get("currentLanguage").toString());
			}
		} catch (Exception e) {
			
		} 
		
	}
	
	@Override
	public Map<String,Object> findGlobalPersonalized(){
		Map<String, Object> global=new HashMap<String, Object>();
		
		PersonalizedVO personalized=new PersonalizedVO();
		personalized.setPersonalizedKey(PersonalizedConstant.GLOBAL);
		personalized=dao.findPersonalized(personalized);
		if(personalized!=null && !StringUtils.isEmpty(personalized.getPersonalizedContent())) {
			ObjectMapper mapper=new ObjectMapper();
			try {
				global=mapper.readValue(personalized.getPersonalizedContent(), Map.class);
			} catch (Exception e) {
				
			} 
		}
		
		return global;
	}

}
