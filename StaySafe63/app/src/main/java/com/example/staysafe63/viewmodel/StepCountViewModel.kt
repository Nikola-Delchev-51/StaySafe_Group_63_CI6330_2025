package com.example.staysafe63.viewmodel

import android.app.Application
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData


/**
 * @author K2336620
 *
 * */
class StepCountViewModel(application: Application) : AndroidViewModel(application), SensorEventListener {

    private val sensorManager = application.getSystemService(SensorManager::class.java)
    private val stepCounterSensor = sensorManager?.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)

    private val _stepCount = MutableLiveData(0)
    val stepCount: LiveData<Int> = _stepCount

    private var initialSteps: Int? = null

    fun startTracking() {
        stepCounterSensor?.let {
            sensorManager?.registerListener(this, it, SensorManager.SENSOR_DELAY_UI)
        }
    }

    fun stopTracking() {
        sensorManager?.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_STEP_COUNTER) {
            val totalSteps = event.values[0].toInt()
            if (initialSteps == null) {
                initialSteps = totalSteps
            }
            _stepCount.postValue(totalSteps - (initialSteps ?: totalSteps))
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
}
