package org.btsoft.jf.config.attachment.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.btsoft.jf.config.attachment.entity.AttachmentVO;
import org.btsoft.jf.core.constants.CoreConstant;
import org.btsoft.jf.core.entity.PageVO;
import org.btsoft.jf.core.entity.PagedResult;
import org.btsoft.jf.core.exception.ApplicationException;

/**
 * @ClassName IAttachmentService
 * @Description 附件Service接口
 * @author bchen
 * @created 2017-6-30 00:14:45
 */
@Path("/attachment")
@Produces(MediaType.APPLICATION_JSON)
public interface IAttachmentService {

	/**
	 * @Description 分页查找附件
	 * @param attachment
	 * @param page
	 * @return
	 * @author bchen
	 * @created 2013-12-3 10:40:10
	 */
	@GET
	@Path(CoreConstant.PATH_LIST)
	public PagedResult<AttachmentVO> findAttachmentList(@QueryParam("") AttachmentVO attachment,
			@QueryParam("") PageVO page) throws ApplicationException;

	/**
	 * @Description 创建单个附件记录
	 * @param attachment
	 * @return id
	 * @author bchen
	 * @created 2013-12-7 11:14:04
	 */
	public Long createAttachment(AttachmentVO attachment) throws ApplicationException;

	/**
	 * @Description 查找单个附件
	 * @param attachment
	 * @return
	 * @author bchen
	 * @created 2013-12-3 10:44:53
	 */
	public AttachmentVO findAttachment(AttachmentVO attachment) throws ApplicationException;

	/**
	 * @Description 查找单个下载附件
	 * @param attachment
	 * @return
	 * @author bchen
	 * @created 2013-12-3 10:44:53
	 */
	public AttachmentVO findDownloadAttachment(AttachmentVO attachment) throws ApplicationException;
}
