package com.example.lottoexam;

import androidx.fragment.app.Fragment;

public class LottoNumber extends Fragment {
    private int mNumber;
    private int mColor;

    public LottoNumber(int mNumber) {
        this.mNumber = mNumber;
        this.mColor = ((int) mNumber / 10);
    }

    public int getmNumber() {
        return mNumber;
    }

    public void setmNumber(int mNumber) {
        this.mNumber = mNumber;
    }

    public int getmColor() {
        //return Drawable.createFromPath("@drable/circle_red");
        return mColor;
    }

    public void setmColor(int mColor) {
        this.mColor = mColor;
    }
}
