import React, { Component } from "react";
import { Form, Input, Select, Modal } from "antd";
import { InputNumber } from 'antd';
const { TextArea } = Input;
class EditRPSForm extends Component {
  render() {
    const {
      visible,
      onCancel,
      onOk,
      form,
      confirmLoading,
      currentRowData,
    } = this.props;
    const { getFieldDecorator } = form;
    const { getFieldValue } = this.props.form;
    const currentSubjectId = getFieldValue('subject_id');
    const { id, name, sks,semester,cpl_prodi,cpl_mk,learningMediaSoftwares,learningMediaHardwares, subjects,lectures, } = currentRowData;
    const formItemLayout = {
      labelCol: {
        xs: { span: 24 },
        sm: { span: 8 },
      },
      wrapperCol: {
        xs: { span: 24 },
        sm: { span: 16 },
      },
    };
    return (
      <Modal
        title="编辑"
        visible={visible}
        onCancel={onCancel}
        onOk={onOk}
        confirmLoading={confirmLoading}
      >
        <Form {...formItemLayout}>
          <Form.Item label="ID Pengguna:">
            {getFieldDecorator("id", {
              initialValue: id,
            })(<Input disabled />)}
          </Form.Item>
          <Form.Item label="Nama:">
            {getFieldDecorator("name", {
              rules: [{ required: true, message: "Nama wajib diisi" }],
              initialValue: name,
            })(<Input placeholder="Nama" />)}
          </Form.Item>
          <Form.Item label="SKS:">
            {getFieldDecorator("sks", {
              rules: [{ required: true, message: "SKS wajib diisi" }],
              initialValue: sks,
            })(
              <InputNumber
                placeholder="SKS RPS"
                min={1}
                style={{ width: 300 }}
              />
            )}
          </Form.Item>
          <Form.Item label="Semester:">
            {getFieldDecorator("semester", {
              rules: [{ required: true, message: "Semester wajib diisi" }],
              initialValue: semester,
            })(
              <InputNumber
                placeholder="Semester"
                min={1}
                style={{ width: 300 }}
              />
            )}
          </Form.Item>
          <Form.Item label="CPL Prodi:">
            {getFieldDecorator("cpl_prodi", {
              rules: [{ required: true, message: "CPL Prodi wajib diisi" }],
              initialValue: cpl_prodi,
            })(<Input placeholder="CPL Prodi" />)}
          </Form.Item>
          <Form.Item label="CPL Mata Kuliah:">
            {getFieldDecorator("cpl_mk", {
              rules: [{ required: true, message: "CPL Mata Kuliah wajib diisi" }],
              initialValue: cpl_mk,
            })(<Input placeholder="CPL Mata Kuliah" />)}
          </Form.Item>
          <Form.Item label="Software Media Pembelajaran:">
            {getFieldDecorator("learning_media_softwares", {
              rules: [{ required: true, message: "Silahkan pilih Software Media Pembelajaran" }],
              initialValue: learningMediaSoftwares,
            })(
              <Select
                mode="multiple"
                style={{ width: 300 }}
                placeholder="Pilih Software Media Pembelajaran"
              >
                {learningMediaSoftwares && learningMediaSoftwares.map((arr, key) => {
                return (
                  <Select.Option
                    value={arr.id}
                    key={"learning-media-software-" + key}
                  >
                    {arr.name}
                  </Select.Option>
                );
              })}
              </Select>
            )}
          </Form.Item>
          <Form.Item label="Hardware Media Pembelajaran:">
            {getFieldDecorator("learning_media_hardwares", {
              rules: [{ required: true, message: "Silahkan pilih Hardware Media Pembelajaran" }],
              initialValue: learningMediaHardwares,
            })(
              <Select
                mode="multiple"
                style={{ width: 300 }}
                placeholder="Pilih Hardware Media Pembelajaran"
              >
                {learningMediaHardwares && learningMediaHardwares.map((arr, key) => {
                  return (
                    <Select.Option
                      value={arr.id}
                      key={"learning-media-software-" + key}
                    >
                      {arr.name}
                    </Select.Option>
                  );
                })}
              </Select>
            )}
          </Form.Item>
          <Form.Item label="Subject ID">
            {getFieldDecorator('subject_id', {
              initialValue: currentRowData.subject_id,
              rules: [{ required: true, message: 'Subject ID is required' }],
            })(<Input placeholder="Subject ID" />)}
          </Form.Item>
          </Form>
      </Modal>
    );
  }
}

export default Form.create({ name: "EditRPSForm" })(EditRPSForm);
