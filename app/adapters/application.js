import DS from 'ember-data';

export default DS.RESTAdapter.extend({
  host: 'http://agenda.clairton.eti.br',
  namespace: null
});
