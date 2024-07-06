import React, { Component } from "react";
import { Form, Input, InputNumber, Modal, Select } from "antd";
const { TextArea } = Input;
const { Option } = Select;

class AddSubjectForm extends Component {
  render() {
    const {
      visible,
      onCancel,
      onOk,
      form,
      confirmLoading,
      subjectGroups,
      studyPrograms,
    } = this.props;
    const { getFieldDecorator } = form;
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
        title="Tambah Mata Kuliah"
        visible={visible}
        onCancel={onCancel}
        onOk={onOk}
        confirmLoading={confirmLoading}
      >
        <Form {...formItemLayout}>
          <Form.Item label="Nama:">
            {getFieldDecorator("name", {
              rules: [
                {
                  required: true,
                  message: "Silahkan isikan nama mata kuliah",
                },
              ],
            })(<Input placeholder="Nama Mata Kuliah" />)}
          </Form.Item>
          <Form.Item label="Deskripsi:">
            {getFieldDecorator("description", {
              rules: [
                {
                  required: true,
                  message: "Silahkan isikan deskripsi mata kuliah",
                },
              ],
            })(<TextArea rows={4} placeholder="Deskripsi Pengguna" />)}
          </Form.Item>
          <Form.Item label="Point Kredit:">
            {getFieldDecorator("credit_point", {
              rules: [
                {
                  required: true,
                  message: "Silahkan isikan point kredit mata kuliah",
                },
              ],
            })(
              <InputNumber
                style={{ width: 300 }}
                min={1}
                placeholder="Point Kredit"
              />
            )}
          </Form.Item>
          <Form.Item label="Tahun Mata Kuliah:">
            {getFieldDecorator("year_commenced", {
              rules: [
                {
                  required: true,
                  message: "Silahkan isikan tahun mata kuliah",
                },
              ],
            })(
              <Select
                showSearch
                style={{ width: 300 }}
                placeholder="Pilih Tahun Ajaran"
                optionFilterProp="children"
                filterOption={(input, option) =>
                  option.props.children
                    .toLowerCase()
                    .indexOf(input.toLowerCase()) >= 0
                }
              >
                <Option value="2022">2022</Option>
                <Option value="2023">2023</Option>
                <Option value="2024">2024</Option>
              </Select>
            )}
          </Form.Item>
          <Form.Item label="Program Study:">
            {getFieldDecorator("study_program_id", {
              rules: [
                {
                  required: true,
                  message: "Silahkan pilih program studi",
                },
              ],
            })(
              <Select style={{ width: 300 }} placeholder="Pilih Program Study">
                {studyPrograms.map((arr, key) => {
                  return (
                    <Select.Option value={arr.id} key={key}>
                      {arr.name}
                    </Select.Option>
                  );
                })}
              </Select>
            )}
          </Form.Item>
          <Form.Item label="Rumpun Mata Kuliah:">
            {getFieldDecorator("subject_group_id", {
              rules: [
                {
                  required: true,
                  message: "Silahkan pilih rumpun mata kuliah",
                },
              ],
            })(
              <Select
                style={{ width: 300 }}
                placeholder="Pilih Rumpun Matakuliah"
              >
                {subjectGroups.map((arr, key) => {
                  return (
                    <Select.Option value={arr.id} key={key}>
                      {arr.name}
                    </Select.Option>
                  );
                })}
              </Select>
            )}
          </Form.Item>
        </Form>
      </Modal>
    );
  }
}

export default Form.create({ name: "AddSubjectForm" })(AddSubjectForm);
