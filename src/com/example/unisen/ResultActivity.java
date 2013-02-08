package com.example.unisen;

import twitter4j.TwitterException;
import twitter4j.auth.OAuthAuthorization;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationContext;
import twitter4j.conf.Configuration;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ResultActivity extends Activity {

	public static RequestToken _req = null;
	public static OAuthAuthorization _oauth = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		
		Button homebutton = (Button)findViewById(R.id.homebutton_id);
        homebutton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                        Intent intent = new Intent(ResultActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finish();
                }           
        });         
        Button twbutton = (Button)findViewById(R.id.tweetbutton_id);
        twbutton.setOnClickListener(new OnClickListener() {
        	
        	@Override
        	public void onClick(View v) {
        		executeOauth();
        		
        	}
        });
	}
	
	private void executeOauth() {
		//Confファイル読み込み
		Configuration conf = ConfigurationContext.getInstance();
		
		//Oauath認証オブジェクト生成
		_oauth = new OAuthAuthorization(conf);
		//Oauth認証オブジェクトにconsumerKeyとocnsumeSecretを設定
		_oauth.setOAuthConsumer("a","b");
		//アプリ認証オブジェクト作成
		try {
			_req = _oauth.getOAuthRequestToken("Callback://CallBackActivity");
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		String _uri;
		_uri = _req.getAuthorizationURL();
		startActivityForResult(new Intent(Intent.ACTION_VIEW, Uri.parse(_uri)), 0);
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_result, menu);
		return true;
	}

}
