package be.thomasmore.logopedieproject2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{
    private static DatabaseHelper dbHelper;

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "logopedieProject2";

    public static synchronized DatabaseHelper getDbHelper(Context context){
        if (dbHelper == null) {
            dbHelper = new DatabaseHelper(context.getApplicationContext());
        }
        return dbHelper;
    }

    // uitgevoerd bij instantiatie van DatabaseHelper
    // -> als database nog niet bestaat, dan creëren (call onCreate)
    // -> als de DATABASE_VERSION hoger is dan de huidige versie,
    //    dan upgraden (call onUpgrade)

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // methode wordt uitgevoerd als de database gecreëerd wordt
    // hierin de tables creëren en opvullen met gegevens
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_PATIENT = "CREATE TABLE patiënt (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "voornaam TEXT," +
                "achternaam TEXT," +
                "geslacht TEXT," +
                "geboortedatum TEXT," +
                "soortAfasieId INTEGER," +
                "logopedistId INTEGER," +
                "FOREIGN KEY (soortAfasieId) REFERENCES soortAfasie(id)," +
                "FOREIGN KEY (logopedistId) REFERENCES logopedist(id))";
        db.execSQL(CREATE_TABLE_PATIENT);

        String CREATE_TABLE_LOGOPEDIST = "CREATE TABLE logopedist (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "voornaam TEXT," +
                "achternaam TEXT," +
                "email TEXT," +
                "gebruikersnaam TEXT," +
                "wachtwoord TEXT)";
        db.execSQL(CREATE_TABLE_LOGOPEDIST);

        String CREATE_TABLE_SOORTAFASIE = "CREATE TABLE soortAfasie (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "naam TEXT)";
        db.execSQL(CREATE_TABLE_SOORTAFASIE);

        String CREATE_TABLE_SCORE = "CREATE TABLE score (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "productiviteit INTEGER," +
                "efficiëntie INTEGER," +
                "substitutiegedrag INTEGER," +
                "coherentie INTEGER," +
                "datum TEXT," +
                "patiëntId INTEGER," +
                "FOREIGN KEY (patiëntId) REFERENCES patiënt(id))";
        db.execSQL(CREATE_TABLE_SCORE);

        String CREATE_TABLE_AANTALWOORDEN = "CREATE TABLE aantalWoorden (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "productiviteit INTEGER," +
                "efficiëntie INTEGER," +
                "substitutiegedrag INTEGER," +
                "coherentie INTEGER," +
                "datum TEXT," +
                "patiëntId INTEGER," +
                "FOREIGN KEY (patiëntId) REFERENCES patiënt(id))";
        db.execSQL(CREATE_TABLE_AANTALWOORDEN);

        String CREATE_TABLE_EFFICIENTIE = "CREATE TABLE efficiëntie (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "woord TEXT)";
        db.execSQL(CREATE_TABLE_EFFICIENTIE);

        String CREATE_TABLE_SYNONIEM = "CREATE TABLE synoniem (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "woord TEXT," +
                "efficiëntieId INTEGER)";
        db.execSQL(CREATE_TABLE_SYNONIEM);

        String CREATE_TABLE_AANVULLEND = "CREATE TABLE aanvullend (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "woord TEXT)";
        db.execSQL(CREATE_TABLE_AANVULLEND);

        String CREATE_TABLE_SUBSTITUTIEGEDRAG = "CREATE TABLE substitutiegedrag (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "woord TEXT)";
        db.execSQL(CREATE_TABLE_SUBSTITUTIEGEDRAG);

        String CREATE_TABLE_COHERENTIE = "CREATE TABLE coherentie (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "oorzaak TEXT," +
                "gevolg TEXT)";
        db.execSQL(CREATE_TABLE_COHERENTIE);

        //Gegevens inladen
        insertPatienten(db);
        insertLogopedist(db);
        insertSoortAfasieen(db);
        insertScore(db);
        insertAantalWoorden(db);
        insertEfficientie(db);
        insertSynoniemen(db);
        insertAanvullenden(db);
        insertSubstitutiegedragen(db);
        insertCoherenties(db);
    }

    private void insertPatienten(SQLiteDatabase db) {
        db.execSQL("INSERT INTO patiënt (id, voornaam, achternaam, geslacht, geboortedatum, soortAfasieId, logopedistId) VALUES (1, 'Bram', 'Van Bergen', 'M', '27/08/1998', 9, 1);");
        db.execSQL("INSERT INTO patiënt (id, voornaam, achternaam, geslacht, geboortedatum, soortAfasieId, logopedistId) VALUES (2, 'Arno', 'Stoop', 'M', '30/07/1998', 3, 1);");
    }

    private void insertLogopedist(SQLiteDatabase db) {
        db.execSQL("INSERT INTO logopedist (id, voornaam, achternaam, email, gebruikersnaam, wachtwoord) VALUES (1, 'Laurien', 'Houben', 'laurien.houben@test.com', 'laurien', 'test');");
        db.execSQL("INSERT INTO logopedist (id, voornaam, achternaam, email, gebruikersnaam, wachtwoord) VALUES (2, 'Tom', 'Nuyts', 'tom.nuyts@test.com', 'tom', 'test');");
    }

    private void insertSoortAfasieen(SQLiteDatabase db) {
        db.execSQL("INSERT INTO soortAfasie (id, naam) VALUES (1, 'Motorische afasie');");
        db.execSQL("INSERT INTO soortAfasie (id, naam) VALUES (2, 'Transcorticale motorische afasie');");
        db.execSQL("INSERT INTO soortAfasie (id, naam) VALUES (3, 'Sensorische afasie');");
        db.execSQL("INSERT INTO soortAfasie (id, naam) VALUES (4, 'Transcorticale sensorische afasie');");
        db.execSQL("INSERT INTO soortAfasie (id, naam) VALUES (5, 'Geleidingsafasie');");
        db.execSQL("INSERT INTO soortAfasie (id, naam) VALUES (6, 'Amnestische afasie');");
        db.execSQL("INSERT INTO soortAfasie (id, naam) VALUES (7, 'Gemengde afasie');");
        db.execSQL("INSERT INTO soortAfasie (id, naam) VALUES (8, 'Akoestische afasie');");
        db.execSQL("INSERT INTO soortAfasie (id, naam) VALUES (9, 'Alexie');");
    }

    private void insertScore(SQLiteDatabase db) {
        db.execSQL("INSERT INTO score (id, productiviteit, efficiëntie, substitutiegedrag, coherentie, datum, patiëntId) VALUES (1, 50, 4, 3, 5, '11/12/2018', 2);");
        db.execSQL("INSERT INTO score (id, productiviteit, efficiëntie, substitutiegedrag, coherentie, datum, patiëntId) VALUES (2, 28, 3, 2, 2, '5/12/2018', 1);");
    }

    private void insertAantalWoorden(SQLiteDatabase db) {
        db.execSQL("INSERT INTO aantalWoorden (id, productiviteit, efficiëntie, substitutiegedrag, coherentie, datum, patiëntId) VALUES (1, 47, 23, 19, 5, '5/12/2018', 1);");
        db.execSQL("INSERT INTO aantalWoorden (id, productiviteit, efficiëntie, substitutiegedrag, coherentie, datum, patiëntId) VALUES (2, 28, 15, 11, 2, '5/12/2018', 2);");
    }

    private void insertEfficientie(SQLiteDatabase db) {
        db.execSQL("INSERT INTO efficiëntie (id, woord) VALUES (1, 'Armstoel');");
        db.execSQL("INSERT INTO efficiëntie (id, woord) VALUES (2, 'Beer');");
        db.execSQL("INSERT INTO efficiëntie (id, woord) VALUES (3, 'Boeken');");
        db.execSQL("INSERT INTO efficiëntie (id, woord) VALUES (4, 'Boekenplank');");
        db.execSQL("INSERT INTO efficiëntie (id, woord) VALUES (5, 'Bokaal');");
        db.execSQL("INSERT INTO efficiëntie (id, woord) VALUES (6, 'De');");
        db.execSQL("INSERT INTO efficiëntie (id, woord) VALUES (7, 'Dochter');");
        db.execSQL("INSERT INTO efficiëntie (id, woord) VALUES (8, 'Duwen');");
        db.execSQL("INSERT INTO efficiëntie (id, woord) VALUES (9, 'Grijpen');");
        db.execSQL("INSERT INTO efficiëntie (id, woord) VALUES (10, 'Grond');");
        db.execSQL("INSERT INTO efficiëntie (id, woord) VALUES (11, 'Grootvader');");
        db.execSQL("INSERT INTO efficiëntie (id, woord) VALUES (12, 'Haar');");
        db.execSQL("INSERT INTO efficiëntie (id, woord) VALUES (13, 'Hoofd');");
        db.execSQL("INSERT INTO efficiëntie (id, woord) VALUES (14, 'In');");
        db.execSQL("INSERT INTO efficiëntie (id, woord) VALUES (15, 'Kat');");
        db.execSQL("INSERT INTO efficiëntie (id, woord) VALUES (16, 'Met');");
        db.execSQL("INSERT INTO efficiëntie (id, woord) VALUES (17, 'Op');");
        db.execSQL("INSERT INTO efficiëntie (id, woord) VALUES (18, 'Poot');");
        db.execSQL("INSERT INTO efficiëntie (id, woord) VALUES (19, 'Proberen');");
        db.execSQL("INSERT INTO efficiëntie (id, woord) VALUES (20, 'Slapen');");
        db.execSQL("INSERT INTO efficiëntie (id, woord) VALUES (21, 'Spelen');");
        db.execSQL("INSERT INTO efficiëntie (id, woord) VALUES (22, 'Staart');");
        db.execSQL("INSERT INTO efficiëntie (id, woord) VALUES (23, 'Vaas');");
        db.execSQL("INSERT INTO efficiëntie (id, woord) VALUES (24, 'Vallen');");
        db.execSQL("INSERT INTO efficiëntie (id, woord) VALUES (25, 'Van');");
        db.execSQL("INSERT INTO efficiëntie (id, woord) VALUES (26, 'Vis');");
        db.execSQL("INSERT INTO efficiëntie (id, woord) VALUES (27, 'Waarschuwen');");
        db.execSQL("INSERT INTO efficiëntie (id, woord) VALUES (28, 'Zitten');");
    }

    private void insertSynoniemen(SQLiteDatabase db) {
        db.execSQL("INSERT INTO synoniem (id, woord, efficiëntieId) VALUES (1, 'Leunstoel', 1);");
        db.execSQL("INSERT INTO synoniem (id, woord, efficiëntieId) VALUES (2, 'Sofa', 1);");
        db.execSQL("INSERT INTO synoniem (id, woord, efficiëntieId) VALUES (3, 'Zetel', 1);");
        db.execSQL("INSERT INTO synoniem (id, woord, efficiëntieId) VALUES (4, 'Bank', 1);");
        db.execSQL("INSERT INTO synoniem (id, woord, efficiëntieId) VALUES (5, 'Knuffel', 2);");
        db.execSQL("INSERT INTO synoniem (id, woord, efficiëntieId) VALUES (6, 'Pop', 2);");
        db.execSQL("INSERT INTO synoniem (id, woord, efficiëntieId) VALUES (7, 'Speelgoed', 2);");
        db.execSQL("INSERT INTO synoniem (id, woord, efficiëntieId) VALUES (8, 'Plank', 4);");
        db.execSQL("INSERT INTO synoniem (id, woord, efficiëntieId) VALUES (9, 'Rek', 4);");
        db.execSQL("INSERT INTO synoniem (id, woord, efficiëntieId) VALUES (10, 'Visbokaal', 5);");
        db.execSQL("INSERT INTO synoniem (id, woord, efficiëntieId) VALUES (11, 'Viskom', 5);");
        db.execSQL("INSERT INTO synoniem (id, woord, efficiëntieId) VALUES (12, 'Het', 6);");
        db.execSQL("INSERT INTO synoniem (id, woord, efficiëntieId) VALUES (13, 'Een', 6);");
        db.execSQL("INSERT INTO synoniem (id, woord, efficiëntieId) VALUES (14, 'Kind', 7);");
        db.execSQL("INSERT INTO synoniem (id, woord, efficiëntieId) VALUES (15, 'Kindje', 7);");
        db.execSQL("INSERT INTO synoniem (id, woord, efficiëntieId) VALUES (16, 'Meisje', 7);");
        db.execSQL("INSERT INTO synoniem (id, woord, efficiëntieId) VALUES (17, 'Duwt', 8);");
        db.execSQL("INSERT INTO synoniem (id, woord, efficiëntieId) VALUES (18, 'Geduwd', 8);");
        db.execSQL("INSERT INTO synoniem (id, woord, efficiëntieId) VALUES (19, 'Te duwen', 8);");
        db.execSQL("INSERT INTO synoniem (id, woord, efficiëntieId) VALUES (20, 'Heeft geduwd', 8);");
        db.execSQL("INSERT INTO synoniem (id, woord, efficiëntieId) VALUES (21, 'duwde', 8);");
        db.execSQL("INSERT INTO synoniem (id, woord, efficiëntieId) VALUES (22, 'Te grijpen', 9);");
        db.execSQL("INSERT INTO synoniem (id, woord, efficiëntieId) VALUES (23, 'Pakken', 9);");
        db.execSQL("INSERT INTO synoniem (id, woord, efficiëntieId) VALUES (24, 'Te pakken', 9);");
        db.execSQL("INSERT INTO synoniem (id, woord, efficiëntieId) VALUES (25, 'Vangen', 9);");
        db.execSQL("INSERT INTO synoniem (id, woord, efficiëntieId) VALUES (26, 'Te vangen', 9);");
        db.execSQL("INSERT INTO synoniem (id, woord, efficiëntieId) VALUES (27, 'Vloer', 10);");
        db.execSQL("INSERT INTO synoniem (id, woord, efficiëntieId) VALUES (28, 'Man', 11);");
        db.execSQL("INSERT INTO synoniem (id, woord, efficiëntieId) VALUES (29, 'Opa', 11);");
        db.execSQL("INSERT INTO synoniem (id, woord, efficiëntieId) VALUES (30, 'Papa', 11);");
        db.execSQL("INSERT INTO synoniem (id, woord, efficiëntieId) VALUES (31, 'Vader', 11);");
        db.execSQL("INSERT INTO synoniem (id, woord, efficiëntieId) VALUES (32, 'Poes', 15);");
        db.execSQL("INSERT INTO synoniem (id, woord, efficiëntieId) VALUES (33, 'Probeert', 19);");
        db.execSQL("INSERT INTO synoniem (id, woord, efficiëntieId) VALUES (34, 'Probeerde', 19);");
        db.execSQL("INSERT INTO synoniem (id, woord, efficiëntieId) VALUES (35, 'Slaapt', 20);");
        db.execSQL("INSERT INTO synoniem (id, woord, efficiëntieId) VALUES (36, 'Slapende', 20);");
        db.execSQL("INSERT INTO synoniem (id, woord, efficiëntieId) VALUES (37, 'Sliep', 20);");
        db.execSQL("INSERT INTO synoniem (id, woord, efficiëntieId) VALUES (38, 'Speelde', 21);");
        db.execSQL("INSERT INTO synoniem (id, woord, efficiëntieId) VALUES (39, 'Te spelen', 21);");
        db.execSQL("INSERT INTO synoniem (id, woord, efficiëntieId) VALUES (40, 'Speelt', 21);");
        db.execSQL("INSERT INTO synoniem (id, woord, efficiëntieId) VALUES (41, 'Vallende', 24);");
        db.execSQL("INSERT INTO synoniem (id, woord, efficiëntieId) VALUES (42, 'Vielen', 24);");
        db.execSQL("INSERT INTO synoniem (id, woord, efficiëntieId) VALUES (43, 'Te waarschuwen', 27);");
        db.execSQL("INSERT INTO synoniem (id, woord, efficiëntieId) VALUES (44, 'Waarschuwt', 27);");
        db.execSQL("INSERT INTO synoniem (id, woord, efficiëntieId) VALUES (45, 'Gewaarschuwd', 27);");
        db.execSQL("INSERT INTO synoniem (id, woord, efficiëntieId) VALUES (46, 'Waarschuwde', 27);");
        db.execSQL("INSERT INTO synoniem (id, woord, efficiëntieId) VALUES (47, 'Wakker maken', 27);");
        db.execSQL("INSERT INTO synoniem (id, woord, efficiëntieId) VALUES (48, 'Wakker te maken', 27);");
        db.execSQL("INSERT INTO synoniem (id, woord, efficiëntieId) VALUES (49, 'Maakt wakker', 27);");
        db.execSQL("INSERT INTO synoniem (id, woord, efficiëntieId) VALUES (50, 'Maakte wakker', 27);");
        db.execSQL("INSERT INTO synoniem (id, woord, efficiëntieId) VALUES (52, 'Wakker gemaakt', 27);");
        db.execSQL("INSERT INTO synoniem (id, woord, efficiëntieId) VALUES (53, 'Wekken', 27);");
        db.execSQL("INSERT INTO synoniem (id, woord, efficiëntieId) VALUES (54, 'Te wekken', 27);");
        db.execSQL("INSERT INTO synoniem (id, woord, efficiëntieId) VALUES (55, 'Wekt', 27);");
        db.execSQL("INSERT INTO synoniem (id, woord, efficiëntieId) VALUES (56, 'Gewekt', 27);");
        db.execSQL("INSERT INTO synoniem (id, woord, efficiëntieId) VALUES (57, 'Wekte', 27);");
        db.execSQL("INSERT INTO synoniem (id, woord, efficiëntieId) VALUES (58, 'Zit', 28);");
        db.execSQL("INSERT INTO synoniem (id, woord, efficiëntieId) VALUES (59, 'Zat', 28);");
    }

    private void insertAanvullenden(SQLiteDatabase db) {
        db.execSQL("INSERT INTO aanvullend (id, woord) VALUES (1, 'Aan');");
        db.execSQL("INSERT INTO aanvullend (id, woord) VALUES (2, 'Bijna');");
        db.execSQL("INSERT INTO aanvullend (id, woord) VALUES (3, 'Boven');");
        db.execSQL("INSERT INTO aanvullend (id, woord) VALUES (4, 'Dat');");
        db.execSQL("INSERT INTO aanvullend (id, woord) VALUES (5, 'Die');");
        db.execSQL("INSERT INTO aanvullend (id, woord) VALUES (6, 'Daar');");
        db.execSQL("INSERT INTO aanvullend (id, woord) VALUES (7, 'Dik');");
        db.execSQL("INSERT INTO aanvullend (id, woord) VALUES (8, 'Dikke');");
        db.execSQL("INSERT INTO aanvullend (id, woord) VALUES (9, 'Gedronken');");
        db.execSQL("INSERT INTO aanvullend (id, woord) VALUES (10, 'Hard');");
        db.execSQL("INSERT INTO aanvullend (id, woord) VALUES (11, 'Hebben');");
        db.execSQL("INSERT INTO aanvullend (id, woord) VALUES (12, 'Heeft');");
        db.execSQL("INSERT INTO aanvullend (id, woord) VALUES (13, 'Had');");
        db.execSQL("INSERT INTO aanvullend (id, woord) VALUES (14, 'Hier');");
        db.execSQL("INSERT INTO aanvullend (id, woord) VALUES (15, 'Hij');");
        db.execSQL("INSERT INTO aanvullend (id, woord) VALUES (16, 'Ik');");
        db.execSQL("INSERT INTO aanvullend (id, woord) VALUES (17, 'Kast');");
        db.execSQL("INSERT INTO aanvullend (id, woord) VALUES (18, 'Klein');");
        db.execSQL("INSERT INTO aanvullend (id, woord) VALUES (19, 'Kleine');");
        db.execSQL("INSERT INTO aanvullend (id, woord) VALUES (20, 'Kunnen');");
        db.execSQL("INSERT INTO aanvullend (id, woord) VALUES (21, 'Kan');");
        db.execSQL("INSERT INTO aanvullend (id, woord) VALUES (22, 'Kon');");
        db.execSQL("INSERT INTO aanvullend (id, woord) VALUES (23, 'Lange');");
        db.execSQL("INSERT INTO aanvullend (id, woord) VALUES (24, 'Leeg');");
        db.execSQL("INSERT INTO aanvullend (id, woord) VALUES (25, 'Lege');");
        db.execSQL("INSERT INTO aanvullend (id, woord) VALUES (26, 'Liggen');");
        db.execSQL("INSERT INTO aanvullend (id, woord) VALUES (27, 'Ligt');");
        db.execSQL("INSERT INTO aanvullend (id, woord) VALUES (28, 'Lag');");
        db.execSQL("INSERT INTO aanvullend (id, woord) VALUES (29, 'Lijken');");
        db.execSQL("INSERT INTO aanvullend (id, woord) VALUES (30, 'Lijkt');");
        db.execSQL("INSERT INTO aanvullend (id, woord) VALUES (31, 'Moe');");
        db.execSQL("INSERT INTO aanvullend (id, woord) VALUES (32, 'Moeten');");
        db.execSQL("INSERT INTO aanvullend (id, woord) VALUES (33, 'Moet');");
        db.execSQL("INSERT INTO aanvullend (id, woord) VALUES (34, 'Mogen');");
        db.execSQL("INSERT INTO aanvullend (id, woord) VALUES (35, 'Mouw');");
        db.execSQL("INSERT INTO aanvullend (id, woord) VALUES (36, 'Naar');");
        db.execSQL("INSERT INTO aanvullend (id, woord) VALUES (37, 'Naast');");
        db.execSQL("INSERT INTO aanvullend (id, woord) VALUES (38, 'Nu');");
        db.execSQL("INSERT INTO aanvullend (id, woord) VALUES (39, 'Onder');");
        db.execSQL("INSERT INTO aanvullend (id, woord) VALUES (40, 'Oude');");
        db.execSQL("INSERT INTO aanvullend (id, woord) VALUES (41, 'Pijn');");
        db.execSQL("INSERT INTO aanvullend (id, woord) VALUES (42, 'Staan');");
        db.execSQL("INSERT INTO aanvullend (id, woord) VALUES (43, 'Staat');");
        db.execSQL("INSERT INTO aanvullend (id, woord) VALUES (44, 'Stond');");
        db.execSQL("INSERT INTO aanvullend (id, woord) VALUES (45, 'Toen');");
        db.execSQL("INSERT INTO aanvullend (id, woord) VALUES (46, 'Trekken');");
        db.execSQL("INSERT INTO aanvullend (id, woord) VALUES (47, 'Trekt');");
        db.execSQL("INSERT INTO aanvullend (id, woord) VALUES (48, 'Getrokken');");
        db.execSQL("INSERT INTO aanvullend (id, woord) VALUES (49, 'Trok');");
        db.execSQL("INSERT INTO aanvullend (id, woord) VALUES (50, 'Vest');");
        db.execSQL("INSERT INTO aanvullend (id, woord) VALUES (51, 'Wakker');");
        db.execSQL("INSERT INTO aanvullend (id, woord) VALUES (52, 'Water');");
        db.execSQL("INSERT INTO aanvullend (id, woord) VALUES (53, 'Wijzen');");
        db.execSQL("INSERT INTO aanvullend (id, woord) VALUES (54, 'Wijst');");
        db.execSQL("INSERT INTO aanvullend (id, woord) VALUES (55, 'Wees');");
        db.execSQL("INSERT INTO aanvullend (id, woord) VALUES (56, 'Willen');");
        db.execSQL("INSERT INTO aanvullend (id, woord) VALUES (57, 'Wil');");
        db.execSQL("INSERT INTO aanvullend (id, woord) VALUES (58, 'Wou');");
        db.execSQL("INSERT INTO aanvullend (id, woord) VALUES (59, 'Worden');");
        db.execSQL("INSERT INTO aanvullend (id, woord) VALUES (60, 'Wordt');");
        db.execSQL("INSERT INTO aanvullend (id, woord) VALUES (61, 'Werd');");
        db.execSQL("INSERT INTO aanvullend (id, woord) VALUES (62, 'Ze');");
        db.execSQL("INSERT INTO aanvullend (id, woord) VALUES (63, 'Zij');");
        db.execSQL("INSERT INTO aanvullend (id, woord) VALUES (64, 'Zie');");
        db.execSQL("INSERT INTO aanvullend (id, woord) VALUES (65, 'Zijn');");
        db.execSQL("INSERT INTO aanvullend (id, woord) VALUES (66, 'Is');");
        db.execSQL("INSERT INTO aanvullend (id, woord) VALUES (67, 'Was');");
        db.execSQL("INSERT INTO aanvullend (id, woord) VALUES (68, 'Waren');");
        db.execSQL("INSERT INTO aanvullend (id, woord) VALUES (69, 'Zullen');");
        db.execSQL("INSERT INTO aanvullend (id, woord) VALUES (70, 'Zal');");
    }

    public void insertSubstitutiegedragen(SQLiteDatabase db) {
        db.execSQL("INSERT INTO substitutiegedrag (id, woord) VALUES (1, 'Cd');");
        db.execSQL("INSERT INTO substitutiegedrag (id, woord) VALUES (2, 'Cd-rek');");
        db.execSQL("INSERT INTO substitutiegedrag (id, woord) VALUES (3, 'Deur');");
        db.execSQL("INSERT INTO substitutiegedrag (id, woord) VALUES (4, 'Deuren');");
        db.execSQL("INSERT INTO substitutiegedrag (id, woord) VALUES (5, 'Fles');");
        db.execSQL("INSERT INTO substitutiegedrag (id, woord) VALUES (6, 'Glas');");
        db.execSQL("INSERT INTO substitutiegedrag (id, woord) VALUES (7, 'Gordijn');");
        db.execSQL("INSERT INTO substitutiegedrag (id, woord) VALUES (8, 'Handen');");
        db.execSQL("INSERT INTO substitutiegedrag (id, woord) VALUES (9, 'Living');");
        db.execSQL("INSERT INTO substitutiegedrag (id, woord) VALUES (10, 'Luidspreker');");
        db.execSQL("INSERT INTO substitutiegedrag (id, woord) VALUES (11, 'Vrouwen');");
        db.execSQL("INSERT INTO substitutiegedrag (id, woord) VALUES (12, 'Luidsprekers');");
        db.execSQL("INSERT INTO substitutiegedrag (id, woord) VALUES (13, 'Plant');");
        db.execSQL("INSERT INTO substitutiegedrag (id, woord) VALUES (14, 'Raam');");
        db.execSQL("INSERT INTO substitutiegedrag (id, woord) VALUES (15, 'Radio');");
        db.execSQL("INSERT INTO substitutiegedrag (id, woord) VALUES (16, 'Rok');");
        db.execSQL("INSERT INTO substitutiegedrag (id, woord) VALUES (17, 'Salon');");
        db.execSQL("INSERT INTO substitutiegedrag (id, woord) VALUES (18, 'Salontafel');");
        db.execSQL("INSERT INTO substitutiegedrag (id, woord) VALUES (19, 'Stereo');");
        db.execSQL("INSERT INTO substitutiegedrag (id, woord) VALUES (20, 'Venster');");
        db.execSQL("INSERT INTO substitutiegedrag (id, woord) VALUES (21, 'Voeten');");
        db.execSQL("INSERT INTO substitutiegedrag (id, woord) VALUES (22, 'Wijn');");
        db.execSQL("INSERT INTO substitutiegedrag (id, woord) VALUES (23, 'Wijnfles');");
        db.execSQL("INSERT INTO substitutiegedrag (id, woord) VALUES (24, 'Woonkamer');");
        db.execSQL("INSERT INTO substitutiegedrag (id, woord) VALUES (25, 'Tegen');");
        db.execSQL("INSERT INTO substitutiegedrag (id, woord) VALUES (26, 'Voor');");
    }

    public void insertCoherenties(SQLiteDatabase db) {
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (1, 'Moe', 'Grootvader slaapt');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (1, 'Moe', 'Man slaapt');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (1, 'Moe', 'Opa slaapt');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (1, 'Moe', 'Papa slaapt');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (1, 'Moe', 'Vader slaapt');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (1, 'Gedronken', 'Grootvader slaapt');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (1, 'Gedronken', 'Man slaapt');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (1, 'Gedronken', 'Opa slaapt');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (1, 'Gedronken', 'Papa slaapt');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (1, 'Gedronken', 'Vader slaapt');");

        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (2, 'Kat vis vangen', 'Boeken vallen');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (2, 'Poes vis vangen', 'Boeken vallen');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (2, 'Kat vis vangen', 'Vaas valt');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (2, 'Poes vis vangen', 'Vaas valt');");

        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (4, 'Kat duwt', 'Vaas valt');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (4, 'Kat staart', 'Vaas valt');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (4, 'Poes duwt', 'Vaas valt');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (4, 'Poes staart', 'Vaas valt');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (4, 'Kat duwt', 'Boeken vallen');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (4, 'Kat staart', 'Boeken vallen');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (4, 'Poes duwt', 'Boeken vallen');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (4, 'Poes staart', 'Boeken vallen');");

        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (6, 'Boeken vallen', 'Dochter waarschuwen man');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (6, 'Boeken vallen', 'Dochter te waarschuwen man');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (6, 'Boeken vallen', 'Dochter waarschuwt man');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (6, 'Boeken vallen', 'Dochter gewaarschuwd man');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (6, 'Boeken vallen', 'Dochter waarschuwde man');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (6, 'Boeken vallen', 'Dochter waarschuwen opa');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (6, 'Boeken vallen', 'Dochter te waarschuwen opa');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (6, 'Boeken vallen', 'Dochter waarschuwt opa');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (6, 'Boeken vallen', 'Dochter gewaarschuwd opa');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (6, 'Boeken vallen', 'Dochter waarschuwde opa');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (6, 'Boeken vallen', 'Dochter waarschuwen papa');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (6, 'Boeken vallen', 'Dochter te waarschuwen papa');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (6, 'Boeken vallen', 'Dochter waarschuwt papa');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (6, 'Boeken vallen', 'Dochter gewaarschuwd papa');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (6, 'Boeken vallen', 'Dochter waarschuwde papa');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (6, 'Boeken vallen', 'Dochter waarschuwen vader');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (6, 'Boeken vallen', 'Dochter te waarschuwen vader');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (6, 'Boeken vallen', 'Dochter waarschuwt vader');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (6, 'Boeken vallen', 'Dochter gewaarschuwd vader');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (6, 'Boeken vallen', 'Dochter waarschuwde vader');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (6, 'Boeken vallen', 'Dochter waarschuwen grootvader');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (6, 'Boeken vallen', 'Dochter te waarschuwen grootvader');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (6, 'Boeken vallen', 'Dochter waarschuwt grootvader');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (6, 'Boeken vallen', 'Dochter gewaarschuwd grootvader');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (6, 'Boeken vallen', 'Dochter waarschuwde grootvader');");

        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (6, 'Boeken vallen', 'Kind waarschuwen man');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (6, 'Boeken vallen', 'Kind te waarschuwen man');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (6, 'Boeken vallen', 'Kind waarschuwt man');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (6, 'Boeken vallen', 'Kind gewaarschuwd man');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (6, 'Boeken vallen', 'Kind waarschuwde man');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (6, 'Boeken vallen', 'Kind waarschuwen opa');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (6, 'Boeken vallen', 'Kind te waarschuwen opa');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (6, 'Boeken vallen', 'Kind waarschuwt opa');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (6, 'Boeken vallen', 'Kind gewaarschuwd opa');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (6, 'Boeken vallen', 'Kind waarschuwde opa');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (6, 'Boeken vallen', 'Kind waarschuwen papa');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (6, 'Boeken vallen', 'Kind te waarschuwen papa');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (6, 'Boeken vallen', 'Kind waarschuwt papa');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (6, 'Boeken vallen', 'Kind gewaarschuwd papa');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (6, 'Boeken vallen', 'Kind waarschuwde papa');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (6, 'Boeken vallen', 'Kind waarschuwen vader');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (6, 'Boeken vallen', 'Kind te waarschuwen vader');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (6, 'Boeken vallen', 'Kind waarschuwt vader');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (6, 'Boeken vallen', 'Kind gewaarschuwd vader');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (6, 'Boeken vallen', 'Kind waarschuwde vader');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (6, 'Boeken vallen', 'Kind waarschuwen grootvader');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (6, 'Boeken vallen', 'Kind te waarschuwen grootvader');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (6, 'Boeken vallen', 'Kind waarschuwt grootvader');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (6, 'Boeken vallen', 'Kind gewaarschuwd grootvader');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (6, 'Boeken vallen', 'Kind waarschuwde grootvader');");

        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (6, 'Boeken vallen', 'Kind waarschuwen man');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (6, 'Boeken vallen', 'Kind te waarschuwen man');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (6, 'Boeken vallen', 'Kind waarschuwt man');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (6, 'Boeken vallen', 'Kind gewaarschuwd man');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (6, 'Boeken vallen', 'Kind waarschuwde man');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (6, 'Boeken vallen', 'Kind waarschuwen opa');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (6, 'Boeken vallen', 'Kind te waarschuwen opa');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (6, 'Boeken vallen', 'Kind waarschuwt opa');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (6, 'Boeken vallen', 'Kind gewaarschuwd opa');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (6, 'Boeken vallen', 'Kind waarschuwde opa');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (6, 'Boeken vallen', 'Kind waarschuwen papa');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (6, 'Boeken vallen', 'Kind te waarschuwen papa');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (6, 'Boeken vallen', 'Kind waarschuwt papa');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (6, 'Boeken vallen', 'Kind gewaarschuwd papa');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (6, 'Boeken vallen', 'Kind waarschuwde papa');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (6, 'Boeken vallen', 'Kind waarschuwen vader');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (6, 'Boeken vallen', 'Kind te waarschuwen vader');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (6, 'Boeken vallen', 'Kind waarschuwt vader');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (6, 'Boeken vallen', 'Kind gewaarschuwd vader');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (6, 'Boeken vallen', 'Kind waarschuwde vader');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (6, 'Boeken vallen', 'Kind waarschuwen grootvader');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (6, 'Boeken vallen', 'Kind te waarschuwen grootvader');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (6, 'Boeken vallen', 'Kind waarschuwt grootvader');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (6, 'Boeken vallen', 'Kind gewaarschuwd grootvader');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (6, 'Boeken vallen', 'Kind waarschuwde grootvader');");

        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (6, 'Boeken vallen', 'Dochter Wakker maken');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (6, 'Boeken vallen', 'Dochter waarschuwen');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (6, 'Boeken vallen', 'Dochter waarschuwen');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (6, 'Boeken vallen', 'Dochter waarschuwen');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (6, 'Boeken vallen', 'Dochter waarschuwen');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (6, 'Boeken vallen', 'Dochter waarschuwen');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (6, 'Boeken vallen', 'Dochter waarschuwen');");


        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (6, 'Boeken vallen', 'Dochter / kind / kindje / meisje waarschuwen / te waarschuwen / waarschuwt / gewaarschuwd / waarschuwde / wakker maken / wakker te maken / maakt wakker / maakte wakker / wakker gemaakt / wekken / te wekken / wekt / gewekt / wekte grootvader / man / opa / papa / vader');");

        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (7, 'Vaas valt', 'Dochter / kind / kindje / meisje waarschuwen / te waarschuwen / waarschuwt / gewaarschuwd / waarschuwde / wakker maken / wakker te maken / maakt wakker / maakte wakker / wakker gemaakt / wekken / te wekken / wekt / gewekt / wekte grootvader / man / opa / papa / vader');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (8, 'Dochter / kind / kindje / meisje waarschuwen / te waarschuwen / waarschuwt / gewaarschuwd / waarschuwde / wakker maken / wakker te maken / maakt wakker / maakte wakker / wakker gemaakt / wekken / te wekken / wekt / gewekt / wekte grootvader / man / opa / papa / vader', 'Grootvader / man / opa / papa / vader pijn');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (9, 'Boeken vallen', 'Grootvader / man / opa / papa / vader pijn');");
        db.execSQL("INSERT INTO coherentie (id, oorzaak, gevolg) VALUES (10, 'Vaast valt', 'Grootvader / man / opa / papa / vader pijn');");
    }

        // methode wordt uitgevoerd als database geupgrade wordt
    // hierin de vorige tabellen wegdoen en opnieuw creëren
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS patiënt");
        db.execSQL("DROP TABLE IF EXISTS logopedist");
        db.execSQL("DROP TABLE IF EXISTS soortAfasie");
        db.execSQL("DROP TABLE IF EXISTS score");
        db.execSQL("DROP TABLE IF EXISTS aantalWoorden");
        db.execSQL("DROP TABLE IF EXISTS efficiëntie");
        db.execSQL("DROP TABLE IF EXISTS synoniem");
        db.execSQL("DROP TABLE IF EXISTS aanvullend");
        db.execSQL("DROP TABLE IF EXISTS substitutiegedrag");
        db.execSQL("DROP TABLE IF EXISTS coherentie");

        // Create tables again
        onCreate(db);
    }
}
