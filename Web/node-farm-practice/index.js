const http = require('http');
const fs = require('fs');
const url = require('url');

const replaceTemp = require('./modules/replaceTemp');

// * Html Files

const errorHTML = fs.readFileSync(`${__dirname}/templates/error404.html`, 'utf-8');
const overviewHTML = fs.readFileSync(`${__dirname}/templates/template-overview.html`, 'utf-8');
const cardHTML = fs.readFileSync(`${__dirname}/templates/template-card.html`, 'utf-8');
const productHTML = fs.readFileSync(`${__dirname}/templates/product.html`, 'utf-8');

// * data Files
const data = fs.readFileSync(`${__dirname}/dev-data/data.json`, 'utf-8');
const dataObj = JSON.parse(data);

// * Server Creation

const server = http.createServer((req, res) => {
  //   const pathName = req.url;
  const { query, pathname: pathName } = url.parse(req.url, true);
  if (pathName === '/' || pathName === '/overview') {
    res.writeHead(200, { 'content-type': 'text/html' });

    const cards = dataObj.map((el) => replaceTemp(el, cardHTML)).join('');
    const output = overviewHTML.replace(/{%PRODUCT_CARDS%}/g, cards);

    res.end(output);
  } else if (pathName === '/api') {
    res.writeHead(200, { 'content-type': 'application/json' });
    res.end(data);
  } else if (pathName === '/product') {
    res.writeHead(200, { 'content-type': 'text/html' });
    const output = replaceTemp(dataObj[query.id], productHTML);
    res.end(output);
  } else {
    res.writeHead(200, { 'content-type': 'text/html' });
    res.end(errorHTML);
  }
});

// * Starting the Server

server.listen(8000, 'localhost', () => {
  console.log('server listening 👂!');
});
