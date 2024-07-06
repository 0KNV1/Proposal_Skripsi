import React, { Component } from "react";
import { Form, Input, InputNumber, Modal, Select } from "antd";
class AddTeamTeachingForm extends Component {
    render() {
        const { visible, onCancel, onOk, form, confirmLoading, questionID,linguisticValues} = this.props;
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
                visible={visible}
                title="Add Team Teaching"
                okText="Add"
                onCancel={onCancel}
                onOk={onOk}
                confirmLoading={confirmLoading}
            >
                <Form {...formItemLayout}>
                <Form.Item label="Question ID">
                    {getFieldDecorator('questionId', {
                        rules: [{ required: true, message: 'Please input the Question ID!' }],
                        initialValue: this.props.questionID, // Set the initial value to the questionID prop
                    })(<Input />)}
                    </Form.Item>
                    {/* <Form.Item label="Team Teaching ID">
                        {getFieldDecorator('teamTeachingId', {
                            rules: [{ required: true, message: 'Please select the Team Teaching ID!' }],
                        })(
                            <Select style={{ width: 300 }} placeholder="Select Team Teaching">
                                {teamTeachings && Array.isArray(teamTeachings) && teamTeachings.map((arr, key) => {
                                    return (
                                        <Select.Option value={arr.id} key={"teamTeaching-" + key}>
                                            {arr.name}
                                        </Select.Option>
                                    );
                                })}
                            </Select>
                        )}
                    
                    </Form.Item> */}
                        
                    <Form.Item label="Kriteria 1:">
                    {getFieldDecorator("value1", {
                        rules: [
                            {
                                required: true,
                                message: "Silahkan pilih nilai kriteria 1",
                            },
                        ],
                    })(
                        <Select style={{ width: 300 }} placeholder="Pilih Nilai Linguistik">
                            {linguisticValues && Array.isArray(linguisticValues) && linguisticValues.map((arr, key) => {
                                return (
                                    <Select.Option value={arr.id} key={"value1-" + key}>
                                        {arr.name}
                                    </Select.Option>
                                );
                            })}
                        </Select>
                    )}
                </Form.Item>

                    {Array.from({ length: 8 }, (_, i) => i + 2).map(i => (
                    <Form.Item label={`Kriteria ${i}:`}>
                        {getFieldDecorator(`value${i}`, {
                            rules: [{ required: true, message: `Silahkan pilih nilai kriteria ${i}` }],
                        })(
                            <Select style={{ width: 300 }} placeholder={'Pilih Nilai Linguistik '}>
                                {linguisticValues && Array.isArray(linguisticValues) && linguisticValues.map((arr, key) => {
                                    return (
                                        <Select.Option value={arr.id} key={`value${i}-${key}`}>
                                            {arr.name}
                                        </Select.Option>
                                    );
                                })}
                            </Select>
                        )}
                    </Form.Item>
                ))}
                    
                </Form>
            </Modal>
        );
    }
}

export default Form.create()(AddTeamTeachingForm);