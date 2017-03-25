//app/adapters/application.js

import DS from 'ember-data';

import Config from 'agenda/config/environment';

export default DS.RESTAdapter.extend({
  host: Config.host,
  namespace: Config.namespace
  coalesceFindRequests: true,

  handleResponse(status, headers, payload) {
    if (this.isInvalid(...arguments)) {
      payload.errors = DS.errorsHashToArray(payload.errors);
    }
    return this._super(...arguments);
  }
});
