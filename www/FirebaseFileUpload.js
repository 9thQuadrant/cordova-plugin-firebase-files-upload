var exec = require("cordova/exec");
var PLUGIN_NAME = "FirebaseFileUpload";

module.exports = {
   
    uploadFile: function(imagePath,imageRef,callback) {
        return new Promise(function(resolve, reject) {
            exec(resolve, reject, PLUGIN_NAME, "uploadFile", [imagePath, imageRef]);
        });
    },

};
