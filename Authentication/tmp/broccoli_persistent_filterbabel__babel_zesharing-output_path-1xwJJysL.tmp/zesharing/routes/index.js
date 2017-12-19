define('zesharing/routes/index', ['exports'], function (exports) {
  'use strict';

  Object.defineProperty(exports, "__esModule", {
    value: true
  });
  var Route = Ember.Route;
  exports.default = Route.extend({
    actions: {
      onLogin: function onLogin() {
        this.transitionTo('login');
      }
    }

  });
});