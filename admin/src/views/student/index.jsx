import React, { Component } from "react";
import { Card, Button, Table, message, Divider } from "antd";
import {
  getStudents,
  deleteStudent,
  editStudent,
  addStudent,
} from "@/api/student";
import { getReligions } from "@/api/religion";
import { getUsersNotUsedInLectures } from "@/api/user";
import { getStudyPrograms } from "@/api/studyProgram";

import TypingCard from "@/components/TypingCard";
import EditStudentForm from "./forms/edit-student-form";
import AddStudentForm from "./forms/add-student-form";
const { Column } = Table;
class Student extends Component {
  state = {
    students: [],
    religions: [],
    users: [],
    studyPrograms: [],
    editStudentModalVisible: false,
    editStudentModalLoading: false,
    currentRowData: {},
    addStudentModalVisible: false,
    addStudentModalLoading: false,
  };
  getStudents = async () => {
    const result = await getStudents();
    const { content, statusCode } = result.data;
    if (statusCode === 200) {
      this.setState({
        students: content,
      });
    }
  };

  getReligions = async () => {
    const result = await getReligions();
    const { content, statusCode } = result.data;

    if (statusCode === 200) {
      this.setState({
        religions: content,
      });
    }
  };

  getUsers = async () => {
    const result = await getUsersNotUsedInLectures();
    const { content, statusCode } = result.data;

    if (statusCode === 200) {
      this.setState({
        users: content,
      });
    }
  };

  getStudyPrograms = async () => {
    const result = await getStudyPrograms();
    const { content, statusCode } = result.data;

    if (statusCode === 200) {
      this.setState({
        studyPrograms: content,
      });
    }
  };

  handleEditStudent = (row) => {
    this.setState({
      currentRowData: Object.assign({}, row),
      editStudentModalVisible: true,
    });
  };

  handleDeleteStudent = (row) => {
    const { id } = row;
    if (id === "admin") {
      message.error("不能删除管理员用户！");
      return;
    }
    deleteStudent({ id }).then((res) => {
      message.success("berhasil dihapus");
      this.getStudents();
      this.getUsers();
    });
  };

  handleEditStudentOk = (_) => {
    const { form } = this.editStudentFormRef.props;
    form.validateFields((err, values) => {
      if (err) {
        return;
      }
      this.setState({ editModalLoading: true });
      editStudent(values)
        .then((response) => {
          form.resetFields();
          this.setState({
            editStudentModalVisible: false,
            editStudentModalLoading: false,
          });
          message.success("编辑成功!");
          this.getStudents();
          this.getUsers();
        })
        .catch((e) => {
          message.success("编辑失败,请重试!");
        });
    });
  };

  handleCancel = (_) => {
    this.setState({
      editStudentModalVisible: false,
      addStudentModalVisible: false,
    });
  };

  handleAddStudent = (row) => {
    this.setState({
      addStudentModalVisible: true,
    });
  };

  handleAddStudentOk = (_) => {
    const { form } = this.addStudentFormRef.props;
    form.validateFields((err, values) => {
      if (err) {
        return;
      }
      this.setState({ addStudentModalLoading: true });
      addStudent(values)
        .then((response) => {
          form.resetFields();
          this.setState({
            addStudentModalVisible: false,
            addStudentModalLoading: false,
          });
          message.success("添加成功!");
          this.getStudents();
          this.getUsers();
        })
        .catch((e) => {
          message.success("添加失败,请重试!");
        });
    });
  };
  componentDidMount() {
    this.getStudents();
    this.getReligions();
    this.getUsers();
    this.getStudyPrograms();
  }
  render() {
    const { students, religions, users, studyPrograms } = this.state;
    const title = (
      <span>
        <Button type="primary" onClick={this.handleAddStudent}>
          Tambahkan mahasiswa
        </Button>
      </span>
    );
    const cardContent = `Di sini, Anda dapat mengelola mahasiswa di sistem, seperti menambahkan mahasiswa baru, atau mengubah mahasiswa yang sudah ada di sistem.`;
    return (
      <div className="app-container">
        <TypingCard title="Manajemen Mahasiswa" source={cardContent} />
        <br />
        <Card title={title}>
          <Table bordered rowKey="id" dataSource={students} pagination={false}>
            <Column title="NIDN" dataIndex="id" key="id" align="center" />
            <Column title="Nama" dataIndex="name" key="name" align="center" />
            <Column
              title="Program Studi"
              dataIndex="studyProgram.name"
              key="studyProgram.name"
              align="center"
            />
            <Column
              title="Telepon"
              dataIndex="phone"
              key="phone"
              align="center"
            />
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
                    onClick={this.handleEditStudent.bind(null, row)}
                  />
                  <Divider type="vertical" />
                  <Button
                    type="primary"
                    shape="circle"
                    icon="delete"
                    title="删除"
                    onClick={this.handleDeleteStudent.bind(null, row)}
                  />
                </span>
              )}
            />
          </Table>
        </Card>
        <EditStudentForm
          currentRowData={this.state.currentRowData}
          wrappedComponentRef={(formRef) => (this.editStudentFormRef = formRef)}
          visible={this.state.editStudentModalVisible}
          confirmLoading={this.state.editStudentModalLoading}
          onCancel={this.handleCancel}
          onOk={this.handleEditStudentOk}
        />
        <AddStudentForm
          wrappedComponentRef={(formRef) => (this.addStudentFormRef = formRef)}
          visible={this.state.addStudentModalVisible}
          confirmLoading={this.state.addStudentModalLoading}
          onCancel={this.handleCancel}
          onOk={this.handleAddStudentOk}
          religion={religions}
          user={users}
          studyProgram={studyPrograms}
        />
      </div>
    );
  }
}

export default Student;
