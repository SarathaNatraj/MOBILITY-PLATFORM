/*
 *  
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sample.yogiraj.instagram.demo.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.sample.yogiraj.instagram.demo.BuildConfig
import com.sample.yogiraj.instagram.demo.InstagramApplication
import com.sample.yogiraj.instagram.demo.data.local.db.DatabaseService
import com.sample.yogiraj.instagram.demo.data.remote.Networking
import com.sample.yogiraj.instagram.demo.data.remote.api.FakeNetworkService
import com.sample.yogiraj.instagram.demo.data.remote.api.NetworkService
import com.sample.yogiraj.instagram.demo.data.remote.api.TokenService
import com.sample.yogiraj.instagram.demo.data.remote.auth.AccessTokenAuthenticator
import com.sample.yogiraj.instagram.demo.di.ApplicationContext
import com.sample.yogiraj.instagram.demo.di.OkHttpClientAccessAuth
import com.sample.yogiraj.instagram.demo.di.OkHttpClientNoAuth
import com.sample.yogiraj.instagram.demo.di.TempDirectory
import com.sample.yogiraj.instagram.demo.utils.common.Constants
import com.sample.yogiraj.instagram.demo.utils.common.FileUtils
import com.sample.yogiraj.instagram.demo.utils.network.FakeNetworkHelperImpl
import com.sample.yogiraj.instagram.demo.utils.network.NetworkHelper
import com.sample.yogiraj.instagram.demo.utils.rx.RxSchedulerProvider
import com.sample.yogiraj.instagram.demo.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.disposables.CompositeDisposable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File
import javax.inject.Singleton

/**
 * Dagger Test Module for creating and exposing dependencies, tied to the Application Lifecycle.
 *
 * @
 */
@Module
class ApplicationTestModule(private val application: InstagramApplication) {

    @Singleton
    @Provides
    fun provideApplication(): Application = application

    @Singleton
    @ApplicationContext
    @Provides
    fun provideContext(): Context = application

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Singleton
    @Provides
    fun provideSchedulerProvider(): SchedulerProvider = RxSchedulerProvider()

    @Singleton
    @Provides
    fun provideNetworkHelper(): NetworkHelper = FakeNetworkHelperImpl(application)

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        Networking.httpLoggingInterceptor()

    @Singleton
    @OkHttpClientAccessAuth
    @Provides
    fun provideHttpClientWithAccessAuthenticator(
        loggingInterceptor: HttpLoggingInterceptor,
        authenticator: AccessTokenAuthenticator
    ): OkHttpClient = Networking.createOkHttpClient(
        application.cacheDir,
        10L * 1024 * 1024, // 10MB Cache Size
        loggingInterceptor,
        authenticator
    )

    @Singleton
    @OkHttpClientNoAuth
    @Provides
    fun provideHttpClientWithoutAuthenticator(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient = Networking.createOkHttpClient(
        application.cacheDir,
        10L * 1024 * 1024, // 10MB Cache Size
        loggingInterceptor
    )

    @Singleton
    @Provides
    fun provideNetworkService(): NetworkService =
        com.sample.yogiraj.instagram.demo.data.remote.api.FakeNetworkService()

    @Singleton
    @Provides
    fun provideTokenService(
        @OkHttpClientNoAuth okHttpClient: OkHttpClient
    ): TokenService =
        Networking.createService(
            com.sample.yogiraj.instagram.demo.BuildConfig.API_KEY,
            com.sample.yogiraj.instagram.demo.BuildConfig.BASE_URL,
            okHttpClient,
            TokenService::class.java
        )

    @Singleton
    @Provides
    fun provideSharedPreferences(): SharedPreferences =
        application.getSharedPreferences("bootcamp-instagram-project-prefs", Context.MODE_PRIVATE)

    @Singleton
    @Provides
    fun provideDatabaseService(): DatabaseService =
        Room.databaseBuilder(
            application,
            DatabaseService::class.java,
            "bootcamp-instagram-project-db"
        ).build()

    @Singleton
    @TempDirectory
    @Provides
    fun provideTempDirectory(): File =
        FileUtils.getDirectory(application, Constants.DIRECTORY_TEMP)!!

}