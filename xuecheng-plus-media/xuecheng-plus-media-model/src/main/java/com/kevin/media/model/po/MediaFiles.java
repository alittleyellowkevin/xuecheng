package com.kevin.media.model.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 媒资信息
 * </p>
 *
 * @author itcast
 */
@Data
@TableName("media_files")
public class MediaFiles implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;
    private Long companyId;
    private String companyName;
    private String filename;
    private String fileType;
    private String tags;
    private String bucket;

    private String filePath;
    private String fileId;
    private String url;
    private String username;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createDate;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime changeDate;
    private String status;
    private String remark;
    private String auditStatus;
    private String auditMind;
    private Long fileSize;

}
