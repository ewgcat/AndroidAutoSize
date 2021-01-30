package com.lishuaihua.autosize.utils

import android.util.Log

class AutoSizeLog private constructor() {
    companion object {
        private const val TAG = "AndroidAutoSize"
        var isDebug = false
        @JvmStatic
        fun d(message: String?) {
            if (isDebug) {
                Log.d(TAG, message!!)
            }
        }

        fun w(message: String?) {
            if (isDebug) {
                Log.w(TAG, message!!)
            }
        }

        fun e(message: String?) {
            if (isDebug) {
                Log.e(TAG, message!!)
            }
        }
    }

    init {
        throw IllegalStateException("you can't instantiate me!")
    }
}