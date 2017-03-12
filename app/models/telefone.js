import DS from 'ember-data';

export default DS.Model.extend({
  numero: DS.attr('telefone-numero'),
  prefixo: DS.attr('number'),
  pessoa: DS.belongsTo('pessoa')
});
