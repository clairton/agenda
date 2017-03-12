export function initialize(application ) {
  let idioma = navigator.language;
  application.register('config:idioma', idioma, { instantiate: false });
  application.inject('service:meses', 'idioma', 'config:idioma');
}

export default {
  name: 'idioma',
  initialize
};
