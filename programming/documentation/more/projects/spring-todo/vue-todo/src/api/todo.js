import request from '@/utils/request';

export let getTodoListByPage = (pageSize = 5, currentPage = 1) => {
  return request.get(`todo/${pageSize}/${currentPage}`);
};

export let addTodo = (todo) => {
  return request.post(`todo`, todo);
};

export let updateTodo = (todo) => {
  return request.put(`todo`, todo);
};

export let removeTodoById = (id) => {
  return request.delete(`todo/${id}`);
};
