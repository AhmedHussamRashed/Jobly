package com.example.jobly;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

// واجهة API
public interface ApiService {

    //  تسجيل حساب جديد
    @POST("ar/api/register")
    Call<LoginResponse> register(@Body RegisterRequest registerRequest);

    //  تسجيل الدخول
    @POST("ar/api/login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    //  تحديث البيانات الشخصية
    @POST("/update-profile")
    Call<Void> updateProfile(@Body ProfileData profile);

    //  الحصول على جميع الوظائف
    @GET("ar/api/job-seeker/all-jobs")
    Call<JobResponse> getAllJobs();

    //  تفاصيل الوظيفة
    @GET("ar/api/job-seeker/job-details/{id}")
    Call<JobDetailsResponse> getJobDetails(@Path("id") int jobId);

    //  وضع الوظيفة في المفضلة
    @POST("ar/api/job-seeker/jobs/{id}/mark-favorite")
    Call<ResponseBody> markFavorite(@Path("id") int jobId);

    //  الحصول على الوظائف المفضلة
    @GET("ar/api/job-seeker/favorite-jobs")
    Call<FavoriteJobsResponse> getFavoriteJobs();

    //  التقديم على وظيفة
    @Multipart
    @POST("ar/api/job-seeker/jobs/applied/{id}")
    Call<ResponseBody> applyJob(
            @Path("id") int jobId,
            @Part MultipartBody.Part video
    );

    //  الحصول على جميع الشركات
    @GET("ar/api/all-companies")
    Call<CompaniesResponse> getAllCompanies();

    //  الحصول على الأسئلة الشائعة
    @GET("ar/api/faqs")
    Call<FaqsResponse> getFaqs();

    //  الحصول على السياسات
    @GET("ar/api/policies")
    Call<PoliciesResponse> getPolicies();
}
