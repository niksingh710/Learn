
//Required variable
const canv=document.getElementById("canvas");
const ctx=canv.getContext("2d");
const scanv=document.getElementById("scorecanvas");
const sctx=scanv.getContext("2d");
box=20;
row=canv.width/box;
column=canv.height/box;
px=py=box;
snake=[];
snake[0]={x:px,y:py}
tail=1;
score=0;
xv=yv=0;
var color="black";
direction="";
var event=document.addEventListener("keydown",keyPush);
foodx=Math.floor(Math.random()*row)*box;
foody=Math.floor(Math.random()*column)*box;
var snakespeed=120;
tap=new Audio ();
tap.src="audio/tap.wav";
tap.playbackRate ="5.0";
tap.volume="0.1";
eat=new Audio ();
eat.src="audio/eat.wav";
eat.playbackRate ="5.0";
dead=new Audio ();
dead.src="audio/dead.wav";
dead.playbackRate ="5.0";
tap.volume="0.8";
head=new Image();
head.src="img/DOWN.png";
food=new Image();
foodpicker();
body=new Image();
body.src="img/BODY.png"
//variable end

//while open window
window.onload=Main;

//main funtion arrainging sub funtion in sequence
function Main(){
    scoreprinter();
    keyevent();
    headpicker();
    draw();
    foodeater();
} game=setInterval(Main,snakespeed);

//drawing required things on canvas
function draw(){
    
    ctx.fillStyle=color;
    ctx.fillRect(0,0,canv.width,canv.height);
    sctx.fillStyle=color;
    sctx.fillRect(0,0,canv.width,canv.height);
    scoreprinter();
    fooddraw();
    snakedraw();
    
}

//drawing Snake
function snakedraw(){
    //moving Snake
    px+=xv;py+=yv;

    //handling snake border activity
    position();
    
    //printing snake
for(var i=0;i<snake.length;i++)
{
    if(i==0)
    {
        ctx.drawImage(head,snake[i].x,snake[i].y,box,box);
    }
    else{
        ctx.fillStyle="black";
ctx.drawImage(body,snake[i].x,snake[i].y,box,box);
    }
if(snake[i].x==foodx && snake[i].y==foody && i>0){
    foodlocation();
} 
if(snake[i].x==px && snake[i].y==py && i>0){
    death();  
}
}
//adding snake in array
snake.unshift({x:px,y:py});
if(snake.length>tail){
    snake.pop();
}
}

//key press
function keyPush(eve){
    if(eve.keyCode==37  && direction!="RIGHT")
    {
        tap.play();
        direction="LEFT";
    }
    else if(eve.keyCode==38 && direction!="DOWN")
    {
        tap.play();
        direction="UP";
    }
    else if(eve.keyCode==39 && direction!="LEFT")
    {
        tap.play();
        direction="RIGHT";
    }
    else if(eve.keyCode==40 && direction!="UP")
    {
        tap.play();
        direction="DOWN";
    }
}

//key Event 
function keyevent(){
    if(direction=="LEFT")
    {
        headpicker();
        xv=-box;yv=0;
    }
    else if(direction=="UP")
    {
        headpicker();
        xv=0;yv=-box;
    }
    else if(direction=="RIGHT")
    {
        headpicker();
        xv=+box;yv=0;
    }
    else if(direction=="DOWN")
    {
        headpicker();
        xv=0;yv=+box;
    }
}
//corner handler
function position()
{
        if(px<0)
        {
            px=canv.width-box;
        }
        if(px>canv.width-box)
        {
            px=0;
        }
        if(py>canv.height-box)
        {
            py=0;
        }
        if(py<0)
        {
            py=canv.height;
        }
}
//food provider
function fooddraw(){
ctx.drawImage(food,foodx,foody,box,box);
}
//food location provider
function foodlocation(){
    foodx=Math.floor(Math.random()*row)*box;
    foody=Math.floor(Math.random()*column)*box;
    foodpicker();
}
//checking food eaten or not
function foodeater(){
    if(foodx==px && foody==py){
        eat.play();
        tail++;
        score++;
        foodlocation();
    }
}

//score printer
function scoreprinter(){
    sctx.fillStyle="white";
    sctx.font="45px Change one";
    sctx.fillText(score,40,40);
    sctx.drawImage(food,15,15,box,box);
}
 //death
 function death(){
     deathsound();
     clearInterval(game);
 }

 // Head changing
 function headpicker(){
    if(direction=="LEFT")
    {
        head.src="img/LEFT.png";
    }
    else if(direction=="UP")
    {
        head.src="img/UP.png";
    }
    else if(direction=="RIGHT")
    {
        head.src="img/RIGHT.png";
    }
    else if(direction=="DOWN")
    {
        head.src="img/DOWN.png";
    }
 }

 //food image
 function foodpicker(){
    var foodtook=Math.floor(Math.random()*5); 
    if(foodtook==0){
        foodpicker();
    }
    if(foodtook==1)
    {
        food.src="img/food1.png";  
    } 
    if(foodtook==2)
    {
        food.src="img/food2.png";
    } 
    if(foodtook==3)
    {
        food.src="img/food3.png";
    } 
    if(foodtook==4)
    {
        food.src="img/food4.png";
    }
 }

    function deathsound(){
    dead.play();
    }
