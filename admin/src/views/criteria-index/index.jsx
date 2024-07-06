import React, { Component } from "react";
import { Card, Button, Table, message, Divider } from "antd";

import{
    getAllCriteriaValueByQuestion,
    addCriteriaValue,
    editCriteriaValue,
    deleteCriteriavalue,
} from "@/api/criteriaValue";
import {getLectures} from "@/api/lecture";
import { getTeamTeachings } from "@/api/teamTeaching";
import { getLinguisticValues } from "@/api/linguisticValue";

import TypingCard from "@/components/TypingCard";
import { withRouter } from "react-router";
import { Link } from "react-router-dom";

import AddCriteriaValueForm from "./form/add-criteria-value-form";

const { Column } = Table;

class CriteriaIndex extends Component {
    state ={
        criteriaValues:[],
        questions:[],
        linguisticValues:[],
        teamTeachings:[],
        lectures:[],
        linguisticValue:[],
        editCriteriaValueModalVisible: false,
        editCriteriaValueModalLoading: false,
        currentRowData: {},
        addCriteriaValueModalVisible: false,
        addCriteriaValueModalLoading: false,
        questionID: "",
    };

    // Usage to get baseed on id question
    getCriteriaValues = async (questionID) => {
        const result = await getAllCriteriaValueByQuestion(questionID);
        const { content, statusCode } = result.data;
        if (statusCode === 200) {
        this.setState({
            criteriaValues: content,
            selectedQuestionID: questionID, // Add this line
        });
        }
    };
    
    getLectures = async () => {
        const result = await getLectures();
        const {content, statusCode} = result.data;
        if (statusCode === 200) {
            this.setState({
                lectures: content,
            });
        }
    };
    
    getTeamTeachings = async () => {
        const result = await getTeamTeachings();
        const { content, statusCode } = result.data;
        if (statusCode === 200) {
            this.setState({
                teamTeachings: content,
            });
        }
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
    handleAddCriteriaValue = () => {
        this.setState({
            addCriteriaValueModalVisible: true,
        });
    };

    handleCancel = () => {
        this.setState({
          addCriteriaValueModalVisible: false,
        });
      };
    
    
    handleAddCriteriaValueOk = () => {
        const { form } = this.addCriteriaValueFormRef.props;
        form.validateFields((err, values) => {
            if (err) {
                return;
            }
            this.setState({ addCriteriaValueModalLoading: true });
            const questionId = this.state.selectedQuestionID; // Get the questionId from the state
            addCriteriaValue(values, questionId) // Pass the questionId to addCriteriaValue
                .then((response) => {
                    form.resetFields();
                    this.setState({
                        addCriteriaValueModalVisible: false,
                        addCriteriaValueModalLoading: false,
                    });
                    message.success("Berhasil menambahkan Criteria Value!");
                    this.getCriteriaValues(questionId); // Refresh the criteria values
                })
                .catch((e) => {
                    console.error(e.response.data);
                    message.error("Gagal menambahkan Criteria Value!");
                });
        });
    };

    componentDidMount() {
        this.getLectures();
        this.getTeamTeachings();
        this.getLinguisticValues();

        const questionID = this.props.match.params.questionID;
        this.setState({ questionID });
        this.getCriteriaValues(questionID);
      }

    render(){
        
        const { criteriaValues, lectures,linguisticValues,teamTeachings } = this.state;
        const title =(
            <span>
                <Button type="primary" onClick={this.handleAddCriteriaValue}>
                    Berikan Nilai ke soal
                </Button>
            </span>
        );
        const { selectedQuestionID } = this.state;
        const cardContent = `Di sini, Anda dapat memilih pertanyaan di sistem, lalu memberinya nilai masing masing kriteria.`;
        
        const averages = criteriaValues.reduce((acc, curr) => {
            for (let i = 1; i <= 9; i++) {
              const key = `value${i}`;
              if (curr[key] && 'average' in curr[key]) {
                if (!acc[key]) {
                  acc[key] = {
                    sum: 0,
                    count: 0,
                  };
                }
                acc[key].sum += parseFloat(curr[key].average);
                acc[key].count += 1;
              }
            }
            return acc;
          }, {});
          
          // Calculate the average for each value
          for (let i = 1; i <= 9; i++) {
            const key = `value${i}`;
            if (averages[key]) {
              averages[key] = (averages[key].sum / averages[key].count).toFixed(4);
            }
          }
          

        const columns = [
            <Column
                title="ID Pertanyaan"
                dataIndex="id"
                key="selectedQuestionID"
                align="center"
            />
        ];
    
        for (let i = 1; i <= 9; i++) {
            columns.push(
                <Column
                    title={`Nilai Kriteria ${i}`}
                    dataIndex={`value${i}.name`}
                    key={`value${i}.name`}
                    align="center"
                />
            );
        }

        // columns.push(
        //     <Column
        //         title="Avg value"
        //         dataIndex="avgOfAvgValue9"
        //         key="avgOfAvgValue9"
        //         align="center"
        //     />
        // );

        console.log(criteriaValues);
        return(
            <div>
                <TypingCard title={title} source={cardContent} />
                
                <Card title={`ID Pertanyaan: ${this.state.selectedQuestionID}`}>
                    

                    <Table dataSource={criteriaValues} rowKey="id">
                    
                    {columns}
                    </Table>
                </Card>
                
                <table>
                    <thead>
                    <tr>
                        {Array.from({ length: 9 }, (_, i) => (
                        <th key={i} style={{border: '1px solid #ddd', padding: '8px'}}>
                            Average Value{i + 1}
                        </th>
                        ))}
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        {Array.from({ length: 9 }, (_, i) => (
                        <td key={i} style={{border: '1px solid #ddd', padding: '8px'}}>
                            {averages[`value${i + 1}`]}
                        </td>
                        ))}
                    </tr>
                    </tbody>
                </table>

                <AddCriteriaValueForm
                wrappedComponentRef={(formRef) => (this.addCriteriaValueFormRef = formRef)}
                visible={this.state.addCriteriaValueModalVisible}
                confirmLoading={this.state.addCriteriaValueModalLoading}
                onCancel={this.handleCancel}
                onOk={this.handleAddCriteriaValueOk}
                lecture={lectures}
                teamTeachings={teamTeachings}
                linguisticValues={linguisticValues}
                questionID={this.state.questionID}
            />
            </div>

        )

    }


    
}

export default withRouter(CriteriaIndex);