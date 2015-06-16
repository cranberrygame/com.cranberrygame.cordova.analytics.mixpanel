Cordova MixpanelAnalytics plugin
====================
# Overview #
mixpanel analytics

[android, ios] [crodova cli] [xdk] [cocoon]

Requires mixpanel account http://www.mixpanel.com/

Fix "error: use of undeclared identifier '_serialQueue'" xdk build error issue:
CORDOVA 3.X HYBRID MOBILE APP SETTINGS - Build Settings - set iOS Target Version: 6

This is open source cordova plugin.

You can see Plugins For Cordova in one page: http://cranberrygame.github.io?referrer=github

# Change log #
```c
```
# Install plugin #

## Cordova cli ##
https://cordova.apache.org/docs/en/edge/guide_cli_index.md.html#The%20Command-Line%20Interface - npm install -g cordova@5.0.0
```c
cordova plugin add cordova-plugin-analytics-mixpanelanalytics
```

## Xdk ##
https://software.intel.com/en-us/intel-xdk - Download XDK - XDK PORJECTS - [specific project] - CORDOVA 3.X HYBRID MOBILE APP SETTINGS - PLUGINS - Third Party Plugins - Add a Third Party Plugin - Get Plugin from the Web -
```c
Name: cordova-plugin-analytics-mixpanelanalytics
Plugin ID: com.cranberrygame.cordova.plugin.analytics.mixpanelanalytics
[v] Plugin is located in the Apache Cordova Plugins Registry
```

## Cocoon ##
https://cocoon.io - Create project - [specific project] - Setting - Plugins - Custom - Git Url: https://github.com/cranberrygame/cordova-plugin-analytics-mixpanelanalytics.git - INSTALL - Save

## Phonegap build service (config.xml) ##
https://build.phonegap.com/ - Apps - [specific project] - Update code - Zip file including config.xml
```c
<gap:plugin name="cordova-plugin-analytics-mixpanelanalytics." source="npm" />
```

## Construct2 ##

# Server setting #
```c
//Mixpanel token
Mixpanel login - [your project] - click down left setting icon ("Update project settings") - Token
```

<img src="https://raw.githubusercontent.com/cranberrygame/cordova-plugin-analytics-mixpanelanalytics/master/doc/token.png"><br>
<img src="https://raw.githubusercontent.com/cranberrygame/cordova-plugin-analytics-mixpanelanalytics/master/doc/mixpanel_bookmark.png"><br>

# API #
```javascript
var token = "REPLACE_THIS_WITH_YOUR_TOKEN";
/*
var token;
//android
if (navigator.userAgent.match(/Android/i)) {
	token = "REPLACE_THIS_WITH_YOUR_TOKEN";
}
//ios
else if (navigator.userAgent.match(/iPhone/i) || navigator.userAgent.match(/iPad/i)) {
	token = "REPLACE_THIS_WITH_YOUR_TOKEN";
}
*/

document.addEventListener("deviceready", function(){
    window.mixpanelanalytics.setUp(token);
}, false);

//
window.mixpanelanalytics.trackEvent(eventName);//As for ios, analytics data will be sent to mixpanel server not directly but after app goes into back ground state.

//
window.mixpanelanalytics.addEventProperty(propertyName, propertyValue);
window.mixpanelanalytics.trackEvent(eventName);

//
window.mixpanelanalytics.identifyPeople(distinctId);
window.mixpanelanalytics.addPeopleProperty(propertyName, propertyValue);
window.mixpanelanalytics.changePeopleProperties();
window.mixpanelanalytics.incrementPeopleProperty(propertyName, propertyValue);
window.mixpanelanalytics.deletePeople();
			
```
# Examples #
<a href="https://github.com/cranberrygame/cordova-plugin-analytics-mixpanelanalytics/blob/master/example/basic/index.html">example/basic/index.html</a><br>
<a href="https://github.com/cranberrygame/cordova-plugin-analytics-mixpanelanalytics/blob/master/example/advanced_event_property/index.html">example/advanced_event_property/index.html</a><br>
<a href="https://github.com/cranberrygame/cordova-plugin-analytics-mixpanelanalytics/blob/master/example/advanced_people/index.html">example/advanced_people/index.html</a><br>

# Test #

You can also run following test apk.
https://dl.dropboxusercontent.com/u/186681453/pluginsforcordova/mixpanelanalytics/apk.html

# Useful links #

Plugins For Cordova<br>
http://cranberrygame.github.io?referrer=github

# Credits #

https://github.com/samzilverberg/cordova-mixpanel-plugin
