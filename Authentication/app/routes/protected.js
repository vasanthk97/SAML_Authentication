import Route from '@ember/routing/route';
import Cookies from 'ember-cli-js-cookie';
import Ember from 'ember';
const {Logger} = Ember;

export default Route.extend({
  beforeModel() {
    if (!Cookies.get('Auth')) {
      this.transitionTo('login');
    }
    else {
      this.transitionTo('protected');
    }
  },

  actions: {
    onLogout() {
      /*Cookies.remove('Auth', { path: '/protected' });
      /!*this.transitionTo('http://localhost:8082/saml/logout')*!/
      this.replaceWith('/');*/
      Ember.$.ajax({
        url: "/saml/logout",
        type: "POST",
        data: "logout"
      }).then(function(resp){
// handle your server response here
        Logger.log(resp);
      }).catch(function(error){
// handle errors here
        Logger.log(error);
      });
      Cookies.remove('Auth', { path: '/protected' });
      this.replaceWith('/');
    }
  }
});
