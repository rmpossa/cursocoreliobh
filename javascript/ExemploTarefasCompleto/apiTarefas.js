import axios from "axios";
const API_URL = "http://localhost:3000/tarefas";
const TIME_URL = "http://worldclockapi.com/api/json/est/now";
async function getTarefas() {
  try {
    return await axios.get(API_URL);
  } catch (e) {
    return "Erro ao obter tarefas";
  }
}

async function editaTarefa(id, tarefa) {
  try {
    return await axios.patch(API_URL + "/" + id, tarefa);
  } catch (e) {
    return "Erro ao editar tarefa";
  }
}

async function deletaTarefa(id) {
  try {
    return await axios.delete(API_URL + "/" + id);
  } catch (e) {
    return "Erro ao excluir tarefa";
  }
}
async function getTime() {
  return await axios.get(TIME_URL);
}
async function adicionaTarefa(tarefa) {
  try {
    return await axios.post(API_URL, tarefa);
  } catch (e) {
    return "Erro ao adicionar tarefa";
  }
}

export default {
  getTarefas,
  deletaTarefa,
  adicionaTarefa,
  editaTarefa,
  getTime
};
