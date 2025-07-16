package com.zc.documenter.service.impl;

import java.util.List;
import java.util.UUID;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zc.common.exception.ServiceException;
import com.zc.common.utils.DateUtils;
import com.zc.common.utils.StringUtils;
import com.zc.common.utils.bean.BeanValidators;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zc.documenter.mapper.ZsItemMapper;
import com.zc.documenter.domain.ZsItem;
import com.zc.documenter.service.IZsItemService;

import javax.validation.Validator;

/**
 * 物料Service业务层处理
 *
 * @author ruoyi
 * @date 2025-07-10
 */
@Service
public class ZsItemServiceImpl extends ServiceImpl<ZsItemMapper, ZsItem> implements IZsItemService
{
    @Autowired
    private ZsItemMapper zsItemMapper;

    private static final Logger log = LoggerFactory.getLogger(ZsItemServiceImpl.class);

    @Autowired
    protected Validator validator;
    /**
     * 查询物料
     *
     * @param itemId 物料主键
     * @return 物料
     */
    @Override
    public ZsItem selectZsItemByItemId(Long itemId)
    {
        return zsItemMapper.selectZsItemByItemId(itemId);
    }

    /**
     * 查询物料列表
     *
     * @param zsItem 物料
     * @return 物料
     */
    @Override
    public List<ZsItem> selectZsItemList(ZsItem zsItem)
    {
        return zsItemMapper.selectZsItemList(zsItem);
    }


    /**
     * 新增物料管理
     *
     * @param zsItem 物料管理
     * @return 结果
     */
    @Override
    public int insertZsItem(ZsItem zsItem) {
        return zsItemMapper.insert(zsItem);
    }

    /**
     * 修改物料管理
     *
     * @param zsItem 物料管理
     * @return 结果
     */
    @Override
    public int updateZsItem(ZsItem zsItem) {
        return zsItemMapper.updateById(zsItem);

    }

    /**
     * 批量删除物料
     *
     * @param itemIds 需要删除的物料主键
     * @return 结果
     */
    @Override
    public int deleteZsItemByItemIds(Long[] itemIds)
    {
        return zsItemMapper.deleteZsItemByItemIds(itemIds);
    }

    /**
     * 删除物料信息
     *
     * @param itemId 物料主键
     * @return 结果
     */
    @Override
    public int deleteZsItemByItemId(Long itemId)
    {
        return zsItemMapper.deleteZsItemByItemId(itemId);
    }


    @Override
    public String importItem(List<ZsItem> itemList, Boolean isUpdateSupport, String operName)
    {
        if (StringUtils.isNull(itemList) || itemList.size() == 0)
        {
            throw new ServiceException("导入物料数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (ZsItem zsItem : itemList)
        {
            try
            {
                // 验证是否存在这个物料
                ZsItem z = zsItemMapper.selectItemByItemCode(zsItem.getItemCode());
                if (StringUtils.isNull(z))
                {
                    BeanValidators.validateWithException(validator, zsItem);
                    zsItem.setItemId((long)Math.abs(UUID.randomUUID().toString().hashCode()));
                    zsItem.setCreateBy(operName);
                    zsItem.setCreationMethod("1");
                    zsItemMapper.insert(zsItem);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "物料 " + zsItem.getItemCode() + " 导入成功");
                }
                else if (isUpdateSupport)
                {
//                    BeanValidators.validateWithException(validator, zsItem);
//                    checkzsItemAllowed(u);
//                    checkzsItemDataScope(u.getzsItemId());
//                    deptService.checkDeptDataScope(zsItem.getDeptId());
//                    zsItem.setzsItemId(u.getzsItemId());
//                    zsItem.setUpdateBy(operName);
//                    zsItemMapper.updatezsItem(zsItem);
//                    successNum++;
//                    successMsg.append("<br/>" + successNum + "、账号 " + zsItem.getzsItemName() + " 更新成功");
                }
                else
                {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、物料 " + zsItem.getItemCode() + " 已存在");
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、编号 " + zsItem.getItemCode() + " 导入失败：";
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

    /**
     * 通过物料编号查询物料
     *
     * @param itemCode 物料编号
     * @return 物料对象信息
     */
    @Override
    public ZsItem selectItemByItemCode(String itemCode)
    {
        return zsItemMapper.selectItemByItemCode(itemCode);
    }
}
