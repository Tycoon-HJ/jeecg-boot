import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
import { getWeekMonthQuarterYear } from '/@/utils';
//列表数据
export const columns: BasicColumn[] = [
  {
    title: '字段',
    align: "center",
    dataIndex: 'ysjField'
  },
  {
    title: '字段中文名称',
    align: "center",
    dataIndex: 'ysjFieldName'
  },
  {
    title: '字段长度',
    align: "center",
    dataIndex: 'ysjFieldLength'
  },
  {
    title: '字段类型',
    align: "center",
    dataIndex: 'ysjFieldType_dictText'
  },
  {
    title: '字典',
    align: "center",
    dataIndex: 'ysjDict_dictText'
  },
  {
    title: '字典编码',
    align: "center",
    dataIndex: 'ysjDictCode_dictText'
  },
  {
    title: '备注',
    align: "center",
    dataIndex: 'remark'
  },
];

// 高级查询数据
export const superQuerySchema = {
  ysjField: {title: '字段',order: 0,view: 'text', type: 'string',},
  ysjFieldName: {title: '字段中文名称',order: 1,view: 'text', type: 'string',},
  ysjFieldLength: {title: '字段长度',order: 2,view: 'number', type: 'number',},
  ysjFieldType: {title: '字段类型',order: 3,view: 'list', type: 'string',dictCode: 'filed_type',},
  ysjDict: {title: '字典',order: 4,view: 'radio', type: 'string',dictCode: 'yn',},
  ysjDictCode: {title: '字典编码',order: 5,view: 'list', type: 'string',dictTable: "jimu_dict", dictCode: 'dict_code', dictText: 'dict_name',},
  remark: {title: '备注',order: 6,view: 'textarea', type: 'string',},
};
