# 今日头条屏幕适配方案终极版，一个极低成本的 Android 屏幕适配方案.



## Notice
* [主流机型设备信息，可以作为参考](https://material.io/tools/devices/)

* [功能介绍](https://juejin.im/post/5bce688e6fb9a05cf715d1c2)

* [原理分析](https://juejin.im/post/5b7a29736fb9a019d53e7ee2)



```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
```
dependencies {
	        implementation 'com.github.ewgcat:AndroidAutoSize:1.0.0'
	}
```
## Usage
### Step 1 (真的不吹牛逼，只需要以下这一步，框架就可以对项目中的所有页面进行适配)
* **请在 AndroidManifest 中填写全局设计图尺寸 (单位 dp)，如果使用副单位，则可以直接填写像素尺寸，不需要再将像素转化为 dp，详情请查看 [demo-subunits](https://github.com/JessYanCoding/AndroidAutoSize/tree/master/demo-subunits)**
```xml
<manifest>
    <application>            
        <meta-data
            android:name="design_width_in_dp"
            android:value="360"/>
        <meta-data
            android:name="design_height_in_dp"
            android:value="640"/>           
     </application>           
</manifest>
```

<a name="preview"></a>
## Preview
* 布局时的实时预览在开发阶段是一个很重要的环节，很多情况下 **Android Studio** 提供的默认预览设备并不能完全展示我们的设计图，所以我们就需要自己创建模拟设备，下面就介绍下 **dp、pt、in、mm** 这四种单位的模拟设备创建方法

* 如果您在预览时不希望在 **Preview** 中出现状态栏和导航栏, 则可以根据下图选择 **panel** 主题，使用该主题后纵向分辨率刚好填充整个预览页面，显示效果完全和设计图一致
![theme](art/theme_panel.png)

* 为了方便广大新手，所以还是将创建模拟设备的步骤贴出来，为大家操碎了心，如果觉得 **AndroidAutoSize** 不错，请一定记得 **star**，并将 **AndroidAutoSize** 推荐给您的伙伴们
![create step](art/create_step.png)

### DP
* 如果您在 **layout** 文件中使用 **dp** 作为单位进行布局 (**AndroidAutoSize** 默认支持 **dp、sp** 进行布局)，则可以根据公式 **(sqrt(纵向分辨率^2+横向分辨率^2))/dpi** 求出屏幕尺寸，然后创建模拟设备 (**只用填写屏幕尺寸和分辨率**)
![dp](art/unit_dp.png)

### PT
* 如果您在 **layout** 文件中使用 **pt** 作为单位进行布局 (需要通过 **AutoSizeConfig.getInstance().getUnitsManager().setSupportSubunits(Subunits.PT);** 打开对单位 **pt** 的支持)，则可以根据公式 **(sqrt(纵向分辨率^2+横向分辨率^2))/72** 求出屏幕尺寸，然后创建模拟设备 (**只用填写屏幕尺寸和分辨率**)
![pt](art/unit_pt.png)

### IN
* 如果您在 **layout** 文件中使用 **in** 作为单位进行布局 (需要通过 **AutoSizeConfig.getInstance().getUnitsManager().setSupportSubunits(Subunits.IN);** 打开对单位 **in** 的支持)，则可以根据公式 **sqrt(纵向分辨率^2+横向分辨率^2)** 求出屏幕尺寸，然后创建模拟设备 (**只用填写屏幕尺寸和分辨率**)
![in](art/unit_in.png)

### MM
* 如果您在 **layout** 文件中使用 **mm** 作为单位进行布局 (需要通过 **AutoSizeConfig.getInstance().getUnitsManager().setSupportSubunits(Subunits.MM);** 打开对单位 **mm** 的支持)，则可以根据公式 **(sqrt(纵向分辨率^2+横向分辨率^2))/25.4** 求出屏幕尺寸，然后创建模拟设备 (**只用填写屏幕尺寸和分辨率**)
![mm](art/unit_mm.png)

## Advanced (以下用法看不懂？答应我，认真看 demo 好不好？)

### Activity
* **当某个 Activity 的设计图尺寸与在 AndroidManifest 中填写的全局设计图尺寸不同时，可以实现 CustomAdapter 接口扩展适配参数**
```kotlin
public class CustomAdaptActivity : AppCompatActivity(), CustomAdapter {

    @Override
    public boolean isBaseOnWidth() {
        return false;
    }

    @Override
    public float getSizeInDp() {
        return 667;
    }
}
```

* **当某个 Activity 想放弃适配，请实现 CancelAdapter 接口**
```kotlin

class CustomAdapterActivity : AppCompatActivity(), CancelAdapter {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_adapt)
    }

    /**
     * 跳转到 [FragmentHost], 展示项目内部的 [Fragment] 自定义适配参数的用法
     *
     * @param view [View]
     */
    fun goCustomAdaptFragment(view: View?) {
        startActivity(Intent(applicationContext, FragmentHost::class.java))
    }

    /**
     * 是否按照宽度进行等比例适配 (为了保证在高宽比不同的屏幕上也能正常适配, 所以只能在宽度和高度之中选择一个作为基准进行适配)
     *
     * @return `true` 为按照宽度进行适配, `false` 为按照高度进行适配
     */
    override val isBaseOnWidth: Boolean
        get() = false


    override val sizeInDp: Float
        get() = 667f
}
```

### Fragment
* **首先开启支持 Fragment 自定义参数的功能**
```kotlin
AutoSizeConfig.instance!!.setCustomFragment(true)
```

* **当某个 Fragment 的设计图尺寸与在 AndroidManifest 中填写的全局设计图尺寸不同时，可以实现 CustomAdapt 接口扩展适配参数**
```kotlin
 class CustomAdaptFragment : Fragment(), CustomAdapter {
          override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
              //由于某些原因, 屏幕旋转后 Fragment 的重建, 会导致框架对 Fragment 的自定义适配参数失去效果
              //所以如果您的 Fragment 允许屏幕旋转, 则请在 onCreateView 手动调用一次 AutoSize.autoConvertDensity()
              //如果您的 Fragment 不允许屏幕旋转, 则可以将下面调用 AutoSize.autoConvertDensity() 的代码删除掉
                autoConvertDensity(activity!!, 720f, true)
              return createTextView(inflater, "Fragment-2\nView width = 360dp\nTotal width = 720dp", -0xff0100)
          }

         override val isBaseOnWidth: Boolean
                  get() = true
         override val sizeInDp: Float
                  get() = 720f
 }
```

* **当某个 Fragment 想放弃适配，请实现 CancelAdapt 接口**
```kotlin
 class CancelAdapterFragment : Fragment(),  CancelAdapter {

}
```

### Subunits (请认真看 demo-subunits，里面有详细介绍)
* 可以在 **pt、in、mm** 这三个冷门单位中，选择一个作为副单位，副单位是用于规避修改 **DisplayMetrics#density** 所造成的对于其他使用 **dp** 布局的系统控件或三方库控件的不良影响，使用副单位后可直接填写设计图上的像素尺寸，不需要再将像素转化为 **dp**


```kotlin
        AutoSizeConfig.instance!!.unitsManager
                .setSupportDP(false)
                .setSupportSP(false)
                .setSupportSubunits(Subunits.MM)
```


* **掘金**: <https://juejin.im/user/57a9dbd9165abd0061714613>



```
