package fastcampus.com.iteratorpattern

data class Car(val brand: String)

class CarIterable(val cars: List<Car> = listOf()) : Iterable<Car> {

    override fun iterator(): Iterator<Car> = CarIterator(cars)

}

class CarIterator(val cars: List<Car> = listOf(), var index: Int = 0) : Iterator<Car> {
    override fun hasNext(): Boolean {
        return cars.size > index
    }

    override fun next(): Car {
        return cars[index++]
    }

}

fun main() {
    val carIterable = CarIterable(listOf(Car("람보르기니"), Car("페라리")))

    val iterator = carIterable.iterator()

    while (iterator.hasNext()) {
        println("브랜드 : ${iterator.next()}")
    }

}


/*
이터레이터(Iterator Pattern)은 데이터의 집합에서 데이터를 순차적으로 꺼내기 위해 만들어진 디자인 패턴을 말함
이터레이터 패턴을 사용하면 컬렉션이 변경되더라도 동일한 인터페이스를 사용해 데이터를 꺼내올 수 있기 때문에 변경사항 없이 사용할 수 있다
데이터의 집합이 얼만큼의 크기를 가진 지 알 수 없는 경우 이터레이터 패턴을 사용하면 순차적으로 데이터를 꺼내올 수 있다

에그리게잇(Aggregate)은 요소들의 집합체를 나타낸다
이터레이터는 집합체 내부에 구현된 iteraotr를 이요해 생성한다
이터레이터를 ㅏㅅ용하는 클라이언트느 생성된 이터레이터의 hasNext 함수를 사용해 데이터가 존재하는지 검사하고 next 함수를 사용해 데이터를 꺼낸다


 */