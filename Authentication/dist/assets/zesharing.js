"use strict";



define('zesharing/app', ['exports', 'zesharing/resolver', 'ember-load-initializers', 'zesharing/config/environment'], function (exports, _resolver, _emberLoadInitializers, _environment) {
  'use strict';

  Object.defineProperty(exports, "__esModule", {
    value: true
  });
  var Application = Ember.Application;


  var App = Application.extend({
    modulePrefix: _environment.default.modulePrefix,
    podModulePrefix: _environment.default.podModulePrefix,
    Resolver: _resolver.default
  });

  (0, _emberLoadInitializers.default)(App, _environment.default.modulePrefix);

  exports.default = App;
});
define('zesharing/components/footer-bar', ['exports'], function (exports) {
  'use strict';

  Object.defineProperty(exports, "__esModule", {
    value: true
  });
  var Component = Ember.Component;
  exports.default = Component.extend({});
});
define('zesharing/components/login-form', ['exports'], function (exports) {
  'use strict';

  Object.defineProperty(exports, "__esModule", {
    value: true
  });
  var Component = Ember.Component;
  exports.default = Component.extend({});
});
define('zesharing/components/logout-button', ['exports'], function (exports) {
  'use strict';

  Object.defineProperty(exports, "__esModule", {
    value: true
  });
  var Component = Ember.Component;
  exports.default = Component.extend({
    /*actions: {
      onLogout() {
        Cookies.remove('Auth', { path: '/protected' });
        this.replaceWith('/');
      }
    }*/
  });
});
define('zesharing/components/nav-bar', ['exports'], function (exports) {
  'use strict';

  Object.defineProperty(exports, "__esModule", {
    value: true
  });
  var Component = Ember.Component;
  exports.default = Component.extend({});
});
define('zesharing/components/welcome-page', ['exports', 'ember-welcome-page/components/welcome-page'], function (exports, _welcomePage) {
  'use strict';

  Object.defineProperty(exports, "__esModule", {
    value: true
  });
  Object.defineProperty(exports, 'default', {
    enumerable: true,
    get: function () {
      return _welcomePage.default;
    }
  });
});
define('zesharing/helpers/app-version', ['exports', 'zesharing/config/environment', 'ember-cli-app-version/utils/regexp'], function (exports, _environment, _regexp) {
  'use strict';

  Object.defineProperty(exports, "__esModule", {
    value: true
  });
  exports.appVersion = appVersion;
  var version = _environment.default.APP.version;
  function appVersion(_) {
    var hash = arguments.length > 1 && arguments[1] !== undefined ? arguments[1] : {};

    if (hash.hideSha) {
      return version.match(_regexp.versionRegExp)[0];
    }

    if (hash.hideVersion) {
      return version.match(_regexp.shaRegExp)[0];
    }

    return version;
  }

  exports.default = Ember.Helper.helper(appVersion);
});
define('zesharing/helpers/pluralize', ['exports', 'ember-inflector/lib/helpers/pluralize'], function (exports, _pluralize) {
  'use strict';

  Object.defineProperty(exports, "__esModule", {
    value: true
  });
  exports.default = _pluralize.default;
});
define('zesharing/helpers/singularize', ['exports', 'ember-inflector/lib/helpers/singularize'], function (exports, _singularize) {
  'use strict';

  Object.defineProperty(exports, "__esModule", {
    value: true
  });
  exports.default = _singularize.default;
});
define('zesharing/initializers/app-version', ['exports', 'ember-cli-app-version/initializer-factory', 'zesharing/config/environment'], function (exports, _initializerFactory, _environment) {
  'use strict';

  Object.defineProperty(exports, "__esModule", {
    value: true
  });
  var _config$APP = _environment.default.APP,
      name = _config$APP.name,
      version = _config$APP.version;
  exports.default = {
    name: 'App Version',
    initialize: (0, _initializerFactory.default)(name, version)
  };
});
define('zesharing/initializers/container-debug-adapter', ['exports', 'ember-resolver/resolvers/classic/container-debug-adapter'], function (exports, _containerDebugAdapter) {
  'use strict';

  Object.defineProperty(exports, "__esModule", {
    value: true
  });
  exports.default = {
    name: 'container-debug-adapter',

    initialize: function initialize() {
      var app = arguments[1] || arguments[0];

      app.register('container-debug-adapter:main', _containerDebugAdapter.default);
      app.inject('container-debug-adapter:main', 'namespace', 'application:main');
    }
  };
});
define('zesharing/initializers/data-adapter', ['exports'], function (exports) {
  'use strict';

  Object.defineProperty(exports, "__esModule", {
    value: true
  });
  exports.default = {
    name: 'data-adapter',
    before: 'store',
    initialize: function initialize() {}
  };
});
define('zesharing/initializers/ember-data', ['exports', 'ember-data/setup-container', 'ember-data'], function (exports, _setupContainer) {
  'use strict';

  Object.defineProperty(exports, "__esModule", {
    value: true
  });
  exports.default = {
    name: 'ember-data',
    initialize: _setupContainer.default
  };
});
define('zesharing/initializers/export-application-global', ['exports', 'zesharing/config/environment'], function (exports, _environment) {
  'use strict';

  Object.defineProperty(exports, "__esModule", {
    value: true
  });
  exports.initialize = initialize;
  function initialize() {
    var application = arguments[1] || arguments[0];
    if (_environment.default.exportApplicationGlobal !== false) {
      var theGlobal;
      if (typeof window !== 'undefined') {
        theGlobal = window;
      } else if (typeof global !== 'undefined') {
        theGlobal = global;
      } else if (typeof self !== 'undefined') {
        theGlobal = self;
      } else {
        // no reasonable global, just bail
        return;
      }

      var value = _environment.default.exportApplicationGlobal;
      var globalName;

      if (typeof value === 'string') {
        globalName = value;
      } else {
        globalName = Ember.String.classify(_environment.default.modulePrefix);
      }

      if (!theGlobal[globalName]) {
        theGlobal[globalName] = application;

        application.reopen({
          willDestroy: function willDestroy() {
            this._super.apply(this, arguments);
            delete theGlobal[globalName];
          }
        });
      }
    }
  }

  exports.default = {
    name: 'export-application-global',

    initialize: initialize
  };
});
define('zesharing/initializers/injectStore', ['exports'], function (exports) {
  'use strict';

  Object.defineProperty(exports, "__esModule", {
    value: true
  });
  exports.default = {
    name: 'injectStore',
    before: 'store',
    initialize: function initialize() {}
  };
});
define('zesharing/initializers/store', ['exports'], function (exports) {
  'use strict';

  Object.defineProperty(exports, "__esModule", {
    value: true
  });
  exports.default = {
    name: 'store',
    after: 'ember-data',
    initialize: function initialize() {}
  };
});
define('zesharing/initializers/transforms', ['exports'], function (exports) {
  'use strict';

  Object.defineProperty(exports, "__esModule", {
    value: true
  });
  exports.default = {
    name: 'transforms',
    before: 'store',
    initialize: function initialize() {}
  };
});
define("zesharing/instance-initializers/ember-data", ["exports", "ember-data/instance-initializers/initialize-store-service"], function (exports, _initializeStoreService) {
  "use strict";

  Object.defineProperty(exports, "__esModule", {
    value: true
  });
  exports.default = {
    name: "ember-data",
    initialize: _initializeStoreService.default
  };
});
define('zesharing/resolver', ['exports', 'ember-resolver'], function (exports, _emberResolver) {
  'use strict';

  Object.defineProperty(exports, "__esModule", {
    value: true
  });
  exports.default = _emberResolver.default;
});
define('zesharing/router', ['exports', 'zesharing/config/environment'], function (exports, _environment) {
  'use strict';

  Object.defineProperty(exports, "__esModule", {
    value: true
  });
  var EmberRouter = Ember.Router;


  var Router = EmberRouter.extend({
    location: _environment.default.locationType,
    rootURL: _environment.default.rootURL
  });

  Router.map(function () {
    this.route('protected');
    this.route('login');
  });

  exports.default = Router;
});
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
    },

    beforeModel: function beforeModel() {
      if (!Cookies.get('Auth')) {
        this.replaceWith('/');
      } else {
        this.replaceWith('protected');
      }
    }
  });
});
define('zesharing/routes/login', ['exports'], function (exports) {
  'use strict';

  Object.defineProperty(exports, "__esModule", {
    value: true
  });
  var Route = Ember.Route;
  exports.default = Route.extend({});
});
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
define('zesharing/services/ajax', ['exports', 'ember-ajax/services/ajax'], function (exports, _ajax) {
  'use strict';

  Object.defineProperty(exports, "__esModule", {
    value: true
  });
  Object.defineProperty(exports, 'default', {
    enumerable: true,
    get: function () {
      return _ajax.default;
    }
  });
});
define("zesharing/templates/application", ["exports"], function (exports) {
  "use strict";

  Object.defineProperty(exports, "__esModule", {
    value: true
  });
  exports.default = Ember.HTMLBars.template({ "id": "qZvlu3u9", "block": "{\"symbols\":[],\"statements\":[[1,[18,\"nav-bar\"],false],[0,\"\\n\"],[1,[18,\"footer-bar\"],false],[0,\"\\n\"],[1,[18,\"outlet\"],false],[0,\"\\n\\n\\n\"]],\"hasEval\":false}", "meta": { "moduleName": "zesharing/templates/application.hbs" } });
});
define("zesharing/templates/components/footer-bar", ["exports"], function (exports) {
  "use strict";

  Object.defineProperty(exports, "__esModule", {
    value: true
  });
  exports.default = Ember.HTMLBars.template({ "id": "NMSBPBei", "block": "{\"symbols\":[],\"statements\":[[6,\"div\"],[9,\"id\",\"footer\"],[9,\"class\",\"footerContainer\"],[7],[0,\"\\n  \"],[6,\"footer\"],[7],[0,\"\\n    \"],[6,\"h5\"],[7],[0,\"Copyright © All Rights Reserved To Zemoso Technologies.\"],[8],[0,\"\\n  \"],[8],[0,\"\\n\"],[8]],\"hasEval\":false}", "meta": { "moduleName": "zesharing/templates/components/footer-bar.hbs" } });
});
define("zesharing/templates/components/login-form", ["exports"], function (exports) {
  "use strict";

  Object.defineProperty(exports, "__esModule", {
    value: true
  });
  exports.default = Ember.HTMLBars.template({ "id": "3fztvit7", "block": "{\"symbols\":[],\"statements\":[[6,\"div\"],[9,\"class\",\"loginForm\"],[7],[0,\"\\n  \"],[6,\"h3\"],[7],[0,\"Login\"],[8],[0,\"\\n  \"],[6,\"form\"],[9,\"class\",\"logon-form\"],[9,\"novalidate\",\"\"],[7],[0,\"\\n    \"],[6,\"div\"],[9,\"id\",\"form-based\"],[7],[0,\"\\n      \"],[6,\"div\"],[7],[0,\"\\n      \"],[6,\"input\"],[9,\"class\",\"email-input\"],[9,\"name\",\"email\"],[9,\"type\",\"text\"],[9,\"placeholder\",\"Username\"],[9,\"value\",\"\"],[9,\"autofocus\",\"\"],[9,\"disabled\",\"\"],[7],[8],[0,\"\\n      \"],[8],[0,\"\\n\\n      \"],[6,\"div\"],[7],[0,\"\\n      \"],[6,\"input\"],[9,\"class\",\"password-input\"],[9,\"name\",\"password\"],[9,\"type\",\"password\"],[9,\"placeholder\",\"Password\"],[9,\"disabled\",\"\"],[7],[8],[0,\"\\n      \"],[8],[0,\"\\n\\n      \"],[6,\"button\"],[9,\"class\",\"login-submit-button\"],[9,\"type\",\"submit\"],[7],[0,\"Login\"],[8],[0,\"\\n    \"],[8],[0,\"\\n  \"],[8],[0,\"\\n\\n  \"],[6,\"div\"],[9,\"class\",\"form-divider\"],[7],[0,\"\\n    \"],[6,\"span\"],[9,\"class\",\"signin-divider\"],[7],[0,\"\\n      \"],[6,\"h3\"],[7],[0,\" --------------- or signin with ---------------\"],[8],[0,\"\\n    \"],[8],[0,\"\\n  \"],[8],[0,\"\\n\\n  \"],[6,\"div\"],[9,\"id\",\"idp-based\"],[7],[0,\"\\n      \"],[6,\"a\"],[9,\"href\",\"http://localhost:8082/saml/login?disco=true\"],[7],[0,\"\\n        \"],[6,\"button\"],[9,\"class\",\"idp-button\"],[7],[0,\"SSO Circle\"],[8],[8],[0,\"\\n\\n      \"],[6,\"button\"],[9,\"class\",\"idp-button\"],[9,\"type\",\"submit\"],[9,\"value\",\"GOOGLE\"],[7],[0,\"GOOGLE\"],[8],[0,\"\\n  \"],[8],[0,\"\\n\\n\\n\"],[8]],\"hasEval\":false}", "meta": { "moduleName": "zesharing/templates/components/login-form.hbs" } });
});
define("zesharing/templates/components/logout-button", ["exports"], function (exports) {
  "use strict";

  Object.defineProperty(exports, "__esModule", {
    value: true
  });
  exports.default = Ember.HTMLBars.template({ "id": "8NXC7JN+", "block": "{\"symbols\":[],\"statements\":[],\"hasEval\":false}", "meta": { "moduleName": "zesharing/templates/components/logout-button.hbs" } });
});
define("zesharing/templates/components/nav-bar", ["exports"], function (exports) {
  "use strict";

  Object.defineProperty(exports, "__esModule", {
    value: true
  });
  exports.default = Ember.HTMLBars.template({ "id": "H4d7E6TS", "block": "{\"symbols\":[],\"statements\":[[6,\"div\"],[9,\"id\",\"header\"],[9,\"class\",\"HeaderContainer\"],[7],[0,\"\\n  \"],[6,\"div\"],[9,\"id\",\"homebutton\"],[7],[0,\"\\n    \"],[6,\"a\"],[9,\"href\",\"http://zemosolabs.com/\"],[9,\"target\",\"_blank\"],[7],[0,\"\\n      \"],[6,\"img\"],[9,\"src\",\"/assets/images/zemoso_logo.png\"],[9,\"id\",\"homebutton\"],[7],[8],[0,\"\\n    \"],[8],[0,\"\\n  \"],[8],[0,\"\\n  \"],[6,\"div\"],[9,\"class\",\"headerDivider\"],[7],[0,\"\\n\\n  \"],[8],[0,\"\\n  \"],[6,\"div\"],[9,\"id\",\"header-title\"],[7],[0,\"\\n    \"],[6,\"h1\"],[9,\"id\",\"title\"],[7],[0,\"Welcome to Zemoso emplyoee portal\"],[8],[0,\"\\n  \"],[8],[0,\"\\n\"],[8]],\"hasEval\":false}", "meta": { "moduleName": "zesharing/templates/components/nav-bar.hbs" } });
});
define("zesharing/templates/index", ["exports"], function (exports) {
  "use strict";

  Object.defineProperty(exports, "__esModule", {
    value: true
  });
  exports.default = Ember.HTMLBars.template({ "id": "g7DNTQMw", "block": "{\"symbols\":[],\"statements\":[[6,\"button\"],[9,\"id\",\"login-button\"],[9,\"class\",\"button\"],[3,\"action\",[[19,0,[]],\"onLogin\"]],[7],[0,\"\\n  \"],[6,\"h3\"],[7],[0,\"Login\"],[8],[0,\"\\n\"],[8]],\"hasEval\":false}", "meta": { "moduleName": "zesharing/templates/index.hbs" } });
});
define("zesharing/templates/login", ["exports"], function (exports) {
  "use strict";

  Object.defineProperty(exports, "__esModule", {
    value: true
  });
  exports.default = Ember.HTMLBars.template({ "id": "Z5GFg/v/", "block": "{\"symbols\":[],\"statements\":[[1,[18,\"login-form\"],false],[0,\"\\n\"]],\"hasEval\":false}", "meta": { "moduleName": "zesharing/templates/login.hbs" } });
});
define("zesharing/templates/protected", ["exports"], function (exports) {
  "use strict";

  Object.defineProperty(exports, "__esModule", {
    value: true
  });
  exports.default = Ember.HTMLBars.template({ "id": "rdLnxiDc", "block": "{\"symbols\":[],\"statements\":[[6,\"div\"],[9,\"class\",\"jumbo\"],[7],[0,\"\\n  \"],[6,\"div\"],[9,\"class\",\"right tomster\"],[7],[8],[0,\"\\n  \"],[6,\"h2\"],[7],[0,\"Manikanta Gadde\"],[8],[0,\"\\n  \"],[6,\"p\"],[7],[0,\"Developer\"],[8],[0,\"\\n  \"],[6,\"p\"],[7],[0,\"\\n    Current Project: ZeSharing\\n  \"],[6,\"address\"],[7],[0,\"\\n    Address\"],[6,\"br\"],[7],[8],[0,\"\\n    Manikonda, Hyderabad \"],[6,\"br\"],[7],[8],[0,\"\\n    Telangana, 500089\\n  \"],[8],[0,\"\\n  \"],[6,\"a\"],[9,\"href\",\"tel:8266802238\"],[7],[0,\"+91-8266802238\"],[8],[6,\"br\"],[7],[8],[0,\"\\n  \"],[6,\"a\"],[9,\"href\",\"mailto:manikantagadde123@gmail.com\"],[7],[0,\"manikantagadde123@gmail.com\"],[8],[0,\"\\n  \"],[8],[0,\"\\n\"],[8],[0,\"\\n\\n\"],[2,\"<a href=\\\"http://localhost:8082/saml/SingleLogout\\\">\"],[0,\"\\n\"],[6,\"button\"],[9,\"id\",\"logout-button\"],[9,\"class\",\"button\"],[3,\"action\",[[19,0,[]],\"onLogout\"]],[7],[0,\"\\n  \"],[6,\"h3\"],[7],[0,\"Logout\"],[8],[0,\"\\n\"],[8],[0,\"\\n\"],[2,\"</a>\"],[0,\"\\n\\n\\n\\n\\n\\n\"]],\"hasEval\":false}", "meta": { "moduleName": "zesharing/templates/protected.hbs" } });
});


define('zesharing/config/environment', [], function() {
  var prefix = 'zesharing';
try {
  var metaName = prefix + '/config/environment';
  var rawConfig = document.querySelector('meta[name="' + metaName + '"]').getAttribute('content');
  var config = JSON.parse(unescape(rawConfig));

  var exports = { 'default': config };

  Object.defineProperty(exports, '__esModule', { value: true });

  return exports;
}
catch(err) {
  throw new Error('Could not read config from meta tag with name "' + metaName + '".');
}

});

if (!runningTests) {
  require("zesharing/app")["default"].create({"name":"zesharing","version":"0.0.0+73978f7d"});
}
//# sourceMappingURL=zesharing.map
