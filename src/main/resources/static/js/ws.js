// WebSocket 
const socket = new WebSocket("ws://localhost:8080/ws");

// Evento cuando la conexión se establece
socket.onopen = function () {
    console.log("Conectado al WebSocket");
};

// Evento cuando se recibe un mensaje
socket.onmessage = function (event) {
    console.log("Mensaje recibido:", event.data);
};

// Evento cuando la conexión se cierra
socket.onclose = function () {
    console.log("Desconectado del WebSocket");
};

// Evento en caso de error
socket.onerror = function (error) {
    console.error("Error en WebSocket", error);
};


