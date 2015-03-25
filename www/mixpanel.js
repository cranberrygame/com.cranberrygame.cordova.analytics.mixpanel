'use strict';

var exec = require('cordova/exec'),
  mixpanel = {};


//mixpanel.alias = mixpanel.createAlias = function(alias, originalId, onSuccess, onFail) {
//  exec(onSuccess, onFail, 'Mixpanel', 'alias', [alias, originalId]);
//};

mixpanel.flush = function(onSuccess, onFail) {
  exec(onSuccess, onFail, 'Mixpanel', 'flush', []);
};

mixpanel.identify = function(id, onSuccess, onFail) {
  exec(onSuccess, onFail, 'Mixpanel', 'identify', [id]);
};

mixpanel.init = function(token, onSuccess, onFail) {
  exec(onSuccess, onFail, 'Mixpanel', 'init', [token]);
};

//cranberrygame start
mixpanel.set = function(peopleProperties, onSuccess, onFail) {
  exec(onSuccess, onFail, 'Mixpanel', 'set', [peopleProperties]);
};
mixpanel.increment = function(propertyName, propertyValue, onSuccess, onFail) {
  exec(onSuccess, onFail, 'Mixpanel', 'increment', [propertyName, propertyValue]);
};
mixpanel.deleteUser = function(onSuccess, onFail) {
  exec(onSuccess, onFail, 'Mixpanel', 'deleteUser', []);
};
//cranberrygame end

mixpanel.reset = function(onSuccess, onFail) {
  exec(onSuccess, onFail, 'Mixpanel', 'reset', []);
};

mixpanel.track = function(eventName, eventProperties, onSuccess, onFail) {
  exec(onSuccess, onFail, 'Mixpanel', 'track', [eventName, eventProperties]);
};


module.exports = mixpanel;