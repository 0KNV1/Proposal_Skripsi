import request from "@/utils/request";

export function addRPSDetail(data) {
  return request({
    url: "/rps-detail",
    method: "post",
    data,
  });
}

export function getRPSDetailByWeekLabel(weekLabel) {
  return request({
    url: `/rpsdetails/${weekLabel}`,
    method: "get",
  });
}

export function getRPSDetail(rpsID) {
  return request({
    url: `/rps-detail?rpsID=${rpsID}`,
    method: "get",
  });
}

export function editRPSDetail(data, id) {
  return request({
    url: `/rps-detail/${id}`,
    method: "put",
    data,
  });
}

export function deleteRPSDetail(data) {
  return request({
    url: `/rps-detail/${data.id}`,
    method: "delete",
    data,
  });
}
