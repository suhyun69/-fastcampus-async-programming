package fastcampus.com.future

import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

/*
    Future는 비동기 작업에 대한 결과를 알고 싶은 경우에 사용한다
    예를 들어 수행 시간이 오래 걸리는 작업이나 작업에 대한 결과를 기다리면서 다른 작업을 병행해서 수행하고 싶은 경우에 유용함
    스레드는 Runnable을 사용해 비동기 처리를 하지만 퓨처를 사용해 처리 결과를 얻기 위해선 Callback을 사용한다.

    퓨처를 사용하면 비동기 작업을 쉽게 구현할 수 있지만 몇 가지 단점을 가지고 있다.
    먼저 get 함수는 비동기 작업의 처리가 완료될 때까지 다음 코드로 넘어갖 ㅣ않고 무한정 대기하거나 지정해둔 타임아웃 시간까지 블로킹됨
    또한 퓨처를 사용하면 동시에 실행되는 한 개 이상의 비동기 작업에 대한 결과를 하나로 조합하여 처리하거나 수동으로 완료 처리(completion) 할 수 있는 방법을 지원하지 않음
 */

fun sum(a: Int, b: Int) = a + b

fun main() {
    val pool = Executors.newSingleThreadExecutor()
    val future = pool.submit(Callable {
        sum(100, 200)
    })

    println("계산 시작")
    val futureResult = future.get(1000L, TimeUnit.MILLISECONDS) // 비동기 작업의 결과를 기다린다.
    println(futureResult)
    println("계산 종료")
}