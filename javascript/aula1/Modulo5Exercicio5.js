//import 'bootstrap'
//import 'bootstrap/dist/css/bootstrap.css'
//import '@fortawesome/fontawesome-free/css/all.css'

    function enviarNovaTarefa() {
        console.log("chamou funcao")

        let novaTarefa = {
            titulo: document.getElementById("titulo").value,
            data_criacao: document.getElementById("dataCriacao").value,
            data_entrega: document.getElementById("dataEntrega").value,
            completo: false
        }

        console.log(novaTarefa)

        axios.post("http://localhost:3000/tarefas",novaTarefa).then(() => listarTarefas());

        event.preventDefault()

    }

    function listarTarefas() {

        let ulLista = document.getElementById("lista")
        ulLista.innerHTML = ""

        axios.get("http://localhost:3000/tarefas").then(response =>
        { 
            response.data.forEach(tarefa => 
                {
                    let elementoLi = document.createElement("li");
                    let inputCheck = document.createElement("input");
                    
                    inputCheck.type = "checkbox";
                    //crie um elemento do tipo span e adicione-o na variavel abaixo
                    let spanDescricao = document.createElement("span");
                    spanDescricao.textContent = tarefa.titulo;
                    
                    //Complete o IF = Se a tarefa está completa
                    if (tarefa.completo) {
                        inputCheck.checked = true;
                        spanDescricao.classList.add("completo");
                    }
                    //Adiciona os elemento ao LI
                    elementoLi.appendChild(inputCheck);
                    elementoLi.appendChild(spanDescricao);
                    
                    //TODO ver essa alteranativa: inputCheck.addEventListener("click", completarTarefa).bind(inputCheck)
                    inputCheck.onclick=function() {completarTarefa(inputCheck, tarefa)};
                    
                    //Adiciona o LI ao UL
                    ulLista.appendChild(elementoLi);


                }
            
            
            ) 
        } 
        
        )


    }

    let completarTarefa = (inputCheck, tarefa) => {
        let elemSpan = inputCheck.parentNode.getElementsByTagName("span")[0];

        elemSpan.classList.toggle("completo");

        tarefa.completo = !tarefa.completo

        // TODO método PUT na tarefa via axios
        axios.put("http://localhost:3000/tarefas/"+tarefa.id, tarefa).then(() => alert("Tarefa atualizada!"))

    };
    window.onload = () => {
        listarTarefas();
    }
