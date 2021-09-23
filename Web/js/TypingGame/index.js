const word = document.getElementById('word');
const text = document.getElementById('text');
const scoreEl = document.getElementById('score');
const timeEl = document.getElementById('time');
const endgameEl = document.getElementById('end-game-container');
const settingsBtn = document.getElementById('settings-btn');
const settings = document.getElementById('settings');
const settingsForm = document.getElementById('settings-form');
const difficultySelect = document.getElementById('difficulty');


const words = [
    'sigh',
    'tense',
    'airplane',
    'ball',
    'pies',
    'juice',
    'warlike',
    'bad',
    'north',
    'dependent',
    'steer',
    'silver',
    'highfalutin',
    'superficial',
    'quince',
    'eight',
    'feeble',
    'admit',
    'drag',
    'loving'
];


let difficulty = localStorage.getItem('difficulty') || 'medium';

let randomWord;
let score = 0;
let time = 10;


function getRandomWord() {
    return words[Math.floor(Math.random() * words.length)];
}

const timeInterval = setInterval(updateTime, 1000);

difficultySelect.value = difficulty;

function updateTime() {
    time--;
    timeEl.innerHTML = `${time}s`;

    if (time === 0) {
        clearInterval(timeInterval);
        gameOver();
    }
}

function gameOver() {
    endgameEl.innerHTML = `
    <h1>Time Ran Out</h1>
    <p>Your Final Score is: ${score}</p>
    <button onclick="location.reload()">Play</button>
    `;
    endgameEl.style.display = 'flex';
}

function updateScore() {
    score++;
    scoreEl.innerHTML = score;
}

function addWordToDOM() {
    randomWord = getRandomWord();

    word.innerHTML = randomWord;
}

addWordToDOM();

text.addEventListener('input', event => {
    const insertedText = event.target.value;

    if (insertedText === randomWord) {
        addWordToDOM();
        updateScore();
        event.target.value = '';
        if (difficulty === 'hard') {
            time += 1;
        } else if (difficulty === 'medium') {
            time += 3;
        } else {
            time += 5;
        }
    }

});

settingsBtn.addEventListener('click', () => {
    settings.classList.toggle('hide');
})

settingsForm.addEventListener('change', (event) => {
    difficulty = event.target.value;
    localStorage.setItem('difficulty', difficulty);
});

text.focus();