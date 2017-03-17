import Ember from 'ember';
import config from './config/environment';

const Router = Ember.Router.extend({
  location: config.locationType,
  rootURL: config.rootURL
});

Router.map(function() {
  this.route('lista');
  this.route('pessoa', {path: ':id'});

  this.route('nova');
  this.route('editar', {path: 'editar/:id'});
});

export default Router;
