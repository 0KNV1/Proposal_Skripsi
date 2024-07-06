// import React, { Component } from "react";
// import { Card, Button, Table, message, Divider } from "antd";
// import{
//     getAllQuestionsByRPS,
// } from "@/api/criteriaValue";
// import { withRouter } from 'react-router-dom';

// import TypingCard from "@/components/TypingCard";
// import { withRouter } from "react-router";
// import { Link } from "react-router-dom";


// const { Column } = Table;

// class CriteriaValueIndexQuestion extends Component {
//     state ={
//         criteriaValue:[],
//         questions:[],
//         editCriteriaValueModalVisible: false,
//         editCriteriaValueModalLoading: false,
//         currentRowData: {},
//         addCriteriaValueModalVisible: false,
//         addCriteriaValueModalLoading: false,
//         rpsID: "",
//     };
    
//     getQuestions = async (rpsID) => {
//         const result = await getAllQuestionsByRPS(rpsID);
//         const { content, statusCode } = result.data;
//         if (statusCode === 200) {
//           this.setState({
//             questions: content,
//           });
//         }
//     };

//     componentDidMount() {
//         const rpsID = this.props.match.params.rpsID;
//         this.setState({ rpsID });
//         this.getQuestions(rpsID);
//     }
//     render(){
//         const { questions, rpsID } = this.state;
//         const title =(
//             <div> Pilih pertanyaan yang akan dinilai</div>
//         );
//         const cardContent = `Di sini, Anda dapat memilih pertanyaan di sistem, lalu memberinya nilai masing masing kriteria.`;
//         return(
//             <div>
//                 <TypingCard title={title} source={cardContent} />
//                 <Card title="Pertanyaan" extra={<Link to={`/criteria-value/add/${rpsID}`}>Tambah</Link>}>
//                     <Table dataSource={questions} rowKey="id">
//                     <Column
//                     title="ID Pertanyaan"
//                     dataIndex="id"
//                     key="id"
//                     align="center"
//                     />
//                     <Column
//                     title="Pertanyaan"
//                     dataIndex="title"
//                     key="title"
//                     align="center"
//                     />
//                     </Table>
//                 </Card>
//             </div>
//         )
//     }

// }
// export default withRouter(Form.create()(CriteriaValueIndexQuestion));