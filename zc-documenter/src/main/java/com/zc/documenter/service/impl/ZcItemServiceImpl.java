package com.zc.documenter.service.impl;

import java.util.List;
import java.util.UUID;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zc.common.exception.ServiceException;
import com.zc.common.utils.StringUtils;
import com.zc.common.utils.bean.BeanValidators;
import com.zc.documenter.mapper.ZcItemMapper;
import com.zc.documenter.service.IZcItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zc.documenter.domain.ZcItem;

import javax.validation.Validator;

/**
 * 物料Service业务层处理
 *
 * @author ruoyi
 * @date 2025-07-10
 */
@Service
public class ZcItemServiceImpl extends ServiceImpl<ZcItemMapper, ZcItem> implements IZcItemService
{
    @Autowired
    private ZcItemMapper zcItemMapper;

    private static final Logger log = LoggerFactory.getLogger(ZcItemServiceImpl.class);

    @Autowired
    protected Validator validator;
    /**
     * 查询物料
     *
     * @param itemId 物料主键
     * @return 物料
     */
    @Override
    public ZcItem selectZcItemByItemId(Long itemId)
    {
        return zcItemMapper.selectZcItemByItemId(itemId);
    }

    /**
     * 查询物料列表
     *
     * @param zcItem 物料
     * @return 物料
     */
    @Override
    public List<ZcItem> selectZcItemList(ZcItem zcItem)
    {
        return zcItemMapper.selectZcItemList(zcItem);
    }


    /**
     * 新增物料管理
     *
     * @param zcItem 物料管理
     * @return 结果
     */
    @Override
    public int insertZcItem(ZcItem zcItem) {
        return zcItemMapper.insert(zcItem);
    }

    /**
     * 修改物料管理
     *
     * @param zcItem 物料管理
     * @return 结果
     */
    @Override
    public int updateZcItem(ZcItem zcItem) {
        return zcItemMapper.updateById(zcItem);
    }

    /**
     * 批量删除物料
     *
     * @param itemIds 需要删除的物料主键
     * @return 结果
     */
    @Override
    public int deleteZcItemByItemIds(Long[] itemIds)
    {
        return zcItemMapper.deleteZcItemByItemIds(itemIds);
    }

    /**
     * 删除物料信息
     *
     * @param itemId 物料主键
     * @return 结果
     */
    @Override
    public int deleteZcItemByItemId(Long itemId)
    {
        return zcItemMapper.deleteZcItemByItemId(itemId);
    }


    @Override
    public String importItem(List<ZcItem> itemList, Boolean isUpdateSupport, String operName)
    {
        if (StringUtils.isNull(itemList) || itemList.size() == 0)
        {
            throw new ServiceException("导入物料数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (ZcItem zcItem : itemList)
        {
            try
            {
                // 验证是否存在这个物料
                ZcItem z = zcItemMapper.selectItemByItemCode(zcItem.getItemCode());
                if (StringUtils.isNull(z))
                {
                    BeanValidators.validateWithException(validator, zcItem);
                    zcItem.setItemId((long)Math.abs(UUID.randomUUID().toString().hashCode()));
                    zcItem.setCreateBy(operName);
                    zcItem.setCreationMethod("1");
                    zcItemMapper.insert(zcItem);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "物料 " + zcItem.getItemCode() + " 导入成功");
                }
                else if (isUpdateSupport)
                {
                    BeanValidators.validateWithException(validator, zcItem);
                    zcItem.setItemId(z.getItemId());
                    zcItem.setCreationMethod("1");
                    zcItemMapper.updateById(zcItem);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、账号 " + zcItem.getItemCode() + " 更新成功");
                }
                else
                {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、物料 " + zcItem.getItemCode() + " 已存在");
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、编号 " + zcItem.getItemCode() + " 导入失败：";
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
    public ZcItem selectItemByItemCode(String itemCode)
    {
        return zcItemMapper.selectItemByItemCode(itemCode);
    }
}