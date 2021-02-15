package com.example.lottoexam;

import androidx.fragment.app.Fragment;

public class LottoNumber extends Fragment {
    private String mNumber;
    private int mColor;

    public LottoNumber(String mNumber) {
        this.mNumber = mNumber;
        if(isNumeric(mNumber) == true) {
            this.mColor = ((int) (Integer.parseInt(mNumber) - 1) / 10);
        } else {
            //+ 일경우 무한대값 설정
            this.mColor = 9999;
        }
    }

    public String getmNumber() {
        return mNumber;
    }

    public void setmNumber(String mNumber) {
        this.mNumber = mNumber;
    }

    public int getmColor() {
        //return Drawable.createFromPath("@drable/circle_red");
        return mColor;
    }

    public void setmColor(int mColor) {
        this.mColor = mColor;
    }

    public static boolean isNumeric(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch(NumberFormatException e) {
            return false;
        }
    }
}
