package fastcampus.com.observerpattern

import java.util.*

class Coffee(val name: String)

// Subject
class Barista : Observable() {

    private lateinit var coffeeName: String

    fun orderCoffee(name: String) {
        this.coffeeName = name
    }

    fun makeCoffee() {
        setChanged() // 변경 여부를 업데이트
        notifyObservers(Coffee(this.coffeeName)) // 고객에게, 만들어진 커피 객체를 전달
    }

}

// Observer
// 바리스타가 커피를 만들면 고객이 응답을 뱓는다.
class Customer(val name: String) : Observer {

    override fun update(o: Observable?, arg: Any?) {
        val coffee = arg as Coffee
        println("${name}이 ${coffee.name}을 받았습니다")
    }
}


fun main() {
    val barista = Barista()
    barista.orderCoffee("아이스 아메리카노")

    val customer = Customer("고객1")
    val customer2 = Customer("고객2")
    val customer3 = Customer("고객3")

    barista.addObserver(customer) // 옵저버 등록
    barista.addObserver(customer2)
    barista.addObserver(customer3)

    barista.makeCoffee()
}

/*
옵저버 패턴이란 GoF가 소개한 디자인 패턴 중 하나로 관찰 대상이 되는 객체가 변경되면 대상 객체를 관찰하고 있는 옵저버(Observer)에게 변경사항을 통지(notify)하는 디자인 패턴을 말한다
옵저버 패턴을 사용하면 객체 간의 상호작용을 쉽게 하고 효과적으로 데이터를 전달할 수 있다

옵저버 패턴은 관찰 대상인 서브젝트(Subject)와 Subject를 관찰하는 옵저버(Objeserver)로 이뤄져 있다
하나의 서브젝트에는 1개 또는 여러 개의 옵저버를 등록할 수 있다
서브젝트의 상태가 변경되면 자신을 관찰하는 옵저버들에게 변경사항을 통지한다
서브젝트로 변경사항을 통지 받은 옵저버는 부가적인 처리를 한다

옵저버 패턴은 서브젝트와 옵저버를 상속하는 구체화(Concrete) 클래스가 존재
구체화 클래스는 서브젝트와 옵저버에 대한 상세 구현을 작성한다

서브젝트의 함수
add - 서브젝트의 상태를 관찰할 옵저버를 등록한다
remove - 등록된 옵저버를 삭제한다
notify - 서브젝트 상태가 변경되면 등록된 옵저버에 통지한다

옵저버의 함수
update - 서브젝트의 notify 내부에서 호출되며 서브젝트의 변경에 따른 부가 기능을 처리

옵저버 패터의 장점
- 옵저버 패턴을 사용하지 않았다면 고객은 일정 간격으로 커피가 완성됐는지 바리스타에게 확인하는 처리가 있어야 함
- 간격이 너무 짧으면 변경된 상태를 빠르게 확인할 수 있지만 매번 불필요한 호출이 발생하므로 성능상 문제가 발생할 수 있음
- 또한 간격이 너무 길면 변경된 상태를 즉시 확인할 수 없으므로 실시간성이 떨어질 수 있다
- 옵저버 패턴은 관찰자인 옵저버가 서브젝트의 변화를 신경 쓰지 않고 상태 변경의 주체인 서브젝트가 변경사항을 옵저버에게 알려줌으로써 앞서 언급한 문제를 해결할 수 있다
- 옵저버 패턴은 데이터를 제공하는 측에서 데이터를 소비하는 측에 통지하는 푸시(Push-Based) 방식이다


옵저버 패턴에서 서브젝트와 옵저버는 관심사에 따라 역할과 책임이 분리되어 있다
서브젝트는 옵저버가 어떤 작업을 하는지 옵저버의 상태가 어떤지에 대해 관심을 가질 필요가 없고 오직 변경 사항을 통지하는 역할만 수행하고, 하나 혹은 다수의 옵저버는 각각의 맡은 작업을 스스로 하기 때문에 옵저버가 하는 일이 서브젝트에 영향을 끼치지 않고 옵저버는 단순한 데이터의 소비자로서 존재하게 된다

 */