import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
import { getWeekMonthQuarterYear } from '/@/utils';
//列表数据
export const columns: BasicColumn[] = [
  {
    title: '表名称',
    align: "center",
    dataIndex: 'ysjTbName'
  },
  {
    title: '表中文名',
    align: "center",
    dataIndex: 'ysjTbCname'
  },
  {
    title: '表的主键',
    align: "center",
    dataIndex: 'ysjTbPrimary_dictText'
  },
];

// 高级查询数据
export const superQuerySchema = {
  ysjTbName: {title: '表名称',order: 0,view: 'text', type: 'string',},
  ysjTbCname: {title: '表中文名',order: 1,view: 'text', type: 'string',},
  ysjTbPrimary: {title: '表的主键',order: 2,view: 'list_multi', type: 'string',dictTable: "ysj_field_manage", dictCode: 'ysj_field', dictText: 'ysj_field',},
};
