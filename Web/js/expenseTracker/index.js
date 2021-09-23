const balance = document.getElementById('balance');
const money_plus = document.getElementById('money-plus');
const money_minus = document.getElementById('money-minus');
const list = document.getElementById('list');
const form = document.getElementById('form');
const text = document.getElementById('text');
const amount = document.getElementById('amount');


// const dummyTransaction = [
//     { id: 1, text: 'Flower', amount: -20 },
//     { id: 2, text: 'Salary', amount: 300 },
//     { id: 3, text: 'Book', amount: -10 },
//     { id: 4, text: 'Camera', amount: 150 },
// ];


const localStorageTransaction = JSON.parse(localStorage.getItem('transactions'));

let transactions = localStorageTransaction || [];

function addTransactionDOM(transaction) {
    const sign = transaction.amount < 0 ? '-' : '+';

    const item = document.createElement('li');

    item.classList.add(transaction.amount < 0 ? 'minus' : 'plus');

    item.innerHTML = `
    ${transaction.text}<span>${sign}${Math.abs(transaction.amount)}</span><button class="delete-btn" onclick="removeTransaction(${transaction.id})">x</button>
    `;
    list.appendChild(item);
}

function updateLocalStorage() {
    localStorage.setItem('transactions', JSON.stringify(transactions))
}

function updateValues() {
    const amounts = transactions.map(transaction => transaction.amount);

    const total = amounts.reduce((acc, item) => (acc += item), 0).toFixed(2);

    const income = amounts.filter(item => item > 0).reduce((acc, item) => (acc += item), 0).toFixed(2);
    const expense = (amounts.filter(item => item < 0).reduce((acc, item) => (acc += item), 0) * -1).toFixed(2);

    balance.innerText = `$${total}`;
    money_plus.innerText = `$${income}`;
    money_minus.innerText = `$${expense}`;

}

function addTransaction(event) {
    event.preventDefault();

    if (text.value.trim() === '' || amount.value.trim() === '') {
        alert("Please enter Text and Amount");
    } else {
        const transaction = {
            id: generateID(),
            text: text.value.trim(),
            amount: +amount.value.trim(),
        };

        transactions.push(transaction);
        addTransactionDOM(transaction);
        updateValues();
        updateLocalStorage();
        text.value = ``;
        amount.value = ``;
    }

}

function generateID() {
    return Math.floor(Math.random() * 10000000);
}

function removeTransaction(id) {
    transactions = transactions.filter(transaction => transaction.id !== id);
    updateLocalStorage();
    init();
}

function init() {
    list.innerHTML = ``;
    transactions.forEach(addTransactionDOM);
    updateValues();
}

init();

form.addEventListener('submit', (event) => addTransaction(event));