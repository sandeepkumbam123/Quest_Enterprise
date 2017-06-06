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
            JSONArray ListofScheduledExams = new JSONArray(response).optJSONObject(0).optJSONArray("ListOfScheduledExams");
            int errorCode =new JSONArray(response).optJSONObject(0).optInt("ErrorCode");
            String errorMessage = new JSONArray(response).optJSONObject(0).optString("ErrorMessage");
            boolean isSuccess = new JSONArray(response).optJSONObject(0).optBoolean("is_success");


            for(int i = 0;i<ListofScheduledExams.length();i++){
                String exam_mannual_Id = ListofScheduledExams.optJSONObject(i).optString("exam_manualID");
                String classname = ListofScheduledExams.optJSONObject(i).optString("class");
                int class_id = ListofScheduledExams.optJSONObject(i).optInt("class_id");
                String title = ListofScheduledExams.optJSONObject(i).optString("title");
                int duration = ListofScheduledExams.optJSONObject(i).optInt("duration");
                boolean exam_status = ListofScheduledExams.optJSONObject(i).optBoolean("exam_status");
                int numberOfQuestions = ListofScheduledExams.optJSONObject(i).optInt("number_of_questions");
                int totalMarks = ListofScheduledExams.optJSONObject(i).optInt("total_marks");
                String topicCovered = ListofScheduledExams.optJSONObject(i).optString("topics_covered");
                String examDate = ListofScheduledExams.optJSONObject(i).optString("exam_date");
                String userNote = ListofScheduledExams.optJSONObject(i).optString("usernote");

                List<ListofExams.ListOfScheduledExamsBean.SubjectsBean> subjectList = new ArrayList<>();
                JSONArray subjectsArray = ListofScheduledExams.optJSONObject(i).optJSONArray("subjects");
                for (int j =0 ;j< subjectsArray.length();j++) {
                    ListofExams.ListOfScheduledExamsBean.SubjectsBean subject = null;
                    if(subjectsArray.optJSONObject(j)!=null){
                        int subjectID =     subjectsArray.optJSONObject(j).optInt("subjectID");
                        int subjectClassId = subjectsArray.optJSONObject(j).optInt("class_names_classID");
                        String subjectName = subjectsArray.optJSONObject(j).optString("subject");
                        String created_At = subjectsArray.optJSONObject(j).optString("created_at");
                        String update_At = subjectsArray.optJSONObject(j).optString("updated_at");
                        String created_by = subjectsArray.optJSONObject(j).optString("created_by");
                        String deletedAt = subjectsArray.optJSONObject(j).optString("deleted_at");
                        subject = new ListofExams.ListOfScheduledExamsBean.SubjectsBean(subjectID,subjectClassId,subjectName,created_by,created_At,deletedAt,update_At);
                        subjectList.add(subject);
                    }
                }


                List<ListofExams.ListOfScheduledExamsBean.ChaptersBean> chaptersBeanList = new ArrayList<>();
                JSONArray chapterArray = ListofScheduledExams.optJSONObject(i).optJSONArray("chapters");
                for(int k =0;k<chapterArray.length();k++){
                    ListofExams.ListOfScheduledExamsBean.ChaptersBean chaptersBean = null;
                    if( subjectsArray.optJSONObject(k)!=null){
                        int chapterId =     subjectsArray.optJSONObject(k).optInt("chapterID");
                        int subjectSubjectId = subjectsArray.optJSONObject(k).optInt("subjects_subjectID");
                        String chapterName = subjectsArray.optJSONObject(k).optString("chapter");
                        String created_At = subjectsArray.optJSONObject(k).optString("created_at");
                        String update_At = subjectsArray.optJSONObject(k).optString("updated_at");
                        String created_by = subjectsArray.optJSONObject(k).optString("created_by");
                        String deletedAt = subjectsArray.optJSONObject(k).optString("deleted_at");

                        chaptersBean = new ListofExams.ListOfScheduledExamsBean.ChaptersBean(chapterId,subjectSubjectId,chapterName,created_by
                                ,created_At,deletedAt,update_At);
                        chaptersBeanList.add(chaptersBean);}
                }
                scheduledExamsBean = new ListofExams.ListOfScheduledExamsBean(exam_mannual_Id,classname ,class_id,title , duration , exam_status,
                        numberOfQuestions,totalMarks,topicCovered,examDate,userNote,subjectList,chaptersBeanList);

                listofExamsBean.add(scheduledExamsBean);

            }



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
            JSONObject modelResponse = jsonData.optJSONObject(0);
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
            JSONObject questionModel = new JSONArray(response).optJSONObject(0);
            int duration = questionModel.optInt("Duration");
            int totalMarks = questionModel.optInt("TotalMarks");
            String examTitle = questionModel.optString("ExamTitle");
            int negativeMarks = questionModel.optInt("NegativeMarks");
            String criticality = questionModel.optString("Critical_level");
            boolean isSuccess = questionModel.optBoolean("isSuccess");
            int errorCode = questionModel.optInt("ErrorCode");
            String errorMessage = questionModel.optString("ErrorMessage");

            StartExamModel.QuestionModel questionList = null;
            List<StartExamModel.QuestionModel> listofQUestions = new ArrayList<>();
            JSONArray questionArrayJSON = questionModel.optJSONArray("QuestionsList");
            for(int i = 0 ;i< questionArrayJSON.length() ; i++){
                int questionId = questionArrayJSON.optJSONObject(i).optInt("questionID");
                int subjectId = questionArrayJSON.optJSONObject(i).optInt("subjects_subjectID");
                int chapterId = questionArrayJSON.optJSONObject(i).optInt("chapters_chapterID");
                int classNameId = questionArrayJSON.optJSONObject(i).optInt("class_names_classID");
                String question = questionArrayJSON.optJSONObject(i).optString("question");
                int mark = questionArrayJSON.optJSONObject(i).optInt("mark");
                int criticalLevel = questionArrayJSON.optJSONObject(i).optInt("critical_level");
                boolean isImage = questionArrayJSON.optJSONObject(i).optBoolean("is_image");
                String  createdBy = questionArrayJSON.optJSONObject(i).optString("createdby");
                String  created_at = questionArrayJSON.optJSONObject(i).optString("created_at");
                String  deletedAt = questionArrayJSON.optJSONObject(i).optString("deleted_at");
                String  updateAt = questionArrayJSON.optJSONObject(i).optString("updated_at");
                int question_optionsId = questionArrayJSON.optJSONObject(i).optInt("question_optionsID");
                int question_questionsId = questionArrayJSON.optJSONObject(i).optInt("questions_questionID");
                String option1 = questionArrayJSON.optJSONObject(i).optString("option1");
                String option2 = questionArrayJSON.optJSONObject(i).optString("option2");
                String option3 = questionArrayJSON.optJSONObject(i).optString("option3");
                String option4 = questionArrayJSON.optJSONObject(i).optString("option4");
                boolean isOptionImage1 = questionArrayJSON.optJSONObject(i).optBoolean("is_option1_image");
                boolean isOptionImage2 = questionArrayJSON.optJSONObject(i).optBoolean("is_option2_image");
                boolean isOptionImage3 = questionArrayJSON.optJSONObject(i).optBoolean("is_option3_image");
                boolean isOptionImage4 = questionArrayJSON.optJSONObject(i).optBoolean("is_option4_image");

                String answer = questionArrayJSON.optJSONObject(i).optString("answer");
                String notes = questionArrayJSON.optJSONObject(i).optString("notes");
                questionList = new StartExamModel.QuestionModel(questionId,questionId,question_questionsId,question,criticality,isImage,
                        subjectId,chapterId,classNameId,notes,created_at,updateAt,deletedAt,createdBy,option1,option2,option3,option4,isOptionImage1,
                        isOptionImage2,isOptionImage3,isOptionImage4,0,answer,negativeMarks);
                listofQUestions.add(questionList);


            }

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