<template>
  <a-spin :spinning="confirmLoading">
    <JFormContainer :disabled="disabled">
      <template #detail>
        <a-form ref="formRef" class="antd-modal-form" :labelCol="labelCol" :wrapperCol="wrapperCol" name="YsjInterfaceManageForm">
          <a-row>
						<a-col :span="24">
							<a-form-item label="接口地址" v-bind="validateInfos.ysjInterfaceUrl" id="YsjInterfaceManageForm-ysjInterfaceUrl" name="ysjInterfaceUrl">
								<a-input v-model:value="formData.ysjInterfaceUrl" placeholder="请输入接口地址"  allow-clear ></a-input>
							</a-form-item>
						</a-col>
						<a-col :span="24">
							<a-form-item label="接口名称" v-bind="validateInfos.ysjInterfaceName" id="YsjInterfaceManageForm-ysjInterfaceName" name="ysjInterfaceName">
								<a-input v-model:value="formData.ysjInterfaceName" placeholder="请输入接口名称"  allow-clear ></a-input>
							</a-form-item>
						</a-col>
						<a-col :span="24">
							<a-form-item label="接口对象" v-bind="validateInfos.ysjInterfaceObj" id="YsjInterfaceManageForm-ysjInterfaceObj" name="ysjInterfaceObj">
								<a-input v-model:value="formData.ysjInterfaceObj" placeholder="请输入接口对象"  allow-clear ></a-input>
							</a-form-item>
						</a-col>
						<a-col :span="24">
							<a-form-item label="接口类型" v-bind="validateInfos.ysjInterfaceType" id="YsjInterfaceManageForm-ysjInterfaceType" name="ysjInterfaceType">
								<j-dict-select-tag v-model:value="formData.ysjInterfaceType" dictCode="interface_type" placeholder="请选择接口类型"  allow-clear />
							</a-form-item>
						</a-col>
						<a-col :span="24">
							<a-form-item label="接口入参" v-bind="validateInfos.ysjInterfaceInParam" id="YsjInterfaceManageForm-ysjInterfaceInParam" name="ysjInterfaceInParam">
								<j-dict-select-tag v-model:value="formData.ysjInterfaceInParam" dictCode="ysj_obj_manage,ysj_obj,ysj_obj" placeholder="请选择接口入参"  allow-clear />
							</a-form-item>
						</a-col>
						<a-col :span="24">
							<a-form-item label="接口出参" v-bind="validateInfos.ysjInterfaceOutParam" id="YsjInterfaceManageForm-ysjInterfaceOutParam" name="ysjInterfaceOutParam">
								<j-dict-select-tag v-model:value="formData.ysjInterfaceOutParam" dictCode="ysj_obj_manage,ysj_obj,ysj_obj" placeholder="请选择接口出参"  allow-clear />
							</a-form-item>
						</a-col>
          </a-row>
        </a-form>
      </template>
    </JFormContainer>
  </a-spin>
</template>

<script lang="ts" setup>
  import { ref, reactive, defineExpose, nextTick, defineProps, computed, onMounted } from 'vue';
  import { defHttp } from '/@/utils/http/axios';
  import { useMessage } from '/@/hooks/web/useMessage';
  import JDictSelectTag from '/@/components/Form/src/jeecg/components/JDictSelectTag.vue';
  import { getValueType } from '/@/utils';
  import { saveOrUpdate } from '../YsjInterfaceManage.api';
  import { Form } from 'ant-design-vue';
  import JFormContainer from '/@/components/Form/src/container/JFormContainer.vue';
  import { duplicateValidate } from '/@/utils/helper/validator'
  const props = defineProps({
    formDisabled: { type: Boolean, default: false },
    formData: { type: Object, default: () => ({})},
    formBpm: { type: Boolean, default: true }
  });
  const formRef = ref();
  const useForm = Form.useForm;
  const emit = defineEmits(['register', 'ok']);
  const formData = reactive<Record<string, any>>({
    id: '',
    ysjInterfaceUrl: '',   
    ysjInterfaceName: '',   
    ysjInterfaceObj: '',   
    ysjInterfaceType: '',   
    ysjInterfaceInParam: '',   
    ysjInterfaceOutParam: '',   
  });
  const { createMessage } = useMessage();
  const labelCol = ref<any>({ xs: { span: 24 }, sm: { span: 5 } });
  const wrapperCol = ref<any>({ xs: { span: 24 }, sm: { span: 16 } });
  const confirmLoading = ref<boolean>(false);
  //表单验证
  const validatorRules = reactive({
    ysjInterfaceUrl: [{ required: true, message: '请输入接口地址!'}, { validator: ysjInterfaceUrlDuplicatevalidate }],
    ysjInterfaceName: [{ required: true, message: '请输入接口名称!'},],
    ysjInterfaceType: [{ required: true, message: '请输入接口类型!'},],
  });
  const { resetFields, validate, validateInfos } = useForm(formData, validatorRules, { immediate: false });

  // 表单禁用
  const disabled = computed(()=>{
    if(props.formBpm === true){
      if(props.formData.disabled === false){
        return false;
      }else{
        return true;
      }
    }
    return props.formDisabled;
  });

  
  /**
   * 新增
   */
  function add() {
    edit({});
  }

  /**
   * 编辑
   */
  function edit(record) {
    nextTick(() => {
      resetFields();
      const tmpData = {};
      Object.keys(formData).forEach((key) => {
        if(record.hasOwnProperty(key)){
          tmpData[key] = record[key]
        }
      })
      //赋值
      Object.assign(formData, tmpData);
    });
  }

  /**
   * 提交数据
   */
  async function submitForm() {
    try {
      // 触发表单验证
      await validate();
    } catch ({ errorFields }) {
      if (errorFields) {
        const firstField = errorFields[0];
        if (firstField) {
          formRef.value.scrollToField(firstField.name, { behavior: 'smooth', block: 'center' });
        }
      }
      return Promise.reject(errorFields);
    }
    confirmLoading.value = true;
    const isUpdate = ref<boolean>(false);
    //时间格式化
    let model = formData;
    if (model.id) {
      isUpdate.value = true;
    }
    //循环数据
    for (let data in model) {
      //如果该数据是数组并且是字符串类型
      if (model[data] instanceof Array) {
        let valueType = getValueType(formRef.value.getProps, data);
        //如果是字符串类型的需要变成以逗号分割的字符串
        if (valueType === 'string') {
          model[data] = model[data].join(',');
        }
      }
    }
    await saveOrUpdate(model, isUpdate.value)
      .then((res) => {
        if (res.success) {
          createMessage.success(res.message);
          emit('ok');
        } else {
          createMessage.warning(res.message);
        }
      })
      .finally(() => {
        confirmLoading.value = false;
      });
  }


  async function ysjInterfaceUrlDuplicatevalidate(_r, value) {
    return duplicateValidate('ysj_interface_manage', 'ysj_interface_url', value, formData.id || '')
  }
  defineExpose({
    add,
    edit,
    submitForm,
  });
</script>

<style lang="less" scoped>
  .antd-modal-form {
    padding: 14px;
  }
</style>
