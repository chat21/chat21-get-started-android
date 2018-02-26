package it.frontiere21.mychat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.database.FirebaseDatabase;

import org.chat21.android.core.ChatManager;
import org.chat21.android.core.authentication.ChatAuthentication;
import org.chat21.android.core.users.models.IChatUser;
import org.chat21.android.ui.ChatUI;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        ChatManager.startAnonymously(this, getString(R.string.chat_firebase_appId),
                        new ChatAuthentication.OnChatLoginCallback() {
                            @Override
                            public void onChatLoginSuccess(IChatUser currentUser) {
                                ChatUI.getInstance().openConversationsListActivity();
                            }

                            @Override
                            public void onChatLoginError(Exception e) {
                                // TODO: 22/02/18
                            }
                        });
    }
}
