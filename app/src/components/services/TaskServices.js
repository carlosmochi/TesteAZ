import http from './API/APIConnection'

class TaskService {
  getAllTasks() {
    http.defaults.headers.get['Access-Control-Allow-Origin'] = '*';
    return http.get()
  }
  getTaskById(id) {
    return http.get(`/${id}`)
  }
  createNewTask(data) {
    return http.post('',data)
  }
  updateTask(id, data) {
    return http.put(`/${id}`, data)
  }
  deleteTask(id) {
    return http.delete(`/${id}`)
  }
}

export default new TaskService()
