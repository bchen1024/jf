package org.btsoft.jf.config.attachment.dao;

import org.btsoft.jf.config.attachment.entity.AttachmentVO;
import org.btsoft.jf.core.entity.PageVO;
import org.btsoft.jf.core.entity.PagedResult;

/**
 * @ClassName IAttachmentDao
 * @Description 附件管理Dao接口
 * @author bchen
 * @created 2017-6-29 23:18:59
 */
public interface IAttachmentDao {

	/**
	 * @Description 创建单个附件
	 * @param attachment
	 * @return
	 * @author bchen
	 * @created 2013-11-20 11:27:36
	 */
	int createAttachment(AttachmentVO attachment);

	/**
	 * @Description 获取单个附件信息
	 * @param attachment
	 * @return
	 * @author bchen
	 * @created 2013-11-20 11:28:48
	 */
	AttachmentVO findAttachment(AttachmentVO attachment);

	/**
	 * @Description 分页获取附件信息
	 * @param attach
	 * @param page
	 * @return
	 * @author bchen
	 * @created 2014-1-9 11:14:43
	 */
	PagedResult<AttachmentVO> findAttachmentList(AttachmentVO attach, PageVO page);
}
