const express = require('express')

const app = express()

// * Controllers
// * Routers (To Handle routes)

app.get('/', (_req, res) => {
  res.status(200).sendFile(`${__dirname}/public/homepage.html`)
})

const tourRouter = require(`${__dirname}/Routes/tourRoutes`)

app.use('/api/v1/tours', tourRouter)

app.get('*', (_req, res) => {
  res.status(200).sendFile(`${__dirname}/public/error404.html`)
})

module.exports = app
