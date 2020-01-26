package com.sizeofanton.passtool

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.IllegalArgumentException

class MainActivity : AppCompatActivity() {

    lateinit var model: MainModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUI()
        if (savedInstanceState != null){
            numberSeekBar.progress = savedInstanceState.getInt("length",6)
            switchUpperCase.isChecked = savedInstanceState.getBoolean("hasUpper", false)
            switchLowerCase.isChecked = savedInstanceState.getBoolean("hasLower", true)
            switchDigits.isChecked = savedInstanceState.getBoolean("hasDigits", true)
            switchSymbols.isChecked = savedInstanceState.getBoolean("hasSymbols", false)
            tvPassword.text = savedInstanceState.getString("generatedPassword", "")
        }
        model = MainModel(this)

    }


    private fun initUI(){
        initNumberSeekBarListener()
        initGenerateButtonListener()
        initSaveButtonListener()
        initButtonInfoListener()
        setDefaultPasswordParameters()
    }


    private fun initNumberSeekBarListener(){
        numberSeekBar?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            // Kludge to set min value
            override fun onProgressChanged(seek: SeekBar, progress: Int, fromUser: Boolean) {
                tvSeekValue.setText("${progress + 4}")
            }


        })
    }

    private fun initGenerateButtonListener(){
        buttonGenerate.setOnClickListener {
            val len = numberSeekBar.progress + 4
            val hasUpper = switchUpperCase.isChecked
            val hasLower = switchLowerCase.isChecked
            val hasDigit = switchDigits.isChecked
            val hasSymbols = switchSymbols.isChecked

            try {
                val password = model.generate(len, hasUpper, hasLower, hasDigit, hasSymbols)
                tvPassword.text = password
            } catch (e: IllegalArgumentException){
                showSnackbarIllegalArgument()
            }

        }
    }

    private fun initSaveButtonListener(){
        buttonSave.setOnClickListener {
            if (tvPassword.text.isEmpty()){

                showSnackbarNoPassword()
                return@setOnClickListener
            }


            model.copyPasswordToClipboard(tvPassword.text.toString())
            val snackbar = Snackbar.make(layout, getString(R.string.password_copied), Snackbar.LENGTH_SHORT)
            snackbar.show()


        }
    }

    private fun initButtonInfoListener(){
        buttonInfo.setOnClickListener {
            if (tvPassword.text.isEmpty()){
                showSnackbarNoPassword()
                return@setOnClickListener
            }

            try {
                val strength = model.measurePasswordStrength(tvPassword.text.toString())
                showSnackbarPasswordStrength(strength)
            } catch (e: IllegalArgumentException){
                showSnackbarIllegalArgument()
            }

        }
    }

    private fun showSnackbarNoPassword(){
        val snackbar: Snackbar = Snackbar.make(
            layout,
            getString(R.string.no_password_snack),
            Snackbar.LENGTH_LONG
        )

        snackbar.show()
    }

    private fun showSnackbarPasswordStrength(strength: Int){
        val snackbar: Snackbar = Snackbar.make(
            layout,
            when (strength){
                    0 -> getString(R.string.password_weak)
                    1 -> getString(R.string.password_fair)
                    2 -> getString(R.string.password_good)
                    3 -> getString(R.string.password_strong)
                    4 -> getString(R.string.password_very_strong)
                    else -> "dumb"

            },
            Snackbar.LENGTH_SHORT
        )
        snackbar.show()
    }

    private fun showSnackbarIllegalArgument(){
        val snackbar: Snackbar = Snackbar.make(
            layout,
            getString(R.string.illegal_parameters),
            Snackbar.LENGTH_SHORT
        )
        snackbar.show()
    }

    private fun setDefaultPasswordParameters(){
        numberSeekBar.progress = 2 // Actually 6, see onProgressChanged()
        switchLowerCase.isChecked = true
        switchDigits.isChecked = true
    }


    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("length", numberSeekBar.progress)
        outState.putBoolean("hasUpper", switchUpperCase.isChecked)
        outState.putBoolean("hasLower", switchLowerCase.isChecked)
        outState.putBoolean("hasDigits", switchDigits.isChecked)
        outState.putBoolean("hasSymbols", switchSymbols.isChecked)
        outState.putString("generatedPassword", tvPassword.text.toString())

        super.onSaveInstanceState(outState)
    }

}





