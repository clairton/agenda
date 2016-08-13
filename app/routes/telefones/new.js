import Ember from 'ember';
import SaveModelMixin from 'agenda/mixins/telefones/save-model-mixin';

export default Ember.Route.extend(SaveModelMixin, {
  model: function() {
    return this.store.createRecord('telefone');
  }
});
