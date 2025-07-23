package com.zc.documenter.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zc.documenter.domain.ZcProcess;
import org.apache.ibatis.annotations.Mapper;

/**
 * 工序Mapper接口
 * 
 * @author ruoyi
 * @date 2025-07-11
 */
@Mapper
public interface ZcProcessMapper extends BaseMapper<ZcProcess>
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
     * 删除工序
     * 
     * @param processId 工序主键
     * @return 结果
     */
    public int deleteZcProcessByProcessId(Long processId);

    /**
     * 批量删除工序
     * 
     * @param processIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteZcProcessByProcessIds(Long[] processIds);
}