package com.daomingedu.art.mvp.model

import android.app.Application
import com.google.gson.Gson
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel
import com.daomingedu.art.mvp.contract.SplashContract
import javax.inject.Inject


@ActivityScope
class SplashModel
@Inject
constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager), SplashContract.Model {

    @Inject
    lateinit var mGson: Gson;
    @Inject
    lateinit var mApplication: Application;

    override fun onDestroy() {
        super.onDestroy()
    }
}
