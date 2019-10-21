function Produto(nome, preco) {
    this.nome = nome
    this.preco = preco
}

function Fabricante(empresa, produtos) {
    this.empresa = empresa;
    this.produtos = produtos;
}

let original = [{
    "nome": "Eveleen",
    "preco": 25
  }, {
    "nome": "Irina",
    "preco": 64
  }, {
    "nome": "Dyana",
    "preco": 94
  }, {
    "nome": "Portia",
    "preco": 92
  }, {
    "nome": "Clarissa",
    "preco": 86
  }, {
    "nome": "Aili",
    "preco": 34
  }, {
    "nome": "Bil",
    "preco": 31
  }, {
    "nome": "Judas",
    "preco": 36
  }, {
    "nome": "Eberhard",
    "preco": 27
  }, {
    "nome": "Lenette",
    "preco": 49
  }]

  let produtos = original.map(item => new Produto(item.nome,item.preco))
//  for (let item of original) {
//    produtos.push(new Produto(item.nome, item.preco))
//  }

  function comparaProduto(p1, p2) {
    if (p1.preco > p2.preco)
        return 1
    else if (p1.preco === p2.preco)
        return 0
    else
        return -1
  }

  produtos.sort(comparaProduto)

  console.log(produtos)