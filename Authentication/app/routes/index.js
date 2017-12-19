import Route from '@ember/routing/route';

export default Route.extend({
  actions: {
    onLogin() {
      this.transitionTo('login');
    }
  },

  beforeModel() {
    if (!Cookies.get('Auth')) {
      this.replaceWith('/');
    }
    else {
      this.replaceWith('protected');
    }
  }
});
