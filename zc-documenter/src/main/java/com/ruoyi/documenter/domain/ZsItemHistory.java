package com.ruoyi.documenter.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 物料历史
对象 zs_item_history
 * 
 * @author ruoyi
 * @date 2025-07-10
 */
public class ZsItemHistory extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 系统主健 */
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
    private Long unitId;

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
    @Excel(name = "最后更新标识")
    private String lastUpdateFlag;

    /** 操作标识 */
    @Excel(name = "操作标识")
    private String operation;

    public void setEventId(Long eventId) 
    {
        this.eventId = eventId;
    }

    public Long getEventId() 
    {
        return eventId;
    }

    public void setItemId(Long itemId) 
    {
        this.itemId = itemId;
    }

    public Long getItemId() 
    {
        return itemId;
    }

    public void setItemCode(String itemCode) 
    {
        this.itemCode = itemCode;
    }

    public String getItemCode() 
    {
        return itemCode;
    }

    public void setItemName(String itemName) 
    {
        this.itemName = itemName;
    }

    public String getItemName() 
    {
        return itemName;
    }

    public void setUnitId(Long unitId)
    {
        this.unitId = unitId;
    }

    public Long getUnitId()
    {
        return unitId;
    }

    public void setItemCategory(String itemCategory) 
    {
        this.itemCategory = itemCategory;
    }

    public String getItemCategory() 
    {
        return itemCategory;
    }

    public void setItemStatus(String itemStatus) 
    {
        this.itemStatus = itemStatus;
    }

    public String getItemStatus() 
    {
        return itemStatus;
    }

    public void setCreationMethod(String creationMethod) 
    {
        this.creationMethod = creationMethod;
    }

    public String getCreationMethod() 
    {
        return creationMethod;
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

    public void setOperation(String operation) 
    {
        this.operation = operation;
    }

    public String getOperation() 
    {
        return operation;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("eventId", getEventId())
            .append("itemId", getItemId())
            .append("itemCode", getItemCode())
            .append("itemName", getItemName())
            .append("unitId", getUnitId())
            .append("itemCategory", getItemCategory())
            .append("itemStatus", getItemStatus())
            .append("creationMethod", getCreationMethod())
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
            .append("operation", getOperation())
            .toString();
    }
}
