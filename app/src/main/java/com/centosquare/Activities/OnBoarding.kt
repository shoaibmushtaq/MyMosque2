package com.centosquare

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import android.support.v4.view.ViewPager
import android.view.View
import android.view.WindowManager
import android.widget.Button
import com.centosquare.Activities.PermissionActivity

class OnBoarding : AppCompatActivity() {
    private var layouts: IntArray? = null
    private var btnSkip: Button? = null
    private var btnNext: Button? = null
    private var pagerAdapter: MyPagerAdapter? = null
    private var viewPager:ViewPager?=null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding)


        viewPager = findViewById(R.id.viewpager_Onboarding)
        btnNext = findViewById(R.id.NextBtn)
        btnSkip = findViewById(R.id.SKIPbtn)

        if (!isFirstTimeStartApp()) {
            var sharedPreference = getSharedPreferences("USER_PREFERENCE", Context.MODE_PRIVATE)
            var  userId = sharedPreference!!.getInt("ID", 0)

            if (userId == 0){

                startPermissionActivity()
                finish()
            }

            else {
                startMainActivity()
                finish()
            }
        }
       btnSkip?.setOnClickListener(View.OnClickListener {

           startPermissionActivity()
           finish()

       }
       )

        btnNext?.setOnClickListener(View.OnClickListener {

            val currentPage = viewPager?.getCurrentItem()?.plus(1)

            if (currentPage!! < layouts?.size!!) {
                //move to next page
                viewPager?.setCurrentItem(currentPage)
            } else {
                startPermissionActivity()
                finish()
            }
        })

        layouts = intArrayOf(
            R.layout.slider_1,
            R.layout.slider_2,
            R.layout.slider_3,
            R.layout.slider_4,
            R.layout.slider_5
        )

        pagerAdapter=MyPagerAdapter(layouts!!,applicationContext)
        viewPager?.setAdapter(pagerAdapter)
        viewPager?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                if (position == layouts?.size?.minus(1)) {
                    //LAST PAGE
                    btnNext?.setText("Start")
                    btnSkip?.setVisibility(View.GONE)
                } else {
                    btnNext?.setText("Next")
                    btnSkip?.setVisibility(View.VISIBLE)
                }


            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })



    }

    private fun isFirstTimeStartApp(): Boolean {
        val ref = applicationContext.getSharedPreferences("IntroSliderApp", Context.MODE_PRIVATE)
        return ref.getBoolean("FirstTimeStartFlag", true)
    }

    private fun setFirstTimeStartStatus(stt: Boolean) {
        val ref = applicationContext.getSharedPreferences("IntroSliderApp", Context.MODE_PRIVATE)
        val editor = ref.edit()
        editor.putBoolean("FirstTimeStartFlag", stt)
        editor.commit()
    }

    private fun startMainActivity() {
        setFirstTimeStartStatus(false)
        startActivity(Intent(applicationContext, NavigationDrawerActivity::class.java))
        finish()
    }

    private fun startPermissionActivity(){

        setFirstTimeStartStatus(false)
        startActivity(Intent(applicationContext, PermissionActivity::class.java))
        finish()



    }

    private fun setStatusBarTransparent() {
        if (Build.VERSION.SDK_INT >= 21) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            val window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.TRANSPARENT
        }

    }

}
