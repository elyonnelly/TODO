package com.example.todo

import android.app.LauncherActivity
import android.content.Context
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.example.todo.components.AppComponent
import com.example.todo.components.DaggerAppComponent
import com.example.todo.database.ListItemEntity
import com.example.todo.interactors.AddItemInteractor
import com.example.todo.interactors.EditItemInteractor
import com.example.todo.interactors.ListInteractor
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.time.LocalDate

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
open class InteractorTest {

    protected lateinit var appComponent: AppComponent

    @Before
    fun setUp() {
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }

        val appContext = InstrumentationRegistry.getTargetContext()
        appComponent = buildAppComponent(appContext)
        appComponent.repository().clear().subscribe()
    }

    private fun buildAppComponent(context : Context) : AppComponent {
        return DaggerAppComponent.builder()
            .context(context)
            .build()
    }
}
