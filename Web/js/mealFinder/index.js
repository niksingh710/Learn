const search = document.getElementById('search'),
    submit = document.getElementById('submit'),
    mealsEl = document.getElementById('meals'),
    single_mealEl = document.getElementById('single-meal'),
    resultHeading = document.getElementById('result-heading'),
    random = document.getElementById('random');


async function searchMeal(event) {
    event.preventDefault();

    single_mealEl.innerHTML = ``;

    const term = search.value;

    if (term.trim()) {
        const data = await fetch(`https://www.themealdb.com/api/json/v1/1/search.php?s=${term}`);
        const dataJson = await data.json();
        resultHeading.innerHTML = `<h2>Search Result for '${term}':</h2>`;

        if (dataJson.meals === null) {
            resultHeading.innerHTML = `<p>There are no search result try again</p>`;
        } else {
            mealsEl.innerHTML = dataJson.meals.map(meal => `
            <div class="meal">
            <img src="${meal.strMealThumb}" alt="strMeal"/>
            <div class="meal-info" data-mealID="${meal.idMeal}">
            <h3>${meal.strMeal}</h3>
            </div>
            </div>
            `).join('');
        }
        search.value = ``;

    } else {
        alert('Please Enter a Search Term ! ');
    }
}

async function getRandomMeal() {
    mealsEl.innerHTML = ``;
    resultHeading.innerHTML = ``;

    const data = await fetch('https://www.themealdb.com/api/json/v1/1/random.php');
    const dataJson = await data.json();

    const meal = dataJson.meals[0];
    addMealtoDOM(meal);
}

async function getMealByID(mealID) {
    const data = await fetch(`https://www.themealdb.com/api/json/v1/1/lookup.php?i=${mealID}`);
    const dataJson = await data.json();

    const meal = dataJson.meals[0];

    addMealtoDOM(meal);
}

function addMealtoDOM(meal) {
    const ingredient = [];

    for (let i = 1; i <= 20; i++) {
        if (meal[`strIngredient${i}`]) {
            ingredient.push(`${meal[`strIngredient${i}`]} - ${meal[`strMeasure${i}`]}`);
        } else {
            break;
        }
    }
    single_mealEl.innerHTML = `
    <div class="single-meal">
    <h1>${meal.strMeal}</h1>
    <img src="${meal.strMealThumb}" alt="${meal.strMeal}" />
    <div class="single-meal-info">
    ${meal.strCategory ? `<p>${meal.strCategory}</p>` : ''}
    ${meal.strArea ? `<p>${meal.strArea}</p>` : ''}
    </div>
    <div class="main">
    <p>${meal.strInstructions}</p>
    <h2>Ingredients</h2>
    <ul>
    ${ingredient.map(ing => `<li>${ing}</li>`).join('')}
    </ul>
    </div>
    </div>
    `;
}

submit.addEventListener('submit', searchMeal);


mealsEl.addEventListener('click', (e) => {
    const mealInfo = e.path.find(item => {
        if (item.classList) {
            return item.classList.contains('meal-info');
        } else {
            return false;
        }
    });

    if (mealInfo) {
        const mealID = mealInfo.getAttribute('data-mealid');
        getMealByID(mealID);
    }

});

random.addEventListener('click', getRandomMeal);