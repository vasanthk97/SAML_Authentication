import Route from '@ember/routing/route';
import Cookies from 'ember-cli-js-cookie';
import Ember from 'ember';
const {Logger} = Ember;

export default Route.extend({
  beforeModel() {
    if (!Cookies.get('jwt')) {
      this.transitionTo('login');
    }
    else {
      this.transitionTo('protected');
    }
  },

  actions: {
    onLogout() {
      Cookies.remove('jwt');
      window.location.replace("http://local.ssocircle.com:8082/landing");


          }
  }
});
