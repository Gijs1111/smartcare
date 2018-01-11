package com.example.gijsdewert.smartcare;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by gijsdewert on 09-01-18.
 */

public class ChatActivity extends AppCompatActivity{

    private EditText editMessage;
    private DatabaseReference mDatabase;
    private RecyclerView mMessageList;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseUser mCurrentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        editMessage = (EditText) findViewById(R.id.editMessageE);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Messages");

        mMessageList = (RecyclerView)findViewById(R.id.messageRec);

        mMessageList.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        mMessageList.setLayoutManager(linearLayoutManager);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() == null) {
                    startActivity(new Intent(ChatActivity.this, MainActivity.class));
                }
            }
        };
    }

    public void sendButtonClicked(View view) {

        mCurrentUser = mAuth.getCurrentUser();

        final String messageValue = editMessage.getText().toString().trim();
        if (!TextUtils.isEmpty(messageValue)) {
            final DatabaseReference newPost = mDatabase.push();
            newPost.child("content").setValue(messageValue);
            newPost.child("email").setValue(mAuth.getCurrentUser().getEmail());
            newPost.child("currentTime").setValue(Calendar.getInstance().getTime().toString());
        }

        mMessageList.smoothScrollToPosition(mMessageList.getAdapter().getItemCount()-1);
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter <Message, MessageViewHolder> fbra = new FirebaseRecyclerAdapter<Message, MessageViewHolder>(
                Message.class,
                R.layout.singlemessagelayout,
                MessageViewHolder.class,
                mDatabase
        ) {
            @Override
            protected void populateViewHolder(MessageViewHolder viewHolder, Message model, int position) {
                viewHolder.setContent(model.getContent());
                viewHolder.setEmail(model.getEmail());
                viewHolder.setCurrentTime(model.getCurrentTime());
            }
        };
        mMessageList.setAdapter(fbra);
    }

    public static class MessageViewHolder extends RecyclerView.ViewHolder {

        View mview;

        public MessageViewHolder(View itemView) {
            super(itemView);
            mview = itemView;
        }

        public void setContent(String content) {
            TextView message_content = (TextView) mview.findViewById(R.id.messageText);
            message_content.setText(content);
        }

        public void setEmail(String email) {
            TextView email_content = (TextView) mview.findViewById(R.id.usernameText);
            email_content.setText(email);
        }

        public void setCurrentTime(String currentTime) {
            TextView time_content = (TextView) mview.findViewById(R.id.timeText);
            time_content.setText(currentTime);
        }
    }

}
