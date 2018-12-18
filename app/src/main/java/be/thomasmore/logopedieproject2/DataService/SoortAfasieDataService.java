package be.thomasmore.logopedieproject2.DataService;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import be.thomasmore.logopedieproject2.App;
import be.thomasmore.logopedieproject2.DatabaseHelper;
import be.thomasmore.logopedieproject2.Models.SoortAfasie;

public class SoortAfasieDataService {
    private DatabaseHelper dbHelper;

    public SoortAfasieDataService(DatabaseHelper dbHelper){
        this.dbHelper = dbHelper;
    }

    // get single SoortAfasie
    public SoortAfasie getSoortAfasie(long id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(
                "soortAfasie",
                new String[] { "id", "naam" },
                "id = ?",
                new String[] { String.valueOf(id) },
                null,
                null,
                null,
                null);

        SoortAfasie soortAfasie = new SoortAfasie();

        if (cursor.moveToFirst()) {
            soortAfasie = new SoortAfasie(
                    cursor.getLong(0),
                    cursor.getString(1)
            );
        }
        cursor.close();
        db.close();
        return soortAfasie;
    }

    // get list SoortAfasie
    public List<SoortAfasie> getSoortAfasieList() {
        List<SoortAfasie> lijst = new ArrayList<SoortAfasie>();

        String selectQuery = "SELECT  * FROM soortAfasie ORDER BY id";

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                SoortAfasie soortAfasie = new SoortAfasie(
                        cursor.getLong(0),
                        cursor.getString(1)
                );
                lijst.add(soortAfasie);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return lijst;
    }
}
