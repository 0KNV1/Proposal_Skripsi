import React, { Component } from "react";
import { Card, Button, Table, message, Divider,Checkbox } from "antd";
import{
    getAllQuestionsByRPS,
} from "@/api/criteriaValue";
import {
    getRPSDetail,
  } from "@/api/rpsDetail";
import TypingCard from "@/components/TypingCard";
import { withRouter } from "react-router";
import { Link } from "react-router-dom";

const { Column } = Table;

class QuestionIndexQuiz1 extends Component {

    state ={
      criteriaValue:[],
      rpsDetail: [],
      questions:[],
      currentRowData: {},
      rpsID: "",
    };  

    getQuestionsQuiz = async (rpsID) => {
      const result = await getAllQuestionsByRPS(rpsID);
      const { content, statusCode } = result.data;
    
      if (statusCode === 200) {
        return content.filter(question => 
          question.examType2 === 'QUIZ'
        );
      }
    
      return [];
    }
    
};