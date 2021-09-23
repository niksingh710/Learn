const form = document.getElementById("form");
const search = document.getElementById("search");
const result = document.getElementById("result");
const more = document.getElementById("more");

const apiURL = `https://api.lyrics.ovh`;


form.addEventListener('submit', e => {
    e.preventDefault();

    const searchText = search.value.trim();

    if (!searchText) {
        alert("Blank Search");
    } else {
        searchSongs(searchText);
    }
});

async function searchSongs(text) {
    const res = await fetch(`${apiURL}/suggest/${text}`);
    const data = await res.json();

    show(data);
}


function show(data) {
    result.innerHTML = `<ul class="songs">
    ${data.data.map((item) => `<li>
    <span><strong>${item.artist.name}</strong> - ${item.title}</span>
    <button class="btn" data-artist="${item.artist.name}" data-song-title="${item.title}">Get Lyrics</button>
</li>`).join('')
        }
    </ul > `;

    if (data.prev || data.next) {
        more.innerHTML = `
        ${data.next ? `<button class="btn" onclick="getMore('${data.next}')">Next</button>` : ''}
        ${data.prev ? `<button class="btn" onclick="getMore('${data.prev}')">Prev</button>` : ''}
        `;
    } else {
        more.innerHTML = ``;
    }
}

async function getMore(link) {

    const res = await fetch(`https://cors-anywhere.herokuapp.com/${link}`);
    const data = await res.json();
    show(data);

}


result.addEventListener('click', e => {
    let clicked = e.target;

    if (clicked.tagName == "BUTTON") {
        const artist = clicked.getAttribute('data-artist');
        const title = clicked.getAttribute('data-song-title');

        getLyrics(artist, title);
    }
});

async function getLyrics(artist, title) {
    const res = await fetch(`${apiURL}/v1/${artist}/${title}`);
    const data = await res.json();

    const lyrics = data.lyrics.replace(/(\r\n|\r|\n)/g, '<br>');

    result.innerHTML = `<h2><strong>${artist}</strong> - ${title}</h2><span>${lyrics}</span>`;
    more.innerHTML = ``;
}
