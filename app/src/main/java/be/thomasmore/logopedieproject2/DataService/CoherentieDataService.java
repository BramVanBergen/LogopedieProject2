package be.thomasmore.logopedieproject2.DataService;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import be.thomasmore.logopedieproject2.App;
import be.thomasmore.logopedieproject2.DatabaseHelper;
import be.thomasmore.logopedieproject2.Models.Coherentie;

public class CoherentieDataService {
    private DatabaseHelper dbHelper;

    public CoherentieDataService(DatabaseHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    // get single Coherentie
    public Coherentie getCoherentie(long id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(
                "coherentie",
                new String[] { "id", "oorzaak", "gevolg" },
                "id = ?",
                new String[] { String.valueOf(id) },
                null,
                null,
                null,
                null);

        Coherentie coherentie = new Coherentie();

        if (cursor.moveToFirst()) {
            coherentie = new Coherentie(
                    cursor.getLong(0),
                    cursor.getString(1),
                    cursor.getString(2)
            );
        }
        cursor.close();
        db.close();
        return coherentie;
    }

    // get list Coherentie
    public List<Coherentie> getCoherentieList() {
        List<Coherentie> lijst = new ArrayList<Coherentie>();

        String selectQuery = "SELECT  * FROM coherentie ORDER BY id";

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Coherentie coherentie = new Coherentie(
                        cursor.getLong(0),
                        cursor.getString(1),
                        cursor.getString(2)
                );
                lijst.add(coherentie);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return lijst;
    }
}
