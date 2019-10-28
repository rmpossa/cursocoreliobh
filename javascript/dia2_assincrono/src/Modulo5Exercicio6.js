import axios from 'axios';

const apiKey = "7791190b58df194b9af362ce2bed2bf3"

const BASE_URL = function (id, apiKey) {
	return `https://api.openweathermap.org/data/2.5/weather?id=${id}&APPID=${apiKey}`;
}

// latitude and longitude data for our three cities
const BH = "3470127"; // id da cidade de Belo Horizonte
const GV = "3462315"; // id da cidade de Governador Valadares
const OURO_PRETO = "3455671"; // id da cidade de Belo Horizonte

const getWeather = (cityId, apiKey) => axios.get(BASE_URL(cityId, apiKey));

function getBH() {
    getWeather(BH, apiKey)
        .then(result => {
            console.log("BH, with promises");
            console.log(result.data);
        })
        .catch(error => console.log(error.message));
}

function getCitiesInParallel() {
    const bhGet = getWeather(BH, apiKey);
    const gvGet = getWeather(GV, apikey);
    const opGet = getWeather(OURO_PRETO, apiKey);

    let diferenca1 = 0
    let diferenca2 = 0

    Promise.all([bhGet, gvGet, opGet])
        .then(([bhData, gvData, opData]) => {
            console.log("All three cities in parallel, with promises");

            

            console.log(bhData.data);
            console.log(gvData.data);
            console.log(opData.data);
        })
        .catch(error => {
            console.log(error.message);
        });
}

window.getWeather=function() {
    getWeather(cityId, apiKey)
}

window.getBH=function() {
    getBH()
}

window.getCitiesInParallel = () => getCitiesInParallel()