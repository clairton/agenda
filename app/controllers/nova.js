import Ember from 'ember';

export default Ember.Controller.extend({

  store: Ember.inject.service(),

  criarTelefoneAoIniciar: Ember.on('init', function(){
    this.send('criarTelefone');
  }),

  actions:{

    salvaAoPressionarEnter(){
      this.send('salvar', this.get('model'));
    },
    
    salvar(pessoa){
      let self = this;
      pessoa.save().then(() => {
        self.transitionToRoute('lista');
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
      let self = this;
      telefone.save().then(() => {
        self.get('model.telefones').pushObject(telefone);
        self.send('criarTelefone');
      });
    }

  }
});
