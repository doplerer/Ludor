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

    // User's Data

    var username = data.username;
    var playerId = data.playerId;
    
    // Add Players
    var players = document.getElementById("players");
    players.innerHTML = "";
    for (let i=0; i<data.players.length; i++){
        let player = data.players[i];
        console.log(player);

        if (player.id ==  playerId){
            break;
        }

        players.innerHTML += `<div class="player">
                            <div class="profile">
                                <img class="avatar" src="/svg/avatar.svg"></img>
                                <div class="username">${player.username}</div>
                            </div>
                            <div class="deck">
                                <img class="card" src="/svg/cards/BACK.svg">
                                <img class="card" src="/svg/cards/BACK.svg">
                                <img class="card" src="/svg/cards/BACK.svg">
                                <img class="card" src="/svg/cards/BACK.svg">
                                <img class="card" src="/svg/cards/BACK.svg">
                            </div>
                        </div>`

    }

    // User's Username
    var usernameDiv = document.getElementById("username");
    usernameDiv.textContent = username;

    // Display cards
    console.log(data.cards);


}

export { displayGame };