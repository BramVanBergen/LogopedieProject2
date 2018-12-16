package be.thomasmore.logopedieproject2.DataService;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import be.thomasmore.logopedieproject2.App;
import be.thomasmore.logopedieproject2.DatabaseHelper;
import be.thomasmore.logopedieproject2.Models.Score;

public class ScoreDataService {
    private DatabaseHelper dbHelper = DatabaseHelper.getDbHelper(App.getAppContext());

    // insert Score
    public long insertScore(Score score) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("productiviteit", score.getProductiviteit());
        values.put("efficiëntie", score.getEfficientie());
        values.put("substitutiegedrag", score.getSubstitutiegedrag());
        values.put("coherentie", score.getCoherentie());
        values.put("datum", score.getDatum());
        values.put("patiëntId", score.getPatientId());

        long id = db.insert("score", null, values);

        db.close();
        return id;
    }

    // update Score
    public boolean updateScore(Score score) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("productiviteit", score.getProductiviteit());
        values.put("efficiëntie", score.getEfficientie());
        values.put("substitutiegedrag", score.getSubstitutiegedrag());
        values.put("coherentie", score.getCoherentie());
        values.put("datum", score.getDatum());
        values.put("patiëntId", score.getPatientId());

        int numrows = db.update(
                "score",
                values,
                "id = ?",
                new String[] { String.valueOf(score.getId()) });

        db.close();
        return numrows > 0;
    }

    // delete Score
    public boolean deleteScore(long id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        int numrows = db.delete(
                "score",
                "id = ?",
                new String[] { String.valueOf(id) });

        db.close();
        return numrows > 0;
    }

    // get single Score
    public Score getScore(long id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(
                "score",
                new String[] { "id", "productiviteit", "efficiëntie", "substitutiegedrag", "coherentie", "datum", "patiëntId" },
                "id = ?",
                new String[] { String.valueOf(id) },
                null,
                null,
                null,
                null);

        Score score = new Score();

        if (cursor.moveToFirst()) {
            score = new Score(
                    cursor.getLong(0),
                    cursor.getInt(1),
                    cursor.getInt(2),
                    cursor.getInt(3),
                    cursor.getInt(4),
                    cursor.getString(5),
                    cursor.getInt(6)
            );
        }
        cursor.close();
        db.close();
        return score;
    }

    // get list Score
    public List<Score> getScoreList() {
        List<Score> lijst = new ArrayList<Score>();

        String selectQuery = "SELECT  * FROM score ORDER BY id";

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Score score = new Score(
                        cursor.getLong(0),
                        cursor.getInt(1),
                        cursor.getInt(2),
                        cursor.getInt(3),
                        cursor.getInt(4),
                        cursor.getString(5),
                        cursor.getInt(6)
                );
                lijst.add(score);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return lijst;
    }
}
