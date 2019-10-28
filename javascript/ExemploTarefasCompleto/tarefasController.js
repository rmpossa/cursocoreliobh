import apiTarefas from "./apiTarefas";
let HOLDER_ID = "holder";
import toastr from "toastr";
import "toastr/build/toastr.min.css";

async function editaTarefa(tarefa) {
  let { currentDateTime } = (await apiTarefas.getTime()).data;
  tarefa.modifiedAt = currentDateTime;
  await apiTarefas.editaTarefa(tarefa.id, tarefa);
  toastr.success("Tarefa editada com Sucesso!");
  carregaTarefas();
}

async function completaTarefa(id) {
  let tarefa = {};
  tarefa.done = true;
  let { currentDateTime } = (await apiTarefas.getTime()).data;
  tarefa.completedAt = currentDateTime;
  await apiTarefas.editaTarefa(id, tarefa);
  toastr.success("Tarefa completa com Sucesso!");
  carregaTarefas();
}
async function deletaTarefa(id) {
  await apiTarefas.deletaTarefa(id);
  toastr.success("Tarefa removida com Sucesso!");
  carregaTarefas();
}
function mostraTarefas(tarefas) {
  let holder = document.getElementById(HOLDER_ID);
  holder.innerHTML = "";
  let darkMode = document
    .getElementsByTagName("BODY")[0]
    .classList.contains("is-dark");
  for (let tarefa of tarefas) {
    holder.innerHTML += `
    <tr >
        <td id='tarefa_id_${tarefa.id}'>
            ${tarefa.id}
        </td>
        <td id='td_campo_tarefa_${tarefa.id}'>
<div class="nes-field" style='padding-right: 10px'>
  <input type="text"  readonly id="tarefa_titulo_${tarefa.id}" value='${
      tarefa.titulo
    }' class="nes-input ${darkMode ? "is-dark" : ""}">
</div>
        </td>
        <td id='td_done_tarefa_${tarefa.id}'>
        ${
          tarefa.done
            ? `<a href="#" class="nes-badge"><span class="is-success">Done at <br>${
                tarefa.completedAt.split("T")[0]
              }</span></a>`
            : `<button class="nes-btn is-primary" onclick='completaTarefa(${tarefa.id})' >Completar</button>`
        }
        <td>
        <button type="button" class="nes-btn is-warning"
         button onclick='editaTarefa(${JSON.stringify(
           tarefa
         )})' style='' >E</button>
        <button  class="nes-btn is-error" onclick='deletaTarefa(${
          tarefa.id
        })' ><i class="nes-icon close"></i></button>
        </td>
        `;
  }
}
async function carregaTarefas() {
  let response = await apiTarefas.getTarefas();
  mostraTarefas(response.data);
  // toastr.success("Tarefas carregadas com Sucesso!");
}
function init(holderId) {
  HOLDER_ID = holderId;
  carregaTarefas();
}
async function adicionaTarefa(titulo) {
  let tarefa = {};
  tarefa.titulo = titulo;
  tarefa.done = false;
  let { currentDateTime } = (await apiTarefas.getTime()).data;
  tarefa.createdAt = currentDateTime;
  let response = await apiTarefas.adicionaTarefa(tarefa);
  if (response) {
    toastr.success("Tarefa adicionada com Sucesso!");
    carregaTarefas();
  }
}
export default {
  init,
  carregaTarefas,
  deletaTarefa,
  editaTarefa,
  completaTarefa,
  adicionaTarefa
};
