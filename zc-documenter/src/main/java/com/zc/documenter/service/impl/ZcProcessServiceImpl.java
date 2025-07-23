package com.zc.documenter.service.impl;

import java.util.List;
import java.util.UUID;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zc.common.exception.ServiceException;
import com.zc.common.utils.StringUtils;
import com.zc.common.utils.bean.BeanValidators;
import com.zc.documenter.mapper.ZcProcessMapper;
import com.zc.documenter.service.IZcProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zc.documenter.domain.ZcProcess;

import javax.validation.Validator;

/**
 * 工序Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-07-11
 */
@Service
public class ZcProcessServiceImpl extends ServiceImpl<ZcProcessMapper, ZcProcess> implements IZcProcessService
{
    @Autowired
    private ZcProcessMapper zcProcessMapper;

    @Autowired
    protected Validator validator;

    /**
     * 查询工序
     * 
     * @param processId 工序主键
     * @return 工序
     */
    @Override
    public ZcProcess selectZcProcessByProcessId(Long processId)
    {
        return zcProcessMapper.selectZcProcessByProcessId(processId);
    }

    /**
     * 查询工序列表
     * 
     * @param zcProcess 工序
     * @return 工序
     */
    @Override
    public List<ZcProcess> selectZcProcessList(ZcProcess zcProcess)
    {
        return zcProcessMapper.selectZcProcessList(zcProcess);
    }
    /**
     * 新增工序
     *
     * @param zcProcess 工序管理
     * @return 结果
     */
    @Override
    public int insertZcProcess(ZcProcess zcProcess) {
        return zcProcessMapper.insert(zcProcess);
    }

    /**
     * 修改工序
     *
     * @param zcProcess 工序管理
     * @return 结果
     */
    @Override
    public int updateZcProcess(ZcProcess zcProcess) {
        return zcProcessMapper.updateById(zcProcess);
    }


    /**
     * 批量删除工序
     * 
     * @param processIds 需要删除的工序主键
     * @return 结果
     */
    @Override
    public int deleteZcProcessByProcessIds(Long[] processIds)
    {
        return zcProcessMapper.deleteZcProcessByProcessIds(processIds);
    }

    /**
     * 删除工序信息
     * 
     * @param processId 工序主键
     * @return 结果
     */
    @Override
    public int deleteZcProcessByProcessId(Long processId)
    {
        return zcProcessMapper.deleteZcProcessByProcessId(processId);
    }



    @Override
    public String importProcess(List<ZcProcess> processList, Boolean isUpdateSupport, String operName)
    {
        if (StringUtils.isNull(processList) || processList.size() == 0)
        {
            throw new ServiceException("导入工序数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (ZcProcess zcProcess : processList)
        {
            try
            {
                // 验证是否存在这个物料
                QueryWrapper<ZcProcess> queryWrapper = new QueryWrapper<>();
                queryWrapper.lambda().eq(ZcProcess::getProcessCode, zcProcess.getProcessCode());
                ZcProcess z = zcProcessMapper.selectOne(queryWrapper);
                if (StringUtils.isNull(z))
                {
                    BeanValidators.validateWithException(validator, zcProcess);
                    zcProcess.setProcessId((long)Math.abs(UUID.randomUUID().toString().hashCode()));
                    zcProcess.setCreationMethod("1");
                    zcProcessMapper.insert(zcProcess);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "工序 " + zcProcess.getProcessCode() + " 导入成功");
                }
                else if (isUpdateSupport)
                {
                    BeanValidators.validateWithException(validator, zcProcess);
                    zcProcess.setProcessId(z.getProcessId());
                    zcProcess.setCreationMethod("1");
                    zcProcessMapper.updateById(zcProcess);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、账号 " + zcProcess.getProcessCode() + " 更新成功");
                }
                else
                {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、物料 " + zcProcess.getProcessCode() + " 已存在");
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、编号 " + zcProcess.getProcessCode() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        if (failureNum > 0)
        {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new ServiceException(failureMsg.toString());
        }
        else
        {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }
}