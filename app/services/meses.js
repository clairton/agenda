import Ember from 'ember';

export default Ember.Service.extend({
  idioma: 'pt-BR',
  meses: {
    'pt-BR': [
      'Janeiro', 
      'Fevereiro', 
      'Março', 
      'Maio', 
      'Junho', 
      'Julho', 
      'Setembro', 
      'Outubro', 
      'Novembro', 
      'Dezembro'
    ],
    'en-US': [
      'January', 
      'February', 
      'March', 
      'May', 
      'June', 
      'July', 
      'September', 
      'October', 
      'November', 
      'December'
    ]
  },

  getPorExtenso(ordinal){
    let idioma = this.get('idioma');
    let meses = this.get('meses')[idioma];
    if(!meses){
        meses = this.get('meses')['pt-BR'];
    }
    return meses[ordinal];
  }
});