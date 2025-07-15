package com.ruoyi.documenter.mapper;

import java.util.List;
import com.ruoyi.documenter.domain.ZsProcess;

/**
 * 工序Mapper接口
 * 
 * @author ruoyi
 * @date 2025-07-11
 */
public interface ZsProcessMapper 
{
    /**
     * 查询工序
     * 
     * @param processId 工序主键
     * @return 工序
     */
    public ZsProcess selectZsProcessByProcessId(Long processId);

    /**
     * 查询工序列表
     * 
     * @param zsProcess 工序
     * @return 工序集合
     */
    public List<ZsProcess> selectZsProcessList(ZsProcess zsProcess);

    /**
     * 新增工序
     * 
     * @param zsProcess 工序
     * @return 结果
     */
    public int insertZsProcess(ZsProcess zsProcess);

    /**
     * 修改工序
     * 
     * @param zsProcess 工序
     * @return 结果
     */
    public int updateZsProcess(ZsProcess zsProcess);

    /**
     * 删除工序
     * 
     * @param processId 工序主键
     * @return 结果
     */
    public int deleteZsProcessByProcessId(Long processId);

    /**
     * 批量删除工序
     * 
     * @param processIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteZsProcessByProcessIds(Long[] processIds);
}
