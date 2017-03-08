import Ember from 'ember';

export default Ember.Route.extend({

	model(){
		return Ember.Object.create({nome: null});
	},

	actions:{
		
		salvar(pessoa){
			//veremos como salvar a pessoa mais tarde
			this.transitionToRoute('pessoas');
		}			
		
	}
});
