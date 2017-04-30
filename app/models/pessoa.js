//app/models/pessoa.js

import DS from 'ember-data';

export default DS.Model.extend({
  nome: DS.attr('string'),
  sobrenome: DS.attr('string'),
  nascidoEm: DS.attr('moment', {format: 'YYYY-MM-DD'}),
  telefones: DS.hasMany('telefone', {async: true})
});
