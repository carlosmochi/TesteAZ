<template>
  <div class="container">
    <div v-for="task in this.tasks">
        <TaskCard v-bind:taskData="task" />
    </div>
  </div>
  <button type="button" class="btn btn-primary" v-on:click="this.openCreateForm">Criar Tarefa</button>
  <div class="form_background" v-on:click="this.toggleForm()" v-bind:hidden="this.hideForm">
    <div v-on:click="event => event.stopPropagation()" class="form_div">
        <CreationForm v-bind:taskFormData="selectedTask" v-bind:hidden="this.isUpdateForm"/>
        <UpdateForm v-bind:taskFormData="selectedTask" v-bind:hidden="!this.isUpdateForm"/>
    </div>
  </div>
</template>

<script>
import TaskService from './services/TaskServices'
import TaskCard from './TaskCard.vue'
import CreationForm from './CreateTaskForm.vue'
import UpdateForm from './UpdateTaskForm.vue'

export default {
  name: 'TaskList',
  components:{
    'TaskCard': TaskCard,
    'CreationForm': CreationForm,
    'UpdateForm': UpdateForm
  },
  data() {
    return {
      tasks: [],
      selectedTask: {},
      isUpdateForm: true,
      hideForm: true
    }
  },
  methods: {
    toggleForm(formType){
      if(formType == 'create'){
        this.isUpdateForm = false
      }else if(formType == 'update'){
        this.isUpdateForm = true
        console.log(this.updateForm);
        
      }
        this.hideForm = !this.hideForm

    },
    openUpdateForm(data){
        this.selectedTask = data
        console.log(data)
        console.log(this.selectedTask);
        
        this.toggleForm('update');   
    },
    openCreateForm(){
        this.selectedTask = {
            title: '',
            description: '',
            completed: false
        }
        this.isUpdateForm = false
        this.toggleForm('create')
    },  
    getAllTasks() {
      TaskService.getAllTasks()
        .then((response) => {
          this.tasks = response.data
          console.log(this.tasks[0]);
          
        })
        .catch((error) => {console.log(error)})
    },
    deleteTask(id){
        TaskService.deleteTask(id)
            .then((response) => {
                alert(response.data)
                this.getAllTasks()
            })
            .catch((error) =>{console.log(error)})
    },
    createTask(data){
        TaskService.createNewTask(data)
            .then(response =>{
                alert(response.data)
                this.getAllTasks()
            })
            .catch(error => alert(error.response.data.message))
    },
    updateTask(data){
      TaskService.updateTask(data.id, data)
        .then(response => {
          alert(response.data)
          this.getAllTasks()
        })
        .catch(error => console.log(error))
    }
  },
  mounted() {
    this.getAllTasks()
  }
}
</script>

<style scoped>
.container {
  padding-top: 10px;
  width: 80vw;
  height: 83vh;
  background-color: aliceblue;
  border-radius: 25px;
  border: 1px solid black;
  margin: 10px;
  margin-top: 0;
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
  grid-template-rows: 1fr 1fr 1fr;
  overflow-y: scroll;
}

.form_background{
    position: absolute;
    width: 90vw;
    height: 90vh;
    background-color: rgba(255, 255, 255, 0.85);
}

.form_div{
    border: 1px solid black;
    background-color: white;
    width: 35vw;
    height: 70vh;
    margin: 0 auto;
    padding: 10px;
}
</style>
