define('zesharing/components/logout-button', ['exports'], function (exports) {
  'use strict';

  Object.defineProperty(exports, "__esModule", {
    value: true
  });
  var Component = Ember.Component;
  exports.default = Component.extend({
    actions: {
      onLogout: function onLogout() {
        Cookies.remove('Auth', { path: '/protected' });
        this.replaceWith('/');
      }
    }
  });
});