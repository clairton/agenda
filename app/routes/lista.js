import Ember from 'ember';

export default Ember.Route.extend({
    pessoas: [{id: "1", nome: 'Clairton'}, {id:"2", nome: 'Rodrigo'}],
 
  queryParams: {
    nome: {
      refreshModel: true
    }
  },

    model(params){
        let filter = {};
        if(!Ember.isEmpty(params.nome)){
          filter.nome = params.nome;
        }
        return this.get('store').query('pessoa', filter);
    },
    
    actions:{
        nova(){
            this.transitionTo('nova');
        }
    }
});
