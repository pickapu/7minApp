package com.example.sevenminuteworkout

import android.app.Dialog
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.CursorAdapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sevenminuteworkout.databinding.ActivityExerciseBinding
import com.example.sevenminuteworkout.databinding.BackCustomDialogBoxBinding
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

class ExerciseActivity : AppCompatActivity(),TextToSpeech.OnInitListener {
    private lateinit var dialogBinding:BackCustomDialogBoxBinding
    private lateinit var binding:ActivityExerciseBinding
    private var resTimer:CountDownTimer?=null
    private var resProgress=0
private var exerciseList:ArrayList<ExerciseModel>?=null
    private var currentExercisePosition=-1
    private var tts:TextToSpeech?=null
private var exerciseAdapter:ExerciseStatusAdapter?=null
    private var exerciseTimer:CountDownTimer?=null
    private var exerciseProgress=0
    private var mediaPlayer:MediaPlayer?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.actionBar)
        val actionBar=supportActionBar
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true)
        }
        binding.actionBar.setNavigationOnClickListener {
            customDialogForBackButton()
        }
        tts= TextToSpeech(this,this)
        exerciseList=Constant.defaultExerciseList()
        setupResView()
        setupExerciseRecyclerView()


    }

    override fun onDestroy() {
        if(resTimer!=null){
            resTimer!!.cancel()
            resProgress=0
        }
        if(exerciseTimer!=null){
            exerciseTimer!!.cancel()
            exerciseProgress=0
        }
        if(tts!=null){
            tts!!.stop()
            tts!!.shutdown()
        }
        if (mediaPlayer!=null){

            mediaPlayer!!.stop()
        }
        super.onDestroy()

    }
    private fun setResFunctionProgressBar(){
        binding.progressBar.progress=resProgress
        resTimer=object:CountDownTimer(10000,1000){
            override fun onTick(p0: Long) {
                resProgress++
                binding.progressBar.progress=10-resProgress
                binding.tvTimer.text=(10-resProgress).toString()

                            }

            override fun onFinish() {
                currentExercisePosition++
                exerciseList!![currentExercisePosition].setSelected(true)
                exerciseAdapter!!.notifyDataSetChanged()
                exerciseView()
            }
        }.start()
    }
    private fun setupResView(){
        try {mediaPlayer=MediaPlayer.create(applicationContext,R.raw.press_start)
            mediaPlayer!!.isLooping=false
            mediaPlayer!!.start()

        }catch (e:Exception){
            e.printStackTrace()
        }

        binding.llExerciseView.visibility=View.GONE
        binding.restView.visibility=View.VISIBLE

            binding.upcomingExercise.visibility=View.VISIBLE
            binding.tvUpcomingExerciseName.text=exerciseList!![currentExercisePosition+1].getName()
            if(resTimer!=null){
                resTimer!!.cancel()
                resProgress=0

            }
            setResFunctionProgressBar()



    }
    private fun setExerciseFunctionProgressBar(){
        binding.progressBarExercise.progress=exerciseProgress
        exerciseTimer=object:CountDownTimer(10000,1000){
            override fun onTick(p0: Long) {
                exerciseProgress++
                binding.progressBarExercise.progress=10-exerciseProgress
                binding.tvExerciseTimer.text=(10-exerciseProgress).toString()

            }

            override fun onFinish() {
                if(currentExercisePosition<exerciseList?.size!!-1){
                    exerciseList!![currentExercisePosition].setSelected(false)
                    exerciseList!![currentExercisePosition].setCompleted(true)
                    exerciseAdapter!!.notifyDataSetChanged()
                    setupResView()
                }else{
                    finish()
                    val intent = Intent(this@ExerciseActivity,Finsh::class.java)
                    startActivity(intent)
                }
            }
        }.start()
    }
    private fun exerciseView(){
        binding.restView.visibility= View.GONE
        binding.llExerciseView.visibility=View.VISIBLE
        if(exerciseTimer!=null){
            exerciseTimer!!.cancel()
            exerciseProgress=0
        }

        speakOut(exerciseList!![currentExercisePosition].getName())
        setExerciseFunctionProgressBar()
        binding.ivImage.setImageResource(exerciseList!![currentExercisePosition].getImage())
        binding.tvExerciseName.text=exerciseList!![currentExercisePosition].getName()
    }

    override fun onInit(p0: Int) {
        if(p0==TextToSpeech.SUCCESS){
            val result=tts!!.setLanguage(Locale.US)
            if(result==TextToSpeech.LANG_MISSING_DATA||result==TextToSpeech.LANG_NOT_SUPPORTED){
                Log.e("tts","the language not supported")
            }
        }else{
            Log.e("tts","Failed")
        }
    }
private fun speakOut(text:String?){
    tts!!.speak(text,TextToSpeech.QUEUE_FLUSH,null,"")
}
    private fun setupExerciseRecyclerView(){
        binding.rvExerciseStatus.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        exerciseAdapter=ExerciseStatusAdapter(exerciseList!!,this)
        binding.rvExerciseStatus.adapter=exerciseAdapter



    }
    private fun customDialogForBackButton () {
        dialogBinding = BackCustomDialogBoxBinding.inflate(layoutInflater)
        val customDialog = Dialog(this)
        customDialog.setContentView(dialogBinding.root)
        dialogBinding.yesBtn.setOnClickListener {
            finish()
            customDialog.dismiss()

        }
        dialogBinding.noBtn.setOnClickListener {
            customDialog.dismiss()
        }
        customDialog.show()
    }
}