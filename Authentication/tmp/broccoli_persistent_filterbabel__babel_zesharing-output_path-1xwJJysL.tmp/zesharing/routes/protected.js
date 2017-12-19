define('zesharing/routes/protected', ['exports', 'ember-cli-js-cookie'], function (exports, _emberCliJsCookie) {
  'use strict';

  Object.defineProperty(exports, "__esModule", {
    value: true
  });
  var Route = Ember.Route;
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
        _emberCliJsCookie.default.remove('Auth', { path: '/protected' });
        this.replaceWith('/');
      }
    }
  });
});