import Ember from 'ember';

export default Ember.Component.extend({

  store: Ember.inject.service(),

  criarTelefoneAoIniciar: Ember.on('init', function(){
    this.send('criarTelefone');
  }),

  actions:{

    salvarAoPressionarEnter(){
      this.send('salvar', this.get('pessoa'));
    },
 
    salvar(pessoa){
      let self = this;
      pessoa.save().then(() => {
        self.sendAction('aoSalvarComSucesso');
      });
    },

    removerTelefone(telefone){
      if(confirm('Você está removendo um telefone, deseja continuar?')){
        telefone.destroyRecord();
      }
    },  

    criarTelefone(){
        let telefone = this.get('store').createRecord('telefone', {});
        this.set('telefone', telefone);
    },   

    adicionarTelefone(telefone){
      this.get('pessoa.telefones').pushObject(telefone);
      this.send('criarTelefone');
    }

  }

});
