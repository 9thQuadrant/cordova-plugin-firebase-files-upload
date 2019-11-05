package by.phaneendra.cordova.firebase;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import by.phaneendra.cordova.support.CordovaMethod;
import by.phaneendra.cordova.support.ReflectiveCordovaPlugin;


import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.common.util.concurrent.AbstractScheduledService;

import android.content.Context;


import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnPausedListener;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageException;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.function.Consumer;


import org.apache.cordova.CallbackContext;
import org.apache.cordova.PluginResult;

import org.json.JSONException;
import org.json.JSONObject;

import static java.util.concurrent.TimeUnit.MILLISECONDS;


public class FirebaseFileUploadPlugin extends ReflectiveCordovaPlugin {


    @CordovaMethod
    public void uploadFile(String imagePath, String imageRefForUpload, final CallbackContext callbackContext) {


        ProgressDialog progressDialog = new ProgressDialog(cordova.getActivity());
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Preparing to Upload...");
        progressDialog.show();
        Context context = cordova.getActivity().getApplicationContext();

        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
        StorageReference storageReferenceProfilePic = firebaseStorage.getReference();
        StorageReference imageRef = storageReferenceProfilePic.child(imageRefForUpload);


        try {


        		imageRef.putFile(imagePath)
                .addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                        imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {

                                progressDialog.dismiss();
                                String imageDownloadUrl = uri.toString();
                                JSONObject jErr = new JSONObject();
                                try{
                                    jErr.accumulate("url",imageDownloadUrl);
                                }catch(JSONException e){}
                                callbackContext.success(jErr);
                            }
                        });

                    }
                })
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        progressDialog.dismiss();
                        Context context = cordova.getActivity().getApplicationContext();
                        Toast.makeText(context, "Image Upload Failed..."+exception.toString(), Toast.LENGTH_LONG).show();
                        JSONObject jErr = new JSONObject();
                        try{
                            jErr.accumulate("response","error");
                        }catch(JSONException e){}
                        callbackContext.error(jErr);
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                        progressDialog.setCancelable(false);
                        progressDialog.setCanceledOnTouchOutside(false);
                        progressDialog.setMessage("Uploaded " + ((int) progress) + "%...");
                    }
                });


        }catch(java.io.IOException e){
            Log.d("image Error",e.toString());
        }

    }


}