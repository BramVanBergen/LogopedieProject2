package be.thomasmore.logopedieproject2.DataService;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import be.thomasmore.logopedieproject2.App;
import be.thomasmore.logopedieproject2.DatabaseHelper;
import be.thomasmore.logopedieproject2.Models.Efficientie;

public class EfficientieDataService {
    private DatabaseHelper dbHelper;

    public EfficientieDataService(DatabaseHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    // get single Efficientie
    public Efficientie getEfficientie(long id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(
                "efficientie",
                new String[] { "id", "woord" },
                "id = ?",
                new String[] { String.valueOf(id) },
                null,
                null,
                null,
                null);

        Efficientie efficientie = new Efficientie();

        if (cursor.moveToFirst()) {
            efficientie = new Efficientie(
                    cursor.getLong(0),
                    cursor.getString(1)
            );
        }
        cursor.close();
        db.close();
        return efficientie;
    }

    // get list Efficientie
    public List<Efficientie> getEfficientieList() {
        List<Efficientie> lijst = new ArrayList<Efficientie>();

        String selectQuery = "SELECT  * FROM efficientie ORDER BY id";

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Efficientie efficientie = new Efficientie(
                        cursor.getLong(0),
                        cursor.getString(1)
                );
                lijst.add(efficientie);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return lijst;
    }
}
