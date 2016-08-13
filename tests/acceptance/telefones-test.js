import Ember from 'ember';
import { module, test } from 'qunit';
import startApp from '../helpers/start-app';

var application;
var originalConfirm;
var confirmCalledWith;

module('Acceptance: Telefone', {
  beforeEach: function() {
    application = startApp();
    originalConfirm = window.confirm;
    window.confirm = function() {
      confirmCalledWith = [].slice.call(arguments);
      return true;
    };
  },
  afterEach: function() {
    Ember.run(application, 'destroy');
    window.confirm = originalConfirm;
    confirmCalledWith = null;
  }
});

test('visiting /telefones without data', function(assert) {
  visit('/telefones');

  andThen(function() {
    assert.equal(currentPath(), 'telefones.index');
    assert.equal(find('#blankslate').text().trim(), 'No Telefones found');
  });
});

test('visiting /telefones with data', function(assert) {
  server.create('telefone');
  visit('/telefones');

  andThen(function() {
    assert.equal(currentPath(), 'telefones.index');
    assert.equal(find('#blankslate').length, 0);
    assert.equal(find('table tbody tr').length, 1);
  });
});

test('create a new telefone', function(assert) {
  visit('/telefones');
  click('a:contains(New Telefone)');

  andThen(function() {
    assert.equal(currentPath(), 'telefones.new');

    fillIn('label:contains(Prefixo) input', 'MyString');
    fillIn('label:contains(Numero) input', 'MyString');

    click('input:submit');
  });

  andThen(function() {
    assert.equal(find('#blankslate').length, 0);
    assert.equal(find('table tbody tr').length, 1);
  });
});

test('update an existing telefone', function(assert) {
  server.create('telefone');
  visit('/telefones');
  click('a:contains(Edit)');

  andThen(function() {
    assert.equal(currentPath(), 'telefones.edit');

    fillIn('label:contains(Prefixo) input', 'MyString');
    fillIn('label:contains(Numero) input', 'MyString');

    click('input:submit');
  });

  andThen(function() {
    assert.equal(find('#blankslate').length, 0);
    assert.equal(find('table tbody tr').length, 1);
  });
});

test('show an existing telefone', function(assert) {
  server.create('telefone');
  visit('/telefones');
  click('a:contains(Show)');

  andThen(function() {
    assert.equal(currentPath(), 'telefones.show');

    assert.equal(find('p strong:contains(Prefixo:)').next().text(), 'MyString');
    assert.equal(find('p strong:contains(Numero:)').next().text(), 'MyString');
  });
});

test('delete a telefone', function(assert) {
  server.create('telefone');
  visit('/telefones');
  click('a:contains(Remove)');

  andThen(function() {
    assert.equal(currentPath(), 'telefones.index');
    assert.deepEqual(confirmCalledWith, ['Are you sure?']);
    assert.equal(find('#blankslate').length, 1);
  });
});
