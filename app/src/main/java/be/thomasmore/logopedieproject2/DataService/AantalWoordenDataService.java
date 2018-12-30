package be.thomasmore.logopedieproject2.DataService;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import be.thomasmore.logopedieproject2.App;
import be.thomasmore.logopedieproject2.DatabaseHelper;
import be.thomasmore.logopedieproject2.Models.AantalWoorden;

public class AantalWoordenDataService {
    private DatabaseHelper dbHelper;

    public AantalWoordenDataService(DatabaseHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    // insert AantalWoorden
    public long insertAantalWoorden(AantalWoorden aantalWoorden) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("productiviteit", aantalWoorden.getProductiviteit());
        values.put("efficiëntie", aantalWoorden.getEfficientie());
        values.put("substitutiegedrag", aantalWoorden.getSubstitutiegedrag());
        values.put("coherentie", aantalWoorden.getCoherentie());
        values.put("datum", aantalWoorden.getDatum());
        values.put("patiëntId", aantalWoorden.getPatientId());

        long id = db.insert("aantalWoorden", null, values);

        db.close();
        return id;
    }

    // update AantalWoorden
    public boolean updateAantalWoorden(AantalWoorden aantalWoorden) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("productiviteit", aantalWoorden.getProductiviteit());
        values.put("efficiëntie", aantalWoorden.getEfficientie());
        values.put("substitutiegedrag", aantalWoorden.getSubstitutiegedrag());
        values.put("coherentie", aantalWoorden.getCoherentie());
        values.put("datum", aantalWoorden.getDatum());
        values.put("patiëntId", aantalWoorden.getPatientId());

        int numrows = db.update(
                "aantalWoorden",
                values,
                "id = ?",
                new String[] { String.valueOf(aantalWoorden.getId()) });

        db.close();
        return numrows > 0;
    }

    // delete AantalWoorden
    public boolean deleteAantalWoorden(long id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        int numrows = db.delete(
                "aantalWoorden",
                "id = ?",
                new String[] { String.valueOf(id) });

        db.close();
        return numrows > 0;
    }

    // get single AantalWoorden
    public AantalWoorden getAantalWoorden(long id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(
                "aantalWoorden",
                new String[] { "id", "productiviteit", "efficiëntie", "substitutiegedrag", "coherentie", "datum", "patiëntId" },
                "id = ?",
                new String[] { String.valueOf(id) },
                null,
                null,
                null,
                null);

        AantalWoorden aantalWoorden = new AantalWoorden();

        if (cursor.moveToFirst()) {
            aantalWoorden = new AantalWoorden(
                    cursor.getLong(0),
                    cursor.getInt(1),
                    cursor.getInt(2),
                    cursor.getInt(3),
                    cursor.getInt(4),
                    cursor.getString(5),
                    cursor.getLong(6)
            );
        }
        cursor.close();
        db.close();
        return aantalWoorden;
    }

    // get list AantalWoorden
    public List<AantalWoorden> getAantalWoordenList() {
        List<AantalWoorden> lijst = new ArrayList<AantalWoorden>();

        String selectQuery = "SELECT  * FROM aantalWoorden ORDER BY id";

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                AantalWoorden aantalWoorden = new AantalWoorden(
                        cursor.getLong(0),
                        cursor.getInt(1),
                        cursor.getInt(2),
                        cursor.getInt(3),
                        cursor.getInt(4),
                        cursor.getString(5),
                        cursor.getLong(6)
                );
                lijst.add(aantalWoorden);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return lijst;
    }
}
