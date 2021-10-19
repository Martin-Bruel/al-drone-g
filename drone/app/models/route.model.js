const Joi = require('joi')
const BaseModel = require('../utils/base-model.js')

module.exports = new BaseModel('Route', {
  direction : Joi.number().required(),
  step:Joi.number().required(),
  itinary: Joi.array().required()
})
