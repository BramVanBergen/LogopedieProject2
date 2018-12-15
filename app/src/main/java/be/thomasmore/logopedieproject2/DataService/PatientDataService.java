package be.thomasmore.logopedieproject2.DataService;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import be.thomasmore.logopedieproject2.App;
import be.thomasmore.logopedieproject2.DatabaseHelper;
import be.thomasmore.logopedieproject2.Models.Patient;

public class PatientDataService {
    private DatabaseHelper dbHelper = DatabaseHelper.getDbHelper(App.getAppContext());

    // insert Patient
    public long insertPatient(Patient patient) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("voornaam", patient.getVoornaam());
        values.put("achternaam", patient.getAchternaam());
        values.put("geslacht", patient.getGeslacht());
        values.put("geboortedatum", patient.getGeboortedatum());
        values.put("soortAfasieId", patient.getSoortAfasieId());
        values.put("logopedistId", patient.getLogopedistId());

        long id = db.insert("patiënt", null, values);

        db.close();
        return id;
    }

    // update Patient
    public boolean updatePatient(Patient patient) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("voornaam", patient.getVoornaam());
        values.put("achternaam", patient.getAchternaam());
        values.put("geslacht", patient.getGeslacht());
        values.put("geboortedatum", patient.getGeboortedatum());
        values.put("soortAfasieId", patient.getSoortAfasieId());
        values.put("logopedistId", patient.getLogopedistId());

        int numrows = db.update(
                "patiënt",
                values,
                "id = ?",
                new String[] { String.valueOf(patient.getId()) });

        db.close();
        return numrows > 0;
    }

    // delete Patient
    public boolean deletePatient(long id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        int numrows = db.delete(
                "patiënt",
                "id = ?",
                new String[] { String.valueOf(id) });

        db.close();
        return numrows > 0;
    }

    // get single Patient
    public Patient getPatient(long id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(
                "patiënt",
                new String[] { "id", "voornaam", "achternaam", "geslacht", "geboortedatum", "soortAfasieId", "logopedistId" },
                "id = ?",
                new String[] { String.valueOf(id) },
                null,
                null,
                null,
                null);

        Patient patient = new Patient();

        if (cursor.moveToFirst()) {
            patient = new Patient(
                    cursor.getLong(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getLong(5),
                    cursor.getLong(6)
            );
        }
        cursor.close();
        db.close();
        return patient;
    }

    // get list Patient
    public List<Patient> getPatientList() {
        List<Patient> lijst = new ArrayList<Patient>();

        String selectQuery = "SELECT  * FROM patiënt ORDER BY id";

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Patient patient = new Patient(
                        cursor.getLong(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getLong(5),
                        cursor.getLong(6)
                );
                lijst.add(patient);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return lijst;
    }
}
