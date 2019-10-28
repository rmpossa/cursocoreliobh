import axios from 'axios';
import anime from 'animejs/lib/anime.es.js';

function carregar() {
    axios.get('https://gist.githubusercontent.com/triangletodd/759365aa251db39f414a73b508465d94/raw/a3c605e523e3f2994280a243364e50ea88c64fc0/whatthecommit.sorted.log').then( response => {
            let todos = response.data.split('\n')
            document.getElementById("resposta").textContent = todos[Math.floor(Math.random() * todos.length)]
        })

        anime.timeline()
        .add({
        targets: '#resposta',
        opacity: [0,1],
        easing: "easeInOutQuad",
        duration: 2000
      })}

window.carregar=function(){
    carregar();
}
