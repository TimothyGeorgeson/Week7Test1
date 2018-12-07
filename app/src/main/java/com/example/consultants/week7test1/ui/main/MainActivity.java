package com.example.consultants.week7test1.ui.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.consultants.week7test1.R;

import java.io.File;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainContract.View{
    public static final String TAG = MainActivity.class.getSimpleName() + "_TAG";

    MainPresenter presenter;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainPresenter();
        tvResult = findViewById(R.id.tvResult);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onAttach(this);

    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onDetach();
    }

    public void onScan(View view) {
        presenter.getPDF();
    }

    @Override
    public void showError(String error) {
        Log.d(TAG, "showError: " + error);
    }

    @Override
    public void onPDF(List<File> files) {
        Log.d(TAG, "onPDF: ");

        String fileInfo = "";

        //display max of 10 files
        int fileCount = files.size();
        if (fileCount > 10) fileCount = 10;

        for (int i = 0; i < fileCount; i++) {
            fileInfo += files.get(i).getName() + ": " + files.get(i).getTotalSpace() + "\n";
        }

        tvResult.setText(fileInfo);
    }

    @Override
    public void onNoPDFs() {
        tvResult.setText("No PDFs found");
    }
}
