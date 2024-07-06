import React, { Component } from "react";
import { Form, Input, Select, Modal } from "antd";
const { TextArea } = Input;
class EditStudentForm extends Component {
  render() {
    const { visible, onCancel, onOk, form, confirmLoading, currentRowData } =
      this.props;
    const { getFieldDecorator } = form;
    const { id, name, role, description } = currentRowData;
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
          <Form.Item label="ID Mahasiswa:">
            {getFieldDecorator("id", {
              initialValue: id,
            })(<Input disabled />)}
          </Form.Item>
          <Form.Item label="Nama:">
            {getFieldDecorator("name", {
              rules: [{ required: true, message: "请输入Nama!" }],
              initialValue: name,
            })(<Input placeholder="请输入Nama" />)}
          </Form.Item>
          <Form.Item label="Peran:">
            {getFieldDecorator("role", {
              initialValue: role,
            })(
              <Select style={{ width: 120 }} disabled={id === "admin"}>
                <Select.Option value="admin">admin</Select.Option>
                <Select.Option value="student">editor</Select.Option>
                <Select.Option value="student">guest</Select.Option>
              </Select>
            )}
          </Form.Item>
          <Form.Item label="Deskripsi Mahasiswa:">
            {getFieldDecorator("description", {
              initialValue: description,
            })(<TextArea rows={4} placeholder="请输入Deskripsi Mahasiswa" />)}
          </Form.Item>
        </Form>
      </Modal>
    );
  }
}

export default Form.create({ name: "EditStudentForm" })(EditStudentForm);
