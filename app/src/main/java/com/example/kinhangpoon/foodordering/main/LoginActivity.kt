package com.example.kinhangpoon.foodordering.main

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.example.kinhangpoon.foodordering.R
import com.facebook.*
import com.facebook.appevents.AppEventsLogger

import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class LoginActivity : AppCompatActivity() {
    internal var sharedPreferences: SharedPreferences? = null
    internal var googleSIgnInButton: SignInButton?=null
    internal var facebookSignInButton: LoginButton?=null
    internal var signOutButton: Button? = null
    var mCallbackManager: CallbackManager? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        sharedPreferences = getSharedPreferences("myinfo", Context.MODE_PRIVATE)
        var loggedIn:Boolean = AccessToken.getCurrentAccessToken() == null;
        if(loggedIn) {
            Log.d("login", "not log in")
        }
        else{
            Log.d("login", " log in")
        }
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        googleSIgnInButton = findViewById(R.id.googleSignIn)
        googleSIgnInButton?.setOnClickListener { signIn() }
        mAuth = FirebaseAuth.getInstance()
        mAuthListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            if (firebaseAuth.currentUser != null) {
                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            }
        }
        mAuth?.addAuthStateListener(mAuthListener!!)
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        //facebook
        mCallbackManager = CallbackManager.Factory.create()
        facebookSignInButton = findViewById(R.id.facebookSignIn)
        facebookSignInButton?.setReadPermissions("email","public_profile")
        facebookSignInButton?.registerCallback(mCallbackManager, object:FacebookCallback<LoginResult>{
            override fun onSuccess(result: LoginResult?) {
                Log.d("Success","facebook:onSuccess:" + result)
                handleFacebookAccessToken(result?.accessToken);
            }

            override fun onCancel() {

            }

            override fun onError(error: FacebookException?) {
                Log.d("Error",error.toString())
            }
        })

    }

    private fun handleFacebookAccessToken(token: AccessToken?) {
        Log.d("FACEBOOK", "handleFacebookAccessToken:" + token)

        val credential = FacebookAuthProvider.getCredential(token!!.token)
        mAuth!!.signInWithCredential(credential)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("facebook", "signInWithCredential:success")
                        val user = mAuth!!.currentUser
                        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("facebook", "signInWithCredential:failure", task.getException())
                        Toast.makeText(this@LoginActivity, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                    }
                }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult<ApiException>(ApiException::class.java!!)
                firebaseAuthWithGoogle(account)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w("Fail", "Google sign in failed", e)
                // ...
            }

        }
        mCallbackManager!!.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)

    }

    private fun firebaseAuthWithGoogle(account: GoogleSignInAccount?) {
        Log.d("Auth", "firebaseAuthWithGoogle:" + account?.id!!)
        Log.d("Auth", "firebaseAuthWithGoogle:" + account?.email!!)
        Log.d("Auth", "firebaseAuthWithGoogle:" + account?.familyName!!)
        Log.d("Auth", "firebaseAuthWithGoogle:" + account?.givenName!!)

        val editor = sharedPreferences?.edit()
        editor?.putString("email",account?.email)
        editor?.putString("familyName",account?.familyName)
        editor?.putString("givenName",account?.givenName)
        editor?.commit()

        Log.d("Auth_after", "firebaseAuthWithGoogle:" + sharedPreferences?.getString("email",""))
        Log.d("Auth_after", "firebaseAuthWithGoogle:" + sharedPreferences?.getString("familyName",""))
        Log.d("Auth_after", "firebaseAuthWithGoogle:" + sharedPreferences?.getString("givenName",""))

        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        mAuth?.signInWithCredential(credential)
                ?.addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("success", "signInWithCredential:success")
                        val user = mAuth?.currentUser

                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("Failure", "signInWithCredential:failure", task.exception)
//                        Snackbar.make(findViewById(R.id.coordinator_admin_login), "Authentication Failed.", Snackbar.LENGTH_SHORT).show()

                    }

                    // ...
                }
    }

    private fun signIn() {
        val signInIntent = mGoogleSignInClient!!.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onStart() {
        super.onStart()
        //        mAuth.addAuthStateListener(mAuthListener);
    }
    companion object {
        private val RC_SIGN_IN = 1
        internal var mAuth: FirebaseAuth?=null
        var mGoogleSignInClient: GoogleSignInClient? = null
        internal var mAuthListener: FirebaseAuth.AuthStateListener?=null
    }
}
