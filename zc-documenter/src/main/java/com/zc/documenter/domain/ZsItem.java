package com.zc.documenter.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.zc.common.annotation.Excel;
import com.zc.common.core.domain.BaseEntity;

/**
 * 物料对象 zs_item
 * 
 * @author ruoyi
 * @date 2025-07-10
 */
@Data
@TableName("zs_item")
public class ZsItem extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 物料ID */
    @TableId(value = "item_id", type = IdType.AUTO)
    @Excel(name = "物料序号", type = Excel.Type.EXPORT, cellType = Excel.ColumnType.NUMERIC, prompt = "物料编号")
    private Long itemId;

    /** 物料编号 */
    @Excel(name = "物料编号")
    private String itemCode;

    /** 物料名称 */
    @Excel(name = "物料名称")
    private String itemName;

    /** 物料单位 */
    @Excel(name = "物料单位")
    private String unitCode;

    /** 物料类别   原材料/半成品/成品 */
    @Excel(name = "物料类别   0-原材料/1-半成品/2-成品")
    private String itemCategory;

    /** 物料状态  Y-有效；N-失效 */
    @Excel(name = "物料状态  Y-有效；N-失效")
    private String itemStatus;

    /** 创建方式  前台录入/模板导入/接口导入 */
    @Excel(name = "创建方式  前台录入/模板导入/接口导入",type = Excel.Type.EXPORT)
    private String creationMethod;

    /** 备注1 */

    private String remark1;

    /** 备注2 */

    private String remark2;

    /** 备注3 */

    private String remark3;

    /** 备注4 */

    private String remark4;

    /** 备注5 */

    private String remark5;

    /** 最后更新标识 */
//    @Excel(name = "最后更新标识")
    private String lastUpdateFlag;


}
