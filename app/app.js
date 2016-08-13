import Ember from 'ember';
import Resolver from './resolver';
import Inflector from 'ember-inflector';
import loadInitializers from 'ember-load-initializers';
import config from './config/environment';

let App;

Ember.MODEL_FACTORY_INJECTIONS = true;

Inflector.inflector.singular(/([ti])a$/i, '$1a');

App = Ember.Application.extend({
  modulePrefix: config.modulePrefix,
  podModulePrefix: config.podModulePrefix,
  Resolver
});

loadInitializers(App, config.modulePrefix);

export default App;
