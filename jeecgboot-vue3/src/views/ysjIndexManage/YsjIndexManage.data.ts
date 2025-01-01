import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
import { getWeekMonthQuarterYear } from '/@/utils';
//列表数据
export const columns: BasicColumn[] = [
  {
    title: '索引名称',
    align: "center",
    dataIndex: 'ysjIndexName'
  },
  {
    title: '表名',
    align: "center",
    dataIndex: 'ysjTbName_dictText'
  },
  {
    title: '索引类型',
    align: "center",
    dataIndex: 'ysjIndexType_dictText'
  },
  {
    title: '索引字段',
    align: "center",
    dataIndex: 'ysjIndexFieldList_dictText'
  },
];

// 高级查询数据
export const superQuerySchema = {
  ysjIndexName: {title: '索引名称',order: 0,view: 'text', type: 'string',},
  ysjTbName: {title: '表名',order: 1,view: 'list', type: 'string',dictTable: "ysj_tb_manage", dictCode: 'ysj_tb_name', dictText: 'ysj_tb_name',},
  ysjIndexType: {title: '索引类型',order: 2,view: 'list', type: 'string',dictCode: 'index_type',},
  ysjIndexFieldList: {title: '索引字段',order: 3,view: 'list_multi', type: 'string',dictTable: "ysj_field_manage", dictCode: 'ysj_field', dictText: 'ysj_field',},
};
