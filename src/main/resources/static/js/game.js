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
    
    console.log("Game status: " + data);


}

export { displayGame };