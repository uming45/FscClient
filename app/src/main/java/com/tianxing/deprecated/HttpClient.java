package com.tianxing.deprecated;

import com.tianxing.pojo.transfer.Comment;
import com.tianxing.pojo.transfer.receive.AssignmentDownload;
import com.tianxing.pojo.transfer.receive.ReplyReceived;
import com.tianxing.pojo.transfer.send.AssignmentUpload;
import com.tianxing.deprecated.entity.http.json.ImageFile;
import com.tianxing.deprecated.entity.info.StudentInfo;
import com.tianxing.pojo.transfer.receive.LoginInfo;
import com.tianxing.deprecated.entity.info.PersonalInfo;
import com.tianxing.pojo.transfer.receive.LoginResponse;
import com.tianxing.pojo.transfer.receive.StudentInfoResponse;
import com.tianxing.pojo.transfer.receive.TeacherInfoResponse;
import com.tianxing.pojo.transfer.send.ReplyUpload;

import java.util.List;

import retrofit2.Response;
import rx.Observable;

/**
 * Created by tianxing on 16/7/19.
 *
 */
public interface HttpClient {



    /**
     * 登录
     * */
    Observable<LoginInfo> Login(String username, String password);


    /**
     * 用户登录
     * */
    Observable<LoginResponse> userLogin(String username, String password);


    /**
     * 请求学生信息
     * */
    Observable<StudentInfoResponse> requestStudentInfo();

    /**
     * 请求老师信息
     * */
    Observable<TeacherInfoResponse> requestTeacherInfo();


    /**
     * 认证
     * */
    void authenticate(String refreshToken);

    String getToken();

    String getRefreshToken();


    /**
     * 请求用户信息 包含班级信息
     * */
    PersonalInfo getPersonalInfo();





    /**
     * 请求单个班级作业数据
     * */
    Observable<Response<List<AssignmentDownload>>> requestAssignmentList(String classID, String serialNumber);



    /**
     * 上传一个作业
     * */
    Observable<Response<Void>> uploadAssignment(AssignmentUpload assignment);



    /**
     * 上传文件
     * */
    Observable<Response<ImageFile>> uploadFile(String filePath);


    /**
     * 上传一张图片
     * */
    Observable<Response<ImageFile>> uploadImage(String imagePath);


    /***
     * 上传一组图片
     * */
    Observable<Response<ImageFile>> uploadImageSet(List<String> imagePath);



    /**
     * 请求一条作业的回复学生列表
     * */
    Observable<Response<List<StudentInfo>>> requestReplyStudentList(String AssignmentID);



    /**
     * 请求一条作业的一个学生的回复
     * */
    Observable<Response<ReplyReceived>> requestReply(String assignmentID, String studentID);


    /**
     * 上传一条回复
     *
     */

    Observable<Response<ReplyReceived>> uploadReply(ReplyUpload replyUpload);

    /**
     * 上传一条批阅
     * */
    Observable<Response<Void>> uploadComment(Comment comment);

}
