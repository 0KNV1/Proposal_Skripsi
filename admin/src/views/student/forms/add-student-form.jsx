import React, { Component } from "react";
import { Form, Input, Modal, DatePicker, Select } from "antd";
const { TextArea } = Input;
class AddStudentForm extends Component {
  render() {
    const {
      visible,
      onCancel,
      onOk,
      form,
      confirmLoading,
      religion,
      user,
      studyProgram,
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
        title="mengedit"
        visible={visible}
        onCancel={onCancel}
        onOk={onOk}
        confirmLoading={confirmLoading}
      >
        <Form {...formItemLayout}>
          <Form.Item label="NIM:">
            {getFieldDecorator("nim", {
              rules: [{ required: true, message: "NIM wajib diisi" }],
            })(<Input placeholder="NIM" />)}
          </Form.Item>
          <Form.Item label="Nama Lengkap:">
            {getFieldDecorator("name", {
              rules: [
                {
                  required: true,
                  message: "Nama lengkap mahasiswa wajib diisi",
                },
              ],
            })(<Input placeholder="Nama Lengkap Mahasiswa" />)}
          </Form.Item>
          <Form.Item label="Tempat Lahir:">
            {getFieldDecorator("place_born", {
              rules: [{ required: true, message: "Tempat Lahir wajib diisi" }],
            })(<Input placeholder="Tempat Lahir" />)}
          </Form.Item>
          <Form.Item label="Tanggal Lahir:">
            {getFieldDecorator("birth_date", {
              rules: [{ required: true, message: "Tanggal Lahir wajib diisi" }],
            })(<DatePicker placeholder="Tanggal Lahir" />)}
          </Form.Item>
          <Form.Item label="Gender:">
            {getFieldDecorator("gender", {
              rules: [{ required: true, message: "Gender wajib diisi" }],
            })(
              <Select style={{ width: 120 }} placeholder="Gender">
                <Select.Option value="L">Laki-laki</Select.Option>
                <Select.Option value="P">Perempuan</Select.Option>
              </Select>
            )}
          </Form.Item>
          <Form.Item label="Nomor Telepon:">
            {getFieldDecorator("phone", {
              rules: [{ required: true, message: "Nomor telefon wajib diisi" }],
            })(<Input type={"number"} placeholder="Nomor Telefon (62)" />)}
          </Form.Item>
          <Form.Item label="Alamat:">
            {getFieldDecorator("address", {
              rules: [{ required: true, message: "Alamat wajib diisi" }],
            })(<TextArea placeholder="Alamat" />)}
          </Form.Item>
          <Form.Item label="Agama:">
            {getFieldDecorator("religion_id", {
              rules: [
                {
                  required: true,
                  message: "Silahkan pilih agama",
                },
              ],
            })(
              <Select style={{ width: 300 }} placeholder="Pilih Agama">
                {religion.map((arr, key) => {
                  return (
                    <Select.Option value={arr.id} key={"religion-" + key}>
                      {arr.name}
                    </Select.Option>
                  );
                })}
              </Select>
            )}
          </Form.Item>
          <Form.Item label="Program Study (Prodi):">
            {getFieldDecorator("study_program_id", {
              rules: [
                {
                  required: true,
                  message: "Silahkan pilih prodi",
                },
              ],
            })(
              <Select style={{ width: 300 }} placeholder="Pilih Prodi">
                {studyProgram.map((arr, key) => {
                  return (
                    <Select.Option value={arr.id} key={"study-program-" + key}>
                      {arr.name}
                    </Select.Option>
                  );
                })}
              </Select>
            )}
          </Form.Item>
          <Form.Item label="Akun untuk login:">
            {getFieldDecorator("user_id", {
              rules: [
                {
                  required: true,
                  message: "Silahkan pilih akun untuk login",
                },
              ],
            })(
              <Select
                style={{ width: 300 }}
                placeholder="Pilih akun untuk login"
              >
                {user.map((arr, key) => {
                  return (
                    <Select.Option value={arr.id} key={"user-" + key}>
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

export default Form.create({ name: "AddStudentForm" })(AddStudentForm);
