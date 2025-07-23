package com.zc.documenter.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.zc.common.annotation.Excel;
import com.zc.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 单位管理对象 zc_unit
 *
 * @author ruoyi
 * @date 2025-07-16
 */
@Data
@TableName("zc_unit")
public class ZcUnit extends BaseEntity {

        private static final long serialVersionUID = 1L;

        /** 单位ID */
        @TableId(value = "unit_id", type = IdType.AUTO)
        private Long unitId;

        /** 单位编码 */
        @Excel(name = "单位编码")
        private String unitCode;

        /** 单位名称 */
        @Excel(name = "单位名称")
        private String unitName;

        /** 单位描述 */
        @Excel(name = "单位描述")
        private String unitDescription;

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
        @Excel(name = "最后更新标识")
        private String lastUpdateFlag;

}