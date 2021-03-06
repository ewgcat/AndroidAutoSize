package com.lishuaihua.autosize

import android.app.Activity
import android.app.Application.ActivityLifecycleCallbacks
import android.os.Bundle
import androidx.fragment.app.FragmentActivity

/**
 * ================================================
 * [ActivityLifecycleCallbacksImpl] 可用来代替在 BaseActivity 中加入适配代码的传统方式
 * [ActivityLifecycleCallbacksImpl] 这种方案类似于 AOP, 面向接口, 侵入性低, 方便统一管理, 扩展性强, 并且也支持适配三方库的 [Activity]
 * ================================================
 */
class ActivityLifecycleCallbacksImpl(autoAdaptStrategy: AutoAdaptStrategy?) : ActivityLifecycleCallbacks {
    /**
     * 屏幕适配逻辑策略类
     */
    private var mAutoAdaptStrategy: AutoAdaptStrategy?

    /**
     * 让 Fragment 支持自定义适配参数
     */
    private var mFragmentLifecycleCallback: FragmentLifecycleCallbackImpl? = null
    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        if (AutoSizeConfig.instance!!.isCustomFragment) {
            if (mFragmentLifecycleCallback != null && activity is FragmentActivity) {
                activity.supportFragmentManager.registerFragmentLifecycleCallbacks(mFragmentLifecycleCallback!!, true)
            }
        }

        //Activity 中的 setContentView(View) 一定要在 super.onCreate(Bundle); 之后执行
        if (mAutoAdaptStrategy != null) {
            mAutoAdaptStrategy!!.applyAdapt(activity, activity)
        }
    }

    override fun onActivityStarted(activity: Activity) {
        if (mAutoAdaptStrategy != null) {
            mAutoAdaptStrategy!!.applyAdapt(activity, activity)
        }
    }

    override fun onActivityResumed(activity: Activity) {}
    override fun onActivityPaused(activity: Activity) {}
    override fun onActivityStopped(activity: Activity) {}
    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}
    override fun onActivityDestroyed(activity: Activity) {}

    /**
     * 设置屏幕适配逻辑策略类
     *
     * @param autoAdaptStrategy [AutoAdaptStrategy]
     */
    fun setAutoAdaptStrategy(autoAdaptStrategy: AutoAdaptStrategy?) {
        mAutoAdaptStrategy = autoAdaptStrategy
        if (mFragmentLifecycleCallback != null) {
            mFragmentLifecycleCallback!!.setAutoAdaptStrategy(autoAdaptStrategy)
        }
    }

    init {
        if (AutoSizeConfig.DEPENDENCY_ANDROIDX) {
            mFragmentLifecycleCallback = FragmentLifecycleCallbackImpl(autoAdaptStrategy)
        }
        mAutoAdaptStrategy = autoAdaptStrategy
    }
}