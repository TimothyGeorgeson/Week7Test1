package com.example.consultants.week7test1.ui.main;

import com.example.consultants.week7test1.ui.base.BasePresenter;
import com.example.consultants.week7test1.ui.base.BaseView;

import java.io.File;
import java.util.List;

public interface MainContract {
    interface View extends BaseView {

        void onPDF(List<File> files);
        void onNoPDFs();
    }

    interface Presenter extends BasePresenter<View> {

        void getPDF();
    }
}
