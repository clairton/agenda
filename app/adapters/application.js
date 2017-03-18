import DS from 'ember-data';

export default DS.RESTAdapter.extend({
  host: 'http://agenda.clairton.eti.br',
  namespace: null,
  coalesceFindRequests: true,

  handleResponse(status, headers, payload) {
    if (this.isInvalid(...arguments)) {
      payload.errors = DS.errorsHashToArray(payload.errors);
    }
    return this._super(...arguments);
  }
});
