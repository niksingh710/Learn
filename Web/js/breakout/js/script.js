const rulesBtn = document.getElementById("rules-btn");
const closeBtn = document.getElementById("close-btn");
const rules = document.getElementById("rules");
const canvas = document.getElementById('canvas');
const ctx = canvas.getContext('2d');


let score = 0;

const brickCCount = 9;
const brickRCount = 5;
const bricks = [];


const ball = {
    x: canvas.width / 2,
    y: canvas.height / 2,
    size: 10,
    speed: 4,
    dx: 4,
    dy: -4,
};

const paddle = {
    x: canvas.width / 2 - 40,
    y: canvas.height - 20,
    w: 80,
    h: 10,
    size: 10,
    speed: 8,
    dx: 0,
}

const brickInfo = {
    w: 70,
    h: 20,
    padding: 10,
    offsetX: 45,
    offsetY: 60,
    visible: true,
};

for (let i = 0; i < brickCCount; i++) {
    bricks[i] = [];
    for (let j = 0; j < brickRCount; j++) {
        const x = i * (brickInfo.w + brickInfo.padding) + brickInfo.offsetX;
        const y = j * (brickInfo.h + brickInfo.padding) + brickInfo.offsetY;
        bricks[i][j] = { x, y, ...brickInfo };
    }
}



function drawScore() {
    ctx.font = "20px Arial";
    ctx.fillText(`Score: ${score}`, canvas.width - 100, 30);
}

function drawBall() {
    ctx.beginPath();
    ctx.arc(ball.x, ball.y, ball.size, 0, Math.PI * 2);
    ctx.fillStyle = "#0095dd";
    ctx.fill();
    ctx.closePath();
}


function drawPaddle() {
    ctx.beginPath();
    ctx.rect(paddle.x, paddle.y, paddle.w, paddle.h);
    ctx.fillStyle = "#0095dd";
    ctx.fill();
    ctx.closePath();
}


function drawBricks() {

    bricks.forEach(column => {
        column.forEach(brick => {
            ctx.beginPath();
            ctx.rect(brick.x, brick.y, brick.w, brick.h);
            ctx.fillStyle = brick.visible ? "#0095dd" : "transparent";
            ctx.fill();
            ctx.closePath();
        });
    });

}


rulesBtn.addEventListener('click', () => {
    rules.classList.add('show');
});


closeBtn.addEventListener('click', () => {
    rules.classList.remove('show');
});


function movePaddle() {
    paddle.x += paddle.dx;

    if (paddle.x + paddle.w > canvas.width) {
        paddle.x = canvas.width - paddle.w;
    }

    if (paddle.x < 0) {
        paddle.x = 0;
    }
}

function moveBall() {
    ball.x += ball.dx;
    ball.y += ball.dy;

    if (ball.x + ball.size > canvas.width || ball.x - ball.size < 0) {
        ball.dx *= -1;
    }
    if (ball.y + ball.size > canvas.height || ball.y - ball.size < 0) {
        ball.dy *= -1;
    }

    if (ball.x - ball.size > paddle.x && ball.x + ball.size < paddle.x + paddle.w && ball.y + ball.size > paddle.y && ball.y - ball.size < paddle.y + paddle.h) {
        ball.dy *= -1;
    }


    bricks.forEach(col => {
        col.forEach(brick => {
            if (brick.visible) {
                if (ball.x - ball.size > brick.x && ball.x + ball.size < brick.x + brick.w && ball.y + ball.size > brick.y && ball.y - ball.size < brick.y + brick.h) {
                    ball.dy *= -1;
                    brick.visible = false;
                    increaseScore();
                }
            }
        })
    });

    if (ball.y + ball.size > canvas.height) {
        showAllbricks();
        score = 0;
    }



}

function increaseScore() {
    score++;

    if (score % (brickCCount * brickRCount) == 0) {
        showAllbricks();
    }
}

function showAllbricks() {
    bricks.forEach(col => {
        col.forEach(brick => {
            brick.visible = true;
        })
    });
}
function drawEverything() {

    ctx.clearRect(0, 0, canvas.width, canvas.height);



    drawBall();
    drawPaddle();
    drawScore();
    drawBricks();
}

function update() {

    movePaddle();
    moveBall();

    drawEverything();
    requestAnimationFrame(update);
}

update();


document.addEventListener('keydown', keyDown);
document.addEventListener('keyup', keyUp);

function keyDown(e) {
    if (e.key == "ArrowRight" || e.key == "Right") {
        paddle.dx = paddle.speed;
    }
    if (e.key == "ArrowLeft" || e.key == "Left") {
        paddle.dx = -paddle.speed;
    }
}
function keyUp(e) {
    if (e.key == "ArrowRight" || e.key == "Right" || e.key == "ArrowLeft" || e.key == "Left") {
        paddle.dx = 0;
    }
}