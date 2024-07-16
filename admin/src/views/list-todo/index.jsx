import React, { Component } from "react";
import { Row, Col, Icon } from "antd";
import CountUp from "react-countup";
import { Card,  Table } from "antd";
import TypingCard from "@/components/TypingCard";

import { getQuiz } from "@/api/quiz";
import { getRPS } from "@/api/rps";
import {reqUserInfo} from "@/api/user";

const { Column } = Table;
class ListTodo extends Component {
    constructor(props) {
        super(props);
        this.state = {
            rps:[],
            quiz:[],
            userInfo:[],
            quizMessages: [],
        };
    }
    getQuiz = async () => {
        const result = await getQuiz();
        const { content, statusCode } = result.data;
        console.log(result.data);
        if (statusCode === 200) {
          this.setState({
            quiz: content,
          });
        }
      };

        getRps = async () => {
            const result = await getRPS();
            const { content, statusCode } = result.data;
        
            if (statusCode === 200) {
            this.setState({
                rps: content,
            });
            }
        };

        getUserInfo = async () => {
            const result = await reqUserInfo();
            const { content, statusCode } = result.data;
        
            if (statusCode === 200) {
            this.setState({
                userInfo: content,
            });
            }
        };

        componentDidMount() {
            this.getQuiz();
            this.getRps();
            this.getUserInfo();
            this.fetchData();

        }
        fetchData = async () => {
            try {
              const quizResponse = await getQuiz();
              const { content: quizContent, statusCode: quizStatusCode } = quizResponse.data;
        
              const userInfoResponse = await reqUserInfo();
              const { content: userInfoContent, statusCode: userInfoStatusCode } = userInfoResponse.data;
        
              const rpsResponse = await getRPS();
              const { content: rpsContent, statusCode: rpsStatusCode } = rpsResponse.data;
        
              // Extract the list of devLecturerIds from the rpsResponse
              const devLecturerIds = rpsContent[0].dev_lecturers.map(lecturer => lecturer.id);
            
              // Get the user's role and id from the userInfoResponse
              const { id: userId, roles: userRole } = userInfoResponse.data;
                
              if (quizStatusCode === 200 && devLecturerIds.includes(userId)) {
                this.setState({
                  quizMessages: quizContent
                    .filter(item => rpsContent.some(rps => rps.id === item.rps.id))
                    .map(item => ({
                      message: item.message,
                      quiz: item.name,
                      rpsName: item.rps.name,
                      devLecturer: rpsContent.find(rps => rps.id === item.rps.id).dev_lecturers
                    })),
                });
              }
            } catch (error) {
              console.error(error);
            }
        };
        render() {
            const { quiz, quizMessages, rps } = this.state;
            const title = (
                <span>
                    List Todo
                  
                </span>
              );

              const combinedData = quiz.map((item, index) => ({
                ...item,
                quizMessage: quizMessages[index],
              })); 
            const cardContent = `Di sini, Anda dapat mengetahui list yang harus dikerjakan oleh Anda.`;
            return (
            <div className="app-container">
                <TypingCard title="Manajemen Kuis" source={cardContent} />
                <br />
                <Card title={title}>
                <Table bordered rowKey={(record, index) => index} dataSource={quizMessages} pagination={false}>
                    <Column title="Quiz Messages" dataIndex="message" key="message" align="center" />
                    <Column title="Quiz Name" dataIndex="quiz" key="rpsName" align="center" />
                    <Column
                    title="status"
                    dataIndex=""
                    key=""
                    align="center"
                    render={(text, record) => (
                        <div style={{ color: 'red' }}>
                        Not Completed
                        </div>
                    )}
                    />               
                     </Table>
                </Card>
            </div>
            );

          }

}

export default ListTodo;
