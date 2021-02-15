package com.example.lottoexam;

import androidx.fragment.app.Fragment;

public class MyLottoNumber extends Fragment {
    private String _round;
    private String _seq;
    private String _type;
    private String _no1;
    private String _no1_yn;
    private String _no2;
    private String _no2_yn;
    private String _no3;
    private String _no3_yn;
    private String _no4;
    private String _no4_yn;
    private String _no5;
    private String _no5_yn;
    private String _no6;
    private String _no6_yn;
    private String _result;
    private int mColor;

    public MyLottoNumber(String _round, String _seq, String _type, String _no1, String _no1_yn, String _no2, String _no2_yn, String _no3, String _no3_yn, String _no4
                       , String _no4_yn, String _no5, String _no5_yn, String _no6, String _no6_yn, String _result) {
        this._round = _round;
        this._seq = _seq;
        this._type = _type;
        this._no1 = _no1;
        this._no1_yn = _no1_yn;
        this._no2 = _no2;
        this._no2_yn = _no2_yn;
        this._no3 = _no3;
        this._no3_yn = _no3_yn;
        this._no4 = _no4;
        this._no4_yn = _no4_yn;
        this._no5 = _no5;
        this._no5_yn = _no5_yn;
        this._no6 = _no6;
        this._no6_yn = _no6_yn;
        this._result = _result;
    }
}

