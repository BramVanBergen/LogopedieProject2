package be.thomasmore.logopedieproject2.DataService;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import be.thomasmore.logopedieproject2.App;
import be.thomasmore.logopedieproject2.DatabaseHelper;
import be.thomasmore.logopedieproject2.Models.Substitutiegedrag;

public class SubstitutiegedragDataService {
    private DatabaseHelper dbHelper = DatabaseHelper.getDbHelper(App.getAppContext());

    // get single Substitutiegedrag
    public Substitutiegedrag getSubstitutiegedrag(long id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(
                "substitutiegedrag",
                new String[] { "id", "woord" },
                "id = ?",
                new String[] { String.valueOf(id) },
                null,
                null,
                null,
                null);

        Substitutiegedrag substitutiegedrag = new Substitutiegedrag();

        if (cursor.moveToFirst()) {
            substitutiegedrag = new Substitutiegedrag(
                    cursor.getLong(0),
                    cursor.getString(1)
            );
        }
        cursor.close();
        db.close();
        return substitutiegedrag;
    }

    // get list Substitutiegedrag
    public List<Substitutiegedrag> getSubstitutiegedragList() {
        List<Substitutiegedrag> lijst = new ArrayList<Substitutiegedrag>();

        String selectQuery = "SELECT  * FROM substitutiegedrag ORDER BY id";

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Substitutiegedrag substitutiegedrag = new Substitutiegedrag(
                        cursor.getLong(0),
                        cursor.getString(1)
                );
                lijst.add(substitutiegedrag);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return lijst;
    }
}
