package com.zc.documenter.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.zc.common.annotation.Excel;
import com.zc.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * 工序对象 zc_process
 *
 * @author ruoyi
 * @date 2025-07-11
 */
@Data
@TableName("zc_process")
public class ZcProcess extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 工序ID */
    @TableId(value = "process_id",type = IdType.AUTO)
    private Long processId;

    /** 工序编号 */
    @Excel(name = "工序编号")
    private String processCode;

    /** 工序名称 */
    @Excel(name = "工序名称")
    private String processName;

    /** 工序描述 */
    @Excel(name = "工序描述")
    private String processDesc;

    /** 工序状态 Y-有效；N-失效 */
    @Excel(name = "工序状态 Y-有效；N-失效")
    private String processStatus;

    /** 创建方式  前台录入/模板导入/接口导入 */
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
    private String lastUpdateFlag;


    /** 一级标准时间 */
    private Double oneLevelStandardTime;

    /** 一级责任人 */
    private Long oneLevelPersonCharge;


    /** 一级责任人名称 */
    @TableField(exist = false)
    private String oneLevelPersonChargeName;


    /** 二级标准时间 */
    private Double twoLevelStandardTime;

    /** 二级责任人 */
    private Long twoLevelPersonCharge;

    /** 二级责任人名称 */
    @TableField(exist = false)
    private String twoLevelPersonChargeName;


    /** 三级标准时间 */
    private Double threeLevelStandardTime;

    /** 三级责任人 */
    private Long threeLevelPersonCharge;


    /** 三级责任人名称 */
    @TableField(exist = false)
    private String threeLevelPersonChargeName;


    /** 通知组id */
    private Long notificationId;

}