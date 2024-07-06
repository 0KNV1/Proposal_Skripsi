import React,{Component} from "react";
import { Card, Button, Table, message, Divider } from "antd";
import {
  getLinguisticValues,
  deleteLinguisticValue,
  editLinguisticValue,
  addLinguisticValue,
} from "@/api/linguisticValue";

import TypingCard from "@/components/TypingCard";
import EditLinguisticValueForm from "./forms/edit-linguistic-value-form";
import AddLinguisticValueForm from "./forms/add-linguistic-value-form";
const { Column } = Table;

class LinguisticValue extends Component {
    state = {
        linguisticValues: [],
        editLinguisticValueModalVisible: false,
        editLinguisticValueModalLoading: false,
        currentRowData: {},
        addLinguisticValueModalVisible: false,
        addLinguisticValueModalLoading: false,
    };
    getLinguisticValues = async () => {
        const result = await getLinguisticValues();
        const { content, statusCode } = result.data;
        if (statusCode === 200) {
        this.setState({
            linguisticValues: content,
        });
        }
    };
    handleEditLinguisticValue = (row) => {
        this.setState({
        currentRowData: Object.assign({}, row),
        editLinguisticValueModalVisible: true,
        });
    }

    handleDeleteLinguisticValue = (row) => {
        const { id } = row;
        if (id === "admin") {
        message.error("不能删除管理员用户！");
        return;
        }
        deleteLinguisticValue({ id }).then((res) => {
        message.success("berhasil dihapus");
        this.getLinguisticValues();
        });
    };
    handleCancel = () => {
        this.setState({
        addLinguisticValueModalVisible: false,
        editLinguisticValueModalVisible: false,
        });
    };
    handleAddLinguisticValue = () => {
        this.setState({
        addLinguisticValueModalVisible: true,
        });
    };
    handleAddLinguisticValueOk = (_) => {
        const { form } = this.addLinguisticValueFormRef.props;
        form.validateFields((err, values) => {
        if (err) {
            return;
        }
        this.setState({ addLinguisticValueModalLoading: true });
        addLinguisticValue(values)
            .then((response) => {
            form.resetFields();
            this.setState({
                addLinguisticValueModalVisible: false,
                addLinguisticValueModalLoading: false,
            });
            message.success("添加成功!");
            this.getLinguisticValues();
            })
            .catch((error) => {
            this.setState({ addLinguisticValueModalLoading: false });
            });
        });
    };

    handleEditLinguisticValueOk = (_) => {
        const { form } = this.editLinguisticValueFormRef.props;
        form.validateFields((err, values) => {
        if (err) {
            return;
        }
        this.setState({ editLinguisticValueModalLoading: true });
        editLinguisticValue(values, this.state.currentRowData.id).then((res) => {
            message.success("编辑成功");
            this.setState({
                editLinguisticValueModalVisible: false,
                editLinguisticValueModalLoading: false,
            });
            message.success("编辑成功!");
            this.getLinguisticValues();
            
        });
        });
    };
    componentDidMount() {
        this.getLinguisticValues();
    }

    render() {
        const { linguisticValues } = this.state;
        const title = (
        <span>    
            <Button type="primary" onClick={this.handleAddLinguisticValue}>
                Tambah Nilai Dari Tabel Linguistic Untuk IVIHV
            </Button>
        </span>
        );
        return (
        <div className="app-container">
            <TypingCard source="模糊集合语言值管理" />
            <Card title = {title} >
            <Table dataSource={linguisticValues} rowKey="id">
                <Column title="ID" dataIndex="id" key="id" />
                <Column title="Name" dataIndex="name" key="name" />
                <Column title="Value 1" dataIndex="value1" key="value1" />
                <Column title="Value 2" dataIndex="value2" key="value2" />
                <Column title="Value 3" dataIndex="value3" key="value3" />
                <Column title="Value 4" dataIndex="value4" key="value4" />
                <Column title="Average" dataIndex="avg" key="avg" />
                <Column
                    title="Operasi"
                    key="action"
                    width={195}
                    align="center"
                    render={(text, row) => (
                        <span>
                            <Button
                                type="primary"
                                shape="circle"
                                icon="edit"
                                title="编辑"
                                onClick={() => this.handleEditLinguisticValue(row)}
                            />
                            <Divider type="vertical" />
                            <Button
                                type="primary"
                                shape="circle"
                                icon="delete"
                                title="删除"
                                onClick={() => this.handleDeleteLinguisticValue(row)}
                            />
                        </span>
                    )}
                />
            </Table>
            </Card>
            <EditLinguisticValueForm
            wrappedComponentRef={(form) => (this.editLinguisticValueFormRef = form)}
            visible={this.state.editLinguisticValueModalVisible}
            confirmLoading={this.state.editLinguisticValueModalLoading}
            onCancel={this.handleCancel}
            onOk={this.handleEditLinguisticValueOk}
            currentRowData={this.state.currentRowData}
            />
            <AddLinguisticValueForm
            wrappedComponentRef={(form) => (this.addLinguisticValueFormRef = form)}
            visible={this.state.addLinguisticValueModalVisible}
            confirmLoading={this.state.addLinguisticValueModalLoading}
            onCancel={this.handleCancel}
            onOk={this.handleAddLinguisticValueOk}
            />
        </div>
        );
    }
}
export default LinguisticValue;