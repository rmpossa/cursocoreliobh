/* @flow */

// const express = require("express");
// const routerHome = express.Router();

// const routerProdutos = require("./router_produtos.js");

// routerHome.use("/produto", routerProdutos);

// module.exports = routerHome;

// app.use(
//     "/",
//     express.static(path.join(__dirname, "./dist"), {
//         immutable: true,
//         maxAge: "30 days"
//     })
// );

// app.use((err, req, res, next) => {
//     console.error("Error....", err.message);
//     res.status(500).send("INTERNAL SERVER ERROR");
// });

// app.listen(8080, () =>
//     console.log(
//         "Mini Express static server ready at http://localhost:8080/ !"
//     )
// );

///////////////////////

// app.use("*", (req, res, next) => {
//     console.log(req.query, req.body);
//     //res.send("Server alive, with Express!");
//     next();
// });

// app.use((req, res, next) => {
//     console.log("Logger... ", new Date(), req.method, req.path);
//     next();
// });

// app.use((req, res, next) => {
//     if (req.method !== "DELETE") {
//         res.send("Server alive, with Express!");
//     } else {
//         next(new Error("DELETEs are not accepted!"));
//     }
// });

// // eslint-disable-next-line no-unused-vars
// app.use((err, req, res, next) => {
//     console.error("Error....", err.message);
//     res.status(500).send("INTERNAL SERVER ERROR");
// });

// app.listen(8080, () =>
//     console.log(
//         "Mini server (with Express) ready at http://localhost:8080/!"
//     )
// );