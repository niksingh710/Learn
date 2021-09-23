const msgEl = document.getElementById("msg");
const play = document.getElementById("play-again");
const randomNum = getRandomNum();

console.log(`Number: ${randomNum}`);

window.SpeechRecognition = window.SpeechRecognition || window.webkitSpeechRecognition;

let recognition = new window.SpeechRecognition();

recognition.start();

function getRandomNum() {
    return Math.floor(Math.random() * 100) + 1;
}

function onSpeak(e) {
    const message = e.results[0][0].transcript;
    writeMessage(message);
    checkNumber(message);
}

function writeMessage(message) {
    msg.innerHTML = `
    <div>You Said:</div>
        <span class="box">${message}</span>
    `;
}

function checkNumber(message) {
    const num = +message;

    if (Number.isNaN(num)) {
        msg.innerHTML = `
        <div>Not a Valid Number</div>
        `;
        return;
    }
    if (num > 100 || num < 1) {
        msg.innerHTML = `
        <div>Number Must be between 1 - 100</div>
        `;
        return;
    }

    if (num === randomNum) {
        document.body.innerHTML = `
        <h2>Congrats You Guessed the Number<br><br><br>
        It Was ${num}
        </h2>

        <button class="play-again" id="play-again">Play Again</button>
        `;
    } else if (num > randomNum) {
        msg.innerHTML += `
        <div>Go Lower</div>
        `;
        return;

    } else {
        msg.innerHTML += `
        <div>Go Higher</div>
        `;
        return;
    }
}

recognition.addEventListener('result', onSpeak);
recognition.addEventListener('end', () => {
    recognition.start();
});

document.body.addEventListener('click', (e) => {
    if (e.target.id == 'play-again') {
        window.location.reload();
    }
});