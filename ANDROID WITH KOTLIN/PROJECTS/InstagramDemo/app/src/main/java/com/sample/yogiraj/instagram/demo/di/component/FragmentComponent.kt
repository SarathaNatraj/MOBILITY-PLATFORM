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

import com.sample.yogiraj.instagram.demo.di.FragmentScope
import com.sample.yogiraj.instagram.demo.di.module.FragmentModule
import com.sample.yogiraj.instagram.demo.ui.home.HomeFragment
import com.sample.yogiraj.instagram.demo.ui.photo.PhotoFragment
import com.sample.yogiraj.instagram.demo.ui.profile.ProfileFragment
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Inject

/**
 * Dagger Component for exposing dependencies from the Module [FragmentModule]
 * and its component [ApplicationComponent] dependency.
 *
 * @
 */
@FragmentScope
@Component(dependencies = [ApplicationComponent::class], modules = [FragmentModule::class])
interface FragmentComponent {

    fun inject(homeFragment: HomeFragment)

    fun inject(photoFragment: PhotoFragment)

    fun inject(profileFragment: ProfileFragment)

}