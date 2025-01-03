import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
import { getWeekMonthQuarterYear } from '/@/utils';
//列表数据
export const columns: BasicColumn[] = [
  {
    title: '接口地址',
    align: "center",
    dataIndex: 'ysjInterfaceUrl'
  },
  {
    title: '接口名称',
    align: "center",
    dataIndex: 'ysjInterfaceName'
  },
  {
    title: '接口类型',
    align: "center",
    dataIndex: 'ysjInterfaceType_dictText'
  },
  {
    title: '接口入参',
    align: "center",
    dataIndex: 'ysjInterfaceInParam_dictText'
  },
  {
    title: '接口出参',
    align: "center",
    dataIndex: 'ysjInterfaceOutParam_dictText'
  },
];

// 高级查询数据
export const superQuerySchema = {
  ysjInterfaceUrl: {title: '接口地址',order: 0,view: 'text', type: 'string',},
  ysjInterfaceName: {title: '接口名称',order: 1,view: 'text', type: 'string',},
  ysjInterfaceType: {title: '接口类型',order: 2,view: 'list', type: 'string',dictCode: 'interface_type',},
  ysjInterfaceInParam: {title: '接口入参',order: 3,view: 'list', type: 'string',dictTable: "ysj_obj_manage", dictCode: 'ysj_obj', dictText: 'ysj_obj',},
  ysjInterfaceOutParam: {title: '接口出参',order: 4,view: 'list', type: 'string',dictTable: "ysj_obj_manage", dictCode: 'ysj_obj', dictText: 'ysj_obj',},
};
