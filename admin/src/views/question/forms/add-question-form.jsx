import React, { Component } from "react";
import { Form, Input, Modal, Select, Upload, Icon } from "antd";
const { TextArea } = Input;
class AddQuestionForm extends Component {
  state = {
    fileList: [],
  };

  handleBeforeUpload = (file) => {
    if (file) {
      this.setState(state => ({
        fileList: [...state.fileList, file],
      }));
    }
    return false; // return false to prevent automatic upload
  };
  render() {
    const { visible, onCancel, onOk, form, confirmLoading } = this.props;
    const { getFieldDecorator } = this.props.form;
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
        title="Tambah Pertanyaan"
        visible={visible}
        onCancel={onCancel}
        onOk={onOk}
        confirmLoading={confirmLoading}
      >
        
        <Form {...formItemLayout} encType="multipart/form-data">
          <Form.Item label="Pertanyaan :">
            {getFieldDecorator("title", {
              rules: [
                { required: true, message: "Silahkan isikan pertanyaan" },
              ],
            })(<Input placeholder="Pertanyaan" />)}
          </Form.Item>
          <Form.Item label="Deskripsi Pertanyaan:">
            {getFieldDecorator("description", {
              rules: [
                {
                  required: true,
                  message: "Silahkan isikan deskripsi pertanyaan",
                },
              ],
            })(<TextArea rows={4} placeholder="Deskripsi pertanyaan" />)}
          </Form.Item>
          <Form.Item label="Tipe Pertanyaan:">
            {getFieldDecorator("question_type", {
              rules: [
                {
                  required: true,
                  message: "Silahkan pilih tipe pertanyaan",
                },
              ],
            })(
              <Select
                style={{ width: 300 }}
                placeholder="Pilih tipe pertanyaan"
              >
                <Select.Option value={"IMAGE"}>Gambar</Select.Option>
                <Select.Option value={"AUDIO"}>Musik / Audio</Select.Option>
                <Select.Option value={"VIDEO"}>Video</Select.Option>
                <Select.Option value={"NORMAL"}>Normal</Select.Option>
              </Select>
            )}
          </Form.Item>
          <Form.Item label="Tipe Jawaban:">
            {getFieldDecorator("answer_type", {
              rules: [
                {
                  required: true,
                  message: "Silahkan pilih tipe jawaban",
                },
              ],
            })(
              <Select style={{ width: 300 }} placeholder="Pilih tipe jawaban">
                <Select.Option value={"MULTIPLE_CHOICE"}>
                  Pilihan Ganda
                </Select.Option>
                <Select.Option value={"BOOLEAN"}>Benar / Salah</Select.Option>
                <Select.Option value={"COMPLETION"}>
                  Menyelesaikan kalimat rumpang
                </Select.Option>
              </Select>
            )}
          </Form.Item>
          <Form.Item label="File">
              {getFieldDecorator('file')(
                <Upload.Dragger
                  name="file"
                  beforeUpload={this.handleBeforeUpload}
                  maxCount={1}
                >
                  <p className="ant-upload-drag-icon">
                    <Icon type="inbox" />
                  </p>
                  <p className="ant-upload-text">Click or drag file to this area to upload</p>
                  <p className="ant-upload-hint">Support for a single or bulk upload.</p>
                </Upload.Dragger>
              )}
            </Form.Item>
        </Form>
      </Modal>
    );
  }
}

export default Form.create({ name: "AddQuestionForm" })(AddQuestionForm);
