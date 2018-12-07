package com.example.consultants.week7test1.ui.main;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View view;
    private List<File> files;
    private List<File> dir;
    private List<File> rootDirs;

    @Override
    public void getPDF() {

        dir = new ArrayList<File>();
        files = new ArrayList<File>();

        File root = new File (Environment.getExternalStorageDirectory().getAbsolutePath());

        try {
            rootDirs = Arrays.asList(root.listFiles());
        } catch (Exception e) {

        }

        if(rootDirs == null)
        {
            view.onNoPDFs();
        } else {
            for (File f : rootDirs) {
                scan(f);
            }

            view.onPDF(rootDirs);

            Log.i("files", "" + files.size());
            Log.i("dir", "" + dir.size());
        }
    }

    public void scan (File path) {

        for (File f : path.listFiles()) {
            if (f.isFile()) {
                files.add(f);
            }
            else {
                dir.add(f);
                scan(f);
            }
        }
    }

    @Override
    public void onAttach(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void onDetach() {
        this.view = null;
    }
}
