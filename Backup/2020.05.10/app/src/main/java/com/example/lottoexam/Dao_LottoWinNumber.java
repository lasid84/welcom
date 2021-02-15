package com.example.lottoexam;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface Dao_LottoWinNumber {
    @Query("SELECT * FROM Table_LottoWinNumber")
    List<Table_LottoWinNumber> getAll();

    @Query("SELECT * FROM Table_LottoWinNumber WHERE _round = :round")
    List<Table_LottoWinNumber> getWinNumber(String round);

    @Query("SELECT count(_round) FROM Table_LottoWinNumber")
    int getAllcount();

    @Query("SELECT max(CAST(_round AS INTEGER)) FROM Table_LottoWinNumber")
    int getMaxRound();

    @Insert
    void insert(Table_LottoWinNumber lottoWinNumber);

    @Update
    void update(Table_LottoWinNumber lottoWinNumber);

    @Delete
    void delete(Table_LottoWinNumber lottoWinNumber);

}

