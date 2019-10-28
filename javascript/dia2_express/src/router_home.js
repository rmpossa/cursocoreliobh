/* @flow */

const express = require("express");
const routerHome = express.Router();

const routerProdutos = require("./router_produtos.js");

routerHome.use("/produto", routerProdutos);

module.exports = routerHome;