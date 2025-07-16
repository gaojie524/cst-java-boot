package com.zc.documenter.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.zc.common.annotation.Excel;
import com.zc.common.core.domain.BaseEntity;

/**
 * 物料历史
对象 zs_item_history
 * 
 * @author ruoyi
 * @date 2025-07-10
 */
@Data
@TableName("zs_item_history")
public class ZsItemHistory extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 系统主健 */
    @TableId(value = "event_id", type = IdType.AUTO)
    private Long eventId;

    /** 物料ID */
    private Long itemId;

    /** 物料编号 */
    @Excel(name = "物料编号")
    private String itemCode;

    /** 物料名称 */
    @Excel(name = "物料名称")
    private String itemName;

    /** 物料单位 */
    @Excel(name = "物料单位")
    private String unitCode;

    /** 物料类别    原材料/半成品/成品 */
    @Excel(name = "物料类别    原材料/半成品/成品")
    private String itemCategory;

    /** 物料状态 */
    @Excel(name = "物料状态")
    private String itemStatus;

    /** 创建方式 */
    @Excel(name = "创建方式")
    private String creationMethod;

    /** 备注1 */
    @Excel(name = "备注1")
    private String remark1;

    /** 备注2 */
    @Excel(name = "备注2")
    private String remark2;

    /** 备注3 */
    @Excel(name = "备注3")
    private String remark3;

    /** 备注4 */
    @Excel(name = "备注4")
    private String remark4;

    /** 备注5 */
    @Excel(name = "备注5")
    private String remark5;

    /** 最后更新标识 */
//    @Excel(name = "最后更新标识")
    private String lastUpdateFlag;

    /** 操作标识 */
    @Excel(name = "操作标识")
    private String operation;

}
