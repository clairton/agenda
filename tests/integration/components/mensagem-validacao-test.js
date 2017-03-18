
//tests/integration/components/mensagem-validacao-test.js

import { moduleForComponent, test } from 'ember-qunit';
import hbs from 'htmlbars-inline-precompile';
import DS from 'ember-data';

moduleForComponent('mensagem-validacao', 'Integration | Component | mensagem validacao', {
  integration: true
});

test('Deveria mostrar uma mensagem', function(assert) {
  let erros = DS.Errors.create();
  erros.add('nome', 'Nome Obrigatório');

  this.set('nomeDaPessoaObrigatorio', erros);

  this.render(hbs`{{mensagem-validacao erros=nomeDaPessoaObrigatorio}}`);

  assert.equal(this.$('.erro').length, 1);
  assert.equal(this.$('.erro').text().trim(), 'Nome Obrigatório');
});




  