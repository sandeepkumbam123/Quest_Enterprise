package quest.com.quest.SqliteDb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import quest.com.quest.models.AttemptedQuestionModel;
import quest.com.quest.models.QuestionModel;
import quest.com.quest.models.StartExamModel;


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
    private static final String COLUMN_CRITICALITY = "CRITICALITY";
    private static final String COLUMN_NEGATIVE_MARKS ="NEGATIVE_MARKS";
    private static final String COLUMN_TOTAL_MARKS ="TOTAL_MARKS";
    private static final String COLUMN_TITLE = "TITLE";
    private static final String COLUMN_ANSWER_MARK = "ANSWER_MARK";
    private static final String COLUMN_DURATION = "DURATION";

    private static final String COLUMN_IMAGE_OPTION_A = "OPTION_A_IMAGE";
    private static final String COLUMN_IMAGE_OPTION_B = "OPTION_B_IMAGE";
    private static final String COLUMN_IMAGE_OPTION_C = "OPTION_C_IMAGE";
    private static final String COLUMN_IMAGE_OPTION_D = "OPTION_D_IMAGE";



    private static final int DATABASE_TABLE_VERSION =1;

    public Database(Context context ) {
        super(context,DATABASE_TABLE,null,DATABASE_TABLE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        String EXAM_QUESTION_TABLE ="CREATE TABLE "+DATABASE_STUDENT_QUESTION_TABLE+
                "( "+COLUMN_EXAM_ID+"  INTEGER NOT NULL  ,"+
                COLUMN_NUMBER_OF_QUESTION+" INTEGER NOT NULL , "+
                COLUMN_QUESTION+" TEXT NOT NULL , "+
                COLUMN_QUESTION_OPTION_A+" TEXT NOT NULL ,"+
                COLUMN_QUESTION_OPTION_B+" TEXT NOT NULL ,"+
                COLUMN_QUESTION_OPTION_C+" TEXT NOT NULL ,"+
                COLUMN_QUESTION_OPTION_D+" TEXT NOT NULL ,"+
                COLUMN_HAS_IMAGES +" TEXT  , "+
                COLUMN_IMAGE_OPTION_A + " TEXT , "+
                COLUMN_IMAGE_OPTION_B + " TEXT , "+
                COLUMN_IMAGE_OPTION_C + " TEXT , "+
                COLUMN_IMAGE_OPTION_D + " TEXT , "+
                COLUMN_CORRECT_ANSWER+" INTEGER NOT NULL )";

        String EXAM_ANSWERS_ATTEMPTED_TABLE ="CREATE TABLE "+TABLE_ANSWERS_ATTEMPTED+
                "( "+COLUMN_EXAM_ID+"  INTEGER NOT NULL  ,"+
                COLUMN_NUMBER_OF_QUESTION+" INTEGER NOT NULL , "+
                COLUMN_QUESTION+" TEXT NOT NULL , "+
                COLUMN_QUESTION_OPTION_A+" TEXT NOT NULL ,"+
                COLUMN_QUESTION_OPTION_B+" TEXT NOT NULL ,"+
                COLUMN_QUESTION_OPTION_C+" TEXT NOT NULL ,"+
                COLUMN_QUESTION_OPTION_D+" TEXT NOT NULL ,"+
                COLUMN_HAS_IMAGES +" TEXT  , "+
                COLUMN_IMAGE_OPTION_A + " TEXT , "+
                COLUMN_IMAGE_OPTION_B + " TEXT , "+
                COLUMN_IMAGE_OPTION_C + " TEXT , "+
                COLUMN_IMAGE_OPTION_D + " TEXT , "+
                COLUMN_ANSWER_ATTEMPTED+" INTEGER , "+
                COLUMN_TIME_TAKEN_TO_ATTEMPT+" INTEGER ,"+
                COLUMN_CORRECT_ANSWER+" INTEGER NOT NULL ,"+
                COLUMN_DURATION +" INTEGER , "+
                COLUMN_NEGATIVE_MARKS +" INTEGER  , "+
                COLUMN_CRITICALITY +" TEXT , " +
                COLUMN_TOTAL_MARKS + " INTEGER , "+
                COLUMN_ANSWER_MARK + " INTEGER , "+

                COLUMN_TITLE+" TEXT NOT NULL  "+")";



        db.execSQL(EXAM_QUESTION_TABLE);
        db.execSQL(EXAM_ANSWERS_ATTEMPTED_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ DATABASE_STUDENT_QUESTION_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_ANSWERS_ATTEMPTED);


    }

    public void insertQuestionsintoTable(Database database,StartExamModel modelList , String examId){
        SQLiteDatabase mDB = database.getWritableDatabase();

        for (StartExamModel.QuestionModel model: modelList.getQuestionsList()){
            ContentValues columnValues = new ContentValues();
            columnValues.put(COLUMN_EXAM_ID,examId);
            columnValues.put(COLUMN_NUMBER_OF_QUESTION,model.getQuestionNumber());
            columnValues.put(COLUMN_QUESTION,model.getQuestionNumber());
            columnValues.put(COLUMN_QUESTION_OPTION_A,model.getOptionA());
            columnValues.put(COLUMN_QUESTION_OPTION_B,model.getOptionB());
            columnValues.put(COLUMN_QUESTION_OPTION_C,model.getOptionC());
            columnValues.put(COLUMN_QUESTION_OPTION_D,model.getOptionD());
            columnValues.put(COLUMN_IMAGE_OPTION_A,model.isHasOption1Image());
            columnValues.put(COLUMN_IMAGE_OPTION_B,model.isHasOption2Image());
            columnValues.put(COLUMN_IMAGE_OPTION_C,model.isHasOption3Image());
            columnValues.put(COLUMN_IMAGE_OPTION_D,model.isHasOption4Image());
            columnValues.put(COLUMN_HAS_IMAGES,false);
            columnValues.put(COLUMN_CORRECT_ANSWER,model.getCorrectAnswer());

            mDB.insert(DATABASE_STUDENT_QUESTION_TABLE,null,columnValues);

            insertQuestionintoAttemptedTable(database,model ,modelList.getCritical_level(),modelList.getDuration()
            ,modelList.getExamTitle(),modelList.getTotalMarks() , model.getNegativeMark(),model.getAnswerMark(),examId);
        }
        mDB.close();
    }

    public void deleteQuestionsListFromTable(Database db){
        SQLiteDatabase mDB = db.getWritableDatabase();
        mDB.delete(DATABASE_STUDENT_QUESTION_TABLE,null,null);
        mDB.delete(TABLE_ANSWERS_ATTEMPTED,null,null);
        mDB.close();
    }

    public void insertQuestionintoAttemptedTable(Database database , StartExamModel.QuestionModel model,
                                                 String criticality,int duration, String title ,int totalMarks , int negativeMarks ,int answerMark , String examID){
        SQLiteDatabase mDB = database.getWritableDatabase();
        ContentValues columnValues = new ContentValues();
        columnValues.put(COLUMN_EXAM_ID,examID);
        columnValues.put(COLUMN_NUMBER_OF_QUESTION,model.getQuestionNumber());
        columnValues.put(COLUMN_QUESTION,model.getQuestionNumber());
        columnValues.put(COLUMN_QUESTION_OPTION_A,model.getOptionA());
        columnValues.put(COLUMN_QUESTION_OPTION_B,model.getOptionB());
        columnValues.put(COLUMN_QUESTION_OPTION_C,model.getOptionC());
        columnValues.put(COLUMN_QUESTION_OPTION_D,model.getOptionD());
        columnValues.put(COLUMN_IMAGE_OPTION_A,model.isHasOption1Image());
        columnValues.put(COLUMN_IMAGE_OPTION_B,model.isHasOption2Image());
        columnValues.put(COLUMN_IMAGE_OPTION_C,model.isHasOption3Image());
        columnValues.put(COLUMN_IMAGE_OPTION_D,model.isHasOption4Image());
        columnValues.put(COLUMN_HAS_IMAGES,false);
        columnValues.put(COLUMN_CORRECT_ANSWER,model.getCorrectAnswer());
        columnValues.put(COLUMN_ANSWER_ATTEMPTED,0);
        columnValues.put(COLUMN_TIME_TAKEN_TO_ATTEMPT,0);
        columnValues.put(COLUMN_CRITICALITY ,criticality);
        columnValues.put(COLUMN_DURATION ,duration);
        columnValues.put(COLUMN_TITLE , title);
        columnValues.put(COLUMN_TOTAL_MARKS ,totalMarks);
        columnValues.put(COLUMN_NEGATIVE_MARKS,negativeMarks);
        columnValues.put(COLUMN_ANSWER_MARK, answerMark);
        mDB.insert(TABLE_ANSWERS_ATTEMPTED,null,columnValues);

    }

    public void insertQuestionintoAttemptedTable(Database database, AttemptedQuestionModel model){
        SQLiteDatabase mDB = database.getWritableDatabase();
        ContentValues columnValues = new ContentValues();
        columnValues.put(COLUMN_EXAM_ID,model.getExamId());
        columnValues.put(COLUMN_NUMBER_OF_QUESTION,model.getQuestionNumber());
        columnValues.put(COLUMN_QUESTION,model.getQuestionNumber());
        columnValues.put(COLUMN_QUESTION_OPTION_A,model.getOptionA());
        columnValues.put(COLUMN_QUESTION_OPTION_B,model.getOptionB());
        columnValues.put(COLUMN_QUESTION_OPTION_C,model.getOptionC());
        columnValues.put(COLUMN_QUESTION_OPTION_D,model.getOptionD());
        columnValues.put(COLUMN_HAS_IMAGES,false);
        columnValues.put(COLUMN_CORRECT_ANSWER,model.getCorrectAnswer());
        columnValues.put(COLUMN_ANSWER_ATTEMPTED,model.getAttemptedAnswer());
        columnValues.put(COLUMN_TIME_TAKEN_TO_ATTEMPT,model.getTimeTakentoAttempt());
        mDB.insert(TABLE_ANSWERS_ATTEMPTED,null,columnValues);
        mDB.close();
    }

    public void updateAttemptedAnswer(Database database ,AttemptedQuestionModel model,
                                      String criticality,int duration, String title ,int totalMarks , int negativeMarks ,String examID ){
        SQLiteDatabase mDB = database.getWritableDatabase();
        ContentValues columnValues = new ContentValues();
        columnValues.put(COLUMN_EXAM_ID,examID);
        columnValues.put(COLUMN_NUMBER_OF_QUESTION,model.getQuestionNumber());
        columnValues.put(COLUMN_QUESTION,model.getQuestionNumber());
        columnValues.put(COLUMN_QUESTION_OPTION_A,model.getOptionA());
        columnValues.put(COLUMN_QUESTION_OPTION_B,model.getOptionB());
        columnValues.put(COLUMN_QUESTION_OPTION_C,model.getOptionC());
        columnValues.put(COLUMN_QUESTION_OPTION_D,model.getOptionD());
        columnValues.put(COLUMN_HAS_IMAGES,false);
        columnValues.put(COLUMN_CORRECT_ANSWER,model.getCorrectAnswer());
        columnValues.put(COLUMN_ANSWER_ATTEMPTED,model.getAttemptedAnswer());
        columnValues.put(COLUMN_TIME_TAKEN_TO_ATTEMPT,model.getTimeTakentoAttempt());
        columnValues.put(COLUMN_CRITICALITY ,criticality);
        columnValues.put(COLUMN_DURATION ,duration);
        columnValues.put(COLUMN_TITLE , title);
        columnValues.put(COLUMN_TOTAL_MARKS ,totalMarks);
        columnValues.put(COLUMN_NEGATIVE_MARKS,negativeMarks);
        mDB.update(TABLE_ANSWERS_ATTEMPTED,columnValues,COLUMN_EXAM_ID +" =? ",new String[]{model.getExamId()});
        mDB.close();
    }


    public List<AttemptedQuestionModel> getQuestions(Database database ){
        SQLiteDatabase mDB = database.getReadableDatabase();
        String selectQuery = "Select * from "+TABLE_ANSWERS_ATTEMPTED;
        Cursor c = mDB.rawQuery(selectQuery,null);
        List<AttemptedQuestionModel> questionModels = new ArrayList<>();
        if(c.moveToFirst()){
        do{
          String examId = c.getString(c.getColumnIndex(COLUMN_EXAM_ID));
            String questionNumber = c.getString(c.getColumnIndex(COLUMN_NUMBER_OF_QUESTION));
            String question = c.getString(c.getColumnIndex(COLUMN_QUESTION));
            String optionA = c.getString(c.getColumnIndex(COLUMN_QUESTION_OPTION_A));
            String optionB = c.getString(c.getColumnIndex(COLUMN_QUESTION_OPTION_B));
            String optionC = c.getString(c.getColumnIndex(COLUMN_QUESTION_OPTION_C));
            String optionD = c.getString(c.getColumnIndex(COLUMN_QUESTION_OPTION_D));
            String imageOptionA = c.getString(c.getColumnIndex(COLUMN_IMAGE_OPTION_A));
            String imageOptionB = c.getString(c.getColumnIndex(COLUMN_IMAGE_OPTION_B));
            String imageOptionC = c.getString(c.getColumnIndex(COLUMN_IMAGE_OPTION_C));
            String imageOptionD = c.getString(c.getColumnIndex(COLUMN_IMAGE_OPTION_D));
            String imageQuestion = c.getString(c.getColumnIndex(COLUMN_HAS_IMAGES));
            int correctAnswer = c.getInt(c.getColumnIndex(COLUMN_CORRECT_ANSWER));
            int answerAttempted = c.getInt(c.getColumnIndex(COLUMN_ANSWER_ATTEMPTED));
            int timetakentoAttempt = c.getInt(c.getColumnIndex(COLUMN_TIME_TAKEN_TO_ATTEMPT));
            int negativeMarks = c.getInt(c.getColumnIndex(COLUMN_NEGATIVE_MARKS));
            String title = c.getString(c.getColumnIndex(COLUMN_TITLE));
            int totalMarks = c.getInt(c.getColumnIndex(COLUMN_TOTAL_MARKS));
            int duration = c.getInt(c.getColumnIndex(COLUMN_DURATION));
            String criticality = c.getString(c.getColumnIndex(COLUMN_CRITICALITY));
            int answerMark = c.getInt(c.getColumnIndex(COLUMN_ANSWER_MARK));

            AttemptedQuestionModel model = new AttemptedQuestionModel(question,examId,optionA,
                    optionB,optionC,optionD,answerAttempted,correctAnswer,timetakentoAttempt ,imageQuestion,
                    imageOptionA,imageOptionB,imageOptionC,imageOptionD ,title,
                    duration,totalMarks,negativeMarks,criticality ,answerMark);
            questionModels.add(model);
        }while ( (c.moveToNext()));
        }
        mDB.close();
        return questionModels;
    }

}
