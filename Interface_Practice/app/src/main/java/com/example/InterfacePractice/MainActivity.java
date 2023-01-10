package com.example.InterfacePractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button forwardButton = findViewById(R.id.Forward);
        Button backwardButton = findViewById(R.id.Backward);
        Button nextPageButton = findViewById(R.id.NextPage) ;
        TextView stackDepthInfo = findViewById(R.id.StackDepth);

        final int[] stackDepth = {this.getIntent().getIntExtra("stackDepth", 0)};
        stackDepthInfo.setText("Stack Depth: " + stackDepth[0]);
        backwardButton.setOnClickListener(view -> {
            if (stackDepth[0] != 0) {
                MainActivity.this.finish();

            }
        });
        forwardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainActivity.this, MainActivity.class);
                stackDepth[0]++;
                intent.putExtra("stackDepth", stackDepth[0]);
                startActivity(intent);
            }

        });
        nextPageButton.setOnClickListener (view -> {
            //val intent = Intent(this@MainActivity, AttachPageActivity::class.java)
            //startActivity(intent)
        });
    }
}