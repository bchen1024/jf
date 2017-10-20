package org.btsoft.jf.config.personalized.entity;

import org.btsoft.jf.core.entity.BaseVO;

/**
 * @ClassName PersonalizedVO
 * @Description 个性化设置VO
 * @author bchen
 * @created 2017年8月5日 上午10:47:16
 */
public class PersonalizedVO extends BaseVO {

	private static final long serialVersionUID = 5482631789156518686L;

	private Long personalizedId;

	private String personalizedKey;

	private String personalizedContent;

	private Long userId;

	public Long getPersonalizedId() {
		return personalizedId;
	}

	public void setPersonalizedId(Long personalizedId) {
		this.personalizedId = personalizedId;
	}

	public String getPersonalizedKey() {
		return personalizedKey;
	}

	public void setPersonalizedKey(String personalizedKey) {
		this.personalizedKey = personalizedKey;
	}

	public String getPersonalizedContent() {
		return personalizedContent;
	}

	public void setPersonalizedContent(String personalizedContent) {
		this.personalizedContent = personalizedContent;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
