import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
import { getWeekMonthQuarterYear } from '/@/utils';
//列表数据
export const columns: BasicColumn[] = [
  {
    title: '列值',
    align: "center",
    dataIndex: 'ysjField_dictText'
  },
  {
    title: '列是否必填',
    align: "center",
    dataIndex: 'ysjColumnNoNull_dictText'
  },
  {
    title: '列中的值是否递增',
    align: "center",
    dataIndex: 'ysjColumnIncr_dictText'
  },
  {
    title: '列所在的表',
    align: "center",
    dataIndex: 'ysjTbName_dictText'
  },
];

// 高级查询数据
export const superQuerySchema = {
  ysjField: {title: '列值',order: 0,view: 'list', type: 'string',dictTable: "ysj_field_manage", dictCode: 'ysj_field', dictText: 'ysj_field',},
  ysjColumnNoNull: {title: '列是否必填',order: 1,view: 'list', type: 'string',dictCode: 'yn',},
  ysjColumnIncr: {title: '列中的值是否递增',order: 2,view: 'list', type: 'string',dictCode: 'yn',},
  ysjTbName: {title: '列所在的表',order: 3,view: 'list', type: 'string',dictTable: "ysj_tb_manage", dictCode: 'ysj_tb_name', dictText: 'ysj_tb_name',},
};
