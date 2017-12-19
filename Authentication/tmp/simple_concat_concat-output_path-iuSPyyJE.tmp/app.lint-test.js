QUnit.module('ESLint | app');

QUnit.test('app.js', function(assert) {
  assert.expect(1);
  assert.ok(true, 'app.js should pass ESLint\n\n');
});

QUnit.test('components/footer-bar.js', function(assert) {
  assert.expect(1);
  assert.ok(true, 'components/footer-bar.js should pass ESLint\n\n');
});

QUnit.test('components/login-form.js', function(assert) {
  assert.expect(1);
  assert.ok(true, 'components/login-form.js should pass ESLint\n\n');
});

QUnit.test('components/logout-button.js', function(assert) {
  assert.expect(1);
  assert.ok(false, 'components/logout-button.js should pass ESLint\n\n6:7 - \'Cookies\' is not defined. (no-undef)');
});

QUnit.test('components/nav-bar.js', function(assert) {
  assert.expect(1);
  assert.ok(true, 'components/nav-bar.js should pass ESLint\n\n');
});

QUnit.test('resolver.js', function(assert) {
  assert.expect(1);
  assert.ok(true, 'resolver.js should pass ESLint\n\n');
});

QUnit.test('router.js', function(assert) {
  assert.expect(1);
  assert.ok(true, 'router.js should pass ESLint\n\n');
});

QUnit.test('routes/index.js', function(assert) {
  assert.expect(1);
  assert.ok(true, 'routes/index.js should pass ESLint\n\n');
});

QUnit.test('routes/login.js', function(assert) {
  assert.expect(1);
  assert.ok(true, 'routes/login.js should pass ESLint\n\n');
});

QUnit.test('routes/protected.js', function(assert) {
  assert.expect(1);
  assert.ok(true, 'routes/protected.js should pass ESLint\n\n');
});

