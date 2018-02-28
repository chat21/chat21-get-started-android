package it.frontiere21.mychat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.database.FirebaseDatabase;

import org.chat21.android.core.ChatManager;
import org.chat21.android.core.authentication.ChatAuthentication;
import org.chat21.android.core.contacts.listeners.OnContactCreatedCallback;
import org.chat21.android.core.exception.ChatRuntimeException;
import org.chat21.android.core.users.models.IChatUser;
import org.chat21.android.ui.ChatUI;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        ChatManager.startWithEmailAndPassword(this, getString(R.string.google_app_id),
                "john@nashville.it", "123456", new ChatAuthentication.OnChatLoginCallback() {
                    @Override
                    public void onChatLoginSuccess(IChatUser currentUser) {
                        ChatManager.getInstance().createContactFor(currentUser.getId(), currentUser.getEmail(),
                                "john", "Nashville", new OnContactCreatedCallback() {
                                    @Override
                                    public void onContactCreatedSuccess(ChatRuntimeException exception) {
                                        if (exception == null) {
                                            ChatUI.getInstance().openConversationsListActivity();
                                        } else {
                                            // TODO: handle the exception
                                        }
                                    }
                                });
                    }

                    @Override
                    public void onChatLoginError(Exception e) {
                        // TODO: 22/02/18
                    }
                });
    }
}
