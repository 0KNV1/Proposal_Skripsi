import request from "@/utils/request";

export function addAttemptExercise(data) {
  return request({
    url: "/exercise-attempt",
    method: "post",
    data,
  });
}

export function getAttemptExerciseByUserID(id) {
  return request({
    url: `/exercise-attempt?userID=${id}`,
    method: "get",
  });
}

export function getAttemptExerciseByExerciseID(id) {
  return request({
    url: `/exercise-attempt?exerciseID=${id}`,
    method: "get",
  });
}
