var startup_pack = document.getElementById("startup-pack");
var btnNombre = document.getElementById("btnNombre");
var nameInput = document.getElementById("name");
var gameCode = document.getElementById("gameCode");
var loader = document.getElementById("loader");
var dropdown = document.getElementById("dropdown");
var partyMenu = document.getElementById("partyMenu");

nameInput.focus();

// Adds animation and removes when it ends
function animateAndRemove(element, animation){
    element.classList.add("animate__animated", animation);
    element.addEventListener("animationend", function() {
        element.classList.remove("animate__animated", animation);
    });
}

function juegoInit () {
    var name = nameInput.value;
    var code = gameCode.value;

    if (name === "") {
        animateAndRemove(startup_pack, "animate__wobble");
        return 0;
    }

    startup_pack.classList.add("animate__animated", "animate__fadeOutUpBig");
    setTimeout(function() {
        startup_pack.classList.add("off");
        loader.classList.remove("off");
    }, 400);

}

// Event listeners para inputs
btnNombre.onclick = juegoInit;
nameInput.addEventListener("keydown", function (e) {
    if (e.key === "Enter") {
        juegoInit();
    }
});
gameCode.addEventListener("keydown", function (e) {
    if (e.key === "Enter") {
        juegoInit();
    }
});

dropdown.onclick = function() {
    dropdown.classList.add("off");
    partyMenu.classList.remove("off");
}



