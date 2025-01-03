import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
import { getWeekMonthQuarterYear } from '/@/utils';
//列表数据
export const columns: BasicColumn[] = [
  {
    title: '对象',
    align: "center",
    dataIndex: 'ysjObj_dictText'
  },
  {
    title: '字段',
    align: "center",
    dataIndex: 'ysjField_dictText'
  },
  {
    title: '对象中的对象',
    align: "center",
    dataIndex: 'ysjInnerObj_dictText'
  },
  {
    title: '字段基数',
    align: "center",
    dataIndex: 'ysjFieldBaseNum_dictText'
  },
];

// 高级查询数据
export const superQuerySchema = {
  ysjObj: {title: '对象',order: 0,view: 'list', type: 'string',dictTable: "ysj_obj_manage", dictCode: 'ysj_obj', dictText: 'ysj_obj',},
  ysjField: {title: '字段',order: 1,view: 'list', type: 'string',dictTable: "ysj_field_manage", dictCode: 'ysj_field', dictText: 'ysj_field',},
  ysjInnerObj: {title: '对象中的对象',order: 2,view: 'list', type: 'string',dictTable: "ysj_obj_manage", dictCode: 'ysj_obj', dictText: 'ysj_obj',},
  ysjFieldBaseNum: {title: '字段基数',order: 3,view: 'list', type: 'string',dictCode: 'filed_base_num',},
};
