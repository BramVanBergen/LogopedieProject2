package be.thomasmore.logopedieproject2.DataService;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import be.thomasmore.logopedieproject2.App;
import be.thomasmore.logopedieproject2.DatabaseHelper;
import be.thomasmore.logopedieproject2.Models.Aanvullend;

public class AanvullendDataService {
    private DatabaseHelper dbHelper;

    public AanvullendDataService(DatabaseHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    // get single Aanvullend
    public Aanvullend getAanvullend(long id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(
                "aanvullend",
                new String[] { "id", "woord" },
                "id = ?",
                new String[] { String.valueOf(id) },
                null,
                null,
                null,
                null);

        Aanvullend aanvullend = new Aanvullend();

        if (cursor.moveToFirst()) {
            aanvullend = new Aanvullend(
                    cursor.getLong(0),
                    cursor.getString(1)
            );
        }
        cursor.close();
        db.close();
        return aanvullend;
    }

    // get list Aanvullend
    public List<Aanvullend> getAanvullendList() {
        List<Aanvullend> lijst = new ArrayList<Aanvullend>();

        String selectQuery = "SELECT  * FROM aanvullend ORDER BY id";

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Aanvullend aanvullend = new Aanvullend(
                        cursor.getLong(0),
                        cursor.getString(1)
                );
                lijst.add(aanvullend);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return lijst;
    }
}
