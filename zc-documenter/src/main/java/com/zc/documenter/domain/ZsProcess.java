package com.zc.documenter.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.zc.common.annotation.Excel;
import com.zc.common.core.domain.BaseEntity;

/**
 * 工序对象 zs_process
 * 
 * @author ruoyi
 * @date 2025-07-11
 */
public class ZsProcess extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 工序ID */
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
    @Excel(name = "创建方式  前台录入/模板导入/接口导入")
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

    public void setProcessId(Long processId) 
    {
        this.processId = processId;
    }

    public Long getProcessId() 
    {
        return processId;
    }

    public void setProcessCode(String processCode) 
    {
        this.processCode = processCode;
    }

    public String getProcessCode() 
    {
        return processCode;
    }

    public void setProcessName(String processName) 
    {
        this.processName = processName;
    }

    public String getProcessName() 
    {
        return processName;
    }

    public void setProcessDesc(String processDesc) 
    {
        this.processDesc = processDesc;
    }

    public String getProcessDesc() 
    {
        return processDesc;
    }

    public void setProcessStatus(String processStatus) 
    {
        this.processStatus = processStatus;
    }

    public String getProcessStatus() 
    {
        return processStatus;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("processId", getProcessId())
            .append("processCode", getProcessCode())
            .append("processName", getProcessName())
            .append("processDesc", getProcessDesc())
            .append("processStatus", getProcessStatus())
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
            .toString();
    }
}
