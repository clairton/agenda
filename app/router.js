import Ember from 'ember';
import config from './config/environment';

const Router = Ember.Router.extend({
  location: config.locationType,
  rootURL: config.rootURL
});

Router.map(function() {
  this.route('pessoas', function() {
    this.route('pessoa', {path: ':id'});
    this.route('nova');
  });
});

export default Router;
