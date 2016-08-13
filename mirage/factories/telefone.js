import { Factory } from 'ember-cli-mirage';

export default Factory.extend(
  {
    prefixo(){
      return Math.floor((Math.random() * 99) + 3);
    }, 
    numero(){
      return `${Math.floor((Math.random() * 9999) + 1)}-${Math.floor((Math.random() * 99999) + 1)}`;
    }
  }
);
