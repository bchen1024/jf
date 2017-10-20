package org.btsoft.jf.config.attachment.service.impl;

import javax.inject.Inject;
import javax.inject.Named;

import org.btsoft.jf.config.attachment.dao.IAttachmentDao;
import org.btsoft.jf.config.attachment.entity.AttachmentConstant;
import org.btsoft.jf.config.attachment.entity.AttachmentVO;
import org.btsoft.jf.config.attachment.service.IAttachmentService;
import org.btsoft.jf.core.annotation.JOperator;
import org.btsoft.jf.core.annotation.JResource;
import org.btsoft.jf.core.application.IRestService;
import org.btsoft.jf.core.constants.CoreConstant;
import org.btsoft.jf.core.entity.PageVO;
import org.btsoft.jf.core.entity.PagedResult;
import org.btsoft.jf.core.exception.ApplicationException;

/**
 * @ClassName AttachmentService
 * @Description 附件管理Service实现类
 * @author bchen
 * @created 2013-11-19 11:07:38
 */
@Named
@JResource(code = AttachmentConstant.RESOURCE_CODE, descCN = AttachmentConstant.RESOURCE_DESC_CN, descEN = AttachmentConstant.RESOURCE_DESC_EN)
public class AttachmentService implements IAttachmentService, IRestService {

	@Inject
	private IAttachmentDao attachmentDao;

	@Override
	@JOperator(code = CoreConstant.JOPERATOR_CODE_LIST, descCN = AttachmentConstant.LIST_DESC_CN, descEN = AttachmentConstant.LIST_DESC_EN)
	public PagedResult<AttachmentVO> findAttachmentList(AttachmentVO attachment, PageVO page)
			throws ApplicationException {
		return attachmentDao.findAttachmentList(attachment, page);
	}

	@Override
	@JOperator(descCN = AttachmentConstant.UPLOAD_DESC_CN, descEN = AttachmentConstant.UPLOAD_DESC_EN, isAudit = true)
	public Long createAttachment(AttachmentVO attachment) throws ApplicationException {
		attachmentDao.createAttachment(attachment);
		return attachment.getAttachmentId();
	}

	@Override
	@JOperator(descCN = AttachmentConstant.SINGLE_DESC_CN, descEN = AttachmentConstant.SINGLE_DESC_EN)
	public AttachmentVO findAttachment(AttachmentVO attachment) throws ApplicationException {
		return attachmentDao.findAttachment(attachment);
	}

	@Override
	@JOperator(descCN = AttachmentConstant.DOWNLOAD_DESC_CN, descEN = AttachmentConstant.DOWNLOAD_DESC_EN, isAudit = true)
	public AttachmentVO findDownloadAttachment(AttachmentVO attachment) throws ApplicationException {
		return attachmentDao.findAttachment(attachment);
	}
}
