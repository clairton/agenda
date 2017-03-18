import { moduleForComponent, test } from 'ember-qunit';
import hbs from 'htmlbars-inline-precompile';

moduleForComponent('mensagem-validacao', 'Integration | Component | mensagem validacao', {
  integration: true
});

test('it renders', function(assert) {

  // Set any properties with this.set('myProperty', 'value');
  // Handle any actions with this.on('myAction', function(val) { ... });

  this.render(hbs`{{mensagem-validacao}}`);

  assert.equal(this.$().text().trim(), '');

  // Template block usage:
  this.render(hbs`
    {{#mensagem-validacao}}
      template block text
    {{/mensagem-validacao}}
  `);

  assert.equal(this.$().text().trim(), 'template block text');
});
