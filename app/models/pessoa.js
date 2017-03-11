import DS from 'ember-data';

export default DS.Model.extend({
  nome: DS.attr('string'),
  sobrenome: DS.attr('string'),
  nascidoEm: DS.attr('date'),
  telefones: DS.hasMany('telefone', {async: true})
});
