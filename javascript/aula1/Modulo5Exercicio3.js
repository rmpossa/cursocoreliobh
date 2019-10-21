function Produto(nome, preco, estoque) {
    this.nome = nome
    this.preco = preco
    this.estoque = estoque
}

/*function Fabricante(empresa, produtos) {
    this.empresa = empresa;
    this.produtos = produtos;
}*/

let produtos = [{
  "nome": "Cyndie",
  "preco": 72,
  "estoque": 1
}, {
  "nome": "Alfons",
  "preco": 77,
  "estoque": 10
}, {
  "nome": "Lisle",
  "preco": 80,
  "estoque": 5
}, {
  "nome": "Jonas",
  "preco": 95,
  "estoque": 9
}, {
  "nome": "Auroora",
  "preco": 88,
  "estoque": 1
}, {
  "nome": "Dukey",
  "preco": 12,
  "estoque": 5
}, {
  "nome": "Darya",
  "preco": 47,
  "estoque": 4
}, {
  "nome": "Christal",
  "preco": 20,
  "estoque": 7
}, {
  "nome": "Sande",
  "preco": 1,
  "estoque": 4
}, {
  "nome": "Ruperto",
  "preco": 89,
  "estoque": 1
}]

let carrinho = []


let criarLista = () => {
            
  let ulLista = document.getElementById("listaProdutos");
  //Percorre o objeto JavaScript com nome checklist e adiciona um elemento LI para cada item.
  for (let index = 0; index < produtos.length; index++) {
      let elementoLi = document.createElement("li");
      
      elementoLi.textContent = produtos[index].nome;

      elementoLi.onclick=function() {completarTarefa(elementoLi)};
      
      //Adiciona o LI ao UL
      ulLista.appendChild(elementoLi);
  }
}
let completarTarefa = (elementoLi) => {
  
  // TODO Verificar se tem estoque
  let produto = produtos.filter((p) => p.nome === elementoLi.textContent)[0];

  try {

    if (!produto.estoque) {
      throw new Error("Produto sem estoque"); // (*)
    }

    produto.estoque = produto.estoque - 1;

    
    let produtoEmCarrinho = carrinho.filter((c) => c.produto.nome === produto.nome)[0];

    if(produtoEmCarrinho) {
      produtoEmCarrinho.quantidade++;
      

    }
    carrinho.push({quantidade:1,produto:produto});
  } catch(e) {
    alert(  e.message ); // JSON Error: Incomplete data: no name
  }
};
window.onload = () => {
  criarLista();
}




//  for (let item of original) {
//    produtos.push(new Produto(item.nome, item.preco))
//  }

  // function comparaProduto(p1, p2) {
  //   if (p1.preco > p2.preco)
  //       return 1
  //   else if (p1.preco === p2.preco)
  //       return 0
  //   else
  //       return -1
  // }

  // produtos.sort(comparaProduto)

  // console.log(produtos)