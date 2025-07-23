package com.zc.organization.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.zc.common.annotation.Excel;
import com.zc.common.core.domain.BaseEntity;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;

import java.util.ArrayList;
import java.util.List;

/**
 * BOM头表对象 zc_bom_head
 *
 * @author zc
 * @date 2025-07-20
 */
@Data
@TableName("zc_bom_head")
public class ZcBomHead extends BaseEntity {

    private static final long serialVersionUID=1L;


    /** BOM头表ID */
    @TableId(value = "bom_head_id",type = IdType.AUTO)
    private Long bomHeadId;

    /** BOM头表编码 */
    @Excel(name = "BOM头表编码")
    private String bomCode;

    /** 上位物料ID   */
    private Long parentItemId;

    /** BOM版本号 */
    @Excel(name = "BOM版本号")
    private String bomVersion;

    /** BOM状态 */
    @Excel(name = "BOM状态")
    private String bomStatus;

    /** 创建方式 */
    @Excel(name = "创建方式")
    private String creationMethod;

    /** 备注1 */
    private String remark1;

    /** 备注2 */
    private String remark2;

    /** 备注3 */
    private String remark3;

    /** 备注4 */
    private String remark4;

    /** 备注5 */
    private String remark5;

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

    /** 行表id */
    @TableField(exist = false)
    private Long bomLineId;

    /** 行表排序*/
    @TableField(exist = false)
    private Long sortOrder;

    /** 行表连接id*/
    @TableField(exist = false)
    private Long bomHeadLineId;

    /** 子部门 */
    @TableField(exist = false)
    private List<ZcBomHead> children = new ArrayList<ZcBomHead>();
}