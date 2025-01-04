# cordova-plugin-firebase-files-upload

[![GitHub repo](https://img.shields.io/badge/GitHub-Repository-blue)](https://github.com/<your-username>/<your-repo-name>)
[![NPM version](https://img.shields.io/npm/v/cordova-plugin-firebase-files-upload.svg)](https://www.npmjs.com/package/cordova-plugin-firebase-files-upload)
[![NPM downloads](https://img.shields.io/npm/dm/cordova-plugin-firebase-files-upload.svg)](https://www.npmjs.com/package/cordova-plugin-firebase-files-upload)

**Cordova plugin to upload files to Firebase Storage with progress feedback.** This plugin supports uploading images to Firebase directly from your mobile app, with a native progress bar showing the upload progress.

## Installation

To install the plugin, run the following command in your project:

```bash
cordova plugin add cordova-plugin-firebase-files-upload --save
```

## Features
- Upload images to Firebase Storage.
- Displays a native progress bar with upload percentage.
- Simple API with Promise-based method for handling uploads.

> **Note:** This plugin does **not** handle user authentication. Ensure that users are authenticated before uploading files.

## Supported Platforms

- **Android**: Fully supported.

## Methods

This plugin provides the following method to upload files to Firebase:

### `uploadFile(filePath, fileRef, _callback_)`

Uploads a file to Firebase Storage and provides a progress bar during the upload process.

#### Parameters:
- **filePath** (string): Local file path of the image to upload.
- **fileRef** (string): Firebase Storage reference where the file will be stored.
- **_callback_** (optional): A callback function that can be used for custom handling after upload (e.g., handling the file URL).

#### Example Usage:

```js
cordova.plugins.firebase.upload.uploadFile(path, ref).then(function(url) {
    // URL of the uploaded file in Firebase Storage
    console.log('File uploaded to Firebase Storage:', url);
}).catch(function(error) {
    // Handle error if upload fails
    console.error('File upload failed:', error);
});
```

## Keywords
- **cordova**
- **firebase**
- **upload**
- **image**
- **files**
- **firebase-storage**
- **cordova-plugin**
- **native-progress**
- **mobile-app**
