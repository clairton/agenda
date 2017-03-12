import Ember from 'ember';

export function formataData(params/*, hash*/) {
  let [nascidoEm] = params;
  let dia = nascidoEm.getDate();
  let mes = nascidoEm.getMonth() +1;
  let ano = nascidoEm.getFullYear();
  return `${dia}/${mes}/${ano}`;
}

export default Ember.Helper.helper(formataData);
