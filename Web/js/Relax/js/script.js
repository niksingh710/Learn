const container = document.getElementById('container');
const text = document.getElementById('text');

const total = 7500;

const breath = total / 5 * 2;

const hold = total / 5;

breathAnimation();


function breathAnimation() {
    text.innerText = `Breath In!`;

    container.className = "container grow";

    setTimeout(() => {
        text.innerText = `Hold!`;
        setTimeout(() => {
            container.className = "container shrink";
            text.innerText = `Breath Out`;
        }, hold);
    }, breath);

}

setInterval(breathAnimation, total);