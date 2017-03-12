

import DS from 'ember-data';

export default DS.Transform.extend({

  serialize(deserialized) {
  	return deserialized.replace(/\D/g, '');
  },
  
  deserialize(serialized) {
  	return serialized.toString().replace(/(\d{4})(\d{4})/, '$1 $2');
  }

});

