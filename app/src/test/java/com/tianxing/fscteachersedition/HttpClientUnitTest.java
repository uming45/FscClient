package com.tianxing.fscteachersedition;

import com.tianxing.entity.assignment.Assignment;
import com.tianxing.entity.http.json.ImageFile;
import com.tianxing.entity.http.json.LoginInfo;
import com.tianxing.model.communication.HttpClient;
import com.tianxing.model.communication.http.FscHttpClient;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

import retrofit2.Response;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Created by tianxing on 16/9/23.
 */

public class HttpClientUnitTest {
    @Test
    public void login() {
        /*HttpClient client = new FscHttpClient();
        client.Login("username", "password");
        try {
            Thread.currentThread().sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }


    /**
     * 请求作业列表
     */
    @Test
    public void getAssignmentList() {
        HttpClient client = new FscHttpClient();
        client.Login("username", "password").subscribe(new Subscriber<LoginInfo>() {
            @Override
            public void onCompleted() {
                System.out.println("登陆成功");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(LoginInfo loginInfo) {

            }
        });

        try {
            Thread.currentThread().sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        client.requestAssignmentList("classID", 8768760098L).subscribe(new Subscriber<Response<List<Assignment>>>() {
            @Override
            public void onCompleted() {
                System.out.println("获取完成");
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(Response<List<Assignment>> listResponse) {
                System.out.println(listResponse.code());
                if (listResponse.code() == 400){
                    try {
                        System.out.println(listResponse.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(listResponse.body().size());

            }
        });


        try {
            Thread.currentThread().sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        client.uploadAssignment(new Assignment()).subscribe(new Subscriber<Response>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(Response response) {
                if (response.code() == 200){
                    System.out.println("上传成功");
                }
            }
        });

        try {
            Thread.currentThread().sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    @Test
    public void fileUpload(){
        HttpClient client = new FscHttpClient();
        client.uploadFile("/Users/tianxing/Desktop/generic-avatar.png")
                .observeOn(Schedulers.io())
                .subscribe(new Subscriber<Response<ImageFile>>() {
            @Override
            public void onCompleted() {
                System.out.println("上传成功");
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(Response<ImageFile> imageFileResponse) {
                System.out.println("收到返回数据");

            }
        });

        try {
            Thread.currentThread().sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
