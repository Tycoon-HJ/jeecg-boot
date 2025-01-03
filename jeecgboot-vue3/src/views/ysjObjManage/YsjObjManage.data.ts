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
    dataIndex: 'ysjObj'
  },
  {
    title: '对象名称',
    align: "center",
    dataIndex: 'ysjObjName'
  },
  {
    title: '对象方向',
    align: "center",
    dataIndex: 'ysjObjDirection_dictText'
  },
  {
    title: '对象基数',
    align: "center",
    dataIndex: 'ysjObjBaseNum_dictText'
  },
];

// 高级查询数据
export const superQuerySchema = {
  ysjObj: {title: '对象',order: 0,view: 'text', type: 'string',},
  ysjObjName: {title: '对象名称',order: 1,view: 'text', type: 'string',},
  ysjObjDirection: {title: '对象方向',order: 2,view: 'list', type: 'string',dictCode: 'obj_direction',},
  ysjObjBaseNum: {title: '对象基数',order: 3,view: 'list', type: 'string',dictCode: 'obj_base_num',},
};
