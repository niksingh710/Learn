const dotenv = require('dotenv')
dotenv.config(`${__dirname}/config.env`)
const app = require('./app')

const port = process.env.PORT || 3000
const host = process.env.HOST || 'localhost'
app.listen(port, host, () => {
  console.log(`Hey Here I am the server 👂 on port ${port}`)
})
