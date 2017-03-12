import Ember from 'ember';

export default Ember.Helper.extend({

  meses: Ember.inject.service('meses'),

  compute(params/*, hash*/){
    let [nascidoEm] = params;
    let dia = nascidoEm.getDate();
    let mes = this.get('meses').getPorExtenso(nascidoEm.getMonth());
    let ano = nascidoEm.getFullYear();
    return `${dia} de ${mes} de ${ano}`;
  }

});