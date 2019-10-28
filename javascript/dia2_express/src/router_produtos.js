/* @flow */

const express = require("express");
const routerProdutos = express.Router();

routerProdutos.get("/", (req, res) => {
    res.send(`Produtos GET ALL... `);
});

routerProdutos.get("/:id", (req, res) => {
    res.send(`Produtos GET ${req.params.id}`);
});

routerProdutos.delete("/:id", (req, res) => {
    res.send(`Produtos DELETE... ${req.params.id}`);
});

routerProdutos.post("/", (req, res) => {
    res.send(`Produtos POST... `);
});

routerProdutos.put("/:id", (req, res) => {
    res.send(`Produtos PUT... ${req.params.id}`);
});

module.exports = routerProdutos;