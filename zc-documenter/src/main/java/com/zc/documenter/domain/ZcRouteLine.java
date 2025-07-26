package com.zc.documenter.domain;

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
 * @date 2025-07-17
 */

@Data
@TableName("zc_route_line")
public class ZcRouteLine extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 工艺路线行ID */
    @Excel(name = "工艺路线行ID")
    @TableId(value = "route_line_id", type = IdType.AUTO)
    private Long routeLineId;

    /** 工艺路线头ID */
    @Excel(name = "工艺路线头ID")
    private Long routeHeadId;

    /** 工序ID */
    @Excel(name = "工序ID")
    private Long processId;

    /** 排序  同一工艺路线头ID的行表数据只能存在唯一一个排序为“1”的数据 */
    @Excel(name = "排序  同一工艺路线头ID的行表数据只能存在唯一一个排序为“1”的数据")
    private Long sortOrder;

    /** 最后更新人 */
    @Excel(name = "最后更新人")
    private Long updatedBy;

    /** 最后更新标识 */
    private String lastUpdateFlag;
}