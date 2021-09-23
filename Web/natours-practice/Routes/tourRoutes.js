const express = require('express')

const router = express.Router()
const {
  getAllTours,
  getTour,
  checkId,
} = require(`${__dirname}/../controllers/tourControllers`)

router.get('/', getAllTours)
router.get('/:id', checkId, getTour)

module.exports = router
