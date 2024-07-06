import React, { Component } from "react";
import { Card, Button, Table, message, Divider } from "antd";
import {
  getQuestions,
  deleteQuestion,
  editQuestion,
  addQuestion,
  getImage,
} from "@/api/question";
import TypingCard from "@/components/TypingCard";
import EditQuestionForm from "./forms/edit-question-form";
import AddQuestionForm from "./forms/add-question-form";
import { withRouter } from "react-router";
import { Link } from "react-router-dom";
import { connect } from 'react-redux';
import axios from "axios";
const { Column } = Table;


class Question extends  React.Component {
  constructor(props) {
    super(props);
    this.state = {
      questions: [],
      editQuestionModalVisible: false,
      editQuestionModalLoading: false,
      currentRowData: {},
      addQuestionModalVisible: false,
      addQuestionModalLoading: false,
      rpsDetailID: "",
      rpsID: "",
      images: {},
    };
  }
  getQuestions = async (rpsDetailID) => {
    const result = await getQuestions(rpsDetailID);
    const { content, statusCode } = result.data;
    if (statusCode === 200) {
      this.setState({
        questions: content,
      });
    }
  };

  handleEditQuestion = (row) => {
    this.setState({
      currentRowData: Object.assign({}, row),
      editQuestionModalVisible: true,
    });
  };

  handleDeleteQuestion = (row) => {
    const { id } = row;
    if (id === "admin") {
      message.error("不能删除管理员用户！");
      return;
    }
    deleteQuestion({ id }).then((res) => {
      message.success("berhasil dihapus");
      this.getQuestions(this.state.rpsDetailID);
    });
  };

  handleEditQuestionOk = (_) => {
    const { form } = this.editQuestionFormRef.props;
    form.validateFields((err, values) => {
      if (err) {
        return;
      }
      this.setState({ editModalLoading: true });
      editQuestion(values)
        .then((response) => {
          form.resetFields();
          this.setState({
            editQuestionModalVisible: false,
            editQuestionModalLoading: false,
          });
          message.success("编辑成功!");
          this.getQuestions(this.state.rpsDetailID);
        })
        .catch((e) => {
          message.success("编辑失败,请重试!");
        });
    });
  };

  handleCancel = (_) => {
    this.setState({
      editQuestionModalVisible: false,
      addQuestionModalVisible: false,
    });
  };

  handleAddQuestion = (row) => {
    this.setState({
      addQuestionModalVisible: true,
    });
  };

  handleAddQuestionOk = () => {
    const { form } = this.addQuestionFormRef.props;
    form.validateFields((err, values) => {
      if (err) {
        return;
      }
      this.setState({ addQuestionModalLoading: true });
      const { file, ...otherValues } = values;
      const formData = new FormData();
      if (file && file.fileList.length > 0) {
        formData.append("file", file.fileList[0].originFileObj);
      }
      formData.append("rps_detail_id", this.state.rpsDetailID);
      formData.append("title", otherValues.title);
    formData.append("description", otherValues.description);
      formData.append("question_type", otherValues.question_type);
      formData.append("answer_type", otherValues.answer_type);
  
      addQuestion(formData)
        .then((response) => {
          form.resetFields();
          this.setState({
            addQuestionModalVisible: false,
            addQuestionModalLoading: false,
          });
          message.success("添加成功!");
          this.getQuestions(this.state.rpsDetailID);
        })
        .catch((e) => {
          message.success("添加失败，请重试!");
        });
    });
  };
  getImage = async (imageName) => {
    try {
      // Dispatch the getImage action...
      await this.props.getImage(imageName);
  
      // The image data is now in the Redux store, so there's no need to update the component's state here
    } catch (error) {
      console.error('Failed to load image:', error);
    }
  };
  componentDidMount() {
    this.setState({
      
      rpsID: this.props.match.params.rpsID,
      rpsDetailID: this.props.match.params.rpsDetailID,
    });
    this.getQuestions(this.props.match.params.rpsDetailID);
    if (this.props.imageNames) {
      this.props.imageNames.forEach(imageName => {
        this.getImage(imageName);
      });
    }
  }

  fetchImage = async (imageName) => {
    if (!imageName) {
      console.error('Image name is not defined');
      return;
    }
  
    try {
      const response = await axios.get(`/api/question/image/${imageName}`, {
        responseType: 'arraybuffer', // This is important for receiving binary data
      });
  
      // Convert the ArrayBuffer to a base64-encoded string
      const base64 = btoa(
        new Uint8Array(response.data).reduce(
          (data, byte) => data + String.fromCharCode(byte),
          '',
        ),
      );
  
      // Update the state with the base64-encoded image data
      this.setState(prevState => ({
        images: {
          ...prevState.images,
          [imageName]: base64,
        },
      }));
    } catch (error) {
      console.error(`Failed to fetch image ${imageName}:`, error);
    }
  };
  render() {
    const { questions, rpsID, rpsDetailID,images } = this.state;
    let imageElements = null;

  if (this.props.imageNames) {
    imageElements = this.props.imageNames.map(imageName => {
      const base64 = images[imageName];
      if (base64) {
        return <img src={`data:image/png;base64,${base64}`} alt={imageName} />;
      } else {
        return null;
      }
    });
  }


    const title = (
      <span>
        <Button type="primary" onClick={this.handleAddQuestion}>
          Tambahkan pertanyaan
        </Button>
      </span>
    );
    const cardContent = `Di sini, Anda dapat mengelola pertanyaan di sistem, seperti menambahkan pertanyaan baru, atau mengubah pertanyaan yang sudah ada di sistem.`;
    return (
      <div className="app-container">
        <TypingCard title="Manajemen Pertanyaan" source={cardContent} />
        <br />
        <Card title={title}>
          <Table bordered rowKey="id" dataSource={questions} pagination={false}>
            <Column
              title="ID Pertanyaan"
              dataIndex="id"
              key="id"
              align="center"
            />
            <Column
              title="Pertanyaan"
              dataIndex="title"
              key="title"
              align="center"
            />
            <Column
              title="Deskripsi Pertanyaan"
              dataIndex="description"
              key="description"
              align="center"
            />
            
            <Column
              title="Tipe Jawaban"
              dataIndex="answerType"
              key="answerType"
              align="center"
            />
            {/* <Column
            title="Image"
            dataIndex="imageName"
            key="image"
            align="center"
            render={(imageName) => {
              const base64Image = this.state.images[imageName];
              if (base64Image) {
                return <img src={`data:image/png;base64,${base64Image}`} alt={imageName} />;
              };
            }}
            /> */}
            <Column
              title="Tipe Soal"
              dataIndex="questionType"
              key="questionType"
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
                    onClick={this.handleEditQuestion.bind(null, row)}
                  />
                  <Divider type="vertical" />
                  <Link to={`/rps/${rpsID}/${rpsDetailID}/${row.id}`}>
                    <Button
                      type="primary"
                      shape="circle"
                      icon="diff"
                      title="Tambahkan Soal"
                    />
                  </Link>
                  <Divider type="vertical" />
                  <Button
                    type="primary"
                    shape="circle"
                    icon="delete"
                    title="删除"
                    onClick={this.handleDeleteQuestion.bind(null, row)}
                  />
                </span>
              )}
            />
          </Table>
        </Card>
        <EditQuestionForm
          currentRowData={this.state.currentRowData}
          wrappedComponentRef={(formRef) =>
            (this.editQuestionFormRef = formRef)
          }
          visible={this.state.editQuestionModalVisible}
          confirmLoading={this.state.editQuestionModalLoading}
          onCancel={this.handleCancel}
          onOk={this.handleEditQuestionOk}
        />
        <AddQuestionForm
          wrappedComponentRef={(formRef) => (this.addQuestionFormRef = formRef)}
          visible={this.state.addQuestionModalVisible}
          confirmLoading={this.state.addQuestionModalLoading}
          onCancel={this.handleCancel}
          onOk={this.handleAddQuestionOk}
        />
      </div>
    );
  }
}


// Map the 'images' state from the Redux store to the component's props
const mapStateToProps = state => ({
  images: state.images
});

// Connect the component to the Redux store and bind the getImage action to the component's props
export default withRouter(connect(mapStateToProps, { getImage })(Question));