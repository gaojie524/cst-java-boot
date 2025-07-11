package com.ruoyi.documenter.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 单位对象 zs_unit
 * 
 * @author ruoyi
 * @date 2025-07-11
 */
public class ZsUnit extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 单位ID */
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

    public void setUnitId(Long unitId) 
    {
        this.unitId = unitId;
    }

    public Long getUnitId() 
    {
        return unitId;
    }

    public void setUnitCode(String unitCode) 
    {
        this.unitCode = unitCode;
    }

    public String getUnitCode() 
    {
        return unitCode;
    }

    public void setUnitName(String unitName) 
    {
        this.unitName = unitName;
    }

    public String getUnitName() 
    {
        return unitName;
    }

    public void setUnitDescription(String unitDescription) 
    {
        this.unitDescription = unitDescription;
    }

    public String getUnitDescription() 
    {
        return unitDescription;
    }

    public void setRemark1(String remark1) 
    {
        this.remark1 = remark1;
    }

    public String getRemark1() 
    {
        return remark1;
    }

    public void setRemark2(String remark2) 
    {
        this.remark2 = remark2;
    }

    public String getRemark2() 
    {
        return remark2;
    }

    public void setRemark3(String remark3) 
    {
        this.remark3 = remark3;
    }

    public String getRemark3() 
    {
        return remark3;
    }

    public void setRemark4(String remark4) 
    {
        this.remark4 = remark4;
    }

    public String getRemark4() 
    {
        return remark4;
    }

    public void setRemark5(String remark5) 
    {
        this.remark5 = remark5;
    }

    public String getRemark5() 
    {
        return remark5;
    }

    public void setLastUpdateFlag(String lastUpdateFlag) 
    {
        this.lastUpdateFlag = lastUpdateFlag;
    }

    public String getLastUpdateFlag() 
    {
        return lastUpdateFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("unitId", getUnitId())
            .append("unitCode", getUnitCode())
            .append("unitName", getUnitName())
            .append("unitDescription", getUnitDescription())
            .append("remark1", getRemark1())
            .append("remark2", getRemark2())
            .append("remark3", getRemark3())
            .append("remark4", getRemark4())
            .append("remark5", getRemark5())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("lastUpdateFlag", getLastUpdateFlag())
            .toString();
    }
}
