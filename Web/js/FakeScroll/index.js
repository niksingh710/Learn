const postContainer = document.getElementById('post-container');
const loading = document.querySelector('.loader');
const filter = document.getElementById('filter');

const limit = 5;
let page = 1;

async function getPost() {
    const res = await fetch(`https://jsonplaceholder.typicode.com/posts?_limit=${limit}&_page=${page}`);
    const data = await res.json();

    return data;
}

async function showPost() {
    const posts = await getPost();

    posts.forEach(post => {
        const postEl = document.createElement('div');
        postEl.classList.add('post');
        postEl.innerHTML = `
        <div class="number">${post.id}</div>
        <div class="post-info">
            <h2 class="post-title">${post.title}</h2>
            <p class="post-body">
                ${post.body}
            </p>
        </div>`;

        postContainer.appendChild(postEl);
    });
}

function showLoading() {
    loading.classList.add('show');

    setTimeout(() => {
        loading.classList.remove('show');
        setTimeout(() => {
            page++;
            showPost();
        }, 300);
    }, 1000);
}

function filterPost(event) {
    const term = event.target.value.trim().toLowerCase();
    const posts = document.querySelectorAll('.post');

    posts.forEach(post => {
        const title = post.querySelector('.post-title').innerText.toLowerCase();
        const body = post.querySelector('.post-body').innerText.toLowerCase();

        if (title.indexOf(term) > -1 || body.indexOf(term) > -1) {
            post.style.display = 'flex';
        } else {
            post.style.display = 'none';
        }
    });
}

showPost();


window.addEventListener('scroll', () => {
    const { scrollTop, scrollHeight, clientHeight } = document.documentElement;
    if (scrollTop + clientHeight >= scrollHeight) {
        showLoading();
    }
});


filter.addEventListener('input', filterPost);