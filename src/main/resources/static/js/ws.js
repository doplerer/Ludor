import { displayGame } from './game.js';

let socket;

function connectWebSocket(username, partyCode) {
    return new Promise((resolve, reject) => {
        socket = new WebSocket("ws://localhost:8080/ws");

        socket.onopen = function () {
            console.log("Conectado al Servidor");
            socket.send(JSON.stringify({ type: "JOIN", username, partyCode }));
            resolve(socket);
        };

        socket.onmessage = function (event) {

            // GameStatus message
            const data = JSON.parse(event.data);
            if (data.type === "STATUS") {
                displayGame(JSON.parse(event.data));
            }else{
                // else
            console.log(event.data);
            }
        };

        socket.onclose = function () {
            console.log("Desconectado del WebSocket");
        };

        socket.onerror = function (error) {
            console.error("Error en WebSocket", error);
            reject(error);
        };
    });
}

export { connectWebSocket, socket };