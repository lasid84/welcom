package com.example.lottoexam;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Table_LottoWinNumber.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract Dao_LottoWinNumber Dao_LottoWinNumber();
}
