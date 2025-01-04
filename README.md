# cordova-plugin-firebase-files-upload

[![NPM version](https://img.shields.io/npm/v/cordova-plugin-firebase-files-upload.svg)](https://www.npmjs.com/package/cordova-plugin-firebase-files-upload)
[![NPM downloads](https://img.shields.io/npm/dm/cordova-plugin-firebase-files-upload.svg)](https://www.npmjs.com/package/cordova-plugin-firebase-files-upload)

## Installation

    cordova plugin add cordova-plugin-firebase-files-upload --save


This plugin only supports image upload, doesn't check authentication.

## Supported Platforms

- Android

## Methods
Method call returns a promise which is optionally fulfilled with an appropriate value.

### uploadFile(filePath,fileRef,_callback_)
Uploads file to firebase:
* Shows a native progress bar while uploading with % completed.

```js
cordova.plugins.firebase.upload.uploadFile(path,ref).then(function(path){
    //URL of file in firebase storage
});
```
