<template>
    <div class="form_container">
        <form>
            <div class="mb-3">
                <label for="taskTitle" class="form-label">Titulo</label>
                <input type="text" class="form-control" id="taskTitle" v-model="this.taskFormData.title">
            </div>
            <div class="mb-3">
                <label for="taskDescription" class="form-label">Descrição</label>
                <textarea class="form-control" id="taskDescription" cols="40" rows="5" v-model="this.taskFormData.description">
                    </textarea>
            </div>
            <button v-on:click="this.submitData" type="submit" class="btn btn-primary">Submit</button>
        </form>
    </div>    
</template>

<script>
    export default{
        name: 'UpdateForm',
        props: ['taskFormData', 'taskId'],
        methods:{
            submitData(event){
                event.preventDefault();
                this.$parent.updateTask(this.taskFormData);
                this.$parent.toggleForm();
            }
        }
    }
</script>

<style scoped>

</style>