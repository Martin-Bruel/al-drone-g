const Joi = require('joi')
const BaseModel = require('../utils/base-model.js')

module.exports = new BaseModel('Route', {
  step:Joi.number(),
  itinary: Joi.array().required()
})
