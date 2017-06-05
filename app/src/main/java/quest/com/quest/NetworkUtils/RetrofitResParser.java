package quest.com.quest.NetworkUtils;


import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import quest.com.quest.models.ExamStatusModel;
import quest.com.quest.models.ListofExams;
import quest.com.quest.models.LoginResponseModel;
import quest.com.quest.models.StartExamModel;

@SuppressWarnings("unchecked")
public class RetrofitResParser <T> {

    public T parseResponse(int requestId, String response) {
        switch (requestId) {
            case RequestConstants.REQ_LOGIN_USER:
                return parseLoginResponse(response);
            case RequestConstants.REQ_FORGOT_PASSWORD:
                    return parseforgotPasswordResponse(response);
            case RequestConstants.REQ_ENABLE_EXAM:
                return parseEnableExamResponse(response);
            case RequestConstants.REQ_DISABLE_EXAM:
                return parseDisableExamResponse(response);
            case RequestConstants.REQ_START_EXAM:
                return parseStartExam(response);
            case RequestConstants.REQ_SUBMIT_EXAM:
                return parseSubmitExam(response);
            case RequestConstants.REQ_EXAMS_LIST:
                return parseExamsList(response);
            default: return (T) response;

        }

    }

    private T parseExamsList(String response) {
        ListofExams examsList =null;
        try {
            List<ListofExams.ListOfScheduledExamsBean> listofExamsBean = new ArrayList<>();
            ListofExams.ListOfScheduledExamsBean scheduledExamsBean = null;
            JSONArray ListofScheduledExams = new JSONArray(response).getJSONObject(0).getJSONArray("ListOfScheduledExams");
            int errorCode =new JSONArray(response).getJSONObject(0).optInt("ErrorCode");
            String errorMessage = new JSONArray(response).getJSONObject(0).optString("ErrorMessage");
            boolean isSuccess = new JSONArray(response).getJSONObject(0).optBoolean("is_success");


            for(int i = 0;i<ListofScheduledExams.length();i++){
                 String exam_mannual_Id = ListofScheduledExams.getJSONObject(i).getString("exam_manualID");
                 String classname = ListofScheduledExams.getJSONObject(i).getString("class");
                int class_id = ListofScheduledExams.getJSONObject(i).getInt("class_id");
                String title = ListofScheduledExams.getJSONObject(i).getString("title");
                int duration = ListofScheduledExams.getJSONObject(i).getInt("duration");
                boolean exam_status = ListofScheduledExams.getJSONObject(i).getBoolean("exam_status");
                int numberOfQuestions = ListofScheduledExams.getJSONObject(i).getInt("number_of_questions");
                int totalMarks = ListofScheduledExams.getJSONObject(i).getInt("total_marks");
                String topicCovered = ListofScheduledExams.getJSONObject(i).getString("topics_covered");
                String examDate = ListofScheduledExams.getJSONObject(i).getString("exam_date");
                String userNote = ListofScheduledExams.getJSONObject(i).getString("usernote");

                List<ListofExams.ListOfScheduledExamsBean.SubjectsBean> subjectList = new ArrayList<>();
                JSONArray subjectsArray = ListofScheduledExams.getJSONObject(i).getJSONArray("subjects");
                for (int j =0 ;j< subjectsArray.length();j++) {
                    ListofExams.ListOfScheduledExamsBean.SubjectsBean subject = null;
                       int subjectID =     subjectsArray.getJSONObject(i).getInt("subjectID");
                       int subjectClassId = subjectsArray.getJSONObject(i).getInt("class_names_classID");
                    String subjectName = subjectsArray.getJSONObject(i).optString("subject");
                    String created_At = subjectsArray.getJSONObject(i).optString("created_at");
                    String update_At = subjectsArray.getJSONObject(i).optString("updated_at");
                    String created_by = subjectsArray.getJSONObject(i).optString("created_by");
                    String deletedAt = subjectsArray.getJSONObject(i).optString("deleted_at");
                  subject = new ListofExams.ListOfScheduledExamsBean.SubjectsBean(subjectID,subjectClassId,subjectName,created_by,created_At,deletedAt,update_At);
                    subjectList.add(subject);
                }


                List<ListofExams.ListOfScheduledExamsBean.ChaptersBean> chaptersBeanList = new ArrayList<>();
                JSONArray chapterArray = ListofScheduledExams.getJSONObject(i).getJSONArray("chapters");
                for(int k =0;k<chapterArray.length();k++){
                   ListofExams.ListOfScheduledExamsBean.ChaptersBean chaptersBean = null;
                    int chapterId =     subjectsArray.getJSONObject(i).getInt("chapterID");
                    int subjectSubjectId = subjectsArray.getJSONObject(i).getInt("subjects_subjectID");
                    String chapterName = subjectsArray.getJSONObject(i).optString("chapter");
                    String created_At = subjectsArray.getJSONObject(i).optString("created_at");
                    String update_At = subjectsArray.getJSONObject(i).optString("updated_at");
                    String created_by = subjectsArray.getJSONObject(i).optString("created_by");
                    String deletedAt = subjectsArray.getJSONObject(i).optString("deleted_at");

                    chaptersBean = new ListofExams.ListOfScheduledExamsBean.ChaptersBean(chapterId,subjectSubjectId,chapterName,created_by
                    ,created_At,deletedAt,update_At);
                    chaptersBeanList.add(chaptersBean);
                }
                 scheduledExamsBean = new ListofExams.ListOfScheduledExamsBean(exam_mannual_Id,classname ,class_id,title , duration , exam_status,
                         numberOfQuestions,totalMarks,topicCovered,examDate,userNote,subjectList,chaptersBeanList);



            }

            listofExamsBean.add(scheduledExamsBean);

            examsList = new ListofExams(isSuccess,errorCode,errorMessage,listofExamsBean);


        } catch (JSONException e) {
            e.printStackTrace();
        }


        return (T) examsList;
    }


    private T parseLoginResponse(String response) {
        /*[{"user_id":2,"branch_id":1,"branch_name":"Ameerpet Branch","section":null,"class":null,
        "subject":null,"is_success":true,"session_id":"028F90CD","user_name":" ",
        "role":"admin","role_id":2,"ErrorCode":null,"ErrorMessage":null}]*/
        LoginResponseModel modelData = null;
        try {
            JSONArray jsonData = new JSONArray(response);
            JSONObject modelResponse = jsonData.getJSONObject(0);
            int user_ID = modelResponse.optInt("user_id");
            int branch_ID = modelResponse.optInt("branch_id");
            String branch_name = modelResponse.optString("branch_name");
            String section = modelResponse.optString("section");
            String className = modelResponse.optString("class");
            String subject = modelResponse.optString("subject");
            boolean isSuccess = modelResponse.optBoolean("is_success");
            String session_ID = modelResponse.optString("session_id");
            String user_name = modelResponse.optString("user_name");
            String role = modelResponse.optString("role");
            int role_Id = modelResponse.optInt("role_id");
            int error_code = modelResponse.optInt("ErrorCode");
            String errorMessage = modelResponse.optString("ErrorMessage");
            modelData = new LoginResponseModel(user_ID,branch_ID,branch_name,section,className,subject,isSuccess,session_ID,
                    user_name,role,role_Id,error_code,errorMessage);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return (T) modelData;
    }


    private T parseforgotPasswordResponse(String response) {

        return null;
    }


    private T parseEnableExamResponse(String response){
        ExamStatusModel enableResponse = null;

        try {
            JSONObject jsonObject = new JSONObject(response);
            String errorMessage = jsonObject.optString("ErrorMessage");
            int errorCode = Integer.parseInt(jsonObject.optString("ErrorCode"));
            boolean isSuccess = jsonObject.optBoolean("is_success");
            enableResponse = new ExamStatusModel(isSuccess,errorCode,errorMessage);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return (T) enableResponse;
    }

    private T parseDisableExamResponse(String response){
        ExamStatusModel disableResponse = ExamStatusModel.objectFromData(response);
        try {
            JSONObject jsonObject = new JSONObject(response);
            String errorMessage = jsonObject.optString("ErrorMessage");
            int errorCode = Integer.parseInt(jsonObject.optString("ErrorCode"));
            boolean isSuccess = jsonObject.optBoolean("is_success");
            disableResponse = new ExamStatusModel(isSuccess,errorCode,errorMessage);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return (T) disableResponse;
    }

    private T parseStartExam(String response){
        StartExamModel startExamResponse = null;


        try {
            JSONObject questionModel = new JSONArray(response).getJSONObject(0);
            int duration = questionModel.getInt("Duration");
            int totalMarks = questionModel.getInt("TotalMarks");
            String examTitle = questionModel.getString("ExamTitle");
            int negativeMarks = questionModel.getInt("NegativeMarks");
            String criticality = questionModel.getString("Critical_level");
            boolean isSuccess = questionModel.getBoolean("isSuccess");
            int errorCode = questionModel.optInt("ErrorCode");
            String errorMessage = questionModel.optString("ErrorMessage");

            StartExamModel.QuestionModel questionList = null;
            List<StartExamModel.QuestionModel> listofQUestions = new ArrayList<>();
            JSONArray questionArrayJSON = questionModel.getJSONArray("QuestionsList");
            for(int i = 0 ;i< questionArrayJSON.length() ; i++){
                int questionId = questionArrayJSON.getJSONObject(i).getInt("questionID");
                int subjectId = questionArrayJSON.getJSONObject(i).getInt("subjects_subjectID");
                int chapterId = questionArrayJSON.getJSONObject(i).getInt("chapters_chapterID");
                int classNameId = questionArrayJSON.getJSONObject(i).getInt("class_names_classID");
                String question = questionArrayJSON.getJSONObject(i).getString("question");
                int mark = questionArrayJSON.getJSONObject(i).getInt("mark");
                int criticalLevel = questionArrayJSON.getJSONObject(i).getInt("critical_level");
                boolean isImage = questionArrayJSON.getJSONObject(i).getBoolean("is_image");
                String  createdBy = questionArrayJSON.getJSONObject(i).optString("createdby");
                String  created_at = questionArrayJSON.getJSONObject(i).optString("created_at");
                String  deletedAt = questionArrayJSON.getJSONObject(i).optString("deleted_at");
                String  updateAt = questionArrayJSON.getJSONObject(i).optString("updated_at");
                int question_optionsId = questionArrayJSON.getJSONObject(i).getInt("question_optionsID");
                int question_questionsId = questionArrayJSON.getJSONObject(i).getInt("questions_questionID");
                String option1 = questionArrayJSON.getJSONObject(i).getString("option1");
                String option2 = questionArrayJSON.getJSONObject(i).getString("option2");
                String option3 = questionArrayJSON.getJSONObject(i).getString("option3");
                String option4 = questionArrayJSON.getJSONObject(i).getString("option4");
                boolean isOptionImage1 = questionArrayJSON.getJSONObject(i).getBoolean("is_option1_image");
                boolean isOptionImage2 = questionArrayJSON.getJSONObject(i).getBoolean("is_option2_image");
                boolean isOptionImage3 = questionArrayJSON.getJSONObject(i).getBoolean("is_option3_image");
                boolean isOptionImage4 = questionArrayJSON.getJSONObject(i).getBoolean("is_option4_image");

                String answer = questionArrayJSON.getJSONObject(i).getString("answer");
                String notes = questionArrayJSON.getJSONObject(i).getString("notes");
              questionList = new StartExamModel.QuestionModel(questionId,questionId,question_questionsId,question,criticality,isImage,
                      subjectId,chapterId,classNameId,notes,created_at,updateAt,deletedAt,createdBy,option1,option2,option3,option4,isOptionImage1,
                      isOptionImage2,isOptionImage3,isOptionImage4,0,answer,negativeMarks);


            }
              listofQUestions.add(questionList);

            startExamResponse= new StartExamModel(duration,totalMarks,examTitle,negativeMarks,criticality,
                    isSuccess,errorCode,errorMessage,listofQUestions);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return (T) startExamResponse;
    }

    private T parseSubmitExam(String response){
        return null;
    }
}