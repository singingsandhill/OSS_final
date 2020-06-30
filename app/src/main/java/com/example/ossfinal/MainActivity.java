package com.example.ossfinal;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.FileInputStream;

public class MainActivity extends AppCompatActivity {
    private Button btn_Save;
    private Button btn_Load;
    private Button btn_Delete;
    private EditText editText_TextArea;
    private String fileName = "MyMemo.txt";
    private long backPressTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_Save = findViewById(R.id.button_save);
        btn_Load = findViewById(R.id.button_load);
        btn_Delete = findViewById(R.id.button_delete);
        editText_TextArea = findViewById(R.id.editText3);
        btn_Save.setOnClickListener(btnSaveListener);
        btn_Load.setOnClickListener(btnLoadListener);
        btn_Delete.setOnClickListener(btnDeleteListener);
    }

    View.OnClickListener btnLoadListener = new View.OnClickListener()
    {
        public void onClick(View v)
        {
            FileInputStream inputStream = null;
            try
            {
                inputStream = openFileInput(fileName);
                byte[] data = new byte[inputStream.available()];
                while(inputStream.read(data) != -1) {}
                editText_TextArea.setText(new String(data));
                Toast.makeText(getApplicationContext(), "로드 성공", Toast.LENGTH_LONG).show();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    if(inputStream != null)
                        inputStream.close();
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    };