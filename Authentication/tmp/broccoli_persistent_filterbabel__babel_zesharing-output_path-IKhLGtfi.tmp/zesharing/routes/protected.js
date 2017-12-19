define('zesharing/routes/protected', ['exports', 'ember-cli-js-cookie'], function (exports, _emberCliJsCookie) {
  'use strict';

  Object.defineProperty(exports, "__esModule", {
    value: true
  });
  var Route = Ember.Route;
  var Logger = Ember.Logger;
  exports.default = Route.extend({
    beforeModel: function beforeModel() {
      if (!_emberCliJsCookie.default.get('Auth')) {
        this.transitionTo('login');
      } else {
        this.transitionTo('protected');
      }
    },


    actions: {
      onLogout: function onLogout() {
        /*Cookies.remove('Auth', { path: '/protected' });
        /!*this.transitionTo('http://localhost:8082/saml/logout')*!/
        this.replaceWith('/');*/
        Ember.$.ajax({
          url: "/saml/logout",
          type: "POST",
          data: "logout"
        }).then(function (resp) {
          // handle your server response here
          Logger.log(resp);
        }).catch(function (error) {
          // handle errors here
          Logger.log(error);
        });
        _emberCliJsCookie.default.remove('Auth', { path: '/protected' });
        this.replaceWith('/');
      }
    }
  });
});