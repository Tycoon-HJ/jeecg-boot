import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
import { getWeekMonthQuarterYear } from '/@/utils';
//列表数据
export const columns: BasicColumn[] = [
   {
    title: '字段值',
    align:"center",
    dataIndex: 'ysjField'
   },
   {
    title: '字段中文名称',
    align:"center",
    dataIndex: 'ysjFieldName'
   },
   {
    title: '字段英文名称',
    align:"center",
    dataIndex: 'ysjFieldEnName'
   },
   {
    title: '字段长度',
    align:"center",
    dataIndex: 'ysjFieldLength'
   },
   {
    title: '字段类型',
    align:"center",
    dataIndex: 'ysjFieldType_dictText'
   },
   {
    title: '字典',
    align:"center",
    dataIndex: 'ysjDict_dictText'
   },
   {
    title: '字典编码',
    align:"center",
    dataIndex: 'ysjDictCode_dictText'
   },
   {
    title: '备注',
    align:"center",
    dataIndex: 'remark'
   },
];
//查询数据
export const searchFormSchema: FormSchema[] = [
];
//表单数据
export const formSchema: FormSchema[] = [
  {
    label: '字段值',
    field: 'ysjField',
    component: 'Input',
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入字段值!'},
                 {...rules.duplicateCheckRule('ysj_field_manage', 'ysj_field',model,schema)[0]},
          ];
     },
  },
  {
    label: '字段中文名称',
    field: 'ysjFieldName',
    component: 'Input',
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入字段中文名称!'},
          ];
     },
  },
  {
    label: '字段英文名称',
    field: 'ysjFieldEnName',
    component: 'Input',
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入字段英文名称!'},
          ];
     },
  },
  {
    label: '字段长度',
    field: 'ysjFieldLength',
    component: 'InputNumber',
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入字段长度!'},
                 { pattern: /^-?\d+\.?\d*$/, message: '请输入数字!'},
          ];
     },
  },
  {
    label: '字段类型',
    field: 'ysjFieldType',
    component: 'JDictSelectTag',
    componentProps:{
        dictCode:"filed_type"
     },
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入字段类型!'},
          ];
     },
  },
  {
    label: '字典',
    field: 'ysjDict',
    component: 'JDictSelectTag',
    componentProps:{
        dictCode:"yn",
        type: "radio"
     },
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入字典!'},
          ];
     },
  },
  {
    label: '字典编码',
    field: 'ysjDictCode',
    component: 'JDictSelectTag',
    componentProps:{
        dictCode:"jimu_dict,dict_name,dict_code"
     },
  },
  {
    label: '备注',
    field: 'remark',
    component: 'InputTextArea',
  },
	// TODO 主键隐藏字段，目前写死为ID
	{
	  label: '',
	  field: 'id',
	  component: 'Input',
	  show: false
	},
];

// 高级查询数据
export const superQuerySchema = {
  ysjField: {title: '字段值',order: 0,view: 'text', type: 'string',},
  ysjFieldName: {title: '字段中文名称',order: 1,view: 'text', type: 'string',},
  ysjFieldEnName: {title: '字段英文名称',order: 2,view: 'text', type: 'string',},
  ysjFieldLength: {title: '字段长度',order: 3,view: 'number', type: 'number',},
  ysjFieldType: {title: '字段类型',order: 4,view: 'list', type: 'string',dictCode: 'filed_type',},
  ysjDict: {title: '字典',order: 5,view: 'radio', type: 'string',dictCode: 'yn',},
  ysjDictCode: {title: '字典编码',order: 6,view: 'list', type: 'string',dictTable: "jimu_dict", dictCode: 'dict_code', dictText: 'dict_name',},
  remark: {title: '备注',order: 7,view: 'textarea', type: 'string',},
};

/**
* 流程表单调用这个方法获取formSchema
* @param param
*/
export function getBpmFormSchema(_formData): FormSchema[]{
  // 默认和原始表单保持一致 如果流程中配置了权限数据，这里需要单独处理formSchema
  return formSchema;
}