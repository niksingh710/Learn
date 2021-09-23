const draggableList = document.getElementById("draggable-list");
const check = document.getElementById("check");

const data = [
    'Jeff Bezos',
    'Bill Gates',
    'Warren Buffett',
    'Bernard Arnault',
    'Carlos Slim Helu',
    'Amancio Ortega',
    'Larry Ellison',
    'Mark Zuckerberg',
    'Michael Bloomberg',
    'Larry Page'
];

const listItems = [];

let dragStartIndex;

createList();

function createList() {
    [...data]
        .map(a => ({ value: a, sort: Math.random() }))
        .sort((a, b) => a.sort - b.sort)
        .map(item => item.value)
        .forEach((val, index) => {
            const listItem = document.createElement('li');
            listItem.setAttribute('data-index', index);

            listItem.innerHTML = `
        <span class="number">${index + 1}</span>
        <div class="draggable" draggable="true">
        <p class="persons-name">${val}</p>
        <i class="fas fa-grip-lines"></i>
        </div>
        `;
            listItems.push(listItem);

            draggableList.appendChild(listItem);
        });

    addEventListeners();

}

function dragStart() {
    // console.log("start");
    dragStartIndex = +this.closest('li').getAttribute('data-index');
}
function drop() {
    // console.log("drop");
    const dragEndIndex = +this.getAttribute('data-index');
    this.classList.remove('over');
    swapItems(dragStartIndex, dragEndIndex);
}
function dragOver(e) {
    e.preventDefault();
    // console.log("Over");
}
function dragEnter() {
    // console.log("Enter");
    this.classList.add("over");
}
function dragLeave() {
    // console.log("Leave");
    this.classList.remove("over");
}

function swapItems(fromIndex, toIndex) {
    const itemOne = listItems[fromIndex].querySelector(".draggable");
    const itemtwo = listItems[toIndex].querySelector(".draggable");

    listItems[fromIndex].appendChild(itemtwo);
    listItems[toIndex].appendChild(itemOne);

}

function addEventListeners() {
    const draggables = document.querySelectorAll(".draggable");
    const dragListItems = document.querySelectorAll(".draggable-list li");

    draggables.forEach(item =>
        item.addEventListener("dragstart", dragStart)
    );

    dragListItems.forEach(item => {
        item.addEventListener("dragover", dragOver);
        item.addEventListener("drop", drop);
        item.addEventListener("dragenter", dragEnter);
        item.addEventListener("dragleave", dragLeave);

    });

}


function checkOrder() {
    listItems.forEach((item, index) => {
        const personName = item.querySelector(".draggable").innerText.trim();

        if (personName != data[index]) {
            item.classList.add("wrong");
        } else {
            item.classList.remove("wrong");
            item.classList.add("right");
        }
    });
}

check.addEventListener('click', checkOrder);