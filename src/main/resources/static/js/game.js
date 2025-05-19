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

    // Display Username
    var username = data.username;
    var usernameDiv = document.getElementById("username");
    usernameDiv.textContent = username;

    // Display user's cards
    var deck = document.getElementById("deck");
    deck.innerHTML = "";
    for (let i=0; i<data.cards.length; i++){
        let card = data.cards[i];
        deck.innerHTML += `<img class="card" src="/svg/cards/${card.id}.svg">`;
    }
    
    // Display Players
    var playerId = data.playerId;
    var players = document.getElementById("players");
    players.innerHTML = "";
    for (let i=0; i<data.players.length; i++){
        let player = data.players[i];

        if (player.id ==  playerId){
            continue;
        }

        var cards = "";
        for (let j=0; j<player.cardCount; j++){
            cards += `<img class="card" src="/svg/cards/BACK.svg">`;
        }

        players.innerHTML += `<div class="player">
                            <div class="profile">
                                <img class="avatar" src="/svg/avatar.svg"></img>
                                <div class="username">${player.username}</div>
                            </div>
                            <div class="deck">
                            ${cards}
                            </div>
                        </div>`

    }

}

export { displayGame };