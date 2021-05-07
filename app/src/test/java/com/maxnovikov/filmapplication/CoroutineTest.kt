package com.maxnovikov.filmapplication

import kotlinx.coroutines.*
import org.junit.Test
import kotlin.coroutines.CoroutineContext

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class CoroutineTest {

    @Test
    fun `coroutine test`() {

        runBlocking {

            val job = launch {
                println("World")
            }

            println("Hello")

            val result1 = async {
                getResult()
            }
            val result2 = async {
                delay(200)
                println("Result 2")
                10
            }

            result1.await()
            println("Result middle")
            result2.await()
        }
    }

    private suspend fun getResult(): Int {
        delay(100)
        println("Result 1")
        return 10
    }

    class ViewModel() : CoroutineScope {

        private var job: Job? = null
        override val coroutineContext: CoroutineContext
            get() = Job() + Dispatchers.Default

        fun test() {
            job?.cancel()
            job = launch {
                getResult()
            }
        }

        fun onDestroy() {
            cancel()
        }

        private suspend fun getResult(): Int {
            delay(100)
            println("Result 1")
            return 10
        }
    }
}