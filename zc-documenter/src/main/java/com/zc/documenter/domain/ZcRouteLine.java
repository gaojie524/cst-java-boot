package com.zc.documenter.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.zc.common.annotation.Excel;
import com.zc.common.core.domain.BaseEntity;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;


/**
 * 工艺路线行对象 zc_route_line
 *
 * @author zc
 * @date 2025-07-26
 */
@Data
@TableName("zc_route_line")
public class ZcRouteLine extends BaseEntity {

    private static final long serialVersionUID=1L;


    /** 工艺路线行ID */
    @TableId(value = "route_line_id",type = IdType.AUTO)
    private Long routeLineId;

    /** 工艺路线头ID */
    private Long routeHeadId;

    /** 工序ID */
    private Long processId;

    /** 工序名称 */
    @TableField(exist = false)
    private String processName;


    /** 排序 */
    @Excel(name = "排序")
    private Long sortOrder;

    /** 最后更新人 */
    @Excel(name = "最后更新人")
    private Long updatedBy;

    /** 最后更新标识 */
    @Excel(name = "最后更新标识")
    private String lastUpdateFlag;

    /** 一级标准时间 */
    @Excel(name = "一级标准时间")
    private Long oneLevelStandardTime;

    /** 一级责任人名称 */
    @Excel(name = "一级责任人")

    private Long oneLevelPersonCharge;

    /** 一级责任人名称*/
    @TableField(exist = false)
    private String oneLevelPersonChargeName;



    /** 二级标准时间 */
    @Excel(name = "二级标准时间")
    private Long twoLevelStandardTime;

    /** 二级责任人 */
    @Excel(name = "二级责任人")
    private Long twoLevelPersonCharge;

    /** 二级责任人名称 */
    @TableField(exist = false)
    private String twoLevelPersonChargeName;

    /** 三级标准时间 */
    @Excel(name = "三级标准时间")
    private Long threeLevelStandardTime;

    /** 三级责任人 */
    @Excel(name = "三级责任人")
    private Long threeLevelPersonCharge;


    /** 三级责任人名称 */
    @TableField(exist = false)
    private String threeLevelPersonChargeName;


}
