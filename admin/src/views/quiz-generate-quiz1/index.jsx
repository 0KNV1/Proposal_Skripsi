import React, { Component } from "react";
import { Row, Col, Icon } from "antd";
import TypingCard from "@/components/TypingCard";
import { Card,  Table,Select } from "antd";
import{
    getQuestionsByRPSQuiz1,
  } from "@/api/quiz";
  import{
    getAllCriteriaValueByQuestion,
  } from "@/api/criteriaValue";
import { getQuiz } from "@/api/quiz";
import { getRPS } from "@/api/rps";
import {reqUserInfo} from "@/api/user";

const { Column } = Table;
class QuizGenerate extends Component {
    constructor(props) {
      super(props);
      this.state = {
          rps: [],
          quiz: [],
          userInfo: [],
          questionsWithCriteria: [], // Ensure this is initialized
          selectedLecturer: '',
          devLecturerIds: [],
          devLecturers: [],
          isMounted: false,        // Initialize as an empty array
          
      };
  }
    

    
  async componentDidMount() {
    this.setState({ isMounted: true });
    await this.fetchData();
  }

  componentWillUnmount() {
    this.setState({ isMounted: false });
  }

  fetchData = async () => {
    try {
      const quizResponse = await getQuiz();
      const { content: quizContent, statusCode: quizStatusCode } = quizResponse.data;

      const userInfoResponse = await reqUserInfo();
      const { content: userInfoContent, statusCode: userInfoStatusCode } = userInfoResponse.data;

      const rpsResponse = await getRPS();
      const { content: rpsContent, statusCode: rpsStatusCode } = rpsResponse.data;

      let devLecturers = [];

      if (quizStatusCode === 200 && rpsStatusCode === 200) {
        quizContent.forEach(quiz => {
          const matchingRPS = rpsContent.find(rps => rps.id === quiz.rps.id);
          if (matchingRPS) {
            devLecturers.push(...matchingRPS.dev_lecturers);
            console.log(`Dev Lecturers for quiz ${quiz.id}:`, matchingRPS.dev_lecturers);
          } else {
            console.log(`No matching RPS found for quiz ${quiz.id}`);
          }
        });
      }

      if (this.state.isMounted) {
        this.setState({ devLecturers });
      }

      if (quizStatusCode === 200) {
        const rpsID = rpsContent[0].id;
        const result = await getQuestionsByRPSQuiz1(rpsID);
        const { content, statusCode } = result.data;

        if (statusCode === 200) {
          const quizQuestions = content.filter(question => question.examType2 === 'QUIZ');

          const questionsWithCriteria = await Promise.all(quizQuestions.map(async (question) => {
            const criteriaResult = await getAllCriteriaValueByQuestion(question.id);
            if (criteriaResult.data.statusCode === 200 ) {
              question.criteriaValues = criteriaResult.data.content;
            } else {
              question.criteriaValues = [];
            }
            return question;
          }));

          if (this.state.isMounted) {
            this.setState({
              rpsContent: rpsContent,
              questionsWithCriteria: questionsWithCriteria,
            });
          }
        }
      }
    } catch (error) {
      console.error('Error fetching data:', error);
    }
  };

  selectLecture = (lecturerName) => {
    this.setState({ selectedLecturer: lecturerName, isMounted: false }, this.fetchData);
  };

render() {
  const { questionsWithCriteria, selectedLecturer, devLecturers, devLecturerIds, matchingRPS } = this.state;

  // Filter the questionsWithCriteria array based on selectedLecturer and user
  const filteredData = questionsWithCriteria
  .filter(item => item.user === this.state.selectedLecturer)
  .map(item => ({
    id: item.id,
    title: item.question.title,
    user: item.user,
  }));
  const values = Array.from({ length: 9 }, (_, i) => i + 1);

  const criteriaNames = [
    "Evaluation",
    "Synthesis",
    "Comprehension",
    "Analysis",
    "Difficulty",
    "Reliability",
    "Discrimination",
    "Application",
    "Knowledge"
  ];
  
  const columns = values.map((value, index) => (
    <Column
      title={criteriaNames[index]}
      key={`value${value}Name`}
      render={(text, record) => (
        <span>
          {record.criteriaValues && record.criteriaValues[0] && record.criteriaValues[0].user === selectedLecturer
            ? record.criteriaValues[0][`value${value}`].name
            : record.criteriaValues && record.criteriaValues[1] && record.criteriaValues[1].user === selectedLecturer
            ? record.criteriaValues[1][`value${value}`].name
            : record.criteriaValues && record.criteriaValues[2] && record.criteriaValues[2].user === selectedLecturer
            ? record.criteriaValues[2][`value${value}`].name
            : 'N/A'}
        </span>
      )}
    />
  ));
  return (
    <div>
    {/* <h4>{selectedLecturer}</h4> */}
    

      <TypingCard source="Daftar Nilai Quiz Berdasarkan Dosen Yang Menilai" />
      <Select onChange={this.selectLecture} style={{ width: 200, marginBottom: 20 }}>
        {devLecturers.map((lecturer, index) => (
          <Select.Option key={index} value={lecturer.id}>
            {lecturer.name}
          </Select.Option>
        ))}
      </Select>
      
      <Table dataSource={questionsWithCriteria} pagination={false} rowKey="id">
        <Column title="Pertanyaan" dataIndex="title" align="center" key="title" />
        {columns}

        {/* Add more columns as needed */}
      </Table>
      
    </div>
  );
}

}

export default QuizGenerate;
