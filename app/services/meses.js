import Ember from 'ember';

export default Ember.Service.extend({
  meses: [
    'Janeiro', 
    'Fevereiro', 
    'Março', 
    'Abril',
    'Maio', 
    'Junho', 
    'Julho', 
    'Agosto',
    'Setembro', 
    'Outubro', 
    'Novembro', 
    'Dezembro'
  ],

  getPorExtenso(ordinal){
    return this.get('meses')[ordinal];
  }
});
