package com.lishuaihua.autosize

import android.app.Activity
import com.lishuaihua.autosize.AutoSize.Companion.autoConvertDensityOfCustomAdapt
import com.lishuaihua.autosize.AutoSize.Companion.autoConvertDensityOfExternalAdaptInfo
import com.lishuaihua.autosize.AutoSize.Companion.autoConvertDensityOfGlobal
import com.lishuaihua.autosize.AutoSize.Companion.cancelAdapt
import com.lishuaihua.autosize.AutoSizeConfig.Companion.instance
import com.lishuaihua.autosize.external.ExternalAdaptInfo
import com.lishuaihua.autosize.internal.CancelAdapter
import com.lishuaihua.autosize.internal.CustomAdapter
import com.lishuaihua.autosize.utils.AutoSizeLog
import java.util.*

/**
 * ================================================
 * 屏幕适配逻辑策略默认实现类, 可通过 [AutoSizeConfig.init]
 * 和 [AutoSizeConfig.setAutoAdaptStrategy] 切换策略
 *
 * @see AutoAdaptStrategy
 * ================================================
 */
class DefaultAutoAdaptStrategy : AutoAdaptStrategy {
    override fun applyAdapt(target: Any?, activity: Activity?) {

        //检查是否开启了外部三方库的适配模式, 只要不主动调用 ExternalAdaptManager 的方法, 下面的代码就不会执行
        if (instance!!.externalAdaptManager.isRun) {
            if (instance!!.externalAdaptManager.isCancelAdapt(target!!.javaClass)) {
                AutoSizeLog.w(String.format(Locale.ENGLISH, "%s canceled the adaptation!", target.javaClass.name))
                cancelAdapt(activity!!)
                return
            } else {
                val info = instance!!.externalAdaptManager
                        .getExternalAdaptInfoOfActivity(target.javaClass)
                if (info != null) {
                    AutoSizeLog.d(String.format(Locale.ENGLISH, "%s used %s for adaptation!", target.javaClass.name, ExternalAdaptInfo::class.java.name))
                    autoConvertDensityOfExternalAdaptInfo(activity!!, info)
                    return
                }
            }
        }

        //如果 target 实现 CancelAdapt 接口表示放弃适配, 所有的适配效果都将失效
        if (target is CancelAdapter) {
            AutoSizeLog.w(String.format(Locale.ENGLISH, "%s canceled the adaptation!", target.javaClass.name))
            cancelAdapt(activity!!)
            return
        }

        //如果 target 实现 CustomAdapt 接口表示该 target 想自定义一些用于适配的参数, 从而改变最终的适配效果
        if (target is CustomAdapter) {
            AutoSizeLog.d(String.format(Locale.ENGLISH, "%s implemented by %s!", target.javaClass.name, CustomAdapter::class.java.name))
            autoConvertDensityOfCustomAdapt(activity!!, (target as CustomAdapter?)!!)
        } else {
            AutoSizeLog.d(String.format(Locale.ENGLISH, "%s used the global configuration.", target!!.javaClass.name))
            autoConvertDensityOfGlobal(activity!!)
        }
    }
}