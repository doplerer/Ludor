import { socket } from './ws.js';

// Variables globales
let selectedCards = [];

function displayGame(data) {

    //Disable loader if not already disabled
    var loader = document.getElementById("loader");
    if (!loader.classList.contains("off")) {
        loader.classList.add("off");
    }

    //Enable game if not already enabled
    if (document.getElementById("game").classList.contains("off")) {
        document.getElementById("game").classList.remove("off");
    }

    // Current Turn's ID
    var currentTurn = data.currentTurn.id;
    if (currentTurn == data.playerId) {
        var profileImg = document.getElementById("profile").querySelector("img");
        profileImg.classList.add("turn");
    }

    // Display Username
    var username = data.username;
    var usernameDiv = document.getElementById("username");
    usernameDiv.textContent = username;

    // Display user's cards
    var deck = document.getElementById("deck");
    deck.innerHTML = "";

    // sort cards by card value
    let sortedCards = [...data.cards].sort((a, b) => a.value - b.value);

    for (let i=0; i<sortedCards.length; i++){
        let card = sortedCards[i];
        deck.innerHTML += `<img class="card" id="${card.id}" src="/svg/cards/${card.id}.svg">`;
    }
    
    // Display Players
    var playerId = data.playerId;
    var players = document.getElementById("players");
    players.innerHTML = "";
    for (let i=0; i<data.players.length; i++){
        let player = data.players[i];

        if (player.id == playerId){
            continue;
        }

        var extra = ""
        if (currentTurn == player.id) {
            var extra = "turn";
        }

        var cards = "";
        for (let j=0; j<player.cardCount; j++){
            cards += `<img class="card" src="/svg/cards/BACK.svg">`;
        }

        players.innerHTML += `<div class="player">
                            <div class="profile">
                                <img class="avatar ${extra}" src="/svg/avatar.svg"></img>
                                <div class="username">${player.username}</div>
                            </div>
                            <div class="deck">
                            ${cards}
                            </div>
                        </div>`

    }





    // Display cards on table (TurnHistory)
    var middle = document.getElementById("middle");
    var turnHistory = data.turnHistory;
    middle.innerHTML = "";
    for (let i=0; i<turnHistory.length; i++){
        let turn = turnHistory[i];
        middle.innerHTML += `<img src="/svg/cards/${turn.move.id}.svg">`;
    }

    // Evento para seleccionar carta/s
    deck.addEventListener('click', function(event) {
        if (event.target.classList.contains('card')) {
            selectCard(event.target);
        }
    });

}
// Function to select or deselect a card
function selectCard(cardElement) {
    const cardId = cardElement.id;

    if (cardElement.classList.contains('selected')) {
        // Deseleccionar carta
        cardElement.classList.remove('selected');
        selectedCards = selectedCards.filter(id => id !== cardId);
    } else {
        // Seleccionar carta
        cardElement.classList.add('selected');
        selectedCards.push(cardId);
    }
}

// Function process turn
function processTurn() {
    if (socket && socket.readyState === WebSocket.OPEN) {
        socket.send(JSON.stringify({ type: "hand", selectedCards}));
    } else {
        console.error("WebSocket is not open. Cannot send move.");
    }
}

// EVENTOS



export { displayGame };