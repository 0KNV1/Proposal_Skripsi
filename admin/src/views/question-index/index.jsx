import React, { Component } from "react";
import { Card, Button, Table, message, Divider } from "antd";
import{
    getAllQuestionsByRPS,
} from "@/api/criteriaValue";

import TypingCard from "@/components/TypingCard";
import { withRouter } from "react-router";
import { Link } from "react-router-dom";


const { Column } = Table;

class questionIndex extends Component {
    state ={
        criteriaValue:[],
        questions:[],
        editCriteriaValueModalVisible: false,
        editCriteriaValueModalLoading: false,
        currentRowData: {},
        addCriteriaValueModalVisible: false,
        addCriteriaValueModalLoading: false,
        rpsID: "",
    };
    
    getQuestions = async (rpsID) => {
        const result = await getAllQuestionsByRPS(rpsID);
        const { content, statusCode } = result.data;
        if (statusCode === 200) {
          this.setState({
            questions: content,
          });
        }
    };

    componentDidMount() {
        const rpsID = this.props.match.params.rpsID;
        this.setState({ rpsID });
        this.getQuestions(rpsID);
    }
    handleLinkClick = (id) => {
        this.setState({
          selectedQuestionID: id,
        });
    };
    render(){
        const { questions, rpsID } = this.state;
        const title =(
            <div> Pilih pertanyaan yang akan dinilai</div>
        );
        const cardContent = `Di sini, Anda dapat memilih pertanyaan di sistem, lalu memberinya nilai masing masing kriteria.`;
        return(
            <div>
                <TypingCard title={title} source={cardContent} />
                <Card title="Pertanyaan" >
                    <Table dataSource={questions} rowKey="id">
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
                    title="Operasi"
                    key="action"
                    width={195}
                    align="center"
                    render={(text, row) => (
                        <span>
                        
                        <Divider type="vertical" />
                        <Link to={`/index/criteria/${row.id}`} onClick={() => {
                            
                            this.handleLinkClick(row.id);
                            }}>
                            <Button
                                type="primary"
                                shape="circle"
                                icon="diff"
                                title="删除"
                            />
                            </Link>
                            <Divider type="vertical" />
                        
                        </span>
                    )}
                    />
                    
                    </Table>
                </Card>
            </div>
            
        )
    }

}
export default withRouter(questionIndex);