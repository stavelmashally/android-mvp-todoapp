package com.example.stav.todoapp.di.module;

import android.app.Application;
import android.content.Context;
import com.example.stav.todoapp.data.TodoRepository;
import com.example.stav.todoapp.data.UserRepository;
import com.example.stav.todoapp.data.network.services.TodoService;
import com.example.stav.todoapp.data.network.services.UserService;
import com.example.stav.todoapp.data.preferences.SharedPrefs;
import com.example.stav.todoapp.data.preferences.SharedPrefsHelper;
import com.example.stav.todoapp.data.repositories.TodoDataRepository;
import com.example.stav.todoapp.data.repositories.UserDataRepository;
import com.example.stav.todoapp.di.ApplicationContext;
import com.example.stav.todoapp.utils.Constants;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by stav on 1/3/18.
 */

@Module
public class AppModule {

    private Application mApplication;

    public AppModule (Application application){
        mApplication = application;
    }

    @Provides
    @Singleton
    Application provideApplication(){
        return mApplication;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    @Singleton
    SharedPrefs provideSharedPrefs(@ApplicationContext Context context){
        return new SharedPrefsHelper(context);
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(SharedPrefs sharedPrefs){
        return new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    Request original = chain.request();
                    Request customRequest;
                    String token = sharedPrefs.getToken();
                    if (token != null){ // Add token header to each request
                        customRequest = original.newBuilder()
                                .header("Authorization", token)
                                .build();
                    } else { // Does not add token header if null
                        customRequest = original;
                    }

                    Response response = chain.proceed(customRequest);
                    response.cacheResponse();
                    return response;
                }).build();
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient okHttpClient, Gson gson) {
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    TodoRepository provideTodoRepository(TodoDataRepository todoDataRepository){
        return todoDataRepository;
    }

    @Provides
    @Singleton
    UserRepository provideUserRepository(UserDataRepository userDataRepository){
        return userDataRepository;
    }

    @Provides
    @Singleton
    TodoService provideTodoService(Retrofit retrofit) {
        return retrofit.create(TodoService.class);
    }

    @Provides
    @Singleton
    UserService provideUserService(Retrofit retrofit) {
        return retrofit.create(UserService.class);
    }

}
