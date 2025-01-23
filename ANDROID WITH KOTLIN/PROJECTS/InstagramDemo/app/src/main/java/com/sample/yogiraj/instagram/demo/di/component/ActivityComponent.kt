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

package com.sample.yogiraj.instagram.demo.di.component

import com.sample.yogiraj.instagram.demo.di.ActivityScope
import com.sample.yogiraj.instagram.demo.di.module.ActivityModule
import com.sample.yogiraj.instagram.demo.ui.detail.PostDetailActivity
import com.sample.yogiraj.instagram.demo.ui.detail.photo.ImmersivePhotoActivity
import com.sample.yogiraj.instagram.demo.ui.like.PostLikeActivity
import com.sample.yogiraj.instagram.demo.ui.login.LoginActivity
import com.sample.yogiraj.instagram.demo.ui.main.MainActivity
import com.sample.yogiraj.instagram.demo.ui.profile.edit.EditProfileActivity
import com.sample.yogiraj.instagram.demo.ui.signup.SignUpActivity
import com.sample.yogiraj.instagram.demo.ui.splash.SplashActivity
import dagger.Component

/**
 * Dagger Component for exposing dependencies from the Module [ActivityModule]
 * and its component [ApplicationComponent] dependency.
 *
 * @ 
 */
@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(splashActivity: SplashActivity)

    fun inject(loginActivity: LoginActivity)

    fun inject(signUpActivity: SignUpActivity)

    fun inject(mainActivity: MainActivity)

    fun inject(editProfileActivity: EditProfileActivity)

    fun inject(postLikeActivity: PostLikeActivity)

    fun inject(postDetailActivity: PostDetailActivity)

    fun inject(immersivePhotoActivity: ImmersivePhotoActivity)
}