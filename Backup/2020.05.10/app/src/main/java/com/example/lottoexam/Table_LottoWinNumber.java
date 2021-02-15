package com.example.lottoexam;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Table_LottoWinNumber {
    @PrimaryKey
    @NonNull
    private String _round;
    private String _date;
    private String _no1;
    private String _no2;
    private String _no3;
    private String _no4;
    private String _no5;
    private String _no6;
    private String _bonus;
    //총판매액
    private String _totSellamnt;
    //1등 당첨 총 금액
    private String _firstAccumamnt;
    //1등 당첨자 수
    private String _firstPrzwnerCo;
    //1등 1명당 당첨 금액
    private String _firstWinamnt;
    //읽기 성공 여부(성공 : success, 실패 : fail)
    private String _result;

    public Table_LottoWinNumber(String _round, String _date, String _no1, String _no2, String _no3, String _no4, String _no5, String _no6, String _bonus
                              , String _totSellamnt, String _firstAccumamnt, String _firstPrzwnerCo, String _firstWinamnt, String _result) {
        this._round = _round;
        this._date = _date;
        this._no1 = _no1;
        this._no2 = _no2;
        this._no3 = _no3;
        this._no4 = _no4;
        this._no5 = _no5;
        this._no6 = _no6;
        this._bonus = _bonus;
        this._totSellamnt = _totSellamnt;
        this._firstAccumamnt = _firstAccumamnt;
        this._firstPrzwnerCo = _firstPrzwnerCo;
        this._firstWinamnt = _firstWinamnt;
        this._result = _result;
    }

    public String get_round() {
        return _round;
    }

    public void set_round(String _round) {
        this._round = _round;
    }

    public String get_date() {
        return _date;
    }

    public void set_date(String _date) {
        this._date = _date;
    }

    public String get_no1() {
        return _no1;
    }

    public void set_no1(String _no1) {
        this._no1 = _no1;
    }

    public String get_no2() {
        return _no2;
    }

    public void set_no2(String _no2) {
        this._no2 = _no2;
    }

    public String get_no3() {
        return _no3;
    }

    public void set_no3(String _no3) {
        this._no3 = _no3;
    }

    public String get_no4() {
        return _no4;
    }

    public void set_no4(String _no4) {
        this._no4 = _no4;
    }

    public String get_no5() {
        return _no5;
    }

    public void set_no5(String _no5) {
        this._no5 = _no5;
    }

    public String get_no6() {
        return _no6;
    }

    public void set_no6(String _no6) {
        this._no6 = _no6;
    }

    public String get_bonus() {
        return _bonus;
    }

    public void set_bonus(String _bonus) {
        this._bonus = _bonus;
    }

    public String get_totSellamnt() {
        return _totSellamnt;
    }

    public void set_totSellamnt(String _totSellamnt) {
        this._totSellamnt = _totSellamnt;
    }

    public String get_firstAccumamnt() {
        return _firstAccumamnt;
    }

    public void set_firstAccumamnt(String _firstAccumamnt) {
        this._firstAccumamnt = _firstAccumamnt;
    }

    public String get_firstPrzwnerCo() {
        return _firstPrzwnerCo;
    }

    public void set_firstPrzwnerCo(String _firstPrzwnerCo) {
        this._firstPrzwnerCo = _firstPrzwnerCo;
    }

    public String get_firstWinamnt() {
        return _firstWinamnt;
    }

    public void set_firstWinamnt(String _firstWinamnt) {
        this._firstWinamnt = _firstWinamnt;
    }

    public String get_result() {
        return _result;
    }

    public void set_result(String _result) {
        this._result = _result;
    }

    @Override
    public String toString() {
        return "{" +
                "\"round\" : " + _round + "," +
                "\"date\" : " + _date + "," +
                "\"no1\" : " + _no1 + "," +
                "\"no2\" : " + _no2 + "," +
                "\"no3\" : " + _no3 + "," +
                "\"no4\" : " + _no4 + "," +
                "\"no5\" : " + _no5 + "," +
                "\"no6\" : " + _no6 + "," +
                "\"bonus\" : " + _bonus + "," +
                "\"totSellamnt\" : " + _totSellamnt + "," +
                "\"firstAccumamnt\" : " + _firstAccumamnt + "," +
                "\"firstPrzwnerCo\" : " + _firstPrzwnerCo + "," +
                "\"firstWinamnt\" : " + _firstWinamnt + "," +
                "\"result\" : " + _result  +
                '}';
    }
}
