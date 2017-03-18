import { test } from 'qunit';
import moduleForAcceptance from 'agenda/tests/helpers/module-for-acceptance';

moduleForAcceptance('Acceptance | nova');

test('Criar Pessoa', function(assert) {
  visit('/nova');

  andThen(function() {
    assert.equal(currentURL(), '/nova');

	fillIn('#nome', 'Clairton');
	fillIn('#sobrenome', 'Heinzen');
	fillIn('#nascidoEm', '07/04/1985');

	click('#salvar');

	andThen(() => {
    	assert.equal(currentURL(), '/lista');
	});

  });
});
