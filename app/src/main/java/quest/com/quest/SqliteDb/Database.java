package quest.com.quest.SqliteDb;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by kumbh on 16-04-2017.
 */

public class Database extends SQLiteOpenHelper {

    private static final String DATABASE_TABLE ="QUEST_APPLICATION";


    private static final String DATABASE_STUDENT_QUESTION_TABLE ="STUDENT_EXAM_TABLE";
    private static final String COLUMN_EXAM_ID="EXAM_ID";
    private static final String COLUMN_NUMBER_OF_QUESTION ="NUMBER_OF_QUESTION";
    private static final String COLUMN_QUESTION ="QUESTION";
    private static final String COLUMN_QUESTION_OPTION_A="QUESTION_OPTION_A";
    private static final String COLUMN_QUESTION_OPTION_B="QUESTION_OPTION_B";
    private static final String COLUMN_QUESTION_OPTION_C="QUESTION_OPTION_C";
    private static final String COLUMN_QUESTION_OPTION_D="QUESTION_OPTION_D";
    private static final String COLUMN_HAS_IMAGES ="QUESTION_HAS_IMAGES";
    private static final String COLUMN_CORRECT_ANSWER="CORRECT_ANSWER";



    private static final String TABLE_ANSWERS_ATTEMPTED ="TABLE_ANSWERS_ATTEMPTED";
    private static final String COLUMN_TIME_TAKEN_TO_ATTEMPT ="TIME_TAKEN_TO_ATTEMPT_QUESTION";
    private static final String COLUMN_ANSWER_ATTEMPTED="ANSWER_ATTEMPTED";


    private static final int DATABASE_TABLE_VERSION =1;

    public Database(Context context ) {
        super(context,DATABASE_TABLE,null,DATABASE_TABLE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
     String EXAM_QUESTION_TABLE ="CREATE TABLE "+DATABASE_STUDENT_QUESTION_TABLE+
             "( "+COLUMN_EXAM_ID+"  INTEGER NOT NULL UNIQUE ,"+
             COLUMN_NUMBER_OF_QUESTION+" INTEGER NOT NULL , "+
             COLUMN_QUESTION+" TEXT NOT NULL , "+
             COLUMN_QUESTION_OPTION_A+" TEXT NOT NULL ,"+
             COLUMN_QUESTION_OPTION_B+" TEXT NOT NULL ,"+
             COLUMN_QUESTION_OPTION_C+" TEXT NOT NULL ,"+
             COLUMN_QUESTION_OPTION_D+" TEXT NOT NULL ,"+
             COLUMN_HAS_IMAGES +" BOOLEAN NOT NULL , "+
             COLUMN_CORRECT_ANSWER+" INTEGER NOT NULL )";

        String EXAM_ANSWERS_ATTEMPTED_TABLE ="CREATE TABLE "+DATABASE_STUDENT_QUESTION_TABLE+
                "( "+COLUMN_EXAM_ID+"  INTEGER NOT NULL UNIQUE ,"+
                COLUMN_NUMBER_OF_QUESTION+" INTEGER NOT NULL , "+
                COLUMN_QUESTION+" TEXT NOT NULL , "+
                COLUMN_QUESTION_OPTION_A+" TEXT NOT NULL ,"+
                COLUMN_QUESTION_OPTION_B+" TEXT NOT NULL ,"+
                COLUMN_QUESTION_OPTION_C+" TEXT NOT NULL ,"+
                COLUMN_QUESTION_OPTION_D+" TEXT NOT NULL ,"+
                COLUMN_HAS_IMAGES +" BOOLEAN NOT NULL , "+
                COLUMN_ANSWER_ATTEMPTED+" INTEGER , "+
                COLUMN_TIME_TAKEN_TO_ATTEMPT+" INTEGER ,"+
                COLUMN_CORRECT_ANSWER+" INTEGER NOT NULL )";



        db.execSQL(EXAM_QUESTION_TABLE);
        db.execSQL(EXAM_ANSWERS_ATTEMPTED_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ DATABASE_STUDENT_QUESTION_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_ANSWERS_ATTEMPTED);


    }

}
