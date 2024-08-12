<template>
  <div class="card_container">
    <div class="card" style="width: 20rem">
      <div class="card-body">
        <h5 class="card-title" >{{ this.taskData.title }}</h5>
        <p class="card-text">
          {{this.taskData.description}}
        </p>
        <h6>Status: {{isCompleted }}</h6>
        <div>
            <div style="display: flex; justify-content: center;">
                <button v-on:click='this.completeTask(this.taskData)' class="btn btn-info">Completar Tarefa</button> 
            </div>
            <button v-on:click='this.deleteTask(this.taskData.id)' class="btn btn-danger">Excluir Tarefa</button>
            <button v-on:click='this.updateTask(this.taskData)' class="btn btn-primary">Alterar Tarefa</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>

    export default{
        name: 'TaskCard',
        props: ['taskData'],
        data(){
            return{
                isCompleted: ''
            }
        },
        methods:{
            deleteTask(id){
                this.$parent.deleteTask(id);
            },
            updateTask(taskData){
                this.$parent.openUpdateForm(taskData);
            },
            completeTask(taskData){
                taskData.completed = true;                
                this.$parent.updateTask(taskData)
            }
        },
        mounted() {
            console.log(this.taskData)
            if(this.taskData.completed == true){
                this.isCompleted = 'Completo'
            }else{
                this.isCompleted = 'Incompleto'
            }
        }
    }

</script>

<style scoped>
    button{
        margin-left: 2px;
        margin-right: 2px;
    }
    .card_container{
        padding: 5px;

    }
</style>
