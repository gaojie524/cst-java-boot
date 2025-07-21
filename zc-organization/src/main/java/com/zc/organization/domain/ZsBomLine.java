package com.zc.organization.domain;


import com.baomidou.mybatisplus.annotation.TableField;
import com.zc.common.annotation.Excel;
import com.zc.common.core.domain.BaseEntity;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;


/**
 * BOM行对象 zs_bom_line
 *
 * @author zc
 * @date 2025-07-21
 */
@Data
@TableName("zs_bom_line")
public class ZsBomLine extends BaseEntity {

    private static final long serialVersionUID=1L;


    /** BOM行表ID */
    @TableId(value = "bom_line_id",type = IdType.AUTO)
    private Long bomLineId;

    /** BOM头表ID */
    private Long bomHeadId;

    /** 下位物料ID */
    @Excel(name = "下位物料ID")
    private Long childItemId;

    /** 排序 */
    @Excel(name = "排序")
    private Long sortOrder;

    /** 最后更新标识 */
    private String lastUpdateFlag;

    /** 删除标识   0删除  1查看 */
    private Long deleteId;

    /** 物料编号 */
    @TableField(exist = false)
    private String itemCode;

    /** 物料名称 */
    @TableField(exist = false)
    private String itemName;
}
