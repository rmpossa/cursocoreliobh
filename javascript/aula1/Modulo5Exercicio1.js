window.onload = function () {
    let tiposAnuncio = [
        {
            titulo: "Grátis",
            taxa: 0.00
        },
        {
            titulo: "Clássico",
            taxa: 0.11
        },
        {
            titulo: "Premium",
            taxa: 0.16
        }
    ];
    montarSelect(tiposAnuncio);
};
function montarSelect(listaTipoAnuncios) {
    let selectTipoAnuncio = document.getElementById("selectTipoAnuncio");
    for (let i = 0; i < listaTipoAnuncios.length; i++) {
        var p = document.createElement("option")
        p.value=listaTipoAnuncios[i].taxa
        p.innerHTML=listaTipoAnuncios[i].titulo
        selectTipoAnuncio.appendChild(p)
        /*
            TODO: com o objeto passado. execute a seguinte lógica:
            - O select exibirá o texto para o usuario
            - cada texto estará vinculado ao valor da taxa
            - com isso se o usuario selecionar clássico, o valor será 0.11
        */
    }
}

function calcularTaxaML() {
    //Pega o valor da venda
    const valorVenda = document.getElementById("inputValor").value;
    //Pega o nome e taxa percentual do anuncio
    const tipoAnuncioSelect=document.getElementById("selectTipoAnuncio") //TODO:  alterar essa linha para pegar a opção de tipo de anuncio selecionada
    const tipoAnuncioNome=tipoAnuncioSelect.text; //TODO: use a variavel tipoAnuncioSelect para pega a palavra selecionada (grátis, etc..)
    const tipoAnuncioTaxa=tipoAnuncioSelect.value; //TODO: novamnete, use a variavel tipoAnuncioSelect, mas pegue a taxa (0 , 0.11, etc..)
    //Pega a tag onde será exibido o resultado
    const resultado = document.getElementById("resultado");
    if (tipoAnuncioNome === "Grátis") {
        resultado.innerHTML = formatarDinheiro(0);
    } else {
        resultado.innerHTML = formatarDinheiro(valorVenda*tipoAnuncioTaxa);
        /*
        TODO: Criar a regra para anúncios clássico e premium
        */
    }
}

function formatarDinheiro(valor) {
    return "R$" + valor.toFixed(2)
    /*
    TODO: Converter o valor recebido em reais
    Exemplo: 
    0 -> R$ 0,00
    0.4 -> R$ 0,40
    12 -> R$ 12,00
    */
}