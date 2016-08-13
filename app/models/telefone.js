import DS from 'ember-data';

export default DS.Model.extend({
  prefixo: DS.attr('string'),
  numero: DS.attr('string')
});
