package com.zc.documenter.service;

import java.util.List;

import com.zc.documenter.domain.ZcProcess;

/**
 * 工序Service接口
 * 
 * @author ruoyi
 * @date 2025-07-11
 */
public interface IZcProcessService 
{
    /**
     * 查询工序
     * 
     * @param processId 工序主键
     * @return 工序
     */
    public ZcProcess selectZcProcessByProcessId(Long processId);

    /**
     * 查询工序列表
     * 
     * @param zcProcess 工序
     * @return 工序集合
     */
    public List<ZcProcess> selectZcProcessList(ZcProcess zcProcess);

    /**
     * 新增工序
     * 
     * @param zcProcess 工序
     * @return 结果
     */
    public int insertZcProcess(ZcProcess zcProcess);

    /**
     * 修改工序
     * 
     * @param zcProcess 工序
     * @return 结果
     */
    public int updateZcProcess(ZcProcess zcProcess);

    /**
     * 批量删除工序
     * 
     * @param processIds 需要删除的工序主键集合
     * @return 结果
     */
    public int deleteZcProcessByProcessIds(Long[] processIds);

    /**
     * 删除工序信息
     * 
     * @param processId 工序主键
     * @return 结果
     */
    public int deleteZcProcessByProcessId(Long processId);

    /**
     * 导入工序数据
     *
     * @param processList 物料数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param operName 操作用户
     * @return 结果
     */
    public String importProcess(List<ZcProcess> processList, Boolean isUpdateSupport, String operName);
}