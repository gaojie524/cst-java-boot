package com.ruoyi.documenter.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.documenter.mapper.ZsProcessMapper;
import com.ruoyi.documenter.domain.ZsProcess;
import com.ruoyi.documenter.service.IZsProcessService;

/**
 * 工序Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-07-11
 */
@Service
public class ZsProcessServiceImpl implements IZsProcessService 
{
    @Autowired
    private ZsProcessMapper zsProcessMapper;

    /**
     * 查询工序
     * 
     * @param processId 工序主键
     * @return 工序
     */
    @Override
    public ZsProcess selectZsProcessByProcessId(Long processId)
    {
        return zsProcessMapper.selectZsProcessByProcessId(processId);
    }

    /**
     * 查询工序列表
     * 
     * @param zsProcess 工序
     * @return 工序
     */
    @Override
    public List<ZsProcess> selectZsProcessList(ZsProcess zsProcess)
    {
        return zsProcessMapper.selectZsProcessList(zsProcess);
    }

    /**
     * 新增工序
     * 
     * @param zsProcess 工序
     * @return 结果
     */
    @Override
    public int insertZsProcess(ZsProcess zsProcess)
    {
        zsProcess.setCreateTime(DateUtils.getNowDate());
        return zsProcessMapper.insertZsProcess(zsProcess);
    }

    /**
     * 修改工序
     * 
     * @param zsProcess 工序
     * @return 结果
     */
    @Override
    public int updateZsProcess(ZsProcess zsProcess)
    {
        zsProcess.setUpdateTime(DateUtils.getNowDate());
        return zsProcessMapper.updateZsProcess(zsProcess);
    }

    /**
     * 批量删除工序
     * 
     * @param processIds 需要删除的工序主键
     * @return 结果
     */
    @Override
    public int deleteZsProcessByProcessIds(Long[] processIds)
    {
        return zsProcessMapper.deleteZsProcessByProcessIds(processIds);
    }

    /**
     * 删除工序信息
     * 
     * @param processId 工序主键
     * @return 结果
     */
    @Override
    public int deleteZsProcessByProcessId(Long processId)
    {
        return zsProcessMapper.deleteZsProcessByProcessId(processId);
    }
}
