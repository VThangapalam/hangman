var base_url = "http://localhost:8093";
function init() {
    storeWrongGuess(0);

}


function startPlay() {
    var y = document.getElementById("wordDiv");
    y.style.display = "block";
    var x = document.getElementById("canvasDiv");
    x.style.display = "block";

    var startButton = document.getElementById("startButton");
    startButton.style.display = "none";

    var xhr = new XMLHttpRequest();
    xhr.open('GET', base_url + "/game/getGameId", true);
    xhr.send();

    xhr.onreadystatechange = processRequest;

    function processRequest(e) {
        if (xhr.readyState == 4 && xhr.status == 200) {
            var response = JSON.parse(xhr.responseText);
            storeGameId(response.gameId);
            storeWordGuessed(response.wordGuessed);
            showWordSpace();
        }
    }


}


function clickedAlpha(alpha) {
    var xhr = new XMLHttpRequest();
    var gameId = getGameId();

    xhr.open('GET', base_url + "/game/" + gameId + "/guess/" + alpha.value, true);
    xhr.send();

    xhr.onreadystatechange = processRequest;

    function processRequest(e) {
        if (xhr.readyState == 4 && xhr.status == 200) {
            var response = JSON.parse(xhr.responseText);
            var prevWrong = getWrongGuess();
            var currWrong = response.numberOfWrongGuess;
            var wordGuess = response.wordGuessed;
            storeWordGuessed(response.wordGuessed);
            showWordSpace();

            //correct guess
            var guessStr = alpha.value.toString();
            if (wordGuess.indexOf("_") == -1)
            {

                document.getElementById("resultMessage").innerHTML = "Won Game!";
                var nextButton = document.getElementById("nextButton");
                nextButton.style.display = "block";

                styleCorrectGuess(guessStr);
                updateWonGames(response.numberOfGamesWon);
                $('#alphabhetDiv').block({message: null});

            } else {

                if (currWrong > prevWrong) {

                    
                    styleWrongGuess(guessStr);
                    storeWrongGuess(currWrong);
                    drawCanvas(currWrong);
                    if (currWrong == 10) {
                        updateLostGames(response.numberOfGamesLost);
                        document.getElementById("resultMessage").innerHTML = "Lost Game!";
                        //alert("Game Lost !!!");
                        $('#alphabhetDiv').block({message: null});
                        var nextButton = document.getElementById("nextButton");
                        nextButton.style.display = "block";
                    }
                } else {
                    //correct guess
                    var guessStr = alpha.value.toString();
                    styleCorrectGuess(guessStr);
                }


            }//else

        } //if status


    } //inner func


} // func


function createAlphaButton() {
    var alphabet = 'abcdefghijklmnopqrstuvwxyz';
    var y = document.getElementById("alphabhetDiv");
    for (var i = 0; i < alphabet.length; i++) {
        var currChar = alphabet.charAt(i);
        y.innerHTML += '<input type="button" class="btn btn-primary" onclick="clickedAlpha(this)" name="' + currChar + '" value="' + currChar + '">';
     }
}

function drawCanvas(wrongGuess) {
    var canvas = document.getElementById("myCanvas");
    var ctx = canvas.getContext("2d");



    switch (wrongGuess) {
        case 1:
            //line 1
            ctx.beginPath();
            ctx.moveTo(0, 90);
            ctx.lineTo(100, 90);
            ctx.stroke();
            break;
        case 2:
            //line 2
            ctx.beginPath();
            ctx.moveTo(25, 90);
            ctx.lineTo(25, 10);
            ctx.stroke();
            break;
        case 3:
            //line 3		
            ctx.beginPath();
            ctx.moveTo(25, 10);
            ctx.lineTo(75, 10);
            ctx.stroke();
            break;
        case 4:
            //line 4	
            ctx.beginPath();
            ctx.moveTo(75, 10);
            ctx.lineTo(75, 20);
            ctx.stroke();
            break;
        case 5:
            //circle 5
            ctx.beginPath();
            ctx.arc(75, 30, 10, 0, 2 * Math.PI);
            ctx.stroke();
            break;
        case 6:
//line 6		 
            ctx.beginPath();
            ctx.moveTo(75, 40);
            ctx.lineTo(75, 70);
            ctx.stroke();
            break;
        case 7:
//line 7	
            ctx.beginPath();
            ctx.moveTo(75, 45);
            ctx.lineTo(65, 50);
            ctx.stroke();
            break;
        case 8:
//line 8	
            ctx.beginPath();
            ctx.moveTo(75, 45);
            ctx.lineTo(85, 50);
            ctx.stroke();
            break;
        case 9:
//line 9	
            ctx.beginPath();
            ctx.moveTo(75, 70);
            ctx.lineTo(65, 75);
            ctx.stroke();
            break;
        case 10:
//line 10	
            ctx.beginPath();
            ctx.moveTo(75, 70);
            ctx.lineTo(85, 75);
            ctx.stroke();
            break;

    }

}


function showWordSpace() {
    var wordGuess = getWordGuessed();
    console.log("word to be gues " + wordGuess);
    var word = "";
    var len = wordGuess.length;
    for (i = 0; i <= len; i++) {
        word = word + wordGuess.charAt(i);
        if (i <= len) {
            word = word + " ";
        }
    }

    document.getElementById("wordPara").innerHTML = word;
}

function storeGameId(gameId) {
    sessionStorage.setItem("gameId", gameId);
}

function getGameId() {
    return sessionStorage.getItem("gameId");
}


function storeWordGuessed(word) {
    sessionStorage.setItem("guess", word);
}

function getWordGuessed() {
    return sessionStorage.getItem("guess");
}

function storeWrongGuess(wrongGuessNumber) {
    sessionStorage.setItem("wrongGuess", wrongGuessNumber);
}

function getWrongGuess() {
    return sessionStorage.getItem("wrongGuess");
}

function updateWonGames(won) {
    document.getElementById("wonCnt").innerHTML = "Won: " + won;
}

function updateLostGames(lost) {
    document.getElementById("lostCnt").innerHTML = "Lost: " + lost;
}

function clearCanvas() {
    var canvas = document.getElementById("myCanvas");
    var ctx = canvas.getContext("2d");
    ctx.clearRect(0, 0, canvas.width, canvas.height);

}


function resetWord() {
    storeWrongGuess(0);
    $('#alphabhetDiv').unblock();
    //clear alpha styling
    var alpDiv = document.getElementById("alphabhetDiv");
    while (alpDiv.hasChildNodes()) {
        alpDiv.removeChild(alpDiv.lastChild);
        var nextButton = document.getElementById("nextButton");
        nextButton.style.display = "none";
        document.getElementById("resultMessage").innerHTML = "";
    }
   createAlphaButton();
    clearCanvas();
}
function newWord() {

    resetWord();
    var xhr = new XMLHttpRequest();
    var gameId = getGameId();
    xhr.open('GET', base_url + "/game/" + gameId + "/newWord", true);
    xhr.send();

    xhr.onreadystatechange = processRequest;

    function processRequest(e) {
        if (xhr.readyState == 4 && xhr.status == 200) {
            var response = JSON.parse(xhr.responseText);
            console.log(response);
            storeGameId(response.gameId);
            storeWordGuessed(response.wordGuessed);
            showWordSpace();
        }
    }
}

function styleCorrectGuess(guess) {
    var alphButtons = document.getElementById('alphabhetDiv').getElementsByTagName('input');
    for (var i = 0; i < alphButtons.length; ++i) {
        var btnClicked = alphButtons[i];
        if (btnClicked.value == guess) {
            btnClicked.className = "btn btn-success";
            btnClicked.disabled = true;
            break;
        }

    }
}

function styleWrongGuess(guess) {

    var alphButtons = document.getElementById('alphabhetDiv').getElementsByTagName('input');
    for (var i = 0; i < alphButtons.length; ++i) {
        var btnClicked = alphButtons[i];
        if (btnClicked.value == guess) {
            btnClicked.className = "btn btn-danger";
            btnClicked.disabled = true;
            break;
        }
    }
}

function hideAlphaDiv() {
    var alp = document.getElementById("alphabhetDiv");
    alp.style.display = "none";
}

function showAlphaDiv() {
    var alp = document.getElementById("alphabhetDiv");
    alp.style.display = "block";
}