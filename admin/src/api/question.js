import request from "@/utils/request";
import requestForm from "@/utils/requestForm";

import axios from "axios";

export const GET_IMAGE = "GET_IMAGE";
export const UPLOAD_IMAGE = "UPLOAD_IMAGE";

// Your existing code goes here...
export function addQuestion(data) {
  console.log(data); // Log data to the console

  return requestForm({
    url: "/question",
    method: "post",
    data,
  });
}

export function getQuestions(rpsDetailID) {
  return request({
    url: `/question?rpsDetailID=${rpsDetailID}`,
    method: "get",
  });
}


export function getQuestionsByRPS(rpsID) {
  return request({
    url: `/question?rpsID=${rpsID}`,
    method: "get",
  });
}

// export function getImageFromServer(imageName) {
//   return request({
//     url: `/question/images/${imageName}`,
//     method: "get",
//     responseType: 'arraybuffer', // This is important for receiving binary data
//   });
// }

export function editQuestion(data, id) {
  return request({
    url: `/question/${id}`,
    method: "put",
    data,
  });
}


export const getImage = (imageName) => async dispatch => {
  const response = await axios.get(axios.defaults.baseURL + `/api/image/${imageName}`);

  dispatch({
    type: GET_IMAGE,
    payload: response.data
  });
};

export function deleteQuestion(data) {
  return request({
    url: `/question/${data.id}`,
    method: "delete",
    data,
  });
}
