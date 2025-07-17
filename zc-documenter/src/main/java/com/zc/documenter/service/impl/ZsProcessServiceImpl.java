package com.zc.documenter.service.impl;

import java.util.List;
import java.util.UUID;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zc.common.exception.ServiceException;
import com.zc.common.utils.DateUtils;
import com.zc.common.utils.StringUtils;
import com.zc.common.utils.bean.BeanValidators;
import com.zc.documenter.domain.ZsItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zc.documenter.mapper.ZsProcessMapper;
import com.zc.documenter.domain.ZsProcess;
import com.zc.documenter.service.IZsProcessService;

import javax.validation.Validator;

/**
 * 工序Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-07-11
 */
@Service
public class ZsProcessServiceImpl extends ServiceImpl<ZsProcessMapper, ZsProcess> implements IZsProcessService
{
    @Autowired
    private ZsProcessMapper zsProcessMapper;

    @Autowired
    protected Validator validator;

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
     * @param zsProcess 工序管理
     * @return 结果
     */
    @Override
    public int insertZsProcess(ZsProcess zsProcess) {
        return zsProcessMapper.insert(zsProcess);
    }

    /**
     * 修改工序
     *
     * @param zsProcess 工序管理
     * @return 结果
     */
    @Override
    public int updateZsProcess(ZsProcess zsProcess) {
        return zsProcessMapper.updateById(zsProcess);

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



    @Override
    public String importProcess(List<ZsProcess> processList, Boolean isUpdateSupport, String operName)
    {
        if (StringUtils.isNull(processList) || processList.size() == 0)
        {
            throw new ServiceException("导入工序数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (ZsProcess zsProcess : processList)
        {
            try
            {
                // 验证是否存在这个物料
                QueryWrapper<ZsProcess> queryWrapper = new QueryWrapper<>();
                queryWrapper.lambda().eq(ZsProcess::getProcessCode, zsProcess.getProcessCode());
                ZsProcess z = zsProcessMapper.selectOne(queryWrapper);
                if (StringUtils.isNull(z))
                {
                    BeanValidators.validateWithException(validator, zsProcess);
                    zsProcess.setProcessId((long)Math.abs(UUID.randomUUID().toString().hashCode()));
                    zsProcess.setCreationMethod("1");
                    zsProcessMapper.insert(zsProcess);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "工序 " + zsProcess.getProcessCode() + " 导入成功");
                }
                else if (isUpdateSupport)
                {
                    BeanValidators.validateWithException(validator, zsProcess);
                    zsProcess.setProcessId(z.getProcessId());
                    zsProcess.setCreationMethod("1");
                    zsProcessMapper.updateById(zsProcess);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、账号 " + zsProcess.getProcessCode() + " 更新成功");
                }
                else
                {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、物料 " + zsProcess.getProcessCode() + " 已存在");
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、编号 " + zsProcess.getProcessCode() + " 导入失败：";
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
