package com.saba21.demo.movies.app.activity

import androidx.appcompat.app.AppCompatActivity
import com.saba21.demo.movies.base.di.scopes.ActivityScope
import com.saba21.demo.movies.presentation.splash.di.SplashComponent
import dagger.BindsInstance
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {

    @Subcomponent.Factory
    interface Factory {

        fun create(@BindsInstance activity: AppCompatActivity): ActivityComponent

    }

    fun inject(activity: MainActivity)

    fun getSplashComponentFactory(): SplashComponent.Factory

}