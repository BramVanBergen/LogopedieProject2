package be.thomasmore.logopedieproject2.DataService;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import be.thomasmore.logopedieproject2.App;
import be.thomasmore.logopedieproject2.DatabaseHelper;
import be.thomasmore.logopedieproject2.Models.Logopedist;

public class LogopedistDataService {
    private DatabaseHelper dbHelper;

    public LogopedistDataService(DatabaseHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    // insert Logopedist
    public long insertLogopedist(Logopedist logopedist) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("voornaam", logopedist.getVoornaam());
        values.put("achternaam", logopedist.getAchternaam());
        values.put("email", logopedist.getEmail());
        values.put("gebruikersnaam", logopedist.getGebruikersnaam());
        values.put("wachtwoord", logopedist.getWachtwoord());

        long id = db.insert("logopedist", null, values);

        db.close();
        return id;
    }

    // update Logopedist
    public boolean updateLogopedist(Logopedist logopedist) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("voornaam", logopedist.getVoornaam());
        values.put("achternaam", logopedist.getAchternaam());
        values.put("email", logopedist.getEmail());
        values.put("gebruikersnaam", logopedist.getGebruikersnaam());
        values.put("wachtwoord", logopedist.getWachtwoord());

        int numrows = db.update(
                "logopedist",
                values,
                "id = ?",
                new String[]{String.valueOf(logopedist.getId())});

        db.close();
        return numrows > 0;
    }

    // delete Logopedist
    public boolean deleteLogopedist(long id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        int numrows = db.delete(
                "logopedist",
                "id = ?",
                new String[]{String.valueOf(id)});

        db.close();
        return numrows > 0;
    }

    // get single Logopedist
    public Logopedist getLogopedist(long id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(
                "logopedist",
                new String[]{"id", "voornaam", "achternaam", "email", "gebruikersnaam", "wachtwoord"},
                "id = ?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);

        Logopedist logopedist = new Logopedist();

        if (cursor.moveToFirst()) {
            logopedist = new Logopedist(
                    cursor.getLong(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5)
            );
        }
        cursor.close();
        db.close();
        return logopedist;
    }

    // check login credentials
    public Logopedist login(String gebruikersnaam, String wachtwoord) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Logopedist logopedist = new Logopedist();

        // controleren of gebruikersnaam of email wordt gebruikt om in te loggen
        if (gebruikersnaam.contains("@")) {
            // email
            String email = gebruikersnaam;
            if (!email.equals("") && !wachtwoord.equals("")) {
                // email of wachtwoord is niet ingevuld

                Cursor cursor = db.query(
                        "logopedist",
                        new String[]{"id", "voornaam", "achternaam", "email", "gebruikersnaam", "wachtwoord"},
                        "email = ?",
                        new String[]{String.valueOf(email)},
                        null,
                        null,
                        null,
                        null);


                if (cursor.moveToFirst()) {
                    logopedist = new Logopedist(
                            cursor.getLong(0),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3),
                            cursor.getString(4),
                            cursor.getString(5)
                    );
                }
                cursor.close();

                if (logopedist.getGebruikersnaam() == null) {
                    logopedist = null;
                } else if (!logopedist.getWachtwoord().equals(wachtwoord)) {
                    // wachtwoord matcht niet met email
                    logopedist = null;
                }
            }
        } else {
            // gebruikersnaam

            if (!gebruikersnaam.equals("") && !wachtwoord.equals("")) {
                // gebruikersnaam of wachtwoord is niet ingevuld

                Cursor cursor = db.query(
                        "logopedist",
                        new String[]{"id", "voornaam", "achternaam", "email", "gebruikersnaam", "wachtwoord"},
                        "gebruikersnaam = ?",
                        new String[]{String.valueOf(gebruikersnaam)},
                        null,
                        null,
                        null,
                        null);


                if (cursor.moveToFirst()) {
                    logopedist = new Logopedist(
                            cursor.getLong(0),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3),
                            cursor.getString(4),
                            cursor.getString(5)
                    );
                }
                cursor.close();

                if (logopedist.getGebruikersnaam() == null) {
                    logopedist = null;
                } else if (!logopedist.getWachtwoord().equals(wachtwoord)) {
                    // wachtwoord matcht niet met gebruikersnaam
                    logopedist = null;
                }
            }
            else {
                logopedist = null;
            }
        }

        db.close();
        return logopedist;
    }

    // get list Logopedist
    public List<Logopedist> getLogopedistList() {
        List<Logopedist> lijst = new ArrayList<Logopedist>();

        String selectQuery = "SELECT  * FROM logopedist ORDER BY id";

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Logopedist logopedist = new Logopedist(
                        cursor.getLong(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5)
                );
                lijst.add(logopedist);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return lijst;
    }
}
