import ApplicationSerializer from './application';

export default ApplicationSerializer.extend({
  attrs: {
    telefones: {
      serialize: 'records',
      deserialize: 'ids'
    }
  }
});
