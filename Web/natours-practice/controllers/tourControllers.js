const fs = require('fs')

const data = JSON.parse(
  fs.readFileSync(`${__dirname}/../dev-data/data/tours-simple.json`)
)

exports.checkId = (req, res, next) => {
  if (req.params.id > data.length)
    return res.status(404).json({ status: 'Not Found', message: 'Not Found' })
  next()
}

exports.getAllTours = (_req, res) => {
  return res.status(200).json({ status: 'Success', data })
}

exports.getTour = (req, res) => {
  let newData = data.find((el) => req.params.id * 1 === el.id)
  return res.status(200).json({ status: 'Success', newData })
}
