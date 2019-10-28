import "nes.css/css/nes.min.css";
import "toastr/build/toastr.min.css";

import tarefasController from "./tarefasController";

tarefasController.init("holder");
window.deletaTarefa = function(id) {
  tarefasController.deletaTarefa(id);
};
window.editaTarefa = function(tarefa) {
  document.getElementById("tarefa_titulo_edicao").value = tarefa.titulo;
  document.getElementById("tarefa_done_edicao").checked = tarefa.done;
  document.getElementById("dialog-default").showModal();
  document.getElementById("confirmaEdicao").onclick = function() {
    let tar = {};
    tar.id = tarefa.id;
    tar.titulo = document.getElementById("tarefa_titulo_edicao").value;
    tar.done = document.getElementById("tarefa_done_edicao").checked;
    tarefasController.editaTarefa(tar);
  };
};
window.completaTarefa = function(id) {
  tarefasController.completaTarefa(id);
};
document.getElementById("adicionar").onclick = () => {
  tarefasController.adicionaTarefa(
    document.getElementById("tarefa_titulo").value
  );
  document.getElementById("tarefa_titulo").value = "";
};
document.getElementById("dark_mode_checkbox").onclick = function() {
  let all = document.getElementsByTagName("*");
  for (let el of all) {
    if (el.tagName !== "SCRIPT") {
      el.classList.toggle("is-dark");
    }
  }
  document.getElementsByTagName("BODY")[0].classList.toggle("dark-background");
};
